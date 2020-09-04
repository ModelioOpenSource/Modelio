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

package org.modelio.diagram.editor.bpmn.elements.bpmnlane.hibridcontainer;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
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
import org.eclipse.gef.editpolicies.OrderedLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.DropRequest;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.BpmnLaneEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnlanesetcontainer.BpmnLaneSetContainerEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnlanesetcontainer.CreateBpmnLaneSetContainerCommand;
import org.modelio.diagram.editor.bpmn.elements.bpmnlanesetcontainer.GmBpmnLaneSetContainer;
import org.modelio.diagram.editor.bpmn.elements.diagrams.GmBpmnDiagramStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.policies.BpmnLaneReparentElementCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.AutoExpandHelper;
import org.modelio.diagram.elements.core.policies.ProgrammaticOnlyDragPolicy;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("6134744d-55b6-11e2-877f-002564c97630")
class BpmnLaneSetEditPolicy extends OrderedLayoutEditPolicy {
    @objid ("265a24ae-dee7-4513-8bf2-c7dbddf38efb")
    private Polyline insertionLine;

    @objid ("422543a3-dace-4dd2-a0f2-25b6b4de71dc")
    @Override
    public Command getCommand(Request request) {
        if (REQ_RESIZE_CHILDREN.equals(request.getType())) {
            return getResizeChildrenCommand((ChangeBoundsRequest) request);
        } else {
            return super.getCommand(request);
        }
    }

    @objid ("61347457-55b6-11e2-877f-002564c97630")
    @Override
    public EditPart getTargetEditPart(final Request request) {
        if (RequestConstants.REQ_CREATE.equals(request.getType())) {
        
            CreateRequest createRequest = (CreateRequest) request;
            final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(createRequest);
            if (ctx != null) {
                if (BpmnLaneSet.class.isAssignableFrom(ctx.getMetaclass().getJavaInterface())
                        || BpmnLane.class.isAssignableFrom(ctx.getMetaclass().getJavaInterface())) {
                    if (ctx.getElementToUnmask() != null) {
                        if (!getHostCompositeNode().canUnmask(ctx.getElementToUnmask())) {
                            return null;
                        }
                    }
                    if (isOnFigureBorder(createRequest.getLocation()) && getLanes().isEmpty()) {
                        // mouse on border and no lane yet, transfer request to the parent
                        return null;
                    } else {
                        return getHost();
                    }
                }
            }
        } else if (RequestConstants.REQ_ADD.equals(request.getType()) 
                || RequestConstants.REQ_MOVE.equals(request.getType())) {
            // - REQ_MOVE needs to be tested too because the Tool will know 
            //   it is a reparenting operation only once we return getHost().
            // - On move we ignore the Lane move if the cusror is on the 20% border 
            //   so that the LaneSet edit part moves the lane as a sibling of this one.
            ChangeBoundsRequest req = (ChangeBoundsRequest) request;
            if (isOnFigureBorder(req.getLocation())) {
                // mouse on border, transfer request to the parent
                return null;
            }
            // Handle the request ==> move the lane inside
        }
        return super.getTargetEditPart(request);
    }

    /**
     * Shows an insertion line during lane creation.
     */
    @objid ("683cb29f-c768-4928-9f60-715824256b6b")
    @Override
    public void showTargetFeedback(Request request) {
        // Show nothing if we cannot issue an executable command.
        Command command = getCommand(request);
        if (!RequestConstants.REQ_MOVE.equals(request.getType()) && (command == null || !command.canExecute())) {
            return;
        }
        
        List<BpmnLaneEditPart> ownedLanes = getLanes();
        if (ownedLanes.isEmpty()) {
            if (RequestConstants.REQ_ADD.equals(request.getType())) {
                // Adding lanes to an empty one should take all available space, this is the standard feedback.
                //super.showTargetFeedback(request);
                Polyline fb = getLineFeedback();
                Transposer transposer = new Transposer();
                //transposer.setEnabled(isHorizontalLaneOrientation());
                Rectangle r = transposer.t(getAbsoluteBounds((GraphicalEditPart) getHost()));
                fb.translateToRelative(r);
                fb.setPoint(r.getTopLeft(), 0);
                fb.setPoint(r.getTopRight(), 1);
                fb.setPoint(r.getBottomRight(), 2);
                fb.setPoint(r.getBottomLeft(), 3);
                if (fb.getPoints().size() == 4) {
                    fb.addPoint(r.getTopLeft());
                } else {
                    fb.setPoint(r.getTopLeft(), 4);
                }
        
            } else {
                // if this is a request for the creation of the first INNER
                // lanes, show a line in the middle of the container.
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
            }
            return;
        }
        
        // Otherwise, show a line where the lane would be inserted.
        Polyline fb = getLineFeedback();
        Transposer transposer = new Transposer();
        transposer.setEnabled(isHorizontalLaneOrientation());
        
        boolean before = true;
        int epIndex = getFeedbackIndexFor(request);
        Rectangle r = null;
        if (epIndex == -1) {
            before = false;
            epIndex = ownedLanes.size() - 1;
            EditPart editPart = ownedLanes.get(epIndex);
            r = transposer.t(getAbsoluteBounds((GraphicalEditPart) editPart));
        } else {
            EditPart editPart = ownedLanes.get(epIndex);
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
                editPart = ownedLanes.get(epIndex);
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
                Rectangle boxPrev = transposer.t(getAbsoluteBounds(ownedLanes.get(epIndex - 1)));
                int prevRight = boxPrev.right();
                if (prevRight < r.x) {
                    // Not a line break
                    x = prevRight + (r.x - prevRight) / 2;
                } else if (prevRight == r.x) {
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

    @objid ("6135faba-55b6-11e2-877f-002564c97630")
    @Override
    protected Command createAddCommand(EditPart child, EditPart after) {
        if (child instanceof ConnectionEditPart) {
            // The request may include all connections inside the model lane, ignore them
            return null;
        }
        // This command is expected to create a sub BpmnLaneSet and move the lane as a sub-lane
        GmCompositeNode hostNode = getHostCompositeNode();
        return new BpmnLaneReparentElementCommand(hostNode.getRelatedElement(),
                                                hostNode,
                                                (GmNodeModel) child.getModel(),
                                                null);
    }

    @objid ("4dbda419-10b9-4ed8-8000-352c7fdf80a9")
    @Override
    protected EditPolicy createChildEditPolicy(EditPart child) {
        return new ProgrammaticOnlyDragPolicy();
    }

    /**
     * Creates the EditPartListener for observing when children are added to the host.
     * <p>
     * Redefined to auto expand on child edit part addition.
     * 
     * @return EditPartListener
     */
    @objid ("1245a3ca-4697-43f8-8b60-ea5c61c15a5d")
    @Override
    protected EditPartListener createListener() {
        return new EditPartListener.Stub() {
                                            @Override
                                            public void childAdded(EditPart child, int index) {
                                                onChildAdded(child);
                                            }
                                        };
    }

    @objid ("17b39ce8-66c4-4249-8a70-028c24017d81")
    @Override
    protected Command createMoveChildCommand(EditPart child, EditPart after) {
        // children don't move
        return null;
    }

    /**
     * Disable child decoration, the auto expand policy does it.
     */
    @objid ("c850d522-056c-4050-829f-ef74573064f6")
    @Override
    protected void decorateChild(EditPart child) {
        if ( isLaneSet(child)) {
            super.decorateChild(child);
        }
    }

    @objid ("7625d1eb-6521-4328-b29e-3f5bc6ff4c58")
    @Override
    protected void eraseLayoutTargetFeedback(Request request) {
        if (this.insertionLine != null) {
            removeFeedback(this.insertionLine);
            this.insertionLine = null;
        }
    }

    @objid ("61347450-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getCreateCommand(final CreateRequest request) {
        final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(request);
        
        if (ctx != null && ctx.getMetaclass().getJavaInterface() == BpmnLane.class) {
            return new CreateBpmnLaneSetContainerCommand(getHostElement(), 
                    getHostCompositeNode(), 
                    ctx, null, 
                    getInsertionReference(request));
        
        }
        return null;
    }

    /**
     * @param request the Request
     * @return the index for the insertion reference
     */
    @objid ("f1f50fe2-f12a-4ffd-822d-3461f10023a1")
    protected int getFeedbackIndexFor(Request request) {
        List<BpmnLaneEditPart> children = getLanes();
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
            EditPart child = children.get(i);
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

    @objid ("251ba9dd-de8d-449c-8cdd-a6355569d6c8")
    protected MObject getHostElement() {
        return getHostCompositeNode().getRelatedElement();
    }

    @objid ("a9e5ebd6-9222-414c-bd1b-7fcc0ddbd982")
    @Override
    protected EditPart getInsertionReference(Request request) {
        List<?> children = getLanes();
        
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
    @objid ("9dd20a54-749c-4710-9bf2-2af9b33bbcd1")
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

    @objid ("58b84d38-ee46-4f67-b662-73bcd00aa98f")
    protected Command getResizeChildrenCommand(final ChangeBoundsRequest request) {
        for (Object ep : request.getEditParts()) {
            if (!isLaneSet((EditPart) ep)) {
                return null;
            }
        }
        
        // Apply the resize to the container
        ChangeBoundsRequest req = RequestHelper.shallowCopy(request);
        req.setType(REQ_RESIZE);
        req.setEditParts(getHost());
        return getHost().getCommand(req);
    }

    /**
     * @return whether lanes should be displayed horizontally or vertically.
     */
    @objid ("ddb769f2-0c52-4295-a3cf-79e2182cecee")
    protected boolean isHorizontalLaneOrientation() {
        return getHostCompositeNode().getDisplayedStyle().getProperty(GmBpmnDiagramStyleKeys.HORIZONTAL_LANES);
    }

    @objid ("c57a7763-373f-4429-903e-55da45b8c6e9")
    protected boolean isLaneSet(EditPart child) {
        return child.getModel() instanceof GmBpmnLaneSetContainer;
    }

    @objid ("bb1bafa9-9858-4d0d-8516-b48277e948b5")
    @Override
    protected void undecorateChild(EditPart child) {
        if ( isLaneSet(child)) {
            super.undecorateChild(child);
        }
    }

    @objid ("5dc6521c-5f95-4fad-ba3d-a46ce14b1a91")
    private static Rectangle getAbsoluteBounds(GraphicalEditPart ep) {
        Rectangle bounds = ep.getFigure().getBounds().getCopy();
        ep.getFigure().translateToAbsolute(bounds);
        return bounds;
    }

    @objid ("d1c090db-a5c3-40f7-8d50-f369849421c4")
    private GmCompositeNode getHostCompositeNode() {
        return (GmCompositeNode) getHost().getModel();
    }

    @objid ("be53a26c-400e-4514-ace3-8362a0edc283")
    private List<BpmnLaneEditPart> getLanes() {
        List<BpmnLaneEditPart> ownedLanes = new ArrayList<>();
        // Better safe than sorry: keep only the lanes!
        for (Object child : getHost().getChildren()) {
            if (child instanceof BpmnLaneSetContainerEditPart) {
                BpmnLaneSetContainerEditPart laneSetEditPart = (BpmnLaneSetContainerEditPart) child;
                for (Object child2 : laneSetEditPart.getChildren()) {
                    if (child2 instanceof BpmnLaneEditPart) {
                        ownedLanes.add((BpmnLaneEditPart) child2);
                    }
                }
            }
        }
        return ownedLanes;
    }

    @objid ("4ebde200-3b8d-412b-b2ac-9fef8fa1b3c9")
    private Point getLocationFromRequest(Request request) {
        return ((DropRequest) request).getLocation();
    }

    /**
     * Called by an edit part listener when a child edit part is added.
     * <p>
     * Try to expand the container to fit all children.
     * 
     * @param child the added edit part
     */
    @objid ("7c5201ac-e871-4ca0-b0bd-bfc1069f3d51")
    protected void onChildAdded(EditPart child) {
        // handle only Laneset containers
        if (! isLaneSet(child)) {
            return;
        }
            
        // Standard behavior inherited from LayoutEditPolicy
        decorateChild(child);
        
        // The child figure has just been added but not yet layouted, force layout now to avoid strange effects.
        getHostFigure().getUpdateManager().performValidation();
        
        ChangeBoundsRequest request = new ChangeBoundsRequest(REQ_RESIZE);
        request.setEditParts(child);
        
        Command cmd = AutoExpandHelper.getExpandContainerCommand(request, getHost(), getLayoutContainer());
        
        if (cmd != null && cmd.canExecute()) {
            cmd.execute();
        } else {
            DiagramElements.LOG.debug("BpmnLaneSetEditPolicy.onChildAdded(%s) : unable to expand <%s>. Command = <%s>",
                    child.toString(), getHost().toString(), cmd);
        }
    }

    /**
     * Tells whether the given point is on the 20% figure border.
     * 
     * @param absLoc a location in absolute coordinates
     * @return true if the point is near the figure border.
     */
    @objid ("3ab060b3-27c1-4a3b-baaa-37dcdb91c899")
    private boolean isOnFigureBorder(Point absLoc) {
        Point loc = new PrecisionPoint(absLoc);
        IFigure hostFigure = getHostFigure();
        hostFigure.translateToRelative(loc);
        loc.translate(-hostFigure.getBounds().x(), -hostFigure.getBounds().y());
        double fraction;
        if (isHorizontalLaneOrientation()) {
            fraction = loc.preciseY() / hostFigure.getBounds().preciseHeight();
        } else {
            fraction = loc.preciseX() / hostFigure.getBounds().preciseWidth();
        }
        return (fraction <= 0.2 || fraction >= 0.8);
    }

}
