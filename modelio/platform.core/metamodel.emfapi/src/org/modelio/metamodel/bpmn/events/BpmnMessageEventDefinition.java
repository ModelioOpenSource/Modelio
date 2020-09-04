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
package org.modelio.metamodel.bpmn.events;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;

/**
 * BpmnMessageEventDefinition v0.0.9054
 * 
 * 
 * Specifies the receive or sending of a message.
 */
@objid ("0090c6e4-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnMessageEventDefinition extends BpmnEventDefinition {
    /**
     * The metaclass simple name.
     */
    @objid ("db570b71-e253-475d-a319-d90609cb9fb8")
    public static final String MNAME = "BpmnMessageEventDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("d8ae280f-563b-4778-8986-193384a82e07")
    public static final String MQNAME = "Standard.BpmnMessageEventDefinition";

    /**
     * Getter for relation 'BpmnMessageEventDefinition->MessageRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f6e53f06-5170-479a-b138-1dccd20574f2")
    BpmnMessage getMessageRef();

    /**
     * Setter for relation 'BpmnMessageEventDefinition->MessageRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ce3b626c-faa5-4119-a0e8-bc09fd2e4f72")
    void setMessageRef(BpmnMessage value);

    /**
     * Getter for relation 'BpmnMessageEventDefinition->OperationRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1620806f-ca90-4384-b748-0778d11ddd33")
    EList<BpmnOperation> getOperationRef();

    /**
     * Filtered Getter for relation 'BpmnMessageEventDefinition->OperationRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b250dc09-bba1-4ba8-8d50-36492a1b8d6a")
    <T extends BpmnOperation> List<T> getOperationRef(java.lang.Class<T> filterClass);

}
