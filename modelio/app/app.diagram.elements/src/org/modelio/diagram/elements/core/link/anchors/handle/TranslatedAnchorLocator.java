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
package org.modelio.diagram.elements.core.link.anchors.handle;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.anchors.FacesConstants;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;

/**
 * Move the figure {@link FixedAnchor#ANCHOR_RADIUS} px farther from the figure to avoid overriding resize handles.
 * @author cmarin
 * @since 5.3.1
 */
@objid ("b7dc53af-5683-4f12-adf9-a94bc9d58ac7")
public class TranslatedAnchorLocator implements Locator {
    @objid ("9e1ce2eb-9fc5-43eb-b5d5-a6b30c377180")
    private final ConnectionAnchor anchor;

    @objid ("aeccebfa-1f34-47bb-a40c-2d04858dba9d")
    public  TranslatedAnchorLocator(ConnectionAnchor anchor) {
        this.anchor = anchor;
    }

    @objid ("e929968f-fb8b-4ad9-b26d-5af5f2c1d016")
    @Override
    public void relocate(IFigure target) {
        Point p = this.anchor.getReferencePoint().getCopy();
        IFigure srcFigure = this.anchor.getOwner();
        
        Rectangle srcBounds = srcFigure.getBounds().getCopy();
        srcFigure.translateToAbsolute(srcBounds);
        int distance = FixedAnchor.ANCHOR_RADIUS;
        
        if (this.anchor instanceof FixedAnchor) {
            FixedAnchor fixedAnchor = (FixedAnchor) this.anchor;
            if (fixedAnchor.getRank() ==0 && fixedAnchor.getTotalOnFace() == 1 ) {
                // XXX hack: move the middle anchor farther to avoid anchors walking on each other
                distance *= 3.5;
            }
        
            switch (fixedAnchor.getFace()) {
            case FacesConstants.FACE_NORTH:
                GeomUtils.translate(p, Direction.NORTH, distance);
                break;
            case FacesConstants.FACE_SOUTH:
                GeomUtils.translate(p, Direction.SOUTH, distance);
                break;
            case FacesConstants.FACE_EAST:
                GeomUtils.translate(p, Direction.EAST, distance);
                break;
            case FacesConstants.FACE_WEST:
                GeomUtils.translate(p, Direction.WEST, distance);
                break;
            default:
                GeomUtils.translate(p, GeomUtils.getDirection(p, srcBounds), distance);
            }
        } else {
            GeomUtils.translate(p, GeomUtils.getDirection(p, srcBounds), distance);
        }
        
        Rectangle r = new Rectangle(p.x, p.y,1,1);
        target.translateToRelative(r);
        r.expand(FixedAnchor.ANCHOR_RADIUS / 2, FixedAnchor.ANCHOR_RADIUS / 2);
        r.expand(target.getInsets());
        target.setBounds(r);
        
    }

}
