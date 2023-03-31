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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;

/**
 * StateMachine v0.0.9054
 * 
 * 
 * State diagams can also define usage protocol for Classes.  
 * 
 * In Modelio, a StateMachine belongs to a Package, an Operation, a UseCase or a Class. Its natural position is to belong to a Class.
 * 
 * 
 */
@objid ("005404fc-c4bf-1fd8-97fe-001ec947cd2a")
public interface StateMachine extends Behavior {
    /**
     * The metaclass simple name.
     */
    @objid ("e32f89f8-3abb-47e5-b35c-a8ba16e8e4bf")
    public static final String MNAME = "StateMachine";

    /**
     * The metaclass qualified name.
     */
    @objid ("a45ad8a5-e5e6-48f2-be50-27ee5bbe791b")
    public static final String MQNAME = "Standard.StateMachine";

    /**
     * Getter for attribute 'StateMachine.Kind'
     * 
     * Metamodel description:
     * <i>A state machine can be a dynamic state machine, as usually defined in UML (Harel state diagrams), or a protocol state machine. Protocol state machines represent the usage protocol of the Class' Operations. It defines in which order and for which condition and state an Operation can be invoked.</i>
     * 
     */
    @objid ("1b5d64db-77ae-49c1-b65b-79ac52e4ecf8")
    KindOfStateMachine getKind();

    /**
     * Setter for attribute 'StateMachine.Kind'
     * 
     * Metamodel description:
     * <i>A state machine can be a dynamic state machine, as usually defined in UML (Harel state diagrams), or a protocol state machine. Protocol state machines represent the usage protocol of the Class' Operations. It defines in which order and for which condition and state an Operation can be invoked.</i>
     * 
     */
    @objid ("d71d61ee-4dc1-424b-acb1-2585f25ea25f")
    void setKind(KindOfStateMachine value);

    /**
     * Getter for relation 'StateMachine->Top'
     * 
     * Metamodel description:
     * <i>Defines the root state for the current StateMachine. All other states will be substates of the TopState.</i>
     * 
     */
    @objid ("766b6f7c-3fd0-4930-9664-22c947acdf3c")
    Region getTop();

    /**
     * Setter for relation 'StateMachine->Top'
     * 
     * Metamodel description:
     * <i>Defines the root state for the current StateMachine. All other states will be substates of the TopState.</i>
     * 
     */
    @objid ("7e28844a-8e2d-4e19-b01f-0212c1d9d7e4")
    void setTop(Region value);

    /**
     * Getter for relation 'StateMachine->SubmachineState'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("2d5c711c-7836-4925-a705-a6d0a6e537c3")
    EList<State> getSubmachineState();

    /**
     * Filtered Getter for relation 'StateMachine->SubmachineState'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("12c11f53-3254-412f-bd73-2a06ce25beae")
    <T extends State> List<T> getSubmachineState(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'StateMachine->EntryPoint'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("ac712475-ea1c-4911-8eb4-b644256ef65d")
    EList<EntryPointPseudoState> getEntryPoint();

    /**
     * Filtered Getter for relation 'StateMachine->EntryPoint'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("410951dc-f04a-4ca3-b157-d45427d9ce8e")
    <T extends EntryPointPseudoState> List<T> getEntryPoint(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'StateMachine->ExitPoint'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("a3e180a6-c8b3-4ed3-b265-558bf82d2d00")
    EList<ExitPointPseudoState> getExitPoint();

    /**
     * Filtered Getter for relation 'StateMachine->ExitPoint'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("c4f064e1-a807-4c90-b8b1-3d2c6096a66b")
    <T extends ExitPointPseudoState> List<T> getExitPoint(java.lang.Class<T> filterClass);
}

