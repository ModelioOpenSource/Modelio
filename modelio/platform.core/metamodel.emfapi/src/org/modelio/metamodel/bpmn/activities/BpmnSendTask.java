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
package org.modelio.metamodel.bpmn.activities;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;

/**
 * BpmnSendTask v0.0.9054
 * 
 * 
 * A Send Task is a simple Task that is designed to send a Message to an external Participant (relative to the Process). Once the Message has been sent, the Task is completed.
 * The actual Participant which the Message is sent can be identified by connecting the Send Task to a Participant using a Message Flow within the definitional Collaboration of the Process
 */
@objid ("00828728-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnSendTask extends BpmnTask {
    /**
     * The metaclass simple name.
     */
    @objid ("9e8b215a-89c8-4dff-9735-5ce7bdf49611")
    public static final String MNAME = "BpmnSendTask";

    /**
     * The metaclass qualified name.
     */
    @objid ("0b3522a6-6485-4660-be95-33e23bef10fa")
    public static final String MQNAME = "Standard.BpmnSendTask";

    /**
     * Getter for attribute 'BpmnSendTask.Implementation'
     * 
     * Metamodel description:
     * <i>This attribute specifies the technology that will be used to send and receive the Messages. 
     * 
     * Valid values are "##unspecified" for leaving the implementation technology open, "##WebService" for the Web service technology or a URI identifying any other technology or coordination protocol.
     * 
     * A Web service is the default technology.</i>
     */
    @objid ("c033c797-a62e-458d-aae3-bf5464ba8360")
    String getImplementation();

    /**
     * Setter for attribute 'BpmnSendTask.Implementation'
     * 
     * Metamodel description:
     * <i>This attribute specifies the technology that will be used to send and receive the Messages. 
     * 
     * Valid values are "##unspecified" for leaving the implementation technology open, "##WebService" for the Web service technology or a URI identifying any other technology or coordination protocol.
     * 
     * A Web service is the default technology.</i>
     */
    @objid ("5786dcbb-ea5f-4a0d-b2e4-47ed5fc39ae7")
    void setImplementation(String value);

    /**
     * Getter for relation 'BpmnSendTask->MessageRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f1a37e99-42ae-41e2-be2e-67b6b5937923")
    BpmnMessage getMessageRef();

    /**
     * Setter for relation 'BpmnSendTask->MessageRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("62c45768-ddc5-47c7-a722-5c53b517956e")
    void setMessageRef(BpmnMessage value);

    /**
     * Getter for relation 'BpmnSendTask->OperationRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a95781ab-a729-49aa-bb80-3ade3afc1fd5")
    BpmnOperation getOperationRef();

    /**
     * Setter for relation 'BpmnSendTask->OperationRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7d3ad8bc-cb75-4d05-a205-4dff36a6add2")
    void setOperationRef(BpmnOperation value);

}
