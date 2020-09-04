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

package org.modelio.diagram.elements.core.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Represents a link rake.
 * <p>
 * All link raked together share the same link rake and must share the same anchor model.
 * 
 * @author cmarin
 */
@objid ("80801cb2-1dec-11e2-8cad-001ec947c8cc")
public interface IGmLinkRake {
    /**
     * Get the node anchor that must be used by all link raked together.
     * @return the shared extremity anchor.
     */
    @objid ("80801cb4-1dec-11e2-8cad-001ec947c8cc")
    Object getSharedAnchor();

}
