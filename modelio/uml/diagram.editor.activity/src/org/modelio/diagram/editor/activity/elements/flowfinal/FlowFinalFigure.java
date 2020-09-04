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

package org.modelio.diagram.editor.activity.elements.flowfinal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.EllipseFigure;

/**
 * figure for a {@link GmFlowFinalPrimaryNode}.
 * 
 * @author cmarin
 */
@objid ("2a73bafe-55b6-11e2-877f-002564c97630")
public class FlowFinalFigure extends EllipseFigure {
    @objid ("2a75417b-55b6-11e2-877f-002564c97630")
    @Override
    protected void paintFigure(Graphics graphics) {
        // super method will draw the shapedFigure
        super.paintFigure(graphics);
        
        final Rectangle r = getBounds().getCopy();
        // reclip with path and draw crossed lines
        if (this.shaper != null)
            graphics.setClip(this.shaper.getShapePath(r));
        
        graphics.setForegroundColor(this.penOptions.lineColor);
        graphics.setLineWidth(this.penOptions.lineWidth);
        graphics.drawLine(r.getTopLeft(), r.getBottomRight());
        graphics.drawLine(r.getTopRight(), r.getBottomLeft());
    }

}
