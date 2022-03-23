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
package org.modelio.diagram.elements.drawings.core;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This interface means that the diagram graphic has the ability to be source or target of a link.
 */
@objid ("13ceca2b-6629-40db-b530-9c5c8d1e72d2")
public interface IGmDrawingLinkable extends IGmDrawing {
    /**
     * Get the links going to this node.
     * @return the ongoing links.
     */
    @objid ("b5dfe29a-2980-4415-99a9-239acbc9ca07")
    List<IGmDrawingLink> getStartingDrawingLinks();

    /**
     * Get the links going to this node.
     * @return the ongoing links.
     */
    @objid ("766e660f-d90b-4d1d-9f69-6e5830594cca")
    List<IGmDrawingLink> getEndingDrawingLinks();

    /**
     * Add a link starting from this node.
     * @param link The starting link.
     */
    @objid ("6408cf3e-48f7-427a-b558-589a579fed4a")
    void addStartingDrawingLink(IGmDrawingLink link);

    /**
     * Add a link going to this element.
     * @param link the ongoing link.
     */
    @objid ("ec105eab-448a-42c2-8bad-5acccb06de6c")
    void addEndingDrawingLink(IGmDrawingLink link);

    /**
     * Remove a link starting from this node.
     * @param gmLink the link to remove.
     */
    @objid ("cfdff1a1-c8e8-479a-96a4-92eaf792506e")
    void removeStartingDrawingLink(IGmDrawingLink gmLink);

    /**
     * Remove a link going to this node.
     * @param gmLink the link to remove.
     */
    @objid ("680f9fe3-d8f2-43d6-8553-f77a9ea788c8")
    void removeEndingDrawingLink(IGmDrawingLink gmLink);

}
