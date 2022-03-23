/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.modelio.vcore.session.impl.load;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.debug.ThreadDumper;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.model.GetAbsoluteSymbol;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.IllegalModelManipulationException;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.fake.FakeMClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

/**
 * Used by {@link ModelLoader} to load a {@link SmDependency}.
 */
@objid ("fd26ba0e-5986-11e1-991a-001ec947ccaf")
class DependencyLoader {
    /**
     * The dependency being loaded. This field is a state. It has to be reset between usage.
     */
    @objid ("7317d5dc-c789-4d42-b6a4-82daa773e9af")
    private SmDependency dep;

    @objid ("6912e64c-ba0e-4e2f-ac0f-e81a67d3a23c")
    private Map<CacheKey, CachedDep> depCache;

    /**
     * The object owning the dependency being loaded. This field is a state. It has to be reset between usage.
     */
    @objid ("4a3e9c9c-7636-4266-a6a0-fb49bb5e61bc")
    private SmObjectImpl obj;

    @objid ("fd24579d-5986-11e1-991a-001ec947ccaf")
    public  DependencyLoader() {
        this.depCache = new HashMap<>();
    }

    /**
     * To be called at model loading session closure.
     * <p>
     * Free cached resources.
     */
    @objid ("bb2c5989-adab-497b-b180-d5ca3aac1b39")
    public void close() {
        if (!this.depCache.isEmpty()) {
            this.depCache = new HashMap<>();
        }
        
    }

    @objid ("fd24579a-5986-11e1-991a-001ec947ccaf")
    public void execute(SmObjectImpl anObj, SmDependency aDep, List<SmObjectImpl> newValues) {
        assert aDep != null;
        
        if (this.dep != null) {
            throwRecursion(anObj, aDep);
        }
        
        try {
            this.obj = anObj;
            this.dep = aDep;
        
            // Compare old and new dependency values and update if required
            List<SmObjectImpl> currentValues = getCurrentContents();
        
            if (!strictEquals(currentValues, newValues)) {
                // Change detected
        
                if (this.dep.isOrdered()) {
                    updateDefaultDependency(currentValues, newValues);
                } else {
                    updateNonOrderedDependency(currentValues, newValues);
                }
            }
        } finally {
            reset();
        }
        
    }

    /**
     * Hook to notify a dependency change.
     * @param anObj the modified object
     * @param aDep the modified relation
     * @param value the added value
     */
    @objid ("7d4d2e4b-1c43-11e2-8eb9-001ec947ccaf")
    protected void depValAdded(final SmObjectImpl anObj, final SmDependency aDep, final SmObjectImpl value) {
        // do nothing by default
    }

    /**
     * Hook to notify a dependency change.
     * @param anObj the modified object
     * @param aDep the modified relation
     * @param value the removed value
     */
    @objid ("7d4d2e54-1c43-11e2-8eb9-001ec947ccaf")
    protected void depValErased(final SmObjectImpl anObj, final SmDependency aDep, final SmObjectImpl value) {
        // do nothing by default
    }

    /**
     * Get the current dependencies values
     * @return the current dependencies values
     */
    @objid ("0095ff7e-20a4-10be-92d7-001ec947cd2a")
    protected List<SmObjectImpl> getCurrentContents() {
        // Get the current dependencies values
        
        if (this.dep.isMultiple()) {
            SmMultipleDependency d = (SmMultipleDependency) this.dep;
            List<SmObjectImpl> l = d.getValueList(this.obj.getData());
            if (l == SmMultipleDependency.EMPTY) {
                return Collections.emptyList();
            } else {
                return new ArrayList<>(l);
            }
        } else {
            SmObjectImpl value = (SmObjectImpl) this.dep.getValue(this.obj.getData());
            if (value == null) {
                return Collections.emptyList();
            } else {
                return Collections.singletonList(value);
            }
        }
        
    }

    @objid ("524c828d-064d-11e2-9eb7-001ec947ccaf")
    private final void __dumpDiff(List<SmObjectImpl> currentValues, List<SmObjectImpl> newValues) {
        Log.trace(" Diff on  '%s'.%s %s :", GetAbsoluteSymbol.get(this.obj), this.dep.getName(), this.obj.getClassOf().getName());
        
        for (SmObjectImpl value : currentValues) {
            if (!newValues.contains(value)) {
                Log.trace("   - removed: '%s' %s {%s}", GetAbsoluteSymbol.get(value), value.getClassOf().getName(), value.getUuid());
            }
        }
        
        for (SmObjectImpl value : newValues) {
            if (!currentValues.contains(value)) {
                Log.trace("   + added: '%s' %s {%s}", GetAbsoluteSymbol.get(value), value.getClassOf().getName(), value.getUuid());
            }
        }
        
    }

    @objid ("fd245792-5986-11e1-991a-001ec947ccaf")
    private void appendDepVal(SmObjectImpl anObj, final SmDependency aDep, final SmObjectImpl value) {
        // Do some cleaning first
        ISmObjectData objData = anObj.getData();
        if (aDep.getMax() == 1) {
            // Erase the old reference
            SmObjectImpl oldValue = (SmObjectImpl) aDep.getValue(objData);
            if (oldValue == value) {
                // Nothing to do
                return;
            } else if (oldValue != null) {
                // Erase old reference
                eraseDepVal(anObj, aDep, oldValue);
            }
        } else {
            // Prevent dep_val from being twice in the list
            eraseDepVal(anObj, aDep, value);
        }
        
        // Do the job
        aDep.add(objData, value);
        
        depValAdded(anObj, aDep, value);
        
        // If the dependency is symmetric and have to propagate
        propagateAppendToOpposite(anObj, aDep, value);
        
    }

    @objid ("fd245791-5986-11e1-991a-001ec947ccaf")
    private void eraseDepVal(SmObjectImpl anObj, SmDependency aDep, final SmObjectImpl value) {
        // do the job
        boolean returnCode = aDep.remove(anObj.getData(), value);
        
        if (returnCode) {
            // Notify change
            depValErased(anObj, aDep, value);
        
            // update the symetric dependency
            propagateEraseToOpposite(anObj, aDep, value);
        }
        
    }

    @objid ("526d9dbc-75ee-4461-9436-5af4679503da")
    private SmDependency getCachedDep(ISmObjectData object, SmDependency original) {
        if (original != null && original.isMultiple() && original.getValueAsCollection(object).size() > 20) {
            return this.depCache.computeIfAbsent(new CacheKey(object, original),
                    (k) -> new CachedDep(original));
        }
        return original;
    }

    @objid ("e9ea3805-356c-11e2-a87b-001ec947ccaf")
    private static String getDebugSymbol(SmObjectImpl obj, Throwable t) {
        /*
         * Commented: This may throw "java.lang.IllegalStateException: Reentrant call not allowed." and make fragment go down.
         *
         * try {
         * return "('" + GetAbsoluteSymbol.get(obj) + "' {" + obj.getUuid() + "} " + obj.getMClass().getName() + ")";
         * } catch (Exception | LinkageError e) {
         * if (t != null) {
         * t.addSuppressed(e);
         * }
         * try {
         * return "(" + obj.toString() + ")";
         * } catch (Exception | LinkageError e2) {
         * e.addSuppressed(e2);
         * return "(" + obj.getMClass().getName() + ")";
         * }
         * }
         */
        try {
            return "(" + obj.toString() + ")";
        } catch (Exception | LinkageError e) {
            if (t != null) {
                t.addSuppressed(e);
            }
            return "(" + obj.getMClass().getName() + ")";
        }
        
    }

    /**
     * Compute an error message telling wich part couldn't be loaded.
     * @param destObject the object that couldn't be added
     * @param e the error
     * @return the error message.
     */
    @objid ("e9ea37fe-356c-11e2-a87b-001ec947ccaf")
    private String getErrorMsg(SmObjectImpl destObject, RuntimeException e) {
        String err;
        try {
            String origMsg = e.getLocalizedMessage();
            if (origMsg == null || origMsg.isEmpty()) {
                origMsg = e.toString();
            }
        
            err = MessageFormat.format("Cannot add {0} to {1}.{2}: {3}",
                    getDebugSymbol(destObject, e),
                    getDebugSymbol(this.obj, e),
                    this.dep.getName(),
                    origMsg);
        } catch (Exception | LinkageError t) {
            e.addSuppressed(t);
            err = MessageFormat.format("Failed loading {0} dependency: {1}",
                    this.dep.getName(),
                    e);
        }
        return err;
    }

    @objid ("9d9edb66-8a24-432b-b5c2-403da00b51e9")
    private SmDependency getOppositeDep(final SmDependency aDep, final SmObjectImpl value) {
        SmDependency oppositeDep = aDep.getSymetric();
        
        if (oppositeDep == null) {
            Log.trace("DependencyLoader: %s dependency on %s has no opposite dep.", aDep, value);
        }
        
        MClass classOf = value.getClassOf();
        if (oppositeDep != null && classOf.isFake()) {
            oppositeDep = (SmDependency) ((FakeMClass) classOf).getSameDependency(oppositeDep);
        }
        return oppositeDep;
    }

    @objid ("9dc9e0dd-66ca-432f-9f30-11388eff99e6")
    private void logShellDepVal(SmObjectImpl value) {
        if (value.isShell() && this.obj.hasStatus(IRStatus.USERWRITE)) {
            Log.trace("DependencyLoader: Loading %s unresolved reference into %s.%s relation.", getDebugSymbol(value, null), getDebugSymbol(this.obj, null), this.dep.getName());
        }
        
    }

    @objid ("fd24578f-5986-11e1-991a-001ec947ccaf")
    private void propagateAppendToOpposite(final SmObjectImpl anObj, final SmDependency aDep, final SmObjectImpl value) {
        if (value != null) {
            SmDependency symetricDep = getOppositeDep(aDep, value);
        
            if (symetricDep != null) {
        
                ISmObjectData valueData = value.getData();
                if (symetricDep.isMultiple()) {
                    SmDependency cachedSym = getCachedDep(valueData, symetricDep);
        
                    // ensure the value won't be twice in the list
                    cachedSym.remove(valueData, anObj);
        
                    // Propagate to symetricDep the append
                    cachedSym.add(valueData, anObj);
        
                    // Notify change
                    depValAdded(value, symetricDep, anObj);
                } else {
                    // Remove from the symetricDep its old value with propagation.
                    SmObjectImpl oldValue = (SmObjectImpl) symetricDep.getValue(valueData);
                    if (oldValue == null) {
                        // just continue
                    } else if (oldValue.equals(anObj)) {
                        // nothing more to do, exit now.
                        return;
                    } else {
                        // Remove the old value with propagation.
                        eraseDepVal(value, symetricDep, oldValue);
                    }
        
                    // Propagate to symetricDep the append
                    symetricDep.add(valueData, anObj);
        
                    // Notify change
                    depValAdded(value, symetricDep, anObj);
                }
            }
        }
        
    }

    @objid ("fd245790-5986-11e1-991a-001ec947ccaf")
    private void propagateEraseToOpposite(final SmObjectImpl anObj, final SmDependency aDep, final SmObjectImpl value) {
        if (value != null) {
            SmDependency oppDep = getOppositeDep(aDep, value);
        
            if (oppDep != null) {
                ISmObjectData valueData = value.getData();
        
                SmDependency cachedDep = getCachedDep(valueData, oppDep);
                cachedDep.remove(valueData, anObj);
        
                depValErased(value, oppDep, anObj);
            }
        }
        
    }

    /**
     * Reset the state of this DependencyLoader
     */
    @objid ("009585bc-20a4-10be-92d7-001ec947cd2a")
    private void reset() {
        this.obj = null;
        this.dep = null;
        
    }

    @objid ("5e23d777-afc4-4f07-ab6d-3a4cd52a71a5")
    private static boolean strictEquals(List<SmObjectImpl> a, List<SmObjectImpl> a2) {
        if (a == a2) {
            return true;
        }
        if (a == null || a2 == null) {
            return false;
        }
        
        int length = a.size();
        if (a2.size() != length) {
            return false;
        }
        
        for (int i = 0; i < length; i++) {
            if (a.get(i) != a2.get(i)) {
                return false;
            }
        }
        return true;
    }

    @objid ("009732ae-20a4-10be-92d7-001ec947cd2a")
    private void throwRecursion(SmObjectImpl anObj, SmDependency aDep) throws IllegalStateException {
        throw new IllegalStateException("Reentrant call of Dependencyloader.execute({"
                + anObj.getUuid() + "} " + anObj.getClassOf().getName() + ", " + aDep
                + "): already loading {"
                + this.obj.getUuid() + "} " + this.obj.getClassOf().getName()
                + "." + this.dep);
        
    }

    @objid ("fd245798-5986-11e1-991a-001ec947ccaf")
    private void updateDefaultDependency(List<SmObjectImpl> currentValues, List<SmObjectImpl> newValues) {
        // STEP 1 - detach all old values
        for (SmObjectImpl value : currentValues) {
            eraseDepVal(this.obj, this.dep, value);
        }
        
        // STEP 2 - add new values to the dependency
        for (SmObjectImpl value : newValues) {
        
            try {
                logShellDepVal(value);
        
                appendDepVal(this.obj, this.dep, value);
            } catch (RuntimeException e) {
                final String err = getErrorMsg(value, e);
                throw new RuntimeException(err, e);
            }
        }
        
    }

    @objid ("fd245796-5986-11e1-991a-001ec947ccaf")
    private void updateNonOrderedDependency(List<SmObjectImpl> currentValues, List<SmObjectImpl> newValues) {
        if (currentValues.size() == newValues.size()) {
            // Roles order is often swapped, this is useless
            boolean changeFound = false;
        
            for (SmObjectImpl role : currentValues) {
                changeFound = !newValues.contains(role);
                if (changeFound) {
                    break;
                }
            }
        
            // Update the dependency only if a real change is found
            if (changeFound) {
                updateDefaultDependency(currentValues, newValues);
            }
        } else {
            updateDefaultDependency(currentValues, newValues);
        }
        
    }

    @objid ("4f5efff6-6353-4b02-895d-db50a09bd9a9")
    private static class CacheKey {
        @objid ("b3e02d72-dbe7-4e49-a14e-848177ee8e1f")
        private final SmDependency orig;

        @objid ("0cebd546-d437-4a3d-8fda-20968583a6a7")
        private final ISmObjectData owner;

        @objid ("3568d877-401a-4252-afbd-3f64fcc055eb")
        public  CacheKey(ISmObjectData owner, SmDependency orig) {
            super();
            this.orig = orig;
            this.owner = owner;
            
        }

        @objid ("679554fd-c786-44e0-80dd-7a970d68b44f")
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (this.orig == null ? 0 : this.orig.hashCode());
            result = prime * result + (this.owner == null ? 0 : this.owner.hashCode());
            return result;
        }

        @objid ("534210a4-0d21-47ce-8bc5-bb9963db6ab4")
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            
            CacheKey other = (CacheKey) obj;
            return Objects.equals(this.orig, other.orig) && Objects.equals(this.owner, other.owner);
        }

    }

    @objid ("681970ea-20e6-4016-b69d-c98468ab04c3")
    private static class CachedDep extends SmDependency {
        /**
         * count uses of {@link #remove(ISmObjectData, SmObjectImpl)}
         */
        @objid ("08232213-8c80-4181-b551-d546bb4aaca8")
        private int useCount;

        /**
         * use count at which the cache is filled
         */
        @objid ("ed6533e9-4112-41f9-9600-72257ef84b71")
        private static final int MIN_USES = 2;

        @objid ("4d3bc0b6-a373-41e6-b8ab-f28224f714c2")
        private Set<SmObjectImpl> cache;

        @objid ("a447ea3c-9f60-45b7-bdff-081bd4210e15")
        private final SmDependency orig;

        /**
         * Global synchronization lock to guard against concurrent dependency content modifications.
         */
        @objid ("f978ef07-df1d-429e-806c-a177efe5e18e")
        private static final Object SYNC = new Object();

        @objid ("6bf68b71-4bbe-47ba-af38-a3828082e544")
        public  CachedDep(SmDependency orig) {
            this.orig = orig;
            
            init(orig.getName(),
                    orig.getOwner(),
                    orig.getType(),
                    orig.getMinCardinality(),
                    orig.getMaxCardinality(),
                    orig.getDirectives().toArray(new SmDirective[0]));
            postInit();
            
        }

        @objid ("1b41afe3-d11a-4a31-88de-4529f7e6fe73")
        @Override
        public boolean add(ISmObjectData obj, SmObjectImpl value) {
            synchronized (CachedDep.SYNC) {
                if (this.cache == null) {
                    return this.orig.add(obj, value);
                } else {
                    if (this.cache.add(value)) {
                        this.orig.add(obj, value);
                        return true;
                    }
                    return false;
                }
            }
            
        }

        @objid ("2878eec0-8589-42e3-bead-572e316bb585")
        @Override
        public SmDependency getSymetric() {
            return this.orig.getSymetric();
        }

        @objid ("b31023cb-2a84-4c68-9c8c-77209b132f2d")
        @Override
        public Object getValue(ISmObjectData object) {
            return this.orig.getValue(object);
        }

        @objid ("773eecf5-d884-44cf-a48d-6474686a80da")
        @Override
        public Collection<SmObjectImpl> getValueAsCollection(ISmObjectData object) {
            if (this.cache != null) {
                return this.cache;
            } else {
                return this.orig.getValueAsCollection(object);
            }
            
        }

        @objid ("ede2dd83-710f-4e17-8274-abc275ff1545")
        @Override
        public void insert(ISmObjectData obj, SmObjectImpl value, int index) {
            throw new UnsupportedOperationException();
        }

        @objid ("d6e7dee7-be1b-4d0d-8764-0462039fb14c")
        @Override
        public void moveRef(ISmObjectData refered, SmObjectImpl ref, int offset) {
            throw new UnsupportedOperationException();
        }

        @objid ("417aabd6-cf6a-4697-a9e1-81d3b089a3e6")
        @Override
        public boolean remove(ISmObjectData obj, SmObjectImpl value) {
            synchronized (CachedDep.SYNC) {
                if (++this.useCount == CachedDep.MIN_USES) {
                    fillCache(obj);
                }
            
                if (this.cache != null) {
                    if (this.cache.remove(value)) {
                        this.orig.remove(obj, value);
                        return true;
                    }
                    return false;
                } else {
                    return this.orig.remove(obj, value);
                }
            }
            
        }

        @objid ("00bb7d3c-0175-4e76-90de-e9a3a8d0fdb7")
        @Override
        public void assertValueType(SmObjectImpl smObjectImpl, Object value) throws IllegalArgumentException, IllegalModelManipulationException {
            this.orig.assertValueType(smObjectImpl, value);
        }

        @objid ("505e1e50-55f5-4b47-a2c8-e4c5ece88202")
        private void fillCache(ISmObjectData obj) {
            int ntry = 0;
            while (true) {
                try {
                    Collection<SmObjectImpl> origContent = this.orig.getValueAsCollection(obj);
                    this.cache = new HashSet<>(origContent);
                    return;
                } catch (ConcurrentModificationException e) {
                    if (++ntry > 5) {
                        // 29/05/2017 : should not occur anymore thanks to SYNC
                        // Add thread dump to the exception and rethrow
                        throw ThreadDumper.get().getAllThreads(false).addAsSupressed(e);
                    }
            
                    // The model is probably being modified in another thread.
                    // Sleep some time then try again.
                    try {
                        final int SLEEPTIME = 20;
                        Log.trace("%s on try %d loading %s on %s. Sleeping %d ms.", e.toString(), ntry, this.orig, obj, SLEEPTIME);
                        Log.trace(e);
                        Thread.sleep(SLEEPTIME);
                    } catch (InterruptedException e1) {
                        e.addSuppressed(e1);
                        throw e;
                    }
                }
            }
            
        }

    }

}
