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
package org.modelio.vcore.smkernel.meta;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.collections.TopologicalSorter.CyclicDependencyException;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.smkernel.SmLiveId;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MetaclassAlreadyExistException;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptor;
import org.modelio.vcore.smkernel.meta.fake.FakeMetamodelFragment;

/**
 * Metamodel represent the so-called 'runtime metamodel' used by Modelio kernel at run time.
 * <p>
 * The runtime metamodel of Modelio is composed of several fragments
 * <ul>
 * <li>Modelio metamodel fragments covering UML and BPMN standards, provided by Modelio application, always present</li>
 * <li>extension metamodel fragments provided by application extensions (eg: Modules)</li>
 * </ul>
 * Note the Modelio kernel does not know anything about metamodel fragment providers and that the above description is there only
 * for the sake of clarity. Practically, once initialized with its composing fragments the Metamodel is only a repository for known
 * SmClass instances.
 * @since Modelio 3.4
 */
@objid ("3197dca3-5748-4365-939a-caf142a80f2e")
public class SmMetamodel implements MMetamodel {
    @objid ("a50aba67-e512-4bfb-b95d-37ceb0428548")
    private static final boolean TRACE_LOADING = false;

    @objid ("a322e9e8-15cb-40ba-8e6e-ab3180cf494d")
    public static boolean TRACE_SHORT_METACLASSNAMES = true;

    /**
     * A cache of registered metaclass to provide a fast acccess by name. This cache can only be maintained in synch with the
     * registered classes table above as long as it is not directly manipulated. Only use the provided accessors.
     */
    @objid ("8336756b-8721-426f-bf7b-0a4b751e61aa")
    private final Map<Class<? extends MObject>, SmClass> byInterfaceCache = new HashMap<>();

    /**
     * A cache of registered meta classes to provide a fast access by name.
     * <p>
     * This cache can only be maintained in synch with the
     * registered classes table above as long as it is not directly manipulated.
     * Only use the provided accessors.
     */
    @objid ("7e5cec9e-d30b-4070-b59d-901a49d745d2")
    private final Map<String, SmClass> byQualifiedNameCache = new HashMap<>();

    @objid ("e775d656-bd13-4822-a19e-a4847eabf79f")
    private final Map<String, SmClass> byShortNameCache = new HashMap<>();

    @objid ("3520f2a0-8d11-429f-9ac7-dab3d1356f90")
    private final Map<String, ISmMetamodelFragment> fragments = new HashMap<>();

    @objid ("db6d575e-873e-4cb5-a005-7812d6f49c1c")
    private final SmExpert mExpert = new SmExpert();

    /**
     * All registered metaclass as a table to provide a direct indexed access from {@link SmLiveId#getClassId(long) a metaclass id }.
     * <p>
     * This is the reference list of all known metaclasses. Do not use this data field directly.
     * <p>
     * In the case a metaclass should be removed from this list don't use {@link List#remove(Object)} but
     * {@link List#set(int, Object)} to nullify the entry so that other elements keep their index.
     */
    @objid ("43fc6e9f-c9da-4c5f-a835-1967c0e54451")
    private final List<SmClass> metaclasses = new ArrayList<>();

    @objid ("f141e6e5-6002-4ce6-96f7-1ab849cdaf80")
    private final Collection<ISmMetamodelFragment> regularFragments = new ArrayList<>();

    /**
     * Default constructor.
     */
    @objid ("dd1f7d4e-2e99-4518-9767-8e1fba21ff10")
    public  SmMetamodel() {
        initKernelFragment();
    }

    /**
     * Add a metamodel fragment to the runtime metamodel.
     * <p>
     * Returns the fake metaclasses that already existed. These metaclasses have been replaced by real ones.
     * Model objects instantiated from old fake metaclasses are not valid anymore.
     * @param mmFragment the metamodel fragment to add
     * @return the fake metaclasses that already were replaced.
     */
    @objid ("8162edba-4f86-4fc1-86f9-59c770700442")
    public Collection<SmClass> addMetamodelFragment(ISmMetamodelFragment mmFragment) {
        final List<SmClass> newMetaclasses = new ArrayList<>();
        final List<SmClass> removedFakes = new ArrayList<>(0);
        
        if (TRACE_LOADING) {
            Log.trace("Adding %s v%s metamodel fragment...", mmFragment.getName(), mmFragment.getVersion().toString());
        }
        
        
        // Register the fragment
        ISmMetamodelFragment existing = this.fragments.get(mmFragment.getName());
        if (existing != null && !existing.isFake()) {
            throw new IllegalArgumentException(String.format("The '%s' metamodel fragment already exists as %s.", existing.getName(), existing));
        }
        
        checkDependencies(mmFragment);
        
        // First pass: create and register all metaclasses
        // Avoids adding the meta class twice. This is because the process of creating a SmClass
        // might also create its parent meta class.
        for (SmClass smClass : mmFragment.createMetaclasses()) {
        
            // Remove fake metaclass
            SmClass fakesmClass = this.byQualifiedNameCache.get(smClass.getQualifiedName());
            if (fakesmClass != null && fakesmClass.isFake()) {
                removeFakeMetaclass(fakesmClass);
                removedFakes.add(fakesmClass);
            }
        
            registerMetaclass(smClass);
        
            newMetaclasses.add(smClass);
        }
        
        // Second pass: load the metaclasses content
        for (SmClass smClass : newMetaclasses) {
            smClass.load(this);
        }
        
        // Third pass: call postInit() on the newly defined metaclasses
        for (final SmClass smClass : newMetaclasses) {
            smClass.postInit();
        }
        
        // Fourth pass: install the checkers
        mmFragment.createDependencyCheckers(this);
        
        // Register the fragment
        this.fragments.put(mmFragment.getName(), mmFragment);
        this.regularFragments.add(mmFragment);
        
        this.mExpert.register(mmFragment, mmFragment.createMExpert(this));
        
        if (TRACE_LOADING) {
            Log.trace("  Loaded %s v%s metamodel fragment.", mmFragment.getName(), mmFragment.getVersion().toString());
        }
        return removedFakes;
    }

    /**
     * Get a builder to create fake metaclasses.
     * @return a fake metaclasses builder.
     */
    @objid ("ff2f1d77-ed65-4a1c-9a5d-2945f093fda5")
    public FakeSmClassBuilder fakeClassBuilder() {
        return new FakeSmClassBuilder(this);
    }

    /**
     * Look for a metamodel fragment from its name.
     * <p>
     * The returned fragment may be a fake fragment.
     * @param fragmentName a metamodel fragment name
     * @return the found metamodel fragment or null.
     */
    @objid ("b18527ee-512e-4991-be4c-cc5fe3606f5f")
    public ISmMetamodelFragment getFragment(String fragmentName) {
        return this.fragments.get(fragmentName);
    }

    /**
     * Get the registered metamodel fragments.
     * <p>
     * The returned list is not modifiable.
     * @return the metamodel fragments.
     */
    @objid ("2e718cc5-6a63-447d-993e-4a6bd4784b82")
    @Override
    public Collection<ISmMetamodelFragment> getFragments() {
        return getFragments(false);
    }

    @objid ("9c8a862b-c52a-47e7-81ec-a21bda97a405")
    @Override
    public Collection<ISmMetamodelFragment> getFragments(boolean withFakes) {
        if (withFakes) {
            return Collections.<ISmMetamodelFragment>unmodifiableCollection(this.fragments.values());
        } else {
            return Collections.<ISmMetamodelFragment>unmodifiableCollection(this.regularFragments);
        }
        
    }

    /**
     * Get a meta class by its name
     * @return the meta class named by 'name', null if it does not exist.
     */
    @objid ("a47b0c3e-43ea-4dde-9a82-5acddb06bdaf")
    @Override
    public SmClass getMClass(final String name) {
        SmClass ret = this.byQualifiedNameCache.get(name);
        
        if (ret == null) {
            ret = this.byShortNameCache.get(name);
            if (ret != null && TRACE_SHORT_METACLASSNAMES && ret.getOrigin().isExtension()) {
                Log.trace(new Throwable(String.format("'%s' metaclass found by '%s' short name, please use qualified name.",ret.getQualifiedName(), name)));
            }
        }
        return ret;
    }

    /**
     * Get a meta class by its internal id
     * @param classid a metaclass internal id
     * @return the meta class number 'id', throws IndexOutOfBoundsException - if the index is out of range
     */
    @objid ("ad9615c3-39d0-4960-8af7-5afad09086c9")
    public SmClass getMClass(final short classid) {
        return this.metaclasses.get(classid);
    }

    /**
     * Get a meta class by its java interface
     * @return the meta class corresponding to the 'interf' Java interface class, null if it cannot be found
     */
    @objid ("c2a385bd-81ea-4096-a2b9-21ab9fd1cce6")
    @Override
    public SmClass getMClass(Class<? extends MObject> interf) {
        return this.byInterfaceCache.get(interf);
    }

    @objid ("0509a0ec-17b5-4cf0-96ed-a0b51e092639")
    @Override
    public MExpert getMExpert() {
        return this.mExpert;
    }

    /**
     * Get the list of meta classes currently composing the runtime metamodel
     * @return an unmodifiable list of the currently registered meta classes
     */
    @objid ("f75313e0-49b8-41c9-8807-dc25109998d5")
    @Override
    public List<SmClass> getRegisteredMClasses() {
        return Collections.unmodifiableList(this.metaclasses);
    }

    /**
     * Get the metaclasses registered by a metamodel fragment.
     * @param mmFragment a metamodel fragment.
     * @return the registered fragment metaclasses.
     */
    @objid ("a754ea23-20c1-454e-97ad-e86b745d9bd6")
    public Collection<SmClass> getRegisteredMClasses(MMetamodelFragment mmFragment) {
        ArrayList<SmClass> ret = new ArrayList<>();
        
        for (SmClass smClass : this.metaclasses) {
            if (smClass.getOrigin().equals(mmFragment)) {
                ret.add(smClass);
            }
        }
        return ret;
    }

    @objid ("b905f28c-14bb-4efa-81f7-1be5deaf8985")
    @Override
    public List<ISmMetamodelFragment> getSortedFragments() throws IllegalStateException {
        MMFragmentTopologicalSorter<ISmMetamodelFragment> sorter = new MMFragmentTopologicalSorter<>(getFragments());
        
        try {
            return sorter.sort();
        } catch (CyclicDependencyException e) {
            throw new IllegalStateException(e.getLocalizedMessage(), e);
        }
        
    }

    /**
     * Merge the given metamodel descriptor into this metamodel
     * in order to make best effort to make it compatible with both the initial
     * metamodel and the described one.
     * @param mmDesc the metamodel to merge.
     */
    @objid ("80d6dfe4-4a14-49ac-b6b0-98ab41c107f5")
    public void merge(MetamodelDescriptor mmDesc) {
        new SmMetamodelMerger(this).merge(mmDesc);
    }

    /**
     * Remove a fake metaclass from the metamodel.
     * @param smClass a fake metaclass.
     */
    @objid ("8b3385ee-8638-4372-bd2e-7293ba4d2117")
    public void removeFakeMetaclass(final SmClass smClass) {
        if (! smClass.isFake()) {
            throw new IllegalArgumentException(smClass.toString());
        }
        
        unregisterMetaclass(smClass);
        
    }

    /**
     * Remove a metamodel fragment and forget all its metaclasses.
     * @param removedMm a metamodel fragment to remove.
     */
    @objid ("1f3eb837-c215-460f-8706-e023b185e0a5")
    public void removeFragment(ISmMetamodelFragment removedMm) {
        for (SmClass smClass : new ArrayList<>(getRegisteredMClasses(removedMm))) {
            unregisterMetaclass(smClass);
        }
        
        this.fragments.remove(removedMm.getName());
        this.regularFragments.remove(removedMm);
        
    }

    /**
     * Produce a {@link MetamodelDescriptor} of this metamodel
     * @return a metamodel descriptor.
     */
    @objid ("254dcdcc-ab6f-4542-96c5-dba9d8facfc0")
    public MetamodelDescriptor serialize() {
        return MetamodelWriter.write(this);
    }

    /**
     * To be called only by {@link FakeSmClassBuilder}.
     * <p>
     * Register a new fake metaclass.
     * @param cls a fake metaclass
     */
    @objid ("ba1f9079-7818-4acb-9dbb-e013502cd86d")
    synchronized void addFakeMetaclass(SmClass cls) throws MetaclassAlreadyExistException {
        if (! cls.isFake()) {
            throw new IllegalArgumentException(String.format("'%s' is not fake.",cls));
        }
        
        SmClass existing = this.byQualifiedNameCache.get(cls.getQualifiedName());
        if (existing != null) {
            throw new MetaclassAlreadyExistException(existing);
        }
        
        registerMetaclass(cls);
        
        cls.load(this);
        cls.postInit();
        
    }

    /**
     * Get or create a fake metamodel fragment.
     * <p>
     * Used only by {@link FakeSmClassBuilder}.
     * @param fragmentName the metamodel fragment name.
     * @param version the metamodel fragment version. Will be used if the fragment has to be created.
     * @return the found or created metamodel fragment.
     */
    @objid ("6508dca0-f432-4840-9717-4cd30b9ce4d7")
    ISmMetamodelFragment getFakeFragment(String fragmentName, Version version) {
        ISmMetamodelFragment f = this.fragments.computeIfAbsent(
                fragmentName,
                name -> new FakeMetamodelFragment(name, version));
        return f;
    }

    /**
     * Register the metaclass in internal maps.
     * @param smClass the metaclass to register.
     * @return the metaclass id number.
     * @throws IllegalStateException if no metaclass id left
     * @throws IllegalArgumentException on duplicate metaclasses
     */
    @objid ("93c01cf5-e9ef-43f4-ab4c-148d6af18ef8")
    protected synchronized short registerMetaclass(final SmClass smClass) throws IllegalStateException, IllegalArgumentException {
        final int id = this.metaclasses.size();
        
        if (id >= Short.MAX_VALUE) {
            throw new IllegalStateException(MessageFormat.format("No handle left for new {0} metaclass.", smClass.getQualifiedName()));
        }
        
        // Register qualified name
        SmClass previous = this.byQualifiedNameCache.putIfAbsent(smClass.getQualifiedName(), smClass);
        if (previous != null) {
            // Duplicate metaclass, abort insertion and throw exception
            throw new IllegalArgumentException(MessageFormat.format(
                    "{0} has same ''{1}'' qualified name as {2} from {3}.",
                    smClass, smClass.getQualifiedName(), previous, previous.getOrigin()));
        }
        
        this.metaclasses.add(smClass);
        
        // Register short name
        if (smClass.getOrigin().isExtension()) {
            // Never add an extension metaclass having the same name as a standard one
            previous = this.byShortNameCache.putIfAbsent(smClass.getName(), smClass);
        } else {
            // Standard metaclasses are always in the 'short' cache
            previous = this.byShortNameCache.put(smClass.getName(), smClass);
        }
        
        // Register interface
        this.byInterfaceCache.put(smClass.getJavaInterface(), smClass);
        
        // initialize metaclass id
        smClass.setMetaclassId((short) id);
        smClass.setMetamodel(this);
        return (short) id;
    }

    @objid ("76287140-9935-45d1-a56b-e68477183179")
    private void checkDependencies(ISmMetamodelFragment mmFragment) throws IllegalArgumentException {
        for (VersionedItem<MMetamodelFragment> ref : mmFragment.getNeededFragments()) {
            ISmMetamodelFragment found = null;
            for (ISmMetamodelFragment f : getFragments()) {
                if (f.getName().equals(ref.getName())) {
                    if (f.getVersion().isOlderThan(ref.getVersion())) {
                        throw new IllegalArgumentException(MessageFormat.format(
                                "''{0}'' metamodel fragment needs ''{1}'' v{2} metamodel fragment, but we have {3} version.",
                                mmFragment.getName(), ref.getName(), ref.getVersion(), f.getVersion()));
                    } else {
                        found = f;
                        break;
                    }
                }
            }
            if (found == null) {
                throw new IllegalArgumentException(MessageFormat.format(
                        "''{0}'' metamodel fragment needs ''{1}'' v{2} metamodel fragment.",
                        mmFragment.getName(), ref.getName(), ref.getVersion()));
            }
        }
        
    }

    /**
     * Load the kernel metamodel fragment.
     */
    @objid ("a02dafad-68c4-429a-a576-f387082d4750")
    private final void initKernelFragment() {
        ISmMetamodelFragment mf = new KernelMetamodelFragment();
        addMetamodelFragment(mf);
        
    }

    @objid ("68389c69-99dd-453c-8fe7-40fe6a8ebd36")
    private synchronized void unregisterMetaclass(SmClass smClass) {
        this.byShortNameCache.remove(smClass.getName());
        this.byQualifiedNameCache.remove(smClass.getQualifiedName());
        this.byInterfaceCache.remove(smClass.getJavaInterface());
        
    }

}
