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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.figures.anchors.DirectionalAnchor;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;
import org.modelio.diagram.elements.core.figures.routers.AnchorBounds;
import org.modelio.diagram.elements.core.figures.routers.AutoOrthoConstants;
import org.modelio.diagram.elements.core.figures.routers.AutoOrthogonalRouter;
import org.modelio.diagram.elements.core.figures.routers.AutoOrthogonalRouterAlgorithm;
import org.modelio.diagram.elements.core.figures.routers.ConnectionState;
import org.modelio.diagram.elements.core.helpers.ToolSelectionUtils;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.link.MPrecisionPoint;
import org.modelio.diagram.elements.core.link.ortho.edit.AxisAccessor.OrientedAccessors;
import org.modelio.diagram.elements.core.link.path.AbstractLinkPathEditor;
import org.modelio.diagram.elements.core.link.path.ILinkPathEditor;
import org.modelio.diagram.elements.core.link.path.ILinkPathEditorFactory;
import org.modelio.diagram.elements.core.requests.ChangeBoundsFeedbackMap;

/**
 * "Auto orthogonal" connections editor that uses {@link ConnectionEditor}.
 * 
 * @since 5.0.2
 */
@objid ("691d83a6-5cf8-4878-818e-9a4ae3a9416e")
public class AutoOrthoLinkPathEditor1 extends AbstractLinkPathEditor implements ILinkPathEditorFactory {
    /**
     * Shared instance
     */
    @objid ("1208c28b-bbdf-4b26-a0e9-1b863f39d91d")
    public static final AutoOrthoLinkPathEditor1 INSTANCE = new AutoOrthoLinkPathEditor1();

    @objid ("914c4c42-60c3-4f93-8782-2e21c499226f")
    private static final SegmentMover SegmentMover = new SegmentMover();

    @objid ("2d79cd95-af0a-46dc-8f11-1f7d596a5313")
    private static final AnchorChangedEditor anchorChangedEditor = new AnchorChangedEditor();

    @objid ("69f0d26a-9fcb-4397-a36d-14d198913b30")
    private static final MPrecisionPoint TMP1 = new MPrecisionPoint();

    @objid ("2b1a5ef2-5b27-4574-8768-492cdc8f152b")
    private static final MPrecisionPoint TMP2 = new MPrecisionPoint();

    @objid ("d53c15f7-fb2a-4bc3-a21f-250a9a58603f")
    private static final MPrecisionPoint TMP3 = new MPrecisionPoint();

    @objid ("37e2b583-4527-43a4-82b9-7cd850c01233")
    private static final MPrecisionPoint TMP4 = new MPrecisionPoint();

    @objid ("f4e7f62b-0a92-45a8-824f-52fd1a8300b1")
    private static final MPrecisionPoint TMP5 = new MPrecisionPoint();

    /**
     * A temporary test connection to test its path is good and not too far from mouse cursor.
     * <p>
     * Exists to minimizes allocations.
     */
    @objid ("84d03d5a-8778-4eea-82c7-1658cd8d623d")
    private static final PolylineConnection TMP_CONNECTION = new PolylineConnection();

    @objid ("cdc5a955-c573-48c8-9f33-e896d1a85965")
    private final ConnectionEditor editor = new ConnectionEditor();

    @objid ("ac1e8a95-1c12-4cf5-a8a5-f4a3290b8975")
    @Override
    public ILinkPathEditor applyChangeBoundsRequest(final ChangeBoundsRequest request, boolean isSimulation) {
        if (isSimulation) {
            return simulateChangeBoundsRequest(request);
        } else {
            return adaptToNewNodeBounds();
        }
        
    }

    /**
     * Adapt the current {@link #getState()} to the new {@link #getConnectionEditPart()} source and target bounds.
     * <p>
     * <h2>Usage:</h2><ol>
     * <li>call {@link #createFrozenStateCopy()} and keep the returned editor
     * <li>Move/resize one or both nodes
     * <li>On the editor copy:
     * <li>Call {@link #adaptToNewNodeBounds()} .
     * <li>then {@link #applyStateToConnection()}
     * </ol>
     * @return this instance
     */
    @objid ("65e7e4d6-999a-4543-a380-29b20fec91de")
    public ILinkPathEditor adaptToNewNodeBounds() {
        anchorChangedEditor.init(this.editor).adaptToNewNodeBounds();
        return this;
    }

    /**
     * Apply a move/resize request to the edited state.
     * <p>
     * The request may involve one or more of:
     * <ul>
     * <li> the source node
     * <li> the target node
     * <li> a parent of the source node
     * <li> a parent of the target node
     * <li> the connection
     * </ul>
     * Assumes the source and target nodes are still at their initial location.
     * @param request the move/resize request
     * @return this instance to chain calls.
     */
    @objid ("d5a8b110-fd1f-4509-8d1f-49ba02fe09d6")
    public ILinkPathEditor simulateChangeBoundsRequest(final ChangeBoundsRequest request) {
        anchorChangedEditor.init(this.editor).simulateChangeBoundsRequest(request);
        return this;
    }

    @objid ("e32ae6e0-6f54-43d3-b6b3-5efb5a29b3a8")
    @Override
    public void applyStateToConnection() {
        this.editor.applyStateToConnection();
    }

    @objid ("6ad065f4-3d54-4052-a6be-e4084bcab6ea")
    public AutoOrthoLinkPathEditor1 forConnection(ConnectionEditPart editPart) {
        this.editor.init(editPart);
        return this;
    }

    @objid ("09a6cc29-13ce-4d7f-ab41-0ad0652c57e9")
    @Override
    public ILinkPathEditor from(ConnectionEditPart connectionEditPart) {
        this.editor.init(connectionEditPart);
        return this;
    }

    @objid ("8475cbec-30b8-4b55-b51c-1216586f6354")
    @Override
    public ILinkPathEditor from(ConnectionEditPart connectionEditPart, ConnectionState initState) {
        this.editor.init(connectionEditPart, initState);
        return this;
    }

    @objid ("005c1b8a-1b37-495d-aa30-6c27bfc9bc5d")
    @Override
    public ConnectionState getState() {
        return this.editor.getView().getState();
    }

    @objid ("ccf0008e-bacb-43fd-bf51-d69f199f402a")
    @Override
    public ILinkPathEditor moveBendPoint(int pointIndex, Point newLocation) {
        ConnectionView connView = this.editor.getView();
        MPrecisionPoint previousPoint = connView.getPoint(TMP1, pointIndex - 1, true);
        MPrecisionPoint curPoint = connView.getPoint(TMP2, pointIndex, true);
        MPrecisionPoint nextPoint = connView.getPoint(TMP3, pointIndex + 1, true);
        
        Direction previousDir = Direction.getMajor(curPoint, previousPoint);
        Direction nextDir = Direction.getMajor(curPoint, nextPoint);
        
        OrientedAccessors access = AxisAccessor.forSegment(previousPoint, curPoint);
        
        curPoint.setLocation(newLocation);
        
        if (true) {
            // Try to align previous and next point to newLocation
        
            // Previous point
            if (!previousPoint.isFixed()) {
                TMP4.setLocation(previousPoint);
                access.across.setCoord(previousPoint, newLocation);
                fixLocation(connView, previousPoint, TMP4, pointIndex - 2, access.across);
                this.editor.setPoint(pointIndex - 1, previousPoint, false, null);
        
                access.across.setCoord(curPoint, previousPoint);
            }
        
            // Next point
            if (!nextPoint.isFixed()) {
                TMP4.setLocation(nextPoint);
                access.along.setCoord(nextPoint, newLocation);
                fixLocation(connView, nextPoint, TMP4, pointIndex + 2, access.along);
                this.editor.setPoint(pointIndex + 1, nextPoint, false, null);
        
                access.along.setCoord(curPoint, nextPoint);
            }
        }
        
        
        // Moved point.
        if (false) {
            // Align moved point to moved previous and next point
            access.across.setCoord(curPoint, previousPoint);
            access.along.setCoord(curPoint, nextPoint);
        } else {
            // Move moved point to requested location
            curPoint.setLocation(newLocation);
            snapToPoint(curPoint, previousPoint);
            snapToPoint(curPoint, nextPoint);
        
            if (false) {
            MPrecisionPoint p6 = new MPrecisionPoint();
            for (int i=0, stop = connView.getTargetAnchorIndex(); i<=stop; i++) {
                if (i < pointIndex -1 || i > pointIndex+1) {
                    snapToPoint(curPoint, connView.getPoint(p6, i, true));
                }
            }
            }
        }
        
        this.editor.setPoint(pointIndex, curPoint, false, true);
        
        if (false) {
            // Insert one segment if previous and next point not aligned.
            //  => Does not give good results.
            if (! access.along.coordEquals(nextPoint, curPoint)) {
                MPoint newNext1 = new MPoint(curPoint, false);
                MPoint newNext2 = new MPoint(nextPoint, false);
                GeomUtils.translate(newNext1, nextDir, 20);
                access.along.setCoord(newNext2, nextPoint);
                access.across.setCoord(newNext2, newNext1);
                this.editor.getView().insertPoint(pointIndex + 1, newNext2);
                this.editor.getView().insertPoint(pointIndex + 1, newNext1);
            }
        
            if (! access.across.coordEquals(previousPoint, curPoint)) {
                MPoint newPrev1 = new MPoint(curPoint, false);
                MPoint newPrev2 = new MPoint(previousPoint, false);
                GeomUtils.translate(newPrev1, previousDir, 20);
        
                access.across.setCoord(newPrev2, previousPoint);
                access.along.setCoord(newPrev2, newPrev1);
        
                this.editor.getView().insertPoint(pointIndex , newPrev1);
                this.editor.getView().insertPoint(pointIndex , newPrev2);
            }
        } else if (true) {
            // Insert one bend point if previous and next point not aligned,
            // and let router fix the remaining
            if (! access.along.coordEquals(nextPoint, curPoint)) {
                MPoint newNext1 = new MPoint(curPoint, false);
                GeomUtils.translate(newNext1, nextDir, 20);
                this.editor.getView().insertPoint(pointIndex + 1, newNext1);
            }
        
            if (! access.across.coordEquals(previousPoint, curPoint)) {
                MPoint newPrev1 = new MPoint(curPoint, false);
                GeomUtils.translate(newPrev1, previousDir, 20);
        
                this.editor.getView().insertPoint(pointIndex , newPrev1);
            }
        }
        
        
        this.editor.fixWithRouter(false);
        return this;
    }

    @objid ("b5939502-105b-454c-82d1-9f3b80fdbfe1")
    private static <T extends Point> T snapToPoint(T edited, Point reference) {
        if (Math.abs(edited.x() - reference.x()) < AutoOrthoConstants.SNAP_DIST) {
            edited.setX(reference.x());
        }
        if (Math.abs(edited.y() - reference.y()) < AutoOrthoConstants.SNAP_DIST) {
            edited.setY(reference.y());
        }
        return edited;
    }

    /**
     * Insert a bend point in the routing constraint so that the link stays orthogonal.
     * @param i the index of the point in the connection points. Must be 1 <= i <= routing.size()
     * @param access axis accessors
     */
    @objid ("c3bed9e1-87e9-4a23-af5d-1f15adf4d54b")
    private void insertOrthoBendPointBefore(int i, Point p1, Point p2, OrientedAccessors access, boolean reversed) {
        MPrecisionPoint newLoc = TMP5;
        if (reversed) {
            access.along.setCoord(newLoc, p2);
            access.across.setCoord(newLoc, p1);
        } else {
            access.across.setCoord(newLoc, p2);
            access.along.setCoord(newLoc, p1);
        }
        this.editor.getView().getConnection().translateToRelative(newLoc);
        this.editor.getView().getState().getMPoints().add(i - 1, new MPoint(newLoc, false));
        
    }

    @objid ("54d4aff0-c3e7-48c2-abcd-aa7545762595")
    @Override
    public ILinkPathEditor moveSegment(int pointIndex, Point newLocation) {
        SegmentMover.moveSegment(this.editor, pointIndex, newLocation);
        return this;
    }

    @objid ("10ffba90-142d-4bcf-bf24-517f71e35461")
    @Override
    public ILinkPathEditor setSourceAnchor(ConnectionAnchor newAnchor) {
        doSetSourceAnchor(this.editor, newAnchor);
        
        this.editor.fixWithRouter(false);
        return this;
    }

    @objid ("cc08ffa7-cf0a-4fdc-a799-db7fb1ae74a9")
    @Override
    public ILinkPathEditor setTargetAnchor(ConnectionAnchor newAnchor) {
        doSetTargetAnchor(this.editor, newAnchor);
        
        this.editor.fixWithRouter(false);
        return this;
    }

    /**
     * Fix the location of the given point against the connection point at given index.
     * <p>
     * Fix is done by bouncing the point back from manual point, or snap to the point if automatic.
     * @param editedAbsLocation the point in absolute coordinates
     * @param startAbsLocation the initial point location, to bounce it back if needed
     * @param pointIndex the reference point index
     * @return true if the point was modified, false if kept as is.
     */
    @objid ("64e83db4-17e2-4e69-bca3-e385ee0f1385")
    static boolean fixLocation(ConnectionView connView, MPrecisionPoint editedAbsLocation, Point startAbsLocation, int pointIndex, AxisAccessor access) {
        if (pointIndex < 0 || pointIndex > connView.getTargetAnchorIndex()) {
            // invalid index, should not happen, ignore
            return false;
        }
        
        // load reference point given by pointIndex
        MPrecisionPoint refPoint = TMP5;
        connView.getPoint(refPoint, pointIndex, true);
        
        if (pointIndex == 0 ) {
            return access.bounceFromRect(editedAbsLocation, connView.getAnchorBounds().source);
        } else if (pointIndex == connView.getTargetAnchorIndex()) {
            return access.bounceFromRect(editedAbsLocation, connView.getAnchorBounds().target);
        } else {
            if (false && access.enforceMinDistance(editedAbsLocation, startAbsLocation, refPoint)) {
                return true;
            }
            return access.snapToPoint(editedAbsLocation, refPoint);
        }
        
    }

    /**
     * Create a test connection to test the path with the given constraint and anchors is good and not too nearest from mouse cursor than the initial connection.
     * @param mouseAbsLoc the mouse position for comparison
     * @param initView the connection to test than modify
     * @param testState the new source anchor, target anchor, routing constrinat
     * @return true if the new constraint were applied, false if the initial connection is better.
     */
    @objid ("b27bda06-9520-433c-8ec7-9a2a9da5990b")
    protected static boolean applyIfConnectionBetter(Point mouseAbsLoc, ConnectionView initView, ConnectionState testState) {
        ConnectionView testView = new ConnectionView().init(initView.getConnection(), testState);
        
        if (!testView.isValidPath()) {
            return false;
        }
        
        PointList testPoints = testView.toPointList(new PointList(testView.cardPoints()));
        
        Point initMidPoint = initView.toPointList(new PointList()).getMidpoint();
        Point testMidPoint = testPoints.getMidpoint();
        
        double d1 = initMidPoint.getDistance(mouseAbsLoc);
        double d2 = testMidPoint.getDistance(mouseAbsLoc);
        
        // System.out.printf("d1=%f, straight dist=%f, newLocation=%s, middleP=%s, new mid point=%s %n", d1, d2, newRelLocation, middlePoint, midpoint);
        if (isPointNearConnection(mouseAbsLoc, testPoints) || d2 < d1) {
            // Path is good , validate changes
            testState.applyTo(initView.getState());
            return true;
        }
        return false;
    }

    @objid ("7f3f89a8-a3a4-4470-b6dd-98091011600b")
    protected static void doSetSourceAnchor(ConnectionEditor editor, ConnectionAnchor newAnchor) {
        ConnectionView connView = editor.getView();
        MPrecisionPoint startPoint = connView.getPoint(TMP2, 0, true);
        MPrecisionPoint endPoint = connView.getPoint(TMP1, 1, true);
        
        // Move end point
        AxisAccessor.forSegment(startPoint, endPoint).across.setCoord(endPoint, newAnchor.getReferencePoint());
        
        if (connView.cardPoints() > 2) {
            connView.setPoint(1, endPoint);
        } else {
            // straight line connection
            connView.getState().setTargetAnchor(editor.requestTargetAnchor(endPoint, true));
        }
        
        connView.getState().setSourceAnchor(newAnchor);
        
    }

    @objid ("bb6d710c-ca35-4d51-94de-53db3afed8ae")
    protected static void doSetTargetAnchor(ConnectionEditor editor, ConnectionAnchor newAnchor) {
        ConnectionView view = editor.getView();
        
        int constraintSize = view.cardPoints();
        int lastIndex = view.getTargetAnchorIndex();
        MPrecisionPoint startPoint = view.getPoint(TMP2, lastIndex, true);
        MPrecisionPoint endPoint = view.getPoint(TMP1, lastIndex - 1, true);
        
        // Move end point
        AxisAccessor.forSegment(startPoint, endPoint).across.setCoord(endPoint, newAnchor.getReferencePoint());
        
        if (constraintSize > 2) {
            view.setPoint(lastIndex - 1, endPoint);
        } else {
            // straight line connection
            view.getState().setSourceAnchor(editor.requestSourceAnchor(endPoint, true));
        }
        
        view.getState().setTargetAnchor(newAnchor);
        
    }

    @objid ("87abc01d-311b-4fa3-b493-0cebd01edef6")
    @Override
    protected ConnectionEditPart getConnectionEditPart() {
        return this.editor.getConnectionEditPart();
    }

    /**
     * Tells whether the given point is near the connection polyline, with some margin.
     * @param relLocation a location coordinates relative to the connection
     * @param connection a connection
     * @return true if the point touches or is near the connection.
     */
    @objid ("67a3697a-a5fa-4cc5-b7ae-1f41d0ffc117")
    protected static boolean isPointNearConnection(Point relLocation, PointList connection) {
        return connection.polylineContainsPoint(
                relLocation.x(),
                relLocation.y(),
                AutoOrthogonalRouterAlgorithm.DEFAULT_MARGIN);
        
    }

    @objid ("a06454b4-3b74-4252-a513-dc29ba466911")
    @Override
    public ILinkPathEditor createFrozenStateCopy() {
        AutoOrthoLinkPathEditor1 ret = new AutoOrthoLinkPathEditor1();
        ConnectionState frozenState = this.editor.createFrozenState();
        ret.from(getConnectionEditPart(), frozenState);
        return ret;
    }

    @objid ("e19f5841-f5af-4b2d-b6ab-4a6f0118c70c")
    @SuppressWarnings ("unqualified-field-access")
    protected static class SegmentMover {
        @objid ("6a16ed44-c327-4849-ba4d-df5d7b024b35")
        private boolean moveBlocked;

        @objid ("99357aee-9351-402a-8aca-d4b1de604664")
        private final MPrecisionPoint origStartPoint = new MPrecisionPoint();

        @objid ("ea94a97a-b8bb-435c-99b0-198b21050a51")
        private final MPrecisionPoint origEndPoint = new MPrecisionPoint();

        @objid ("a860e881-a754-42b3-8dae-8fa3a4077e25")
        private final MPrecisionPoint newStartPoint = new MPrecisionPoint();

        @objid ("0abfed08-905b-4920-b981-4832cc2e1363")
        private final MPrecisionPoint newEndPoint = new MPrecisionPoint();

        @objid ("1883865c-3279-417b-a90f-7df71d622f6f")
        private static final MPrecisionPoint TMP_PREV = new MPrecisionPoint();

        @objid ("319de0fe-ea6d-485e-a3b7-d7f3392784a1")
        private static final MPrecisionPoint TMP_NEXT = new MPrecisionPoint();

        @objid ("ff45f983-29b8-450c-b553-c13d9cc7db8b")
        private ConnectionEditor editor;

        @objid ("11eb0af6-ab46-4248-a350-95a2ce0d69e6")
        private OrientedAccessors axis;

        @objid ("0587ef83-06ff-4683-b08e-06971d96adb2")
        public void moveSegment(ConnectionEditor aeditor, int pointIndex, Point newLocation) {
            init(aeditor, pointIndex, newLocation);
            
            int nbConstraints = this.editor.getView().cardPoints() - 2;
            
            if (nbConstraints == 0) {
                moveStraightLine(newLocation);
            } else if (pointIndex == 0) {
                // first segment : call move source anchor
                moveFirstSegment(newLocation);
            } else if (pointIndex >= this.editor.getView().getTargetAnchorIndex() - 1) {
                // last segment : move target anchor
                moveLastSegment(pointIndex, newLocation);
            } else if (pointIndex == 1 && isUConnection()) {
                // U like figure and moving the central segment
                moveUCentralSegment(newLocation);
                // if still 2 bend points try to make connection straight
                int newNbConstraints = this.editor.getView().cardPoints() - 2;
                if (newNbConstraints == 2) {
                    makeStraightConnectionIfBetter(newLocation);
                }
            } else {
                moveIntermediateSegment(pointIndex, newLocation);
            }
            
        }

        /**
         * @param pointIndex the point index
         * @param newLocation the mouse location
         */
        @objid ("96111c3a-ef7e-4274-ba61-447857a4fa29")
        private void moveLastSegment(int pointIndex, Point newLocation) {
            AnchorBounds anchorBounds = this.editor.getView().getAnchorBounds();
            
            this.newEndPoint.setValues(this.origEndPoint);
            this.axis.across.setCoord(this.newEndPoint, newLocation);
            if (this.axis.across.isCoordContained(newLocation, anchorBounds.target)) {
                ConnectionAnchor anchor = this.editor.requestTargetAnchor(this.newEndPoint, true);
                changeTargetAnchor(anchor);
            } else {
                // if newLocation outside node bounds, add a segment
                newLocation = newLocation.getCopy();
                if (this.axis.along.isCoordContained(newLocation, anchorBounds.source)) {
                    this.axis.across.bounceFromRect(newLocation, anchorBounds.source);
                }
            
                this.axis.across.bounceFromRect(newLocation, anchorBounds.target);
            
                // Move bend point N
                this.newStartPoint.setValues(this.origStartPoint);
                this.axis.across.setCoord(this.newStartPoint, newLocation);
                this.editor.setPoint(pointIndex, this.newStartPoint, false, null);
            
                // Request a new anchor on perpendicular face
                this.axis.along.setCoord(this.newEndPoint, anchorBounds.target.getCenter());
                ConnectionAnchor anchor = this.editor.requestTargetAnchor(this.newEndPoint, false);
                this.editor.getView().getState().setTargetAnchor(anchor);
            
                // Insert an orthogonal bend point
                insertOrthoBendPointBefore(pointIndex + 1, this.axis, true);
            
            }
            
        }

        /**
         * @param newLocation the mouse location
         */
        @objid ("0b06ad7b-c8f8-4bec-b614-08c6704b44e8")
        private void moveFirstSegment(Point newLocation) {
            this.newStartPoint.setValues(this.origStartPoint);
            this.axis.across.setCoord(this.newStartPoint, newLocation);
            AnchorBounds anchorBounds = this.editor.getView().getAnchorBounds();
            
            if (this.axis.across.isCoordContained(newLocation, anchorBounds.source)) {
                // Mouse xcoord inside node bounds
                // first segment : call move source anchor
            
                ConnectionAnchor anchor = this.editor.requestSourceAnchor(this.newStartPoint, true);
                changeSourceAnchor(anchor);
            } else {
                // if newLocation outside node bounds, add a segment
            
                // Bounce mouse back from nodes
                newLocation = newLocation.getCopy();
                if (this.axis.along.isCoordContained(newLocation, anchorBounds.target)) {
                    this.axis.across.bounceFromRect(newLocation, anchorBounds.target);
                }
            
                this.axis.across.bounceFromRect(newLocation, anchorBounds.source);
            
                // Request a new anchor on perpendicular face
                this.axis.along.setCoord(this.newStartPoint, anchorBounds.source.getCenter());
                ConnectionAnchor anchor = this.editor.requestSourceAnchor(this.newStartPoint, false);
                this.editor.getView().getState().setSourceAnchor(anchor);
            
                // Move bend point 1
                this.newEndPoint.setValues(this.origEndPoint);
                this.axis.across.setCoord(this.newEndPoint, newLocation);
            
                this.editor.setPoint(1, this.newEndPoint, false, null);
            
                // Insert an orthogonal bend point
                insertOrthoBendPointBefore(1, this.axis, false);
            
            }
            
        }

        /**
         * Insert a bend point in the routing constraint so that the link stays orthogonal.
         * @param i the index of the point in the connection points. Must be 1 <= i <= routing.size()
         * @param access must match the segment [i-1, i] orientation
         */
        @objid ("4a9a0cae-9065-4dce-b035-b37390085b9d")
        private void insertOrthoBendPointBefore(int i, OrientedAccessors access, boolean reversed) {
            this.editor.getView().getPoint(TMP1, i - 1, true);
            this.editor.getView().getPoint(TMP2, i, true);
            insertOrthoBendPointBefore(i, TMP1, TMP2, access, reversed);
            
        }

        /**
         * Insert a bend point in the routing constraint so that the link stays orthogonal.
         * @param i the index of the point in the connection points. Must be 1 <= i <= routing.size()
         * @param access axis accessors
         */
        @objid ("6bc3a3ea-f05c-4d4c-b3d0-3fcdde4ed6db")
        private void insertOrthoBendPointBefore(int i, Point p1, Point p2, OrientedAccessors access, boolean reversed) {
            if (reversed) {
                access.along.setCoord(TMP3, p2);
                access.across.setCoord(TMP3, p1);
            } else {
                access.across.setCoord(TMP3, p2);
                access.along.setCoord(TMP3, p1);
            }
            this.editor.getView().getConnection().translateToRelative(TMP3);
            this.editor.getView().getState().getMPoints().add(i - 1, new MPoint(TMP3, false));
            
        }

        /**
         * A U connection has two bend points and looks like a U.
         * @return true if the connection looks like a U.
         */
        @objid ("a0b84f10-2b6e-4c61-a6f5-1f8f3810ba02")
        private boolean isUConnection() {
            ConnectionView view = this.editor.getView();
            int nbPoints = view.cardPoints();
            if (nbPoints != 4) {
                return false;
            }
            
            if (false) {
                return view.getDirectionFromSource()==view.getDirectionFromTarget();
            } else {
                MPrecisionPoint p1 = view.getSourceLocation(TMP_PREV, true);
                MPrecisionPoint p2 = view.getTargetLocation(TMP_NEXT, true);
                return p1.x() == p2.x() || p1.y() == p2.y() ;
            }
            
        }

        /**
         * General case : move intermediate segment
         * @param pointIndex the segment first point index
         * @param newLocation the mouse location
         */
        @objid ("a2e12194-dc50-4e3b-b4ec-9aafa66a506c")
        private void moveIntermediateSegment(int pointIndex, Point newLocation) {
            AxisAccessor acrossAccess = this.axis.across;
            
            this.newStartPoint.setValues(this.origStartPoint);
            this.newEndPoint.setValues(this.origEndPoint);
            
            // Move both points
            acrossAccess.setCoord(this.newStartPoint, newLocation);
            acrossAccess.setCoord(this.newEndPoint, newLocation);
            
            ConnectionView view = editor.getView();
            boolean snapPrevious = fixLocation(view, this.newStartPoint, this.origStartPoint, pointIndex - 1, acrossAccess);
            boolean snapNext = fixLocation(view, this.newEndPoint, this.origEndPoint, pointIndex + 2, acrossAccess);
            boolean conflict = snapPrevious && snapNext ;
            
            if (snapPrevious) {
                acrossAccess.setCoord(this.newEndPoint, this.newStartPoint);
            } else if (snapNext) {
                acrossAccess.setCoord(this.newStartPoint, this.newEndPoint);
            }
            
            this.editor.setPoint(pointIndex, this.newStartPoint, true, true);
            this.editor.setPoint(pointIndex + 1, this.newEndPoint, true, null);
            
            if (conflict) {
                // May be a bounce conflict because of intersection with both nodes
                this.editor.fixWithRouter(true);
            }
            
        }

        @objid ("46a8b2e5-0aa1-4511-8ecd-3e242aae494a")
        private void moveStraightLine(Point newLocation) {
            ConnectionState connState = this.editor.getView().getState();
            AnchorBounds anchorBounds = this.editor.getView().getAnchorBounds();
            
            // Straight line from source to target, move both anchors
            this.newStartPoint.setValues(this.origStartPoint);
            this.newEndPoint.setValues(this.origEndPoint);
            
            boolean locInSource = this.axis.across.isCoordContained(newLocation, anchorBounds.source);
            boolean locInTarget = this.axis.across.isCoordContained(newLocation, anchorBounds.target);
            
            this.axis.across.setCoord(this.newStartPoint, newLocation);
            this.axis.across.setCoord(this.newEndPoint, newLocation);
            if (!locInSource) {
                this.axis.along.setCoord(this.newStartPoint, anchorBounds.source.getCenter());
            }
            if (!locInTarget) {
                this.axis.along.setCoord(this.newEndPoint, anchorBounds.target.getCenter());
            }
            
            ConnectionAnchor srcAnchor = this.editor.requestSourceAnchor(this.newStartPoint, locInSource);
            ConnectionAnchor targetAnchor = this.editor.requestTargetAnchor(this.newEndPoint, locInTarget);
            
            connState.setSourceAnchor(srcAnchor);
            connState.setTargetAnchor(targetAnchor);
            
            this.newStartPoint.setLocation(srcAnchor.getLocation(targetAnchor.getReferencePoint()));
            this.newEndPoint.setLocation(targetAnchor.getLocation(srcAnchor.getReferencePoint()));
            
            Connection connection = this.editor.getView().getConnection();
            List<MPoint> routingConstraint = connState.getMPoints();
            
            if (locInSource && locInTarget) {
                boolean makeStep = !this.axis.across.coordEquals(this.newStartPoint, this.newEndPoint);
                if (makeStep) {
                    // Make stair step connection
                    this.axis.across.setCoord(TMP1, this.newStartPoint);
                    this.axis.across.setCoord(TMP2, this.newEndPoint);
                    this.axis.along.setCoord(TMP1, newLocation);
                    this.axis.along.setCoord(TMP2, newLocation);
            
                    connection.translateToRelative(TMP1);
                    connection.translateToRelative(TMP2);
            
                    routingConstraint.add(new MPoint(TMP1, false));
                    routingConstraint.add(new MPoint(TMP2, false));
                }
            } else if (!locInSource && locInTarget) {
                // move target anchor to perpendicular face
                TMP1.setLocation(this.newStartPoint);
                this.axis.across.setCoord(TMP1, this.newEndPoint);
                this.axis.across.bounceFromRect(TMP1, anchorBounds.source);
            
                targetAnchor = this.editor.requestTargetAnchor(TMP1, false);
                connState.setTargetAnchor(targetAnchor);
                this.newEndPoint.setLocation(targetAnchor.getLocation(srcAnchor.getReferencePoint()));
                this.axis.across.setCoord(TMP1, this.newEndPoint);
            
                this.newStartPoint.setLocation(TMP1);
                connection.translateToRelative(TMP1);
                routingConstraint.add(new MPoint(TMP1, false));
            } else if (locInSource && !locInTarget) {
                TMP1.setLocation(this.newEndPoint);
                this.axis.across.setCoord(TMP1, this.newStartPoint);
                this.axis.across.bounceFromRect(TMP1, anchorBounds.target);
            
                srcAnchor = this.editor.requestSourceAnchor(TMP1, false);
                connState.setSourceAnchor(srcAnchor);
                this.newStartPoint.setLocation(srcAnchor.getLocation(targetAnchor.getReferencePoint()));
                this.axis.across.setCoord(TMP1, this.newStartPoint);
            
                this.newEndPoint.setLocation(TMP1);
                connection.translateToRelative(TMP1);
                routingConstraint.add(new MPoint(TMP1, false));
            } else {
                // !locInSource && ! locInTarget : make U connection
                TMP1.setLocation(this.newStartPoint);
                TMP2.setLocation(this.newEndPoint);
                TMP3.setLocation(newLocation);
            
                this.axis.across.bounceFromRect(TMP3, anchorBounds.source);
                this.axis.across.bounceFromRect(TMP3, anchorBounds.target);
                this.axis.across.setCoord(TMP1, TMP3);
                this.axis.across.setCoord(TMP2, TMP3);
            
                connection.translateToRelative(TMP1);
                connection.translateToRelative(TMP2);
            
                routingConstraint.add(new MPoint(TMP1, false));
                routingConstraint.add(new MPoint(TMP2, false));
            }
            
        }

        @objid ("259a80ab-b38a-4fbf-9c3c-c6917e493260")
        private void moveUCentralSegment(Point newAbsLocation) {
            final int pointIndex = 1;
            
            // assert (routingConstraint.size()==2);
            
            ConnectionAnchor srcAnchor;
            ConnectionAnchor targetAnchor;
            
            this.newStartPoint.setValues(this.origStartPoint);
            this.newEndPoint.setValues(this.origEndPoint);
            
            this.axis.across.setCoord(this.newStartPoint, newAbsLocation);
            this.axis.across.setCoord(this.newEndPoint, newAbsLocation);
            
            srcAnchor = this.editor.requestSourceAnchor(this.newStartPoint, false);
            targetAnchor = this.editor.requestTargetAnchor(this.newEndPoint, false);
            
            ConnectionView view = this.editor.getView();
            ConnectionState connState = view.getState();
            
            connState.setSourceAnchor(srcAnchor);
            connState.setTargetAnchor(targetAnchor);
            
            fixLocation(view, this.newStartPoint, this.origStartPoint, pointIndex - 1, this.axis.across);
            fixLocation(view, this.newEndPoint, this.origEndPoint, pointIndex + 2, this.axis.across);
            
            this.editor.setPoint(pointIndex, this.newStartPoint, true, true);
            this.editor.setPoint(pointIndex + 1, this.newEndPoint, true, null);
            
        }

        @objid ("4bb144eb-3315-4444-acd8-b7a810570ac4")
        private void setConstrainedPoint(int pointIndex, Point absPoint, boolean sameFace, Boolean manual) {
            this.editor.setPoint(pointIndex, absPoint, sameFace, manual);
        }

        @objid ("59d912f9-7724-438d-814f-fd86f3a6c203")
        private void changeSourceAnchor(ConnectionAnchor newAnchor) {
            doSetSourceAnchor(editor, newAnchor);
            
            this.editor.fixWithRouter(false);
            
        }

        @objid ("46670d8f-dda9-44fc-9865-e1f7aed95832")
        private void changeTargetAnchor(ConnectionAnchor newAnchor) {
            doSetTargetAnchor(editor, newAnchor);
            
            this.editor.fixWithRouter(false);
            
        }

        @objid ("1df61a17-2bdc-49c3-9c5a-ac6c0ddfc515")
        protected void init(ConnectionEditor editor, int pointIndex, Point newLocation) {
            this.editor = editor;
            this.moveBlocked = false;
            
            editor.getView().getPoint(this.origStartPoint, pointIndex, true);
            editor.getView().getPoint(this.origEndPoint, pointIndex + 1, true);
            this.axis = AxisAccessor.forSegment(this.origStartPoint, this.origEndPoint);
            
        }

        /**
         * Transform a straight line connection to a U form connection that pass by newLocation.
         * @param newAbsLocation the point the segment should traverse, in absolute coordinates
         */
        @objid ("069a3ab6-c60e-4b02-b078-9ac68160e9c5")
        private void makeUConnectionIfBetter(Point newAbsLocation) {
            // if ( !isFirstSegmentParallel(this.connection) || !isLastSegmentParallel(this.connection))
            // return ;
            
            ConnectionView view = editor.getView();
            MPoint p1 = view.getPoint(new MPoint(), 0, false);
            MPoint p2 = view.getPoint(new MPoint(), 1, false);
            AxisAccessor access = AxisAccessor.forSegment(p1, p2).across;
            
            access.setCoord(p1, newAbsLocation);
            access.setCoord(p2, newAbsLocation);
            
            Connection connection = view.getConnection();
            connection.translateToRelative(p1);
            connection.translateToRelative(p2);
            
            List<MPoint> constraint = view.getState().getMPoints();
            constraint.add(p1);
            constraint.add(p2);
            
        }

        /**
         * change a U connection to a straight one if better
         * @param newAbsLocation the point the connection should traverse, in absolute coordinates
         */
        @objid ("0c3db854-25d7-4a9a-860d-ec5188db7c97")
        private boolean makeStraightConnectionIfBetter(Point newAbsLocation) {
            ConnectionAnchor srcAnchor;
            ConnectionAnchor targetAnchor;
            
            srcAnchor = editor.requestSourceAnchor(newAbsLocation, false);
            targetAnchor = editor.requestTargetAnchor(newAbsLocation, false);
            
            ConnectionState currentState = editor.getView().getState();
            if (srcAnchor.equals(currentState.getSourceAnchor()) && targetAnchor.equals(currentState.getTargetAnchor())) {
                return false;
            }
            
            ConnectionState testState = new ConnectionState();
            testState.setSourceAnchor(srcAnchor);
            testState.setTargetAnchor(targetAnchor);
            testState.setConstraint(new ArrayList<MPoint>(0));
            
            // Create a test connection to test its path is good and not too far from mouse cursor
            boolean isBetter = applyIfConnectionBetter(newAbsLocation, this.editor.getView(), testState);
            return isBetter;
        }

    }

    @objid ("e7ae99ce-ff56-4fd2-b3db-63e858862a08")
    protected static class AnchorChangedEditor {
        @objid ("c4be9243-e198-4c28-b109-d155b5c539fb")
        private ConnectionEditor editor;

        @objid ("ef5b0567-d3de-4ad6-88d4-7b63a8eff7cb")
        private static final PrecisionRectangle R1 = new PrecisionRectangle();

        @objid ("8cf0e455-4c7e-49f7-9339-993ca5766bff")
        public AnchorChangedEditor init(ConnectionEditor editor) {
            this.editor = editor;
            return this;
        }

        /**
         * Adapt the current {@link #getState()} to the new {@link #getConnectionEditPart()} source and target bounds.
         * <p>
         * <h2>Usage:</h2><ol>
         * <li>call {@link #createFrozenStateCopy()} and keep the returned editor
         * <li>Move/resize one or both nodes
         * <li>On the editor copy:
         * <li>Call {@link #adaptToNewNodeBounds()} .
         * <li>then {@link #applyStateToConnection()}
         * </ol>
         * @return this instance
         */
        @objid ("59408514-50e2-4a97-9ba9-42103e28b16c")
        public void adaptToNewNodeBounds() {
            ConnectionEditPart linkEditPart = this.editor.getConnectionEditPart();
            GraphicalEditPart linkSource = (GraphicalEditPart) linkEditPart.getSource();
            GraphicalEditPart linkTarget = (GraphicalEditPart) linkEditPart.getTarget();
            if (linkSource == null || linkTarget == null) {
                return ;
            }
            
            Connection connection = this.editor.getView().getConnection();
            
            AnchorBounds origNodeBounds = this.editor.getView().getAnchorBounds();
            AnchorBounds newNodesBounds = new AnchorBounds().fromConnectionAbs(connection);
            Point srcMoveDelta = newNodesBounds.source.getLocation().translate(origNodeBounds.source.getLocation().negate());
            Point targetMoveDelta = newNodesBounds.target.getLocation().translate(origNodeBounds.target.getLocation().negate());
            Point bendpointsMoveDelta = srcMoveDelta.equals(targetMoveDelta) ? srcMoveDelta : new Point(0,0);
            Dimension srcDelta = newNodesBounds.source.getSize().shrink(origNodeBounds.source.getSize());
            Dimension targetDelta = newNodesBounds.target.getSize().shrink(origNodeBounds.target.getSize());
            
            boolean sourceMoved   = !srcMoveDelta.equals(0, 0);
            boolean targetMoved   = !targetMoveDelta.equals(0, 0);
            boolean sourceResized = !srcDelta.equals(0, 0);
            boolean targetResized = !targetDelta.equals(0, 0);
            
            
            ConnectionState state = this.editor.getView().getState();
            
            // Deal with the source
            if (sourceMoved || sourceResized) {
                ConnectionAnchor previousAnchor = state.getSourceAnchor();
                ConnectionAnchor newAnchor = getAnchorAfterBoundsChanged(linkSource.getFigure(), previousAnchor, true);
                state.setSourceAnchor(newAnchor);
            } else {
                state.setSourceAnchor(connection.getSourceAnchor());
            }
            
            // Deal with the target
            if (targetMoved || targetResized) {
                ConnectionAnchor previousAnchor = state.getTargetAnchor();
                ConnectionAnchor newAnchor = getAnchorAfterBoundsChanged(linkTarget.getFigure(), previousAnchor, false);
                state.setTargetAnchor(newAnchor);
            } else {
                state.setTargetAnchor(connection.getTargetAnchor());
            }
            
            rerouteAfterNodeBoundschange(bendpointsMoveDelta, sourceMoved, targetMoved, sourceResized || targetResized);
            
        }

        /**
         * Apply a move/resize request to the edited state.
         * <p>
         * The request may involve one or more of:
         * <ul>
         * <li> the source node
         * <li> the target node
         * <li> a parent of the source node
         * <li> a parent of the target node
         * <li> the connection
         * </ul>
         * Assumes the source and target nodes are still at their initial location.
         * @param request the move/resize request
         */
        @objid ("0f85ee79-f909-4d7e-8ace-95d010de0215")
        public void simulateChangeBoundsRequest(final ChangeBoundsRequest request) {
            ConnectionEditPart linkEditPart = this.editor.getConnectionEditPart();
            GraphicalEditPart linkSource = (GraphicalEditPart) linkEditPart.getSource();
            GraphicalEditPart linkTarget = (GraphicalEditPart) linkEditPart.getTarget();
            if (linkSource == null || linkTarget == null) {
                return ;
            }
            
            Point srcMoveDelta = request.getMoveDelta();
            Point absMoveDelta = srcMoveDelta;
            Dimension absSizeDelta = request.getSizeDelta();
            boolean sourceInSet = ToolSelectionUtils.sourceInRequest(linkEditPart, request);
            boolean targetInSet = ToolSelectionUtils.targetInRequest(linkEditPart, request);
            
            ConnectionState state = this.editor.getView().getState();
            
            // Deal with the source
            if (sourceInSet) {
                ConnectionAnchor previousAnchor = state.getSourceAnchor();
                ConnectionAnchor newAnchor = getSimulatedAnchor(linkSource, linkTarget, request, previousAnchor, true);
                state.setSourceAnchor(newAnchor);
            }
            
            // Deal with the target
            if (targetInSet) {
                ConnectionAnchor previousAnchor = state.getTargetAnchor();
                ConnectionAnchor newAnchor = getSimulatedAnchor(linkSource, linkTarget, request, previousAnchor, false);
                state.setTargetAnchor(newAnchor);
            }
            
            rerouteAfterNodeBoundschange(absMoveDelta, sourceInSet, targetInSet, absSizeDelta.equals(0, 0));
            
        }

        @objid ("9dfb9f15-a253-4522-b723-1970a5406ee9")
        private void rerouteAfterNodeBoundschange(Point bendpointsDelta, boolean sourceMoved, boolean targetMoved, boolean sourceOrTargetResized) {
            ConnectionState origState = this.editor.getView().getState();
            ConnectionState state = new ConnectionState().init(origState) ; // copy state to allow replay the method in the debugger
            Connection connection = this.editor.getView().getConnection();
            
            if (sourceMoved && targetMoved && ! bendpointsDelta.equals(0, 0) && ! sourceOrTargetResized) {
                // Both source and target are being moved the same vector, move the link's points too.
                // Use a precision point to not loose precision during conversions.
                final MPrecisionPoint p = TMP1;
                for (MPoint bendpoint : state.getMPoints()) {
                    p.setLocation(bendpoint);
                    connection.translateToAbsolute(p);
                    p.translate(bendpointsDelta);
                    connection.translateToRelative(p);
                    bendpoint.setLocation(p);
                }
            } else {
                // All other cases: mainly only the source or target are being moved, or it is a resize.
            
                // 1) Delete first or last automatic points
                if (sourceMoved ) {
                    deleteFirstAutomaticBendPoints(state);
                }
            
                if (targetMoved)  {
                    deleteLastAutomaticBendPoints(state);
                }
            
                // 2) call the router again
                AutoOrthogonalRouter router = new AutoOrthogonalRouter()
                        .setCleanupManualPoints(false);
                List<MPoint> newConstraint = router.computeMPointRoute(connection, state);
            
                state.setConstraint(AutoOrthogonalRouter.routeToConstraint(newConstraint));
            
            }
            
            state.applyTo(origState);
            
        }

        @objid ("5f631896-ca05-4fa3-9b00-52e4dac910dc")
        private void deleteLastAutomaticBendPoints(ConnectionState state) {
            ListIterator<MPoint> it = state.getMPoints().listIterator(state.getMPoints().size());
            while (it.hasPrevious()) {
                if(it.previous().isFixed())
                    break;
                it.remove();
            }
            
        }

        @objid ("37b0f5d7-bfb5-4b48-bab6-fa54ebe2d8ee")
        private void deleteFirstAutomaticBendPoints(ConnectionState state) {
            for (Iterator<MPoint> it = state.getMPoints().iterator(); it.hasNext();) {
                MPoint p = it.next();
                if (p.isFixed())
                    break;
                it.remove();
            }
            
        }

        /**
         * Request an anchor at the same fraction of node size, after the node has been resized or moved.
         * @param nodeFigure the node figure that have been resized
         * @param previousAnchor the previous anchor
         * @param source whether a source or target anchor is requested
         * @return the new anchor
         */
        @objid ("71a09acc-8e79-465e-9ba9-afa42ce3eeb2")
        private ConnectionAnchor getAnchorAfterBoundsChanged(IFigure nodeFigure, ConnectionAnchor previousAnchor, boolean source) {
            if (nodeFigure instanceof Connection)
                return getAnchorAfterConnectionChanged((Connection) nodeFigure, previousAnchor, source);
            
            final ConnectionView view = this.editor.getView();
            final AnchorBounds anchorBounds = view.getAnchorBounds();
            final PrecisionRectangle oldBounds ;
            
            final Rectangle newBounds = R1.setBounds(getHandleBounds(nodeFigure));
            nodeFigure.translateToAbsolute(newBounds);
            
            final Point prevRelLoc;
            if (source) {
                oldBounds = anchorBounds.source;
                prevRelLoc = view.getSourceLocation(TMP1, previousAnchor, true);
            } else {
                oldBounds = anchorBounds.target;
                prevRelLoc = view.getTargetLocation(TMP1, previousAnchor, true);
            }
            
            Point newRelLoc = TMP2.setLocation(prevRelLoc)
                    .translate(-oldBounds.x(), -oldBounds.y())
                    .scale(
                            newBounds.preciseWidth() / oldBounds.preciseWidth(),
                            newBounds.preciseHeight() / oldBounds.preciseHeight())
                    .translate(newBounds.x(), newBounds.y());
            
            if (source) {
                return this.editor.requestSourceAnchor()
                        .withLocation(newRelLoc)
                        .withSameFaceAs(previousAnchor)
                        .withSameSliding(previousAnchor )
                        .withNodeFigure(nodeFigure)
                        .requestAnchor();
            } else {
                return this.editor.requestTargetAnchor()
                        .withLocation(newRelLoc)
                        .withSameFaceAs(previousAnchor)
                        .withSameSliding(previousAnchor )
                        .withNodeFigure(nodeFigure)
                        .requestAnchor();
            }
            
        }

        @objid ("7a0724b6-eca6-4337-8361-94546d696383")
        private static Rectangle getHandleBounds(IFigure nodeFigure) {
            if (nodeFigure instanceof HandleBounds) {
                return ((HandleBounds) nodeFigure).getHandleBounds();
            } else {
                return nodeFigure.getBounds();
            }
            
        }

        /**
         * Request an anchor at the same fraction of node size, after the Connection node has been changed.
         * <p>
         * Uses {@link ConnectionView#getConnection()} anchors to guess new location.
         * @param connectionNode the Connection node figure that have been edited
         * @param previousAnchor the previous anchor
         * @param source whether a source or target anchor is requested
         * @return the new anchor
         */
        @objid ("0415eea5-5a47-462d-abba-1f2adf377d32")
        private ConnectionAnchor getAnchorAfterConnectionChanged(Connection connectionNode, ConnectionAnchor previousAnchor, boolean source) {
            final ConnectionView view = this.editor.getView();
            
            final Point prevRelLoc;
            final Point newRelLoc;
            if (source) {
                prevRelLoc = view.getSourceLocation(TMP1, previousAnchor, true);
                newRelLoc = view.getSourceLocation(TMP2, view.getConnection().getSourceAnchor(), true);
                return this.editor.requestSourceAnchor()
                        .withLocation(newRelLoc)
                        .withSameFaceAs(previousAnchor)
                        .withSameSliding(previousAnchor)
                        .withNodeFigure(connectionNode)
                        .requestAnchor();
            } else {
                prevRelLoc = view.getTargetLocation(TMP1, previousAnchor, true);
                newRelLoc = view.getTargetLocation(TMP2, view.getConnection().getTargetAnchor(), true);
                return this.editor.requestTargetAnchor()
                        .withLocation(newRelLoc)
                        .withSameFaceAs(previousAnchor)
                        .withSameSliding(previousAnchor)
                        .withNodeFigure(connectionNode)
                        .requestAnchor();
            }
            
        }

        /**
         * @param sourceEp the source node edit part
         * @param targetEp the target node edit part
         * @param request the move/resize request
         * @param previousAnchor the anchor whose move is to simulate
         * @param isSource whether a source or target anchor is requested
         * @return the simulated anchor
         */
        @objid ("66d92da7-ebf1-4dbc-ab2e-dcba06c36fb2")
        private ConnectionAnchor getSimulatedAnchor(GraphicalEditPart sourceEp, GraphicalEditPart targetEp, ChangeBoundsRequest request, ConnectionAnchor previousAnchor, boolean isSource) {
            Point absMoveDelta = request.getMoveDelta();
            Point oldAnchorLoc = previousAnchor.getReferencePoint();
            Point newAnchorLoc = oldAnchorLoc.getTranslated(absMoveDelta);
            
            GraphicalEditPart nodeEditpart = isSource ? sourceEp : targetEp;
            ChangeBoundsFeedbackMap feedbacks = ChangeBoundsFeedbackMap.getOrDummy(request);
            IFigure feedbackFigure = feedbacks.getOrDefault(nodeEditpart, null);
            if (feedbackFigure != null) {
                return getAnchorAfterBoundsChanged(feedbackFigure, previousAnchor, isSource);
            } else if (nodeEditpart.getFigure() instanceof Connection){
                // Connections are their own feedback on our edit policies ==> request same anchor
                return getAnchorAfterConnectionChanged((Connection) nodeEditpart.getFigure(), previousAnchor, isSource);
            } else if (previousAnchor instanceof DirectionalAnchor) {
                // Keep the direction used last time, the current bounds are not OK to compute the actual direction
                DirectionalAnchor prevDirAnchor = (DirectionalAnchor) previousAnchor;
                prevDirAnchor.setLocation(newAnchorLoc);
                return prevDirAnchor;
            } else {
                // Here we have the source figure with its bounds before moving
                IFigure nodeFigure = nodeEditpart.getFigure();
                Rectangle nodeBounds = nodeFigure.getBounds().getCopy();
                nodeFigure.translateToAbsolute(nodeBounds);
                Direction sourceDirection = GeomUtils.getDirection(oldAnchorLoc, nodeBounds);
                DirectionalAnchor newAnchor = new DirectionalAnchor(newAnchorLoc, sourceDirection);
                return newAnchor;
            }
            
        }

    }

}
