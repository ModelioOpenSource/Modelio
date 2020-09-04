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

package org.modelio.diagram.editor.state.elements.exit;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.EllipseFigure;

/**
 * Figure for a {@link GmExitPointPrimaryNode}.
 * <p>
 * It is a filled circle with an X splitting it.
 * 
 * @author sbe
 */
@objid ("f5192bfc-55b6-11e2-877f-002564c97630")
public class ExitPointFigure extends EllipseFigure {
    @objid ("f5192c00-55b6-11e2-877f-002564c97630")
    @Override
    protected void paintFigure(Graphics graphics) {
        // super method will draw the shapedFigure
        super.paintFigure(graphics);
        
        // Reclip with path and draw crossed lines
        final Rectangle r = getBounds().getCopy();
        
        if (this.shaper != null)
            graphics.setClip(this.shaper.getShapePath(r));
        
        graphics.setForegroundColor(this.penOptions.lineColor);
        graphics.setLineWidth(this.penOptions.lineWidth);
        graphics.drawLine(r.getTopLeft(), r.getBottomRight());
        graphics.drawLine(r.getTopRight(), r.getBottomLeft());
    }

}
