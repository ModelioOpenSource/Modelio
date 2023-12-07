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

@objid ("00937678-c4be-1fd8-97fe-001ec947cd2a")
public class ResourceTypeData extends ModelElementData {
    @objid ("f6fde94c-6753-41e4-833e-18eb7ebaf475")
    Object mIsHidden = false;

    @objid ("b0bd19fa-a78a-4dc3-91b4-0212fb857da2")
    Object mLabelKey = "";

    @objid ("6a4ad3a5-71f1-4f45-902a-77145954a40f")
    Object mIcon = "";

    @objid ("ddea38cb-4f92-4079-b0c0-e96e9cffbea8")
    Object mImage = "";

    @objid ("b7521f3f-548b-4b3c-9c29-9f6ec4ace34d")
    SmObjectImpl mOwnerStereotype;

    @objid ("79acfc30-6508-4024-a366-852e9029b73b")
    SmObjectImpl mOwnerReference;

    @objid ("6be53c7c-bbd7-4be3-9194-e1872699ac60")
    List<SmObjectImpl> mTypedResource = null;

    @objid ("e7dc0c57-716b-479a-a645-49676aa89a29")
    public  ResourceTypeData(ResourceTypeSmClass smClass) {
        super(smClass);
    }

}
