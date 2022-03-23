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
package org.modelio.diagram.elements.core.link.ortho.edit;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.elements.core.figures.geometry.Orientation;
import org.modelio.diagram.elements.core.figures.routers.AnchorBounds;
import org.modelio.diagram.elements.core.figures.routers.ConnectionState;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.link.MPrecisionPoint;
import org.modelio.diagram.elements.core.link.ortho.edit.AxisAccessor.OrientedAccessors;

/**
 * Experimental orthogonal bend point editor based on {@link MoveBpReq requests} and {@link MoveBpCommand commands}.
 * 
 * @deprecated experimental
 * @since 5.0.2
 */
@objid ("1af028fe-4fd6-4ca7-98d1-fe38e9ee402c")
@Deprecated
class ConnectionBpEditor {
    @objid ("16d6e6da-253b-4b55-ad87-b75ea6766f7f")
    private ConnectionEditor editor;

    @objid ("241527cc-2204-4e76-a49a-b5f5a6ea10f2")
    private final MPrecisionPoint otherPoint = new MPrecisionPoint();

    @objid ("e8a48b82-e665-4d60-85cd-1e2c1a597a4f")
    private final MPrecisionPoint origPoint = new MPrecisionPoint();

    /**
     * Initialize this instance with the given editor.
     * @param editor a connection editor
     * @return this instance
     */
    @objid ("c3b602c5-764b-491f-a0c1-ce4061abb39e")
    public ConnectionBpEditor init(ConnectionEditor editor) {
        this.editor = editor;
        return this;
    }

    /**
     * Execute a {@link MoveBpCommand} on the edited state
     * @param command the command to execute
     */
    @objid ("2ceb65b2-3503-48b6-a9e8-6a8da0dc2d22")
    public void apply(MoveBpCommand command) {
        ConnectionView view = this.editor.getView();
        ConnectionState state = view.getState();
        
        if (command.getNewSourceAnchor() != null) {
            state.setSourceAnchor(command.getNewSourceAnchor());
        }
        
        if (command.getNewTargetAnchor() != null) {
            state.setTargetAnchor(command.getNewTargetAnchor());
        }
        
        if (command.getIndex() > 0) {
            MPoint mPoint = state.getMPoints().get(command.getIndex() - 1);
            mPoint.setLocation(command.getNewLocation());
            view.getConnection().translateToRelative(mPoint);
            if (command.getManual() != null) {
                mPoint.setFixed(command.getManual().booleanValue());
            }
        }
        
    }

    /**
     * Request moving a connection point.
     * @param command the command to fill
     * @param request the move request
     * @return the command, for convenience
     */
    @objid ("4014a37e-3d7e-4541-8fb3-3e15eb892953")
    public MoveBpCommand getMovePointCommand(MoveBpCommand command, MoveBpReq request) {
        command.reset();
        
        if (request.getPointIndex() == 0) {
            // Update source anchor.
            getMoveSourceCommand(command, request);
        
        } else if (request.getPointIndex() == this.editor.getView().getTargetAnchorIndex()) {
            // Update target anchor.
            getMoveTargetCommand(command, request);
        
        } else {
            command.setNewLocation(request.getLocation());
            command.setManual(request.getManual());
            command.setIndex(request.getPointIndex());
        
            if (request.getCheckPrevious() != null) {
                int otherIdx = request.getPointIndex() - 1;
                fixNewLocation(request, command, otherIdx, request.getCheckPrevious());
            }
        
            if (request.getCheckNext() != null) {
                int otherIdx = request.getPointIndex() + 1;
                fixNewLocation(request, command, otherIdx, request.getCheckNext());
            }
        }
        return command;
    }

    /**
     * Snap the command result point or bounce it from the point at given index depending on it is manual or automatic.
     * @param request the move point request
     * @param command the result command
     * @param otherIdx the index of the point to bounce or snap
     * @param axisAccessor the axis for which constraints must be enforced
     */
    @objid ("b24298af-faa3-4629-8fd6-33f6c542ff5e")
    private void fixNewLocation(MoveBpReq request, MoveBpCommand command, int otherIdx, AxisAccessor axisAccessor) {
        Point newLocation = command.getNewLocation();
        this.editor.getView().getPoint(this.origPoint, request.getPointIndex(), true);
        this.editor.getView().getPoint(this.otherPoint, otherIdx, true);
        
        // snap startPoint if previous point
        if (this.otherPoint.isFixed()) {
            if (axisAccessor.enforceMinDistance(newLocation, this.origPoint, this.otherPoint)) {
                command.setBlockedByIndex(otherIdx);
            }
        } else {
            if (axisAccessor.snapToPoint(newLocation, this.otherPoint)) {
                command.setSnappedToIndex(otherIdx);
            }
        }
        
    }

    @objid ("77b07570-c4e8-4f22-8524-4d74c0767ab7")
    @Deprecated
    private MoveBpCommand getBasicMovePointCommand(MoveBpCommand command, MoveBpReq request) {
        command.reset();
        
        boolean sameFace = true;
        if (request.getPointIndex() == 0) {
            // Update source anchor.
            Point reqLoc = request.getLocation();
        
            ConnectionAnchor newConnectionAnchor = this.editor.requestSourceAnchor(reqLoc, sameFace);
            command.setNewSourceAnchor(newConnectionAnchor);
        } else if (request.getPointIndex() == this.editor.getView().getTargetAnchorIndex()) {
            // Update target anchor.
            Point reqLoc = request.getLocation();
        
            ConnectionAnchor newAnchor = this.editor.requestTargetAnchor(reqLoc, sameFace);
            command.setNewTargetAnchor(newAnchor);
        } else {
            command.setNewLocation(request.getLocation());
            command.setManual(request.getManual());
        }
        return command;
    }

    @objid ("fba30cc1-1489-4b4d-b77c-897744f9808d")
    private void getMoveSourceCommand(MoveBpCommand command, MoveBpReq request) {
        AnchorBounds anchorBounds = this.editor.getView().getAnchorBounds();
        
        // Update source anchor.
        Point reqLoc = request.getLocation();
        this.editor.getView().getSourceLocation(this.origPoint, true);
        Point newLocation = command.getNewLocation().setLocation(reqLoc);
        
        AxisAccessor access = AxisAccessor.forPointAndRectangle(this.origPoint, anchorBounds.source).across;
        access.setCoord(newLocation, reqLoc);
        
        if (access.isCoordContained(reqLoc, anchorBounds.source)) {
            ConnectionAnchor newConnectionAnchor = this.editor.requestSourceAnchor(reqLoc, true);
            command.setNewSourceAnchor(newConnectionAnchor);
        } else {
            // Bounce mouse back from nodes
            if (access.perpendicular().isCoordContained(newLocation, anchorBounds.target)) {
                access.bounceFromRect(newLocation, anchorBounds.target);
            }
        
            access.bounceFromRect(newLocation, anchorBounds.source);
        
            // Request a new anchor on perpendicular face
            access.perpendicular().setCoord(newLocation, anchorBounds.source.getCenter());
            ConnectionAnchor anchor = this.editor.requestSourceAnchor(newLocation, false);
            command.setNewSourceAnchor(anchor);
        }
        
        command.setNewLocation(this.editor.getView().getSourceLocation(this.otherPoint, command.getNewSourceAnchor(), true));
        
    }

    @objid ("bff078ee-f987-4722-83a9-36071bc67c2d")
    private void getMoveTargetCommand(MoveBpCommand command, MoveBpReq request) {
        ConnectionView view = this.editor.getView();
        
        // Update target anchor.
        AnchorBounds anchorBounds = view.getAnchorBounds();
        Point reqLoc = request.getLocation();
        view.getTargetLocation(this.origPoint, true);
        AxisAccessor access = AxisAccessor.forPointAndRectangle(this.origPoint, anchorBounds.target).across;
        Point newLocation = command.getNewLocation().setLocation(this.origPoint);
        access.setCoord(newLocation, reqLoc);
        
        if (access.isCoordContained(reqLoc, anchorBounds.target)) {
            // request anchor on same face
            ConnectionAnchor newAnchor = this.editor.requestTargetAnchor(newLocation, true);
            command.setNewTargetAnchor(newAnchor);
        } else {
            // if newLocation outside node bounds, Request a new anchor on perpendicular face, a segment will have to be added by caller
            // Bounce mouse back from nodes
            // newLocation = newLocation.getCopy();
            if (access.perpendicular().isCoordContained(newLocation, anchorBounds.source)) {
                access.bounceFromRect(newLocation, anchorBounds.source);
            }
        
            access.bounceFromRect(newLocation, anchorBounds.target);
        
            // Request a new anchor on perpendicular face
            access.perpendicular().setCoord(newLocation, anchorBounds.target.getCenter());
            ConnectionAnchor anchor = this.editor.requestTargetAnchor(newLocation, false);
            command.setNewTargetAnchor(anchor);
        }
        
        command.setNewLocation(view.getTargetLocation(this.otherPoint, command.getNewSourceAnchor(), true));
        
    }

    /**
     * Ensure path is orthogonal from startIndex to startIndex + len.
     * <p>
     * Add bend points to make connection orthogonal, does not move points.
     * @param startIndex the index of the first point
     * @param len the number of points to check
     */
    @objid ("1cc3108f-a231-483d-b71b-0ebc4a173fbc")
    public void insertOrthogonalBendPoints(int startIndex, int len) {
        // Note : This method may be moved to ConnectionView
        ConnectionView cview = this.editor.getView();
        
        Orientation previousOrientation = cview.getOrientationFromPrevious(startIndex);
        
        int index = Math.max(0, startIndex); // ensure indexes are within points bounds
        int endIndex = Math.min(startIndex + len, cview.getTargetAnchorIndex());
        
        while (index < endIndex) {
            if (index > 20) {
                throw new IllegalStateException();
            }
        
            Orientation currentOrientation = cview.getOrientationToNext(index);
            if (currentOrientation == Orientation.NONE) {
                MPrecisionPoint curPoint = cview.getPoint(this.origPoint, index, true);
                MPrecisionPoint nextPoint = cview.getPoint(this.otherPoint, index + 1, true);
        
                MPoint newPoint = new MPoint();
                OrientedAccessors access = AxisAccessor.forOrientation(previousOrientation);
                access.along.setCoord(newPoint, curPoint);
                access.across.setCoord(newPoint, nextPoint);
                if (false) {
                    System.err.printf("Add %s bendpoint at %d :\n", newPoint, index);
                    System.err.printf("  constraint = %s\n", cview.getState().getMPoints());
                    System.err.printf("  curPoint[%d] = %s, nextPoint[%d] = %s\n", index, curPoint, index + 1, nextPoint, newPoint);
                    System.err.printf("  previous orientation = %s\n", previousOrientation);
                }
        
                cview.getConnection().translateToRelative(newPoint);
                cview.getState().getMPoints().add(index, newPoint);
                endIndex++;
                // swap previous orientation so that all points don't come on one line
                previousOrientation = previousOrientation.getPerpendicular();
            } else {
                previousOrientation = currentOrientation;
            }
            index++;
        }
        
    }

}
