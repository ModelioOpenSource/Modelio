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

@objid ("1bb4f60a-5e33-11e2-b81d-002564c97630")
public class ToSideAnchor extends AbstractConnectionAnchor {
    @objid ("41cacc51-21c7-4b6f-bf39-8f2b64891239")
    private boolean vertical;

    @objid ("1bb4f60d-5e33-11e2-b81d-002564c97630")
    public ToSideAnchor(final IFigure owner, boolean vertical) {
        super(owner);
        this.vertical = vertical;
    }

    @objid ("1bb4f613-5e33-11e2-b81d-002564c97630")
    @Override
    public Point getLocation(final Point reference) {
        Point p = this.vertical ? this.getOwner().getBounds().getTop().getCopy() : this.getOwner()
                .getBounds().getRight().getCopy();
        this.getOwner().translateToAbsolute(p);
        return p;
    }

}
