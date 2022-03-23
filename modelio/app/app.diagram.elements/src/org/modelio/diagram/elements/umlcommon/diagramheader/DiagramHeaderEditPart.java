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
package org.modelio.diagram.elements.umlcommon.diagramheader;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.elements.common.label.modelelement.ModelElementLabelEditPart;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.figures.IShaper;
import org.modelio.diagram.elements.core.figures.borders.ShapedBorder;
import org.modelio.diagram.styles.core.IStyle;

/**
 * Edit part for {@link GmDiagramHeader}.
 * <p>
 * Creates a flat label figure with a diagram header shaped border.
 */
@objid ("812bc5b2-1dec-11e2-8cad-001ec947c8cc")
public class DiagramHeaderEditPart extends ModelElementLabelEditPart {
    @objid ("812bc5b4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshFromStyle(final IFigure headerFigure, final IStyle style) {
        super.refreshFromStyle(headerFigure, style);
        
        updateFigureBorder(headerFigure);
        
    }

    @objid ("812bc5bd-1dec-11e2-8cad-001ec947c8cc")
    private void updateFigureBorder(final IFigure aFigure) {
        if (aFigure instanceof IPenOptionsSupport) {
            final IPenOptionsSupport fig = (IPenOptionsSupport) aFigure;
            aFigure.setBorder(new ShapedBorder(fig.getLineColor(), fig.getLineWidth(), new DiagramHeaderShaper(fig.getLineWidth())));
        }
        
    }

    @objid ("812bc5c3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isSelectable() {
        return false;
    }

    /**
     * Diagram header shape.
     * <p>
     * Looks like:
     * <p>
     * 
     * <pre>
     * +-------+
     * |       |
     * |      /
     * +------
     * </pre>
     * 
     * Using a shapedBorder to draw the header separator is not a good idea as the ShapedBorder shrinks the figure bounds rectangle by the line width. This results in a separator line drawn 'inside' the header and not properly joining the outline of the
     * figure. Should definitely manage this with a dedicated figure...
     * 
     * The current workaround consists in compensating for the line width, a ugly hack.
     */
    @objid ("812bc5c8-1dec-11e2-8cad-001ec947c8cc")
    private static class DiagramHeaderShaper implements IShaper {
        @objid ("af486ec2-c3a3-404b-9ea0-9c93e61c4d51")
        private int lineWidth;

        @objid ("812bc5cb-1dec-11e2-8cad-001ec947c8cc")
        public  DiagramHeaderShaper(int lineWidth) {
            this.lineWidth = lineWidth;
        }

        @objid ("812bc5cd-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Insets getShapeInsets(final Rectangle rect) {
            return new Insets(2, 2, 2, getFoldSize(rect));
        }

        /**
         * Computes the fold size for a rectangle.
         * @param rect a rectangle
         * @return the fold size for this rectangle.
         */
        @objid ("812bc5d8-1dec-11e2-8cad-001ec947c8cc")
        private int getFoldSize(final Rectangle rect) {
            return rect.height / 2;
        }

        @objid ("812bc5e1-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Path createShapePath(final Rectangle r) {
            final Path ret = new Path(Display.getCurrent());
            final int foldSize = getFoldSize(r);
            
            // Compensating the lineWidth => hack is here !
            // ret.moveTo(r.x, r.y);
            ret.moveTo(r.right() + this.lineWidth, r.y);
            ret.lineTo(r.right() + this.lineWidth, r.y + foldSize);
            ret.lineTo(r.right() + this.lineWidth - foldSize, r.bottom());
            ret.lineTo(r.x - this.lineWidth, r.bottom());
            // ret.lineTo(r.x, r.y);
            return ret;
        }

    }

}
