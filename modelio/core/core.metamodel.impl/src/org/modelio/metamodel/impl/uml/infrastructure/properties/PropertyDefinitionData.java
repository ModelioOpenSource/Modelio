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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.impl.uml.infrastructure.properties;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00653092-ec87-1098-b22e-001ec947cd2a")
public class PropertyDefinitionData extends ModelElementData {
    @objid ("f8723cdf-26be-4fd6-bd6f-03100a932a40")
    Object mIsEditable = true;

    @objid ("720ff0d1-308a-4ee9-b88e-d3d08cd7c53a")
    Object mDefaultValue = "";

    @objid ("8064f26b-9ca0-4da8-b7a4-c3077ba5b0aa")
    SmObjectImpl mType;

    @objid ("55f6836d-c7da-4cc9-8774-6e8e2c117a72")
    SmObjectImpl mOwner;

    @objid ("73a0bdeb-7786-4455-b2f4-1110f76debee")
    public  PropertyDefinitionData(PropertyDefinitionSmClass smClass) {
        super(smClass);
    }

}
