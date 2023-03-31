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
 * Data model that loads a read model object.
 */
@objid ("44af155d-1aa4-4c68-964d-7f412bca808d")
interface IObjectDataModel {
    @objid ("608f5024-a497-42ca-afe5-4bc3ba0fb6ff")
    SmObjectImpl getObject();

    @objid ("eba3f40c-efd8-4245-ae60-c354107c66d3")
    void addToDep(SmObjectImpl obj);

    @objid ("f50f219b-662a-4503-bb2a-7bfdc0f66684")
    void beginDependency(String depName) throws DependencyNotFoundException;

    @objid ("fb192de2-3d9c-452e-ba99-3996023558e9")
    void updateCurrentDependency();

    @objid ("5ed5eaeb-cef6-4fc5-acb7-43b2b13b9568")
    AbstractState getCurrentState();

    @objid ("13a2766a-cc1b-4aa9-85bb-b95964440074")
    void setCurrentState(AbstractState currentstate);

    /**
     * Finishes the dependencies loading.
     * <p>
     * Ensure dependencies for which no content was read are empty or have
     * the hooked content.
     */
    @objid ("c2a12bb8-5846-4141-9de5-fcf7b7e7082b")
    void finishDependenciesLoading();
}

