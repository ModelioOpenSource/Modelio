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
import org.modelio.diagram.api.dg.common.DiagramDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'BpmnProcessCollaborationDiagram' element.
 */
@objid ("1081cf60-9b95-4a78-be0d-5d21d58fb931")
public class BpmnProcessCollaborationDiagramDG extends DiagramDG {
    @objid ("0727b615-6f32-4701-a793-ba52e79f0f29")
    @Override
    public DiagramNode getParent() {
        return null;
    }

    @objid ("011271c4-7378-44a9-badb-e8a3b8a855b7")
    @Override
    public List<IDiagramLink> getFromLinks() {
        return Collections.emptyList();
    }

    @objid ("12ef0f27-a68a-4ab2-964f-300a8cf02c49")
    @Override
    public List<IDiagramLink> getToLinks() {
        return Collections.emptyList();
    }

    /**
     * Initialize the activity diagram graphic element.
     * @param diagramHandle The diagram handle
     * @param node The internal graphic node representing the diagram.
     */
    @objid ("d40adb58-5a73-4d91-b6d3-b8a815052e2f")
    public  BpmnProcessCollaborationDiagramDG(final DiagramHandle diagramHandle, final GmNodeModel node) {
        super(diagramHandle, node);
    }

}
