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
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;

/**
 * BpmnFlowElement v0.0.9054
 * 
 * 
 * FlowElement is the abstract super class for all elements that can appear in a Process flow, which are FlowNodes - which consist of Activities, Choreography Activities  Gateways, and Events - Data Objects, Data Associations, and Sequence Flow.
 * 
 * Ownership:
 * A FlowElement belongs to an Element Container or a SubProcess
 */
@objid ("00792098-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnFlowElement extends BpmnBaseElement {
    /**
     * The metaclass simple name.
     */
    @objid ("63b9c2e9-809f-4b8d-bfb2-0f1dbd2895e9")
    public static final String MNAME = "BpmnFlowElement";

    /**
     * The metaclass qualified name.
     */
    @objid ("f010f68b-2864-437e-b67c-5dbc1e03f473")
    public static final String MQNAME = "Standard.BpmnFlowElement";

    /**
     * Getter for attribute 'BpmnFlowElement.TriggeredByEvent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6047b007-0ce8-4ecc-b428-43885fb46df3")
    boolean isTriggeredByEvent();

    /**
     * Setter for attribute 'BpmnFlowElement.TriggeredByEvent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5b60cc6c-623e-4406-be63-4c3c1b8829bc")
    void setTriggeredByEvent(boolean value);

    /**
     * Getter for relation 'BpmnFlowElement->Groups'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b557ffe4-3c8e-48d0-9182-d09917cdd52c")
    EList<BpmnGroup> getGroups();

    /**
     * Filtered Getter for relation 'BpmnFlowElement->Groups'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c95a17ce-bfab-429f-9a8d-ffb2dcfb2e6e")
    <T extends BpmnGroup> List<T> getGroups(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnFlowElement->SubProcess'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4e951780-e70c-4453-a61f-a3890165cbbb")
    BpmnSubProcess getSubProcess();

    /**
     * Setter for relation 'BpmnFlowElement->SubProcess'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("32ddae39-e7a2-423a-82ab-9d4f943e97b9")
    void setSubProcess(BpmnSubProcess value);

    /**
     * Getter for relation 'BpmnFlowElement->Lane'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f965be7c-0ce7-4b76-a943-e8362af9d909")
    EList<BpmnLane> getLane();

    /**
     * Filtered Getter for relation 'BpmnFlowElement->Lane'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f1b518b0-1f0f-4221-8377-05c2ea5b3ff3")
    <T extends BpmnLane> List<T> getLane(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnFlowElement->Container'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("13f55880-bb48-4a36-93eb-f5304f85d1cb")
    BpmnProcess getContainer();

    /**
     * Setter for relation 'BpmnFlowElement->Container'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8a3e4a71-d697-49f2-b75b-8180cdf64d27")
    void setContainer(BpmnProcess value);

}
