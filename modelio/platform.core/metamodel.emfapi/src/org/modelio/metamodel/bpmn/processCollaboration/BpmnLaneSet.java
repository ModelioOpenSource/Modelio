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
package org.modelio.metamodel.bpmn.processCollaboration;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;

/**
 * BpmnLaneSet v0.0.9054
 * 
 * 
 * The LaneSet element defines the container for one or more Lanes. A Process can contain one or more LaneSets.
 * Each LaneSet and its Lanes can partition the Flow Elements in a different way.
 * 
 * Ownership:
 * A LaneSet belongs to a process, or to a Lane.
 */
@objid ("0074fd92-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnLaneSet extends BpmnBaseElement {
    /**
     * The metaclass simple name.
     */
    @objid ("1a549087-02ac-48f5-a624-b7955aee6a0e")
    public static final String MNAME = "BpmnLaneSet";

    /**
     * The metaclass qualified name.
     */
    @objid ("973364f0-e9eb-4ee8-bd50-b917bc2b0f31")
    public static final String MQNAME = "Standard.BpmnLaneSet";

    /**
     * Getter for relation 'BpmnLaneSet->Lane'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0ae0f3c5-6668-454a-acb2-ed9ffe7eecad")
    EList<BpmnLane> getLane();

    /**
     * Filtered Getter for relation 'BpmnLaneSet->Lane'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d8665b10-8bfa-4725-8481-9456d04ebda4")
    <T extends BpmnLane> List<T> getLane(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnLaneSet->Process'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d8933f1b-302a-4ec9-89a0-7a9887ad60eb")
    BpmnProcess getProcess();

    /**
     * Setter for relation 'BpmnLaneSet->Process'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b225475a-4454-41b0-a111-817102e0909c")
    void setProcess(BpmnProcess value);

    /**
     * Getter for relation 'BpmnLaneSet->ParentLane'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("594d97c1-143b-470b-8eda-5a4b0f164d24")
    BpmnLane getParentLane();

    /**
     * Setter for relation 'BpmnLaneSet->ParentLane'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("fdc33cee-7344-4a63-95c1-64e0e425ce8b")
    void setParentLane(BpmnLane value);

    /**
     * Getter for relation 'BpmnLaneSet->SubProcess'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("fcdd0c9f-26f0-48ab-9e94-ffe65b9938f7")
    BpmnSubProcess getSubProcess();

    /**
     * Setter for relation 'BpmnLaneSet->SubProcess'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3aac8003-3b0f-48e4-a65c-8b5d5e289420")
    void setSubProcess(BpmnSubProcess value);

}
