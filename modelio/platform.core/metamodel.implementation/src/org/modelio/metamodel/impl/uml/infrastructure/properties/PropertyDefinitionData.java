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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure.properties;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00653092-ec87-1098-b22e-001ec947cd2a")
public class PropertyDefinitionData extends ModelElementData {
    @objid ("6f198caa-89bf-42b6-af06-4d5974eab566")
     Object mIsEditable = true;

    @objid ("dcd6559f-9115-4a10-97a3-e8887f442665")
     Object mDefaultValue = "";

    @objid ("19f97416-961a-4c90-a263-1f4283da3f22")
     SmObjectImpl mType;

    @objid ("6fe6914e-ee9f-4c21-ae04-91b8a108cabf")
     SmObjectImpl mOwner;

    @objid ("acfb144e-399a-4aef-a677-0edf612663b5")
    public PropertyDefinitionData(PropertyDefinitionSmClass smClass) {
        super(smClass);
    }

}
