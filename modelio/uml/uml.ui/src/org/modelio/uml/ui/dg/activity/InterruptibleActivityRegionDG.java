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

package org.modelio.uml.ui.dg.activity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.api.dg.common.LeafNodeDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'InterruptibleActivityRegion' element.
 */
@objid ("13e7993b-1432-4b4f-964a-bc55a723a124")
public class InterruptibleActivityRegionDG extends LeafNodeDG {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("fcaa80ce-c4f6-4b3d-9523-de16bb7e847f")
    public InterruptibleActivityRegionDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

}
