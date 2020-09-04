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

package org.modelio.diagram.elements.core.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PrinterGraphics;

/**
 * Utility class to handle line drawing when the diagram is zoomed.
 * <p>
 * In this case the line width is also zoomed .
 * 
 * @author cmarin
 */
@objid ("7fcfae67-1dec-11e2-8cad-001ec947c8cc")
public class ZoomDrawer {
    /**
     * Set the graphics line width depending on the zoom level and the graphics type.
     * 
     * @param g the graphics
     * @param width line width
     */
    @objid ("7fcfae69-1dec-11e2-8cad-001ec947c8cc")
    public static void setLineWidth(Graphics g, int width) {
        if (!(g instanceof PrinterGraphics)) {
            double dw = width * g.getAbsoluteScale();
            g.setLineWidth((int) Math.ceil(dw));
        }
    }

}
