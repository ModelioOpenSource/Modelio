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
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.link.MPoint;

/**
 * Computes {@link MPoint Bendpoints} that make an orthogonal path between a source and a destination.
 * <p>
 * Tries to avoid going through the source and target nodes.
 * </p>
 */
@objid ("24a60084-5d3c-4e4e-ac6f-2e3e0ccb3b0c")
public class AutoOrthogonalRouterAlgorithm {
    @objid ("5ca35e71-0048-4f61-a85c-687235bc154f")
    public static final int DEFAULT_MARGIN = AutoOrthoConstants.MIN_DIST;

    @objid ("bb01379c-af10-4784-9859-3b82e6efceba")
    private final int workaroundMargin;

    /**
     * Temporary point used to minimize Point allocations.
     */
    @objid ("82b5b254-93c7-45a1-8268-6602aef91779")
    private static final PrecisionPoint A_POINT = new PrecisionPoint();

    @objid ("42b5d20e-4894-43f7-9670-67297ad0dd98")
    public  AutoOrthogonalRouterAlgorithm() {
        this(DEFAULT_MARGIN);
    }

    @objid ("a331c363-f18d-4fd7-b494-fe16a3380d90")
    public  AutoOrthogonalRouterAlgorithm(int workaroundMargin) {
        this.workaroundMargin = workaroundMargin;
    }

    @objid ("50f0c2b8-8c30-4296-9b3c-d3c1bf1e17d8")
    public List<MPoint> computeOrthogonalPath(Point sourceLocation, Direction sourceAnchorOrientation, Rectangle sourceBounds, Point targetLocation, Direction targetAnchorOrientation, Rectangle targetBounds) {
        // System.err.println(MessageFormat.format("\t\tSOURCE - anchor={0} bounds={1} orientation={2}", sourceLocation, sourceBounds, sourceAnchorOrientation));
        // System.err.println(MessageFormat.format("\t\tTARGET - anchor={0} bounds={1} orientation={2}", targetLocation, targetBounds, targetAnchorOrientation));
        
        if (sourceAnchorOrientation == Direction.NONE) {
            sourceAnchorOrientation = getDirection(sourceLocation, targetLocation);
        }
        if (targetAnchorOrientation == Direction.NONE) {
            targetAnchorOrientation = getDirection(targetLocation, sourceLocation);
        }
        
        List<MPoint> allPoints = new ArrayList<>();
        
        switch (sourceAnchorOrientation) {
        case NORTH: {
            switch (targetAnchorOrientation) {
            case NORTH: {
                routeNorthToNorth(allPoints, sourceLocation, sourceBounds, targetLocation, targetBounds);
                break;
            }
            case SOUTH: {
                routeNorthToSouth(allPoints, sourceLocation, sourceBounds, targetLocation, targetBounds);
                break;
            }
            case EAST: {
                routeNorthToEast(allPoints, sourceLocation, sourceBounds, targetLocation, targetBounds);
                break;
            }
            case WEST: {
                routeNorthToWest(allPoints, sourceLocation, sourceBounds, targetLocation, targetBounds);
                break;
            }
            default:
                break;
            }
            break;
        }
        case SOUTH: {
            switch (targetAnchorOrientation) {
            case NORTH: {
                routeSouthToNorth(allPoints, sourceLocation, sourceBounds, targetLocation, targetBounds);
                break;
            }
            case SOUTH: {
                routeSouthToSouth(allPoints, sourceLocation, sourceBounds, targetLocation, targetBounds);
                break;
            }
            case EAST: {
                routeSouthToEast(allPoints, sourceLocation, sourceBounds, targetLocation, targetBounds);
                break;
            }
            case WEST: {
                routeSouthToWest(allPoints, sourceLocation, sourceBounds, targetLocation, targetBounds);
                break;
            }
            default:
                break;
            }
            break;
        }
        case EAST: {
            switch (targetAnchorOrientation) {
            case NORTH: {
                routeEastToNorth(allPoints, sourceLocation, sourceBounds, targetLocation, targetBounds);
                break;
            }
            case SOUTH: {
                routeEastToSouth(allPoints, sourceLocation, sourceBounds, targetLocation, targetBounds);
                break;
            }
            case EAST: {
                routeEastToEast(allPoints, sourceLocation, sourceBounds, targetLocation, targetBounds);
                break;
            }
            case WEST: {
                routeEastToWest(allPoints, sourceLocation, sourceBounds, targetLocation, targetBounds);
                break;
            }
            default:
                break;
            }
            break;
        }
        case WEST: {
            switch (targetAnchorOrientation) {
            case NORTH: {
                routeWestToNorth(allPoints, sourceLocation, sourceBounds, targetLocation, targetBounds);
                break;
            }
            case SOUTH: {
                routeWestToSouth(allPoints, sourceLocation, sourceBounds, targetLocation, targetBounds);
                break;
            }
            case EAST: {
                routeWestToEast(allPoints, sourceLocation, sourceBounds, targetLocation, targetBounds);
                break;
            }
            case WEST: {
                routeWestToWest(allPoints, sourceLocation, sourceBounds, targetLocation, targetBounds);
                break;
            }
            default:
                break;
            }
            break;
        }
        default:
            break;
        }
        
        // allPoints.add(0, new MPoint(sourceLocation));
        // allPoints.add(new MPoint(targetLocation));
        return allPoints;
    }

    @objid ("fa8290d4-7232-41f0-8386-ba441ece54ad")
    private void routeWestToWest(final List<MPoint> allPoints, Point sourceLocation, Rectangle sourceBounds, Point targetLocation, Rectangle targetBounds) {
        if (targetBounds.bottom() > sourceLocation.y - this.workaroundMargin && targetBounds.y < sourceLocation.y + this.workaroundMargin) {
            if (sourceBounds.x > targetBounds.x) {
                // 6 - Target is W, go W -> S -> E -> N -> E
                int xWorkaround1 = sourceBounds.x - (sourceBounds.x - targetBounds.right()) / 2;
                int xWorkaround2 = Math.min(targetBounds.x - this.workaroundMargin, sourceBounds.x - (sourceBounds.x - targetBounds.x) / 2);
                int yWorkaround1 = Math.max(sourceLocation.y, targetBounds.bottom() + this.workaroundMargin);
        
                allPoints.add(new MPoint(xWorkaround1, sourceLocation.y, false));
                allPoints.add(new MPoint(xWorkaround1, yWorkaround1, false));
                allPoints.add(new MPoint(xWorkaround2, yWorkaround1, false));
                allPoints.add(new MPoint(xWorkaround2, targetLocation.y, false));
            } else if (sourceBounds.y > targetLocation.y) {
                // 5 - Target is slightly NE
                int xWorkaround1 = Math.min(sourceBounds.x, targetBounds.x) - this.workaroundMargin;
                allPoints.add(new MPoint(xWorkaround1, sourceLocation.y, false));
                allPoints.add(new MPoint(xWorkaround1, targetLocation.y, false));
            } else {
                // 6 - Target is W, go W -> S -> W -> N -> E
                int xWorkaround1 = sourceBounds.x - this.workaroundMargin;
                int xWorkaround2 = sourceBounds.right() + Math.max((targetBounds.x - sourceBounds.right()) / 2, this.workaroundMargin);
                int yWorkaround1 = Math.max(targetLocation.y, sourceBounds.bottom() + this.workaroundMargin);
        
                allPoints.add(new MPoint(xWorkaround1, sourceLocation.y, false));
                allPoints.add(new MPoint(xWorkaround1, yWorkaround1, false));
                allPoints.add(new MPoint(xWorkaround2, yWorkaround1, false));
                allPoints.add(new MPoint(xWorkaround2, targetLocation.y, false));
            }
        } else {
            // 5 - Target is not E or W
            int xWorkaround1 = Math.min(sourceBounds.x, targetBounds.x) - this.workaroundMargin;
            allPoints.add(new MPoint(xWorkaround1, sourceLocation.y, false));
            allPoints.add(new MPoint(xWorkaround1, targetLocation.y, false));
        }
        
    }

    @objid ("b80dc8db-f041-4415-9739-5e1ba8af9da0")
    private void routeWestToEast(final List<MPoint> allPoints, Point sourceLocation, Rectangle sourceBounds, Point targetLocation, Rectangle targetBounds) {
        if (sourceBounds.x > targetBounds.right() + this.workaroundMargin) {
            // 8 - Target is SW, W or NW, go W -> S or N -> W
            int xWorkaround1 = sourceBounds.x - (sourceBounds.x - targetBounds.right()) / 2;
        
            allPoints.add(new MPoint(xWorkaround1, sourceLocation.y, false));
            allPoints.add(new MPoint(xWorkaround1, targetLocation.y, false));
        } else if (sourceBounds.y >= targetBounds.bottom() + this.workaroundMargin || sourceBounds.bottom() <= targetBounds.y - this.workaroundMargin) {
            int xWorkaround1 = sourceBounds.x - this.workaroundMargin;
            int xWorkaround2 = targetBounds.right() + this.workaroundMargin;
            int yWorkaround1;
        
            if (sourceBounds.y > targetBounds.bottom()) {
                // 9 - Target is S or SE
                yWorkaround1 = sourceBounds.y - (sourceBounds.y - targetBounds.bottom()) / 2;
            } else {
                // 9 - Target is N or NE
                yWorkaround1 = sourceBounds.bottom() + (targetBounds.y - sourceBounds.bottom()) / 2;
            }
        
            allPoints.add(new MPoint(xWorkaround1, sourceLocation.y, false));
            allPoints.add(new MPoint(xWorkaround1, yWorkaround1, false));
            allPoints.add(new MPoint(xWorkaround2, yWorkaround1, false));
            allPoints.add(new MPoint(xWorkaround2, targetLocation.y, false));
        } else {
            // 10 - Target is E, go W -> N -> E -> S -> W
            int xWorkaround1;
            if (sourceLocation.y < targetBounds.bottom()) {
                xWorkaround1 = Math.min(sourceBounds.x, targetBounds.x) - this.workaroundMargin;
            } else {
                xWorkaround1 = sourceBounds.x - this.workaroundMargin;
            }
            int xWorkaround2 = Math.max(targetBounds.right(), sourceBounds.right()) + this.workaroundMargin;
            int yWorkaround1 = Math.max(sourceBounds.bottom(), targetBounds.bottom()) + this.workaroundMargin;
        
            allPoints.add(new MPoint(xWorkaround1, sourceLocation.y, false));
            allPoints.add(new MPoint(xWorkaround1, yWorkaround1, false));
            allPoints.add(new MPoint(xWorkaround2, yWorkaround1, false));
            allPoints.add(new MPoint(xWorkaround2, targetLocation.y, false));
        }
        
    }

    @objid ("7d533145-b809-4c0d-a009-1435bbad578d")
    private void routeWestToSouth(final List<MPoint> allPoints, Point sourceLocation, Rectangle sourceBounds, Point targetLocation, Rectangle targetBounds) {
        if (targetLocation.x < sourceBounds.x - this.workaroundMargin && targetBounds.bottom() < sourceLocation.y - this.workaroundMargin) {
            // 1 - Target on NW, go W -> N
            allPoints.add(new MPoint(targetLocation.x, sourceLocation.y, false));
        } else if (targetLocation.x < sourceBounds.x && targetBounds.right() < sourceBounds.x - this.workaroundMargin) {
            // 2 - Target is W or NW, go W -> N -> W -> S
            A_POINT.x = sourceBounds.x - (sourceBounds.x - targetBounds.right()) / 2;
            A_POINT.y = targetBounds.bottom() + this.workaroundMargin;
        
            allPoints.add(new MPoint(A_POINT.x, sourceLocation.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(targetLocation.x, A_POINT.y, false));
        } else if (sourceBounds.y < targetBounds.bottom() + this.workaroundMargin) {
            // 3 - Target is N, NE or E, go W -> N -> E -> S
            A_POINT.x = Math.min(targetBounds.x, sourceBounds.x) - this.workaroundMargin;
            A_POINT.y = Math.max(sourceBounds.bottom(), targetBounds.bottom()) + this.workaroundMargin;
        
            allPoints.add(new MPoint(A_POINT.x, sourceLocation.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(targetLocation.x, A_POINT.y, false));
        } else {
            // 4 - Target is SE or S, go W -> S -> E -> S
            A_POINT.x = sourceBounds.x - this.workaroundMargin;
            A_POINT.y = sourceBounds.y() - (sourceBounds.y() - targetBounds.bottom()) / 2;
        
            allPoints.add(new MPoint(A_POINT.x, sourceLocation.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(targetLocation.x, A_POINT.y, false));
        }
        
    }

    @objid ("471badb5-0efd-4df5-8584-9b25ac16591a")
    private void routeWestToNorth(final List<MPoint> allPoints, Point sourceLocation, Rectangle sourceBounds, Point targetLocation, Rectangle targetBounds) {
        if (targetLocation.x < sourceBounds.x + this.workaroundMargin && targetBounds.y > sourceLocation.y + this.workaroundMargin) {
            // 1 - Target on SW, go W -> S
            allPoints.add(new MPoint(targetLocation.x, sourceLocation.y, false));
        } else if (targetLocation.x < sourceBounds.x && targetBounds.right() < sourceBounds.x - this.workaroundMargin) {
            // 2 - Target is W or NW, go W -> N -> W -> S
            A_POINT.x = sourceBounds.x - (sourceBounds.x - targetBounds.right()) / 2;
            A_POINT.y = targetBounds.y - this.workaroundMargin;
        
            allPoints.add(new MPoint(A_POINT.x, sourceLocation.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(targetLocation.x, A_POINT.y, false));
        } else if (sourceBounds.bottom() > targetBounds.y - this.workaroundMargin) {
            // 3 - Target is N, NE or E, go W -> N -> E -> S
            A_POINT.x = Math.min(targetBounds.x, sourceBounds.x) - this.workaroundMargin;
            A_POINT.y = Math.min(sourceBounds.y, targetBounds.y) - this.workaroundMargin;
        
            allPoints.add(new MPoint(A_POINT.x, sourceLocation.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(targetLocation.x, A_POINT.y, false));
        } else {
            // 4 - Target is SE or S, go W -> S -> E -> S
            A_POINT.x = sourceBounds.x - this.workaroundMargin;
            A_POINT.y = sourceBounds.bottom() + (targetBounds.y - sourceBounds.bottom()) / 2;
        
            allPoints.add(new MPoint(A_POINT.x, sourceLocation.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(targetLocation.x, A_POINT.y, false));
        }
        
    }

    @objid ("cd6691b4-09c4-4f75-8dda-6b1c4d443f47")
    private void routeEastToWest(final List<MPoint> allPoints, Point sourceLocation, Rectangle sourceBounds, Point targetLocation, Rectangle targetBounds) {
        if (sourceBounds.right() < targetBounds.x - this.workaroundMargin) {
            // 8 - Target is SE, E or NE, go E -> S or N -> E
            int xWorkaround1 = sourceBounds.right() + (targetBounds.x - sourceBounds.right()) / 2;
        
            allPoints.add(new MPoint(xWorkaround1, sourceLocation.y, false));
            if (targetLocation.y != sourceLocation.y) {
                allPoints.add(new MPoint(xWorkaround1, targetLocation.y, false));
            }
        } else if (sourceBounds.y >= targetBounds.bottom() + this.workaroundMargin || sourceBounds.bottom() <= targetBounds.y - this.workaroundMargin) {
            int xWorkaround1 = sourceBounds.right() + this.workaroundMargin;
            int xWorkaround2 = targetBounds.x - this.workaroundMargin;
            int yWorkaround1;
            if (sourceBounds.y > targetBounds.bottom()) {
                // 9 - Target is S or SW
                yWorkaround1 = sourceBounds.y - (sourceBounds.y - targetBounds.bottom()) / 2;
            } else {
                // 9 - Target is N or NW
                yWorkaround1 = sourceBounds.bottom() + (targetBounds.y - sourceBounds.bottom()) / 2;
            }
        
            allPoints.add(new MPoint(xWorkaround1, sourceLocation.y, false));
            allPoints.add(new MPoint(xWorkaround1, yWorkaround1, false));
            allPoints.add(new MPoint(xWorkaround2, yWorkaround1, false));
            allPoints.add(new MPoint(xWorkaround2, targetLocation.y, false));
        } else {
            // 10 - Target is W, go E -> N -> W -> S -> E
            int xWorkaround1;
            if (sourceLocation.y < targetBounds.bottom()) {
                xWorkaround1 = Math.max(sourceBounds.right(), targetBounds.right()) + this.workaroundMargin;
            } else {
                xWorkaround1 = sourceBounds.right() + this.workaroundMargin;
            }
            int xWorkaround2 = Math.min(targetBounds.x, sourceBounds.x) - this.workaroundMargin;
            int yWorkaround1 = Math.max(sourceBounds.bottom(), targetBounds.bottom()) + this.workaroundMargin;
        
            allPoints.add(new MPoint(xWorkaround1, sourceLocation.y, false));
            allPoints.add(new MPoint(xWorkaround1, yWorkaround1, false));
            allPoints.add(new MPoint(xWorkaround2, yWorkaround1, false));
            allPoints.add(new MPoint(xWorkaround2, targetLocation.y, false));
        }
        
    }

    @objid ("9c8d534b-30fa-4115-88cd-a6e9ce1e7135")
    private void routeEastToEast(final List<MPoint> allPoints, Point sourceLocation, Rectangle sourceBounds, Point targetLocation, Rectangle targetBounds) {
        if (targetBounds.bottom() > sourceLocation.y - this.workaroundMargin && targetBounds.y < sourceLocation.y + this.workaroundMargin) {
            if (sourceBounds.right() < targetBounds.right()) {
                // 6 - Target is E, go E -> S -> E -> N -> W
                int xWorkaround1 = sourceBounds.right() + Math.max(this.workaroundMargin, (targetBounds.x - sourceBounds.right()) / 2);
                int xWorkaround2 = targetBounds.right() + this.workaroundMargin;
                int yWorkaround1 = Math.max(sourceLocation.y, targetBounds.bottom() + this.workaroundMargin);
        
                allPoints.add(new MPoint(xWorkaround1, sourceLocation.y, false));
                allPoints.add(new MPoint(xWorkaround1, yWorkaround1, false));
                allPoints.add(new MPoint(xWorkaround2, yWorkaround1, false));
                allPoints.add(new MPoint(xWorkaround2, targetLocation.y, false));
            } else if (sourceBounds.y > targetLocation.y) {
                // 5 - Target is slightly NW
                int xWorkaround1 = Math.max(sourceBounds.right(), targetBounds.right()) + this.workaroundMargin;
                allPoints.add(new MPoint(xWorkaround1, sourceLocation.y, false));
                allPoints.add(new MPoint(xWorkaround1, targetLocation.y, false));
            } else {
                // 6 - Target is W, go E -> S -> W -> N -> W
                int xWorkaround1 = Math.max(sourceBounds.right(), targetBounds.right()) + this.workaroundMargin;
                int xWorkaround2 = sourceBounds.x - Math.max(this.workaroundMargin, (sourceBounds.x - targetBounds.right()) / 2);
                int yWorkaround1 = Math.max(targetLocation.y, sourceBounds.bottom() + this.workaroundMargin);
        
                allPoints.add(new MPoint(xWorkaround1, sourceLocation.y, false));
                allPoints.add(new MPoint(xWorkaround1, yWorkaround1, false));
                allPoints.add(new MPoint(xWorkaround2, yWorkaround1, false));
                allPoints.add(new MPoint(xWorkaround2, targetLocation.y, false));
            }
        } else {
            // 5 - Target is not E or W
            int xWorkaround1 = Math.max(sourceBounds.right(), targetBounds.right()) + this.workaroundMargin;
            allPoints.add(new MPoint(xWorkaround1, sourceLocation.y, false));
            allPoints.add(new MPoint(xWorkaround1, targetLocation.y, false));
        }
        
    }

    @objid ("25e660c5-f9d7-4734-98ed-5cbefe73aa4a")
    private void routeEastToSouth(final List<MPoint> allPoints, Point sourceLocation, Rectangle sourceBounds, Point targetLocation, Rectangle targetBounds) {
        if (targetLocation.x > sourceBounds.right() + this.workaroundMargin && targetBounds.bottom() < sourceLocation.y - this.workaroundMargin) {
            // 1 - Target on NE, go E -> N
            allPoints.add(new MPoint(targetLocation.x, sourceLocation.y, false));
        } else if (targetLocation.x > sourceBounds.right() && targetBounds.x > sourceBounds.right() + this.workaroundMargin) {
            // 2 - Target is E or SE, go E -> S -> E -> N
            A_POINT.x = sourceBounds.right() + (targetBounds.x - sourceBounds.right()) / 2;
            A_POINT.y = targetBounds.bottom() + this.workaroundMargin;
        
            allPoints.add(new MPoint(A_POINT.x, sourceLocation.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(targetLocation.x, A_POINT.y, false));
        } else if (sourceBounds.y < targetBounds.bottom() + this.workaroundMargin) {
            // 3 - Target is S, SW or W, go E -> S -> W -> N
            A_POINT.x = Math.max(targetBounds.right(), sourceBounds.right()) + this.workaroundMargin;
            A_POINT.y = Math.max(sourceBounds.bottom(), targetBounds.bottom()) + this.workaroundMargin;
        
            allPoints.add(new MPoint(A_POINT.x, sourceLocation.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(targetLocation.x, A_POINT.y, false));
        } else {
            // 4 - Target is NW or N, go E -> N -> W -> N
            A_POINT.x = sourceBounds.right() + this.workaroundMargin;
            //this makes E->S->W->N : A_POINT.y = sourceBounds.bottom() + (targetBounds.y - sourceBounds.bottom()) / 2;
            A_POINT.y = middle(targetBounds.bottom(), sourceBounds.y());
        
            allPoints.add(new MPoint(A_POINT.x, sourceLocation.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(targetLocation.x, A_POINT.y, false));
        }
        
    }

    /**
     * Compute the integer at mid distance between a and b.
     * @param a an integer
     * @param b an integer
     * @return the integer at mid distance between a and b.
     */
    @objid ("a60b1eaf-185d-44c0-893e-219abb40e165")
    private static int middle(int a, int b) {
        if (a < b) {
            return a + (b - a) / 2;
        } else {
            return b + (a - b) / 2;
        }
        
    }

    @objid ("bb1c3b5f-c9d9-4ce7-9be3-6474ec535869")
    private void routeEastToNorth(final List<MPoint> allPoints, Point sourceLocation, Rectangle sourceBounds, Point targetLocation, Rectangle targetBounds) {
        if (targetLocation.x > sourceBounds.right() + this.workaroundMargin && targetBounds.y > sourceLocation.y + this.workaroundMargin) {
            // 1 - Target on SE, go E -> S
            allPoints.add(new MPoint(targetLocation.x, sourceLocation.y, false));
        } else if (targetLocation.x > sourceBounds.right() && targetBounds.x > sourceBounds.right() + this.workaroundMargin) {
            // 2 - Target is E or NE, go E -> N -> E -> S
            A_POINT.x = sourceBounds.right() + (targetBounds.x - sourceBounds.right()) / 2;
            A_POINT.y = targetBounds.y - this.workaroundMargin;
        
            allPoints.add(new MPoint(A_POINT.x, sourceLocation.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(targetLocation.x, A_POINT.y, false));
        } else if (sourceBounds.bottom() > targetBounds.y - this.workaroundMargin) {
            // 3 - Target is N, NW or W, go E -> N -> W -> S
            A_POINT.x = Math.max(targetBounds.right(), sourceBounds.right()) + this.workaroundMargin;
            A_POINT.y = Math.min(sourceBounds.y, targetBounds.y) - this.workaroundMargin;
        
            allPoints.add(new MPoint(A_POINT.x, sourceLocation.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(targetLocation.x, A_POINT.y, false));
        } else {
            // 4 - Target is SW or S, go E -> S -> W -> S
            A_POINT.x = sourceBounds.right() + this.workaroundMargin;
            A_POINT.y = sourceBounds.bottom() + (targetBounds.y - sourceBounds.bottom()) / 2;
        
            allPoints.add(new MPoint(A_POINT.x, sourceLocation.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(targetLocation.x, A_POINT.y, false));
        }
        
    }

    @objid ("b2bc53c8-5955-4738-8bc2-451d7e4e518d")
    private void routeSouthToWest(final List<MPoint> allPoints, Point sourceLocation, Rectangle sourceBounds, Point targetLocation, Rectangle targetBounds) {
        if (targetLocation.y > sourceBounds.bottom() + this.workaroundMargin && targetBounds.x() > sourceLocation.x + this.workaroundMargin) {
            // 1 - Target on SE, go S -> E
            allPoints.add(new MPoint(sourceLocation.x, targetLocation.y, false));
        } else if (targetLocation.y > sourceBounds.bottom() && targetBounds.y > sourceBounds.bottom() + this.workaroundMargin) {
            // 2 - Target is S or SW, go S -> W -> S -> E
            A_POINT.x = targetBounds.x - this.workaroundMargin;
            A_POINT.y = sourceBounds.bottom() - (sourceBounds.bottom() - targetBounds.y) / 2;
        
            allPoints.add(new MPoint(sourceLocation.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, targetLocation.y, false));
        } else if (sourceBounds.right() > targetBounds.x() - this.workaroundMargin) {
            // 3 - Target is W, NW or N, go S -> W -> N -> E
            A_POINT.x = Math.min(sourceBounds.x, targetBounds.x()) - this.workaroundMargin;
            A_POINT.y = Math.max(targetBounds.bottom(), sourceBounds.bottom()) + this.workaroundMargin;
        
            allPoints.add(new MPoint(sourceLocation.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, targetLocation.y, false));
        } else {
            // 4 - Target is NE or E, go S -> E -> N -> E
            A_POINT.x = sourceBounds.right() + (targetBounds.x - sourceBounds.right()) / 2;
            A_POINT.y = sourceBounds.bottom() + this.workaroundMargin;
        
            allPoints.add(new MPoint(sourceLocation.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, targetLocation.y, false));
        }
        
    }

    @objid ("4229bea2-17d3-473e-8405-9d05727e8f75")
    private void routeSouthToEast(final List<MPoint> allPoints, Point sourceLocation, Rectangle sourceBounds, Point targetLocation, Rectangle targetBounds) {
        if (targetLocation.y > sourceBounds.bottom() + this.workaroundMargin && targetBounds.right() < sourceLocation.x - this.workaroundMargin) {
            // 1 - Target on SW, go S -> W
            allPoints.add(new MPoint(sourceLocation.x, targetLocation.y, false));
        } else if (targetLocation.y > sourceBounds.bottom() && targetBounds.y > sourceBounds.bottom() + this.workaroundMargin) {
            // 2 - Target is N or NE, go S -> E -> S -> W
            A_POINT.x = targetBounds.right() + this.workaroundMargin;
            A_POINT.y = sourceBounds.bottom() - (sourceBounds.bottom() - targetBounds.y) / 2;
        
            allPoints.add(new MPoint(sourceLocation.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, targetLocation.y, false));
        } else if (sourceBounds.x < targetBounds.right() + this.workaroundMargin) {
            // 3 - Target is E, SE or S, go S -> E -> N -> W
            A_POINT.x = Math.max(sourceBounds.right(), targetBounds.right()) + this.workaroundMargin;
            A_POINT.y = Math.max(targetBounds.bottom(), sourceBounds.bottom()) + this.workaroundMargin;
        
            allPoints.add(new MPoint(sourceLocation.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, targetLocation.y, false));
        } else {
            // 4 - Target is SW or W, go S -> W -> N -> W
            A_POINT.x = sourceBounds.x - (sourceBounds.x - targetBounds.right()) / 2;
            A_POINT.y = sourceBounds.bottom() + this.workaroundMargin;
        
            allPoints.add(new MPoint(sourceLocation.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, targetLocation.y, false));
        }
        
    }

    @objid ("32b7664b-dca8-4da1-af14-5df7bd5ae3fd")
    private void routeSouthToSouth(final List<MPoint> allPoints, Point sourceLocation, Rectangle sourceBounds, Point targetLocation, Rectangle targetBounds) {
        if (targetLocation.x > sourceBounds.x - this.workaroundMargin && targetLocation.x < sourceBounds.right() + this.workaroundMargin) {
            if (sourceBounds.bottom() > targetBounds.bottom()) {
                // 6 - Target is S, go N -> W -> S -> E -> S
                int xWorkaround1 = sourceBounds.x - this.workaroundMargin;
                int yWorkaround1 = Math.max(sourceBounds.bottom(), targetBounds.bottom()) + this.workaroundMargin;
                int yWorkaround2 = sourceBounds.y - Math.max(this.workaroundMargin, (sourceBounds.y - targetBounds.bottom()) / 2);
        
                allPoints.add(new MPoint(sourceLocation.x, yWorkaround1, false));
                allPoints.add(new MPoint(xWorkaround1, yWorkaround1, false));
                allPoints.add(new MPoint(xWorkaround1, yWorkaround2, false));
                allPoints.add(new MPoint(targetLocation.x, yWorkaround2, false));
            } else {
                // 6 - Target is N, go N -> W -> N -> E -> S
                int xWorkaround1 = Math.min(sourceLocation.x, targetBounds.x - this.workaroundMargin);
                int yWorkaround1 = sourceBounds.bottom() + Math.max(this.workaroundMargin, (targetBounds.y - sourceBounds.bottom()) / 2);
                int yWorkaround2 = targetBounds.bottom() + this.workaroundMargin;
        
                allPoints.add(new MPoint(sourceLocation.x, yWorkaround1, false));
                allPoints.add(new MPoint(xWorkaround1, yWorkaround1, false));
                allPoints.add(new MPoint(xWorkaround1, yWorkaround2, false));
                allPoints.add(new MPoint(targetLocation.x, yWorkaround2, false));
            }
        } else {
            // 5 - Target is not N or S
            int yWorkaround1 = Math.max(sourceBounds.bottom(), targetBounds.bottom()) + this.workaroundMargin;
            allPoints.add(new MPoint(sourceLocation.x, yWorkaround1, false));
            allPoints.add(new MPoint(targetLocation.x, yWorkaround1, false));
        }
        
    }

    @objid ("7733b13c-a0fe-4c22-aec4-c41b9d8ddaba")
    private void routeSouthToNorth(final List<MPoint> allPoints, Point sourceLocation, Rectangle sourceBounds, Point targetLocation, Rectangle targetBounds) {
        if (targetBounds.y > sourceBounds.bottom()) {
            // 8 - Target is SW, S or SE, go S -> W or E -> S
            int yWorkaround1 = sourceBounds.bottom() + (targetBounds.y - sourceBounds.bottom()) / 2;
        
            allPoints.add(new MPoint(sourceLocation.x, yWorkaround1, false));
            allPoints.add(new MPoint(targetLocation.x, yWorkaround1, false));
        } else if (sourceBounds.x > targetBounds.right() || sourceBounds.right() < targetBounds.x) {
            int xWorkaround1;
            if (sourceBounds.x > targetBounds.right()) {
                // 9 - Target is W or NW
                xWorkaround1 = sourceBounds.x - (sourceBounds.x - targetBounds.right()) / 2;
            } else {
                // 9 - Target is E or NE
                xWorkaround1 = sourceBounds.right() + (targetBounds.x - sourceBounds.right()) / 2;
            }
            int yWorkaround1 = sourceBounds.bottom() + this.workaroundMargin;
            int yWorkaround2 = targetBounds.y - this.workaroundMargin;
        
            allPoints.add(new MPoint(sourceLocation.x, yWorkaround1, false));
            allPoints.add(new MPoint(xWorkaround1, yWorkaround1, false));
            allPoints.add(new MPoint(xWorkaround1, yWorkaround2, false));
            allPoints.add(new MPoint(targetLocation.x, yWorkaround2, false));
        } else {
            // 10 - Target is N, go S -> E -> N -> W -> S
            int xWorkaround1 = Math.max(sourceBounds.right(), targetBounds.right()) + this.workaroundMargin;
            int yWorkaround1;
            if (sourceLocation.x < targetBounds.right()) {
                yWorkaround1 = Math.max(sourceBounds.bottom(), targetBounds.bottom()) + this.workaroundMargin;
            } else {
                yWorkaround1 = sourceBounds.bottom() + this.workaroundMargin;
            }
            int yWorkaround2 = Math.min(targetBounds.y, sourceBounds.y) - this.workaroundMargin;
        
            allPoints.add(new MPoint(sourceLocation.x, yWorkaround1, false));
            allPoints.add(new MPoint(xWorkaround1, yWorkaround1, false));
            allPoints.add(new MPoint(xWorkaround1, yWorkaround2, false));
            allPoints.add(new MPoint(targetLocation.x, yWorkaround2, false));
        }
        
    }

    @objid ("4fb07e7c-87c7-4b23-aab3-89b8af3ef263")
    private void routeNorthToWest(final List<MPoint> allPoints, Point sourceLocation, Rectangle sourceBounds, Point targetLocation, Rectangle targetBounds) {
        if (targetLocation.y < sourceBounds.y - this.workaroundMargin && targetLocation.x > sourceLocation.x + this.workaroundMargin) {
            // 1 - Target on NE, go N -> E
            allPoints.add(new MPoint(sourceLocation.x, targetLocation.y, false));
        } else if (targetLocation.y < sourceBounds.y && targetBounds.bottom() < sourceBounds.y - this.workaroundMargin) {
            // 2 - Target is N or NW, go N -> W -> N -> E
            A_POINT.x = targetBounds.x - this.workaroundMargin;
            A_POINT.y = sourceBounds.y - (sourceBounds.y - targetBounds.bottom()) / 2;
        
            allPoints.add(new MPoint(sourceLocation.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, targetLocation.y, false));
        } else if (sourceBounds.right() > targetLocation.x - this.workaroundMargin) {
            // 3 - Target is W, SW or S, go N -> W -> S -> E
            A_POINT.x = Math.min(sourceBounds.x, targetBounds.x) - this.workaroundMargin;
            A_POINT.y = Math.min(targetBounds.y, sourceBounds.y) - this.workaroundMargin;
        
            allPoints.add(new MPoint(sourceLocation.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, targetLocation.y, false));
        } else {
            // 4 - Target is SE or E, go N -> E -> S -> E
            A_POINT.x = sourceBounds.right() + (targetBounds.x - sourceBounds.right()) / 2;
            A_POINT.y = sourceBounds.y - this.workaroundMargin;
        
            allPoints.add(new MPoint(sourceLocation.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, targetLocation.y, false));
        }
        
    }

    @objid ("875df9d5-91d8-47c9-8de7-a8c7d8798203")
    private void routeNorthToEast(final List<MPoint> allPoints, Point sourceLocation, Rectangle sourceBounds, Point targetLocation, Rectangle targetBounds) {
        if (targetLocation.y < sourceBounds.y - this.workaroundMargin && targetLocation.x < sourceLocation.x - this.workaroundMargin) {
            // 1 - Target on NW, go N -> W
            allPoints.add(new MPoint(sourceLocation.x, targetLocation.y, false));
        } else if (targetLocation.y < sourceBounds.y && targetBounds.bottom() < sourceBounds.y - this.workaroundMargin) {
            // 2 - Target is N or NE, go N -> E -> N -> W
            A_POINT.x = targetBounds.right() + this.workaroundMargin;
            A_POINT.y = sourceBounds.y - (sourceBounds.y - targetBounds.bottom()) / 2;
        
            allPoints.add(new MPoint(sourceLocation.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, targetLocation.y, false));
        } else if (sourceBounds.x < targetLocation.x + this.workaroundMargin) {
            // 3 - Target is E, SE or S, go N -> E -> S -> W
            A_POINT.x = Math.max(sourceBounds.right(), targetBounds.right()) + this.workaroundMargin;
            A_POINT.y = Math.min(targetBounds.y, sourceBounds.y) - this.workaroundMargin;
        
            allPoints.add(new MPoint(sourceLocation.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, targetLocation.y, false));
        } else {
            // 4 - Target is SW or W, go N -> W -> S -> W
            A_POINT.x = sourceBounds.x - (sourceBounds.x - targetBounds.right()) / 2;
            A_POINT.y = sourceBounds.y - this.workaroundMargin;
        
            allPoints.add(new MPoint(sourceLocation.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, A_POINT.y, false));
            allPoints.add(new MPoint(A_POINT.x, targetLocation.y, false));
        }
        
    }

    @objid ("f7549d1e-734a-4220-9b75-8d5a7f6c040a")
    private void routeNorthToSouth(final List<MPoint> allPoints, Point sourceLocation, Rectangle sourceBounds, Point targetLocation, Rectangle targetBounds) {
        if (sourceBounds.y > targetBounds.bottom()) {
            // 8 - Target is NW, N or NE, go N -> W or E -> N
            int yWorkaround1 = sourceBounds.y - (sourceBounds.y - targetBounds.bottom()) / 2;
        
            allPoints.add(new MPoint(sourceLocation.x, yWorkaround1, false));
            allPoints.add(new MPoint(targetLocation.x, yWorkaround1, false));
        } else if (sourceBounds.x > targetBounds.right() || sourceBounds.right() < targetBounds.x) {
            int xWorkaround1;
            if (sourceBounds.x > targetBounds.right()) {
                // 9 - Target is W or SW
                xWorkaround1 = sourceBounds.x - (sourceBounds.x - targetBounds.right()) / 2;
            } else {
                // 9 - Target is E or SE
                xWorkaround1 = sourceBounds.right() + (targetBounds.x - sourceBounds.right()) / 2;
            }
            int yWorkaround1 = sourceBounds.y - this.workaroundMargin;
            int yWorkaround2 = targetBounds.bottom() + this.workaroundMargin;
        
            allPoints.add(new MPoint(sourceLocation.x, yWorkaround1, false));
            allPoints.add(new MPoint(xWorkaround1, yWorkaround1, false));
            allPoints.add(new MPoint(xWorkaround1, yWorkaround2, false));
            allPoints.add(new MPoint(targetLocation.x, yWorkaround2, false));
        } else {
            // 10 - Target is S, go N -> E -> S -> W -> N
            int xWorkaround1 = Math.max(sourceBounds.right(), targetBounds.right()) + this.workaroundMargin;
            int yWorkaround1;
            if (sourceLocation.x < targetBounds.right()) {
                yWorkaround1 = Math.min(sourceBounds.y, targetBounds.y) - this.workaroundMargin;
            } else {
                yWorkaround1 = sourceBounds.y - this.workaroundMargin;
            }
            int yWorkaround2 = Math.max(targetBounds.bottom(), sourceBounds.bottom()) + this.workaroundMargin;
        
            allPoints.add(new MPoint(sourceLocation.x, yWorkaround1, false));
            allPoints.add(new MPoint(xWorkaround1, yWorkaround1, false));
            allPoints.add(new MPoint(xWorkaround1, yWorkaround2, false));
            allPoints.add(new MPoint(targetLocation.x, yWorkaround2, false));
        }
        
    }

    @objid ("1bb04a38-e07f-444f-872a-65fb0774ffeb")
    private void routeNorthToNorth(final List<MPoint> allPoints, Point sourceLocation, Rectangle sourceBounds, Point targetLocation, Rectangle targetBounds) {
        if (targetLocation.x > sourceBounds.x - this.workaroundMargin && targetLocation.x < sourceBounds.right() + this.workaroundMargin) {
            if (sourceBounds.y > targetBounds.y) {
                // 6 - Target is S, go N -> W -> S -> E -> S
                int xWorkaround1 = targetBounds.x - this.workaroundMargin;
                int yWorkaround1 = sourceBounds.y - Math.max(this.workaroundMargin, (sourceBounds.y - targetBounds.bottom()) / 2);
                int yWorkaround2 = targetBounds.y - this.workaroundMargin;
        
                allPoints.add(new MPoint(sourceLocation.x, yWorkaround1, false));
                allPoints.add(new MPoint(xWorkaround1, yWorkaround1, false));
                allPoints.add(new MPoint(xWorkaround1, yWorkaround2, false));
                allPoints.add(new MPoint(targetLocation.x, yWorkaround2, false));
            } else {
                // 6 - Target is N, go N -> W -> N -> E -> S
                int xWorkaround1 = Math.min(targetLocation.x, sourceBounds.x - this.workaroundMargin);
                int yWorkaround1 = sourceBounds.y - this.workaroundMargin;
                int yWorkaround2 = sourceBounds.bottom() + Math.max(this.workaroundMargin, (targetBounds.y - sourceBounds.bottom()) / 2);
        
                allPoints.add(new MPoint(sourceLocation.x, yWorkaround1, false));
                allPoints.add(new MPoint(xWorkaround1, yWorkaround1, false));
                allPoints.add(new MPoint(xWorkaround1, yWorkaround2, false));
                allPoints.add(new MPoint(targetLocation.x, yWorkaround2, false));
            }
        } else {
            // 5 - Target is not N or S
            int yWorkaround1 = Math.min(sourceBounds.y, targetBounds.y) - this.workaroundMargin;
            allPoints.add(new MPoint(sourceLocation.x, yWorkaround1, false));
            allPoints.add(new MPoint(targetLocation.x, yWorkaround1, false));
        }
        
    }

    @objid ("98184f0f-8cdd-47bb-948f-4c0ff84107a7")
    private Direction getDirection(Point sourceLocation, Point targetLocation) {
        Direction targetAnchorOrientation;
        if (Math.abs(sourceLocation.x - targetLocation.x) > Math.abs(sourceLocation.y - targetLocation.y)) {
            if (sourceLocation.x < targetLocation.x) {
                targetAnchorOrientation = Direction.EAST;
            } else {
                targetAnchorOrientation = Direction.WEST;
            }
        } else if (sourceLocation.y < targetLocation.y) {
            targetAnchorOrientation = Direction.SOUTH;
        } else {
            targetAnchorOrientation = Direction.NORTH;
        }
        return targetAnchorOrientation;
    }

}
