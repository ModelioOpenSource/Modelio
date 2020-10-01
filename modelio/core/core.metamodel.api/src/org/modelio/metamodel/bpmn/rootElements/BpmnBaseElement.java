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
package org.modelio.metamodel.bpmn.rootElements;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.rootElements.BpmnAssociation;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * BpmnBaseElement v0.0.9054
 * 
 * 
 * BaseElement is the abstract super class for most BPMN elements. 
 */
@objid ("0077d2a6-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnBaseElement extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("328c7e4e-5793-4366-b02d-92d2a12883fe")
    public static final String MNAME = "BpmnBaseElement";

    /**
     * The metaclass qualified name.
     */
    @objid ("6dce2ead-fb0a-493a-adb2-f381579a95f5")
    public static final String MQNAME = "Standard.BpmnBaseElement";

    /**
     * @return the first non BPMN element in the upward model composition tree.
     * @since 3.7
     */
    @objid ("d081356e-12cd-4d66-aff2-fab9f0cfcff4")
    MObject getBpmnContext();

    /**
     * Getter for relation 'BpmnBaseElement->OutgoingAssoc'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9ca660c0-a724-41dc-aa68-6b398924c141")
    EList<BpmnAssociation> getOutgoingAssoc();

    /**
     * Filtered Getter for relation 'BpmnBaseElement->OutgoingAssoc'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("70d13409-dc17-4ee7-9d71-459545d20dd3")
    <T extends BpmnAssociation> List<T> getOutgoingAssoc(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnBaseElement->IncomingAssoc'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("71dbddcb-a105-46a2-8636-4334e719fb92")
    EList<BpmnAssociation> getIncomingAssoc();

    /**
     * Filtered Getter for relation 'BpmnBaseElement->IncomingAssoc'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8a8a8339-0757-437d-a52d-0a3b01f2c05d")
    <T extends BpmnAssociation> List<T> getIncomingAssoc(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnBaseElement->IncomingFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1f8a4482-b77a-437b-a87f-b6564ed776ae")
    EList<BpmnMessageFlow> getIncomingFlow();

    /**
     * Filtered Getter for relation 'BpmnBaseElement->IncomingFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b7fdfc9d-b90f-478b-ba5a-8d1b26c653f0")
    <T extends BpmnMessageFlow> List<T> getIncomingFlow(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnBaseElement->OutgoingFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("283201cc-02e9-4baa-ac93-d1faa4921e82")
    EList<BpmnMessageFlow> getOutgoingFlow();

    /**
     * Filtered Getter for relation 'BpmnBaseElement->OutgoingFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2d3da1a9-1332-4e74-8ecc-080ee919d0a7")
    <T extends BpmnMessageFlow> List<T> getOutgoingFlow(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnBaseElement->PartitionedLaneRefs'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6d3495bf-6710-421f-a834-3259b629aa5b")
    EList<BpmnLane> getPartitionedLaneRefs();

    /**
     * Filtered Getter for relation 'BpmnBaseElement->PartitionedLaneRefs'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("fdd28441-700c-469c-bf0f-14c8f801891e")
    <T extends BpmnLane> List<T> getPartitionedLaneRefs(java.lang.Class<T> filterClass);

}
