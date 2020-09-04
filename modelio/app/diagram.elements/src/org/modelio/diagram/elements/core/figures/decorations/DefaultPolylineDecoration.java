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

package org.modelio.diagram.elements.core.figures.decorations;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.figures.ZoomDrawer;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * {@link PolylineDecoration} that:
 * <ul>
 * <li>supports {@link IPenOptionsSupport}.
 * <li>Scales itself whit its line width so that the decoration background is still visible.
 * </ul>
 * 
 * @author cmarin
 */
@objid ("7f72b2ce-1dec-11e2-8cad-001ec947c8cc")
public class DefaultPolylineDecoration extends PolylineDecoration implements IPenOptionsSupport {
    @objid ("7f72b2d2-1dec-11e2-8cad-001ec947c8cc")
     double scalex = 1;

    @objid ("7f72b2d3-1dec-11e2-8cad-001ec947c8cc")
     double scaley = 1;

    @objid ("7f751501-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getLineColor() {
        return getForegroundColor();
    }

    @objid ("7f751506-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getTextColor() {
        return null;
    }

    @objid ("7f75150b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Font getTextFont() {
        return getFont();
    }

    @objid ("7f751510-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineColor(Color lineColor) {
        setForegroundColor(lineColor);
    }

    @objid ("7f751514-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextColor(Color textColor) {
        // Nothing to do
    }

    @objid ("7f751518-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextFont(Font textFont) {
        setFont(textFont);
    }

    @objid ("7f75151c-1dec-11e2-8cad-001ec947c8cc")
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
    @objid ("7f751522-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setScale(double x, double y) {
        this.scalex = x;
        this.scaley = y;
        super.setScale(x + 2 * getLineWidth() - 2, y + 2 * getLineWidth() - 2);
    }

    /**
     * Redefined to scale the decoration depending on the line width.
     * <p>
     * Line width of 1 has no effect, upper does scale the decoration.
     */
    @objid ("7f751528-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineWidth(int w) {
        super.setScale(this.scalex + 2 * w - 2, this.scaley + 2 * w - 2);
        super.setLineWidth(w);
    }

    @objid ("7f77775b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Rectangle getBounds() {
        // trying to fix bug in inherited method where the bounding box
        // is too small when the drawn figure is a triangle and the line width
        // is big.
        if (this.bounds == null) {
            this.bounds = getPoints().getBounds().getExpanded(getLineWidth(), getLineWidth());
        }
        return this.bounds;
    }

    @objid ("7f777762-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public LinePattern getLinePattern() {
        return LinePattern.fromSWTConstant(this.getLineStyle());
    }

    @objid ("7f777767-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLinePattern(LinePattern linePattern) {
        this.setLineStyle(linePattern.toSWTConstant());
    }

}
