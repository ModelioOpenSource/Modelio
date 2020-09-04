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

package org.modelio.diagram.editor.bpmn.elements.bpmnlanesetcontainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polyline;
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
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.OrderedLayoutEditPolicy;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.DropRequest;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.BpmnLaneEditPart;
import org.modelio.diagram.editor.bpmn.elements.diagrams.GmBpmnDiagramStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.policies.BpmnLaneReparentElementCommand;
import org.modelio.diagram.editor.bpmn.elements.policies.BpmnLaneSetContainerLayout;
import org.modelio.diagram.elements.core.commands.DefaultReparentElementCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.figures.ChainedLayout;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.AutoExpandHelper;
import org.modelio.diagram.elements.core.policies.DefaultNodeResizableEditPolicy;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * An EditPolicy for use with {@link BpmnLaneSetContainerLayout}.
 * <p>
 * This EditPolicy knows how to map an <x,y> coordinate on the layout container to the appropriate index for the operation being performed. It also shows target feedback consisting of an insertion line at the appropriate location.
 */
@objid ("613c1561-55b6-11e2-877f-002564c97630")
class BpmnLaneSetContainerLayoutEditPolicy extends OrderedLayoutEditPolicy {
    @objid ("59da3bdc-5290-4df9-a7d9-300e0266d9a5")
    private Polyline insertionLine;

    @objid ("bd70a3c0-a71c-41f2-9ce0-84b233b94371")
    private static final Dimension DEFAULT_SIZE = new Dimension(-1, -1);

    @objid ("613c1566-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        ModelioCreationContext ctx = ModelioCreationContext.lookRequest(request);
        if (ctx != null) {
            final Class<? extends MObject> cls = ctx.getJavaClass();
            MObject hostElement = getHostElement();
            MObject elementToUnmask = ctx.getElementToUnmask();
            GmCompositeNode gmParentNode = getHostCompositeNode();
        
            if (cls == BpmnLane.class) {
                if (elementToUnmask != null && !gmParentNode.canUnmask(elementToUnmask)) {
                    return null;
                }
        
                Object requestConstraint = getConstraintForCreate(request);
                return new CreateBpmnLaneSetContainerCommand(hostElement, gmParentNode, ctx, requestConstraint, getInsertionReference(request));
            }
        }
        return null;
    }

    @objid ("613c156c-55b6-11e2-877f-002564c97630")
    @Override
    protected Command createMoveChildCommand(EditPart child, EditPart after) {
        // if child is a 'node' it usually can be resized and/or moved
        if (child instanceof AbstractNodeEditPart) {
            GmNodeModel reference = null;
            if (after != null) {
                reference = (GmNodeModel) after.getModel();
            }
            ReorderChildrenCommand command = new ReorderChildrenCommand(getHostCompositeNode(), (GmNodeModel) child.getModel(), reference);
            return command;
        }
        return null;
    }

    @objid ("613c1573-55b6-11e2-877f-002564c97630")
    @Override
    protected Command createAddCommand(EditPart child, EditPart after) {
        GmNodeModel childModel = (GmNodeModel) child.getModel();
        GmNodeModel gmmodel = childModel;
        MObject element = gmmodel.getRelatedElement();
        
        GmNodeModel reference = null;
        if (after != null) {
            reference = (GmNodeModel) after.getModel();
        }
        
        CompoundCommand compound = new CompoundCommand();
        if (element instanceof BpmnLane) {
            compound.add(new BpmnLaneReparentElementCommand(getHostElement(), getHostCompositeNode(), childModel, childModel.getLayoutData()));
        } else {
            compound.add(new DefaultReparentElementCommand(getHostElement(), getHostCompositeNode(), childModel, childModel.getLayoutData()));
        }
        
        if (reference != null) {
            compound.add(new ReorderChildrenCommand(getHostCompositeNode(), childModel, reference));
        }
        return compound.unwrap();
    }

    /**
     * @return the {@link GmCompositeNode label} model of the host edit part.
     */
    @objid ("613c157a-55b6-11e2-877f-002564c97630")
    private GmCompositeNode getHostCompositeNode() {
        return (GmCompositeNode) getHost().getModel();
    }

    /**
     * @return the element represented.
     */
    @objid ("613d9bdf-55b6-11e2-877f-002564c97630")
    protected MObject getHostElement() {
        MObject hostElement = getHostCompositeNode().getRelatedElement();
        // Watch out for partition container being on the diagram background: we
        // actually want the context of the diagram.
        if (hostElement instanceof AbstractDiagram) {
            hostElement = ((AbstractDiagram) hostElement).getOrigin();
        }
        return hostElement;
    }

    @objid ("613d9be6-55b6-11e2-877f-002564c97630")
    @Override
    public EditPart getTargetEditPart(Request request) {
        Object type = request.getType();
        if (RequestConstants.REQ_CREATE.equals(type)) {
            // Don't handle create directly: let underlying partition what to
            // do, whether we are dealing with Inner or Sibling creation etc...
            // CreateRequest createRequest = (CreateRequest) request;
            // return getTargetEditPart(createRequest);
            return null;
        } else if (RequestConstants.REQ_ADD.equals(type)
                || RequestConstants.REQ_CLONE.equals(type)
                || RequestConstants.REQ_MOVE.equals(type)) {
            ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) request;
            return getTargetEditPart(changeBoundsRequest);
        }
        return null;
    }

    /**
     * Return the host edit part if this policy can handle all edit parts involved in the request.
     * @param changeBoundsRequest the request, can be CLONE or ADD.
     * @return the host editpart if all editparts involved in the request can be handled by this policy, <code>null</code> otherwise.
     */
    @objid ("613d9bec-55b6-11e2-877f-002564c97630")
    private EditPart getTargetEditPart(ChangeBoundsRequest changeBoundsRequest) {
        for (Object editPartObj : changeBoundsRequest.getEditParts()) {
            // If there is at least 1 element that this policy cannot
            // handle, do not handle the request at all!
            final EditPart editPart = (EditPart) editPartObj;
            if (editPart.getModel() instanceof GmModel) {
                final GmModel gmModel = (GmModel) editPart.getModel();
        
                if (!canHandle(gmModel.getRelatedMClass()) && !(editPart instanceof ConnectionEditPart)) {
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
     * @param metaclass the metaclass to handle.
     * @return true if this policy can handle the metaclass.
     */
    @objid ("613d9bf2-55b6-11e2-877f-002564c97630")
    protected boolean canHandle(MClass metaclass) {
        MObject hostElement = getHostElement();
        if (hostElement == null) {
            return false;
        }
        return hostElement.getMClass().getMetamodel().getMExpert().canCompose(hostElement, metaclass, null)
                                && getHostCompositeNode().canCreate(metaclass.getJavaInterface());
    }

    @objid ("613d9bfa-55b6-11e2-877f-002564c97630")
    @Override
    protected void eraseLayoutTargetFeedback(Request request) {
        if (this.insertionLine != null) {
            removeFeedback(this.insertionLine);
            this.insertionLine = null;
        }
    }

    @objid ("613d9bfe-55b6-11e2-877f-002564c97630")
    private static Rectangle getAbsoluteBounds(GraphicalEditPart ep) {
        Rectangle bounds = ep.getFigure().getBounds().getCopy();
        ep.getFigure().translateToAbsolute(bounds);
        return bounds;
    }

    /**
     * @param request the Request
     * @return the index for the insertion reference
     */
    @objid ("613d9c03-55b6-11e2-877f-002564c97630")
    protected int getFeedbackIndexFor(Request request) {
        List<?> children = getHost().getChildren();
        if (children.isEmpty()) {
            return -1;
        }
        
        Transposer transposer = new Transposer();
        transposer.setEnabled(isHorizontalLaneOrientation());
        
        Point p = transposer.t(getLocationFromRequest(request));
        
        // Current row bottom, initialize to above the top.
        int rowBottom = Integer.MIN_VALUE;
        int candidate = -1;
        for (int i = 0; i < children.size(); i++) {
            EditPart child = (EditPart) children.get(i);
            Rectangle rect = transposer.t(getAbsoluteBounds(((GraphicalEditPart) child)));
            if (rect.y > rowBottom) {
                /*
                 * We are in a new row, so if we don't have a candidate but yet are within the previous row,
                 * then the current entry becomes the candidate.
                 * This is because we know we must be to the right of center of the last Figure in the previous row,
                 * so this Figure (which is at the start of a new row) is the candidate.
                 */
                if (p.y <= rowBottom) {
                    if (candidate == -1) {
                        candidate = i;
                    }
                    break;
                } else {
                    // Mouse's Y is outside the row, so reset the candidate
                    candidate = -1;
                }
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
                     * Now we have determined that the cursor.Y is above the bottom of the current row of figures.
                     * Stop now, to prevent the next row from being searched
                     */
                    break;
                }
            }
        }
        return candidate;
    }

    @objid ("613d9c09-55b6-11e2-877f-002564c97630")
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
     * @return a Polyline figure
     */
    @objid ("613d9c0f-55b6-11e2-877f-002564c97630")
    protected Polyline getLineFeedback() {
        if (this.insertionLine == null) {
            this.insertionLine = new Polyline();
            this.insertionLine.setForegroundColor(ColorConstants.green);
            this.insertionLine.setLineWidth(3);
            this.insertionLine.addPoint(new Point(-5, 0));
            this.insertionLine.addPoint(new Point(+5, 0));
            this.insertionLine.addPoint(new Point(0, 0));
            this.insertionLine.addPoint(new Point(10, 10));
            addFeedback(this.insertionLine);
        }
        return this.insertionLine;
    }

    @objid ("613d9c14-55b6-11e2-877f-002564c97630")
    private Point getLocationFromRequest(Request request) {
        return ((DropRequest) request).getLocation();
    }

    /**
     * Shows an insertion line if there is one or more current children.
     * @see LayoutEditPolicy#showLayoutTargetFeedback(Request)
     */
    @objid ("613f227e-55b6-11e2-877f-002564c97630")
    @Override
    protected void showLayoutTargetFeedback(Request request) {
        // Show nothing if we cannot issue an executable command.
        Command command = getCommand(request);
        boolean isMoveReq = RequestConstants.REQ_MOVE.equals(request.getType());
        if (!isMoveReq && (command == null || !command.canExecute())) {
            return;
        }
        
        // Better safe than sorry: keep only the partitions!
        @SuppressWarnings ("unchecked")
        List<GraphicalEditPart> partitionChildren = new ArrayList<>(getHost().getChildren());
        partitionChildren.removeIf(child -> !(child instanceof BpmnLaneEditPart));
        
        if (partitionChildren.isEmpty()) {
            // if this is a request for the creation of the first INNER
            // partitions, show a line in the middle of the container.
            Polyline fb = getLineFeedback();
            Transposer transposer = new Transposer();
            transposer.setEnabled(isHorizontalLaneOrientation());
            Rectangle r = transposer.t(getAbsoluteBounds((GraphicalEditPart) getHost()));
            Point p1 = new Point(r.x + (r.width / 2), r.y);
            p1 = transposer.t(p1);
            fb.translateToRelative(p1);
            Point p2 = new Point(r.x + (r.width / 2), r.y + r.height);
            p2 = transposer.t(p2);
            fb.translateToRelative(p2);
            fb.setPoint(p1, 0);
            fb.setPoint(p1, 1);
            fb.setPoint(p2, 2);
            fb.setPoint(p2, 3);
            return;
        } else if (isMoveReq && partitionChildren.size() == 1) {
            // only 1 partition, no reordering
            return;
        }
        
        // Otherwise, show a line where the partition would be inserted.
        Polyline fb = getLineFeedback();
        Transposer transposer = new Transposer();
        transposer.setEnabled(isHorizontalLaneOrientation());
        
        boolean before = true;
        int epIndex = getFeedbackIndexFor(request);
        Rectangle r = null;
        if (epIndex == -1) {
            before = false;
            epIndex = partitionChildren.size() - 1;
            GraphicalEditPart editPart = partitionChildren.get(epIndex);
            r = transposer.t(getAbsoluteBounds(editPart));
        } else {
            GraphicalEditPart editPart = partitionChildren.get(epIndex);
            r = transposer.t(getAbsoluteBounds(editPart));
            Point p = transposer.t(getLocationFromRequest(request));
            if (p.x <= r.x + (r.width / 2)) {
                before = true;
            } else {
                /*
                 * We are not to the left of this Figure, so the emphasis line needs to be to the right of the previous Figure, which must be on the previous row.
                 */
                before = false;
                epIndex--;
                editPart = partitionChildren.get(epIndex);
                r = transposer.t(getAbsoluteBounds(editPart));
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
                Rectangle boxPrev = transposer.t(getAbsoluteBounds(partitionChildren.get(epIndex - 1)));
                int prevRight = boxPrev.right();
                BpmnLaneSetContainerLayout lm = (BpmnLaneSetContainerLayout) ChainedLayout.getRootLayout(getHostFigure());
                if (prevRight < (r.x - lm.getSpacing())) {
                    // Not a line break
                    x = prevRight + (r.x - prevRight) / 2;
                } else if (prevRight == (r.x - lm.getSpacing())) {
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
        
        Point header1 = new Point(x - 10, r.y);
        header1 = transposer.t(header1);
        fb.translateToRelative(header1);
        
        Point header2 = new Point(x + 10, r.y);
        header2 = transposer.t(header2);
        fb.translateToRelative(header2);
        
        Point p1 = new Point(x, r.y);
        p1 = transposer.t(p1);
        fb.translateToRelative(p1);
        
        Point p2 = new Point(x, r.y + r.height);
        p2 = transposer.t(p2);
        fb.translateToRelative(p2);
        
        fb.setPoint(header1, 0);
        fb.setPoint(header2, 1);
        fb.setPoint(p1, 2);
        fb.setPoint(p2, 3);
    }

    @objid ("613f2283-55b6-11e2-877f-002564c97630")
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
    @objid ("613f2289-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getMoveChildrenCommand(Request request) {
        ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) request;
        List<?> editParts = changeBoundsRequest.getEditParts();
        CompoundCommand command = new CompoundCommand();
        
        // Handle child reordering first
        EditPart insertionReference = getInsertionReference(request);
        int refIndex = getHost().getChildren().indexOf(insertionReference);
        for (int i = 0; i < editParts.size(); i++) {
            EditPart child = (EditPart) editParts.get(i);
        
            int childIndex = getHost().getChildren().indexOf(child);
            boolean isToBeMoved = childIndex != refIndex - 1;
            boolean isLast = childIndex == getHost().getChildren().size() - 1;
            if (isToBeMoved && !isLast) {
                // create a move command if child is nor at the correct place, nor last
                command.add(createMoveChildCommand(child, insertionReference));
            }
        }
        
        if (!command.isEmpty()) {
            // Return reordering command
            return command.unwrap();
        } else {
            // No child reordering, consider the request as a move of the container itself
            ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_MOVE_CHILDREN);
            req.setEditParts(getHost());
        
            req.setMoveDelta(changeBoundsRequest.getMoveDelta());
            req.setSizeDelta(changeBoundsRequest.getSizeDelta());
            req.setLocation(changeBoundsRequest.getLocation());
            req.setExtendedData(changeBoundsRequest.getExtendedData());
            return getHost().getParent().getCommand(req);
        }
    }

    /**
     * Overridden to prevent sizes from becoming too small, and to prevent preferred sizes from getting lost. If the Request is a MOVE, the existing width and height are preserved. During RESIZE, the new width and height have a lower bound determined by
     * {@link #getMinimumSizeFor(GraphicalEditPart)}.
     * @param request the ChangeBoundsRequest.
     * @param child the child EditPart for which the constraint should be generated.
     * @return the rectangle being the desired bounds of the child.
     */
    @objid ("613f2290-55b6-11e2-877f-002564c97630")
    protected Rectangle getBoundsFor(ChangeBoundsRequest request, GraphicalEditPart child) {
        IFigure childFigure = child.getFigure();
        Rectangle rect = new PrecisionRectangle(childFigure.getBounds());
        Rectangle original = rect.getCopy();
        
        childFigure.translateToAbsolute(rect);
        rect = request.getTransformedRectangle(rect);
        childFigure.translateToRelative(rect);
        // rect.translate(getLayoutContainer().getClientArea().getLocation().negate());
        
        if (request.getSizeDelta().equals(0, 0)) {
            // It is a move
            return rect;
        } else {
            // It is a resize
            Dimension minSize = getMinimumSizeFor(child);
            if (rect.width < minSize.width) {
                rect.width = minSize.width;
                int minLeft = original.right() - minSize.width;
                if (rect.x > minLeft) {
                    rect.x = minLeft;
                }
            }
            if (rect.height < minSize.height) {
                rect.height = minSize.height;
                int minTop = original.bottom() - minSize.height;
                if (rect.y > minTop) {
                    rect.y = minTop;
                }
            }
        }
        return rect;
    }

    /**
     * Determines the <em>minimum</em> size that the specified child can be resized to.
     * Called from {@link #getBoundsFor(ChangeBoundsRequest, GraphicalEditPart)}.
     * By default, a small <code>Dimension</code> is returned.
     * @param child the child
     * @return the minimum size
     */
    @objid ("613f22a9-55b6-11e2-877f-002564c97630")
    protected Dimension getMinimumSizeFor(GraphicalEditPart child) {
        Dimension minimumSize = child.getFigure().getMinimumSize();
        boolean isHorizontalOrientation = isHorizontalLaneOrientation();
        
        for (Object ep : getHost().getChildren()) {
            IFigure childFig = ((GraphicalEditPart) ep).getFigure();
            Dimension childSize = childFig.getMinimumSize();
            if (isHorizontalOrientation) {
                minimumSize.width = Math.max(minimumSize.width, childSize.width);
            } else {
                minimumSize.height = Math.max(minimumSize.height, childSize.height);
            }
        }
        return minimumSize;
    }

    @objid ("6140a91a-55b6-11e2-877f-002564c97630")
    @Override
    public Command getCommand(Request request) {
        if (RequestConstants.REQ_RESIZE_CHILDREN.equals(request.getType())) {
            return getResizeChildrenCommand((ChangeBoundsRequest) request);
        } else {
            return super.getCommand(request);
        }
    }

    /**
     * @param request the resize children request.
     * @return the command resizing the children.
     */
    @objid ("6140a920-55b6-11e2-877f-002564c97630")
    protected Command getResizeChildrenCommand(ChangeBoundsRequest request) {
        // Resizing children: resize children as they asked (as much as
        // possible) and also resize their "neighbour" to keep the whole
        // container the same size. If there is no neighbour (resized child is
        // either the leftmost or the rightmost), in that particular case then
        // resize the container.
        CompoundCommand compound = new CompoundCommand();
        Map<GmNodeModel, Integer> newConstraints = new HashMap<>();
        final boolean VARIANT = true;
        
        boolean horizontalLanes = isHorizontalLaneOrientation();
        int moveDirX = (int) Math.signum(request.getMoveDelta().x);
        int moveDirY = (int) Math.signum(request.getMoveDelta().y);
        if (VARIANT) {
            IFigure containerFig = getHostFigure();
            ChangeBoundsRequest resizeContainerReq = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
            resizeContainerReq.setEditParts(getHost());
        
            Dimension maxChildrenSize = new Dimension();
        
            for (GraphicalEditPart child : (List<GraphicalEditPart>) getHost().getChildren()) {
                IFigure childFigure = child.getFigure();
                if (request.getEditParts().contains(child)) {
                    Dimension newChildSize = getBoundsFor(request, child).getSize();
                    maxChildrenSize.union(newChildSize);
        
                    newConstraints.put((GmNodeModel) child.getModel(), horizontalLanes ? newChildSize.height : newChildSize.width);
        
                    Dimension childSizeDelta = newChildSize.getShrinked(childFigure.getSize());
                    childFigure.translateToAbsolute(childSizeDelta);
        
                    resizeContainerReq.getSizeDelta().expand(childSizeDelta);
                    resizeContainerReq.getMoveDelta().translate(
                            moveDirX * Math.abs(childSizeDelta.width),
                            moveDirY * Math.abs(childSizeDelta.height));
        
                } else {
                    maxChildrenSize.union(childFigure.getMinimumSize());
                }
            }
        
            if (horizontalLanes) {
                int maxWidth = Math.max(containerFig.getPreferredSize().width(), maxChildrenSize.width());
                int widthDelta = maxWidth - containerFig.getSize().width();
                resizeContainerReq.getSizeDelta().setWidth(widthDelta);
                resizeContainerReq.getMoveDelta().setX(moveDirX * widthDelta);
            } else {
                int maxHeight = Math.max(containerFig.getPreferredSize().height(), maxChildrenSize.height());
                int heightDelta = maxHeight - containerFig.getSize().height();
                resizeContainerReq.getSizeDelta().setHeight(heightDelta);
                resizeContainerReq.getMoveDelta().setY(moveDirY * heightDelta);
            }
        
            Command parentCommand = getHost().getCommand(resizeContainerReq);
            compound.add(parentCommand);
        
        } else {
        
            for (GraphicalEditPart resizedChild : (List<GraphicalEditPart>) request.getEditParts()) {
                Dimension newChildSize = getBoundsFor(request, resizedChild).getSize();
        
                newConstraints.put((GmNodeModel) resizedChild.getModel(), horizontalLanes ? newChildSize.height : newChildSize.width);
        
                Dimension childSizeDelta = newChildSize.getShrinked(resizedChild.getFigure().getSize());
                resizedChild.getFigure().translateToAbsolute(childSizeDelta);
        
                ChangeBoundsRequest resizeContainerReq = RequestHelper.deepCopy(request);
                resizeContainerReq.setType(RequestConstants.REQ_RESIZE);
                resizeContainerReq.setEditParts(getHost());
                resizeContainerReq.getSizeDelta().setSize(childSizeDelta);
                resizeContainerReq.getMoveDelta().setLocation(
                        moveDirX * Math.abs(childSizeDelta.width),
                        moveDirY * Math.abs(childSizeDelta.height));
        
                Command parentCommand = getHost().getCommand(resizeContainerReq);
                compound.add(parentCommand);
            }
        }
        
        ResizePartitionsCommand command = new ResizePartitionsCommand((GmBpmnLaneSetContainer) getHost().getModel());
        command.setNewConstraints(newConstraints);
        compound.add(command);
        return compound.unwrap();
    }

    /**
     * Generates a draw2d constraint for the given <code>CreateRequest</code>.
     * <P>
     * The CreateRequest's location is relative the Viewer.
     * @param request the CreateRequest
     * @return a draw2d constraint
     */
    @objid ("6140a926-55b6-11e2-877f-002564c97630")
    protected int getConstraintForCreate(CreateRequest request) {
        IFigure figure = getLayoutContainer();
        
        Dimension size = request.getSize();
        
        if (size == null || size.isEmpty()) {
            size = BpmnLaneSetContainerLayoutEditPolicy.DEFAULT_SIZE; // set a default size.
        }
        
        size = size.getCopy();
        figure.translateToRelative(size);
        figure.translateFromParent(size);
        if (isHorizontalLaneOrientation()) {
            return size.height();
        } else {
            return size.width();
        }
    }

    @objid ("6140a931-55b6-11e2-877f-002564c97630")
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
     * @return whether lanes should be displayed horizontally or vertically.
     */
    @objid ("90134ef3-e400-4fb6-8f5c-20fbbadb797a")
    protected boolean isHorizontalLaneOrientation() {
        return getHostCompositeNode().getDisplayedStyle().getProperty(GmBpmnDiagramStyleKeys.HORIZONTAL_LANES);
    }

    /**
     * Creates the EditPartListener for observing when children are added to the host.
     * <p>
     * Redefined to auto expand on child edit part addition.
     * @return EditPartListener
     */
    @objid ("ed74ae20-6170-4be6-91dd-cf7706b0ab3b")
    @Override
    protected EditPartListener createListener() {
        return new EditPartListener.Stub() {
                            @Override
                            public void childAdded(EditPart child, int index) {
                                onChildAdded(child);
                            }
                        };
    }

    /**
     * Called by an edit part listener when a child edit part is added.
     * <p>
     * Try to expand the container to fit all children.
     * @param child the added edit part
     */
    @objid ("de5b0fc9-e442-4548-baa7-0828abe02209")
    protected void onChildAdded(EditPart child) {
        // Standard behavior inherited from LayoutEditPolicy
        decorateChild(child);
        
        // The child figure has just been added but not yet layouted, force layout now to avoid strange effects.
        getHostFigure().getUpdateManager().performValidation();
        
        ChangeBoundsRequest request = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        request.setEditParts(child);
        
        Command cmd = AutoExpandHelper.getExpandContainerCommand(request, getHost(), getLayoutContainer());
        
        if (cmd != null && cmd.canExecute()) {
            cmd.execute();
        } else {
            DiagramElements.LOG.debug("BpmnLaneSetContainerLayoutEditPolicy.onChildAdded(%s) : unable to expand <%s>. Command = <%s>",
                    child.toString(), getHost().toString(), cmd);
        }
    }

    /**
     * A command that reorders children of a composite node.
     */
    @objid ("6149d117-55b6-11e2-877f-002564c97630")
    private static class ReorderChildrenCommand extends Command {
        @objid ("71d9c589-55c1-11e2-9337-002564c97630")
        private GmCompositeNode container;

        @objid ("71d9c58a-55c1-11e2-9337-002564c97630")
        private GmNodeModel childToMove;

        @objid ("71d9c58b-55c1-11e2-9337-002564c97630")
        private GmNodeModel reference;

        @objid ("614b577d-55b6-11e2-877f-002564c97630")
        @Override
        public void execute() {
            // this.container.removeChild(this.childToMove);
            this.container.moveChild(this.childToMove, this.container.getChildren().indexOf(this.reference));
        }

        /**
         * C'tor.
         * @param container the container in which children will be moved.
         * @param childToMove the child to move.
         * @param reference the reference: moved child will be moved just before this reference. If it is null, child will be moved at the end of the container.
         */
        @objid ("614b5780-55b6-11e2-877f-002564c97630")
        public ReorderChildrenCommand(GmCompositeNode container, GmNodeModel childToMove, GmNodeModel reference) {
            this.container = container;
            this.childToMove = childToMove;
            this.reference = reference;
            setLabel(String.format("Move %s before %s", childToMove, reference));
        }

        @objid ("7615cb86-73fc-4b42-8567-3c00fb6ce72d")
        @Override
        public boolean canExecute() {
            // Don't test container content is compatible here because a previous command may modify it on execution.
            return true;
        }

    }

}
