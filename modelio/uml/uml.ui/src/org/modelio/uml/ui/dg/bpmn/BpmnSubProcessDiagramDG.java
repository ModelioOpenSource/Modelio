/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.dg.common.DiagramDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.editor.bpmn.elements.diagrams.subprocess.GmBpmnSubProcessDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'BpmnSubProcessDiagram' element.
 */
@objid ("f252de96-a4c7-4cbd-9603-ddf30bc86ceb")
public class BpmnSubProcessDiagramDG extends DiagramDG {
    @objid ("6d10f2c7-643b-4284-bd70-8296480bffc6")
    @Override
    public DiagramNode getParent() {
        return null;
    }

    @objid ("3c51f83b-d8a2-4d75-8d1d-2fc5910e2dbc")
    @Override
    public List<IDiagramLink> getFromLinks() {
        return Collections.emptyList();
    }

    @objid ("feeff0d1-1d69-4f68-8bc5-54888f587d8a")
    @Override
    public List<IDiagramLink> getToLinks() {
        return Collections.emptyList();
    }

    /**
     * Initialize the activity diagram graphic element.
     * @param diagramHandle The diagram handle
     * @param node The internal graphic node representing the diagram.
     */
    @objid ("8a8aba4f-5a2b-4697-acfb-767473a77474")
    public BpmnSubProcessDiagramDG(final DiagramHandle diagramHandle, final GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("66f91263-a108-4200-a9c1-a226515f3dec")
    @Override
    public List<IDiagramNode> getNodes() {
        // Make the GmWorkflow transparent
        return DGFactory.getInstance().getDiagramNodes(this.diagramHandle, ((GmBpmnSubProcessDiagram) this.gmNode).getWorkflow().getVisibleChildren());
    }

}
