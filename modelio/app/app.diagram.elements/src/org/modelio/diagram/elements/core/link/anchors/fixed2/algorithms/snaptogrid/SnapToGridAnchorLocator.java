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
package org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.snaptogrid;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.SnapToGrid;
import org.modelio.diagram.elements.core.figures.anchors.AnchorFigureFactory;
import org.modelio.diagram.elements.core.figures.anchors.FacesConstants;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator;
import org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.wrapped.WrappedFixedAnchorLocator;

@objid ("d3611da1-bc0e-4320-a83e-b69b67235cfc")
public class SnapToGridAnchorLocator extends WrappedFixedAnchorLocator {
    @objid ("7ab8e2cd-4cd5-4e2b-a030-381eb19a7cd4")
    private final SnapToGrid snapToGrid;

    @objid ("dc1f66a9-7666-4937-9918-bbeb8d28f91d")
    public  SnapToGridAnchorLocator(IFixedAnchorLocator inner, SnapToGrid snapToGrid) {
        super(inner);
        this.snapToGrid = snapToGrid;
        
    }

    @objid ("d4df9848-98cc-4edd-9031-9c29cb113f91")
    @Override
    public Point getReferencePoint(final FixedAnchor anchor) {
        // Call inner anchor
        Point delegateLoc = super.getReferencePoint(anchor);
        
        // Snap to grid
        PrecisionPoint ret = snapPoint(anchor, delegateLoc);
        return ret;
    }

    @objid ("037cd632-772e-46dc-9d52-76644fff3d15")
    @Override
    public Point getLocation(FixedAnchor anchor, Point reference) {
        // Call inner anchor
        Point delegateLoc = super.getLocation(anchor, reference);
        
        // Snap to grid
        PrecisionPoint ret = snapPoint(anchor, delegateLoc);
        return ret;
    }

    @objid ("f7388416-c679-4425-af09-4f20e59dc2e6")
    private PrecisionPoint snapPoint(final FixedAnchor anchor, Point delegateLoc) {
        PrecisionPoint referencePoint = new PrecisionPoint(delegateLoc);
        
        // Snap point to grid
        PrecisionPoint ret = new PrecisionPoint(referencePoint);
        
        int snapDirections;
        switch (anchor.getFace()) {
        case FacesConstants.FACE_EAST:
        case FacesConstants.FACE_WEST:
            snapDirections = PositionConstants.NORTH ;
            break;
        case FacesConstants.FACE_NORTH:
        case FacesConstants.FACE_SOUTH:
        default:
            snapDirections = PositionConstants.WEST ;
        }
        
        this.snapToGrid.snapPoint(null, snapDirections, referencePoint, ret);
        return ret;
    }

    @objid ("3f635b29-c2ac-4843-a141-26eede5c785a")
    @Override
    public IFigure createAnchorHandleFigure(ConnectionAnchor anchor) {
        Ellipse fig = AnchorFigureFactory.createDefaultHandleFigure(anchor.getOwner());
        fig.setLineWidthFloat(0.5f);
        return fig;
    }

}
