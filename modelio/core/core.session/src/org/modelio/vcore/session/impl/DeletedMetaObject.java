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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.AccessDeniedException;
import org.modelio.vcore.smkernel.IMetaOf;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * The DeletedMetaObject is the special meta object used for deleted objects
 * that are not yet definitely deleted, ie their deletion can still be undone as
 * no save operation occured since their deletion.
 * The DeletedMetaObject will simply forbid any modification on a deleted object
 * but will allow 'getter' accessors to work providing navigation facilities in
 * the deleted objects graph.
 */
@objid ("00050190-0dbb-1f20-85a5-001ec947cd2a")
public class DeletedMetaObject implements IMetaOf {
    @objid ("00050afa-0dbb-1f20-85a5-001ec947cd2a")
    protected IMetaOf metaObject;

    @objid ("00051040-0dbb-1f20-85a5-001ec947cd2a")
    @Override
    public boolean appendObjDepVal(SmObjectImpl obj, SmDependency dep, SmObjectImpl dep_val) {
        throw throwDeletedObject(obj);
    }

    @objid ("000510d6-0dbb-1f20-85a5-001ec947cd2a")
    @Override
    public boolean appendObjDepValIndex(SmObjectImpl obj, SmDependency dep, SmObjectImpl dep_val, final int index) {
        throw throwDeletedObject(obj);
    }

    @objid ("0005131a-0dbb-1f20-85a5-001ec947cd2a")
    @Override
    public void createObject(SmObjectImpl obj) {
        // This code should never ever be called.
        throw throwDeletedObject(obj);
    }

    @objid ("000513c4-0dbb-1f20-85a5-001ec947cd2a")
    @Override
    public void deleteObject(SmObjectImpl obj) {
        // Deleting an already deleted element has no effect.
    }

    @objid ("0005023a-0dbb-1f20-85a5-001ec947cd2a")
    @Override
    public boolean eraseObjDepVal(SmObjectImpl obj, SmDependency dep, SmObjectImpl dep_val) {
        throw throwDeletedObject(obj);
    }

    @objid ("000502f8-0dbb-1f20-85a5-001ec947cd2a")
    @Override
    public Object getObjAttVal(SmObjectImpl obj, SmAttribute att) {
        ISmObjectData data = obj.getData();
        return att.getValue(data);
    }

    @objid ("0005038e-0dbb-1f20-85a5-001ec947cd2a")
    @Override
    public Object getObjDepVal(SmObjectImpl obj, SmDependency dep) {
        ISmObjectData data = obj.getData();
        return dep.getValue(data);
    }

    @objid ("000506fe-0dbb-1f20-85a5-001ec947cd2a")
    @Override
    public boolean moveObjDepVal(SmObjectImpl obj, SmDependency dep, SmObjectImpl moving_ref, final int offset) {
        throw throwDeletedObject(obj);
    }

    @objid ("0005078a-0dbb-1f20-85a5-001ec947cd2a")
    @Override
    public void objUndeleted(SmObjectImpl obj) {
        // Restore original metaobject
        obj.getData().setMetaOf(this.metaObject);
        
        // The original metaobject has to be informed,
        // as it's to him (or this) to tell the storage handler the element
        // is undeleted
        obj.getData().getMetaOf().objUndeleted(obj);
    }

    @objid ("00050820-0dbb-1f20-85a5-001ec947cd2a")
    @Override
    public void setActionRecording(final boolean val) {
        throw new UnsupportedOperationException();
    }

    @objid ("00050942-0dbb-1f20-85a5-001ec947cd2a")
    @Override
    public boolean setObjAttVal(SmObjectImpl obj, SmAttribute att, final Object value) {
        throw throwDeletedObject(obj);
    }

    @objid ("000509d8-0dbb-1f20-85a5-001ec947cd2a")
    @Override
    public void silentActionRemove(SmObjectImpl obj) {
        throw throwDeletedObject(obj);
    }

    @objid ("000ae9e8-702c-1f21-85a5-001ec947cd2a")
    @Override
    public boolean setObjDepVal(final SmObjectImpl smObject, final SmDependency dep, final SmObjectImpl value, final int index) {
        throw throwDeletedObject(smObject);
    }

    /**
     * Set the initial meta object of deleted objects.
     * 
     * @param initialMetaObject the initial meta object
     */
    @objid ("002c7b62-eb1c-1f22-8c06-001ec947cd2a")
    public void setMetaObject(final IMetaOf initialMetaObject) {
        this.metaObject = initialMetaObject;
    }

    /**
     * Initialize the meta object.
     */
    @objid ("002c91ce-eb1c-1f22-8c06-001ec947cd2a")
    public DeletedMetaObject() {
    }

    /**
     * Builds an AccessDeniedException for the given model object.
     * 
     * @param smObject a model object
     * @return AccessDeniedException a ready to throw exception
     */
    @objid ("e81e329b-3558-11e2-a87b-001ec947ccaf")
    private static AccessDeniedException throwDeletedObject(final SmObjectImpl smObject) {
        return new AccessDeniedException(smObject+" is deleted.", smObject);
    }

    @objid ("5cd04d9e-27a9-4598-bedb-6f2523d85ebb")
    @Override
    public void objStatusChanged(SmObjectImpl obj, long oldStatus, long newStatus) {
        // ignore
    }

    @objid ("86e6803c-a2ba-40fe-bb3a-20f36523c054")
    @Override
    public void importObject(SmObjectImpl obj) {
        // This code should never ever be called.
        throw throwDeletedObject(obj);
    }

}
