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
import org.modelio.metamodel.bpmn.activities.BpmnComplexBehaviorDefinition;

/**
 * BpmnImplicitThrowEvent v0.0.9054
 * 
 * 
 * This is a non-graphical Event that this used for Multi-Instance Activities
 */
@objid ("008df234-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnImplicitThrowEvent extends BpmnThrowEvent {
    /**
     * The metaclass simple name.
     */
    @objid ("25b0951e-eefa-4bee-ae2d-c0e4d8233747")
    public static final String MNAME = "BpmnImplicitThrowEvent";

    /**
     * The metaclass qualified name.
     */
    @objid ("5d582a75-2a34-44ae-980c-5b5b4f1a1488")
    public static final String MQNAME = "Standard.BpmnImplicitThrowEvent";

    /**
     * Getter for relation 'BpmnImplicitThrowEvent->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0034ec98-9c51-4415-b9f9-43a75ccf3b7a")
    BpmnComplexBehaviorDefinition getOwner();

    /**
     * Setter for relation 'BpmnImplicitThrowEvent->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a713adfd-15e5-4b93-942b-1b2902371dc9")
    void setOwner(BpmnComplexBehaviorDefinition value);

}
