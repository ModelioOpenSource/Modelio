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
package org.modelio.metamodel.uml.behavior.commonBehaviors;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.informationFlow.DataFlow;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;

/**
 * Signal v0.0.9054
 * 
 * 
 * Signals are processed by StateMachines, which represent how SignalEvents are taken into account. 
 * 
 * Modelio provides the DataFlow extension to UML.  Using this extension, a Signal can be declared as representing a ModelElement (GeneralClass, Operation, or Parameter). 
 * 
 * A DataFlow associated to the Signal will then be able to express this data, represented by the Signal, and may circulate between different NameSpaces.  
 * 
 * In Modelio, a Signal belongs to a NameSpace, notably its Package.
 */
@objid ("00421152-c4bf-1fd8-97fe-001ec947cd2a")
public interface Signal extends GeneralClass {
    /**
     * The metaclass simple name.
     */
    @objid ("6c5c9692-5da5-4c17-8cf2-68f1ac5785f2")
    public static final String MNAME = "Signal";

    /**
     * The metaclass qualified name.
     */
    @objid ("fa6c1313-30b7-4172-bea4-c38bb1ae403b")
    public static final String MQNAME = "Standard.Signal";

    /**
     * Getter for attribute 'Signal.IsEvent'
     * 
     * Metamodel description:
     * <i>Establishes if it is an event in the sense of event based systems : CORBA, Java, XWindow's, SGBDR.</i>
     */
    @objid ("0480e45a-6886-42c0-b13c-78b8ab8f713d")
    boolean isIsEvent();

    /**
     * Setter for attribute 'Signal.IsEvent'
     * 
     * Metamodel description:
     * <i>Establishes if it is an event in the sense of event based systems : CORBA, Java, XWindow's, SGBDR.</i>
     */
    @objid ("b47d48ab-22b9-48c5-87ec-5f1f114e042d")
    void setIsEvent(boolean value);

    /**
     * Getter for attribute 'Signal.IsException'
     * 
     * Metamodel description:
     * <i>Defines if it is an exception, as they exist in Java, C++, and so on.</i>
     */
    @objid ("7652bc7c-103c-4e5a-a8fa-ee4e662fd446")
    boolean isIsException();

    /**
     * Setter for attribute 'Signal.IsException'
     * 
     * Metamodel description:
     * <i>Defines if it is an exception, as they exist in Java, C++, and so on.</i>
     */
    @objid ("9ecad15f-7403-4692-8909-d35b3a624c86")
    void setIsException(boolean value);

    /**
     * Getter for relation 'Signal->Sender'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("022e5c44-f663-4da4-8639-9b68dd8c72fb")
    EList<SendSignalAction> getSender();

    /**
     * Filtered Getter for relation 'Signal->Sender'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ced63337-39ce-42f8-90bd-d077dff7d70f")
    <T extends SendSignalAction> List<T> getSender(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Signal->Usage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f29e2e4a-e5e9-43fa-b193-525c198df148")
    EList<Message> getUsage();

    /**
     * Filtered Getter for relation 'Signal->Usage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("32b6a3ff-7ef7-493d-9a41-7f72b2ccde96")
    <T extends Message> List<T> getUsage(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Signal->Sends'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5fbc4043-71c3-4fa2-8c35-72372f15ccb1")
    EList<Transition> getSends();

    /**
     * Filtered Getter for relation 'Signal->Sends'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c8de396e-ab02-483d-b196-1ed35eed0fa2")
    <T extends Transition> List<T> getSends(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Signal->PBase'
     * 
     * Metamodel description:
     * <i>Parameter that the Signal may represent.</i>
     */
    @objid ("9a83ac2d-0b09-43f4-8fe1-99b21824cd6b")
    Parameter getPBase();

    /**
     * Setter for relation 'Signal->PBase'
     * 
     * Metamodel description:
     * <i>Parameter that the Signal may represent.</i>
     */
    @objid ("4a70fdf5-35b2-4c97-bf6d-f67d64977605")
    void setPBase(Parameter value);

    /**
     * Getter for relation 'Signal->OBase'
     * 
     * Metamodel description:
     * <i>The Operation that the Signal may represent.</i>
     */
    @objid ("5d1e9f76-f0d9-4b67-a34e-4ac268bb9498")
    Operation getOBase();

    /**
     * Setter for relation 'Signal->OBase'
     * 
     * Metamodel description:
     * <i>The Operation that the Signal may represent.</i>
     */
    @objid ("f604cc17-9920-4a0a-a7d0-2659ddf966f4")
    void setOBase(Operation value);

    /**
     * Getter for relation 'Signal->CommunicationUsage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d7ed9b2c-9f72-4c27-953d-a33403c8913b")
    EList<CommunicationMessage> getCommunicationUsage();

    /**
     * Filtered Getter for relation 'Signal->CommunicationUsage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("940a558d-b540-4c08-9dac-bccf65aba0cc")
    <T extends CommunicationMessage> List<T> getCommunicationUsage(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Signal->DOccurence'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a51f6fac-85fb-48fa-82df-d3b3861efe20")
    EList<DataFlow> getDOccurence();

    /**
     * Filtered Getter for relation 'Signal->DOccurence'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c8f09e68-aa31-4979-af01-1efd506d638e")
    <T extends DataFlow> List<T> getDOccurence(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Signal->EOccurence'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f06e9701-bed5-478b-88af-6a964a67a2f4")
    EList<Event> getEOccurence();

    /**
     * Filtered Getter for relation 'Signal->EOccurence'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2c133f02-d778-4c15-874c-c08e507222c0")
    <T extends Event> List<T> getEOccurence(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Signal->Base'
     * 
     * Metamodel description:
     * <i>Class that the Signal may represent.</i>
     */
    @objid ("7c271445-4f62-40f6-a5cd-146acbb64048")
    GeneralClass getBase();

    /**
     * Setter for relation 'Signal->Base'
     * 
     * Metamodel description:
     * <i>Class that the Signal may represent.</i>
     */
    @objid ("332a71dc-5b01-4699-b13d-7978d6dfd6de")
    void setBase(GeneralClass value);

    /**
     * Getter for relation 'Signal->Receiver'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("47a6d36a-07a0-47b5-ac0d-05c42d9fcecf")
    EList<AcceptSignalAction> getReceiver();

    /**
     * Filtered Getter for relation 'Signal->Receiver'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("cbd0a8e5-49ed-47a7-945c-b91754b129e5")
    <T extends AcceptSignalAction> List<T> getReceiver(java.lang.Class<T> filterClass);

}
