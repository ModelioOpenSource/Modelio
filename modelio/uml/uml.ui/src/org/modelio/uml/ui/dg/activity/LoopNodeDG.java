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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.dg.common.PortContainerDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.uml.activitydiagram.editor.elements.loopnode.GmLoopNodePrimaryNode;

/**
 * This class represents the DiagramGraphic of a 'LoopNode' element.
 */
@objid ("508dc934-01c2-45c7-917f-cab8d34af0cc")
public class LoopNodeDG extends PortContainerDG {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("de35766e-c581-4202-9a40-913e8ceec7c7")
    public LoopNodeDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("9644c2f7-588c-42c2-a567-8cde0b287591")
    @Override
    protected List<IDiagramNode> getPrimaryChildrenNodes() {
        // Inner nodes
        GmLoopNodePrimaryNode mainNode = (GmLoopNodePrimaryNode) getPrimaryNode();
        return DGFactory.getInstance().getDiagramNodes(this.diagramHandle, mainNode.getInnerZone().getVisibleChildren());
    }

}
