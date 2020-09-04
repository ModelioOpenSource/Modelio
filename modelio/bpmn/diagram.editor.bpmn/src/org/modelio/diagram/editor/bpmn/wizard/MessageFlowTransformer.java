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

package org.modelio.diagram.editor.bpmn.wizard;

import java.util.List;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessageflow.BpmnMessageFlowEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessageflow.GmBpmnMessageFlow;
import org.modelio.diagram.elements.core.commands.ICreationCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.metamodel.bpmn.activities.BpmnReceiveTask;
import org.modelio.metamodel.bpmn.activities.BpmnSendTask;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnCollaborationDiagram;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This model transformer gathers every selected {@link BpmnFlowNode} into a {@link BpmnSubProcess}, keeping the link's layout between them.
 * <p>
 * Flows which source or target is not moved into the sub process are considered outside the scope and are rerouted to link the {@link BpmnSubProcess} itself instead.
 * </p>
 */
@objid ("b5e921c6-2c73-43f7-ba1b-6fc93714efe6")
public class MessageFlowTransformer implements IModelTransformer {
    @objid ("083ba07f-e8df-438a-b383-4ddc6d15f33d")
    @Inject
    private IModuleContext moduleContext;

    /**
     * Criteria: - process collaboration diagrams - selection contains only message flows.
     * @see IModelTransformer#isAvailable(AbstractDiagram, ISelection)
     */
    @objid ("ce5e1c4e-54c8-4b72-92d1-90120518056e")
    @Override
    public boolean isAvailable(AbstractDiagram diagram, ISelection selection) {
        if (!canExecute(diagram, selection)) {
            return false;
        }
        
        // In a BPMN diagram
        if (!(diagram instanceof BpmnCollaborationDiagram)) {
            return false;
        }
        return true;
    }

    /**
     * Criteria: - process collaboration diagrams - selection contains only message flows.
     * @see IModelTransformer#isAvailable(AbstractDiagram, ISelection)
     */
    @objid ("7b7fcda5-873a-4152-9885-1081675cc0a2")
    @Override
    public boolean canExecute(AbstractDiagram diagram, ISelection selection) {
        List<MObject> elements = SelectionHelper.toList(selection, MObject.class);
        
        if (elements.size() != 1) {
            return false;
        }
        
        for (MObject elt : elements) {
            if (!(elt instanceof BpmnMessageFlow)) {
                return false;
            }
        
            BpmnMessageFlow messageFlow = (BpmnMessageFlow) elt;
            BpmnBaseElement source = messageFlow.getSourceRef();
            BpmnBaseElement target = messageFlow.getTargetRef();
        
            boolean isFromExternalParticipant = source instanceof BpmnParticipant && ((BpmnParticipant) source).getProcess() == null;
            boolean isToExternalParticipant = target instanceof BpmnParticipant && ((BpmnParticipant) target).getProcess() == null;
        
            boolean isOk;
            if (isFromExternalParticipant) {
                isOk = target instanceof BpmnIntermediateCatchEvent || target instanceof BpmnReceiveTask || isToExternalParticipant;
            } else if (source instanceof BpmnIntermediateThrowEvent) {
                isOk = target instanceof BpmnIntermediateCatchEvent || isToExternalParticipant;
            } else if (source instanceof BpmnSendTask) {
                isOk = target instanceof BpmnReceiveTask || isToExternalParticipant;
            } else {
                isOk = false;
            }
            return !isOk;
        }
        return false;
    }

    @objid ("cb3faf23-e453-4d4c-9d20-34f8f5369eb8")
    @Override
    public List<MObject> transform(AbstractDiagram diagram, ISelection selection) {
        IModelingSession session = this.moduleContext.getModelingSession();
        IUmlModel model = session.getModel();
        
        for (BpmnMessageFlowEditPart linkEditPart : SelectionHelper.toList(selection, BpmnMessageFlowEditPart.class)) {
            EditPartViewer viewer = linkEditPart.getViewer();
        
            GmBpmnMessageFlow gmLink = linkEditPart.getModel();
            BpmnBaseElement source = gmLink.getFromElement();
            BpmnBaseElement target = gmLink.getToElement();
        
            boolean isFromExternalParticipant = source instanceof BpmnParticipant && ((BpmnParticipant) source).getProcess() == null;
            boolean isToExternalParticipant = target instanceof BpmnParticipant && ((BpmnParticipant) target).getProcess() == null;
        
            if (isFromExternalParticipant) {
                if (!(target instanceof BpmnIntermediateCatchEvent) && (target instanceof BpmnReceiveTask) && !isToExternalParticipant) {
                    // Create a receive task
                    BpmnReceiveTask task = model.createBpmnReceiveTask();
                    task.setContainer((((BpmnFlowElement) target).getContainer()));
        
                    replaceTarget(linkEditPart, (BpmnIntermediateCatchEvent) target, task, viewer);
                }
            } else if (source instanceof BpmnSendTask) {
                if (!(target instanceof BpmnReceiveTask) && !isToExternalParticipant) {
                    // Create a receive task
                    BpmnReceiveTask task = model.createBpmnReceiveTask();
                    task.setContainer((((BpmnFlowElement) target).getContainer()));
        
                    replaceTarget(linkEditPart, (BpmnReceiveTask) target, task, viewer);
                }
            } else if (source instanceof BpmnIntermediateThrowEvent) {
                if (!(target instanceof BpmnIntermediateCatchEvent) && !isToExternalParticipant) {
                    // Create a catch event
                    BpmnIntermediateCatchEvent event = model.createBpmnIntermediateCatchEvent();
                    event.setContainer((((BpmnIntermediateCatchEvent) target).getContainer()));
        
                    replaceTarget(linkEditPart, (BpmnFlowNode) target, event, viewer);
                }
            } else {
                if (isToExternalParticipant) {
                    // Create a send task
                    BpmnSendTask task = model.createBpmnSendTask();
                    task.setContainer((((BpmnFlowElement) source).getContainer()));
        
                    replaceSource(linkEditPart, (BpmnFlowNode) source, task, viewer);
                } else if (target instanceof BpmnIntermediateCatchEvent) {
                    // Create a throw event
                    BpmnIntermediateThrowEvent event = model.createBpmnIntermediateThrowEvent();
                    event.setContainer((((BpmnFlowElement) source).getContainer()));
        
                    replaceSource(linkEditPart, (BpmnIntermediateCatchEvent) source, event, viewer);
                } else if (target instanceof BpmnReceiveTask) {
                    // Create a send task
                    BpmnSendTask task = model.createBpmnSendTask();
                    task.setContainer((((BpmnFlowElement) source).getContainer()));
        
                    replaceSource(linkEditPart, (BpmnReceiveTask) source, task, viewer);
                } else {
                    // Create a send task
                    BpmnSendTask task1 = model.createBpmnSendTask();
                    task1.setContainer((((BpmnFlowElement) source).getContainer()));
        
                    replaceSource(linkEditPart, (BpmnFlowNode) source, task1, viewer);
        
                    // Create a receive task
                    BpmnReceiveTask task2 = model.createBpmnReceiveTask();
                    task2.setContainer((((BpmnFlowElement) target).getContainer()));
        
                    replaceTarget(linkEditPart, (BpmnFlowNode) target, task2, viewer);
                }
            }
        }
        return null;
    }

    @objid ("0bfba8be-1b5c-4015-a032-3127d6bb6c27")
    private void replaceTarget(BpmnMessageFlowEditPart linkEditPart, BpmnFlowNode oldTarget, BpmnFlowNode newTarget, EditPartViewer viewer) {
        BpmnMessageFlow messageFlow = linkEditPart.getModel().getRepresentedElement();
        // messageFlow.setTargetRef(newTarget);
        
        Point dropLocation = ((GraphicalEditPart) linkEditPart.getTarget()).getFigure().getBounds().getLocation();
        GraphicalEditPart parentEditPart = (GraphicalEditPart) linkEditPart.getTarget().getParent().getParent();
        IGmLinkable newTargetGm = unmask(newTarget, dropLocation, parentEditPart, viewer);
        
        BpmnSequenceFlow sequenceFlow = createSequenceFlow(oldTarget, newTarget);
        unmask(sequenceFlow, dropLocation, parentEditPart, viewer);
        
        createGmSequenceFlow(messageFlow, (NodeEditPart) linkEditPart.getSource(), findNode(newTargetGm, viewer), linkEditPart);
    }

    @objid ("1841c108-cb22-4249-8112-e33389ba82af")
    private void replaceSource(BpmnMessageFlowEditPart linkEditPart, BpmnFlowNode oldSource, BpmnFlowNode newSource, EditPartViewer viewer) {
        BpmnMessageFlow messageFlow = linkEditPart.getModel().getRepresentedElement();
        // messageFlow.setSourceRef(newSource);
        
        Point dropLocation = ((GraphicalEditPart) linkEditPart.getSource()).getFigure().getBounds().getLocation();
        GraphicalEditPart parentEditPart = (GraphicalEditPart) linkEditPart.getSource().getParent().getParent();
        IGmLinkable newSourceGm = unmask(newSource, dropLocation, parentEditPart, viewer);
        
        BpmnSequenceFlow sequenceFlow = createSequenceFlow(newSource, oldSource);
        unmask(sequenceFlow, dropLocation, parentEditPart, viewer);
        
        createGmSequenceFlow(messageFlow, findNode(newSourceGm, viewer), (NodeEditPart) linkEditPart.getTarget(), linkEditPart);
    }

    @objid ("9101f136-657d-4715-bbdc-7f222b9d3870")
    private BpmnSequenceFlow createSequenceFlow(BpmnFlowNode source, BpmnFlowNode target) {
        IModelingSession session = this.moduleContext.getModelingSession();
        IUmlModel model = session.getModel();
        
        BpmnSequenceFlow sequenceFlow = model.createBpmnSequenceFlow();
        sequenceFlow.setSourceRef(target);
        sequenceFlow.setTargetRef(source);
        sequenceFlow.setContainer(source.getContainer());
        return sequenceFlow;
    }

    @objid ("2c65da6a-e150-4124-842e-8baa8f527f57")
    private IGmLinkable unmask(MObject elementToUnmask, Point dropLocation, GraphicalEditPart parentEditPart, EditPartViewer viewer) {
        final CreateRequest req = new CreateRequest();
        req.setLocation(dropLocation);
        req.setSize(new Dimension(-1, -1));
        req.setFactory(new ModelioCreationContext(elementToUnmask));
        
        Command command = parentEditPart.getCommand(req);
        if (command != null && command.canExecute()) {
            command.execute();
            if (command instanceof ICreationCommand) {
                return ((ICreationCommand<IGmLinkable>) command).getCreatedGraphicModel();
            }
        }
        return null;
    }

    @objid ("fd630ce6-b2cc-4a81-9ae4-10f3b421b50c")
    private IGmLink createGmSequenceFlow(BpmnMessageFlow messageFlow, NodeEditPart sourceNode, NodeEditPart targetNode, BpmnMessageFlowEditPart linkEditPart) {
        CreateBendedConnectionRequest creq = new CreateBendedConnectionRequest();
        ModelioLinkCreationContext ctx = new ModelioLinkCreationContext(messageFlow);
        creq.setFactory(ctx);
        
        creq.setType(RequestConstants.REQ_CONNECTION_START);
        
        creq.setSourceEditPart(sourceNode);
        creq.setTargetEditPart(null);
        creq.setLocation(sourceNode.getFigure().getBounds().getCenter());
        sourceNode.getFigure().translateToAbsolute(creq.getLocation());
        creq.getData().setRoutingMode(ConnectionRouterId.ORTHOGONAL);
        creq.getData().setSrcPoint(new Point(creq.getLocation()));
        
        EditPart newMainNode = MessageFlowTransformer.findChildEditPartFor(sourceNode, creq);
        creq.setStartCommand(newMainNode.getCommand(creq));
        creq.setSourceEditPart(newMainNode);
        
        checkCommand(creq, creq.getStartCommand());
        
        creq.setType(RequestConstants.REQ_CONNECTION_END);
        creq.setTargetEditPart(targetNode);
        creq.setLocation(targetNode.getTargetConnectionAnchor(linkEditPart).getReferencePoint());
        creq.getData().setLastPoint(new Point(creq.getLocation()));
        
        Command finishCmd = targetNode.getCommand(creq);
        
        checkCommand(creq, finishCmd);
        
        finishCmd.execute();
        return ((ICreationCommand<IGmLink>) finishCmd).getCreatedGraphicModel();
    }

    @objid ("6c184e77-c980-4298-bc1e-88aa18a454a6")
    private NodeEditPart findNode(IGmLinkable mainLinkable, EditPartViewer viewer) {
        return (NodeEditPart) viewer.getEditPartRegistry().get(mainLinkable);
    }

    @objid ("e127ec7d-385f-4d40-aacd-935a91f8d8c7")
    private static EditPart findChildEditPartFor(EditPart from, Request req) {
        for (EditPart e : (List<EditPart>) from.getChildren()) {
            EditPart targetEditPart = e.getTargetEditPart(req);
            if (targetEditPart != null) {
                return targetEditPart;
            }
        }
        
        throw new IllegalArgumentException(String.format("No child edit part in '%s' that supports %s", from, RequestHelper.toString(req)));
    }

    @objid ("0671ff9f-9287-4b29-9813-87cda8a1704d")
    private void checkCommand(Request recoReq, Command command) {
        if (command == null || !command.canExecute()) {
            String msg = String.format("%s is not excutable for %s", command, RequestHelper.toString(recoReq));
            throw new IllegalStateException(msg);
        }
    }

}
