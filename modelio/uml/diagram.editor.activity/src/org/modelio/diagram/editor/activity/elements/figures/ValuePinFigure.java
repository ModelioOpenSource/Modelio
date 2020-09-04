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

package org.modelio.diagram.editor.activity.elements.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.core.figures.GradientFigure;

@objid ("2a723444-55b6-11e2-877f-002564c97630")
public class ValuePinFigure extends GradientFigure {
    @objid ("2a723447-55b6-11e2-877f-002564c97630")
    private LineBorder lineBorder;

    @objid ("2a723448-55b6-11e2-877f-002564c97630")
    public ValuePinFigure() {
        super();
        this.setOpaque(true);
        this.lineBorder = new LineBorder(1);
        setBorder(this.lineBorder);
    }

    @objid ("2a72344a-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineColor(Color lineColor) {
        if (lineColor != this.penOptions.lineColor) {
            super.setLineColor(lineColor);
            this.lineBorder.setColor(lineColor);
        }
    }

    @objid ("2a72344e-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineWidth(int lineWidth) {
        if (lineWidth != this.penOptions.lineWidth) {
            super.setLineWidth(lineWidth);
            this.lineBorder.setWidth(lineWidth);
        }
    }

    @objid ("2a723452-55b6-11e2-877f-002564c97630")
    @Override
    protected void paintFigure(Graphics g) {
        super.paintFigure(g);
        Rectangle r = this.getBounds().getCopy();
        
        r.shrink(r.width / 3, r.height / 3);
        g.setBackgroundColor(this.penOptions.lineColor);
        g.fillRectangle(r);
    }

}
