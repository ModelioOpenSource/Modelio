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

package org.modelio.diagram.elements.core.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * Base class for a rounded rectangle figure with gradient background.
 */
@objid ("7fabeb17-1dec-11e2-8cad-001ec947c8cc")
public class RoundedBoxFigure extends Figure implements IBrushOptionsSupport, IPenOptionsSupport, HandleBounds {
    @objid ("7fabeb1d-1dec-11e2-8cad-001ec947c8cc")
    private int radius = 14;

    /**
     * Simple convenient public data structure of a brush properties.
     */
    @objid ("7fabeb1e-1dec-11e2-8cad-001ec947c8cc")
    protected BrushOptions brushOptions;

    /**
     * Simple convenient public data structure of a pen properties
     */
    @objid ("7fabeb20-1dec-11e2-8cad-001ec947c8cc")
    protected PenOptions penOptions;

    @objid ("ce9a2a00-c8c8-43e4-a3b5-803ca9b608eb")
    private static Rectangle tempRect = new Rectangle();

    /**
     * Creates a rounded box figure.
     */
    @objid ("7fabeb25-1dec-11e2-8cad-001ec947c8cc")
    public RoundedBoxFigure() {
        this.brushOptions = new BrushOptions();
        this.penOptions = new PenOptions();
        setBorder(new RoundedRectangleBorder(null, 1));
    }

    /**
     * Get the radius value adjusted so that it is not too big for the given rectangle.
     * 
     * @param rect The rectangle the radius must be adjusted to.
     * @return the adjusted radius.
     */
    @objid ("7fabeb28-1dec-11e2-8cad-001ec947c8cc")
    public int getAdjustedRadius(Rectangle rect) {
        // adjust radius if too big compared to width/height
        if (rect.width < this.radius * 3) {
            return rect.width / 3;
        }
        if (rect.height < this.radius * 3) {
            return rect.height / 3;
        }
        return this.radius;
    }

    @objid ("7fabeb30-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getFillColor() {
        return (isOpaque() ? this.brushOptions.fillColor : null);
    }

    @objid ("7fabeb35-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Rectangle getHandleBounds() {
        return getBounds().getCopy();
    }

    @objid ("7fabeb3c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getLineColor() {
        return this.penOptions.lineColor;
    }

    @objid ("7fabeb41-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public LinePattern getLinePattern() {
        return this.penOptions.linePattern;
    }

    @objid ("7fabeb46-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getLineWidth() {
        return this.penOptions.lineWidth;
    }

    /**
     * @return the radius
     */
    @objid ("7fabeb4b-1dec-11e2-8cad-001ec947c8cc")
    public int getRadius() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.radius;
    }

    @objid ("7fabeb50-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getTextColor() {
        return this.penOptions.textColor;
    }

    @objid ("7fabeb55-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Font getTextFont() {
        return this.penOptions.textFont;
    }

    @objid ("7fabeb5a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean getUseGradient() {
        return this.brushOptions.useGradient;
    }

    @objid ("7fae4d71-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setFillColor(Color fillColor) {
        if (this.brushOptions.fillColor == fillColor) {
            return;
        }
        setOpaque((fillColor != null));
        setBackgroundColor(fillColor);
        this.brushOptions.fillColor = fillColor;
        this.repaint();
    }

    @objid ("7fae4d75-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineColor(Color lineColor) {
        if (this.penOptions.lineColor == lineColor) {
            return;
        }
        this.penOptions.lineColor = lineColor;
        ((LineBorder) getBorder()).setColor(lineColor);
        this.repaint();
    }

    @objid ("7fae4d79-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLinePattern(LinePattern linePattern) {
        if (this.penOptions.linePattern != linePattern) {
            this.penOptions.linePattern = linePattern;
            ((LineBorder) getBorder()).setStyle(linePattern.toSWTConstant());
            this.repaint();
        }
    }

    @objid ("7fae4d7d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineWidth(int lineWidth) {
        if (this.penOptions.lineWidth == lineWidth) {
            return;
        }
        this.penOptions.lineWidth = lineWidth;
        ((LineBorder) getBorder()).setWidth(lineWidth);
        this.repaint();
    }

    /**
     * Set the new radius.
     * 
     * @param value the new radius
     */
    @objid ("7fae4d81-1dec-11e2-8cad-001ec947c8cc")
    public void setRadius(int value) {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        this.radius = value;
    }

    @objid ("7fae4d85-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextColor(Color textColor) {
        if (this.penOptions.textColor == textColor) {
            return;
        }
        this.penOptions.textColor = textColor;
        this.repaint();
    }

    @objid ("7fae4d89-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextFont(Font textFont) {
        if (this.penOptions.textFont == textFont) {
            return;
        }
        this.penOptions.textFont = textFont;
        this.repaint();
    }

    @objid ("7fae4d8d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setUseGradient(boolean useGradient) {
        if (this.brushOptions.useGradient == useGradient) {
            return;
        }
        this.brushOptions.useGradient = useGradient;
        this.repaint();
    }

    @objid ("7fae4d91-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void paintFigure(Graphics graphics) {
        RoundedBoxFigure.tempRect = getBounds();
        int adjustedRadius = getAdjustedRadius(RoundedBoxFigure.tempRect);
        int x = RoundedBoxFigure.tempRect.x;
        int y = RoundedBoxFigure.tempRect.y;
        int w = RoundedBoxFigure.tempRect.width;
        int h = RoundedBoxFigure.tempRect.height;
        
        // Paint background
        if (isOpaque()) {
            Color base = this.brushOptions.fillColor;
            Color gradientColor = this.brushOptions.useGradient ? computeGradientColor(base) : base;
            graphics.setBackgroundColor(gradientColor);
            graphics.setForegroundColor(base);
        
            // Compute the clip for rounded corners if radius > 0
            if (adjustedRadius > 0) {
                Path path = new Path(Display.getCurrent());
        
                path.moveTo(x, y + adjustedRadius);
                path.lineTo(x, y + h - adjustedRadius);
                path.addArc(x, y + h - 2 * adjustedRadius, 2 * adjustedRadius, 2 * adjustedRadius, 180, 90);
                path.lineTo(x + w - adjustedRadius, y + h);
                path.addArc(x + w - 2 * adjustedRadius,
                        y + h - 2 * adjustedRadius,
                        2 * adjustedRadius,
                        2 * adjustedRadius,
                        -90,
                        90);
                path.lineTo(x + w, y + adjustedRadius);
                path.addArc(x + w - 2 * adjustedRadius, y, 2 * adjustedRadius, 2 * adjustedRadius, 0, 90);
                path.lineTo(x + adjustedRadius, y);
                path.addArc(x, y, 2 * adjustedRadius, 2 * adjustedRadius, 90, 90);
                graphics.clipPath(path);
            }
        
            graphics.setAlpha(this.brushOptions.alpha);
            if (this.brushOptions.useGradient) {
                graphics.fillGradient(RoundedBoxFigure.tempRect, false);
                gradientColor.dispose();
            } else {
                graphics.fillRectangle(RoundedBoxFigure.tempRect);
            }
        }
        
        // // Draw the rounded rectangle outline
        // if (this.penOptions.lineWidth > 0) {
        // Rectangle rect = getBounds();
        // int xr = rect.x + this.penOptions.lineWidth / 2;
        // int yr = rect.y + this.penOptions.lineWidth / 2;
        // int wr = rect.width - Math.max(1, this.penOptions.lineWidth);
        // int hr = rect.height - Math.max(1, this.penOptions.lineWidth);
        // graphics.setForegroundColor(this.penOptions.lineColor);
        // graphics.drawRoundRectangle(new Rectangle(xr, yr, wr, hr),
        // adjustedRadius * 2, adjustedRadius * 2);
        // }
        graphics.restoreState();
    }

    @objid ("7fae4d97-1dec-11e2-8cad-001ec947c8cc")
    private Color computeGradientColor(Color base) {
        // float[] hsb = base.getRGB().getHSB();
        // Color derivedColor = new Color(base.getDevice(), new RGB(hsb[0],
        // hsb[1] * 0.1f, hsb[2] /*Math.min(hsb[2] * 1.1f, 1.0f)*/ ));
        Color derivedColor = new Color(base.getDevice(), new RGB(255, 255, 255));
        return derivedColor;
    }

    @objid ("7a7a409e-9a62-49dc-8b65-8d71426b90fd")
    @Override
    public void setFillAlpha(int alpha) {
        if (this.brushOptions.alpha != alpha) {
            this.brushOptions.alpha = alpha;
            this.repaint();
        }
    }

    @objid ("db96871a-8231-4d40-9bd9-ff9fa6af3c65")
    @Override
    public int getFillAlpha() {
        return this.brushOptions.alpha;
    }

    /**
     * Specialisation of StyledLineBorder for RoundedBoxFigure.
     * 
     * @author pvlaemynck
     */
    @objid ("7fae4d9c-1dec-11e2-8cad-001ec947c8cc")
    protected static class RoundedRectangleBorder extends LineBorder {
        @objid ("95171c2a-ec65-4ca6-8d6e-bea7274da1fb")
        protected static final double COS45 = Math.cos(Math.toRadians(45));

        /**
         * Constructs a RoundedRectangleBorder with the specified color and of the specified width.
         * 
         * @param color The color of the border.
         * @param width The width of the border in pixels.
         * @since 2.0
         */
        @objid ("7fae4da0-1dec-11e2-8cad-001ec947c8cc")
        public RoundedRectangleBorder(Color color, int width) {
            super(color, width);
        }

        @objid ("7fae4da5-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public void paint(IFigure figure, Graphics graphics, Insets insets) {
            AbstractBorder.tempRect.setBounds(getPaintRectangle(figure, insets));
            if (getWidth() % 2 != 0) {
                AbstractBorder.tempRect.width--;
                AbstractBorder.tempRect.height--;
            }
            AbstractBorder.tempRect.shrink(getWidth() / 2, getWidth() / 2);
            if (getColor() != null) {
                graphics.setForegroundColor(getColor());
            }
            graphics.setLineWidth(getWidth());
            graphics.setLineStyle(getStyle());
            int borderRadius = ((RoundedBoxFigure) figure).getAdjustedRadius(AbstractBorder.tempRect);
            graphics.drawRoundRectangle(AbstractBorder.tempRect, borderRadius * 2, borderRadius * 2);
        }

        /**
         * Returns the space used by the border for the figure provided as input. In this border all sides always have equal width.
         * 
         * @param figure The figure this border belongs to
         * @return This border's insets
         */
        @objid ("7fae4db1-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Insets getInsets(IFigure figure) {
            int borderRadius = ((RoundedBoxFigure) figure).getAdjustedRadius(figure.getBounds());
            int lineWidth = getWidth();
            if (borderRadius > 0) {
            
                double radiusinset = borderRadius - (borderRadius - lineWidth) * RoundedRectangleBorder.COS45;
            
                return new Insets(Math.max(lineWidth, (int) radiusinset));
                // return new Insets(0 + this.getWidth() / 2 + borderRadius * 40 / 100);
            } else {
                return new Insets(lineWidth);
            }
        }

    }

}
