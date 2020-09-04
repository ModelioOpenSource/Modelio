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

package org.modelio.vstore.jdbm;

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
import org.modelio.vstore.jdbm.impl.Helper;

@objid ("78c589ac-293d-4f22-ae29-b9a25f107fc1")
class JdbmStorageHandler implements IRepositoryObject {
    @objid ("191a9b39-a920-4749-91dd-1a0dad2ef042")
    private final JdbmRepository repo;

    @objid ("10fbcdaa-4265-4bc1-8e42-94a6cab53133")
    public JdbmStorageHandler(JdbmRepository jdbmRepository) {
        this.repo = jdbmRepository;
    }

    @objid ("395d92cc-3cf6-4660-9c8e-9439b210b308")
    @Override
    public void attModified(SmObjectImpl obj, SmAttribute att) {
        setDirty(obj);
    }

    @objid ("759d2c4e-3511-4ae9-bced-f2a57d509e17")
    private void setDirty(SmObjectImpl obj) {
        ISmObjectData data = obj.getData();
        if (! isDirty(data)) {
            data.setRFlags(IRStatus.REPO_DIRTY, StatusState.TRUE);
            this.repo.addDirty(obj);
        }
    }

    @objid ("1446e1e9-bf70-47c0-9f18-11c694a4c447")
    @Override
    public void depValAppended(SmObjectImpl obj, SmDependency dep, SmObjectImpl val) {
        setDirty(obj);
    }

    @objid ("9cfde3d4-fbb1-4d0f-af78-6e13e5dfc1f4")
    @Override
    public void depValErased(SmObjectImpl obj, SmDependency dep, SmObjectImpl val) {
        setDirty(obj);
    }

    @objid ("71406f8b-a946-4e09-933c-fcadcd309496")
    @Override
    public void depValMoved(SmObjectImpl obj, SmDependency dep, SmObjectImpl val) {
        setDirty(obj);
    }

    @objid ("f6cdc8e1-4dec-4a73-aa2e-4dae5f45ba21")
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

    @objid ("f7a5b3d3-e35e-4257-bc39-20fae7a4f3df")
    @Override
    public boolean isPersistent(SmDependency dep) {
        return Helper.isPersistent(dep);
    }

    @objid ("f2871e64-3039-411b-9c3f-ea130dc1f18e")
    @Override
    public boolean isAttLoaded(SmObjectImpl obj, SmAttribute att) {
        return obj.getData().hasAllStatus(IRStatus.REPO_LOADED) == StatusState.TRUE;
    }

    @objid ("175697bd-dccf-40aa-9985-f0c7fe4f573f")
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

    @objid ("5e499d64-1948-4929-8d91-43b1e496ef96")
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

    @objid ("2e344311-8b80-4173-8cfc-631cbfd8fe04")
    @Override
    public byte getRepositoryId() {
        return this.repo.getRepositoryId();
    }

    @objid ("e22dbc23-61ab-4862-9c79-7570d267740b")
    @Override
    public Resource getEmfResource() {
        return this.repo.getEmfResource();
    }

    @objid ("6ce65b4d-1ab2-4d8e-8221-73a0f72df3f3")
    @Override
    public void detach(SmObjectImpl obj) {
        obj.getData().setRFlags(IRStatus.MASK_REPO, StatusState.FALSE);
        this.repo.removeObj(obj);
    }

    @objid ("bba17e72-32ac-476a-a135-d8d4837c7fcc")
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

    @objid ("e585d2b9-298a-4e84-94a6-4801d2f1abf5")
    @Override
    public void unload(SmObjectImpl obj) {
        this.repo.unloadObject(obj);
    }

    @objid ("673ac912-ec60-4bfb-bc2d-c2dedd4072c2")
    @Override
    public boolean isDirty(SmObjectImpl obj) {
        return isDirty(obj.getData());
    }

    @objid ("8c2f236b-8d28-4c72-b748-a6c762522330")
    public boolean isDirty(ISmObjectData data) {
        return data.hasAllStatus(IRStatus.REPO_DIRTY) == StatusState.TRUE;
    }

    @objid ("ae8cb865-89f8-4447-8fbd-cb3765d12279")
    @Override
    public void attachCreatedObj(SmObjectImpl obj) {
        attach(obj);
    }

    @objid ("acd35965-a2d4-4529-b7dd-cd50df3bd2a8")
    @Override
    public void setToReload(SmObjectImpl obj) {
        obj.getData().setRFlags(0, IRStatus.REPO_LOADED, 0);
    }

}
