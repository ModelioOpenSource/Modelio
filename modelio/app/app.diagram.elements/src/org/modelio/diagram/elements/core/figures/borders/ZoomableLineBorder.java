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
package org.modelio.diagram.elements.core.figures.borders;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.core.figures.ZoomDrawer;

/**
 * Provides for a line border with sides of equal widths which are proportional to the zoom level.
 * 
 * @author cmarin
 */
@objid ("7f66c6fb-1dec-11e2-8cad-001ec947c8cc")
public class ZoomableLineBorder extends LineBorder {
    /**
     * Constructs a LineBorder with the specified color and of the specified width.
     * @param lineColor The color of the border.
     * @param lineWidth The width of the border in pixels.
     * @since 2.0
     */
    @objid ("7f66c6ff-1dec-11e2-8cad-001ec947c8cc")
    public  ZoomableLineBorder(Color lineColor, int lineWidth) {
        super(lineColor, lineWidth);
    }

    @objid ("7f66c704-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void paint(IFigure figure, Graphics graphics, Insets insets) {
        AbstractBorder.tempRect.setBounds(getPaintRectangle(figure, insets));
        if (getWidth() % 2 != 0) {
            AbstractBorder.tempRect.width--;
            AbstractBorder.tempRect.height--;
        }
        AbstractBorder.tempRect.shrink(getWidth() / 2, getWidth() / 2);
        
        ZoomDrawer.setLineWidth(graphics, getWidth());
        graphics.setLineStyle(getStyle());
        
        if (getColor() != null) {
            graphics.setForegroundColor(getColor());
        }
        graphics.drawRectangle(AbstractBorder.tempRect);
        
    }

    @objid ("90768f22-26f9-4ff3-a89a-4e0d591f8a64")
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append(" [");
        builder.append("width=");
        builder.append(getWidth());
        builder.append(", ");
        builder.append("color=");
        builder.append(getColor());
        builder.append("]");
        return builder.toString();
    }

}
