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
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Operation;

/**
 * Event v0.0.9054
 * 
 * 
 * An Event is the specification of a specific occurrence at a specific point in space and time.  An instance of an Event can lead to the activation of a behavioral Feature in an object. 
 * 
 * An Event can be either an occurrence of a Signal, a message occurrence, a time or a change expression occurrence.  
 * 
 * In Modelio, an Event belongs to a State Machine.
 * 
 * 
 */
@objid ("00428d1c-c4bf-1fd8-97fe-001ec947cd2a")
public interface Event extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("173cc384-3d30-40ff-9e2e-f2a5cda31b4b")
    public static final String MNAME = "Event";

    /**
     * The metaclass qualified name.
     */
    @objid ("e98b3396-26c1-4159-83ad-ca9814c0e8ea")
    public static final String MQNAME = "Standard.Event";

    /**
     * Getter for attribute 'Event.Expression'
     * 
     * Metamodel description:
     * <i>Expression initiating the Event. This can be a time expression or a triggering condition, and can contain parameter values in the case of operation call event, and so on.</i>
     * 
     */
    @objid ("ed9b8f39-83d4-46af-b825-929a6c2aa255")
    String getExpression();

    /**
     * Setter for attribute 'Event.Expression'
     * 
     * Metamodel description:
     * <i>Expression initiating the Event. This can be a time expression or a triggering condition, and can contain parameter values in the case of operation call event, and so on.</i>
     * 
     */
    @objid ("d2b5fe6f-fac5-46ff-ade3-e50f9811176d")
    void setExpression(String value);

    /**
     * Getter for attribute 'Event.Kind'
     * 
     * Metamodel description:
     * <i>Defines the nature of the event (Time, Signal occurrence, and so on.)</i>
     * 
     */
    @objid ("9b51efb2-1a77-4e47-99b4-faeb8adbf318")
    EventType getKind();

    /**
     * Setter for attribute 'Event.Kind'
     * 
     * Metamodel description:
     * <i>Defines the nature of the event (Time, Signal occurrence, and so on.)</i>
     * 
     */
    @objid ("481bba70-747e-4feb-b197-f7372756fe31")
    void setKind(EventType value);

    /**
     * Getter for relation 'Event->Triggered'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("53645357-c9c3-40f5-9957-aa41d78a2c80")
    EList<Transition> getTriggered();

    /**
     * Filtered Getter for relation 'Event->Triggered'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("de1819f6-014c-45fd-90e4-cd63b5be8be9")
    <T extends Transition> List<T> getTriggered(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Event->Model'
     * 
     * Metamodel description:
     * <i>Signal of which the Event is an occurrence.</i>
     * 
     */
    @objid ("415cbc0a-cc97-4d8c-a160-ec2fd9314de1")
    Signal getModel();

    /**
     * Setter for relation 'Event->Model'
     * 
     * Metamodel description:
     * <i>Signal of which the Event is an occurrence.</i>
     * 
     */
    @objid ("0270f3f5-fbfa-4da4-9ca6-746b1b534dc8")
    void setModel(Signal value);

    /**
     * Getter for relation 'Event->Origin'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("4328336c-44db-4b94-b68f-69637770fcd2")
    EList<State> getOrigin();

    /**
     * Filtered Getter for relation 'Event->Origin'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("8e9b0179-4fd8-46ad-86eb-09f4f9ca55f6")
    <T extends State> List<T> getOrigin(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Event->Called'
     * 
     * Metamodel description:
     * <i>Direct link to an Operation in case of a call Event.</i>
     * 
     */
    @objid ("6a8d0d72-cc92-4711-93e8-34cb82f835a6")
    Operation getCalled();

    /**
     * Setter for relation 'Event->Called'
     * 
     * Metamodel description:
     * <i>Direct link to an Operation in case of a call Event.</i>
     * 
     */
    @objid ("b9bba279-01fb-4891-92d9-a6b5b5a390fc")
    void setCalled(Operation value);

    /**
     * Getter for relation 'Event->Composed'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("eb550e00-0e93-4788-aa83-cb5cb5b24dcb")
    Behavior getComposed();

    /**
     * Setter for relation 'Event->Composed'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("1de2759b-8c56-408b-b5d1-1116ed49329b")
    void setComposed(Behavior value);
}

