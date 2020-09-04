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

package org.modelio.diagram.elements.core.figures.decorations;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.elements.core.figures.IBrushOptionsSupport;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.figures.ZoomDrawer;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * {@link PolygonDecoration} that:
 * <ul>
 * .
 * <li>Supports {@link IPenOptionsSupport} and {@link IBrushOptionsSupport}.
 * <li>Scales itself whit its line width so that the decoration background is still visible.
 * </ul>
 * 
 * @author cmarin
 */
@objid ("7f70504e-1dec-11e2-8cad-001ec947c8cc")
public class DefaultPolygonDecoration extends PolygonDecoration implements IPenOptionsSupport, IBrushOptionsSupport {
    @objid ("7f705052-1dec-11e2-8cad-001ec947c8cc")
    private double scalex = 1;

    @objid ("7f705053-1dec-11e2-8cad-001ec947c8cc")
    private double scaley = 1;

    @objid ("47ca4568-635e-4183-8980-b5aed29734dc")
    private int alpha;

    @objid ("7f705054-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getLineColor() {
        return getForegroundColor();
    }

    @objid ("7f705059-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getTextColor() {
        return null;
    }

    @objid ("7f70505e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Font getTextFont() {
        return getFont();
    }

    @objid ("7f705063-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineColor(Color lineColor) {
        setForegroundColor(lineColor);
    }

    @objid ("7f705067-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextColor(Color textColor) {
        // Nothing to do
    }

    @objid ("7f70506b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextFont(Font textFont) {
        setFont(textFont);
    }

    @objid ("7f70506f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getFillColor() {
        return getBackgroundColor();
    }

    @objid ("7f705074-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean getUseGradient() {
        return false;
    }

    @objid ("7f705079-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setFillColor(Color fillColor) {
        setBackgroundColor(fillColor);
    }

    @objid ("7f70507d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setUseGradient(boolean useGradient) {
        // Nothing to do
    }

    @objid ("7f72b2a9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void outlineShape(Graphics g) {
        ZoomDrawer.setLineWidth(g, getLineWidth());
        
        super.outlineShape(g);
    }

    /**
     * Sets the amount of scaling to be done along X and Y axes on the PolylineDecoration's template.
     * <p>
     * Redefined to take into account the current line width too.
     */
    @objid ("7f72b2af-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setScale(double x, double y) {
        this.scalex = x;
        this.scaley = y;
        super.setScale(x + getLineWidth() * 2 - 2, y + getLineWidth() * 2 - 2);
    }

    /**
     * Redefined to scale the decoration depending on the line width.
     * <p>
     * Line width of 1 has no effect, upper does scale the decoration.
     */
    @objid ("7f72b2b5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineWidth(int w) {
        super.setScale(this.scalex + 2 * w - 2, this.scaley + 2 * w - 2);
        super.setLineWidth(w);
    }

    @objid ("7f72b2ba-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Rectangle getBounds() {
        // trying to fix bug in inherited method where the bounding box
        // is too small when the drawn figure is a triangle and the line width
        // is large.
        if (this.bounds == null) {
            this.bounds = getPoints().getBounds().getExpanded(getLineWidth(), getLineWidth());
        }
        return this.bounds;
    }

    @objid ("7f72b2c1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public LinePattern getLinePattern() {
        return LinePattern.fromSWTConstant(this.getLineStyle());
    }

    @objid ("7f72b2c6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLinePattern(LinePattern linePattern) {
        this.setLineStyle(linePattern.toSWTConstant());
    }

    @objid ("2af825e6-e386-4ee9-b11b-40d50ae8912c")
    @Override
    public void setFillAlpha(int alpha) {
        if (this.alpha != alpha) {
            this.alpha = alpha;
            this.repaint();
        }
    }

    @objid ("238e7d9c-6491-4ca2-a4cd-ad470b384037")
    @Override
    public int getFillAlpha() {
        return this.alpha;
    }

}
