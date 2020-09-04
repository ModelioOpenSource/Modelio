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

package org.modelio.uml.ui.dg.usecase;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.editor.usecase.elements.system.GmSystemFreeZone;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'System' element.
 */
@objid ("54edc394-d6b6-4320-9fc3-10f15ab124dd")
public class SystemDG extends DiagramNode {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("1b262f64-d813-43cf-bc2e-000502efb2fd")
    public SystemDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("626c7f0f-f779-4ee1-9f61-9e9cc82c2319")
    @Override
    public List<IDiagramNode> getNodes() {
        // Inner nodes
        GmSystemFreeZone bodyNode = (GmSystemFreeZone) ((GmCompositeNode) this.gmNode).getFirstChild("body");
        if (bodyNode != null) {
            return DGFactory.getInstance().getDiagramNodes(this.diagramHandle, bodyNode.getVisibleChildren());
        } else {
            return Collections.emptyList();
        }
    }

    @objid ("780b023e-6778-4c38-99b9-05e7c4a55f4f")
    @Override
    public Collection<IDiagramNode> getNodes(Role role) {
        if (role == Role.INNER) {
            return getNodes();
        } else {
            return Collections.emptyList();
        }
    }

}
