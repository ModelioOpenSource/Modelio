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

package org.modelio.diagram.editor.statik.elements.ports;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.RectangularFigure;
import org.modelio.metamodel.uml.statik.PortOrientation;

@objid ("364b3c3e-55b7-11e2-877f-002564c97630")
public class PortFigure extends RectangularFigure {
    @objid ("364b3c41-55b7-11e2-877f-002564c97630")
    private int position = PositionConstants.NONE;

    @objid ("364b3c42-55b7-11e2-877f-002564c97630")
    private PortOrientation portDirection = PortOrientation.NONE;

    @objid ("364b3c45-55b7-11e2-877f-002564c97630")
    public void setPosition(final int position) {
        this.position = position;
    }

    @objid ("364b3c49-55b7-11e2-877f-002564c97630")
    @Override
    protected void paintFigure(final Graphics g) {
        super.paintFigure(g);
        g.setLineWidth(getLineWidth());
        g.setForegroundColor(getLineColor());
        g.setBackgroundColor(getLineColor());
        switch (this.portDirection) {
        case IN: {
            drawPortIn(g);
            break;
        }
        case OUT: {
            drawPortOut(g);
            break;
        }
        case INOUT: {
            drawPortInout(g);
            break;
        }
        case NONE: {
            break;
        }
        }
        
        g.restoreState();
    }

    @objid ("364b3c4e-55b7-11e2-877f-002564c97630")
    public void setPortDirection(final PortOrientation portDirection) {
        if (portDirection != this.portDirection) {
            this.portDirection = portDirection;
            repaint();
        }
    }

    @objid ("364b3c54-55b7-11e2-877f-002564c97630")
    private void drawPortIn(final Graphics g) {
        Rectangle rect = this.getBounds().getShrinked(2, 2);
        int x = rect.x;
        int y = rect.y;
        int w = rect.width;
        int h = rect.height;
        
        switch (this.position) {
        case PositionConstants.NORTH:
        case PositionConstants.NORTH_EAST: {
            drawArrowPointingSouth(g, x, y, w, h);
            break;
        }
        case PositionConstants.EAST:
        case PositionConstants.SOUTH_EAST: {
            drawArrowPointingWest(g, x, y, w, h);
            break;
        }
        case PositionConstants.SOUTH:
        case PositionConstants.SOUTH_WEST: {
            drawArrowPointingNorth(g, x, y, w, h);
            break;
        }
        case PositionConstants.WEST:
        case PositionConstants.NORTH_WEST: {
            drawArrowPointingEast(g, x, y, w, h);
            break;
        }
        }
    }

    @objid ("364cc2b9-55b7-11e2-877f-002564c97630")
    private void drawPortOut(final Graphics g) {
        Rectangle rect = this.getBounds().getShrinked(2, 2);
        int x = rect.x;
        int y = rect.y;
        int w = rect.width;
        int h = rect.height;
        
        switch (this.position) {
        case PositionConstants.SOUTH:
        case PositionConstants.SOUTH_WEST: {
            drawArrowPointingSouth(g, x, y, w, h);
            break;
        }
        case PositionConstants.WEST:
        case PositionConstants.NORTH_WEST: {
            drawArrowPointingWest(g, x, y, w, h);
            break;
        }
        case PositionConstants.NORTH:
        case PositionConstants.NORTH_EAST: {
            drawArrowPointingNorth(g, x, y, w, h);
            break;
        }
        case PositionConstants.EAST:
        case PositionConstants.SOUTH_EAST: {
            drawArrowPointingEast(g, x, y, w, h);
            break;
        }
        }
    }

    @objid ("364cc2bd-55b7-11e2-877f-002564c97630")
    private void drawPortInout(final Graphics g) {
        Rectangle rect = this.getBounds().getShrinked(2, 2);
        int x = rect.x;
        int y = rect.y;
        int w = rect.width;
        int h = rect.height;
        
        int[] points = { x + (w / 2), y - 1, x, y + (h / 2), x + (w / 2), y + h, x + w, y + (h / 2) };
        g.fillPolygon(points);
    }

    @objid ("364cc2c1-55b7-11e2-877f-002564c97630")
    private void drawArrowPointingSouth(final Graphics g, final int x, final int y, final int w, final int h) {
        int[] points = { x, y + (h / 4) + 1, x + (w / 2), y + (3 * h / 4) + 2, x + w, y + (h / 4) + 1 };
        g.fillPolygon(points);
    }

    @objid ("364cc2cd-55b7-11e2-877f-002564c97630")
    private void drawArrowPointingNorth(final Graphics g, final int x, final int y, final int w, final int h) {
        int[] points = { x - 1, y + (3 * h / 4), x + (w / 2), y + (h / 4) - 2, x + w + 1, y + (3 * h / 4) };
        g.fillPolygon(points);
    }

    @objid ("364cc2d9-55b7-11e2-877f-002564c97630")
    private void drawArrowPointingWest(final Graphics g, final int x, final int y, final int w, final int h) {
        int[] points = { x + (3 * w / 4), y - 1, x + (w / 4) - 1, y + (h / 2), x + (3 * w / 4), y + h + 1 };
        g.fillPolygon(points);
    }

    @objid ("364cc2e5-55b7-11e2-877f-002564c97630")
    private void drawArrowPointingEast(final Graphics g, final int x, final int y, final int w, final int h) {
        int[] points = { x + (w / 4) + 1, y - 1, x + (3 * w / 4) + 2, y + (h / 2), x + (w / 4) + 1, y + h };
        g.fillPolygon(points);
    }

}
