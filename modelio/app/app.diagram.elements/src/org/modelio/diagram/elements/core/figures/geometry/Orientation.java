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
package org.modelio.diagram.elements.core.figures.geometry;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Defines horizontal or vertical orientation.
 * 
 * @author cmarin
 */
@objid ("7f8cec9f-1dec-11e2-8cad-001ec947c8cc")
public enum Orientation {
    /**
     * horizontal
     */
    @objid ("7f8ceca1-1dec-11e2-8cad-001ec947c8cc")
    HORIZONTAL,
    /**
     * Vertical
     */
    @objid ("7f8ceca3-1dec-11e2-8cad-001ec947c8cc")
    VERTICAL,
    /**
     * Not defined or oblique.
     */
    @objid ("7f8ceca5-1dec-11e2-8cad-001ec947c8cc")
    NONE;

    /**
     * return the perpendicular orientation of this orientation.
     * @return the perpendicular orientation.
     */
    @objid ("7f8ceca7-1dec-11e2-8cad-001ec947c8cc")
    public Orientation getPerpendicular() {
        if (this == HORIZONTAL)
            return VERTICAL;
        else if (this == VERTICAL)
            return HORIZONTAL;
        else
            return NONE;
        
    }

}
