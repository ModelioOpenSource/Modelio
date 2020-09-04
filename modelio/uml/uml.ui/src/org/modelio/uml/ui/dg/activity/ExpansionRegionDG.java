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

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.dg.common.PortContainerDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.editor.activity.elements.expansionregion.GmExpansionRegionPrimaryNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'ExpansionRegion' element.
 */
@objid ("30e6f2b8-db09-4458-844e-80b193c02d91")
public class ExpansionRegionDG extends PortContainerDG {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("de3ca55b-7d08-4007-b7ca-cc28b75a6c1b")
    public ExpansionRegionDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("5565b9a2-2723-48af-800f-c4f60149c71b")
    @Override
    protected List<IDiagramNode> getPrimaryChildrenNodes() {
        // Inner nodes
        GmExpansionRegionPrimaryNode mainNode = (GmExpansionRegionPrimaryNode) getPrimaryNode();
        if (mainNode.getInnerZone() != null) {
            return DGFactory.getInstance().getDiagramNodes(this.diagramHandle, mainNode.getInnerZone().getVisibleChildren());
        } else
            return Collections.emptyList();
    }

}
