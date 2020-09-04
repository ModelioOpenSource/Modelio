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

package org.modelio.diagram.editor.sequence.elements;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;

/**
 * Anchor defined specifically to handle correct placement of MessageEnds in sequence diagrams.
 * 
 * @author fpoyer
 */
@objid ("d96cd22c-55b6-11e2-877f-002564c97630")
public class MessageEndAnchor extends AbstractConnectionAnchor {
    @objid ("d96cd22e-55b6-11e2-877f-002564c97630")
    private int yCoordinate;

    /**
     * C'tor.
     * 
     * @param owner the owner figure on which bounds this anchor will be placed.
     * @param yCoordinate the y coordinate used for this anchor.
     */
    @objid ("d96cd22f-55b6-11e2-877f-002564c97630")
    public MessageEndAnchor(IFigure owner, int yCoordinate) {
        super(owner);
        this.yCoordinate = yCoordinate;
    }

    /**
     * The MessageEndAnchor's location is computed as the coordinates given at creation, with the X coordinate being modified by the given offset. This offset is either added or subtracted to the X coordinate, based on the reference point used when
     * computing the location.
     */
    @objid ("d96cd234-55b6-11e2-877f-002564c97630")
    @Override
    public Point getLocation(Point reference) {
        Point fakePoint = Point.SINGLETON;
        fakePoint.x = this.getOwner().getBounds().getCenter().x;
        fakePoint.y = this.yCoordinate;
        this.getOwner().translateToAbsolute(fakePoint);
        return fakePoint;
    }

    @objid ("d96cd23b-55b6-11e2-877f-002564c97630")
    @Override
    public Point getReferencePoint() {
        return new Point(this.getOwner().getBounds().getCenter().x, this.yCoordinate);
    }

}
