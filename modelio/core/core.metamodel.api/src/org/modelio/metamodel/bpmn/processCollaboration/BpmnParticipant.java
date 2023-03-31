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

package org.modelio.metamodel.bpmn.processCollaboration;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.bpmnService.BpmnEndPoint;
import org.modelio.metamodel.bpmn.bpmnService.BpmnInterface;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;

/**
 * BpmnParticipant v0.0.9054
 * 
 * 
 * A Participant represents a specific PartnerEntity (e.g., a company) and/or a more general PartnerRole (e.g., a buyer, seller, or manufacturer) that are Participants in a Collaboration. A Participant is often responsible for the execution of the Process enclosed in a Pool; however, a Pool may be defined without a Process.
 * 
 * 
 */
@objid ("007577d6-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnParticipant extends BpmnBaseElement {
    /**
     * The metaclass simple name.
     */
    @objid ("2163a1c1-ae40-4d62-95b2-1921e608bbe9")
    public static final String MNAME = "BpmnParticipant";

    /**
     * The metaclass qualified name.
     */
    @objid ("8ef829e4-67e8-40c9-a5cf-95b9575bbd74")
    public static final String MQNAME = "Standard.BpmnParticipant";

    /**
     * @return <code>true</code> if the workflow is local, i.e. it references a process belonging to the same {@link BpmnBehavior}
     * as the opened participant.
     * @since 3.7
     */
    @objid ("a464bad3-1d64-4181-83a1-2336e64cdde2")
    boolean isLocal();

    /**
     * Getter for attribute 'BpmnParticipant.MultiplicityMin'
     * 
     * Metamodel description:
     * <i>When Participants represent more than 1 instance, min and max are specified (1 - 1 by default)</i>
     * 
     */
    @objid ("a2a72968-d77b-41a0-ade7-5125601c3445")
    int getMultiplicityMin();

    /**
     * Setter for attribute 'BpmnParticipant.MultiplicityMin'
     * 
     * Metamodel description:
     * <i>When Participants represent more than 1 instance, min and max are specified (1 - 1 by default)</i>
     * 
     */
    @objid ("a9582f70-d8bc-42ad-be8f-8b62781a707b")
    void setMultiplicityMin(int value);

    /**
     * Getter for attribute 'BpmnParticipant.MultiplicityMax'
     * 
     * Metamodel description:
     * <i>When Participants represent more than 1 instance, min and max are specified (1 - 1 by default)</i>
     * 
     */
    @objid ("c11de0b7-fe27-4d24-82b9-2c3829f308b9")
    int getMultiplicityMax();

    /**
     * Setter for attribute 'BpmnParticipant.MultiplicityMax'
     * 
     * Metamodel description:
     * <i>When Participants represent more than 1 instance, min and max are specified (1 - 1 by default)</i>
     * 
     */
    @objid ("a1199bc5-fb38-469b-b2bd-1a18d9882b9f")
    void setMultiplicityMax(int value);

    /**
     * Getter for relation 'BpmnParticipant->Process'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("24cb54c9-f782-4802-bcf8-4c658aae7bf4")
    BpmnProcess getProcess();

    /**
     * Setter for relation 'BpmnParticipant->Process'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("8cecbae3-c780-4664-9743-e991c6651a9a")
    void setProcess(BpmnProcess value);

    /**
     * Getter for relation 'BpmnParticipant->Container'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("a07b9a00-f0b9-4c27-856c-15e215a7d3f7")
    BpmnCollaboration getContainer();

    /**
     * Setter for relation 'BpmnParticipant->Container'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("3aa867c8-3b9d-4aa7-b917-4b6f9d14db60")
    void setContainer(BpmnCollaboration value);

    /**
     * Getter for relation 'BpmnParticipant->EndPointRefs'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("d7495e3d-2dd1-4280-b374-1b7483646efb")
    EList<BpmnEndPoint> getEndPointRefs();

    /**
     * Filtered Getter for relation 'BpmnParticipant->EndPointRefs'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("acba07d9-87e1-4944-8f77-7eaf83e2d496")
    <T extends BpmnEndPoint> List<T> getEndPointRefs(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnParticipant->InterfaceRefs'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("f58b83f0-2834-41bd-a14d-1dbc1d831baf")
    EList<BpmnInterface> getInterfaceRefs();

    /**
     * Filtered Getter for relation 'BpmnParticipant->InterfaceRefs'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("daa18ac9-b8da-488e-9540-d492000c68b7")
    <T extends BpmnInterface> List<T> getInterfaceRefs(java.lang.Class<T> filterClass);
}

