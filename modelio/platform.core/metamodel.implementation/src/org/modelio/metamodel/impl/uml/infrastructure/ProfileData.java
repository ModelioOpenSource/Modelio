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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("008ce3b2-c4be-1fd8-97fe-001ec947cd2a")
public class ProfileData extends ModelElementData {
    @objid ("164c48f3-5096-42a9-8bc4-83d2911911fc")
     List<SmObjectImpl> mDefinedStereotype = null;

    @objid ("77e67bdf-81da-49f2-9fb0-7c945f41e798")
     List<SmObjectImpl> mOwnedReference = null;

    @objid ("78fb1a16-ed2c-4d67-a805-c8576b5c41a6")
     SmObjectImpl mOwnerModule;

    @objid ("6ea56045-35c0-493a-a8f6-49ac90b031ae")
     List<SmObjectImpl> mDefinedType = null;

    @objid ("523818f9-c3ab-4289-b10e-4c721bd469de")
    public ProfileData(ProfileSmClass smClass) {
        super(smClass);
    }

}
