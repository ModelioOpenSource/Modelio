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
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.SnapToGrid;
import org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator;
import org.modelio.diagram.elements.core.link.anchors.fixed2.algorithms.fixed.ConfigurableFixedAnchorFactory;
import org.modelio.diagram.elements.core.link.anchors.fixed2.core.IFigureAnchorsFactory;

/**
 * Anchor factory that generates an anchor at each grid step.
 */
@objid ("05a21515-76bc-4a2d-b281-4aa96a1dad96")
public class GridSpacedAnchorFactory extends ConfigurableFixedAnchorFactory {
    @objid ("2aa65f96-90cc-4b38-b1d7-33f773f038e5")
    private Dimension gridSize;

    /**
     * C'tor.
     * @param algoId the algorithm identifier to serialize
     */
    @objid ("6ec69b4f-7b5f-441f-87ab-18810bcb10f3")
    public  GridSpacedAnchorFactory(String algoId, IFixedAnchorLocator locator) {
        super(algoId, locator);
    }

    @objid ("55abea63-4fb2-40f9-a8d8-981ea5032e6b")
    @Override
    public void fillAnchorCount(Dimension out) {
        Dimension gridSize = getGridSize();
        Dimension figSize = getNodeFigure().getSize();
        
        // compute how many time the 'margin' fit on horizontal and vertical faces
        int nHorizontal = Math.max(1, figSize.width / gridSize.width - 0);
        int nVertical = Math.max(1, figSize.height / gridSize.height - 0);
        
        if (false) {
            // ensure count is odd
            if (nHorizontal % 2 == 0) {
                nHorizontal++;
            }
            if (nVertical % 2 == 0) {
                nVertical++;
            }
        }
        out.setSize(nHorizontal, nVertical);
        
    }

    @objid ("03ea0f57-2841-4ade-925c-e441af4d86b8")
    @Override
    public IFigureAnchorsFactory getAnchorFactoryFor(GraphicalEditPart anEditPart, IFigure aFigure) {
        this.gridSize = (Dimension) anEditPart.getViewer().getProperty(SnapToGrid.PROPERTY_GRID_SPACING);
        return super.getAnchorFactoryFor(anEditPart, aFigure);
    }

    @objid ("95963d80-4d37-4e18-b923-04ed3deb368d")
    private Dimension getGridSize() {
        return this.gridSize;
    }

    @objid ("aacf2937-6d30-4401-9637-9710bffca54c")
    public GridSpacedAnchorFactory withWrappedAnchorLocator(IFixedAnchorLocator alocator) {
        SnapToGridAnchorLocator snapToGridAnchorLocator = new SnapToGridAnchorLocator(alocator, new SnapToGrid(getNodeEdtPart()));
        setLocator(snapToGridAnchorLocator);
        return this;
    }

}
