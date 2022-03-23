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
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;

/**
 * {@link #getLocation(Point)} snap anchor location to the given reference point if within tolerance.
 * 
 * @deprecated not used, to be deleted if still not used after 5.0.2 release.
 * @since 5.0.2
 */
@objid ("dba527b5-b9c6-4aaf-bccd-e372a1a69695")
@Deprecated
class TolerantFixedAnchor extends DelegateAnchor {
    @objid ("1c5e3ddd-7979-40cb-9e4f-1a2667b79889")
    private static final int TOLERANCE = 2;

    @objid ("e07eccfe-f791-4be6-b434-1f02c5d5cb3f")
    public  TolerantFixedAnchor(FixedAnchor wrapped) {
        setDelegate(wrapped);
    }

    @objid ("9d2567c6-68ef-4cee-859f-1d1d575288c2")
    @Override
    public Point getLocation(Point reference) {
        Point location = getDelegate().getLocation(reference);
        
        Dimension tolerance = new Dimension(TOLERANCE, TOLERANCE);
        getOwner().translateToAbsolute(tolerance);
        
        // snap anchor location to the given reference point if within tolerance.
        int d;
        switch (((FixedAnchor) getDelegate()).getFace()) {
        case FixedNodeAnchorProvider.FACE_EAST:
        case FixedNodeAnchorProvider.FACE_WEST:
            d = Math.abs(reference.y() - location.y());
            if (d > 0 && d <= tolerance.height()) {
                return new Point(location).setY(reference.y());
            }
            break;
        case FixedNodeAnchorProvider.FACE_NORTH:
        case FixedNodeAnchorProvider.FACE_SOUTH:
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

    @objid ("baf7d54f-94f0-4a5a-ba49-771469555b4a")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = getClass().hashCode();
        result = prime * result + ((getDelegate() == null) ? 0 : getDelegate().hashCode());
        return result;
    }

    @objid ("fe060551-ab2a-4a8e-8ffa-1f6959116108")
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
