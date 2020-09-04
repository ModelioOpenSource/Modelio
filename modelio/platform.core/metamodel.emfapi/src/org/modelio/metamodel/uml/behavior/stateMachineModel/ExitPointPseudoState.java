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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;

/**
 * ExitPointPseudoState v0.0.9054
 * 
 * 
 * An exit point pseudostate is an exit point of a state machine or composite state. Entering an exit point within any region of the composite state or state machine referenced by a submachine state implies the exit of this composite state or submachine state and the triggering of the transition that has this exit point as source in the state machine enclosing the submachine or composite state.
 */
@objid ("004fc78e-c4bf-1fd8-97fe-001ec947cd2a")
public interface ExitPointPseudoState extends AbstractPseudoState {
    /**
     * The metaclass simple name.
     */
    @objid ("e1d0bc4b-cc96-4bcc-8b39-a47397d421fb")
    public static final String MNAME = "ExitPointPseudoState";

    /**
     * The metaclass qualified name.
     */
    @objid ("d2b249bd-af52-4959-b06b-326e0a7b8f27")
    public static final String MQNAME = "Standard.ExitPointPseudoState";

    /**
     * Getter for relation 'ExitPointPseudoState->ExitOf'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("c3e11d2e-db22-43aa-96bf-150d5065778e")
    State getExitOf();

    /**
     * Setter for relation 'ExitPointPseudoState->ExitOf'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("ba45ac5d-8529-40cf-afe4-6b50c43ea738")
    void setExitOf(State value);

    /**
     * Getter for relation 'ExitPointPseudoState->Connection'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("4e724efc-720e-416c-8841-cb642f1d1c0b")
    EList<ConnectionPointReference> getConnection();

    /**
     * Filtered Getter for relation 'ExitPointPseudoState->Connection'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("fc24d24a-8edb-409e-889c-77825bba3f08")
    <T extends ConnectionPointReference> List<T> getConnection(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ExitPointPseudoState->ExitOfMachine'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6b6d3bf7-c03c-4f74-9f1a-67ccbfab19d7")
    StateMachine getExitOfMachine();

    /**
     * Setter for relation 'ExitPointPseudoState->ExitOfMachine'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4c60da79-fc2e-41f0-9a18-c6445b56365c")
    void setExitOfMachine(StateMachine value);

}
