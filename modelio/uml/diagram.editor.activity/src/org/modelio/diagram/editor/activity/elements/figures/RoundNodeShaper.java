/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.activity.elements.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.elements.core.figures.IShaper;

@objid ("2a6f2719-55b6-11e2-877f-002564c97630")
public class RoundNodeShaper implements IShaper {
    @objid ("2a6f271c-55b6-11e2-877f-002564c97630")
    @Override
    public Insets getShapeInsets(Rectangle rect) {
        return new Insets(0, getDepth(rect), 0, 0);
    }

    @objid ("2a6f2722-55b6-11e2-877f-002564c97630")
    @Override
    public Path getShapePath(Rectangle rect) {
        int x = rect.x;
        int y = rect.y;
        int w = rect.width;
        int h = rect.height;
        
        Path path = new Path(Display.getCurrent());
        
        path.moveTo(x, y);
        path.addArc(x, y, w, h, 0, 360);
        return path;
    }

    @objid ("2a6f2728-55b6-11e2-877f-002564c97630")
    private int getDepth(Rectangle rect) {
        return rect.height / 3;
    }

}
