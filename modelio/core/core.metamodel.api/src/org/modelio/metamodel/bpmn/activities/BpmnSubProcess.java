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
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.rootElements.BpmnArtifact;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;

/**
 * BpmnSubProcess v0.0.9054
 * 
 * 
 * A Sub-Process is an Activity whose internal details have been modeled using Activities, Gateways, Events, and Sequence Flow. A Sub-Process is a graphical object within a Process, but it also can be ?opened up? to show a lower-level Process. Sub-Processes define a contextual scope that can be used for attribute visibility, transactional scope, for the handling of exceptions, of Events, or for compensation. 
 * 
 * An Event Sub-Process may or may not occur while the parent Process is active, but it is possible that it will occur many times. Unlike a standard  Sub-Process, which uses the flow of the parent Process as a trigger, an Event Sub- Process has a Start Event with a trigger. Each time the Start Event is triggered while the parent Process is active, then the Event Sub-Process will start.
 */
@objid ("00843028-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnSubProcess extends BpmnActivity {
    /**
     * The metaclass simple name.
     */
    @objid ("aa7eb7b0-9de1-45d7-b7b0-97f8a606b0e8")
    public static final String MNAME = "BpmnSubProcess";

    /**
     * The metaclass qualified name.
     */
    @objid ("410b3f8b-07a4-49c6-bd8f-03fbd0d94db3")
    public static final String MQNAME = "Standard.BpmnSubProcess";

    /**
     * Getter for relation 'BpmnSubProcess->Artifact'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("719d62b7-d652-4f7c-98db-75a93239b897")
    EList<BpmnArtifact> getArtifact();

    /**
     * Filtered Getter for relation 'BpmnSubProcess->Artifact'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ad84b2d7-9dc6-4f35-a248-2b4b6a25c28e")
    <T extends BpmnArtifact> List<T> getArtifact(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnSubProcess->FlowElement'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("87ae5f36-1803-42cd-9b9a-136de891bf16")
    EList<BpmnFlowElement> getFlowElement();

    /**
     * Filtered Getter for relation 'BpmnSubProcess->FlowElement'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("72e15384-326e-46fa-9865-6e6fe9a3d3b1")
    <T extends BpmnFlowElement> List<T> getFlowElement(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnSubProcess->LaneSet'
     * 
     * Metamodel description:
     * <i>laneset of the process. The process is represented in this cas by a pool which is decomposed by lansets and lanes.</i>
     */
    @objid ("bc8378ea-6af6-46bd-a897-a256461ae9be")
    BpmnLaneSet getLaneSet();

    /**
     * Setter for relation 'BpmnSubProcess->LaneSet'
     * 
     * Metamodel description:
     * <i>laneset of the process. The process is represented in this cas by a pool which is decomposed by lansets and lanes.</i>
     */
    @objid ("aef8dd72-8c14-4228-b512-17136c1d01b8")
    void setLaneSet(BpmnLaneSet value);

}
