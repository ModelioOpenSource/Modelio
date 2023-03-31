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
package org.modelio.diagram.elements.common.freezone;

import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionDimension;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagramStyleKeys;
import org.modelio.diagram.elements.common.abstractdiagram.LayoutAssistantStyleKeys;
import org.modelio.diagram.elements.core.commands.DefaultCloneElementCommand;
import org.modelio.diagram.elements.core.commands.DefaultCreateElementCommand;
import org.modelio.diagram.elements.core.commands.DefaultEditCreatedElementCommand;
import org.modelio.diagram.elements.core.commands.DefaultReparentElementCommand;
import org.modelio.diagram.elements.core.commands.DefaultSelectElementCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.commands.NodeChangeLayoutCommand;
import org.modelio.diagram.elements.core.commands.PostLayoutCommand;
import org.modelio.diagram.elements.core.figures.ChainedLayout;
import org.modelio.diagram.elements.core.figures.FigureUtilities2;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DefaultNodeResizableEditPolicy;
import org.modelio.diagram.elements.core.requests.RequestTypes;
import org.modelio.diagram.elements.drawings.core.GmDrawing;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specialization of the {@link XYLayoutEditPolicy} providing:
 * <ul>
 * <li>actual commands for usual requests and handling DROP requests.
 * <li>a smart (colored) feedback.
 * <li>a {@link #getLayoutAssistant(ChangeBoundsRequest) layout assistant} to use on children move/resize.
 * </ul>
 */
@objid ("7e30d540-1dec-11e2-8cad-001ec947c8cc")
public class BaseFreeZoneLayoutEditPolicy extends XYLayoutEditPolicy {
    @objid ("e252b106-9395-4fb4-8791-79781bea7fb0")
    protected IFigure highlight = null;

    @objid ("4f316319-4cd5-4a24-a431-21852da155d1")
    private XYLayout xyLayout;

    /**
     * Request key to get the intersection remover helper in a request extended data.
     */
    @objid ("459ba26d-b74f-4506-a2a1-61d0ed308424")
    private Object intersectionsRemoverKey = new Object() {
                    @Override
                    public String toString() {
                        return "Intersection remover for " + BaseFreeZoneLayoutEditPolicy.this.toString();
                    }
                };

    @objid ("7e333790-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void eraseTargetFeedback(Request request) {
        if (RequestConstants.REQ_ADD.equals(request.getType()) || RequestConstants.REQ_CREATE.equals(request.getType()) || RequestTypes.UNMASK_OR_CREATE_CHILDREN.equals(request.getType())) {
        
            if (this.highlight != null) {
                removeFeedback(this.highlight);
                this.highlight = null;
            }
        }
        
        // Workaround GEF bug in super() where REQ_RESIZE_CHILDREN is tested instead of REQ_RESIZE
        if (RequestConstants.REQ_RESIZE.equals(request.getType())) {
            eraseLayoutTargetFeedback(request);
        }
        
        super.eraseTargetFeedback(request);
        
    }

    @objid ("7e333796-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (RequestConstants.REQ_CREATE.equals(request.getType()) || RequestTypes.UNMASK_OR_CREATE_CHILDREN.equals(request.getType())) {
            final CreateRequest createRequest = (CreateRequest) request;
            return getTargetEditPart(createRequest);
        }
        if (RequestConstants.REQ_MOVE.equals(request.getType()) || RequestConstants.REQ_ADD.equals(request.getType()) || RequestConstants.REQ_CLONE.equals(request.getType())) {
            // Special case: MOVE and ADD request can be tricky: depending on the "previous loop" of the tool sending
            // the request, an ADD request can be send for a simple graphic move, and a MOVE request can be sent for an
            // actual graphic re-parenting. Let's analyse the request a bit to determine how to handle it.
            final ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) request;
            if (isMove(changeBoundsRequest)) {
                // This is a simple graphic move inside this container, it should be accepted.
                return getHost();
            } else {
                // This is a clone or a graphic re-parenting: it probably involves Ob model changes and such, so
                // further analysis is needed to decide.
                return getTargetEditPart(changeBoundsRequest);
            }
        }
        return null;
    }

    @objid ("7e3337a0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void showTargetFeedback(Request request) {
        if (RequestConstants.REQ_ADD.equals(request.getType()) || RequestConstants.REQ_CREATE.equals(request.getType()) || RequestTypes.UNMASK_OR_CREATE_CHILDREN.equals(request.getType())) {
        
            // compute highlight type
            final Command c = getHost().getCommand(request);
            FigureUtilities2.HighlightType hightlightType = null;
            if (c == null) {
                hightlightType = FigureUtilities2.HighlightType.ERROR;
            } else if (c.canExecute()) {
                hightlightType = FigureUtilities2.HighlightType.SUCCESS;
            } else {
                hightlightType = FigureUtilities2.HighlightType.WARNING;
            }
        
            // create the highlight figure if it does not exists
            if (this.highlight == null) {
                // create a highlight figure
                this.highlight = FigureUtilities2.createHighlightFigure(getFeedbackLayer(), getTargetFeedbackFigure(request), hightlightType);
                // add the highlight figure to the feedback layer
                getFeedbackLayer().add(this.highlight);
            }
            // configure the highlight figure
            FigureUtilities2.updateHighlightType(this.highlight, hightlightType);
        }
        
        // Workaround GEF bug in super() where REQ_RESIZE_CHILDREN is tested instead of REQ_RESIZE
        if (RequestConstants.REQ_RESIZE.equals(request.getType())) {
            showLayoutTargetFeedback(request);
        }
        
        super.showTargetFeedback(request);
        
    }

    /**
     * Returns whether this edit policy can handle this metaclass (either through simple or smart behavior). Default behavior is to accept any metaclass that can be child (in the CreationExpert's understanding) of the host's metaclass This method should be
     * overridden by subclasses to add specific the behavior.
     * @param metaclass the metaclass to handle.
     * @return true if this policy can handle the metaclass.
     */
    @objid ("7e3337a6-1dec-11e2-8cad-001ec947c8cc")
    protected boolean canHandle(MClass metaclass, String dep) {
        final MObject hostElement = getHostElement();
        if (hostElement == null) {
            return false;
        }
        MMetamodel mm = hostElement.getMClass().getMetamodel();
        return mm.getMExpert().canCompose(hostElement.getMClass(), metaclass, dep) && getHostCompositeNode().canCreate(metaclass.getJavaInterface());
    }

    /**
     * @deprecated Don't call or override, see {@link #createAddCommand(ChangeBoundsRequest, EditPart, Object)} instead.
     */
    @objid ("7e3337ae-1dec-11e2-8cad-001ec947c8cc")
    @Deprecated
    @Override
    protected final Command createAddCommand(EditPart child, Object constraint) {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated Don't call or override, see {@link #createChangeConstraintCommand(ChangeBoundsRequest, EditPart, Object)} instead.
     */
    @objid ("7e3337b9-1dec-11e2-8cad-001ec947c8cc")
    @Deprecated
    @Override
    protected final Command createChangeConstraintCommand(EditPart child, Object constraint) {
        throw new UnsupportedOperationException();
    }

    @objid ("7e3337c4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPolicy createChildEditPolicy(EditPart child) {
        // if child is a node that can provide its own policy (maybe for keeping
        // specific geometric needs, etc) return it.
        if (child instanceof AbstractNodeEditPart) {
            final AbstractNodeEditPart childNode = (AbstractNodeEditPart) child;
            final SelectionEditPolicy childPolicy = childNode.getPreferredDragRolePolicy(RequestConstants.REQ_RESIZE);
            if (childPolicy != null) {
                return childPolicy;
            }
        }
        // default
        return new DefaultNodeResizableEditPolicy();
    }

    /**
     * Same as super implementation, except we filter the connection editparts.
     */
    @objid ("7e37fc45-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getAddCommand(final Request genericReq) {
        ChangeBoundsRequest request = (ChangeBoundsRequest) genericReq;
        List<GraphicalEditPart> editParts = request.getEditParts();
        CompoundCommand command = new CompoundCommand();
        command.setDebugLabel("Add in " + getClass().getSimpleName());//$NON-NLS-1$
        
        for (GraphicalEditPart child : editParts) {
            if (child instanceof ConnectionEditPart) {
                command.add(child.getCommand(genericReq));
            } else {
                command.add(createAddCommand(request, child, translateToModelConstraint(getConstraintFor(request, child))));
            }
        }
        return command.unwrap();
    }

    @objid ("7e3337ce-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getCloneCommand(ChangeBoundsRequest request) {
        if (getHost().getModel() instanceof GmCompositeNode) {
            MObject targetElement = getHostElement();
            MExpert metaUtils = targetElement.getMClass().getMetamodel().getMExpert();
        
            final GmCompositeNode hostModel = getHostCompositeNode();
            final CompoundCommand command = new CompoundCommand();
            for (final Object editPartObj : request.getEditParts()) {
                final GraphicalEditPart copiedEPart = (GraphicalEditPart) editPartObj;
                if (copiedEPart.getModel() instanceof GmModel) {
                    final GmModel copiedGmModel = (GmModel) copiedEPart.getModel();
                    final MObject copiedElement = copiedGmModel.getRelatedElement();
                    command.add(createCloneChildCommand(request, metaUtils, targetElement, hostModel, copiedEPart, copiedElement));
                }
            }
            return command.unwrap();
        }
        return null;
    }

    /**
     * Called by {@link #getCloneCommand(ChangeBoundsRequest)} to create a command for a edit part to clone.
     * <p>
     * May be redefined by sub classes to make a special command.
     * @param request the {@link RequestConstants#REQ_CLONE} request
     * @param metaUtils the metamodel expert for the host element
     * @param hostElement the host model element
     * @param hostModel the host graphic model
     * @param copiedEPart the copied edit part
     * @param copiedElement the copied related model element
     * @return the created command or null.
     * @since 5.1.0
     */
    @objid ("8d17c9ea-b686-4f8c-a34c-018b17e4b0b1")
    protected Command createCloneChildCommand(ChangeBoundsRequest request, MExpert metaUtils, MObject hostElement, final GmCompositeNode hostModel, final GraphicalEditPart copiedEPart, final MObject copiedElement) {
        if (hostElement instanceof BpmnLane && copiedElement instanceof BpmnFlowElement) {
            // TODO : move this in a sub class
            final Object requestConstraint = translateToModelConstraint(getConstraintFor(request, copiedEPart));
            return new BpmnCloneFlowElementCommand(hostModel, (BpmnLane) hostElement, (BpmnFlowElement) copiedElement,
                    requestConstraint);
        } else if (metaUtils.canCompose(hostElement, copiedElement, null)) {
            final Object requestConstraint = translateToModelConstraint(getConstraintFor(request, copiedEPart));
            return new DefaultCloneElementCommand(hostModel, hostElement, copiedElement,
                    requestConstraint);
        }
        return null;
    }

    /**
     * Generates a draw2d constraint for the given CreateRequest.
     * <p>
     * Redefined to making sure that the constraint doesn't violate the "min size".
     */
    @objid ("7e359a34-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Object getConstraintFor(final CreateRequest request) {
        Object constraint = super.getConstraintFor(request);
        
        // Just making sure that the constraint doesn't violate the "min size".
        if (constraint instanceof Rectangle) {
            Rectangle rectConstraint = (Rectangle) constraint;
            if (rectConstraint.width != -1 && rectConstraint.width < 8) {
                rectConstraint.width = 8;
            }
            if (rectConstraint.height != -1 && rectConstraint.height < 8) {
                rectConstraint.height = 8;
            }
        }
        return constraint;
    }

    @objid ("7e3337d8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        final MObject hostElement = getHostElement();
        final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(request);
        if (ctx != null) {
            final MObject elementToUnmask = ctx.getElementToUnmask();
            final GmCompositeNode gmParentNode = getHostCompositeNode();
            if (elementToUnmask != null) {
                if (gmParentNode.canUnmask(elementToUnmask)) {
                    final Object requestConstraint = getConstraintFor(request);
                    return new DefaultEditCreatedElementCommand(new DefaultCreateElementCommand(hostElement, gmParentNode, ctx, requestConstraint),getHost().getRoot().getViewer().getEditPartRegistry());
                } else {
                    return null;
                }
            } else if (hostElement != null) {
                MClass metaclassToCreate = ctx.getMetaclass();
        
                if (gmParentNode.canCreate(metaclassToCreate.getJavaInterface())) {
                    MExpert expert = metaclassToCreate.getMetamodel().getMExpert();
                    if (expert.canCompose(hostElement.getMClass(), metaclassToCreate, ctx.getDependencyName())) {
                        final Object requestConstraint = getConstraintFor(request);
                        return new DefaultEditCreatedElementCommand(new DefaultCreateElementCommand(hostElement, gmParentNode, ctx, requestConstraint),getHost().getRoot().getViewer().getEditPartRegistry());
                    }
                }
            }
        }
        return null;
    }

    /**
     * Retrieves the child's current constraint from the {@link #getLayoutContainer() layout container} <code>LayoutManager</code>.
     * <p>
     * The returned constraint may be a reference and must not be modified.
     * @param child the child
     * @return the current constraint by reference.
     */
    @objid ("7e37fc51-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Rectangle getCurrentConstraintFor(GraphicalEditPart child) {
        IFigure fig = child.getFigure();
        IFigure parentFig = getLayoutContainer();
        
        if (parentFig != null) {
            LayoutManager parentLayout = parentFig.getLayoutManager();
            if (parentLayout != null) {
                Object constraint = parentLayout.getConstraint(fig);
                if (constraint instanceof Rectangle) {
                    return (Rectangle) constraint;
                }
            }
        }
        return null;
    }

    /**
     * @return the {@link GmCompositeNode} model of the host edit part.
     */
    @objid ("7e3599ec-1dec-11e2-8cad-001ec947c8cc")
    protected GmCompositeNode getHostCompositeNode() {
        return (GmCompositeNode) getHost().getModel();
    }

    /**
     * @return the element represented.
     */
    @objid ("7e3599f1-1dec-11e2-8cad-001ec947c8cc")
    protected MObject getHostElement() {
        return getHostCompositeNode().getRelatedElement();
    }

    /**
     * There is currently nothing to do to orphan a group of children since the orphan is done when the children are added to their new parent (this is needed to cover cases where no orphan is actually performed, like in smart interactions), so
     * <code>null</code> is returned.
     */
    @objid ("7e3599f6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getOrphanChildrenCommand(Request request) {
        return super.getOrphanChildrenCommand(request);
    }

    /**
     * Return the host edit part if this policy can handle the metaclass involved in the request.
     * @param createRequest the request.
     * @return the host editpart if the metaclass involved in the request can be handled by this policy, <code>null</code> otherwise.
     */
    @objid ("7e359a01-1dec-11e2-8cad-001ec947c8cc")
    private EditPart getTargetEditPart(CreateRequest createRequest) {
        final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(createRequest);
        if (ctx != null) {
            if (ctx.getElementToUnmask() != null) {
                if (getHostCompositeNode().canUnmask(ctx.getElementToUnmask())) {
                    return getHost();
                } else {
                    return null;
                }
            }
        
            if (canHandle(ctx.getMetaclass(), ctx.getDependencyName())) {
                return getHost();
            }
        }
        return null;
    }

    /**
     * Return the host edit part if this policy can handle all edit parts involved in the request.
     * @param changeBoundsRequest the request, can be CLONE or ADD.
     * @return the host edit part if all edit parts involved in the request can be handled by this policy, <code>null</code> otherwise.
     */
    @objid ("7e359a0b-1dec-11e2-8cad-001ec947c8cc")
    private EditPart getTargetEditPart(ChangeBoundsRequest changeBoundsRequest) {
        for (final Object editPartObj : changeBoundsRequest.getEditParts()) {
        
            final EditPart editPart = (EditPart) editPartObj;
            if (editPart.getModel() instanceof GmModel) {
                final GmModel gmModel = (GmModel) editPart.getModel();
                // If there is at least 1 element that this policy cannot
                // handle, do not handle the request at all!
                if (!canHandle(gmModel.getRelatedMClass(), null)
                        && !(editPart instanceof ConnectionEditPart)) {
                    return null;
                }
        
            } else {
                // This is not a GmModel (maybe a drawing): not handled
                return null;
            }
        }
        // This policy can handle all elements of this request: handle it!
        return getHost();
    }

    @objid ("7e359a15-1dec-11e2-8cad-001ec947c8cc")
    private boolean isMove(ChangeBoundsRequest changeBoundsRequest) {
        // Start by excluding CLONE: this is never a move:
        if (RequestConstants.REQ_CLONE.equals(changeBoundsRequest.getType())) {
            return false;
        }
        // The request is actually a move request (not taking its type into account) if the primary selection's
        // parent edit part is the host.
        for (final Object o : changeBoundsRequest.getEditParts()) {
            final EditPart editPart = (EditPart) o;
            if (editPart.getSelected() == EditPart.SELECTED_PRIMARY) {
                if (editPart.getParent() != null && editPart.getParent().equals(getHost())) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    /**
     * Build the commands to avoid new nodes and links intersections based on the intersections remover.
     * @param helper the intersections remover.
     * @param finalCommand the compound command the built ones will be added to.
     */
    @objid ("5e3821cf-9119-4fac-ba5c-6219b90a7aaa")
    protected void createLayoutAssistantCommands(ILayoutAssistant helper, CompoundCommand finalCommand) {
        Collection<ChangeBoundsRequest> requests = helper.getNodeRequests();
        Collection<BendpointRequest> bpRequests = helper.getBendPointRequests();
        
        // build commands for initial and added requests
        for (ChangeBoundsRequest pushRequest : requests) {
            // call inherited ConstrainedLayoutEditPolicy behavior to avoid infinite loops
            Command cmd = super.getChangeConstraintCommand(pushRequest);
            helper.addCommand(cmd);
        }
        
        // build commands for moved bend points
        for (BendpointRequest bpRequest : bpRequests) {
            // No risk for infinite loops on bend points: they are not owned by the container
            Command cmd = bpRequest.getSource().getCommand(bpRequest);
            helper.addCommand(cmd);
        }
        
        finalCommand.add(helper.createExecuteCommand());
        
    }

    /**
     * Get the intersection removal helper for this host stored in the given request extended data.
     * <p>
     * Add one if not already present.
     * <p>
     * The NewIntersectionsRemover is put in requests extended data and passed to request created from initial requests. Each container having a BaseFreeZoneLayoutEditPolicy may have its own NewIntersectionsRemover in the request. This allows using the
     * same remover for all moved elements edit part on multiple selection moves.
     * @param request the request to parse.
     * @return the host intersection remover.
     */
    @objid ("676595f3-30f4-4a95-98da-7645ff3401c3")
    protected ILayoutAssistant getLayoutAssistant(ChangeBoundsRequest request) {
        List<?> involved = request.getEditParts();
        
        // first look for existing helper for this container
        ILayoutAssistant helper = (ILayoutAssistant) request.getExtendedData().get(this.intersectionsRemoverKey);
        
        // Helper not found, create one
        if (helper == null) {
            helper = createLayoutAssistant(involved, !request.getSizeDelta().equals(0, 0));
        
            request.getExtendedData().put(this.intersectionsRemoverKey, helper);
        }
        
        if (helper != NoopLayoutAssistant.instance) {
            Point layoutOrigin = getLayoutOrigin();
            for (int i = 0; i < involved.size(); i++) {
                if (involved.get(i) instanceof AbstractNodeEditPart) {
                    AbstractNodeEditPart movedEp = (AbstractNodeEditPart) involved.get(i);
        
                    Rectangle cachedOldRect = (Rectangle) request.getExtendedData().get(AbstractNodeEditPart.REQPROP_OLD_TRIMMED_BOUNDS);
                    PrecisionRectangle oldRect;
                    if (cachedOldRect == null) {
                        oldRect = new PrecisionRectangle(movedEp.getTrimmedBounds());
                        movedEp.getFigure().translateToAbsolute(oldRect);
                    } else {
                        // convert Rectangle to PrecisionRectangle
                        oldRect = new PrecisionRectangle(cachedOldRect);
                    }
        
                    PrecisionRectangle newRect = getNewAbsTrimmedBounds(request, layoutOrigin, movedEp);
        
                    helper.addBoundsChange(movedEp, oldRect, newRect);
                }
            }
        }
        return helper;
    }

    /**
     * Compute the bounds of the given edit part after having applied the request.
     * @param request the request to apply
     * @param layoutOrigin the container layout origin. see {@link #getLayoutOrigin()}
     * @param child the moved/resized edit part
     * @return the new child bounds in absolute coordinates
     */
    @objid ("39105322-7197-4c6e-996e-6eda6c8d4367")
    protected Rectangle getNewAbsBounds(ChangeBoundsRequest request, Point layoutOrigin, GraphicalEditPart child) {
        // Use the constraint instead of the figure bounds
        // because the layouter constraints the bounds to be inside the container.
        Rectangle constraint = (Rectangle) getConstraintFor(request, child);
        IFigure childFigure = child.getFigure();
        if (constraint.width() == -1 || constraint.height() == -1) {
            Dimension childPrefSize = childFigure.getPreferredSize();
            if (constraint.width() == -1) {
                constraint.width = childPrefSize.width();
            }
        
            if (constraint.height() == -1) {
                constraint.height = childPrefSize.height();
            }
        }
        constraint.translate(layoutOrigin);
        
        childFigure.translateToAbsolute(constraint);
        return constraint;
    }

    /**
     * Redefined to handle {@link ChainedLayout} layout managers.
     * @return the {@link XYLayout} layout manager set on the {@link LayoutEditPolicy#getLayoutContainer() container}
     */
    @objid ("54dcf5ad-90db-45e0-8558-c1e4917cad1c")
    @Override
    protected XYLayout getXYLayout() {
        if (this.xyLayout == null) {
            LayoutManager layout = ChainedLayout.getRootLayout(getLayoutContainer());
            this.xyLayout = (XYLayout) layout;
        }
        return this.xyLayout;
    }

    /**
     * Used by {@link #showTargetFeedback(Request)} to know which figure must be highlighted.
     * @param request the request sent to {@link #showTargetFeedback(Request)}
     * @return the figure to highlight.
     */
    @objid ("e3ba1dab-4a3c-427f-9627-4bff5a92edfd")
    protected IFigure getTargetFeedbackFigure(Request request) {
        return getHostFigure();
    }

    @objid ("238e38f1-ceaa-4907-b28c-b63b2dcf0bb9")
    @Override
    protected Command createAddCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
        if (child.getModel() instanceof GmNodeModel) {
            return new DefaultReparentElementCommand(getHostElement(), getHostCompositeNode(), (GmNodeModel) child.getModel(), constraint);
        } else {
            return null;
        }
        
    }

    @objid ("ae931583-dba3-4778-aad1-9399f482e27b")
    @Override
    protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart movedEditPart, Object constraint) {
        // if child is a 'node' it usually can be resized and/or moved
        if (movedEditPart instanceof AbstractNodeEditPart || movedEditPart.getModel() instanceof GmDrawing) {
            final CompoundCommand command = new CompoundCommand();
        
            final NodeChangeLayoutCommand layoutCommand = new NodeChangeLayoutCommand();
            layoutCommand.setModel(movedEditPart.getModel());
            layoutCommand.setConstraint(constraint);
            command.add(layoutCommand);
        
            return new PostLayoutCommand(command, request);
        }
        return null;
    }

    /**
     * Compute the trimmed bounds of the given edit part after having applied the request.
     * @see AbstractNodeEditPart#getTrimmedBounds()
     * @param request the request to apply
     * @param layoutOrigin the container layout origin. see {@link #getLayoutOrigin()}
     * @param child the moved/resized edit part
     * @return the new child bounds in absolute coordinates
     */
    @objid ("4be16133-debe-40d8-a4b0-858d134cb7ce")
    protected PrecisionRectangle getNewAbsTrimmedBounds(ChangeBoundsRequest request, Point layoutOrigin, AbstractNodeEditPart child) {
        Rectangle reqBounds = (Rectangle) request.getExtendedData().get(AbstractNodeEditPart.REQPROP_TRIMMED_BOUNDS);
        if (reqBounds != null) {
            return new PrecisionRectangle(reqBounds);
        } else {
            // Use the constraint instead of the figure bounds
            // because the layouter constraints the bounds to be inside the container.
            Rectangle constraint = (Rectangle) getConstraintFor(request, child);
            IFigure childFigure = child.getFigure();
            if (constraint.width() == -1 || constraint.height() == -1) {
                Dimension childPrefSize = childFigure.getPreferredSize();
                if (constraint.width() == -1) {
                    constraint.width = childPrefSize.width();
                }
        
                if (constraint.height() == -1) {
                    constraint.height = childPrefSize.height();
                }
            }
            constraint.translate(layoutOrigin);
        
            // ugly: Temporarily change figure bounds to have exact figure trimmed ones.
            Rectangle oldBounds = childFigure.getBounds().getCopy();
            childFigure.setBounds(constraint);
            PrecisionRectangle ret = new PrecisionRectangle(child.getTrimmedBounds());
            childFigure.setBounds(oldBounds); // restore figure bounds
        
            childFigure.translateToAbsolute(ret);
            return ret;
        }
        
    }

    @objid ("5be2830f-6ab7-4108-ac90-f937b30d92a6")
    protected ILayoutAssistant createLayoutAssistant(List<?> toExclude, boolean forResize) {
        IGmObject gm = (IGmObject) getHost().getModel();
        IStyle gmStyle = gm.getDisplayedStyle();
        
        boolean disabledInViewer = isLayoutAssistantDisabledInViewer();
        boolean enabled = gmStyle.getProperty(LayoutAssistantStyleKeys.ENABLED);
        
        if (disabledInViewer || !enabled) {
            return NoopLayoutAssistant.instance;
        } else {
            DefaultLayoutAssistant helper = new DefaultLayoutAssistant();
        
            boolean keepSameDist = forResize && gmStyle.getBoolean(LayoutAssistantStyleKeys.KEEP_DIST_ON_RESIZE);
            helper.setKeepSameDistance(keepSameDist);
        
            int minDist = gmStyle.getInteger(LayoutAssistantStyleKeys.MINDIST);
            if (minDist < 0) {
                minDist = gmStyle.getInteger(GmAbstractDiagramStyleKeys.GRIDSPACING) + minDist;
            }
        
            Dimension dminDist = new PrecisionDimension(minDist, minDist);
            getHostFigure().translateToAbsolute(dminDist);
            helper.setMinDist(dminDist.preciseWidth());
        
            helper.setAvoidBendPoints(gmStyle.getBoolean(LayoutAssistantStyleKeys.AVOIDBENDDPOINTS));
        
            // Populate the helper
            List<GraphicalEditPart> children = getHost().getChildren();
            for (GraphicalEditPart child : children) {
                if (child instanceof AbstractNodeEditPart) {
                    if (!toExclude.contains(child)) {
                        IFigure f = child.getFigure();
                        Rectangle oldRect = ((AbstractNodeEditPart) child).getTrimmedBounds().getCopy();
                        f.translateToAbsolute(oldRect);
                        helper.addMovableNode(child, oldRect);
                    }
                }
            }
            return helper;
        }
        
    }

    /**
     * Tells whether the layout assistant is disabled in the viewer properties.
     * <p>
     * The layout assistant may be disabled temporarily (without modifying the model) by setting {@link ILayoutAssistant#VIEWPROP_ENABLED} property id to <i>false</i>.
     * @return whether the layout assistant is disabled in the viewer properties.
     */
    @objid ("3747901a-8367-4ae2-8517-fdef8c35365c")
    protected boolean isLayoutAssistantDisabledInViewer() {
        return Boolean.FALSE.equals(getHost().getViewer().getProperty(ILayoutAssistant.VIEWPROP_ENABLED));
    }

    @objid ("53cf6cc2-1b33-4de5-8383-b40da8dfd3a9")
    @Override
    public Command getCommand(Request request) {
        if (RequestTypes.UNMASK_OR_CREATE_CHILDREN.equals(request.getType())) {
            return getUnamskOrCreateCommand((CreateRequest) request);
        }
        return super.getCommand(request);
    }

    @objid ("62e273e0-b62f-43e2-a200-732ac830381c")
    protected Command getUnamskOrCreateCommand(CreateRequest request) {
        final MObject hostElement = getHostElement();
        final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(request);
        if (ctx != null) {
            final MObject elementToUnmask = ctx.getElementToUnmask();
            final GmCompositeNode gmParentNode = getHostCompositeNode();
            if (elementToUnmask != null) {
                if (gmParentNode.canUnmask(elementToUnmask)) {
                    final Object requestConstraint = getConstraintFor(request);
                    return new DefaultSelectElementCommand(hostElement, gmParentNode, ctx, requestConstraint);
                } else {
                    return null;
                }
            } else if (hostElement != null) {
                MClass metaclassToCreate = ctx.getMetaclass();
        
                if (gmParentNode.canCreate(metaclassToCreate.getJavaInterface())) {
                    MExpert expert = metaclassToCreate.getMetamodel().getMExpert();
                    if (expert.canCompose(hostElement.getMClass(), metaclassToCreate, ctx.getDependencyName())) {
                        final Object requestConstraint = getConstraintFor(request);
                        return new DefaultSelectElementCommand(hostElement, gmParentNode, ctx, requestConstraint);
                    }
                }
            }
        }
        return null;
    }

}
