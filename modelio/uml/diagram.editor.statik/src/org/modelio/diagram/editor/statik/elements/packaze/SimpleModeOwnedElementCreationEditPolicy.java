/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.statik.elements.packaze;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.DefaultCloneElementCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.commands.NodeChangeLayoutCommand;
import org.modelio.diagram.elements.core.commands.SimpleModeDeferredCreateCommand;
import org.modelio.diagram.elements.core.commands.SimpleModeDeferredGroupCommand;
import org.modelio.diagram.elements.core.figures.FigureUtilities2;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DefaultNodeResizableEditPolicy;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("362cb7c4-55b7-11e2-877f-002564c97630")
public class SimpleModeOwnedElementCreationEditPolicy extends XYLayoutEditPolicy {
    @objid ("629aa5c2-5bd5-11e2-9e33-00137282c51b")
    protected IFigure highlight = null;

    @objid ("362cb7c6-55b7-11e2-877f-002564c97630")
    @Override
    public void eraseTargetFeedback(final Request request) {
        if (REQ_ADD.equals(request.getType()) || REQ_CREATE.equals(request.getType())) {
        
            if (this.highlight != null) {
                removeFeedback(this.highlight);
                this.highlight = null;
            }
        }
        
        super.eraseTargetFeedback(request);
    }

    @objid ("362cb7cb-55b7-11e2-877f-002564c97630")
    @Override
    public EditPart getTargetEditPart(final Request request) {
        if (REQ_CREATE.equals(request.getType())) {
            final CreateRequest createRequest = (CreateRequest) request;
            return getTargetEditPart(createRequest);
        }
        if (REQ_MOVE.equals(request.getType()) ||
                REQ_ADD.equals(request.getType()) ||
                REQ_CLONE.equals(request.getType())) {
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

    @objid ("362cb7d2-55b7-11e2-877f-002564c97630")
    @Override
    public void showTargetFeedback(final Request request) {
        if (REQ_ADD.equals(request.getType()) || REQ_CREATE.equals(request.getType())) {
        
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
                this.highlight = FigureUtilities2.createHighlightFigure(getFeedbackLayer(),
                        getHostFigure(),
                        hightlightType);
                // add the highlight figure to the feedback layer
                getFeedbackLayer().add(this.highlight);
            }
            // configure the highlight figure
            FigureUtilities2.updateHighlightType(this.highlight, hightlightType);
        }
        super.showTargetFeedback(request);
    }

    /**
     * Returns whether this edit policy can handle this metaclass (either through simple or smart behavior). Default
     * behavior is to accept any metaclass that can be child (in the CreationExpert's understanding) of the host's
     * metaclass This method should be overridden by subclasses to add specific the behavior.
     * 
     * @param metaclass the metaclass to handle.
     * @return true if this policy can handle the metaclass.
     */
    @objid ("362cb7d7-55b7-11e2-877f-002564c97630")
    protected boolean canHandle(MClass metaclass) {
        final MObject hostElement = getHostElement();
        if (hostElement == null) {
            return false;
        }
        MClass hostMClass = hostElement.getMClass();
        return hostMClass.getMetamodel().getMExpert().canCompose(hostMClass, metaclass, null) &&
                                                                                ((GmCompositeNode) getHost().getModel()).canCreate(metaclass.getJavaInterface());
    }

    @objid ("362cb7e0-55b7-11e2-877f-002564c97630")
    @Override
    protected Command createAddCommand(final ChangeBoundsRequest request, final EditPart child, final Object constraint) {
        GmNodeModel childModel = (GmNodeModel) child.getModel();
        GmNodeModel hostModel = (GmNodeModel) getHost().getModel();
        if (hostModel.canCreate(childModel.getRelatedMClass().getJavaInterface())) {
            return new ReparentElementCommand(getHostElement(),
                    getHostCompositeNode().getParentNode(),
                    childModel,
                    constraint);
        } else {
            return new SimpleModeDeferredGroupCommand(request, getHost());
        }
    }

    @objid ("362e3e42-55b7-11e2-877f-002564c97630")
    @Override
    protected Command createChangeConstraintCommand(final ChangeBoundsRequest request, final EditPart child, final Object constraint) {
        // if child is a 'node' it usually can be resized and/or moved
        if (child instanceof AbstractNodeEditPart) {
            final NodeChangeLayoutCommand command = new NodeChangeLayoutCommand();
            command.setModel(child.getModel());
            command.setConstraint(constraint);
            return command;
        }
        return null;
    }

    @objid ("362e3e4d-55b7-11e2-877f-002564c97630")
    @Override
    protected EditPolicy createChildEditPolicy(final EditPart child) {
        // if child is a node that can provide its own policy (maybe for keeping
        // specific geometric needs, etc) return it.
        if (child instanceof AbstractNodeEditPart) {
            final AbstractNodeEditPart childNode = (AbstractNodeEditPart) child;
            final SelectionEditPolicy childPolicy = childNode.getPreferredDragRolePolicy(REQ_RESIZE);
            if (childPolicy != null) {
                return childPolicy;
            }
        }
        // default
        return new DefaultNodeResizableEditPolicy();
    }

    @objid ("362e3e54-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getCloneCommand(final ChangeBoundsRequest request) {
        if (getHost().getModel() instanceof GmCompositeNode) {
            final GmCompositeNode hostModel = getHostCompositeNode();
            final CompoundCommand command = new CompoundCommand();
            for (final Object editPartObj : request.getEditParts()) {
                final EditPart editPart = (EditPart) editPartObj;
                if (editPart.getModel() instanceof GmModel) {
                    GmModel gmModel = (GmModel) editPart.getModel();
                    MClass hostMClass = getHostElement().getMClass();
        
                    if(hostMClass.getMetamodel().getMExpert().canCompose(hostMClass, gmModel.getRelatedElement().getMClass(), null)){
                        final Object requestConstraint = getConstraintFor(request,
                                (GraphicalEditPart) editPart);
                        command.add(new DefaultCloneElementCommand(hostModel,
                                getHostElement(),
                                gmModel.getRelatedElement(),
                                requestConstraint));
                    }
                }
            }
            return command.unwrap();
        }
        return null;
    }

    @objid ("362e3e5b-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getCreateCommand(final CreateRequest request) {
        final MObject hostElement = getHostElement();
        final ModelioCreationContext ctx = (ModelioCreationContext) request.getNewObject();
        
        final MObject elementToUnmask = ctx.getElementToUnmask();
        final GmCompositeNode gmParentNode = getHostCompositeNode();
        if (elementToUnmask != null) {
            if (gmParentNode.canUnmask(elementToUnmask)) {
                final Object requestConstraint = getConstraintFor(request);
                //TODO remove hostElement argument?
                return new CreateElementCommand(hostElement, gmParentNode, ctx, requestConstraint);
            } else {
                return new SimpleModeDeferredCreateCommand(request, getHost());
            }
        } else if (hostElement != null) {
            final MClass metaclassToCreate = ctx.getMetaclass();
            final String depName = ctx.getDependencyName();
            final MClass hostMetaclass = hostElement.getMClass();
        
            if (gmParentNode.canCreate(metaclassToCreate.getJavaInterface())) {
                if (hostMetaclass.getMetamodel().getMExpert().canCompose(hostMetaclass, metaclassToCreate, depName)) {
                    final Object requestConstraint = getConstraintFor(request);
                    return new CreateElementCommand(hostElement, gmParentNode, ctx, requestConstraint);
                } else {
                    return null;
                }
            } else {
                return new SimpleModeDeferredCreateCommand(request, getHost());
            }
        }
        return null;
    }

    /**
     * @return the {@link GmCompositeNode} model of the host edit part.
     */
    @objid ("362e3e62-55b7-11e2-877f-002564c97630")
    protected GmCompositeNode getHostCompositeNode() {
        return (GmCompositeNode) getHost().getModel();
    }

    /**
     * @return the element represented.
     */
    @objid ("362e3e69-55b7-11e2-877f-002564c97630")
    protected MObject getHostElement() {
        return getHostCompositeNode().getRelatedElement();
    }

    /**
     * There is currently nothing to do to orphan a group of children since the orphan is done when the children are
     * added to their new parent (this is needed to cover cases where no orphan is actually performed, like in smart
     * interactions), so <code>null</code> is returned.
     */
    @objid ("362e3e70-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getOrphanChildrenCommand(final Request request) {
        return super.getOrphanChildrenCommand(request);
    }

    /**
     * Return the host edit part if this policy can handle the metaclass involved in the request.
     * 
     * @param createRequest the request.
     * @return the host editpart if the metaclass involved in the request can be handled by this policy,
     * <code>null</code> otherwise.
     */
    @objid ("362e3e78-55b7-11e2-877f-002564c97630")
    private EditPart getTargetEditPart(final CreateRequest createRequest) {
        final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(createRequest);
        if (ctx != null) {
            if (ctx.getElementToUnmask() != null) {
                if (getHostCompositeNode().canUnmask(ctx.getElementToUnmask())) {
                    return getHost();
                }
            }
        
            if (canHandle(ctx.getMetaclass())) {
                return getHost();
            }
        
            return getEditPartFor(ctx.getMetaclass().getJavaInterface());
        
        }
        return null;
    }

    /**
     * Return the host edit part if this policy can handle all edit parts involved in the request.
     * 
     * @param changeBoundsRequest the request, can be CLONE or ADD.
     * @return the host edit part if all edit parts involved in the request can be handled by this policy,
     * <code>null</code> otherwise.
     */
    @objid ("362fc4df-55b7-11e2-877f-002564c97630")
    private EditPart getTargetEditPart(final ChangeBoundsRequest changeBoundsRequest) {
        // Add/move/clone request : many elements are involved.
        // Look for an edit part that can handle the whole request with
        // all its involved elements, or null if none found.
        // We could later try to split the request by metaclass and let each inner
        // part handle what it can.
        EditPart ret = null;
        for (final Object editPartObj : changeBoundsRequest.getEditParts()) {
        
            final EditPart editPart = (EditPart) editPartObj;
            if (editPart.getModel() instanceof GmModel) {
                final GmModel gmModel = (GmModel) editPart.getModel();
                final MClass gmMClass = gmModel.getRelatedMClass();
        
                // If there is at least 1 element that this policy cannot
                // handle, do not handle the request at all!
                EditPart target = null;
                if (canHandle(gmMClass) ||
                        (editPart instanceof ConnectionEditPart)) {
                    target = getHost();
                }
                if (target == null) {
                    target = getEditPartFor(gmMClass.getJavaInterface());
                }
                if (ret == null) {
                    ret = target;
                } else if (ret != target) {
                    return null;
                }
            }
        }
        // This policy can handle all elements of this request: handle it!
        return ret;
    }

    @objid ("362fc4e6-55b7-11e2-877f-002564c97630")
    private boolean isMove(final ChangeBoundsRequest changeBoundsRequest) {
        // Start by excluding CLONE: this is never a move:
        if (REQ_CLONE.equals(changeBoundsRequest.getType())) {
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

    @objid ("362fc4ec-55b7-11e2-877f-002564c97630")
    protected Rectangle getEffectiveBounds(final IFigure figure) {
        return (figure instanceof HandleBounds) ? ((HandleBounds) figure).getHandleBounds().getCopy()
                                                                                : figure.getBounds().getCopy();
    }

    @objid ("362fc4f2-55b7-11e2-877f-002564c97630")
    @Override
    protected Object getConstraintFor(final CreateRequest request) {
        // Just making sure that the constraint doesn't violate the "min size".
        Object constraint = super.getConstraintFor(request);
        if (constraint instanceof Rectangle) {
            Rectangle rectConstraint = (Rectangle) constraint;
            if (rectConstraint.width != -1 && rectConstraint.width < 8) {
                rectConstraint.width = 8;
            }
            if (rectConstraint.height != -1 && rectConstraint.height < 8) {
                rectConstraint.height = 8;
            }
            rectConstraint.translate(getHostFigure().getSize().width(), getHostFigure().getSize().height());
        }
        return constraint;
    }

    @objid ("362fc4f9-55b7-11e2-877f-002564c97630")
    @Override
    protected Point getLayoutOrigin() {
        return getHostFigure().getParent().getClientArea().getLocation();
    }

    /**
     * Same as super implementation, except we filter the connection editparts.
     */
    @objid ("362fc4fe-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getAddCommand(final Request generic) {
        ChangeBoundsRequest request = (ChangeBoundsRequest) generic;
        List<?> editParts = request.getEditParts();
        CompoundCommand command = new CompoundCommand();
        command.setDebugLabel("Add in ConstrainedLayoutEditPolicy");//$NON-NLS-1$
        GraphicalEditPart child;
        
        for (int i = 0; i < editParts.size(); i++) {
            child = (GraphicalEditPart) editParts.get(i);
            if (child instanceof ConnectionEditPart) {
                command.add(child.getCommand(generic));
            } else {
                command.add(createAddCommand(request,
                        child,
                        translateToModelConstraint(getConstraintFor(request, child))));
            }
        }
        return command.unwrap();
    }

    @objid ("362fc506-55b7-11e2-877f-002564c97630")
    @Override
    protected Object getConstraintFor(final ChangeBoundsRequest request, final GraphicalEditPart child) {
        // Just making sure that the constraint doesn't violate the "min size".
        Object constraint = super.getConstraintFor(request, child);
        if (constraint instanceof Rectangle) {
            Rectangle rectConstraint = (Rectangle) constraint;
            if (rectConstraint.width != -1 && rectConstraint.width < 8) {
                rectConstraint.width = 8;
            }
            if (rectConstraint.height != -1 && rectConstraint.height < 8) {
                rectConstraint.height = 8;
            }
            rectConstraint.translate(getHostFigure().getSize().width(), getHostFigure().getSize().height());
        }
        return constraint;
    }

    /**
     * Get the child EditPart where elements of the given metaclass are displayed.
     * <p>
     * {@link GmCompositeNode#getCompositeFor(Class)} is used on the model in order to find the right edit part.
     * <p>
     * If no child model is found, return <tt>null</tt>.<br>
     * If the found model has no edit part, return the host edit part.
     * <p>
     * Copy-pasted from: SimpleModeDeferringCreateNodePolicy.
     * 
     * @param metaclass The metaclass to create or drop.
     * @return <ul>
     * <li><tt>null</tt> if no suitable child model could be found <li>{@link #getHost()} if the child model is
     * not displayed <li>The found child model edit part if one suitable is found.
     * </ul>
     */
    @objid ("36314b7c-55b7-11e2-877f-002564c97630")
    private EditPart getEditPartFor(Class<? extends MObject> metaclass) {
        Object model = getHost().getModel();
        if (!(model instanceof GmCompositeNode)) {
            return null;
        }
        final GmCompositeNode gmNode = (GmCompositeNode) model;
        
        final GmCompositeNode gmTargetChild = gmNode.getCompositeFor(metaclass);
        
        if (gmTargetChild == null || !gmTargetChild.canCreate(metaclass)) {
            return null;
        }
        
        final EditPart p = (EditPart) getHost().getRoot()
                .getViewer()
                .getEditPartRegistry()
                .get(gmTargetChild);
        if (p != null) {
            return p;
        } else {
            return getHost();
        }
    }

}
