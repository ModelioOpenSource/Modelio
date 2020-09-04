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

package org.modelio.uml.ui.dg.bpmn;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.dg.common.PortContainerDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.editor.bpmn.elements.bpmntransaction.GmBpmnTransactionPrimaryNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'BpmnTransaction' element.
 */
@objid ("4d62a7ac-e830-433a-b054-2c27f5853685")
public class BpmnTransactionDG extends PortContainerDG {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("a2d1854f-e1b7-48e8-91b6-49e078c7f134")
    public BpmnTransactionDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("dde023db-0397-439d-8775-9ec9743ae9de")
    @Override
    protected List<IDiagramNode> getPrimaryChildrenNodes() {
        GmBpmnTransactionPrimaryNode mainNode = (GmBpmnTransactionPrimaryNode) getPrimaryNode();
        return DGFactory.getInstance().getDiagramNodes(this.diagramHandle, mainNode.getInnerZone().getVisibleChildren());
    }

}
