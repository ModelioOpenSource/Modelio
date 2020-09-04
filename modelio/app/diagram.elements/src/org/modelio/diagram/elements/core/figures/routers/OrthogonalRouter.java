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

package org.modelio.diagram.elements.core.figures.routers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;
import org.modelio.diagram.elements.core.figures.geometry.Orientation;

/**
 * Routes {@link Connection}s through a <code>List</code> of {@link Bendpoint Bendpoints} that make an orthogonal path.
 * <p>
 * The route constraint is modified to be made orthogonal.
 * </p>
 */
@objid ("7fb5747f-1dec-11e2-8cad-001ec947c8cc")
public class OrthogonalRouter extends BendpointConnectionRouter {
    /**
     * Temporary point used to avoid Point allocations.
     */
    @objid ("6354815d-1e83-11e2-8cad-001ec947c8cc")
    private static final PrecisionPoint A_POINT = new PrecisionPoint();

    @objid ("7fb57488-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void route(Connection connection) {
        PointList newPointList = computePointList(connection);
        connection.setPoints(newPointList);
    }

    /**
     * convenience method to get the constraint as a list of bend points.
     * @param connection a connection figure
     * @return The list of bend points.
     */
    @objid ("7fb5748e-1dec-11e2-8cad-001ec947c8cc")
    @SuppressWarnings ("unchecked")
    private List<Bendpoint> getBendpoints(Connection connection) {
        return (List<Bendpoint>) getConstraint(connection);
    }

    /**
     * Get the anchor owner (handle)bounds in absolute coordinates. If the anchor is not attached to a figure, returns a
     * 1x1 sized rectangle located at the anchor reference point.
     * @param anchor The anchor.
     * @return The anchor owner bounds.
     */
    @objid ("7fb5749b-1dec-11e2-8cad-001ec947c8cc")
    private Rectangle getAnchorOwnerAbsoluteBounds(ConnectionAnchor anchor) {
        final IFigure f = anchor.getOwner();
        if (f == null) {
            Point p = anchor.getReferencePoint();
            return new Rectangle(p.x, p.y, 1, 1);
        } else {
            PrecisionRectangle bounds = new PrecisionRectangle(f instanceof HandleBounds
                    ? ((HandleBounds) f).getHandleBounds() : f.getBounds());
            f.translateToAbsolute(bounds);
        
            return bounds;
        }
    }

    /**
     * @param allPoints point list to clean unnecessary bend points from.
     */
    @objid ("7fb574a4-1dec-11e2-8cad-001ec947c8cc")
    private void cleanup(final List<Bendpoint> allPoints) {
        boolean pointsRemoved = false;
        // Finish by removing unnecessary points:
        // 1: overlapping points.
        List<Integer> indexesToRemove = new ArrayList<>();
        for (int i = 1; i < allPoints.size() - 2; ++i) {
            Point p1 = allPoints.get(i).getLocation();
            Point p2 = allPoints.get(i + 1).getLocation();
        
            if (p1.getDistance(p2) < 1) {
                indexesToRemove.add(i);
            }
        }
        for (int i = indexesToRemove.size() - 1; i >= 0; --i) {
            allPoints.remove(indexesToRemove.get(i).intValue());
            pointsRemoved = true;
        }
        // 2: allPoints not bending
        indexesToRemove.clear();
        for (int i = 1; i < allPoints.size() - 1; ++i) {
            if (allPoints.get(i - 1).getLocation().x == allPoints.get(i + 1).getLocation().x ||
                    allPoints.get(i - 1).getLocation().y == allPoints.get(i + 1).getLocation().y) {
                indexesToRemove.add(i);
            }
        }
        for (int i = indexesToRemove.size() - 1; i >= 0; --i) {
            allPoints.remove(indexesToRemove.get(i).intValue());
            pointsRemoved = true;
        }
        
        if (pointsRemoved) {
            // Some points were removed, try cleaning the new point list again
            cleanup(allPoints);
        }
    }

    @objid ("7fb574ad-1dec-11e2-8cad-001ec947c8cc")
    private void fixSeveralBendpointsLink(final List<Bendpoint> bendpoints, final Point sourceLocation, final Point targetLocation, final Direction sourceAnchorOrientation, final Direction targetAnchorOrientation) {
        // If there are at least 2 intermediary bend points, fix them to get orthogonal segments
        Point fixedPoint = bendpoints.get(1).getLocation();
        Point nextPoint = bendpoints.get(2).getLocation();
        Orientation nextSegmentOrientation = Orientation.NONE;
        if (fixedPoint.x == nextPoint.x) {
            nextSegmentOrientation = Orientation.VERTICAL;
        } else if (fixedPoint.y == nextPoint.y) {
            nextSegmentOrientation = Orientation.HORIZONTAL;
        } else {
            assert (false) : "impossible to determine orientation of start segment, something is wrong with the provided list of bendpoints!";
        }
        if (sourceAnchorOrientation == Direction.NONE) {
            if (nextSegmentOrientation == Orientation.VERTICAL) {
                // next segment is vertical, so first was horizontal
                fixedPoint.y = sourceLocation.y;
            } else if (nextSegmentOrientation == Orientation.HORIZONTAL) {
                // next segment is horizontal so first is vertical
                fixedPoint.x = sourceLocation.x;
            }
        } else if (sourceAnchorOrientation == Direction.NORTH || sourceAnchorOrientation == Direction.SOUTH) {
            // First segment is vertical: align the X coordinates
            // check that we don't need an additional bend point (next segment must be horizontal) first
            if (nextSegmentOrientation != Orientation.HORIZONTAL) {
                // Add an additional bendpoint (null is allright, it will be replaced later anyway).
                bendpoints.add(1, null);
                fixedPoint = new Point(fixedPoint);
            }
            fixedPoint.x = sourceLocation.x;
        } else {
            // First segment is horizontal: align the Y coordinates
            // check that we don't need an additional bend point (next segment must be vertical) first
            if (nextSegmentOrientation != Orientation.VERTICAL) {
                // Add an additional bendpoint (null is allright, it will be replaced later anyway).
                bendpoints.add(1, null);
                fixedPoint = new Point(fixedPoint);
            }
            fixedPoint.y = sourceLocation.y;
        }
        AbsoluteBendpoint fixedBendpoint = new AbsoluteBendpoint(fixedPoint);
        bendpoints.set(1, fixedBendpoint);
        
        int lastBendpointIndex = bendpoints.size() - 2;
        fixedPoint = bendpoints.get(lastBendpointIndex).getLocation();
        nextPoint = bendpoints.get(lastBendpointIndex - 1).getLocation();
        Orientation previousSegmentOrientation = Orientation.NONE;
        if (fixedPoint.x == nextPoint.x) {
            previousSegmentOrientation = Orientation.VERTICAL;
        } else if (fixedPoint.y == nextPoint.y) {
            previousSegmentOrientation = Orientation.HORIZONTAL;
        } else {
            assert (false) : "impossible to determine orientation of last segment, something is wrong with the provided list of bendpoints!";
        }
        if (targetAnchorOrientation == Direction.NONE) {
            // Target anchor is not oriented, deduct orientation from previous segment if possible.
            if (previousSegmentOrientation == Orientation.VERTICAL) {
                // previous segment is vertical, so first was horizontal
                fixedPoint.y = targetLocation.y;
            } else if (previousSegmentOrientation == Orientation.HORIZONTAL) {
                // previous segment is horizontal so first is vertical
                fixedPoint.x = targetLocation.x;
            }
        } else if (targetAnchorOrientation == Direction.NORTH || targetAnchorOrientation == Direction.SOUTH) {
            // Last segment is vertical: align the X coordinates
            // Check that we don't need an additional bend point (previous segment must be horizontal) first
            if (previousSegmentOrientation != Orientation.HORIZONTAL) {
                // Add an additional bendpoint (null is allright, it will be replaced later anyway).
                ++lastBendpointIndex;
                bendpoints.add(lastBendpointIndex, null);
                fixedPoint = new Point(fixedPoint);
            }
            fixedPoint.x = targetLocation.x;
        } else {
            // Last segment is horizontal: align the Y coordinates
            // Check that we don't need an additional bend point (previous segment must be vertical) first
            if (previousSegmentOrientation != Orientation.VERTICAL) {
                // Add an additional bendpoint (null is allright, it will be replaced later anyway).
                ++lastBendpointIndex;
                bendpoints.add(lastBendpointIndex, null);
                fixedPoint = new Point(fixedPoint);
            }
            fixedPoint.y = targetLocation.y;
        }
        fixedBendpoint = new AbsoluteBendpoint(fixedPoint);
        bendpoints.set(lastBendpointIndex, fixedBendpoint);
    }

    @objid ("7fb574d7-1dec-11e2-8cad-001ec947c8cc")
    private void fixNoBendpointsLink(final List<Bendpoint> allPoints, final Point sourceLocation, final Point targetLocation, final Direction sourceAnchorOrientation, final Direction targetAnchorOrientation) {
        // If there is no intermediary bend point, check whether the anchors location are aligned, and add bend point(s) if not.
        if (sourceAnchorOrientation == Direction.NORTH || sourceAnchorOrientation == Direction.SOUTH) {
            if (targetAnchorOrientation == Direction.NORTH || targetAnchorOrientation == Direction.SOUTH) {
                if (sourceLocation.x != targetLocation.x) {
                    // No luck: not aligned, we need 2 additional bend points.
                    OrthogonalRouter.A_POINT.setLocation(sourceLocation.x, (sourceLocation.y + targetLocation.y) / 2);
                    allPoints.add(allPoints.size() - 1, new AbsoluteBendpoint(OrthogonalRouter.A_POINT));
                    OrthogonalRouter.A_POINT.setLocation(targetLocation.x, (sourceLocation.y + targetLocation.y) / 2);
                    allPoints.add(allPoints.size() - 1, new AbsoluteBendpoint(OrthogonalRouter.A_POINT));
                }
                // else: good luck: both anchors are aligned, nothing to do!
            } else {
                // We need an additional bend point.
                OrthogonalRouter.A_POINT.setLocation(sourceLocation.x, targetLocation.y);
                allPoints.add(allPoints.size() - 1, new AbsoluteBendpoint(OrthogonalRouter.A_POINT));
            }
        } else {
            if (targetAnchorOrientation == Direction.NONE) {
                // Not oriented target anchor: we might need an additional bend point.
                if (sourceLocation.y != targetLocation.y) {
                    // No luck, anchors are not aligned, we need a bend point.
                    OrthogonalRouter.A_POINT.setLocation(targetLocation.x, sourceLocation.y);
                    allPoints.add(allPoints.size() - 1, new AbsoluteBendpoint(OrthogonalRouter.A_POINT));
                }
                // else: good luck, both anchors are aligned, nothing to do!
            } else if (targetAnchorOrientation == Direction.SOUTH ||
                    targetAnchorOrientation == Direction.NORTH) {
                // We need an additional bend point
                OrthogonalRouter.A_POINT.setLocation(targetLocation.x, sourceLocation.y);
                allPoints.add(allPoints.size() - 1, new AbsoluteBendpoint(OrthogonalRouter.A_POINT));
            } else {
                if (sourceLocation.y != targetLocation.y) {
                    // No luck: not aligned, we need 2 additional bend points.
                    OrthogonalRouter.A_POINT.setLocation((sourceLocation.x + targetLocation.x) / 2, sourceLocation.y);
                    allPoints.add(allPoints.size() - 1, new AbsoluteBendpoint(OrthogonalRouter.A_POINT));
                    OrthogonalRouter.A_POINT.setLocation((sourceLocation.x + targetLocation.x) / 2, targetLocation.y);
                    allPoints.add(allPoints.size() - 1, new AbsoluteBendpoint(OrthogonalRouter.A_POINT));
                }
                // else: good luck: both anchors are aligned, nothing to do!
            }
        }
    }

    @objid ("7fb7d6e7-1dec-11e2-8cad-001ec947c8cc")
    private List<Bendpoint> computeInitialBendpointsList(final Connection connection, final ConnectionAnchor sourceAnchor, final ConnectionAnchor targetAnchor) {
        List<Bendpoint> origBendpoints = getBendpoints(connection);
        if (origBendpoints == null) {
            origBendpoints = Collections.emptyList();
        }
        final List<Bendpoint> allPoints = new ArrayList<>();
        
        // Let's assume the first point is the source anchor reference point (This may be modified later).
        OrthogonalRouter.A_POINT.setLocation(sourceAnchor.getReferencePoint());
        connection.translateToRelative(OrthogonalRouter.A_POINT);
        allPoints.add(new AbsoluteBendpoint(OrthogonalRouter.A_POINT));
        // Now assume the given allPoints are good (we'll fix them later if needed)
        for (Bendpoint bendpoint : origBendpoints) {
            allPoints.add(new AbsoluteBendpoint(bendpoint.getLocation()));
        }
        // End with the target anchor reference point
        OrthogonalRouter.A_POINT.setLocation(targetAnchor.getReferencePoint());
        connection.translateToRelative(OrthogonalRouter.A_POINT);
        allPoints.add(new AbsoluteBendpoint(OrthogonalRouter.A_POINT));
        
        final Rectangle srcBounds = getAnchorOwnerAbsoluteBounds(sourceAnchor).expand(1, 1);
        connection.translateToRelative(srcBounds);
        final Rectangle targetBounds = getAnchorOwnerAbsoluteBounds(targetAnchor).expand(1, 1);
        connection.translateToRelative(targetBounds);
        
        // Cleanup some useless points if needed at the beginning
        boolean sourceContainsTarget = srcBounds.contains(targetBounds);
        if (!sourceContainsTarget) {
            // Remove from the beginning of the list all allPoints until the first outside the source bounds.
            // We want to keep at least 2 points (source and target anchor reference point)
            while (allPoints.size() > 2 && srcBounds.contains(allPoints.get(1).getLocation())) {
                allPoints.remove(1);
            }
        }
        
        // Cleanup some useless points if needed at the end
        boolean targetContainsSource = targetBounds.contains(srcBounds);
        if (!targetContainsSource) {
            // Remove from the end of the list all allPoints until the first outside the target bounds.
            // We want to keep at least 2 points (source and target anchor reference point)
            while (allPoints.size() > 2 &&
                    targetBounds.contains(allPoints.get(allPoints.size() - 2).getLocation())) {
                allPoints.remove(allPoints.size() - 2);
            }
        }
        
        // Now compute the actual location of the source anchor, based on the next bendpoint (might be the target anchor reference point).
        OrthogonalRouter.A_POINT.setLocation(allPoints.get(1).getLocation());
        connection.translateToAbsolute(OrthogonalRouter.A_POINT);
        OrthogonalRouter.A_POINT.setLocation(sourceAnchor.getLocation(OrthogonalRouter.A_POINT));
        connection.translateToRelative(OrthogonalRouter.A_POINT);
        // Use that value in the list, instead of the reference point.
        allPoints.set(0, new AbsoluteBendpoint(OrthogonalRouter.A_POINT));
        
        // Now compute the actual location of the target anchor, based on the previous bendpoint (might be the source anchor location point).
        int index = allPoints.size() - 1;
        OrthogonalRouter.A_POINT.setLocation(allPoints.get(index - 1).getLocation());
        connection.translateToAbsolute(OrthogonalRouter.A_POINT);
        OrthogonalRouter.A_POINT.setLocation(targetAnchor.getLocation(OrthogonalRouter.A_POINT));
        connection.translateToRelative(OrthogonalRouter.A_POINT);
        // Use that value in the list, instead of the reference point.
        allPoints.set(index, new AbsoluteBendpoint(OrthogonalRouter.A_POINT));
        return allPoints;
    }

    @objid ("cb4690fe-8592-4b34-91fe-bc8dc27a40d7")
    private void fixLastBendpointLink(final List<Bendpoint> allPoints, final Point targetLocation, final Direction targetAnchorOrientation) {
        Point previousLocation = allPoints.get(allPoints.size() - 3).getLocation();
        Point lastLocation = allPoints.get(allPoints.size() - 2).getLocation();
        
        Direction direction;
        if (previousLocation.x == lastLocation.x) {
            // HORIZONTAL
            direction = lastLocation.x - targetLocation.x >= 0 ? Direction.NORTH : Direction.SOUTH;
        } else if (previousLocation.y == lastLocation.y) {
            // VERTICAL
            direction = lastLocation.y - targetLocation.y >= 0 ? Direction.WEST : Direction.EAST;
        } else {
            // impossible to determine a direction, something is temporarily inconsistent with the provided list of allPoints, will be dealt with later
            direction = Direction.NONE;
        }
        fixNoBendpointsLink(allPoints, lastLocation, targetLocation, direction, targetAnchorOrientation);
    }

    /**
     * Compute a list of points to use when routing the connection.
     * @param connection an orthogonal connection.
     * @return a List of Points
     */
    @objid ("567bbf8a-64db-4388-a7c3-e962996a5175")
    public PointList computePointList(Connection connection) {
        final ConnectionAnchor sourceAnchor = connection.getSourceAnchor();
        final ConnectionAnchor targetAnchor = connection.getTargetAnchor();
        
        final List<Bendpoint> allPoints = computeInitialBendpointsList(connection, sourceAnchor, targetAnchor);
        
        // Source and target locations are now fixed, we are not allowed to move them anymore.
        Point sourceLocation = allPoints.get(0).getLocation();
        Point targetLocation = allPoints.get(allPoints.size() - 1).getLocation();
        
        // Now the tricky part: fix the first and last bend points to form an orthogonal path.
        final Rectangle sourceRelativeBounds = getAnchorOwnerAbsoluteBounds(sourceAnchor).expand(1, 1);
        connection.translateToRelative(sourceRelativeBounds);
        final Rectangle targetRelativeBounds = getAnchorOwnerAbsoluteBounds(targetAnchor).expand(1, 1);
        connection.translateToRelative(targetRelativeBounds);
        Direction sourceAnchorOrientation = GeomUtils.getDirection(sourceLocation, sourceRelativeBounds);
        Direction targetAnchorOrientation = GeomUtils.getDirection(targetLocation, targetRelativeBounds);
        
        if (allPoints.size() == 2) {
            fixNoBendpointsLink(allPoints, sourceLocation, targetLocation, sourceAnchorOrientation, targetAnchorOrientation);
        } else if (allPoints.size() > 2) {
            fixLastBendpointLink(allPoints, targetLocation, targetAnchorOrientation);
            fixSeveralBendpointsLink(allPoints, sourceLocation, targetLocation, sourceAnchorOrientation, targetAnchorOrientation);
        }
        
        // Cleanup of useless points.
        cleanup(allPoints);
        
        // Build a new point list
        final PointList points = new PointList(allPoints.size());
        for (int i = 0; i < allPoints.size(); i++) {
            Bendpoint bp = allPoints.get(i);
            points.addPoint(bp.getLocation());
        }
        return points;
    }

}
