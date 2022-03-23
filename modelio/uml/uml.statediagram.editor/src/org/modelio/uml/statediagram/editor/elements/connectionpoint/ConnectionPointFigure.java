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
package org.modelio.uml.statediagram.editor.elements.connectionpoint;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Path;
import org.modelio.diagram.elements.core.figures.EllipseFigure;

/**
 * connection point reference figure
 */
@objid ("f4faa767-55b6-11e2-877f-002564c97630")
public class ConnectionPointFigure extends EllipseFigure {
    @objid ("f6ce2eda-55b6-11e2-877f-002564c97630")
    private ReferencedConnectionPoint Reference = ReferencedConnectionPoint.NOREF;

    /**
     * Sets the exact type of decoration to use.
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
        
        graphics.setAdvanced(true);
        graphics.setAntialias(SWT.ON);
        
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
        graphics.restoreState();
        
    }

    @objid ("f4faa777-55b6-11e2-877f-002564c97630")
    private void paintConnectionPoint(Graphics graphics, Rectangle r) {
        // reclip with path and draw crossed lines
        Path shapePath = createShapePath(r);
        try {
            if (shapePath != null) {
                graphics.setClip(shapePath);
            }
        
            graphics.setForegroundColor(this.penOptions.lineColor);
            graphics.setLineWidth(this.penOptions.lineWidth);
            graphics.drawLine(r.getTopRight(), r.getCenter());
            graphics.drawLine(r.getCenter(), r.getBottomRight());
        } finally {
            if (shapePath != null) {
                shapePath.dispose();
            }
        }
        
    }

    @objid ("dac33aa1-0573-43d9-8247-8ff43d93f999")
    protected Path createShapePath(Rectangle r) {
        Path shapePath = null;
        if (this.shaper != null) {
            shapePath = this.shaper.createShapePath(r);
        }
        return shapePath;
    }

    @objid ("f4faa77b-55b6-11e2-877f-002564c97630")
    private void paintExitFigure(Graphics graphics, Rectangle r) {
        // reclip with path and draw crossed lines
        Path shapePath = createShapePath(r);
        try {
            if (shapePath != null) {
                graphics.setClip(shapePath);
            }
        
            graphics.setForegroundColor(this.penOptions.lineColor);
            graphics.setLineWidth(this.penOptions.lineWidth);
            graphics.drawLine(r.getTopLeft(), r.getBottomRight());
            graphics.drawLine(r.getTopRight(), r.getBottomLeft());
        } finally {
            if (shapePath != null) {
                shapePath.dispose();
            }
        }
        
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
        @objid ("f4faa781-55b6-11e2-877f-002564c97630")
        ENTRYREF,
        /**
         * This is used when the reference is to an exit point
         */
        @objid ("f4faa783-55b6-11e2-877f-002564c97630")
        EXITREF,
        /**
         * This is used when the reference is undefined
         */
        @objid ("f4faa785-55b6-11e2-877f-002564c97630")
        NOREF;

    }

}
