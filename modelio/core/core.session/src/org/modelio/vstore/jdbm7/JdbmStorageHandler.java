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

package org.modelio.vstore.jdbm7;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.resource.Resource;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.IRepositoryObject;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.StatusState;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vstore.jdbm7.impl.Helper;

@objid ("b53d633c-8464-4aa9-ab21-ec6e4941072a")
class JdbmStorageHandler implements IRepositoryObject {
    @objid ("07c54785-19b9-4385-b477-61b0b05fd574")
    private JdbmRepository repo;

    @objid ("00e23140-d259-4e93-aec7-306441400122")
    public JdbmStorageHandler(JdbmRepository jdbmRepository) {
        this.repo = jdbmRepository;
    }

    @objid ("32fbc56f-3144-4285-87ca-cbe797a324bd")
    @Override
    public void attModified(SmObjectImpl obj, SmAttribute att) {
        setDirty(obj);
    }

    @objid ("81545a28-2816-41bf-a9df-e6b7563c3cef")
    private void setDirty(SmObjectImpl obj) {
        ISmObjectData data = obj.getData();
        if (! isDirty(data)) {
            data.setRFlags(IRStatus.REPO_DIRTY, StatusState.TRUE);
            this.repo.addDirty(obj);
        }
    }

    @objid ("eda2ec46-0501-4914-bf15-03717e075940")
    @Override
    public void depValAppended(SmObjectImpl obj, SmDependency dep, SmObjectImpl val) {
        setDirty(obj);
    }

    @objid ("5cedd375-f07f-44c6-8164-5cda18439738")
    @Override
    public void depValErased(SmObjectImpl obj, SmDependency dep, SmObjectImpl val) {
        setDirty(obj);
    }

    @objid ("884e2ca2-d974-4b63-83d1-c5149a8fae9d")
    @Override
    public void depValMoved(SmObjectImpl obj, SmDependency dep, SmObjectImpl val) {
        setDirty(obj);
    }

    @objid ("f9eea9ea-b37b-426f-9c56-17a06f59b2af")
    @Override
    public boolean isDepLoaded(SmObjectImpl obj, SmDependency dep) {
        if (isPersistent(dep)) {
            return obj.getData().hasAllStatus(IRStatus.REPO_LOADED) == StatusState.TRUE;
        } else {
            // Shortcut for 0..1 dependency inverse :
            // if the dependency is filled then it was loaded from the other side
            if (! dep.isMultiple() && dep.getValue(obj.getData()) != null) {
                return true;
            }
        
            return obj.getData().hasAllStatus(IRStatus.REPO_USERS_LOADED) == StatusState.TRUE;
        }
    }

    @objid ("db49ea72-07e5-42ac-ae4b-e0158a070a4a")
    @Override
    public boolean isPersistent(SmDependency dep) {
        return Helper.isPersistent(dep);
    }

    @objid ("7e4c41a1-ff24-4788-bd69-14a27e500d92")
    @Override
    public boolean isAttLoaded(SmObjectImpl obj, SmAttribute att) {
        return obj.getData().hasAllStatus(IRStatus.REPO_LOADED) == StatusState.TRUE;
    }

    @objid ("780f4408-7646-4851-859f-35de89f12006")
    @Override
    public void loadDep(SmObjectImpl obj, SmDependency dep) {
        if (!isDepLoaded(obj, dep)) {
            synchronized(this.repo) {
                // Redo the test in case of the object was being loaded
                if (!isDepLoaded(obj, dep)) {
                    if (isPersistent(dep)) {
                        this.repo.loadObj(obj);
                    } else {
                        this.repo.loadDynamicDep(obj, dep);
                        obj.getData().setRFlags(IRStatus.REPO_USERS_LOADED, 0, 0);
                    }
                }
            }
        }
    }

    @objid ("8fbe4252-72c0-40db-b955-b81983e9c718")
    @Override
    public void loadAtt(SmObjectImpl obj, SmAttribute att) {
        if (! isAttLoaded(obj, att)) {
            synchronized(this.repo) {
                // Redo the test in case of the object was being loaded
                if (! isAttLoaded(obj, att)) {
                    this.repo.loadObj(obj);
                }
            }
        }
    }

    @objid ("ea4a85f0-2c9b-4e84-8515-10157650112b")
    @Override
    public byte getRepositoryId() {
        return this.repo.getRepositoryId();
    }

    @objid ("3a316d9d-8dbd-4cc6-ae40-27e6eb16de17")
    @Override
    public Resource getEmfResource() {
        return this.repo.getEmfResource();
    }

    @objid ("cc9b16b5-01b8-4e9d-8d72-d107bee3f1c2")
    @Override
    public void detach(SmObjectImpl obj) {
        obj.getData().setRFlags(IRStatus.MASK_REPO, StatusState.FALSE);
        this.repo.removeObj(obj);
    }

    @objid ("63bf6faa-8558-4637-8fcb-f310f9cbfdf8")
    @Override
    public void attach(SmObjectImpl obj) {
        ISmObjectData data = obj.getData();
        
        // Set as loaded and modified
        data.setRFlags(IRStatus.REPO_LOADED, StatusState.TRUE);
        setDirty(obj);
        
        // Attach repository object
        data.setRepositoryObject(this);
        
        try {
            this.repo.getLoadCache().addToCache(obj);
        } catch (DuplicateObjectException e) {
            this.repo.getErrorSupport().fireError(e);
        }
    }

    @objid ("0583c262-5e90-4b30-abbd-4432a648ca1c")
    @Override
    public void unload(SmObjectImpl obj) {
        this.repo.unloadObject(obj);
    }

    @objid ("857d0a9e-ac0a-4a51-b27a-2d75c0a0501c")
    @Override
    public boolean isDirty(SmObjectImpl obj) {
        return isDirty(obj.getData());
    }

    @objid ("88923b62-7364-4ba3-aac8-f8bbe390541b")
    public boolean isDirty(ISmObjectData data) {
        return data.hasAllStatus(IRStatus.REPO_DIRTY) == StatusState.TRUE;
    }

    @objid ("74bda43b-10f5-464b-a87e-f100a4938453")
    @Override
    public void attachCreatedObj(SmObjectImpl obj) {
        attach(obj);
    }

    @objid ("cffa3e09-ea5a-421e-a250-23d46a16ca4e")
    @Override
    public void setToReload(SmObjectImpl obj) {
        obj.getData().setRFlags(0, IRStatus.REPO_LOADED, 0);
    }

}
