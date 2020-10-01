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
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnArtifact;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;

/**
 * BpmnCollaboration v2.2.00
 * 
 * 
 * The Collaboration package contains classes which are used for modeling Collaborations, which is a collection of Participants shown as Pools, their interactions as shown by Message Flow, and may include Processes within the Pools and/or Choreographies between the Pools.
 * 
 * Ownership:
 * Collaborations/Processes are structured under UML Packages or Classifiers or operations.
 */
@objid ("0073c9fe-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnCollaboration extends Behavior {
    /**
     * The metaclass simple name.
     */
    @objid ("1830016b-3c2e-49c3-a84a-1ceac547f163")
    public static final String MNAME = "BpmnCollaboration";

    /**
     * The metaclass qualified name.
     */
    @objid ("41cdf62a-c568-43cd-a1a8-b9a535583ab3")
    public static final String MQNAME = "Standard.BpmnCollaboration";

    /**
     * Getter for attribute 'BpmnCollaboration.IsClosed'
     * 
     * Metamodel description:
     * <i>A boolean value specifying whether Message Flow not modeled in the Collaboration can occur when the Collaboration is carried out.
     * ? If the value is true, they MAY NOT occur.
     * ? If the value is false, they MAY occur.</i>
     */
    @objid ("e0e88aa4-4c8c-4dd0-90bb-7df9712edce3")
    boolean isIsClosed();

    /**
     * Setter for attribute 'BpmnCollaboration.IsClosed'
     * 
     * Metamodel description:
     * <i>A boolean value specifying whether Message Flow not modeled in the Collaboration can occur when the Collaboration is carried out.
     * ? If the value is true, they MAY NOT occur.
     * ? If the value is false, they MAY occur.</i>
     */
    @objid ("033efae4-a9d9-4399-a805-d0d7003591f9")
    void setIsClosed(boolean value);

    /**
     * Getter for relation 'BpmnCollaboration->Artifact'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("529d1b67-1469-4a9f-96ba-1f1930f6e75d")
    EList<BpmnArtifact> getArtifact();

    /**
     * Filtered Getter for relation 'BpmnCollaboration->Artifact'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d0a8b543-1766-4221-8612-352b522f64ea")
    <T extends BpmnArtifact> List<T> getArtifact(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnCollaboration->MessageFlow'
     * 
     * Metamodel description:
     * <i>This provides the list of Message Flow that are used in the
     * Collaboration.</i>
     */
    @objid ("6c7aeca7-88dc-42ae-9829-5d54fe696708")
    EList<BpmnMessageFlow> getMessageFlow();

    /**
     * Filtered Getter for relation 'BpmnCollaboration->MessageFlow'
     * 
     * Metamodel description:
     * <i>This provides the list of Message Flow that are used in the
     * Collaboration.</i>
     */
    @objid ("d0739dd1-f0e7-4c7e-a212-06c9c2cf6ad2")
    <T extends BpmnMessageFlow> List<T> getMessageFlow(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnCollaboration->Participants'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("37e743ff-38a0-477c-8459-e359c85336b9")
    EList<BpmnParticipant> getParticipants();

    /**
     * Filtered Getter for relation 'BpmnCollaboration->Participants'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d365f132-c1cd-41b9-ae53-840f304a97c6")
    <T extends BpmnParticipant> List<T> getParticipants(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnCollaboration->Messages'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("adecdfc7-7925-46b8-b65e-add8ed7ec17c")
    EList<BpmnMessage> getMessages();

    /**
     * Filtered Getter for relation 'BpmnCollaboration->Messages'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b32a6a89-ec9f-49a5-96f7-6fdd6089c377")
    <T extends BpmnMessage> List<T> getMessages(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnCollaboration->DefinedProcess'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a10c0b97-5ef1-4537-a95b-8b2b1a35f9b6")
    BpmnProcess getDefinedProcess();

    /**
     * Setter for relation 'BpmnCollaboration->DefinedProcess'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9579f47d-1a58-4768-87b6-4522c936b4bc")
    void setDefinedProcess(BpmnProcess value);

}
