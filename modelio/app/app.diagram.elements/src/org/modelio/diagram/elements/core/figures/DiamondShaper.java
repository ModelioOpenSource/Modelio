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
 * Draws a diamond shape.
 * 
 * @author sbe
 */
@objid ("7f77776f-1dec-11e2-8cad-001ec947c8cc")
public class DiamondShaper implements IShaper {
    @objid ("7f777771-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Insets getShapeInsets(Rectangle rect) {
        return new Insets(0, getDepth(rect), 0, 0);
    }

    @objid ("7f77777b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Path createShapePath(Rectangle rect) {
        int x = rect.x;
        int y = rect.y;
        int w = rect.width;
        int h = rect.height;
        
        Path path = new Path(Display.getCurrent());
        
        path.moveTo(x + w / 2 , y);
        path.lineTo(x + w     , y + h / 2);
        path.lineTo(x + w / 2 , y + h);
        path.lineTo(x         , y + h / 2);
        path.lineTo(x + w / 2 , y);
        return path;
    }

    @objid ("7f777783-1dec-11e2-8cad-001ec947c8cc")
    private int getDepth(Rectangle rect) {
        return rect.height / 3;
    }

}
