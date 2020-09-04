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

package org.modelio.diagram.elements.core.figures.anchors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.geometry.LineSeg;
import org.modelio.diagram.elements.core.figures.geometry.PrecisionPointList;

/**
 * Provides the implementation of Slidable anchor whose reference point is stored as a fraction of the figure size.
 * 
 * @author oboyko / sshaw
 */
@objid ("7f53b418-1dec-11e2-8cad-001ec947c8cc")
public class LinearSlidableAnchor extends AbstractConnectionAnchor implements ISlidableAnchor {
    @objid ("7f53b420-1dec-11e2-8cad-001ec947c8cc")
    private static int STRAIGHT_LINE_TOLERANCE = 3;

    /**
     * The connection anchor reference point (sometimes the same as anchor location)
     */
    @objid ("e997e38e-5526-418f-b8c2-07fab8464c61")
    private PrecisionPoint relativeReference;

    /**
     * Calculates the relative location of the reference point with respect to the bounds of the figure. If point p is not inside of the figure's bounds then the point is mapped on the bounds and the point relative location is calculated
     * @param bounds
     * @param p the <code>Point</code> that is relative coordinates of the point
     * @return <Code>PrecisionPoint</Code>, i.e. the relative reference for <Code>SlidableAnchor</Code>
     */
    @objid ("7f53b421-1dec-11e2-8cad-001ec947c8cc")
    private static PrecisionPoint getAnchorRelativeLocation(Point p, Rectangle bounds) {
        if (bounds.width == 0 || bounds.height == 0) {
            /*
             * If figure hasn't been laid out yet, we don't want to fail the slidable anchor creation. Hence, we'll just return the (0.5, 0.5) meaning that the anchor reference point is the center of the figure.
             */
            return new PrecisionPoint(0.5, 0.5);
        }
        PrecisionPoint relLocation;
        PrecisionPoint temp = new PrecisionPoint(p);
        if (p.x < bounds.x ||
                p.x > bounds.x + bounds.width ||
                p.y < bounds.y ||
                p.y > bounds.y + bounds.height) {
            if (p.x < bounds.x || p.x > bounds.x + bounds.width) {
                temp.setPreciseX(p.x < bounds.x ? bounds.x : bounds.x + bounds.width);
            }
            if (p.y < bounds.y || p.y > bounds.y + bounds.height) {
                temp.setPreciseY(p.y < bounds.y ? bounds.y : bounds.y + bounds.height);
            }
            relLocation = new PrecisionPoint((temp.preciseX() - bounds.x) / bounds.width,
                    (temp.preciseY() - bounds.y) / bounds.height);
        } else {
        
            relLocation = new PrecisionPoint((temp.preciseX() - bounds.x) / bounds.width,
                    (temp.preciseY() - bounds.y) / bounds.height);
        }
        return relLocation;
    }

    /**
     * Assumption: Points in the <Code>PointList</Code> and <Code>Point</Code> p lie on the same line. Returns the <Code>Point</Code> from the <Code>PointList</Code> closest to
     * @param p
     * @param p
     * @param points - the list of points to select the result from
     * @param p - the point to which the closest point must be found
     * @return the <Code>Point</Code> from the <Code>PointList</Code> closest to
     */
    @objid ("7f53b42e-1dec-11e2-8cad-001ec947c8cc")
    private static Point pickClosestPoint(PointList points, Point p) {
        Point result = null;
        if (points.size() != 0) {
            result = points.getFirstPoint();
            for (int i = 1; i < points.size(); i++) {
                Point temp = points.getPoint(i);
                if (Math.abs(temp.x - p.x) < Math.abs(result.x - p.x)) {
                    result = temp;
                } else if (Math.abs(temp.y - p.y) < Math.abs(result.y - p.y)) {
                    result = temp;
                }
            }
        }
        return result;
    }

    /**
     * Empty constructor
     */
    @objid ("7f53b43b-1dec-11e2-8cad-001ec947c8cc")
    public LinearSlidableAnchor() {
        // empty constructor
    }

    /**
     * Default constructor. The anchor will have the center of the figure as the reference point
     * @param f <code>IFigure</code> that this anchor is associated with.
     */
    @objid ("7f53b43e-1dec-11e2-8cad-001ec947c8cc")
    public LinearSlidableAnchor(IFigure f) {
        super(f);
    }

    /**
     * Constructor. Takes point p to store the reference point
     * @param f <code>IFigure</code> that this anchor is associated with.
     * @param p the <code>PrecisionPoint</code> that the anchor will initially attach to.
     */
    @objid ("7f53b444-1dec-11e2-8cad-001ec947c8cc")
    public LinearSlidableAnchor(IFigure f, PrecisionPoint p) {
        super(f);
        this.relativeReference = new PrecisionPoint(p.preciseX(), p.preciseY());
    }

    @objid ("7f53b44d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LinearSlidableAnchor) {
            LinearSlidableAnchor anchor = (LinearSlidableAnchor) obj;
            /*
             * Owning figures must be identical to satisfy equality of anchors
             */
            if (getOwner() == anchor.getOwner()) {
                if (isDefaultAnchor()) {
                    return anchor.isDefaultAnchor();
                }
                return this.relativeReference.equals(anchor.relativeReference);
            }
        }
        return false;
    }

    @objid ("7f53b453-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getLocation(Point orthoReference) {
        final PrecisionPoint ownReference = new PrecisionPoint(getReferencePoint());
        
        final PrecisionRectangle bounds = new PrecisionRectangle(getOwner().getBounds());
        getOwner().translateToAbsolute(bounds);
        bounds.expand(0.000001, 0.000001);
        
        final PrecisionPoint preciseOrthoReference = new PrecisionPoint(orthoReference);
        int orientation = PositionConstants.NONE;
        if (preciseOrthoReference.preciseX() >= bounds.preciseX() &&
                preciseOrthoReference.preciseX() <= bounds.preciseX() + bounds.preciseWidth()) {
            ownReference.setPreciseX(preciseOrthoReference.preciseX());
            orientation = PositionConstants.VERTICAL;
        } else if (preciseOrthoReference.preciseY() >= bounds.preciseY() &&
                preciseOrthoReference.preciseY() <= bounds.preciseY() + bounds.preciseHeight()) {
            ownReference.setPreciseY(preciseOrthoReference.preciseY());
            orientation = PositionConstants.HORIZONTAL;
        }
        
        Point location = getLocation(ownReference, preciseOrthoReference);
        if (location == null) {
            location = getDefaultLocation(orthoReference);
            orientation = PositionConstants.NONE;
        }
        
        if (orientation != PositionConstants.NONE) {
            PrecisionPoint loc = new PrecisionPoint(location);
            if (orientation == PositionConstants.VERTICAL) {
                loc.setPreciseX(preciseOrthoReference.preciseX());
            } else {
                loc.setPreciseY(preciseOrthoReference.preciseY());
            }
            location = loc;
        }
        return location;
    }

    /**
     * (non-Javadoc)
     * @see org.eclipse.draw2d.ConnectionAnchor#getReferencePoint()
     */
    @objid ("7f53b45d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getReferencePoint() {
        return getAnchorPosition();
    }

    @objid ("7f53b465-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int hashCode() {
        int figureHashCode = getOwner() != null ? getOwner().hashCode() : 0;
        if (this.relativeReference == null) {
            return figureHashCode;
        }
        return new Double(this.relativeReference.preciseX()).hashCode() ^
                        new Double(this.relativeReference.preciseY()).hashCode() ^
                        figureHashCode;
    }

    /**
     * Returns true if the <Code>SlidableAnchor</Code> is default one with a reference at the center
     * @return <code>boolean</code> <code>true</code> is the <code>SlidableAnchor</code> is default one, <code>false</code> otherwise
     */
    @objid ("7f53b46a-1dec-11e2-8cad-001ec947c8cc")
    public boolean isDefaultAnchor() {
        return this.relativeReference == null;
    }

    /**
     * Calculates intersection points of the figure and the line that passes through ownReference and foreignReference points
     * @param ownReference the reference <code>Point</code> on or inside the shape that is being anchored to.
     * @param foreignReference the outside reference <code>Point</code> point that is the terminal end of the line formed by the two parameters.
     * @return intersection points of the figure and the line that passes through ownReference and foreignReference points
     */
    @objid ("7f561670-1dec-11e2-8cad-001ec947c8cc")
    protected PointList getIntersectionPoints(Point ownReference, Point foreignReference) {
        final PointList polygon = getPolygonPoints();
        return (new LineSeg(ownReference, foreignReference)).getLineIntersectionsWithLineSegs(polygon);
    }

    /**
     * Calculates the location of the anchor depending on the anchors own reference and foreign reference points
     * @param ownReference - the own reference of the anchor
     * @param foreignReference - foreign reference that comes in
     * @return the location of the anchor depending on the anchors own reference and foreign reference points
     */
    @objid ("7f56167d-1dec-11e2-8cad-001ec947c8cc")
    protected Point getLocation(Point ownReference, Point foreignReference) {
        PointList intersections = getIntersectionPoints(ownReference, foreignReference);
        if (intersections != null && intersections.size() != 0) {
            Point location = pickClosestPoint(intersections, foreignReference);
            return location;
        }
        return null;
    }

    /**
     * Returns the list of all the vertices of the figure. The created list must form a polygon, i.e. closed polyline, for figures hence the starting and ending points must be the same
     * @return the <code>PointList</code> list of all the vertices of the figure.
     */
    @objid ("7f56168a-1dec-11e2-8cad-001ec947c8cc")
    protected PointList getPolygonPoints() {
        PrecisionRectangle r = new PrecisionRectangle(getBox());
        PrecisionPointList ptList = new PrecisionPointList(5);
        ptList.addPoint(new PrecisionPoint(r.preciseX(), r.preciseY()));
        ptList.addPoint(new PrecisionPoint(r.preciseX() + r.preciseWidth(), r.preciseY()));
        ptList.addPoint(new PrecisionPoint(r.preciseX() + r.preciseWidth(), r.preciseY() + r.preciseHeight()));
        ptList.addPoint(new PrecisionPoint(r.preciseX(), r.preciseY() + r.preciseHeight()));
        ptList.addPoint(new PrecisionPoint(r.preciseX(), r.preciseY()));
        return ptList;
    }

    /**
     * Returns a new owned reference point that is normalized to be with-in a straight-line tolerance value.
     * @param foreignReference <code>Point</code> that is the foreign reference point used to calculate the interfection anchor point on the shape in absolute coordinates.
     * @param ownReference <code>Point</code> that is the reference point with-in the shape in absolute coordinates
     * @param tolerance <code>int</code> value that is the difference in absolute coordinates where the two points would be considered straight and then adjusted.
     * @return <code>Point</code> that is the normalized owned reference to be with-in a given straight-line tolerance value of the foreign reference point.
     */
    @objid ("7f561691-1dec-11e2-8cad-001ec947c8cc")
    protected Point normalizeToStraightlineTolerance(Point foreignReference, Point ownReference, int tolerance) {
        PrecisionPoint preciseOwnReference = new PrecisionPoint(ownReference);
        PrecisionPoint normalizedReference = (PrecisionPoint) preciseOwnReference.getCopy();
        PrecisionPoint preciseForeignReference = new PrecisionPoint(foreignReference);
        if (Math.abs(preciseForeignReference.preciseX() - preciseOwnReference.preciseX()) < tolerance) {
            normalizedReference.setPreciseX(preciseForeignReference.preciseX());
            return normalizedReference;
        }
        if (Math.abs(preciseForeignReference.preciseY() - preciseOwnReference.preciseY()) < tolerance) {
            normalizedReference.setPreciseY(preciseForeignReference.preciseY());
        }
        return normalizedReference;
    }

    /**
     * From relative reference returns the relative coordinates of the anchor Method's visibility can be changed as needed
     */
    @objid ("7f56169f-1dec-11e2-8cad-001ec947c8cc")
    private Point getAnchorPosition() {
        PrecisionRectangle rBox = new PrecisionRectangle(getBox());
        if (isDefaultAnchor()) {
            return rBox.getCenter();
        }
        return new PrecisionPoint(this.relativeReference.preciseX() * rBox.preciseWidth() + rBox.preciseX(), this.relativeReference.preciseY() * rBox.preciseHeight() + rBox.preciseY());
    }

    /**
     * Gets the anchors associated figure's bounding box in absolute coordinates.
     * @return a <code>Rectangle</code> that is the bounding box of the owner figure in absolute coordinates
     */
    @objid ("7f5616a6-1dec-11e2-8cad-001ec947c8cc")
    private Rectangle getBox() {
        Rectangle rBox = (getOwner() instanceof Connection) ? ((Connection) getOwner()).getPoints()
                .getBounds()
                : getOwner().getBounds();
        PrecisionRectangle box = new PrecisionRectangle(rBox);
        getOwner().translateToAbsolute(box);
        return box;
    }

    @objid ("7f5616ad-1dec-11e2-8cad-001ec947c8cc")
    private Point getDefaultLocation(Point reference) {
        Point ownReference = normalizeToStraightlineTolerance(reference,
                getReferencePoint(),
                LinearSlidableAnchor.STRAIGHT_LINE_TOLERANCE);
        
        Point location = getLocation(ownReference, reference);
        if (location == null) {
            location = getLocation(new PrecisionPoint(getBox().getCenter()), reference);
            if (location == null) {
                location = getBox().getCenter();
            }
        }
        return location;
    }

    @objid ("7f5878cc-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLocation(Point newlocation) {
        final Rectangle bounds = getBox();
        final double w = newlocation.x - bounds.x;
        final double h = newlocation.y - bounds.y;
        this.relativeReference = new PrecisionPoint(w / bounds.width, h / bounds.height);
    }

}
