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
package org.modelio.diagram.elements.core.link.migration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.HandleBounds;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;
import org.modelio.diagram.elements.core.figures.geometry.Orientation;
import org.modelio.diagram.elements.core.figures.geometry.PointListUtilities;
import org.modelio.diagram.elements.core.figures.routers.AutoOrthogonalRouter;
import org.modelio.diagram.elements.core.figures.routers.OrthogonalRouter;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.MPoint;

/**
 * With Modelio 3.7, the orthogonal router changed a little, making it sometimes necessary to adapt the layout data before using the new router.
 * <p>
 * This class compares the point list gotten from {@link OrthogonalRouter} and {@link OrthogonalRouter3_6}, and updates the {@link GmPath} of the {@link GmLink} if needed to keep the same looks for old orthogonal links.
 * </p>
 */
@objid ("2cbffec6-160b-4b95-a877-5ad5c94576ea")
public final class OrthogonalPathFixer3_7 extends AbstractPathFixer {
    /**
     * C'tor.
     * @param gmLink the link to migrate.
     */
    @objid ("b2c06a65-00f8-46db-b578-b514a84d2fad")
    public  OrthogonalPathFixer3_7(GmLink gmLink) {
        super(gmLink);
    }

    @objid ("6aa91a29-f045-4bbc-ace0-2c04c16ffbca")
    @Override
    public void run(EditPartViewer viewer) {
        if (this.gmLink.isValid()) {
            GraphicalEditPart linkEditPart = (GraphicalEditPart) viewer.getEditPartRegistry().get(this.gmLink);
            if (linkEditPart != null && linkEditPart.getFigure() instanceof Connection) {
                Connection connection = (Connection) linkEditPart.getFigure();
                List<Point> pointList = computeFixedPointList(connection);
                if (pointList != null) {
                    applyLayoutData(pointList);
                }
            }
        }
        
    }

    /**
     * Compares the point list gotten from {@link OrthogonalRouter} and {@link OrthogonalRouter3_6}.
     * @param connection the connection being routed.
     * @return a list of points when a layout fix is needed, <code>null</code> otherwise.
     */
    @objid ("fbd0e950-1e0a-40d5-ba99-d0821c51890a")
    private List<Point> computeFixedPointList(Connection connection) {
        OrthogonalRouter newRouter;
        if (connection.getConnectionRouter() instanceof AutoOrthogonalRouter) {
            newRouter = new OrthogonalRouter();
        } else if (!(connection.getConnectionRouter() instanceof OrthogonalRouter)) {
            return null;
        } else {
            newRouter = (OrthogonalRouter) connection.getConnectionRouter();
        }
        
        PointList newPointList = newRouter.computePointList(connection);
        PointList oldPointList = new OrthogonalRouter3_6().computePointList(connection, newRouter);
        
        if (oldPointList.size() >= 2 && newPointList.size() >= 2) {
            // Keep point list unchanged when anchors are not properly initialized (aka both equals to (0, 0))
            if (newPointList.getFirstPoint().equals(new Point()) && newPointList.getFirstPoint().equals(newPointList.getLastPoint())) {
                return null;
            }
        
            // Remove first and last points, handled by anchors
            oldPointList.removePoint(oldPointList.size() - 1);
            newPointList.removePoint(newPointList.size() - 1);
            oldPointList.removePoint(0);
            newPointList.removePoint(0);
        
            // Check at least one bendpoint is different
            if (PointListUtilities.isContentDifferent(newPointList, oldPointList)) {
                // Set old router points as bend points for new router
                List<Point> newPathData = new ArrayList<>(oldPointList.size());
                for (int i = 0; i < oldPointList.size(); i++) {
                    newPathData.add(new MPoint(oldPointList.getPoint(i), false));
                }
                return newPathData;
            }
        }
        // No fix needed
        return null;
    }

    /**
     * Pre-Modelio 3.7 of the orthogonal router, which has been replaced with {@link OrthogonalRouter}.
     * <p>
     * Routes {@link Connection}s through a <code>List</code> of {@link Bendpoint Bendpoints} that make an orthogonal path.
     * </p>
     * <p>
     * The route constraint is modified to be made orthogonal.
     * </p>
     */
    @objid ("be934d8e-d6c8-44d5-a76e-8ff079a62c4f")
    private static final class OrthogonalRouter3_6 {
        /**
         * Temporary point used to avoid Point allocations.
         */
        @objid ("b6837aa4-fa87-4b5e-85c5-d71d345c12be")
        private static final PrecisionPoint A_POINT = new PrecisionPoint();

        /**
         * Compute a list of points to use when routing the connection.
         * @param connection an orthogonal connection.
         * @param orthogonalRouter the actual orthogonal router holding the connection's constraint.
         * @return a List of Points
         */
        @objid ("e6889762-df87-42fb-b538-1a171414a6d9")
        public PointList computePointList(Connection connection, OrthogonalRouter orthogonalRouter) {
            final ConnectionAnchor sourceAnchor = connection.getSourceAnchor();
            final ConnectionAnchor targetAnchor = connection.getTargetAnchor();
            
            final List<Bendpoint> allPoints = computeInitialBendpointsList(connection, sourceAnchor, targetAnchor, orthogonalRouter);
            
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
            } else if (allPoints.size() == 3) {
                fixOneBendpointLink(allPoints, sourceLocation, targetLocation, sourceAnchorOrientation, targetAnchorOrientation);
            
            } else {
                fixSeveralBendpointsLink(allPoints, sourceLocation, targetLocation, sourceAnchorOrientation, targetAnchorOrientation);
            }
            
            // Some cleanup of useless allPoints.
            cleanup(allPoints);
            
            // Clear the old points list
            final PointList points = new PointList(allPoints.size());
            for (int i = 0; i < allPoints.size(); i++) {
                Bendpoint bp = allPoints.get(i);
                points.addPoint(bp.getLocation());
            }
            return points;
        }

        /**
         * convenience method to get the constraint as a list of bend points.
         * @param connection a connection figure
         * @return The list of bend points.
         */
        @objid ("facd1262-4527-46e9-8ff5-3637d1f1af69")
        @SuppressWarnings ("unchecked")
        private List<Bendpoint> getBendpoints(Connection connection, OrthogonalRouter orthogonalRouter) {
            return (List<Bendpoint>) orthogonalRouter.getConstraint(connection);
        }

        /**
         * Get the anchor owner (handle)bounds in absolute coordinates. If the anchor is not attached to a figure, returns a 1x1 sized rectangle located at the anchor reference point.
         * @param anchor The anchor.
         * @return The anchor owner bounds.
         */
        @objid ("8aa4d331-1da2-46b9-95a6-e8ce12dd6fa3")
        private Rectangle getAnchorOwnerAbsoluteBounds(ConnectionAnchor anchor) {
            final IFigure f = anchor.getOwner();
            if (f == null) {
                Point p = anchor.getReferencePoint();
                return new Rectangle(p.x, p.y, 1, 1);
            } else {
                PrecisionRectangle bounds = new PrecisionRectangle(f instanceof HandleBounds
                        ? ((HandleBounds) f).getHandleBounds()
                        : f.getBounds());
                f.translateToAbsolute(bounds);
            
                return bounds;
            }
            
        }

        /**
         * @param allPoints point list to clean unnecessary bend points from.
         */
        @objid ("d1478634-0ea4-42bf-a0c1-fae5b07bfc60")
        private void cleanup(final List<Bendpoint> allPoints) {
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
            }
            
        }

        @objid ("f4fcefda-1afc-408f-8586-87ab72cf6337")
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
                assert false : "impossible to determine orientation of start segment, something is wrong with the provided list of bendpoints!";
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
                assert false : "impossible to determine orientation of last segment, something is wrong with the provided list of bendpoints!";
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

        @objid ("85a0f971-c28c-4935-93ef-da900c04d07e")
        private void fixNoBendpointsLink(final List<Bendpoint> allPoints, final Point sourceLocation, final Point targetLocation, final Direction sourceAnchorOrientation, final Direction targetAnchorOrientation) {
            // If there is no intermediary bend point, check whether the anchors location are aligned, and add bend point(s) if not.
            if (sourceAnchorOrientation == Direction.NORTH || sourceAnchorOrientation == Direction.SOUTH) {
                if (targetAnchorOrientation == Direction.NORTH || targetAnchorOrientation == Direction.SOUTH) {
                    if (sourceLocation.x != targetLocation.x) {
                        // No luck: not aligned, we need 2 additional bend points.
                        OrthogonalRouter3_6.A_POINT.setLocation(sourceLocation.x, (sourceLocation.y + targetLocation.y) / 2);
                        allPoints.add(1, new AbsoluteBendpoint(OrthogonalRouter3_6.A_POINT));
                        OrthogonalRouter3_6.A_POINT.setLocation(targetLocation.x, (sourceLocation.y + targetLocation.y) / 2);
                        allPoints.add(2, new AbsoluteBendpoint(OrthogonalRouter3_6.A_POINT));
                    }
                    // else: good luck: both anchors are aligned, nothing to do!
                } else {
                    // We need an additional bend point.
                    OrthogonalRouter3_6.A_POINT.setLocation(sourceLocation.x, targetLocation.y);
                    allPoints.add(1, new AbsoluteBendpoint(OrthogonalRouter3_6.A_POINT));
                }
            } else {
                if (targetAnchorOrientation == Direction.NONE) {
                    // Not oriented target anchor: we might need an additional bend point.
                    if (sourceLocation.y != targetLocation.y) {
                        // No luck, anchors are not aligned, we need a bend point.
                        OrthogonalRouter3_6.A_POINT.setLocation(targetLocation.x, sourceLocation.y);
                        allPoints.add(1, new AbsoluteBendpoint(OrthogonalRouter3_6.A_POINT));
                    }
                    // else: good luck, both anchors are aligned, nothing to do!
                } else if (targetAnchorOrientation == Direction.SOUTH ||
                        targetAnchorOrientation == Direction.NORTH) {
                    // We need an additional bend point
                    OrthogonalRouter3_6.A_POINT.setLocation(targetLocation.x, sourceLocation.y);
                    allPoints.add(1, new AbsoluteBendpoint(OrthogonalRouter3_6.A_POINT));
                } else {
                    if (sourceLocation.y != targetLocation.y) {
                        // No luck: not aligned, we need 2 additional bend points.
                        OrthogonalRouter3_6.A_POINT.setLocation((sourceLocation.x + targetLocation.x) / 2, sourceLocation.y);
                        allPoints.add(1, new AbsoluteBendpoint(OrthogonalRouter3_6.A_POINT));
                        OrthogonalRouter3_6.A_POINT.setLocation((sourceLocation.x + targetLocation.x) / 2, targetLocation.y);
                        allPoints.add(2, new AbsoluteBendpoint(OrthogonalRouter3_6.A_POINT));
                    }
                    // else: good luck: both anchors are aligned, nothing to do!
                }
            }
            
        }

        @objid ("76193c86-75f1-4707-9b67-2af2e809ad74")
        private List<Bendpoint> computeInitialBendpointsList(final Connection connection, final ConnectionAnchor sourceAnchor, final ConnectionAnchor targetAnchor, OrthogonalRouter orthogonalRouter) {
            List<Bendpoint> origBendpoints = getBendpoints(connection, orthogonalRouter);
            if (origBendpoints == null) {
                origBendpoints = Collections.emptyList();
            }
            final List<Bendpoint> allPoints = new ArrayList<>();
            
            // Let's assume the first point is the source anchor reference point (This may be modified later).
            OrthogonalRouter3_6.A_POINT.setLocation(sourceAnchor.getReferencePoint());
            connection.translateToRelative(OrthogonalRouter3_6.A_POINT);
            allPoints.add(new AbsoluteBendpoint(OrthogonalRouter3_6.A_POINT));
            // Now assume the given allPoints are good (we'll fix them later if needed)
            for (Bendpoint bendpoint : origBendpoints) {
                allPoints.add(new AbsoluteBendpoint(bendpoint.getLocation()));
            }
            // End with the target anchor reference point
            OrthogonalRouter3_6.A_POINT.setLocation(targetAnchor.getReferencePoint());
            connection.translateToRelative(OrthogonalRouter3_6.A_POINT);
            allPoints.add(new AbsoluteBendpoint(OrthogonalRouter3_6.A_POINT));
            
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
            OrthogonalRouter3_6.A_POINT.setLocation(allPoints.get(1).getLocation());
            connection.translateToAbsolute(OrthogonalRouter3_6.A_POINT);
            OrthogonalRouter3_6.A_POINT.setLocation(sourceAnchor.getLocation(OrthogonalRouter3_6.A_POINT));
            connection.translateToRelative(OrthogonalRouter3_6.A_POINT);
            // Use that value in the list, instead of the reference point.
            allPoints.set(0, new AbsoluteBendpoint(OrthogonalRouter3_6.A_POINT));
            
            // Now compute the actual location of the target anchor, based on the previous bendpoint (might be the source anchor location point).
            int index = allPoints.size() - 1;
            OrthogonalRouter3_6.A_POINT.setLocation(allPoints.get(index - 1).getLocation());
            connection.translateToAbsolute(OrthogonalRouter3_6.A_POINT);
            OrthogonalRouter3_6.A_POINT.setLocation(targetAnchor.getLocation(OrthogonalRouter3_6.A_POINT));
            connection.translateToRelative(OrthogonalRouter3_6.A_POINT);
            // Use that value in the list, instead of the reference point.
            allPoints.set(index, new AbsoluteBendpoint(OrthogonalRouter3_6.A_POINT));
            return allPoints;
        }

        @objid ("cffae04a-16cc-41b5-ad93-0ea9f1a5ea0e")
        private void fixOneBendpointLink(final List<Bendpoint> bendpoints, final Point sourceLocation, final Point targetLocation, final Direction sourceAnchorOrientation, final Direction targetAnchorOrientation) {
            // If there is only 1 intermediary bend point, try to fix it or add another bend point if needed
            Point fixedPoint = bendpoints.get(1).getLocation();
            if (sourceAnchorOrientation == Direction.NORTH || sourceAnchorOrientation == Direction.SOUTH) {
                fixedPoint.x = sourceLocation.x;
                if (targetAnchorOrientation == Direction.NORTH || targetAnchorOrientation == Direction.SOUTH) {
                    // Unless the 3 points are aligned on the X axis, we are gonna need an additional bend point.
                    if (targetLocation.x != fixedPoint.x) {
                        OrthogonalRouter3_6.A_POINT.setLocation(targetLocation.x, fixedPoint.y);
                        bendpoints.add(2, new AbsoluteBendpoint(OrthogonalRouter3_6.A_POINT));
                    }
                    // else: do nothing, the 3 points are aligned, the intermediary bendpoint will be removed during the cleanup phase.
                } else {
                    fixedPoint.y = targetLocation.y;
                }
            } else {
                fixedPoint.y = sourceLocation.y;
                if (targetAnchorOrientation == Direction.NORTH ||
                        targetAnchorOrientation == Direction.SOUTH ||
                        targetAnchorOrientation == Direction.NONE) {
                    fixedPoint.x = targetLocation.x;
                } else {
                    // Unless the 3 points are aligned on the Y axis, we are gonna need an additional bend point.
                    if (targetLocation.y != fixedPoint.y) {
                        OrthogonalRouter3_6.A_POINT.setLocation(fixedPoint.x, targetLocation.y);
                        bendpoints.add(2, new AbsoluteBendpoint(OrthogonalRouter3_6.A_POINT));
                    }
                    // else: do nothing, the 3 points are aligned, the intermediary bendpoint will be removed during the cleanup phase.
                }
            }
            AbsoluteBendpoint fixedBendpoint = new AbsoluteBendpoint(fixedPoint);
            bendpoints.add(1, fixedBendpoint);
            bendpoints.remove(2);
            
        }

    }

}
