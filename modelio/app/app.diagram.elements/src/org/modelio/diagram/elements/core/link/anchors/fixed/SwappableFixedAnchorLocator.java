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

import java.util.function.Consumer;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.anchors.FacesConstants;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;

/**
 * Anchor locator that may move the anchor location on the opposite face if the passed reference point is nearest.
 */
@objid ("73e7a7b8-270e-489a-81dc-929b9b136678")
@Deprecated
class SwappableFixedAnchorLocator extends FixedNodeAnchorLocator {
    @objid ("f5ce599b-6c72-41c4-85dd-ec1aea481283")
    public  SwappableFixedAnchorLocator(String algoId, Consumer<IFigure> figureMoveListener) {
        super(algoId, figureMoveListener);
    }

    @objid ("c8065bb5-c922-4c9a-b802-0853a0c3f66e")
    @Override
    public Point getLocation(FixedAnchor anchor, Point reference) {
        final Rectangle rect = anchor.getOwner().getBounds().getCopy();
        anchor.getOwner().translateToAbsolute(rect);
        
        double fraction =  (anchor.getRank() + 1.0) / (anchor.getTotalOnFace() + 1.0);
        
        switch (anchor.getFace()) {
        case FacesConstants.FACE_NORTH:
        case FacesConstants.FACE_SOUTH:
            if (reference.y <= rect.y+rect.height / 2)
                return rect.getTopLeft().translate(rect.width * fraction, 0);
            else
                return rect.getBottomLeft().translate(rect.width * fraction, 0);
        case FacesConstants.FACE_EAST:
        case FacesConstants.FACE_WEST:
            if (reference.x <= rect.x+rect.width / 2)
                return rect.getTopLeft().translate(0, rect.height * fraction);
            else
                return rect.getTopRight().translate(0, rect.height * fraction);
        default:
            throw new IllegalStateException("Unknow border:" + anchor.getFaceName());
        
        }
        
    }

}
