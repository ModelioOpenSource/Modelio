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

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Represents a free drawing node.
 */
@objid ("45716af6-2728-48b1-8083-5ff1025ca5ee")
public interface IGmNodeDrawing extends IGmDrawingLinkable {
    /**
     * Set the parent layer
     * 
     * @param gmDrawingLayer the parent layer
     */
    @objid ("94f65976-fca6-4882-a180-e45a9914c82d")
    void setParent(IGmDrawingLayer gmDrawingLayer);

    /**
     * @return the node label
     */
    @objid ("ac204096-c2ca-43d0-8b2c-2d8f5959b357")
    String getLabel();

    /**
     * Set the node label
     * 
     * @param label the node label
     */
    @objid ("6efda563-7876-4368-af17-76a8791f1d24")
    void setLabel(String label);

}
