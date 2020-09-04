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
package org.modelio.metamodel.uml.behavior.activityModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * InstanceNode v0.0.9054
 * 
 * 
 * An instance node is an activity node that indicates an instance of a particular classifier, possibly in a particular state, potentially available at a particular point in the activity. Instance nodes can be used in a variety of ways, depending on where objects are flowing to and from.
 * 
 * Instances nodes are also used to represent variables in activities and structured nodes.
 */
@objid ("0035be3e-c4bf-1fd8-97fe-001ec947cd2a")
public interface InstanceNode extends ObjectNode {
    /**
     * The metaclass simple name.
     */
    @objid ("30576922-31b8-4fe7-99e0-c7228584e38d")
    public static final String MNAME = "InstanceNode";

    /**
     * The metaclass qualified name.
     */
    @objid ("52d7e18d-df95-4559-a07f-6ff0c362cdc1")
    public static final String MQNAME = "Standard.InstanceNode";

}
