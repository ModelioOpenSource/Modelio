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
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.elements.core.figures.geometry.Direction;

/**
 * Algorithm the {@link FixedAnchor} delegates to to compute its position.
 * @since 5.0.2
 */
@objid ("a4f04b9c-ac2b-4436-b653-c2f3dff3ed4a")
public interface IFixedAnchorLocator extends IAnchorHandleProvider {
    /**
     * Returns the location where the Connection should be anchored in absolute
     * coordinates. The anchor may use the given reference Point to calculate
     * this location.
     * <p>
     * Default implementation return the reference point.
     * @param anchor the anchor to compute
     * @param reference The reference Point in absolute coordinates
     * @return The anchor's location
     */
    @objid ("3da5bc63-57ee-4291-872f-655c2294fdb0")
    default Point getLocation(FixedAnchor anchor, Point reference) {
        return getReferencePoint(anchor);
    }

    /**
     * Returns the reference point for this anchor in absolute coordinates. This
     * might be used by another anchor to determine its own location (i.e.
     * {@link ChopboxAnchor}).
     * @param anchor the anchor to compute
     * @return The reference Point
     */
    @objid ("78fb8674-89ca-4308-bd93-d792af0de278")
    Point getReferencePoint(FixedAnchor anchor);

    /**
     * Get a debug friendly name for the face number
     * @param fixedAnchor an anchor
     * @return the anchor face name
     */
    @objid ("419c7073-89b2-4e26-b7d8-b0d132d2de17")
    default String getFaceName(FixedAnchor fixedAnchor) {
        return String.valueOf(fixedAnchor.getFace());
    }

    /**
     * Called when an anchor node figure moved.
     * <p>
     * Do nothing by default.
     * @param figure a node figure
     */
    @objid ("b77bda68-a0f1-453b-b96a-33b75c69a097")
    default void onFigureMoved(IFigure figure) {
        // Do nothing by default.
    }

    /**
     * @param anchor a fixed anchor
     * @return the orthogonal direction from the anchor to outside the node .
     */
    @objid ("67fa7ca6-f172-48eb-a52e-d1f3ddf57759")
    default Direction getDirection(FixedAnchor anchor) {
        return Direction.NONE;
    }

    /**
     * Get the locator implementation identifier, used for serialization.
     * @return the locator implementation identifier.
     */
    @objid ("edd9b4fe-dc79-40f4-bd3c-ba2853991a93")
    String getAlgorithm();

    @objid ("fd4e00d3-4b92-447f-8323-d1883795f79c")
    @Override
    default IFigure createAnchorHandleFigure(ConnectionAnchor anchor) {
        return AnchorFigureFactory.createDefaultHandleFigure(anchor.getOwner());
    }
}

