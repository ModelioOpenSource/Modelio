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

package org.modelio.metamodel.impl.mmextensions.standard.migration.from_bpmn_36;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel;
import org.modelio.vcore.smkernel.meta.mof.MofSmClass;

@objid ("8aea5926-17d3-47b6-8400-6eeca7a60340")
class MM {
    /**
     * {@link BpmnBehavior#MQNAME}
     */
    @objid ("c439eef1-a673-4805-bd65-f7c6851e8ba8")
    private static final String MC_BPMN_BEHAVIOR = "Standard.BpmnBehavior";

    @objid ("863af136-6d39-48e1-9eed-0cfac37385ab")
    private static final String MC_BPMN_COLLABO = "Standard.BpmnCollaboration";

    @objid ("d37412dd-df5c-4ce1-8b4a-c3b914361cf2")
    private static final String MC_BPMN_MESSAGE = "Standard.BpmnMessage";

    @objid ("89a746b1-9299-4bde-8276-e40c66582a8e")
    private static final String MC_BPMN_MESSAGEFLOW = "Standard.BpmnMessageFlow";

    @objid ("03311cb8-2e64-4d3e-82b9-4c96d4f4bbe9")
    private static final String MC_BPMN_PARTICIPANT = "Standard.BpmnParticipant";

    @objid ("374edf06-ccd9-4780-9caa-21bc2f9cc4b6")
    private static final String MC_BPMN_PROCESS = "Standard.BpmnProcess";

    @objid ("d38b2b30-4a04-4b4d-9858-e5025cf9b347")
    public final MClass bpmnActivityMC;

    @objid ("16e06839-e1e3-4099-8ade-c8fe86312c63")
    public final SmClass bpmnBehaviorMClass;

    @objid ("4d7c5d10-704b-4a92-988d-afcae7558985")
    public final MClass bpmnBoundaryEventMC;

    @objid ("a228d382-3eeb-477e-9321-b813752bfb68")
    public final SmClass bpmnCollaboMC;

    @objid ("7bfee6fd-3e12-4877-97f7-2517eeacf4ac")
    public final MofSmClass bpmnCollaborationDiagramMC;

    @objid ("f9c7f5d9-2137-4a94-a1f4-9c2aa4c73afa")
    public final MofSmClass bpmnDataAssociationMC;

    @objid ("9b5fdb0f-4fb2-485f-8b8e-12bf0f5a2c34")
    public final MClass bpmnFlowNodeMC;

    @objid ("d53a8f79-091e-4f54-af9a-fbe33690b5a0")
    public final MClass bpmnItemAwareElementMC;

    @objid ("349c06ae-b74c-49d2-a4ec-365bca5ad079")
    public final SmClass bpmnLaneMC;

    @objid ("86d46dc5-041b-4b80-b433-a56405f91bb5")
    public final SmClass bpmnLaneSetMC;

    @objid ("257ccb27-08c6-4114-a43a-b296c3423fee")
    public final SmClass bpmnMessageFlowMC;

    @objid ("9457142a-c3d2-42d5-9518-9ad3300caeb6")
    public final SmClass bpmnMessageMC;

    @objid ("feab572a-2921-4418-8848-f0839b834799")
    public final MofSmClass bpmnProcessCollaborationDiagramMC;

    @objid ("73c2762d-bba5-4431-9b7b-2726c16df5e8")
    public final MofSmClass bpmnProcessDesignDiagramMC;

    @objid ("a0cf55ae-d981-4943-a8f9-b1e3e82e387f")
    public final MClass bpmnSequenceFlowMC;

    @objid ("47488db7-bb1f-464f-9e80-2e8b20bc615f")
    public final SmClass bpmnSubProcessMC;

    @objid ("fc2893d6-5825-4a21-934a-6514ae505e56")
    public final MDependency flowElementDep;

    @objid ("369254b0-e8e6-4719-898d-ef31c3fc4036")
    public final MDependency participantDep;

    @objid ("302bc5c2-a017-499e-968a-da7b0774b451")
    public final MofSmClass participantMC;

    @objid ("1f4a2a5e-32c3-4488-a415-7ea4e00d36b1")
    public final SmClass processMclass;

    @objid ("26f2288e-1b56-42a8-8a54-09a3db09fe71")
    public final SmClass umlClassifierMC;

    @objid ("07d0d383-0ad1-4c15-a7e5-770be3fc7c2e")
    public final SmClass umlPackageMC;

    @objid ("f2a94b82-ce16-43f5-bc23-1f5966ba09ad")
    public final SmClass bpmnBaseElementMc;

    @objid ("b08dcf84-7782-4836-8139-11570bded790")
    public MM(MofMetamodel metamodel) {
        // Get pointers to used metaclasses
        this.processMclass = requireMClass(metamodel, MM.MC_BPMN_PROCESS);
        this.bpmnCollaboMC = requireMClass(metamodel, MM.MC_BPMN_COLLABO);
        this.bpmnMessageMC = requireMClass(metamodel, MM.MC_BPMN_MESSAGE);
        this.bpmnMessageFlowMC = requireMClass(metamodel, MM.MC_BPMN_MESSAGEFLOW);
        this.bpmnBehaviorMClass = requireMClass(metamodel, MM.MC_BPMN_BEHAVIOR);
        this.participantMC = requireMClass(metamodel, MM.MC_BPMN_PARTICIPANT);
        
        this.bpmnProcessCollaborationDiagramMC = requireMClass(metamodel, "Standard.BpmnProcessCollaborationDiagram");
        this.bpmnProcessDesignDiagramMC = requireMClass(metamodel, "Standard.BpmnProcessDesignDiagram");
        this.bpmnCollaborationDiagramMC = requireMClass(metamodel, "Standard.BpmnCollaborationDiagram");
        
        this.bpmnBaseElementMc = requireMClass(metamodel, "Standard.BpmnBaseElement");
        this.bpmnFlowNodeMC = requireMClass(metamodel, "Standard.BpmnFlowNode");
        this.bpmnItemAwareElementMC = requireMClass(metamodel, "Standard.BpmnItemAwareElement");
        this.bpmnSequenceFlowMC = requireMClass(metamodel, "Standard.BpmnSequenceFlow");
        this.bpmnBoundaryEventMC = requireMClass(metamodel, "Standard.BpmnBoundaryEvent");
        this.bpmnActivityMC = requireMClass(metamodel, "Standard.BpmnActivity");
        this.bpmnLaneSetMC = requireMClass(metamodel, "Standard.BpmnLaneSet");
        this.bpmnLaneMC = requireMClass(metamodel, "Standard.BpmnLane");
        this.bpmnSubProcessMC = requireMClass(metamodel, "Standard.BpmnSubProcess");
        this.bpmnDataAssociationMC = requireMClass(metamodel, "Standard.BpmnDataAssociation");
        
        this.umlClassifierMC = requireMClass(metamodel, "Standard.Classifier");
        this.umlPackageMC = requireMClass(metamodel, "Standard.Package");
        
        this.participantDep = this.processMclass.getDependency("Participant");
        this.flowElementDep = this.processMclass.getDependency("FlowElement");
        
        assert (this.processMclass.getDependency("LaneSet") != null);
        assert (this.participantDep != null);
    }

    @objid ("f980c56b-aab6-41e0-92a8-968f061b7866")
    @SuppressWarnings ("unchecked")
    private static <T extends SmClass> T requireMClass(MofMetamodel metamodel, String name) {
        return (T) Objects.requireNonNull(metamodel.getMClass(name), name);
    }

}
