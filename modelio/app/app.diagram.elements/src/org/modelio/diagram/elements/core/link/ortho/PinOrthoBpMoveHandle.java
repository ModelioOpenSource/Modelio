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
package org.modelio.diagram.elements.core.link.ortho;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Graphics;
import org.eclipse.gef.ConnectionEditPart;
import org.modelio.diagram.elements.core.figures.RotatedPinFigureHelper;
import org.modelio.diagram.elements.core.figures.geometry.Orientation;

/**
 * Orthogonal bendpoint handle that look like a pin planted on the bend point.
 * 
 * @author cma
 * @since 5.0.2
 */
@objid ("601287c4-583e-4f64-beb9-718c20f51cf3")
public class PinOrthoBpMoveHandle extends OrthoBendPointMoveHandle {
    @objid ("6fdc5761-15d4-4a2d-b003-ce2e470658f0")
    private final RotatedPinFigureHelper helper;

    @objid ("f83427be-a355-4333-83da-1c64d355695b")
    public  PinOrthoBpMoveHandle(ConnectionEditPart owner, int index, Orientation orientationOfPreviousSegment) {
        super(owner, index, orientationOfPreviousSegment);
        
        this.helper = new RotatedPinFigureHelper((Connection) owner.getFigure(), index);
        
        // Force use helper as locator.
        setLocator(this.helper);
        
    }

    /**
     * Initializes the handle.
     */
    @objid ("b11363c5-1cc1-4c9a-b37a-2869b5722132")
    @Override
    protected void init() {
        setPreferredSize(null);
    }

    /**
     * Draws the handle with fill color and outline color dependent on the primary selection status of the owner editpart.
     * @param g The graphics used to paint the figure.
     */
    @objid ("036d95f9-90ca-4c69-a55a-49cb002c4fc8")
    @Override
    public void paintFigure(Graphics g) {
        this.helper.paintFigure(g, isPrimary());
    }

}
