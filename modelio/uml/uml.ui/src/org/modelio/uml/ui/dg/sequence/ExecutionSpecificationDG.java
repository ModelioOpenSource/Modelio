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

package org.modelio.uml.ui.dg.sequence;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'ExecutionSpecification' element.
 */
@objid ("ffb081bc-d50f-4b89-a83e-8b714574a083")
public class ExecutionSpecificationDG extends DiagramNode {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("a5fe85bb-9c73-433d-8109-c11c536899d9")
    public ExecutionSpecificationDG(final DiagramHandle diagramHandle, final GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("5de0ea9c-f6fe-40a7-aeba-2c58bc1f33ae")
    @Override
    public List<IDiagramNode> getNodes() {
        return Collections.emptyList();
    }

    @objid ("7d30b553-d71c-4e84-9bb9-23f77d180d29")
    @Override
    public void setBounds(final Rectangle newBounds) {
        // Do nothing
    }

    @objid ("86d1a0b2-9238-4838-8652-7aa7e0e1b5fb")
    @Override
    public Rectangle getBounds() {
        return new Rectangle();
    }

    @objid ("95a39d2d-aea7-4f5f-8e7f-83e40192115b")
    @Override
    public Collection<IDiagramNode> getNodes(Role role) {
        return Collections.emptyList();
    }

}
