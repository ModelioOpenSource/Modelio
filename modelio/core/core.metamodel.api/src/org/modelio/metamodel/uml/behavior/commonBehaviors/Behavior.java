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

package org.modelio.metamodel.uml.behavior.commonBehaviors;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;

/**
 * Behavior v0.0.9054
 * 
 * 
 * Behavior is a specification of how its context classifier changes state over time. This specification may be either a definition of possible behavior execution or emergent behavior, or a selective illustration of an interesting subset of possible executions. The latter form is typically used for capturing examples, such as a trace of a particular execution. 
 */
@objid ("00409570-c4bf-1fd8-97fe-001ec947cd2a")
public interface Behavior extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("8bd1cffe-20ea-46ff-a961-824335343923")
    public static final String MNAME = "Behavior";

    /**
     * The metaclass qualified name.
     */
    @objid ("dfc564a9-d9f4-4cc6-ad14-a88e80a2b7ce")
    public static final String MQNAME = "Standard.Behavior";

    /**
     * Getter for attribute 'Behavior.IsReentrant'
     * 
     * Metamodel description:
     * <i>Indicates whether the behavior can be invoked while it is still executing from a previous invocation. The default value is false.</i>
     */
    @objid ("f58f98f7-fdb4-4570-b5d3-4f8cb0a4e03e")
    boolean isIsReentrant();

    /**
     * Setter for attribute 'Behavior.IsReentrant'
     * 
     * Metamodel description:
     * <i>Indicates whether the behavior can be invoked while it is still executing from a previous invocation. The default value is false.</i>
     */
    @objid ("96348433-d2e4-48c5-99bd-37bf8c61247b")
    void setIsReentrant(boolean value);

    /**
     * Getter for relation 'Behavior->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("44751a20-6564-410b-9d3d-eaccf043da7f")
    NameSpace getOwner();

    /**
     * Setter for relation 'Behavior->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e8847023-4169-4b12-846a-85f26889abde")
    void setOwner(NameSpace value);

    /**
     * Getter for relation 'Behavior->Parameter'
     * 
     * Metamodel description:
     * <i>References a list of parameters to the behavior that describes the order and type of arguments that can be given when the behavior is invoked and of the values that will be returned when the behavior completes its execution.</i>
     */
    @objid ("a3ca0e38-38c5-4457-85e2-6790f1fb3049")
    EList<BehaviorParameter> getParameter();

    /**
     * Filtered Getter for relation 'Behavior->Parameter'
     * 
     * Metamodel description:
     * <i>References a list of parameters to the behavior that describes the order and type of arguments that can be given when the behavior is invoked and of the values that will be returned when the behavior completes its execution.</i>
     */
    @objid ("69e76e0f-72b3-4075-8e5e-388c1d0ed95c")
    <T extends BehaviorParameter> List<T> getParameter(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Behavior->OwnerOperation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e9cd2733-79bb-4abc-b583-116bd6db32ce")
    Operation getOwnerOperation();

    /**
     * Setter for relation 'Behavior->OwnerOperation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("73ca8aad-3da7-4448-8d85-0513a659e3f5")
    void setOwnerOperation(Operation value);

    /**
     * Getter for relation 'Behavior->OwnedCollaboration'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("46c644bf-dfcc-4afd-a085-806360303838")
    EList<Collaboration> getOwnedCollaboration();

    /**
     * Filtered Getter for relation 'Behavior->OwnedCollaboration'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("aab9f9a7-e323-432c-a63c-813eeb2dec42")
    <T extends Collaboration> List<T> getOwnedCollaboration(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Behavior->Caller'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("69128dc0-5db6-458a-afe7-9a04b77b9949")
    EList<CallBehaviorAction> getCaller();

    /**
     * Filtered Getter for relation 'Behavior->Caller'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0e44c679-2187-48a4-9c0e-da2ddd7894d7")
    <T extends CallBehaviorAction> List<T> getCaller(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Behavior->EComponent'
     * 
     * Metamodel description:
     * <i>Events are defined in the context of a Behavior.</i>
     */
    @objid ("651c9e0c-3225-49e7-9711-1ef4a533288c")
    EList<Event> getEComponent();

    /**
     * Filtered Getter for relation 'Behavior->EComponent'
     * 
     * Metamodel description:
     * <i>Events are defined in the context of a Behavior.</i>
     */
    @objid ("643c315a-3141-400b-9810-606027d366b2")
    <T extends Event> List<T> getEComponent(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Behavior->EffectOf'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7980f96d-dda2-450b-acfe-821cd8dd9fad")
    EList<Transition> getEffectOf();

    /**
     * Filtered Getter for relation 'Behavior->EffectOf'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2445d750-c4d7-4b60-bcd3-9a8e35779490")
    <T extends Transition> List<T> getEffectOf(java.lang.Class<T> filterClass);

}
