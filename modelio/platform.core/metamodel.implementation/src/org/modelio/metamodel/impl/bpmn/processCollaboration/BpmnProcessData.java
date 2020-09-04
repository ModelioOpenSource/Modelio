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
package org.modelio.metamodel.impl.bpmn.processCollaboration;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcessType;
import org.modelio.metamodel.bpmn.processCollaboration.OptionalBoolean;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00768e50-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnProcessData extends BehaviorData {
    @objid ("35d034b7-be3f-4fdc-84a0-e936d08b3342")
     Object mProcessType = BpmnProcessType.NONEPROCESS;

    @objid ("5d2d9f0f-7183-4c0e-9f20-07f3114c20a5")
     Object mIsClosed = false;

    @objid ("84e29822-6c93-432d-b20b-9e4018735cf7")
     Object mIsExecutable = OptionalBoolean.OUNDEFINED;

    @objid ("23eb5948-4b3e-4467-b0e4-6e9178cc534a")
     List<SmObjectImpl> mSupports = null;

    @objid ("cb6f62ff-e2e0-4f13-9ef4-d002f421e14b")
     List<SmObjectImpl> mArtifact = null;

    @objid ("b7da39ad-a717-4e02-b5d9-8a6b8e6bc475")
     SmObjectImpl mLaneSet;

    @objid ("343b121d-0da2-4eb3-9ab5-0bee5a9d7d1e")
     List<SmObjectImpl> mSupported = null;

    @objid ("a663c69d-5b5e-480e-b8ca-d4375c977b1b")
     List<SmObjectImpl> mParticipant = null;

    @objid ("07c632c3-28d1-4e56-8cce-66a30977b025")
     List<SmObjectImpl> mFlowElement = null;

    @objid ("f172eb12-0447-4cde-bc38-01859edfb2c5")
     List<SmObjectImpl> mResource = null;

    @objid ("3df6b801-6dfc-4067-b08a-9b80424b570c")
     SmObjectImpl mDefinitionalCollaboration;

    @objid ("9731f0ae-6096-4a1a-adf4-93cb588af7f4")
    public BpmnProcessData(BpmnProcessSmClass smClass) {
        super(smClass);
    }

}
