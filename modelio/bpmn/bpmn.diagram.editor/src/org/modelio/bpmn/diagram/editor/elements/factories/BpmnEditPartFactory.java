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
package org.modelio.bpmn.diagram.editor.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.modelio.bpmn.diagram.editor.elements.bpmnadhocsubprocess.BpmnAdHocSubProcessEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnadhocsubprocess.GmBpmnAdHocSubProcess;
import org.modelio.bpmn.diagram.editor.elements.bpmnadhocsubprocess.GmBpmnAdHocSubProcessPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmnboundaryevent.BpmnBoundaryEventPrimaryNodeEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnboundaryevent.GmBpmnBoundaryEvent;
import org.modelio.bpmn.diagram.editor.elements.bpmnboundaryevent.GmBpmnBoundaryEventPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmnbusinessruletask.BpmnBusinessRuleTaskEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnbusinessruletask.GmBpmnBusinessRuleTask;
import org.modelio.bpmn.diagram.editor.elements.bpmnbusinessruletask.GmBpmnBusinessRuleTaskPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmncallactivity.BpmnCallActivityEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmncallactivity.BpmnCallActivityHeaderEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmncallactivity.GmBpmnCallActivity;
import org.modelio.bpmn.diagram.editor.elements.bpmncallactivity.GmBpmnCallActivityHeader;
import org.modelio.bpmn.diagram.editor.elements.bpmncallactivity.GmBpmnCallActivityPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmncomplexgateway.BpmnComplexGatewayEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmncomplexgateway.GmBpmnComplexGateway;
import org.modelio.bpmn.diagram.editor.elements.bpmncomplexgateway.GmBpmnComplexGatewayPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmndataassociation.BpmnDataAssociationEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmndataassociation.GmBpmnDataAssociation;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.GmBpmnDataLabel;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.datainput.BpmnDataInputEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.datainput.GmBpmnDataInput;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.datainput.GmBpmnDataInputLabel;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.datainput.GmBpmnDataInputPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.dataobject.BpmnDataObjectEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.dataobject.GmBpmnDataObject;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.dataobject.GmBpmnDataObjectPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.dataoutput.BpmnDataOutputEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.dataoutput.GmBpmnDataOutput;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.dataoutput.GmBpmnDataOutputLabel;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.dataoutput.GmBpmnDataOutputPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.datastore.BpmnDataStoreEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.datastore.BpmnDataStoreSimpleEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.datastore.GmBpmnDataStore;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.datastore.GmBpmnDataStorePrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmnendevent.BpmnEndEventPrimaryNodeEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnendevent.GmBpmnEndEvent;
import org.modelio.bpmn.diagram.editor.elements.bpmnendevent.GmBpmnEndEventPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmneventbasedgateway.BpmnEventBasedGatewayEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmneventbasedgateway.GmBpmnEventBasedGateway;
import org.modelio.bpmn.diagram.editor.elements.bpmneventbasedgateway.GmBpmnEventBasedGatewayPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmnexclusivegateway.BpmnExclusiveGatewayEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnexclusivegateway.GmBpmnExclusiveGateway;
import org.modelio.bpmn.diagram.editor.elements.bpmnexclusivegateway.GmBpmnExclusiveGatewayPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmninclusivegateway.BpmnInclusiveGatewayEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmninclusivegateway.GmBpmnInclusiveGateway;
import org.modelio.bpmn.diagram.editor.elements.bpmninclusivegateway.GmBpmnInclusiveGatewayPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmnintermediatecatchevent.BpmnIntermediateCatchEventEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnintermediatecatchevent.BpmnIntermediateCatchEventPrimaryNodeEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnintermediatecatchevent.GmBpmnIntermediateCatchEvent;
import org.modelio.bpmn.diagram.editor.elements.bpmnintermediatecatchevent.GmBpmnIntermediateCatchEventPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmnintermediatethrowevent.BpmnIntermediateThrowEventEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnintermediatethrowevent.BpmnIntermediateThrowEventPrimaryNodeEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnintermediatethrowevent.GmBpmnIntermediateThrowEvent;
import org.modelio.bpmn.diagram.editor.elements.bpmnintermediatethrowevent.GmBpmnIntermediateThrowEventPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmnlane.BpmnLaneEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnlane.GmBpmnLane;
import org.modelio.bpmn.diagram.editor.elements.bpmnlane.header.BpmnLaneHeaderEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnlane.header.GmBpmnLaneHeader;
import org.modelio.bpmn.diagram.editor.elements.bpmnlane.hibridcontainer.BodyHybridContainerEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnlane.hibridcontainer.GmBodyHybridContainer;
import org.modelio.bpmn.diagram.editor.elements.bpmnlanesetcontainer.BpmnLaneSetContainerEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnlanesetcontainer.GmBpmnLaneSetContainer;
import org.modelio.bpmn.diagram.editor.elements.bpmnmanualtask.BpmnManualTaskEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnmanualtask.GmBpmnManualTask;
import org.modelio.bpmn.diagram.editor.elements.bpmnmanualtask.GmBpmnManualTaskPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmnmessage.BpmnMessageEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnmessage.BpmnMessageLinkEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnmessage.BpmnMessageSimpleEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnmessage.GmBpmnMessage;
import org.modelio.bpmn.diagram.editor.elements.bpmnmessage.GmBpmnMessageLabel;
import org.modelio.bpmn.diagram.editor.elements.bpmnmessage.GmBpmnMessageLink;
import org.modelio.bpmn.diagram.editor.elements.bpmnmessage.GmBpmnMessagePrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmnmessageflow.BpmnMessageFlowEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnmessageflow.GmBpmnMessageFlow;
import org.modelio.bpmn.diagram.editor.elements.bpmnnodefooter.BpmnNodeFooterEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnnodefooter.GmBpmnNodeFooter;
import org.modelio.bpmn.diagram.editor.elements.bpmnnodeheader.BpmnNodeHeaderEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnnodeheader.GmBpmnNodeHeader;
import org.modelio.bpmn.diagram.editor.elements.bpmnparallelgateway.BpmnParallelGatewayEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnparallelgateway.GmBpmnParallelGateway;
import org.modelio.bpmn.diagram.editor.elements.bpmnparallelgateway.GmBpmnParallelGatewayPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmnreceivetask.BpmnReceiveTaskEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnreceivetask.GmBpmnReceiveTask;
import org.modelio.bpmn.diagram.editor.elements.bpmnreceivetask.GmBpmnReceiveTaskPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmnsendtask.BpmnSendTaskEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnsendtask.GmBpmnSendTask;
import org.modelio.bpmn.diagram.editor.elements.bpmnsendtask.GmBpmnSendTaskPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmnsequenceflow.BpmnSequenceFlowEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnsequenceflow.GmBpmnEdgeGuard;
import org.modelio.bpmn.diagram.editor.elements.bpmnsequenceflow.GmBpmnSequenceFlow;
import org.modelio.bpmn.diagram.editor.elements.bpmnsequenceflowdataassociation.BpmnSequenceFlowDataAssociationEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnsequenceflowdataassociation.GmBpmnSequenceFlowDataAssociation;
import org.modelio.bpmn.diagram.editor.elements.bpmnservicetask.BpmnServiceTaskEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnservicetask.GmBpmnServiceTask;
import org.modelio.bpmn.diagram.editor.elements.bpmnservicetask.GmBpmnServiceTaskPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmnservicetask.GmBpmnServiceTaskTypeLabel;
import org.modelio.bpmn.diagram.editor.elements.bpmnsripttask.BpmnScriptTaskEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnsripttask.GmBpmnScriptTask;
import org.modelio.bpmn.diagram.editor.elements.bpmnsripttask.GmBpmnScriptTaskPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmnstartevent.BpmnStartEventPrimaryNodeEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnstartevent.GmBpmnStartEvent;
import org.modelio.bpmn.diagram.editor.elements.bpmnstartevent.GmBpmnStartEventPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmnsubprocess.BpmnSubProcessEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnsubprocess.GmBpmnSubProcess;
import org.modelio.bpmn.diagram.editor.elements.bpmnsubprocess.GmBpmnSubProcessPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmnsubprocess.content.GmBpmnSubProcessContent;
import org.modelio.bpmn.diagram.editor.elements.bpmnsubprocess.content.SubProcessContentRootEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmntask.BpmnTaskEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmntask.GmBpmnTask;
import org.modelio.bpmn.diagram.editor.elements.bpmntask.GmBpmnTaskPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmntransaction.BpmnTransactionEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmntransaction.GmBpmnTransaction;
import org.modelio.bpmn.diagram.editor.elements.bpmntransaction.GmBpmnTransactionPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmnusertask.BpmnUserTaskEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnusertask.GmBpmnUserTask;
import org.modelio.bpmn.diagram.editor.elements.bpmnusertask.GmBpmnUserTaskPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.common.editpart.BpmnNonSelectableImageEditPart;
import org.modelio.bpmn.diagram.editor.elements.common.editpart.BpmnPortContainerEditPart;
import org.modelio.bpmn.diagram.editor.elements.diagrams.processcollaboration.BpmnProcessCollaborationDiagramEditPart;
import org.modelio.bpmn.diagram.editor.elements.diagrams.processcollaboration.GmBpmnProcessCollaborationDiagram;
import org.modelio.bpmn.diagram.editor.elements.diagrams.processdesign.BpmnProcessDesignDiagramEditPart;
import org.modelio.bpmn.diagram.editor.elements.diagrams.processdesign.GmBpmnProcessDesignDiagram;
import org.modelio.bpmn.diagram.editor.elements.diagrams.subprocess.BpmnSubProcessDiagramEditPart;
import org.modelio.bpmn.diagram.editor.elements.diagrams.subprocess.GmBpmnSubProcessDiagram;
import org.modelio.bpmn.diagram.editor.elements.participant.GmBpmnParticipantPortContainer;
import org.modelio.bpmn.diagram.editor.elements.participant.GmBpmnParticipantPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.participant.ParticipantPrimaryExpandedEditPart;
import org.modelio.bpmn.diagram.editor.elements.participant.ParticipantPrimarySimpleEditPart;
import org.modelio.bpmn.diagram.editor.elements.participant.content.GmBpmnParticipantContent;
import org.modelio.bpmn.diagram.editor.elements.participant.content.ParticipantContentRootEditPart;
import org.modelio.bpmn.diagram.editor.elements.participant.header.GmBpmnParticipantHeader;
import org.modelio.bpmn.diagram.editor.elements.participant.header.ParticipantHeaderEditPart;
import org.modelio.bpmn.diagram.editor.elements.workflow.GmWorkflow;
import org.modelio.bpmn.diagram.editor.elements.workflow.WorkflowEditPart;
import org.modelio.diagram.elements.common.freezone.FreeZoneEditPart;
import org.modelio.diagram.elements.common.freezone.GmBodyFreeZone;
import org.modelio.diagram.elements.common.header.ModelElementHeaderEditPart;
import org.modelio.diagram.elements.common.image.LabelledImageEditPart;
import org.modelio.diagram.elements.common.label.base.ElementLabelEditPart;
import org.modelio.diagram.elements.common.label.modelelement.ModelElementLabelEditPart;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.factory.GenericUserImageModeEditPartFactory;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
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

    @objid ("2c6f2e97-d3c7-4f2c-8106-0e5cd3ee991c")
    private static final ImageModeEditPartFactory imageModeEditPartFactory = new ImageModeEditPartFactory();

    @objid ("b50d3089-37d3-4c9b-aad7-890a08d498ce")
    private final EditPartFactory userImageModeEditPartFactory = new GenericUserImageModeEditPartFactory(this.imageModeEditPartFactory);

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
                editPart = BpmnEditPartFactory.imageModeEditPartFactory.createEditPart(context, model);
                break;
            case USER_IMAGE:
                editPart = this.userImageModeEditPartFactory.createEditPart(context, model);
                break;
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
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnBoundaryEvent.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnIntermediateCatchEvent.class) {
                editPart = new BpmnIntermediateCatchEventEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnEndEvent.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnIntermediateThrowEvent.class) {
                editPart = new BpmnIntermediateThrowEventEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnExclusiveGateway.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnInclusiveGateway.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnComplexGateway.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnParallelGateway.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnEventBasedGateway.class) {
                editPart = new BpmnPortContainerEditPart();
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
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnDataInput.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnDataOutput.class) {
                editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (modelClass == GmBpmnDataStore.class) {
                editPart = new BpmnPortContainerEditPart();
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
                editPart = new BpmnPortContainerEditPart();
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

    /**
     * EditPart factory for node models in stereotype image mode.
     */
    @objid ("e3a731e4-9552-447e-b174-94cd337a5795")
    private static final class ImageModeEditPartFactory implements EditPartFactory {
        @objid ("f43fcd1d-ec5c-4428-b318-c06bfa184d82")
        @Override
        public EditPart createEditPart(EditPart context, Object model) {
            // Port containers stay a port container in image mode
            if (model instanceof GmPortContainer) {
                new IllegalStateException("Ports containers should never be in image mode.").printStackTrace();
            
                final EditPart editPart = new BpmnPortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (model instanceof IImageableNode && model instanceof GmNodeModel) {
                if (((GmNodeModel) model).getParent() instanceof GmPortContainer) {
                    final EditPart editPart = new BpmnNonSelectableImageEditPart();
                    editPart.setModel(model);
                    return editPart;
                } else {
                    final EditPart editPart = new LabelledImageEditPart();
                    editPart.setModel(model);
                    return editPart;
                }
            }
            return null;
        }

    }

}
