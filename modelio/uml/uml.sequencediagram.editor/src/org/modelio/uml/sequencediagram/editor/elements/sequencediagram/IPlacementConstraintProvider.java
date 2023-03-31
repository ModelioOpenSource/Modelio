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
package org.modelio.uml.sequencediagram.editor.elements.sequencediagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.GmModel;

/**
 * <p>
 * This interface defines the capacity to create a {@link PlacementConstraint} for a given Gm that will be used to place the corresponding figure on the sequence diagram background.
 * </p>
 * <p>
 * This interface is to be implemented by the edit part of all the org.modelio.uml.sequencediagram.editor.elements that are on the diagram background (lifeline, combined fragments, etc).
 * </p>
 * 
 * @author fpoyer
 */
@objid ("d97780a3-55b6-11e2-877f-002564c97630")
public interface IPlacementConstraintProvider {
    /**
     * Creates and returns a PlacementConstraint for the given model.
     * @param model the graphic model for which a constraint is to be created.
     * @param x the desired X coordinate in coordinates relative to the parent figure.
     * @param width the desired width of the figure.
     * @return a new PlacementConstraint.
     */
    @objid ("d979071a-55b6-11e2-877f-002564c97630")
    PlacementConstraint createPlacementConstraint(final GmModel model, final int x, final int y, final int width, final int height);
}

