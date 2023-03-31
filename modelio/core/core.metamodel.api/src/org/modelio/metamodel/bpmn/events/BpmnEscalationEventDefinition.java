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
 * BpmnEscalationEventDefinition v0.0.9054
 * 
 * 
 * Event corresponding to an escalation.
 * 
 * 
 */
@objid ("008be606-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnEscalationEventDefinition extends BpmnEventDefinition {
    /**
     * The metaclass simple name.
     */
    @objid ("6ecd303e-e671-4407-bec3-a17cf5df288e")
    public static final String MNAME = "BpmnEscalationEventDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("35a3036c-bc20-4daf-9568-ec66bab76118")
    public static final String MQNAME = "Standard.BpmnEscalationEventDefinition";

    /**
     * Getter for attribute 'BpmnEscalationEventDefinition.EscalationCode'
     * 
     * Metamodel description:
     * <i>For an End Event:
     * If the Result is an Escalation, then the escalationCode MUST be supplied (if the processType attribute of the Process is set to executable).
     * This ?throws? the Escalation.
     * For an Intermediate Event within normal flow:
     * If the trigger is an Escalation, then the escalationCode MUST be entered (if the processType attribute of the Process is set to executable).
     * This ?throws? the Escalation.
     * For an Intermediate Event attached to the boundary of an Activity: 
     * If the trigger is an Escalation, then the escalationCode MAY be entered.
     * This Event ?catches? the Escalation. If there is no escalationCode, then any Escalation SHALL trigger the Event. If there is an escalationCode, then only an Escalation that matches the escalationCode SHALL trigger the Event.</i>
     * 
     */
    @objid ("da480362-cb57-4894-8119-5c5727524288")
    String getEscalationCode();

    /**
     * Setter for attribute 'BpmnEscalationEventDefinition.EscalationCode'
     * 
     * Metamodel description:
     * <i>For an End Event:
     * If the Result is an Escalation, then the escalationCode MUST be supplied (if the processType attribute of the Process is set to executable).
     * This ?throws? the Escalation.
     * For an Intermediate Event within normal flow:
     * If the trigger is an Escalation, then the escalationCode MUST be entered (if the processType attribute of the Process is set to executable).
     * This ?throws? the Escalation.
     * For an Intermediate Event attached to the boundary of an Activity: 
     * If the trigger is an Escalation, then the escalationCode MAY be entered.
     * This Event ?catches? the Escalation. If there is no escalationCode, then any Escalation SHALL trigger the Event. If there is an escalationCode, then only an Escalation that matches the escalationCode SHALL trigger the Event.</i>
     * 
     */
    @objid ("056b17a1-51b2-424b-8f43-2cb6b0087710")
    void setEscalationCode(String value);
}

