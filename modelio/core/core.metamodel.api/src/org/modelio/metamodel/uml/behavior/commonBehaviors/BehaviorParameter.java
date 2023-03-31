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
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.statik.Parameter;

/**
 * BehaviorParameter v0.0.9054
 * 
 * 
 * BehaviorParameter is a Parameter extended to add support for streaming, exceptions and parameter sets.
 * 
 * A BehaviorParameter is owned only by a Behavior : Activities, Interactions, State machines or OpaqueBehaviors.
 * 
 * Behaviors that are owned by Operations have their BehaviorParameter linked to the corresponding operation Parameter.
 * 
 * isException applies to output parameters. An output posted to an exception excludes outputs from being posted to other data and control outputs of the behavior. A token arriving at an exception output parameter of an activity aborts all flows in the activity. Any objects previously posted to non-stream outputs never leave the activity. Streaming outputs posted before any exception are not affected. Use exception parameters on activities only if it is desired to abort all flows in the activity.
 * 
 * Streaming parameters give action access to tokens passed from its invoker while the action is executing. Values for streaming parameters may arrive anytime during the execution of the action, not just at the beginning. 
 * 
 * The effect of a parameter is a declaration of the modeler's intent, and does not have execution semantics. The modeler must ensure that the owner of the parameter has the stated effect.
 * 
 * 
 */
@objid ("004119be-c4bf-1fd8-97fe-001ec947cd2a")
public interface BehaviorParameter extends Parameter {
    /**
     * The metaclass simple name.
     */
    @objid ("29094803-bf46-47be-924f-c42a444252c7")
    public static final String MNAME = "BehaviorParameter";

    /**
     * The metaclass qualified name.
     */
    @objid ("750b8816-667c-4548-ae34-d598f42ca0bd")
    public static final String MQNAME = "Standard.BehaviorParameter";

    /**
     * Getter for relation 'BehaviorParameter->RepresentingObjectNode'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("27d6b791-4bd0-4f19-92f8-d997905cb682")
    EList<ObjectNode> getRepresentingObjectNode();

    /**
     * Filtered Getter for relation 'BehaviorParameter->RepresentingObjectNode'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("718c84e7-e6b8-4c87-bcde-9ddf566a19db")
    <T extends ObjectNode> List<T> getRepresentingObjectNode(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BehaviorParameter->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("2f08bb7a-be9e-4f38-b6f6-18eeb493253a")
    Behavior getOwner();

    /**
     * Setter for relation 'BehaviorParameter->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("64028638-7572-40d4-a713-85b4ae67db0e")
    void setOwner(Behavior value);

    /**
     * Getter for relation 'BehaviorParameter->Mapped'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("bd695c7d-8c27-427d-b60e-1f11ac952cb0")
    Parameter getMapped();

    /**
     * Setter for relation 'BehaviorParameter->Mapped'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("cbf143b1-6c28-4a1f-891c-8f90c9ce9482")
    void setMapped(Parameter value);
}

