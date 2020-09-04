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

package org.modelio.diagram.elements.core.figures.borders;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.core.figures.IShaper;

/**
 * Line border based on a {@link IShaper}.
 */
@objid ("7f620252-1dec-11e2-8cad-001ec947c8cc")
public class ShapedBorder extends LineBorder {
    @objid ("7f620255-1dec-11e2-8cad-001ec947c8cc")
    private IShaper shaper;

    /**
     * Constructs a ShapedBorder with the specified color and of the specified line width and shaped by the shaper
     * @param color The color of the border.
     * @param width The width of the border in pixels.
     * @param shaper the border shape
     * @since 2.0
     */
    @objid ("7f620256-1dec-11e2-8cad-001ec947c8cc")
    public ShapedBorder(Color color, int width, IShaper shaper) {
        super(color, width);
        this.shaper = shaper;
    }

    @objid ("7f62025c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void paint(IFigure figure, Graphics graphics, Insets insets) {
        AbstractBorder.tempRect.setBounds(getPaintRectangle(figure, insets));
        if (getWidth() % 2 != 0) {
            AbstractBorder.tempRect.width--;
            AbstractBorder.tempRect.height--;
        }
        AbstractBorder.tempRect.shrink(getWidth() / 2, getWidth() / 2);
        graphics.setLineWidth(getWidth());
        
        if (getColor() != null) {
            graphics.setForegroundColor(getColor());
        }
        
        graphics.setLineStyle(getStyle());
        
        graphics.drawPath(this.shaper.getShapePath(AbstractBorder.tempRect));
    }

    /**
     * Returns the space used by the border for the figure provided as input. In this border all sides always have equal width.
     * @param figure The figure this border belongs to
     * @return This border's insets
     */
    @objid ("7f620268-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Insets getInsets(IFigure figure) {
        return super.getInsets(figure).getAdded(this.shaper.getShapeInsets(figure.getBounds()));
    }

    @objid ("4e361bc1-8e43-45b2-9d26-3014a86efeda")
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append(" [");
        builder.append("color=");
        builder.append(getColor());
        builder.append(", ");
        builder.append("width=");
        builder.append(getWidth());
        builder.append(", ");
        builder.append("shaper=");
        builder.append(this.shaper);
        builder.append("]");
        return builder.toString();
    }

    @objid ("d2f223d6-df9c-47ad-b089-1c72a70e0252")
    public IShaper getShaper() {
        return shaper;
    }

}
