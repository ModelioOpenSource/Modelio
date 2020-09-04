/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.modelio.metamodel.bpmn.activities.BpmnActivity;

/**
 * BpmnCompensateEventDefinition v0.0.9054
 * 
 * 
 * Compensation Events are used in the context of triggering or handling compensation. There are four (4) variations: a Start Event, both a catch and throw Intermediate Event, and an End Event.
 * - The Compensation Start Event MAY NOT be used for a top-level Process.
 * - The Compensation Start Event MAY be used for an Event Sub-Process.
 * - The catch Compensation Intermediate Event MUST only be attached to the boundary of an Activity and, thus, MAY NOT be used in normal flow.
 * - The throw Compensation Intermediate Event MAY be used in normal flow.
 * - The Compensation End Event MAY be used within any Sub-Process or Process.
 */
@objid ("008944c8-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnCompensateEventDefinition extends BpmnEventDefinition {
    /**
     * The metaclass simple name.
     */
    @objid ("66a5fd45-f43e-4a10-801e-39c1ec407c46")
    public static final String MNAME = "BpmnCompensateEventDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("a59260d2-c00a-4f10-b018-2bd633db715f")
    public static final String MQNAME = "Standard.BpmnCompensateEventDefinition";

    /**
     * Getter for attribute 'BpmnCompensateEventDefinition.WaitForCompletion'
     * 
     * Metamodel description:
     * <i>The Expression might be underspecified and provided in the form of natural language.
     * For executable Processes (processType = executable), if the trigger is Conditional, then a FormalExpression MUST be entered.</i>
     */
    @objid ("f1f6e168-8e7b-4266-9e70-e971a4c5152d")
    String getWaitForCompletion();

    /**
     * Setter for attribute 'BpmnCompensateEventDefinition.WaitForCompletion'
     * 
     * Metamodel description:
     * <i>The Expression might be underspecified and provided in the form of natural language.
     * For executable Processes (processType = executable), if the trigger is Conditional, then a FormalExpression MUST be entered.</i>
     */
    @objid ("09f7c892-f8f0-4a92-947d-fba4d34b3bea")
    void setWaitForCompletion(String value);

    /**
     * Getter for relation 'BpmnCompensateEventDefinition->ActivityRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("dd2c0b25-45f3-44d3-adcb-92e623cb5ffd")
    BpmnActivity getActivityRef();

    /**
     * Setter for relation 'BpmnCompensateEventDefinition->ActivityRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("53b688b2-a80e-4aed-8d06-4c638d771189")
    void setActivityRef(BpmnActivity value);

}
