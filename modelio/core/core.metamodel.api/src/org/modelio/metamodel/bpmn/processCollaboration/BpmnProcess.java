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
import org.modelio.metamodel.bpmn.resources.BpmnResourceRole;
import org.modelio.metamodel.bpmn.rootElements.BpmnArtifact;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;

/**
 * BpmnProcess v2.2.0
 * 
 * 
 * <p>A Process describes a sequence or flow of Activities in an organization with the objective of carrying out work.</p><p>In BPMN a Process is depicted as a graph of Flow Elements, which are a set of Activities, Events, Gateways, and Sequence Flow that define finite execution semantics.</p><p>Processes may be defined at any level from enterprise-wide Processes to Processes performed by a single person. Low-level Processes may be grouped together to achieve a common business goal.</p><p>Note that BPMN uses the term Process specifically to mean a set of flow elements. It uses the terms Collaboration and Choreography when modeling the interaction between</p><p>Processes Ownership: In Modelio, a&nbsp;Process is a Behavior so&nbsp;belongs to any UML element that can contain a Behavior. BPMN Process will usually be created in UML Packages, but may also be found to a Class or an Operation.</p>
 */
@objid ("00762104-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnProcess extends Behavior {
    /**
     * The metaclass simple name.
     */
    @objid ("50fb4ee7-7bce-4fd7-8977-4365a7306ba2")
    public static final String MNAME = "BpmnProcess";

    /**
     * The metaclass qualified name.
     */
    @objid ("5f097d11-f53e-4a60-a122-e83ccec373e2")
    public static final String MQNAME = "Standard.BpmnProcess";

    /**
     * Getter for attribute 'BpmnProcess.ProcessType'
     * 
     * Metamodel description:
     * <i><p>The <em>processType</em> attribute Provides additional information about the level of abstraction modeled by this Process.</p><p>A <em>public</em> Process shows only those flow elements that are relevant to&nbsp;external consumers. Internal details are not modeled. These&nbsp;Processes are publicly visible and can be used within a&nbsp;Collaboration . Note that the public processType was named&nbsp;abstract in BPMN 1.2 .</p><p>A <em>private</em> Process is one that is internal to a specific organization.</p><p>By default, the processType is &quot;<em>none</em>&quot;,&nbsp;meaning undefined.</p>
     * </i>
     */
    @objid ("fcff9bb8-f7bd-414c-8750-dcbde3efd409")
    BpmnProcessType getProcessType();

    /**
     * Setter for attribute 'BpmnProcess.ProcessType'
     * 
     * Metamodel description:
     * <i><p>The <em>processType</em> attribute Provides additional information about the level of abstraction modeled by this Process.</p><p>A <em>public</em> Process shows only those flow elements that are relevant to&nbsp;external consumers. Internal details are not modeled. These&nbsp;Processes are publicly visible and can be used within a&nbsp;Collaboration . Note that the public processType was named&nbsp;abstract in BPMN 1.2 .</p><p>A <em>private</em> Process is one that is internal to a specific organization.</p><p>By default, the processType is &quot;<em>none</em>&quot;,&nbsp;meaning undefined.</p>
     * </i>
     */
    @objid ("7a8b5f40-c1e1-4301-a247-41024d1d4561")
    void setProcessType(BpmnProcessType value);

    /**
     * Getter for attribute 'BpmnProcess.IsClosed'
     * 
     * Metamodel description:
     * <i><p>A boolean value specifying whether interactions, such as sending and receiving Messages and Events, not modeled in the Process can occur when the Process is executed or performed.</p><p>If the value is <em>true</em>, they MAY NOT occur. If the value is <em>false</em>, they MAY occur.</p>
     * </i>
     */
    @objid ("996256dc-8918-46af-93cf-359abc1db670")
    boolean isIsClosed();

    /**
     * Setter for attribute 'BpmnProcess.IsClosed'
     * 
     * Metamodel description:
     * <i><p>A boolean value specifying whether interactions, such as sending and receiving Messages and Events, not modeled in the Process can occur when the Process is executed or performed.</p><p>If the value is <em>true</em>, they MAY NOT occur. If the value is <em>false</em>, they MAY occur.</p>
     * </i>
     */
    @objid ("61d6d776-8a2e-430e-8617-ec565372da68")
    void setIsClosed(boolean value);

    /**
     * Getter for attribute 'BpmnProcess.IsExecutable'
     * 
     * Metamodel description:
     * <i><p>An optional Boolean value specifying whether the Process is executable.</p><p>An executable Process is a private Process that has been modeled for the purpose of being executed according to the semantics of Chapter 14 (see page 442). Of course, during the development cycle of the Process, there will be stages where the Process does not have enough detail to be &#39;executable.&#39;</p><p>A non-executable Process is a private Process that has been modeled for the purpose of documenting Process behavior at a modeler-defined level of detail. Thus, information needed for execution, such as formal condition expressions are typically not included in a non-executable Process.</p><p>For public Processes, no value has the same semantics as if the value were <em>false</em>. The value MAY not be <em>true</em> for public Processes.</p>
     * </i>
     */
    @objid ("c1a3b2a8-4e56-467a-9e35-258ec997df69")
    OptionalBoolean getIsExecutable();

    /**
     * Setter for attribute 'BpmnProcess.IsExecutable'
     * 
     * Metamodel description:
     * <i><p>An optional Boolean value specifying whether the Process is executable.</p><p>An executable Process is a private Process that has been modeled for the purpose of being executed according to the semantics of Chapter 14 (see page 442). Of course, during the development cycle of the Process, there will be stages where the Process does not have enough detail to be &#39;executable.&#39;</p><p>A non-executable Process is a private Process that has been modeled for the purpose of documenting Process behavior at a modeler-defined level of detail. Thus, information needed for execution, such as formal condition expressions are typically not included in a non-executable Process.</p><p>For public Processes, no value has the same semantics as if the value were <em>false</em>. The value MAY not be <em>true</em> for public Processes.</p>
     * </i>
     */
    @objid ("6b22c0a2-c8a4-4a8a-bac2-1a333d6d8a8f")
    void setIsExecutable(OptionalBoolean value);

    /**
     * Getter for relation 'BpmnProcess->Supports'
     * 
     * Metamodel description:
     * <i>Modelers can declare that they intend all executions or performances of one Process to also be valid for another Process. This means they expect all the executions or performances of the first Processes to also follow the steps laid out in the second Process.</i>
     */
    @objid ("7b538e0c-ccab-4e63-8527-3e432946322f")
    EList<BpmnProcess> getSupports();

    /**
     * Filtered Getter for relation 'BpmnProcess->Supports'
     * 
     * Metamodel description:
     * <i>Modelers can declare that they intend all executions or performances of one Process to also be valid for another Process. This means they expect all the executions or performances of the first Processes to also follow the steps laid out in the second Process.</i>
     */
    @objid ("3f390b2b-ed76-454d-83a2-378bcd101069")
    <T extends BpmnProcess> List<T> getSupports(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnProcess->Artifact'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ccd69cca-7768-4653-9e10-936001c0fb71")
    EList<BpmnArtifact> getArtifact();

    /**
     * Filtered Getter for relation 'BpmnProcess->Artifact'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b4c588c9-2096-4b02-ae04-5976ade3214b")
    <T extends BpmnArtifact> List<T> getArtifact(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnProcess->LaneSet'
     * 
     * Metamodel description:
     * <i>laneset of the process. The process is represented in this cas by a pool which is decomposed by lansets and lanes.</i>
     */
    @objid ("44ad1083-0c10-487c-b0ca-8e6a2cacaeea")
    BpmnLaneSet getLaneSet();

    /**
     * Setter for relation 'BpmnProcess->LaneSet'
     * 
     * Metamodel description:
     * <i>laneset of the process. The process is represented in this cas by a pool which is decomposed by lansets and lanes.</i>
     */
    @objid ("565b214c-5768-4666-a557-bd675b61de46")
    void setLaneSet(BpmnLaneSet value);

    /**
     * Getter for relation 'BpmnProcess->Supported'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a2b4003a-5f42-46e4-9bf7-08e443dc45b3")
    EList<BpmnProcess> getSupported();

    /**
     * Filtered Getter for relation 'BpmnProcess->Supported'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("88ea4882-3f1b-4891-b70e-35eaaee6a724")
    <T extends BpmnProcess> List<T> getSupported(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnProcess->Participant'
     * 
     * Metamodel description:
     * <i>When a participant is decomposed by a process, both are related by this association.</i>
     */
    @objid ("00628219-948e-4952-98eb-d32b5c986b33")
    EList<BpmnParticipant> getParticipant();

    /**
     * Filtered Getter for relation 'BpmnProcess->Participant'
     * 
     * Metamodel description:
     * <i>When a participant is decomposed by a process, both are related by this association.</i>
     */
    @objid ("a0c1f70d-bdce-4cb8-9fdd-8248c8f94d6f")
    <T extends BpmnParticipant> List<T> getParticipant(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnProcess->FlowElement'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("90b349f5-bfaa-4c2c-bbd5-ec3b32298f39")
    EList<BpmnFlowElement> getFlowElement();

    /**
     * Filtered Getter for relation 'BpmnProcess->FlowElement'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5f09b1c9-21ff-43f2-ac4a-ca84ae49809e")
    <T extends BpmnFlowElement> List<T> getFlowElement(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnProcess->Resource'
     * 
     * Metamodel description:
     * <i>Resources attached to the process, such as auditing or monitoring. These resources can be defined externally, in particular by the user, in order to attach descritpion properties to a process.</i>
     */
    @objid ("72186396-81d6-4f6c-96a5-46da85efe927")
    EList<BpmnResourceRole> getResource();

    /**
     * Filtered Getter for relation 'BpmnProcess->Resource'
     * 
     * Metamodel description:
     * <i>Resources attached to the process, such as auditing or monitoring. These resources can be defined externally, in particular by the user, in order to attach descritpion properties to a process.</i>
     */
    @objid ("bafb7e39-e419-4164-b862-65bb36cacc31")
    <T extends BpmnResourceRole> List<T> getResource(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnProcess->DefinitionalCollaboration'
     * 
     * Metamodel description:
     * <i><p>For <strong>Processes</strong> that interact with other <strong>Participants</strong>, a <strong>definitional&nbsp;Collaboration</strong> can be referenced by the <strong>Process</strong>. The <strong>definitional</strong>&nbsp;<strong>Collaboration</strong> specifies the <strong>Participants</strong> the Process interacts with,&nbsp;and more specifically, which individual service, Send or Receive <strong>Task</strong>,&nbsp;or <strong>Message Event</strong>, is connected to which <strong>Participant</strong> through&nbsp;<strong>Message Flows</strong>.</p><p>The <strong>definitional Collaboration</strong> need not be&nbsp;displayed.</p><p>Additionally, the definitional Collaboration can be used to include&nbsp;Conversation information within a Process.</p>
     * </i>
     */
    @objid ("fb6447ef-511b-49f2-9641-770905c02cf4")
    BpmnCollaboration getDefinitionalCollaboration();

    /**
     * Setter for relation 'BpmnProcess->DefinitionalCollaboration'
     * 
     * Metamodel description:
     * <i><p>For <strong>Processes</strong> that interact with other <strong>Participants</strong>, a <strong>definitional&nbsp;Collaboration</strong> can be referenced by the <strong>Process</strong>. The <strong>definitional</strong>&nbsp;<strong>Collaboration</strong> specifies the <strong>Participants</strong> the Process interacts with,&nbsp;and more specifically, which individual service, Send or Receive <strong>Task</strong>,&nbsp;or <strong>Message Event</strong>, is connected to which <strong>Participant</strong> through&nbsp;<strong>Message Flows</strong>.</p><p>The <strong>definitional Collaboration</strong> need not be&nbsp;displayed.</p><p>Additionally, the definitional Collaboration can be used to include&nbsp;Conversation information within a Process.</p>
     * </i>
     */
    @objid ("b37882e9-a597-4a82-a1a0-99183c0266d2")
    void setDefinitionalCollaboration(BpmnCollaboration value);

}
