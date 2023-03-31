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

/**
 * Orientable shaper.
 */
@objid ("7fa4c432-1dec-11e2-8cad-001ec947c8cc")
public interface IOrientableShaper extends IShaper {
    /**
     * @param orientation the orientation
     */
    @objid ("7fa4c433-1dec-11e2-8cad-001ec947c8cc")
    void setOrientation(Orientation orientation);

    /**
     * Orientation
     */
    @objid ("7fa4c435-1dec-11e2-8cad-001ec947c8cc")
    enum Orientation {
        /**
         * Do not stick to any specific direction.
         */
        @objid ("7fa4c436-1dec-11e2-8cad-001ec947c8cc")
        Undefined,
        /**
         * The top border.
         */
        @objid ("7fa4c438-1dec-11e2-8cad-001ec947c8cc")
        NorthSouth,
        /**
         * The bottom border.
         */
        @objid ("7fa4c43a-1dec-11e2-8cad-001ec947c8cc")
        SouthNorth,
        /**
         * the right border.
         */
        @objid ("7fa4c43c-1dec-11e2-8cad-001ec947c8cc")
        EastWest,
        /**
         * The left border.
         */
        @objid ("7fa4c43e-1dec-11e2-8cad-001ec947c8cc")
        WestEast;

    }
}

