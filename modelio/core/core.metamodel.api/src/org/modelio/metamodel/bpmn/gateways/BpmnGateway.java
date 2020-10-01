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
package org.modelio.metamodel.bpmn.gateways;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.gateways.BpmnGatewayDirection;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;

/**
 * BpmnGateway v0.0.9054
 * 
 * 
 * Gateways are used to control how the Process flows (how Tokens flow) through Sequence Flow as they converge and diverge within a Process. If the flow does not need to be controlled, then a Gateway is not needed. The term ?gateway? implies that there is a gating mechanism that either allows or disallows passage through the Gateway--that is, as tokens arrive at a Gateway, they can be merged together on input and/or split apart on output as the Gateway mechanisms are invoked. Gateways, like Activities, are capable of consuming or generating additional control tokens, effectively controlling the execution semantics of a given Process. The main difference is that Gateways do not represent ?work? being done and they are considered to have zero effect on the operational measures of the Process being executed (cost, time, etc.). The Gateway controls the flow of both diverging and converging Sequence Flow. That is, a single Gateway could have multiple input and multiple output flows. Modelers and modeling tools may want to enforce a best practice of a Gateway only performing one of these functions. Thus, it would take two sequential Gateways to first converge and then to diverge the Sequence Flow.
 * 
 * Ownership
 * Gateways belong to Flow element containers, or sub processes
 */
@objid ("0000e402-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnGateway extends BpmnFlowNode {
    /**
     * The metaclass simple name.
     */
    @objid ("29b5bb64-f78e-449d-8fb5-8d17b03a5150")
    public static final String MNAME = "BpmnGateway";

    /**
     * The metaclass qualified name.
     */
    @objid ("c4a7a3ce-e27e-4406-8ca9-709a7ee5bdd2")
    public static final String MQNAME = "Standard.BpmnGateway";

    /**
     * Getter for attribute 'BpmnGateway.GatewayDirection'
     * 
     * Metamodel description:
     * <i>- A Gateway with a gatewayDirection of unspecified MAY have both multiple incoming and outgoing Sequence Flow.
     * -  A Gateway with a gatewayDirection of mixed MUST have both multiple incoming and outgoing Sequence Flow.
     * -  A Gateway with a gatewayDirection of converging MUST have multiple incoming Sequence Flow, but MUST NOT have multiple outgoing Sequence Flow.
     * -  A Gateway with a gatewayDirection of diverging MUST have multiple outgoing Sequence Flow, but MUST NOT have multiple incoming Sequence Flow.</i>
     */
    @objid ("0ed70bd5-9ea1-4f18-b49a-ce3646dbd0d4")
    BpmnGatewayDirection getGatewayDirection();

    /**
     * Setter for attribute 'BpmnGateway.GatewayDirection'
     * 
     * Metamodel description:
     * <i>- A Gateway with a gatewayDirection of unspecified MAY have both multiple incoming and outgoing Sequence Flow.
     * -  A Gateway with a gatewayDirection of mixed MUST have both multiple incoming and outgoing Sequence Flow.
     * -  A Gateway with a gatewayDirection of converging MUST have multiple incoming Sequence Flow, but MUST NOT have multiple outgoing Sequence Flow.
     * -  A Gateway with a gatewayDirection of diverging MUST have multiple outgoing Sequence Flow, but MUST NOT have multiple incoming Sequence Flow.</i>
     */
    @objid ("3c04761c-f2a5-46a1-95b1-fa3bb65c881b")
    void setGatewayDirection(BpmnGatewayDirection value);

}
