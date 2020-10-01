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

package org.modelio.uml.sequencediagram.editor.elements.executionspecification;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.elements.core.figures.IBrushOptionsSupport;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.figures.RectangularFigure;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * Figure (ie View in the GEF MVC model) for Execution in Sequence Diagram. A bit complex mainly because it has to handle the layout of nested Executions.
 */
@objid ("d8e68b2a-55b6-11e2-877f-002564c97630")
public class ExecutionSpecificationFigure extends Figure implements IBrushOptionsSupport, IPenOptionsSupport, HandleBounds {
    @objid ("4fa5406a-55c2-11e2-9337-002564c97630")
    private RectangularFigure visibleFigure;

    /**
     * C'tor.
     */
    @objid ("d8e68b32-55b6-11e2-877f-002564c97630")
    public ExecutionSpecificationFigure() {
        super();
        this.visibleFigure = new RectangularFigure();
        this.visibleFigure.setOpaque(true);
        // Use super implementation to avoid automatic increase of index.
        super.add(this.visibleFigure, null, 0);
    }

    @objid ("d8e68b35-55b6-11e2-877f-002564c97630")
    @Override
    public void add(final IFigure figure, final Object constraint, final int index) {
        // Increase index by one, because index 0 is already used by this.visibleFigure.
        super.add(figure, constraint, index + 1);
    }

    /**
     * Get the fill color.
     * 
     * @return the fill color.
     */
    @objid ("d8e68b3e-55b6-11e2-877f-002564c97630")
    @Override
    public Color getFillColor() {
        return this.visibleFigure.getFillColor();
    }

    /**
     * Returns the Rectangle around which handles are to be placed. The Rectangle should be in the same coordinate system as the figure itself.
     * 
     * @return The rectangle used for handles
     */
    @objid ("d8e68b44-55b6-11e2-877f-002564c97630")
    @Override
    public Rectangle getHandleBounds() {
        return this.visibleFigure.getBounds().getCopy();
    }

    /**
     * Get the line color.
     * 
     * @return the line color.
     */
    @objid ("d8e68b4a-55b6-11e2-877f-002564c97630")
    @Override
    public Color getLineColor() {
        return this.visibleFigure.getLineColor();
    }

    /**
     * Get the line pattern
     * 
     * @return lineStyle the line style See {@link LinePattern}
     */
    @objid ("d8e68b50-55b6-11e2-877f-002564c97630")
    @Override
    public LinePattern getLinePattern() {
        return this.visibleFigure.getLinePattern();
    }

    /**
     * Get the line width.
     * 
     * @return the line width.
     */
    @objid ("d8e68b58-55b6-11e2-877f-002564c97630")
    @Override
    public int getLineWidth() {
        return this.visibleFigure.getLineWidth();
    }

    /**
     * Get the text color.
     * 
     * @return the text color.
     */
    @objid ("d8e68b5e-55b6-11e2-877f-002564c97630")
    @Override
    public Color getTextColor() {
        return this.visibleFigure.getTextColor();
    }

    /**
     * Get the text font.
     * 
     * @return the text font.
     */
    @objid ("d8e811bb-55b6-11e2-877f-002564c97630")
    @Override
    public Font getTextFont() {
        return this.visibleFigure.getTextFont();
    }

    /**
     * Tells whether the background is filled with a gradient.
     * 
     * @return true if the background is filled with a gradient, false in the other case.
     */
    @objid ("d8e811c1-55b6-11e2-877f-002564c97630")
    @Override
    public boolean getUseGradient() {
        return this.visibleFigure.getUseGradient();
    }

    /**
     * Set the fill color.
     * 
     * @param fillColor the fill color.
     */
    @objid ("d8e811c7-55b6-11e2-877f-002564c97630")
    @Override
    public void setFillColor(Color fillColor) {
        this.visibleFigure.setFillColor(fillColor);
    }

    /**
     * Set the line(s) color.
     * 
     * @param lineColor the line color.
     */
    @objid ("d8e811cc-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineColor(Color lineColor) {
        this.visibleFigure.setLineColor(lineColor);
    }

    /**
     * Sets the line pattern to the argument, which must be one of the constants
     * 
     * {@link LinePattern}
     * 
     * @param lineStyle the new style
     */
    @objid ("d8e811d1-55b6-11e2-877f-002564c97630")
    @Override
    public void setLinePattern(LinePattern lineStyle) {
        this.visibleFigure.setLinePattern(lineStyle);
    }

    /**
     * Set the line(s) width.
     * 
     * @param lineWidth the line(s) width.
     */
    @objid ("d8e811d8-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineWidth(int lineWidth) {
        this.visibleFigure.setLineWidth(lineWidth);
    }

    /**
     * Set the text color.
     * 
     * @param textColor the text color.
     */
    @objid ("d8e811dd-55b6-11e2-877f-002564c97630")
    @Override
    public void setTextColor(Color textColor) {
        this.visibleFigure.setTextColor(textColor);
    }

    /**
     * Set the text font.
     * 
     * @param textFont the text font.
     */
    @objid ("d8e811e2-55b6-11e2-877f-002564c97630")
    @Override
    public void setTextFont(Font textFont) {
        this.visibleFigure.setTextFont(textFont);
    }

    /**
     * Set whether the background is filled with a gradient.
     * 
     * @param useGradient true to fill with a gradient, false to fill only with the fill color.
     */
    @objid ("d8e811e7-55b6-11e2-877f-002564c97630")
    @Override
    public void setUseGradient(boolean useGradient) {
        this.visibleFigure.setUseGradient(useGradient);
    }

    @objid ("a9022eab-c875-4169-a7a8-adb83faa7a79")
    @Override
    public void setFillAlpha(int alpha) {
        this.visibleFigure.setFillAlpha(alpha);
    }

    @objid ("d76c22d8-882b-48f0-a7b3-e3e974484c6b")
    @Override
    public int getFillAlpha() {
        return this.visibleFigure.getFillAlpha();
    }

    /**
     * Layout that indents child figures by 3/4 the execution standard width.
     */
    @objid ("d8e811ec-55b6-11e2-877f-002564c97630")
    class ExecutionXYLayout extends XYLayout {
        @objid ("d8e811ed-55b6-11e2-877f-002564c97630")
        public ExecutionXYLayout() {
            super();
        }

        @objid ("d8e811ef-55b6-11e2-877f-002564c97630")
        @Override
        public void layout(IFigure container) {
            ExecutionSpecificationFigure executionFigure = (ExecutionSpecificationFigure) container;
            int X = executionFigure.getBounds().x + (ExecutionSpecificationEditPart.EXECUTION_WIDTH * 3 / 4);
            
            for (Object obj : container.getChildren()) {
                Figure child = (Figure) obj;
                Rectangle constraintRect = (Rectangle) this.getConstraint(child);
                Rectangle newBounds = constraintRect.getCopy();
                newBounds.x = X;
                newBounds.width = child.getPreferredSize().width;
                child.setBounds(newBounds);
            }
        }

        @objid ("d8e99859-55b6-11e2-877f-002564c97630")
        @Override
        protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {
            int childMaxWidth = 0;
            for (Object obj : container.getChildren()) {
                Figure child = (Figure) obj;
                childMaxWidth = Math.max(childMaxWidth, child.getPreferredSize().width);
            }
            int width = (childMaxWidth == 0) ? ExecutionSpecificationEditPart.EXECUTION_WIDTH
                    : (ExecutionSpecificationEditPart.EXECUTION_WIDTH * 3 / 4) + childMaxWidth;
            int height = container.getBounds().height;
            return new Dimension(width, height);
        }

    }

}
