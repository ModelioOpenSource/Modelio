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
package org.modelio.uml.ui.dg.sequence;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.api.dg.common.LeafNodeDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'StateInvariant' element.
 */
@objid ("d9f4b9df-df3f-4172-883b-2f0881dbdaee")
public class StateInvariantDG extends LeafNodeDG {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("25772298-0b1c-4dbc-8c5e-b1fb36d701b6")
    public  StateInvariantDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("2dca3660-8282-46d8-b56e-b180f39cca6b")
    @Override
    public void setBounds(final Rectangle newBounds) {
        // Do nothing
    }

    @objid ("8bdf6927-c8ee-47d0-adcf-63c17e38163e")
    @Override
    public Rectangle getBounds() {
        return new Rectangle();
    }

}
