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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;
import org.modelio.diagram.elements.core.figures.geometry.Orientation;
import org.modelio.diagram.elements.core.figures.routers.AnchorBounds;
import org.modelio.diagram.elements.core.figures.routers.ConnectionState;
import org.modelio.diagram.elements.core.link.IMPoint;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.link.MPrecisionPoint;

/**
 * Layer above {@link ConnectionState} and {@link AnchorBounds} with many helper methods.
 * <p>
 * Handles absolute and relative coordinates conversion by holding a {@link Connection}.
 * <p>
 * All methods take and return geometries in absolute coordinates.
 * <p>
 * All methods taking an index interpret it following:
 * <ul>
 * <li><b>0</b> is the source anchor,
 * <li><b>1</b> is the first bend point,
 * <li><b>{@link #getTargetAnchorIndex()} - 1</b> is the last bend point,
 * <li><b>{@link #getTargetAnchorIndex()}</b> is the target anchor.
 * </ul>
 * 
 * @since 5.0.2
 */
@objid ("0b403619-c209-4df3-83a8-6362b13b5c2a")
public class ConnectionView {
    /**
     * The edited connection state
     */
    @objid ("91f76163-2eb4-41e7-95d3-05ecc2d59e23")
    private final ConnectionState connectionState;

    /**
     * The connection extremity nodes bounds
     */
    @objid ("241b6b4b-508b-4c14-ab4a-553e5422e6a6")
    private final AnchorBounds anchorBounds;

    /**
     * The figure to handle coordinates conversions.
     */
    @objid ("4c364b57-7a6b-4d7a-946a-d3e78a35db62")
    private Connection connection;

    @objid ("c7b50b73-1e5e-45c3-9c0e-b43fad0b17f2")
    private static final MPoint TMP1 = new MPoint();

    @objid ("d81e2f2b-8abe-4530-ae06-d9f89a9df2c8")
    private static final MPoint TMP2 = new MPoint();

    @objid ("091dc02f-7d9a-4692-890f-31e2863aaec8")
    public  ConnectionView() {
        this.connectionState = new ConnectionState();
        this.anchorBounds = new AnchorBounds();
        
    }

    /**
     * Initialize this instance from the given Connection.
     * @param c the input connection
     * @return this instance
     */
    @objid ("044a91c7-3f86-4c5a-9f17-7e9e9adc7162")
    public ConnectionView init(Connection c) {
        this.connection = c;
        this.connectionState.init(c);
        refreshAnchorBounds();
        return this;
    }

    /**
     * Initialize this instance from the given {@link ConnectionState} and a {@link Connection} to make coordinate translations.
     * @param c the input connection
     * @param state the connection state
     * @return this instance
     */
    @objid ("f1809383-f42d-4e1c-b165-c28776b69321")
    public ConnectionView init(Connection c, ConnectionState state) {
        this.connection = c;
        this.connectionState.init(state);
        refreshAnchorBounds();
        return this;
    }

    /**
     * Compute again cached {@link #getAnchorBounds()}.
     * <p>
     * To call after having modified anchors in {@link #getState()} .
     * @return this instance
     */
    @objid ("4ec1a790-22a8-42fa-9c6e-d4feb32e5443")
    public ConnectionView refreshAnchorBounds() {
        this.anchorBounds.fromAnchors(
                this.connectionState.getSourceAnchor(), getPoint(TMP1, 1, true),
                this.connectionState.getTargetAnchor(), getPoint(TMP2, getTargetAnchorIndex()-1, true));
        return this;
    }

    /**
     * @return the index of the point at the target anchor.
     */
    @objid ("ddbf458f-b393-4eaa-8847-58cd51dd6850")
    public int getTargetAnchorIndex() {
        return this.connectionState.getMPoints().size() + 1;
    }

    /**
     * Get the position of the point at the given index from the anchors and routing constraint.
     * <p>
     * Fills output with the  point location in relative coordinates.
     * This method minimize the number of point allocations.
     * {@link IMPoint#setFixed(boolean)} is called with true for manual bend points and anchors, false for automatic anchors.
     * @param <P> the type of the passed point
     * @param output the point that will receive the point location in relative coordinates.
     * @param index the index of the point to modify in the points list.<br>
     * <li><b>0</b> is the source anchor,
     * <li><b>1</b> is the first bend point,
     * <li><b>c.size()</b> is the last bend point,
     * <li><b>c.size()+1</b> is the target anchor.
     * @param anchorIsfixed {@link IMPoint#setFixed(boolean)} is called with this value with anchors
     * @return output for convenience. the point location in relative coordinates.
     */
    @objid ("bcb809b3-9fa8-412d-92f1-5b90d4545d0f")
    public <P extends IMPoint<P>> P getPoint(P output, int index, boolean anchorIsfixed) {
        List<MPoint> constraint = this.connectionState.getMPoints();
        if (index == 0) {
            return getSourceLocation(output, anchorIsfixed);
        } else if (index == getTargetAnchorIndex()) {
            return getTargetLocation(output, anchorIsfixed);
        } else {
            output.setValues(constraint.get(index - 1));
            this.connection.translateToAbsolute(output);
            return output;
        }
        
    }

    /**
     * Insert a point at the given index.
     * <p>
     * The constraint list takes ownership of the point by reference.
     * @param index the point index. anchor indexes are illegal.
     * @param newPoint the point to insert
     * @throws IndexOutOfBoundsException if index < 0 or > target anchor index.
     */
    @objid ("5d5b6bbd-8a5d-4dfa-bbdb-a52796aa14be")
    public void insertPoint(int index, MPoint newPoint) throws IndexOutOfBoundsException {
        if (index < 0 || index > getTargetAnchorIndex())
            throw new IndexOutOfBoundsException(String.format("%d index out of [0..%d] bounds.",index, getTargetAnchorIndex()));
        
        getConnection().translateToRelative(newPoint);
        getState().getMPoints().add(index - 1, newPoint);
        
    }

    /**
     * Check the index is not an anchor index.
     * @see #getTargetAnchorIndex()
     * @param index the index to test
     * @throws IndexOutOfBoundsException if the index matches an anchor index
     */
    @objid ("95b2e083-2a71-461d-b8e1-08358c54b77d")
    private void checkIndexIsNotAnchor(int index) throws IndexOutOfBoundsException {
        if (index <= 0 || index >= getTargetAnchorIndex())
            throw new IndexOutOfBoundsException(String.format("%d is an anchor index.",index));
        
    }

    /**
     * Overwrite a constraint point at the given index.
     * @param index the point index. anchor indexes are illegal.
     * @param newPoint the point to insert. The point data is copied into the existing constraint point.
     */
    @objid ("8706f57a-2dce-49e0-9541-3f846ab22007")
    public void setPoint(int index, IMPoint<?> newPoint) {
        checkIndexIsNotAnchor(index);
        
        getConnection().translateToRelative(newPoint);
        getState().getMPoints().get(index - 1).setValues(newPoint);
        
    }

    /**
     * Get the location of the target point.
     * @param <P> the type of the point
     * @param output the point to fill
     * @param anchorIsfixed calls {@link IMPoint#setFixed(boolean)} with this value
     * @return the connection target point in absolute coordinates
     */
    @objid ("233a54e7-d7f9-4ad8-a0ca-2d946c320e00")
    public <P extends IMPoint<P>> P getTargetLocation(P output, boolean anchorIsfixed) {
        return getTargetLocation(output, getState().getTargetAnchor(), anchorIsfixed);
    }

    /**
     * Get the location of a target anchor candidate.
     * @param <P> the type of the point
     * @param output the point to fill
     * @param anchor the target anchor candidate
     * @param anchorIsfixed calls {@link IMPoint#setFixed(boolean)} with this value
     * @return the connection target point in absolute coordinates
     */
    @objid ("ab0c40f5-621d-4957-855d-b19e7138ca92")
    public <P extends IMPoint<P>> P getTargetLocation(P output, ConnectionAnchor anchor, boolean anchorIsfixed) {
        // Get target anchor.
        // hack : use output as ref to minimize allocations.
        P ref = output;
        List<MPoint> constraint = this.connectionState.getMPoints();
        if (constraint.isEmpty()) {
            ref.setLocation(this.connectionState.getSourceAnchor().getReferencePoint());
        } else {
            ref.setLocation(constraint.get(constraint.size() - 1));
            this.getConnection().translateToAbsolute(ref);
        }
        
        output.setLocation(anchor.getLocation(ref.asPoint()));
        output.setFixed(anchorIsfixed);
        return output;
    }

    /**
     * @param <P> the type of the point
     * @param output the point to fill
     * @param anchorIsfixed calls {@link IMPoint#setFixed(boolean)} with this value
     * @return the connection source point in absolute coordinates
     */
    @objid ("623392fd-29d5-49eb-ba59-726d489554fa")
    public <P extends IMPoint<P>> P getSourceLocation(P output, boolean anchorIsfixed) {
        // Get source anchor.
        return getSourceLocation(output, this.connectionState.getSourceAnchor(), anchorIsfixed);
    }

    /**
     * Get the location of a source anchor candidate.
     * @param <P> the type of the point
     * @param output the point to fill
     * @param anchor the source anchor to use
     * @param anchorIsfixed calls {@link IMPoint#setFixed(boolean)} with this value
     * @return output, containing the connection source point in absolute coordinates
     */
    @objid ("98c4c59b-fb4d-4219-bcc8-cf6d79edb03e")
    public <P extends IMPoint<P>> P getSourceLocation(P output, ConnectionAnchor anchor, boolean anchorIsfixed) {
        // Get source anchor.
        // hack : use output as ref to minimize allocations.
        P ref = output;
        List<MPoint> constraint = this.connectionState.getMPoints();
        if (constraint.isEmpty()) {
            ref.setLocation(this.connectionState.getTargetAnchor().getReferencePoint());
        } else {
            ref.setLocation(constraint.get(0));
            getConnection().translateToAbsolute(ref);
        }
        
        output.setLocation(anchor.getLocation(ref.asPoint()));
        output.setFixed(anchorIsfixed);
        return output;
    }

    /**
     * Get the orientation of the segment from index to index+1.
     * <p>
     * If index matches the target anchor returns the expected segment orientation from the target node with the current target anchor.
     * @param index the segment first point index
     * @return the orientation of the segment from the index point to index+1
     */
    @objid ("f1b97899-ae1b-4434-be04-9224368b1dca")
    public Orientation getOrientationToNext(int index) {
        if (index >= getTargetAnchorIndex()) {
            return getDirectionFromTarget().orientation();
        }
        
        getPoint(TMP1, index, true);
        getPoint(TMP2, index + 1, true);
        return Direction.getOrtho(TMP1, TMP2).orientation();
    }

    /**
     * @return the Direction of the source anchor
     */
    @objid ("7e51d99e-61ed-4d4d-906f-b18db644327c")
    public Direction getDirectionFromSource() {
        getSourceLocation(TMP1, true);
        return GeomUtils.getDirection(TMP1, getAnchorBounds().source);
    }

    /**
     * The direction from the target node to the target anchor.
     * @return the Direction the target anchor is pointing to
     */
    @objid ("df84c3e0-fc5d-4108-987b-931f223e9253")
    public Direction getDirectionFromTarget() {
        getTargetLocation(TMP1, true);
        return GeomUtils.getDirection(TMP1, getAnchorBounds().target);
    }

    /**
     * Test whether the path is orthogonal and does not intersect source and target nodes.
     * @return true only if the path does not need modifications.
     */
    @objid ("3df461c5-ca58-4f20-9159-b517380a7e2b")
    public boolean isValidPath() {
        return Validator.isValidPath(this);
    }

    /**
     * Compute the connections points into a given PointList.
     * @param out the PointList to overwrite.
     * @return a PointList with absolute coordinates.
     */
    @objid ("26c57eef-bbad-41c8-bc24-808488063a75")
    public PointList toPointList(PointList out) {
        out.removeAllPoints();
        int nb = cardPoints();
        MPrecisionPoint pp = new MPrecisionPoint();
        
        for (int i = 0; i < nb; i++) {
            getPoint(pp, i, true);
            out.addPoint(pp);
        }
        return out;
    }

    /**
     * Compute the connection points into a new  array list.
     * @param anchorAsManual IMPoint.setFixed(boolean) is called with this value with anchors
     * @return a List with absolute coordinates.
     */
    @objid ("19b43594-7615-4809-9a7b-d8728e337a37")
    public List<MPoint> toMPointList(boolean anchorAsManual) {
        int nb = cardPoints();
        ArrayList<MPoint> out = new ArrayList<>(nb);
        for (int i = 0; i < nb; i++) {
            out.add(getPoint(new MPoint(), i, anchorAsManual));
        }
        return out;
    }

    @objid ("dc6a89a1-cace-4a18-9e23-7392e6eae96e")
    @Override
    public String toString() {
        return String.format("%s [%n\tsource bounds=%s, %n\t target bounds=%s, %n\t absolute points=%s]",
                getClass().getSimpleName(),
                GeomUtils.toString(getAnchorBounds().source),
                GeomUtils.toString(getAnchorBounds().target),
                toMPointList(true));
        
    }

    /**
     * @return the number of points of the connection
     */
    @objid ("66e4b471-6595-4260-a025-21b1f782b1bc")
    public int cardPoints() {
        return getState().getMPoints().size() + 2;
    }

    /**
     * Get the orientation of the segment from index-1 to index.
     * <p>
     * If index is 0 returns the expected segment orientation from the source node with the current anchor.
     * @param index the index of the segment second point
     * @return the orientation of the segment from index-1 to index
     */
    @objid ("50b2dc25-6775-4422-ac0a-1291fe0e7adc")
    public Orientation getOrientationFromPrevious(int index) {
        if (index <= 0) {
            return getDirectionFromSource().orientation();
        }
        
        getPoint(TMP1, index, true);
        getPoint(TMP2, index - 1, true);
        return Direction.getOrtho(TMP1, TMP2).orientation();
    }

    /**
     * @return the wrapped {@link ConnectionState}.
     */
    @objid ("7a423018-8345-4723-a231-f9e74298f4e5")
    public ConnectionState getState() {
        return this.connectionState;
    }

    /**
     * @return the connection used to translate coordinates.
     */
    @objid ("8126163f-7037-4e61-be10-8a044aa169f4")
    public Connection getConnection() {
        return this.connection;
    }

    /**
     * @return the connected node bounds, in absolute coordinates .
     */
    @objid ("261a691d-53fb-48b8-9b46-095bba9f3e75")
    public AnchorBounds getAnchorBounds() {
        return this.anchorBounds;
    }

    /**
     * Services to validate a {@link ConnectionView}
     */
    @objid ("9b3f2176-0380-47e6-84e2-92247edb1b15")
    public static class Validator {
        @objid ("9b159fe5-8256-4d50-89e8-2f57627448aa")
        private static final PointList allPoints = new PointList();

        @objid ("5ef4deb5-5f0c-4e1d-b152-ef83aefd4b05")
        private  Validator() {
            // no instance
        }

        /**
         * Test whether the path is orthogonal and does not intersect source and target nodes.
         * @param connectionFig the connection figure
         * @return true only if the path does not need modifications.
         */
        @objid ("d9b84e6d-2004-4639-9f6f-b77f5cbf1c15")
        public static boolean isValidPath(ConnectionView connectionFig) {
            final PointList pointList = connectionFig.toPointList(allPoints);
            
            final MPoint p = new MPoint();
            final MPoint prev = new MPoint();
            
            final int nb = pointList.size();
            
            pointList.getPoint(prev, 0);
            
            for (int i = 1; i < nb; i++) {
                pointList.getPoint(p, i);
                if (p.x() != prev.x() && p.y() != prev.y()) {
                    return false;
                }
            
                prev.setLocation(p);
            }
            
            // check for intersections with source figure
            if (firstSegIntersectSource(connectionFig)) {
                return false;
            }
            
            if (!isFirstSegmentOrthogonal(connectionFig, pointList))
                return false;
            
            // check for intersections with target figure
            if (lastSegIntersectTarget(connectionFig)) {
                return false;
            }
            
            if (!isLastSegmentOrthogonal(connectionFig, pointList))
                return false;
            return true;
        }

        @objid ("90f63304-9fa7-450a-a9fd-61c8ddc0c9f8")
        private static boolean firstSegIntersectSource(ConnectionView connectionFig) {
            final PrecisionRectangle bounds = connectionFig.getAnchorBounds().source;
            if (bounds.width() < 5 && bounds.height() < 5)
                return false;
            return GeomUtils.segmentIntersects(
                    allPoints.getPoint(TMP1, 0),
                    allPoints.getPoint(TMP2, 1),
                    Rectangle.SINGLETON.setBounds(bounds).shrink(1, 1));
            
        }

        @objid ("23655cb2-1d8d-4ef0-8291-21c1cb65adbf")
        private static boolean lastSegIntersectTarget(ConnectionView connectionFig) {
            final PrecisionRectangle bounds = connectionFig.getAnchorBounds().target;
            if (bounds.width() < 5 && bounds.height() < 5)
                return false;
            
            int nb = allPoints.size();
            return GeomUtils.segmentIntersects(
                    allPoints.getPoint(TMP1, nb - 2),
                    allPoints.getPoint(TMP2, nb - 1),
                    Rectangle.SINGLETON.setBounds(bounds).shrink(1, 1));
            
        }

        /**
         * Check and report whether the path is orthogonal and does not intersect source and target nodes.
         * @param connView the connection figure
         * @return a string report or null if the path does not need modifications.
         */
        @objid ("45e6c890-be72-434a-a23d-48946fd9c3e6")
        public static String validate(ConnectionView connView) {
            StringBuilder s = new StringBuilder();
            AnchorBounds newAnchorBounds = new AnchorBounds()
                    .fromAnchors(connView.connectionState.getSourceAnchor(), connView.connectionState.getTargetAnchor())
                    .toRelative(connView.getConnection())
                    .expand(-1);
            connView.getConnection().translateToAbsolute(newAnchorBounds);
            
            final PointList pointList = connView.toPointList(allPoints);
            
            
            final MPoint p = new MPoint();
            final MPoint prev = new MPoint();
            
            final int nb = pointList.size();
            
            pointList.getPoint(prev, 0);
            
            for (int i = 1; i < nb; i++) {
                pointList.getPoint(p, i);
                if (p.x() != prev.x() && p.y() != prev.y()) {
                    s.append(String.format("- segment %d not orthogonal: %s - %s %n", i-1, prev, p));
                }
            
                prev.setLocation(p);
            }
            
            // check for intersections with source figure
            PrecisionRectangle sourceBounds = newAnchorBounds.source;
            if (firstSegIntersectSource(connView)) {
                dumpNodeIntersections(s, sourceBounds, pointList, "source");
            }
            
            if (!isFirstSegmentOrthogonal(connView, pointList))
                s.append(String.format("- first segment not orthogonal to source node: %s%n", GeomUtils.toString(sourceBounds)));
            
            
            // check for intersections with target figure
            PrecisionRectangle targetBounds = newAnchorBounds.target;
            if (lastSegIntersectTarget(connView)) {
                dumpNodeIntersections(s, targetBounds, pointList, "target");
            }
            
            if (!isLastSegmentOrthogonal(connView, pointList))
                s.append(String.format("- last segment not orthogonal to target node: %s%n", GeomUtils.toString(targetBounds)));
            
            if (s.length() > 0) {
                s.insert(0, String.format("Invalid connection path:%n"));
                s.append("- connection state:").append(connView).append('\n');
                return s.toString();
            } else {
                return null;
            }
            
        }

        @objid ("b67c964a-497f-49b2-b118-f224a3caf591")
        private static void dumpNodeIntersections(StringBuilder s, PrecisionRectangle bounds, final PointList pointList, String name) {
            final Point p = new Point();
            final Point prev = new Point();
            
            final int nb = pointList.size();
            Rectangle shrinkedBounds = bounds.getShrinked(1, 1);
            
                PointList subList = new PointList(2);
                subList.addPoint(0, 0);
                subList.addPoint(0, 0);
                for (int i=0; i< nb-1; i++) {
                    pointList.getPoint(prev, i);
                    pointList.getPoint(p, i+1);
                    subList.setPoint(prev, 0);
                    subList.setPoint(p  , 1);
                    if (subList.intersects(shrinkedBounds)) {
                    s.append(String.format("   - segment %d : [%s -> %s] intersect %s node %n",
                            i, prev, p, name));
                    }
                }
            
        }

        /**
         * Tells whether the first connection segment is parallel to the figure border where the source anchor is located.
         * @param connView the connection figure
         * @return true if the first segment is parallel to the figure.
         */
        @objid ("8db7fb37-6171-4da3-ae37-8323382d96fc")
        private static boolean isFirstSegmentOrthogonal(ConnectionView connView, PointList pointList) {
            ConnectionAnchor anchor = connView.getState().getSourceAnchor();
            if (anchor != null) {
                IFigure node = anchor.getOwner();
                if (node != null && ! (node instanceof Connection)) {
                    // check first segment is orthogonal to the figure border
                    pointList.getPoint(TMP1, 0);
                    pointList.getPoint(TMP2, 1);
                    boolean isHorizontal = TMP1.y() == TMP2.y();
            
                    Orientation anchorOrientation = GeomUtils.getDirection(TMP1, connView.getAnchorBounds().source).orientation();
                    return (anchorOrientation == Orientation.HORIZONTAL) == isHorizontal;
                }
            }
            return true;
        }

        /**
         * Tells whether the last connection segment is parallel to the figure border where the target anchor is located.
         * @param connectionFig the connection figure
         * @return true if the last segment is parallel to the figure.
         */
        @objid ("97758d64-212d-4abe-92db-2921de09d833")
        private static boolean isLastSegmentOrthogonal(ConnectionView connectionFig, PointList pointList) {
            ConnectionAnchor anchor = connectionFig.getState().getTargetAnchor();
            if (anchor != null) {
                IFigure node = anchor.getOwner();
                if (node != null && ! (node instanceof Connection)) {
                    int nb = connectionFig.cardPoints();
            
                    // check last segment is orthogonal to the figure border
                    pointList.getPoint(TMP1, nb - 2);
                    pointList.getPoint(TMP2, nb - 1);
                    boolean isHorizontal = TMP1.y() == TMP2.y();
            
                    Orientation anchorOrientation = GeomUtils.getDirection(TMP2, connectionFig.getAnchorBounds().target).orientation();
                    return (anchorOrientation == Orientation.HORIZONTAL) == isHorizontal;
                }
            }
            return true;
        }

    }

}
