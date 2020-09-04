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

package org.modelio.diagram.elements.core.link;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gef.requests.TargetRequest;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.modelio.diagram.elements.core.figures.LinkFigure;
import org.modelio.diagram.elements.core.figures.geometry.PointListUtilities;
import org.modelio.diagram.elements.core.requests.NavigationRequest;

/**
 * Specialized <code>SelectEditPartTracker</code> that allows for a request action to be taken on a
 * <code>Connection</code> shapes anywhere along the extent of the line.
 * <p>
 * Depending on whether the user clicks on a bend
 * point along a line or on the line itself, this is interpreted as either a
 * <code>RequestConstants.REQ_MOVE_BENDPOINT</code> request
 * or a <code>RequestConstants.REQ_CREATE_BENDPOINT</code> request respectively.
 * 
 * @author cma
 */
@objid ("806aa75e-1dec-11e2-8cad-001ec947c8cc")
public class SelectConnectionEditPartTracker extends SelectEditPartTracker {
    /**
     * Key modifier for ignoring snap while dragging. It's CTRL on Mac, and ALT on all other platforms.
     */
    @objid ("806aa762-1dec-11e2-8cad-001ec947c8cc")
    private final int MODIFIER_NO_SNAPPING;

    @objid ("806aa76a-1dec-11e2-8cad-001ec947c8cc")
    private boolean bSourceFeedback = false;

    @objid ("806aa768-1dec-11e2-8cad-001ec947c8cc")
    private int index = -1;

    @objid ("9067f6f7-1e83-11e2-8cad-001ec947c8cc")
    private String type;

    @objid ("806aa771-1dec-11e2-8cad-001ec947c8cc")
    private Collection<Object> exclusionSet;

    @objid ("651be283-1e83-11e2-8cad-001ec947c8cc")
    private Point originalLocation = null;

    @objid ("6519802b-1e83-11e2-8cad-001ec947c8cc")
    private PrecisionRectangle sourceRectangle;

    @objid ("65198029-1e83-11e2-8cad-001ec947c8cc")
    private Request sourceRequest;

    /**
     * Initialize the tracker.
     * @param owner Connection edit part that creates and owns the tracker object
     */
    @objid ("806aa774-1dec-11e2-8cad-001ec947c8cc")
    public SelectConnectionEditPartTracker(ConnectionEditPart owner) {
        super(owner);
        if (SWT.getPlatform().equals("carbon")) {
            this.MODIFIER_NO_SNAPPING = SWT.CTRL;
        } else {
            this.MODIFIER_NO_SNAPPING = SWT.ALT;
        }
    }

    @objid ("806aa77a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void deactivate() {
        if (!isInState(STATE_TERMINAL)) {
            eraseSourceFeedback();
            eraseTargetFeedback();
        }
        this.sourceRequest = null;
        super.deactivate();
    }

    @objid ("806aa785-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Cursor calculateCursor() {
        if (getType() == RequestConstants.REQ_MOVE_BENDPOINT) {
            return Cursors.SIZEALL;
            // TODO: look at this
            //return Cursors.CURSOR_SEG_MOVE;
        }
        return getConnection().getCursor();
    }

    /**
     * @see org.eclipse.gef.tools.AbstractTool#createOperationSet()
     */
    @objid ("806d0993-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final List<EditPart> createOperationSet() {
        List<EditPart> list = new ArrayList<>();
        list.add(getConnectionEditPart());
        return list;
    }

    /**
     * Creates the source request that is activated when the drag operation occurs.
     * @return a <code>Request</code> that is the newly created source request
     */
    @objid ("806d099d-1dec-11e2-8cad-001ec947c8cc")
    protected Request createSourceRequest() {
        final String t = getType();
        
        if (t.equals(REQ_CREATE_BENDPOINT) || t.equals(REQ_MOVE_BENDPOINT)) {
            final BendpointRequest request = new BendpointRequest();
            request.setType(getType());
            request.setIndex(getIndex());
            request.setSource((ConnectionEditPart) getSourceEditPart());
            return request;
        } else if (t.equals(REQ_RECONNECT_SOURCE) || t.equals(REQ_RECONNECT_TARGET)) {
            final ReconnectRequest req = new ReconnectRequest();
            req.setConnectionEditPart(getConnectionEditPart());
            req.setType(t);
            return req;
        } else {
            throw new IllegalStateException("Unknow request type:" + t);
        }
    }

    @objid ("806d09a8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final Command getCommand() {
        final Request r = getSourceRequest();
        
        if (r instanceof TargetRequest) {
            if (getTargetEditPart() != null) {
                return getTargetEditPart().getCommand(r);
            } else {
                return null;
            }
        } else {
            return getSourceEditPart().getCommand(r);
        }
    }

    @objid ("806d09a3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final String getCommandName() {
        return getType();
    }

    /**
     * @return the <code>LinkFigure</code> that is referenced by the connection edit part.
     */
    @objid ("806d09e5-1dec-11e2-8cad-001ec947c8cc")
    protected final LinkFigure getConnection() {
        return (LinkFigure) getConnectionEditPart().getFigure();
    }

    /**
     * Method getConnectionEditPart.
     * @return ConnectionEditPart
     */
    @objid ("806d09de-1dec-11e2-8cad-001ec947c8cc")
    protected final ConnectionEditPart getConnectionEditPart() {
        return (ConnectionEditPart) getSourceEditPart();
    }

    @objid ("806d09ae-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected String getDebugName() {
        return "Bendpoint Handle Tracker " + getCommandName(); //$NON-NLS-1$
    }

    @objid ("806f6c18-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final Collection<?> getExclusionSet() {
        if (this.exclusionSet == null) {
            this.exclusionSet = new ArrayList<>();
            this.exclusionSet.add(getConnection());
        }
        return this.exclusionSet;
    }

    /**
     * Gets the current line segment index that the user clicked on to activate the drag tracker.
     * @return int
     */
    @objid ("806d09b3-1dec-11e2-8cad-001ec947c8cc")
    protected final int getIndex() {
        return this.index;
    }

    @objid ("806f6c11-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final Request getTargetRequest() {
        return getSourceRequest();
    }

    /**
     * Determines the type of request that will be created for the drag operation.
     * @return Object
     */
    @objid ("806d09b8-1dec-11e2-8cad-001ec947c8cc")
    protected final String getType() {
        return this.type;
    }

    @objid ("806d09bd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final boolean handleButtonDown(int button) {
        if (isNavigateEvent(button)) {
            return handleNavigationEvent();
        }
        
        if (!super.handleButtonDown(button)) {
            return false;
        }
        
        final LinkFigure connection = getConnection();
        
        final Point p = getLocation();
        connection.translateToRelative(p);
        
        final PointList points = connection.getPoints();
        
        if (connection.getSourceDecoration() != null && connection.getSourceDecoration().containsPoint(p)) {
            handleButtonDownOnSource(button);
        } else if (connection.getTargetDecoration() != null &&
                   connection.getTargetDecoration().containsPoint(p)) {
            handleButtonDownOnTarget(button);
        } else {
            // look for a bend point
            final Dimension size = new Dimension(9, 9);
            connection.translateToRelative(size);
        
            for (int i = 0; i < points.size(); i++) {
                final Point ptCenter = points.getPoint(i);
                final Rectangle rect = new Rectangle(ptCenter.x - size.width / 2,
                        ptCenter.y - size.height / 2,
                        size.width, size.height);
        
                if (rect.contains(p)) {
                    if (i == 0) {
                        handleButtonDownOnSource(button);
                    } else if (i == points.size() - 1) {
                        handleButtonDownOnTarget(button);
                    } else {
                        handleButtonDownOnBendpoint(button, i, points);
                    }
                }
            }
        
            if (getIndex() == -1) {
                // Mouse is not on a bendpoint, look for a segment
                final int segmentIndex = PointListUtilities.findNearestLineSegIndexOfPoint(
                        connection.getPoints(),
                        new Point(p.x, p.y));
                handleButtonDownOnSegment(button, segmentIndex);
            }
        }
        return true;
    }

    /**
     * Handle mouse button down on the given connection point.
     * @param button the mouse button
     * @param pointIndex the index of the point in the point list
     * @param points the connection points list.
     */
    @objid ("806f6bfc-1dec-11e2-8cad-001ec947c8cc")
    protected void handleButtonDownOnBendpoint(final int button, final int pointIndex, final PointList points) {
        if (pointIndex == 0) {
            setType(RequestConstants.REQ_RECONNECT_SOURCE);
            setIndex(pointIndex);
        } else if (pointIndex == points.size() - 1) {
            setType(RequestConstants.REQ_RECONNECT_TARGET);
            setIndex(pointIndex);
        } else {
            setType(RequestConstants.REQ_MOVE_BENDPOINT);
            setIndex(pointIndex - 1);
        }
    }

    /**
     * Handle mouse button down on the source end point.
     * @param button the mouse button.
     */
    @objid ("806f6c07-1dec-11e2-8cad-001ec947c8cc")
    protected void handleButtonDownOnSource(final int button) {
        setType(RequestConstants.REQ_RECONNECT_SOURCE);
        setIndex(0);
    }

    /**
     * Handle mouse button down on the target end point.
     * @param button the mouse button.
     */
    @objid ("806f6c0c-1dec-11e2-8cad-001ec947c8cc")
    protected void handleButtonDownOnTarget(final int button) {
        setType(RequestConstants.REQ_RECONNECT_TARGET);
        setIndex(0);
    }

    @objid ("806d09c3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final boolean handleButtonUp(int button) {
        boolean bExecuteDrag = isInState(STATE_DRAG_IN_PROGRESS) && shouldAllowDrag();
        
        boolean bRet = super.handleButtonUp(button);
        
        if (bExecuteDrag) {
            eraseSourceFeedback();
            eraseTargetFeedback();
            setCurrentCommand(getCommand());
            executeCurrentCommand();
        }
        return bRet;
    }

    @objid ("806d09c9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final boolean handleDragInProgress() {
        if (isInState(STATE_DRAG_IN_PROGRESS) && shouldAllowDrag()) {
            updateSourceRequest();
            if (getSourceRequest() instanceof TargetRequest) {
                updateTargetUnderMouse();
                showTargetFeedback();
            }
            showSourceFeedback();
            setCurrentCommand(getCommand());
        }
        return true;
    }

    @objid ("806d09ce-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final boolean handleDragStarted() {
        this.originalLocation = null;
        this.sourceRectangle = null;
        return stateTransition(STATE_DRAG, STATE_DRAG_IN_PROGRESS);
    }

    /**
     * Sets the current line segment index based on the location the user clicked on the connection.
     * @param i int representing the line segment index in the connection.
     */
    @objid ("806aa77d-1dec-11e2-8cad-001ec947c8cc")
    protected final void setIndex(int i) {
        this.index = i;
    }

    /**
     * Sets the type of request that will be created for the drag operation.
     * @param type the <code>String</code> that represents the type of request.
     */
    @objid ("806aa781-1dec-11e2-8cad-001ec947c8cc")
    protected final void setType(String type) {
        this.type = type;
    }

    /**
     * Determines if the the connection should be dragged or not.
     * @return <code>boolean</code> <code>true</code> if dragging can occur, <code>false</code> otherwise.
     */
    @objid ("806d09d3-1dec-11e2-8cad-001ec947c8cc")
    protected final boolean shouldAllowDrag() {
        return (getIndex() != -1);
    }

    @objid ("806d09d8-1dec-11e2-8cad-001ec947c8cc")
    protected final void updateSourceRequest() {
        /**
         * @see org.eclipse.gef.tools.SimpleDragTracker#updateSourceRequest()
         */
        LocationRequest request = (LocationRequest) getSourceRequest();
        
        if (this.originalLocation == null) {
            this.originalLocation = getStartLocation().getCopy();
        }
        
        Dimension delta = getDragMoveDelta();
        
        if (getCurrentInput().isShiftKeyDown()) {
            float ratio = 0;
            if (delta.width != 0) {
                ratio = (float) delta.height / (float) delta.width;
            }
        
            ratio = Math.abs(ratio);
            if (ratio > 0.5 && ratio < 1.5) {
                if (Math.abs(delta.height) > Math.abs(delta.width)) {
                    if (delta.height > 0) {
                        delta.height = Math.abs(delta.width);
                    } else {
                        delta.height = -Math.abs(delta.width);
                    }
                } else {
                    if (delta.width > 0) {
                        delta.width = Math.abs(delta.height);
                    } else {
                        delta.width = -Math.abs(delta.height);
                    }
                }
            } else {
                if (Math.abs(delta.width) > Math.abs(delta.height)) {
                    delta.height = 0;
                } else {
                    delta.width = 0;
                }
            }
        }
        
        Point moveDelta = new Point(delta.width, delta.height);
        SnapToHelper snapToHelper = getConnectionEditPart().getAdapter(SnapToHelper.class);
        
        Rectangle rect = new Rectangle(this.originalLocation.x, this.originalLocation.y, 1, 1);
        if (this.sourceRectangle == null) {
            this.sourceRectangle = new PrecisionRectangle(rect);
        }
        
        if (snapToHelper != null && !getCurrentInput().isModKeyDown(this.MODIFIER_NO_SNAPPING)) {
            PrecisionRectangle baseRect = this.sourceRectangle.getPreciseCopy();
            baseRect.translate(moveDelta);
            PrecisionPoint preciseDelta = new PrecisionPoint(moveDelta);
            snapToHelper.snapPoint(request,
                                   PositionConstants.HORIZONTAL | PositionConstants.VERTICAL,
                                   new PrecisionRectangle[] { baseRect },
                                   preciseDelta);
            Point newLocation = this.originalLocation.getCopy().translate(preciseDelta);
            request.setLocation(newLocation);
        } else {
            request.setLocation(getLocation());
        }
    }

    /**
     * Show the source drag feedback for the drag occurring within the viewer.
     */
    @objid ("806d09da-1dec-11e2-8cad-001ec947c8cc")
    private void eraseSourceFeedback() {
        if (!isShowingFeedback()) {
            return;
        }
        setShowingFeedback(false);
        
        final List<EditPart> editParts = getOperationSet();
        for (EditPart editPart : editParts) {
            editPart.eraseSourceFeedback(getSourceRequest());
        }
    }

    /**
     * Source request getter.
     * <p>
     * Lazily creates the request if needed.
     * @return the source request.
     */
    @objid ("806d09ea-1dec-11e2-8cad-001ec947c8cc")
    private Request getSourceRequest() {
        if (this.sourceRequest == null) {
            this.sourceRequest = createSourceRequest();
        }
        return this.sourceRequest;
    }

    /**
     * @return boolean true if feedback is being displayed, false otherwise.
     */
    @objid ("806f6bf0-1dec-11e2-8cad-001ec947c8cc")
    private boolean isShowingFeedback() {
        return this.bSourceFeedback;
    }

    /**
     * Method setShowingFeedback.
     * @param bSet boolean to set the feedback flag on or off.
     */
    @objid ("806f6bf5-1dec-11e2-8cad-001ec947c8cc")
    private void setShowingFeedback(boolean bSet) {
        this.bSourceFeedback = bSet;
    }

    /**
     * Method showSourceFeedback. Show the source drag feedback for the drag occurring within the viewer.
     */
    @objid ("806f6bf9-1dec-11e2-8cad-001ec947c8cc")
    private void showSourceFeedback() {
        for (Object o : getOperationSet()) {
            EditPart editPart = (EditPart) o;
            editPart.showSourceFeedback(getSourceRequest());
        }
        setShowingFeedback(true);
    }

    @objid ("6f4beb00-277d-4085-a5d0-140acc64043e")
    private boolean handleNavigationEvent() {
        NavigationRequest request = new NavigationRequest();
        request.setLocation(getLocation());
        getSourceEditPart().performRequest(request);
        return true;
    }

    @objid ("f209e2f8-1d92-41e4-979e-f7e3db52d3e3")
    private boolean isNavigateEvent(int button) {
        final Input currentInput = getCurrentInput();
        return button==1 && currentInput.isControlKeyDown() && currentInput.isAltKeyDown();
    }

    /**
     * @param button the mouse button
     * @param segmentIndex the segement index
     */
    @objid ("a920dd5b-2479-458e-9722-34ba2f211467")
    protected void handleButtonDownOnSegment(int button, int segmentIndex) {
        setIndex(segmentIndex - 1);
        setType(RequestConstants.REQ_CREATE_BENDPOINT);
    }

}
