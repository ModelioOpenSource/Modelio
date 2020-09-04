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

package org.modelio.vcore.smkernel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.resource.Resource;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Dummy {@link IRepositoryObject} that does nothing and fails on any writing operation.
 * <p>
 * Redefine {@link #writeObjectCalled(SmObjectImpl)} to throw the wanted runtime exception.
 */
@objid ("dfb25bc2-9b5a-4805-981f-eddfe18e58f4")
public abstract class RepositoryObjectStub implements IRepositoryObject {
    @objid ("de78fe63-bbf5-4a04-91db-e5591f9c30fe")
    @Override
    public void attModified(SmObjectImpl obj, SmAttribute att) {
        writeObjectCalled(obj);
    }

    @objid ("2a677cae-f5d9-4fa5-a3fa-0e0b3f766bca")
    @Override
    public void attach(SmObjectImpl obj) {
    }

    @objid ("22ce87dd-a170-4423-936b-a461b4ce6a9e")
    @Override
    public void attachCreatedObj(SmObjectImpl obj) {
    }

    @objid ("be35afd5-c614-41e0-ae5c-4608521bc5f8")
    @Override
    public void depValAppended(SmObjectImpl obj, SmDependency dep, SmObjectImpl val) {
        writeObjectCalled(obj);
    }

    @objid ("b254db36-f1a9-4b59-ac8d-dee9dbde246b")
    @Override
    public void depValErased(SmObjectImpl obj, SmDependency dep, SmObjectImpl val) {
        writeObjectCalled(obj);
    }

    @objid ("f0e085db-af67-4ef1-b02b-fb1cf31a53b4")
    @Override
    public void depValMoved(SmObjectImpl obj, SmDependency dep, SmObjectImpl val) {
        writeObjectCalled(obj);
    }

    @objid ("9549da19-7764-462d-964f-d710659fa4a5")
    @Override
    public void detach(SmObjectImpl obj) {
        // do nothing
    }

    @objid ("d8f7ac61-b60f-4c7f-807a-ed3d5ba26b37")
    @Override
    public Resource getEmfResource() {
        throw new UnsupportedOperationException();
    }

    @objid ("22f3fdae-64dd-4264-b1e5-3e574050c008")
    @Override
    public boolean isAttLoaded(SmObjectImpl obj, SmAttribute att) {
        testLoadedCalled(obj);
        return true;
    }

    @objid ("a4259e18-19b1-4dbb-93e9-7d9d353fc7b4")
    @Override
    public boolean isDepLoaded(SmObjectImpl obj, SmDependency dep) {
        testLoadedCalled(obj);
        return true;
    }

    @objid ("94b02681-c832-4a96-a6a3-2a87ba46a369")
    @Override
    public boolean isDirty(SmObjectImpl obj) {
        testLoadedCalled(obj);
        return true;
    }

    @objid ("5e64b3b3-1ea9-45ac-aeca-d3d143fe44da")
    @Override
    public boolean isPersistent(SmDependency dep) {
        return false;
    }

    @objid ("22ca7890-b9e2-49fb-8d9a-f68fdcb9e701")
    @Override
    public void loadAtt(SmObjectImpl obj, SmAttribute att) {
        // do nothing
        loadCalled(obj);
    }

    @objid ("2186bb09-c1fc-4499-80dd-0237a4c79eac")
    @Override
    public void loadDep(SmObjectImpl obj, SmDependency dep) {
        // do nothing
        loadCalled(obj);
    }

    @objid ("5f3dcd40-768b-447d-84f1-c2e2709cb3f8")
    @Override
    public void setToReload(SmObjectImpl obj) {
        // do nothing
    }

    @objid ("e9ed93a6-3a0e-4d98-920a-c52f04530f0f")
    @Override
    public void unload(SmObjectImpl obj) {
        // do nothing
    }

    /**
     * Called by each loadXxxx(...) method.
     * <p>
     * Does nothing by default. May be redefined to do anything including throwing a runtime exception.
     * @param obj the loaded object
     */
    @objid ("de7d5747-9da3-44b1-a74c-30f58ad74973")
    protected void loadCalled(SmObjectImpl obj) {
        // does nothing by default
    }

    /**
     * Called by each isXxxLoaded(...) method.
     * <p>
     * Does nothing by default. May be redefined to do anything including throwing a runtime exception.
     * @param obj the loaded object
     */
    @objid ("f40d1eac-b67f-4f55-815c-ec8ad595b4db")
    protected void testLoadedCalled(SmObjectImpl obj) {
        // do nothing
    }

    @objid ("f59aaff5-475f-476a-8f48-37072c857a1d")
    protected abstract void writeObjectCalled(SmObjectImpl obj) throws RuntimeException;

}
