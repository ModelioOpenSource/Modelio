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

package org.modelio.diagram.editor.state.elements.connectionpoint;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.EllipseFigure;

/**
 * connection point reference figure
 */
@objid ("f4faa767-55b6-11e2-877f-002564c97630")
public class ConnectionPointFigure extends EllipseFigure {
    @objid ("f6ce2eda-55b6-11e2-877f-002564c97630")
    private ReferencedConnectionPoint Reference = ReferencedConnectionPoint.NOREF;

    /**
     * C'tor.
     */
    @objid ("f4faa76c-55b6-11e2-877f-002564c97630")
    public ConnectionPointFigure() {
        super();
    }

    /**
     * Sets the exact type of decoration to use.
     * 
     * @param reference the type of decoration to use.
     */
    @objid ("f4faa76f-55b6-11e2-877f-002564c97630")
    public void setReference(ReferencedConnectionPoint reference) {
        if (this.Reference != reference) {
            this.Reference = reference;
            this.repaint();
        }
    }

    @objid ("f4faa773-55b6-11e2-877f-002564c97630")
    @Override
    protected void paintFigure(Graphics graphics) {
        // super method will draw the shapedFigure
        super.paintFigure(graphics);
        Rectangle r = getBounds().getCopy();
        
        switch (this.Reference) {
        case EXITREF:
            paintExitFigure(graphics, r);
            break;
        case NOREF:
            paintConnectionPoint(graphics, r);
            break;
        default:
            break;
        }
    }

    @objid ("f4faa777-55b6-11e2-877f-002564c97630")
    private void paintConnectionPoint(Graphics graphics, Rectangle r) {
        // reclip with path and draw crossed lines
        if (this.shaper != null) {
            graphics.setClip(this.shaper.getShapePath(r));
        }
        
        graphics.setForegroundColor(this.penOptions.lineColor);
        graphics.setLineWidth(this.penOptions.lineWidth);
        graphics.drawLine(r.getTopRight(), r.getCenter());
        graphics.drawLine(r.getCenter(), r.getBottomRight());
    }

    @objid ("f4faa77b-55b6-11e2-877f-002564c97630")
    private void paintExitFigure(Graphics graphics, Rectangle r) {
        // reclip with path and draw crossed lines
        if (this.shaper != null) {
            graphics.setClip(this.shaper.getShapePath(r));
        }
        
        graphics.setForegroundColor(this.penOptions.lineColor);
        graphics.setLineWidth(this.penOptions.lineWidth);
        graphics.drawLine(r.getTopLeft(), r.getBottomRight());
        graphics.drawLine(r.getTopRight(), r.getBottomLeft());
    }

    /**
     * The different type of decoration a connection point reference can use.
     * 
     * @author fpoyer
     */
    @objid ("f4faa77f-55b6-11e2-877f-002564c97630")
    public enum ReferencedConnectionPoint {
        /**
         * This is used when the reference is to an entry point
         */
        ENTRYREF,
        /**
         * This is used when the reference is to an exit point
         */
        EXITREF,
        /**
         * This is used when the reference is undefined
         */
        NOREF;
    }

}
