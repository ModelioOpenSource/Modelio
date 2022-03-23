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
package org.modelio.uml.ui.dg.constraint;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.api.dg.common.LeafNodeDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'Constraint' element.
 */
@objid ("e123dab1-2e62-4a8c-8126-f88210089415")
public class ConstraintDG extends LeafNodeDG {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("1d3a4006-3723-49e1-bc48-1ba5169a2e5a")
    public  ConstraintDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

}
