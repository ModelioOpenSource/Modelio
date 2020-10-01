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
package org.modelio.metamodel.bpmn.flows;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;

/**
 * BpmnMessageFlow v0.0.9054
 * 
 * 
 * A Message Flow is used to show the flow of Messages between two Participants that are prepared to send and receive them.
 * 
 * ownership
 * a MessageFlow belongs to a collaboration
 */
@objid ("007c58ee-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnMessageFlow extends BpmnBaseElement {
    /**
     * The metaclass simple name.
     */
    @objid ("cf773673-161e-47d6-b375-a53a30fe3353")
    public static final String MNAME = "BpmnMessageFlow";

    /**
     * The metaclass qualified name.
     */
    @objid ("5971677d-b07d-4737-afc7-a83fd195e292")
    public static final String MQNAME = "Standard.BpmnMessageFlow";

    /**
     * Getter for relation 'BpmnMessageFlow->MessageRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("fc61b415-66f1-420e-9cee-fef089863da6")
    BpmnMessage getMessageRef();

    /**
     * Setter for relation 'BpmnMessageFlow->MessageRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("55dc3637-6eb8-49b8-b76c-4eebaa30e54e")
    void setMessageRef(BpmnMessage value);

    /**
     * Getter for relation 'BpmnMessageFlow->SourceRef'
     * 
     * Metamodel description:
     * <i>The BaseElement that the Association is connecting from.
     * 
     * The source can only be:
     * - a Participant,
     * - an Event,
     * - or a Task
     * </i>
     */
    @objid ("15f62a14-3910-430d-aeea-f7ae3d1a8e82")
    BpmnBaseElement getSourceRef();

    /**
     * Setter for relation 'BpmnMessageFlow->SourceRef'
     * 
     * Metamodel description:
     * <i>The BaseElement that the Association is connecting from.
     * 
     * The source can only be:
     * - a Participant,
     * - an Event,
     * - or a Task
     * </i>
     */
    @objid ("18146e19-fced-44a5-9322-b6a2ce0787d3")
    void setSourceRef(BpmnBaseElement value);

    /**
     * Getter for relation 'BpmnMessageFlow->TargetRef'
     * 
     * Metamodel description:
     * <i>The BaseElement that the Association is connecting to.
     * 
     * The target can only be:
     * - a Participant,
     * - an Event,
     * - or a Task.
     * </i>
     */
    @objid ("421e2bb6-0a8a-45c6-a8d4-07f9a96f669c")
    BpmnBaseElement getTargetRef();

    /**
     * Setter for relation 'BpmnMessageFlow->TargetRef'
     * 
     * Metamodel description:
     * <i>The BaseElement that the Association is connecting to.
     * 
     * The target can only be:
     * - a Participant,
     * - an Event,
     * - or a Task.
     * </i>
     */
    @objid ("38161bee-5508-404a-ae1b-6a8932fbfa3f")
    void setTargetRef(BpmnBaseElement value);

    /**
     * Getter for relation 'BpmnMessageFlow->Collaboration'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b8cd2e22-c5d4-4190-854b-2de62fea6587")
    BpmnCollaboration getCollaboration();

    /**
     * Setter for relation 'BpmnMessageFlow->Collaboration'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("481977a8-7cbd-4b57-910c-eae29db5a2cd")
    void setCollaboration(BpmnCollaboration value);

}
