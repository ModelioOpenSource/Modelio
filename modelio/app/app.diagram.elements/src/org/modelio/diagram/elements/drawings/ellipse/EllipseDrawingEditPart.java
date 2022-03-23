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
package org.modelio.diagram.elements.drawings.ellipse;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.modelio.diagram.elements.core.figures.EllipseFigure;
import org.modelio.diagram.elements.drawings.core.IDrawingConstants;
import org.modelio.diagram.elements.drawings.core.node.NodeDrawingWithLabelEditPart;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Edit part for {@link GmEllipseDrawing}.
 */
@objid ("72c700e9-71a1-4431-a0a9-7a1fb8ec21c8")
public class EllipseDrawingEditPart extends NodeDrawingWithLabelEditPart {
    @objid ("55eb1e04-a16e-4c37-ae54-605257909826")
    @Override
    protected IFigure createFigure() {
        EllipseFigure f = new EllipseDrawingFigure();
        
        // set style independent properties
        
        // set style dependent properties
        refreshFromStyle(f, getModelStyle());
        
        // return the figure
        return f;
    }

    @objid ("ae3dd427-c17b-4d2e-a170-31837ff3d9af")
    @Override
    protected StyleKey getHAlignKey() {
        return GmEllipseStyleKeys.Label.HALIGN;
    }

    @objid ("36f12dc8-e96a-4301-8592-ec001a02720e")
    @Override
    protected StyleKey getVAlignKey() {
        return GmEllipseStyleKeys.Label.VALIGN;
    }

    @objid ("69560dec-cd8e-46f1-8265-8b2b65e2537a")
    @Override
    protected StyleKey getTextAlignKey() {
        return GmEllipseStyleKeys.Label.TEXTALIGN;
    }

    @objid ("02236acb-33d0-43bf-9ec2-e3f60f1534d4")
    public static final class EllipseDrawingFigure extends EllipseFigure {
        @objid ("509b92da-7a84-4ec1-9f46-d46f821c4f20")
        public  EllipseDrawingFigure() {
            super();
        }

        @objid ("9fc2432f-ac10-4df9-a636-d3ffa0be5510")
        @Override
        public boolean containsPoint(int x, int y) {
            if (super.containsPoint(x, y)) {
                if (! isOpaque() || getFillAlpha() <255){
                    for (Object c : getChildren())
                        if (((IFigure)c).containsPoint(x, y))
                            return true;
                        
                    return ! (getBounds().getShrinked(getInsets().getAdded(IDrawingConstants.TRANSPARENT_MARGIN)).contains(x, y));
                }
                return true;
            }
            return false;
        }

        @objid ("a0147a9d-fd99-4a09-a504-d4710b11ba43")
        @Override
        public Dimension getPreferredSize(int wHint, int hHint) {
            // Controls the one click creation size
            // Preferred size is the biggest of:
            // - current size, 
            // - layout computed preferred size 
            // - and default size.
            final Dimension preferredSize = super.getPreferredSize(wHint, hHint);
            return IDrawingConstants.DEFAULT_SIZE.getUnioned(preferredSize).union(getSize());
        }

        @objid ("e936e4fc-9368-4152-a387-514a64635de5")
        @Override
        public Dimension getMinimumSize(int wHint, int hHint) {
            // Controls the "fit to content" size
            // Minimum size is the biggest of:
            // - current size, 
            // - layout computed minimum size 
            // - and "fit to content" size.
            final Dimension lminSize = super.getMinimumSize(wHint, hHint);
            return IDrawingConstants.FIT_TO_CONTENT_MINSIZE.getUnioned(lminSize).union(getSize());
        }

    }

}
