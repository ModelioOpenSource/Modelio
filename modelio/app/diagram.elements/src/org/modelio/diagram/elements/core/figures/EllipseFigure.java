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
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.modelio.diagram.elements.core.figures.borders.ShapedBorder;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;
import org.modelio.ui.CoreFontRegistry;

/**
 * A Filled ellipse figure with an optional label on its center.
 * <p>
 * The label is scaled to always fit the ellipse.
 */
@objid ("7f77778d-1dec-11e2-8cad-001ec947c8cc")
public class EllipseFigure extends ShapedFigure {
    @objid ("8130347d-1e83-11e2-8cad-001ec947c8cc")
    private String label = null;

    @objid ("7f77778f-1dec-11e2-8cad-001ec947c8cc")
    private ShapedBorder shapedBorder;

    @objid ("a3cbe549-34d1-4bc5-ad7a-8fa3e52677c0")
    private Font textFont = null;

    @objid ("c639136c-36fa-4f68-861e-fb7594816904")
    private Dimension textSize = null;

    /**
     * Initialize the figure.
     */
    @objid ("7f777795-1dec-11e2-8cad-001ec947c8cc")
    public EllipseFigure() {
        super(new EllipseShaper());
        this.shapedBorder = new ShapedBorder(this.penOptions.lineColor, this.penOptions.lineWidth, this.shaper);
        setBorder(this.shapedBorder);
        setOpaque(true);
    }

    @objid ("7f777798-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setBounds(Rectangle rect) {
        final Rectangle oldBounds = getBounds();
        if (oldBounds == null || oldBounds.width != rect.width || oldBounds.height != rect.height) {
            super.setBounds(rect);
            computeFont(getClientArea());
        } else {
            super.setBounds(rect);
        }
    }

    /**
     * set the label displayed on the center of the circle.
     * 
     * @param string the label.
     */
    @objid ("7f77779e-1dec-11e2-8cad-001ec947c8cc")
    public void setLabel(String string) {
        this.label = string;
        computeFont(getClientArea());
    }

    @objid ("7f7777a2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineColor(Color lineColor) {
        this.shapedBorder.setColor(lineColor);
        super.setLineColor(lineColor);
    }

    @objid ("7f7777a6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLinePattern(LinePattern lineStyle) {
        this.shapedBorder.setStyle(lineStyle.toSWTConstant());
        super.setLinePattern(lineStyle);
    }

    @objid ("7f7777aa-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineWidth(int lineWidth) {
        if (lineWidth == 0) {
            setBorder(null);
        } else {
            setBorder(this.shapedBorder);
            this.shapedBorder.setWidth(lineWidth);
        }
        super.setLineWidth(lineWidth);
    }

    @objid ("7f7777b2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void paintFigure(Graphics graphics) {
        super.paintFigure(graphics);
        
        if (this.label != null) {
            if (this.textSize == null) {
                computeFont(getClientArea());
            }
        
            Dimension delta = getBounds().getSize().getShrinked(this.textSize).scale(0.5);
            Point textLocation = getBounds().getLocation().translate(delta);
        
            graphics.setFont(this.textFont);
            graphics.setForegroundColor(getLineColor());
            graphics.drawText(this.label, textLocation);
            graphics.restoreState();
        }
    }

    @objid ("7f7777b8-1dec-11e2-8cad-001ec947c8cc")
    private void computeFont(Rectangle rect) {
        final Font figureFont = getFont();
        
        if (this.label == null || figureFont == null) {
            return;
        }
        
        final double maxTextW = rect.width - 2;
        final double maxTextH = rect.height - 2;
        final Dimension extents = FigureUtilities.getStringExtents(this.label, figureFont);
        
        final double zoomH = maxTextH / extents.height;
        final double zoomW = maxTextW / extents.width;
        
        double neededZoom = Math.max(zoomH, zoomW);
        
        if (extents.width * neededZoom > maxTextW) {
            neededZoom = zoomW;
        } else if (extents.height * neededZoom > maxTextH) {
            neededZoom = zoomH;
        }
        
        this.textSize = extents.scale(neededZoom);
        
        final FontData[] fontData = figureFont.getFontData();
        for (FontData d : fontData) {
            d.setHeight((int) (d.getHeight() * neededZoom));
            // d.setStyle(SWT.BOLD);
        }
        
        this.textFont = CoreFontRegistry.getFont(fontData);
    }

    /**
     * Copy constructor
     * 
     * @param orig the original
     */
    @objid ("c7046cdc-de8d-4651-b32a-895345ba6315")
    public EllipseFigure(EllipseFigure orig) {
        super(orig);
        
        this.shapedBorder = new ShapedBorder(this.penOptions.lineColor,
                this.penOptions.lineWidth,
                this.shaper);
        
        this.label = orig.label;
        
        setOpaque(true);
        setBorder(this.shapedBorder);
    }

    @objid ("c7315533-d4ec-4ac6-a28b-c558f160088b")
    @Override
    public ShapedFigure getCopy() {
        return new EllipseFigure(this);
    }

}
