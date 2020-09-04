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
package org.modelio.metamodel.bpmn.bpmnService;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnReceiveTask;
import org.modelio.metamodel.bpmn.activities.BpmnSendTask;
import org.modelio.metamodel.bpmn.activities.BpmnServiceTask;
import org.modelio.metamodel.bpmn.bpmnService.BpmnInterface;
import org.modelio.metamodel.bpmn.events.BpmnMessageEventDefinition;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;

/**
 * BpmnOperation v0.0.9054
 * 
 * 
 * <p>An Operation defines Messages that are consumed and, optionally, produced when the Operation is called.</p><p>It can&nbsp;also define zero or more errors that are returned when operation fails. The Operation inherits the attributes and model&nbsp;associations of BaseElement.</p>
 */
@objid ("000d4990-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnOperation extends BpmnBaseElement {
    /**
     * The metaclass simple name.
     */
    @objid ("e9f317c2-9d01-4daf-91d5-b17fb5dbee24")
    public static final String MNAME = "BpmnOperation";

    /**
     * The metaclass qualified name.
     */
    @objid ("ecf5dc29-b541-4ddf-82d3-5ee3a0bf2545")
    public static final String MQNAME = "Standard.BpmnOperation";

    /**
     * Getter for relation 'BpmnOperation->Sender'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("30a08d12-ed9f-4b36-8ae4-3a32a6e3c5d8")
    EList<BpmnSendTask> getSender();

    /**
     * Filtered Getter for relation 'BpmnOperation->Sender'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("bcc29a01-1cd0-47a9-8bbe-6d5619da043e")
    <T extends BpmnSendTask> List<T> getSender(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnOperation->InMessageRef'
     * 
     * Metamodel description:
     * <i>specifies the input Message of the Operation. An Operation has exactly one input Message. </i>
     */
    @objid ("f0260e66-5daf-4a25-882e-829527b1eff3")
    BpmnMessage getInMessageRef();

    /**
     * Setter for relation 'BpmnOperation->InMessageRef'
     * 
     * Metamodel description:
     * <i>specifies the input Message of the Operation. An Operation has exactly one input Message. </i>
     */
    @objid ("6a100d10-5ce3-42c9-b7e2-c4606ddf08e6")
    void setInMessageRef(BpmnMessage value);

    /**
     * Getter for relation 'BpmnOperation->Caller'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c6ae9dd2-a479-481c-b49a-f0cbd1f39d96")
    EList<BpmnServiceTask> getCaller();

    /**
     * Filtered Getter for relation 'BpmnOperation->Caller'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b89204cc-2ff2-4522-9312-86e92563768a")
    <T extends BpmnServiceTask> List<T> getCaller(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnOperation->OutMessageRef'
     * 
     * Metamodel description:
     * <i>specifies the output Message of the Operation. An Operation has at most one input Message.</i>
     */
    @objid ("9dcf0239-23d7-4ee6-a9a2-aea64c768b02")
    BpmnMessage getOutMessageRef();

    /**
     * Setter for relation 'BpmnOperation->OutMessageRef'
     * 
     * Metamodel description:
     * <i>specifies the output Message of the Operation. An Operation has at most one input Message.</i>
     */
    @objid ("7bb718b0-e37b-4172-9458-c52df6aafd61")
    void setOutMessageRef(BpmnMessage value);

    /**
     * Getter for relation 'BpmnOperation->EventDefinition'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2528915c-c542-412d-a1ac-8575500d3a1a")
    EList<BpmnMessageEventDefinition> getEventDefinition();

    /**
     * Filtered Getter for relation 'BpmnOperation->EventDefinition'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8fc0ba4e-4d21-42c8-a994-9b2a9ae21ef3")
    <T extends BpmnMessageEventDefinition> List<T> getEventDefinition(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnOperation->BpmnInterfaceRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("849285e8-693b-4fd4-aa2a-c1f22ff2c5fa")
    BpmnInterface getBpmnInterfaceRef();

    /**
     * Setter for relation 'BpmnOperation->BpmnInterfaceRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ee1d8d8d-f2e2-4d8d-98a7-e259a951ed93")
    void setBpmnInterfaceRef(BpmnInterface value);

    /**
     * Getter for relation 'BpmnOperation->Receiver'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9a27fd4f-e62e-4aa5-bd57-af37439c219e")
    EList<BpmnReceiveTask> getReceiver();

    /**
     * Filtered Getter for relation 'BpmnOperation->Receiver'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("46a93367-b9db-454a-9ac4-ac8c9909a98b")
    <T extends BpmnReceiveTask> List<T> getReceiver(java.lang.Class<T> filterClass);

}
