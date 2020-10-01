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

package org.modelio.uml.sequencediagram.editor.elements.lifeline;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;

/**
 * Specific anchor that is always on the "dashed line" part of a lifeline (that's the x coordinate) and at the y given by the source time of the corresponding {@link ExecutionOccurenceSpecification}.
 * 
 * @author fpoyer
 */
@objid ("d94b405f-55b6-11e2-877f-002564c97630")
public class LifelineAnchor extends AbstractConnectionAnchor {
    @objid ("d94b4061-55b6-11e2-877f-002564c97630")
    private int timeCoordinate = 0;

    /**
     * c'tor.
     * 
     * @param owner Owner of this anchor
     * @param timeCoordinate the time coordinate of that anchor.
     */
    @objid ("d94b4062-55b6-11e2-877f-002564c97630")
    public LifelineAnchor(final IFigure owner, final int timeCoordinate) {
        super(owner);
        this.timeCoordinate = timeCoordinate;
    }

    @objid ("d94b4069-55b6-11e2-877f-002564c97630")
    @Override
    public Point getLocation(final Point reference) {
        return new Point(getReferencePoint().x, this.timeCoordinate);
    }

    @objid ("d94b4070-55b6-11e2-877f-002564c97630")
    @Override
    public Point getReferencePoint() {
        Point p = super.getReferencePoint();
        p.y = this.timeCoordinate;
        return p;
    }

}
