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
package org.modelio.uml.statediagram.editor.elements;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Fork/join node orientation.
 * 
 * @author sbe
 */
@objid ("f5393716-55b6-11e2-877f-002564c97630")
public enum ForkJoinOrientation {
    /**
     * The fork/join is horizontal.
     */
    @objid ("f5393718-55b6-11e2-877f-002564c97630")
    HORIZONTAL,
    /**
     * The fork/join is vertical.
     */
    @objid ("f539371a-55b6-11e2-877f-002564c97630")
    VERTICAL;

}
