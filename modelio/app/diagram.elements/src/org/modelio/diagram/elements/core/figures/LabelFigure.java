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

package org.modelio.diagram.elements.core.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

/**
 * Label that can be underlined and stroked through.
 */
@objid ("7fa7267e-1dec-11e2-8cad-001ec947c8cc")
public class LabelFigure extends Label {
    @objid ("7fa72682-1dec-11e2-8cad-001ec947c8cc")
    private boolean underline = false;

    @objid ("7fa72683-1dec-11e2-8cad-001ec947c8cc")
    private boolean strikeThrough = false;

    @objid ("7fa72684-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void paintFigure(final Graphics graphics) {
        super.paintFigure(graphics);
        
        // Draw the underline
        if (this.underline || this.strikeThrough) {
            Dimension textSize = getSubStringTextSize();
            Point p1 = getBounds().getTopLeft();
            p1.translate(getTextLocation());
            if (this.underline) {
                int y = p1.y + textSize.height - 1;
        
                graphics.drawLine(p1.x, y, p1.x + textSize.width, y);
            }
            if (this.strikeThrough) {
                int y = p1.y + (textSize.height / 2) - 1;
        
                graphics.drawLine(p1.x, y, p1.x + textSize.width, y);
            }
        }
    }

    /**
     * Set whether the main label is underlined.
     * @param underline true to underline the main label
     */
    @objid ("7fa7268b-1dec-11e2-8cad-001ec947c8cc")
    public void setUnderline(final boolean underline) {
        if (this.underline != underline) {
            this.underline = underline;
            repaint();
        }
    }

    /**
     * Set whether the main label is underlined.
     * @param strikeThrough true to strike the label
     */
    @objid ("7fa72690-1dec-11e2-8cad-001ec947c8cc")
    public void setStrikeThrough(final boolean strikeThrough) {
        if (this.strikeThrough != strikeThrough) {
            this.strikeThrough = strikeThrough;
            repaint();
        }
    }

}
