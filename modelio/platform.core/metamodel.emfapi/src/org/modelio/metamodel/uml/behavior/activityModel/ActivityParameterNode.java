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
package org.modelio.metamodel.uml.behavior.activityModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * ActivityParameterNode v0.0.9054
 * 
 * 
 * Activity parameter nodes are object nodes at the beginning and end of flows that provide a means to accept inputs to an activity and provide outputs from the activity, through the activity parameters.
 * 
 * As a kind of behavior, an activity may have owned parameters. Within the activity, in and inout parameters may be associated with activity parameter nodes that have no incoming edges-they provide a source within the activity for the overall "input values" of the activity. Similarly, inout, out, and return parameters may be associated with activity nodes that have no outgoing edges-they provide a sink within the activity for the overall "output values" of the activity.
 * 
 * Per the general semantics of a behavior, when the activity is invoked, its in and inout parameters may be given actual values. These input values are placed as tokens on those activity parameter nodes within the activity that are associated with the corresponding in and inout parameters, the ones which do not have incoming edges. The overall activity input values are then available within the activity via the outgoing edges of the activity parameter nodes.
 */
@objid ("00293b96-c4bf-1fd8-97fe-001ec947cd2a")
public interface ActivityParameterNode extends ObjectNode {
    /**
     * The metaclass simple name.
     */
    @objid ("e6ef64d4-f2f1-42de-9028-a8006352cc46")
    public static final String MNAME = "ActivityParameterNode";

    /**
     * The metaclass qualified name.
     */
    @objid ("c9d93327-af15-4418-9ae8-9b7118a2eb26")
    public static final String MQNAME = "Standard.ActivityParameterNode";

}
