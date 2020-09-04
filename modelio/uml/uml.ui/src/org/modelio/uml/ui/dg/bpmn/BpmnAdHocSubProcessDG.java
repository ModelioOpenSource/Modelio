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
import org.modelio.diagram.editor.bpmn.elements.bpmnadhocsubprocess.GmBpmnAdHocSubProcessPrimaryNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'BpmnAdHocSubProcess' element.
 */
@objid ("1a986952-efc6-429a-bda3-6c9bee99e68d")
public class BpmnAdHocSubProcessDG extends PortContainerDG {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("3362837d-1b48-48e1-a674-2558547b80d3")
    public BpmnAdHocSubProcessDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("08fca7aa-3a0e-4d4a-9556-0cf8037986c4")
    @Override
    protected List<IDiagramNode> getPrimaryChildrenNodes() {
        GmBpmnAdHocSubProcessPrimaryNode mainNode = (GmBpmnAdHocSubProcessPrimaryNode) getPrimaryNode();
        return DGFactory.getInstance().getDiagramNodes(this.diagramHandle, mainNode.getInnerZone().getVisibleChildren());
    }

}
