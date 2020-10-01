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

package org.modelio.uml.activitydiagram.editor.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.modelio.diagram.elements.common.header.ModelElementHeaderEditPart;
import org.modelio.diagram.elements.common.label.base.ElementLabelEditPart;
import org.modelio.diagram.elements.common.label.base.NonEditableItalicLabelEditPart;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPart;
import org.modelio.diagram.elements.common.resizablegroup.ResizableGroupEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.uml.activitydiagram.editor.elements.acceptsignal.AcceptSignalEditPart;
import org.modelio.uml.activitydiagram.editor.elements.acceptsignal.GmAcceptSignal;
import org.modelio.uml.activitydiagram.editor.elements.acceptsignal.GmAcceptSignalHeader;
import org.modelio.uml.activitydiagram.editor.elements.acceptsignal.GmAcceptSignalPrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.acceptsignal.GmAcceptedSignalsLabel;
import org.modelio.uml.activitydiagram.editor.elements.action.ActionBodyEditPart;
import org.modelio.uml.activitydiagram.editor.elements.action.ActionEditPart;
import org.modelio.uml.activitydiagram.editor.elements.action.GmAction;
import org.modelio.uml.activitydiagram.editor.elements.action.GmActionBody;
import org.modelio.uml.activitydiagram.editor.elements.action.GmActionPrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.activitydiagram.ActivityDiagramEditPart;
import org.modelio.uml.activitydiagram.editor.elements.activitydiagram.GmActivityDiagram;
import org.modelio.uml.activitydiagram.editor.elements.activityedgelabels.GmActivityEdgeGuard;
import org.modelio.uml.activitydiagram.editor.elements.activityedgelabels.GmActivityEdgeWeight;
import org.modelio.uml.activitydiagram.editor.elements.activityfinal.ActivityFinalEditPart;
import org.modelio.uml.activitydiagram.editor.elements.activityfinal.GmActivityFinal;
import org.modelio.uml.activitydiagram.editor.elements.activityfinal.GmActivityFinalPrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.activitynodeheader.ActivityNodeHeaderEditPart;
import org.modelio.uml.activitydiagram.editor.elements.activitynodeheader.GmActivityNodeHeader;
import org.modelio.uml.activitydiagram.editor.elements.callbehavior.CallBehaviorEditPart;
import org.modelio.uml.activitydiagram.editor.elements.callbehavior.GmCallBehavior;
import org.modelio.uml.activitydiagram.editor.elements.callbehavior.GmCallBehaviorPrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.callevent.CallEventEditPart;
import org.modelio.uml.activitydiagram.editor.elements.callevent.GmCallEvent;
import org.modelio.uml.activitydiagram.editor.elements.callevent.GmCallEventHeader;
import org.modelio.uml.activitydiagram.editor.elements.callevent.GmCallEventPrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.callevent.GmCalledOperationLabel;
import org.modelio.uml.activitydiagram.editor.elements.calloperation.CallOperationEditPart;
import org.modelio.uml.activitydiagram.editor.elements.calloperation.GmCallOperation;
import org.modelio.uml.activitydiagram.editor.elements.calloperation.GmCallOperationPrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.centralbuffer.CentralBufferEditPart;
import org.modelio.uml.activitydiagram.editor.elements.centralbuffer.GmCentralBuffer;
import org.modelio.uml.activitydiagram.editor.elements.centralbuffer.GmCentralBufferPrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.changeevent.ChangeEventEditPart;
import org.modelio.uml.activitydiagram.editor.elements.changeevent.ChangeEventExpressionEditPart;
import org.modelio.uml.activitydiagram.editor.elements.changeevent.GmChangeEvent;
import org.modelio.uml.activitydiagram.editor.elements.changeevent.GmChangeEventExpression;
import org.modelio.uml.activitydiagram.editor.elements.changeevent.GmChangeEventPrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.clause.ClauseEditPart;
import org.modelio.uml.activitydiagram.editor.elements.clause.ClauseTestEditPart;
import org.modelio.uml.activitydiagram.editor.elements.clause.GmClause;
import org.modelio.uml.activitydiagram.editor.elements.conditional.ConditionalEditPart;
import org.modelio.uml.activitydiagram.editor.elements.conditional.GmClausesGroup;
import org.modelio.uml.activitydiagram.editor.elements.conditional.GmConditional;
import org.modelio.uml.activitydiagram.editor.elements.conditional.GmConditionalPrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.controlflow.ControlFlowEditPart;
import org.modelio.uml.activitydiagram.editor.elements.controlflow.GmControlFlow;
import org.modelio.uml.activitydiagram.editor.elements.datastore.DataStoreEditPart;
import org.modelio.uml.activitydiagram.editor.elements.datastore.GmDataStore;
import org.modelio.uml.activitydiagram.editor.elements.datastore.GmDataStorePrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.decisionmerge.DecisionMergeEditPart;
import org.modelio.uml.activitydiagram.editor.elements.decisionmerge.DecisionMergePrimaryNodeEditPart;
import org.modelio.uml.activitydiagram.editor.elements.decisionmerge.GmDecisionMerge;
import org.modelio.uml.activitydiagram.editor.elements.decisionmerge.GmDecisionMergePrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.decisionmerge.GmInputBehaviourText;
import org.modelio.uml.activitydiagram.editor.elements.decisionmerge.InputBehaviourTextEditPart;
import org.modelio.uml.activitydiagram.editor.elements.exceptionhandler.ExceptionHandlerEditPart;
import org.modelio.uml.activitydiagram.editor.elements.exceptionhandler.GmExceptionHandler;
import org.modelio.uml.activitydiagram.editor.elements.expansionnode.ExpansionNodeEditPart;
import org.modelio.uml.activitydiagram.editor.elements.expansionnode.GmExpansionNode;
import org.modelio.uml.activitydiagram.editor.elements.expansionnode.GmExpansionNodePrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.expansionregion.ExpansionRegionEditPart;
import org.modelio.uml.activitydiagram.editor.elements.expansionregion.GmExpansionRegion;
import org.modelio.uml.activitydiagram.editor.elements.expansionregion.GmExpansionRegionPrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.flowfinal.FlowFinalEditPart;
import org.modelio.uml.activitydiagram.editor.elements.flowfinal.GmFlowFinal;
import org.modelio.uml.activitydiagram.editor.elements.flowfinal.GmFlowFinalPrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.forkjoin.ForkJoinPrimaryNodeEditPart;
import org.modelio.uml.activitydiagram.editor.elements.forkjoin.ForkJoinSatelliteContainerEditPart;
import org.modelio.uml.activitydiagram.editor.elements.forkjoin.GmForkJoin;
import org.modelio.uml.activitydiagram.editor.elements.forkjoin.GmForkJoinPrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.generic.ActivityNonSelectableImageEditPart;
import org.modelio.uml.activitydiagram.editor.elements.initial.GmInitial;
import org.modelio.uml.activitydiagram.editor.elements.initial.GmInitialPrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.initial.InitialEditPart;
import org.modelio.uml.activitydiagram.editor.elements.inputpin.GmInputPin;
import org.modelio.uml.activitydiagram.editor.elements.inputpin.GmInputPinPrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.inputpin.InputPinEditPart;
import org.modelio.uml.activitydiagram.editor.elements.interruptible.GmInterruptible;
import org.modelio.uml.activitydiagram.editor.elements.interruptible.InterruptibleEditPart;
import org.modelio.uml.activitydiagram.editor.elements.loopnode.GmLoopNode;
import org.modelio.uml.activitydiagram.editor.elements.loopnode.GmLoopNodePrimaryNode.GmSetup;
import org.modelio.uml.activitydiagram.editor.elements.loopnode.GmLoopNodePrimaryNode.GmTest;
import org.modelio.uml.activitydiagram.editor.elements.loopnode.GmLoopNodePrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.loopnode.LoopNodeEditPart;
import org.modelio.uml.activitydiagram.editor.elements.objectflow.GmObjectFlow;
import org.modelio.uml.activitydiagram.editor.elements.objectflow.ObjectFlowEditPart;
import org.modelio.uml.activitydiagram.editor.elements.objectnode.GmObjectNode;
import org.modelio.uml.activitydiagram.editor.elements.objectnode.GmObjectNodeHeader;
import org.modelio.uml.activitydiagram.editor.elements.objectnode.GmObjectNodePrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.objectnode.GmObjectNodeStateLabel;
import org.modelio.uml.activitydiagram.editor.elements.objectnode.ObjectNodeEditPart;
import org.modelio.uml.activitydiagram.editor.elements.objectnode.ObjectNodeStateLabelEditPart;
import org.modelio.uml.activitydiagram.editor.elements.outputpin.GmOutputPin;
import org.modelio.uml.activitydiagram.editor.elements.outputpin.GmOutputPinPrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.outputpin.OutputPinEditPart;
import org.modelio.uml.activitydiagram.editor.elements.partition.GmPartition;
import org.modelio.uml.activitydiagram.editor.elements.partition.PartitionEditPart;
import org.modelio.uml.activitydiagram.editor.elements.partition.bodyhybridcontainer.BodyHybridContainerEditPart;
import org.modelio.uml.activitydiagram.editor.elements.partition.bodyhybridcontainer.GmBodyHybridContainer;
import org.modelio.uml.activitydiagram.editor.elements.partition.header.GmPartitionHeader;
import org.modelio.uml.activitydiagram.editor.elements.partition.header.PartitionHeaderEditPart;
import org.modelio.uml.activitydiagram.editor.elements.partitioncontainer.GmDiagramPartitionContainer;
import org.modelio.uml.activitydiagram.editor.elements.partitioncontainer.GmPartitionParameterContainer;
import org.modelio.uml.activitydiagram.editor.elements.partitioncontainer.PartitionContainerEditPart;
import org.modelio.uml.activitydiagram.editor.elements.sendsignal.GmSendSignal;
import org.modelio.uml.activitydiagram.editor.elements.sendsignal.GmSendSignalHeader;
import org.modelio.uml.activitydiagram.editor.elements.sendsignal.GmSendSignalLabel;
import org.modelio.uml.activitydiagram.editor.elements.sendsignal.GmSendSignalPrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.sendsignal.SendSignalEditPart;
import org.modelio.uml.activitydiagram.editor.elements.structuredactivity.GmStructuredActivity;
import org.modelio.uml.activitydiagram.editor.elements.structuredactivity.GmStructuredActivityPrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.structuredactivity.StructuredActivityEditPart;
import org.modelio.uml.activitydiagram.editor.elements.timeevent.GmTimeEvent;
import org.modelio.uml.activitydiagram.editor.elements.timeevent.GmTimeEventPrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.timeevent.TimeEventEditPart;
import org.modelio.uml.activitydiagram.editor.elements.valuepin.GmValuePin;
import org.modelio.uml.activitydiagram.editor.elements.valuepin.GmValuePinPrimaryNode;
import org.modelio.uml.activitydiagram.editor.elements.valuepin.ValuePinEditPart;

/**
 * The State diagram EditPart factory.
 * <p>
 * This factory only processes the state specific edit parts. It is intended to be used only as a cascaded factory in order to dynamically enriching the Modelio standard factory so that this latter ends by being able to process the complete UML. The
 * StateDiagramEditPartFactory does not provide edit part for simple and image mode.
 */
@objid ("29980111-55b6-11e2-877f-002564c97630")
public class ActivityEditPartFactory implements EditPartFactory {
    /**
     * the default factory to use when image mode is requested.
     */
    @objid ("29980115-55b6-11e2-877f-002564c97630")
    private static final EditPartFactory imageModeEditPartFactory = new ImageModeEditPartFactory();

    /**
     * the default factory to use when structured mode is requested.
     */
    @objid ("29980117-55b6-11e2-877f-002564c97630")
    private static final StructuredModeEditPartFactory structuredModeEditPartFactory = new StructuredModeEditPartFactory();

    /**
     * the default factory to use when the simple mode is requested.
     */
    @objid ("29980119-55b6-11e2-877f-002564c97630")
    private static final EditPartFactory simpleModeEditPartFactory = new SimpleModeEditPartFactory();

    @objid ("2998011b-55b6-11e2-877f-002564c97630")
    @Override
    public EditPart createEditPart(EditPart context, Object model) {
        EditPart editPart;
        if (model instanceof GmNodeModel) {
            // For node models, delegates according the representation model.
            GmNodeModel node = (GmNodeModel) model;
            switch (node.getRepresentationMode()) {
            case IMAGE:
                editPart = ActivityEditPartFactory.imageModeEditPartFactory.createEditPart(context, model);
                break;
            case SIMPLE:
                editPart = ActivityEditPartFactory.simpleModeEditPartFactory.createEditPart(context, model);
                break;
            case STRUCTURED:
                editPart = ActivityEditPartFactory.structuredModeEditPartFactory.createEditPart(context, model);
                break;
            default:
                editPart = null;
            }
        
            return editPart;
        } else {
            // Link models are always in structured mode.
            editPart = ActivityEditPartFactory.structuredModeEditPartFactory.createEditPart(context, model);
            return editPart;
        }
    }

    /**
     * EditPart factory for node models in standard structured mode.
     * <p>
     * This is the default mode so the default factory.
     */
    @objid ("29998782-55b6-11e2-877f-002564c97630")
    private static final class StructuredModeEditPartFactory implements EditPartFactory {
        @objid ("29998784-55b6-11e2-877f-002564c97630")
        @Override
        public EditPart createEditPart(EditPart context, Object model) {
            EditPart editPart = null;
            
            // Satellite elements
            if (model.getClass() == GmInitial.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmFlowFinal.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (model.getClass() == GmDecisionMerge.class) {
                editPart = new DecisionMergeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInputBehaviourText.class) {
                editPart = new InputBehaviourTextEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmActivityFinal.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (model.getClass() == GmForkJoin.class) {
                editPart = new ForkJoinSatelliteContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmPartitionParameterContainer.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (model.getClass() == GmActivityDiagram.class) {
                editPart = new ActivityDiagramEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmDiagramPartitionContainer.class) {
                editPart = new PartitionContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (model.getClass() == GmActivityNodeHeader.class) {
                editPart = new ActivityNodeHeaderEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmAction.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmActionBody.class) {
                editPart = new ActionBodyEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmActionPrimaryNode.class) {
                editPart = new ActionEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Call behavior
            if (model.getClass() == GmCallBehavior.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCallBehaviorPrimaryNode.class) {
                editPart = new CallBehaviorEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Call operation
            if (model.getClass() == GmCallOperation.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCallOperationPrimaryNode.class) {
                editPart = new CallOperationEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // Object node
            if (model.getClass() == GmObjectNode.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmObjectNodePrimaryNode.class) {
                editPart = new ObjectNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmObjectNodeStateLabel.class) {
                editPart = new ObjectNodeStateLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmObjectNodeHeader.class) {
                editPart = new ModelElementHeaderEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmDataStore.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmDataStorePrimaryNode.class) {
                editPart = new DataStoreEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCentralBuffer.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (model.getClass() == GmCentralBufferPrimaryNode.class) {
                editPart = new CentralBufferEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmControlFlow.class) {
                editPart = new ControlFlowEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmActivityEdgeGuard.class) {
                editPart = new ElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmActivityEdgeWeight.class) {
                editPart = new ElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (model.getClass() == GmExceptionHandler.class) {
                editPart = new ExceptionHandlerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmObjectFlow.class) {
                editPart = new ObjectFlowEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmSendSignal.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmSendSignalPrimaryNode.class) {
                editPart = new SendSignalEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmSendSignalLabel.class) {
                editPart = new NonEditableItalicLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmSendSignalHeader.class) {
                editPart = new ModelElementHeaderEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmExpansionRegion.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmExpansionRegionPrimaryNode.class) {
                editPart = new ExpansionRegionEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmExpansionNode.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmExpansionNodePrimaryNode.class) {
                editPart = new ExpansionNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmValuePin.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmValuePinPrimaryNode.class) {
                editPart = new ValuePinEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInterruptible.class) {
                editPart = new InterruptibleEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCallEvent.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCallEventPrimaryNode.class) {
                editPart = new CallEventEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCalledOperationLabel.class) {
                editPart = new NonEditableItalicLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCallEventHeader.class) {
                editPart = new ModelElementHeaderEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmChangeEvent.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmChangeEventPrimaryNode.class) {
                editPart = new ChangeEventEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmChangeEventExpression.class) {
                editPart = new ChangeEventExpressionEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmAcceptSignal.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmAcceptSignalPrimaryNode.class) {
                editPart = new AcceptSignalEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmAcceptedSignalsLabel.class) {
                editPart = new NonEditableItalicLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmAcceptSignalHeader.class) {
                editPart = new ModelElementHeaderEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmLoopNode.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmLoopNodePrimaryNode.class) {
                editPart = new LoopNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmSetup.class) {
                editPart = new ElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmTest.class) {
                editPart = new ElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmStructuredActivity.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmStructuredActivityPrimaryNode.class) {
                editPart = new StructuredActivityEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmConditional.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmConditionalPrimaryNode.class) {
                editPart = new ConditionalEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmClausesGroup.class) {
                editPart = new ResizableGroupEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmClause.class) {
                editPart = new ClauseEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmTimeEvent.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmTimeEventPrimaryNode.class) {
                editPart = new TimeEventEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInitialPrimaryNode.class) {
                editPart = new InitialEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmForkJoinPrimaryNode.class) {
                editPart = new ForkJoinPrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmActivityFinalPrimaryNode.class) {
                editPart = new ActivityFinalEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmFlowFinalPrimaryNode.class) {
                editPart = new FlowFinalEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmDecisionMergePrimaryNode.class) {
                editPart = new DecisionMergePrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInputPin.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInputPinPrimaryNode.class) {
                editPart = new InputPinEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmOutputPin.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmOutputPinPrimaryNode.class) {
                editPart = new OutputPinEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmPartition.class) {
                editPart = new PartitionEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmPartitionHeader.class) {
                editPart = new PartitionHeaderEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (model.getClass() == GmBodyHybridContainer.class) {
                editPart = new BodyHybridContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            if (model.getClass() == GmClause.GmTest.class) {
                editPart = new ClauseTestEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // not found
            return null;
        }

    }

    /**
     * EditPart factory for node models in stereotype image mode.
     * 
     * @author fpoyer
     */
    @objid ("2999878b-55b6-11e2-877f-002564c97630")
    private static final class ImageModeEditPartFactory implements EditPartFactory {
        @objid ("2999878e-55b6-11e2-877f-002564c97630")
        @Override
        public EditPart createEditPart(EditPart context, Object model) {
            // Lets handle special cases.
            if (model instanceof GmInputPinPrimaryNode ||
                    model instanceof GmValuePinPrimaryNode ||
                    model instanceof GmOutputPinPrimaryNode ||
                    model instanceof GmInitialPrimaryNode ||
                    model instanceof GmActivityFinalPrimaryNode ||
                    model instanceof GmFlowFinalPrimaryNode ||
                    model instanceof GmDecisionMergePrimaryNode ||
                    model instanceof GmForkJoinPrimaryNode ||
                    model instanceof GmTimeEventPrimaryNode ||
                    model instanceof GmExpansionNodePrimaryNode) {
                EditPart editPart = new ActivityNonSelectableImageEditPart();
                editPart.setModel(model);
                return editPart;
            }
            return null;
        }

    }

    /**
     * EditPart factory for node models in simple mode.
     * 
     * @author fpoyer
     */
    @objid ("29998795-55b6-11e2-877f-002564c97630")
    private static final class SimpleModeEditPartFactory implements EditPartFactory {
        @objid ("29998798-55b6-11e2-877f-002564c97630")
        @Override
        public EditPart createEditPart(final EditPart context, final Object model) {
            // Lets handle special cases.
            EditPart editPart = null;
            if (model.getClass() == GmTimeEventPrimaryNode.class) {
                editPart = new TimeEventEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInitialPrimaryNode.class) {
                editPart = new InitialEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmForkJoinPrimaryNode.class) {
                editPart = new ForkJoinPrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmActivityFinalPrimaryNode.class) {
                editPart = new ActivityFinalEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmFlowFinalPrimaryNode.class) {
                editPart = new FlowFinalEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmDecisionMergePrimaryNode.class) {
                editPart = new DecisionMergePrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmExpansionNodePrimaryNode.class) {
                editPart = new ExpansionNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmValuePinPrimaryNode.class) {
                editPart = new ValuePinEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInputPinPrimaryNode.class) {
                editPart = new InputPinEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmOutputPinPrimaryNode.class) {
                editPart = new OutputPinEditPart();
                editPart.setModel(model);
                return editPart;
            }
            return null;
        }

    }

}
