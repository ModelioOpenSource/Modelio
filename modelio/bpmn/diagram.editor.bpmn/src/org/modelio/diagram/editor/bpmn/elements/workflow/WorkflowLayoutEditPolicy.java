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

package org.modelio.diagram.editor.bpmn.elements.workflow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.tools.ToolUtilities;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.BpmnLaneEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnlanesetcontainer.BpmnLaneSetContainerEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnlanesetcontainer.CreateBpmnLaneSetContainerCommand;
import org.modelio.diagram.editor.bpmn.elements.bpmnsubprocess.CreateBpmnSubProcessCommand;
import org.modelio.diagram.editor.bpmn.elements.diagrams.GmBpmnDiagramStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.diagrams.processdesign.BpmnProcessDesignDiagramEditPart;
import org.modelio.diagram.editor.bpmn.elements.policies.BpmnBoundaryEventReparentElementCommand;
import org.modelio.diagram.editor.bpmn.elements.policies.BpmnFlowElementReparentElementCommand;
import org.modelio.diagram.editor.bpmn.elements.policies.BpmnLaneReparentElementCommand;
import org.modelio.diagram.elements.common.freezone.DefaultFreeZoneLayoutEditPolicy;
import org.modelio.diagram.elements.common.freezone.ILayoutAssistant;
import org.modelio.diagram.elements.core.commands.DefaultReparentElementCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.commands.NodeChangeLayoutCommand;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.drawings.core.GmDrawing;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("ca885454-ad0e-4613-b07a-034002fece18")
class WorkflowLayoutEditPolicy extends DefaultFreeZoneLayoutEditPolicy {
    @objid ("245ae62f-78ef-42d9-a0cc-572710fb40e3")
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        ModelioCreationContext ctx = ModelioCreationContext.lookRequest(request);
        if (ctx != null) {
            final Class<? extends MObject> cls = ctx.getJavaClass();
            MObject hostElement = getHostElement();
            MObject elementToUnmask = ctx.getElementToUnmask();
            GmWorkflow gmParentNode = getHostCompositeNode();
        
            if (cls == BpmnLaneSet.class) {
                if (elementToUnmask != null && !gmParentNode.canUnmask(elementToUnmask)) {
                    return null;
                }
        
                Object requestConstraint = getConstraintFor(request);
                return new CreateBpmnLaneSetContainerCommand(hostElement, gmParentNode, ctx, requestConstraint, null);
            } else if (cls == BpmnLane.class) {
                if (elementToUnmask != null && !gmParentNode.canUnmask(elementToUnmask)) {
                    return null;
                }
        
                Object requestConstraint = getConstraintFor(request);
                return new CreateBpmnLaneSetContainerCommand(hostElement, gmParentNode, ctx, requestConstraint, null);
            } else if (BpmnSubProcess.class.isAssignableFrom(cls)) {
                if (elementToUnmask != null && !gmParentNode.canUnmask(elementToUnmask)) {
                    return null;
                }
        
                MClass metaclassToCreate = ctx.getMetaclass();
        
                if (gmParentNode.canCreate(metaclassToCreate.getJavaInterface())) {
                    MExpert expert = metaclassToCreate.getMetamodel().getMExpert();
                    if (expert.canCompose(hostElement.getMClass(), metaclassToCreate, ctx.getDependencyName())) {
                        final Object requestConstraint = getConstraintFor(request);
                        return new CreateBpmnSubProcessCommand(hostElement, gmParentNode, ctx, requestConstraint);
                    }
                }
                return null;
            }
        }
        return super.getCreateCommand(request);
    }

    @objid ("637686f1-cd06-4dfd-9a23-0ca769f30d07")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (RequestConstants.REQ_CREATE.equals(request.getType())) {
            CreateRequest createRequest = (CreateRequest) request;
            return getTargetEditPartForCreate(createRequest);
        } else if (RequestConstants.REQ_ADD.equals(request.getType()) || RequestConstants.REQ_MOVE.equals(request.getType())) {
            WorkflowEditPart host = getHost();
        
            // Make sure the moved elements are not part of the diagram itself, aka generic nodes.
            for (Object ep : ((ChangeBoundsRequest) request).getEditParts()) {
                EditPart editPart = (EditPart) ep;
                if (editPart.getParent() instanceof BpmnProcessDesignDiagramEditPart) {
                    return null;
                }
            }
        
            // Let the lane set container if any deal with the request
            for (Object child : host.getChildren()) {
                if (child instanceof BpmnLaneSetContainerEditPart) {
                    EditPart targetEditPart = ((BpmnLaneSetContainerEditPart) child).getTargetEditPart(request);
                    if (targetEditPart != null) {
                        return targetEditPart;
                    }
                }
            }
        
            // No lane in the workflow, the workflow will handle the request
            return host;
        }
        
        if (getHostCompositeNode().isUserEditable()) {
            return super.getTargetEditPart(request);
        } else {
            return null;
        }
    }

    @objid ("778d3a66-f041-4178-bb14-9a79669d2721")
    @Override
    protected boolean canHandle(MClass metaclass, String dep) {
        return getHostCompositeNode().canCreate(metaclass.getJavaInterface());
    }

    @objid ("47f4aba4-b5ba-4c43-ae35-1f9fcd582045")
    @Override
    protected MObject getHostElement() {
        // AbstractDiagram diagram = (AbstractDiagram) super.getHostElement();
        // return diagram.getOrigin();
        return getRoot(getHostCompositeNode().getRelatedElement());
    }

    @objid ("5d44ac65-e157-4423-ab92-379c11a87544")
    public MObject getRoot(MObject element) {
        if (element instanceof AbstractDiagram) {
            return ((AbstractDiagram) element).getOrigin();
        } else if (element instanceof BpmnProcess || element instanceof BpmnSubProcess) {
            return element;
        }
        return getRoot(element.getCompositionOwner());
    }

    @objid ("ded5857b-81e1-4fc0-a943-6f56f07d9e2c")
    @Override
    protected Command createAddCommand(ChangeBoundsRequest request, final EditPart child, final Object constraint) {
        if (child.getModel() instanceof GmNodeModel) {
            GmNodeModel gmmodel = (GmNodeModel) child.getModel();
            MObject element = gmmodel.getRelatedElement();
            if (element instanceof BpmnBoundaryEvent) {
                if (getHostElement() instanceof BpmnActivity) {
                    return new BpmnBoundaryEventReparentElementCommand(getHostElement(), getHostCompositeNode(), (GmNodeModel) child.getModel(), constraint);
                }
            } else if (element instanceof BpmnLane) {
                return new BpmnLaneReparentElementCommand(getHostElement(), getHostCompositeNode(), (GmNodeModel) child.getModel(), constraint);
            } else if (element instanceof BpmnFlowElement) {
                return new BpmnFlowElementReparentElementCommand(getHostElement(), getHostCompositeNode(), (GmNodeModel) child.getModel(), constraint);
            } else if (element instanceof BpmnMessage) {
                return null;
            } else if (element instanceof Note || element instanceof AbstractResource) {
                return null;
            } else {
                return new DefaultReparentElementCommand(getHostElement(), getHostCompositeNode(), (GmNodeModel) child.getModel(), constraint);
            }
            return null;
        } else {
            return super.createAddCommand(request, child, constraint);
        }
    }

    @objid ("c6e9378b-028a-4ca3-8454-fc65bad77f3b")
    @Override
    protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
        // if child is a 'node' it usually can be resized and/or moved
        if (child instanceof AbstractNodeEditPart || child.getModel() instanceof GmDrawing) {
            final NodeChangeLayoutCommand command = new NodeChangeLayoutCommand();
            command.setModel(child.getModel());
            command.setConstraint(constraint);
            return command;
        }
        return null;
    }

    @objid ("85d52961-da9e-4515-8e10-ca5a52f88f8c")
    @Override
    protected Object getConstraintFor(CreateRequest request) {
        ModelioCreationContext ctx = ModelioCreationContext.fromRequest(request);
        Object constraint = super.getConstraintFor(request);
        if (constraint instanceof Rectangle && (ctx.getJavaClass() == BpmnLane.class || ctx.getJavaClass() == BpmnLaneSet.class)) {
            Rectangle rectConstraint = (Rectangle) constraint;
        
            boolean isHorizontalLaneOrientation = getHostCompositeNode().getDiagram().getDisplayedStyle().getProperty(GmBpmnDiagramStyleKeys.HORIZONTAL_LANES);
            if (isHorizontalLaneOrientation) {
                if (rectConstraint.width == -1) {
                    rectConstraint.width = 200;
                }
        
                for (EditPart children : new ArrayList<EditPart>(getHost().getChildren())) {
                    if (children instanceof BpmnLaneEditPart) {
                        Rectangle childrenPos = ((BpmnLaneEditPart) children).getFigure().getBounds();
                        rectConstraint.y = childrenPos.y;
                        rectConstraint.height = childrenPos.height;
                    }
                }
            } else {
                if (rectConstraint.height == -1) {
                    rectConstraint.height = 200;
                }
        
                for (EditPart children : new ArrayList<EditPart>(getHost().getChildren())) {
                    if (children instanceof BpmnLaneEditPart) {
                        Rectangle childrenPos = ((BpmnLaneEditPart) children).getFigure().getBounds();
                        rectConstraint.x = childrenPos.x;
                        rectConstraint.width = childrenPos.width;
                    }
                }
            }
        
        }
        return constraint;
    }

    /**
     * For diagram the default feedback provided by the base class is ugly because it has a background Specialize and remove the background.
     */
    @objid ("d962bce9-f57b-4a23-b728-4d229b82b6ce")
    @Override
    public void showTargetFeedback(final Request request) {
        super.showTargetFeedback(request);
        if (this.highlight != null) {
            this.highlight.setOpaque(false);
            this.highlight.setBackgroundColor(null);
        }
    }

    /**
     * Redefined to avoid intersections during resize.
     */
    @objid ("86e1aa67-642e-4e80-b5b6-def831fde385")
    @Override
    protected Command getChangeConstraintCommand(ChangeBoundsRequest request) {
        CompoundCommand finalCommand = new CompoundCommand();
        
        // Call super to get the child resize command
        Command cmd = super.getChangeConstraintCommand(request);
        finalCommand.add(cmd);
        
        ILayoutAssistant helper = getLayoutAssistant(request);
        
        // build commands for initial and added requests
        // build commands for moved bend points
        createLayoutAssistantCommands(helper, finalCommand);
        List<Object> editParts = request.getEditParts();
        for (Object ep : editParts) {
            if (ep instanceof BpmnLaneSetContainerEditPart) {
                createMoveBendpointCommands((BpmnLaneSetContainerEditPart) ep, finalCommand, request.getMoveDelta());
            }
        }
        return finalCommand.isEmpty() ? null : finalCommand.unwrap();
    }

    @objid ("1f628c2d-9690-4f7f-9ad6-36f23aaa774d")
    @Override
    public Command getCommand(Request request) {
        if (true) {
            return super.getCommand(request);
        } else {
            return null;
        }
    }

    @objid ("6c5d4504-bc3a-4c7e-b392-81b3b5bf70ea")
    @Override
    protected final GmWorkflow getHostCompositeNode() {
        return (GmWorkflow) super.getHostCompositeNode();
    }

    @objid ("a5430af7-81f5-4bda-9720-3ab6f7fb291c")
    @Override
    public final WorkflowEditPart getHost() {
        return (WorkflowEditPart) super.getHost();
    }

    @objid ("b94706cc-8967-4dcb-a766-75f901f304a5")
    @Override
    protected Point getLayoutOrigin() {
        IFigure layoutContainer = getLayoutContainer();
        if (layoutContainer.getLayoutManager() instanceof XYLayout) {
            return super.getLayoutOrigin();
        } else {
            return layoutContainer.getClientArea().getLocation();
        }
    }

    @objid ("39b05a7a-e3b8-4877-a8e2-b89b3c810d6c")
    protected EditPart getTargetEditPartForCreate(CreateRequest createRequest) {
        final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(createRequest);
        if (ctx != null) {
            if (BpmnLane.class.isAssignableFrom(ctx.getMetaclass().getJavaInterface())) {
                for (Object child : getHost().getChildren()) {
                    if (child instanceof BpmnLaneSetContainerEditPart) {
                        return (BpmnLaneSetContainerEditPart) child;
                    }
                }
            }
        
            MObject elementToUnmask = ctx.getElementToUnmask();
            if (elementToUnmask instanceof BpmnLaneSet || elementToUnmask instanceof BpmnLane) {
                if (canHandle(ctx.getMetaclass(), ctx.getDependencyName())) {
                    return getHost();
                }
            }
        }
        
        if (getHostCompositeNode().isUserEditable()) {
            return super.getTargetEditPart(createRequest);
        } else {
            return null;
        }
    }

    @objid ("27e69296-2424-4242-83ea-2792a3eab10f")
    private void createMoveBendpointCommands(BpmnLaneSetContainerEditPart ep, CompoundCommand finalCommand, Point move) {
        // Add all links :
        // - linking any two nodes that are in that list or have an ancestor in that list
        // - understanding the request
        List<GraphicalEditPart> editParts = new ArrayList<>();
        editParts.add(ep);
        
        Set<LinkEditPart> linksToAdd = new HashSet<>();
        do {
            linksToAdd.clear();
            computeAllLinksFor(editParts, linksToAdd);
            editParts.addAll(linksToAdd);
            // Do it again if some links were added (this might trigger some new links, because of links on links!).
        } while (!linksToAdd.isEmpty());
        
        editParts.remove(ep);
        
        for (GraphicalEditPart lep : editParts) {
            ChangeBoundsRequest req = new ChangeBoundsRequest();
            req.setType(RequestConstants.REQ_MOVE);
            req.setEditParts(lep);
            req.setMoveDelta(move);
        
            Command com = lep.getCommand(req);
            if (com != null && com.canExecute()) {
                finalCommand.add(com);
            }
        }
    }

    @objid ("b9275000-8878-4aa1-9e40-636336eec595")
    private void computeAllLinksFor(final List<GraphicalEditPart> operationSet, final Set<LinkEditPart> linksToAdd) {
        Set<GraphicalEditPart> transitiveChildren = new HashSet<>();
        for (GraphicalEditPart object : operationSet) {
            WorkflowLayoutEditPolicy.getAllChildrenInDiagram(object, transitiveChildren);
        }
        for (GraphicalEditPart child : transitiveChildren) {
            List<GraphicalEditPart> links = child.getSourceConnections();
            for (GraphicalEditPart link : links) {
                if (isLinkToInclude(link, operationSet, null)) {
                    linksToAdd.add((LinkEditPart) link);
                }
            }
        
            links = child.getTargetConnections();
            for (GraphicalEditPart link : links) {
                if (isLinkToInclude(link, operationSet, null)) {
                    linksToAdd.add((LinkEditPart) link);
                }
        
            }
        }
    }

    /**
     * Returns the transitive child edit part set of the given parent <code>GraphicalEditPart</code>.
     * 
     * Prunes embedded diagram edit parts from the result.
     * @param parentEditPart the parent graphical edit part for which to retrieve the transitive child edit part set.
     * @return the transitive child edit part set
     */
    @objid ("ea3645f7-f85d-4f7d-9248-05e876a41d3b")
    private static Set<GraphicalEditPart> getAllChildrenInDiagram(GraphicalEditPart parentEditPart, Set<GraphicalEditPart> transitiveChildren) {
        List<GraphicalEditPart> children = parentEditPart.getChildren();
        transitiveChildren.addAll(children);
        for (GraphicalEditPart child : children) {
            if (!(child.getModel() instanceof IGmDiagram)) {
                WorkflowLayoutEditPolicy.getAllChildrenInDiagram(child, transitiveChildren);
            }
        }
        return transitiveChildren;
    }

    @objid ("319ff823-3bec-4e8c-97cd-52b12abe465b")
    private boolean isLinkToInclude(GraphicalEditPart link, final List<GraphicalEditPart> operationSet, ChangeBoundsRequest request) {
        if (link instanceof LinkEditPart && !operationSet.contains(link)) {
            LinkEditPart linkEditPart = (LinkEditPart) link;
            EditPart linkSource = linkEditPart.getSource();
            EditPart linkTarget = linkEditPart.getTarget();
            boolean sourceInSet = linkSource == null || ToolUtilities.isAncestorContainedIn(operationSet,
                    linkSource);
            boolean targetInSet = linkTarget == null || ToolUtilities.isAncestorContainedIn(operationSet,
                    linkTarget);
        
            if (sourceInSet && targetInSet) {
                return true;
            }
        }
        return false;
    }

}
