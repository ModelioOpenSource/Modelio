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
package org.modelio.vcore.session.impl;

import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.impl.cache.CacheHandle;
import org.modelio.vcore.session.impl.handles.IAccessHandle;
import org.modelio.vcore.session.impl.handles.IStorageHandle;
import org.modelio.vcore.session.impl.transactions.ActionHandle;
import org.modelio.vcore.session.impl.transactions.events.StatusChangeManager;
import org.modelio.vcore.smkernel.IMetaOf;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.IRepositoryObject;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.StatusState;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMultipleDependency;

/**
 * The StdMetaObject is the meta object used for 'normal' objects in the kernel.
 * The actual behavior of these objects (memory handling, persistence,
 * transaction and so on) is controlled by the StdMetaObject. The StdMetaObject
 * currently delegates most of its work to specialized 'handles' that can be
 * reconfigured when needed to specialize or modify the object behavior.
 */
@objid ("002a4eb4-19b6-1f18-8b7a-001ec947cd2a")
@SuppressWarnings("static-method")
public class StdMetaObject implements IMetaOf {
    @objid ("d81508f1-4a13-47dd-964d-9b14c4b5e805")
    private final IAccessHandle accessHandle;

    @objid ("2d12186b-0e0e-4142-a77b-56ba071cd356")
    private final ActionHandle actionHandle;

    @objid ("64f5ced4-1aeb-4fa3-bba3-349f83627331")
    private final CacheHandle cacheHandle;

    @objid ("29cbb59a-d8ba-4d56-a4f6-0573dbc8836c")
    private final DeletedMetaObject deletedMetaObject;

    @objid ("59af4bf6-3656-4647-b02e-086a01d0e24a")
    private StatusChangeManager statusChangeManager;

    @objid ("b438e78f-a2b8-4b28-9105-3537dcfdc2e9")
    private final IStorageHandle storageHandle;

    /**
     * Initialize the meta object.
     * @param storageHandle the storage handle.
     * @param accessHandle the access handle
     * @param actionHandle the transaction handle
     * @param cacheHandle the cache handle
     * @param deletedMetaObject the meta object to put on deleted objects
     * @param statusChangeManager the status change manager.
     */
    @objid ("002a4a2c-19b6-1f18-8b7a-001ec947cd2a")
    public  StdMetaObject(IStorageHandle storageHandle, IAccessHandle accessHandle, ActionHandle actionHandle, CacheHandle cacheHandle, DeletedMetaObject deletedMetaObject, StatusChangeManager statusChangeManager) {
        this.storageHandle = storageHandle;
        this.accessHandle = accessHandle;
        this.cacheHandle = cacheHandle;
        this.actionHandle = actionHandle;
        this.deletedMetaObject = deletedMetaObject;
        this.statusChangeManager = statusChangeManager;
        
    }

    @objid ("002a3f3c-19b6-1f18-8b7a-001ec947cd2a")
    @Override
    public boolean appendObjDepVal(SmObjectImpl obj, SmDependency dep, SmObjectImpl dep_val) {
        boolean jobDone = false;
        boolean storageDone = false;
        boolean success = false;
        
        final ISmObjectData data = obj.getData();
        
        final IRepositoryObject oldValStore = (dep_val != null) ? dep_val.getRepositoryObject() : null;
        try {
            // Load the dep only if it is stored.
            if (this.storageHandle.isStored(dep)) {
                this.storageHandle.loadDep(obj, data, dep);
            }
        
            this.accessHandle.checkAccessFor(obj, dep, dep_val);
        
            // Check the value type
            dep.assertValueType(obj, dep_val);
        
            // Do the job on the SmObjectImpl
            jobDone = dep.add(data, dep_val);
        
            if (!jobDone) {
                return false;
            }
        
            // Storage handling
            this.storageHandle.appendObjDepVal(data, obj, dep, dep_val);
            storageDone = true;
        
            // Action handling
            this.actionHandle.appendObjDepVal(obj, dep, dep_val);
        
            success = true;
            return true;
        
        } finally {
            if (!success) {
                // Cleanup
                if (jobDone) {
                    dep.remove(data, dep_val);
                }
                if (storageDone && oldValStore != null) {
                    this.storageHandle.undoAppendDepVal(obj, dep, dep_val, oldValStore);
                    // if (cacheDone) cacheHandle.eraseObjDepVal(obj, dep,
                    // dep_val);
                }
            }
        }
        
    }

    @objid ("002a3fdc-19b6-1f18-8b7a-001ec947cd2a")
    @Override
    public boolean appendObjDepValIndex(SmObjectImpl obj, SmDependency dep, SmObjectImpl dep_val, final int index) {
        boolean jobDone = false;
        boolean storageDone = false;
        boolean success = false;
        
        final ISmObjectData data = obj.getData();
        final IRepositoryObject oldValStore = (dep_val != null) ? dep_val.getRepositoryObject() : null;
        
        try {
            // Load the dep only if it is stored. Don't load dynamic deps.
            if (this.storageHandle.isStored(dep)) {
                this.storageHandle.loadDep(obj, data, dep);
            }
        
            this.accessHandle.checkAccessFor(obj, dep, dep_val);
        
            // Check the value type
            dep.assertValueType(obj, dep_val);
        
            // Do the job on the SmObjectImpl
            dep.insert(data, dep_val, index);
            jobDone = true;
        
            // Storage handling
            this.storageHandle.appendObjDepVal(data, obj, dep, dep_val);
            storageDone = true;
        
            // Action handling
            this.actionHandle.appendObjDepValIndex(obj, dep, dep_val, index);
        
            success = true;
            return true;
        
        } finally {
            if (!success) {
                // Cleanup
                if (jobDone) {
                    dep.remove(data, dep_val);
                }
                if (storageDone && oldValStore != null) {
                    this.storageHandle.undoAppendDepVal(obj, dep, dep_val, oldValStore);
                    // if (cacheDone) cacheHandle.eraseObjDepVal(obj, dep,
                    // dep_val);
                }
            }
        
        }
        
    }

    @objid ("002a4252-19b6-1f18-8b7a-001ec947cd2a")
    @Override
    public void createObject(SmObjectImpl obj) {
        // boolean storageDone = false;
        boolean cacheDone = false;
        
        boolean success = false;
        
        final ISmObjectData data = obj.getData();
        
        try {
            // Ensure that the obj/att/dep is loaded : nothing to do
        
            // Do the job on the SmObjectImpl : nothing to do
        
            // Storage handling
            data.getRepositoryObject().attachCreatedObj(obj);
            // storageDone = true;
        
            // Memory model handling
            this.cacheHandle.createObject(obj);
            cacheDone = true;
        
            // Action handling
            this.actionHandle.createObject(obj);
        
            success = true;
        
        } finally {
            if (!success) {
                // Don't remove from storage now, it will be done by the cache
                // manager when emptying the deleted objects list.
                // if (storageDone) obj.getStorage().deleteObject(obj);
        
                if (cacheDone) {
                    this.cacheHandle.deleteObject(obj);
                }
            }
        }
        
    }

    @objid ("002a42f2-19b6-1f18-8b7a-001ec947cd2a")
    @Override
    public void deleteObject(SmObjectImpl obj) {
        final ISmObjectData data = obj.getData();
        
        if (data.hasAnyStatus(IRStatus.DELETED) == StatusState.TRUE) {
            // if object is already deleted ignore it
            return;
        }
        
        // Cannot delete an object that cannot be modified...
        // Exception : shell objects can be deleted
        if (! obj.isShell()) {
            this.accessHandle.checkAccess(obj);
        }
        
        boolean jobDone = false;
        boolean cacheDone = false;
        boolean success = false;
        try {
            // Ensure that the obj/att/dep is loaded
            // ;
        
            // Do the job on the SmObjectImpl
            data.setRFlags(IRStatus.DELETED, StatusState.TRUE);
            data.setMetaOf(this.deletedMetaObject);
        
            jobDone = true;
        
            // Storage handling
            // ;
        
            // Memory model handling
            this.cacheHandle.deleteObject(obj);
            cacheDone = true;
        
            // Action handling
            this.actionHandle.deleteObject(obj);
            success = true;
        
        } finally {
            if (!success) {
                // Cleanup
                if (cacheDone) {
                    this.cacheHandle.objUndeleted(obj);
                }
        
                if (jobDone) {
                    data.setRFlags(IRStatus.DELETED, StatusState.FALSE);
                    data.setMetaOf(this);
                }
            }
        }
        
    }

    @objid ("002a4388-19b6-1f18-8b7a-001ec947cd2a")
    @Override
    public boolean eraseObjDepVal(SmObjectImpl obj, SmDependency dep, SmObjectImpl dep_val) {
        boolean jobDone = false;
        boolean storageDone = false;
        boolean success = false;
        
        final ISmObjectData data = obj.getData();
        
        try {
            // Compute old index and load the dependency only if
            // stored and not containing the element.
            int oldIndex ;
            oldIndex = this.storageHandle.loadDepIndexOf(obj, dep, dep_val, data);
        
            this.accessHandle.checkAccessFor(obj, dep, dep_val);
        
            // Do the job on the SmObjectImpl
            jobDone = dep.remove(data, dep_val);
            if (!jobDone) {
                return false;
            }
        
            // Storage handling
            data.getRepositoryObject().depValErased(obj, dep, dep_val);
            storageDone = true;
        
            // Action handling
            this.actionHandle.eraseObjDepVal(obj, dep, dep_val, oldIndex);
        
            success = true;
            return true;
        
        } finally {
            if (!success) {
                // Cleanup
                if (jobDone) {
                    dep.add(data, dep_val);
                }
                if (storageDone) {
                    data.getRepositoryObject().depValAppended(obj, dep, dep_val);
                }
            }
        }
        
    }

    @objid ("002a4428-19b6-1f18-8b7a-001ec947cd2a")
    @Override
    public Object getObjAttVal(SmObjectImpl obj, SmAttribute att) {
        final ISmObjectData data = obj.getData();
        
        // Ensure that the object is loaded
        this.storageHandle.loadAtt(obj, att, data);
        
        // No other handling here
        return att.getValue(data);
    }

    @objid ("002a4d10-19b6-1f18-8b7a-001ec947cd2a")
    @Override
    public Object getObjDepVal(SmObjectImpl obj, SmDependency dep) {
        final ISmObjectData data = obj.getData();
        
        this.storageHandle.loadDep(obj, data, dep);
        return dep.getValue(data);
    }

    @objid ("1dde1136-edcd-4775-a2fb-88aa1480302a")
    @Override
    public void importObject(SmObjectImpl obj) {
        // boolean storageDone = false;
        boolean cacheDone = false;
        
        boolean success = false;
        
        final ISmObjectData data = obj.getData();
        
        try {
            // Ensure that the obj/att/dep is loaded : nothing to do
        
            // Do the job on the SmObjectImpl : nothing to do
        
            // Storage handling
            data.getRepositoryObject().attach(obj);
            // storageDone = true;
        
            // Memory model handling
            this.cacheHandle.createObject(obj);
            cacheDone = true;
        
            // Action handling
            this.actionHandle.createObject(obj);
        
            success = true;
        
        } finally {
            if (!success) {
                // Don't remove from storage now, it will be done by the cache
                // manager when emptying the deleted objects list.
                // if (storageDone) obj.getStorage().deleteObject(obj);
        
                if (cacheDone) {
                    this.cacheHandle.deleteObject(obj);
                }
            }
        }
        
    }

    @objid ("002a4694-19b6-1f18-8b7a-001ec947cd2a")
    @Override
    public boolean moveObjDepVal(SmObjectImpl obj, SmDependency dep, SmObjectImpl moving_ref, final int offset) {
        boolean jobDone = false;
        boolean storageDone = false;
        
        boolean success = false;
        
        final ISmObjectData data = obj.getData();
        
        try {
            this.storageHandle.loadDep(obj, data, dep);
        
            this.accessHandle.checkAccessFor(obj, dep, moving_ref);
        
            // Do the job on the SmObjectImpl
            dep.moveRef(data, moving_ref, offset);
            jobDone = true;
        
            // Storage handling
            data.getRepositoryObject().depValMoved(obj, dep, moving_ref);
            storageDone = true;
        
            // Memory model handling
            // cacheDone = true;
        
            // Action handling
            this.actionHandle.moveObjDepVal(obj, dep, moving_ref, offset);
        
            success = true;
            return true;
        
        } finally {
            if (!success) {
                // Cleanup
                if (jobDone) {
                    dep.moveRef(data, moving_ref, -offset);
                }
                if (storageDone) {
                    data.getRepositoryObject().depValMoved(obj, dep, moving_ref);
                }
            }
        }
        
    }

    @objid ("633ad50f-2b0a-44d7-be7f-b25642be2a39")
    @Override
    public void objStatusChanged(SmObjectImpl obj, long oldStatus, long newStatus) {
        this.statusChangeManager.objStatusChanged(obj, oldStatus);
    }

    @objid ("002a472a-19b6-1f18-8b7a-001ec947cd2a")
    @Override
    public void objUndeleted(SmObjectImpl obj) {
        boolean jobDone = false;
        // boolean storageDone = false;
        //boolean cacheDone = false;
        boolean success = false;
        
        final ISmObjectData data = obj.getData();
        
        try {
            // Ensure that the obj/att/dep is loaded
            // ;
        
            // Do the job on the SmObjectImpl
            setAsValid(data);
            jobDone = true;
        
            // Storage handling
            // storageDone = true;
        
            // Memory model handling
            this.cacheHandle.objUndeleted(obj);
            //cacheDone = true;
        
            success = true;
        
        } finally {
            if (!success) {
                // Cleanup
                if (jobDone) {
                    data.setRFlags(IRStatus.DELETED, StatusState.TRUE);
                }
        
                //if (cacheDone) {
                //    this.cacheHandle.deleteObject(obj);
                //}
            }
        }
        
    }

    @objid ("002a47ca-19b6-1f18-8b7a-001ec947cd2a")
    @Override
    public void setActionRecording(final boolean val) {
        this.actionHandle.setActionRecording(val);
    }

    @objid ("002a4860-19b6-1f18-8b7a-001ec947cd2a")
    @Override
    public boolean setObjAttVal(SmObjectImpl obj, SmAttribute att, final Object att_val) {
        Objects.requireNonNull(att_val , obj+"."+att.getName()+" = null");
        
        boolean jobDone = false;
        boolean storageDone = false;
        boolean success = false;
        
        final ISmObjectData data = obj.getData();
        
        final Object oldVal = att.getValue(data);
        
        // Do not carry out a setAtt if new value is not different from the current value.
        if (att_val.equals(oldVal)) {
            return false; // false = nothing done.
        }
        
        try {
            // Ensure that the obj/att/dep is loaded
            // ;
        
            this.accessHandle.checkAccessFor(obj, att, null);
        
            // Do the job on the SmObjectImpl
            att.setValue(data, att_val);
            jobDone = true;
        
            // Storage handling
            data.getRepositoryObject().attModified(obj, att);
            storageDone = true;
        
            // Action handling
            // Highly tricky here: call with the old_value
            // and obj already modified with the new value
            this.actionHandle.setObjAttVal(obj, att, oldVal);
        
            success = true;
            return true;
        
        } finally {
            if (!success) {
                // Cleanup
                if (jobDone) {
                    att.setValue(data, oldVal);
                }
                if (storageDone) {
                    data.getRepositoryObject().attModified(obj, att);
                    // if (cacheDone) cacheHandle.setObjAttVal(obj, att,
                    // oldVal);
                }
            }
        }
        
    }

    @objid ("0004df4e-702c-1f21-85a5-001ec947cd2a")
    @Override
    public boolean setObjDepVal(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl dep_val, final int index) {
        boolean jobDone = false;
        boolean storageDone = false;
        boolean success = false;
        
        final ISmObjectData data = obj.getData();
        
        try {
            // Ensure that the obj/att/dep is loaded
            this.storageHandle.loadDep(obj, data, dep);
        
            this.accessHandle.checkAccessFor(obj, dep, dep_val);
        
            // Do the job on the SmObjectImpl
            if (dep.isMultiple()) {
                dep.assertValueType(obj, dep_val);
                final List<SmObjectImpl> list = ((SmMultipleDependency) dep).getValueList(data);
                list.set(index, dep_val);
            } else {
                throw new IllegalArgumentException(dep + " is a simple dependency.");
            }
            jobDone = true;
        
            // Storage handling
            data.getRepositoryObject().depValAppended(obj, dep, dep_val);
            storageDone = true;
        
            // Action handling
            this.actionHandle.appendObjDepValIndex(obj, dep, dep_val, index);
        
            success = true;
            return true;
        
        } finally {
            if (!success) {
                // Cleanup
                if (jobDone) {
                    dep.remove(data, dep_val);
                }
                if (storageDone) {
                    data.getRepositoryObject().depValErased(obj, dep, dep_val);
                }
            }
        
        }
        
    }

    @objid ("002a48f6-19b6-1f18-8b7a-001ec947cd2a")
    @Override
    public void silentActionRemove(SmObjectImpl obj) {
        boolean jobDone = false;
        // boolean storageDone = false;
        boolean cacheDone = false;
        
        boolean success = false;
        
        final ISmObjectData data = obj.getData();
        try {
            // Ensure that the obj/att/dep is loaded
            // ;
        
            // Do the job on the SmObjectImpl: only change the handle and the
            // flags
            data.setRFlags(IRStatus.DELETED, StatusState.TRUE);
            data.setMetaOf(this.deletedMetaObject);
            jobDone = true;
        
            // Storage handling
            // ;
        
            // Memory model handling
            this.cacheHandle.deleteObject(obj);
            cacheDone = true;
        
            // Action handling
            // ;
        
            success = true;
        } finally {
            // Cleanup
            if (!success) {
                if (jobDone) {
                    data.setMetaOf(this);
                    setAsValid(data);
                }
                if (cacheDone) {
                    this.cacheHandle.objUndeleted(obj);
                }
            }
        }
        
    }

    @objid ("c8dbff94-0de3-498d-af2e-60792a001f8f")
    private static void setAsValid(final ISmObjectData data) {
        data.setRFlags(IRStatus.DELETED | IRStatus.BEINGDELETED, StatusState.FALSE);
    }

}
