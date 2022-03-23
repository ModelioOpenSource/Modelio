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
/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/

package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyBaseType;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("006f8eac-ec87-1098-b22e-001ec947cd2a")
public class PropertyTypeData extends ModelElementData {
    @objid ("6b5f2d01-674f-4228-87b5-b766f8ab23ea")
    Object mBaseType = PropertyBaseType.STRING;

    @objid ("a3a3f3ae-0873-4d23-86e6-5758ea4c7dc3")
    SmObjectImpl mModuleOwner;

    @objid ("f4e8c732-d3f3-4f29-9496-3f8ab983d36d")
    List<SmObjectImpl> mTyped = null;

    @objid ("43c0abbf-9e20-4bb8-b9fc-cdf5961d8862")
    SmObjectImpl mAnalystOwner;

    @objid ("d016b3e7-2ff9-454f-a5e0-da64dbdec8c2")
    public  PropertyTypeData(PropertyTypeSmClass smClass) {
        super(smClass);
    }

}
