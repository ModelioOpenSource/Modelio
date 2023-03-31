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

package org.modelio.metamodel.bpmn.activities;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.events.BpmnCompensateEventDefinition;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;

/**
 * BpmnActivity v0.0.9054
 * 
 * 
 * An Activity is work that is performed within a Business Process. An Activity can be atomic or non-atomic (compound). The types of Activities that are a part of a Process are: Task, Sub-Process, and Call Activity, which allows the inclusion of re-usable Tasks and Processes in the diagram.
 * 
 * Ownership
 * An activity belongs to a GlowElementContainer or to a sub process.
 * 
 * 
 */
@objid ("007d712a-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnActivity extends BpmnFlowNode {
    /**
     * The metaclass simple name.
     */
    @objid ("e342e445-ee3b-4da6-a54f-aed0d5327407")
    public static final String MNAME = "BpmnActivity";

    /**
     * The metaclass qualified name.
     */
    @objid ("dfb06963-8214-46ee-89d2-471831127af5")
    public static final String MQNAME = "Standard.BpmnActivity";

    /**
     * Getter for attribute 'BpmnActivity.IsForCompensation'
     * 
     * Metamodel description:
     * <i>A flag that identifies whether this Activity is intended for the purposes of compensation.
     * If false, then this Activity executes as a result of normal execution flow.
     * If true, this Activity is only activated when a Compensation Event is detected and initiated under Compensation Event visibility scope</i>
     * 
     */
    @objid ("b27d6761-477e-4828-a145-6e29c02a4ede")
    boolean isIsForCompensation();

    /**
     * Setter for attribute 'BpmnActivity.IsForCompensation'
     * 
     * Metamodel description:
     * <i>A flag that identifies whether this Activity is intended for the purposes of compensation.
     * If false, then this Activity executes as a result of normal execution flow.
     * If true, this Activity is only activated when a Compensation Event is detected and initiated under Compensation Event visibility scope</i>
     * 
     */
    @objid ("8871bd13-1786-43a5-9491-1358cafef546")
    void setIsForCompensation(boolean value);

    /**
     * Getter for attribute 'BpmnActivity.StartQuantity'
     * 
     * Metamodel description:
     * <i>The default value is 1. The value MUST NOT be less than 1. This attribute defines the number of tokens that must arrive before the Activity can begin. Note that any value for the attribute that is greater than 1 is an advanced type of modeling and should be used with caution.</i>
     * 
     */
    @objid ("7e4b3a66-c3cd-4853-ba94-aaa2082ca652")
    int getStartQuantity();

    /**
     * Setter for attribute 'BpmnActivity.StartQuantity'
     * 
     * Metamodel description:
     * <i>The default value is 1. The value MUST NOT be less than 1. This attribute defines the number of tokens that must arrive before the Activity can begin. Note that any value for the attribute that is greater than 1 is an advanced type of modeling and should be used with caution.</i>
     * 
     */
    @objid ("5a0dfe7b-a5e8-457e-8785-a262f8d9b2fb")
    void setStartQuantity(int value);

    /**
     * Getter for attribute 'BpmnActivity.CompletionQuantity'
     * 
     * Metamodel description:
     * <i>The default value is 1. The value MUST NOT be less than 1. This attribute defines the number of tokens that must be generated from the Activity. This number of tokens will be sent done any outgoing Sequence Flow (assuming any Sequence Flow conditions are satisfied).
     * Note that any value for the attribute that is greater than 1 is an advanced type of modeling and should be used with caution.</i>
     * 
     */
    @objid ("b5298c3e-69ef-408b-a55c-48f0e6fd894a")
    int getCompletionQuantity();

    /**
     * Setter for attribute 'BpmnActivity.CompletionQuantity'
     * 
     * Metamodel description:
     * <i>The default value is 1. The value MUST NOT be less than 1. This attribute defines the number of tokens that must be generated from the Activity. This number of tokens will be sent done any outgoing Sequence Flow (assuming any Sequence Flow conditions are satisfied).
     * Note that any value for the attribute that is greater than 1 is an advanced type of modeling and should be used with caution.</i>
     * 
     */
    @objid ("1645486b-ce3c-48f2-aa0d-41e03166c45c")
    void setCompletionQuantity(int value);

    /**
     * Getter for relation 'BpmnActivity->CompensateEventDefinitions'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("61868c47-14ad-4341-9d8f-125c1ca9445b")
    EList<BpmnCompensateEventDefinition> getCompensateEventDefinitions();

    /**
     * Filtered Getter for relation 'BpmnActivity->CompensateEventDefinitions'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("3c94bcbd-a365-4354-862d-d458030414e0")
    <T extends BpmnCompensateEventDefinition> List<T> getCompensateEventDefinitions(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnActivity->InputSpecification'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e8949ace-e124-4e79-9bdc-6ada76006fdd")
    EList<BpmnDataInput> getInputSpecification();

    /**
     * Filtered Getter for relation 'BpmnActivity->InputSpecification'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("761d73a3-5562-4111-8050-39064ffe82a5")
    <T extends BpmnDataInput> List<T> getInputSpecification(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnActivity->DataInputAssociation'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("0eb93a21-80de-4e9f-833e-a01d9f2b7771")
    EList<BpmnDataAssociation> getDataInputAssociation();

    /**
     * Filtered Getter for relation 'BpmnActivity->DataInputAssociation'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("c9773382-2a2d-42ba-9ae1-0d1de6b5c008")
    <T extends BpmnDataAssociation> List<T> getDataInputAssociation(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnActivity->OutputSpecification'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("24ab2915-7a1d-41ba-8d25-9e55c086cdfe")
    EList<BpmnDataOutput> getOutputSpecification();

    /**
     * Filtered Getter for relation 'BpmnActivity->OutputSpecification'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("749a8191-cab3-4f12-a8eb-62e7ec777485")
    <T extends BpmnDataOutput> List<T> getOutputSpecification(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnActivity->LoopCharacteristics'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("0bbbfdec-27d1-4e5e-a509-a18e1a890aef")
    BpmnLoopCharacteristics getLoopCharacteristics();

    /**
     * Setter for relation 'BpmnActivity->LoopCharacteristics'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("8d759113-9a55-4910-acdc-61ca21680c24")
    void setLoopCharacteristics(BpmnLoopCharacteristics value);

    /**
     * Getter for relation 'BpmnActivity->BoundaryEventRef'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("fc5ac4f1-f769-4473-b8bb-3daf5d1cd6db")
    EList<BpmnBoundaryEvent> getBoundaryEventRef();

    /**
     * Filtered Getter for relation 'BpmnActivity->BoundaryEventRef'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("556a15ed-9dd4-4817-8b4f-b01e80b63a93")
    <T extends BpmnBoundaryEvent> List<T> getBoundaryEventRef(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnActivity->DataOutputAssociation'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("6df063a1-cad5-406e-b23a-8a93877df953")
    EList<BpmnDataAssociation> getDataOutputAssociation();

    /**
     * Filtered Getter for relation 'BpmnActivity->DataOutputAssociation'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("6a27d0aa-26fa-442d-adcd-34a6c8b112b8")
    <T extends BpmnDataAssociation> List<T> getDataOutputAssociation(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnActivity->DefaultFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("effafa39-8dc7-43af-9823-acc9e5305dc6")
    BpmnSequenceFlow getDefaultFlow();

    /**
     * Setter for relation 'BpmnActivity->DefaultFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("a60dd273-b089-424d-9d52-77bd8b74515b")
    void setDefaultFlow(BpmnSequenceFlow value);
}

