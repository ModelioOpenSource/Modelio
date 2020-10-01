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

package org.modelio.diagram.elements.core.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Display;

/**
 * Shapes an ellipse that touches the given rectangle borders.
 * 
 * @author phv
 */
@objid ("7f79d9bf-1dec-11e2-8cad-001ec947c8cc")
public class EllipseShaper implements IShaper {
    @objid ("7f79d9c1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Insets getShapeInsets(Rectangle rect) {
        final int a = rect.width / 2;
        final int b = rect.height / 2;
        final double sqrt2 = Math.sqrt(2);
        final int x = (int) Math.ceil(a / sqrt2);
        final int y = (int) Math.ceil(b / sqrt2);
        return new Insets(b - y, a - x, b - y, a - x);
    }

    @objid ("7f79d9cb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Path getShapePath(Rectangle rect) {
        int x = rect.x;
        int y = rect.y;
        int w = rect.width - 1;
        int h = rect.height - 1;
        
        Path path = new Path(Display.getCurrent());
        
        path.moveTo(x, y);
        path.addArc(x, y, w, h, 0, 360);
        return path;
    }

}
