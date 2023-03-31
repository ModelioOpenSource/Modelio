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
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Ray;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Translatable;

/**
 * This is a geometric utility class that allows for manipulation of line segments. A line segment is defined as a set of two points where one point is designated as the origin and the other is the terminal.
 * 
 * @author sshaw
 */
@objid ("7f836324-1dec-11e2-8cad-001ec947c8cc")
public class LineSeg implements Cloneable, java.io.Serializable, Translatable {
    @objid ("7f836328-1dec-11e2-8cad-001ec947c8cc")
    private static final int DEFAULT_INTERSECTION_TOLERANCE = 1;

    @objid ("7f836330-1dec-11e2-8cad-001ec947c8cc")
    static final long serialVersionUID = 1;

    /**
     * Constant to avoid divide by zero errors.
     */
    @objid ("7f836332-1dec-11e2-8cad-001ec947c8cc")
    private static final float BIGSLOPE = 9999;

    @objid ("630a9879-1e83-11e2-8cad-001ec947c8cc")
    private final Point origin;

    @objid ("630a987a-1e83-11e2-8cad-001ec947c8cc")
    private final Point terminus;

    /**
     * Returns the coefficients of the generalized equation of the line passing through points (x1,y1) and (x2,y2) Generalized line equation: ax+by=c => a==result[0], b==result[1], c==result[2]
     * @param x1 - x coordinate of the 1st point
     * @param y1 - y coordinate of the 1st point
     * @param x2 - x coordinate of the 2nd point
     * @param y2 - y coordinate of the 2nd point
     * @return the coefficients of the generalized equation of the line passing through points (x1,y1) and (x2,y2)
     */
    @objid ("7f836335-1dec-11e2-8cad-001ec947c8cc")
    public static double[] getLineEquation(double x1, double y1, double x2, double y2) {
        double equation[] = new double[3];
        for (int i = 0; i < 3; i++) {
            equation[i] = 0;
        }
        
        if (x1 == x2 && y1 == y2) {
            return equation;
        }
        
        if (x1 == x2) {
            equation[0] = 1;
            equation[1] = 0;
            equation[2] = x1;
            return equation;
        }
        
        equation[0] = (y1 - y2) / (x2 - x1);
        equation[1] = 1.0;
        equation[2] = y2 + equation[0] * x2;
        return equation;
    }

    /**
     * Constructor
     * @param ptStart Point indicating the start of the line segment
     * @param ptEnd Point indicating the end of the line segment
     */
    @objid ("7f836340-1dec-11e2-8cad-001ec947c8cc")
    public  LineSeg(Point ptStart, Point ptEnd) {
        this.origin = ptStart.getCopy();
        this.terminus = ptEnd.getCopy();
        
    }

    /**
     * Creates a segment using (fromX, fromY) as either the first point of the segment (start == Origin) or the midpoint of the segment (start == Midpoint), and using slope as its new slope and len as the new length. xdir indicates which direction the
     * segment should go in the x-axis.
     * @param start <code>KeyPoint</code> from which the other parameters are relative to
     * @param fromX int x value of start <code>KeyPoint</code>
     * @param fromY int y value of start <code>KeyPoint</code>
     * @param slope <code>float</code> slope of the line
     * @param len <code>long</code> length of the line
     * @param xdir direction
     */
    @objid ("7f836349-1dec-11e2-8cad-001ec947c8cc")
    public  LineSeg(final KeyPoint start, final int fromX, final int fromY, final float slope, final long len, final int xdir) {
        super();
        
        this.origin = new Point();
        this.terminus = new Point();
        
        int dx, dy;
        float dx_float;
        double len_squared;
        
        // Find the delta y and x needed to get to the end points. See
        // pointOn() for explanation of these equations
        if (start == KeyPoint.ORIGIN) {
            len_squared = (float) len * (float) len;
        } else // start == DirectedLine::Midpoint
        {
            len_squared = len / 2.0 * len / 2.0;
        }
        
        double slope_squared = slope * slope;
        dx_float = (float) Math.sqrt(len_squared / (slope_squared + 1.0));
        
        // Set which direction the segment should go in the x direction.
        // The y direction will get set automatically based on slope
        // and the dx.
        
        dx_float *= xdir;
        dx = (int) (dx_float + 0.5);
        
        dy = (int) ((slope * dx_float) + 0.5);
        
        if (start == KeyPoint.ORIGIN) {
            this.origin.x = fromX;
            this.origin.y = fromY;
        } else // start == DirectedLine::Midpoint
        {
            this.origin.x = fromX - dx;
            this.origin.y = fromY - dy;
        }
        
        this.terminus.x = fromX + dx;
        this.terminus.y = fromY + dy;
        
    }

    /**
     * Checks if this line segment contains the given point within a tolerance value.
     * @param aPoint <code>Point</code> to test if contained in this line.
     * @param tolerance int tolerance value for detecting the intersection.
     * @return <code>boolean</code> <code>true</code> if the given point lies on this segment, <code>false</code> otherwise.
     */
    @objid ("7f836358-1dec-11e2-8cad-001ec947c8cc")
    public final boolean containsPoint(final Point aPoint, final int tolerance) {
        /*
         * We need perform the calculations in double numbers to avoid possible integer overflows in Point#getDistance2() method
         */
        double lengthOfSegment = this.origin.getDistance(this.terminus);
        double lengthFromOriginToPoint = this.origin.getDistance(aPoint);
        double lengthFromTerminusToPoint = this.terminus.getDistance(aPoint);
        return lengthFromTerminusToPoint + lengthFromOriginToPoint - lengthOfSegment <= tolerance;
    }

    /**
     * Finds the percentage distance along this line segement where the given point resides.
     * @param coord <code>Point</code> to determine how far along the line segment it resides.
     * @return <code>float</code> the distance along the line segment where the ptCoord is in a percentage from.
     */
    @objid ("7f836363-1dec-11e2-8cad-001ec947c8cc")
    public final float distanceAlong(Point coord) {
        int xCoord = coord.x;
        int yCoord = coord.y;
        
        /*
         * Use parametric form for equation of a line segment: p + td, where 0 < t < 1 and d = p2 - p (direction vector)
         *
         * To find out if point lies "inside" line segment (i.e. can draw perpendicular line from segment to point), use projection of point (q) to line (p + td): t = (q-p).d/length(d)^2 (. is dot product)
         */
        
        /* get the direction vector */
        long dirx = (long) this.terminus.x - (long) this.origin.x;
        long diry = (long) this.terminus.y - (long) this.origin.y;
        
        /* get q - p */
        long qpx = (long) xCoord - (long) this.origin.x;
        long qpy = (long) yCoord - (long) this.origin.y;
        
        /* dot product of (q-p) and d */
        long dotprod = qpx * dirx + qpy * diry;
        
        /*
         * avoid divide by 0 - check if point1 equals point2. If so, there is no segment - return a value which indicates projection falls outside the segment.
         */
        if (dirx == 0 && diry == 0) {
            return -1;
        }
        
        /*
         * length (magnitude) of d is sqrt(dirx^2 + diry^2). Don't bother taking square root since we want the length squared.
         */
        return ((float) dotprod / (float) (dirx * dirx + diry * diry));
    }

    /**
     * Finds the perpendicular distance from a point coordinates to this line segment. If point is "inside" line segment, then use distance from point to the line, otherwise use distance to nearest endpoint of segment
     * @param xCoord the x coordinate of the point.
     * @param yCoord the y coordinate of the point.
     * @return <code>long</code> the distance from the line segment to the given point.
     */
    @objid ("7f85c579-1dec-11e2-8cad-001ec947c8cc")
    public final long distanceToPoint(final int xCoord, final int yCoord) {
        double proj = projection(xCoord, yCoord);
        
        if (proj > 0 && proj < 1) {
            Point pt = perpIntersect(xCoord, yCoord);
            return Math.round(pt.getDistance(new Point(xCoord, yCoord)));
        }
        
        long d1 = Math.round(getOrigin().getDistance(new Point(xCoord, yCoord)));
        long d2 = Math.round(getTerminus().getDistance(new Point(xCoord, yCoord)));
        return (d1 < d2 ? d1 : d2);
        /*
         * There are 2 general forms to equations of a line:
         * 1. y = mx + c, where c = y1 - m(x1),
         * and 2. ax + by + c = 0
         *
         * We know m and c, so putting first version in the form of the second version, we get:
         * mx - y + c = 0
         * So, for form 2, a = m, b = -1, and
         * c = y1 - m(x1).
         * Distance from point (x, y) to line (using form 2) is:
         *  |ax + by + c| / sqrt(a^2 + b^2) or
         *  |mx - y + y1 - m(x1)| / sqrt(m^2 + 1)
         */
        
    }

    @objid ("7f85c582-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean equals(Object seg) {
        if (!(seg instanceof LineSeg)) {
            return false;
        }
        
        LineSeg ls = (LineSeg) seg;
        return getOrigin().equals(ls.getOrigin()) && getTerminus().equals(ls.getTerminus());
    }

    /**
     * Returns array with 3 numbers in it, which are the coefficients of the generalized line equation
     * of the line corresponding to this line segment
     * <p>
     * a*x+b*y=c is the equation => result[0]=a, result[1]=b, result[2]=c
     * @return an array with 3 numbers in it, which are the coefficients of the generalized line equation
     */
    @objid ("7f85c588-1dec-11e2-8cad-001ec947c8cc")
    public double[] getEquation() {
        PrecisionPoint preciseOrigin = new PrecisionPoint(this.origin);
        PrecisionPoint preciseTerminus = new PrecisionPoint(this.terminus);
        return getLineEquation(preciseOrigin.preciseX(),
                        preciseOrigin.preciseY(),
                        preciseTerminus.preciseX(),
                        preciseTerminus.preciseY());
        
    }

    /**
     * Get a <code>Point</code> representing the lowest point value for this line segment.
     * @return <code>Point</code> Representing the lowest point value.
     */
    @objid ("7f85c58f-1dec-11e2-8cad-001ec947c8cc")
    public final Point getInfimum() {
        return new Point(Math.min(this.origin.x, this.terminus.x), Math.min(this.origin.y, this.terminus.y));
    }

    /**
     * Calculates intersection points of the line of the line segment and ellipse
     * @param ellipseBounds - width and height of the ellipse
     * @return - <Code>PointList</Code> containing all intersection points
     */
    @objid ("7f85c596-1dec-11e2-8cad-001ec947c8cc")
    public PointList getLineIntersectionsWithEllipse(Rectangle ellipseBounds) {
        PointList intersections = new PrecisionPointList();
        PrecisionPoint preciseOrigin = new PrecisionPoint(this.origin);
        PrecisionPoint preciseTerminus = new PrecisionPoint(this.terminus);
        PrecisionRectangle preciseEllipseBounds = new PrecisionRectangle(ellipseBounds);
        if (preciseEllipseBounds.preciseWidth() == 0 || preciseEllipseBounds.preciseHeight() == 0) {
            return intersections;
        }
        PrecisionPoint ellipsePreciseCenter = new PrecisionPoint(preciseEllipseBounds.getCenter());
        double xl1 = preciseOrigin.preciseX() - ellipsePreciseCenter.preciseX();
        double xl2 = preciseTerminus.preciseX() - ellipsePreciseCenter.preciseX();
        double yl1 = preciseOrigin.preciseY() - ellipsePreciseCenter.preciseY();
        double yl2 = preciseTerminus.preciseY() - ellipsePreciseCenter.preciseY();
        double[] equation = LineSeg.getLineEquation(xl1, yl1, xl2, yl2);
        
        if (equation.length < 3 || (equation[0] == 0 && equation[1] == 0)) {
            return intersections;
        }
        
        double a = equation[0];
        double b = equation[1];
        double c = equation[2];
        double w = preciseEllipseBounds.preciseWidth();
        double h = preciseEllipseBounds.preciseHeight();
        
        // Ellipse with a center at the origin has an equation:
        // (h*x)^2+(w*y)^2=(h*w/2)^2
        // Line equation: a*x+b*y=c
        
        if (b == 0) {
            // b==0 is a special case since in general case we will express
            // y in terms of x, i.e. we need to divide by b, which should not
            // be 0
            // b==0 => a*x=c +> x=c/a;
            double x = c / a;
            // y^2 = (h/2)^2-((h*c)/(a*w))^2
            double y = Math.pow(h / 2, 2) - Math.pow((h * c) / (a * w), 2);
            if (y < 0) {
                return intersections;
            }
            intersections.addPoint(new PrecisionPoint(x + ellipsePreciseCenter.preciseX(),
                    Math.sqrt(y) + ellipsePreciseCenter.preciseY()));
            intersections.addPoint(new PrecisionPoint(x + ellipsePreciseCenter.preciseX(),
                    -Math.sqrt(y) + ellipsePreciseCenter.preciseY()));
        } else {
            // y = (c-a*x)/b => we get quadratic equation for x
            // x^2*(h^2+(w*a/b)^2)-x*(2*w^2*a*c)/(b^2)+((w*c/b)^2-(h*w/2)^2)=0 or
            // x^2*xA+x*xB+xC=0
            double xA = Math.pow(h, 2) + Math.pow((w * a) / b, 2);
            double xB = (-2) * Math.pow(w, 2) * a * c / Math.pow(b, 2);
            double xC = Math.pow(w * c / b, 2) - Math.pow(h * w / 2, 2);
            double xD = Math.pow(xB, 2) - 4 * xA * xC;
        
            if (xD < 0) {
                return intersections;
            }
        
            double x1 = (-xB + Math.sqrt(xD)) / (2 * xA);
            double x2 = (-xB - Math.sqrt(xD)) / (2 * xA);
            intersections.addPoint(new PrecisionPoint(x1 + ellipsePreciseCenter.preciseX(),
                    (c - a * x1) / b + ellipsePreciseCenter.preciseY()));
            intersections.addPoint(new PrecisionPoint(x2 + ellipsePreciseCenter.preciseX(),
                    (c - a * x2) / b + ellipsePreciseCenter.preciseY()));
        }
        return intersections;
    }

    @objid ("10737b5e-799e-4b6a-b72a-7549f09bca73")
    public LineSeg setValues(PointList from, int index1, int index2) {
        from.getPoint(this.origin, index1);
        from.getPoint(this.terminus, index2);
        return this;
    }

    /**
     * Calculates intersection points of the line that contains this line segment with a list of other line segments. If the list of points (line segments) form a closed <Code>PolyLine</Code>, i.e form a closed polygon figure, then the method will
     * Calculate intersections of a line and a figure
     * @param points - list of points that form line segments, i.e the <Code>PolyLine</Code>
     * @return the intersection points of the line that contains this line segment with a list of other line segments.
     */
    @objid ("7f85c5a0-1dec-11e2-8cad-001ec947c8cc")
    public PointList getLineIntersectionsWithLineSegs(final PointList points) {
        PointList intersections = new PrecisionPointList();
        if (points.size() < 2) {
            Point firstPoint = points.getFirstPoint();
            if (containsPoint(firstPoint, LineSeg.DEFAULT_INTERSECTION_TOLERANCE)) {
                intersections.addPoint(firstPoint);
            }
        } else {
            LineSeg seg = new LineSeg(this.origin, this.terminus);
            for (int i = 0; i < points.size() - 1; i++) {
                seg.setValues(points, i, i+1);
                PointList currentIntersections = getLinesIntersections(seg);
                for (int j = 0; j < currentIntersections.size(); j++) {
                    Point intersection = currentIntersections.getPoint(j);
                    if (seg.containsPoint(intersection, LineSeg.DEFAULT_INTERSECTION_TOLERANCE)) {
                        intersections.addPoint(currentIntersections.getPoint(j));
                    }
                }
            }
        }
        return intersections;
    }

    /**
     * Returns intersection points of two lines that contain this line segment and the argumet line segment. The list of intersection points may contain at most two points and will contain 2 points if and only if the lines are equal. The 2 points will be
     * the end points of the parameter line segment
     * @param line - the line segment
     * @return intersection points of two lines that contain this line segment and the argumet line segment.
     */
    @objid ("7f85c5ab-1dec-11e2-8cad-001ec947c8cc")
    public PointList getLinesIntersections(LineSeg line) {
        PrecisionPointList intersections = new PrecisionPointList();
        double temp[] = getEquation();
        double a1 = temp[0];
        double b1 = temp[1];
        double c1 = temp[2];
        
        temp = line.getEquation();
        double a2 = temp[0];
        double b2 = temp[1];
        double c2 = temp[2];
        // Cramer's rule for the system of linear equations
        double det = a1 * b2 - b1 * a2;
        if (det == 0) {
            if (a1 == a2 && b1 == b2 && c1 == c2) {
                List<Point> points = new ArrayList<>(4);
                points.add(getOrigin());
                points.add(getTerminus());
                points.add(line.getOrigin());
                points.add(line.getTerminus());
                Collections.sort(points, (arg0, arg1) -> {
                    if (arg0.equals(arg1)) {
                        return 0;
                    } else if (arg0.preciseX() < arg1.preciseX() ||
                            (arg0.preciseX() == arg1.preciseX() && arg0.preciseY() < arg1.preciseY())) {
                        return -1;
                    } else {
                        return 1;
                    }
                });
                // if lines are the same, then instead of infinite number of intersections
                // we will put the 2 points out of 4 points - ends of 2 segments. Look above.
                intersections.addPoint(points.get(1));
                intersections.addPoint(points.get(2));
            }
        } else {
            intersections.addPrecisionPoint((c1 * b2 - b1 * c2) / det, (a1 * c2 - c1 * a2) / det);
        }
        return intersections;
    }

    /**
     * Accessor to retrieve the origin point of the line segment.
     * @return <code>Point</code> the origin of the line segment, by copy.
     */
    @objid ("7f85c5b3-1dec-11e2-8cad-001ec947c8cc")
    public Point getOrigin() {
        return this.origin.getCopy();
    }

    /**
     * Accessor to retrieve the origin point of the line segment.
     * @param out the point to fill with the origin
     * @return <code>out</code> filled with the origin of the line segment.
     */
    @objid ("133ef952-890c-4382-bbb3-1eb1faa18b01")
    public Point getOrigin(Point out) {
        return out.setLocation(this.origin);
    }

    /**
     * Returns a new <code>LineSeg</code> that is parallel to this by the given distance. Orientation is relative to the start and end. Negative implies to the left and Position implies to the right.
     * @param ptLoc <code>Point</code> value to constrain the line to.
     * @return <code>LineSeg</code> line that was calculated going through the given point
     */
    @objid ("7f85c5ba-1dec-11e2-8cad-001ec947c8cc")
    public final LineSeg getParallelLineSegThroughPoint(Point ptLoc) {
        if (isHorizontal()) {
            return new LineSeg(new Point(getOrigin().x, ptLoc.y), new Point(getTerminus().x, ptLoc.y));
        } else if (isVertical()) {
            return new LineSeg(new Point(ptLoc.x, getOrigin().y), new Point(ptLoc.x, getTerminus().y));
        } else {
            Point ptProj = perpIntersect(ptLoc.x, ptLoc.y);
            long nHeight = Math.round(ptProj.getDistance(ptLoc));
            Sign position = positionRelativeTo(ptLoc);
        
            return new LineSeg(locatePoint(0.0, nHeight, position), locatePoint(1.0, nHeight, position));
        }
        
    }

    /**
     * Get points representing the highest point value for this line segment.
     * @return <code>Point</code> Representing the highest point value.
     */
    @objid ("7f85c5c1-1dec-11e2-8cad-001ec947c8cc")
    public final Point getSupremum() {
        return new Point(Math.max(this.origin.x, this.terminus.x), Math.max(this.origin.y, this.terminus.y));
    }

    /**
     * Accessor to retrieve the terminal point of the line segment.
     * @return <code>Point</code> the terminating point of the line segment
     */
    @objid ("7f85c5c8-1dec-11e2-8cad-001ec947c8cc")
    public Point getTerminus() {
        return this.terminus.getCopy();
    }

    /**
     * Accessor to retrieve the terminal point of the line segment.
     * @return <code>Point</code> the terminating point of the line segment
     */
    @objid ("485a7166-de4f-4342-a7ab-3241ecd7b643")
    public Point getTerminus(Point out) {
        return out.setLocation(this.terminus);
    }

    /**
     * Gets the trig values associated with the angle from this line segment to the given vector.
     * @param ptToVector <code>Ray</code> value to calculate trig values of.
     * @return <code>TrigValues</code> object representing the trigonometry values for the angle of the passed in <code>Ray</code> relative to <code>this</code> or null if calculation is not possible,
     */
    @objid ("7f85c5cf-1dec-11e2-8cad-001ec947c8cc")
    public TrigValues getTrigValues(final Ray ptToVector) {
        double dFromLength = length();
        double dToLength = ptToVector.length();
        
        Ray ptFromVector = new Ray(getOrigin(), getTerminus());
        
        if (dFromLength <= 0 || dToLength <= 0) {
            return null;
        }
        
        // 1. find angle for ptToVector relative to the origin.
        double dAlpha;
        double dCosAlpha, dSinAlpha;
        
        dCosAlpha = ptFromVector.x / dFromLength;
        dSinAlpha = ptFromVector.y / dFromLength;
        dAlpha = Math.atan2(dSinAlpha, dCosAlpha);
        
        // 2. inverse the angle to get the rotation
        dCosAlpha = Math.cos(-dAlpha);
        dSinAlpha = Math.sin(-dAlpha);
        
        // 3. rotate vector 2 by angle above so that it's angle relative to vector 1 can
        // be calculated
        double dRotateX = (ptToVector.x * dCosAlpha) - (ptToVector.y * dSinAlpha);
        double dRotateY = (ptToVector.x * dSinAlpha) + (ptToVector.y * dCosAlpha);
        
        // 4. Now calculate the Theta trig values
        TrigValues val = new TrigValues();
        val.cosTheta = dRotateX / dToLength;
        val.sinTheta = dRotateY / dToLength;
        return val;
    }

    @objid ("7f8827d2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int hashCode() {
        return getOrigin().hashCode() ^ getTerminus().hashCode();
    }

    /**
     * Determines the intersect point between this line and the line passed in as a parameter. If they intersect, then true is returned and the point reference passed in will be set to the intersect point. If they don't intersect, then the method returns
     * <code>false</code>.
     * @param line <code>LineSeg</code> to test the intersection against.
     * @param nTolerance int tolerance value for detecting the intersection.
     * @return <code>Point</code> that represents the intersection with this line, or <code>null</code> if the calculation is not possible.
     */
    @objid ("7f8827d7-1dec-11e2-8cad-001ec947c8cc")
    public Point intersect(final LineSeg line, final int nTolerance) {
        PointList intersections = getLinesIntersections(line);
        if (intersections.size() > 1) {
            intersections.addPoint(getOrigin());
            intersections.addPoint(getTerminus());
        }
        for (int i = 0; i < intersections.size(); i++) {
            Point result = intersections.getPoint(i);
            if (containsPoint(result, nTolerance) && line.containsPoint(result, nTolerance)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Determines if this a horizontal segment
     * @return <code>boolean</code> <code>true</code> if horizontal, <code>false</code> otherwise.
     */
    @objid ("7f8827e2-1dec-11e2-8cad-001ec947c8cc")
    public final boolean isHorizontal() {
        return (this.origin.y == this.terminus.y);
    }

    /**
     * Determines if this a vertical segment
     * @return <code>boolean</code> <code>true</code> if vertical, <code>false</code> otherwise.
     */
    @objid ("7f8827e7-1dec-11e2-8cad-001ec947c8cc")
    public final boolean isVertical() {
        return (this.origin.x == this.terminus.x);
    }

    /**
     * Calculate the length of the line segment.
     * @return the <code>double</code> length of the line segment.
     */
    @objid ("7f8827ec-1dec-11e2-8cad-001ec947c8cc")
    public final double length() {
        return getOrigin().getDistance(getTerminus());
    }

    /**
     * Locates a point at a given height and distance along the line segment. B (the point we are looking for) + | dist |h this segment P1-----------+-------------------> A get point A (on picture above)
     * @param pctDist <code>double</code> distance along the line
     * @param theHeight <code>long</code> height above the line
     * @param asOriented <code>Sign</code> indicating relative position of the point to be located
     * @return <code>Point</code> value that was located on the line.
     */
    @objid ("7f8827f1-1dec-11e2-8cad-001ec947c8cc")
    public final Point locatePoint(final double pctDist, final long theHeight, final Sign asOriented) {
        int xdir;
        int dist = (int) (pctDist * length());
        Point pt = new Point();
        pointOn(dist, KeyPoint.ORIGIN, pt); // (x,y) now = A
        
        // get linesegment AB
        // first determine the direction AB should go in the x axis. Don't ask-
        // just have faith.
        
        if (getOrigin().y > getTerminus().y ||
                (getOrigin().y == getTerminus().y && getOrigin().x < getTerminus().x)) {
            xdir = (asOriented == Sign.POSITIVE ? -1 : 1);
        } else {
            xdir = (asOriented == Sign.POSITIVE ? 1 : -1);
        }
        
        LineSeg linesegAB = new LineSeg(KeyPoint.ORIGIN, pt.x, pt.y, perpSlope(), theHeight, xdir);
        return (new Point(linesegAB.getTerminus().x, linesegAB.getTerminus().y));
    }

    @objid ("7f8827fe-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void performScale(double factor) {
        setOrigin(getOrigin().scale(factor));
        setTerminus(getTerminus().scale(factor));
        
    }

    @objid ("7f882801-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void performTranslate(int dx, int dy) {
        setOrigin(getOrigin().translate(dx, dy));
        setTerminus(getTerminus().translate(dx, dy));
        
    }

    /**
     * Calculates the perpendicular intersection point on the line segment from the given point.
     * @param startX the x coordinate of the point
     * @param startY the y coordinate of the point
     * @return <code>Point</code> value containment the perpendicular intersection point.
     */
    @objid ("7f882805-1dec-11e2-8cad-001ec947c8cc")
    public final Point perpIntersect(final int startX, final int startY) {
        float fx;
        
        // The following equations are based on solving 2 equations with
        // 2 unknowns (x and y). The 2 equations are equations for the
        // slope of each line segment where the slope and 1 point in the
        // segment are known:
        // (y1 - y) / (x1 - x) = m
        // (sy - y) / (sx - x) = -1/m (-1/m is slope of perp. line)
        //
        Point ptResult = new Point();
        float m = slope();
        
        fx = (m * startY - m * getOrigin().y + m * m * getOrigin().x + startX) / (float) (m * m + 1.0);
        
        if (m == 0) {
            ptResult.y = getOrigin().y; // segment is horizontal - avoid divide by 0
        } else {
            ptResult.y = (int) (startY + ((startX - fx) / m) + 0.5);
        }
        
        ptResult.x = Math.round(fx); // add .5 for rounding
        return ptResult;
    }

    /**
     * Calculates the perpendicular slope of this line segment. This calculates the slope and then inverts it. Again, to avoid divide by zero errors, the constant <code>BIGSLOPE</code> is returned if the calculated slope before inverting it was zero.
     * @return <code>float</code> the perpendicular slope value of the line segment.
     */
    @objid ("7f882810-1dec-11e2-8cad-001ec947c8cc")
    public final float perpSlope() {
        float m = slope();
        if (m == 0.0) {
            return LineSeg.BIGSLOPE;
        } else {
            return -(1.0F / m);
        }
        
    }

    /**
     * Gets the point on the line segment at the given distance away from the key point.
     * @param theDistance <code>long</code> distance along the line
     * @param fromKeyPoint <code>KeyPoint</code> to calculate the distance from
     * @param ptResult <code>Point</code> where the resulting calculating value is stored.
     * @return <code>boolean</code> <code>true</code> if point can be calculated, <code>false</code> otherwise.
     */
    @objid ("7f882814-1dec-11e2-8cad-001ec947c8cc")
    public final boolean pointOn(final long theDistance, final KeyPoint fromKeyPoint, Point ptResult) {
        float m, dx_float;
        int dx, dy, startX = 0, startY = 0, otherX = 0, otherY = 0;
        
        // Set the point to offset from and the other point used to determine
        // which direction dx and dy should be applied to get a point on the
        // line.
        
        if (fromKeyPoint == KeyPoint.ORIGIN) {
            startX = getOrigin().x;
            startY = getOrigin().y;
            otherX = getTerminus().x;
            otherY = getTerminus().y;
        } else if (fromKeyPoint == KeyPoint.TERMINUS) {
            startX = getTerminus().x;
            startY = getTerminus().y;
            otherX = getOrigin().x;
            otherY = getOrigin().y;
        } else if (fromKeyPoint == KeyPoint.MIDPOINT) {
            startX = (getOrigin().x + getTerminus().x) / 2;
            startY = (getOrigin().y + getTerminus().y) / 2;
            otherX = getTerminus().x;
            otherY = getTerminus().y;
        } else {
            return false;
        }
        
        m = slope(); // get the slope of this line
        
        // Find dx and dy - the delta x and y to get from the endpoint to the
        // point on the line at the specified distance away.
        // The following is based on solving 2 equations with 2 unknowns:
        // dy/dx = m (m is slope of line)
        // dy^2 + dx^2 = dist^2
        //
        double d_squared = (float) theDistance * (float) theDistance;
        double m_squared = m * m;
        
        // Add .5 so result is rounded to nearest integer when cast
        dx_float = (float) Math.sqrt(d_squared / (m_squared + 1.0));
        dx = (int) (dx_float + 0.5);
        dy = (int) (Math.sqrt(d_squared * m_squared / (m_squared + 1.0)) + 0.5);
        
        /* negative distance means we want point off the line */
        if (theDistance < 0) {
            dx = -dx;
            dy = -dy;
        }
        
        ptResult.x = ((startX > otherX) ? startX - dx : startX + dx);
        ptResult.y = ((startY > otherY) ? startY - dy : startY + dy);
        boolean in_line;
        if (startX > otherX) {
            in_line = ptResult.x >= otherX;
        } else {
            in_line = ptResult.x <= otherX;
        }
        if (in_line) {
            if (startY > otherY) {
                in_line = ptResult.y >= otherY;
            } else {
                in_line = ptResult.y <= otherY;
            }
        }
        return in_line;
    }

    /**
     * Returns out a positive or negative value (Positive / Negative) depending on the orientation of the given point to the line. Point on this side: Positive. P1------------------------------> this segment Point on this side: Negative.
     * @param rel <code>Point</code> to test the relative position against this line.
     * @return <code>Sign</code> value indicating the relative position of the given point.
     */
    @objid ("7f8a8a2c-1dec-11e2-8cad-001ec947c8cc")
    public final Sign positionRelativeTo(Point rel) {
        Ray ptRelRay = new Ray(getOrigin(), rel);
        
        TrigValues val = getTrigValues(ptRelRay);
        if (val != null) {
            double dNewAngle = Math.atan2(-val.sinTheta, -val.cosTheta);
            if (dNewAngle > 0) {
                return Sign.POSITIVE;
            }
        }
        return Sign.NEGATIVE;
    }

    /**
     * Calculates the projection of the given point onto the line segment.
     * @param xCoord the x coordinate of the point.
     * @param yCoord the y coordinate of the point.
     * @return <code>double</code> value of the calculated projection.
     */
    @objid ("7f8a8a34-1dec-11e2-8cad-001ec947c8cc")
    public final double projection(final int xCoord, final int yCoord) {
        /*
         * Use parametric form for equation of a line segment: p + td, where 0 < t < 1 and d = p2 - p (direction vector)
         *
         * To find out if point lies "inside" line segment (i.e. can draw perpendicular line from segment to point), use projection of point (q) to line (p + td): t = (q-p).d/length(d)^2 (. is dot product)
         */
        
        /* get the direction vector */
        long dirx = (long) getTerminus().x - (long) getOrigin().x;
        long diry = (long) getTerminus().y - (long) getOrigin().y;
        
        /* get q - p */
        long qpx = (long) xCoord - (long) getOrigin().x;
        long qpy = (long) yCoord - (long) getOrigin().y;
        
        /* dot product of (q-p) and d */
        long dotprod = qpx * dirx + qpy * diry;
        
        /*
         * avoid divide by 0 - check if point1 equals point2. If so, there is no segment - return a value which indicates projection falls outside the segment.
         */
        if (dirx == 0 && diry == 0) {
            return -1.0F;
        }
        
        /*
         * length (magnitude) of d is sqrt(dirx^2 + diry^2). Don't bother taking square root since we want the length squared.
         */
        return ((double) dotprod / (double) (dirx * dirx + diry * diry));
    }

    /**
     * Sets the origin point of the line segment
     * @param origin Point to set as origin
     * @return this instance
     */
    @objid ("7f8a8a3d-1dec-11e2-8cad-001ec947c8cc")
    public LineSeg setOrigin(Point origin) {
        this.origin.setLocation(origin);
        return this;
    }

    /**
     * Sets the terminating point of the line segment.
     * @param terminus Point to set as terminus
     * @return this instance
     */
    @objid ("7f8a8a43-1dec-11e2-8cad-001ec947c8cc")
    public LineSeg setTerminus(Point terminus) {
        this.terminus.setLocation(terminus);
        return this;
    }

    /**
     * Calculates the slope of this line segment (y=mx+b)
     * @return <code>float</code> the slope of this segment. If the slope is not defined such as when the line segment is vertical, then the constant <code>BIGSLOPE</code> is returned to avoid divide by zero errors.
     */
    @objid ("7f8a8a49-1dec-11e2-8cad-001ec947c8cc")
    public final float slope() {
        if (isVertical()) {
            return LineSeg.BIGSLOPE;
        }
        return (float) (this.terminus.y - this.origin.y) / (float) (this.terminus.x - this.origin.x);
    }

    /**
     * Enumeration class for defining the keypoint along a line segment. Can be one of <code>ORIGIN</code>, <code>MIDPOINT</code> or <code>TERMINUS</code>.
     */
    @objid ("7f8a8a4e-1dec-11e2-8cad-001ec947c8cc")
    public static class KeyPoint {
        @objid ("813e82a8-1e83-11e2-8cad-001ec947c8cc")
        private final String name;

        /**
         * Constant designating the origin point on the line segment.
         */
        @objid ("7f8a8a53-1dec-11e2-8cad-001ec947c8cc")
        public static final KeyPoint ORIGIN = new KeyPoint("origin");

        /**
         * Constant designating the mid point on the line segment.
         */
        @objid ("7f8a8a57-1dec-11e2-8cad-001ec947c8cc")
        public static final KeyPoint MIDPOINT = new KeyPoint("midpoint");

        /**
         * Constant designating the terminal point on the line segment.
         */
        @objid ("7f8a8a5b-1dec-11e2-8cad-001ec947c8cc")
        public static final KeyPoint TERMINUS = new KeyPoint("terminus");

        @objid ("7f8a8a5f-1dec-11e2-8cad-001ec947c8cc")
        private  KeyPoint(String name) {
            this.name = name;
        }

        @objid ("7f8a8a62-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public String toString() {
            return this.name;
        }

    }

    /**
     * Enumeration class for defining the orientations of a point relative to the line segment. The orientations can be one of <code>POSITIVE</code> or <code>NEGATIVE</code>.
     */
    @objid ("7f8a8a67-1dec-11e2-8cad-001ec947c8cc")
    public static class Sign {
        @objid ("8140e4f2-1e83-11e2-8cad-001ec947c8cc")
        private final String name;

        /**
         * Constant designating an orientation that is position relative to the lineseg vector.
         */
        @objid ("7f8a8a6c-1dec-11e2-8cad-001ec947c8cc")
        public static final Sign POSITIVE = new Sign("positive");

        /**
         * Constant designating an orientation that is negative relative to the lineseg vector.
         */
        @objid ("7f8a8a70-1dec-11e2-8cad-001ec947c8cc")
        public static final Sign NEGATIVE = new Sign("negative");

        @objid ("7f8cec88-1dec-11e2-8cad-001ec947c8cc")
        private  Sign(String name) {
            this.name = name;
        }

        @objid ("7f8cec8b-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public String toString() {
            return this.name;
        }

    }

    /**
     * Structure to hold onto trig values that represent an angle
     * 
     * @author sshaw
     */
    @objid ("7f8cec90-1dec-11e2-8cad-001ec947c8cc")
    public static class TrigValues {
        /**
         * Sin theta value
         */
        @objid ("7f8cec93-1dec-11e2-8cad-001ec947c8cc")
        public double sinTheta;

        /**
         * Cos theta value.
         */
        @objid ("7f8cec95-1dec-11e2-8cad-001ec947c8cc")
        public double cosTheta;

    }

}
