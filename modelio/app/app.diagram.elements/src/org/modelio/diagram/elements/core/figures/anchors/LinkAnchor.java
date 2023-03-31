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
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.elements.core.link.extensions.FractionalConnectionLocator;
import org.modelio.diagram.elements.core.link.extensions.GmFractionalConnectionLocator;
import org.modelio.diagram.elements.core.link.extensions.LocatorFactory;

@objid ("7f5878dc-1dec-11e2-8cad-001ec947c8cc")
public class LinkAnchor extends AbstractConnectionAnchor {
    @objid ("7f5878df-1dec-11e2-8cad-001ec947c8cc")
    private final GmFractionalConnectionLocator locator;

    @objid ("7f5878e1-1dec-11e2-8cad-001ec947c8cc")
    public  LinkAnchor(final Connection connection, final GmFractionalConnectionLocator locator) {
        super(connection);
        this.locator = locator;
        
    }

    @objid ("7f5878e9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getLocation(final Point reference) {
        Connection connOwner = (Connection) getOwner();
        Locator x = LocatorFactory.getInstance().getLocator(connOwner, this.locator);
        Point ret = ((FractionalConnectionLocator) x).getLocation(connOwner);
        connOwner.translateToAbsolute(ret);
        return ret;
    }

    @objid ("ed84a42d-88cb-4ed6-9bda-d7e5bb79748d")
    @Override
    public int hashCode() {
        return Objects.hash(getOwner(), this.locator);
    }

    @objid ("2a0d11a6-32d7-4499-911f-63082e6db8af")
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
        LinkAnchor other = (LinkAnchor) obj;
        return Objects.equals(this.locator, other.locator) && Objects.equals(getOwner(), other.getOwner());
    }

}
