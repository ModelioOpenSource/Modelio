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
package org.modelio.uml.ui.dg.statik;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.diagram.api.dg.common.DiagramDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * Represents a statik diagram.
 */
@objid ("68d17266-1e78-47b8-8862-cf457eb43f62")
public class StaticDiagramDG extends DiagramDG {
    /**
     * Initialize the activity diagram graphic element.
     * @param diagramHandle The diagram handle
     * @param node The internal graphic node representing the diagram.
     */
    @objid ("1f43aaed-4a4a-404b-b876-6f6426d7ec82")
    public  StaticDiagramDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("950869a2-b563-49eb-9dea-c80d0e202670")
    @Override
    public DiagramNode getParent() {
        return null;
    }

    @objid ("be7b94a6-26f6-46e2-a408-42b60359c873")
    @Override
    public List<IDiagramLink> getFromLinks() {
        return Collections.emptyList();
    }

    @objid ("b6d685f3-8113-4d58-85d4-7f3f0f552c6e")
    @Override
    public List<IDiagramLink> getToLinks() {
        return Collections.emptyList();
    }

}
