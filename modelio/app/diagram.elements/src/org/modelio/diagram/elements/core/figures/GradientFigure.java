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

package org.modelio.diagram.elements.core.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * Base class for rectangle figure with gradient background.
 */
@objid ("7fa261af-1dec-11e2-8cad-001ec947c8cc")
public class GradientFigure extends Figure implements IBrushOptionsSupport, IPenOptionsSupport, HandleBounds {
    @objid ("7fa261b5-1dec-11e2-8cad-001ec947c8cc")
    protected BrushOptions brushOptions;

    @objid ("7fa261b9-1dec-11e2-8cad-001ec947c8cc")
    protected PenOptions penOptions;

    @objid ("6330be17-1e83-11e2-8cad-001ec947c8cc")
    private static Rectangle tempRect = new Rectangle();

    @objid ("7fa261ba-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Rectangle getHandleBounds() {
        return getBounds().getCopy();
    }

    /**
     * Creates a gradient figure.
     */
    @objid ("7fa261c1-1dec-11e2-8cad-001ec947c8cc")
    public GradientFigure() {
        this.brushOptions = new BrushOptions();
        this.penOptions = new PenOptions();
    }

    @objid ("7fa261c4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void paintFigure(Graphics graphics) {
        if (isOpaque() && this.brushOptions.fillColor != null) {
            Color base = this.brushOptions.fillColor;
            Color gradientColor = this.brushOptions.useGradient ? computeGradientColor(base) : base;
            graphics.setBackgroundColor(gradientColor);
            graphics.setForegroundColor(base);
            graphics.setAlpha(this.brushOptions.alpha);
        
            tempRect = getPaintRectangle();
        
            if (this.brushOptions.useGradient) {
                graphics.fillGradient(tempRect, false);
                gradientColor.dispose();
            } else {
                graphics.fillRectangle(tempRect);
            }
        }
        
        graphics.restoreState();
    }

    @objid ("7fa261ca-1dec-11e2-8cad-001ec947c8cc")
    protected Color computeGradientColor(Color base) {
        // float[] hsb = base.getRGB().getHSB();
        // Color derivedColor = new Color(base.getDevice(), new RGB(hsb[0], hsb[1] * 0.1f, hsb[2] /*Math.min(hsb[2] * 1.1f, 1.0f)*/ ));
        Color derivedColor = new Color(base.getDevice(), new RGB(255, 255, 255));
        return derivedColor;
    }

    @objid ("7fa261cf-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setFillColor(Color fillColor) {
        if (this.brushOptions.fillColor != fillColor) {
            setBackgroundColor(fillColor);
            this.brushOptions.fillColor = fillColor;
            this.repaint();
        }
    }

    @objid ("7fa261d3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getFillColor() {
        return this.brushOptions.fillColor;
    }

    @objid ("7fa261d8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean getUseGradient() {
        return this.brushOptions.useGradient;
    }

    @objid ("7fa261dd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setUseGradient(boolean useGradient) {
        if (this.brushOptions.useGradient != useGradient) {
            this.brushOptions.useGradient = useGradient;
            this.repaint();
        }
    }

    @objid ("7fa261e1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getLineColor() {
        return this.penOptions.lineColor;
    }

    @objid ("7fa261e6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getLineWidth() {
        return this.penOptions.lineWidth;
    }

    @objid ("7fa261eb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getTextColor() {
        return this.penOptions.textColor;
    }

    @objid ("7fa261f0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Font getTextFont() {
        return this.penOptions.textFont;
    }

    @objid ("7fa261f5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineColor(Color lineColor) {
        if (this.penOptions.lineColor != lineColor) {
            this.penOptions.lineColor = lineColor;
            this.repaint();
        }
    }

    @objid ("7fa4c40a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineWidth(int lineWidth) {
        if (this.penOptions.lineWidth != lineWidth) {
            this.penOptions.lineWidth = lineWidth;
            this.repaint();
        }
    }

    @objid ("7fa4c40e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextColor(Color textColor) {
        if (this.penOptions.textColor != textColor) {
            this.penOptions.textColor = textColor;
            this.repaint();
        }
    }

    @objid ("7fa4c412-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextFont(Font textFont) {
        if (this.penOptions.textFont != textFont) {
            this.penOptions.textFont = textFont;
            this.repaint();
        }
    }

    @objid ("7fa4c416-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public LinePattern getLinePattern() {
        return this.penOptions.linePattern;
    }

    @objid ("7fa4c41b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLinePattern(LinePattern lineStyle) {
        if (this.penOptions.linePattern != lineStyle) {
            this.penOptions.linePattern = lineStyle;
            this.repaint();
        }
    }

    @objid ("8e1447f4-a6ee-4e12-a9b2-70b54a996a7f")
    @Override
    public void setFillAlpha(int alpha) {
        if (this.brushOptions.alpha != alpha) {
            this.brushOptions.alpha = alpha;
            this.repaint();
        }
    }

    @objid ("d12ba4b3-4884-4030-8eeb-0a91ef43bbc8")
    @Override
    public int getFillAlpha() {
        return this.brushOptions.alpha;
    }

    /**
     * Copy constructor.
     * <p>
     * Copy only what can be safely be copied, skip layout manager, Border and composition hierarchy.
     * @param orig the original figure.
     */
    @objid ("e98df5b2-c891-4034-a851-af50e389a784")
    public GradientFigure(GradientFigure orig) {
        // No copy constructor on Figure ;-(
        super();
        
        // Figure copy constructor emulation
        this.bounds.setBounds(orig.getBounds());
        // don't set border nor layout manager : we usually have no copy constructor for them.
        
        // GradientFigure specific
        this.brushOptions = new BrushOptions(orig.brushOptions);
        this.penOptions = new PenOptions(orig.penOptions);
    }

    @objid ("e6b25987-b256-454b-bbde-79316a90607e")
    @Override
    public String toString() {
        return String.format("%s [%s%sbounds = %s, id=%s]",
                getClass().getSimpleName(),
                isVisible() ? "" : "invisible ",
                getParent() == null ? "orphan, " : "",
                getBounds(),
                java.lang.System.identityHashCode(this));
    }

    /**
     * The rectangle to be painted by the gradient. It is computed by shrinking the figure bounds by the (outer) border insets (only the 'margin' part of the border)
     * 
     * This method might require re-definition in sub-classes, should these subclasses use a different approach to draw the gradient background.
     * 
     * Typically {@link ShapedFigure} uses a clippath to limit the gradient drawing instead of relying on their shaped border.
     * @return the static tempRect is returned to avoid expensive rectangle allocations.
     */
    @objid ("3217fcd8-e156-4637-b4d3-509eb98e73f7")
    protected Rectangle getPaintRectangle() {
        Border b = this.getBorder();
        Insets insets = NO_INSETS;
        if (b != null && b instanceof CompoundBorder) {
            insets = ((CompoundBorder) b).getOuterBorder().getInsets(this);
        }
        tempRect = getBounds().getShrinked(insets);
        return tempRect;
    }

}
