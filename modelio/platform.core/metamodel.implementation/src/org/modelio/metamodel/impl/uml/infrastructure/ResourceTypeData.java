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

@objid ("00937678-c4be-1fd8-97fe-001ec947cd2a")
public class ResourceTypeData extends ModelElementData {
    @objid ("4b38f237-f850-4e66-9b2d-6f2cff14e17a")
     Object mIsHidden = false;

    @objid ("3cd478b2-c658-4e25-8cf4-0279f5155bdf")
     Object mLabelKey = "";

    @objid ("b3f31649-cd48-4d0e-81d5-234623bcb50a")
     Object mIcon = "";

    @objid ("c43d09ae-ebf2-43e2-8814-7bac955d67e2")
     Object mImage = "";

    @objid ("86d03201-ab64-4f5f-a944-d7d3d7ce4552")
     SmObjectImpl mOwnerStereotype;

    @objid ("c48bd156-e06b-485a-a7a5-4666f7cf305e")
     SmObjectImpl mOwnerReference;

    @objid ("ed3d0a32-346b-461e-9d3c-cc7de9d2db09")
     List<SmObjectImpl> mTypedResource = null;

    @objid ("67e38600-db44-43f6-a620-f551c828fc38")
    public ResourceTypeData(ResourceTypeSmClass smClass) {
        super(smClass);
    }

}
