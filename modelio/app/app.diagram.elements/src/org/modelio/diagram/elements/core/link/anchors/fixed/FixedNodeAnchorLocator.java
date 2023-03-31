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
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.anchors.FacesConstants;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.plugin.DiagramElements;

@objid ("8052fbc8-c69e-430e-b7db-b5e3d3a39296")
class FixedNodeAnchorLocator implements IFixedAnchorLocator {
    @objid ("7a697e1c-8acc-4c38-b1b8-86a50551c1a7")
    protected final String algoId;

    @objid ("01ab539d-1b27-427c-8abb-e11d494a3428")
    private Consumer<IFigure> figureMovedListener;

    @objid ("49355cb1-d60a-44c5-acd9-eaa5756939b8")
    public  FixedNodeAnchorLocator(String algoId, Consumer<IFigure> figureMovedListener) {
        this.figureMovedListener = figureMovedListener;
        this.algoId = algoId;
        
    }

    @objid ("380b03bb-1a5a-491b-ac95-ef0a8baa5e93")
    @Override
    public String getFaceName(FixedAnchor fixedAnchor) {
        try {
            return FacesConstants.FACE_LABELS[fixedAnchor.getFace()];
        } catch (RuntimeException e) {
            DiagramElements.LOG.warning(e);
            return String.valueOf(fixedAnchor.getFace());
        }
        
    }

    @objid ("efd8b861-7c3f-4830-b952-72d130cc9020")
    @Override
    public String getAlgorithm() {
        return this.algoId;
    }

    @objid ("e476503c-3722-4282-9d65-75547ff5b511")
    @Override
    public Direction getDirection(FixedAnchor anchor) {
        try {
            return FacesConstants.FACE_DIRECTION[anchor.getFace()];
        } catch (RuntimeException e) {
            DiagramElements.LOG.warning(e);
            return Direction.NONE;
        }
        
    }

    @objid ("92f30884-f350-491a-81be-084ddc03d6a0")
    @Override
    public Point getReferencePoint(FixedAnchor anchor) {
        final IFigure nodefig = anchor.getOwner();
        final Rectangle rect = nodefig.getBounds();
        
        Point ret = new PrecisionPoint(rect.x(), rect.y());
        double fraction = (anchor.getRank() + 1.0) / (anchor.getTotalOnFace() + 1.0);
        
        switch (anchor.getFace()) {
        case FacesConstants.FACE_NORTH:
            ret.translate(rect.preciseWidth() * fraction, 0);
        
            break;
        case FacesConstants.FACE_SOUTH:
            ret.translate(rect.preciseWidth() * fraction, rect.preciseHeight());
            break;
        
        case FacesConstants.FACE_EAST:
            ret.translate(rect.preciseWidth(), rect.preciseHeight() * fraction);
            break;
        
        case FacesConstants.FACE_WEST:
            ret.translate(0, rect.preciseHeight() * fraction);
            break;
        
        default:
            DiagramElements.LOG.warning(new IllegalStateException("Unknow border:" + getFaceName(anchor) + " for " + this));
        }
        
        nodefig.translateToAbsolute(ret);
        return ret;
    }

    /**
     * Called by connection anchors and manually when the node figure changes.
     * <p>
     * Invalidates computed state.
     */
    @objid ("a36a99c2-f672-42c4-8d8b-8e48735fb214")
    @Override
    public void onFigureMoved(IFigure figure) {
        if (this.figureMovedListener != null)
            this.figureMovedListener.accept(figure);
        
    }

}
