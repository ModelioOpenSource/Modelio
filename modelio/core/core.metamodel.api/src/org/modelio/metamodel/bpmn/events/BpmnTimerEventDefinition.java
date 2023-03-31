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
 * BpmnTimerEventDefinition v0.0.9054
 * 
 * 
 * Event corresponding to a timer event, such as the end of a duration
 * 
 * 
 */
@objid ("00965cf8-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnTimerEventDefinition extends BpmnEventDefinition {
    /**
     * The metaclass simple name.
     */
    @objid ("f6c55b38-c67b-47f1-ab22-9087408c3dc2")
    public static final String MNAME = "BpmnTimerEventDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("2b222fc1-151a-4310-affb-9175de72278c")
    public static final String MQNAME = "Standard.BpmnTimerEventDefinition";

    /**
     * Getter for attribute 'BpmnTimerEventDefinition.TimeCycle'
     * 
     * Metamodel description:
     * <i>If the trigger is a Timer, then a timeCycle MAY be entered. If a timeCycle is not entered, then a timeDate MUST be entered (see attribute above?if the processType attribute of the Process is set to executable).</i>
     * 
     */
    @objid ("16db0a30-5697-4512-a883-59a87c6e5d80")
    String getTimeCycle();

    /**
     * Setter for attribute 'BpmnTimerEventDefinition.TimeCycle'
     * 
     * Metamodel description:
     * <i>If the trigger is a Timer, then a timeCycle MAY be entered. If a timeCycle is not entered, then a timeDate MUST be entered (see attribute above?if the processType attribute of the Process is set to executable).</i>
     * 
     */
    @objid ("317d2120-d7d0-4173-8f6f-2af9fdb8a1c0")
    void setTimeCycle(String value);

    /**
     * Getter for attribute 'BpmnTimerEventDefinition.TimeDate'
     * 
     * Metamodel description:
     * <i>If the trigger is a Timer, then a timeDate MAY be entered. If a timeDate is not entered, then a timeCycle MUST be entered (see attribute below?if the processType attribute of the Process is set to executable).</i>
     * 
     */
    @objid ("d281f450-c90f-4d4e-ab41-eaf2ab118276")
    String getTimeDate();

    /**
     * Setter for attribute 'BpmnTimerEventDefinition.TimeDate'
     * 
     * Metamodel description:
     * <i>If the trigger is a Timer, then a timeDate MAY be entered. If a timeDate is not entered, then a timeCycle MUST be entered (see attribute below?if the processType attribute of the Process is set to executable).</i>
     * 
     */
    @objid ("5e45bac0-c40d-4acc-90e8-70de1991f8bf")
    void setTimeDate(String value);

    /**
     * Getter for attribute 'BpmnTimerEventDefinition.TimeDuration'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("06310744-8b53-4854-913d-da607b8f865f")
    String getTimeDuration();

    /**
     * Setter for attribute 'BpmnTimerEventDefinition.TimeDuration'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("309d4bab-b553-44e9-a918-871bd4c181f4")
    void setTimeDuration(String value);
}

