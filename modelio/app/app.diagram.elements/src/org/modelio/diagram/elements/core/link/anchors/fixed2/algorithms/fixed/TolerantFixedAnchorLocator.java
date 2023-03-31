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
package org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.fixed;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionDimension;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.modelio.diagram.elements.core.figures.anchors.FacesConstants;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator;
import org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.wrapped.WrappedFixedAnchorLocator;

/**
 * {@link #getLocation(FixedAnchor, Point)} snaps anchor location to the passed reference point if within tolerance.
 */
@objid ("8af92425-208c-43d3-bdde-d95aef79adcb")
public class TolerantFixedAnchorLocator extends WrappedFixedAnchorLocator {
    @objid ("cabb7a53-afdc-47b6-a938-4d1d3da3a78c")
    private final int tolerance;

    /**
     * @param delegate the wrapped anchor.
     * @param tolerance the distance from which the passed point may be away from the anchor fixed location.
     */
    @objid ("5435321b-6bf5-440f-ad38-c91caf0fc02f")
    public  TolerantFixedAnchorLocator(IFixedAnchorLocator delegate, int tolerance) {
        super(delegate);
        this.tolerance = tolerance;
        
    }

    @objid ("9bba3429-13dc-47c4-a398-f180091336f7")
    @Override
    public Point getLocation(FixedAnchor anchor, Point reference) {
        Point location = super.getLocation(anchor, reference);
        
        // snap anchor location to the given reference point if within tolerance.
        Dimension tolerance2d = new PrecisionDimension(this.tolerance, this.tolerance);
        anchor.getOwner().translateToAbsolute(tolerance2d);
        
        double d;
        switch (anchor.getFace()) {
        case FacesConstants.FACE_EAST:
        case FacesConstants.FACE_WEST:
            d = reference.preciseY() - location.preciseY();
            if (d > 0 && d > -tolerance2d.preciseHeight() && d <= tolerance2d.preciseHeight()) {
                return new PrecisionPoint(location.preciseX(), reference.preciseY());
            }
            break;
        case FacesConstants.FACE_NORTH:
        case FacesConstants.FACE_SOUTH:
            d = reference.preciseX() - location.preciseX();
            if (d > 0 && d > -tolerance2d.preciseWidth() && d <= tolerance2d.preciseWidth()) {
                return new PrecisionPoint(reference.preciseX(), location.preciseY());
            }
            break;
        default:
        }
        
        // Return anchor location by default
        return location;
    }

}
