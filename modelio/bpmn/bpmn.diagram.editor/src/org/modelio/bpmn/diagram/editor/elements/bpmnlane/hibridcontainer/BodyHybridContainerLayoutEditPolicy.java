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
package org.modelio.bpmn.diagram.editor.elements.bpmnlane.hibridcontainer;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.bpmn.diagram.editor.elements.bpmnboundaryevent.GmBpmnBoundaryEvent;
import org.modelio.bpmn.diagram.editor.elements.bpmnlane.BpmnLaneEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnlane.hibridcontainer.BodyHybridContainerEditPart.Behaviour;
import org.modelio.bpmn.diagram.editor.elements.bpmnlanesetcontainer.BpmnLaneSetContainerEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnlanesetcontainer.CreateBpmnLaneSetContainerCommand;
import org.modelio.bpmn.diagram.editor.elements.bpmnlanesetcontainer.GmBpmnLaneSetContainer;
import org.modelio.bpmn.diagram.editor.elements.bpmnlanesetcontainer.LayoutLaneSetConnectionsEditPolicyDecorator;
import org.modelio.bpmn.diagram.editor.elements.bpmnsubprocess.CreateBpmnSubProcessCommand;
import org.modelio.bpmn.diagram.editor.elements.common.policies.BpmnBoundaryEventReparentElementCommand;
import org.modelio.bpmn.diagram.editor.elements.common.policies.BpmnCreateLinkChooseNodeEditPolicy;
import org.modelio.bpmn.diagram.editor.elements.common.policies.BpmnFlowElementReparentElementCommand;
import org.modelio.diagram.elements.common.freezone.DefaultFreeZoneLayoutEditPolicy;
import org.modelio.diagram.elements.core.commands.DefaultCreateElementCommand;
import org.modelio.diagram.elements.core.commands.DefaultReparentElementCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.link.linknode.AbstractCreateLinkChooseNodeEditPolicy;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.LayoutConnectionsConstrainedLayoutEditPolicyDecorator;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Very specific hybrid edit policy that behave either like a default free zone layout edit policy OR like a lane set layout edit policy, depending on the nature of the current children of the Gm.
 */
@objid ("613166f9-55b6-11e2-877f-002564c97630")
class BodyHybridContainerLayoutEditPolicy extends AbstractEditPolicy {
    /**
     * Current behavioural state. Defaults to {@link Behavior.UNDEFINED}
     */
    @objid ("613166ff-55b6-11e2-877f-002564c97630")
    private Behaviour behaviour = Behaviour.HYBRID;

    /**
     * This will be used to "get" the behaviour of a free zone policy.
     */
    @objid ("c48b764f-59a6-11e2-ae45-002564c97630")
    private EditPolicy freeZonePolicy;

    /**
     * This will be used to "get" the behaviour of a lane set policy.
     */
    @objid ("985cf634-1c9d-4511-a1e6-58e30e706330")
    private EditPolicy laneSetPolicy;

    /**
     * C'tor.
     */
    @objid ("61316701-55b6-11e2-877f-002564c97630")
    public  BodyHybridContainerLayoutEditPolicy() {
        super();
        // Create an instance of both free zone and lane set policies.
        this.freeZonePolicy = new LayoutConnectionsConstrainedLayoutEditPolicyDecorator( new BpmnFreeZoneLayoutPolicy());
        this.laneSetPolicy = new LayoutLaneSetConnectionsEditPolicyDecorator(new BpmnLaneSetEditPolicy());
        
    }

    @objid ("61316704-55b6-11e2-877f-002564c97630")
    @Override
    public void activate() {
        super.activate();
        switch (this.behaviour) {
        case HYBRID: {
            this.freeZonePolicy.activate();
            this.laneSetPolicy.activate();
            installLinkAndNodePolicy();
            break;
        }
        case FREE_ZONE: {
            this.freeZonePolicy.activate();
            this.laneSetPolicy.deactivate();
            installLinkAndNodePolicy();
            break;
        }
        case LANE_CONTAINER: {
            this.freeZonePolicy.deactivate();
            this.laneSetPolicy.activate();
            break;
        }
        default:
            break;
        }
        
    }

    @objid ("61316707-55b6-11e2-877f-002564c97630")
    @Override
    public void deactivate() {
        switch (this.behaviour) {
        case HYBRID: {
            this.freeZonePolicy.deactivate();
            this.laneSetPolicy.deactivate();
            break;
        }
        case FREE_ZONE: {
            this.freeZonePolicy.deactivate();
            break;
        }
        case LANE_CONTAINER: {
            this.laneSetPolicy.deactivate();
            break;
        }
        default:
            break;
        }
        removeLinkAndNodePolicy();
        super.deactivate();
        
    }

    @objid ("6131670a-55b6-11e2-877f-002564c97630")
    @Override
    public void eraseSourceFeedback(Request request) {
        super.eraseSourceFeedback(request);
        switch (this.behaviour) {
        case HYBRID: {
            EditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                editPolicy.eraseSourceFeedback(request);
            }
            break;
        }
        case FREE_ZONE: {
            this.freeZonePolicy.eraseSourceFeedback(request);
            break;
        }
        case LANE_CONTAINER: {
            this.laneSetPolicy.eraseSourceFeedback(request);
            break;
        }
        default:
            break;
        }
        
    }

    @objid ("6131670e-55b6-11e2-877f-002564c97630")
    @Override
    public void eraseTargetFeedback(Request request) {
        super.eraseTargetFeedback(request);
        switch (this.behaviour) {
        case HYBRID: {
            EditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                editPolicy.eraseTargetFeedback(request);
            }
            break;
        }
        case FREE_ZONE: {
            this.freeZonePolicy.eraseTargetFeedback(request);
            break;
        }
        case LANE_CONTAINER: {
            this.laneSetPolicy.eraseTargetFeedback(request);
            break;
        }
        default:
            break;
        }
        
    }

    @objid ("61316712-55b6-11e2-877f-002564c97630")
    @Override
    public Command getCommand(Request request) {
        Command command = null;
        switch (this.behaviour) {
        case HYBRID: {
            EditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                command = editPolicy.getCommand(request);
            }
            break;
        }
        case FREE_ZONE: {
            command = this.freeZonePolicy.getCommand(request);
            break;
        }
        case LANE_CONTAINER: {
            command = this.laneSetPolicy.getCommand(request);
            break;
        }
        default:
            break;
        }
        return command;
    }

    @objid ("61316718-55b6-11e2-877f-002564c97630")
    @Override
    public EditPart getTargetEditPart(Request request) {
        EditPart targetEditPart = null;
        switch (this.behaviour) {
        case HYBRID: {
            EditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                targetEditPart = editPolicy.getTargetEditPart(request);
            }
            break;
        }
        case FREE_ZONE: {
            targetEditPart = this.freeZonePolicy.getTargetEditPart(request);
            break;
        }
        case LANE_CONTAINER: {
            targetEditPart = this.laneSetPolicy.getTargetEditPart(request);
            break;
        }
        default:
            break;
        }
        
        // Make sure the current model is editable
        GmNodeModel model = (GmNodeModel) getHost().getModel();
        if (model.isUserEditable()) {
            return targetEditPart;
        } else {
            return null;
        }
        
    }

    @objid ("6132ed7d-55b6-11e2-877f-002564c97630")
    @Override
    public void setHost(EditPart editpart) {
        super.setHost(editpart);
        this.freeZonePolicy.setHost(editpart);
        this.laneSetPolicy.setHost(editpart);
        
    }

    @objid ("6132ed81-55b6-11e2-877f-002564c97630")
    @Override
    public void showSourceFeedback(Request request) {
        switch (this.behaviour) {
        case HYBRID: {
            EditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                editPolicy.showSourceFeedback(request);
            }
            break;
        }
        case FREE_ZONE: {
            this.freeZonePolicy.showSourceFeedback(request);
            break;
        }
        case LANE_CONTAINER: {
            this.laneSetPolicy.showSourceFeedback(request);
            break;
        }
        default:
            break;
        }
        
    }

    @objid ("6132ed85-55b6-11e2-877f-002564c97630")
    @Override
    public void showTargetFeedback(Request request) {
        switch (this.behaviour) {
        case HYBRID: {
            EditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                editPolicy.showTargetFeedback(request);
            }
            break;
        }
        case FREE_ZONE: {
            this.freeZonePolicy.showTargetFeedback(request);
            break;
        }
        case LANE_CONTAINER: {
            this.laneSetPolicy.showTargetFeedback(request);
            break;
        }
        default:
            break;
        }
        
    }

    @objid ("6132ed89-55b6-11e2-877f-002564c97630")
    @Override
    public boolean understandsRequest(Request request) {
        switch (this.behaviour) {
        case HYBRID: {
            EditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                return editPolicy.understandsRequest(request);
            } else {
                return false;
            }
        }
        case FREE_ZONE: {
            return this.freeZonePolicy.understandsRequest(request);
        }
        case LANE_CONTAINER: {
            return this.laneSetPolicy.understandsRequest(request);
        }
        default:
            break;
        }
        return false;
    }

    /**
     * Sets the behaviour to adopt.
     * @param value the new behaviour.
     */
    @objid ("6132ed8f-55b6-11e2-877f-002564c97630")
    public void setBehaviour(Behaviour value) {
        // If state actually changes, update the behavior.
        if (this.behaviour != value) {
            Behaviour previousBehaviour = this.behaviour;
            this.behaviour = value;
            switch (this.behaviour) {
            case HYBRID: {
                if (previousBehaviour == Behaviour.LANE_CONTAINER) {
                    this.freeZonePolicy.activate();
                    installLinkAndNodePolicy();
                }
                if (previousBehaviour == Behaviour.FREE_ZONE) {
                    this.laneSetPolicy.activate();
                }
                break;
            }
            case FREE_ZONE: {
                if (previousBehaviour == Behaviour.LANE_CONTAINER) {
                    this.laneSetPolicy.deactivate();
                    this.freeZonePolicy.activate();
        
                    installLinkAndNodePolicy();
                }
                if (previousBehaviour == Behaviour.HYBRID) {
                    this.laneSetPolicy.deactivate();
                }
                break;
            }
            case LANE_CONTAINER: {
                if (previousBehaviour == Behaviour.FREE_ZONE) {
                    this.freeZonePolicy.deactivate();
                    this.laneSetPolicy.activate();
                }
                if (previousBehaviour == Behaviour.HYBRID) {
                    this.freeZonePolicy.deactivate();
                }
        
                removeLinkAndNodePolicy();
                break;
            }
            default:
                break;
            }
        }
        
    }

    /**
     * <p>
     * This Request analysis method is used when in hybrid mode, to determine which policy should handle a specific request.
     * </p>
     * <p>
     * Basically, if the request concerns the addition/cloning of sub lanes, then the lane set policy should handle it. Otherwise, the free zone policy is concerned.
     * </p>
     */
    @objid ("6132ed93-55b6-11e2-877f-002564c97630")
    private EditPolicy getPolicyInvolvedWith(ChangeBoundsRequest request) {
        // Involved node Edit Parts must be of a consistant type (either all lanes or none)
        // Check for inconsistency
        Boolean allLanes = null;
        for (Object editPartObj : request.getEditParts()) {
            if (editPartObj instanceof ConnectionEditPart) {
                // The request may include all connections inside the model lane, ignore them
                continue;
            }
        
            boolean isLane = editPartObj instanceof BpmnLaneEditPart;
            if (allLanes == null) {
                allLanes = Boolean.valueOf(isLane);
            } else if (allLanes != isLane) {
                // Inconsistent
                return null;
            }
        }
        
        if (allLanes == null) {
            // no node edit part in the request
            return null;
        } else {
            // Everything of the same type, return corresponding policy.
            return allLanes ? this.laneSetPolicy : this.freeZonePolicy;
        }
        
    }

    /**
     * <p>
     * This Request analysis method is used when in hybrid mode, to determine which policy should handle a specific request.
     * </p>
     * <p>
     * Basically, if the request concerns the creation of sub lanes, then the lane set policy should handle it. Otherwise, the free zone policy is concerned.
     * </p>
     */
    @objid ("6132ed98-55b6-11e2-877f-002564c97630")
    private EditPolicy getPolicyInvolvedWith(CreateRequest request) {
        final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(request);
        if (ctx == null) {
            return null;
        } else if (ctx.getJavaClass() == BpmnLane.class) {
            // Anything concerning BpmnLane may only be handled by lane set policy.
            return this.laneSetPolicy;
        } else {
            return this.freeZonePolicy;
        }
        
    }

    @objid ("6132ed9d-55b6-11e2-877f-002564c97630")
    private EditPolicy getPolicyInvolvedWith(Request request) {
        if (RequestConstants.REQ_CREATE.equals(request.getType())) {
            return getPolicyInvolvedWith((CreateRequest) request);
        } else if (RequestConstants.REQ_CONNECTION_START.equals(request.getType()) ||
                RequestConstants.REQ_CONNECTION_END.equals(request.getType())) {
            return getPolicyInvolvedWith((CreateConnectionRequest) request);
        } else if (RequestConstants.REQ_ADD.equals(request.getType()) ||
                RequestConstants.REQ_RESIZE_CHILDREN.equals(request.getType()) ||
                RequestConstants.REQ_MOVE_CHILDREN.equals(request.getType()) ||
                RequestConstants.REQ_RESIZE.equals(request.getType()) ||
                RequestConstants.REQ_MOVE.equals(request.getType()) ||
                RequestConstants.REQ_CLONE.equals(request.getType())) {
            return getPolicyInvolvedWith((ChangeBoundsRequest) request);
        }
        return null;
    }

    /**
     * Returns the current behaviour.
     * @return the current behaviour.
     */
    @objid ("6132eda3-55b6-11e2-877f-002564c97630")
    public Behaviour getBehaviour() {
        return this.behaviour;
    }

    @objid ("2c5503b4-2dcd-418e-8410-dc9a1da5d8ab")
    private void removeLinkAndNodePolicy() {
        getHost().removeEditPolicy(AbstractCreateLinkChooseNodeEditPolicy.ROLE);
    }

    @objid ("b954c99f-3821-4c4d-9536-075260a38d32")
    private void installLinkAndNodePolicy() {
        // Policy to create Link+Node for sequence flow and data association (allow to create a node as target of a new link, user can choose the kind of node)
        getHost().installEditPolicy(AbstractCreateLinkChooseNodeEditPolicy.ROLE, new BpmnCreateLinkChooseNodeEditPolicy());
        
    }

    @objid ("53e2fcae-28c3-4227-b4b0-e720a6f2051e")
    protected static boolean isLaneSet(EditPart child) {
        return child.getModel() instanceof GmBpmnLaneSetContainer;
    }

    /**
     * LayoutPolicy of Bpmn Free Zone
     */
    @objid ("61347421-55b6-11e2-877f-002564c97630")
    private static class BpmnFreeZoneLayoutPolicy extends DefaultFreeZoneLayoutEditPolicy {
        @objid ("61347425-55b6-11e2-877f-002564c97630")
        @Override
        protected boolean canHandle(MClass metaclass, String dep) {
            if (BpmnFlowElement.class.isAssignableFrom(metaclass.getJavaInterface()) || BpmnLane.class.isAssignableFrom(metaclass.getJavaInterface())) {
                return true;
            }
            return false;
        }

        @objid ("6134742d-55b6-11e2-877f-002564c97630")
        @Override
        protected Command getCreateCommand(CreateRequest request) {
            ModelioCreationContext ctx = ModelioCreationContext.lookRequest(request);
            
            if (ctx == null) {
                return null;
            } else {
                final Class<? extends MObject> cls = ctx.getJavaClass();
                MObject hostElement = getHostElement();
                MObject elementToUnmask = ctx.getElementToUnmask();
                GmCompositeNode gmParentNode = getHostCompositeNode();
            
                if (cls == BpmnLane.class) {
                    if (elementToUnmask != null) {
                        return null;
                    }
            
                    Object requestConstraint = getConstraintFor(request);
                    return new CreateBpmnLaneSetContainerCommand(hostElement, gmParentNode, ctx, requestConstraint, null);
                }
            
                while (hostElement instanceof BpmnLane) {
                    hostElement = hostElement.getCompositionOwner().getCompositionOwner();
                }
            
                if (elementToUnmask != null) {
                    if (gmParentNode.canUnmask(elementToUnmask)) {
                        Object requestConstraint = getConstraintFor(request);
                        return new DefaultCreateElementCommand(hostElement,
                                gmParentNode,
                                ctx,
                                requestConstraint);
                    } else {
                        return null;
                    }
                }
            
                MClass metaclassToCreate = ctx.getMetaclass();
                String depName = ctx.getDependencyName();
            
                MExpert expert = hostElement.getMClass().getMetamodel().getMExpert();
                boolean returnCommand = expert.canCompose(hostElement.getMClass(), metaclassToCreate, depName);
            
                if (returnCommand) {
                    Object requestConstraint = getConstraintFor(request);
                    if (BpmnSubProcess.class.isAssignableFrom(ctx.getJavaClass())) {
                        return new CreateBpmnSubProcessCommand(hostElement,
                                gmParentNode,
                                ctx,
                                requestConstraint);
                    } else {
                        return new CreateBpmnFlowElementCommand(hostElement,
                                gmParentNode,
                                ctx,
                                requestConstraint);
                    }
                }
            }
            return null;
        }

        @objid ("61347433-55b6-11e2-877f-002564c97630")
        @Override
        protected Command createAddCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
            GmNodeModel gmmodel = (GmNodeModel) child.getModel();
            MObject element = gmmodel.getRelatedElement();
            if (element instanceof BpmnBoundaryEvent) {
                return new BpmnBoundaryEventReparentElementCommand(getHostElement(),
                        getHostCompositeNode(),
                        (GmNodeModel) child.getModel(),
                        constraint);
            } else if (element instanceof BpmnFlowElement) {
                return new BpmnFlowElementReparentElementCommand(getHostElement(),
                        getHostCompositeNode(),
                        (GmNodeModel) child.getModel(),
                        constraint);
            } else {
                return new DefaultReparentElementCommand(getHostElement(),
                        getHostCompositeNode(),
                        (GmNodeModel) child.getModel(),
                        constraint);
            }
            
        }

        @objid ("61347439-55b6-11e2-877f-002564c97630")
        @Override
        public EditPart getTargetEditPart(Request request) {
            switch ((String) request.getType()) {
            case RequestConstants.REQ_CONNECTION_START:
            case RequestConstants.REQ_CONNECTION_END:
                CreateConnectionRequest crequest = (CreateConnectionRequest) request;
                if (crequest.getTargetEditPart() instanceof BpmnLaneSetContainerEditPart) {
                    return null;
                }
                break;
            case RequestConstants.REQ_CREATE:
                ModelioCreationContext ctx = ModelioCreationContext.lookRequest((CreateRequest) request);
                if (ctx != null) {
                    Class<? extends MObject> cls = ctx.getJavaClass();
                    if (cls == BpmnLaneSet.class) {
                        if (ctx.getElementToUnmask() == null || !Objects.equals(getHostElement(), ctx.getElementToUnmask().getCompositionOwner())) {
                            return null;
                        }
                    } else if (cls == BpmnBoundaryEvent.class) {
                        return null;
                    }
                }
                break;
            case RequestConstants.REQ_ADD:
                ChangeBoundsRequest add_request = (ChangeBoundsRequest) request;
                for (Object element : add_request.getEditParts()) {
                    EditPart editpart = (EditPart) element;
                    if (editpart.getModel() instanceof GmBpmnBoundaryEvent) {
                        return null;
                    }
                }
                break;
            default:
                break;
            }
            return super.getTargetEditPart(request);
        }

        @objid ("8b784de5-6e7c-4484-ba62-8695ffc625d2")
        @Override
        protected void decorateChild(EditPart child) {
            if (!isLaneSet(child)) {
                super.decorateChild(child);
            }
            
        }

        @objid ("12b12153-1fef-4c90-97d9-98080f921a32")
        @Override
        protected void undecorateChild(EditPart child) {
            if (!isLaneSet(child)) {
                super.undecorateChild(child);
            }
            
        }

        @objid ("dc45f2fc-f788-46b0-aa0b-11e0990804cb")
        @Override
        protected Object getConstraintFor(ChangeBoundsRequest request, GraphicalEditPart child) {
            IFigure childFig = child.getFigure();
            
            // Use the constraint instead of the figure bounds
            // because the layouter constraints the bounds to be inside the container.
            Rectangle currentConstraint = getCurrentConstraintFor(child);
            
            Rectangle original;
            if (currentConstraint != null) {
                original = new PrecisionRectangle(currentConstraint);
                if (original.x < 0) {
                    original.x = 0;
                }
                if (original.y < 0) {
                    original.y = 0;
                }
                original.translate(getLayoutOrigin());
            } else {
                original = new PrecisionRectangle(childFig.getBounds());
                currentConstraint = original.getCopy();
            }
            
            Rectangle rect = original.getCopy();
            childFig.translateToAbsolute(rect);
            rect = request.getTransformedRectangle(rect);
            translateFromAbsoluteToLayoutRelative(rect);
            
            if (request.getSizeDelta().equals(0, 0)) {
                Rectangle cons = currentConstraint;
                if (cons != null) {
                    // Bug 86473 allows for unintended use of this method
                    rect.setSize(cons.width, cons.height);
                }
            } else {
                // resize, nothing else to do
            }
            return getConstraintFor(rect);
        }

        @objid ("621c6360-55b6-11e2-877f-002564c97630")
        private static class CreateBpmnFlowElementCommand extends DefaultCreateElementCommand {
            @objid ("621de9c4-55b6-11e2-877f-002564c97630")
            public  CreateBpmnFlowElementCommand(MObject parentNode, GmCompositeNode parentElement, ModelioCreationContext context, Object constraint) {
                super(parentNode, parentElement, context, constraint);
            }

            @objid ("621de9d0-55b6-11e2-877f-002564c97630")
            @Override
            public void execute() {
                final IGmDiagram diagram = getParentNode().getDiagram();
                
                MObject newElement = getContext().getElementToUnmask();
                
                if (newElement == null) {
                    IModelManager modelManager = diagram.getModelManager();
                
                    // Create the Element...
                    final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
                    MClass mc = getContext().getMetaclass();
                    newElement = modelFactory.createElement(mc);
                
                    // The new element must be attached to its parent using the composition dependency
                    // provided by the context.
                    // If the context provides a null dependency, use the default dependency recommended by the metamodel
                    MExpert expert = mc.getMetamodel().getMExpert();
                    MDependency effectiveDependency = expert.getDefaultCompositionDep(getParentElement(), newElement);
                    // Attach to parent
                    if (effectiveDependency == null) {
                        throw new IllegalStateException("Cannot find a composition dependency to attach " +
                                Objects.toString(newElement) +
                                " to " +
                                Objects.toString(getParentElement()));
                        // ... and attach it to its parent.
                    }
                
                    getParentElement().mGet(effectiveDependency).add(newElement);
                
                    // Attach the stereotype if needed.
                    if (getContext().getStereotype() != null && newElement instanceof ModelElement) {
                        ((ModelElement) newElement).getExtension().add(getContext().getStereotype());
                    }
                
                    if (newElement instanceof BpmnFlowElement &&
                            getParentNode().getRelatedElement() instanceof BpmnLane) {
                        BpmnLane lane = (BpmnLane) getParentNode().getRelatedElement();
                        BpmnFlowElement flowElement = (BpmnFlowElement) newElement;
                        // flowElement.addBpmnLaneRefs(lane);
                        lane.getFlowElementRef().add(flowElement);
                    }
                
                    // Configure element from properties
                    final IElementConfigurator elementConfigurer = modelManager.getModelServices().getElementConfigurer();
                    elementConfigurer.configure(newElement, getContext().getProperties());
                
                    // Set default name
                    IElementNamer elementNamer = modelManager.getModelServices().getElementNamer();
                    newElement.setName(elementNamer.getUniqueName(newElement));
                
                }
                
                // Show the new element in the diagram (ie create its Gm )
                GmNodeModel gm = diagram.unmask(getParentNode(), newElement, getConstraint());
                
                this.mainLinkable = gm;
                
                afterUnmask(newElement, gm);
                
            }

        }

    }

}
