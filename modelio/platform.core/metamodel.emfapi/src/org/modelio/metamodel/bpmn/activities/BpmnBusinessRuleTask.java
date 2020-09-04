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
 * BpmnBusinessRuleTask v0.0.9054
 * 
 * 
 * A Business Rule Task provides a mechanism for the Process to provide input to a Business Rules Engine and to get the output of calculations that the Business Rules Engine might provide. The InputOutputSpecification of the Task will allow the Process to send data to and receive data from the Business Rules Engine.
 */
@objid ("007e6878-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnBusinessRuleTask extends BpmnTask {
    /**
     * The metaclass simple name.
     */
    @objid ("35d6cabd-e6e9-41b7-a3de-a6d8156752fa")
    public static final String MNAME = "BpmnBusinessRuleTask";

    /**
     * The metaclass qualified name.
     */
    @objid ("a1b51726-204a-459f-a037-ec750dec359f")
    public static final String MQNAME = "Standard.BpmnBusinessRuleTask";

}
