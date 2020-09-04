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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("008ce3b2-c4be-1fd8-97fe-001ec947cd2a")
public class ProfileData extends ModelElementData {
    @objid ("e271baf7-5570-4a74-b745-04da419ff700")
     List<SmObjectImpl> mDefinedStereotype = null;

    @objid ("68f4373e-591a-4a49-b498-d5a5c3a0f21c")
     List<SmObjectImpl> mOwnedReference = null;

    @objid ("7705dc50-2991-4e3b-b265-155daacbc8ee")
     SmObjectImpl mOwnerModule;

    @objid ("45a128bb-0322-4838-920f-864064efefff")
     List<SmObjectImpl> mDefinedType = null;

    @objid ("5a96e202-cb00-4b4b-8dd6-f2e0e1fb60b4")
    public ProfileData(ProfileSmClass smClass) {
        super(smClass);
    }

}
