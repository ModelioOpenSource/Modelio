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

package org.modelio.diagram.editor.activity.elements.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.core.figures.ShapedFigure;
import org.modelio.diagram.elements.core.figures.borders.ShapedBorder;

@objid ("2a70adbb-55b6-11e2-877f-002564c97630")
public class TimeEventFigure extends ShapedFigure {
    @objid ("d0638b09-55c0-11e2-9337-002564c97630")
     ShapedBorder shapedBorder;

    @objid ("2a70adc1-55b6-11e2-877f-002564c97630")
    public TimeEventFigure() {
        super(new TimeEventShaper());
        setSize(30, 30);
        setOpaque(true);
        this.shapedBorder = new ShapedBorder(this.penOptions.lineColor,
                this.penOptions.lineWidth,
                this.shaper);
        setBorder(this.shapedBorder);
    }

    @objid ("2a70adc3-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineColor(Color lineColor) {
        if (this.penOptions.lineColor != lineColor) {
            this.shapedBorder.setColor(lineColor);
            super.setLineColor(lineColor);
        }
    }

    @objid ("2a70adc7-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineWidth(int lineWidth) {
        if (this.penOptions.lineWidth != lineWidth) {
            this.shapedBorder.setWidth(lineWidth);
            super.setLineWidth(lineWidth);
        }
    }

}
