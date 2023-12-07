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

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("008fbe34-c4be-1fd8-97fe-001ec947cd2a")
public class TagTypeData extends ModelElementData {
    @objid ("3974827b-646e-4820-af3c-d8e57aac790e")
    Object mParamNumber = "1";

    @objid ("44577ecc-8903-415f-811d-f926d3a389ea")
    Object mIsQualified = false;

    @objid ("0794177d-e0a0-4c6b-8a0e-b3ba9d81ffa1")
    Object mBelongToPrototype = false;

    @objid ("24ee1aac-b1cc-4dd9-bfac-bf7e6770ab9c")
    Object mIsHidden = false;

    @objid ("6ebcc7a4-d271-4131-9d37-096072525412")
    Object mLabelKey = "";

    @objid ("766de5f6-546a-4819-92b0-d1a83e1647a2")
    List<SmObjectImpl> mTagOccurence = null;

    @objid ("7d7ee199-9f18-4fe0-b9be-3a34d39c6dc3")
    SmObjectImpl mOwnerStereotype;

    @objid ("86b61d7d-17de-4c10-84bb-1a9dfe64a73a")
    SmObjectImpl mOwnerReference;

    @objid ("8d6117ec-cf7d-4519-816d-2d648e5bbd45")
    public  TagTypeData(TagTypeSmClass smClass) {
        super(smClass);
    }

}
