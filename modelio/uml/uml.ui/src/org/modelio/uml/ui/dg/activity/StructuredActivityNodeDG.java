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

package org.modelio.uml.ui.dg.activity;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.dg.common.PortContainerDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.editor.activity.elements.structuredactivity.GmStructuredActivityPrimaryNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'StructuredActivityNode' element.
 */
@objid ("75784548-8827-438e-ace1-eb0d254cab27")
public class StructuredActivityNodeDG extends PortContainerDG {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("fdf0c423-a2a2-4bb2-8471-42edef3490e6")
    public StructuredActivityNodeDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("e7798424-9e6f-4a30-abdd-27c780b8082f")
    @Override
    protected List<IDiagramNode> getPrimaryChildrenNodes() {
        // Inner nodes
        GmStructuredActivityPrimaryNode mainNode = (GmStructuredActivityPrimaryNode) getPrimaryNode();
        return DGFactory.getInstance().getDiagramNodes(this.diagramHandle, mainNode.getInnerZone().getVisibleChildren());
    }

}
