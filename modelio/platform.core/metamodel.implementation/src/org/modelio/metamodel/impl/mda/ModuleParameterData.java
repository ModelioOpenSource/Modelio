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
package org.modelio.metamodel.impl.mda;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.metamodel.mda.ModuleParameterType;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00653042-c4bf-1fd8-97fe-001ec947cd2a")
public class ModuleParameterData extends ModelElementData {
    @objid ("875a5352-981d-495f-b047-2a4904ada79b")
     Object mGroupName = "";

    @objid ("6dd2e620-659f-40bb-a412-ab98c85184e7")
     Object mType = ModuleParameterType.TYPE_PARAM_STRING;

    @objid ("426f5b32-711c-4178-9341-9c1b12b82599")
     Object mIsUserRead = true;

    @objid ("50cc351b-ccae-418e-9647-e5c5252853c4")
     Object mIsUserWrite = true;

    @objid ("a1724d18-1934-4fbf-8c60-8cda829b5c79")
     Object mIsApiRead = true;

    @objid ("7f8c5950-05ce-4c0f-9767-017f461b65f3")
     Object mIsApiWrite = true;

    @objid ("f7c23efa-4484-4eb9-bc15-7afc5dbac7d5")
     Object mDefaultValue = "";

    @objid ("fc423e38-f6c1-42c1-a437-4a8b3381246e")
     SmObjectImpl mOwner;

    @objid ("83b0a08d-0121-42da-b3f5-d5b7131822dd")
     SmObjectImpl mEnumType;

    @objid ("c7325aef-917a-4f90-9b7d-361c84d7dac9")
    public ModuleParameterData(ModuleParameterSmClass smClass) {
        super(smClass);
    }

}
