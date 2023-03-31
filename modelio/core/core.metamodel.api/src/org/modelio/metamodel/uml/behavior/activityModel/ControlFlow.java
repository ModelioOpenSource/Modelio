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
 * ControlFlow v0.0.9054
 * 
 * 
 * A control flow is an edge that starts an activity node after the previous one is finished.
 * Objects and data cannot pass along a control flow edge. 
 * 
 * 
 */
@objid ("002de952-c4bf-1fd8-97fe-001ec947cd2a")
public interface ControlFlow extends ActivityEdge {
    /**
     * The metaclass simple name.
     */
    @objid ("99d2ce06-1d5a-4180-9c20-4523a8d98115")
    public static final String MNAME = "ControlFlow";

    /**
     * The metaclass qualified name.
     */
    @objid ("2a15f88b-94b1-43d8-aa1a-3fe05cc017fc")
    public static final String MQNAME = "Standard.ControlFlow";
}

