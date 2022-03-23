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
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Factory of all possible FixedAnchors for a figure.
 */
@objid ("62c8633d-74fa-47f4-bbd4-39654eec735b")
public class SwappableFixedAnchorProvider extends FixedNodeAnchorProvider {
    @objid ("e1d86e9d-35df-469b-984e-3d3dda62d449")
    public  SwappableFixedAnchorProvider(int nHorizontal, int nVertical) {
        super( nHorizontal, nVertical);
    }

    @objid ("c8065bb5-c922-4c9a-b802-0853a0c3f66e")
    @Override
    public Point getLocation(FixedAnchor anchor, Point reference) {
        final Rectangle rect = anchor.getOwner().getBounds().getCopy();
        anchor.getOwner().translateToAbsolute(rect);
        
        double fraction =  (anchor.getRank() + 1.0) / (anchor.getTotalOnFace() + 1.0);
        
        switch (anchor.getFace()) {
        case FACE_NORTH:
        case FACE_SOUTH:
            if (reference.y <= rect.y+rect.height / 2)
                return rect.getTopLeft().translate(rect.width * fraction, 0);
            else
                return rect.getBottomLeft().translate(rect.width * fraction, 0);
        case FACE_EAST:
        case FACE_WEST:
            if (reference.x <= rect.x+rect.width / 2)
                return rect.getTopLeft().translate(0, rect.height * fraction);
            else
                return rect.getTopRight().translate(0, rect.height * fraction);
        default:
            throw new IllegalStateException("Unknow border:" + anchor.getFaceName());
        
        }
        
    }

    /**
     * Redefined to test distance to {@link ConnectionAnchor#getReferencePoint() reference point}
     * instead of {@link ConnectionAnchor#getLocation(Point)}.
     */
    @objid ("676fc7ab-a070-4a37-b635-c46e31dbd666")
    @Override
    public ConnectionAnchor getNearest(IFigure node, Point absPoint, ConnectionRouterId routerId, Integer face, boolean isSource) {
        ComputedState state = getState(node);
        
        double mind = Double.MAX_VALUE;
        ConnectionAnchor nearest = state.anchors.iterator().next();
        
        for (ConnectionAnchor a : state.anchors) {
            double dist = a.getReferencePoint().getDistance(absPoint);
            if (dist < mind) {
                mind = dist;
                nearest = a;
            }
        }
        return nearest;
    }

}
