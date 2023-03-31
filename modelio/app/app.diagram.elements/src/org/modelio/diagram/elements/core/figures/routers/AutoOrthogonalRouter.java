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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.Orientation;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.link.MPrecisionPoint;
import org.modelio.diagram.elements.core.link.path.BendPointUtils;
import org.modelio.diagram.elements.plugin.DiagramElements;

/**
 * Routes {@link Connection}s through a <code>List</code> of {@link MPoint Bendpoints} that make an orthogonal path.
 * <p>
 * The route constraint is modified to be made orthogonal.
 * </p>
 * <p>
 * Tries to avoid going through the source and target nodes.
 * </p>
 * 
 * @see AutoOrthogonalRouterAlgorithm
 */
@objid ("eef13028-5217-45f6-b5ae-391bbf2ae0b9")
public class AutoOrthogonalRouter extends BendpointConnectionRouter {
    /**
     * if true useless manual points may be removed
     */
    @objid ("dbffc59d-a120-4c3c-a8b9-8f70c925e6d9")
    private boolean cleanupManualPoints;

    /**
     * Activate simplification by asking the source/target anchors whether they can align to the second/before last bend points.
     */
    @objid ("63e0d17a-97a3-48cf-85a3-48a8bfac6453")
    private boolean simplifyEnds = true;

    @objid ("afe1ba17-e17d-4845-968a-48c06abcb7f0")
    private boolean rerouteWrongSectionFromPreviousManualPoint;

    @objid ("80f1cc21-58dd-489e-b490-571c14a6d0a1")
    private static final Rectangle R1 = new Rectangle();

    @objid ("a253fea5-4b81-467e-8624-24cd4f5d9e65")
    private static final Rectangle R2 = new Rectangle();

    /**
     * Temporary point used to avoid Point allocations.
     */
    @objid ("5d6ddb25-cffb-4411-b040-6d2207e3ca16")
    private static final MPrecisionPoint A_POINT = new MPrecisionPoint();

    /**
     * Temporary state data used only while computing route
     */
    @objid ("85106767-0362-4fd6-a384-266bcbdb2afc")
    private static final AutoOrthoState sharedState = new AutoOrthoState();

    /**
     * Convert a route to a constraint.
     * <p>
     * @see AutoOrthoUtils#routeToConstraint(List)
     * @param route a route computed by {@link #computeMPointRoute(Connection, List)}, {@link #computePointList(Connection)} ...
     * @return the same list striped from extremities, suitable as constraint.
     */
    @objid ("336c0542-6357-413f-9a0e-eecc1caaa7b1")
    public static List<MPoint> routeToConstraint(List<MPoint> route) {
        return AutoOrthoUtils.routeToConstraint(route);
    }

    /**
     * Compute the connection route as a list of {@link MPoint} from a routing constraint.
     * <p>
     * New points are added as automatic points. Manual points in the routing constraint are expected to be kept at same coordinates in the returned list.
     * @param connection a connection
     * @param initialConstraint the routing constraint. It won't be modified
     * @return the new updated connection route.
     */
    @objid ("60e0d599-2198-463c-a7b1-43313f19cde1")
    public List<MPoint> computeMPointRoute(Connection connection, List<MPoint> initialConstraint) {
        final ConnectionAnchor sourceAnchor = connection.getSourceAnchor();
        final ConnectionAnchor targetAnchor = connection.getTargetAnchor();
        return computeMPointRoute(connection, initialConstraint, sourceAnchor, targetAnchor);
    }

    /**
     * Compute the connection route as a list of {@link MPoint} from a ConnectionState.
     * <p>
     * New points are added as automatic points. Manual points in the routing constraint are expected to be kept at same coordinates in the returned list.
     * @param connection a connection
     * @param connState a connection state. It won't be modified
     * @return the new updated connection route.
     */
    @objid ("271873f1-0e11-45b5-b319-006557033cfe")
    public List<MPoint> computeMPointRoute(Connection connection, ConnectionState connState) {
        return computeMPointRoute(connection, connState.getMPoints(), connState.getSourceAnchor(), connState.getTargetAnchor());
    }

    /**
     * Compute the connection route as a list of {@link MPoint} from a routing constraint.
     * <p>
     * New points are added as automatic points. Manual points in the routing constraint are expected to be kept at same coordinates in the returned list.
     * @param connection a connection
     * @param initialConstraint the routing constraint. It won't be modified
     * @param startIndex the first segment point to check
     * @param len the number of segments to recompute
     * @return the new updated connection route.
     */
    @objid ("730c248d-45ba-4b0a-9291-59132d8e2eb1")
    public List<MPoint> computePartialRoute(Connection connection, List<MPoint> initialConstraint, int startIndex, int len) {
        AutoOrthoState state = sharedState.init(
                connection,
                initialConstraint,
                connection.getSourceAnchor(),
                connection.getTargetAnchor());
        
        // Ask the anchors to align with 2nd and n-2 points
        state.simplifyStartBendPoints(startIndex + len);
        state.simplifyEndBendPoints(startIndex);
        
        state.refreshAnchorBounds();
        state.refreshAnchorDirections();
        
        int realStart = findManualPointBefore(state.allPoints, startIndex);
        int realEnd = findManualPointAfter(state.allPoints, startIndex + len);
        
        // Now the tricky part: fix the bend points to form an orthogonal path.
        int delta = computePointList2(state, realStart, realEnd - realStart);
        
        // Cleanup of useless points.
        AutoOrthoUtils.cleanup(state.allPoints.subList(startIndex, startIndex + len + delta), this.cleanupManualPoints);
        return state.allPoints;
    }

    /**
     * Returned a cleaned up copy of the given routing constraint.
     * @param connection the connection to use for coordinates conversions
     * @param state the connection state to use
     * @param cleanFixed true to clean manual bend points too, false to always keep them
     * @return a cleaned up copy of the given routing constraint.
     */
    @objid ("1ee89d36-95bd-45f3-9f8a-66e5020ee0d6")
    public static List<MPoint> getCleanedConstraint(Connection connection, ConnectionState state, boolean cleanFixed) {
        return getCleanedConstraint(connection, state.getMPoints(), cleanFixed);
    }

    @objid ("93d65d87-02c6-4729-acc5-4282c45edaaa")
    @Override
    public void route(Connection connection) {
        PointList newPointList = computePointList(connection);
        connection.setPoints(newPointList);
        
    }

    /**
     * @param cleanupManualPoints if true useless manual points may be removed
     * @return this instance
     */
    @objid ("c5530428-11ca-45f6-9e6b-b82f7b9ddc47")
    public AutoOrthogonalRouter setCleanupManualPoints(boolean cleanupManualPoints) {
        this.cleanupManualPoints = cleanupManualPoints;
        return this;
    }

    /**
     * Activate simplification by asking the source/target anchors whether they can align to the second/before last bend points.
     * <p>
     * According to the link, it may result in bendpoint removal.
     * </p>
     * @param enable if <code>true</code>, activates the simplification.
     * @return this instance
     */
    @objid ("54934afe-2d21-4008-8322-b0da4899815c")
    public AutoOrthogonalRouter setSimplifyEnds(boolean enable) {
        this.simplifyEnds = enable;
        return this;
    }

    @objid ("7a502c42-0461-4c49-b71f-3630e0e3f7b6")
    private List<MPoint> computeMPointRoute(Connection connection, List<MPoint> initialConstraint, final ConnectionAnchor sourceAnchor, final ConnectionAnchor targetAnchor) {
        AutoOrthoState state = sharedState.init(connection, initialConstraint, sourceAnchor, targetAnchor);
        
        // Ask the anchors to align with 2nd and n-2 points
        if (this.simplifyEnds) {
            state.simplifyStartBendPoints(state.allPoints.size() - 1);
            state.simplifyEndBendPoints(0);
        }
        
        state.refreshAnchorBounds();
        
        // Get or guess initial and final directions
        state.refreshAnchorDirections();
        
        // Now the tricky part: fix the bend points to form an orthogonal path.
        computePointList2(state, 0, state.allPoints.size() - 1);
        
        // Cleanup of useless points.
        AutoOrthoUtils.cleanup(state.allPoints, this.cleanupManualPoints);
        return state.allPoints;
    }

    /**
     * Compute a list of points to use when routing the connection.
     * @param connection an orthogonal connection.
     * @return a List of Points
     */
    @objid ("e826abe3-71e4-4f03-adc6-fa7642276d17")
    private PointList computePointList(Connection connection) {
        List<MPoint> initialConstraint = getBendpoints(connection);
        
        final List<MPoint> allPoints = computeMPointRoute(connection, initialConstraint);
        
        // Build a new point list
        final PointList points = AutoOrthoUtils.toPointList(allPoints);
        return points;
    }

    @objid ("ab3f788f-c293-4cc7-ac66-6a06355832b6")
    private static boolean tryFixIntermediateBendPoint(int index, final List<MPoint> allPoints) {
        assert index > 1;
        assert index < allPoints.size() - 2;
        
        MPoint cur = allPoints.get(index);
        if (cur.isFixed()) {
            return false;
        }
        
        MPoint next1 = allPoints.get(index + 1);
        MPoint prev1 = allPoints.get(index - 1);
        
        // here Direction.getOrtho(cur, next1) returns NONE
        Direction prevDir = Direction.getOrtho(allPoints.get(index - 2), prev1);
        Direction nextDir = Direction.getOrtho(next1, allPoints.get(index + 2));
        
        if (prevDir == Direction.NONE || nextDir == Direction.NONE || prevDir.orientation() == nextDir.orientation()) {
            return false;
        }
        
        if (prevDir.orientation() == Orientation.VERTICAL) {
            cur.setX(next1.x());
            cur.setY(prev1.y());
        } else {
            cur.setX(prev1.x());
            cur.setY(next1.y());
        }
        return true;
    }

    @objid ("dd29589a-4896-4888-a349-25fceba34b49")
    private static boolean alignFirsBendPoint(final List<MPoint> allPoints, Direction sourceAnchorDir, Direction targetAnchorDir) {
        final int index = 1;
        
        MPoint cur = allPoints.get(index);
        if (cur.isFixed()) {
            return false;
        }
        
        MPoint prev = allPoints.get(index - 1);
        MPoint next = allPoints.get(index + 1);
        
        boolean nextSegIsLast = index + 2 >= allPoints.size() - 1;
        Orientation prevDir = sourceAnchorDir.orientation();
        Orientation nextDir = nextSegIsLast ? targetAnchorDir.orientation() : Direction.getOrtho(next, allPoints.get(index + 2)).orientation();
        Orientation expectedNextOrientation = nextSegIsLast ? prevDir.getPerpendicular() : prevDir;
        
        if (prevDir == Orientation.NONE || nextDir == Orientation.NONE || nextDir != expectedNextOrientation) {
            return false;
        }
        
        if (prevDir == Orientation.HORIZONTAL) {
            cur.setX(next.x());
            cur.setY(prev.y());
        } else {
            cur.setX(prev.x());
            cur.setY(next.y());
        }
        return true;
    }

    @objid ("ff74d6c5-8417-45dc-8816-bf0d2dcb40f1")
    private static boolean alignLastBendPoint(final List<MPoint> allPoints, Direction sourceAnchorDir, Direction targetAnchorDir) {
        final int index = allPoints.size() - 2;
        
        MPoint cur = allPoints.get(index);
        if (cur.isFixed()) {
            return false;
        }
        
        MPoint prev = allPoints.get(index - 1);
        MPoint next = allPoints.get(index + 1);
        
        Orientation curdir1 = Direction.getOrtho(prev, cur).orientation();
        Orientation curdir2 = Direction.getOrtho(cur, next).orientation();
        if (curdir1 != Orientation.NONE && curdir2 != Orientation.NONE) {
            return true;
        }
        
        boolean prevSegIsfirst = index - 2 < 0;
        Orientation prevDir = prevSegIsfirst ? sourceAnchorDir.orientation() : Direction.getOrtho(allPoints.get(index - 2), prev).orientation();
        Orientation nextDir = targetAnchorDir.orientation();
        Orientation expectedPrevDir = prevSegIsfirst ? nextDir.getPerpendicular() : nextDir;
        
        if (prevDir == Orientation.NONE || nextDir == Orientation.NONE || prevDir != expectedPrevDir) {
            return false;
        }
        
        if (nextDir == Orientation.VERTICAL) {
            cur.setX(next.x());
            cur.setY(prev.y());
        } else {
            cur.setX(prev.x());
            cur.setY(next.y());
        }
        return true;
    }

    /**
     * In case of wrong segment, allow the router to reroute all segments from previous manual point.
     * <p>
     * If false, reroute only from the wrong segment start.
     * @param rewindToPreviousManualPoint true to allow the router to reroute all segments from previous manual point.
     * @return this instance
     */
    @objid ("fc61ddb0-ee8e-463a-8a24-60fa5c77e180")
    public AutoOrthogonalRouter setRerouteWrongSectionFromPreviousManualPoint(boolean rewindToPreviousManualPoint) {
        this.rerouteWrongSectionFromPreviousManualPoint = rewindToPreviousManualPoint;
        return this;
    }

    /**
     * Make partial routing.
     * @param state all points
     * @param startIndex start index of partial routing
     * @param len number of segments to reroute
     * @return number of added points - number of removed ones
     */
    @objid ("a6af06e0-80e1-4a96-a242-390d643a4d58")
    private int computePointList2(AutoOrthoState state, int startIndex, int len) {
        /**
         * the index where to stop
         */
        int endIndex = startIndex + len;
        /**
         * total number of added bend points minus number of deleted ones
         */
        int deltaCount = 0;
        
        if (true) {
            // Try to align first and last bend points to their adjacent bend point and the anchor
            if (startIndex <= 1) {
                alignFirsBendPoint(state.allPoints, state.sourceAnchorDir, state.targetAnchorDir);
            }
            if (endIndex >= state.allPoints.size() - 1 && endIndex > 2) {
                alignLastBendPoint(state.allPoints, state.sourceAnchorDir, state.targetAnchorDir);
            }
        }
        
        int i = startIndex;
        int watchdog = 0;
        while (i < endIndex) {
            // First look for a non orthogonal segment
            MPoint segmentSource = state.allPoints.get(i);
            MPoint segmentTarget = state.allPoints.get(i + 1);
        
            // Test segment is orthogonal and for extreme ones whether it matches expected direction
            Direction segmentDir = Direction.getOrtho(segmentSource, segmentTarget);
            if (segmentDir == Direction.NONE && i > 1 && i < state.allPoints.size() - 2) {
                // Intermediate point : try to align it immediately with previous and next points
                tryFixIntermediateBendPoint(i, state.allPoints);
                segmentDir = Direction.getOrtho(segmentSource, segmentTarget);
            }
        
            boolean ok = segmentDir != Direction.NONE;
            if (ok && i == 0 && segmentDir != state.sourceAnchorDir) {
                // first segment has wrong direction
                ok = false;
            }
            if (ok && i == state.allPoints.size() - 2 && segmentDir.opposite() != state.targetAnchorDir) {
                // last bend point, and last segment has wrong direction
                ok = false;
            }
            if (ok) {
                // The segment is orthogonal.
                // Advance index and continue loop
                i++;
                continue;
            }
        
            // Here the segment [i, i+1] is not orthogonal or has wrong direction.
            //
            // Reroute all between current /*previous manual*/ point and next manual point
            // ----------------------------------------------------------------------------
            final int index1 ;
            final int index2 = findManualPointAfter(state.allPoints, i + 1);
            if (this.rerouteWrongSectionFromPreviousManualPoint && index2 == endIndex) {
                index1 = findManualPointBefore(state.allPoints, i-1);
            } else {
                index1 = i;
            }
            final MPoint point1 = state.allPoints.get(index1);
            final MPoint point2 = state.allPoints.get(index2);
        
            // Compute margin, bounds and directions for point1
            final int WORKAROUND = AutoOrthoConstants.MIN_DIST / 2;
            int margin = 0;
            Rectangle currentSourceBounds;
            Direction currentSourceDir;
            if (index1 == 0) {
                // we are on source point
                currentSourceBounds = R1.setBounds(state.anchorBounds.source).expand(WORKAROUND, WORKAROUND);
                currentSourceDir = state.sourceAnchorDir;
            } else if (index1 == startIndex) {
                currentSourceDir = Direction.getMajor(state.allPoints.get(startIndex - 1), state.allPoints.get(startIndex));
                currentSourceBounds = R1.setBounds(point1.x, point1.y, 0, 0);
            } else {
                currentSourceBounds = R1.setBounds(point1.x, point1.y, 0, 0);
                currentSourceDir = Direction.getMajor(state.allPoints.get(index1 - 1), point1);
            }
        
            // Compute margin, bounds and directions for point2
            Direction currentTargetDir;
            Rectangle currentTargetBounds;
            if (index2 < state.allPoints.size() - 1) {
                // We are not on the last segment
                currentTargetDir = state.guessBestDirectionFromPreviousSegments(currentSourceDir, index2);
                currentTargetBounds = R2.setBounds(point2.x, point2.y, 0, 0);
            } else {
                // We are on last segment
                if (state.anchorBounds.target.width() > 5) {
                    // normal node
                    currentTargetDir = state.targetAnchorDir;
                    currentTargetBounds = R2.setBounds(state.anchorBounds.target).expand(WORKAROUND, WORKAROUND);
                } else {
                    // false node : it is the mouse cursor ==> no margin
                    currentTargetDir = state.targetAnchorDir;
                    currentTargetBounds = state.anchorBounds.target;
                }
            }
        
            if (true && currentSourceBounds.intersects(currentTargetBounds)) {
                // Source and target node are too near from each other,
                // revert currentXxxBounds.expands(WORKAROUND,WORKAROUND)
                if (currentSourceBounds.width() == state.anchorBounds.source.width()+WORKAROUND*2) {
                    currentSourceBounds.setBounds(state.anchorBounds.source);
                }
                if (currentTargetBounds.width() == state.anchorBounds.target.width()+WORKAROUND*2) {
                    currentTargetBounds.setBounds(state.anchorBounds.target);
                }
            }
        
            // Compute a route
            List<MPoint> result = new AutoOrthogonalRouterAlgorithm(margin).computeOrthogonalPath(
                    point1,
                    currentSourceDir,
                    currentSourceBounds,
                    point2,
                    currentTargetDir,
                    currentTargetBounds);
        
            // Get a sub list between the 2 indexes
            List<MPoint> subList = state.allPoints.subList(index1 + 1, index2);
        
            if (!subList.equals(result)) {
                // The new route is different from the initial one : OK.
        
                // Delete invalid bend points
                int delCount = subList.size();
                if (delCount > 0) {
                    deltaCount -= delCount;
                    endIndex -= delCount;
                    subList.clear();
                }
        
                // Put in place new bend points
                int resultCount = result.size();
                if (resultCount > 0) {
                    subList.addAll(result);
                    i += resultCount;
                    endIndex += resultCount;
                    deltaCount += resultCount;
                }
            } else {
                // Router algo returned same list as original.
                // This should happen only if first and last point are equal.
                if (!point1.equals(point2) && DiagramElements.LOG.isDebugEnabled()) {
                    // Houston we have a problem ...
                    DiagramElements.LOG.debug("Same points list as original returned by ");
                    DiagramElements.LOG.debug("  new AutoOrthogonalRouterAlgorithm(margin).computeOrthogonalPath(");
                    DiagramElements.LOG.debug("     point1 = %s,", point1);
                    DiagramElements.LOG.debug("     currentSourceDir = %s,", currentSourceDir);
                    DiagramElements.LOG.debug("     currentSourceBounds = %s,", currentSourceBounds);
                    DiagramElements.LOG.debug("     point2 = %s,", point2);
                    DiagramElements.LOG.debug("     currentTargetDir = %s,", currentTargetDir);
                    DiagramElements.LOG.debug("     currentTargetBounds = %s) returned same list as original ", currentTargetBounds);
                    DiagramElements.LOG.debug("  while rerouting with i=%d section [%d-%d] of %s", i, index1, index2, state.allPoints);
                    DiagramElements.LOG.debug("  computePointList2( int startIndex=%d,", startIndex);
                    DiagramElements.LOG.debug("     int len=%d, ", len);
                    DiagramElements.LOG.debug("     allPoints=%s, ", state.allPoints);
                    DiagramElements.LOG.debug("     sourceAnchorDir=%s,", state.sourceAnchorDir);
                    DiagramElements.LOG.debug("     sourceBounds=%s, ", state.anchorBounds.source);
                    DiagramElements.LOG.debug("     targetAnchorDir=%s,", state.targetAnchorDir);
                    DiagramElements.LOG.debug("     targetBounds=%s", state.anchorBounds.target);
                    DiagramElements.LOG.debug(new IllegalStateException("Same points list as original returned by AutoOrthogonalRouterAlgorithm"));
                }
                // skip section
                i = index2;
            }
        
            if (watchdog++ > len * 2) {
                // Too many iterations occured: Houston we have a problem ...
                DiagramElements.LOG.warning("AutoOrthogonalRouter.computePointList2(): Watchdog triggered at iteration %d :", watchdog);
                DiagramElements.LOG.warning("startIndex=%d,\n\t len=%d,\n\t allPoints=%s,\n\t sourceAnchorDir=%s,\n\t sourceBounds=%s,\n\t targetAnchorDir=%s,\n\t targetBounds=%s,\n\t i=%d,\n\t endIndex=%d",
                        startIndex, len, state.allPoints, state.sourceAnchorDir, state.anchorBounds.source, state.targetAnchorDir, state.anchorBounds.target,
                        i, endIndex);
                DiagramElements.LOG.warning("point1[%d]=%s,\n\t point2[%d]=%s,\n\t subList=%s, \n\t result=%s", index1, point1, index2, point2, subList, result);
                DiagramElements.LOG.error(new IllegalStateException("AutoOrthogonalRouter.computePointList2(): Watchdog triggered"));
                break;
            }
        
        }
        return deltaCount;
    }

    @objid ("c082978e-a4bf-4f89-a369-ea2626418667")
    private static int findManualPointAfter(List<MPoint> allPoints, int index) {
        int nb = allPoints.size();
        for (int j = index; j < nb; j++) {
            MPoint p = allPoints.get(j);
            if (p.isFixed()) {
                return j;
            }
        
        }
        return nb - 1;
    }

    @objid ("e88c2d71-cd17-4b66-9a83-a9fb3aadd7dd")
    private static int findManualPointBefore(List<MPoint> allPoints, int startIndex) {
        for (int j = startIndex; j > 0; j--) {
            MPoint p = allPoints.get(j);
            if (p.isFixed()) {
                return j;
            }
        }
        return 0;
    }

    /**
     * convenience method to get the constraint as a list of bend points.
     * @param connection a connection figure
     * @return The list of bend points.
     */
    @objid ("1310b8f9-0b28-4d91-b947-0125bad0cef8")
    @SuppressWarnings ("unchecked")
    private List<MPoint> getBendpoints(Connection connection) {
        return (List<MPoint>) getConstraint(connection);
    }

    @objid ("a3745fc5-6399-4760-b708-bfcbb1dfa1df")
    private static List<MPoint> getCleanedConstraint(Connection connection, List<MPoint> initialConstraint, boolean cleanFixed) {
        final List<MPoint> allPoints = new ArrayList<>(initialConstraint);
        
        // Add source anchor location
        BendPointUtils.getConstrainedPoint(A_POINT, connection, initialConstraint, 0, true);
        allPoints.add(0, new MPoint(A_POINT, true));
        
        // Add target anchor location
        BendPointUtils.getConstrainedPoint(A_POINT, connection, initialConstraint, initialConstraint.size() + 1, true);
        allPoints.add(new MPoint(A_POINT, true));
        
        // Clean the points list
        AutoOrthoUtils.cleanup(allPoints, cleanFixed);
        
        // Remove first and last points that are anchors
        AutoOrthoUtils.routeToConstraint(allPoints);
        return allPoints;
    }

}
