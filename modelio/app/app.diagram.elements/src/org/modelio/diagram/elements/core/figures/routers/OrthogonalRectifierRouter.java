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
package org.modelio.diagram.elements.core.figures.routers;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.modelio.diagram.elements.core.figures.LinkFigure;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.Orientation;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.link.ortho.edit.AxisAccessor;

/**
 * Routes {@link Connection}s through a <code>List</code> of {@link MPoint MPoints} that make an orthogonal path.
 * <p>
 * The route constraint is modified to be made orthogonal by minimal changes only. Anchors aren't moved.
 * </p>
 * 
 * @since 5.1.0
 */
@objid ("c6e3d120-1ecf-43ed-97f9-04a6008e4390")
public class OrthogonalRectifierRouter extends BendpointConnectionRouter {
    @objid ("ba96a852-d477-4e78-9ba7-ddc0621e762c")
    private static final AutoOrthoState sharedState = new AutoOrthoState();

    @objid ("419a70e4-8c7b-4ac4-be56-3118169a3413")
    @Override
    public void route(Connection connection) {
        if (Arrays.equals(connection.getPoints().toIntArray(), LinkFigure.DEFAULT_POINTS)) {
            // This is probably an embedded diagram, do not rectify the link for now
            super.route(connection);
            return;
        }
        
        AutoOrthoState state = sharedState.init(connection);
        
        boolean valid = isValid(state);
        if (valid && AutoOrthoUtils.areSame(state.allPoints, connection.getPoints())) {
            return;
        }
        
        if (!valid) {
            computeMPointRoute(state);
        }
        
        // Build a new point list
        final PointList newPointList = AutoOrthoUtils.toPointList(state.allPoints);
        
        // Set the new points
        connection.setPoints(newPointList);
        
        if (!valid) {
            // Modifies the stored constraint to match the new routing.
            // Avoid call Connection.setRoutingConstraint(...) : it triggers routing again
            List<MPoint> newConstraint = AutoOrthoUtils.routeToConstraint(state.allPoints);
            setConstraint(connection, newConstraint);
        }
        
    }

    @objid ("3064a58b-8d03-4e07-b79d-869eeca72998")
    private void computeMPointRoute(AutoOrthoState state) {
        // Ask the anchors to align with 2nd and n-2 points to allow deletion of first and last bend points
        state.simplifyStartBendPoints(state.allPoints.size() - 1);
        state.simplifyEndBendPoints(0);
        
        state.refreshAnchorBounds();
        
        // Source and target locations are now fixed, we are not allowed to move them anymore.
        
        // Get or guess initial and final directions
        state.refreshAnchorDirections();
        
        // Now the tricky part: fix the bend points to form an orthogonal path.
        computeMPointRoute2(state);
        
        // Cleanup of useless points.
        AutoOrthoUtils.cleanup(state.allPoints, false);
        
    }

    @objid ("dfd5eee1-e2b3-4f15-8979-bc977180d0c0")
    private void computeMPointRoute2(AutoOrthoState state) {
        if (state.allPoints.size() <= 2) {
            // If there is no intermediary bend point, check whether the anchors location are aligned, and add bend point(s) if not.
            fixSingleSegment(state.allPoints, state.sourceAnchorDir, state.targetAnchorDir);
        } else {
            // If there are at least 1 intermediary bend points, fix the second and before last segments.
            fixSecondSegment(state.allPoints, state.sourceAnchorDir);
        
            fixBeforeLastSegment(state.allPoints, state.targetAnchorDir);
        }
        
    }

    @objid ("6abe45fe-5308-4b45-bf42-00714760b230")
    private void fixBeforeLastSegment(List<MPoint> allPoints, Direction targetAnchorDir) {
        int lastBendpointIndex = allPoints.size() - 2;
        Point fixedPoint = allPoints.get(lastBendpointIndex);
        Point nextPoint = allPoints.get(lastBendpointIndex - 1);
        Point targetLocation = allPoints.get(lastBendpointIndex + 1);
        Orientation previousSegmentOrientation = Direction.getMajor(fixedPoint, nextPoint).orientation();
        
        if (previousSegmentOrientation == Orientation.NONE) {
            // both points are equal, it's as if it had the right orientation.
            previousSegmentOrientation = targetAnchorDir.orientation().getPerpendicular();
        }
        
        if (targetAnchorDir == Direction.NONE) {
            // Target anchor is not oriented, deduct orientation from previous segment if possible.
            if (previousSegmentOrientation == Orientation.VERTICAL) {
                // previous segment is vertical, so first was horizontal
                fixedPoint.y = targetLocation.y;
            } else {
                // previous segment is horizontal so first is vertical
                fixedPoint.x = targetLocation.x;
            }
        } else if (targetAnchorDir.orientation() == Orientation.VERTICAL) {
            // Last segment is vertical: align the X coordinates
            // Check that we don't need an additional bend point (previous segment must be horizontal) first
            if (previousSegmentOrientation != Orientation.HORIZONTAL) {
                // Add an additional bendpoint (null is allright, it will be replaced later anyway).
                ++lastBendpointIndex;
                allPoints.add(lastBendpointIndex, null);
                fixedPoint = new Point(fixedPoint);
            }
            fixedPoint.x = targetLocation.x;
        } else {
            // Last segment is horizontal: align the Y coordinates
            // Check that we don't need an additional bend point (previous segment must be vertical) first
            if (previousSegmentOrientation != Orientation.VERTICAL) {
                // Add an additional bendpoint (null is allright, it will be replaced later anyway).
                ++lastBendpointIndex;
                allPoints.add(lastBendpointIndex, null);
                fixedPoint = new Point(fixedPoint);
            }
            fixedPoint.y = targetLocation.y;
        }
        
        MPoint fixedBendpoint = new MPoint(fixedPoint, false);
        allPoints.set(lastBendpointIndex, fixedBendpoint);
        
    }

    @objid ("05cf5892-b8ec-4158-89d2-07badf4eadb6")
    private void fixSecondSegment(List<MPoint> allPoints, Direction sourceAnchorDir) {
        // If there are at least 1 intermediary bend points, fix them to get orthogonal segments
        MPoint sourceLocation = allPoints.get(0);
        Point fixedPoint = allPoints.get(1);
        Point nextPoint = allPoints.get(2);
        Orientation nextSegmentOrientation = Direction.getMajor(fixedPoint, nextPoint).orientation();
        
        if (nextSegmentOrientation == Orientation.NONE) {
            // both points are equal, it's as if it had the right orientation.
            nextSegmentOrientation = sourceAnchorDir.orientation().getPerpendicular();
        }
        
        if (sourceAnchorDir == Direction.NONE) {
            if (nextSegmentOrientation == Orientation.VERTICAL) {
                // next segment is vertical, so first was horizontal
                fixedPoint.y = sourceLocation.y;
            } else {
                // next segment is horizontal so first is vertical
                fixedPoint.x = sourceLocation.x;
            }
        } else if (sourceAnchorDir.orientation() == Orientation.VERTICAL) {
            // First segment is vertical: align the X coordinates
            // check that we don't need an additional bend point (next segment must be horizontal) first
            if (nextSegmentOrientation != Orientation.HORIZONTAL) {
                // Add an additional bendpoint (null is allright, it will be replaced later anyway).
                allPoints.add(1, null);
                fixedPoint = new Point(fixedPoint);
            }
            fixedPoint.x = sourceLocation.x;
        } else {
            // First segment is horizontal: align the Y coordinates
            // check that we don't need an additional bend point (next segment must be vertical) first
            if (nextSegmentOrientation != Orientation.VERTICAL) {
                // Add an additional bendpoint (null is allright, it will be replaced later anyway).
                allPoints.add(1, null);
                fixedPoint = new Point(fixedPoint);
            }
            fixedPoint.y = sourceLocation.y;
        }
        MPoint fixedBendpoint = new MPoint(fixedPoint, false);
        allPoints.set(1, fixedBendpoint);
        
    }

    @objid ("8ed2f5e9-3722-4e01-bbe0-652a78abd80e")
    private void fixSingleSegment(List<MPoint> allPoints, Direction sourceAnchorDir, Direction targetAnchorDir) {
        MPoint sourceLocation = allPoints.get(0);
        MPoint targetLocation = allPoints.get(allPoints.size() - 1);
        Orientation sourceAnchorOrientation = sourceAnchorDir.orientation();
        Orientation targetAnchorOrientation = targetAnchorDir.orientation();
        if (sourceAnchorOrientation == Orientation.VERTICAL) {
            if (targetAnchorOrientation == Orientation.VERTICAL) {
                if (sourceLocation.x != targetLocation.x) {
                    // No luck: not aligned, we need 2 additional bend points.
                    allPoints.add(allPoints.size() - 1, new MPoint(sourceLocation.x, (sourceLocation.y + targetLocation.y) / 2, false));
                    allPoints.add(allPoints.size() - 1, new MPoint(targetLocation.x, (sourceLocation.y + targetLocation.y) / 2, false));
                }
                // else: lucky: both anchors are aligned, nothing to do!
            } else {
                // We need an additional bend point.
                allPoints.add(allPoints.size() - 1, new MPoint(sourceLocation.x, targetLocation.y, false));
            }
        } else {
            if (targetAnchorOrientation == Orientation.NONE) {
                // Not oriented target anchor: we might need an additional bend point.
                if (sourceLocation.y != targetLocation.y) {
                    // No luck, anchors are not aligned, we need a bend point.
                    allPoints.add(allPoints.size() - 1, new MPoint(targetLocation.x, sourceLocation.y, false));
                }
                // else: lucky, both anchors are aligned, nothing to do!
            } else if (targetAnchorOrientation == Orientation.VERTICAL) {
                // We need an additional bend point
                allPoints.add(allPoints.size() - 1, new MPoint(targetLocation.x, sourceLocation.y, false));
            } else {
                if (sourceLocation.y != targetLocation.y) {
                    // No luck: not aligned, we need 2 additional bend points.
                    allPoints.add(allPoints.size() - 1, new MPoint((sourceLocation.x + targetLocation.x) / 2, sourceLocation.y, false));
                    allPoints.add(allPoints.size() - 1, new MPoint((sourceLocation.x + targetLocation.x) / 2, targetLocation.y, false));
                }
                // else: lucky: both anchors are aligned, nothing to do!
            }
        }
        
    }

    /**
     * convenience method to get the constraint as a list of bend points.
     * @param connection a connection figure
     * @return The list of bend points.
     */
    @objid ("dbeb1495-bffb-4dc8-916b-23533a48204a")
    @SuppressWarnings ("unchecked")
    private List<MPoint> getBendpoints(Connection connection) {
        return (List<MPoint>) getConstraint(connection);
    }

    @objid ("632e1ff6-ef82-429c-b538-0624413e3a8e")
    private boolean isValid(AutoOrthoState state) {
        MPoint sourceLocation = state.allPoints.get(0);
        MPoint targetLocation = state.allPoints.get(state.allPoints.size() - 1);
        if (state.allPoints.size() <= 2) {
            return sourceLocation.x == targetLocation.x || sourceLocation.y == targetLocation.y;
        } else {
            // If there are at least 2 intermediary bend points, fix them to get orthogonal segments
            Point fixedPoint = state.allPoints.get(1).getLocation();
            Point nextPoint = state.allPoints.get(2).getLocation();
            Orientation nextSegmentOrientation = Direction.getOrtho(fixedPoint, nextPoint).orientation();
        
            if (state.sourceAnchorDir == Direction.NONE || state.sourceAnchorDir.orientation().getPerpendicular() != nextSegmentOrientation) {
                return false;
            }
        
            if (!AxisAccessor.forOrientation(state.sourceAnchorDir.orientation()).across.coordEquals(fixedPoint, sourceLocation)) {
                return false;
            }
        
            int lastBpIndex = state.allPoints.size() - 2;
            fixedPoint = state.allPoints.get(lastBpIndex).getLocation();
            nextPoint = state.allPoints.get(lastBpIndex - 1).getLocation();
            Orientation prevSegOrientation = Direction.getOrtho(fixedPoint, nextPoint).orientation();
            if (prevSegOrientation == Orientation.NONE) {
                return false;
            }
        
            if (state.targetAnchorDir == Direction.NONE || state.targetAnchorDir.orientation().getPerpendicular() != prevSegOrientation) {
                return false;
            }
        
            if (!AxisAccessor.forOrientation(state.targetAnchorDir.orientation()).across.coordEquals(fixedPoint, targetLocation)) {
                return false;
            }
        
        }
        return true;
    }

    @objid ("1337ae61-2f4a-4c36-b714-9c976364acf0")
    private static boolean removeIf(final List<MPoint> points, IntPredicate test) {
        boolean pointRemoved = false;
        for (int i = 1; i < points.size() - 1; ++i) {
            if (test.test(i)) {
                points.remove(i);
                i--;
                pointRemoved = true;
            }
        }
        return pointRemoved;
    }

}
