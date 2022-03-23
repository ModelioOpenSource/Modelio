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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/

package org.modelio.metamodel.impl.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("001da934-c4bf-1fd8-97fe-001ec947cd2a")
public class TemplateParameterData extends GeneralClassData {
    @objid ("d0afc624-1c5a-4962-84ac-4898c5d45fba")
    Object mDefaultValue = "";

    @objid ("aaf4dfc2-4349-4bef-a115-e9c9f9d336d6")
    Object mIsValueParameter = false;

    @objid ("f361f656-fd38-40c1-b84d-b139d66d4e63")
    List<SmObjectImpl> mParameterSubstitution = null;

    @objid ("8ccdcf54-a63b-40cd-af48-2d1d73fa15f6")
    SmObjectImpl mType;

    @objid ("5d2421ee-9bcf-4855-be71-a2ee8a2490c3")
    SmObjectImpl mParameterized;

    @objid ("80c5b6c0-0e29-4ed8-bda6-96e98dd94cf9")
    SmObjectImpl mOwnedParameterElement;

    @objid ("e3fae64d-a58d-40d5-8996-f55ac45a5dbc")
    SmObjectImpl mDefaultType;

    @objid ("054c3532-8ddf-4d68-995d-d7c61055b12f")
    SmObjectImpl mParameterizedOperation;

    @objid ("0f80eb85-8053-43c4-8695-11c91e1a9f02")
    public  TemplateParameterData(TemplateParameterSmClass smClass) {
        super(smClass);
    }

}
