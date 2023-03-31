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
 * JoinPseudoState v0.0.9054
 * 
 * 
 * Join vertices serve to merge several transitions emanating from source vertices in different orthogonal regions. The transitions entering a join vertex cannot have guards or triggers.
 * 
 * 
 */
@objid ("0051d7ea-c4bf-1fd8-97fe-001ec947cd2a")
public interface JoinPseudoState extends AbstractPseudoState {
    /**
     * The metaclass simple name.
     */
    @objid ("b4020764-fd8e-427d-93f6-fa06346b39b7")
    public static final String MNAME = "JoinPseudoState";

    /**
     * The metaclass qualified name.
     */
    @objid ("5a0ec27c-0cba-4b6c-ac4a-65c1a22b2f8f")
    public static final String MQNAME = "Standard.JoinPseudoState";
}

