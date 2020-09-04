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
package org.modelio.metamodel.bpmn.activities;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;

/**
 * BpmnReceiveTask v0.0.9054
 * 
 * 
 * A Receive Task is a simple Task that is designed to wait for a Message to arrive from an external Participant (relative to the Process). Once the Message has been received, the Task is completed.
 * The actual Participant from which the Message is received can be identified by connecting the Receive Task to a Participant using a Message Flow within the definitional Collaboration of the Process.
 * 
 * A Receive Task is often used to start a Process. In a sense, the Process is bootstrapped by the receipt of the Message. In order for the Task to Instantiate the Process it must meet one of the following conditions:
 * - The Process does not have a Start Event and the Receive Task has no incoming Sequence Flow.
 * - The incoming Sequence Flow for the Receive Task has a source of a Start Event.
 * - Note that no other incoming Sequence Flow are allowed for that Receive Task (in particular, a loop
 * connection from a downstream object).
 */
@objid ("008180d0-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnReceiveTask extends BpmnTask {
    /**
     * The metaclass simple name.
     */
    @objid ("ca4051e3-bdce-4460-b526-f5ffba642957")
    public static final String MNAME = "BpmnReceiveTask";

    /**
     * The metaclass qualified name.
     */
    @objid ("840333bc-8115-4450-b3cb-1e9256ad9555")
    public static final String MQNAME = "Standard.BpmnReceiveTask";

    /**
     * Getter for attribute 'BpmnReceiveTask.Implementation'
     * 
     * Metamodel description:
     * <i>This attribute specifies the technology that will be used to send and receive the Messages. 
     * 
     * Valid values are "##unspecified" for leaving the implementation technology open, "##WebService" for the Web service technology or a URI identifying any other technology or coordination protocol.
     * 
     * A Web service is the default technology.</i>
     */
    @objid ("478c36b7-cff6-4c15-98d5-a6e42459ffc1")
    String getImplementation();

    /**
     * Setter for attribute 'BpmnReceiveTask.Implementation'
     * 
     * Metamodel description:
     * <i>This attribute specifies the technology that will be used to send and receive the Messages. 
     * 
     * Valid values are "##unspecified" for leaving the implementation technology open, "##WebService" for the Web service technology or a URI identifying any other technology or coordination protocol.
     * 
     * A Web service is the default technology.</i>
     */
    @objid ("117762a9-ccf1-4242-b514-667a4c4fdf42")
    void setImplementation(String value);

    /**
     * Getter for attribute 'BpmnReceiveTask.Instanciate'
     * 
     * Metamodel description:
     * <i>Receive Tasks can be defined as the instantiation mechanism for the Process with the instantiate attribute. 
     * 
     * This attribute MAY be set to true if the Task is the first Activity (i.e., there are no incoming Sequence Flows). 
     * 
     * Multiple Tasks MAY have this attribute set to true.</i>
     */
    @objid ("6905296f-a282-4e84-b63d-4662fec4eec1")
    boolean isInstanciate();

    /**
     * Setter for attribute 'BpmnReceiveTask.Instanciate'
     * 
     * Metamodel description:
     * <i>Receive Tasks can be defined as the instantiation mechanism for the Process with the instantiate attribute. 
     * 
     * This attribute MAY be set to true if the Task is the first Activity (i.e., there are no incoming Sequence Flows). 
     * 
     * Multiple Tasks MAY have this attribute set to true.</i>
     */
    @objid ("45c6fd90-5ca8-4599-92b7-4ce6cda0af60")
    void setInstanciate(boolean value);

    /**
     * Getter for relation 'BpmnReceiveTask->MessageRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("62720dce-60b1-459b-b04f-5092e40c375b")
    BpmnMessage getMessageRef();

    /**
     * Setter for relation 'BpmnReceiveTask->MessageRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b226c6e2-89bd-4d38-ada2-6518d481c7a7")
    void setMessageRef(BpmnMessage value);

    /**
     * Getter for relation 'BpmnReceiveTask->OperationRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("987948aa-1d6a-4bcc-9f54-7c23370a0233")
    BpmnOperation getOperationRef();

    /**
     * Setter for relation 'BpmnReceiveTask->OperationRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0f259938-c36c-45b2-a54a-6e1694b7f052")
    void setOperationRef(BpmnOperation value);

}
