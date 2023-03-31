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
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;

/**
 * {@link ConnectionAnchor} that define the orthogonal direction from the node to outside.
 * @author cma
 * @since 5.1.0
 */
@objid ("86e1b844-94eb-490c-bbf4-42faa3781ee2")
public interface IOrientedAnchor extends ConnectionAnchor {
    /**
     * @return the orthogonal direction from the anchor to outside the node .
     */
    @objid ("a646a28d-d4c3-48d5-a713-e92a81aa58d8")
    Direction getDirection();

    /**
     * Return {@link #getDefinedDirection(ConnectionAnchor)} in the anchor if it implements {@link IOrientedAnchor},
     * {@link Direction#NONE} in the other case.
     * @param anchor an anchor
     * @return the anchor direction or NONE.
     */
    @objid ("04d2a98e-5c1a-4557-84c2-a811fa037f22")
    static Direction getDefinedDirection(ConnectionAnchor anchor) {
        if (anchor instanceof IOrientedAnchor)
            return ((IOrientedAnchor) anchor).getDirection();
        return Direction.NONE;
    }

    /**
     * Return {@link #getDefinedDirection(ConnectionAnchor)} in the anchor if it implements {@link IOrientedAnchor},
     * or compute it from the given position and the given rectangle in the other case.
     * <p>
     * Then anchor location and the node bounds must be in same coordinates.
     * @param anchor an anchor
     * @param anchorLoc the anchor location
     * @param nodeBounds the anchor node bounds
     * @return the defined or computed anchor direction.
     */
    @objid ("ea779cab-e818-4009-b7d0-9a9c8e062d9b")
    static Direction getAnchorDirection(ConnectionAnchor anchor, Point anchorLoc, Rectangle nodeBounds) {
        Direction ret = IOrientedAnchor.getDefinedDirection(anchor);
        if (ret != Direction.NONE)
            return ret;
        return GeomUtils.getDirection(anchorLoc, nodeBounds);
    }
}

