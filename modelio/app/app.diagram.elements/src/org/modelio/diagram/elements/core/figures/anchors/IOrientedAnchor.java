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
@objid ("f5916924-968e-40b7-8b8d-bee61329df27")
public interface IOrientedAnchor extends ConnectionAnchor {
    /**
     * @return the orthogonal direction from the anchor to outside the node .
     */
    @objid ("de292326-b364-43f9-a25d-c2247dd029e5")
    Direction getDirection();

    /**
     * Return {@link #getDefinedDirection(ConnectionAnchor)} in the anchor if it implements {@link IOrientedAnchor},
     * {@link Direction#NONE} in the other case.
     * @param anchor an anchor
     * @return the anchor direction or NONE.
     */
    @objid ("12f6750c-b6fa-4366-8d35-f927d8e007b3")
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
    @objid ("9df2e422-8aea-4127-a20e-b412da0d12e4")
    static Direction getAnchorDirection(ConnectionAnchor anchor, Point anchorLoc, Rectangle nodeBounds) {
        Direction ret = IOrientedAnchor.getDefinedDirection(anchor);
        if (ret != Direction.NONE)
            return ret;
        return GeomUtils.getDirection(anchorLoc, nodeBounds);
    }

}
