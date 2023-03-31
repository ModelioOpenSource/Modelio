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
package org.modelio.diagram.elements.core.figures.anchors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.figures.geometry.Direction;

/**
 * Rectangular assimilated figures faces constants.
 * @author cmarin
 * @since 5.3.1
 */
@objid ("c3f0ecba-e0f7-4846-97ff-16d34ba9050f")
public abstract class FacesConstants {
    @objid ("c7ff5ab9-14a8-4ae8-ab53-3f92ca164a0a")
    public static final String[] FACE_LABELS = new String[]{"NORTH", "EAST", "SOUTH", "WEST"};

    @objid ("92a45f24-174a-40e9-970e-4d01b0826d02")
    public static final Direction[] FACE_DIRECTION = new Direction[]{Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};

    @objid ("8b66349c-34be-4f35-af0a-5ad45a991edd")
    public static final int FACE_EAST = 1;

    @objid ("1acbeaf9-1245-42d1-9301-afa63fe9f458")
    public static final int FACE_NORTH = 0;

    @objid ("a2ef1712-3ccf-4c62-a9a8-02ff75549d66")
    public static final int FACE_SOUTH = 2;

    @objid ("bbc0df60-8ab2-445d-9132-00dc6a827757")
    public static final int FACE_WEST = 3;

}
