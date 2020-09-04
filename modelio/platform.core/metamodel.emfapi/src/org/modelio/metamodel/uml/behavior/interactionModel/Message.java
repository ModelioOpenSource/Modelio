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
package org.modelio.metamodel.uml.behavior.interactionModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageKind;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Operation;

/**
 * Message v2.2.01
 * 
 * 
 * Messages are used in object diagrams, collaboration diagrams and sequence diagrams. 
 * 
 * If the Message has no InvokedOperation, then its description is in its name. 
 * 
 * In Modelio, a Message belongs to the sending MessageEnd.
 * 
 * The message sequencing information is handled in sequence diagrams by both its MessageEnds, with  an internal feature that is not directly accessible.
 * The Joni Java API will provide methods to access the ordering of MessagesEnd as for InteractionFragments.
 */
@objid ("00493ff4-c4bf-1fd8-97fe-001ec947cd2a")
public interface Message extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("33face65-32af-4e41-8c46-62b85289c3fc")
    public static final String MNAME = "Message";

    /**
     * The metaclass qualified name.
     */
    @objid ("1d73ad54-847a-4a8c-a997-cee424d5f1bf")
    public static final String MQNAME = "Standard.Message";

    /**
     * Getter for attribute 'Message.Argument'
     * 
     * Metamodel description:
     * <i>Arguments passed with the message.</i>
     */
    @objid ("b07c14cc-cafc-4d54-a9ea-be050d0c0e80")
    String getArgument();

    /**
     * Setter for attribute 'Message.Argument'
     * 
     * Metamodel description:
     * <i>Arguments passed with the message.</i>
     */
    @objid ("e778037b-a90c-4b78-ab3e-f1dfbd05129f")
    void setArgument(String value);

    /**
     * Getter for attribute 'Message.KindOfMessage'
     * 
     * Metamodel description:
     * <i>The derived kind of the Message (complete, lost, found, or unknown). The default value is unknown.</i>
     */
    @objid ("69c5e5a5-2189-4c53-9d35-4d67584928dc")
    MessageKind getKindOfMessage();

    /**
     * Setter for attribute 'Message.KindOfMessage'
     * 
     * Metamodel description:
     * <i>The derived kind of the Message (complete, lost, found, or unknown). The default value is unknown.</i>
     */
    @objid ("8f0b9298-1892-4535-9a6e-12dcf583e5d5")
    void setKindOfMessage(MessageKind value);

    /**
     * Getter for attribute 'Message.SortOfMessage'
     * 
     * Metamodel description:
     * <i>The sort of communication reflected by the Message. The default value is synchCall.</i>
     */
    @objid ("1dd151fa-6087-4f80-95d4-007bfcb8a836")
    MessageSort getSortOfMessage();

    /**
     * Setter for attribute 'Message.SortOfMessage'
     * 
     * Metamodel description:
     * <i>The sort of communication reflected by the Message. The default value is synchCall.</i>
     */
    @objid ("b24eef98-49e1-453e-b4b2-0f1e40a72b3b")
    void setSortOfMessage(MessageSort value);

    /**
     * Getter for attribute 'Message.Sequence'
     * 
     * Metamodel description:
     * <i>Arguments passed with the message.</i>
     */
    @objid ("8ace86bf-73b7-447d-98b1-82c7bf2c51b5")
    String getSequence();

    /**
     * Setter for attribute 'Message.Sequence'
     * 
     * Metamodel description:
     * <i>Arguments passed with the message.</i>
     */
    @objid ("2afcd8ad-54f0-4067-9ee7-02f7432ac668")
    void setSequence(String value);

    /**
     * Getter for relation 'Message->SignalSignature'
     * 
     * Metamodel description:
     * <i>Signal that is sent by the message.</i>
     */
    @objid ("71df1909-20a8-4a08-8414-b86cece376e6")
    Signal getSignalSignature();

    /**
     * Setter for relation 'Message->SignalSignature'
     * 
     * Metamodel description:
     * <i>Signal that is sent by the message.</i>
     */
    @objid ("8a321b92-431e-4b2b-9daf-acafb43fa393")
    void setSignalSignature(Signal value);

    /**
     * Getter for relation 'Message->ReceiveEvent'
     * 
     * Metamodel description:
     * <i>References the reception of the message.</i>
     */
    @objid ("df1a3657-2d45-4783-9273-a61e4ff958c5")
    MessageEnd getReceiveEvent();

    /**
     * Setter for relation 'Message->ReceiveEvent'
     * 
     * Metamodel description:
     * <i>References the reception of the message.</i>
     */
    @objid ("6d2f06bc-19c4-43f3-b1ff-4df3b0ef86ec")
    void setReceiveEvent(MessageEnd value);

    /**
     * Getter for relation 'Message->SendEvent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f0ebf765-985a-44d1-a735-3491d3b4faf4")
    MessageEnd getSendEvent();

    /**
     * Setter for relation 'Message->SendEvent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("140b7683-c454-4a77-8630-986a062d3d08")
    void setSendEvent(MessageEnd value);

    /**
     * Getter for relation 'Message->Invoked'
     * 
     * Metamodel description:
     * <i>The Operation that is invoked by the Message.</i>
     */
    @objid ("12cb26e2-22fb-4464-8d4c-e7cf57095395")
    Operation getInvoked();

    /**
     * Setter for relation 'Message->Invoked'
     * 
     * Metamodel description:
     * <i>The Operation that is invoked by the Message.</i>
     */
    @objid ("5ee404a4-24b3-43f2-a3e6-289f700c025a")
    void setInvoked(Operation value);

    /**
     * Getter for relation 'Message->RealizedInformationFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e264b8e3-6026-4d19-9cda-34a9fcfefe58")
    EList<InformationFlow> getRealizedInformationFlow();

    /**
     * Filtered Getter for relation 'Message->RealizedInformationFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("fca4f651-ed57-4400-93b4-fb4cfae60a90")
    <T extends InformationFlow> List<T> getRealizedInformationFlow(java.lang.Class<T> filterClass);

}
