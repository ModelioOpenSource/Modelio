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
package org.modelio.metamodel.impl.bpmn.processCollaboration;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00742fca-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnCollaborationData extends BehaviorData {
    @objid ("e5582c16-45ff-4ec6-aaac-dd71d2891131")
     Object mIsClosed = false;

    @objid ("35c87028-3d2c-43e2-8e49-857041f3bb57")
     List<SmObjectImpl> mArtifact = null;

    @objid ("8757fca7-e2b1-4c2a-8cb6-758f25e670d6")
     List<SmObjectImpl> mMessageFlow = null;

    @objid ("120edd68-a8cc-43cb-af85-81281f7555a5")
     List<SmObjectImpl> mParticipants = null;

    @objid ("43ccfdfb-bcbc-4ebb-9185-1ef62b3a155e")
     List<SmObjectImpl> mMessages = null;

    @objid ("f80351b2-4757-4935-a459-b550eda7236d")
     SmObjectImpl mDefinedProcess;

    @objid ("34f6d40e-c7de-4a3d-bb0d-350fc7ee8be5")
    public BpmnCollaborationData(BpmnCollaborationSmClass smClass) {
        super(smClass);
    }

}
