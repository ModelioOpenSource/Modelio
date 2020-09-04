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

package org.modelio.vcore.smkernel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Dummy {@link IRepositoryObject} that does nothing.
 * <p>
 * This is the initial repository object of a {@link SmObjectData}.
 * Allows reading an object but forbids its modification.
 */
@objid ("bba8b402-2ceb-11e2-81f1-001ec947ccaf")
class DummyRepositoryObject extends RepositoryObjectStub {
    /**
     * Get the singleton. To be accessed from the package only.
     */
    @objid ("fca7a9d3-2cec-11e2-81f1-001ec947ccaf")
     static final IRepositoryObject instance = new DummyRepositoryObject();

    /**
     * Private singleton constructor
     * @param data
     */
    @objid ("fca7a9d6-2cec-11e2-81f1-001ec947ccaf")
    protected DummyRepositoryObject() {
        // nothing
    }

    @objid ("fcaa0c32-2cec-11e2-81f1-001ec947ccaf")
    @Override
    protected void writeObjectCalled(SmObjectImpl obj) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(obj+" repository object not initialized.");
    }

    @objid ("fcaa0c5d-2cec-11e2-81f1-001ec947ccaf")
    @Override
    public byte getRepositoryId() {
        return -100;
    }

    @objid ("fcaa0c62-2cec-11e2-81f1-001ec947ccaf")
    @Override
    public Resource getEmfResource() {
        throw new UnsupportedOperationException();
    }

    @objid ("5db9f57f-77fb-4272-b347-7e34874c0308")
    @Override
    public String toString() {
        if (this == instance) {
            return "<no repository yet>";
        } else {
            return super.toString();
        }
    }

    @objid ("d701c4da-e46d-413e-b5f0-b662f0f3e91e")
    @Override
    public void attach(SmObjectImpl obj) {
        throw new UnsupportedOperationException();
    }

    @objid ("79ac4531-7ed1-4599-8c03-e5789a2fb732")
    @Override
    public void attachCreatedObj(SmObjectImpl obj) {
        throw new UnsupportedOperationException();
    }

    /**
     * Get a {@link DummyRepositoryObject} for the given data.
     * <p>
     * Returns the DummyRepositoryObject singleton in normal run, a {@link DummyDebugRepositoryObject}
     * when assertions are enabled.
     * @param smObjectData
     * 
     * @return a dummy repository object.
     */
    @objid ("37cd123c-585d-4bc1-bd34-8159d31d4555")
    static IRepositoryObject getInstance(SmObjectData smObjectData) {
        boolean isassert = false;
        assert (isassert  = true); // intentional side effect
        
        if (isassert) {
            return new DummyDebugRepositoryObject(smObjectData);
        } else {
            return instance;
        }
    }

}
