/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.core.figures.borders.ShapedBorder;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * Shaped figure with a border and a margin.
 * <p>
 * The border is drawn with the line color.
 * 
 * @author cmarin
 */
@objid ("7fcae9f3-1dec-11e2-8cad-001ec947c8cc")
public class ShapedBorderedFigure extends ShapedFigure {
    @objid ("7fcae9f6-1dec-11e2-8cad-001ec947c8cc")
    private static final int MARGIN = 2;

    @objid ("7fcae9f5-1dec-11e2-8cad-001ec947c8cc")
    private ShapedBorder shapedBorder;

    /**
     * Creates the figure.
     * 
     * @param aShaper The shape of the figure
     */
    @objid ("7fcae9f8-1dec-11e2-8cad-001ec947c8cc")
    public ShapedBorderedFigure(final IShaper aShaper) {
        super(aShaper);
        setSize(30, 40);
        setOpaque(true);
        
        setShaper(aShaper);
    }

    @objid ("7fcae9fd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineColor(Color lineColor) {
        if (lineColor != this.penOptions.lineColor) {
            super.setLineColor(lineColor);
            this.shapedBorder.setColor(lineColor);
        }
    }

    @objid ("7fcaea01-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineWidth(int lineWidth) {
        if (lineWidth != this.penOptions.lineWidth) {
            super.setLineWidth(lineWidth);
            this.shapedBorder.setWidth(lineWidth);
        }
    }

    @objid ("7fcaea05-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setShaper(final IShaper value) {
        super.setShaper(value);
        
        updateBorder();
    }

    /**
     * Updates the shaped border.
     */
    @objid ("7fcd4c05-1dec-11e2-8cad-001ec947c8cc")
    protected void updateBorder() {
        this.shapedBorder = new ShapedBorder(this.penOptions.lineColor,
                                             this.penOptions.lineWidth,
                                             this.shaper);
        this.shapedBorder.setStyle(getLinePattern().toSWTConstant());
        setBorder(new CompoundBorder(this.shapedBorder, new MarginBorder(MARGIN)));
    }

    @objid ("7fcd4c08-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLinePattern(final LinePattern lineStyle) {
        if (lineStyle != this.penOptions.linePattern) {
            super.setLinePattern(lineStyle);
            this.shapedBorder.setStyle(lineStyle.toSWTConstant());
        }
    }

    /**
     * Copy constructor.
     * <p>
     * Copy the border.
     * 
     * @param orig the original
     */
    @objid ("8c2d393d-a181-479c-a5f8-ec4e489ac438")
    public ShapedBorderedFigure(ShapedBorderedFigure orig) {
        super(orig);
        
        updateBorder();
    }

    @objid ("1beb0cf5-321b-4242-af94-4a543ea6d45d")
    @Override
    public ShapedBorderedFigure getCopy() {
        return new ShapedBorderedFigure(this);
    }

}
