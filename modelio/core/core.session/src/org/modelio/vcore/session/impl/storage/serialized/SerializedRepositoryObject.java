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

package org.modelio.vcore.session.impl.storage.serialized;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.resource.Resource;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.smkernel.IRepositoryObject;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * {@link IRepositoryObject} implmentation for {@link SerializedRepository}.
 */
@objid ("0071e378-fd1a-1f27-a7da-001ec947cd2a")
class SerializedRepositoryObject implements IRepositoryObject {
    @objid ("0071ebd4-fd1a-1f27-a7da-001ec947cd2a")
    private SerializedRepository repository;

    @objid ("0071f9c6-fd1a-1f27-a7da-001ec947cd2a")
    SerializedRepositoryObject(final SerializedRepository repository) {
        this.repository = repository;
    }

    @objid ("0072126c-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public void attModified(final SmObjectImpl obj, final SmAttribute att) {
        this.repository.setModified(obj);
    }

    @objid ("007235bc-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public void depValAppended(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl val) {
        this.repository.setModified(obj);
    }

    @objid ("00725ee8-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public void depValErased(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl val) {
        this.repository.setModified(obj);
    }

    @objid ("007287a6-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public void depValMoved(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl val) {
        this.repository.setModified(obj);
    }

    @objid ("0072b032-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public boolean isDepLoaded(final SmObjectImpl obj, final SmDependency dep) {
        return this.repository.isLoaded(obj);
    }

    @objid ("0072e23c-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public boolean isPersistent(final SmDependency dep) {
        return !dep.isTransient();
    }

    @objid ("00730f5a-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public boolean isAttLoaded(SmObjectImpl obj, SmAttribute att) {
        return this.repository.isLoaded(obj);
    }

    @objid ("00733c8c-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public void loadDep(SmObjectImpl obj, SmDependency dep) {
        loadAtt(obj, null);
    }

    @objid ("00736112-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public void loadAtt(SmObjectImpl obj, SmAttribute att) {
        if (!this.repository.isLoaded(obj)) {
            try {
                this.repository.load(obj);
            } catch (DuplicateObjectException e) {
                this.repository.getErrorSupport().fireError(e);
            } catch (IOException e) {
                this.repository.getErrorSupport().fireError(e);
            }
        }
    }

    @objid ("00738156-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public void detach(final SmObjectImpl obj) {
        try {
            this.repository.removeFromStorage(obj);
        } catch (IOException e) {
            this.repository.getErrorSupport().fireError(e);
        }
    }

    @objid ("0073a1c2-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public void attach(final SmObjectImpl obj) {
        this.repository.addObject(obj);
    }

    @objid ("00740400-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public byte getRepositoryId() {
        return this.repository.getRepositoryId();
    }

    @objid ("aafaca43-c063-11e1-b511-001ec947ccaf")
    @Override
    public Resource getEmfResource() {
        return this.repository.getEmfResource();
    }

    @objid ("f4bac59b-08b1-11e2-b33c-001ec947ccaf")
    @Override
    public void unload(SmObjectImpl obj) {
        this.repository.unload(obj);
    }

    @objid ("dbab5051-4868-11e2-91c9-001ec947ccaf")
    @Override
    public boolean isDirty(SmObjectImpl obj) {
        return this.repository.isDirty(obj);
    }

    @objid ("52bf0d71-c19c-4d84-86e1-dbe7442c691d")
    @Override
    public void attachCreatedObj(SmObjectImpl obj) {
        attach(obj);
    }

    @objid ("93eec304-9a60-47db-9910-8237cf18bfda")
    @Override
    public void setToReload(SmObjectImpl obj) {
        this.repository.setToReload(obj);
    }

}
