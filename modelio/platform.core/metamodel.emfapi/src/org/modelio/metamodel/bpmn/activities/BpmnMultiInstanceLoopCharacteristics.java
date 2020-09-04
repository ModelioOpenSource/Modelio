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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnComplexBehaviorDefinition;
import org.modelio.metamodel.bpmn.activities.MultiInstanceBehavior;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;

/**
 * BpmnMultiInstanceLoopCharacteristics v0.0.9054
 * 
 * 
 * null
 */
@objid ("0080f732-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnMultiInstanceLoopCharacteristics extends BpmnLoopCharacteristics {
    /**
     * The metaclass simple name.
     */
    @objid ("09dbe520-0ea9-482e-b542-f12c3f8027aa")
    public static final String MNAME = "BpmnMultiInstanceLoopCharacteristics";

    /**
     * The metaclass qualified name.
     */
    @objid ("8c95ee44-b469-47f4-a20a-5ec2a438b258")
    public static final String MQNAME = "Standard.BpmnMultiInstanceLoopCharacteristics";

    /**
     * Getter for attribute 'BpmnMultiInstanceLoopCharacteristics.IsSequencial'
     * 
     * Metamodel description:
     * <i>This attribute is a flag that controls whether the Activity instances will execute sequentially or in parallel.
     * 
     * If the multi-instance instances are set to be performed in parallel rather than sequential (the isSequential attribute set to false), then the lines of the marker will vertical.
     * 
     * If the multi-instance instances are set to be performed in sequence rather than parallel (the isSequential attribute set to true), then the marker will be horizontal</i>
     */
    @objid ("6512aa65-0ef7-4e3b-a772-5eff6b32f448")
    boolean isIsSequencial();

    /**
     * Setter for attribute 'BpmnMultiInstanceLoopCharacteristics.IsSequencial'
     * 
     * Metamodel description:
     * <i>This attribute is a flag that controls whether the Activity instances will execute sequentially or in parallel.
     * 
     * If the multi-instance instances are set to be performed in parallel rather than sequential (the isSequential attribute set to false), then the lines of the marker will vertical.
     * 
     * If the multi-instance instances are set to be performed in sequence rather than parallel (the isSequential attribute set to true), then the marker will be horizontal</i>
     */
    @objid ("8a125115-7443-49d4-a6c2-9d0c177b9012")
    void setIsSequencial(boolean value);

    /**
     * Getter for attribute 'BpmnMultiInstanceLoopCharacteristics.Behavior'
     * 
     * Metamodel description:
     * <i>The attribute behavior acts as a shortcut for specifying when events SHALL be thrown from an Activity instance that is about to complete. It can assume values of None, One, All, and Complex, resulting in the following behavior:
     * ? None: the EventDefinition which is associated through the noneEvent association will be thrown for each instance completing;
     * ? One: the EventDefinition referenced through the oneEvent association will be thrown upon the first instance completing;
     * ? All: no Event is ever thrown; a token is produced after completion of all instances
     * ? Complex: the complexBehaviorDefinitions are consulted to determine if and which Events to throw.
     * 
     * For the behaviors of none and one, a default SignalEventDefinition will be thrown which automatically carries the current runtime attributes of the MI Activity.
     * 
     * Any thrown Events can be caught by boundary Events on the MultiInstance Activity.</i>
     */
    @objid ("cf341d15-c773-4ee8-b76d-4572b0ebfe34")
    MultiInstanceBehavior getBehavior();

    /**
     * Setter for attribute 'BpmnMultiInstanceLoopCharacteristics.Behavior'
     * 
     * Metamodel description:
     * <i>The attribute behavior acts as a shortcut for specifying when events SHALL be thrown from an Activity instance that is about to complete. It can assume values of None, One, All, and Complex, resulting in the following behavior:
     * ? None: the EventDefinition which is associated through the noneEvent association will be thrown for each instance completing;
     * ? One: the EventDefinition referenced through the oneEvent association will be thrown upon the first instance completing;
     * ? All: no Event is ever thrown; a token is produced after completion of all instances
     * ? Complex: the complexBehaviorDefinitions are consulted to determine if and which Events to throw.
     * 
     * For the behaviors of none and one, a default SignalEventDefinition will be thrown which automatically carries the current runtime attributes of the MI Activity.
     * 
     * Any thrown Events can be caught by boundary Events on the MultiInstance Activity.</i>
     */
    @objid ("292ae5b8-0f3a-43a3-a4bf-1f53a024e9e2")
    void setBehavior(MultiInstanceBehavior value);

    /**
     * Getter for attribute 'BpmnMultiInstanceLoopCharacteristics.LoopCardinality'
     * 
     * Metamodel description:
     * <i>A numeric Expression that controls the number of Activity instances that will be created. This Expression MUST evaluate to an integer.
     * This MAY be underspecified, meaning that the modeler MAY simply document the condition. In such a case the loop cannot be formally executed.
     * 
     * In order to initialize a valid multi-instance, either the loopCardinality Expression or the loopDataInput MUST be specified.</i>
     */
    @objid ("6935734e-8031-4eb1-85ba-7acab30039ee")
    String getLoopCardinality();

    /**
     * Setter for attribute 'BpmnMultiInstanceLoopCharacteristics.LoopCardinality'
     * 
     * Metamodel description:
     * <i>A numeric Expression that controls the number of Activity instances that will be created. This Expression MUST evaluate to an integer.
     * This MAY be underspecified, meaning that the modeler MAY simply document the condition. In such a case the loop cannot be formally executed.
     * 
     * In order to initialize a valid multi-instance, either the loopCardinality Expression or the loopDataInput MUST be specified.</i>
     */
    @objid ("1677071b-eda3-464a-a440-629bc30762b5")
    void setLoopCardinality(String value);

    /**
     * Getter for attribute 'BpmnMultiInstanceLoopCharacteristics.CompletionCondition'
     * 
     * Metamodel description:
     * <i>This attribute defines a boolean Expression that when evaluated to true, cancels the remaining Activity instances and produces a token.</i>
     */
    @objid ("6c862373-e948-4b4c-b1ea-151d4acca2f4")
    String getCompletionCondition();

    /**
     * Setter for attribute 'BpmnMultiInstanceLoopCharacteristics.CompletionCondition'
     * 
     * Metamodel description:
     * <i>This attribute defines a boolean Expression that when evaluated to true, cancels the remaining Activity instances and produces a token.</i>
     */
    @objid ("7af92715-9765-4384-a484-66b3dc17e5ba")
    void setCompletionCondition(String value);

    /**
     * Getter for relation 'BpmnMultiInstanceLoopCharacteristics->LoopDataInput'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2d7a6880-173a-4bc5-9f0a-82f235971564")
    BpmnDataInput getLoopDataInput();

    /**
     * Setter for relation 'BpmnMultiInstanceLoopCharacteristics->LoopDataInput'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3f86ed46-5407-4f0a-9367-77c83079766b")
    void setLoopDataInput(BpmnDataInput value);

    /**
     * Getter for relation 'BpmnMultiInstanceLoopCharacteristics->LoopDataOutputRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b368488d-5a4e-4584-a02b-f544d72b73a7")
    BpmnDataOutput getLoopDataOutputRef();

    /**
     * Setter for relation 'BpmnMultiInstanceLoopCharacteristics->LoopDataOutputRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("34d9b277-f3b5-411a-b611-5b49125c90b4")
    void setLoopDataOutputRef(BpmnDataOutput value);

    /**
     * Getter for relation 'BpmnMultiInstanceLoopCharacteristics->CompletionEventRef'
     * 
     * Metamodel description:
     * <i>The EventDefinition which is thrown when:
     * -  behavior is set to one and the first internal Activity instance has completed,
     * - or the behavior is set to none and an internal Activity instance has completed.</i>
     */
    @objid ("c210eb61-1a65-4a51-a779-58924fbc9fc3")
    BpmnEventDefinition getCompletionEventRef();

    /**
     * Setter for relation 'BpmnMultiInstanceLoopCharacteristics->CompletionEventRef'
     * 
     * Metamodel description:
     * <i>The EventDefinition which is thrown when:
     * -  behavior is set to one and the first internal Activity instance has completed,
     * - or the behavior is set to none and an internal Activity instance has completed.</i>
     */
    @objid ("f3ed9826-aa42-4241-b64d-ac52146c0559")
    void setCompletionEventRef(BpmnEventDefinition value);

    /**
     * Getter for relation 'BpmnMultiInstanceLoopCharacteristics->ComplexBehaviorDefinition'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("124fa85e-a3ed-4fe0-87a9-46503a2fa144")
    EList<BpmnComplexBehaviorDefinition> getComplexBehaviorDefinition();

    /**
     * Filtered Getter for relation 'BpmnMultiInstanceLoopCharacteristics->ComplexBehaviorDefinition'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2289a103-de38-4dda-a168-f5e043ced302")
    <T extends BpmnComplexBehaviorDefinition> List<T> getComplexBehaviorDefinition(java.lang.Class<T> filterClass);

}
