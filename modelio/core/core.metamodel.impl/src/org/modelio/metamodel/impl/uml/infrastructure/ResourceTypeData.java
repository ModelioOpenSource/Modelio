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

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00937678-c4be-1fd8-97fe-001ec947cd2a")
public class ResourceTypeData extends ModelElementData {
    @objid ("efb2d169-78ec-4062-b529-fc5c19119187")
    Object mIsHidden = false;

    @objid ("018e00f4-a3db-4e68-911b-03158a474e35")
    Object mLabelKey = "";

    @objid ("fc7812f6-0229-408a-8a9b-780b19137974")
    Object mIcon = "";

    @objid ("93b97fcb-2f0c-4bf9-ad1c-797da1e03caa")
    Object mImage = "";

    @objid ("1905f742-ab99-4d60-8444-077076546cf0")
    SmObjectImpl mOwnerStereotype;

    @objid ("4851eabf-1fdf-40b8-994e-0dbcf5f74b31")
    SmObjectImpl mOwnerReference;

    @objid ("74a61c04-53eb-438c-8e45-34957b7fd00a")
    List<SmObjectImpl> mTypedResource = null;

    @objid ("9c859f9c-13ee-4bd9-94bc-3539f38ca0db")
    public  ResourceTypeData(ResourceTypeSmClass smClass) {
        super(smClass);
    }

}
