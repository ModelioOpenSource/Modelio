/* 
 * Copyright 2013-2019 Modeliosoft
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.session.impl.storage.IModelRefresher;
import org.modelio.vcore.session.impl.transactions.smAction.AppendDependencyAction;
import org.modelio.vcore.session.impl.transactions.smAction.CreateElementAction;
import org.modelio.vcore.session.impl.transactions.smAction.DeleteElementAction;
import org.modelio.vcore.session.impl.transactions.smAction.EraseDependencyAction;
import org.modelio.vcore.session.impl.transactions.smAction.IAction;
import org.modelio.vcore.session.impl.transactions.smAction.SetAttributeAction;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.IRepositoryObject;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * {@link IModelRefresher} implementation.
 * <p>
 * Build models change events and fires them when calling {@link #close()}.
 */
@objid ("1559e83f-19f3-11e2-8eb9-001ec947ccaf")
class ModelRefresher extends ModelLoader implements IModelRefresher {
    @objid ("2b43673a-bac9-459f-a578-456eb4060469")
    private SmObjectImpl lastStatusChange = null;

    @objid ("1258e0c4-2f2e-4a1d-9d09-cc09d3af6216")
    private Collection<SmObjectImpl> mayBeOrphan;

    @objid ("da4be954-3794-4af9-9e5e-296814c907ac")
    private DepRefresher depRefresher;

    @objid ("5b7fca04-54e9-41ed-b198-0df0c415351a")
    private Collection<IAction> recordedActions;

    @objid ("afcf508c-0215-4337-a6c1-52e064b8aa55")
    private ModelRefreshDeleter deleter;

    /**
     * Objects that were explicitly deleted.
     */
    @objid ("fdb6c542-bd07-4680-9779-b4ce233218da")
    private Collection<SmObjectImpl> deletedObjs;

    /**
     * Records deleted model object data after they are
     * removed from the cache to prevent them from
     * being GCed until the model change events have been fired.
     */
    @objid ("e771f939-613d-4fd3-befe-820560b36f6b")
    private Collection<ISmObjectData> deletedData;

    @objid ("3a1b1bbf-8213-44da-b422-9b8c9f4f9112")
    private final RefreshEventService refreshEventService;

    @objid ("d46b9f04-832b-42d3-a854-747c2133fbc7")
    private final IRepositoryObject unloadedRepoHandle;

    @objid ("7d88c89e-1c43-11e2-8eb9-001ec947ccaf")
    public ModelRefresher(ModelLoaderConfiguration loaderConfig, Collection<IModelLoader> pool) {
        super(loaderConfig, pool);
        this.refreshEventService = loaderConfig.getRefreshEventService();
        this.unloadedRepoHandle = loaderConfig.getUnloadedRepositoryHandle();
        
        reset();
    }

    @objid ("7d8b2af7-1c43-11e2-8eb9-001ec947ccaf")
    @Override
    protected void doClose() {
        // inherited behavior
        super.doClose();
        
        // Triggers the refresh event service
        this.refreshEventService.addEvent(this.recordedActions, this.deletedData);
        
        // Reinitialize
        reset();
    }

    @objid ("7d88c8a7-1c43-11e2-8eb9-001ec947ccaf")
    @Override
    public SmObjectImpl createLoadedObject(SmClass classof, String id) throws DuplicateObjectException {
        SmObjectImpl ret = super.createLoadedObject(classof, id);
        
        this.recordedActions.add(new CreateElementAction(ret));
        return ret;
    }

    @objid ("7d88c8ae-1c43-11e2-8eb9-001ec947ccaf")
    @Override
    public void loadAttribute(SmObjectImpl obj, SmAttribute att, Object newValue) {
        final Object oldVal = att.getValue(obj.getData());
        
        super.loadAttribute(obj, att, newValue);
        
        if (! Objects.equals(oldVal, newValue)) {
            this.recordedActions.add(new SetAttributeAction(obj, att, oldVal, newValue));
        }
    }

    @objid ("7d88c8b4-1c43-11e2-8eb9-001ec947ccaf")
    @Override
    public void loadDependency(SmObjectImpl obj, SmDependency dep, List<SmObjectImpl> newContent) {
        this.depRefresher.execute(obj, dep, newContent);
    }

    @objid ("d2837cc9-1ebc-11e2-99fc-001ec947ccaf")
    private Collection<SmObjectImpl> getObjsToDelete() {
        Collection<SmObjectImpl> ret = new ArrayList<>(this.deletedObjs.size() + this.mayBeOrphan.size() / 2);
        
        // Add explicitly deleted objects
        ret.addAll(this.deletedObjs);
        
        // Add orphans objects
        for (SmObjectImpl obj : this.mayBeOrphan) {
            if (obj.getCompositionOwner() == null) {
                ret.add(obj);
            }
        }
        return ret;
    }

    /**
     * Reset the model change event to fire.
     */
    @objid ("7d88c8a5-1c43-11e2-8eb9-001ec947ccaf")
    private void reset() {
        this.mayBeOrphan = new HashSet<>();
        this.recordedActions = new ArrayList<>();
        this.depRefresher = new DepRefresher( this.mayBeOrphan, this.recordedActions);
        this.deleter = new ModelRefreshDeleter(this);
        this.deletedObjs = new ArrayList<>();
        this.deletedData = new ArrayList<>();
    }

    @objid ("8fd36252-152e-4714-b0bf-0f1b36269037")
    @Override
    public void setPStatus(SmObjectImpl obj, long trueFlags, long falseFlags, long undefFlags) {
        final ISmObjectData data = obj.getData();
        final long oldStatus = data.getStatus();
        
        data.setPFlags(trueFlags, falseFlags, undefFlags);
        
        long objStatus = data.getStatus();
        
        if (oldStatus != objStatus) {
            this.recordedActions.add(new SetAttributeAction(obj, obj.getClassOf().statusAtt(), oldStatus, objStatus));
        }
    }

    @objid ("3bae9bad-1d7f-42cf-bb22-5cb6afda897a")
    @Override
    public void setRStatus(SmObjectImpl obj, long trueFlags, long falseFlags, long undefFlags) {
        final ISmObjectData data = obj.getData();
        final long oldStatus = data.getStatus();
        
        data.setRFlags(trueFlags, falseFlags, undefFlags);
        
        long objStatus = data.getStatus();
        
        if (oldStatus != objStatus) {
            this.recordedActions.add(new SetAttributeAction(obj, obj.getClassOf().statusAtt(), oldStatus, objStatus));
        }
    }

    @objid ("415ceb93-daaa-4e0f-8a01-4b2fd254b537")
    @Override
    public void deleteObject(SmObjectImpl obj) {
        // record as deleted if not already done
        boolean wasAlive = this.deletedObjs.add(obj);
        
        // Register action if needed (useful only for composition roots)
        if (wasAlive && !this.mayBeOrphan.contains(obj)) {
            this.recordedActions.add(new DeleteElementAction(obj));
        }
    }

    /**
     * Remove a value to a dependency content.
     * <p>
     * Does not remove it from the other side.
     * 
     * @param obj a model object
     * @param dep the dependency to modify
     * @param toRemove the model object to remove
     * @return <code>true</code> if the object to remove was found, <code>false</code> if there
     * was no such object to remove.
     */
    @objid ("caa6dcdf-1331-464c-8d87-21f908249a83")
    boolean eraseObjDepVal(SmObjectImpl obj, SmDependency dep, SmObjectImpl toRemove) {
        // do the job
        boolean ret = dep.remove(obj.getData(), toRemove);
        
        if (ret) {
            if (dep.isComposition() || dep.isSharedComposition()) {
                this.mayBeOrphan.add(toRemove);
            }
        
            this.recordedActions.add(new EraseDependencyAction(obj, dep, toRemove, 0));
        }
        return ret;
    }

    /**
     * Delete the given object individually.
     * 
     * @param obj the object to delete.
     */
    @objid ("ab9f7625-7ea1-4657-89d9-48c962718e50")
    void doDeleteObject(SmObjectImpl obj) {
        ISmObjectData data = obj.getData();
        
        // record deletion action
        this.recordedActions.add(new DeleteElementAction(obj));
        
        // record deleted data before removing from cache to avoid early garbage collection
        this.deletedData.add(data);
        
        //synchronized(this.cacheManager) {
        
            // remove SmObjectImpl and smObjectData from caches
            this.cacheManager.removeFromCache(obj);
        
            // tell the repository to forget the object
            data.getRepositoryObject().unload(obj);
        
            // put a special "unloaded" repository handle to prevent accidental future access
            data.setRepositoryObject(this.unloadedRepoHandle);
        
            // put nice status flags
            data.setRFlags(IRStatus.DELETED | IRStatus.SHELL, IRStatus.BEINGDELETED, 0);
            
            data.setMetaOf(getMetaOf());
        //}
    }

    /**
     * Debug method to dump recorded actions.
     * 
     * @return the string dump
     */
    @objid ("f1800684-5912-45f6-bdf2-18866b99e1da")
    public String dumpRecordedActions() {
        StringBuilder s = new StringBuilder(this.recordedActions.size() * 50);
        for (IAction action : this.recordedActions) {
            s.append(" - ");
            s.append(action.toString());
            s.append("\n");
        }
        return s.toString();
    }

    @objid ("221dd866-54d0-4139-b851-8e348e30cb30")
    @Override
    protected void doFinalizeDeletions() {
        super.doFinalizeDeletions();
        
        // Delete orphan elements
        this.deleter.doDelete(getObjsToDelete());
    }

    /**
     * Same as DependencyLoader but record changes.
     */
    @objid ("7d8b2afa-1c43-11e2-8eb9-001ec947ccaf")
    private static class DepRefresher extends DependencyLoader {
        @objid ("d2837cd0-1ebc-11e2-99fc-001ec947ccaf")
        private Collection<SmObjectImpl> orphanDetection;

        @objid ("3c7f3480-353b-4aec-9f1d-81b8128aaf3d")
        private Collection<IAction> recordedActions;

        @objid ("7d8b2afc-1c43-11e2-8eb9-001ec947ccaf")
        public DepRefresher(Collection<SmObjectImpl> orphanDetection, Collection<IAction> recordedActions) {
            this.orphanDetection = orphanDetection;
            this.recordedActions = recordedActions;
        }

        @objid ("7d8b2b00-1c43-11e2-8eb9-001ec947ccaf")
        @Override
        protected void depValAdded(SmObjectImpl obj, SmDependency dep, SmObjectImpl value) {
            this.recordedActions.add(new AppendDependencyAction(obj, dep, value));
        }

        @objid ("7d8b2b06-1c43-11e2-8eb9-001ec947ccaf")
        @Override
        protected void depValErased(SmObjectImpl obj, SmDependency dep, SmObjectImpl value) {
            if (dep.isComposition() || dep.isSharedComposition()) {
                this.orphanDetection.add(value);
            }
            
            this.recordedActions.add(new EraseDependencyAction(obj, dep, value, 0));
        }

    }

}
