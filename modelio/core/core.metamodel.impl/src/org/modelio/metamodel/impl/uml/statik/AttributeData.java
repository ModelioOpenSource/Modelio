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

@objid ("009851b6-c4be-1fd8-97fe-001ec947cd2a")
public class AttributeData extends StructuralFeatureData {
    @objid ("25658bf5-54bb-427f-b64f-cd544d8467fe")
    Object mTypeConstraint = "";

    @objid ("7a9c5ce8-726d-497e-8965-a26168935ea0")
    Object mValue = "";

    @objid ("5d82cccc-61dc-4755-bbf7-ce77249eb35e")
    Object mTargetIsClass = false;

    @objid ("aa5f1cbd-593e-456b-8d21-446f852351be")
    SmObjectImpl mType;

    @objid ("22a2cf4b-5972-4427-87a6-a875698bb9d3")
    SmObjectImpl mOwner;

    @objid ("6db4df52-9c51-41a4-baa9-89b29e24653a")
    List<SmObjectImpl> mOccurence = null;

    @objid ("40a5f3d8-db4f-4e8d-b80d-7fdaf3a08bb1")
    List<SmObjectImpl> mRepresentingObjectNode = null;

    @objid ("924e4a40-e54c-44e5-b1fb-cc50273459b7")
    SmObjectImpl mQualified;

    @objid ("3c0922fe-7820-4390-bdbd-b3005cedd3c9")
    public  AttributeData(AttributeSmClass smClass) {
        super(smClass);
    }

}
