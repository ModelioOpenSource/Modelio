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
package org.modelio.metamodel.bpmn.activities;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.events.BpmnImplicitThrowEvent;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;

/**
 * BpmnComplexBehaviorDefinition v0.0.9054
 * 
 * 
 * This element controls when and which Events are thrown in case behavior of the Multi-Instance Activity is set to complex.
 */
@objid ("007f7bdc-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnComplexBehaviorDefinition extends BpmnBaseElement {
    /**
     * The metaclass simple name.
     */
    @objid ("f272e3fd-ff7e-43fd-b2a2-ceb06327efd4")
    public static final String MNAME = "BpmnComplexBehaviorDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("0c340198-776a-4472-b145-01b9e3d076c5")
    public static final String MQNAME = "Standard.BpmnComplexBehaviorDefinition";

    /**
     * Getter for attribute 'BpmnComplexBehaviorDefinition.Condition'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("54c9c31d-994b-4994-baf3-4669562ed284")
    String getCondition();

    /**
     * Setter for attribute 'BpmnComplexBehaviorDefinition.Condition'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("171c71b7-a5e8-4c5a-a7bd-52b7184f7142")
    void setCondition(String value);

    /**
     * Getter for relation 'BpmnComplexBehaviorDefinition->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c9efaab1-6cb1-4a17-a87d-18b328740e4c")
    BpmnMultiInstanceLoopCharacteristics getOwner();

    /**
     * Setter for relation 'BpmnComplexBehaviorDefinition->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("135ff49e-7c01-457a-a727-d346659a3156")
    void setOwner(BpmnMultiInstanceLoopCharacteristics value);

    /**
     * Getter for relation 'BpmnComplexBehaviorDefinition->Event'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e1686bd9-3f4e-4466-a1e9-94baa26e6bef")
    BpmnImplicitThrowEvent getEvent();

    /**
     * Setter for relation 'BpmnComplexBehaviorDefinition->Event'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("190cc26a-320e-4388-93a7-cbad64fc65d8")
    void setEvent(BpmnImplicitThrowEvent value);

}
