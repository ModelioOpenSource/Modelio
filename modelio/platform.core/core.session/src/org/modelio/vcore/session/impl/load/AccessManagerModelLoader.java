/* 
 * Copyright 2013-2018 Modeliosoft
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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.smkernel.IMetaOf;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.SmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Model loader implementation for access manager.
 * <p>
 * This loader allows modifying only the status.
 */
@objid ("33968f70-42cf-11e2-91c9-001ec947ccaf")
class AccessManagerModelLoader implements IModelLoader {
    @objid ("40f9e33e-7fe5-49bf-a573-2f08bf83a5b5")
    private IModelLoader origin;

    @objid ("a8d99b55-42ee-11e2-91c9-001ec947ccaf")
    AccessManagerModelLoader(ModelLoader modelLoader) {
        this.origin = modelLoader;
    }

    @objid ("a8d99b58-42ee-11e2-91c9-001ec947ccaf")
    @Override
    public SmObjectImpl createLoadedObject(SmClass classof, String id) throws DuplicateObjectException {
        throw Unsupported();
    }

    @objid ("a8d99b5e-42ee-11e2-91c9-001ec947ccaf")
    private static UnsupportedOperationException Unsupported() {
        return new UnsupportedOperationException();
    }

    @objid ("a8d99b62-42ee-11e2-91c9-001ec947ccaf")
    @Override
    public SmObjectImpl loadForeignObject(SmClass classof, String id, String name) {
        throw Unsupported();
    }

    @objid ("a8d99b69-42ee-11e2-91c9-001ec947ccaf")
    @Override
    public void loadDependency(SmObjectImpl obj, SmDependency dep, List<SmObjectImpl> newContent) {
        throw Unsupported();
    }

    @objid ("a8d99b71-42ee-11e2-91c9-001ec947ccaf")
    @Override
    public void loadAttribute(SmObjectImpl obj, SmAttribute att, Object newValue) {
        assert (newValue != null) : obj + "." + att.getName() + " = null";
        assert (att == obj.getClassOf().statusAtt()) : att + " is not the status attribute.";
        
        att.setValue(obj.getData(), newValue);
    }

    @objid ("a8d99b7d-42ee-11e2-91c9-001ec947ccaf")
    @Override
    public IMetaOf getMetaOf() {
        return this.origin.getMetaOf();
    }

    @objid ("a8d99b87-42ee-11e2-91c9-001ec947ccaf")
    @Override
    public SmObjectImpl createLoadedObject(SmClass cls, String uuid, SmObjectData d) throws DuplicateObjectException {
        throw Unsupported();
    }

    @objid ("a8d99b94-42ee-11e2-91c9-001ec947ccaf")
    @Override
    public void close() {
        // do nothing.
    }

    @objid ("db806669-4868-11e2-91c9-001ec947ccaf")
    @Override
    public ISmObjectData createObjectData(SmObjectImpl obj) {
        throw Unsupported();
    }

    @objid ("1a0a9187-d9eb-49c7-b546-b1b4926e04f1")
    @Override
    public void setPStatus(SmObjectImpl obj, long trueFlags, long falseFlags, long undefFlags) {
        obj.getData().setPFlags(trueFlags, falseFlags, undefFlags);
    }

    @objid ("1fa5098b-9ccf-452b-a1e9-6acd33ef3d11")
    @Override
    public void setRStatus(SmObjectImpl obj, long trueFlags, long falseFlags, long undefFlags) {
        obj.getData().setRFlags(trueFlags, falseFlags, undefFlags);
    }

    @objid ("e9233f8a-eae0-446d-a3b6-177fedbc34df")
    @Override
    public void begin() {
        // ignore
    }

}
