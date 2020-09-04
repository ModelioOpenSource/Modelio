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

package org.modelio.vcore.model.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Model object factory.
 * <p>
 * 
 * @author cmarin
 * @since 3.6
 */
@objid ("d4c922fb-c41b-4dad-abb9-ef9088f7738e")
public interface IModelFactory {
    @objid ("f1f8c24a-a1de-4f5e-a922-0b03f5c65de7")
    MObject createElement(String metaclassName);

    @objid ("64e55e93-e0b8-48ad-9c3a-a0b48e4de1cc")
    MObject createElement(String metaclassName, MObject owner, String dependencyName);

    @objid ("311ef380-c169-4443-b96d-731a9b988cd7")
    <T extends MObject> T createElement(java.lang.Class<T> metaclass);

    @objid ("52790db0-99d5-4606-8521-862ee952fe9a")
    MObject createElement(MClass metaclass);

    @objid ("f1e9176b-7a6b-4ad6-8e90-25bf570bb053")
    MObject createElement(MClass metaclass, MObject owner, MDependency dependency);

    @objid ("36058fb4-9231-43d8-9ead-405cacd844a7")
    <T extends MObject> T createElement(java.lang.Class<T> metaclass, MObject owner, String dependencyName);

}
