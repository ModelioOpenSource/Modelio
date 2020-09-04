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
package org.modelio.metamodel.uml.behavior.stateMachineModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;

/**
 * InternalTransition v0.0.9054
 * 
 * 
 * This Transition is related to a State. It can be triggered when entering or exiting the State, or can describe an activity that is performed whilst in the State (do Transitions).  
 * 
 * A Transition belongs to its origin StateVertex.
 */
@objid ("005151a8-c4bf-1fd8-97fe-001ec947cd2a")
public interface InternalTransition extends Transition {
    /**
     * The metaclass simple name.
     */
    @objid ("0ac1a1ba-50b8-48ff-8089-58254804cdcd")
    public static final String MNAME = "InternalTransition";

    /**
     * The metaclass qualified name.
     */
    @objid ("265425f6-d54b-4553-afa1-e2fbcab72c2b")
    public static final String MQNAME = "Standard.InternalTransition";

    /**
     * Getter for relation 'InternalTransition->SComposed'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("59cfc1b9-f5ea-4e23-887d-3bc9488de694")
    State getSComposed();

    /**
     * Setter for relation 'InternalTransition->SComposed'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("66029b7a-26f2-4d92-a63e-a7443328ff22")
    void setSComposed(State value);

}
