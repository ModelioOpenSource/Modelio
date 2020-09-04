/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.bpmn.elements.figure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.core.figures.RoundedBoxFigure;

@objid ("62088cfa-55b6-11e2-877f-002564c97630")
public class DoubleRoundedRectangleBorder extends LineBorder {
    /**
     * Constructs a DoubleRoundedRectangleBorder with the specified color and of the specified width.
     * @param color The color of the border.
     * @param width The width of the border in pixels.
     * @since 2.0
     */
    @objid ("62088cfb-55b6-11e2-877f-002564c97630")
    public DoubleRoundedRectangleBorder(Color color, int width) {
        super(color, width);
    }

    @objid ("62088d00-55b6-11e2-877f-002564c97630")
    @Override
    public void paint(IFigure figure, Graphics graphics, Insets insets) {
        tempRect.setBounds(getPaintRectangle(figure, insets));
        if (this.getWidth() % 2 != 0) {
            tempRect.width--;
            tempRect.height--;
        }
        tempRect.shrink(this.getWidth() / 2, this.getWidth() / 2);
        graphics.setLineWidth(this.getWidth());
        if (this.getColor() != null)
            graphics.setForegroundColor(this.getColor());
        
        int borderRadius = ((RoundedBoxFigure) figure).getAdjustedRadius(tempRect);
        graphics.drawRoundRectangle(tempRect, borderRadius * 2, borderRadius * 2);
        
        tempRect.height -= 6;
        tempRect.width -= 6;
        tempRect.x += 3;
        tempRect.y += 3;
        borderRadius = ((RoundedBoxFigure) figure).getAdjustedRadius(tempRect);
        graphics.drawRoundRectangle(tempRect, borderRadius * 2, borderRadius * 2);
    }

    /**
     * Returns the space used by the border for the figure provided as input. In this border all sides always have equal width.
     * @param figure The figure this border belongs to
     * @return This border's insets
     */
    @objid ("62088d06-55b6-11e2-877f-002564c97630")
    @Override
    public Insets getInsets(IFigure figure) {
        int borderRadius = ((RoundedBoxFigure) figure).getAdjustedRadius(figure.getBounds());
        return new Insets(0 + this.getWidth() / 2 + borderRadius * 40 / 100);
    }

}
