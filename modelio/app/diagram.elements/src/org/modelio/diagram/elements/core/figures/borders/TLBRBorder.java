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
import org.modelio.diagram.elements.core.figures.ZoomDrawer;

/**
 * Border that draws at choice a line on the top, left, bottom and/or right borders of the figure bounds.
 * <p>
 * To be used on figures that have a rectangle shape.
 */
@objid ("7f64648b-1dec-11e2-8cad-001ec947c8cc")
public class TLBRBorder extends LineBorder {
    @objid ("7f64648f-1dec-11e2-8cad-001ec947c8cc")
    private boolean drawTop;

    @objid ("7f646490-1dec-11e2-8cad-001ec947c8cc")
    private boolean drawLeft;

    @objid ("7f646491-1dec-11e2-8cad-001ec947c8cc")
    private boolean drawBottom;

    @objid ("7f646492-1dec-11e2-8cad-001ec947c8cc")
    private boolean drawRight;

    /**
     * Default C'tor. Create a border with all sides visible, default color and width of 1.
     */
    @objid ("7f646493-1dec-11e2-8cad-001ec947c8cc")
    public TLBRBorder() {
        this(true, true, true, true);
    }

    /**
     * C'tor that allows to specify which sides are visible. Uses default color and width of 1.
     * @param drawTop true if top side should be drawn
     * @param drawLeft true if left side should be drawn
     * @param drawBottom true if bottom side should be drawn
     * @param drawRight true if right side should be drawn
     */
    @objid ("7f646496-1dec-11e2-8cad-001ec947c8cc")
    public TLBRBorder(final boolean drawTop, final boolean drawLeft, final boolean drawBottom, final boolean drawRight) {
        this(1, drawTop, drawLeft, drawBottom, drawRight);
    }

    /**
     * C'tor that allows to specify the line width and which sides are visible. Uses default color.
     * @param width the width of the border in pixels
     * @param drawTop true if top side should be drawn
     * @param drawLeft true if left side should be drawn
     * @param drawBottom true if bottom side should be drawn
     * @param drawRight true if right side should be drawn
     */
    @objid ("7f6464a1-1dec-11e2-8cad-001ec947c8cc")
    public TLBRBorder(final int width, final boolean drawTop, final boolean drawLeft, final boolean drawBottom, final boolean drawRight) {
        this(null, width, drawTop, drawLeft, drawBottom, drawRight);
    }

    /**
     * C'tor that allows to specify the line color, the line width and which sides are visible.
     * @param color the color of the border
     * @param width the width of the border in pixels
     * @param drawTop true if top side should be drawn
     * @param drawLeft true if left side should be drawn
     * @param drawBottom true if bottom side should be drawn
     * @param drawRight true if right side should be drawn
     */
    @objid ("7f6464ae-1dec-11e2-8cad-001ec947c8cc")
    public TLBRBorder(final Color color, final int width, final boolean drawTop, final boolean drawLeft, final boolean drawBottom, final boolean drawRight) {
        super(color, width);
        this.drawTop = drawTop;
        this.drawLeft = drawLeft;
        this.drawBottom = drawBottom;
        this.drawRight = drawRight;
    }

    @objid ("7f6464bd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void paint(final IFigure figure, final Graphics graphics, final Insets insets) {
        AbstractBorder.tempRect = getPaintRectangle(figure, insets);
        final int lineWidth = getWidth();
        if (lineWidth % 2 != 0) {
            AbstractBorder.tempRect.width--;
            AbstractBorder.tempRect.height--;
        }
        
        ZoomDrawer.setLineWidth(graphics, lineWidth);
        
        graphics.setLineStyle(getStyle());
        if (getColor() != null) {
            graphics.setForegroundColor(getColor());
        }
        
        // Compute rectangular border just like in LineBorder
        tempRect.shrink(lineWidth / 2, lineWidth / 2);
        
        // Only draw selected segments of tempRect rectangle
        if (this.drawTop) {
            // graphics.drawLine(AbstractBorder.tempRect.x, AbstractBorder.tempRect.y + halfLineWidth, AbstractBorder.tempRect.right(), AbstractBorder.tempRect.y + halfLineWidth);
            graphics.drawLine(AbstractBorder.tempRect.x - lineWidth / 2, AbstractBorder.tempRect.y,
                    AbstractBorder.tempRect.right() + lineWidth / 2, AbstractBorder.tempRect.y);
        }
        if (this.drawLeft) {
            // graphics.drawLine(AbstractBorder.tempRect.x + halfLineWidth, AbstractBorder.tempRect.y, AbstractBorder.tempRect.x + halfLineWidth, AbstractBorder.tempRect.bottom() + lineWidth);
            graphics.drawLine(AbstractBorder.tempRect.x, AbstractBorder.tempRect.y,
                    AbstractBorder.tempRect.x, AbstractBorder.tempRect.bottom() + lineWidth / 2);
        }
        if (this.drawBottom) {
            // graphics.drawLine(AbstractBorder.tempRect.x, AbstractBorder.tempRect.bottom() - halfLineWidth, AbstractBorder.tempRect.right(), AbstractBorder.tempRect.bottom() - halfLineWidth);
            graphics.drawLine(AbstractBorder.tempRect.x, AbstractBorder.tempRect.bottom(),
                    AbstractBorder.tempRect.right(), AbstractBorder.tempRect.bottom());
        }
        if (this.drawRight) {
            // graphics.drawLine(AbstractBorder.tempRect.right() - halfLineWidth, AbstractBorder.tempRect.y, AbstractBorder.tempRect.right() - halfLineWidth, AbstractBorder.tempRect.bottom() + halfLineWidth);
            graphics.drawLine(AbstractBorder.tempRect.right(), AbstractBorder.tempRect.y,
                    AbstractBorder.tempRect.right(), AbstractBorder.tempRect.bottom() + lineWidth / 2);
        }
    }

    /**
     * Changes whether the top side should be drawn or not.
     * @param drawTop true if the top side should be drawn.
     */
    @objid ("7f6464cc-1dec-11e2-8cad-001ec947c8cc")
    public void setDrawTop(final boolean drawTop) {
        this.drawTop = drawTop;
    }

    /**
     * Changes whether the left side should be drawn or not.
     * @param drawLeft true if the left side should be drawn.
     */
    @objid ("7f66c6e7-1dec-11e2-8cad-001ec947c8cc")
    public void setDrawLeft(final boolean drawLeft) {
        this.drawLeft = drawLeft;
    }

    /**
     * Changes whether the bottom side should be drawn or not.
     * @param drawBottom true if the bottom side should be drawn.
     */
    @objid ("7f66c6ec-1dec-11e2-8cad-001ec947c8cc")
    public void setDrawBottom(final boolean drawBottom) {
        this.drawBottom = drawBottom;
    }

    /**
     * Changes whether the right side should be drawn or not.
     * @param drawRight true if the right side should be drawn.
     */
    @objid ("7f66c6f1-1dec-11e2-8cad-001ec947c8cc")
    public void setDrawRight(final boolean drawRight) {
        this.drawRight = drawRight;
    }

    @objid ("9ea24459-ae04-4b64-9a70-e6546689a1bc")
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append(" [draw=");
        if (this.drawTop) {
            builder.append("top");
        }
        if (this.drawLeft) {
            builder.append(" left");
        }
        if (this.drawBottom) {
            builder.append(" bottom");
        }
        if (this.drawRight) {
            builder.append(" right");
        }
        
        builder.append(", ");
        builder.append("color=");
        builder.append(getColor());
        builder.append(", ");
        builder.append("width=");
        builder.append(getWidth());
        builder.append("]");
        return builder.toString();
    }

    @objid ("a63eda41-dc6f-404a-8b98-a63a23849607")
    public boolean hasDrawnTop() {
        return this.drawTop;
    }

    @objid ("e91ec840-a217-4e6e-acde-97fbfde2716b")
    public boolean hasDrawnBottom() {
        return this.drawBottom;
    }

    @objid ("4d0a9cca-50e6-42ad-bf1b-0a0573558473")
    public boolean hasDrawnLeft() {
        return this.drawLeft;
    }

    @objid ("6d95393b-21c6-450b-8d8f-7d8156d0334a")
    public boolean hasDrawnRight() {
        return this.drawRight;
    }

    @objid ("cc73de61-55b6-4a9f-8087-3e6b55aa34c8")
    @Override
    public Insets getInsets(IFigure figure) {
        return new Insets(
                hasDrawnTop() ? getWidth() : 0,
                hasDrawnLeft() ? getWidth() : 0,
                hasDrawnBottom() ? getWidth() : 0,
                hasDrawnRight() ? getWidth() : 0);
    }

}
