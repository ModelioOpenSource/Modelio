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
package org.modelio.uml.sequencediagram.editor.elements.executionoccurencespecification;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;

/**
 * Anchor defined specifically to handle correct placement of MessageEnds in sequence diagrams.
 * 
 * @author fpoyer
 */
@objid ("d8deea32-55b6-11e2-877f-002564c97630")
class MasterExecutionOccurrenceSpecificationAnchor extends AbstractConnectionAnchor {
    /**
     * Last valid computed location, in coordinates relative to the owner.
     */
    @objid ("e2f020e7-ceb8-4cd6-b9f1-eb6fd99dd765")
    private Point lastValidLocation;

    /**
     * C'tor.
     * @param owner the owner figure on which bounds this anchor will be placed.
     */
    @objid ("d8deea36-55b6-11e2-877f-002564c97630")
    public  MasterExecutionOccurrenceSpecificationAnchor(IFigure owner) {
        super(owner);
        this.lastValidLocation = owner.getBounds().getCenter();
        
    }

    @objid ("d8deea3a-55b6-11e2-877f-002564c97630")
    @Override
    public Point getLocation(Point reference) {
        Point ownReference = getReferencePoint();
        Point location;
        if (reference.x < ownReference.x) {
            location = this.getOwner().getBounds().getLeft();
        } else if (reference.x == ownReference.x) {
            location = this.getOwner().getBounds().getCenter();
        } else {
            location = this.getOwner().getBounds().getRight();
        }
        if (!location.equals(this.lastValidLocation)) {
            this.lastValidLocation = location.getCopy();
            fireAnchorMoved();
        }
        this.getOwner().translateToAbsolute(location);
        return location;
    }

    @objid ("d8deea40-55b6-11e2-877f-002564c97630")
    @Override
    public Point getReferencePoint() {
        Point ownReference = this.getOwner().getBounds().getCenter();
        this.getOwner().translateToAbsolute(ownReference);
        return ownReference;
    }

    @objid ("d8e0709b-55b6-11e2-877f-002564c97630")
    Point getLastValidLocation() {
        Point.SINGLETON.setLocation(this.lastValidLocation);
        getOwner().translateToAbsolute(Point.SINGLETON);
        return Point.SINGLETON;
    }

    @objid ("d8e0709f-55b6-11e2-877f-002564c97630")
    @Override
    public void ancestorMoved(final IFigure figure) {
        Point location = this.getOwner().getBounds().getCenter();
        // this.getOwner().translateToAbsolute(location);
        if (!location.equals(this.lastValidLocation)) {
            this.lastValidLocation = location.getCopy();
        }
        super.ancestorMoved(figure);
        
    }

}
