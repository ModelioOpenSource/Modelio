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

package org.modelio.diagram.api.dg.common;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * implementation for DG that have no child node.
 * 
 * @author cmarin
 * @since 3.5
 */
@objid ("143bee6b-7476-42c3-b64a-132446165033")
public class LeafNodeDG extends DiagramNode {
    @objid ("14e005fc-d1b5-4b6c-bcf6-d51af93f2456")
    public LeafNodeDG(DiagramHandle diagramHandle, GmNodeModel gmNode) {
        super(diagramHandle, gmNode);
    }

    @objid ("9ab765a8-184c-449f-87ed-42728c8377a2")
    @Override
    public final Collection<IDiagramNode> getNodes(Role role) {
        return Collections.emptyList();
    }

    @objid ("9731fda7-0a8f-4ddc-8bc3-7e9b2c9cc8fe")
    @Override
    public final List<IDiagramNode> getNodes() {
        return Collections.emptyList();
    }

}
