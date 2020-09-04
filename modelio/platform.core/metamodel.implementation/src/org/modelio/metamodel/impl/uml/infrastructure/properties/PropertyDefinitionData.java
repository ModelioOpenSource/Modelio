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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00653092-ec87-1098-b22e-001ec947cd2a")
public class PropertyDefinitionData extends ModelElementData {
    @objid ("a0f18ada-dff4-43d8-904f-258b10443185")
     Object mIsEditable = true;

    @objid ("c4720bdd-853c-4287-b52b-b1fc679b2645")
     Object mDefaultValue = "";

    @objid ("5992bb29-5be4-49e4-a4d1-cac4faef7c71")
     SmObjectImpl mType;

    @objid ("212e490b-d685-4bd8-8eab-7c03a8ca4dc7")
     SmObjectImpl mOwner;

    @objid ("839c6726-d1ed-42a5-ba77-1baa75d56ed1")
    public PropertyDefinitionData(PropertyDefinitionSmClass smClass) {
        super(smClass);
    }

}
