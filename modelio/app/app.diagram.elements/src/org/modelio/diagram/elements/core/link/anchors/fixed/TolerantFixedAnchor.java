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
package org.modelio.diagram.elements.core.link.anchors.fixed;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.elements.core.figures.anchors.DelegateAnchor;
import org.modelio.diagram.elements.core.figures.anchors.FacesConstants;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;

/**
 * {@link #getLocation(Point)} snap anchor location to the given reference point if within tolerance.
 * 
 * @deprecated not used, to be deleted if still not used after 5.0.2 release.
 * @since 5.0.2
 */
@objid ("1ee843f3-42e5-407f-bbc0-84469be0d8af")
@Deprecated
class TolerantFixedAnchor extends DelegateAnchor {
    @objid ("2fc33ad6-7752-45a7-a74c-a5c8958a310b")
    private static final int TOLERANCE = 2;

    @objid ("e7a70856-2b49-4d10-8c03-769ecbbda437")
    public  TolerantFixedAnchor(FixedAnchor wrapped) {
        super(wrapped);
    }

    @objid ("0c557816-b780-4854-8d46-7e192e8cd677")
    @Override
    public Point getLocation(Point reference) {
        Point location = getDelegate().getLocation(reference);
        
        Dimension tolerance = new Dimension(TOLERANCE, TOLERANCE);
        getOwner().translateToAbsolute(tolerance);
        
        // snap anchor location to the given reference point if within tolerance.
        int d;
        switch (((FixedAnchor) getDelegate()).getFace()) {
        case FacesConstants.FACE_EAST:
        case FacesConstants.FACE_WEST:
            d = Math.abs(reference.y() - location.y());
            if (d > 0 && d <= tolerance.height()) {
                return new Point(location).setY(reference.y());
            }
            break;
        case FacesConstants.FACE_NORTH:
        case FacesConstants.FACE_SOUTH:
            d = Math.abs(reference.x() - location.x());
            if (d > 0 && d <= tolerance.width()) {
                return new Point(location).setX(reference.x());
            }
            break;
        default:
        }
        
        // Return anchor location by default
        return location;
    }

    @objid ("5d98743f-ebb2-44d8-a63c-9ff66a77568b")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = getClass().hashCode();
        result = prime * result + ((getDelegate() == null) ? 0 : getDelegate().hashCode());
        return result;
    }

    @objid ("533721d9-10f7-4907-81aa-157931b2b085")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TolerantFixedAnchor other = (TolerantFixedAnchor) obj;
        if (getDelegate() == null) {
            if (other.getDelegate() != null)
                return false;
        } else if (!this.getDelegate().equals(other.getDelegate()))
            return false;
        return true;
    }

}
