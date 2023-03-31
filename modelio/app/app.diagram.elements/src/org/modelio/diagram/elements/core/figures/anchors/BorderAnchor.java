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

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;

/**
 * Anchor on a rectangle figure border, with an offset from the top or left depending on the border.
 */
@objid ("7f4eef62-1dec-11e2-8cad-001ec947c8cc")
public class BorderAnchor extends AbstractConnectionAnchor {
    @objid ("7f4eef65-1dec-11e2-8cad-001ec947c8cc")
    private final int border;

    @objid ("7f4eef66-1dec-11e2-8cad-001ec947c8cc")
    private final int offset;

    /**
     * @param owner the node figure
     * @param border the border identifier. Use one {@link PositionConstants} constant.
     * @param offset the offset from the border top or left corner
     */
    @objid ("7f4eef67-1dec-11e2-8cad-001ec947c8cc")
    public  BorderAnchor(final IFigure owner, final int border, final int offset) {
        super(owner);
        this.border = border;
        if (offset < 0) {
            this.offset = 0;
        } else {
            this.offset = offset;
        }
        
    }

    @objid ("7f4eef79-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getLocation(final Point reference) {
        return getReferencePoint();
    }

    @objid ("7f4eef84-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getReferencePoint() {
        final Rectangle rect = getOwner().getBounds();
        
        Point ret;
        
        switch (this.border) {
            case PositionConstants.NORTH:
                ret = rect.getTopLeft().translate(this.offset, 0);
        
                break;
            case PositionConstants.SOUTH:
                ret = rect.getBottomLeft().translate(this.offset, 0);
                break;
        
            case PositionConstants.EAST:
                ret = rect.getTopRight().translate(0, this.offset);
                break;
        
            case PositionConstants.WEST:
                ret = rect.getTopLeft().translate(0, this.offset);
                break;
        
            default:
                throw new IllegalStateException("Unknow border:" + this.border);
        }
        
        GeomUtils.forcePointInside(ret, rect);
        getOwner().translateToAbsolute(ret);
        return ret;
    }

    /**
     * @return the border constant
     */
    @objid ("7f4eef8b-1dec-11e2-8cad-001ec947c8cc")
    public int getBorder() {
        return this.border;
    }

    /**
     * @return the offset from the border top or left corner
     */
    @objid ("7f4eef8f-1dec-11e2-8cad-001ec947c8cc")
    public int getOffset() {
        return this.offset;
    }

    @objid ("c8fc2759-a4ec-4ed6-9435-ead64a64a6f5")
    @Override
    public int hashCode() {
        return Objects.hash(getOwner(), this.border, this.offset);
    }

    @objid ("2612f68f-1355-4285-8c6f-b10badef5b90")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BorderAnchor other = (BorderAnchor) obj;
        return this.border == other.border && this.offset == other.offset && getOwner()==other.getOwner();
    }

    @objid ("058085d5-0349-4156-a573-9ffa0aaf1260")
    @Override
    public String toString() {
        return String.format("%s [border=%d (%s), offset=%d, ref point=%s]",
                getClass().getSimpleName(),
                this.border,
                Direction.fromPositionConstant(this.border, Direction.NONE),
                this.offset,
                getReferencePoint());
        
    }

}
