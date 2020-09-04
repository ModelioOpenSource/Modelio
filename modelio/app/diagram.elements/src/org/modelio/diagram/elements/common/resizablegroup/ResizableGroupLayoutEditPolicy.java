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

package org.modelio.diagram.elements.common.resizablegroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transposer;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.OrderedLayoutEditPolicy;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.DropRequest;
import org.modelio.diagram.elements.core.commands.DefaultReparentElementCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.AutoExpandHelper;
import org.modelio.diagram.elements.core.policies.DefaultNodeResizableEditPolicy;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * An EditPolicy for use with {@link ResizableGroupLayout}.
 * <p>
 * This EditPolicy knows how to map an <x,y> coordinate on the layout container to the appropriate index for the operation being
 * performed. It also shows target feedback consisting of an insertion line at the appropriate location.
 * 
 * 
 * @author fpoyer
 */
@objid ("7f0e9029-1dec-11e2-8cad-001ec947c8cc")
public class ResizableGroupLayoutEditPolicy extends OrderedLayoutEditPolicy {
    @objid ("7e9ab84e-0268-477d-a01a-aa6d77e4473c")
    private static final Dimension DEFAULT_SIZE = new Dimension(-1, -1);

    @objid ("00ddff29-87e8-42e4-8011-cee9483dc990")
    private Polyline insertionLine;

    @objid ("7f15b723-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Command getCommand(Request request) {
        if (REQ_RESIZE_CHILDREN.equals(request.getType())) {
            return getResizeChildrenCommand((ChangeBoundsRequest) request);
        }
        // else
        return super.getCommand(request);
    }

    /**
     * Returns a Rectangle at the given Point with width and height of -1. Layout uses width or height equal to '-1' to mean use the
     * figure's preferred size.
     * 
     * @param p the input Point
     * @return a Rectangle
     */
    @objid ("7f15b6fb-1dec-11e2-8cad-001ec947c8cc")
    public static Rectangle getConstraintFor(Point p) {
        return new Rectangle(p, DEFAULT_SIZE);
    }

    @objid ("7f10f265-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (REQ_CREATE.equals(request.getType())) {
            CreateRequest createRequest = (CreateRequest) request;
            return getTargetEditPart(createRequest);
        }
        if (REQ_MOVE.equals(request.getType()) || REQ_ADD.equals(request.getType()) || REQ_CLONE.equals(request.getType())) {
            // Special case: MOVE and ADD request can be tricky: depending on the "previous loop" of the tool sending
            // the request, an ADD request can be send for a simple graphic move, and a MOVE request can be sent for an
            // actual graphic re-parenting. Let's analyse the request a bit to determine how to handle it.
            ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) request;
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

    /**
     * Returns whether this edit policy can handle this metaclass (either through simple or smart behavior). Default behavior is to
     * accept any metaclass that can be child (in the CreationExpert's understanding) of the host's metaclass This method should be
     * overridden by subclasses to add specific the behavior.
     * 
     * @param metaclass the metaclass to handle.
     * @return true if this policy can handle the metaclass.
     */
    @objid ("7f10f283-1dec-11e2-8cad-001ec947c8cc")
    protected boolean canHandle(MClass metaclass) {
        MExpert expert = getHostElement().getMClass().getMetamodel().getMExpert();
        return expert.canCompose(getHostElement(), metaclass, null)
                                                                                                                && getHostCompositeNode().canCreate(metaclass.getJavaInterface());
    }

    @objid ("7f10f24e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command createAddCommand(EditPart child, EditPart after) {
        if (!getHostCompositeNode().allowsMove()) {
            return null;
        }
        // First re-parent, then put at the correct place.
        // TODO: could probably be done better with a single dedicated
        // command...
        GmNodeModel reference = null;
        if (after != null) {
            reference = (GmNodeModel) after.getModel();
        }
        
        CompoundCommand compound = new CompoundCommand();
        compound.add(new DefaultReparentElementCommand(getHostElement(), getHostCompositeNode(), (GmNodeModel) child.getModel(),
                ((GmNodeModel) child.getModel()).getLayoutData()));
        compound.add(new ReorderChildrenCommand(getHostCompositeNode(), (GmNodeModel) child.getModel(), reference));
        return compound;
    }

    @objid ("7f1354d0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPolicy createChildEditPolicy(EditPart child) {
        // if child is a node that can provide its own policy (maybe for keeping
        // specific geometric needs, etc) return it.
        if (child instanceof AbstractNodeEditPart) {
            AbstractNodeEditPart childNode = (AbstractNodeEditPart) child;
            SelectionEditPolicy childPolicy = childNode.getPreferredDragRolePolicy(REQ_RESIZE);
            if (childPolicy != null) {
                return childPolicy;
            }
        }
        
        // default
        return new DefaultNodeResizableEditPolicy();
    }

    /**
     * Creates the EditPartListener for observing when children are added to the host.
     * <p>
     * Redefined to auto expand on child edit part addition.
     * 
     * @return EditPartListener
     */
    @objid ("8e90c3cf-7bf6-42a5-9466-378ecdcbae5b")
    @Override
    protected EditPartListener createListener() {
        return new EditPartListener.Stub() {
                                                    @Override
                                                    public void childAdded(EditPart child, int index) {
                                                        onChildAdded(child);
                                                    }
                                                };
    }

    @objid ("7f10f241-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command createMoveChildCommand(EditPart child, EditPart after) {
        // if child is a 'node' it usually can be resized and/or moved
        if (child instanceof AbstractNodeEditPart) {
            GmNodeModel reference = null;
            if (after != null) {
                reference = (GmNodeModel) after.getModel();
            }
            ReorderChildrenCommand command = new ReorderChildrenCommand(getHostCompositeNode(), (GmNodeModel) child.getModel(),
                    reference);
            return command;
        }
        return null;
    }

    @objid ("7f10f28b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void eraseLayoutTargetFeedback(Request request) {
        if (this.insertionLine != null) {
            removeFeedback(this.insertionLine);
            this.insertionLine = null;
        }
    }

    @objid ("7f181965-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getAddCommand(final Request req) {
        ChangeBoundsRequest request = (ChangeBoundsRequest) req;
        List<?> editParts = request.getEditParts();
        CompoundCommand command = new CompoundCommand();
        
        for (int i = 0; i < editParts.size(); i++) {
            EditPart child = (EditPart) editParts.get(i);
            if (child instanceof ConnectionEditPart) {
                command.add(child.getCommand(req));
            } else {
                command.add(createAddCommand(child, getInsertionReference(request)));
            }
        }
        return command.unwrap();
    }

    /**
     * Overridden to prevent sizes from becoming too small, and to prevent preferred sizes from getting lost.
     * <p>
     * If the Request is a MOVE, the existing width and height are preserved.<br>
     * During RESIZE, the new width and height have a lower bound determined by
     * {@link #getMinimumSizeFor(GraphicalEditPart)}.
     * 
     * @param request the ChangeBoundsRequest.
     * @param child the child EditPart for which the constraint should be generated.
     * @return the rectangle being the desired bounds of the child.
     */
    @objid ("7f1354e5-1dec-11e2-8cad-001ec947c8cc")
    protected Rectangle getConstraintFor(ChangeBoundsRequest request, GraphicalEditPart child) {
        IFigure childFig = child.getFigure();
        Rectangle oldChildRect = new PrecisionRectangle(childFig.getBounds());
        Rectangle newChildRect = oldChildRect.getCopy();
        
        childFig.translateToAbsolute(newChildRect);
        newChildRect = request.getTransformedRectangle(newChildRect);
        childFig.translateToRelative(newChildRect);
        
        if (request.getSizeDelta().equals(0, 0)) {
            // It's a move
            Rectangle cons = getCurrentConstraintFor(child);
            if (cons != null) {
                newChildRect.setSize(cons.width, cons.height);
            }
        } else {
            // It's a resize
            Dimension minSize = getMinimumSizeFor(child);
            if (newChildRect.width < minSize.width) {
                newChildRect.width = minSize.width;
        
                if (newChildRect.x > (oldChildRect.right() - minSize.width)) {
                    // prevent new bounds to go outside old bounds ?
                    newChildRect.x = oldChildRect.right() - minSize.width;
                }
            }
            if (newChildRect.height < minSize.height) {
                newChildRect.height = minSize.height;
                if (newChildRect.y > (oldChildRect.bottom() - minSize.height)) {
                    // prevent new bounds to go outside old bounds ?
                    newChildRect.y = oldChildRect.bottom() - minSize.height;
                }
            }
        }
        
        newChildRect.translate(getLayoutOrigin().getNegated());
        return newChildRect;
    }

    /**
     * Generates a draw2d constraint for the given <code>CreateRequest</code>. If the CreateRequest has a size,
     * {@link #getConstraintFor(Rectangle)} is called with a Rectangle of that size and the result is returned. This is used during
     * size-on-drop creation. Otherwise, {@link #getConstraintFor(Point)} is returned.
     * <P>
     * The CreateRequest's location is relative the Viewer. The location is made layout-relative before calling one of the methods
     * mentioned above.
     * 
     * @param request the CreateRequest
     * @return a draw2d constraint
     */
    @objid ("7f15b737-1dec-11e2-8cad-001ec947c8cc")
    protected Rectangle getConstraintFor(CreateRequest request) {
        IFigure figure = getLayoutContainer();
        
        Point where = request.getLocation().getCopy();
        Dimension size = request.getSize();
        
        figure.translateToRelative(where);
        figure.translateFromParent(where);
        where.translate(getLayoutContainer().getClientArea().getLocation().getNegated());
        
        if (size == null || size.isEmpty()) {
            return getConstraintFor(where);
        }
        // else
        size = size.getCopy();
        figure.translateToRelative(size);
        figure.translateFromParent(size);
        return new Rectangle(where, size);
    }

    @objid ("7f0e9034-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(request);
        if (ctx != null) {
            final EditPart insertAfter = getInsertionReference(request);
            final GmNodeModel insertAfterModel = (insertAfter == null ? null : (GmNodeModel) insertAfter.getModel());
        
            IFigure containerFig = getLayoutContainer();
            containerFig.revalidate();
        
            int constraint = -1;
            final Dimension containerDimension = containerFig.getSize();
            if (isHorizontal() && containerDimension.width != 0) {
                constraint = containerDimension.width / (containerFig.getChildren().size() + 1);
            } else if (!isHorizontal() && containerDimension.height != 0) {
                constraint = containerDimension.height / (containerFig.getChildren().size() + 1);
            } else {
                constraint = -1;
            }
            return new AddChildToGroupCommand(getHost(), ctx, insertAfterModel, constraint);
        } else {
            return null;
        }
    }

    /**
     * Retrieves the child's current constraint from the <code>LayoutManager</code>.
     * 
     * @param child the child
     * @return the current constraint
     */
    @objid ("7f15b70f-1dec-11e2-8cad-001ec947c8cc")
    protected static Rectangle getCurrentConstraintFor(GraphicalEditPart child) {
        IFigure fig = child.getFigure();
        return (Rectangle) fig.getParent().getLayoutManager().getConstraint(fig);
    }

    /**
     * @param request the Request
     * @return the index for the insertion reference
     */
    @objid ("7f1354a2-1dec-11e2-8cad-001ec947c8cc")
    protected int getFeedbackIndexFor(Request request) {
        List<?> children = getHost().getChildren();
        if (children.isEmpty()) {
            return -1;
        }
        
        Transposer transposer = new Transposer();
        transposer.setEnabled(!isHorizontal());
        
        Point locationFromRequest = getLocationFromRequest(request);
        Point p = locationFromRequest != null ? transposer.t(locationFromRequest) : new Point(0, 0);
        
        // Current row bottom, initialize to above the top.
        int rowBottom = Integer.MIN_VALUE;
        int candidate = -1;
        for (int i = 0; i < children.size(); i++) {
            EditPart child = (EditPart) children.get(i);
            Rectangle rect = transposer.t(getAbsoluteBounds(((GraphicalEditPart) child)));
            if (rect.y > rowBottom) {
                /*
                 * We are in a new row, so if we don't have a candidate but yet are within the previous row, then the current entry
                 * becomes the candidate. This is because we know we must be to the right of center of the last Figure in the
                 * previous row, so this Figure (which is at the start of a new row) is the candidate.
                 */
                if (p.y <= rowBottom) {
                    if (candidate == -1) {
                        candidate = i;
                    }
                    break;
                }
                // else
                candidate = -1; // Mouse's Y is outside the row, so reset the
                // candidate
            }
            rowBottom = Math.max(rowBottom, rect.bottom());
            if (candidate == -1) {
                /*
                 * See if we have a possible candidate. It is a candidate if the cursor is left of the center of this candidate.
                 */
                if (p.x <= rect.x + (rect.width / 2)) {
                    candidate = i;
                }
            }
            if (candidate != -1) {
                // We have a candidate, see if the rowBottom has grown to
                // include the mouse Y.
                if (p.y <= rowBottom) {
                    /*
                     * Now we have determined that the cursor.Y is above the bottom of the current row of figures. Stop now, to
                     * prevent the next row from being searched
                     */
                    break;
                }
            }
        }
        return candidate;
    }

    /**
     * @return the element represented.
     */
    @objid ("7f10f260-1dec-11e2-8cad-001ec947c8cc")
    protected MObject getHostElement() {
        MObject hostElement = getHostCompositeNode().getRelatedElement();
        // Watch out for container being on the diagram background: we
        // actually want the context of the diagram.
        if (hostElement instanceof AbstractDiagram) {
            hostElement = ((AbstractDiagram) hostElement).getOrigin();
        }
        return hostElement;
    }

    @objid ("7f1354aa-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPart getInsertionReference(Request request) {
        List<?> children = getHost().getChildren();
        
        if (request.getType().equals(RequestConstants.REQ_CREATE)) {
            int i = getFeedbackIndexFor(request);
            if (i == -1) {
                return null;
            }
            return (EditPart) children.get(i);
        }
        
        int index = getFeedbackIndexFor(request);
        if (index != -1) {
            List<?> selection = getHost().getViewer().getSelectedEditParts();
            do {
                EditPart editpart = (EditPart) children.get(index);
                if (!selection.contains(editpart)) {
                    return editpart;
                }
            } while (++index < children.size());
        }
        return null; // Not found, add at the end.
    }

    /**
     * Lazily creates and returns a <code>Polyline</code> Figure for use as feedback.
     * 
     * @return a Polyline figure
     */
    @objid ("7f1354b4-1dec-11e2-8cad-001ec947c8cc")
    protected Polyline getLineFeedback() {
        if (this.insertionLine == null) {
            this.insertionLine = new Polyline();
            this.insertionLine.setForegroundColor(ColorConstants.blue);
            this.insertionLine.setLineWidth(3);
            this.insertionLine.setAlpha(128);
            this.insertionLine.addPoint(new Point(-5, 0));
            this.insertionLine.addPoint(new Point(+5, 0));
            this.insertionLine.addPoint(new Point(0, 0));
            this.insertionLine.addPoint(new Point(10, 10));
            addFeedback(this.insertionLine);
        }
        return this.insertionLine;
    }

    /**
     * Determines the <em>minimum</em> size that the specified child can be resized to. Called from
     * {@link #getConstraintFor(ChangeBoundsRequest, GraphicalEditPart)}. By default, a small <code>Dimension</code> is returned.
     * 
     * @param child the child
     * @return the minimum size
     */
    @objid ("7f15b719-1dec-11e2-8cad-001ec947c8cc")
    protected static Dimension getMinimumSizeFor(GraphicalEditPart child) {
        return child.getFigure().getMinimumSize();
    }

    /**
     * A translation is interpreted here as a change in order of the children. This method obtains the proper index, and then calls
     * {@link #createMoveChildCommand(EditPart, EditPart)}.
     * @see LayoutEditPolicy#getMoveChildrenCommand(Request)
     */
    @objid ("7f1354da-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getMoveChildrenCommand(Request request) {
        if (!getHostCompositeNode().allowsMove()) {
            return null;
        }
        
        // Translation
        List<?> editParts = ((ChangeBoundsRequest) request).getEditParts();
        CompoundCommand command = new CompoundCommand();
        
        EditPart insertionReference = getInsertionReference(request);
        for (int i = 0; i < editParts.size(); i++) {
            EditPart child = (EditPart) editParts.get(i);
            command.add(createMoveChildCommand(child, insertionReference));
        }
        return command.unwrap();
    }

    @objid ("7f181970-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getOrphanChildrenCommand(final Request request) {
        if (!getHostCompositeNode().allowsMove()) {
            return UnexecutableCommand.INSTANCE;
        }
        return super.getOrphanChildrenCommand(request);
    }

    @objid ("b4ef0315-8309-4b56-a933-9c8d12fdeea1")
    protected Command getResizeChildrenCommand(ChangeBoundsRequest request) {
        if (!getHostCompositeNode().allowsResize()) {
            return null;
        }
        // Resizing children: resize children as they asked (as much as
        // possible) and also resize their "neighbour" to keep the whole
        // container the same size. If there is no neighbour (resized child is
        // either the leftmost or the rightmost), in that particular case then
        // try to resize the container.
        CompoundCommand compound = new CompoundCommand();
        Transposer t = new Transposer();
        t.setEnabled(isHorizontal());
        
        ResizeChildrenCommand command = new ResizeChildrenCommand(getHostCompositeNode());
        Point trReqMoveDelta = t.t(request.getMoveDelta());
        
        List<?> resizedEditParts = request.getEditParts();
        Map<GmNodeModel, Integer> newConstraints = new HashMap<>();
        
        Dimension trContainerDelta = new Dimension();
        int maxMinorDim = 0;
        
        // Compute new children size and total size delta
        for (int i = 0; i < resizedEditParts.size(); i++) {
            GraphicalEditPart resizedChild = (GraphicalEditPart) resizedEditParts.get(i);
            Dimension trNewSize = t.t(getConstraintFor(request, resizedChild).getSize());
            Dimension trOldSize = t.t(resizedChild.getFigure().getSize());
            Dimension trSizeDelta = new Dimension(0, trNewSize.height() - trOldSize.height());
        
            maxMinorDim = Math.max(trNewSize.width(), maxMinorDim);
            newConstraints.put((GmNodeModel) resizedChild.getModel(), trNewSize.height());
        
            trContainerDelta.expand(trSizeDelta);
        
        }
        command.setNewConstraints(newConstraints);
        
        // Set container delta minor dimension
        trContainerDelta.width = maxMinorDim - getLayoutContainer().getClientArea().width();
        
        boolean topLeftBorderMoved = trReqMoveDelta.y() != 0;
        
        // Decide whether the container must be expanded or shrunk on major dimension depending on
        // the container delta, the resized children index and the resize direction
        // computed from the move delta.
        Dimension containerDelta;
        if (trContainerDelta.height() > 0) {
            // Expansion is needed : try to shrink previous/next children
            //
            // - transpose containerDelta to original coords.
            containerDelta = t.t(trContainerDelta);
        
            // - try shrink other children
            Dimension remainDelta = putShrinkChildrenConstraints(resizedEditParts, topLeftBorderMoved, containerDelta, newConstraints);
        
            // - The remaining delta is the delta the container needs to expand
            containerDelta = remainDelta ;
        } else if (trContainerDelta.height() < 0) {
            // allow container to shrink only if moving extremity borders
            if (!containsExtremity(resizedEditParts, topLeftBorderMoved)) {
                // Not an extremity:
                // - expand previous/next child instead
                Dimension toExpand = t.t(trContainerDelta.getCopy().setWidth(0)).negate();
                putExpandNeighbourChildConstraints(resizedEditParts, topLeftBorderMoved, toExpand, newConstraints);
                // - Forbid container shrink
                trContainerDelta.height = 0;
            }
        
            // transpose containerDelta to original coords.
            containerDelta = t.t(trContainerDelta);
        } else {
            // transpose containerDelta to original coords.
            containerDelta = t.t(trContainerDelta);
        }
        
        if (containerDelta.width() != 0 || containerDelta.height() != 0 ) {
            // Ask container to expand.
            //
            // Request a resize of the the container and append the
            // resulting command to the returned command.
            // Ask that container parent is resized (not this container itself,
            // as it is only meant to be a child without drag edit policy)
            getLayoutContainer().translateToAbsolute(containerDelta);
        
            ChangeBoundsRequest resizeContainerRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
            EditPart toResize = getHost().getParent();
            resizeContainerRequest.setEditParts(toResize);
            resizeContainerRequest.setSizeDelta(containerDelta);
            resizeContainerRequest.setExtendedData(request.getExtendedData());
        
            if (! request.getMoveDelta().equals(0, 0)) {
                // Top and/or left border moved
                // - major axis : use computed container delta
                // - minor axis : use initial request move delta
                Point trMoveDelta = resizeContainerRequest.getMoveDelta();
                trContainerDelta = t.t(containerDelta);
        
                trMoveDelta.x = trReqMoveDelta.x;
                trMoveDelta.y = -trContainerDelta.height();
        
                resizeContainerRequest.setMoveDelta(t.t(trMoveDelta));
            }
        
            Command parentCommand = toResize.getCommand(resizeContainerRequest);
            compound.add(parentCommand);
        }
        
        compound.add(command);
        return compound.unwrap();
    }

    /**
     * @return <code>true</code> if the host is in a horizontal orientation
     */
    @objid ("7f1354c4-1dec-11e2-8cad-001ec947c8cc")
    protected boolean isHorizontal() {
        return !getHostCompositeNode().isVertical();
    }

    /**
     * Called by the edit part listener created by {@link #createListener()} when a child edit part is added.
     * <p>
     * Try to expand the container to fit all children.
     * 
     * @param child the added edit part
     */
    @objid ("99bc7c31-bf9a-4c48-9d74-2ac6407f3f52")
    protected void onChildAdded(EditPart child) {
        // Standard behavior inherited from LayoutEditPolicy#createListener()
        decorateChild(child);
        
        // Auto expand to fit the new child
        GraphicalEditPart grChild = (GraphicalEditPart) child;
        ChangeBoundsRequest req = AutoExpandHelper.getNewChildAutoExpandRequest(getHost(),
                grChild, getLayoutContainer());
        
        if (req != null) {
            // resize needed
            AutoExpandHelper.executeExpandRequest(req, getHost());
        }
    }

    /**
     * Shows an insertion line if there is one or more current children.
     * @see LayoutEditPolicy#showLayoutTargetFeedback(Request)
     */
    @objid ("7f1354c9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void showLayoutTargetFeedback(Request request) {
        // Show nothing if we cannot issue an executable command.
        Command command = getCommand(request);
        if (!RequestConstants.REQ_MOVE.equals(request.getType()) && (command == null || !command.canExecute())) {
            return;
        }
        
        if (getHost().getChildren().isEmpty()) {
            // First child is a specific case
            showFirstChildFeedback();
        } else {
            // Otherwise, show a line where the partition would be inserted.
            showInsertionFeedback(request);
        }
    }

    /**
     * Tells whether the given list contains the first or the last child.
     * @param resizedEditParts
     * 
     * @param topBorder if true look for fist child else for last child
     * @return whether the given list contains the first or the last child.
     */
    @objid ("eb866229-2b1f-457d-9015-8541bbd15806")
    private boolean containsExtremity(List<?> resizedEditParts, boolean topBorder) {
        List<GraphicalEditPart> hostChildren = getHost().getChildren();
        int idxToFind = topBorder ? 0 : hostChildren.size() - 1;
        for (int i = 0; i < resizedEditParts.size(); i++) {
            GraphicalEditPart resizedChild = (GraphicalEditPart) resizedEditParts.get(i);
            int childIndex = hostChildren.indexOf(resizedChild);
            if (childIndex == idxToFind) {
                return true;
            }
        }
        return false;
    }

    @objid ("7f135499-1dec-11e2-8cad-001ec947c8cc")
    private static Rectangle getAbsoluteBounds(GraphicalEditPart ep) {
        Rectangle bounds = ep.getFigure().getBounds().getCopy();
        ep.getFigure().translateToAbsolute(bounds);
        return bounds;
    }

    /**
     * @return the {@link GmResizableGroup label} model of the host edit part.
     */
    @objid ("7f10f25b-1dec-11e2-8cad-001ec947c8cc")
    private GmResizableGroup getHostCompositeNode() {
        return (GmResizableGroup) getHost().getModel();
    }

    @objid ("7f18194b-1dec-11e2-8cad-001ec947c8cc")
    private GraphicalEditPart getImpactedNeighbour(GraphicalEditPart resizedChild, ChangeBoundsRequest request) {
        // Previous child is initially "null", indicating there is no neighbour
        // on the left of first child.
        GraphicalEditPart previousChild = null;
        List<GraphicalEditPart> nextChildren = new ArrayList<>(getHost().getChildren().size());
        for (Object childObj : getHost().getChildren()) {
            nextChildren.add((GraphicalEditPart) childObj);
        }
        nextChildren.removeAll(request.getEditParts());
        // Add "null" at the end, indicating there is no neighbour on the right
        // of last child.
        nextChildren.add(null);
        
        for (Object childObj : getHost().getChildren()) {
            GraphicalEditPart child = (GraphicalEditPart) childObj;
            if (child.equals(resizedChild)) {
                // Depending on the resize direction, return either previous or
                // next child, or null.
                // If movement to the right, return next child
                if (((request.getResizeDirection() & PositionConstants.EAST) != 0)
                        || ((request.getResizeDirection() & PositionConstants.SOUTH) != 0)) {
                    // Watch out: first element if the nextChildren list might
                    // be the current child, in which case just skip over it.
                    if (child.equals(nextChildren.get(0))) {
                        return nextChildren.get(1);
                    }
                    // else
                    return nextChildren.get(0);
                }
                // If movement to the left, return previous child
                if (((request.getResizeDirection() & PositionConstants.WEST) != 0)
                        || ((request.getResizeDirection() & PositionConstants.NORTH) != 0)) {
                    return previousChild;
                }
                // else
                return null;
        
            }
            // Update the nextChildren list by removing current child (note that
            // first element of nextChildren may NOT be current child, since we
            // already removed all resized children from it) and add it as the
            // previous child for next loop.
            if (child.equals(nextChildren.get(0))) {
                nextChildren.remove(0);
                previousChild = child;
            }
        
        }
        // Not found, something is wrong here
        throw new IllegalArgumentException("argument edit part is not a child of current container");
    }

    @objid ("7f1354bb-1dec-11e2-8cad-001ec947c8cc")
    private static Point getLocationFromRequest(Request request) {
        return ((DropRequest) request).getLocation();
    }

    /**
     * Return the host edit part if this policy can handle the metaclass involved in the request.
     * 
     * @param createRequest the request.
     * @return the host editpart if the metaclass involved in the request can be handled by this policy, <code>null</code>
     * otherwise.
     */
    @objid ("7f10f26f-1dec-11e2-8cad-001ec947c8cc")
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
        
            if (!canHandle(ctx.getMetaclass())) {
                return null;
            }
        }
        return getHost();
    }

    /**
     * Return the host edit part if this policy can handle all edit parts involved in the request.
     * 
     * @param changeBoundsRequest the request, can be CLONE or ADD.
     * @return the host editpart if all editparts involved in the request can be handled by this policy, <code>null</code>
     * otherwise.
     */
    @objid ("7f10f279-1dec-11e2-8cad-001ec947c8cc")
    private EditPart getTargetEditPart(ChangeBoundsRequest changeBoundsRequest) {
        for (Object editPartObj : changeBoundsRequest.getEditParts()) {
            // If there is at least 1 element that this policy cannot
            // handle, do not handle the request at all.
            final EditPart editPart = (EditPart) editPartObj;
            if (editPart.getModel() instanceof GmModel) {
                final GmModel gmModel = (GmModel) editPart.getModel();
        
                if (!canHandle(gmModel.getRelatedMClass()) && !(editPart instanceof ConnectionEditPart)) {
                    return null;
                }
        
            } else {
                // It is a probably drawing : don't handle
                return null;
            }
        }
        // This policy can handle all elements of this request: handle it.
        return getHost();
    }

    @objid ("7f181956-1dec-11e2-8cad-001ec947c8cc")
    private boolean isMove(ChangeBoundsRequest changeBoundsRequest) {
        // Start by excluding CLONE: this is never a move:
        if (REQ_CLONE.equals(changeBoundsRequest.getType())) {
            return false;
        }
        // The request is actually a move request (not taking its type into account) if the primary selection's
        // parent edit part is the host.
        for (Object o : changeBoundsRequest.getEditParts()) {
            EditPart editPart = (EditPart) o;
            if (editPart.getSelected() == EditPart.SELECTED_PRIMARY) {
                if (editPart.getParent() != null && editPart.getParent().equals(getHost())) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    @objid ("c9bed871-d50a-42c4-bdb7-04c794cc8560")
    private void putExpandNeighbourChildConstraints(List<?> resizedEditParts, boolean topLeftBorderMoved, Dimension containerDelta, Map<GmNodeModel, Integer> newConstraints) {
        List<GraphicalEditPart> hostChildren = getHost().getChildren();
        
        int shrinkIxStart;
        if (topLeftBorderMoved) {
            // top/left border moved
            shrinkIxStart = hostChildren.size() - 1 ;
        } else {
            // bottom/right border
            shrinkIxStart = 0;
        }
        
        for (int i = 0; i < resizedEditParts.size(); i++) {
            GraphicalEditPart resizedChild = (GraphicalEditPart) resizedEditParts.get(i);
            int childIndex = hostChildren.indexOf(resizedChild);
            if (topLeftBorderMoved) {
                // top/left border moved
                shrinkIxStart = Math.min(shrinkIxStart, childIndex - 1);
            }  else {
                // bottom/right border
                shrinkIxStart = Math.max(shrinkIxStart, childIndex + 1);
            }
        }
        
        ChangeBoundsRequest inverseRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        GraphicalEditPart child = hostChildren.get(shrinkIxStart);
        inverseRequest.setEditParts(child);
        inverseRequest.getSizeDelta().setSize(containerDelta);
        
        Dimension neighbourConstraint = getConstraintFor(inverseRequest, child).getSize();
        newConstraints.put((GmNodeModel) child.getModel(),
                isHorizontal() ? Integer.valueOf(neighbourConstraint.width) : Integer.valueOf(neighbourConstraint.height));
    }

    @objid ("ab220182-907e-49bb-9449-27d554d65ad8")
    private Dimension putShrinkChildrenConstraints(List<?> resizedEditParts, boolean topLeftBorderMoved, Dimension containerDelta, Map<GmNodeModel, Integer> newConstraints) {
        List<GraphicalEditPart> hostChildren = getHost().getChildren();
        
        int shrinkIxStart;
        int shrinkIxEnd;
        if (topLeftBorderMoved) {
            // top/left border moved
            shrinkIxStart = hostChildren.size() - 1 ;
            shrinkIxEnd = 0;
        } else {
            // bottom/right border
            shrinkIxStart = 0;
            shrinkIxEnd = hostChildren.size() - 1;
        }
        
        for (int i = 0; i < resizedEditParts.size(); i++) {
            GraphicalEditPart resizedChild = (GraphicalEditPart) resizedEditParts.get(i);
            int childIndex = hostChildren.indexOf(resizedChild);
            if (topLeftBorderMoved) {
                // top/left border moved
                shrinkIxStart = Math.min(shrinkIxStart, childIndex - 1);
            }  else {
                // bottom/right border
                shrinkIxStart = Math.max(shrinkIxStart, childIndex + 1);
            }
        }
        
        int shrinkIxInc = topLeftBorderMoved ? -1 : 1;
        Dimension remainDelta = putShrinkChildrenConstraints(shrinkIxStart, shrinkIxEnd, shrinkIxInc, newConstraints, containerDelta);
        return remainDelta;
    }

    /**
     * @param askedEnd last index
     * 
     * @param start start index
     * @param newConstraints the constraints map to fill
     * @param askedShrink the requested shrink (values must be positive)
     * @return the shrink that remains
     */
    @objid ("2b8e575b-93f6-43f7-af3a-6ebc78944ecc")
    private Dimension putShrinkChildrenConstraints(int start, int end, int inc, Map<GmNodeModel, Integer> newConstraints, Dimension askedShrink) {
        List<GraphicalEditPart> hostChildren = getHost().getChildren();
        Dimension remainingShrink = askedShrink.getCopy();
        boolean horizontal = isHorizontal();
        
        for (int i = start; (inc > 0 ? i <= end : i >= end) && !remainingShrink.equals(0, 0); i+=inc) {
            GraphicalEditPart child = hostChildren.get(i);
            IFigure childFig = child.getFigure();
            Dimension childSize = childFig.getSize();
            Dimension childPrefSize = childFig.getPreferredSize();
            Dimension childAvail = childSize.getShrinked(childPrefSize);
            Dimension childDelta = new Dimension();
        
            if (horizontal) {
                if (askedShrink.width() > 0 && childAvail.width > 0) {
                    childDelta.width = Math.min(remainingShrink.width, childAvail.width);
                }
            } else {
                if (askedShrink.height() > 0 && childAvail.height > 0) {
                    childDelta.height = Math.min(remainingShrink.height, childAvail.height);
                }
            }
        
            remainingShrink.shrink(childDelta);
        
            ChangeBoundsRequest inverseRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
            inverseRequest.setEditParts(child);
            inverseRequest.getSizeDelta().shrink(childDelta);
        
            Dimension neighbourConstraint = getConstraintFor(inverseRequest, child).getSize();
            newConstraints.put((GmNodeModel) child.getModel(),
                    horizontal ? Integer.valueOf(neighbourConstraint.width) : Integer.valueOf(neighbourConstraint.height));
        
        }
        return remainingShrink;
    }

    @objid ("7f181963-1dec-11e2-8cad-001ec947c8cc")
    private void showFirstChildFeedback() {
        // if this is a request for the creation of the first INNER
        // partitions, show a line in the middle of the container.
        Polyline fb = getLineFeedback();
        Transposer transposer = new Transposer();
        transposer.setEnabled(!isHorizontal());
        Rectangle r = transposer.t(getAbsoluteBounds((GraphicalEditPart) getHost()));
        Point p1 = new Point(r.x + (r.width / 2), r.y - 4);
        p1 = transposer.t(p1);
        fb.translateToRelative(p1);
        Point p2 = new Point(r.x + (r.width / 2), r.y + r.height + 4);
        p2 = transposer.t(p2);
        fb.translateToRelative(p2);
        fb.setPoint(p1, 0);
        fb.setPoint(p1, 1);
        fb.setPoint(p2, 2);
        fb.setPoint(p2, 3);
    }

    /**
     * @param request
     * @param childrenEditParts
     */
    @objid ("7f18195d-1dec-11e2-8cad-001ec947c8cc")
    private void showInsertionFeedback(Request request) {
        List<?> childrenEditParts = getHost().getChildren();
        Polyline fb = getLineFeedback();
        Transposer transposer = new Transposer();
        transposer.setEnabled(!isHorizontal());
        
        boolean before = true;
        int epIndex = getFeedbackIndexFor(request);
        Rectangle r = null;
        if (epIndex == -1) {
            before = false;
            epIndex = childrenEditParts.size() - 1;
            EditPart editPart = (EditPart) childrenEditParts.get(epIndex);
            r = transposer.t(getAbsoluteBounds((GraphicalEditPart) editPart));
        } else {
            EditPart editPart = (EditPart) childrenEditParts.get(epIndex);
            r = transposer.t(getAbsoluteBounds((GraphicalEditPart) editPart));
            Point p = transposer.t(getLocationFromRequest(request));
            if (p.x <= r.x + (r.width / 2)) {
                before = true;
            } else {
                /*
                 * We are not to the left of this Figure, so the emphasis line needs to be to the right of the previous Figure,
                 * which must be on the previous row.
                 */
                before = false;
                epIndex--;
                editPart = (EditPart) childrenEditParts.get(epIndex);
                r = transposer.t(getAbsoluteBounds((GraphicalEditPart) editPart));
            }
        }
        int x = Integer.MIN_VALUE;
        if (before) {
            /*
             * Want the line to be halfway between the end of the previous and the beginning of this one. If at the begining of a
             * line, then start halfway between the left edge of the parent and the beginning of the box, but no more than 5 pixels
             * (it would be too far and be confusing otherwise).
             */
            if (epIndex > 0) {
                // Need to determine if a line break.
                Rectangle boxPrev = transposer.t(getAbsoluteBounds((GraphicalEditPart) childrenEditParts.get(epIndex - 1)));
                int prevRight = boxPrev.right();
                if (prevRight < (r.x - ((ResizableGroupLayout) getHostFigure().getLayoutManager()).getSpacing())) {
                    // Not a line break
                    x = prevRight + (r.x - prevRight) / 2;
                } else if (prevRight == (r.x - ((ResizableGroupLayout) getHostFigure().getLayoutManager()).getSpacing())) {
                    x = prevRight + 1;
                }
            }
            if (x == Integer.MIN_VALUE) {
                // It is a line break.
                Rectangle parentBox = transposer.t(getAbsoluteBounds((GraphicalEditPart) getHost()));
                x = r.x - 5;
                if (x < parentBox.x) {
                    x = parentBox.x + (r.x - parentBox.x) / 2;
                }
            }
        } else {
            /*
             * We only have before==false if we are at the end of a line, so go halfway between the right edge and the right edge of
             * the parent, but no more than 5 pixels.
             */
            Rectangle parentBox = transposer.t(getAbsoluteBounds((GraphicalEditPart) getHost()));
            int rRight = r.x + r.width;
            int pRight = parentBox.x + parentBox.width;
            x = rRight + 5;
            if (x > pRight) {
                x = rRight + (pRight - rRight) / 2;
            }
        }
        Point header1 = new Point(x - 10, r.y - 4);
        header1 = transposer.t(header1);
        fb.translateToRelative(header1);
        Point header2 = new Point(x + 10, r.y - 4);
        header2 = transposer.t(header2);
        fb.translateToRelative(header2);
        Point p1 = new Point(x, r.y - 4);
        p1 = transposer.t(p1);
        fb.translateToRelative(p1);
        Point p2 = new Point(x, r.y + r.height + 4);
        p2 = transposer.t(p2);
        fb.translateToRelative(p2);
        fb.setPoint(header1, 0);
        fb.setPoint(header2, 1);
        fb.setPoint(p1, 2);
        fb.setPoint(p2, 3);
    }

}
