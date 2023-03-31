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
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Geometry;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Translatable;

/**
 * Geometric utility functions.
 * 
 * @author cmarin
 */
@objid ("7f7e9e76-1dec-11e2-8cad-001ec947c8cc")
public class GeomUtils {
    /**
     * Align rectangle center location with SnapToGeometry algorithm to avoid differences in midpoint locations.
     * @see org.eclipse.gef.SnapToGeometry#getCorrectionFor(Entry[], Map, boolean, double, double)
     * @since 5.3.1
     * @param rect the rectangle to align
     */
    @objid ("a4430f6e-c879-448c-be99-742498e0b74d")
    @SuppressWarnings ("javadoc")
    public static void alignRectangleWithSnapToGeometry(PrecisionRectangle rect) {
        rect.resize( -1.0, -1.0);
        
        // If the width is even (i.e., odd right now because we have reduced one pixel from
        // far) there is no middle pixel so favor the left-most/top-most pixel
        // (which is what SnapToGeometry.populateRowsAndCols() does by using int precision).
        if ((int) (rect.preciseWidth()) % 2 != 0)
            rect.resize( -1.0, 0);
        
        if ((int) (rect.preciseHeight()) % 2 != 0)
            rect.resize(0, -1.0);
        
    }

    /**
     * Align rectangle center location with SnapToGeometry algorithm to avoid differences in midpoint locations.
     * @see org.eclipse.gef.SnapToGeometry#getCorrectionFor(Entry[], Map, boolean, double, double)
     * @since 5.3.1
     * @param rect the rectangle to align
     */
    @objid ("c6319cab-01c9-44d4-b305-49a8014c9a81")
    @SuppressWarnings ("javadoc")
    public static void alignRectangleWithSnapToGeometry(Rectangle rect) {
        rect.resize( -1, -1);
        
        // If the width is even (i.e., odd right now because we have reduced one pixel from
        // far) there is no middle pixel so favor the left-most/top-most pixel
        // (which is what SnapToGeometry.populateRowsAndCols() does by using int precision).
        if ((rect.width()) % 2 != 0)
            rect.resize( -1, 0);
        
        if ((rect.height()) % 2 != 0)
            rect.resize(0, -1);
        
    }

    /**
     * Correct the point position so that it is inside or touches the given rectangle.
     * @param p a point
     * @param r the point location limits.
     */
    @objid ("7f5adb31-1dec-11e2-8cad-001ec947c8cc")
    public static void forcePointInside(final Point p, final Rectangle r) {
        p.x = Math.max(p.x(), r.x());
        p.x = Math.min(p.x(), r.right());
        p.y = Math.max(p.y(), r.y());
        p.y = Math.min(p.y(), r.bottom());
        
    }

    /**
     * Get the direction of the given point relative to the given rectangle. Will return one of the Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH directions. The rectangle diagonals are used as separation between directions.
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
     * Convert the given direction to horizontal or vertical
     * @param anchorRelativeLocation PositionConstants.NORTH, PositionConstants.SOUTH, PositionConstants.EAST or PositionConstants.WEST
     * @return PositionConstants.HORIZONTAL or PositionConstants.VERTICAL.
     * @deprecated use {@link Direction#orientation()}.
     */
    @objid ("7f81011d-1dec-11e2-8cad-001ec947c8cc")
    @Deprecated
    public static Orientation getOrientation(final Direction anchorRelativeLocation) {
        return anchorRelativeLocation.orientation();
    }

    /**
     * Get the intersection between the rectangle and the line formed by the 2 given points
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
     * Determines whether any of the line segment represented by the two points
     * intersect the given Rectangle. If the segment touches the given rectangle,
     * that's considered intersection.
     * @param p1 the segment start
     * @param p2 the segment end
     * @param r the rectangle
     * @return <code>true</code> if the given rectangle intersects the
     * segments represented by the two points
     * @since 5.1.0
     */
    @objid ("f9dd8e15-99d9-46da-ad8a-5aaf05bc411e")
    public static boolean segmentIntersects(Point p1, Point p2, Rectangle r) {
        if (r.isEmpty())
            return false;
        if (r.contains(p1) || r.contains(p2))
            return true;
        
        int diagonal1x1 = r.x;
        int diagonal1y1 = r.y;
        int diagonal1x2 = r.x + r.width - 1;
        int diagonal1y2 = r.y + r.height - 1;
        int diagonal2x1 = r.x + r.width - 1;
        int diagonal2y1 = r.y;
        int diagonal2x2 = r.x;
        int diagonal2y2 = r.y + r.height - 1;
        return (Geometry.linesIntersect(diagonal1x1, diagonal1y1, diagonal1x2, diagonal1y2, p1.x(), p1.y(), p2.x(), p2.y())
                || Geometry.linesIntersect(diagonal2x1, diagonal2y1, diagonal2x2, diagonal2y2, p1.x(), p1.y(), p2.x(), p2.y()));
        
    }

    /**
     * Return equivalent of {@link Rectangle#toString()} with topleft and bottom right points
     * instead of top left and size.
     * @param r a rectangle
     * @return a dump of the rectangle
     */
    @objid ("500a42e1-7297-48db-bf19-a8f61e1d2074")
    public static String toString(Rectangle r) {
        return r.getClass().getSimpleName() + "["
                + r.preciseX()
                + ", " + r.preciseY()
                + " --> " + (r.preciseX() + r.preciseWidth())
                + ", " + (r.preciseY() + r.preciseHeight()) + "]";
        
    }

    /**
     * Translate a point/rectangle/... toward a given direction.
     * @param t a translatable thing
     * @param dir the direction
     * @param distance the distance
     */
    @objid ("cb44b62b-ab23-40e3-a7d2-78a238f06101")
    public static void translate(Translatable t, Direction dir, int distance) {
        switch (dir) {
        case EAST:
            t.performTranslate(distance, 0);
            break;
        case NONE:
            return;
        case NORTH:
            t.performTranslate(0, -distance);
            break;
        case SOUTH:
            t.performTranslate(0, distance);
            break;
        case WEST:
            t.performTranslate(-distance, 0);
            break;
        default:
            throw new UnsupportedOperationException(dir.toString());
        }
        
    }

    /**
     * No instance
     */
    @objid ("2894b6f9-26c1-4875-a1d8-e1c6d0a9b511")
    private  GeomUtils() {
        
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
     * @param p1 first point of line
     * @param p2 other point of line
     * @param segment a segment
     * @return the intersection between the given line and the given segment.
     */
    @objid ("7f8100fc-1dec-11e2-8cad-001ec947c8cc")
    private static Point getLineIntersection(Point p1, Point p2, LineSeg segment) {
        LineSeg s1 = new LineSeg(p1, p2);
        PointList intersections = s1.getLinesIntersections(segment);
        
        if (intersections != null
                && intersections.size() > 0
                && segment.containsPoint(intersections.getFirstPoint(), 0)) {
            return intersections.getFirstPoint();
        }
        return null;
    }

    /**
     * Get the intersection point between the p1-p2 segment and the p3-p4 segment.
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
        
        a1 = (long)p2.y - p1.y;
        b1 = (long)p1.x - p2.x;
        c1 = (long)p2.x * p1.y - p1.x * p2.y;
        
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
        
        a2 = (long)p4.y - p3.y;
        b2 = (long)p3.x - p4.x;
        c2 = (long)p4.x * p3.y - p3.x * p4.y;
        
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

}
