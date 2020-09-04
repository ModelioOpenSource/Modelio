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
package org.modelio.metamodel.bpmn.rootElements;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.resources.BpmnResourceRole;

/**
 * BpmnFlowNode v0.0.9054
 * 
 * 
 * FlowNodes represent nodes interconnected using sequence flows or message flows.
 * 
 * Ownership
 * FlowNodes belong to a FlowElementContainer or a sub process
 */
@objid ("0079b594-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnFlowNode extends BpmnFlowElement {
    /**
     * The metaclass simple name.
     */
    @objid ("f8fb7522-922f-492e-9934-a8104dac6c24")
    public static final String MNAME = "BpmnFlowNode";

    /**
     * The metaclass qualified name.
     */
    @objid ("f3afb085-634f-4971-b5e0-e7812be6ecde")
    public static final String MQNAME = "Standard.BpmnFlowNode";

    /**
     * Getter for relation 'BpmnFlowNode->Outgoing'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9601277c-d16e-472d-b59c-7ee2e5b310c1")
    EList<BpmnSequenceFlow> getOutgoing();

    /**
     * Filtered Getter for relation 'BpmnFlowNode->Outgoing'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a1f01496-98f1-4512-aa9c-d6133a0a430a")
    <T extends BpmnSequenceFlow> List<T> getOutgoing(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnFlowNode->Resource'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0d7cef71-154a-4431-9a8a-960562a2a1cd")
    EList<BpmnResourceRole> getResource();

    /**
     * Filtered Getter for relation 'BpmnFlowNode->Resource'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("08427f0a-54d9-4d3e-a321-24dd9346fad5")
    <T extends BpmnResourceRole> List<T> getResource(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnFlowNode->Incoming'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d3d8361c-48bc-437b-9e1e-753b73e3beeb")
    EList<BpmnSequenceFlow> getIncoming();

    /**
     * Filtered Getter for relation 'BpmnFlowNode->Incoming'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a98b38b7-0716-40c1-86a8-3bdb28bccfaa")
    <T extends BpmnSequenceFlow> List<T> getIncoming(java.lang.Class<T> filterClass);

}
