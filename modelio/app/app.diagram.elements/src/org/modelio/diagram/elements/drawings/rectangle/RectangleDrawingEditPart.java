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

package org.modelio.diagram.elements.drawings.rectangle;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.modelio.diagram.elements.core.figures.RoundedBoxFigure;
import org.modelio.diagram.elements.drawings.core.GmDrawing;
import org.modelio.diagram.elements.drawings.core.IDrawingConstants;
import org.modelio.diagram.elements.drawings.core.node.NodeDrawingWithLabelEditPart;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Edit part for {@link GmRectangleDrawing}.
 */
@objid ("3ea3eaaf-058e-4c8e-b720-c199e6f76845")
public class RectangleDrawingEditPart extends NodeDrawingWithLabelEditPart {
    @objid ("6bc4c3c2-175a-42e6-bfa6-db11af7756ff")
    @Override
    protected IFigure createFigure() {
        RoundedBoxFigure f = new RectangleDrawingFigure();
        
        // set style independent properties
        
        // set style dependent properties
        refreshFromStyle(f, getModelStyle());
        
        // return the figure
        return f;
    }

    @objid ("7f5fe266-00b3-499a-9942-957f8e53ae9a")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        super.refreshFromStyle(aFigure, style);
        
        final GmDrawing gmModel = getModel();
        
        RoundedBoxFigure f = (RoundedBoxFigure) aFigure;
        f.setRadius(style.getInteger(gmModel.getStyleKey(MetaKey.LINERADIUS)));
    }

    @objid ("4a7a0a28-c580-4e17-91fb-a5b515874453")
    @Override
    protected StyleKey getHAlignKey() {
        return GmRectangleStyleKeys.Label.HALIGN;
    }

    @objid ("8b9c7191-8f95-44e1-85ec-0863c8ea83cc")
    @Override
    protected StyleKey getVAlignKey() {
        return GmRectangleStyleKeys.Label.VALIGN;
    }

    @objid ("4c118a1b-1399-4e1c-91e7-7203c299b1bf")
    @Override
    protected StyleKey getTextAlignKey() {
        return GmRectangleStyleKeys.Label.TEXTALIGN;
    }

    @objid ("1b407167-cef2-4c2a-be05-57d34a7227ef")
    public static final class RectangleDrawingFigure extends RoundedBoxFigure {
        @objid ("6d964167-2e1d-44b3-bcb0-ab84c419aed2")
        public RectangleDrawingFigure() {
            super();
        }

        @objid ("6e70b3cb-3927-4fbc-8c29-b5e45f85c0ea")
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

        @objid ("e99f9f80-9d3b-45be-ab9a-bebefb12783e")
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

        @objid ("0ebe1e3d-d9c4-4693-a551-0a9d3f355b85")
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
