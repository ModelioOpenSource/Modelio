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

package org.modelio.diagram.editor.bpmn.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.modelio.diagram.editor.bpmn.elements.bpmnactivity.BpmnPortContainerEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnadhocsubprocess.BpmnAdHocSubProcessEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnadhocsubprocess.GmBpmnAdHocSubProcess;
import org.modelio.diagram.editor.bpmn.elements.bpmnadhocsubprocess.GmBpmnAdHocSubProcessPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnboundaryevent.BpmnBoundaryEventPrimaryNodeEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnboundaryevent.GmBpmnBoundaryEvent;
import org.modelio.diagram.editor.bpmn.elements.bpmnboundaryevent.GmBpmnBoundaryEventPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnbusinessruletask.BpmnBusinessRuleTaskEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnbusinessruletask.GmBpmnBusinessRuleTask;
import org.modelio.diagram.editor.bpmn.elements.bpmnbusinessruletask.GmBpmnBusinessRuleTaskPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmncallactivity.BpmnCallActivityEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmncallactivity.BpmnCallActivityHeaderEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmncallactivity.GmBpmnCallActivity;
import org.modelio.diagram.editor.bpmn.elements.bpmncallactivity.GmBpmnCallActivityHeader;
import org.modelio.diagram.editor.bpmn.elements.bpmncallactivity.GmBpmnCallActivityPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmncomplexgateway.BpmnComplexGatewayEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmncomplexgateway.GmBpmnComplexGateway;
import org.modelio.diagram.editor.bpmn.elements.bpmncomplexgateway.GmBpmnComplexGatewayPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmndataassociation.BpmnDataAssociationEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmndataassociation.GmBpmnDataAssociation;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.GmBpmnDataLabel;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.datainput.BpmnDataInputEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.datainput.GmBpmnDataInput;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.datainput.GmBpmnDataInputLabel;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.datainput.GmBpmnDataInputPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.dataobject.BpmnDataObjectEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.dataobject.GmBpmnDataObject;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.dataobject.GmBpmnDataObjectPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.dataoutput.BpmnDataOutputEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.dataoutput.GmBpmnDataOutput;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.dataoutput.GmBpmnDataOutputLabel;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.dataoutput.GmBpmnDataOutputPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.datastore.BpmnDataStoreEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.datastore.BpmnDataStoreSimpleEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.datastore.GmBpmnDataStore;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.datastore.GmBpmnDataStorePrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnendevent.BpmnEndEventPrimaryNodeEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnendevent.GmBpmnEndEvent;
import org.modelio.diagram.editor.bpmn.elements.bpmnendevent.GmBpmnEndEventPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmneventbasedgateway.BpmnEventBasedGatewayEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmneventbasedgateway.GmBpmnEventBasedGateway;
import org.modelio.diagram.editor.bpmn.elements.bpmneventbasedgateway.GmBpmnEventBasedGatewayPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnexclusivegateway.BpmnExclusiveGatewayEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnexclusivegateway.GmBpmnExclusiveGateway;
import org.modelio.diagram.editor.bpmn.elements.bpmnexclusivegateway.GmBpmnExclusiveGatewayPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmninclusivegateway.BpmnInclusiveGatewayEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmninclusivegateway.GmBpmnInclusiveGateway;
import org.modelio.diagram.editor.bpmn.elements.bpmninclusivegateway.GmBpmnInclusiveGatewayPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnintermediatecatchevent.BpmnIntermediateCatchEventEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnintermediatecatchevent.BpmnIntermediateCatchEventPrimaryNodeEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnintermediatecatchevent.GmBpmnIntermediateCatchEvent;
import org.modelio.diagram.editor.bpmn.elements.bpmnintermediatecatchevent.GmBpmnIntermediateCatchEventPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnintermediatethrowevent.BpmnIntermediateThrowEventEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnintermediatethrowevent.BpmnIntermediateThrowEventPrimaryNodeEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnintermediatethrowevent.GmBpmnIntermediateThrowEvent;
import org.modelio.diagram.editor.bpmn.elements.bpmnintermediatethrowevent.GmBpmnIntermediateThrowEventPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.BpmnLaneEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.GmBpmnLane;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.header.BpmnLaneHeaderEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.header.GmBpmnLaneHeader;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.hibridcontainer.BodyHybridContainerEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.hibridcontainer.GmBodyHybridContainer;
import org.modelio.diagram.editor.bpmn.elements.bpmnlanesetcontainer.BpmnLaneSetContainerEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnlanesetcontainer.GmBpmnLaneSetContainer;
import org.modelio.diagram.editor.bpmn.elements.bpmnmanualtask.BpmnManualTaskEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnmanualtask.GmBpmnManualTask;
import org.modelio.diagram.editor.bpmn.elements.bpmnmanualtask.GmBpmnManualTaskPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessage.BpmnMessageEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessage.BpmnMessageLinkEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessage.BpmnMessageSimpleEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessage.GmBpmnMessage;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessage.GmBpmnMessageLabel;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessage.GmBpmnMessageLink;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessage.GmBpmnMessagePrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessageflow.BpmnMessageFlowEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessageflow.GmBpmnMessageFlow;
import org.modelio.diagram.editor.bpmn.elements.bpmnnodefooter.BpmnNodeFooterEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnnodefooter.GmBpmnNodeFooter;
import org.modelio.diagram.editor.bpmn.elements.bpmnnodeheader.BpmnNodeHeaderEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnnodeheader.GmBpmnNodeHeader;
import org.modelio.diagram.editor.bpmn.elements.bpmnparallelgateway.BpmnParallelGatewayEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnparallelgateway.GmBpmnParallelGateway;
import org.modelio.diagram.editor.bpmn.elements.bpmnparallelgateway.GmBpmnParallelGatewayPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnreceivetask.BpmnReceiveTaskEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnreceivetask.GmBpmnReceiveTask;
import org.modelio.diagram.editor.bpmn.elements.bpmnreceivetask.GmBpmnReceiveTaskPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnsendtask.BpmnSendTaskEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnsendtask.GmBpmnSendTask;
import org.modelio.diagram.editor.bpmn.elements.bpmnsendtask.GmBpmnSendTaskPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflow.BpmnSequenceFlowEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflow.GmBpmnEdgeGuard;
import org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflow.GmBpmnSequenceFlow;
import org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflowdataassociation.BpmnSequenceFlowDataAssociationEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflowdataassociation.GmBpmnSequenceFlowDataAssociation;
import org.modelio.diagram.editor.bpmn.elements.bpmnservicetask.BpmnServiceTaskEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnservicetask.GmBpmnServiceTask;
import org.modelio.diagram.editor.bpmn.elements.bpmnservicetask.GmBpmnServiceTaskPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnservicetask.GmBpmnServiceTaskTypeLabel;
import org.modelio.diagram.editor.bpmn.elements.bpmnsripttask.BpmnScriptTaskEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnsripttask.GmBpmnScriptTask;
import org.modelio.diagram.editor.bpmn.elements.bpmnsripttask.GmBpmnScriptTaskPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnstartevent.BpmnStartEventPrimaryNodeEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnstartevent.GmBpmnStartEvent;
import org.modelio.diagram.editor.bpmn.elements.bpmnstartevent.GmBpmnStartEventPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnsubprocess.BpmnSubProcessEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnsubprocess.GmBpmnSubProcess;
import org.modelio.diagram.editor.bpmn.elements.bpmnsubprocess.GmBpmnSubProcessPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnsubprocess.content.GmBpmnSubProcessContent;
import org.modelio.diagram.editor.bpmn.elements.bpmnsubprocess.content.SubProcessContentRootEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmntask.BpmnTaskEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmntask.GmBpmnTask;
import org.modelio.diagram.editor.bpmn.elements.bpmntask.GmBpmnTaskPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmntransaction.BpmnTransactionEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmntransaction.GmBpmnTransaction;
import org.modelio.diagram.editor.bpmn.elements.bpmntransaction.GmBpmnTransactionPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnusertask.BpmnUserTaskEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnusertask.GmBpmnUserTask;
import org.modelio.diagram.editor.bpmn.elements.bpmnusertask.GmBpmnUserTaskPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.diagrams.processcollaboration.BpmnProcessCollaborationDiagramEditPart;
import org.modelio.diagram.editor.bpmn.elements.diagrams.processcollaboration.GmBpmnProcessCollaborationDiagram;
import org.modelio.diagram.editor.bpmn.elements.diagrams.processdesign.BpmnProcessDesignDiagramEditPart;
import org.modelio.diagram.editor.bpmn.elements.diagrams.processdesign.GmBpmnProcessDesignDiagram;
import org.modelio.diagram.editor.bpmn.elements.diagrams.subprocess.BpmnSubProcessDiagramEditPart;
import org.modelio.diagram.editor.bpmn.elements.diagrams.subprocess.GmBpmnSubProcessDiagram;
import org.modelio.diagram.editor.bpmn.elements.participant.GmBpmnParticipantPortContainer;
import org.modelio.diagram.editor.bpmn.elements.participant.GmBpmnParticipantPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.participant.ParticipantPrimaryExpandedEditPart;
import org.modelio.diagram.editor.bpmn.elements.participant.ParticipantPrimarySimpleEditPart;
import org.modelio.diagram.editor.bpmn.elements.participant.content.GmBpmnParticipantContent;
import org.modelio.diagram.editor.bpmn.elements.participant.content.ParticipantContentRootEditPart;
import org.modelio.diagram.editor.bpmn.elements.participant.header.GmBpmnParticipantHeader;
import org.modelio.diagram.editor.bpmn.elements.participant.header.ParticipantHeaderEditPart;
import org.modelio.diagram.editor.bpmn.elements.workflow.GmWorkflow;
import org.modelio.diagram.editor.bpmn.elements.workflow.WorkflowEditPart;
import org.modelio.diagram.elements.common.freezone.FreeZoneEditPart;
import org.modelio.diagram.elements.common.freezone.GmBodyFreeZone;
import org.modelio.diagram.elements.common.header.ModelElementHeaderEditPart;
import org.modelio.diagram.elements.common.label.base.ElementLabelEditPart;
import org.modelio.diagram.elements.common.label.modelelement.ModelElementLabelEditPart;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;

/**
 * The Scope EditPart factory for Modelio diagrams.
 * <p>
 * Implementation of {@link EditPartFactory}.
 */
@objid ("60da6d4c-55b6-11e2-877f-002564c97630")
public class BpmnEditPartFactory implements EditPartFactory {
    /**
     * the default factory to use when structured mode is requested.
     */
    @objid ("60da6d4e-55b6-11e2-877f-002564c97630")
    private static final StructuredModeEditPartFactory structuredModeEditPartFactory = new StructuredModeEditPartFactory();

    @objid ("fd8ccee8-38b4-4969-af28-6e0cca39a475")
    private static final SimpleModeEditPartFactory simpleModeEditPartFactory = new SimpleModeEditPartFactory();

    @objid ("60da6d50-55b6-11e2-877f-002564c97630")
    @Override
    public EditPart createEditPart(EditPart context, Object model) {
        EditPart editPart = null;
        
        if (model instanceof GmNodeModel) {
            // For node models, delegates according the representation model.
            GmNodeModel node = (GmNodeModel) model;
            switch (node.getRepresentationMode()) {
            case SIMPLE:
                editPart = BpmnEditPartFactory.simpleModeEditPartFactory.createEditPart(context, model);
                break;
            case STRUCTURED:
                editPart = BpmnEditPartFactory.structuredModeEditPartFactory.createEditPart(context, model);
                break;
            case IMAGE:
            case USER_IMAGE:
            default:
                break;
            }
        
            if (editPart != null) {
                return editPart;
            }
        
            return null;
        } else {
            // Link models are always in structured mode.
            editPart = BpmnEditPartFactory.structuredModeEditPartFactory.createEditPart(context, model);
        }
        return editPart;
    }

    /**
     * EditPart factory for BPMN models in standard structured mode.
     * <p>
     * This is the default mode so the default factory.
     * </p>
     */
    @objid ("60da6d57-55b6-11e2-877f-002564c97630")
    private static class StructuredModeEditPartFactory implements EditPartFactory {
        @objid ("60da6d59-55b6-11e2-877f-002564c97630")
        @Override
        public EditPart createEditPart(EditPart context, Object model) {
            EditPart editPart = null;
            
            Class<? extends Object> modelClass = model.getClass();
            
            if (modelClass == GmBpmnProcessCollaborationDiagram.class) {
                editPart = new BpmnProcessCollaborationDiagramEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model instanceof GmBpmnProcessDesignDiagram) {
                editPart = new BpmnProcessDesignDiagramEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model instanceof GmBpmnSubProcessDiagram) {
                editPart = new BpmnSubProcessDiagramEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnBusinessRuleTaskPrimaryNode.class) {
                editPart = new BpmnBusinessRuleTaskEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnBusinessRuleTask.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnTaskPrimaryNode.class) {
                editPart = new BpmnTaskEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnTask.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnSendTaskPrimaryNode.class) {
                editPart = new BpmnSendTaskEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnSendTask.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnReceiveTaskPrimaryNode.class) {
                editPart = new BpmnReceiveTaskEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnReceiveTask.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (modelClass == GmBpmnServiceTaskPrimaryNode.class) {
                editPart = new BpmnServiceTaskEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnServiceTaskTypeLabel.class) {
                editPart = new ModelElementHeaderEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnServiceTask.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (modelClass == GmBpmnUserTaskPrimaryNode.class) {
                editPart = new BpmnUserTaskEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnUserTask.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (modelClass == GmBpmnManualTaskPrimaryNode.class) {
                editPart = new BpmnManualTaskEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnManualTask.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (modelClass == GmBpmnScriptTaskPrimaryNode.class) {
                editPart = new BpmnScriptTaskEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnScriptTask.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnNodeHeader.class) {
                editPart = new BpmnNodeHeaderEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnNodeFooter.class) {
                editPart = new BpmnNodeFooterEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnSequenceFlow.class) {
                editPart = new BpmnSequenceFlowEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnSequenceFlowDataAssociation.class) {
                editPart = new BpmnSequenceFlowDataAssociationEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnStartEvent.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnBoundaryEvent.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnIntermediateCatchEvent.class) {
                editPart = new BpmnIntermediateCatchEventEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnEndEvent.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnIntermediateThrowEvent.class) {
                editPart = new BpmnIntermediateThrowEventEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnExclusiveGateway.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnInclusiveGateway.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnComplexGateway.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnParallelGateway.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnEventBasedGateway.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnStartEventPrimaryNode.class) {
                editPart = new BpmnStartEventPrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnBoundaryEventPrimaryNode.class) {
                editPart = new BpmnBoundaryEventPrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnIntermediateCatchEventPrimaryNode.class) {
                editPart = new BpmnIntermediateCatchEventPrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnEndEventPrimaryNode.class) {
                editPart = new BpmnEndEventPrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnIntermediateThrowEventPrimaryNode.class) {
                editPart = new BpmnIntermediateThrowEventPrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnExclusiveGatewayPrimaryNode.class) {
                editPart = new BpmnExclusiveGatewayEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (modelClass == GmBpmnInclusiveGatewayPrimaryNode.class) {
                editPart = new BpmnInclusiveGatewayEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnComplexGatewayPrimaryNode.class) {
                editPart = new BpmnComplexGatewayEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnParallelGatewayPrimaryNode.class) {
                editPart = new BpmnParallelGatewayEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnEventBasedGatewayPrimaryNode.class) {
                editPart = new BpmnEventBasedGatewayEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnCallActivityPrimaryNode.class) {
                editPart = new BpmnCallActivityEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnCallActivity.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnAdHocSubProcess.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnAdHocSubProcessPrimaryNode.class) {
                editPart = new BpmnAdHocSubProcessEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnSubProcessContent.class) {
                editPart = new SubProcessContentRootEditPart(context, model);
                return editPart;
            }
            
            if (modelClass == GmBpmnSubProcessPrimaryNode.class) {
                editPart = new BpmnSubProcessEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnSubProcess.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnTransactionPrimaryNode.class) {
                editPart = new BpmnTransactionEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnTransaction.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnAdHocSubProcessPrimaryNode.class) {
                editPart = new BpmnAdHocSubProcessEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBodyFreeZone.class) {
                editPart = new FreeZoneEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnLaneSetContainer.class) {
                editPart = new BpmnLaneSetContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnLane.class) {
                editPart = new BpmnLaneEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnLaneHeader.class) {
                editPart = new BpmnLaneHeaderEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBodyHybridContainer.class) {
                editPart = new BodyHybridContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnEdgeGuard.class) {
                editPart = new ElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnMessageFlow.class) {
                editPart = new BpmnMessageFlowEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnMessagePrimaryNode.class) {
                editPart = new BpmnMessageEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnMessage.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnMessageLink.class) {
                editPart = new BpmnMessageLinkEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnDataInputPrimaryNode.class) {
                editPart = new BpmnDataInputEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnDataOutputPrimaryNode.class) {
                editPart = new BpmnDataOutputEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnDataStorePrimaryNode.class) {
                editPart = new BpmnDataStoreEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnDataObject.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnDataInput.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnDataOutput.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnDataStore.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnDataObjectPrimaryNode.class) {
                editPart = new BpmnDataObjectEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnDataAssociation.class) {
                editPart = new BpmnDataAssociationEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnDataLabel.class) {
                editPart = new ModelElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnMessageLabel.class) {
                editPart = new ModelElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnDataInputLabel.class) {
                editPart = new ModelElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnDataOutputLabel.class) {
                editPart = new ModelElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnCallActivityHeader.class) {
                editPart = new BpmnCallActivityHeaderEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmWorkflow.class) {
                editPart = new WorkflowEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Participant
            if (modelClass == GmBpmnParticipantHeader.class) {
                editPart = new ParticipantHeaderEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnParticipantPrimaryNode.class) {
                if (((GmBpmnParticipantPrimaryNode) model).getRepresentationMode() == RepresentationMode.STRUCTURED) {
                    editPart = new ParticipantPrimaryExpandedEditPart();
                    editPart.setModel(model);
                    return editPart;
                } else {
                    editPart = new ParticipantPrimarySimpleEditPart();
                    editPart.setModel(model);
                    return editPart;
                }
            }
            
            if (modelClass == GmBpmnParticipantContent.class) {
                editPart = new ParticipantContentRootEditPart(context, model);
                return editPart;
            }
            
            if (modelClass == GmBpmnParticipantPortContainer.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            
            }
            return null;
        }

    }

    /**
     * EditPart factory for BPMN models in simple mode.
     * <p>
     * Most edit parts are the same for simple and structured mode.
     * </p>
     */
    @objid ("95e0c43c-3b46-4278-8c19-9cab5b5d3813")
    private static final class SimpleModeEditPartFactory extends StructuredModeEditPartFactory {
        @objid ("5e51058e-b6f7-4758-b1cb-913bd300e94e")
        @Override
        public EditPart createEditPart(EditPart context, Object model) {
            EditPart editPart = null;
            
            Class<? extends Object> modelClass = model.getClass();
            
            if (modelClass == GmBpmnMessagePrimaryNode.class) {
                editPart = new BpmnMessageSimpleEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnDataStorePrimaryNode.class) {
                editPart = new BpmnDataStoreSimpleEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // No "simple" edit part found, fallback to structured edit part.
            return super.createEditPart(context, model);
        }

    }

}
