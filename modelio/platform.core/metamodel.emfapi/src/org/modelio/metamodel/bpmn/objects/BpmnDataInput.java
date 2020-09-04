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
package org.modelio.metamodel.bpmn.objects;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;

/**
 * BpmnDataInput v0.0.9054
 * 
 * 
 * Activities and Processes often required data in order to execute. In addition they may produce data during or as a result of execution. Data requirements are captured as Data Inputs
 * DataInput elements may appear in a Process diagram to show the inputs to
 * the Process as whole, which are passed along as the inputs of Activities by DataAssociations.
 * 
 * Ownership:
 * A DtaInput is owned by a process, or by an activity.
 */
@objid ("00065874-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnDataInput extends BpmnItemAwareElement {
    /**
     * The metaclass simple name.
     */
    @objid ("ec1a0520-05c3-4309-8bd6-57591b645b68")
    public static final String MNAME = "BpmnDataInput";

    /**
     * The metaclass qualified name.
     */
    @objid ("5e55f24d-e23c-4523-80e9-284b8cdf8ed9")
    public static final String MQNAME = "Standard.BpmnDataInput";

    /**
     * Getter for attribute 'BpmnDataInput.IsCollection'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3aa9704b-487d-41e5-b933-31586f14afdb")
    boolean isIsCollection();

    /**
     * Setter for attribute 'BpmnDataInput.IsCollection'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("79e10b7a-9438-45b7-9fba-e90464bd814c")
    void setIsCollection(boolean value);

    /**
     * Getter for relation 'BpmnDataInput->OwnerLoopCharacteristics'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e5491d6d-3e33-4870-9117-c26d5a807cf3")
    BpmnMultiInstanceLoopCharacteristics getOwnerLoopCharacteristics();

    /**
     * Setter for relation 'BpmnDataInput->OwnerLoopCharacteristics'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("24113f9c-5685-4fed-8828-ea3aef9b6eab")
    void setOwnerLoopCharacteristics(BpmnMultiInstanceLoopCharacteristics value);

    /**
     * Getter for relation 'BpmnDataInput->OwnerActivity'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("fedeb17b-481a-479a-b6aa-ff7e6e898bda")
    BpmnActivity getOwnerActivity();

    /**
     * Setter for relation 'BpmnDataInput->OwnerActivity'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7fba89a7-a81b-484b-94f3-4ab7403b1b9e")
    void setOwnerActivity(BpmnActivity value);

    /**
     * Getter for relation 'BpmnDataInput->OwnerThrowEvent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e8023505-8ad7-4506-9f58-4f87429b965d")
    BpmnThrowEvent getOwnerThrowEvent();

    /**
     * Setter for relation 'BpmnDataInput->OwnerThrowEvent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("54fea6a7-2bf8-41b0-9e2b-66e5b3e54ccd")
    void setOwnerThrowEvent(BpmnThrowEvent value);

}
