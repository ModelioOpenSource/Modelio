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
package org.modelio.metamodel.bpmn.bpmnService;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedElement;

/**
 * BpmnInterface v0.0.9054
 * 
 * 
 * <p>An Interface defines a set of operations that are implemented by Services.</p><p>The Interface inherits the attributes and model associations of BaseElement through its relationship&nbsp;to RootElement.--</p>
 */
@objid ("000de92c-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnInterface extends BpmnSharedElement {
    /**
     * The metaclass simple name.
     */
    @objid ("ecc10790-2c95-4355-9857-ebfd7a62785d")
    public static final String MNAME = "BpmnInterface";

    /**
     * The metaclass qualified name.
     */
    @objid ("d5031da5-9f7a-462c-bbb5-6f5ffdc0572f")
    public static final String MQNAME = "Standard.BpmnInterface";

    /**
     * Getter for relation 'BpmnInterface->Operation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("bdeb9cbd-4e2a-46bc-84c5-a9d11907b6d2")
    EList<BpmnOperation> getOperation();

    /**
     * Filtered Getter for relation 'BpmnInterface->Operation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("51579b7a-6edf-4a91-8063-705f8ade6e00")
    <T extends BpmnOperation> List<T> getOperation(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnInterface->ParticipantRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c34a139a-89fc-4889-8399-9db37cee09c6")
    EList<BpmnParticipant> getParticipantRef();

    /**
     * Filtered Getter for relation 'BpmnInterface->ParticipantRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("aa724bd1-6103-4201-b3e9-83f0c86206b2")
    <T extends BpmnParticipant> List<T> getParticipantRef(java.lang.Class<T> filterClass);

}
