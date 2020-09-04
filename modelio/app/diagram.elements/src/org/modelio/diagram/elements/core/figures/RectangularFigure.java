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
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.core.figures.borders.TLBRBorder;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * A Box figure is a rectangular GradientFigure completed by a compound border made of a LineBorder (supporting
 * PenOptions) and a inner margin border
 * 
 * @author pvlaemyn
 */
@objid ("7fa988eb-1dec-11e2-8cad-001ec947c8cc")
public class RectangularFigure extends GradientFigure {
    @objid ("7fa988ee-1dec-11e2-8cad-001ec947c8cc")
    private static final int MARGIN = 1;

    @objid ("7fa988ed-1dec-11e2-8cad-001ec947c8cc")
    private TLBRBorder lineBorder;

    /**
     * Creates a new rectangular figure.
     */
    @objid ("7fa988f0-1dec-11e2-8cad-001ec947c8cc")
    public RectangularFigure() {
        super();
        this.lineBorder = new TLBRBorder();
        this.setBorder(new CompoundBorder(this.lineBorder, new MarginBorder(MARGIN)));
    }

    @objid ("7fa988f3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineColor(Color lineColor) {
        if (lineColor != this.penOptions.lineColor) {
            this.lineBorder.setColor(lineColor);
            super.setLineColor(lineColor);
        }
    }

    @objid ("7fa988f7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineWidth(int lineWidth) {
        if (lineWidth != this.penOptions.lineWidth) {
            this.lineBorder.setWidth(lineWidth);
            super.setLineWidth(lineWidth);
        }
    }

    @objid ("7fa988fb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLinePattern(LinePattern linePattern) {
        if (linePattern != this.penOptions.linePattern) {
            this.lineBorder.setStyle(linePattern.toSWTConstant());
            super.setLinePattern(linePattern);
        }
    }

    /**
     * Changes whether the top side should be drawn or not.
     * @param drawTop true if the top side should be drawn.
     */
    @objid ("7fa988ff-1dec-11e2-8cad-001ec947c8cc")
    public void setDrawTop(final boolean drawTop) {
        this.lineBorder.setDrawTop(drawTop);
    }

    /**
     * Changes whether the left side should be drawn or not.
     * @param drawLeft true if the left side should be drawn.
     */
    @objid ("7fa98904-1dec-11e2-8cad-001ec947c8cc")
    public void setDrawLeft(final boolean drawLeft) {
        this.lineBorder.setDrawLeft(drawLeft);
    }

    /**
     * Changes whether the bottom side should be drawn or not.
     * @param drawBottom true if the bottom side should be drawn.
     */
    @objid ("7fa98909-1dec-11e2-8cad-001ec947c8cc")
    public void setDrawBottom(final boolean drawBottom) {
        this.lineBorder.setDrawBottom(drawBottom);
    }

    /**
     * Changes whether the right side should be drawn or not.
     * @param drawRight true if the right side should be drawn.
     */
    @objid ("7fa9890e-1dec-11e2-8cad-001ec947c8cc")
    public void setDrawRight(final boolean drawRight) {
        this.lineBorder.setDrawRight(drawRight);
    }

}
