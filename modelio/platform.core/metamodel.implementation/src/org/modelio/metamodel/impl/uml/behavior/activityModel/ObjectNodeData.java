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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNodeOrderingKind;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0039b58e-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class ObjectNodeData extends ActivityNodeData {
    @objid ("2659d734-272b-45a5-859c-36c6ff5166f1")
     Object mIsControlType = false;

    @objid ("0950f022-15ad-49b5-ba35-6d71c75999cd")
     Object mOrdering = ObjectNodeOrderingKind.FIFO;

    @objid ("4a9638e6-9ef8-4dc6-b370-6cc7af8e0344")
     Object mSelectionBehavior = "";

    @objid ("1cdc4e27-7709-412f-af50-903e0b4a1eb9")
     Object mUpperBound = "1";

    @objid ("1a9468c7-f02a-4da9-9033-891dac5d5284")
     SmObjectImpl mRepresented;

    @objid ("c4627373-9b3c-4c0e-b8ad-7de2677ec69d")
     SmObjectImpl mRepresentedRealParameter;

    @objid ("7571a578-2507-427f-9fc0-a5beea432085")
     SmObjectImpl mType;

    @objid ("9c51db95-bf5f-4387-8e3f-cd802bcdce55")
     SmObjectImpl mRepresentedRole;

    @objid ("040c69a7-03af-4e1e-a006-9125ea0ccf59")
     SmObjectImpl mRepresentedAttribute;

    @objid ("4aec6e4a-fb31-4ed0-9410-6e1856f325ee")
     SmObjectImpl mInState;

    @objid ("758bef01-55eb-40df-999a-32d15a10df7e")
    public ObjectNodeData(ObjectNodeSmClass smClass) {
        super(smClass);
    }

}
