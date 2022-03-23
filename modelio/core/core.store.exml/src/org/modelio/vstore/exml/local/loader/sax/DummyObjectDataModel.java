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
package org.modelio.vstore.exml.local.loader.sax;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vstore.exml.common.model.DependencyNotFoundException;

/**
 * Data model for an object to be skipped while loading.
 */
@objid ("96321cd4-ce77-42c1-9ef8-8f2df4ba01c1")
class DummyObjectDataModel implements IObjectDataModel {
    @objid ("4ffe458c-6689-41a6-a22d-e53beb39397c")
    private AbstractState currentstate;

    @objid ("5f82cdc3-cb68-4ef8-8bc8-e0acc9b5b45c")
    private final SmObjectImpl skippedObj;

    @objid ("22513207-356f-48ee-88af-c84b7b086c63")
    public  DummyObjectDataModel(SmObjectImpl obj) {
        this.skippedObj = obj;
    }

    @objid ("f2d83e0f-0b16-471f-9dda-4f0b50ca044f")
    @Override
    public SmObjectImpl getObject() {
        return this.skippedObj;
    }

    @objid ("2510613d-6d59-4666-aa36-01b3e2db2f01")
    @Override
    public void addToDep(SmObjectImpl obj) {
        // ignore
    }

    @objid ("fcbe9055-0f53-46a3-840c-b5ced9bb2f81")
    @Override
    public void beginDependency(String depName) throws DependencyNotFoundException {
        // ignore
    }

    @objid ("0b695b74-eada-4a7f-a1b1-9d28669f415d")
    @Override
    public void updateCurrentDependency() {
        // ignore
    }

    @objid ("7f7ef80b-0ffc-472b-83b6-2129ed4db99c")
    @Override
    public AbstractState getCurrentState() {
        return this.currentstate ;
    }

    @objid ("4f0dd6f7-c55a-4505-9134-111cf4189169")
    @Override
    public void setCurrentState(AbstractState currentstate) {
        this.currentstate = currentstate;
    }

    @objid ("dd7875af-17d1-4cb4-8ef3-2bce7f99aa97")
    @Override
    public void finishDependenciesLoading() {
        // ignore
    }

}
