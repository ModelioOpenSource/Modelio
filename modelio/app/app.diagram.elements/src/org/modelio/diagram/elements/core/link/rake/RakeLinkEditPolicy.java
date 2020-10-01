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
import org.eclipse.gef.AccessibleHandleProvider;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy;
import org.eclipse.gef.handles.BendpointHandle;
import org.eclipse.gef.handles.BendpointMoveHandle;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.figures.anchors.ISlidableAnchor;
import org.modelio.diagram.elements.core.figures.geometry.LineSeg;
import org.modelio.diagram.elements.core.figures.routers.RakeConstraint;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.IAnchorModelProvider;
import org.modelio.diagram.elements.core.policies.SelectionHandlesBuilder;
import org.modelio.vcore.model.api.MTools;

/**
 * This EditPolicy defines the behavior of Bendpoints on a Connection.
 */
@objid ("805ebbab-1dec-11e2-8cad-001ec947c8cc")
public class RakeLinkEditPolicy extends SelectionHandlesEditPolicy implements PropertyChangeListener {
    @objid ("805ebbaf-1dec-11e2-8cad-001ec947c8cc")
    private FeedbackState feedbackState = null;

    /**
     * Constructor for EditPolicy
     */
    @objid ("805ebbb0-1dec-11e2-8cad-001ec947c8cc")
    public RakeLinkEditPolicy() {
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
     * 
     * @param request the Request
     */
    @objid ("80611dcf-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void eraseSourceFeedback(Request request) {
        if (REQ_MOVE_BENDPOINT.equals(request.getType()) || REQ_CREATE_BENDPOINT.equals(request.getType())) {
            restoreOriginalConstraint();
            getFeedbackState().originalConstraint = null;
            if (((BendpointRequest) request).getSource() != getHost()) {
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
        if (REQ_MOVE_BENDPOINT.equals(request.getType())) {
            return getFeedbackState().currentCommand;
        }
        if (REQ_CREATE_BENDPOINT.equals(request.getType())) {
            return getFeedbackState().currentCommand;
        }
        return null;
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
        if (REQ_CREATE_BENDPOINT.equals(request.getType())) {
            showMoveLineSegFeedback((BendpointRequest) request);
            getFeedbackState().currentCommand = getBendpointsChangedCommand((BendpointRequest) request);
        } else if (REQ_MOVE_BENDPOINT.equals(request.getType())) {
            showMoveOrthogonalBendpointFeedback((BendpointRequest) request);
            getFeedbackState().currentCommand = getBendpointsChangedCommand((BendpointRequest) request);
        }
        
        super.showSourceFeedback(request);
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
     * @param list
     * @param connEP
     * @param i
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
     * 
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
        /*
        if ((index == 0 && lineOutsideSource(newLine)) ||
                ((index == 2) && lineOutsideTarget(newLine)))
        {
            newLine.setOrigin(moveLine.getOrigin());
            newLine.setTerminus(moveLine.getTerminus());
            return true;
        }*/
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
     * 
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
        return new SetRakeConstraintCommand((ConnectionEditPart) getHost(),
                                                                                                    c.getSourceRakeLocation(),
                                                                                                    c.getTargetRakeLocation(),
                                                                                                    srcAnchor,
                                                                                                    targetAnchor).chain(cleanFpCmd);
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
     * 
     * @return the connection routing constraint.
     */
    @objid ("8063803a-1dec-11e2-8cad-001ec947c8cc")
    private RakeConstraint getConnectionRoutingConstraint() {
        return (RakeConstraint) getConnection().getRoutingConstraint();
    }

    /**
     * Get the line point at the given index. index 0 is the source anchor, the last point is the target anchor and
     * other indexes correspond to the bend points in the constraint.
     * 
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
     * 
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
        final RakeConstraint originalConstraint = getFeedbackState().originalConstraint;
        if (originalConstraint != null) {
            final RakeConstraint current = getConnectionRoutingConstraint();
            if (current.getSourceRakeAnchor() != null) {
                current.getSourceRakeAnchor().setLocation(originalConstraint.getSourceRakeAnchor()
                                                                            .getReferencePoint());
            }
        
            if (current.getTargetRakeAnchor() != null) {
                current.getTargetRakeAnchor().setLocation(originalConstraint.getTargetRakeAnchor()
                                                                            .getReferencePoint());
            }
        
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
        }
    }

    /**
     * Set the position of the point at the given index for a rake that joins on the target side.
     * 
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
                // 0 is the source anchor, try to update it
                if (connection.getSourceAnchor() instanceof ISlidableAnchor) {
                    ISlidableAnchor a = (ISlidableAnchor) connection.getSourceAnchor();
                    a.setLocation(absolutePoint);
        
                } else {
                    connection.setSourceAnchor(getNewSourceAnchor(absolutePoint));
                }
                break;
            case 1:
                // 1 is the rake anchor on the source side or a computed point, do nothing
        
                if (c.getSourceRakeAnchor() != null) {
                    c.getSourceRakeAnchor().setLocation(relativePoint);
                }
                break;
            case 2:
                // 2 is the rake anchor on the target side
        
                if (c.getTargetRakeAnchor() != null) {
                    c.getTargetRakeAnchor().setLocation(relativePoint);
                }
                break;
            case 3:
                // 3 is the target anchor, try to update it
                if (connection.getTargetAnchor() instanceof ISlidableAnchor) {
                    ISlidableAnchor a = (ISlidableAnchor) connection.getTargetAnchor();
                    a.setLocation(absolutePoint);
                } else {
                    connection.setTargetAnchor(getNewTargetAnchor(absolutePoint));
                }
                break;
            default:
                throw new IllegalArgumentException("index out of bounds");
        }
    }

    /**
     * Modify the coordinates of one segment of the constraint.
     * 
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
        
        connection.setRoutingConstraint(constraint);
    }

    /**
     * Draws feedback for moving a bend point of a rectilinear connection
     * 
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
        
        connection.setRoutingConstraint(constraint);
    }

    /**
     * Command that set the rake location.
     * 
     * @author cmarin
     */
    @objid ("8065e297-1dec-11e2-8cad-001ec947c8cc")
    public static class SetRakeConstraintCommand extends Command {
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
         * 
         * @param connectionEditPart the link to modify
         * @param sourceRake source side rake position, can be null if targetRake is not null.
         * @param targetRake target side rake position, can be null is sourceRake is not null.
         * @param sourceAnchor Source anchor
         * @param targetAnchor Target anchor
         */
        @objid ("8065e2a8-1dec-11e2-8cad-001ec947c8cc")
        public SetRakeConstraintCommand(final ConnectionEditPart connectionEditPart, Point sourceRake, Point targetRake, ConnectionAnchor sourceAnchor, ConnectionAnchor targetAnchor) {
            if (sourceRake == null && targetRake == null) {
                throw new NullPointerException("rakes are all null.");
            }
            
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
            }
            
            if (this.targetRakePos != null) {
                c.getTargetRakeAnchor().setLocation(this.targetRakePos);
            }
            
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
        @objid ("8065e2c6-1dec-11e2-8cad-001ec947c8cc")
        public RakeConstraint originalConstraint;

        @objid ("8065e2c7-1dec-11e2-8cad-001ec947c8cc")
        public RakeConstraint feedbackConstraint;

        @objid ("67914f53-1e83-11e2-8cad-001ec947c8cc")
        public Command currentCommand;

        @objid ("806844df-1dec-11e2-8cad-001ec947c8cc")
        public FeedbackState() {
        }

    }

}
