/* 
 * Copyright 2013-2019 Modeliosoft
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
 * Align the figure on the Connection, at a a fraction of the line length from the starting point of the line, applying a rotation in some cases.
 * <p>
 * This locator is obsolete and only remains for the 3.8.1 migration. When moving labels in a diagram, the obsolete locator
 * is going to be replaced automatically with the new one.
 * </p>
 */
@objid ("26752e33-3b6b-4037-8a82-4da910a27676")
public class ObsoleteFractionalConnectionLocator extends FractionalConnectionLocator {
    /**
     * Constant to avoid divide by zero errors.
     */
    @objid ("31685da5-4261-4de8-b7a0-3008cf66146c")
    private static final float BIGSLOPE = 9999;

    /**
     * Distance from the reference point towards the target
     */
    @objid ("7a7fd18c-426c-468a-a2b1-9d4d6ea28605")
    private int uDistance;

    /**
     * Distance from the connection.
     */
    @objid ("d1dc9edd-e220-4696-98ef-a8078202a220")
    private int vDistance;

    /**
     * Width constraint. -1 = no constraint.
     */
    @objid ("3f450299-f8ba-4ab2-a4d3-9e85862a908f")
    private int widthConstraint = -1;

    /**
     * Height constraint. -1 = no constraint.
     */
    @objid ("db3a6515-5c47-4b9b-bdce-8b84f3f93c43")
    private int heightConstraint = -1;

    /**
     * Construct a locator with U and V computed from the given point.
     * 
     * @param c The Connection
     * @param fraction distance from the starting point of the line as a fraction of the line length.
     * @param figLocation The figure location, in the connection coordinates
     * @param towardTarget true : orient toward target, false: orient toward source
     * @return the built locator.
     */
    @objid ("7bb5639b-ce1f-4509-8e67-1ba3eddab510")
    public static ObsoleteFractionalConnectionLocator createFromXyPoint(final Connection c, final double fraction, final Point figLocation, final boolean towardTarget) {
        final ObsoleteFractionalConnectionLocator ret = new ObsoleteFractionalConnectionLocator(c, fraction, towardTarget);
        
        final Point referencePoint = new Point(); // The reference point on the connection for positioning the label
        final Point orientationPoint = new Point();
        
        ret.getReferenceSegment(c.getPoints(), referencePoint, orientationPoint);
        
        final Dimension xy = figLocation.getDifference(referencePoint);
        
        final Dimension segDiff = referencePoint.getDifference(orientationPoint);
        final double dist = orientationPoint.getDistance(referencePoint);
        
        final double cost = segDiff.width / dist;
        final double sint = segDiff.height / dist;
        
        ret.setUDistance((int) (xy.width * cost + xy.height * sint));
        ret.setVDistance((int) (-xy.width * sint + xy.height * cost));
        return ret;
    }

    /**
     * Constructs a locator.
     * 
     * @param c The Connection
     * @param fraction distance from the starting point of the line as a fraction of the line length.
     * @param towardTarget <code>true</code> to orient toward the target, <code>false</code> for the source
     */
    @objid ("ef15aed7-427a-40e2-8bf5-28ae767bbc3f")
    public ObsoleteFractionalConnectionLocator(Connection c, final double fraction, final boolean towardTarget) {
        super(c, fraction, towardTarget);
    }

    /**
     * Get the position where the given figure center would be located if {@link #relocate(IFigure)} was called.
     * 
     * @param target The figure to relocate
     * @return The figure center location in the figure coordinates
     */
    @objid ("ad5f0dae-23dc-4fe2-8e25-f55de93c0143")
    @Override
    public Point getLocation(final IFigure target) {
        final Connection conn = getConnection();
        
        final Point referencePoint = new Point();
        final Point orientationPoint = new Point();
        
        getReferenceSegment(conn.getPoints(), referencePoint, orientationPoint);
        
        final Dimension t = getUvTranslation(orientationPoint, referencePoint);
        referencePoint.translate(t);
        conn.translateToAbsolute(referencePoint);
        target.translateToRelative(referencePoint);
        return referencePoint;
    }

    /**
     * Returns how/where to anchor the label to the connection
     * 
     * @param points The points in the Connection
     * @param referencePoint Will contain the reference point location, in the connection coordinates.
     * @param orientationPoint Will contain a point used in association with referencePoint above to define the label orientation
     */
    @objid ("df119589-87d6-4689-b119-6462d73562a4")
    @Override
    public void getReferenceSegment(final PointList points, final Point referencePoint, final Point orientationPoint) {
        final long theLength = length(points);
        
        Point P1 = new Point();
        Point P2 = new Point();
        
        long remainingLength = Math.round(getFraction() * theLength);
        
        for (int i = 0; i < points.size() - 1; i++) {
            points.getPoint(P1, i);
            points.getPoint(P2, i + 1);
        
            final long nextLength = Math.round(P2.getDistance(P1));
        
            if (nextLength >= remainingLength) {
                pointOn(remainingLength, P1, P2, referencePoint);
                if (isTowardTarget()) {
                    orientationPoint.setLocation(P1);
                } else {
                    orientationPoint.setLocation(P2);
                }
                return;
            } else {
                remainingLength -= nextLength;
            }
        }
        
        throw new IllegalStateException("Failed to compute location");
    }

    /**
     * @return The current DeltaX positioning value.
     */
    @objid ("a0771a1b-3a5e-4727-aeb9-13a2cf1b8038")
    @Override
    public int getUDistance() {
        return this.uDistance;
    }

    /**
     * @return The current DeltaY positioning value.
     */
    @objid ("61a7ddf1-8300-41ba-964a-9162750e792a")
    @Override
    public int getVDistance() {
        return this.vDistance;
    }

    @objid ("f16a5c47-161a-46a0-80ef-42af619d1774")
    @Override
    public void relocate(final IFigure target) {
        final Connection conn = getConnection();
        
        final Point referencePoint = new Point(); // The reference point on the connection for positioning the label
        final Point orientationPoint = new Point(); // In association with referencePoint above, defines the label orientation
        
        getReferenceSegment(conn.getPoints(), referencePoint, orientationPoint);
        
        final Dimension t = getUvTranslation(orientationPoint, referencePoint);
        
        referencePoint.translate(t);
        orientationPoint.translate(t);
        
        if (target instanceof RotatableDecoration) {
            final RotatableDecoration rot = (RotatableDecoration) target;
        
            conn.translateToAbsolute(referencePoint);
            target.translateToRelative(referencePoint);
        
            conn.translateToAbsolute(orientationPoint);
            target.translateToRelative(orientationPoint);
        
            rot.setLocation(referencePoint);
            rot.setReferencePoint(orientationPoint);
        
        } else {
        
            conn.translateToAbsolute(referencePoint);
            target.translateToRelative(referencePoint);
        
            final Dimension prefSize = computeFigureSize(target);
        
            target.setBounds(computeNewBounds(prefSize, referencePoint));
        }
    }

    /**
     * Distance from the reference point towards the target
     * 
     * @param uDistance The distance from the reference point towards the target
     */
    @objid ("76d53b99-cba9-4fe6-97b9-09095eebf09e")
    @Override
    public void setUDistance(final int uDistance) {
        this.uDistance = uDistance;
    }

    /**
     * Distance from the connection.
     * 
     * @param vDistance The distance from the connection
     */
    @objid ("8cc4304a-7e05-484b-9092-245575930da3")
    @Override
    public void setVDistance(final int vDistance) {
        this.vDistance = vDistance;
    }

    /**
     * Calculates the slope of the line segment (y=ax+b)
     * 
     * @param start start of segment
     * @param end end of segment
     * @return <code>float</code> the slope of the segment. If the slope is not defined such as when the line segment is vertical, then the constant <code>BIGSLOPE</code> is returned to avoid divide by zero errors.
     */
    @objid ("cf1f4902-bacc-4380-901b-cf59bf210828")
    private final float slope(final Point start, final Point end) {
        if (end.x == start.x) {
            return ObsoleteFractionalConnectionLocator.BIGSLOPE;
        }
        return (float) (end.y - start.y) / (float) (end.x - start.x);
    }

    /**
     * Recalculate the location of the figure according to its desired position relative to the center point.
     * 
     * @param size The size of the figure
     * @param center The center point
     * @return The new bounds
     */
    @objid ("cb5113f1-7b31-49c8-bb77-03de44abe2ab")
    private Rectangle computeNewBounds(final Dimension size, final Point center) {
        final Rectangle bounds = new Rectangle(center, size);
        
        bounds.x -= bounds.width / 2;
        bounds.y -= bounds.height / 2;
        return bounds;
    }

    /**
     * Returns Locator's reference point in absolute coordinates.
     * 
     * @return The reference point
     */
    @objid ("11b25691-959d-43fd-bc58-0bc63db399fb")
    @Override
    Point getReferencePoint() {
        final Point referencePoint = new Point();
        final Point orientationPoint = new Point();
        getReferenceSegment(getConnection().getPoints(), referencePoint, orientationPoint);
        getConnection().translateToAbsolute(referencePoint);
        return referencePoint;
    }

    /**
     * Convert {@link #uDistance} and {@link #vDistance} to XY coordinates. The given points give the vector used to
     * compute the rotation.
     * 
     * @param origin vector origin in the connection coordinates
     * @param direction vector direction in the connection coordinates
     * @return The converted coordinates in the connection coordinates.
     */
    @objid ("56e1c9e3-456d-4a26-89e1-7cf647f5155b")
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
     * 
     * @param points The point list
     * @return the length
     */
    @objid ("1b2836a0-41f9-4add-9ed3-fb36e631a575")
    private long length(final PointList points) {
        Point P1 = new Point();
        Point P2 = new Point();
        
        long ret = 0;
        
        for (int i = 0; i < points.size() - 1; i++) {
            points.getPoint(P1, i);
            points.getPoint(P2, i + 1);
        
            ret += Math.round(P2.getDistance(P1));
        }
        return ret;
    }

    /**
     * Gets the point on the line segment at the given distance away from the key point.
     * 
     * @param theDistance <code>long</code> distance along the line
     * @param start start of the segment
     * @param end end of the segment
     * @param ptResult <code>Point</code> where the resulting calculating value is stored.
     * @return <code>boolean</code> <code>true</code> if point can be calculated, <code>false</code> otherwise.
     */
    @objid ("f63d3ee4-cde2-4262-a107-493804713088")
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
        
        ptResult.x = startX > otherX ? startX - dx : startX + dx;
        ptResult.y = startY > otherY ? startY - dy : startY + dy;
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
     * 
     * @param target the figure to relocate.
     * @return the figure size to set.
     */
    @objid ("a1bc5828-f559-423a-8f2f-4ef19e04c74c")
    private Dimension computeFigureSize(final IFigure target) {
        return target.getPreferredSize(getWidthConstraint(), getHeightConstraint());
    }

    /**
     * Get the width constraint.
     * <p>
     * -1 means no constraint.
     * 
     * @return the width constraint.
     */
    @objid ("0d373038-97ca-43e8-bbc3-8ba0253c088c")
    @Override
    public int getWidthConstraint() {
        return this.widthConstraint;
    }

    /**
     * Set the width constraint.
     * <p>
     * -1 means no constraint.
     * 
     * @param fixedWidth the width constraint.
     */
    @objid ("c5464ab7-68a9-4733-8cad-b2f31cbedd39")
    @Override
    public void setWidthConstraint(int fixedWidth) {
        this.widthConstraint = fixedWidth;
    }

    /**
     * Get the height constraint.
     * <p>
     * -1 means no constraint.
     * 
     * @return the height constraint.
     */
    @objid ("5a590661-9d37-427d-820a-62e9cc3e7cf5")
    @Override
    public int getHeightConstraint() {
        return this.heightConstraint;
    }

    /**
     * Set the height constraint.
     * <p>
     * -1 means no constraint.
     * 
     * @param fixedHeight the height constraint.
     */
    @objid ("1500fff3-698b-408d-b91b-89381bc850b7")
    @Override
    public void setHeightConstraint(int fixedHeight) {
        this.heightConstraint = fixedHeight;
    }

}
