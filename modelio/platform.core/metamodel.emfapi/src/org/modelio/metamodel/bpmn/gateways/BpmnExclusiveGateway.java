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
package org.modelio.metamodel.bpmn.gateways;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;

/**
 * BpmnExclusiveGateway v0.0.9054
 * 
 * 
 * A diverging Exclusive Gateway (Decision) is used to create alternative paths within a Process flow. This is basically the ?diversion point in the road? for a Process. For a given instance of the Process, only one of the paths can be taken.
 * A Decision can be thought of as a question that is asked at a particular point in the Process. The question has a defined set of alternative answers. Each question is associated with a condition Expression that is associated with a Gateway?s outgoing Sequence Flow.
 */
@objid ("000002bc-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnExclusiveGateway extends BpmnGateway {
    /**
     * The metaclass simple name.
     */
    @objid ("c23aa0ee-a08a-48ca-ab88-49cbe2b561b4")
    public static final String MNAME = "BpmnExclusiveGateway";

    /**
     * The metaclass qualified name.
     */
    @objid ("417ae2a5-e489-4d26-9881-cb8a3078f8e2")
    public static final String MQNAME = "Standard.BpmnExclusiveGateway";

    /**
     * Getter for relation 'BpmnExclusiveGateway->DefaultFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1c3f9ff2-7329-4684-8521-44b88611175b")
    BpmnSequenceFlow getDefaultFlow();

    /**
     * Setter for relation 'BpmnExclusiveGateway->DefaultFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5ce4e8d1-3ebb-451e-8457-67b3ed6198b5")
    void setDefaultFlow(BpmnSequenceFlow value);

}
