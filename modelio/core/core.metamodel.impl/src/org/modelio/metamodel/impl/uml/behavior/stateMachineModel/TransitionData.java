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
package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00561594-c4bf-1fd8-97fe-001ec947cd2a")
public class TransitionData extends UmlModelElementData {
    @objid ("cbfbd5bb-f675-4c50-aed3-1639e63a9476")
     Object mEffect = "";

    @objid ("7816af05-affe-4aaa-8f77-c7e67f6ea83d")
     Object mReceivedEvents = "";

    @objid ("e1e08047-4cfc-4c9c-92bb-3bcb57bcb19b")
     Object mSentEvents = "";

    @objid ("5eafd337-1ee0-49a1-823d-2f218f637c41")
     Object mGuard = "";

    @objid ("fb4e4055-2ce6-46c7-8584-507ecce7fac1")
     Object mPostCondition = "";

    @objid ("49603331-c692-4ce1-afe9-087a2a55f992")
     SmObjectImpl mProcessed;

    @objid ("f69bc6bf-d172-4b05-8ab3-6eda2efef961")
     SmObjectImpl mTrigger;

    @objid ("9689a697-26e9-4237-a08d-ab189d2d7bbc")
     SmObjectImpl mBehaviorEffect;

    @objid ("07bd8e22-ba1d-477d-9c75-514219a2fe8a")
     SmObjectImpl mTarget;

    @objid ("2549d379-0875-4d38-a045-1c710bc8ad77")
     SmObjectImpl mSource;

    @objid ("607a24a2-6769-4eaf-add6-f3efbcb3b0aa")
     SmObjectImpl mEffects;

    @objid ("a7bd4bd6-fa26-42fb-a5ba-b56ac3cfd137")
    public TransitionData(TransitionSmClass smClass) {
        super(smClass);
    }

}
