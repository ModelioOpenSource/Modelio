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
import org.modelio.diagram.elements.core.link.IGmAnchorListener;

/**
 * Represents a graphic link.
 * <p>
 * The graphic link may represent a model element or not.
 */
@objid ("04f63c5d-56f4-4d21-b956-7beea3c4ed68")
public interface IGmLinkObject extends IGmObject, IGmAnchorListener {
    /**
     * @return the link path.
     */
    @objid ("d39c17d5-4717-4071-817d-cc72051a4002")
    IGmPath getPath();

}
