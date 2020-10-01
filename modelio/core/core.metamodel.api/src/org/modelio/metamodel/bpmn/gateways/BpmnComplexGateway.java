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
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;

/**
 * BpmnComplexGateway v0.0.9054
 * 
 * 
 * The Complex Gateway can be used to model complex synchronization behavior. An Expression
 * activationCondition is used to describe the precise behavior. For example, this Expression could specify that tokens on three out of five incoming Sequence Flow are needed to activate the Gateway. What tokens are produced by the Gateway is determined by conditions on the outgoing Sequence Flow as in the split behavior of the Inclusive Gateway. If token arrive later on the two remaining Sequence Flow, those tokens cause a reset of the Gateway and new token can be produced on the outgoing Sequence Flow. To determine whether it needs to wait for additional tokens before it can reset, the Gateway uses the synchronization semantics of the Inclusive Gateway.
 */
@objid ("00973ae2-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnComplexGateway extends BpmnGateway {
    /**
     * The metaclass simple name.
     */
    @objid ("a65c603a-0d5e-43ef-919b-d04f23d898f6")
    public static final String MNAME = "BpmnComplexGateway";

    /**
     * The metaclass qualified name.
     */
    @objid ("d950d3ba-fb47-4d06-9136-f55e48ed7bef")
    public static final String MQNAME = "Standard.BpmnComplexGateway";

    /**
     * Getter for attribute 'BpmnComplexGateway.ActivationExpression'
     * 
     * Metamodel description:
     * <i>An activationExpression is a boolean Expression
     * that refers to data and to the activationCount of incoming gates. For example, an activationExpression
     * could be x1+x2+?+xm >= 3 stating that it needs 3 out of the m incoming gates to have a token in order to proceed. To
     * prevent undesirable oscillation of activation of the Complex Gateway, ActivationCount variables should only be
     * used in subexpressions of the form expr >= const where expr is an arithmetic Expression that uses only addition and
     * const is an Expression whose evaluation remains constant during execution of the Process.</i>
     */
    @objid ("90bc42c2-4daf-48fc-bee9-0ebae0ef138e")
    String getActivationExpression();

    /**
     * Setter for attribute 'BpmnComplexGateway.ActivationExpression'
     * 
     * Metamodel description:
     * <i>An activationExpression is a boolean Expression
     * that refers to data and to the activationCount of incoming gates. For example, an activationExpression
     * could be x1+x2+?+xm >= 3 stating that it needs 3 out of the m incoming gates to have a token in order to proceed. To
     * prevent undesirable oscillation of activation of the Complex Gateway, ActivationCount variables should only be
     * used in subexpressions of the form expr >= const where expr is an arithmetic Expression that uses only addition and
     * const is an Expression whose evaluation remains constant during execution of the Process.</i>
     */
    @objid ("40fc8886-8aa9-4ce1-9f50-44bc58e0f7ee")
    void setActivationExpression(String value);

    /**
     * Getter for relation 'BpmnComplexGateway->DefaultFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b656d014-80d4-4e4b-b7cc-914140d8cece")
    BpmnSequenceFlow getDefaultFlow();

    /**
     * Setter for relation 'BpmnComplexGateway->DefaultFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("87132cb0-e040-4f7a-86a0-0bf9ca309f74")
    void setDefaultFlow(BpmnSequenceFlow value);

}
