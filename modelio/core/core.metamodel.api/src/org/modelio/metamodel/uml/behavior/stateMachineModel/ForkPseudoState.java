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

package org.modelio.metamodel.uml.behavior.stateMachineModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * ForkPseudoState v0.0.9054
 * 
 * 
 * ForkPseudoState vertices serve to split an incoming transition into two or more transitions terminating on orthogonal target vertices (i.e., vertices in different regions of a composite state). The segments outgoing from a fork vertex must not have guards or triggers.
 * 
 * 
 */
@objid ("00504830-c4bf-1fd8-97fe-001ec947cd2a")
public interface ForkPseudoState extends AbstractPseudoState {
    /**
     * The metaclass simple name.
     */
    @objid ("03f7d3a7-bfc6-4aad-8233-dea5355bac96")
    public static final String MNAME = "ForkPseudoState";

    /**
     * The metaclass qualified name.
     */
    @objid ("91333213-9d8c-4fd7-901b-5e3895ccee69")
    public static final String MQNAME = "Standard.ForkPseudoState";
}

