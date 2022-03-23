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
import org.modelio.diagram.elements.common.portcontainer.PortConstraint.Border;
import org.modelio.diagram.elements.core.figures.IOrientableShaper.Orientation;

@objid ("2a6f270f-55b6-11e2-877f-002564c97630")
public class OutputPinFigure extends InputPinFigure {
    @objid ("2a6f2710-55b6-11e2-877f-002564c97630")
    @Override
    public void setOrientation(Border b) {
        switch (b) {
        case North:
            this.orientation = Orientation.SouthNorth;
            break;
        case South:
            this.orientation = Orientation.NorthSouth;
            break;
        case East:
            this.orientation = Orientation.WestEast;
            break;
        case West:
            this.orientation = Orientation.EastWest;
            break;
        default:
            this.orientation = Orientation.Undefined;
            break;
        }
        
    }

}
