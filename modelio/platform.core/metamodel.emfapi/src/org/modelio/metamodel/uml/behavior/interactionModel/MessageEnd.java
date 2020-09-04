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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;

/**
 * MessageEnd v0.0.9054
 * 
 * 
 * A MessageEnd is an abstract NamedElement that represents what can occur at the end of a Message.
 * Subclasses of MessageEnd define the specific semantics appropriate to the concept they represent.
 */
@objid ("0049cc1c-c4bf-1fd8-97fe-001ec947cd2a")
public interface MessageEnd extends OccurrenceSpecification {
    /**
     * The metaclass simple name.
     */
    @objid ("d7eed736-5227-4779-9851-9e55b25ba877")
    public static final String MNAME = "MessageEnd";

    /**
     * The metaclass qualified name.
     */
    @objid ("8b14ae92-d065-4a0f-998a-359df4b8e96b")
    public static final String MQNAME = "Standard.MessageEnd";

    /**
     * Getter for relation 'MessageEnd->ReceivedMessage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4aa78118-b98f-42ad-a06a-20f1df002763")
    Message getReceivedMessage();

    /**
     * Setter for relation 'MessageEnd->ReceivedMessage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d913cd9c-4a21-4bcf-aa46-ae147f0c50b9")
    void setReceivedMessage(Message value);

    /**
     * Getter for relation 'MessageEnd->SentMessage'
     * 
     * Metamodel description:
     * <i>Message sent.</i>
     */
    @objid ("6ecb5fc9-684c-406c-9633-1c8480ad78b0")
    Message getSentMessage();

    /**
     * Setter for relation 'MessageEnd->SentMessage'
     * 
     * Metamodel description:
     * <i>Message sent.</i>
     */
    @objid ("f05f6d25-83c8-4a07-84a2-bca5891850e7")
    void setSentMessage(Message value);

}
