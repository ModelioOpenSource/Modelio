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

package org.modelio.diagram.editor.sequence.elements.combinedfragment.primarynode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.elements.core.figures.IShaper;

/**
 * Specialised figure drawing additional decoration.
 * 
 * @author fpoyer
 */
@objid ("d8d2b51d-55b6-11e2-877f-002564c97630")
public class OperatorShaper implements IShaper {
    @objid ("7e9dd371-35a7-45f4-9659-f9a77cc778c7")
    private static final Insets INSETS = new Insets(1, 1, 5, 5);

    /**
     * Constructs and empty label.
     */
    @objid ("d8d2b524-55b6-11e2-877f-002564c97630")
    public OperatorShaper() {
        super();
    }

    @objid ("0a7bbd8f-4388-4e70-a688-537d2115fcf7")
    @Override
    public Path getShapePath(Rectangle r) {
        Path p = new Path(Display.getDefault());
        p.moveTo(r.x                     , r.bottom() - 1);
        p.lineTo(r.right() - INSETS.right, r.bottom() - 1);
        p.lineTo(r.right() - 1           , r.bottom() - INSETS.bottom);
        p.lineTo(r.right() - 1           , r.y);
        p.lineTo(r.x                     , r.y);
        p.lineTo(r.x                     , r.bottom() - 1);
        return p;
    }

    @objid ("564df198-f4c5-4313-aa82-453d99c7e8d6")
    @Override
    public Insets getShapeInsets(Rectangle rect) {
        return INSETS;
    }

}
