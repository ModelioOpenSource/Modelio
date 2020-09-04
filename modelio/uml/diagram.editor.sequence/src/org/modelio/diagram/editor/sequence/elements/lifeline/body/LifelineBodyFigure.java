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

package org.modelio.diagram.editor.sequence.elements.lifeline.body;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.figures.PenOptions;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * Figure class for the body zone of the lifeline. Handles pen options.
 * 
 * @author fpoyer
 */
@objid ("d92fc94e-55b6-11e2-877f-002564c97630")
public class LifelineBodyFigure extends Figure implements IPenOptionsSupport {
    /**
     * Distance (in pixels) beyond which a point is not considered "near" the dashed line.
     */
    @objid ("d92fc955-55b6-11e2-877f-002564c97630")
    private static final int NEAR_LINE = 10;

    @objid ("4f9d9f49-55c2-11e2-9337-002564c97630")
    protected PenOptions penOptions;

    /**
     * C'tor.
     */
    @objid ("d9314fb9-55b6-11e2-877f-002564c97630")
    public LifelineBodyFigure() {
        super();
        this.penOptions = new PenOptions();
    }

    @objid ("d9314fbc-55b6-11e2-877f-002564c97630")
    @Override
    public boolean containsPoint(final int x, final int y) {
        // Either in a child or "near" center line...
        for (Object childObj : getChildren()) {
            IFigure childFigure = (IFigure) childObj;
            if (childFigure.containsPoint(x, y)) {
                return true;
            }
        }
        // No child contains the point... check if it is near the line.
        int centerX = getBounds().getCenter().x;
        return (getBounds().contains(x, y) && (centerX - NEAR_LINE <= x) && (x <= centerX + NEAR_LINE));
    }

    /**
     * Get the line color.
     * 
     * @return the line color.
     */
    @objid ("d9314fc5-55b6-11e2-877f-002564c97630")
    @Override
    public Color getLineColor() {
        return this.penOptions.lineColor;
    }

    /**
     * Get the line pattern
     * 
     * @return lineStyle the line style See {@link LinePattern}
     */
    @objid ("d9314fcb-55b6-11e2-877f-002564c97630")
    @Override
    public LinePattern getLinePattern() {
        return this.penOptions.linePattern;
    }

    /**
     * Get the line width.
     * 
     * @return the line width.
     */
    @objid ("d9314fd3-55b6-11e2-877f-002564c97630")
    @Override
    public int getLineWidth() {
        return this.penOptions.lineWidth;
    }

    /**
     * Get the text color.
     * 
     * @return the text color.
     */
    @objid ("d9314fd9-55b6-11e2-877f-002564c97630")
    @Override
    public Color getTextColor() {
        return this.penOptions.textColor;
    }

    /**
     * Get the text font.
     * 
     * @return the text font.
     */
    @objid ("d9314fdf-55b6-11e2-877f-002564c97630")
    @Override
    public Font getTextFont() {
        return this.penOptions.textFont;
    }

    /**
     * Set the line(s) color.
     * 
     * @param lineColor the line color.
     */
    @objid ("d9314fe5-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineColor(Color lineColor) {
        if (this.penOptions.lineColor != lineColor) {
            this.penOptions.lineColor = lineColor;
            this.repaint();
        }
    }

    /**
     * Sets the line pattern to the argument, which must be one of the constants
     * 
     * {@link LinePattern}
     * 
     * @param lineStyle the new style
     */
    @objid ("d9314fea-55b6-11e2-877f-002564c97630")
    @Override
    public void setLinePattern(LinePattern lineStyle) {
        if (this.penOptions.linePattern != lineStyle) {
            this.penOptions.linePattern = lineStyle;
        }
    }

    /**
     * Set the line(s) width.
     * 
     * @param lineWidth the line(s) width.
     */
    @objid ("d9314ff1-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineWidth(int lineWidth) {
        if (this.penOptions.lineWidth != lineWidth) {
            this.penOptions.lineWidth = lineWidth;
            this.repaint();
        }
    }

    /**
     * Set the text color.
     * 
     * @param textColor the text color.
     */
    @objid ("d9314ff6-55b6-11e2-877f-002564c97630")
    @Override
    public void setTextColor(Color textColor) {
        if (this.penOptions.textColor != textColor) {
            this.penOptions.textColor = textColor;
        }
    }

    /**
     * Set the text font.
     * 
     * @param textFont the text font.
     */
    @objid ("d932d65c-55b6-11e2-877f-002564c97630")
    @Override
    public void setTextFont(Font textFont) {
        if (this.penOptions.textFont != textFont) {
            this.penOptions.textFont = textFont;
        }
    }

    @objid ("d932d661-55b6-11e2-877f-002564c97630")
    @Override
    protected void paintFigure(final Graphics graphics) {
        if (this.isOpaque()) {
            graphics.setForegroundColor(this.penOptions.lineColor);
            graphics.setLineStyle(SWT.LINE_DASH);
            graphics.setLineWidth(this.penOptions.lineWidth);
            graphics.drawLine(this.getBounds().getTop(), this.getBounds().getBottom());
        }
        
        graphics.restoreState();
    }

}
