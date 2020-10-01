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

@objid ("008fbe34-c4be-1fd8-97fe-001ec947cd2a")
public class TagTypeData extends ModelElementData {
    @objid ("d62529b8-6c95-4e45-89fd-b175d1eea44a")
     Object mParamNumber = "1";

    @objid ("9caba601-5ed6-4446-ab5d-44b551a5a4d9")
     Object mIsQualified = false;

    @objid ("91981517-c49d-4efa-bcb8-63a06063638e")
     Object mBelongToPrototype = false;

    @objid ("2dbd2258-3b5e-4e6d-b6f2-dc654e3ec20a")
     Object mIsHidden = false;

    @objid ("53f83b2b-0b5d-410f-b78b-183486214ee8")
     Object mLabelKey = "";

    @objid ("93017048-764e-4f6b-9881-bc8d1e26c635")
     List<SmObjectImpl> mTagOccurence = null;

    @objid ("aaaf4163-6d35-4ae9-9451-8fd26e8e2437")
     SmObjectImpl mOwnerStereotype;

    @objid ("0c5ac9d5-b8c7-4e9a-ae92-e3d15c7d9a0b")
     SmObjectImpl mOwnerReference;

    @objid ("d9382fa7-bae7-42be-88f7-6d72ea33e460")
    public TagTypeData(TagTypeSmClass smClass) {
        super(smClass);
    }

}
