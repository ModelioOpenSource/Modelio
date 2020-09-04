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

package org.modelio.diagram.editor.sequence.elements.executionoccurencespecification;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.core.figures.BrushOptions;
import org.modelio.diagram.elements.core.figures.IBrushOptionsSupport;

/**
 * Specific figure for execution occurence specification.
 * 
 * @author fpoyer
 */
@objid ("d8d8cfbf-55b6-11e2-877f-002564c97630")
public class ExecutionOccurenceSpecificationFigure extends Figure implements IBrushOptionsSupport, HandleBounds {
    @objid ("d8da561c-55b6-11e2-877f-002564c97630")
    private boolean drawX = false;

    @objid ("4ffdc0a9-55c2-11e2-9337-002564c97630")
    protected BrushOptions brushOptions;

    /**
     * Sets wether this figure shoud draw an 'X' or not. Used for destruction events.
     * 
     * @param drawX the new value.
     */
    @objid ("d8da5620-55b6-11e2-877f-002564c97630")
    public void setDrawX(final boolean drawX) {
        this.drawX = drawX;
    }

    @objid ("d8da5625-55b6-11e2-877f-002564c97630")
    @Override
    protected void paintFigure(final Graphics graphics) {
        if (this.brushOptions.fillColor != null) {
            graphics.setForegroundColor(this.brushOptions.fillColor);
            graphics.setBackgroundColor(this.brushOptions.fillColor);
            Rectangle tempRect = this.getBounds();
            if (this.drawX) {
                graphics.setLineWidth(2);
                graphics.drawLine(tempRect.getTopLeft(), tempRect.getBottomRight());
                graphics.drawLine(tempRect.getTopRight(), tempRect.getBottomLeft());
        
            } else if (this.isOpaque()) {
                // TODO: handle gradient
                graphics.fillOval(tempRect);
            }
        }
        
        graphics.restoreState();
    }

    /**
     * Returns the Rectangle around which handles are to be placed. The Rectangle should be in the same coordinate system as the figure itself.
     * 
     * @return The rectangle used for handles
     */
    @objid ("d8da562a-55b6-11e2-877f-002564c97630")
    @Override
    public Rectangle getHandleBounds() {
        return this.getBounds().getCopy();
    }

    /**
     * Creates a figure.
     */
    @objid ("d8da5630-55b6-11e2-877f-002564c97630")
    public ExecutionOccurenceSpecificationFigure() {
        this.brushOptions = new BrushOptions();
    }

    /**
     * Set the fill color.
     * 
     * @param fillColor the fill color.
     */
    @objid ("d8da5633-55b6-11e2-877f-002564c97630")
    @Override
    public void setFillColor(Color fillColor) {
        if (this.brushOptions.fillColor != fillColor) {
            this.setBackgroundColor(fillColor);
            this.brushOptions.fillColor = fillColor;
            this.repaint();
        }
    }

    /**
     * Get the fill color.
     * 
     * @return the fill color.
     */
    @objid ("d8da5638-55b6-11e2-877f-002564c97630")
    @Override
    public Color getFillColor() {
        return this.brushOptions.fillColor;
    }

    /**
     * Tells whether the background is filled with a gradient.
     * 
     * @return true if the background is filled with a gradient, false in the other case.
     */
    @objid ("d8da563e-55b6-11e2-877f-002564c97630")
    @Override
    public boolean getUseGradient() {
        return this.brushOptions.useGradient;
    }

    /**
     * Set whether the background is filled with a gradient.
     * 
     * @param useGradient true to fill with a gradient, false to fill only with the fill color.
     */
    @objid ("d8da5644-55b6-11e2-877f-002564c97630")
    @Override
    public void setUseGradient(boolean useGradient) {
        if (this.brushOptions.useGradient != useGradient) {
            this.brushOptions.useGradient = useGradient;
            this.repaint();
        }
    }

    @objid ("fe408b64-95d0-4591-834d-7c1c7c09f384")
    @Override
    public void setFillAlpha(int alpha) {
        this.brushOptions.alpha = alpha;
    }

    @objid ("8aa7b7aa-e2ff-4bbe-8961-ba9eafcd6ffd")
    @Override
    public int getFillAlpha() {
        return this.brushOptions.alpha;
    }

}
