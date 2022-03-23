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
package org.modelio.uml.activitydiagram.editor.elements.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Path;
import org.modelio.diagram.elements.core.figures.EllipseShaper;
import org.modelio.diagram.elements.core.figures.ShapedFigure;
import org.modelio.diagram.elements.core.figures.borders.ShapedBorder;
import org.modelio.uml.activitydiagram.editor.elements.activityfinal.GmActivityFinalPrimaryNode;

/**
 * {@link GmActivityFinalPrimaryNode Activity final node} figure.
 * 
 * @author cmarin
 */
@objid ("2a6da05c-55b6-11e2-877f-002564c97630")
public class FinalNodeFigure extends ShapedFigure {
    @objid ("2a6da063-55b6-11e2-877f-002564c97630")
    private final int MARGIN = 4;

    @objid ("d1f96d4d-55c0-11e2-9337-002564c97630")
    private ShapedBorder shapedBorder;

    /**
     * Creates the figure.
     */
    @objid ("2a6da065-55b6-11e2-877f-002564c97630")
    public  FinalNodeFigure() {
        super(new EllipseShaper());
        setOpaque(true);
        this.shapedBorder = new ShapedBorder(this.penOptions.lineColor,
                this.penOptions.lineWidth,
                this.shaper);
        setBorder(this.shapedBorder);
        
    }

    @objid ("2a6da068-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineColor(Color lineColor) {
        if (this.penOptions.lineColor != lineColor) {
            this.shapedBorder.setColor(lineColor);
            super.setLineColor(lineColor);
        }
        
    }

    @objid ("2a6da06c-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineWidth(int lineWidth) {
        if (this.penOptions.lineWidth != lineWidth) {
            this.shapedBorder.setWidth(lineWidth);
            super.setLineWidth(lineWidth);
        }
        
    }

    @objid ("2a6da070-55b6-11e2-877f-002564c97630")
    @Override
    protected void paintFigure(Graphics graphics) {
        // Shrink the bounds to draw the inner circle (therefore fooling the shaper)
        final Rectangle innerRect = getBounds().getCopy().shrink(this.MARGIN, this.MARGIN);
        Path shapePath = this.shaper.createShapePath(innerRect);
        try {
            graphics.setClip(shapePath);
        
            // Draw the (gradient) background
            // do not call the super method as it would restore a full sized shaped clip
            if (isOpaque() && this.brushOptions.fillColor != null) {
                final Color base = this.brushOptions.fillColor;
                final Color gradientColor = this.brushOptions.useGradient ? computeGradientColor(base) : base;
        
                graphics.setBackgroundColor(gradientColor);
                graphics.setForegroundColor(base);
        
                if (this.brushOptions.useGradient) {
                    graphics.fillGradient(innerRect, false);
                    gradientColor.dispose();
                } else {
                    graphics.fillRectangle(innerRect);
                }
            }
        
            graphics.restoreState();
        } finally {
            shapePath.dispose();
        }
        
    }

}
