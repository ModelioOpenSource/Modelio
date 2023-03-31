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

package org.modelio.metamodel.uml.behavior.interactionModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * ExecutionOccurenceSpecification v0.0.9054
 * 
 * 
 * An execution occurence specification is a kind of message end. It specifies the occurrence of message events, such as sending and receiving signals or invoking or receiving operation calls. 
 * 
 * Messages are generated either by synchronous operation calls or asynchronous signal sends. They are received by the execution of corresponding accept event actions.
 * 
 * It can also represent an execution occurrence specification : in other words, it can represent a moment in time at which actions or behaviors start or finish.
 * 
 * 
 */
@objid ("00444a76-c4bf-1fd8-97fe-001ec947cd2a")
public interface ExecutionOccurenceSpecification extends MessageEnd {
    /**
     * The metaclass simple name.
     */
    @objid ("7f96629e-cd20-4d09-ada2-e11e76fdb2fc")
    public static final String MNAME = "ExecutionOccurenceSpecification";

    /**
     * The metaclass qualified name.
     */
    @objid ("16197473-3dc8-4369-949c-49a390cf52a6")
    public static final String MQNAME = "Standard.ExecutionOccurenceSpecification";

    /**
     * Getter for relation 'ExecutionOccurenceSpecification->Finished'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("53ef1b45-ea41-4cb9-8973-deef2492b8e0")
    ExecutionSpecification getFinished();

    /**
     * Setter for relation 'ExecutionOccurenceSpecification->Finished'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("1a75e285-76fe-414c-8cab-60a7b48ab233")
    void setFinished(ExecutionSpecification value);

    /**
     * Getter for relation 'ExecutionOccurenceSpecification->Started'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("1016c93f-a569-437c-8108-6bbeca976754")
    ExecutionSpecification getStarted();

    /**
     * Setter for relation 'ExecutionOccurenceSpecification->Started'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("467f25e3-dd86-41c9-a573-a0b75725d752")
    void setStarted(ExecutionSpecification value);
}

