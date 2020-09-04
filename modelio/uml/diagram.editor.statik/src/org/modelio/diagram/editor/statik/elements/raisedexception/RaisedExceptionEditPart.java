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

package org.modelio.diagram.editor.statik.elements.raisedexception;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.core.figures.EllipseFigure;
import org.modelio.diagram.elements.core.figures.decorations.DefaultPolylineDecoration;
import org.modelio.diagram.elements.core.link.LinkEditPart;

/**
 * Edit part for {@link GmRaisedException}.
 * 
 * @author cmarin
 */
@objid ("36621f85-55b7-11e2-877f-002564c97630")
public class RaisedExceptionEditPart extends LinkEditPart {
    @objid ("36621f89-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // Create the link
        final PolylineConnection connection = (PolylineConnection) super.createFigure();
        
        // Set style independent properties
        
        // Navigability arrow toward target
        final DefaultPolylineDecoration arrow = new DefaultPolylineDecoration();
        arrow.setTemplate(PolylineDecoration.TRIANGLE_TIP);
        arrow.setScale(9, 4);
        arrow.setOpaque(false);
        arrow.setBackgroundColor(null);
        arrow.setFill(false);
        connection.setTargetDecoration(arrow);
        
        CircleDeco circle = new CircleDeco();
        circle.setOpaque(true);
        circle.setSize(6, 6);
        circle.setBackgroundColor(null);
        connection.setSourceDecoration(circle);
        
        // Set style dependent properties
        refreshFromStyle(connection, getModelStyle());
        
        // Set style dependent properties
        return connection;
    }

    @objid ("36621f8e-55b7-11e2-877f-002564c97630")
    private static class CircleDeco extends EllipseFigure implements RotatableDecoration {
        @objid ("36621f92-55b7-11e2-877f-002564c97630")
        public CircleDeco() {
        }

        @objid ("36621f94-55b7-11e2-877f-002564c97630")
        @Override
        public void setReferencePoint(final Point p) {
            // Do nothing
        }

        @objid ("36621f99-55b7-11e2-877f-002564c97630")
        @Override
        public void setLocation(final Point p) {
            Dimension dim = getBounds().getSize().scale(0.5);
            super.setLocation(new Point(p.x - dim.width, p.y - dim.height));
        }

        @objid ("36621f9e-55b7-11e2-877f-002564c97630")
        @Override
        public Color getFillColor() {
            return super.getLineColor();
        }

        @objid ("36621fa3-55b7-11e2-877f-002564c97630")
        @Override
        public void setFillColor(final Color fillColor) {
            super.setFillColor(getLineColor());
        }

        @objid ("36621fa8-55b7-11e2-877f-002564c97630")
        @Override
        public void setLineColor(final Color lineColor) {
            super.setLineColor(lineColor);
            setFillColor(lineColor);
        }

    }

}
