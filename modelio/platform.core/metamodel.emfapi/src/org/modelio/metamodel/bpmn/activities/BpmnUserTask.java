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

/**
 * BpmnUserTask v0.0.9054
 * 
 * 
 * A User Task is a typical "workflow" Task where a human performer performs the Task with the assistance of a software application and is scheduled through a task list manager of some sort.
 * A User Task is executed by and managed by a business process runtime. Attributes concerning the human involvement,like people assignments and UI rendering can be specified in great detail.
 */
@objid ("0086182a-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnUserTask extends BpmnTask {
    /**
     * The metaclass simple name.
     */
    @objid ("14aae30e-2c38-4fac-a2c0-e4af44b216a3")
    public static final String MNAME = "BpmnUserTask";

    /**
     * The metaclass qualified name.
     */
    @objid ("045490ae-bcf6-4722-befa-e5b095dbe5bb")
    public static final String MQNAME = "Standard.BpmnUserTask";

    /**
     * Getter for attribute 'BpmnUserTask.Implementation'
     * 
     * Metamodel description:
     * <i>This attribute specifies the technology that will be used to send and receive the Messages. A Web service is the default technology.</i>
     */
    @objid ("60bcc173-de1f-4458-b41a-f3f1fd7dc6f8")
    String getImplementation();

    /**
     * Setter for attribute 'BpmnUserTask.Implementation'
     * 
     * Metamodel description:
     * <i>This attribute specifies the technology that will be used to send and receive the Messages. A Web service is the default technology.</i>
     */
    @objid ("ee56b330-4b17-4160-9025-e69104507062")
    void setImplementation(String value);

}
