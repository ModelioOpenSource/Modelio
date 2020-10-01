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

package org.modelio.diagram.elements.core.figures.geometry;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Geometric utility functions.
 * 
 * @author cmarin
 */
@objid ("7f7e9e76-1dec-11e2-8cad-001ec947c8cc")
public class GeomUtils {
    /**
     * Get the direction of the given point relative to the given rectangle. Will return one of the Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH directions. The rectangle diagonals are used as separation between directions.
     * 
     * @param p the point to evaluate
     * @param rect The rectangle
     * @return the direction or Direction.NONE if the point is the center of the rectangle.
     */
    @objid ("7f7e9e78-1dec-11e2-8cad-001ec947c8cc")
    public static Direction getDirection(Point p, Rectangle rect) {
        Point center = rect.getCenter();
        
        // to avoid division by zero
        if (p.x == center.x) {
            if (p.y == center.y)
                return Direction.NONE;
            else if (p.y >= center.y)
                return Direction.SOUTH;
            else
                return Direction.NORTH;
        }
        
        Point upright = rect.getTopRight();
        Point bottomLeft = rect.getBottomLeft();
        
        double a = (double) (center.y - upright.y) / (double) (center.x - upright.x);
        double b = (double) (center.y - p.y) / (double) (center.x - p.x);
        
        if (-a > b && b > a) {
            if (p.x >= center.x)
                return Direction.EAST;
            else
                return Direction.WEST;
        } else {
            if (p.y >= center.y)
                return Direction.SOUTH;
            else
                return Direction.NORTH;
        }
    }

    /**
     * Get the intersection between the rectangle and the line formed by the 2 given points
     * 
     * @param p1 first point of the line
     * @param p2 last point of the line. Also used as reference to choose the nearest intersection point.
     * @param r a rectangle
     * @return The intersection between the rectangle and the line.
     */
    @objid ("7f7e9e82-1dec-11e2-8cad-001ec947c8cc")
    public static Point getLineIntersection(Point p1, Point p2, Rectangle r) {
        if (r.isEmpty())
            return null;
        
        ArrayList<Point> pts = new ArrayList<>(4);
        pts.add(getLineIntersection(p1, p2, new LineSeg(r.getTopLeft(), r.getTopRight())));
        pts.add(getLineIntersection(p1, p2, new LineSeg(r.getTopLeft(), r.getBottomLeft())));
        pts.add(getLineIntersection(p1, p2, new LineSeg(r.getTopRight(), r.getBottomRight())));
        pts.add(getLineIntersection(p1, p2, new LineSeg(r.getBottomLeft(), r.getBottomRight())));
        return getNearestPoint(pts, p2);
    }

    /**
     * Get the nearest point from the given one among 2 candidates points.
     * 
     * @param origin first candidate
     * @param destination second candidate
     * @param p the point to evaluate
     * @return The nearest point
     */
    @objid ("7f8100c7-1dec-11e2-8cad-001ec947c8cc")
    public static Point getNearestPoint(Point origin, Point destination, Point p) {
        if (p.getDistance(origin) < p.getDistance(destination))
            return origin;
        else
            return destination;
    }

    /**
     * Return the point that is the nearest from the given reference point.
     * 
     * @param candidates Candidates points
     * @param ref Reference point
     * @return the nearest point or <tt>null</tt> if the collection is empty.
     */
    @objid ("7f8100d6-1dec-11e2-8cad-001ec947c8cc")
    public static Point getNearestPoint(Collection<Point> candidates, Point ref) {
        Point ret = null;
        int min = -1;
        
        for (Point p : candidates) {
            if (p != null) {
                int dist = ref.getDistance2(p);
                if (ret == null || dist < min) {
                    min = dist;
                    ret = p;
                }
            }
        }
        return ret;
    }

    /**
     * Get the intersection between the rectangle and the line formed by the 2 given points
     * 
     * @param p1 first point of the line
     * @param p2 last point of the line. Also used as reference to choose the nearest intersection point.
     * @param r a rectangle
     * @return The intersection between the rectangle and the line.
     */
    @objid ("7f8100e5-1dec-11e2-8cad-001ec947c8cc")
    public static Point getSegmentIntersection(Point p1, Point p2, Rectangle r) {
        if (r.isEmpty())
            return null;
        
        ArrayList<Point> pts = new ArrayList<>(4);
        pts.add(getSegmentsIntersection(p1, p2, r.getTopLeft(), r.getTopRight()));
        pts.add(getSegmentsIntersection(p1, p2, r.getTopLeft(), r.getBottomLeft()));
        pts.add(getSegmentsIntersection(p1, p2, r.getTopRight(), r.getBottomRight()));
        pts.add(getSegmentsIntersection(p1, p2, r.getBottomLeft(), r.getBottomRight()));
        return getNearestPoint(pts, p2);
    }

    /**
     * @param a a number
     * @param b another number
     * @return true if a and b have the same sign.
     */
    @objid ("7f8100f5-1dec-11e2-8cad-001ec947c8cc")
    private static boolean areSameSign(long a, long b) {
        return ((a ^ b) >= 0);
    }

    /**
     * Get the intersection between the given lines and the given segment.
     * @param segment
     * 
     * @param p1 first point of line
     * @param p2 other point of line
     * @return the intersection between the given line and the given segment.
     */
    @objid ("7f8100fc-1dec-11e2-8cad-001ec947c8cc")
    private static Point getLineIntersection(Point p1, Point p2, LineSeg segment) {
        LineSeg s1 = new LineSeg(p1, p2);
        PointList intersections = s1.getLinesIntersections(segment);
        
        if (intersections != null && intersections.size() > 0) {
            if (segment.containsPoint(intersections.getFirstPoint(), 0))
                return intersections.getFirstPoint();
        }
        return null;
    }

    /**
     * Get the intersection point between the p1-p2 segment and the p3-p4 segment.
     * 
     * @param p1 1st segment begin
     * @param p2 1st segment end
     * @param p3 2nd segment begin
     * @param p4 2nd segment end
     * @return the intersection between the 2 given segments.
     */
    @objid ("7f81010a-1dec-11e2-8cad-001ec947c8cc")
    private static Point getSegmentsIntersection(Point p1, Point p2, Point p3, Point p4) {
        long a1, a2, b1, b2, c1, c2; /* Coefficients of line eqns. */
        long r1, r2, r3, r4; /* 'Sign' values */
        long denom, offset, num; /* Intermediate values */
        
        /*
         * Compute a1, b1, c1, where line joining points 1 and 2 is "a1 x  +  b1 y  +  c1  =  0".
         */
        
        a1 = p2.y - p1.y;
        b1 = p1.x - p2.x;
        c1 = p2.x * p1.y - p1.x * p2.y;
        
        /*
         * Compute r3 and r4.
         */
        
        r3 = a1 * p3.x + b1 * p3.y + c1;
        r4 = a1 * p4.x + b1 * p4.y + c1;
        
        /*
         * Check signs of r3 and r4. If both point 3 and point 4 lie on same side of line 1, the line segments do not intersect.
         */
        
        if (r3 != 0 && r4 != 0 && areSameSign(r3, r4))
            return (null);
        
        /* Compute a2, b2, c2 */
        
        a2 = p4.y - p3.y;
        b2 = p3.x - p4.x;
        c2 = p4.x * p3.y - p3.x * p4.y;
        
        /* Compute r1 and r2 */
        
        r1 = a2 * p1.x + b2 * p1.y + c2;
        r2 = a2 * p2.x + b2 * p2.y + c2;
        
        /*
         * Check signs of r1 and r2. If both point 1 and point 2 lie on same side of second line segment, the line segments do not intersect.
         */
        
        if (r1 != 0 && r2 != 0 && areSameSign(r1, r2))
            return (null);
        
        /*
         * Line segments intersect: compute intersection point.
         */
        
        denom = a1 * b2 - a2 * b1;
        if (denom == 0)
            return (null);
        offset = denom < 0 ? -denom / 2 : denom / 2;
        
        /*
         * The denom/2 is to get rounding instead of truncating. It is added or subtracted to the numerator, depending upon the sign of the numerator.
         */
        
        Point ret = new Point();
        num = b1 * c2 - b2 * c1;
        ret.x = (int) ((num < 0 ? num - offset : num + offset) / denom);
        
        num = a2 * c1 - a1 * c2;
        ret.y = (int) ((num < 0 ? num - offset : num + offset) / denom);
        return ret;
    }

    /**
     * Convert the given direction to horizontal or vertical
     * 
     * @param anchorRelativeLocation PositionConstants.NORTH, PositionConstants.SOUTH, PositionConstants.EAST or PositionConstants.WEST
     * @return PositionConstants.HORIZONTAL or PositionConstants.VERTICAL.
     */
    @objid ("7f81011d-1dec-11e2-8cad-001ec947c8cc")
    public static Orientation getOrientation(final Direction anchorRelativeLocation) {
        if (anchorRelativeLocation == Direction.EAST || anchorRelativeLocation == Direction.WEST) {
            return Orientation.HORIZONTAL;
        } else if (anchorRelativeLocation == Direction.NORTH || anchorRelativeLocation == Direction.SOUTH) {
            return Orientation.VERTICAL;
        }
        return Orientation.NONE;
    }

}
