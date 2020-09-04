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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Operation;

/**
 * Transition v0.0.9054
 * 
 * 
 * Transitions represent the reaction of an object in a certain State, to a particular Event. 
 * 
 * For protocol state diagrams, Transitions represent the possible paths between States. 
 * 
 * In Modelio, a Transition belongs to its source StateVertex.
 */
@objid ("0055b6a8-c4bf-1fd8-97fe-001ec947cd2a")
public interface Transition extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("8bf14276-2643-4c96-93fd-e2c4a4f388f2")
    public static final String MNAME = "Transition";

    /**
     * The metaclass qualified name.
     */
    @objid ("a3da56c8-3255-4fc3-9641-ad0ce2ccfd91")
    public static final String MQNAME = "Standard.Transition";

    /**
     * Getter for attribute 'Transition.Effect'
     * 
     * Metamodel description:
     * <i>Defines the actions triggered by the Transition. This field excludes the ProcessedOperation association that is shorthand for defining a call action.</i>
     */
    @objid ("f2e000da-79d4-431f-bd7e-e702083c5f54")
    String getEffect();

    /**
     * Setter for attribute 'Transition.Effect'
     * 
     * Metamodel description:
     * <i>Defines the actions triggered by the Transition. This field excludes the ProcessedOperation association that is shorthand for defining a call action.</i>
     */
    @objid ("f0b4089f-e38c-4133-895b-e1c9e755a585")
    void setEffect(String value);

    /**
     * Getter for attribute 'Transition.ReceivedEvents'
     * 
     * Metamodel description:
     * <i>Received events that trigger the Transition.</i>
     */
    @objid ("24550a91-df70-46e2-ae42-46b061724801")
    String getReceivedEvents();

    /**
     * Setter for attribute 'Transition.ReceivedEvents'
     * 
     * Metamodel description:
     * <i>Received events that trigger the Transition.</i>
     */
    @objid ("adac3e1a-29ba-4c3b-948f-35be615b1bb3")
    void setReceivedEvents(String value);

    /**
     * Getter for attribute 'Transition.SentEvents'
     * 
     * Metamodel description:
     * <i>Events sent by the Transition once it is triggered.</i>
     */
    @objid ("43314849-2a50-4e23-9411-df13d75cde38")
    String getSentEvents();

    /**
     * Setter for attribute 'Transition.SentEvents'
     * 
     * Metamodel description:
     * <i>Events sent by the Transition once it is triggered.</i>
     */
    @objid ("e4b421bf-01fb-405c-8c5d-681f24cfce41")
    void setSentEvents(String value);

    /**
     * Getter for attribute 'Transition.Guard'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("341fd243-77d5-4bc9-baed-6c112273ca97")
    String getGuard();

    /**
     * Setter for attribute 'Transition.Guard'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("00234c58-7955-4fbc-85a0-94541cf6ba35")
    void setGuard(String value);

    /**
     * Getter for attribute 'Transition.PostCondition'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("86ff58ab-0183-4aa3-9fff-c810de7117f5")
    String getPostCondition();

    /**
     * Setter for attribute 'Transition.PostCondition'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("d01fc467-d26f-461c-8812-41f537e64051")
    void setPostCondition(String value);

    /**
     * Getter for relation 'Transition->Processed'
     * 
     * Metamodel description:
     * <i>The Operation processed once the Transition is triggered. This is shorthand for a call event, and is also useful for defining the operation carried by a Transition in protocol state diagrams.</i>
     */
    @objid ("1b6f5134-9144-402a-8ca3-a965339b15db")
    Operation getProcessed();

    /**
     * Setter for relation 'Transition->Processed'
     * 
     * Metamodel description:
     * <i>The Operation processed once the Transition is triggered. This is shorthand for a call event, and is also useful for defining the operation carried by a Transition in protocol state diagrams.</i>
     */
    @objid ("45eba240-3b75-44a3-936e-4e8094f88594")
    void setProcessed(Operation value);

    /**
     * Getter for relation 'Transition->Trigger'
     * 
     * Metamodel description:
     * <i>Events that may trigger the Transition (under initial state and initial Transitions). This association is exclusive from the "ReceivedEvents" string.</i>
     */
    @objid ("c58ec606-7484-4d0e-8929-bdaa22b181f2")
    Event getTrigger();

    /**
     * Setter for relation 'Transition->Trigger'
     * 
     * Metamodel description:
     * <i>Events that may trigger the Transition (under initial state and initial Transitions). This association is exclusive from the "ReceivedEvents" string.</i>
     */
    @objid ("1b8cc04d-a302-4c07-a092-fd2b9c32c872")
    void setTrigger(Event value);

    /**
     * Getter for relation 'Transition->BehaviorEffect'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("bbd95b8a-0e38-4205-b249-a04950e887be")
    Behavior getBehaviorEffect();

    /**
     * Setter for relation 'Transition->BehaviorEffect'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7f2a2cfa-7da1-47b2-a97c-eb6d1f1cea14")
    void setBehaviorEffect(Behavior value);

    /**
     * Getter for relation 'Transition->Target'
     * 
     * Metamodel description:
     * <i>Specifies the Transitions entering the vertex.</i>
     */
    @objid ("b7833985-b8a5-4d60-9483-e64cfbe837ea")
    StateVertex getTarget();

    /**
     * Setter for relation 'Transition->Target'
     * 
     * Metamodel description:
     * <i>Specifies the Transitions entering the vertex.</i>
     */
    @objid ("dfa0edf3-4cf7-41df-b78e-6ea29e98445d")
    void setTarget(StateVertex value);

    /**
     * Getter for relation 'Transition->Source'
     * 
     * Metamodel description:
     * <i>Specifies the Transitions departing from the vertex.</i>
     */
    @objid ("f277a879-268b-4a12-a16e-13c98f086ccb")
    StateVertex getSource();

    /**
     * Setter for relation 'Transition->Source'
     * 
     * Metamodel description:
     * <i>Specifies the Transitions departing from the vertex.</i>
     */
    @objid ("1e6c487b-66bb-46f5-ab7f-d99fa191be3d")
    void setSource(StateVertex value);

    /**
     * Getter for relation 'Transition->Effects'
     * 
     * Metamodel description:
     * <i>When the Transition is accomplished, occurrences of this Signal will be sent.</i>
     */
    @objid ("62464da3-6667-41fd-91ed-77e8c5278bda")
    Signal getEffects();

    /**
     * Setter for relation 'Transition->Effects'
     * 
     * Metamodel description:
     * <i>When the Transition is accomplished, occurrences of this Signal will be sent.</i>
     */
    @objid ("312cb56b-f4c5-439a-a0c0-73196172e68a")
    void setEffects(Signal value);

}
