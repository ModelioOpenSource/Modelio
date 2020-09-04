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

package org.modelio.diagram.api.dg;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.services.DiagramLink;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * Diagram graphic factory.
 */
@objid ("52ab3df0-fa7a-486e-b03d-735444f72391")
public interface IDGFactory {
    /**
     * Returns a DiagramLink from the given model.
     * @param diagramHandle a handle to the diagram.
     * @param gmLink the model.
     * @return a {@link DiagramLink}
     */
    @objid ("a813c4a7-30df-482e-aa66-5f631faea68e")
    IDiagramLink getDiagramLink(IDiagramHandle diagramHandle, IGmLink gmLink);

    /**
     * Returns a Diagram Node for the given model.
     * @param diagramHandle the diagram in which the model is shown.
     * @param gmNodeModel the model.
     * @return a {@link DiagramNode}
     */
    @objid ("b3ad134e-01f2-4146-be90-856e066bc445")
    IDiagramNode getDiagramNode(IDiagramHandle diagramHandle, GmNodeModel gmNodeModel);

}
