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
package org.modelio.metamodel.bpmn.processCollaboration;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;

/**
 * BpmnLane v0.0.9054
 * 
 * 
 * A Lane element defines one specific partition in a LaneSet. The Lane can define a partition element which specifies the value and element type, a tool can use to determine the list of Flow Elements to be partitioned into this Lane.
 * 
 * Ownership:
 * A Lane belongs to a LaneSet
 */
@objid ("00746c56-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnLane extends BpmnBaseElement {
    /**
     * The metaclass simple name.
     */
    @objid ("113e2802-25eb-4c2f-b7eb-ea05a16537be")
    public static final String MNAME = "BpmnLane";

    /**
     * The metaclass qualified name.
     */
    @objid ("74e492e3-d850-433b-83dd-b408ab0a0193")
    public static final String MQNAME = "Standard.BpmnLane";

    /**
     * Getter for relation 'BpmnLane->ChildLaneSet'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8620b169-360c-45f3-8e65-603d2505cb1b")
    BpmnLaneSet getChildLaneSet();

    /**
     * Setter for relation 'BpmnLane->ChildLaneSet'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b057df60-99fc-4edd-b545-7b26a6b3a3fe")
    void setChildLaneSet(BpmnLaneSet value);

    /**
     * Getter for relation 'BpmnLane->FlowElementRef'
     * 
     * Metamodel description:
     * <i><p>The list of FlowNodes partitioned into this Lane according to the&nbsp;partitionElement defined as part of the Lane element.</p>
     * </i>
     */
    @objid ("721e1077-2aa6-4591-94f8-6d35bc01e96e")
    EList<BpmnFlowElement> getFlowElementRef();

    /**
     * Filtered Getter for relation 'BpmnLane->FlowElementRef'
     * 
     * Metamodel description:
     * <i><p>The list of FlowNodes partitioned into this Lane according to the&nbsp;partitionElement defined as part of the Lane element.</p>
     * </i>
     */
    @objid ("1b03a39b-d266-40ed-a36f-f239fbbbe56f")
    <T extends BpmnFlowElement> List<T> getFlowElementRef(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnLane->LaneSet'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3219f4e6-fc6f-4ed5-bfc8-a5003c195a95")
    BpmnLaneSet getLaneSet();

    /**
     * Setter for relation 'BpmnLane->LaneSet'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d297e393-8620-47a8-b6bf-3cfb5ae48f01")
    void setLaneSet(BpmnLaneSet value);

    /**
     * Getter for relation 'BpmnLane->BpmnPartitionElementRef'
     * 
     * Metamodel description:
     * <i><p>A reference to a BaseElement that specifies the partition value and partition&nbsp;type. Using this partition element a BPMN compliant tool can determine the&nbsp;FlowElements that have to be partitioned in this Lane.</p>
     * </i>
     */
    @objid ("aafb336c-450d-4f04-a16e-2192aab76706")
    BpmnBaseElement getBpmnPartitionElementRef();

    /**
     * Setter for relation 'BpmnLane->BpmnPartitionElementRef'
     * 
     * Metamodel description:
     * <i><p>A reference to a BaseElement that specifies the partition value and partition&nbsp;type. Using this partition element a BPMN compliant tool can determine the&nbsp;FlowElements that have to be partitioned in this Lane.</p>
     * </i>
     */
    @objid ("8ebf1c58-bbca-4cab-a1f2-64a35877af89")
    void setBpmnPartitionElementRef(BpmnBaseElement value);

}
