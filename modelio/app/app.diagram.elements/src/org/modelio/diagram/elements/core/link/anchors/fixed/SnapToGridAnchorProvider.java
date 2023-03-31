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
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.SnapToGrid;
import org.modelio.diagram.elements.core.figures.anchors.AnchorFigureFactory;
import org.modelio.diagram.elements.core.figures.anchors.FacesConstants;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator;

/**
 * Default Factory of all possible FixedAnchors for a rectangular figure.
 * VariableFixedAnchorProvider
 */
@objid ("7e1f6856-c08a-4d60-b6b3-b4f57da4675d")
@Deprecated
class SnapToGridAnchorProvider extends VariableFixedAnchorFactory {
    @objid ("dc89796e-0202-479d-83e7-2d040bcfdd29")
    private final GraphicalEditPart editPart;

    /**
     * C'tor.
     * @param algoId the algorithm identifier to serialize
     * @param editPart the node edit part
     */
    @objid ("d99186e2-3fd4-41cb-b71e-6756498b9c27")
    public  SnapToGridAnchorProvider(String algoId, GraphicalEditPart editPart) {
        super(algoId, getGridSpacing(editPart).width);
        this.editPart = editPart;
        
    }

    @objid ("20f5cb69-e1bc-40d5-be52-38ea2b01bcfe")
    private static Dimension getGridSpacing(GraphicalEditPart editPart) {
        return (Dimension) editPart.getViewer().getProperty(
                SnapToGrid.PROPERTY_GRID_SPACING);
        
    }

    @objid ("c00fd4d3-ff24-4478-b2e1-495db0d18870")
    @Override
    protected IFixedAnchorLocator createAnchorLocator(String algoId, IFigure aNodeFigure) {
        return new SnapToGridFixedAnchorLocator(super.createAnchorLocator(algoId, aNodeFigure), new SnapToGrid(this.editPart));
    }

    @objid ("d24720f6-89f0-403c-8576-c8a6402d69c6")
    public static class SnapToGridFixedAnchorLocator extends WrappedFixedAnchorLocator {
        @objid ("8de4503b-c8cf-4b39-94c5-77800254d953")
        private SnapToGrid snapToGrid;

        @objid ("506ddf93-e0d4-4805-9681-b93f580b7e10")
        public  SnapToGridFixedAnchorLocator(IFixedAnchorLocator inner, SnapToGrid snapToGrid) {
            super(inner);
            this.snapToGrid = snapToGrid;
            
        }

        @objid ("a87d5798-4b18-43ba-aeec-f557e078bd45")
        @Override
        public Point getReferencePoint(final FixedAnchor anchor) {
            // Call inner anchor
            PrecisionPoint referencePoint = new PrecisionPoint(super.getReferencePoint(anchor));
            
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

        @objid ("f3c9a2a3-68c6-4222-beed-188437b5aacb")
        @Override
        public IFigure createAnchorHandleFigure(ConnectionAnchor anchor) {
            Ellipse fig = AnchorFigureFactory.createDefaultHandleFigure(anchor.getOwner());
            fig.setLineWidthFloat(0.5f);
            return fig;
        }

    }

}
