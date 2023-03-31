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

package org.modelio.metamodel.bpmn.activities;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * BpmnTransaction v0.0.9054
 * 
 * 
 * A Transaction is a specialized type of Sub-Process which will have a special behavior that is controlled through a transaction protocol (such as WS-Transaction). The boundary of the Sub-Process will be double-lined to indicate that it is a Transaction
 * 
 * 
 */
@objid ("00855ebc-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnTransaction extends BpmnSubProcess {
    /**
     * The metaclass simple name.
     */
    @objid ("6d96da93-eca9-4e42-a1a1-703787d6325d")
    public static final String MNAME = "BpmnTransaction";

    /**
     * The metaclass qualified name.
     */
    @objid ("82f36642-1ab4-4b6a-b5fc-c4daf1cd15d3")
    public static final String MQNAME = "Standard.BpmnTransaction";

    /**
     * Getter for attribute 'BpmnTransaction.Method'
     * 
     * Metamodel description:
     * <i>TransactionMethod is an attribute that defines the technique that will be used to undo a Transaction that has been cancelled.
     * The default is compensate, but the attribute MAY be set to store or image.</i>
     * 
     */
    @objid ("6fedfcd5-a6db-46be-b068-055565fd88a3")
    TransactionMethod getMethod();

    /**
     * Setter for attribute 'BpmnTransaction.Method'
     * 
     * Metamodel description:
     * <i>TransactionMethod is an attribute that defines the technique that will be used to undo a Transaction that has been cancelled.
     * The default is compensate, but the attribute MAY be set to store or image.</i>
     * 
     */
    @objid ("29a1fa51-9b8e-42fd-9730-28c720e35ca1")
    void setMethod(TransactionMethod value);
}

