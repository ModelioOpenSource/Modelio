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
package org.modelio.diagram.elements.drawings.line;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Decorations on source and target side of the line.
 */
@objid ("35a8eedc-89fb-4154-a149-b88d41800935")
public enum LineDecoration {
    @objid ("c00de1c5-2689-4511-a987-0cb9e099b541")
    ARROW,
    @objid ("e8ad1bfe-4a45-45fa-ba5b-3e9c29787442")
    TRIANGLE,
    @objid ("bbcf4d68-0fc5-4a1f-8e53-fc92104b15b0")
    LOSANGE,
    @objid ("bad05062-b83f-43ac-927d-5801be23fa6d")
    NONE;

}
