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

package org.modelio.uml.activitydiagram.editor.elements.partitioncontainer;

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
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.OrderedLayoutEditPolicy;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.DropRequest;
import org.modelio.diagram.elements.core.commands.DefaultReparentElementCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DefaultNodeResizableEditPolicy;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.uml.activitydiagram.editor.elements.partition.PartitionEditPart;
import org.modelio.uml.activitydiagram.editor.elements.partition.PartitionToolKind;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * An EditPolicy for use with {@link PartitionContainerLayout}. This EditPolicy knows how to map an <x,y> coordinate on the layout container to the appropriate index for the operation being performed. It also shows target feedback consisting of an
 * insertion line at the appropriate location.
 * 
 * 
 * @author fpoyer
 */
@objid ("2b2f1b99-55b6-11e2-877f-002564c97630")
public class PartitionContainerLayoutEditPolicy extends OrderedLayoutEditPolicy {
    @objid ("2b2f1b9b-55b6-11e2-877f-002564c97630")
    private Polyline insertionLine;

    @objid ("2b2f1b9c-55b6-11e2-877f-002564c97630")
    private static final Dimension DEFAULT_SIZE = new Dimension(-1, -1);

    @objid ("2b2f42aa-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        ModelioCreationContext ctx = (ModelioCreationContext) request.getNewObject();
        // Get the specific property "kind" from the tool, to know exactly what
        // is requested: a partition container, a sibling partition, or an inner
        // partition.
        PartitionToolKind kind = PartitionToolKind.valueOf((String) ctx.getProperties().get("kind"));
        switch (kind) {
        case INNER:
            // If asking to create INNER, we can be in 2 different cases:
            // either it is requested that we actually unmask an existing
            // partition, or that we really create 2 inner partitions.
            if (ctx.getElementToUnmask() == null
                    && !getHostCompositeNode().getChildren(GmPartitionContainer.SUB_PARTITION).isEmpty()) {
                // We can only provide a command to "split" this container
                // into 2 new sub partitions if it doesn't already have sub
                // partitions.
                // Otherwise, the use of the "create sibling partition" on
                // one of the existing children is the way to go.
                return null;
            }
            // Controlled fall through: if the request to create an
            // inner is accepted, then it is treated in exactly the same way
            // as a request to create a sibling.
            // $FALL-THROUGH$
        case SIBLING:
            EditPart insertAfter = getInsertionReference(request);
            GmNodeModel insertAfterModel = insertAfter != null ? (GmNodeModel) insertAfter.getModel() : null;
            getHostFigure().getUpdateManager().performValidation();
            return new CreatePartitionInPartitionContainerCommand(request, getHost(), ctx, insertAfterModel, -1);
        case HORIZONTAL_CONTAINER:
        case VERTICAL_CONTAINER:
        default:
            // Partition container creation is only processed by the diagram background.
            return null;
        }
    }

    @objid ("2b2f69ba-55b6-11e2-877f-002564c97630")
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

    @objid ("2b2f90ca-55b6-11e2-877f-002564c97630")
    @Override
    protected Command createAddCommand(EditPart child, EditPart after) {
        // First reparent, then put at the correct place.
        // TODO: could probably be done better with a single dedicated command...
        GmNodeModel reference = null;
        if (after != null) {
            reference = (GmNodeModel) after.getModel();
        }
        
        GmNodeModel childModel = (GmNodeModel) child.getModel();
        
        CompoundCommand compound = new CompoundCommand();
        
        compound.add(new DefaultReparentElementCommand(getHostElement(), getHostCompositeNode(), childModel,
                childModel.getLayoutData()));
        compound.add(new ReorderChildrenCommand(getHostCompositeNode(), childModel, reference));
        return compound;
    }

    /**
     * @return the {@link GmCompositeNode label} model of the host edit part.
     */
    @objid ("2b2fb7da-55b6-11e2-877f-002564c97630")
    private GmCompositeNode getHostCompositeNode() {
        return (GmCompositeNode) getHost().getModel();
    }

    /**
     * @return the element represented.
     */
    @objid ("2b313e7a-55b6-11e2-877f-002564c97630")
    protected MObject getHostElement() {
        MObject hostElement = getHostCompositeNode().getRelatedElement();
        // Watch out for partition container being on the diagram background: we
        // actually want the context of the diagram.
        if (hostElement instanceof AbstractDiagram) {
            hostElement = ((AbstractDiagram) hostElement).getOrigin();
        }
        return hostElement;
    }

    @objid ("2b32c51a-55b6-11e2-877f-002564c97630")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (RequestConstants.REQ_CREATE.equals(request.getType())) {
            // Don't handle create directly: let underlying partition what to
            // do, whether we are dealing with Inner or Sibling creation etc...
            // CreateRequest createRequest = (CreateRequest) request;
            // return getTargetEditPart(createRequest);
            return null;
        }
        if (RequestConstants.REQ_ADD.equals(request.getType()) || RequestConstants.REQ_CLONE.equals(request.getType()) || RequestConstants.REQ_MOVE.equals(request.getType())) {
            ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) request;
            return getTargetEditPart(changeBoundsRequest);
        }
        return null;
    }

    /**
     * Return the host edit part if this policy can handle all edit parts involved in the request.
     * 
     * @param changeBoundsRequest the request, can be CLONE or ADD.
     * @return the host editpart if all editparts involved in the request can be handled by this policy, <code>null</code> otherwise.
     */
    @objid ("2b32c520-55b6-11e2-877f-002564c97630")
    private EditPart getTargetEditPart(ChangeBoundsRequest changeBoundsRequest) {
        for (Object editPartObj : changeBoundsRequest.getEditParts()) {
            // If there is at least 1 element that this policy cannot
            // handle, do not handle the request at all!
            final EditPart editPart = (EditPart) editPartObj;
            if (editPart.getModel() instanceof GmModel) {
                final GmModel gmModel = (GmModel) editPart.getModel();
        
                final String metaclassName = gmModel.getRepresentedRef().mc;
        
                if (!canHandle(metaclassName) && !(editPart instanceof ConnectionEditPart)) {
                    return null;
                }
            } else {
                // Probably a drawing
                return null;
            }
        }
        // This policy can handle all elements of this request: handle it!
        return getHost();
    }

    /**
     * Returns whether this edit policy can handle this metaclass (either through simple or smart behavior). Default behavior is to accept any metaclass that can be child (in the CreationExpert's understanding) of the host's metaclass This method should be
     * overridden by subclasses to add specific the behavior.
     * 
     * @param metaclass the metaclass to handle.
     * @return true if this policy can handle the metaclass.
     */
    @objid ("2b32c526-55b6-11e2-877f-002564c97630")
    protected boolean canHandle(String metaclass) {
        MObject hostElement = getHostElement();
        if (hostElement == null) {
            return false;
        }
        MMetamodel mm = hostElement.getMClass().getMetamodel();
        MExpert mExpert = mm.getMExpert();
        MClass mc = mm.getMClass(metaclass);
        return (mc != null)
                                && (mExpert.canCompose(hostElement, mc, null) && ((GmCompositeNode) getHost().getModel()).canCreate(mc
                                        .getJavaInterface()));
    }

    @objid ("2b32c52e-55b6-11e2-877f-002564c97630")
    @Override
    protected void eraseLayoutTargetFeedback(Request request) {
        if (this.insertionLine != null) {
            removeFeedback(this.insertionLine);
            this.insertionLine = null;
        }
    }

    @objid ("2b32c532-55b6-11e2-877f-002564c97630")
    private Rectangle getAbsoluteBounds(GraphicalEditPart ep) {
        Rectangle bounds = ep.getFigure().getBounds().getCopy();
        ep.getFigure().translateToAbsolute(bounds);
        return bounds;
    }

    /**
     * @param request the Request
     * @return the index for the insertion reference
     */
    @objid ("2b32c537-55b6-11e2-877f-002564c97630")
    protected int getFeedbackIndexFor(Request request) {
        List<?> children = getHost().getChildren();
        if (children.isEmpty()) {
            return -1;
        }
        
        Transposer transposer = new Transposer();
        transposer.setEnabled(!isHorizontal());
        
        Point p = transposer.t(getLocationFromRequest(request));
        
        // Current row bottom, initialize to above the top.
        int rowBottom = Integer.MIN_VALUE;
        int candidate = -1;
        for (int i = 0; i < children.size(); i++) {
            EditPart child = (EditPart) children.get(i);
            Rectangle rect = transposer.t(getAbsoluteBounds(((GraphicalEditPart) child)));
            if (rect.y > rowBottom) {
                /*
                 * We are in a new row, so if we don't have a candidate but yet are within the previous row, then the current entry becomes the candidate. This is because we know we must be to the right of center of the last Figure in the previous row, so
                 * this Figure (which is at the start of a new row) is the candidate.
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
                     * Now we have determined that the cursor.Y is above the bottom of the current row of figures. Stop now, to prevent the next row from being searched
                     */
                    break;
                }
            }
        }
        return candidate;
    }

    @objid ("2b32c53d-55b6-11e2-877f-002564c97630")
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
    @objid ("2b32c543-55b6-11e2-877f-002564c97630")
    protected Polyline getLineFeedback() {
        if (this.insertionLine == null) {
            this.insertionLine = new Polyline();
            this.insertionLine.setForegroundColor(ColorConstants.blue);
            this.insertionLine.setLineWidth(3);
            this.insertionLine.addPoint(new Point(-5, 0));
            this.insertionLine.addPoint(new Point(+5, 0));
            this.insertionLine.addPoint(new Point(0, 0));
            this.insertionLine.addPoint(new Point(10, 10));
            addFeedback(this.insertionLine);
        }
        return this.insertionLine;
    }

    @objid ("2b32c548-55b6-11e2-877f-002564c97630")
    private Point getLocationFromRequest(Request request) {
        return ((DropRequest) request).getLocation();
    }

    /**
     * @return <code>true</code> if the host is in a horizontal orientation
     */
    @objid ("2b32c54d-55b6-11e2-877f-002564c97630")
    protected boolean isHorizontal() {
        return !((GmPartitionContainer) getHost().getModel()).isVertical();
    }

    /**
     * Shows an insertion line if there is one or more current children.
     * @see LayoutEditPolicy#showLayoutTargetFeedback(Request)
     */
    @objid ("2b32c552-55b6-11e2-877f-002564c97630")
    @Override
    protected void showLayoutTargetFeedback(Request request) {
        // Show nothing if we cannot issue an executable command.
        Command command = getCommand(request);
        if (!RequestConstants.REQ_MOVE.equals(request.getType()) && (command == null || !command.canExecute())) {
            return;
        }
        List<Object> partitionChildren = new ArrayList<>(getHost().getChildren());
        // Better safe than sorry: keep only the partitions!
        for (Object child : getHost().getChildren()) {
            if (!(child instanceof PartitionEditPart)) {
                partitionChildren.remove(child);
            }
        }
        if (partitionChildren.isEmpty()) {
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
            return;
        }
        
        // Otherwise, show a line where the partition would be inserted.
        Polyline fb = getLineFeedback();
        Transposer transposer = new Transposer();
        transposer.setEnabled(!isHorizontal());
        
        boolean before = true;
        int epIndex = getFeedbackIndexFor(request);
        Rectangle r = null;
        if (epIndex == -1) {
            before = false;
            epIndex = partitionChildren.size() - 1;
            EditPart editPart = (EditPart) partitionChildren.get(epIndex);
            r = transposer.t(getAbsoluteBounds((GraphicalEditPart) editPart));
        } else {
            EditPart editPart = (EditPart) partitionChildren.get(epIndex);
            r = transposer.t(getAbsoluteBounds((GraphicalEditPart) editPart));
            Point p = transposer.t(getLocationFromRequest(request));
            if (p.x <= r.x + (r.width / 2)) {
                before = true;
            } else {
                /*
                 * We are not to the left of this Figure, so the emphasis line needs to be to the right of the previous Figure, which must be on the previous row.
                 */
                before = false;
                epIndex--;
                editPart = (EditPart) partitionChildren.get(epIndex);
                r = transposer.t(getAbsoluteBounds((GraphicalEditPart) editPart));
            }
        }
        int x = Integer.MIN_VALUE;
        if (before) {
            /*
             * Want the line to be halfway between the end of the previous and the beginning of this one. If at the begining of a line, then start halfway between the left edge of the parent and the beginning of the box, but no more than 5 pixels (it would
             * be too far and be confusing otherwise).
             */
            if (epIndex > 0) {
                // Need to determine if a line break.
                Rectangle boxPrev = transposer.t(getAbsoluteBounds((GraphicalEditPart) partitionChildren.get(epIndex - 1)));
                int prevRight = boxPrev.right();
                if (prevRight < (r.x - ((PartitionContainerLayout) getHostFigure().getLayoutManager()).getSpacing())) {
                    // Not a line break
                    x = prevRight + (r.x - prevRight) / 2;
                } else if (prevRight == (r.x - ((PartitionContainerLayout) getHostFigure().getLayoutManager()).getSpacing())) {
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
             * We only have before==false if we are at the end of a line, so go halfway between the right edge and the right edge of the parent, but no more than 5 pixels.
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

    @objid ("2b344bba-55b6-11e2-877f-002564c97630")
    @Override
    protected EditPolicy createChildEditPolicy(EditPart child) {
        // if child is a node that can provide its own policy (maybe for keeping
        // specific geometric needs, etc) return it.
        if (child instanceof AbstractNodeEditPart) {
            AbstractNodeEditPart childNode = (AbstractNodeEditPart) child;
            SelectionEditPolicy childPolicy = childNode.getPreferredDragRolePolicy(RequestConstants.REQ_RESIZE);
            if (childPolicy != null) {
                return childPolicy;
            }
        }
        // default
        return new DefaultNodeResizableEditPolicy();
    }

    /**
     * A translation is interpreted here as a change in order of the children. This method obtains the proper index, and then calls {@link #createMoveChildCommand(EditPart, EditPart)}.
     * @see LayoutEditPolicy#getMoveChildrenCommand(Request)
     */
    @objid ("2b344bc0-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getMoveChildrenCommand(Request request) {
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

    /**
     * Overridden to prevent sizes from becoming too small, and to prevent preferred sizes from getting lost. If the Request is a MOVE, the existing width and height are preserved. During RESIZE, the new width and height have a lower bound determined by
     * {@link #getMinimumSizeFor(GraphicalEditPart)}.
     * 
     * @param request the ChangeBoundsRequest.
     * @param child the child EditPart for which the constraint should be generated.
     * @return the rectangle being the desired bounds of the child.
     */
    @objid ("2b344bc7-55b6-11e2-877f-002564c97630")
    protected Rectangle getConstraintFor(ChangeBoundsRequest request, GraphicalEditPart child) {
        Rectangle rect = new PrecisionRectangle(child.getFigure().getBounds());
        Rectangle original = rect.getCopy();
        child.getFigure().translateToAbsolute(rect);
        rect = request.getTransformedRectangle(rect);
        child.getFigure().translateToRelative(rect);
        rect.translate(getLayoutContainer().getClientArea().getLocation().getNegated());
        
        if (request.getSizeDelta().equals(0, 0)) {
            // It is a move
            Rectangle cons = getCurrentConstraintFor(child);
            if (cons != null) {
                // method
                rect.setSize(cons.width, cons.height);
            }
        } else {
            // It is a resize
            Dimension minSize = getMinimumSizeFor(child);
            if (rect.width < minSize.width) {
                rect.width = minSize.width;
                if (rect.x > (original.right() - minSize.width)) {
                    rect.x = original.right() - minSize.width;
                }
            }
            if (rect.height < minSize.height) {
                rect.height = minSize.height;
                if (rect.y > (original.bottom() - minSize.height)) {
                    rect.y = original.bottom() - minSize.height;
                }
            }
        }
        return getConstraintFor(rect);
    }

    /**
     * Returns a Rectangle at the given Point with width and height of -1. Layout uses width or height equal to '-1' to mean use the figure's preferred size.
     * 
     * @param p the input Point
     * @return a Rectangle
     */
    @objid ("2b344bce-55b6-11e2-877f-002564c97630")
    public Rectangle getConstraintFor(Point p) {
        return new Rectangle(p, PartitionContainerLayoutEditPolicy.DEFAULT_SIZE);
    }

    /**
     * Returns a new Rectangle equivalent to the passed Rectangle.
     * 
     * @param r the input Rectangle
     * @return a copy of the input Rectangle
     */
    @objid ("2b344bd4-55b6-11e2-877f-002564c97630")
    public Rectangle getConstraintFor(Rectangle r) {
        return new Rectangle(r);
    }

    /**
     * Retrieves the child's current constraint from the <code>LayoutManager</code>.
     * 
     * @param child the child
     * @return the current constraint
     */
    @objid ("2b344bda-55b6-11e2-877f-002564c97630")
    protected Rectangle getCurrentConstraintFor(GraphicalEditPart child) {
        IFigure fig = child.getFigure();
        return (Rectangle) fig.getParent().getLayoutManager().getConstraint(fig);
    }

    /**
     * Determines the <em>minimum</em> size that the specified child can be resized to. Called from {@link #getConstraintFor(ChangeBoundsRequest, GraphicalEditPart)}. By default, a small <code>Dimension</code> is returned.
     * 
     * @param child the child
     * @return the minumum size
     */
    @objid ("2b344be0-55b6-11e2-877f-002564c97630")
    protected Dimension getMinimumSizeFor(GraphicalEditPart child) {
        return child.getFigure().getMinimumSize();
    }

    @objid ("2b344be6-55b6-11e2-877f-002564c97630")
    @Override
    public Command getCommand(Request request) {
        if (RequestConstants.REQ_RESIZE_CHILDREN.equals(request.getType())) {
            return getResizeChildrenCommand((ChangeBoundsRequest) request);
        }
        // else
        return super.getCommand(request);
    }

    /**
     * @param request the resize children request.
     * @return the command resizing the children.
     */
    @objid ("2b344bec-55b6-11e2-877f-002564c97630")
    protected Command getResizeChildrenCommand(ChangeBoundsRequest request) {
        // Resizing children: resize children as they asked (as much as
        // possible) and also resize their "neighbour" to keep the whole
        // container the same size. If there is no neighbour (resized child is
        // either the leftmost or the rightmost), in that particular case then
        // resize the container.
        CompoundCommand compound = new CompoundCommand();
        Map<GmNodeModel, Integer> newConstraints = new HashMap<>();
        
        for (GraphicalEditPart resizedChild : (List<GraphicalEditPart>) request.getEditParts()) {
            Dimension constraint = getConstraintFor(request, resizedChild).getSize();
        
            newConstraints.put((GmNodeModel) resizedChild.getModel(),
                    isHorizontal() ? Integer.valueOf(constraint.width) : Integer.valueOf(constraint.height));
        
            /*
             * // Get the impacted neighbour: GraphicalEditPart impactedNeighbour = getImpactedNeighbour(resizedChild, request); if (impactedNeighbour != null) { // resize said neighbour addResizedNeighbourConstraint(request, newConstraints,
             * impactedNeighbour); } else { // No neighbour, this means the resizedChild is on a border: // request a resize of the the container and append the // resulting command to the returned command. getResizeContainerCommand(request, compound); }
             */
        
            Dimension constraintDelta = constraint.getShrinked(resizedChild.getFigure().getSize());
            resizedChild.getFigure().translateToAbsolute(constraintDelta);
        
            getResizeContainerCommand(request, compound, constraintDelta);
        }
        
        ResizePartitionsCommand command = new ResizePartitionsCommand((GmPartitionContainer) getHost().getModel());
        command.setNewConstraints(newConstraints);
        compound.add(command);
        return compound;
    }

    /**
     * Generates a draw2d constraint for the given <code>CreateRequest</code>. If the CreateRequest has a size, {@link #getConstraintFor(Rectangle)} is called with a Rectangle of that size and the result is returned. This is used during size-on-drop
     * creation. Otherwise, {@link #getConstraintFor(Point)} is returned.
     * <P>
     * The CreateRequest's location is relative the Viewer. The location is made layout-relative before calling one of the methods mentioned above.
     * 
     * @param request the CreateRequest
     * @return a draw2d constraint
     */
    @objid ("2b35d25d-55b6-11e2-877f-002564c97630")
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
        return getConstraintFor(new Rectangle(where, size));
    }

    @objid ("2b35d263-55b6-11e2-877f-002564c97630")
    private GraphicalEditPart getImpactedNeighbour(GraphicalEditPart resizedChild, ChangeBoundsRequest request) {
        int idx = getImpactedNeighbourIndex(resizedChild, request);
        if (idx == -1) {
            return null;
        } else {
            return (GraphicalEditPart) getHost().getChildren().get(idx);
        }
    }

    @objid ("2b35d268-55b6-11e2-877f-002564c97630")
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

    @objid ("2b35d26f-55b6-11e2-877f-002564c97630")
    protected void getResizeContainerCommand(final ChangeBoundsRequest request, final CompoundCommand compound, Dimension constraintDelta) {
        ChangeBoundsRequest resizeContainerRequest = RequestHelper.deepCopy(request);
        resizeContainerRequest.setType(RequestConstants.REQ_RESIZE);
        resizeContainerRequest.setEditParts(getHost());
        resizeContainerRequest.getSizeDelta().setSize(constraintDelta);
        Point moveDelta = resizeContainerRequest.getMoveDelta();
        moveDelta.setLocation(
                (int) Math.signum(moveDelta.x) * Math.abs(constraintDelta.width),
                (int) Math.signum(moveDelta.y) * Math.abs(constraintDelta.height));
        // Only ask to be resized in the "major" axis.
        // if (isHorizontal())
        // sizeDelta.height = 0;
        // else
        // sizeDelta.width = 0;
        
        Command parentCommand = getHost().getCommand(resizeContainerRequest);
        compound.add(parentCommand);
    }

    @objid ("2b35d275-55b6-11e2-877f-002564c97630")
    protected void addResizedNeighbourConstraint(final ChangeBoundsRequest request, final Map<GmNodeModel, Integer> newConstraints, final GraphicalEditPart impactedNeighbour) {
        // Resize neighbour to compensate for size change of resizedChild.
        ChangeBoundsRequest inverseRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        inverseRequest.setEditParts(impactedNeighbour);
        inverseRequest.setSizeDelta(request.getSizeDelta().getNegated());
        // TODO: reverse direction?
        inverseRequest.setResizeDirection(request.getResizeDirection());
        
        Dimension neighbourConstraint = getConstraintFor(inverseRequest, impactedNeighbour).getSize();
        
        newConstraints.put((GmNodeModel) impactedNeighbour.getModel(),
                isHorizontal() ? Integer.valueOf(neighbourConstraint.width) : Integer.valueOf(neighbourConstraint.height));
    }

    @objid ("6a594794-4552-45f3-b598-4899df6f8bed")
    private int getImpactedNeighbourIndex(GraphicalEditPart resizedChild, ChangeBoundsRequest request) {
        List<?> hostChildren = getHost().getChildren();
        
        int childIndex = hostChildren.indexOf(resizedChild);
        int neighbourIndex = childIndex;
        
        if (childIndex == -1) {
            // Not found, something is wrong here
            throw new IllegalArgumentException(String.format("%s is not a child of %s container", resizedChild, getHost()));
        }
        
        // Depending on the resize direction, return either previous or
        // next child, or null.
        int resizeDir = request.getResizeDirection();
        if ((resizeDir & (PositionConstants.EAST | PositionConstants.SOUTH)) != 0) {
            // If movement to the right, return next child
            neighbourIndex++;
        }
        if ((resizeDir & (PositionConstants.WEST | PositionConstants.NORTH)) != 0) {
            // If movement to the left, return previous child
            neighbourIndex--;
        }
        
        // case for both directions:
        if (neighbourIndex == childIndex) {
            neighbourIndex++;
        }
        
        if (neighbourIndex < 0 || neighbourIndex >= hostChildren.size()) {
            return -1;
        } else {
            return neighbourIndex;
        }
    }

}
