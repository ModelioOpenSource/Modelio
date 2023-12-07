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

package org.modelio.metamodel.impl.mda;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.metamodel.mda.ModuleParameterType;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00653042-c4bf-1fd8-97fe-001ec947cd2a")
public class ModuleParameterData extends ModelElementData {
    @objid ("0f394fb7-10aa-4335-9c46-55d02921fbb7")
    Object mGroupName = "";

    @objid ("9d740fb1-4936-404e-8570-f58f9f8b8ae3")
    Object mType = ModuleParameterType.TYPE_PARAM_STRING;

    @objid ("08c15dd2-570d-4e69-8ebe-677c2e2d5c8c")
    Object mIsUserRead = true;

    @objid ("24635533-ddb2-441c-b4b6-abc1ed5f6fb2")
    Object mIsUserWrite = true;

    @objid ("2032d17c-8d9a-46df-8e67-ea482cd3dfdc")
    Object mIsApiRead = true;

    @objid ("6f5f4572-36f4-4f0c-9cbe-a0ac87e29700")
    Object mIsApiWrite = true;

    @objid ("00b311f7-49cf-40ce-af03-23a6ae9dbbe1")
    Object mDefaultValue = "";

    @objid ("6c9befbf-7f4d-4ee6-af77-cd557482115f")
    SmObjectImpl mOwner;

    @objid ("07b628b4-d1e0-4f26-a676-eda890961e89")
    SmObjectImpl mEnumType;

    @objid ("38011958-1108-487d-bc4c-6e5225ee380e")
    public  ModuleParameterData(ModuleParameterSmClass smClass) {
        super(smClass);
    }

}
