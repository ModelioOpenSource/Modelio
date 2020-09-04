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

package org.modelio.uml.ui.dg.object;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.diagram.api.dg.common.DiagramDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * Represents an object diagram.
 */
@objid ("4506055e-a4a9-4dc9-a924-4c4ec052578c")
public class ObjectDiagramDG extends DiagramDG {
    /**
     * Initialize the activity diagram graphic element.
     * 
     * @param diagramHandle The diagram handle
     * @param node The internal graphic node representing the diagram.
     */
    @objid ("7f1270e8-518f-4f4c-9920-dfb9c44a8308")
    public ObjectDiagramDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("d8f466f7-5688-49df-96d6-bfc0f8e232a9")
    @Override
    public DiagramNode getParent() {
        return null;
    }

    @objid ("20897f0d-9af6-48df-84c8-90404a1ed0a5")
    @Override
    public List<IDiagramLink> getFromLinks() {
        return Collections.emptyList();
    }

    @objid ("88cb89df-587e-49c1-b955-7016001cf979")
    @Override
    public List<IDiagramLink> getToLinks() {
        return Collections.emptyList();
    }

}
