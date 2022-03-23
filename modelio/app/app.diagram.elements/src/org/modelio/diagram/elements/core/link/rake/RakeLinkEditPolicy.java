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
package org.modelio.diagram.elements.core.link.rake;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BendpointLocator;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.AccessibleHandleProvider;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy;
import org.eclipse.gef.handles.BendpointHandle;
import org.eclipse.gef.handles.BendpointMoveHandle;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;
import org.modelio.diagram.elements.core.figures.geometry.LineSeg;
import org.modelio.diagram.elements.core.figures.geometry.Orientation;
import org.modelio.diagram.elements.core.figures.routers.RakeConstraint;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.IAnchorModelProvider;
import org.modelio.diagram.elements.core.policies.SelectionHandlesBuilder;
import org.modelio.vcore.model.api.MTools;

/**
 * This EditPolicy defines the behavior of Bendpoints on a raked Connection.
 */
@objid ("805ebbab-1dec-11e2-8cad-001ec947c8cc")
public class RakeLinkEditPolicy extends SelectionHandlesEditPolicy implements PropertyChangeListener {
    @objid ("805ebbaf-1dec-11e2-8cad-001ec947c8cc")
    private FeedbackState feedbackState = null;

    /**
     * Constructor for EditPolicy
     */
    @objid ("805ebbb0-1dec-11e2-8cad-001ec947c8cc")
    public  RakeLinkEditPolicy() {
        super();
    }

    /**
     * Adds a PropertyChangeListener to the Connection so we can react to point changes in the connection.
     * @see SelectionHandlesEditPolicy#activate()
     */
    @objid ("805ebbb3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void activate() {
        super.activate();
        
        getConnection().addPropertyChangeListener(Connection.PROPERTY_POINTS, this);
        
    }

    /**
     * Removes this from the Connection's list of PropertyChangeListeners.
     * @see SelectionHandlesEditPolicy#deactivate()
     */
    @objid ("805ebbb7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void deactivate() {
        getConnection().removePropertyChangeListener(Connection.PROPERTY_POINTS, this);
        
        super.deactivate();
        
    }

    /**
     * Erases bendpoint feedback. Since the original figure is used for feedback, we just restore the original
     * constraint that was saved before feedback started to show.
     * @param request the Request
     */
    @objid ("80611dcf-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void eraseSourceFeedback(Request request) {
        Object reqType = request.getType();
        if (reqType instanceof String) {
            switch ((String)reqType) {
            case REQ_MOVE:
                restoreOriginalConstraint();
                getFeedbackState().originalConstraint = null;
                cleanFeedbackState();
                break;
            case REQ_MOVE_BENDPOINT:
            case REQ_CREATE_BENDPOINT:
                restoreOriginalConstraint();
                getFeedbackState().originalConstraint = null;
                if (((BendpointRequest) request).getSource() != getHost()) {
                    cleanFeedbackState();
                }
                break;
            default:
                cleanFeedbackState();
            }
        } else {
            cleanFeedbackState();
        }
        
    }

    /**
     * Override for AccessibleHandleProvider when deactivated https://bugs.eclipse.org/bugs/show_bug.cgi?id=69316
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    @objid ("80611dd6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object getAdapter(Class key) {
        if (key == AccessibleHandleProvider.class) {
            // handles == null when deactivated
            if (this.handles == null) {
                return null;
            }
        }
        return super.getAdapter(key);
    }

    /**
     * Returns the appropriate Command for the request type given. Handles creating, moving and deleting bendpoints. The
     * actual creation of the command is taken care of by subclasses implementing the appropriate methods.
     */
    @objid ("80611ddd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Command getCommand(Request request) {
        Object reqType = request.getType();
        if (REQ_DELETE.equals(reqType))
            return null;
        
        
        if (reqType instanceof String) {
            switch ((String)reqType) {
            case REQ_MOVE:
                return getMoveCommand((ChangeBoundsRequest) request);
            case REQ_MOVE_BENDPOINT:
            case REQ_CREATE_BENDPOINT:
                return getFeedbackState().currentCommand;
            }
        }
        return null;
    }

    @objid ("f3ea9472-d5f7-4c6f-b51b-7b47ce77d2f5")
    protected Command getMoveCommand(final ChangeBoundsRequest request) {
        // When handling a request without displaying feedback first, we have to simulate it first
        // in order to compute the move coordinates...
        // This happens when moving the connection with the keyboard.
        
        boolean simulateFeedback = false;
        if (this.feedbackState == null) {
            simulateFeedback = true;
            showSourceFeedback(request);
            showTargetFeedback(request);
        }
        
        Command command = getFeedbackState().currentCommand;
        
        if (simulateFeedback) {
            eraseSourceFeedback(request);
            eraseTargetFeedback(request);
        }
        return command;
    }

    /**
     * Adds selection handles to the Connection, if it is selected, when the points property changes. Since we only
     * listen for changes in the points property, this method is only called when the points of the Connection have
     * changed.
     */
    @objid ("80611de8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (getHost().getSelected() != EditPart.SELECTED_NONE) {
            addSelectionHandles();
        }
        
    }

    /**
     * Shows feedback, when appropriate. Calls a different method depending on the request type.
     */
    @objid ("80611ded-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void showSourceFeedback(Request request) {
        Object reqType = request.getType();
        if (RequestConstants.REQ_MOVE.equals(reqType))  {
            showMoveRequestFeedback(request);
        } else if (REQ_CREATE_BENDPOINT.equals(reqType)) {
            showMoveLineSegFeedback((BendpointRequest) request);
            getFeedbackState().currentCommand = getBendpointsChangedCommand((BendpointRequest) request);
        } else if (REQ_MOVE_BENDPOINT.equals(reqType)) {
            showMoveOrthogonalBendpointFeedback((BendpointRequest) request);
            getFeedbackState().currentCommand = getBendpointsChangedCommand((BendpointRequest) request);
        }
        
        super.showSourceFeedback(request);
        
    }

    @objid ("40237797-bcc6-4888-b52e-660077fb2ac7")
    protected void showMoveRequestFeedback(Request request) {
        ChangeBoundsRequest cbReq = (ChangeBoundsRequest) request;
        
        final FeedbackState fbState = getFeedbackState();
        if (fbState.originalConstraint == null) {
            saveOriginalConstraint();
        }
        
        final RakeConstraint constraint = getConnectionRoutingConstraint();
        XYAnchor curAnchor = constraint.getSourceRakeAnchor();
        int index = 1;
        if (curAnchor == null) {
            index = 2;
            curAnchor = constraint.getTargetRakeAnchor();
        }
        
        final Connection connection = getConnection();
        
        //System.err.printf("%s.showMoveRequestFeedback(move=%s): host=%s%n", getClass().getSimpleName(), cbReq.getMoveDelta(), getHost());
        
        
        // move conn source
        Point p = fbState.originalSourceAbsLoc.getCopy();
        //System.err.printf("  - orig source anchor=%s%n", p);
        
        p.translate(cbReq.getMoveDelta());
        connection.setSourceAnchor(new XYAnchor( p));
        //System.err.printf("   - moved source anchor=%s%n", p);
        
        // move conn target
        p = fbState.originalTargetAbsLoc.getCopy();
        //System.err.printf("  - orig target anchor=%s%n", p);
        
        p.translate(cbReq.getMoveDelta());
        connection.setTargetAnchor(new XYAnchor(p));
        //System.err.printf("   - moved target anchor=%s%n", p);
        
        // avoid modifying the anchor twice if many links of the same rake are moved
        if (request.getExtendedData().putIfAbsent(curAnchor, Boolean.TRUE)==null) {
        
            // work on the original constraint to edit the current one
            XYAnchor origAnchor;
            if (index==1) {
                origAnchor = fbState.originalConstraint.getSourceRakeAnchor();
            } else {
                origAnchor = fbState.originalConstraint.getTargetRakeAnchor();
            }
        
            p = origAnchor.getReferencePoint().getCopy();
            //System.err.printf("  - rake orig anchor=%s%n", p);
            connection.translateToAbsolute(p);
            p.translate(cbReq.getMoveDelta());
            connection.translateToRelative(p);
            //System.err.printf("  - rake moved anchor=%s%n", p);
            curAnchor.setLocation(p);
        
            fbState.currentCommand = new MoveRakeCommand(getHost(), fbState.feedbackConstraint);
        }
        //System.err.println();
        
    }

    @objid ("e72337de-6f73-4ece-a864-ced389f2252c")
    @Override
    public boolean understandsRequest(final Request req) {
        if (RequestConstants.REQ_MOVE.equals(req.getType())) {
            return true;
        } else {
            return super.understandsRequest(req);
        }
        
    }

    /**
     * Adds selection handles to the connection for the bendpoints. In this class, this method just decides if it is
     * appropriate to add the handles, and then calls on the superclass to do the dirty work.s
     */
    @objid ("80611df4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void addSelectionHandles() {
        if (this.handles == null) {
            super.addSelectionHandles();
        } else {
            int count = this.handles.size();
            int points = getConnection().getPoints().size();
            if (count != points * 2 - 3) {
                super.addSelectionHandles();
            }
        }
        
    }

    /**
     * Forget the feedback data.
     */
    @objid ("80611df8-1dec-11e2-8cad-001ec947c8cc")
    protected void cleanFeedbackState() {
        this.feedbackState = null;
    }

    /**
     * Creates selection handles for the bendpoints.
     * <p>
     * Explicit (user-defined) bendpoints will have
     * {@link BendpointMoveHandle}s on them with a single {@link LineSegMoveInvisibleHandle} between 2 consecutive
     * explicit bendpoints.
     */
    @objid ("80611dfb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected List<?> createSelectionHandles() {
        return SelectionHandlesBuilder.disableHandlesIfReadOnly(getHost(), createManualHandles());
    }

    /**
     * This handle is necessary for the accessibility feature to allow keyboard navigation to the add bendpoint feature.
     */
    @objid ("80611e03-1dec-11e2-8cad-001ec947c8cc")
    private void addInvisibleCreationHandle(List<BendpointHandle> list, ConnectionEditPart connEP, int i) {
        list.add(new LineSegMoveInvisibleHandle(connEP, i));
    }

    @objid ("80611e0f-1dec-11e2-8cad-001ec947c8cc")
    private void adjustLineToRectangle(LineSeg line, IFigure figure) {
        PrecisionRectangle endRect = new PrecisionRectangle(figure.getBounds());
        
        if (line.isHorizontal()) {
            endRect.shrink(0, 5);
        } else {
            endRect.shrink(5, 0);
        }
        
        /*
         * Rectangle needs to be expanded by the "odd" number below because the number after translations could be
         * N.999999999...
         */
        figure.translateToAbsolute(endRect);
        getConnection().translateToRelative(endRect);
        
        final Point p1 = line.getOrigin();
        final Point p2 = line.getTerminus();
        
        if (line.isHorizontal()) {
            if (p2.y < endRect.y) {
                p1.y = endRect.y;
                p2.y = endRect.y;
        
                line.setOrigin(p1);
                line.setTerminus(p2);
            } else if (p2.y > endRect.bottom()) {
                p1.y = endRect.bottom();
                p2.y = endRect.bottom();
        
                line.setOrigin(p1);
                line.setTerminus(p2);
            }
        }
        
        //Note : the line may be horizontal and vertical if end points are the same.
        if (line.isVertical()) {
            if (p2.x < endRect.x) {
                p1.x = endRect.x;
                p2.x = endRect.x;
        
                line.setOrigin(p1);
                line.setTerminus(p2);
            } else if (p2.x > endRect.right()) {
                p1.x = endRect.right();
                p2.x = endRect.right();
                line.setOrigin(p1);
                line.setTerminus(p2);
            }
        }
        
    }

    /**
     * handle feedback where the line is dragged outside of the source or target shapes bounding box.
     * @param newLine LineSeg representing the line currently being manipulated.
     * @param index the index
     * @param constraint the rake constraint to the gesture.
     * @param moveLine original segment that is being manipulated
     * @return true if the line been adjusted, false in the other case.
     */
    @objid ("80611e15-1dec-11e2-8cad-001ec947c8cc")
    private boolean adjustOutsideBoundsLineFeedback(LineSeg newLine, int index, RakeConstraint constraint, LineSeg moveLine) {
        // merely enforce the fact that we can't adjust the line outside the bounds of the source and target.
        if (index == 0) {
            adjustLineToRectangle(newLine, getConnection().getSourceAnchor().getOwner());
        } else if (index == 2) {
            adjustLineToRectangle(newLine, getConnection().getTargetAnchor().getOwner());
        }
        return false;
    }

    /**
     * @return list of manual handles
     */
    @objid ("80611e1e-1dec-11e2-8cad-001ec947c8cc")
    private List<BendpointHandle> createManualHandles() {
        List<BendpointHandle> list = new ArrayList<>();
        ConnectionEditPart connEP = (ConnectionEditPart) getHost();
        PointList points = getConnection().getPoints();
        //ArrayList<Point> debugPoints = new ArrayList<>(points.size() - 2);
        
        for (int i = 1; i < points.size() - 1; i++) {
            addInvisibleCreationHandle(list, connEP, i - 1);
            list.add(new BendpointMoveHandle(connEP, i, new BendpointLocator(getConnection(), i)));
            //debugPoints.add(points.getPoint(i));
        }
        addInvisibleCreationHandle(list, connEP, points.size() - 2);
        return list;
    }

    /**
     * This method will return a SetBendpointsCommand with the points retrieved from the user feedback in the figure.
     * @param request BendpointRequest from the user gesture for moving / creating a bendpoint
     * @return Command SetBendpointsCommand that contains the point changes for the connection.
     */
    @objid ("80638029-1dec-11e2-8cad-001ec947c8cc")
    private Command getBendpointsChangedCommand(BendpointRequest request) {
        final Connection connection = getConnection();
        final RakeConstraint c = getFeedbackState().feedbackConstraint;
        
        final ConnectionAnchor srcAnchor = connection.getSourceAnchor();
        final ConnectionAnchor targetAnchor = connection.getTargetAnchor();
        
        Command cleanFpCmd = new Command() {
            @Override
            public void execute() {
                cleanFeedbackState();
            }
        };
        return new SetRakeConstraintCommand(
                (ConnectionEditPart) getHost(),
                c.getSourceRakeLocation(),
                c.getTargetRakeLocation(),
                c.getOrientation(),
                srcAnchor,
                targetAnchor)
                .chain(cleanFpCmd);
        
    }

    /**
     * Returns the Connection associated with this EditPolicy.
     */
    @objid ("80638033-1dec-11e2-8cad-001ec947c8cc")
    private Connection getConnection() {
        return (Connection) ((ConnectionEditPart) getHost()).getFigure();
    }

    /**
     * convenience method to get the connection routing constraint casted to List&lt;Bendpoint>
     * @return the connection routing constraint.
     */
    @objid ("8063803a-1dec-11e2-8cad-001ec947c8cc")
    private RakeConstraint getConnectionRoutingConstraint() {
        return (RakeConstraint) getConnection().getRoutingConstraint();
    }

    /**
     * Get the line point at the given index. index 0 is the source anchor, the last point is the target anchor and
     * other indexes correspond to the bend points in the constraint.
     * @param index if 0, return the source anchor location. If index is the size of the constraint, return the target
     * anchor.
     * @return return the line point location, <i>relative</i> to the connection
     */
    @objid ("8063803f-1dec-11e2-8cad-001ec947c8cc")
    private Point getConstrainedPoint(Connection connection, int index) {
        return connection.getPoints().getPoint(index);
    }

    /**
     * Get the feedback state.
     * <p>
     * Creates one if it does not exist.
     * @return the feedback state.
     */
    @objid ("8063804a-1dec-11e2-8cad-001ec947c8cc")
    private FeedbackState getFeedbackState() {
        if (this.feedbackState == null) {
            this.feedbackState = new FeedbackState();
        }
        return this.feedbackState;
    }

    @objid ("8063804f-1dec-11e2-8cad-001ec947c8cc")
    private final LineSeg getLineSeg(Connection connection, int nIndex) {
        Point pt1 = new Point(getConstrainedPoint(connection, nIndex - 1));
        Point pt2 = new Point(getConstrainedPoint(connection, nIndex));
        return new LineSeg(pt1, pt2);
    }

    @objid ("80638057-1dec-11e2-8cad-001ec947c8cc")
    private ConnectionAnchor getNewSourceAnchor(final Point absolutePoint) {
        ConnectionEditPart connEp = (ConnectionEditPart) getHost();
        NodeEditPart sourceEp = (NodeEditPart) connEp.getSource();
        ReconnectRequest r = new ReconnectRequest(REQ_RECONNECT_SOURCE);
        r.setConnectionEditPart(connEp);
        r.setTargetEditPart(sourceEp);
        r.setLocation(absolutePoint);
        return sourceEp.getSourceConnectionAnchor(r);
    }

    @objid ("80638061-1dec-11e2-8cad-001ec947c8cc")
    private ConnectionAnchor getNewTargetAnchor(final Point absolutePoint) {
        ConnectionEditPart connEp = (ConnectionEditPart) getHost();
        NodeEditPart sourceEp = (NodeEditPart) connEp.getTarget();
        
        ReconnectRequest r = new ReconnectRequest(REQ_RECONNECT_TARGET);
        r.setConnectionEditPart(connEp);
        r.setTargetEditPart(sourceEp);
        r.setLocation(absolutePoint);
        return sourceEp.getTargetConnectionAnchor(r);
    }

    /**
     * Restores the original constraint that was saved before feedback began to show.
     */
    @objid ("8063806b-1dec-11e2-8cad-001ec947c8cc")
    private void restoreOriginalConstraint() {
        final FeedbackState fbState = getFeedbackState();
        final RakeConstraint originalConstraint = fbState.originalConstraint;
        final RakeConstraint current = getConnectionRoutingConstraint();
        if (originalConstraint != null) {
            if (current.getSourceRakeAnchor() != null) {
                current.getSourceRakeAnchor().setLocation(originalConstraint.getSourceRakeAnchor()
                                                                            .getReferencePoint());
            }
        
            if (current.getTargetRakeAnchor() != null) {
                current.getTargetRakeAnchor().setLocation(originalConstraint.getTargetRakeAnchor()
                                                                            .getReferencePoint());
            }
            current.setOrientation(originalConstraint.getOrientation());
        }
        
        Connection connection = getConnection();
        if (current.getSourceRakeAnchor() != null) {
            current.setSharedSourceAnchor(fbState.originalSourceAnchor);
        } else {
            connection.setSourceAnchor(fbState.originalSourceAnchor);
        }
        
        if (current.getTargetRakeAnchor() != null) {
            current.setSharedTargetAnchor(fbState.originalTargetAnchor);
        } else {
            connection.setTargetAnchor(fbState.originalTargetAnchor);
        }
        
        
        // Refresh visual, source and target anchor from model.
        getHost().refresh();
        
    }

    /**
     * Since the original figure is used for feedback, this method saves the original constraint, so that is can be
     * restored when the feedback is erased.
     */
    @objid ("8063806e-1dec-11e2-8cad-001ec947c8cc")
    private void saveOriginalConstraint() {
        final FeedbackState fbState = getFeedbackState();
        final RakeConstraint currentConstraint = getConnectionRoutingConstraint();
        
        fbState.originalConstraint = new RakeConstraint();
        fbState.feedbackConstraint = currentConstraint;
        
        if (currentConstraint != null) {
            XYAnchor anchor = currentConstraint.getSourceRakeAnchor();
            if (anchor != null) {
                fbState.originalConstraint.setSourceRakeAnchor(new XYAnchor(anchor.getReferencePoint()));
            }
        
            anchor = currentConstraint.getTargetRakeAnchor();
            if (anchor != null) {
                fbState.originalConstraint.setTargetRakeAnchor(new XYAnchor(anchor.getReferencePoint()));
            }
        
            if (currentConstraint.getOrientation()==null) {
                updateRakeOrientation(currentConstraint);
            }
            fbState.originalConstraint.setOrientation(currentConstraint.getOrientation());
        }
        
        Connection connection = getConnection();
        fbState.originalSourceAnchor = connection.getSourceAnchor();
        fbState.originalTargetAnchor = connection.getTargetAnchor();
        
        fbState.originalSourceAbsLoc = connection.getPoints().getFirstPoint();
        fbState.originalTargetAbsLoc = connection.getPoints().getLastPoint();
        connection.translateToAbsolute(fbState.originalSourceAbsLoc);
        connection.translateToAbsolute(fbState.originalTargetAbsLoc);
        
    }

    /**
     * Set the position of the point at the given index for a rake that joins on the target side.
     * @param connection The connection to modify
     * @param c The rake constraint for convenience, avoid casts.
     * @param index the index of the point to modify. 0 is the source anchor and 3 is the target anchor.
     * @param relativePoint a point in relative coordinates.
     */
    @objid ("80638071-1dec-11e2-8cad-001ec947c8cc")
    private void setContrainedPoint(Connection connection, RakeConstraint c, int index, Point relativePoint) {
        Point absolutePoint = new Point(relativePoint);
        connection.translateToAbsolute(absolutePoint);
        
        switch (index) {
            case 0:
                // 0 is the source anchor
                if (c.getSourceRakeAnchor() != null) {
                    // change the source anchor for all connections on the rake
                    c.setSharedSourceAnchor(getNewSourceAnchor(absolutePoint));
                } else {
                    // non shared non mutable anchor, change it
                    connection.setSourceAnchor(getNewSourceAnchor(absolutePoint));
                }
                break;
            case 1:
                // 1 is the join anchor on the source side or a computed point, do nothing for computed point
        
                if (c.getSourceRakeAnchor() != null) {
                    c.getSourceRakeAnchor().setLocation(relativePoint);
                }
                break;
            case 2:
                // 2 is the join anchor on the target side or a computed point, do nothing for computed point
        
                if (c.getTargetRakeAnchor() != null) {
                    c.getTargetRakeAnchor().setLocation(relativePoint);
                }
                break;
            case 3:
                // 3 is the target anchor
                if (c.getTargetRakeAnchor() != null) {
                    // change the anchor for all connections on the rake
                    c.setSharedTargetAnchor(getNewTargetAnchor(absolutePoint));
                } else {
                    // non shared non mutable anchor, change it
                    connection.setTargetAnchor(getNewTargetAnchor(absolutePoint));
                }
                break;
            default:
                throw new IllegalArgumentException("index out of bounds");
        }
        
    }

    /**
     * Modify the coordinates of one segment of the constraint.
     * @param rakeConstraint The constraint
     * @param nIndex the index of the first point of the segment
     * @param newSeg the new segment
     */
    @objid ("8063807c-1dec-11e2-8cad-001ec947c8cc")
    private void setLineSeg(Connection c, RakeConstraint rakeConstraint, int nIndex, LineSeg newSeg) {
        setContrainedPoint(c, rakeConstraint, nIndex - 1, newSeg.getOrigin());
        setContrainedPoint(c, rakeConstraint, nIndex, newSeg.getTerminus());
        
    }

    /**
     * Shows feedback when a line segment is being moved. The original figure is used for feedback and the original
     * constraint is saved, so that it can be restored when feedback is erased.
     */
    @objid ("8065e28b-1dec-11e2-8cad-001ec947c8cc")
    private void showMoveLineSegFeedback(BendpointRequest request) {
        if (getFeedbackState().originalConstraint == null) {
            saveOriginalConstraint();
        }
        
        final Point ptLoc = new Point(request.getLocation());
        final Connection connection = getConnection();
        connection.translateToRelative(ptLoc);
        
        // adjust request index to account for source bendpoint if needed
        // index begins at 1.
        int index = request.getIndex();
        final RakeConstraint constraint = getConnectionRoutingConstraint();
        
        LineSeg moveLine = getLineSeg(connection, index + 1);
        LineSeg newLine = moveLine.getParallelLineSegThroughPoint(ptLoc);
        
        adjustOutsideBoundsLineFeedback(newLine, index, constraint, moveLine);
        
        setLineSeg(connection, constraint, index + 1, newLine);
        updateRakeOrientation(constraint);
        
        connection.setRoutingConstraint(constraint);
        
    }

    @objid ("4a5c36af-5f82-47c4-83cc-e2619fc0f36d")
    private void updateRakeOrientation(RakeConstraint c) {
        final Connection connection = getConnection();
        Point rakePos;
        ConnectionAnchor connAnchor;
        if (c.getSourceRakeAnchor() != null) {
            connAnchor = connection.getSourceAnchor();
            rakePos = connection.getPoints().getPoint(1);
        } else {
            connAnchor = connection.getTargetAnchor();
            rakePos = connection.getPoints().getPoint(2);
        }
        
        IFigure node = connAnchor.getOwner();
        if (node == null)
            return;
        
        Rectangle nodeBounds =  node.getBounds().getCopy();
        if (nodeBounds.width < 2 || nodeBounds.height < 2)
            return;
        
        node.translateToAbsolute(nodeBounds);
        connection.translateToAbsolute(rakePos);
        Direction rakeDir = GeomUtils.getDirection(connAnchor.getLocation(rakePos), nodeBounds);
        c.setOrientation(GeomUtils.getOrientation(rakeDir));
        
    }

    /**
     * Draws feedback for moving a bend point of a rectilinear connection
     * @param request Bendpoint request
     */
    @objid ("8065e291-1dec-11e2-8cad-001ec947c8cc")
    private void showMoveOrthogonalBendpointFeedback(BendpointRequest request) {
        if (getFeedbackState().originalConstraint == null) {
            saveOriginalConstraint();
        }
        
        final Point ptLoc = new Point(request.getLocation());
        final Connection connection = getConnection();
        connection.translateToRelative(ptLoc);
        
        int index = request.getIndex();
        
        final Point previous = getConstrainedPoint(connection, index - 1);
        final Point moving = getConstrainedPoint(connection, index);
        final Point next = getConstrainedPoint(connection, index + 1);
        
        final LineSeg originalFirst = new LineSeg(previous.getCopy(), moving.getCopy());
        final LineSeg originalSecond = new LineSeg(moving.getCopy(), next.getCopy());
        
        if (originalFirst.isHorizontal()) {
            // Compute adjacent points move
            final Dimension diff = ptLoc.getDifference(moving);
            previous.y += diff.height;
            next.x += diff.width;
        } else {
            // Compute adjacent points move
            final Dimension diff = ptLoc.getDifference(moving);
            previous.x += diff.width;
            next.y += diff.height;
        }
        
        final LineSeg movedFirst = new LineSeg(previous, ptLoc.getCopy());
        final LineSeg movedSecond = new LineSeg(ptLoc.getCopy(), next);
        
        final RakeConstraint constraint = getConnectionRoutingConstraint();
        
        adjustOutsideBoundsLineFeedback(movedFirst, index - 1, constraint, originalFirst);
        setContrainedPoint(connection, constraint, index - 1, movedFirst.getOrigin());
        setContrainedPoint(connection, constraint, index, movedFirst.getTerminus());
        
        adjustOutsideBoundsLineFeedback(movedSecond, index, constraint, originalSecond);
        setContrainedPoint(connection, constraint, index + 1, movedSecond.getTerminus());
        
        updateRakeOrientation(constraint);
        
        connection.setRoutingConstraint(constraint);
        
    }

    /**
     * Command that set the rake location.
     * 
     * @author cmarin
     */
    @objid ("8065e297-1dec-11e2-8cad-001ec947c8cc")
    private static class SetRakeConstraintCommand extends Command {
        @objid ("62d6d4b1-26af-4bf9-8608-e05ee102525e")
        private Orientation rakeOrientation;

        @objid ("8065e29c-1dec-11e2-8cad-001ec947c8cc")
        private GmLink gmLink;

        @objid ("8065e29d-1dec-11e2-8cad-001ec947c8cc")
        private Object sourceAnchorModel;

        @objid ("8065e29e-1dec-11e2-8cad-001ec947c8cc")
        private Object targetAnchorModel;

        @objid ("20e8ea0e-0014-4c2e-9615-5696c61fdac2")
        private Point sourceRakePos;

        @objid ("98c82692-f33e-499b-a77e-0ec28246e4d7")
        private Point targetRakePos;

        /**
         * Creates the command.
         * @param connectionEditPart the link to modify
         * @param sourceRake source side rake position, can be null if targetRake is not null.
         * @param targetRake target side rake position, can be null is sourceRake is not null.
         * @param rakeOrientation the rake orientation
         * @param sourceAnchor Source anchor
         * @param targetAnchor Target anchor
         */
        @objid ("8065e2a8-1dec-11e2-8cad-001ec947c8cc")
        public  SetRakeConstraintCommand(final ConnectionEditPart connectionEditPart, Point sourceRake, Point targetRake, Orientation rakeOrientation, ConnectionAnchor sourceAnchor, ConnectionAnchor targetAnchor) {
            if (sourceRake == null && targetRake == null) {
                throw new NullPointerException("rakes are all null.");
            }
            
            this.rakeOrientation = rakeOrientation;
            
            if (sourceRake != null) {
                this.sourceRakePos = new Point(sourceRake);
            }
            
            if (targetRake != null) {
                this.targetRakePos = new Point(targetRake);
            }
            
            this.gmLink = (GmLink) connectionEditPart.getModel();
            
            // Set anchor locations
            EditPart nodeEditPart = connectionEditPart.getSource();
            if (nodeEditPart instanceof IAnchorModelProvider) {
                this.sourceAnchorModel = ((IAnchorModelProvider) nodeEditPart).createAnchorModel(sourceAnchor);
            }
            
            nodeEditPart = connectionEditPart.getTarget();
            if (nodeEditPart instanceof IAnchorModelProvider) {
                this.targetAnchorModel = ((IAnchorModelProvider) nodeEditPart).createAnchorModel(targetAnchor);
            }
            
        }

        @objid ("8065e2bb-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public void execute() {
            final GmPath newPath = new GmPath(this.gmLink.getPath());
            
            newPath.setSourceAnchor(this.sourceAnchorModel);
            newPath.setTargetAnchor(this.targetAnchorModel);
            
            final RakeConstraint c = (RakeConstraint) newPath.getPathData();
            
            if (this.sourceRakePos != null) {
                c.getSourceRakeAnchor().setLocation(this.sourceRakePos);
                newPath.getSourceRake().setSharedAnchor(this.sourceAnchorModel);
            }
            
            if (this.targetRakePos != null) {
                c.getTargetRakeAnchor().setLocation(this.targetRakePos);
                newPath.getTargetRake().setSharedAnchor(this.targetAnchorModel);
            }
            
            c.setOrientation(this.rakeOrientation);
            
            this.gmLink.setLayoutData(newPath);
            
        }

        @objid ("8065e2be-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public boolean canExecute() {
            return (MTools.getAuthTool().canModify(this.gmLink.getDiagram().getRelatedElement()));
        }

    }

    /**
     * Stores feedback data and backup data.
     * 
     * @author cmarin
     */
    @objid ("8065e2c3-1dec-11e2-8cad-001ec947c8cc")
    private static class FeedbackState {
        @objid ("6d717391-38a3-47e5-9978-214716777be6")
        public Point originalTargetAbsLoc;

        @objid ("677873a6-ecd3-4bed-933f-417983d93b44")
        public Point originalSourceAbsLoc;

        @objid ("6d24b14d-1a93-420d-bcef-cd1aaca52fe0")
        public ConnectionAnchor originalTargetAnchor;

        @objid ("72e4528f-9d4a-4102-a525-d69c3e7f9533")
        public ConnectionAnchor originalSourceAnchor;

        @objid ("8065e2c6-1dec-11e2-8cad-001ec947c8cc")
        public RakeConstraint originalConstraint;

        @objid ("8065e2c7-1dec-11e2-8cad-001ec947c8cc")
        public RakeConstraint feedbackConstraint;

        @objid ("67914f53-1e83-11e2-8cad-001ec947c8cc")
        public Command currentCommand;

    }

    /**
     * Command that moves the rake link.
     * <p>
     * Happens when the connection is moved with both source and target nodes.
     */
    @objid ("78583f8a-ef4c-4202-b8a4-2b6d06dba375")
    private static class MoveRakeCommand extends Command {
        @objid ("0dad4660-2da6-4dab-8980-01b3f02b9169")
        private final GmLink gmLink;

        @objid ("8ba241b4-5cdf-4e63-87dc-ec5352c08357")
        private final RakeConstraint constraint;

        @objid ("ab8cfad2-f49d-43c9-94ba-c646d4cbf0e6")
        public  MoveRakeCommand(final EditPart connectionEditPart, RakeConstraint constraint) {
            if (constraint.getSourceRakeAnchor() == null && constraint.getTargetRakeAnchor() == null) {
                throw new NullPointerException("rakes are all null.");
            }
            
            this.constraint = constraint.getCopy();
            this.gmLink = (GmLink) connectionEditPart.getModel();
            
        }

        @objid ("38405d61-524a-4199-9a03-289ef9b5c878")
        @Override
        public void execute() {
            final GmPath newPath = new GmPath(this.gmLink.getPath());
            
            final RakeConstraint c = (RakeConstraint) newPath.getPathData();
            
            if (this.constraint.getSourceRakeAnchor() != null) {
                c.getSourceRakeAnchor().setLocation(this.constraint.getSourceRakeLocation());
            }
            
            if (this.constraint.getTargetRakeAnchor() != null) {
                c.getTargetRakeAnchor().setLocation(this.constraint.getTargetRakeLocation());
            }
            
            c.setOrientation(this.constraint.getOrientation());
            
            this.gmLink.setLayoutData(newPath);
            
        }

        @objid ("92991606-87a1-45ca-bc2b-917bca430e63")
        @Override
        public boolean canExecute() {
            return (MTools.getAuthTool().canModify(this.gmLink.getDiagram().getRelatedElement()));
        }

    }

}
