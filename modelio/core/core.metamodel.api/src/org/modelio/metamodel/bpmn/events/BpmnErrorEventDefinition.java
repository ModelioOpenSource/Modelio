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
package org.modelio.metamodel.bpmn.events;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * BpmnErrorEventDefinition v0.0.9054
 * 
 * 
 * Event definition for an error.
 */
@objid ("008b3af8-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnErrorEventDefinition extends BpmnEventDefinition {
    /**
     * The metaclass simple name.
     */
    @objid ("6e8bd7ce-d3a3-4603-9589-a8395bd006b6")
    public static final String MNAME = "BpmnErrorEventDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("a76fee0d-6942-407e-ab19-2262fa95a5e5")
    public static final String MQNAME = "Standard.BpmnErrorEventDefinition";

    /**
     * Getter for attribute 'BpmnErrorEventDefinition.ErrorCode'
     * 
     * Metamodel description:
     * <i>For an End Event:
     * If the result is an Error, then the errorCode MUST be supplied (if the processType attribute of the Process is set to executable) This ?throws? the Error.
     * For an Intermediate Event within normal flow:
     * If the Trigger is an Error, then the errorCode MUST be entered (if the processType attribute of the Process is set to executable). This ?throws? the Error.
     * For an Intermediate Event attached to the boundary of an Activity:
     * If the Trigger is an Error, then the errorCode MAY be entered. This Event ?catches? the Error. If there is no errorCode, then any error SHALL trigger the Event. If there is an errorCode, then only an Error that matches the errorCode SHALL trigger the Event.</i>
     */
    @objid ("e105da0c-90b9-4653-881b-4eec31fd4899")
    String getErrorCode();

    /**
     * Setter for attribute 'BpmnErrorEventDefinition.ErrorCode'
     * 
     * Metamodel description:
     * <i>For an End Event:
     * If the result is an Error, then the errorCode MUST be supplied (if the processType attribute of the Process is set to executable) This ?throws? the Error.
     * For an Intermediate Event within normal flow:
     * If the Trigger is an Error, then the errorCode MUST be entered (if the processType attribute of the Process is set to executable). This ?throws? the Error.
     * For an Intermediate Event attached to the boundary of an Activity:
     * If the Trigger is an Error, then the errorCode MAY be entered. This Event ?catches? the Error. If there is no errorCode, then any error SHALL trigger the Event. If there is an errorCode, then only an Error that matches the errorCode SHALL trigger the Event.</i>
     */
    @objid ("07db8ec5-9b15-46ee-8336-b5233e43e19c")
    void setErrorCode(String value);

}
