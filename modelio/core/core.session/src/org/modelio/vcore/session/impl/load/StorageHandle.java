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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.CompositionGetter;
import org.modelio.vcore.session.api.blob.IBlobSupport;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.api.repository.IRepositorySupport;
import org.modelio.vcore.session.impl.handles.IStorageHandle;
import org.modelio.vcore.smkernel.IRepositoryObject;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmLiveId;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;

/**
 * Helper class for storage.
 * <p>
 * Ensures that :
 * <ul>
 * <li>all the composition tree belongs to the same repository.
 * <li>a loading session is started
 */
@objid ("99e0d633-3a25-11e2-bf6c-001ec947ccaf")
public class StorageHandle implements IStorageHandle {
    @objid ("645fb5db-2b13-43f2-be7f-6c76846384ae")
    private final IRepositorySupport repoSupport;

    @objid ("cf9ebf4c-2bf4-42ed-bdcb-c611df1d2bb8")
    private final IBlobSupport blobSupport;

    @objid ("1dac7121-5ceb-4042-b6d6-f9f6739ef515")
    private final IRepository newBornRepo;

    /**
     * initialize the storage helper.
     * @param repoSupport a view of all connected repositories.
     * @param blobSupport the BLOB support
     */
    @objid ("1fe03503-3a2d-11e2-bf6c-001ec947ccaf")
    public  StorageHandle(IRepositorySupport repoSupport, IBlobSupport blobSupport) {
        this.repoSupport = repoSupport;
        this.blobSupport = blobSupport;
        this.newBornRepo = this.repoSupport.getRepository(IRepositorySupport.REPOSITORY_KEY_SCRATCH);
        
    }

    @objid ("1fe03509-3a2d-11e2-bf6c-001ec947ccaf")
    @Override
    public void loadAtt(SmObjectImpl obj, SmAttribute att, ISmObjectData data) {
        if (!data.getRepositoryObject().isAttLoaded(obj, att)) {
            data.getRepositoryObject().loadAtt(obj, att);
        }
        
    }

    @objid ("1fe03510-3a2d-11e2-bf6c-001ec947ccaf")
    @Override
    public void forceLoadDep(SmObjectImpl obj, SmDependency dep, ISmObjectData data) {
        data.getRepositoryObject().loadDep(obj, dep);
    }

    @objid ("dc24d776-8fb5-11e1-81e9-001ec947ccaf")
    private void loadNonStoredDep(final SmObjectImpl obj, final SmDependency dep) {
        IRepositoryObject repositoryHandle = obj.getRepositoryObject();
        byte objRepositoryId = repositoryHandle.getRepositoryId();
        
        // Ask the own repository to load the dynamic dep as a normal dep.
        // note: This is a questionable optimization that can make loadDep() implementation more complex.
        repositoryHandle.loadDep(obj, dep);
        
        // Query other repositories for the dep
        for (IRepository r : this.repoSupport.getRepositories()) {
            if (r.isOpen() && r.getRepositoryId() != objRepositoryId) {
                r.loadDynamicDep(obj, dep);
            }
        }
        
    }

    @objid ("1fe2974f-3a2d-11e2-bf6c-001ec947ccaf")
    @Override
    public void loadDep(final SmObjectImpl obj, final ISmObjectData data, final SmDependency dep) {
        if (isStored(dep)) {
            // The dependency is stored for sure
            if (!data.getRepositoryObject().isDepLoaded(obj, dep)) {
                forceLoadDep(obj, dep, data);
            }
        } else {
            // The repository may be unable to store it because of extern
            // references.
            // Ask all repositories.
            loadNonStoredDep(obj, dep);
        }
        
    }

    @objid ("1fe29759-3a2d-11e2-bf6c-001ec947ccaf")
    @Override
    public int loadDepIndexOf(SmObjectImpl obj, SmDependency dep, SmObjectImpl dep_val, ISmObjectData data) {
        int oldIndex;
        if (dep.isMultiple()) {
            SmMultipleDependency mdep = (SmMultipleDependency) dep;
            oldIndex = mdep.getValueList(data).indexOf(dep_val);
            if (oldIndex == -1 && isStored(dep)) {
                // This is a stored not loaded multiple dependency
                forceLoadDep(obj, dep, data);
                oldIndex = mdep.getValueList(data).indexOf(dep_val);
            }
        } else {
            oldIndex = 0;
            if (dep.getValue(data) == null) {
                forceLoadDep(obj, dep, data);
            }
        }
        return oldIndex;
    }

    @objid ("1fe29762-3a2d-11e2-bf6c-001ec947ccaf")
    @Override
    public void appendObjDepVal(ISmObjectData data, SmObjectImpl obj, SmDependency dep, SmObjectImpl dep_val) {
        IRepositoryObject destRepo = data.getRepositoryObject();
        if (isRepositoryMove(dep, dep_val, destRepo)) {
            moveToRepository(dep_val, destRepo);
        }
        
        data.getRepositoryObject().depValAppended(obj, dep, dep_val);
        
    }

    @objid ("1fe29769-3a2d-11e2-bf6c-001ec947ccaf")
    @Override
    public void undoAppendDepVal(SmObjectImpl obj, SmDependency dep, SmObjectImpl dep_val, IRepositoryObject destRepo) {
        if (isRepositoryMove(dep, dep_val, destRepo)) {
            moveToRepository(dep_val, destRepo);
        }
        
        obj.getRepositoryObject().depValErased(obj, dep, dep_val);
        
    }

    /**
     * Fix the repository handler of 'obj' and its composition tree to be contained in 'destRepo' repository.
     * @param toMove the object to fix
     * @param destRepoHandle the destination repository object.
     */
    @objid ("1fe29770-3a2d-11e2-bf6c-001ec947ccaf")
    private void moveToRepository(SmObjectImpl toMove, IRepositoryObject destRepoHandle) {
        // First ensure all composition graph is loaded
        CompositionGetter.getAllChildren(Collections.singleton(toMove));
        
        // Process
        final HashSet<SmObjectImpl> moved = new HashSet<>();
        IRepository fromRepo = this.repoSupport.getRepository(toMove);
        doMoveToRepository(toMove, destRepoHandle, moved, fromRepo==this.newBornRepo);
        
        // Move related blobs
        IRepository destRepo = this.repoSupport.getRepository(toMove);
        doMoveBlobs(moved, fromRepo, destRepo);
        
    }

    /**
     * Tells whether appending the given object to the given dependency means
     * moving the object to another IRepository.
     * @param dep the dependency where the object will be appended
     * @param appended the appended object
     * @return <code>true</code> if it is a move across repositories else <code>false</code>.
     */
    @objid ("1fe29777-3a2d-11e2-bf6c-001ec947ccaf")
    private static boolean isRepositoryMove(SmDependency dep, SmObjectImpl appended, IRepositoryObject destRepoHandle) {
        if (isMove(dep, appended)) {
            IRepositoryObject oldRepoHandle = appended.getRepositoryObject();
            return (oldRepoHandle.getRepositoryId() != destRepoHandle.getRepositoryId());
        }
        return false;
    }

    /**
     * Tells whether any IRepository should store the given {@link SmDependency}.
     * @param dep a dependency
     * @return <code>true</code> if the dependency is stored for sure, else <code>false</code>.
     */
    @objid ("1fe2977d-3a2d-11e2-bf6c-001ec947ccaf")
    @Override
    public boolean isStored(SmDependency dep) {
        if (dep.isPartOf() || dep.isComponent() || dep.isSharedComposition()) {
            return true;
        }
        
        // Composition inverse are stored for sure
        SmDependency sym = dep.getSymetric();
        if (dep.getMax() == 1 && (sym.isComponent() || sym.isSharedComposition())) {
            return true;
        }
        return false;
    }

    /**
     * Tells whether appending the given object to the given dependency means
     * moving the object.
     * @param dep the dependency where the object will be appended
     * @param appended the appended object
     * @return <code>true</code> if it is a move else <code>false</code>.
     */
    @objid ("deaf7d83-5d00-40da-ae57-5fdf198242c5")
    private static boolean isMove(SmDependency dep, SmObjectImpl appended) {
        if (dep.isComponent()) {
            return true;
        }
        
        if (!dep.isSharedComposition()) {
            return false;
        }
        
        // Shared composition: we need to know
        // if another composition relation is already defined.
        // Move repository if no other composition relation is defined
        // but this one.
        SmDepVal rel = appended.getCompositionRelation();
        if (rel == null) {
            return true;
        }
        
        if (rel.dep == dep.getSymetric()) {
            return true;
        }
        return false;
    }

    /**
     * To be called only by {@link #moveToRepository(SmObjectImpl, IRepositoryObject)}.
     * <p>
     * Fix the repository handler of 'obj' and its composition tree to be contained in 'destRepo' repository.
     * @param toMove the object to fix
     * @param destRepoHandle the destination repository object.
     * @param moved will contain all objects moved by this method. Shield against composition cycles.
     * @param fromNewBorn whether objects are moved from the new born objects repository
     */
    @objid ("4ceae771-81db-477f-be01-3ecc22595dc3")
    private static void doMoveToRepository(SmObjectImpl toMove, IRepositoryObject destRepoHandle, Collection<SmObjectImpl> moved, boolean fromNewBorn) {
        // Composition graph cycle shield
        if (moved.contains(toMove)) {
            return ;
        }
        
        moved.add(toMove);
        
        // Get composition children when we can still load them
        List<SmObjectImpl> children = toMove.getCompositionChildren();
        
        // Move to repository if different
        IRepositoryObject oldRepoHandle = toMove.getRepositoryObject();
        byte destRepoId = destRepoHandle.getRepositoryId();
        if (oldRepoHandle.getRepositoryId() != destRepoId) {
            // Change repository
            oldRepoHandle.detach(toMove);
        
            if (fromNewBorn) {
                destRepoHandle.attachCreatedObj(toMove);
            } else {
                destRepoHandle.attach(toMove);
            }
        
            // Update live ids
            long oldLiveId = toMove.getLiveId();
            long newLiveId = SmLiveId.make(SmLiveId.getKid(oldLiveId), destRepoId, SmLiveId.getClassId(oldLiveId));
            toMove.init(toMove.getUuid(), newLiveId);
        }
        
        // Recurse to children
        IRepositoryObject newRepoHandle = toMove.getRepositoryObject();
        for (SmObjectImpl child : children) {
            doMoveToRepository(child, newRepoHandle, moved, fromNewBorn);
        }
        
    }

    @objid ("11757475-befa-4133-828b-91cb7838201f")
    private void doMoveBlobs(Collection<SmObjectImpl> toMove, IRepository fromRepo, IRepository destRepo) {
        this.blobSupport.fireObjectsMoved(toMove, fromRepo, destRepo);
    }

}
