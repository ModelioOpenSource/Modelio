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
 * ForkJoinNode v0.0.9054
 * 
 * 
 * A fork node has one incoming edge and multiple outgoing edges. A fork node is a control node that splits a flow into multiple concurrent flows.
 * 
 * A join node has multiple incoming edges and one outgoing edge. Join nodes have a boolean specification using the names of the incoming edges to specify the conditions under which the join will emit a token.
 * 
 * Modelio extension:
 * The functionality of join node and fork node can be combined by using the same node. 
 * This case maps to a model containing a join node with all the incoming edges shown in the diagram and one outgoing edge to a fork node that has all the outgoing edges shown in the diagram.
 * 
 * 
 */
@objid ("0033b2ec-c4bf-1fd8-97fe-001ec947cd2a")
public interface ForkJoinNode extends ControlNode {
    /**
     * The metaclass simple name.
     */
    @objid ("47f15b37-43eb-47d6-ad93-932297d46044")
    public static final String MNAME = "ForkJoinNode";

    /**
     * The metaclass qualified name.
     */
    @objid ("6fc334c7-cbaa-4e8f-9a4c-dd6cd431f87b")
    public static final String MQNAME = "Standard.ForkJoinNode";

    /**
     * Getter for attribute 'ForkJoinNode.IsCombineDuplicate'
     * 
     * Metamodel description:
     * <i>Indicates whether tokens with objects with the same identity are combined into one by the join. The default value is true.</i>
     * 
     */
    @objid ("c031abec-4112-4222-8291-646820e2dc18")
    boolean isIsCombineDuplicate();

    /**
     * Setter for attribute 'ForkJoinNode.IsCombineDuplicate'
     * 
     * Metamodel description:
     * <i>Indicates whether tokens with objects with the same identity are combined into one by the join. The default value is true.</i>
     * 
     */
    @objid ("9e13f21a-33a6-46dc-9014-b1d0f0ed6144")
    void setIsCombineDuplicate(boolean value);

    /**
     * Getter for attribute 'ForkJoinNode.JoinSpec'
     * 
     * Metamodel description:
     * <i>A specification giving the conditions under which the join will emit a 
     * token. The default value is �??and.</i>
     * 
     */
    @objid ("62cb8bae-e6dc-4c99-9607-4a8f5e8ad677")
    String getJoinSpec();

    /**
     * Setter for attribute 'ForkJoinNode.JoinSpec'
     * 
     * Metamodel description:
     * <i>A specification giving the conditions under which the join will emit a 
     * token. The default value is �??and.</i>
     * 
     */
    @objid ("236ab438-e93b-4e3a-88c3-b324f3806a7a")
    void setJoinSpec(String value);
}

