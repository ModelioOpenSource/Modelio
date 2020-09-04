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

package org.modelio.diagram.editor.state.elements.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.core.figures.ShapedFigure;
import org.modelio.diagram.elements.core.figures.borders.ShapedBorder;

/**
 * Diamond shaped figure.
 * 
 * @author sbe
 */
@objid ("f522539a-55b6-11e2-877f-002564c97630")
public class DiamondFigure extends ShapedFigure {
    @objid ("8160932a-55c2-11e2-9337-002564c97630")
    private ShapedBorder shapedBorder;

    /**
     * Creates the figure.
     */
    @objid ("f52253a1-55b6-11e2-877f-002564c97630")
    public DiamondFigure() {
        super();
        setSize(30, 40);
        setShaper(new DiamondShaper());
        this.setOpaque(true);
        this.shapedBorder = new ShapedBorder(this.penOptions.lineColor,
                                             this.penOptions.lineWidth,
                                             this.shaper);
        
        setBorder(new CompoundBorder(this.shapedBorder, new MarginBorder(2)));
    }

    @objid ("f52253a4-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineColor(Color lineColor) {
        if (lineColor != this.penOptions.lineColor) {
            super.setLineColor(lineColor);
            this.shapedBorder.setColor(lineColor);
        }
    }

    @objid ("f52253a8-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineWidth(int lineWidth) {
        if (lineWidth != this.penOptions.lineWidth) {
            super.setLineWidth(lineWidth);
            this.shapedBorder.setWidth(lineWidth);
        }
    }

}
