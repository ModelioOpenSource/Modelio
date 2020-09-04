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
package org.modelio.metamodel.bpmn.flows;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnReceiveTask;
import org.modelio.metamodel.bpmn.activities.BpmnSendTask;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.events.BpmnMessageEventDefinition;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedElement;

/**
 * BpmnMessage v0.0.9054
 * 
 * 
 * <p>A Message represents the content of a communication between two Participants. In BPMN 2.0, a Message is a graphical object (it was a supporting element in BPMN 1.2). An ItemDefinition is used to specify the Message structure.</p><p>In a Process that is not used in a Collaboration, the communication is not displayed, but a Message can be defined for Activities that send and receive Messages (such as a Send Task). Note that the display of Messages in a Process, Collaboration, or Choreography is optional.</p><p>Ownership:<br />
 * A message belongs to a collaboration</p>
 */
@objid ("007be7e2-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnMessage extends BpmnSharedElement {
    /**
     * The metaclass simple name.
     */
    @objid ("b98e61f9-8c1e-41ac-b3f8-bbc95e152024")
    public static final String MNAME = "BpmnMessage";

    /**
     * The metaclass qualified name.
     */
    @objid ("f2030a56-eb19-4b47-809b-83a1fc7a8c7c")
    public static final String MQNAME = "Standard.BpmnMessage";

    /**
     * Getter for relation 'BpmnMessage->OutputMessage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a6422f6c-a66f-4548-b105-3b349917ddd3")
    EList<BpmnOperation> getOutputMessage();

    /**
     * Filtered Getter for relation 'BpmnMessage->OutputMessage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("18f3d041-73c3-432f-a840-6798b408c84d")
    <T extends BpmnOperation> List<T> getOutputMessage(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnMessage->ItemRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5e599c8a-a7a6-4793-8cc4-94267c1b2b5e")
    BpmnItemDefinition getItemRef();

    /**
     * Setter for relation 'BpmnMessage->ItemRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9054a8c0-e3e8-4785-9505-eaad0cd74ad3")
    void setItemRef(BpmnItemDefinition value);

    /**
     * Getter for relation 'BpmnMessage->EventDefinition'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1501d1d6-7b2e-49fe-b557-6a9dccd5e6e1")
    EList<BpmnMessageEventDefinition> getEventDefinition();

    /**
     * Filtered Getter for relation 'BpmnMessage->EventDefinition'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8ae3ce2a-acd9-4e16-9c3a-871392f871b4")
    <T extends BpmnMessageEventDefinition> List<T> getEventDefinition(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnMessage->Sender'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5c21a2a3-cd82-44f9-b924-4f25b1d0d743")
    EList<BpmnSendTask> getSender();

    /**
     * Filtered Getter for relation 'BpmnMessage->Sender'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5bc7efe4-a0df-4156-9169-2f1c94330515")
    <T extends BpmnSendTask> List<T> getSender(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnMessage->InputMessage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("95f65d39-1baa-4b3b-b109-60a290a8029e")
    EList<BpmnOperation> getInputMessage();

    /**
     * Filtered Getter for relation 'BpmnMessage->InputMessage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("bbf72a8a-05a2-452a-99f1-d0e386ff9323")
    <T extends BpmnOperation> List<T> getInputMessage(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnMessage->Receiver'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4161f4b7-13d4-4dac-bf92-6e2fb865d19e")
    EList<BpmnReceiveTask> getReceiver();

    /**
     * Filtered Getter for relation 'BpmnMessage->Receiver'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("32295d20-b434-48d5-81f3-fa223daa7289")
    <T extends BpmnReceiveTask> List<T> getReceiver(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnMessage->MessageFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7e6a8b05-e174-4bb7-b93a-a00743993e0f")
    EList<BpmnMessageFlow> getMessageFlow();

    /**
     * Filtered Getter for relation 'BpmnMessage->MessageFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5d8f4d4b-58ed-4c4f-9f75-6fbd83f0e96d")
    <T extends BpmnMessageFlow> List<T> getMessageFlow(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnMessage->Collaboration'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("bbcae7c0-21ef-444c-bf90-867ec277ad91")
    BpmnCollaboration getCollaboration();

    /**
     * Setter for relation 'BpmnMessage->Collaboration'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("68837ab5-726f-4d6e-b133-6d6030397a0d")
    void setCollaboration(BpmnCollaboration value);

}
