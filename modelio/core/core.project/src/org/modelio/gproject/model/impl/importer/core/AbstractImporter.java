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

package org.modelio.gproject.model.impl.importer.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.facilities.BrokenElementTester;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Base class for model import or copy service classes.
 */
@objid ("008d8740-5246-1091-8d81-001ec947cd2a")
public abstract class AbstractImporter {
    @objid ("008d8f6a-5246-1091-8d81-001ec947cd2a")
    protected Result result;

    /**
     * Run the copy/import operation
     * 
     * @param localSession the destination session
     * @param localRoot the destination root element
     * @param refSession the source modeling session
     * @param refRoots the elements to copy/import
     * @return the result of the import operation
     */
    @objid ("008d96f4-5246-1091-8d81-001ec947cd2a")
    public final IImportReport execute(final ICoreSession localSession, final SmObjectImpl localRoot, final ICoreSession refSession, List<SmObjectImpl> refRoots) {
        this.result = new Result();
        
        // STEP 1:
        prepare(localSession, localRoot, refSession, refRoots);
        
        // STEP 2: Create or find all 'nodes' and update meta-attributes
        importElements(localSession, localRoot, refSession, refRoots);
        
        // STEP 3: fill links between nodes
        importDependencies();
        
        // STEP 4: Process orphan roots
        fixRoots(localSession, localRoot, refRoots);
        
        // STEP 5: Fix broken elements
        fixElements(localSession, refSession);
        
        // STEP 6: Mark all remaining orphan elements as 'to delete'
        collectGarbage(localSession);
        
        // STEP 7: Definitively delete unwanted elements
        deleteGarbage();
        
        // STEP 8: Build the import report
        return new ImportReport(this.result);
    }

    /**
     * Run a copy/import operation on multiple roots at the same time.
     * <p/>
     * 
     * @param localSession the destination session
     * @param localRootList the destination root elements
     * @param refSession the source modeling session
     * @param refRootsList the elements to copy/import
     * @return the result of the import operation
     */
    @objid ("660c3522-3c87-46c8-be6d-a3bce980c603")
    public final IImportReport execute(final ICoreSession localSession, final List<SmObjectImpl> localRootList, final ICoreSession refSession, List<List<SmObjectImpl>> refRootsList) {
        if (localRootList.size() != refRootsList.size()) {
            throw new IllegalArgumentException(localRootList.size() + " local roots but " + refRootsList.size() + " reference roots.");
        }
        
        this.result = new Result();
        
        List<SmObjectImpl> mergedRefRoots = new ArrayList<>();
        for (List<SmObjectImpl> refList : refRootsList) {
            mergedRefRoots.addAll(refList);
        }
        
        // STEP 1:
        prepare(localSession, localRootList.get(0), refSession, mergedRefRoots);
        
        // STEP 2: Create or find all 'nodes' and update meta-attributes
        importElements(localSession, localRootList.get(0), refSession, mergedRefRoots);
        
        // STEP 3: fill links between nodes
        importDependencies();
        
        // STEP 4: Process orphan roots
        for (int i = 0; i < localRootList.size(); i++) {
            SmObjectImpl localRoot = localRootList.get(i);
            List<SmObjectImpl> refRoots = refRootsList.get(i);
            fixRoots(localSession, localRoot, refRoots);
        }
        
        // STEP 5: Fix broken elements
        fixElements(localSession, refSession);
        
        // STEP 6: Mark all remaining orphan elements as 'to delete'
        collectGarbage(localSession);
        
        // STEP 7: Definitively delete unwanted elements
        deleteGarbage();
        
        // STEP 8: Build the import report
        return new ImportReport(this.result);
    }

    /**
     * Test whether the given model object is a composition root.
     * 
     * @param obj a model object
     * @param localSession the destination session
     * @return <code>true</code> only if it is a composition root.
     */
    @objid ("0087a488-e548-108f-8d81-001ec947cd2a")
    public static boolean isRoot(final SmObjectImpl obj, ICoreSession localSession) {
        // Look for the fragment and search obj in its roots.
        IRepository f = localSession.getRepositorySupport().getRepository(obj);
        if (f != null) {
            return MTools.get(localSession).getRootGetter().getRootElements(f).contains(obj);
        }
        
        // Test for known root metaclass
        return (obj instanceof AbstractProject);
    }

    /**
     * Iterates ~garbaged~ elements,
     * Finds the orphan elements that are not root elements,
     * then schedule them for deletion.
     * 
     * @param localSession the destination session
     */
    @objid ("008789c6-e548-108f-8d81-001ec947cd2a")
    protected final void collectGarbage(ICoreSession localSession) {
        // Iterates ~garbaged~ elements,
        // Finds the orphan elements that are not root elements,
        // then schedule them for deletion.
        BrokenElementTester tester = new BrokenElementTester();
        
        // Get rid of orphan
        for (SmObjectImpl localObject : this.result.getGarbage()) {
            if (localObject.getCompositionOwner() == null && !AbstractImporter.isRoot(localObject, localSession)) {
                // delete it
                this.result.addObjectToDelete(localObject);
            }
        }
        
        // Find broken created elements and mark them deleted
        for (SmObjectImpl localObject : this.result.getCreations().values()) {
            if (localObject != null && localObject.isValid() && tester.isBroken(localObject)) {
                this.result.addObjectToDelete(localObject);
            }
        }
        
        // Find broken updated elements and mark them deleted
        for (SmObjectImpl localObject : this.result.getUpdates().values()) {
            if (localObject != null && localObject.isValid() && tester.isBroken(localObject)) {
                this.result.addObjectToDelete(localObject);
            }
        }
    }

    /**
     * Delete objects scheduled for deletion.
     */
    @objid ("008e3334-5246-1091-8d81-001ec947cd2a")
    protected void deleteGarbage() {
        for (SmObjectImpl localObjectToDelete : this.result.getDeletions()) {
            if (localObjectToDelete.isValid()) {
                localObjectToDelete.delete();
            }
        }
    }

    /**
     * Fix the given element if broken.
     * 
     * @param localObject the target model object to fix
     * @param refObject the source model object
     * @param localSession the target model session
     * @param refSession the source model session
     */
    @objid ("008e4112-5246-1091-8d81-001ec947cd2a")
    protected abstract void fixElement(SmObjectImpl localObject, final SmObjectImpl refObject, ICoreSession localSession, ICoreSession refSession);

    /**
     * Fix all imported elements.
     * <p>
     * Either repair or delete broken elements.
     * 
     * @param localSession the target model session
     * @param refSession the source model session
     */
    @objid ("008debcc-5246-1091-8d81-001ec947cd2a")
    protected final void fixElements(ICoreSession localSession, ICoreSession refSession) {
        for (Entry<SmObjectImpl, SmObjectImpl> entry : this.result.getCreations().entrySet()) {
            SmObjectImpl refObject = entry.getKey();
            SmObjectImpl localObject = entry.getValue();
        
            if (localObject != null && !localObject.isDeleted()) {
                fixElement(localObject, refObject, localSession, refSession);
            }
        }
        
        for (Entry<SmObjectImpl, SmObjectImpl> entry : this.result.getUpdates().entrySet()) {
            SmObjectImpl refObject = entry.getKey();
            SmObjectImpl localObject = entry.getValue();
        
            if (localObject != null && !localObject.isDeleted()) {
                fixElement(localObject, refObject, localSession, refSession);
            }
        }
    }

    /**
     * Post process imported roots composition relation.
     * <p>
     * Should ensure that all imported roots have a composition owner.<br>
     * This method should look for orphan elements then call {@link #reparentElements(Map, ICoreSession, SmObjectImpl)}.
     * 
     * @param localSession the target session
     * @param localRoot the target element
     * @param refRoots the imported roots in the source model.
     */
    @objid ("008df932-5246-1091-8d81-001ec947cd2a")
    protected void fixRoots(final ICoreSession localSession, final SmObjectImpl localRoot, List<SmObjectImpl> refRoots) {
        // Gather all root orphans
        Map<SmObjectImpl, SmDependency> rootOrphans = new HashMap<>();
        for (SmObjectImpl refRoot : refRoots) {
            SmObjectImpl localObject = this.result.getObjectCreatedFrom(refRoot);
        
            if (localObject == null) {
                localObject = this.result.getObjectUpdatedFrom(refRoot);
            }
        
            if (localObject != null && localObject.getCompositionOwner() == null) {
                rootOrphans.put(localObject, refRoot.getCompositionRelation().dep.getSymetric());
            }
        }
        
        // Reparent only orphan roots
        reparentElements(rootOrphans, localSession, localRoot);
    }

    @objid ("008e8bfe-5246-1091-8d81-001ec947cd2a")
    protected abstract void importCompositionDependencies(final SmObjectImpl refObject, SmObjectImpl localObject);

    @objid ("008f857c-5246-1091-8d81-001ec947cd2a")
    protected final void importDependencies() {
        // Import dependencies for the created objects
        for (Entry<SmObjectImpl, SmObjectImpl> entry : this.result.getCreations().entrySet()) {
            SmObjectImpl refObject = entry.getKey();
            SmObjectImpl localObject = entry.getValue();
        
            importCompositionDependencies(refObject, localObject);
            importReferenceDependencies(refObject, localObject);
        }
        
        // Import dependencies for the updated objects
        for (Entry<SmObjectImpl, SmObjectImpl> entry : this.result.getUpdates().entrySet()) {
            SmObjectImpl refObject = entry.getKey();
            SmObjectImpl localObject = entry.getValue();
        
            importCompositionDependencies(refObject, localObject);
            importReferenceDependencies(refObject, localObject);
        }
    }

    @objid ("008efe40-5246-1091-8d81-001ec947cd2a")
    protected abstract void importElements(final ICoreSession localSession, final SmObjectImpl localRoot, final ICoreSession refSession, List<SmObjectImpl> refRoots);

    @objid ("008ea44a-5246-1091-8d81-001ec947cd2a")
    protected abstract void importReferenceDependencies(final SmObjectImpl refObject, SmObjectImpl localObject);

    @objid ("008ebcfa-5246-1091-8d81-001ec947cd2a")
    protected abstract void prepare(final ICoreSession localSession, final SmObjectImpl localRoot, final ICoreSession refSession, List<SmObjectImpl> refRoots);

    /**
     * Called by {@link #fixRoots(ICoreSession, SmObjectImpl, List)}.
     * <p>
     * Change the composition owner of the map keys to the given parent, using the map values
     * as dependency.
     * 
     * @param toReparent the map of elements to reparent in the target local model
     * @param localSession the target local session
     * @param localRoot the target root destination element where roots should be reparented to.
     */
    @objid ("008e597c-5246-1091-8d81-001ec947cd2a")
    protected abstract void reparentElements(Map<SmObjectImpl, SmDependency> toReparent, ICoreSession localSession, SmObjectImpl localRoot);

    @objid ("00924ae6-5246-1091-8d81-001ec947cd2a")
    protected static final class ImportReport implements IImportReport {
        @objid ("00925b9e-5246-1091-8d81-001ec947cd2a")
        private final Result result;

        @objid ("00926ab2-5246-1091-8d81-001ec947cd2a")
        public ImportReport(org.modelio.gproject.model.impl.importer.core.AbstractImporter.Result result) {
            this.result = result;
        }

        @objid ("009285ba-5246-1091-8d81-001ec947cd2a")
        @Override
        public List<SmObjectImpl> getCreatedObjects() {
            return this.result.getCreatedObjects();
        }

        @objid ("0092b4e0-5246-1091-8d81-001ec947cd2a")
        @Override
        public List<SmObjectImpl> getUpdatedObjects() {
            return this.result.getUpdatedObjects();
        }

        @objid ("0092e3de-5246-1091-8d81-001ec947cd2a")
        @Override
        public List<SmObjectImpl> getDeletedObjects() {
            return this.result.getDeletions();
        }

        @objid ("6a5ecc48-6bd9-40e2-bcb7-1924d706643b")
        @Override
        public SmObjectImpl getCreatedObject(SmObjectImpl refObject) {
            return this.result.getObjectCreatedFrom(refObject);
        }

        @objid ("de9e5eb4-0835-4838-8cd4-51dffb879bc0")
        @Override
        public SmObjectImpl getUpdatedObject(SmObjectImpl refObject) {
            return this.result.getObjectUpdatedFrom(refObject);
        }

    }

    @objid ("0082901a-e548-108f-8d81-001ec947cd2a")
    protected static class Result {
        @objid ("0082a23a-e548-108f-8d81-001ec947cd2a")
        protected Map<SmObjectImpl, SmObjectImpl> createdObjects = new HashMap<>();

        @objid ("0082bcd4-e548-108f-8d81-001ec947cd2a")
        protected List<SmObjectImpl> objectsToDelete = new ArrayList<>();

        @objid ("0082ded0-e548-108f-8d81-001ec947cd2a")
        protected List<SmObjectImpl> objectsToGarbage = new ArrayList<>();

        @objid ("0082fa96-e548-108f-8d81-001ec947cd2a")
        protected Map<SmObjectImpl, SmObjectImpl> replacedObjects = new HashMap<>();

        @objid ("00157c14-e70c-108f-8d81-001ec947cd2a")
        public Result() {
        }

        @objid ("0083135a-e548-108f-8d81-001ec947cd2a")
        public void addCreatedObject(SmObjectImpl localObject, SmObjectImpl refObject) {
            this.createdObjects.put(refObject, localObject);
        }

        @objid ("00832a5c-e548-108f-8d81-001ec947cd2a")
        public void addObjectToDelete(SmObjectImpl localObject) {
            this.objectsToDelete.add(localObject);
        }

        @objid ("00833c54-e548-108f-8d81-001ec947cd2a")
        public void addObjectToGarbage(SmObjectImpl localObject) {
            this.objectsToGarbage.add(localObject);
        }

        @objid ("00834e92-e548-108f-8d81-001ec947cd2a")
        public void addUpdatedObject(SmObjectImpl localObject, SmObjectImpl refObject) {
            // Add updated SmObjectImpl only if it has not been created
            if (getObjectCreatedFrom(refObject) == null) {
                this.replacedObjects.put(refObject, localObject);
            }
        }

        @objid ("00836620-e548-108f-8d81-001ec947cd2a")
        public List<SmObjectImpl> getCreatedObjects() {
            return new ArrayList<>(this.createdObjects.values());
        }

        @objid ("00838c68-e548-108f-8d81-001ec947cd2a")
        public List<SmObjectImpl> getObjectsToDelete() {
            return new ArrayList<>(this.objectsToDelete);
        }

        @objid ("0083b292-e548-108f-8d81-001ec947cd2a")
        public List<SmObjectImpl> getUpdatedObjects() {
            return new ArrayList<>(this.replacedObjects.values());
        }

        @objid ("0083d8ee-e548-108f-8d81-001ec947cd2a")
        public Map<SmObjectImpl, SmObjectImpl> getCreations() {
            return this.createdObjects;
        }

        @objid ("00840134-e548-108f-8d81-001ec947cd2a")
        public List<SmObjectImpl> getDeletions() {
            return this.objectsToDelete;
        }

        @objid ("00842a42-e548-108f-8d81-001ec947cd2a")
        public List<SmObjectImpl> getGarbage() {
            return this.objectsToGarbage;
        }

        @objid ("00845bc0-e548-108f-8d81-001ec947cd2a")
        public SmObjectImpl getObjectCreatedFrom(final SmObjectImpl refObject) {
            return this.createdObjects.get(refObject);
        }

        @objid ("008485be-e548-108f-8d81-001ec947cd2a")
        public SmObjectImpl getObjectUpdatedFrom(final SmObjectImpl refObject) {
            return this.replacedObjects.get(refObject);
        }

        @objid ("0084b070-e548-108f-8d81-001ec947cd2a")
        public Map<SmObjectImpl, SmObjectImpl> getUpdates() {
            return this.replacedObjects;
        }

        @objid ("0084d9ce-e548-108f-8d81-001ec947cd2a")
        public boolean isCreated(final SmObjectImpl localObject) {
            return this.createdObjects.containsValue(localObject);
        }

        @objid ("0085044e-e548-108f-8d81-001ec947cd2a")
        public boolean isDeleted(final SmObjectImpl localObject) {
            return this.objectsToDelete.contains(localObject);
        }

        @objid ("00852f32-e548-108f-8d81-001ec947cd2a")
        public boolean isImported(final SmObjectImpl localObject) {
            return isCreated(localObject) || isUpdated(localObject);
        }

        @objid ("00855a98-e548-108f-8d81-001ec947cd2a")
        public boolean isUpdated(final SmObjectImpl localObject) {
            return this.replacedObjects.containsValue(localObject);
        }

        @objid ("00858612-e548-108f-8d81-001ec947cd2a")
        public void mergeListsFrom(final Result result) {
            this.createdObjects.putAll(result.getCreations());
            this.replacedObjects.putAll(result.getUpdates());
            this.objectsToDelete.addAll(result.getDeletions());
        }

        @objid ("0085a5d4-e548-108f-8d81-001ec947cd2a")
        private void removeCreatedObject(final SmObjectImpl localObject) {
            SmObjectImpl key = null;
            for (Entry<SmObjectImpl, SmObjectImpl> entry : this.createdObjects.entrySet()) {
                if (entry.getValue().equals(localObject)) {
                    key = entry.getKey();
                    break;
                }
            }
            if (key != null) {
                this.createdObjects.remove(key);
            }
        }

        @objid ("0085c604-e548-108f-8d81-001ec947cd2a")
        public void removeCreation(final SmObjectImpl refObject) {
            this.createdObjects.remove(refObject);
        }

        @objid ("0085e684-e548-108f-8d81-001ec947cd2a")
        public void removeObjectToDelete(final SmObjectImpl localObject) {
            this.objectsToDelete.remove(localObject);
        }

        @objid ("00860722-e548-108f-8d81-001ec947cd2a")
        public void removeUpdate(final SmObjectImpl refObject) {
            this.replacedObjects.remove(refObject);
        }

        @objid ("008627e8-e548-108f-8d81-001ec947cd2a")
        private void removeUpdatedObject(final SmObjectImpl localObject) {
            SmObjectImpl key = null;
            for (Entry<SmObjectImpl, SmObjectImpl> entry : this.replacedObjects.entrySet()) {
                if (entry.getValue().equals(localObject)) {
                    key = entry.getKey();
                    break;
                }
            }
            if (key != null) {
                this.replacedObjects.remove(key);
            }
        }

    }

}
