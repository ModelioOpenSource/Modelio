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
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;

/**
 * State v0.0.9054
 * 
 * 
 * A State represents a period of time during which an object waits for an Event or some Events to occur, or a period of time during which an object performs some ongoing activity. 
 * 
 * States are interconnected by Transitions. 
 * 
 * In Modelio, States belong either to another State, or to a StateMachine if they are the root.
 */
@objid ("0053788e-c4bf-1fd8-97fe-001ec947cd2a")
public interface State extends StateVertex {
    /**
     * The metaclass simple name.
     */
    @objid ("526fee48-dab9-4c48-9960-8ef87f8f1458")
    public static final String MNAME = "State";

    /**
     * The metaclass qualified name.
     */
    @objid ("32c5e46e-8b5e-4d11-912d-c97d164ad71f")
    public static final String MQNAME = "Standard.State";

    /**
     * Getter for relation 'State->ExitPoint'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("c3e4f850-d24c-4cd2-b357-745d0db5eb5c")
    EList<ExitPointPseudoState> getExitPoint();

    /**
     * Filtered Getter for relation 'State->ExitPoint'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("98200dde-572f-464d-853e-f2cb2c473046")
    <T extends ExitPointPseudoState> List<T> getExitPoint(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'State->Deffered'
     * 
     * Metamodel description:
     * <i>A list of Events the effect of whose occurrence during the State is postponed until the owner enters a State in which they are not deferred, at which time they may trigger Transitions as if they had just occurred.</i>
     */
    @objid ("f155ccce-7e59-4d38-a7ed-3206001a84b1")
    EList<Event> getDeffered();

    /**
     * Filtered Getter for relation 'State->Deffered'
     * 
     * Metamodel description:
     * <i>A list of Events the effect of whose occurrence during the State is postponed until the owner enters a State in which they are not deferred, at which time they may trigger Transitions as if they had just occurred.</i>
     */
    @objid ("fd8543d4-8773-463e-8686-38ae7bafd25a")
    <T extends Event> List<T> getDeffered(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'State->Internal'
     * 
     * Metamodel description:
     * <i>Transitions that occur entirely within the State. If one of their triggers is satisfied then the action is performed without changing State. This means that the entry or exit condition of the State will not be invoked. These Transitions apply even if the StateMachine is in a nested region and they leave it in the same State.</i>
     */
    @objid ("3b251bdf-74e0-44c0-86a7-502cb4f2ae2f")
    EList<InternalTransition> getInternal();

    /**
     * Filtered Getter for relation 'State->Internal'
     * 
     * Metamodel description:
     * <i>Transitions that occur entirely within the State. If one of their triggers is satisfied then the action is performed without changing State. This means that the entry or exit condition of the State will not be invoked. These Transitions apply even if the StateMachine is in a nested region and they leave it in the same State.</i>
     */
    @objid ("d2c293eb-0b81-44e0-9e5a-5741670990ff")
    <T extends InternalTransition> List<T> getInternal(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'State->EntryPoint'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("cd238fb1-bf0a-44b1-b317-e2272472a9d7")
    EList<EntryPointPseudoState> getEntryPoint();

    /**
     * Filtered Getter for relation 'State->EntryPoint'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("c87993ed-7fa0-403b-9c8c-4122ff68589c")
    <T extends EntryPointPseudoState> List<T> getEntryPoint(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'State->OwnedRegion'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("ab07a990-9695-457c-9537-e3058d9f0f45")
    EList<Region> getOwnedRegion();

    /**
     * Filtered Getter for relation 'State->OwnedRegion'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("055c0df0-d1af-4853-ba22-544b6fedd1e7")
    <T extends Region> List<T> getOwnedRegion(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'State->RequiredStateOf'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b4724638-0a0b-442e-a0a8-be37fdb87f67")
    EList<ObjectNode> getRequiredStateOf();

    /**
     * Filtered Getter for relation 'State->RequiredStateOf'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2160eb19-36a2-45a1-bdeb-64ce423b8d81")
    <T extends ObjectNode> List<T> getRequiredStateOf(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'State->Connection'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("88903416-822d-4f43-8163-2512fcf7308d")
    EList<ConnectionPointReference> getConnection();

    /**
     * Filtered Getter for relation 'State->Connection'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("2440a565-c2f8-48b4-910d-4f22109d5b39")
    <T extends ConnectionPointReference> List<T> getConnection(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'State->SubMachine'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("dc7b51a8-90db-41ce-a64a-1e3f3de536d6")
    StateMachine getSubMachine();

    /**
     * Setter for relation 'State->SubMachine'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("8b55721f-5ac9-4d68-8e59-a54d65e63967")
    void setSubMachine(StateMachine value);

}
