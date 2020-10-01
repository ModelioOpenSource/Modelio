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
    public LinkAnchor(final Connection connection, final GmFractionalConnectionLocator locator) {
        super(connection);
        this.locator = locator;
    }

    @objid ("7f5878e9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Point getLocation(final Point reference) {
        Locator x = LocatorFactory.getInstance().getLocator((Connection) getOwner(), this.locator);
        // FIXME was //x = new FractionalConnectionLocator((Connection) this.getOwner(), this.locator.getFraction(), false);
        Point ret = ((FractionalConnectionLocator) x).getLocation(getOwner());
        getOwner().translateToAbsolute(ret);
        return ret;
    }

}
