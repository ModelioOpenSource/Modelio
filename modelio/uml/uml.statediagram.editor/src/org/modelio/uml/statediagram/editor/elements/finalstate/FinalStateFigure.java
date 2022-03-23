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
package org.modelio.uml.statediagram.editor.elements.finalstate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Path;
import org.modelio.diagram.elements.core.figures.EllipseShaper;
import org.modelio.diagram.elements.core.figures.ShapedFigure;
import org.modelio.diagram.elements.core.figures.borders.ShapedBorder;

/**
 * Figure for the final state.
 * <p>
 * The line color and the fill color are the same.
 * 
 * @author cmarin
 */
@objid ("f52560ff-55b6-11e2-877f-002564c97630")
public class FinalStateFigure extends ShapedFigure {
    @objid ("f5256106-55b6-11e2-877f-002564c97630")
    private static final int MARGIN = 4;

    @objid ("816219ca-55c2-11e2-9337-002564c97630")
    private ShapedBorder shapedBorder;

    /**
     * Creates the figure.
     */
    @objid ("f5256108-55b6-11e2-877f-002564c97630")
    public  FinalStateFigure() {
        super(new EllipseShaper());
        setSize(20, 20);
        setOpaque(true);
        this.shapedBorder = new ShapedBorder(this.penOptions.lineColor,
                this.penOptions.lineWidth,
                this.shaper);
        setBorder(this.shapedBorder);
        
    }

    /**
     * The fill color and line color are the same.
     */
    @objid ("f525610b-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineColor(Color lineColor) {
        if (this.penOptions.lineColor != lineColor) {
            this.shapedBorder.setColor(lineColor);
            super.setLineColor(lineColor);
            super.setFillColor(lineColor);
        }
        
    }

    @objid ("f5256110-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineWidth(int lineWidth) {
        if (this.penOptions.lineWidth != lineWidth) {
            this.shapedBorder.setWidth(lineWidth);
            super.setLineWidth(lineWidth);
        }
        
    }

    @objid ("f5256114-55b6-11e2-877f-002564c97630")
    @Override
    protected void paintFigure(Graphics graphics) {
        graphics.setAdvanced(true);
        graphics.setAntialias(SWT.ON);
        
        // Shrink the bounds to draw the inner circle (therefore fooling the shaper)
        Rectangle innerRect = getBounds().getCopy().shrink(MARGIN, MARGIN);
        Path shapePath = this.shaper.createShapePath(innerRect);
        try {
            graphics.setClip(shapePath);
        
            // Draw the (gradient) background
            // do not call the super method as it would restore a full sized shaped clip
            final Color fillColor = getFillColor();
            if (isOpaque() && fillColor != null) {
                Color base = fillColor;
        
                if (this.brushOptions.useGradient) {
                    final Color gradientColor = computeGradientColor(base);
                    graphics.setBackgroundColor(gradientColor);
                    graphics.setForegroundColor(base);
        
                    graphics.fillGradient(innerRect, false);
        
                    gradientColor.dispose();
                } else {
                    graphics.setBackgroundColor(base);
                    graphics.fillRectangle(innerRect);
                }
            }
        
            graphics.restoreState();
        } finally {
            shapePath.dispose();
        }
        
    }

    /**
     * The fill color and line color are the same.
     */
    @objid ("f526e779-55b6-11e2-877f-002564c97630")
    @Override
    public Color getFillColor() {
        return super.getLineColor();
    }

    /**
     * The fill color and line color are the same.
     */
    @objid ("f526e77f-55b6-11e2-877f-002564c97630")
    @Override
    public void setFillColor(Color fillColor) {
        this.setLineColor(fillColor);
    }

}
