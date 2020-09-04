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

package org.modelio.diagram.elements.core.figures.geometry;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Indicates a direction.
 * 
 * @author cmarin
 */
@objid ("7f7e9e6a-1dec-11e2-8cad-001ec947c8cc")
public enum Direction {
    /**
     * Not defined
     */
    NONE,
    /**
     * North
     */
    NORTH,
    /**
     * East
     */
    EAST,
    /**
     * South
     */
    SOUTH,
    /**
     * West
     */
    WEST;
}
