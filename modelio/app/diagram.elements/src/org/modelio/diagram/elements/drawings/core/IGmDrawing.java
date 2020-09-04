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

package org.modelio.diagram.elements.drawings.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic model representing a node or a link that does not represent a model element.
 * <p>
 * A drawing is stored inside a layer.
 */
@objid ("ec7223bd-a294-4aea-8e18-57babffe51ae")
public interface IGmDrawing extends IGmObject {
    /**
     * Get the drawing layer.
     * @return the drawing layer.
     */
    @objid ("337fce70-ba74-4f24-9a68-d74c457f484f")
    IGmDrawingLayer getLayer();

    /**
     * Get the strings that uniquelly identifies this drawing in the diagram.
     * @return the drawing identifier.
     */
    @objid ("e5c2bb22-0d1d-4d5b-826d-b6750e48ea03")
    String getIdentifier();

    /**
     * @return an hyperlink to a model element
     */
    @objid ("6c50a765-f8c4-48d6-a7ed-af208c136d40")
    MRef getHyperLink();

    /**
     * Set the drawing as an hyperlink to a model element.
     * @param ref the linked element or null.
     */
    @objid ("3a9d17a1-4b2f-413c-8b2b-769c8e228aa4")
    void setHyperLink(MRef ref);

}
