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
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Anchor that is located outside its reference figure, at a given distance.
 * <p>
 * The Dimension gives the distance from the figure border. A negative dimension on horizontal or vertical axis means
 * the anchor is on the left or top of the reference figure.
 */
@objid ("7f5d3dad-1dec-11e2-8cad-001ec947c8cc")
public class SatelliteAnchor extends AbstractConnectionAnchor {
    /**
     * Location of the anchor relative to the owner figure top left corner.
     */
    @objid ("d2391399-10f8-42e0-ac09-f11c14f8c23b")
    private final Dimension distance;

    /**
     * Create an anchor.
     * @param reference The owner node of the anchor
     * @param distance The location of the anchor relative to the owner node location.
     */
    @objid ("7f5d3db5-1dec-11e2-8cad-001ec947c8cc")
    public  SatelliteAnchor(final IFigure reference, Dimension distance) {
        super(reference);
        this.distance = new Dimension(distance);
        
    }

    @objid ("7f5d3dbf-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getLocation(Point reference) {
        return getReferencePoint();
    }

    @objid ("7f5d3dc9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getReferencePoint() {
        final Rectangle bounds = getOwner().getBounds();
        final Point ret = bounds.getLocation();
        
        ret.translate(this.distance);
        
        getOwner().translateToAbsolute(ret);
        return ret;
    }

    /**
     * Modifies the anchor reference point location
     * @param d The new relative location of the reference point.
     */
    @objid ("7f5d3dd0-1dec-11e2-8cad-001ec947c8cc")
    public void setDistance(Dimension d) {
        this.distance.setSize(d);
        fireAnchorMoved();
        
    }

    /**
     * Get the distance of the reference point from the owner figure.
     * @return the reference distance.
     */
    @objid ("7f5d3dd6-1dec-11e2-8cad-001ec947c8cc")
    public Dimension getDistance() {
        return this.distance;
    }

    @objid ("17152861-9424-45f5-a270-de8aafb49026")
    @Override
    public int hashCode() {
        return Objects.hash(getOwner(), this.distance);
    }

    @objid ("2e1fa872-8a3d-4f6d-a00c-2e99afd6ae4a")
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
        SatelliteAnchor other = (SatelliteAnchor) obj;
        return this.distance == other.distance && getOwner()==other.getOwner();
    }

}
