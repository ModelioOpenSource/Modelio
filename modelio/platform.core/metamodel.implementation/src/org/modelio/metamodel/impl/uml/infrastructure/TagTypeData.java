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

@objid ("008fbe34-c4be-1fd8-97fe-001ec947cd2a")
public class TagTypeData extends ModelElementData {
    @objid ("be3bd94d-45fb-4839-843d-6f9b1c8f334a")
     Object mParamNumber = "1";

    @objid ("6cd9f375-fe79-43f6-b3a8-7c4456c30e6f")
     Object mIsQualified = false;

    @objid ("3627069c-2ea7-4ee2-a3e5-67d47bfaaf9a")
     Object mBelongToPrototype = false;

    @objid ("2001320e-3270-4b80-a649-e7b72c9cbe4e")
     Object mIsHidden = false;

    @objid ("8b848bc5-770f-4c16-a010-a5e3a30bc8ca")
     Object mLabelKey = "";

    @objid ("5f6e1463-f612-4867-b3d1-c7daff6b3089")
     List<SmObjectImpl> mTagOccurence = null;

    @objid ("35c0cf2b-b266-4acc-afc8-fd2b327f4d0c")
     SmObjectImpl mOwnerStereotype;

    @objid ("885523e5-ef60-491c-9302-f6e7bb033e32")
     SmObjectImpl mOwnerReference;

    @objid ("4c3691a5-f79d-4ee6-b197-bdb7eb84d4e8")
    public TagTypeData(TagTypeSmClass smClass) {
        super(smClass);
    }

}
