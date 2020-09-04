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

package org.modelio.diagram.elements.core.link.extensions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Align the figure on the Connection, at a a fraction of the line length from the starting point of the line.
 * 
 * @author cmarin
 */
@objid ("7ff83692-1dec-11e2-8cad-001ec947c8cc")
public class FractionalConnectionLocator implements IResizableFigureLocator {
    /**
     * Distance from the starting point of the line as a fraction of the line length
     */
    @objid ("7ff83696-1dec-11e2-8cad-001ec947c8cc")
    private double fraction;

    /**
     * Constant to avoid divide by zero errors.
     */
    @objid ("7ff836a2-1dec-11e2-8cad-001ec947c8cc")
    private static final float BIGSLOPE = 9999;

    /**
     * Distance from the reference point towards the target
     */
    @objid ("7ff836a8-1dec-11e2-8cad-001ec947c8cc")
    private int uDistance;

    /**
     * Distance from the connection.
     */
    @objid ("7ffa98b2-1dec-11e2-8cad-001ec947c8cc")
    private int vDistance;

    /**
     * Rotatable figures orientation: <li>true: toward the target. <li>false: toward the source.
     */
    @objid ("7ffa98b4-1dec-11e2-8cad-001ec947c8cc")
    private boolean towardTarget;

    /**
     * Width constraint. -1 = no constraint.
     */
    @objid ("7c5339df-962a-4c1d-adc8-adeb2480b093")
    private int widthConstraint = -1;

    /**
     * Height constraint. -1 = no constraint.
     */
    @objid ("78daa96c-6695-471c-af4c-b4606a272c1e")
    private int heightConstraint = -1;

    /**
     * Preallocated local variable used in getReferenceSegment()
     */
    @objid ("6731f150-1e83-11e2-8cad-001ec947c8cc")
    private static final Point P1 = new Point();

    /**
     * Preallocated local variable used in getReferenceSegment()
     */
    @objid ("6731f153-1e83-11e2-8cad-001ec947c8cc")
    private static final Point P2 = new Point();

    @objid ("6731f156-1e83-11e2-8cad-001ec947c8cc")
    private Connection connection;

    /**
     * Construct a locator with U and V computed from the given point.
     * @param c The Connection
     * @param fraction distance from the starting point of the line as a fraction of the line length.
     * @param figLocation The figure location, in the connection coordinates
     * @param towardTarget true : orient toward target, false: orient toward source
     * @return the built locator.
     */
    @objid ("7ffa98b6-1dec-11e2-8cad-001ec947c8cc")
    public static FractionalConnectionLocator createFromXyPoint(final Connection c, final double fraction, final Point figLocation, final boolean towardTarget) {
        final FractionalConnectionLocator ret = new FractionalConnectionLocator(c, fraction);
        final Point center = new Point();
        final Point previousPoint = new Point();
        
        ret.setTowardTarget(towardTarget);
        ret.getReferenceSegment(c.getPoints(), center, previousPoint);
        
        final Dimension xy = figLocation.getDifference(center);
        
        final Dimension segDiff = center.getDifference(previousPoint);
        final double dist = previousPoint.getDistance(center);
        
        final double cost = segDiff.width / dist;
        final double sint = segDiff.height / dist;
        
        ret.setUDistance((int) (xy.width * cost + xy.height * sint));
        ret.setVDistance((int) (-xy.width * sint + xy.height * cost));
        return ret;
    }

    /**
     * Constructs a locator.
     * @param c The Connection
     * @param fraction distance from the starting point of the line as a fraction of the line length.
     */
    @objid ("7ffa98c7-1dec-11e2-8cad-001ec947c8cc")
    public FractionalConnectionLocator(Connection c, final double fraction) {
        this.connection = c;
        
        if (fraction > 1.0 || fraction < 0.0) {
            throw new IllegalArgumentException("fraction must be 0.0 < f < 1.0");
        }
        
        this.fraction = fraction;
    }

    /**
     * Get the distance from the starting point of the line as a fraction of the line length.
     * @return The fractional distance.
     */
    @objid ("7ffa98cf-1dec-11e2-8cad-001ec947c8cc")
    public double getFraction() {
        return this.fraction;
    }

    /**
     * Get the position where the given figure center would be located if {@link #relocate(IFigure)} was called.
     * @param target The figure to relocate
     * @return The figure center location in the figure coordinates
     */
    @objid ("7ffa98d4-1dec-11e2-8cad-001ec947c8cc")
    public Point getLocation(final IFigure target) {
        final Connection conn = getConnection();
        
        final Point center = new Point();
        final Point previousPoint = new Point();
        
        getReferenceSegment(conn.getPoints(), center, previousPoint);
        
        final Dimension t = getUvTranslation(previousPoint, center);
        
        center.translate(t);
        
        conn.translateToAbsolute(center);
        target.translateToRelative(center);
        return center;
    }

    /**
     * Returns in center and next the position the reference point and the connection point following it.
     * @param points The points in the Connection
     * @param center Will contain the reference point location, in the connection coordinates.
     * @param previous Will contain the previous connection point location, in the connection coordinates.
     */
    @objid ("7ffa98df-1dec-11e2-8cad-001ec947c8cc")
    public void getReferenceSegment(final PointList points, final Point center, final Point previous) {
        final long theLength = length(points);
        
        long remainingLength = Math.round(getFraction() * theLength);
        
        final int n = points.size() - 1;
        for (int i = 0; i < n; i++) {
            points.getPoint(P1, i);
            points.getPoint(P2, i + 1);
        
            final long nextLength = Math.round(P2.getDistance(P1));
        
            if (nextLength >= remainingLength) {
                pointOn(remainingLength, P1, P2, center);
                if (this.towardTarget) {
                    previous.setLocation(P1);
                } else {
                    previous.setLocation(P2);
                }
                return;
            } else {
                remainingLength -= nextLength;
            }
        }
        
        throw new IllegalStateException("Failed to compute location");
    }

    /**
     * Distance from the reference point towards the target
     * @return Distance from the reference point towards the target
     */
    @objid ("7ffa98ee-1dec-11e2-8cad-001ec947c8cc")
    public int getUDistance() {
        return this.uDistance;
    }

    /**
     * Get the distance from the connection.
     * @return The distance from the connection.
     */
    @objid ("7ffa98f3-1dec-11e2-8cad-001ec947c8cc")
    public int getVDistance() {
        return this.vDistance;
    }

    /**
     * Get the rotatable figures orientation.
     * @return true : toward the target, false: toward the source
     */
    @objid ("7ffcfb0e-1dec-11e2-8cad-001ec947c8cc")
    public boolean isTowardTarget() {
        return this.towardTarget;
    }

    @objid ("7ffcfb13-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void relocate(final IFigure target) {
        final Connection conn = getConnection();
        
        final Point center = new Point();
        final Point fromPoint = new Point();
        
        getReferenceSegment(conn.getPoints(), center, fromPoint);
        
        final Dimension t = getUvTranslation(fromPoint, center);
        
        center.translate(t);
        fromPoint.translate(t);
        
        if (target instanceof RotatableDecoration) {
            final RotatableDecoration rot = (RotatableDecoration) target;
        
            conn.translateToAbsolute(center);
            target.translateToRelative(center);
        
            conn.translateToAbsolute(fromPoint);
            target.translateToRelative(fromPoint);
        
            rot.setLocation(center);
            rot.setReferencePoint(fromPoint);
        } else {
            final Dimension prefSize = computeFigureSize(target);
            conn.translateToAbsolute(center);
            target.translateToRelative(center);
            target.setBounds(computeNewBounds(prefSize, center));
        }
    }

    /**
     * Set the rotatable figures orientation.
     * @param towardTarget true to orient toward the target, false for the source
     */
    @objid ("7ffcfb1a-1dec-11e2-8cad-001ec947c8cc")
    public void setTowardTarget(final boolean towardTarget) {
        this.towardTarget = towardTarget;
    }

    /**
     * Distance from the reference point towards the target
     * @param uDistance The distance from the reference point towards the target
     */
    @objid ("7ffcfb1f-1dec-11e2-8cad-001ec947c8cc")
    public void setUDistance(final int uDistance) {
        this.uDistance = uDistance;
    }

    /**
     * Distance from the connection.
     * @param vDistance The distance from the connection
     */
    @objid ("7ffcfb24-1dec-11e2-8cad-001ec947c8cc")
    public void setVDistance(final int vDistance) {
        this.vDistance = vDistance;
    }

    /**
     * Calculates the slope of the line segment (y=ax+b)
     * @param start start of segment
     * @param end end of segment
     * @return <code>float</code> the slope of the segment. If the slope is not defined such as when the line segment is
     * vertical, then the constant <code>BIGSLOPE</code> is returned to avoid divide by zero errors.
     */
    @objid ("7ffcfb29-1dec-11e2-8cad-001ec947c8cc")
    public final float slope(final Point start, final Point end) {
        if (end.x == start.x) {
            return BIGSLOPE;
        }
        return (float) (end.y - start.y) / (float) (end.x - start.x);
    }

    /**
     * Recalculate the location of the figure according to its desired position relative to the center point.
     * @param size The size of the figure
     * @param center The center point
     * @return The new bounds
     */
    @objid ("7ffcfb36-1dec-11e2-8cad-001ec947c8cc")
    protected Rectangle computeNewBounds(final Dimension size, final Point center) {
        final Rectangle bounds = new Rectangle(center, size);
        
        bounds.x -= bounds.width / 2;
        bounds.y -= bounds.height / 2;
        return bounds;
    }

    /**
     * Returns Locator's reference point in absolute coordinates.
     * @return The reference point
     */
    @objid ("7ffcfb45-1dec-11e2-8cad-001ec947c8cc")
    protected Point getReferencePoint() {
        final Point center = new Point();
        final Point directionPoint = new Point();
        getReferenceSegment(getConnection().getPoints(), center, directionPoint);
        getConnection().translateToAbsolute(center);
        return center;
    }

    @objid ("7ffcfb4c-1dec-11e2-8cad-001ec947c8cc")
    private Connection getConnection() {
        return this.connection;
    }

    /**
     * Convert {@link #uDistance} and {@link #vDistance} to XY coordinates. The given points give the vector used to
     * compute the rotation.
     * @param origin vector origin in the connection coordinates
     * @param direction vector direction in the connection coordinates
     * @return The converted coordinates in the connection coordinates.
     */
    @objid ("7ffcfb52-1dec-11e2-8cad-001ec947c8cc")
    private Dimension getUvTranslation(final Point origin, final Point direction) {
        final Dimension diff = direction.getDifference(origin);
        final double dist = origin.getDistance(direction);
        
        final double cost = diff.width / dist;
        final double sint = diff.height / dist;
        
        final double u = this.uDistance * cost - this.vDistance * sint;
        final double v = this.uDistance * sint + this.vDistance * cost;
        return new Dimension((int) u, (int) v);
    }

    /**
     * Get the length of the given point list.
     * @param points The point list
     * @return the length
     */
    @objid ("7fff5d66-1dec-11e2-8cad-001ec947c8cc")
    private long length(final PointList points) {
        long ret = 0;
        final int n = points.size() - 1;
        for (int i = 0; i < n; i++) {
            points.getPoint(P1, i);
            points.getPoint(P2, i + 1);
        
            ret += Math.round(P2.getDistance(P1));
        }
        return ret;
    }

    /**
     * Gets the point on the line segment at the given distance away from the key point.
     * @param theDistance <code>long</code> distance along the line
     * @param start start of the segment
     * @param end end of the segment
     * @param ptResult <code>Point</code> where the resulting calculating value is stored.
     * @return <code>boolean</code> <code>true</code> if point can be calculated, <code>false</code> otherwise.
     */
    @objid ("7fff5d6f-1dec-11e2-8cad-001ec947c8cc")
    private boolean pointOn(final long theDistance, final Point start, final Point end, final Point ptResult) {
        float m, dx_float;
        int dx, dy, startX = 0, startY = 0, otherX = 0, otherY = 0;
        
        // Set the point to offset from and the other point used to determine
        // which direction dx and dy should be applied to get a point on the
        // line.
        
        startX = start.x;
        startY = start.y;
        otherX = end.x;
        otherY = end.y;
        
        m = slope(start, end); // get the slope of this line
        
        // Find dx and dy - the delta x and y to get from the endpoint to the
        // point on the line at the specified distance away.
        // The following is based on solving 2 equations with 2 unknowns:
        // dy/dx = m (m is slope of line)
        // dy^2 + dx^2 = dist^2
        //
        final double d_squared = (float) theDistance * (float) theDistance;
        final double m_squared = m * m;
        
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
     * Calculate the figure size from this locator
     * @param target the figure to relocate.
     * @return the figure size to set.
     */
    @objid ("cc9ba26e-76b9-4773-b907-a8dd3513a4aa")
    protected Dimension computeFigureSize(final IFigure target) {
        return target.getPreferredSize(getWidthConstraint(), getHeightConstraint());
    }

    /**
     * Get the width constraint.
     * <p>
     * -1 means no constraint.
     * @return the width constraint.
     */
    @objid ("8248410c-a9b7-40df-b4c1-ea0f289561ce")
    @Override
    public int getWidthConstraint() {
        return this.widthConstraint;
    }

    /**
     * Set the width constraint.
     * <p>
     * -1 means no constraint.
     * @param fixedWidth the width constraint.
     */
    @objid ("772ac200-1d89-4269-b999-19f0642351fd")
    @Override
    public void setWidthConstraint(int fixedWidth) {
        this.widthConstraint = fixedWidth;
    }

    /**
     * Get the height constraint.
     * <p>
     * -1 means no constraint.
     * @return the height constraint.
     */
    @objid ("9c1fb88e-f941-474d-8035-924f514299d9")
    @Override
    public int getHeightConstraint() {
        return this.heightConstraint;
    }

    /**
     * Set the height constraint.
     * <p>
     * -1 means no constraint.
     * @param fixedHeight the height constraint.
     */
    @objid ("a984229c-c551-499f-94ea-1c7480f770b7")
    @Override
    public void setHeightConstraint(int fixedHeight) {
        this.heightConstraint = fixedHeight;
    }

}
