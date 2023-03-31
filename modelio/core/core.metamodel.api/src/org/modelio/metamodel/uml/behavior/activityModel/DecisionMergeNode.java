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

package org.modelio.metamodel.uml.behavior.activityModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * DecisionMergeNode v0.0.9054
 * 
 * 
 * A decision node is a control node that chooses between outgoing flows.
 * 
 * A merge node is a control node that brings together multiple alternate flows. It is not used to synchronize concurrent flows but to accept one among several alternate flows.
 * 
 * Modelio specific:
 * The functionality of merge node and decision node can be combined by using the same node. This case maps to a model containing a merge node with all the incoming edges and one outgoing edge to a decision node that has all the outgoing edges.
 * 
 * 
 * 
 * 
 */
@objid ("002fbdcc-c4bf-1fd8-97fe-001ec947cd2a")
public interface DecisionMergeNode extends ControlNode {
    /**
     * The metaclass simple name.
     */
    @objid ("d58c1443-3af6-4500-922a-d7972792dc2c")
    public static final String MNAME = "DecisionMergeNode";

    /**
     * The metaclass qualified name.
     */
    @objid ("75d252be-e22b-4b8e-bd94-a43113586f62")
    public static final String MQNAME = "Standard.DecisionMergeNode";

    /**
     * Getter for attribute 'DecisionMergeNode.DecisionKind'
     * 
     * Metamodel description:
     * <i>BPMN decision kind.</i>
     * 
     */
    @objid ("df893ddf-15af-47bd-b5bd-a892a4496531")
    DecisionNodeKind getDecisionKind();

    /**
     * Setter for attribute 'DecisionMergeNode.DecisionKind'
     * 
     * Metamodel description:
     * <i>BPMN decision kind.</i>
     * 
     */
    @objid ("7fd5e37b-ad6e-4671-a710-740e29eb2eea")
    void setDecisionKind(DecisionNodeKind value);

    /**
     * Getter for attribute 'DecisionMergeNode.DecisionInputBehavior'
     * 
     * Metamodel description:
     * <i>Provides input to guard specifications on edges outgoing from the decision node.</i>
     * 
     */
    @objid ("047f4395-cb2d-42e4-a05f-a5969e9a2628")
    String getDecisionInputBehavior();

    /**
     * Setter for attribute 'DecisionMergeNode.DecisionInputBehavior'
     * 
     * Metamodel description:
     * <i>Provides input to guard specifications on edges outgoing from the decision node.</i>
     * 
     */
    @objid ("21bdf8d6-431c-4b8b-8bdf-5469fcc89bf5")
    void setDecisionInputBehavior(String value);
}

