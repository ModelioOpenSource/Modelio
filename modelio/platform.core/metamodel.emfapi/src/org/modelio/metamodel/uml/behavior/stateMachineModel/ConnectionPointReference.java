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
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;

/**
 * ConnectionPointReference v0.0.9054
 */
@objid ("004e3efa-c4bf-1fd8-97fe-001ec947cd2a")
public interface ConnectionPointReference extends StateVertex {
    /**
     * The metaclass simple name.
     */
    @objid ("5c32b414-2173-4a66-b269-79b0e89d6a1c")
    public static final String MNAME = "ConnectionPointReference";

    /**
     * The metaclass qualified name.
     */
    @objid ("5fd9421d-60b2-47cc-b1b3-6de8a92e0ab1")
    public static final String MQNAME = "Standard.ConnectionPointReference";

    /**
     * Getter for relation 'ConnectionPointReference->Exit'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("d8efebae-6e1c-4630-a7a8-7d84f3c31a65")
    ExitPointPseudoState getExit();

    /**
     * Setter for relation 'ConnectionPointReference->Exit'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("96c7f8c8-feb3-45cf-a9b3-8675cc97366c")
    void setExit(ExitPointPseudoState value);

    /**
     * Getter for relation 'ConnectionPointReference->Entry'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("724eb9ac-1e57-4f9c-8e8d-b5712a9e2cd1")
    EntryPointPseudoState getEntry();

    /**
     * Setter for relation 'ConnectionPointReference->Entry'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("1b3b5a3a-91eb-4b3d-9b3c-c65f14e5c815")
    void setEntry(EntryPointPseudoState value);

    /**
     * Getter for relation 'ConnectionPointReference->OwnerState'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("9ab3fc46-90f8-4ece-acaf-b2e398f5f93a")
    State getOwnerState();

    /**
     * Setter for relation 'ConnectionPointReference->OwnerState'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("6e4be17e-39d9-40e5-b0ac-e4cac097f452")
    void setOwnerState(State value);

}
