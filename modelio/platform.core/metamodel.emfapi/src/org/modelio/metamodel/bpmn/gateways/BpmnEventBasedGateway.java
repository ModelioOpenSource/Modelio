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
package org.modelio.metamodel.bpmn.gateways;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGatewayType;

/**
 * BpmnEventBasedGateway v0.0.9054
 * 
 * 
 * The Event-Based Gateway represents a branching point in the Process where the alternative paths that follow the Gateway are based on Events that occur, rather than the evaluation of Expressions using Process data (as with an Exclusive or Inclusive Gateway). A specific Event, usually the receipt of a Message, determines the path that will be taken. Basically, the decision is made by another Participant, based on data that is not visible to Process, thus, requiring the use of the Event-Based Gateway.
 * For example, if a company is waiting for a response from a customer they will perform one set of Activities if the customer responds ?Yes? and another set of Activities if the customer responds ?No.? The customer?s response determines which path is taken. The identity of the Message determines which path is taken. That is, the ?Yes? Message and the ?No? Message are different Messages?i.e., they are not the same Message with different values within a property of the Message. The receipt of the Message can be modeled with an Intermediate Event with a
 * Message Trigger or a Receive Task. In addition to Messages, other Triggers for Intermediate Events can be used, such as Timers.
 */
@objid ("0097e370-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnEventBasedGateway extends BpmnGateway {
    /**
     * The metaclass simple name.
     */
    @objid ("f8db0aa8-12c3-4a7c-918c-0ffc5f32f84b")
    public static final String MNAME = "BpmnEventBasedGateway";

    /**
     * The metaclass qualified name.
     */
    @objid ("895df629-efce-4de4-80c6-7944246639a0")
    public static final String MQNAME = "Standard.BpmnEventBasedGateway";

    /**
     * Getter for attribute 'BpmnEventBasedGateway.Instanciate'
     * 
     * Metamodel description:
     * <i>Event Gateways can be used to instantiate a Process. By default the Gateway?s instantiate attribute is false, but if set to true, then the Process is instantiated when the first Event of the Gateway?s configuration is triggered.</i>
     */
    @objid ("eda45df4-f2f7-40a4-bc26-4064ec7459c1")
    boolean isInstanciate();

    /**
     * Setter for attribute 'BpmnEventBasedGateway.Instanciate'
     * 
     * Metamodel description:
     * <i>Event Gateways can be used to instantiate a Process. By default the Gateway?s instantiate attribute is false, but if set to true, then the Process is instantiated when the first Event of the Gateway?s configuration is triggered.</i>
     */
    @objid ("0d03c4f9-15bd-4279-852e-5f78e54f55c8")
    void setInstanciate(boolean value);

    /**
     * Getter for attribute 'BpmnEventBasedGateway.EventGatewayType'
     * 
     * Metamodel description:
     * <i>The eventGatewayType determines the behavior of the Gateway when used to instantiate a Process (as described above).
     * </i>
     */
    @objid ("af2efaed-bca9-4112-876e-ca4ee1b473a0")
    BpmnEventBasedGatewayType getEventGatewayType();

    /**
     * Setter for attribute 'BpmnEventBasedGateway.EventGatewayType'
     * 
     * Metamodel description:
     * <i>The eventGatewayType determines the behavior of the Gateway when used to instantiate a Process (as described above).
     * </i>
     */
    @objid ("ccd6a773-eac6-4904-bb01-5748f77795f4")
    void setEventGatewayType(BpmnEventBasedGatewayType value);

}
