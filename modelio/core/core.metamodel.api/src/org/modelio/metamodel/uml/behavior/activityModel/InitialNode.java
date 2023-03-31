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
 * InitialNode v0.0.9054
 * 
 * 
 * An initial node is a starting point for executing an activity (or structured node, see StructuredActivityNode (from CompleteStructuredActivities, StructuredActivities) ). A control token is placed at the initial node when the activity starts, but not in initial nodes in structured nodes contained by the activity. Tokens in an initial node are offered to all outgoing edges. If an activity has more than one initial node, then invoking the activity starts multiple flows, one at each initial node. 
 * 
 * For convenience, initial nodes are an exception to the rule that control nodes cannot hold tokens if they are blocked from moving downstream, for example, by guards (see Activity). This is equivalent to interposing a CentralBufferNode between the initial node and its outgoing edges.
 * 
 * Note that flows can also start at other nodes, see ActivityParameterNode and AcceptEventAction, so initial nodes are not required for an activity to start execution. In addition, when an activity starts, a control token is placed at each action or structured node that has no incoming edges, except if it is a handler body (see "ExceptionHandler (from ExtraStructuredActivities), it is the fromAction of an action input pin (see "ActionInputPin (as specialized)" ), or it is contained in a structured node.
 * 
 * 
 */
@objid ("00345b3e-c4bf-1fd8-97fe-001ec947cd2a")
public interface InitialNode extends ControlNode {
    /**
     * The metaclass simple name.
     */
    @objid ("a9b19a44-217c-44a8-b695-b9a67a6f6417")
    public static final String MNAME = "InitialNode";

    /**
     * The metaclass qualified name.
     */
    @objid ("3ac806ea-46cf-47d8-a222-44c43db0ecd6")
    public static final String MQNAME = "Standard.InitialNode";
}

