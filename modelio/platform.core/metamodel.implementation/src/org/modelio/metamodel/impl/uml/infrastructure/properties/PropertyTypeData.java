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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyBaseType;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("006f8eac-ec87-1098-b22e-001ec947cd2a")
public class PropertyTypeData extends ModelElementData {
    @objid ("5ad69a56-93ca-46cd-98d4-2c7682d9ed9a")
     Object mBaseType = PropertyBaseType.STRING;

    @objid ("451c1fe4-8b4e-4f8e-99bf-9cf0d3a5bdb4")
     SmObjectImpl mModuleOwner;

    @objid ("769870e1-c376-4377-9091-52d9c8902855")
     List<SmObjectImpl> mTyped = null;

    @objid ("adb13cdd-a2b0-4814-a43e-861783ac7bfa")
     SmObjectImpl mAnalystOwner;

    @objid ("505643b7-0f4d-4a2c-9eb3-40709c3e357e")
    public PropertyTypeData(PropertyTypeSmClass smClass) {
        super(smClass);
    }

}
