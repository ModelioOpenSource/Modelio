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

package org.modelio.diagram.elements.core.figures.anchors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;

/**
 * The ChopboxAnchor's location is found by calculating the intersection of a line drawn from a given point inside its
 * owner's box to a reference point . Thus {@link Connection Connections} using the ChopBoxAnchor will be oriented such
 * that they point to the reference point inside the box.
 */
@objid ("7f5878f8-1dec-11e2-8cad-001ec947c8cc")
public class NodeAnchor extends AbstractConnectionAnchor {
    /**
     * Location of the anchor relative to the owner figure top left corner.
     */
    @objid ("8d11e6f6-6fe4-4073-b320-8b4c7824038e")
    private Dimension anchorOffset;

    /**
     * Temporary rectangle to avoid allocation.
     */
    @objid ("c463fa8c-1577-413b-92e3-05b6ca823a61")
    private static Rectangle A_RECTANGLE = new Rectangle();

    /**
     * Temporary point to avoid allocation.
     */
    @objid ("fcb8b26a-3e65-4e2c-be72-445b0b3dbda8")
    private static Point A_POINT = new Point();

    /**
     * Create an anchor.
     * 
     * @param owner The owner node of the anchor
     * @param anchorLocation The location of the anchor relative to the owner node location.
     */
    @objid ("7f587908-1dec-11e2-8cad-001ec947c8cc")
    public NodeAnchor(IFigure owner, Dimension anchorLocation) {
        super(owner);
        this.anchorOffset = new Dimension(anchorLocation);
    }

    @objid ("7f587911-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getLocation(Point reference) {
        final Point anchor = A_POINT;
        getReferencePoint(anchor);
        
        final Rectangle r = A_RECTANGLE;
        r.setBounds(getOwner().getBounds());
        r.expand(1,1);
        getOwner().translateToAbsolute(r);
        
        if (r.isEmpty())
            return r.getLocation();
        else if ((reference.x == anchor.x && reference.y == anchor.y))
            return new Point(anchor); //This avoids divide-by-zero
        else {
            Point ret = GeomUtils.getLineIntersection(anchor, reference, r);
            if (ret == null)
                ret = r.getLocation();
        
            return ret;
        }
    }

    /**
     * Get the offset to the reference point.
     * <p>
     * The offset is returned by reference and must not be directly modified.
     * 
     * @return the reference offset.
     */
    @objid ("7f58791a-1dec-11e2-8cad-001ec947c8cc")
    public Dimension getOffset() {
        return this.anchorOffset;
    }

    @objid ("7f5adb24-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getReferencePoint() {
        final Point ret = new Point();
        getReferencePoint(ret);
        return ret;
    }

    /**
     * Modifies the anchor reference offset.
     * 
     * @param d The new offset to locate the reference point.
     */
    @objid ("7f5adb2b-1dec-11e2-8cad-001ec947c8cc")
    public void setReference(Dimension d) {
        this.anchorOffset.setSize(d);
        fireAnchorMoved();
    }

    /**
     * Correct the point position so that it is inside or touches the given rectangle.
     * 
     * @param p a point
     * @param r the point location limits.
     */
    @objid ("7f5adb31-1dec-11e2-8cad-001ec947c8cc")
    protected void fixPointInto(final Point p, final Rectangle r) {
        p.x = Math.max(p.x, r.x);
        p.x = Math.min(p.x, r.right());
        p.y = Math.max(p.y, r.y);
        p.y = Math.min(p.y, r.bottom());
    }

    @objid ("7f5adb3c-1dec-11e2-8cad-001ec947c8cc")
    private void getReferencePoint(final Point refPoint) {
        final Rectangle bounds = getOwner().getBounds();
        
        refPoint.setLocation(bounds.x(), bounds.y());
        refPoint.translate(this.anchorOffset);
        
        fixPointInto(refPoint, bounds.getExpanded(-1, -1));
        
        getOwner().translateToAbsolute(refPoint);
    }

}
