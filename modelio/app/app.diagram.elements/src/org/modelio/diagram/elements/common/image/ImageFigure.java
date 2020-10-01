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

package org.modelio.diagram.elements.common.image;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * Figure that represents an element in image mode.
 * 
 * @author phv
 */
@objid ("7e86a9e7-1dec-11e2-8cad-001ec947c8cc")
public class ImageFigure extends Label implements IPenOptionsSupport {
    @objid ("7e86a9eb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getLineColor() {
        return getForegroundColor();
    }

    @objid ("7e86a9f0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getLineWidth() {
        return 0;
    }

    @objid ("7e86a9f5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineColor(Color lineColor) {
        // Ignore line color
    }

    @objid ("7e86a9f9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineWidth(int lineWidth) {
        // Ignore line width
    }

    @objid ("7e86a9fd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getTextColor() {
        return getForegroundColor();
    }

    @objid ("7e86aa02-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Font getTextFont() {
        return getFont();
    }

    @objid ("7e86aa07-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextColor(Color textColor) {
        setForegroundColor(textColor);
    }

    @objid ("7e86aa0b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextFont(Font textFont) {
        setFont(textFont);
    }

    /**
     * Set the image.
     * 
     * @param img the image.
     */
    @objid ("7e86aa13-1dec-11e2-8cad-001ec947c8cc")
    public void setImage(Image img) {
        setIcon(img);
    }

    @objid ("7e86aa17-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public LinePattern getLinePattern() {
        return LinePattern.LINE_SOLID;
    }

    @objid ("7e86aa1c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLinePattern(LinePattern linePattern) {
        // Ignore line pattern
    }

    @objid ("b7c8b77c-8d12-48cf-b9f6-a9ad14aee60e")
    @Override
    protected Dimension getTextSize() {
        return getText().isEmpty() ? new Dimension() : super.getTextSize();
    }

    @objid ("20f83513-a191-4f08-8918-eeb08d4c32a2")
    @Override
    protected Dimension getSubStringTextSize() {
        return getText().isEmpty() ? new Dimension() : super.getTextSize();
    }

    /**
     * Redefines {@link Label#paintFigure(Graphics)} to scale the displayed image if necessary.
     */
    @objid ("0dd607fc-4cb3-484e-8f45-319376c6d7ac")
    @Override
    protected void paintFigure(Graphics graphics) {
        if (isOpaque()) {
            super.paintFigure(graphics);
        }
        Rectangle figureBounds = getBounds();
        graphics.translate(figureBounds.x, figureBounds.y);
        Image icon = getIcon();
        if (icon != null) {
            if (icon.getImageData().width > figureBounds.width || icon.getImageData().height > figureBounds.height) {
                // Resize the image to fit the figure
                double wRatio = (double) icon.getImageData().width / figureBounds.width;
                double hRatio = (double) icon.getImageData().height / figureBounds.height;
                double ratio = Math.max(wRatio, hRatio);
        
                PrecisionRectangle completeBounds = new PrecisionRectangle(0, 0, icon.getImageData().width, icon.getImageData().height);
                PrecisionRectangle imageBounds = new PrecisionRectangle(0, 0, icon.getImageData().width / ratio, icon.getImageData().height / ratio);
        
                // Center resized image
                double dx = (double) (figureBounds.width - imageBounds.width) / 2;
                double dy = (double) (figureBounds.height - imageBounds.height) / 2;
                imageBounds.translate(dx, dy);
        
                graphics.drawImage(icon, completeBounds, imageBounds);
            } else {
                graphics.drawImage(icon, getIconLocation());
            }
        
        }
        if (!isEnabled()) {
            graphics.translate(1, 1);
            graphics.setForegroundColor(ColorConstants.buttonLightest);
            graphics.drawText(getSubStringText(), getTextLocation());
            graphics.translate(-1, -1);
            graphics.setForegroundColor(ColorConstants.buttonDarker);
        }
        graphics.drawText(getSubStringText(), getTextLocation());
        graphics.translate(-figureBounds.x, -figureBounds.y);
    }

}
