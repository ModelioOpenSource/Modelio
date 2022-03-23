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
package org.modelio.uml.activitydiagram.editor.elements.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.elements.core.figures.IOrientableShaper;
import org.modelio.diagram.elements.core.figures.IOrientableShaper.Orientation;

/**
 * Shaper for the ExpansionNodeFigure
 * 
 * @author fpoyer
 */
@objid ("2a6c19db-55b6-11e2-877f-002564c97630")
public class ExpansionNodeShaper implements IOrientableShaper {
    @objid ("2a6c19df-55b6-11e2-877f-002564c97630")
    private Orientation orientation = Orientation.Undefined;

    @objid ("2a6c19e2-55b6-11e2-877f-002564c97630")
    @Override
    public Insets getShapeInsets(Rectangle rect) {
        return new Insets(0, 0, 0, 0);
    }

    @objid ("2a6c19e8-55b6-11e2-877f-002564c97630")
    @Override
    public Path createShapePath(Rectangle rect) {
        int x = rect.x;
        int y = rect.y;
        int w = rect.width;
        int h = rect.height;
        Path path = new Path(Display.getCurrent());
        path.moveTo(x, y);
        path.lineTo(x + w, y);
        path.lineTo(x + w, y + h);
        path.lineTo(x, y + h);
        path.lineTo(x, y);
        
        switch (this.orientation) {
        case NorthSouth:
        case SouthNorth: {
            int inc = h / 4;
            path.moveTo(x, y + (inc));
            path.lineTo(x + w, y + (inc));
            path.moveTo(x, y + (inc * 2));
            path.lineTo(x + w, y + (inc * 2));
            path.moveTo(x, y + (inc * 3));
            path.lineTo(x + w, y + (inc * 3));
            break;
        }
        case WestEast:
        case EastWest:
        case Undefined: {
            int inc = w / 4;
            path.moveTo(x + (inc), y);
            path.lineTo(x + (inc), y + h);
            path.moveTo(x + (inc * 2), y);
            path.lineTo(x + (inc * 2), y + h);
            path.moveTo(x + (inc * 3), y);
            path.lineTo(x + (inc * 3), y + h);
            break;
        }
        }
        return path;
    }

    /**
     * Sets the orientation of the shaper.
     * @param orientation the new orientation. Must be either {@link IOrientableShaper.Orientation#NorthSouth NorthSouth} or {@link IOrientableShaper.Orientation#WestEast WestEast}
     */
    @objid ("2a6c19ee-55b6-11e2-877f-002564c97630")
    @Override
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    /**
     * @return the current orientation.
     */
    @objid ("2a6c19f5-55b6-11e2-877f-002564c97630")
    public Orientation getOrientation() {
        return this.orientation;
    }

}
