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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This interface means that the diagram graphic has the ability to be source or target of a link.
 */
@objid ("80801c8c-1dec-11e2-8cad-001ec947c8cc")
public interface IGmLinkable extends IGmModelRelated {
    /**
     * Get the links going to this node.
     * @return the ongoing links.
     */
    @objid ("80801c8e-1dec-11e2-8cad-001ec947c8cc")
    List<IGmLink> getStartingLinks();

    /**
     * Get the links going to this node.
     * @return the ongoing links.
     */
    @objid ("80801c93-1dec-11e2-8cad-001ec947c8cc")
    List<IGmLink> getEndingLinks();

    /**
     * Add a link starting from this node.
     * @param link The starting link.
     */
    @objid ("80801c98-1dec-11e2-8cad-001ec947c8cc")
    void addStartingLink(IGmLink link);

    /**
     * Add a link going to this element.
     * @param link the ongoing link.
     */
    @objid ("80801c9b-1dec-11e2-8cad-001ec947c8cc")
    void addEndingLink(IGmLink link);

    /**
     * Remove a link starting from this node.
     * @param gmLink the link to remove.
     */
    @objid ("80801c9e-1dec-11e2-8cad-001ec947c8cc")
    void removeStartingLink(IGmLink gmLink);

    /**
     * Remove a link going to this node.
     * @param gmLink the link to remove.
     */
    @objid ("80801ca1-1dec-11e2-8cad-001ec947c8cc")
    void removeEndingLink(IGmLink gmLink);

}
