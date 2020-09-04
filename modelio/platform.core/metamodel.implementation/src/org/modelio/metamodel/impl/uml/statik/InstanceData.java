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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("000dc94c-c4bf-1fd8-97fe-001ec947cd2a")
public class InstanceData extends UmlModelElementData {
    @objid ("b550da48-2173-4eae-8a3e-46649182c90b")
     Object mIsConstant = false;

    @objid ("cea66cbd-94c0-4ceb-a3e5-f3ea9a2dc6d8")
     Object mMultiplicityMin = "1";

    @objid ("0094e38d-30e3-499f-8f75-efdf139fd652")
     Object mMultiplicityMax = "1";

    @objid ("e1a762d3-c38e-46a8-b65f-66d7287a9bcd")
     Object mValue = "";

    @objid ("3663d28f-6784-4722-92e2-a4b8ddee11d7")
     List<SmObjectImpl> mRepresentedCommunicationNode = null;

    @objid ("96e60484-0860-4ea2-aa14-987c04556d47")
     List<SmObjectImpl> mOwnedEnd = null;

    @objid ("b53dfabb-a6f4-4b53-b0f5-75f442bf6f7b")
     SmObjectImpl mBase;

    @objid ("5fc80fcc-f68e-4aa9-b37a-fc1c2a85b322")
     List<SmObjectImpl> mRepresentingObjectNode = null;

    @objid ("c2510e05-3370-40d3-8a94-81c7d4b4474c")
     SmObjectImpl mOwner;

    @objid ("affedb82-b0de-4541-9678-0a515a32875a")
     List<SmObjectImpl> mOwnedNaryEnd = null;

    @objid ("bad02b21-bf4a-4ca6-b80e-28e42e6d94c3")
     List<SmObjectImpl> mRepresentedLifeLine = null;

    @objid ("80dfd0ce-70db-474c-9ce8-0b9c262c46c5")
     List<SmObjectImpl> mSlot = null;

    @objid ("42824c34-3c39-43f3-bc71-c86503714403")
     List<SmObjectImpl> mPart = null;

    @objid ("e4b9561a-ad08-45fd-84a0-e4ed62e6d03f")
     List<SmObjectImpl> mTargetingEnd = null;

    @objid ("f1c91064-1e84-470c-9a6e-bf136c44dfb8")
    public InstanceData(InstanceSmClass smClass) {
        super(smClass);
    }

}
