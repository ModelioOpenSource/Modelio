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
package org.modelio.metamodel.uml.behavior.stateMachineModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * JunctionPseudoState v0.0.9054
 * 
 * 
 * Junction vertices are semantic-free vertices that are used to chain together multiple transitions. They are used to construct compound transition paths between states. For example, a junction can be used to converge multiple incoming transitions into a single outgoing transition representing a shared transition path (this is known as a merge). 
 * 
 * Conversely, they can be used to split an incoming transition into multiple outgoing transition segments with different guard conditions. This realizes a static conditional branch. 
 * (In the latter case, outgoing transitions whose guard conditions evaluate to false are disabled. 
 * A predefined guard denoted "else" may be defined for at most one outgoing transition. This transition is enabled if all the guards labeling the other transitions are false.) 
 * Static conditional branches are distinct from dynamic conditional branches that are realized by choice vertices (described below).
 */
@objid ("00525fe4-c4bf-1fd8-97fe-001ec947cd2a")
public interface JunctionPseudoState extends AbstractPseudoState {
    /**
     * The metaclass simple name.
     */
    @objid ("e9fad77d-4b39-44d1-9f3f-ef87dacc2c0a")
    public static final String MNAME = "JunctionPseudoState";

    /**
     * The metaclass qualified name.
     */
    @objid ("b8104bf0-9195-4876-9f28-0d54f2301f57")
    public static final String MQNAME = "Standard.JunctionPseudoState";

}
