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
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Point;

/**
 * Represents an anchor whose {@link #getLocation(Point)} result depends on the given point.
 * <p>
 * The returned point is usually orthogonal to the given point.
 * 
 * @author cmarin
 */
@objid ("7f5151f4-1dec-11e2-8cad-001ec947c8cc")
public interface ISlidableAnchor extends ConnectionAnchor {
    /**
     * Changes the reference point so that next call to {@link #getLocation(Point)} returns the given point.
     * @param newlocation The new anchor location in absolute coordinates.
     */
    @objid ("7f5151f8-1dec-11e2-8cad-001ec947c8cc")
    void setLocation(Point newlocation);
}

