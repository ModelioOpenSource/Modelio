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
package org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.fixed;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.anchors.FacesConstants;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.plugin.DiagramElements;

@objid ("da652c11-3494-495b-9a42-0b54adb30856")
public class FixedNodeAnchorLocator implements IFixedAnchorLocator {
    @objid ("2af29fea-0551-4a5c-98a8-f286a6d7baff")
    protected final String algoId;

    @objid ("6f22ca00-3fab-4e8b-8b00-d43e0c464bbd")
    public  FixedNodeAnchorLocator(String algoId) {
        this.algoId = algoId;
    }

    @objid ("afed4a5d-3403-4a3c-b4b5-87135ee15c70")
    @Override
    public String getFaceName(FixedAnchor fixedAnchor) {
        try {
            return FacesConstants.FACE_LABELS[fixedAnchor.getFace()];
        } catch (RuntimeException e) {
            DiagramElements.LOG.warning(e);
            return String.valueOf(fixedAnchor.getFace());
        }
        
    }

    @objid ("37402534-0617-4837-8bd3-10146162c4cc")
    @Override
    public String getAlgorithm() {
        return this.algoId;
    }

    @objid ("f3255c7c-c84e-4875-8ca1-9006d89e34b3")
    @Override
    public Direction getDirection(FixedAnchor anchor) {
        try {
            return FacesConstants.FACE_DIRECTION[anchor.getFace()];
        } catch (RuntimeException e) {
            DiagramElements.LOG.warning(e);
            return Direction.NONE;
        }
        
    }

    @objid ("7a8d51ec-2f89-4d00-a07f-8269fc769eeb")
    @Override
    public Point getReferencePoint(FixedAnchor anchor) {
        final IFigure nodefig = anchor.getOwner();
        final Rectangle rect = nodefig.getBounds();
        
        Point ret = new PrecisionPoint(rect.x(), rect.y());
        double fraction = (anchor.getRank() + 1.0) / (anchor.getTotalOnFace() + 1.0);
        
        double preciseWidth = rect.preciseWidth();
        double preciseHeight = rect.preciseHeight();
        
        if (true) {
            // Align middle anchor location with SnapGeometry algorithm to avoid differences in midpoint locations.
            // @see: org.eclipse.gef.SnapToGeometry.getCorrectionFor(Entry[], Map, boolean, double, double)
            //
            // If the width is even (i.e., odd right now because we have reduced one pixel from
            // far) there is no middle pixel so favor the left-most/top-most pixel
            // (which is what populateRowsAndCols() does by using int precision).
        
            preciseWidth--;
            preciseHeight--;
            if ((int) (preciseWidth) % 2 != 0)
                preciseWidth -= 1.0;
            if ((int) (preciseHeight) % 2 != 0)
                preciseHeight -= 1.0;
        }
        
        
        switch (anchor.getFace()) {
        case FacesConstants.FACE_NORTH:
            ret.translate(preciseWidth * fraction, 0);
        
            break;
        case FacesConstants.FACE_SOUTH:
            ret.translate(preciseWidth * fraction, preciseHeight);
            break;
        
        case FacesConstants.FACE_EAST:
            ret.translate(preciseWidth, preciseHeight * fraction);
            break;
        
        case FacesConstants.FACE_WEST:
            ret.translate(0, preciseHeight * fraction);
            break;
        
        default:
            DiagramElements.LOG.warning(new IllegalStateException("Unknow border:" + getFaceName(anchor) + " for " + this));
        }
        
        nodefig.translateToAbsolute(ret);
        return ret;
    }

}
