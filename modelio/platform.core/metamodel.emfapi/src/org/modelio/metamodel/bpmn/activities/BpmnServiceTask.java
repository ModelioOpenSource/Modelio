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
package org.modelio.metamodel.bpmn.activities;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;

/**
 * BpmnServiceTask v0.0.9054
 * 
 * 
 * A Service Task is a Task that uses some sort of service, which could be a Web service or an automated application.
 * A Service Task object shares the same shape as the Task, which is a rectangle that has rounded corners. However, there is a graphical marker in the upper left corner of the shape that indicates that the Task is a Service Task
 */
@objid ("008313be-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnServiceTask extends BpmnTask {
    /**
     * The metaclass simple name.
     */
    @objid ("73e61a90-f38b-4760-a635-16161bf06f20")
    public static final String MNAME = "BpmnServiceTask";

    /**
     * The metaclass qualified name.
     */
    @objid ("34986b5f-eeef-497d-8572-09ace7a4b02d")
    public static final String MQNAME = "Standard.BpmnServiceTask";

    /**
     * Getter for attribute 'BpmnServiceTask.Implementation'
     * 
     * Metamodel description:
     * <i>This attribute specifies the technology that will be used to send and receive the Messages. 
     * 
     * Valid values are "##unspecified" for leaving the implementation technology open, "##WebService" for the Web service technology or a URI identifying any other technology or coordination protocol.
     * 
     * A Web service is the default technology.</i>
     */
    @objid ("d9995f2d-6100-453a-899f-a698f77f2623")
    String getImplementation();

    /**
     * Setter for attribute 'BpmnServiceTask.Implementation'
     * 
     * Metamodel description:
     * <i>This attribute specifies the technology that will be used to send and receive the Messages. 
     * 
     * Valid values are "##unspecified" for leaving the implementation technology open, "##WebService" for the Web service technology or a URI identifying any other technology or coordination protocol.
     * 
     * A Web service is the default technology.</i>
     */
    @objid ("9dbee6ff-a0a5-4435-919f-8685a466333d")
    void setImplementation(String value);

    /**
     * Getter for relation 'BpmnServiceTask->OperationRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e858d345-03fd-43c3-97a7-2b34c025dd8f")
    BpmnOperation getOperationRef();

    /**
     * Setter for relation 'BpmnServiceTask->OperationRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e913a9fd-0e1c-4aef-a780-764bda8a05d2")
    void setOperationRef(BpmnOperation value);

}
