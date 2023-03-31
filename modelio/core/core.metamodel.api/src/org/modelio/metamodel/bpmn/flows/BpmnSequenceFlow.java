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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.gateways.BpmnComplexGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnExclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnInclusiveGateway;
import org.modelio.metamodel.bpmn.objects.BpmnSequenceFlowDataAssociation;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;

/**
 * BpmnSequenceFlow v0.0.9054
 * 
 * 
 * A Sequence Flow is used to show the order of Flow Elements in a Process or a Choreography. Each Sequence Flow has only one source and only one target. The source and target must be from the set of the following Flow Elements: Events (Start, Intermediate, and End), Activities (Task and Sub-Process; for Processes), Choreography Activities (Choreography Task and  Sub-Choreography for Choreographies), and Gateways.
 * A Sequence Flow can optionally define a condition Expression, indicating that the token will be passed down the Sequence Flow only if the Expression evaluates to true. This Expression is typically used when the source of the Sequence Flow is a Gateway or an Activity.
 * A Sequence Flow that has an Exclusive, Inclusive, or Complex Gateway or an Activity as its source can also be defined with as default. Such Sequence Flow will have a marker to show that it is a default flow. The default Sequence Flow is taken (a token is passed) only if all the other outgoing Sequence Flow from the Activity or Gateway are not valid (i.e., their condition Expressions are false).
 * 
 * A sequence Flow is "default", if has a default association from a flowNode (Gateway or Activity).
 * A sequence flow is "conditional" is it originates from an activity, and has a conditionExpression.
 * 
 * Ownership
 * A Sequence flow belongs to a flow element container or a sub process.
 * 
 * 
 */
@objid ("007cd986-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnSequenceFlow extends BpmnFlowElement {
    /**
     * The metaclass simple name.
     */
    @objid ("027fb188-84ec-4be0-9518-36aebf102bb0")
    public static final String MNAME = "BpmnSequenceFlow";

    /**
     * The metaclass qualified name.
     */
    @objid ("db353ae1-9232-4da9-99da-088ba8cf14a7")
    public static final String MQNAME = "Standard.BpmnSequenceFlow";

    /**
     * Getter for attribute 'BpmnSequenceFlow.IsImmediate'
     * 
     * Metamodel description:
     * <i>An optional boolean value specifying whether Activities or Choreography
     * Activities not in the model containing the Sequence Flow can occur between the
     * elements connected by the Sequence Flow. If the value is true, they MAY NOT occur. If the value is false, they MAY occur. Also see the isClosed attribute on Process, Choreography, and Collaboration. When the attribute has no value, the default semantics depends on the kind of model containing Sequence Flow:
     * ? For a public Processes and Choreographies no value has the same semantics as if the value were false.
     * ? For an executable and non-executable (internal) Processes no value has the same semantics as if the value were true.
     * ? For executable Processes, the attribute MUST NOT be false.</i>
     * 
     */
    @objid ("ee5b2fa5-cac6-4b39-a468-99b88cd42efc")
    boolean isIsImmediate();

    /**
     * Setter for attribute 'BpmnSequenceFlow.IsImmediate'
     * 
     * Metamodel description:
     * <i>An optional boolean value specifying whether Activities or Choreography
     * Activities not in the model containing the Sequence Flow can occur between the
     * elements connected by the Sequence Flow. If the value is true, they MAY NOT occur. If the value is false, they MAY occur. Also see the isClosed attribute on Process, Choreography, and Collaboration. When the attribute has no value, the default semantics depends on the kind of model containing Sequence Flow:
     * ? For a public Processes and Choreographies no value has the same semantics as if the value were false.
     * ? For an executable and non-executable (internal) Processes no value has the same semantics as if the value were true.
     * ? For executable Processes, the attribute MUST NOT be false.</i>
     * 
     */
    @objid ("6b0c784a-cc1e-49bc-b125-5769d9594b78")
    void setIsImmediate(boolean value);

    /**
     * Getter for attribute 'BpmnSequenceFlow.ConditionExpression'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("2a4c132c-41dc-4fae-9e8b-c341577717ed")
    String getConditionExpression();

    /**
     * Setter for attribute 'BpmnSequenceFlow.ConditionExpression'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("c031518d-e583-4377-88bb-202daf2886e0")
    void setConditionExpression(String value);

    /**
     * Getter for relation 'BpmnSequenceFlow->SourceRef'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("59635031-9ed1-4c87-a95d-4e1d46a31b7c")
    BpmnFlowNode getSourceRef();

    /**
     * Setter for relation 'BpmnSequenceFlow->SourceRef'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("850e20ee-5276-4cf7-893e-d98affbcfaf5")
    void setSourceRef(BpmnFlowNode value);

    /**
     * Getter for relation 'BpmnSequenceFlow->TargetRef'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("a6733d87-0514-46fb-8a64-cb8a8a608786")
    BpmnFlowNode getTargetRef();

    /**
     * Setter for relation 'BpmnSequenceFlow->TargetRef'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("ab7d76d3-0950-4873-bd9e-6cec6cc31a64")
    void setTargetRef(BpmnFlowNode value);

    /**
     * Getter for relation 'BpmnSequenceFlow->DefaultOfInclusive'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e819a900-ff36-4764-ba77-ec852bd7feb8")
    BpmnInclusiveGateway getDefaultOfInclusive();

    /**
     * Setter for relation 'BpmnSequenceFlow->DefaultOfInclusive'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("a9246186-05f1-40e9-b35b-a1b8265435aa")
    void setDefaultOfInclusive(BpmnInclusiveGateway value);

    /**
     * Getter for relation 'BpmnSequenceFlow->DefaultFrom'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("c429188b-0557-4131-b85a-79768bed9c8b")
    BpmnActivity getDefaultFrom();

    /**
     * Setter for relation 'BpmnSequenceFlow->DefaultFrom'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("ed7dab99-3273-4e8d-8ea6-4f09377d9688")
    void setDefaultFrom(BpmnActivity value);

    /**
     * Getter for relation 'BpmnSequenceFlow->DefaultOfExclusive'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("0b9ef63b-2348-4a4c-bdc4-3be1cc5c9020")
    BpmnExclusiveGateway getDefaultOfExclusive();

    /**
     * Setter for relation 'BpmnSequenceFlow->DefaultOfExclusive'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("998a7cca-48a4-4a48-aa94-72b473f3f59b")
    void setDefaultOfExclusive(BpmnExclusiveGateway value);

    /**
     * Getter for relation 'BpmnSequenceFlow->Connector'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("1cf68921-140f-4e0b-9315-06ed803d29ca")
    EList<BpmnSequenceFlowDataAssociation> getConnector();

    /**
     * Filtered Getter for relation 'BpmnSequenceFlow->Connector'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("d0492f33-9f8b-4f6b-8ee9-120164bfb01e")
    <T extends BpmnSequenceFlowDataAssociation> List<T> getConnector(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnSequenceFlow->DefaultOfComplex'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("b3783c17-b4c7-44ff-a6e2-bd60dccf9e98")
    BpmnComplexGateway getDefaultOfComplex();

    /**
     * Setter for relation 'BpmnSequenceFlow->DefaultOfComplex'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("53bbd498-e0b8-4d6e-a436-e11341887120")
    void setDefaultOfComplex(BpmnComplexGateway value);
}

