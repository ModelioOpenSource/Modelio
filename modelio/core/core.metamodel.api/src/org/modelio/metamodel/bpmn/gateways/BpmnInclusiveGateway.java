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
 * BpmnInclusiveGateway v0.0.9054
 * 
 * 
 * A diverging Inclusive Gateway (Inclusive Decision) can be used to create alternative but also parallel paths within a Process flow. Unlike the Exclusive Gateway, all condition Expressions are evaluated. The true evaluation of one condition Expression does not exclude the evaluation of other condition Expressions. All Sequence Flow with a true evaluation will be traversed by a token. Since each path is considered to be independent, all combinations of the paths may be taken, from zero to all. However, it should be designed so that at least one path is taken.
 */
@objid ("00019a82-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnInclusiveGateway extends BpmnGateway {
    /**
     * The metaclass simple name.
     */
    @objid ("6b5251a2-b5de-49ee-b71b-ec338f8df500")
    public static final String MNAME = "BpmnInclusiveGateway";

    /**
     * The metaclass qualified name.
     */
    @objid ("c968cc57-40d8-4fe7-a958-91add48e458f")
    public static final String MQNAME = "Standard.BpmnInclusiveGateway";

    /**
     * Getter for relation 'BpmnInclusiveGateway->DefaultFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e2ff77f2-b7ca-462e-aca8-0d03af420168")
    BpmnSequenceFlow getDefaultFlow();

    /**
     * Setter for relation 'BpmnInclusiveGateway->DefaultFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("43242af2-6a3f-4d2b-964a-42a15edb37e4")
    void setDefaultFlow(BpmnSequenceFlow value);

}
