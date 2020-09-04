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
package org.modelio.metamodel.bpmn.events;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;

/**
 * BpmnEventDefinition v0.0.9054
 * 
 * 
 * Defines the type of contained triggers expected for an Event
 */
@objid ("008d3f9c-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnEventDefinition extends BpmnBaseElement {
    /**
     * The metaclass simple name.
     */
    @objid ("4901844b-9b82-4974-ac30-eef4eaa38067")
    public static final String MNAME = "BpmnEventDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("4371b45e-e5dc-42ff-a0f8-29d178f38da8")
    public static final String MQNAME = "Standard.BpmnEventDefinition";

    /**
     * Getter for relation 'BpmnEventDefinition->Defined'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5d6425af-9445-4ca3-b8ba-67abccad58e6")
    BpmnEvent getDefined();

    /**
     * Setter for relation 'BpmnEventDefinition->Defined'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("884edc2f-e7f7-4e37-876f-4de30db7b25d")
    void setDefined(BpmnEvent value);

    /**
     * Getter for relation 'BpmnEventDefinition->LoopRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("292d798d-8a48-409b-a703-8096c4215d45")
    EList<BpmnMultiInstanceLoopCharacteristics> getLoopRef();

    /**
     * Filtered Getter for relation 'BpmnEventDefinition->LoopRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9ef5da23-0870-472b-8d34-98d22f8ae1db")
    <T extends BpmnMultiInstanceLoopCharacteristics> List<T> getLoopRef(java.lang.Class<T> filterClass);

}
