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
 * BpmnConditionalEventDefinition v0.0.9054
 * 
 * 
 * Event defined by a condition
 */
@objid ("0089eafe-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnConditionalEventDefinition extends BpmnEventDefinition {
    /**
     * The metaclass simple name.
     */
    @objid ("9eb017f5-3824-47b8-9a67-6c8d01d5d831")
    public static final String MNAME = "BpmnConditionalEventDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("4ac8c48b-f2b9-41d7-ba26-b9b1a1160058")
    public static final String MQNAME = "Standard.BpmnConditionalEventDefinition";

    /**
     * Getter for attribute 'BpmnConditionalEventDefinition.Condition'
     * 
     * Metamodel description:
     * <i>The Expression might be underspecified and provided in the form of natural language.
     * For executable Processes (processType = executable), if the trigger is Conditional, then a FormalExpression MUST be entered.</i>
     */
    @objid ("3bbdf2ef-bd75-4a92-aaf4-52570b30504b")
    String getCondition();

    /**
     * Setter for attribute 'BpmnConditionalEventDefinition.Condition'
     * 
     * Metamodel description:
     * <i>The Expression might be underspecified and provided in the form of natural language.
     * For executable Processes (processType = executable), if the trigger is Conditional, then a FormalExpression MUST be entered.</i>
     */
    @objid ("c5330437-5edc-473f-ba19-dbcf0ba616e2")
    void setCondition(String value);

}
