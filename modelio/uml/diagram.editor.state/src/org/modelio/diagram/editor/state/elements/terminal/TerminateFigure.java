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

package org.modelio.diagram.editor.state.elements.terminal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.GradientFigure;

/**
 * Figure for a {@link GmTerminalPrimaryNode terminal state node}.
 * 
 * @author sbe
 */
@objid ("f5a7142c-55b6-11e2-877f-002564c97630")
public class TerminateFigure extends GradientFigure {
    /**
     * Creates the figure.
     */
    @objid ("f5a71430-55b6-11e2-877f-002564c97630")
    public TerminateFigure() {
        super();
        setSize(30, 30);
        this.setOpaque(false);
    }

    @objid ("f5a71433-55b6-11e2-877f-002564c97630")
    @Override
    public void paint(Graphics graphics) {
        // super method will draw the shapedFigure
        super.paintFigure(graphics);
        
        final Rectangle r = getBounds();
        graphics.setForegroundColor(this.penOptions.lineColor);
        graphics.setLineWidth(this.penOptions.lineWidth);
        graphics.drawLine(r.getTopLeft(), r.getBottomRight());
        graphics.drawLine(r.getTopRight(), r.getBottomLeft());
    }

}
