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
package org.modelio.vcore.model.spi.mm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.session.impl.GenericFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.mapi.MetaclassNotFoundException;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel;
import org.modelio.vcore.smkernel.meta.mof.MofSmClass;
import org.modelio.vcore.smkernel.meta.mof.MofSmObjectImpl;

/**
 * Convenience modeling session for migration.
 * @author cma
 */
@objid ("e8b8eb2b-6c7e-4fe6-97f2-dbb473ed8e6a")
public class MofSession implements IMofSession {
    @objid ("fa79f16f-022a-4848-8ee7-cb5a0988542a")
    private static final boolean TRACE = false;

    @objid ("731dc536-7a7d-4408-9b35-7afd6dfdffb6")
    private final ICoreSession coreSession;

    @objid ("a8061a58-8532-4fb1-9d71-aa72055fe149")
    private IMigrationReporter report;

    @objid ("4e122829-e994-430b-8986-9e2b12f8de8c")
    private final IRepository targetRepository;

    /**
     * List of elements to reidentify at the end of the session.
     */
    @objid ("fde01569-a93e-4436-94db-f92aa44e89c1")
    private Collection<ReidentData> toReidentify = new ArrayList<>();

    /**
     * @param coreSession the modeling session
     * @param targetRepository the repository where to create new objects
     * @param report the migration report builder
     */
    @objid ("bb21c45d-c3b5-4701-ac9e-3d60cdc5ad1c")
    public  MofSession(ICoreSession coreSession, IRepository targetRepository, IMigrationReporter report) {
        this.coreSession = coreSession;
        this.targetRepository = targetRepository;
        this.report = report;
        
    }

    /**
     * Create an object
     * @throws MetaclassNotFoundException
     * @param cls a metaclass name, preferably qualified
     * @param name the element name
     * @return the created object
     */
    @objid ("32d98add-739e-4ee7-a502-910e436ec551")
    @Override
    public MofSmObjectImpl createObject(String cls, String name) throws MetaclassNotFoundException {
        GenericFactory genericFactory = getFactory();
        MofSmObjectImpl ret = (MofSmObjectImpl) genericFactory.create(getMetaclass(cls), this.targetRepository);
        if (name != null) {
            ret.setName(name);
        }
        return ret;
    }

    @objid ("31d7b53d-859a-4a27-9d94-c02317c5bd9e")
    @Override
    public MofSmObjectImpl createObject(MClass newMetaclass) {
        MofSmObjectImpl ret = (MofSmObjectImpl) getFactory().create(newMetaclass, getTargetRepository());
        return ret;
    }

    @objid ("5de0ff2f-9477-49af-ae4c-76eec260a7a2")
    @SuppressWarnings("unchecked")
    @Override
    public Collection<MofSmObjectImpl> findByAtt(SmClass metaclass, boolean withSubClasses, String attName, Object attValue) {
        return (Collection<MofSmObjectImpl>) this.coreSession.getModel().findByAtt(metaclass, withSubClasses, attName, attValue);
    }

    @objid ("2e5ea990-6c2d-4c86-8678-81be4f61b56d")
    @Override
    public Collection<MofSmObjectImpl> findByClass(String clsName, boolean withSubClasses) throws MetaclassNotFoundException {
        return findByClass(getMetaclass(clsName), withSubClasses);
    }

    @objid ("964ca203-2561-41b8-aa7b-ee93c1e411e3")
    @Override
    @SuppressWarnings("unchecked")
    public Collection<MofSmObjectImpl> findByClass(MClass metaclass, boolean withSubClasses) {
        return (Collection<MofSmObjectImpl>) this.coreSession.getModel().findByClass(metaclass, withSubClasses);
    }

    /**
     * Find an element from a reference.
     * @param ref an element reference
     * @return the found element or null.
     */
    @objid ("353dfeb1-f579-4fd3-ab46-7e94d1fe7e01")
    @Override
    public MObject findByRef(MRef ref) {
        MObject found = this.coreSession.getModel().findByRef(ref);
        return found;
    }

    /**
     * @return the modeling session
     */
    @objid ("12ccf275-ea82-4e52-ae1c-42c6288a501f")
    @Override
    public ICoreSession getCoreSession() {
        return this.coreSession;
    }

    @objid ("3da74d21-28c7-4592-85b8-f564897d1f73")
    @Override
    public SmClass getMetaclass(String clsName) throws MetaclassNotFoundException {
        SmClass mClass = this.coreSession.getMetamodel().getMClass(clsName);
        if (mClass == null) {
            throw new MetaclassNotFoundException(clsName);
        }
        return mClass;
    }

    /**
     * @return the MOF metamodel
     */
    @objid ("441ba310-5058-4dfe-8f19-8901d354a936")
    @Override
    public MofMetamodel getMetamodel() {
        return (MofMetamodel) this.coreSession.getMetamodel();
    }

    /**
     * Look for an object by scanning a dependency. Create the element if not found.
     * @param from the source element to scan
     * @param depName the dependency name
     * @param clsName the target metaclass name, preferably qualified
     * @param name the element name
     * @return the found or created element.
     * @throws MetaclassNotFoundException if the metaclass does not exist.
     */
    @objid ("2fc1acad-c499-44c5-b40a-f22be4f0561e")
    @Override
    public MofSmObjectImpl getOrCreate(MofSmObjectImpl from, String depName, String clsName, String name) throws MetaclassNotFoundException {
        MofSmClass mc = (MofSmClass) getMetaclass(clsName);
        MofSmObjectImpl obj = from.getDep(depName)
                .stream()
                .filter(o -> o.getName().equals(name) )
                .findFirst()
                .orElseGet(() -> { 
                    MofSmObjectImpl ret = createObject(mc);
                    ret.setName(name);
                    from.getDep(depName).add(ret);
                    getReport().getLogger().printf("  Created %s in %s.%s .\n", ret, from, depName);
                    return ret;
                });
        return obj;
    }

    @objid ("8d2f36a7-78b3-4a9d-8d84-9081dd6068a3")
    @Override
    public IMigrationReporter getReport() {
        return this.report;
    }

    /**
     * @return the migrated repository
     */
    @objid ("f81e0254-2668-402a-abc5-ff907761e79b")
    @Override
    public IRepository getTargetRepository() {
        return this.targetRepository;
    }

    @objid ("d377b578-75cb-4467-ac40-5eae0d8ad219")
    @Override
    public void processScheduledReidentifications(Supplier<SubProgress> supplier) throws MofMigrationException {
        if (! this.toReidentify.isEmpty()) {
            
            int workcount = this.toReidentify.size();
            SubProgress mon = SubProgress.convert(supplier.get(), workcount * 3);
            getReport().getLogger().format(" Process %d scheduled reidentifications...\n", workcount);
            
            saveSession(mon.newChild(workcount/2));
            
            for (ReidentData entry : this.toReidentify) {
                MObject old = this.coreSession.getModel().findByRef(entry.tempIdent);
                if (old != null) {
                    MRef newRef = entry.finalIdent;
        
                    try {
                        replace(old, getMetaclass(newRef.mc), newRef.uuid, false);
                    } catch (MetaclassNotFoundException e) {
                        throw new MofMigrationException(String.format(
                                "Failed reidentify %s to %s: %s", 
                                old, 
                                newRef, 
                                e.getLocalizedMessage()), e);
                    }
                }
            }
            
            processReidentificationsInDiagrams(mon.newChild(workcount));
            
            saveSession(mon);
            
            getReport().getLogger().format(" %d Reidentifications done.\n", this.toReidentify.size());
        
            // Reset reidentification map
            this.toReidentify = new ArrayList<>();
        
        }
        
    }

    /**
     * Replace an object by a new one, possibly of another metaclass.
     * <p>
     * A new object is created.
     * The original object is detached to all references, that are attached to the new object.
     * At the end the original object is deleted.
     * @param original the original object to be replaced by another
     * @param newMetaclass the new object metaclass
     * @param newUuid the new object UUID. If <i>null</i> a new UUID will be generated. This UUID must NOT be the same as the original one.
     * @param scheduleReidentify if <i>true</i>, the replacement will be recorded so that the new object may be replaced
     * by another with the original identifier by {@link #processScheduledReidentifications(Supplier)} .
     * @return the new object
     */
    @objid ("bcb28cbd-57ed-4dc2-91e8-6fd6c4b4fb43")
    public MofSmObjectImpl replace(MObject original, SmClass newMetaclass, String newUuid, boolean scheduleReidentify) {
        assert (! Objects.equals(newUuid, original.getUuid()));
        
        IRepository repo = this.coreSession.getRepositorySupport().getRepository(original);
        
        if (TRACE) {
            if ( !scheduleReidentify && original.getCompositionOwner() == null && ((SmObjectImpl) original).getRepositoryObject().getRepositoryId() == this.targetRepository.getRepositoryId()) {
                Log.trace("  %1$s is orphan or root.", original);
            }
        }
        
        MofSmObjectImpl ret = (MofSmObjectImpl) ((CoreSession) this.coreSession)
                .getSmFactory()
                .createObject(newMetaclass, repo, newUuid);
        
        for (SmAttribute att : newMetaclass.getAllAttDef()) {
            Object val = original.mGet(att);
            if (val != null) {
                if (att.getType() == String.class) {
                    ret.mSet(att, val.toString());
                } else {
                    ret.mSet(att, val);
                }
            }
        }
        
        for (MDependency origDep : original.getMClass().getDependencies(true)) {
            SmDependency newDep = newMetaclass.getDependencyDef(origDep.getName());
            if (newDep == null) {
                newDep = (SmDependency) origDep; // the caller is expected to have copied 'newDep' in the original metaclass or the reverse.
            }
            MDependency oldDepSym = origDep.getSymetric();
            MDependency newDepSym = newDep.getSymetric();
            if (isNavigable(newDep) || newDepSym == null) {
                if (newDepSym == null) {
                    getReport().getLogger().format("  Warn: %s: %s.%s has no opposite dependency", original, newMetaclass.getQualifiedName(), newDep);
                }
                
                // Just move the content to the new object
                List<MObject> origDepContent = original.mGet(origDep);
                for (MObject value : new ArrayList<>(origDepContent)) {
                    // Usually the following line is not needed. But we are in a configuration
                    // where we have SmDependencies clones that point to the same opposite,
                    // which may prevent correct cleaning.
                    origDepContent.remove(value);
                    
                    ret.appendDepVal(newDep, (SmObjectImpl) value);
        
                    assert(! original.mGet(origDep).contains(value));
                    assert(  ret.mGet(newDep).contains(value));
                    assert (newDepSym==null || value.mGet(newDepSym).contains(ret)) : String.format("%s.%s does not contain %s", value, newDepSym, ret);
                    assert (oldDepSym==null || !value.mGet(oldDepSym).contains(original)): String.format("%s.%s still contains %s", value, oldDepSym, original);
                }
            } else {
                // Move the content to the new object 
                // while ensuring order is preserved on the opposite association
                List<MObject> origDepContent = original.mGet(origDep);
                for (MObject value : new ArrayList<>(origDepContent)) {
                    moveNonNavigableDepValue(original, ret, origDep, newDep, newDepSym, origDepContent, value);
                }
            }
        }
        
        if (scheduleReidentify) {
            MRef finalRef = new MRef(newMetaclass.getQualifiedName(), original.getUuid(), original.getName());
            this.toReidentify.add(new ReidentData(new MRef(original), new MRef(ret),finalRef));
        }
        
        // invariant: The original object should be empty
        assert (original.getCompositionChildren().isEmpty()) : original + " still owns:"+original.getCompositionChildren();
        
        // Delete the original object
        original.delete();
        
        // Log the action
        String msg = String.format(
                newMetaclass==original.getMClass() ? "  Replaced %s by %s%s." : "  Transmuted %s to %s%s.",
                original, ret, 
                scheduleReidentify ? " (reidentification scheduled)" : "");
        
        getReport().getLogger().println(msg);
        Log.trace(msg);
        
        // Return the new object
        return ret;
    }

    /**
     * Replace the given object by another object of the given metaclass.
     * <p>
     * Warn: For technical reasons the new object has a temporary identifier that is different
     * from the original one.
     * The original object is deleted in the process.
     * The transmuted object will be reidentified at the end of the migration.
     * @param toTransmute the object to transmute.
     * @param newMetaclass the new metaclass
     * @return the new object.
     */
    @objid ("49307f88-e34b-46bd-b55b-bbd9a4011a43")
    @Override
    public MofSmObjectImpl transmute(MofSmObjectImpl toTransmute, MofSmClass newMetaclass) {
        return replace(toTransmute, newMetaclass, null, true);
    }

    @objid ("f7027d64-4ae5-4e29-824a-ee9b7b2bb6c3")
    private GenericFactory getFactory() {
        return this.coreSession.getModel().getGenericFactory();
    }

    @objid ("7df07ad8-76d4-41af-b9da-575ca7751304")
    private static boolean isNavigable(SmDependency dep) {
        return dep.isComposition() || dep.isSharedComposition() || dep.isPartOf();
    }

    @objid ("5934a3d2-9b30-408b-b33c-701797c2a4a5")
    @Override
    public MofSmObjectImpl getObjectReference(MRef ref) throws MetaclassNotFoundException {
        MObject ret = this.coreSession.getModel().findByRef(ref);
        if (ret == null) {
            CoreSession coreSession2 = (CoreSession) this.coreSession;
            IRepository shellRepo = coreSession2.getShellRepository();
            SmClass newMetaclass = getMetaclass(ref.mc);
            ret = coreSession2
                    .getSmFactory()
                    .createObject(newMetaclass, shellRepo, ref.uuid);
            
        }
        return (MofSmObjectImpl) ret;
    }

    @objid ("595e7877-ca52-4857-bb64-885563ae0f47")
    @Override
    public MofSmObjectImpl createObject(MClass newMetaclass, String name) {
        MofSmObjectImpl ret = (MofSmObjectImpl) getFactory().create(newMetaclass, getTargetRepository());
        if (name != null) {
            ret.setName(name);
        }
        return ret;
    }

    @objid ("75dea7fb-cabc-4f1c-b7af-90f910a09cee")
    private void saveSession(IModelioProgress mon) throws MofMigrationException {
        try {
            this.coreSession.save(mon);
        } catch (IOException e) {
            throw new MofMigrationException(FileUtils.getLocalizedMessage(e), e);
        }
        
    }

    @objid ("ea5d98da-bdf1-4d09-a20b-da644872a34b")
    private void processReidentificationsInDiagrams(SubProgress monitor) throws MofMigrationException {
        MofDiagramMigrator mofDiagramMigrator = new MofDiagramMigrator(this);
        for (ReidentData entry : this.toReidentify) {
            mofDiagramMigrator.addMapping(entry.origIdent, entry.finalIdent);
        }
        
        mofDiagramMigrator.run(monitor);
        
    }

    /**
     * Move the given dependency value to the new object
     * while ensuring order is preserved on the opposite association.
     * @param dep the dependency to move
     * @param original the original object
     * @param transmuted the transmuted version of the object
     * @param transmutedDepOpposite the dependency opposite
     * @param origDepContent read/write access to the dependency content.
     * @param value the value to move
     */
    @objid ("8a332714-c8ab-4553-ac7d-a83b6ea3e7ea")
    private void moveNonNavigableDepValue(MObject original, MofSmObjectImpl transmuted, MDependency origDep, SmDependency transmutedDep, MDependency transmutedDepOpposite, List<MObject> origDepContent, MObject value) {
        MDependency origDepOpposite = origDep.getSymetric();
        List<MObject> origOppositeContent = value.mGet(origDepOpposite);
        List<MObject> newOppositeContent = value.mGet(transmutedDepOpposite);
        int idx = origOppositeContent.indexOf(original);
        if (idx != -1) {
            // Usually the 2 following lines are not needed. But we are in a configuration
            // where we have SmDependencies clones that point to the same opposite,
            // which may prevent correct cleaning.
            origDepContent.remove(value);
            origOppositeContent.remove(original);
              
            origOppositeContent.add(idx, transmuted);
              
            /*if( !value.mGet(transmutedDepOpposite).contains(transmuted)) {
                origOppositeContent.add(idx, transmuted);
            }*/
            if (! transmuted.mGet(transmutedDep).contains(value)) {
                // transmutedDep is only one of the opposites of transmutedDepOpposite, fill transmutedDep on one way only
                transmuted.getMetaOf().appendObjDepVal(transmuted, transmutedDep, (SmObjectImpl) value);
            }
            
            assert(! original.mGet(origDep).contains(value));
            assert(  transmuted.mGet(transmutedDep).contains(value));
            assert(  value.mGet(origDepOpposite).contains(transmuted));
            assert(! value.mGet(transmutedDepOpposite).contains(original));
            assert(! value.mGet(origDepOpposite).contains(original));
        } else {
            // newDep may be the opposite of many MDependencies. This happen only in migrations.
            // scan all target dependencies.
            boolean found = false;
            Collection<MDependency> foundopposites = new ArrayList<>();
            for (MDependency valueDep : value.getMClass().getDependencies(true)) {
                if (valueDep.getSymetric() == origDep) {
                    foundopposites.add(valueDep);
                    origOppositeContent = value.mGet(valueDep);
                    idx = origOppositeContent.indexOf(original);
                    if (idx != -1) {
                        found = true;
                        origDepContent.remove(value);
                        origOppositeContent.remove(original);
        
                        newOppositeContent.add(idx, transmuted);
                        
                        /*if( !value.mGet(valueDep).contains(transmuted)) {
                            origOppositeContent.add(idx, transmuted);
                        }*/
                    }
                }
            }
            if (! found) {
                getReport().getLogger().format("  Warn: %1$s.%2$s contains %3$s but %3$s.%4$s does not contain %1$s.\n", 
                        original, origDep.getName(), value, foundopposites);
                Log.warning("  Warn: %1$s.%2$s contains %3$s but %3$s.%4$s does not contain %1$s.\n", 
                        original, origDep.getName(), value, foundopposites);
            }
            assert(! original.mGet(origDep).contains(value)) : String.format("%s.%s still contains %s", original, origDep, value);
            assert(  transmuted.mGet(transmutedDep).contains(value)) : String.format("%s.%s does not contains %s", transmuted, transmutedDep, value);
            assert(  value.mGet(transmutedDepOpposite).contains(transmuted)) : String.format("%s.%s does not contains %s", value, transmutedDepOpposite, transmuted);
            assert(! value.mGet(transmutedDepOpposite).contains(original)) : String.format("%s.%s still contains %s", value, transmutedDepOpposite, original);
            assert(! value.mGet(origDepOpposite).contains(original)) : String.format("%s.%s still contains %s", value, origDepOpposite, original);
        }
        
    }

    /**
     * Elements to reidentify at the end of the session.
     * <p>
     * contains:<ul>
     * <li>origIdent : original identifier
     * <li>tempIdent : temporary transmuted
     * <li>finalIdent : final identifier
     * </ul>
     */
    @objid ("fc4c7ad3-3b53-4a8c-a62c-5c69244db153")
    private static final class ReidentData {
        @objid ("db19f628-b959-45d8-be5c-71e83ef09375")
        final MRef origIdent;

        @objid ("406a9fcd-3190-4b20-b844-cd987db1e8b4")
        final MRef tempIdent;

        @objid ("2087cc1a-5448-43af-a127-55f5789b716c")
        final MRef finalIdent;

        @objid ("2042794f-e168-4d3c-b376-472c90c51b0b")
        public  ReidentData(MRef origIdent, MRef tempIdent, MRef finalIdent) {
            this.origIdent = origIdent;
            this.tempIdent = tempIdent;
            this.finalIdent = finalIdent;
            
        }

    }

}
