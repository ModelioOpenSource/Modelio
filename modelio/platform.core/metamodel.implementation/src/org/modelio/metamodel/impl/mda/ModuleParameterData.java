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
package org.modelio.metamodel.impl.mda;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.metamodel.mda.ModuleParameterType;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00653042-c4bf-1fd8-97fe-001ec947cd2a")
public class ModuleParameterData extends ModelElementData {
    @objid ("57e0e5c2-4e55-4186-b1fb-d931a54a7dff")
     Object mGroupName = "";

    @objid ("323e02f6-620e-4cab-8513-3b31e1f22226")
     Object mType = ModuleParameterType.TYPE_PARAM_STRING;

    @objid ("3d033e80-ad75-4122-9c16-f107d7589960")
     Object mIsUserRead = true;

    @objid ("9eb70c50-51b0-474c-8042-eda8277d6344")
     Object mIsUserWrite = true;

    @objid ("3d8ecf9d-0748-4f46-b9de-757349ef4b03")
     Object mIsApiRead = true;

    @objid ("a967a297-5a1f-4bae-939f-d2dcb886fece")
     Object mIsApiWrite = true;

    @objid ("fae4b685-1785-4ab8-979d-44c8f9cd3986")
     Object mDefaultValue = "";

    @objid ("b89ebe68-d0cc-45b0-a937-893cd3d58f8a")
     SmObjectImpl mOwner;

    @objid ("94b6d6ca-3d5e-469e-930e-d3549abc2cfe")
     SmObjectImpl mEnumType;

    @objid ("c5663171-a99d-443a-a625-10265fc17b37")
    public ModuleParameterData(ModuleParameterSmClass smClass) {
        super(smClass);
    }

}
