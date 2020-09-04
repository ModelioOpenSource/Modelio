/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.diagram.elements.core.model.IGmModelRelated;
import org.modelio.diagram.elements.core.model.IGmObject;

/**
 * Layer containing drawings.
 */
@objid ("85215e54-fa4b-42c0-9062-cc2561ce677f")
public interface IGmDrawingLayer extends IGmDrawingLinkable, IGmModelRelated {
    /**
     * Add a child to the children list at the given index and fires a {@link IGmObject#PROPERTY_CHILDREN} property change event.
     * 
     * @param child The node to add
     * @param index the index where the child will be added.
     */
    @objid ("bbf8b8af-eee1-4648-80b1-5fe0f1e58068")
    void addChild(IGmNodeDrawing child, int index);

    /**
     * Add a child to the children list and fires a {@link IGmObject#PROPERTY_CHILDREN} property change event.
     * 
     * @param child The node to add
     */
    @objid ("8b04a00c-552a-45a3-a1dc-1c90f9c06f35")
    void addChild(IGmNodeDrawing child);

    /**
     * Get the node drawings.
     * 
     * @return the node drawings.
     */
    @objid ("5c33a7d9-3c52-41f2-ad7e-e442e510fc18")
    List<IGmNodeDrawing> getNodes();

    /**
     * Remove a child from the children list and fires a {@link IGmObject#PROPERTY_CHILDREN} property change event.
     * 
     * @param child The node to remove
     */
    @objid ("68d296fc-a6f3-4a18-9ad8-6537e8774cc7")
    void removeChild(IGmNodeDrawing child);

}
