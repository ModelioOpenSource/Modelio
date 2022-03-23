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

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.bpmn.diagram.editor.elements.diagrams.processdesign.GmBpmnProcessDesignDiagram;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.dg.common.DiagramDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'BpmnProcessDesignDiagram' element.
 */
@objid ("399dd83a-1952-4fb2-9e84-2f1cb8f1d73b")
public class BpmnProcessDesignDiagramDG extends DiagramDG {
    @objid ("bdb8812a-b509-447e-acc0-00324580d0c4")
    @Override
    public DiagramNode getParent() {
        return null;
    }

    @objid ("3be1d1a8-4f1c-49f0-9b6a-4403cfe7ecb8")
    @Override
    public List<IDiagramLink> getFromLinks() {
        return Collections.emptyList();
    }

    @objid ("c380af16-13ec-438c-a8d4-d9b59cacdfe5")
    @Override
    public List<IDiagramLink> getToLinks() {
        return Collections.emptyList();
    }

    /**
     * Initialize the activity diagram graphic element.
     * @param diagramHandle The diagram handle
     * @param node The internal graphic node representing the diagram.
     */
    @objid ("f032e114-e34e-46dd-a700-e77439bc1e40")
    public  BpmnProcessDesignDiagramDG(final DiagramHandle diagramHandle, final GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("db6443df-04e4-422c-80a3-88df111278cd")
    @Override
    public List<IDiagramNode> getNodes() {
        // Make the GmWorkflow transparent
        return DGFactory.getInstance().getDiagramNodes(this.diagramHandle, ((GmBpmnProcessDesignDiagram) this.gmNode).getWorkflow().getVisibleChildren());
    }

}
