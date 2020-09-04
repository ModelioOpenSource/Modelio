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

package org.modelio.diagram.elements.core.figures.anchors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.RoundedBoxFigure;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;

/**
 * Provides the implementation of Slidable anchor whose reference point is relative to the top left corner of the
 * figure.
 * 
 * @author fpoyer
 */
@objid ("7f5adb6e-1dec-11e2-8cad-001ec947c8cc")
public class RaySlidableAnchor extends NodeAnchor implements ISlidableAnchor {
    @objid ("7f5adb70-1dec-11e2-8cad-001ec947c8cc")
    private static final int DEFAULT_EXCLUSION = 10;

    /**
     * Constructor.
     * 
     * @param f <code>IFigure</code> that this anchor is associated with.
     * @param relativePos the position that the anchor will initially attach to, <i>relative</i> to the node top left corner..
     */
    @objid ("7f5d3d7e-1dec-11e2-8cad-001ec947c8cc")
    public RaySlidableAnchor(IFigure f, Dimension relativePos) {
        super(f, relativePos);
    }

    /**
     * Returns the location where the Connection should be anchored in absolute
     * coordinates. The anchor may use the given reference Point to calculate
     * this location.
     * 
     * @param reference The reference Point in absolute coordinates
     * @return The anchor's location
     */
    @objid ("7f5d3d87-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getLocation(final Point reference) {
        int reduction = RaySlidableAnchor.DEFAULT_EXCLUSION;
        Rectangle ownerBounds = getOwner().getBounds();
        // adjust reduction if too big compared to width/height
        if (ownerBounds.width < reduction * 3)
            reduction = ownerBounds.width / 3;
        if (ownerBounds.height < reduction * 3)
            reduction = ownerBounds.height / 3;
        final Rectangle ownerAbsoluteBounds = ownerBounds.getExpanded(-reduction, -reduction);
        getOwner().translateToAbsolute(ownerAbsoluteBounds);
        
        Point location = reference.getCopy();
        fixPointInto(location, ownerAbsoluteBounds);
        
        Direction direction = GeomUtils.getDirection(reference, ownerAbsoluteBounds);
        switch (direction) {
            case EAST:
                location.x = getAbsoluteBox().right();
                break;
            case NORTH:
                location.y = getAbsoluteBox().y;
                break;
            case SOUTH:
                location.y = getAbsoluteBox().bottom();
                break;
            case WEST:
                location.x = getAbsoluteBox().x;
                break;
            case NONE:
            default:
                break;
        
        }
        return location;
    }

    @objid ("7f5d3d92-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getReferencePoint() {
        final Rectangle bounds = getOwner().getBounds();
        final Point ret = bounds.getLocation();
        
        int reduction = RaySlidableAnchor.DEFAULT_EXCLUSION;
        if (getOwner() instanceof RoundedBoxFigure) {
            reduction = -((RoundedBoxFigure) getOwner()).getRadius();
        }
        
        ret.translate(getOffset());
        
        fixPointInto(ret, bounds.getExpanded(reduction, reduction));
        
        getOwner().translateToAbsolute(ret);
        return ret;
    }

    @objid ("7f5d3d99-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLocation(Point newAbsoluteLocation) {
        final Dimension d = newAbsoluteLocation.getDifference(getAbsoluteBox().getTopLeft());
        
        //if (d.width < 0 || d.height < 0)
        //    throw new IllegalArgumentException("New reference is not inside the figure ");
        
        getOwner().translateToRelative(d);
        setReference(d);
    }

    /**
     * Gets the anchors associated figure's bounding box in absolute coordinates.
     * 
     * @return a <code>Rectangle</code> that is the bounding box of the owner figure in absolute coordinates
     */
    @objid ("7f5d3d9f-1dec-11e2-8cad-001ec947c8cc")
    protected Rectangle getAbsoluteBox() {
        Rectangle rBox;
        if (getOwner() instanceof Connection) {
            rBox = ((Connection) getOwner()).getPoints().getBounds();
        } else {
            rBox = getOwner().getBounds();
        }
        
        PrecisionRectangle box = new PrecisionRectangle(rBox);
        getOwner().translateToAbsolute(box);
        return box;
    }

}
