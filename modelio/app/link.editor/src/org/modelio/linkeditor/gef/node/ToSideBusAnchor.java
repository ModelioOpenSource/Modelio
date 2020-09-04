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

package org.modelio.linkeditor.gef.node;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

@objid ("1bb4f622-5e33-11e2-b81d-002564c97630")
public class ToSideBusAnchor extends AbstractConnectionAnchor {
    @objid ("211dc833-2c89-4ffa-9e34-de3f27e2a05e")
    private boolean vertical;

    @objid ("1bb4f625-5e33-11e2-b81d-002564c97630")
    public ToSideBusAnchor(final IFigure owner, boolean vertical) {
        super(owner);
        this.vertical = vertical;
    }

    @objid ("1bb4f62b-5e33-11e2-b81d-002564c97630")
    @Override
    public Point getLocation(final Point reference) {
        Rectangle ownerBounds = this.getOwner().getBounds().getCopy();
        this.getOwner().translateToAbsolute(ownerBounds);
        
        Point p = this.vertical ? ownerBounds.getTop() : ownerBounds.getRight();
        if (this.vertical) {
            p.x = reference.x;
        } else {
            p.y = reference.y;
        }
        if (p.x < ownerBounds.x) {
            p.x = ownerBounds.x;
        }
        if (ownerBounds.right() < p.x) {
            p.x = ownerBounds.right();
        }
        if (p.y < ownerBounds.y) {
            p.y = ownerBounds.y;
        }
        if (ownerBounds.bottom() < p.y) {
            p.y = ownerBounds.bottom();
        }
        return p;
    }

}
