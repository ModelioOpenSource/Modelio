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
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

@objid ("7f4eef62-1dec-11e2-8cad-001ec947c8cc")
public class BorderAnchor extends AbstractConnectionAnchor {
    @objid ("7f4eef65-1dec-11e2-8cad-001ec947c8cc")
    private int border;

    @objid ("7f4eef66-1dec-11e2-8cad-001ec947c8cc")
    private int offset;

    @objid ("7f4eef67-1dec-11e2-8cad-001ec947c8cc")
    public BorderAnchor(final IFigure owner, final int border, final int offset) {
        super(owner);
        this.border = border;
        this.offset = offset;
    }

    @objid ("7f4eef71-1dec-11e2-8cad-001ec947c8cc")
    public void setBorder(final int border) {
        this.border = border;
    }

    @objid ("7f4eef75-1dec-11e2-8cad-001ec947c8cc")
    public void setOffset(final int offset) {
        this.offset = offset;
    }

    @objid ("7f4eef79-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getLocation(final Point reference) {
        return getReferencePoint();
    }

    @objid ("7f4eef84-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getReferencePoint() {
        final Rectangle rect = getOwner().getBounds().getCopy();
        
        Dimension tmpOffset = Dimension.SINGLETON;
        tmpOffset.width = this.offset;
        
        Point ret;
        
        getOwner().translateToAbsolute(rect);
        getOwner().translateToAbsolute(tmpOffset);
        
        int absOffset = tmpOffset.width;
        
        switch (this.border) {
            case PositionConstants.NORTH:
                if (absOffset > rect.width)
                    absOffset = rect.width;
                ret = rect.getTopLeft().translate(absOffset, 0);
        
                break;
            case PositionConstants.SOUTH:
                if (absOffset > rect.width)
                    absOffset = rect.width;
                ret = rect.getBottomLeft().translate(absOffset, 0);
                break;
        
            case PositionConstants.EAST:
                if (absOffset > rect.height)
                    absOffset = rect.height;
                ret = rect.getTopRight().translate(0, absOffset);
                break;
        
            case PositionConstants.WEST:
                if (absOffset > rect.height)
                    absOffset = rect.height;
                ret = rect.getTopLeft().translate(0, absOffset);
                break;
        
            default:
                throw new IllegalStateException("Unknow border:" + this.border);
        }
        return ret;
    }

    @objid ("7f4eef8b-1dec-11e2-8cad-001ec947c8cc")
    public int getBorder() {
        return this.border;
    }

    @objid ("7f4eef8f-1dec-11e2-8cad-001ec947c8cc")
    public int getOffset() {
        return this.offset;
    }

}
