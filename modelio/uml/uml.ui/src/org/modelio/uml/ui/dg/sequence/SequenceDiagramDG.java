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

package org.modelio.uml.ui.dg.sequence;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.diagram.api.dg.common.DiagramDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * Represents a sequence diagram.
 */
@objid ("afb78948-efec-4638-b3e7-8166813038d0")
public class SequenceDiagramDG extends DiagramDG {
    /**
     * Initialize the activity diagram graphic element.
     * 
     * @param diagramHandle The diagram handle
     * @param node The internal graphic node representing the diagram.
     */
    @objid ("cd6d9451-bc91-4d3c-9fc3-5b796fc483f9")
    public SequenceDiagramDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("7d5f49b9-5e3a-40b4-8dbd-151c9e88f622")
    @Override
    public DiagramNode getParent() {
        return null;
    }

    @objid ("91768340-1dbf-4b32-b863-9fa6cb748c19")
    @Override
    public List<IDiagramLink> getFromLinks() {
        return Collections.emptyList();
    }

    @objid ("0066f35e-f9ad-4093-883c-948a50ac72ef")
    @Override
    public List<IDiagramLink> getToLinks() {
        return Collections.emptyList();
    }

}
