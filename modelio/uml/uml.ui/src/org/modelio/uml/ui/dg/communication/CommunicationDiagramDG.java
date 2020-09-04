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

package org.modelio.uml.ui.dg.communication;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.diagram.api.dg.common.DiagramDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * Represents a communication diagram.
 */
@objid ("1665fbc3-48e3-4fee-97c0-0adbe3bf8096")
public class CommunicationDiagramDG extends DiagramDG {
    /**
     * Initialize the activity diagram graphic element.
     * 
     * @param diagramHandle The diagram handle
     * @param node The internal graphic node representing the diagram.
     */
    @objid ("46403e7a-18b0-42c7-9d6a-f3c7b2eedeb1")
    public CommunicationDiagramDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("08ad2e92-2ed8-4e94-9c7e-116814177f16")
    @Override
    public DiagramNode getParent() {
        return null;
    }

    @objid ("ff24a4a4-cd4f-4bb3-b97b-c8e6b89c396b")
    @Override
    public List<IDiagramLink> getFromLinks() {
        return Collections.emptyList();
    }

    @objid ("f057afe3-e517-4679-90bf-87de67c388df")
    @Override
    public List<IDiagramLink> getToLinks() {
        return Collections.emptyList();
    }

}
