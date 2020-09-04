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

package org.modelio.uml.ui.dg.state;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'Region' element.
 */
@objid ("76f02fe1-af63-40a2-97fb-ff8d49171258")
public class RegionDG extends DiagramNode {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("7bab98f9-2a6f-4e65-94de-b88c03f11c70")
    public RegionDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("77ef3ad5-d031-4c3c-84e1-fff898bfbc16")
    @Override
    public List<IDiagramNode> getNodes() {
        // Inner nodes
        return DGFactory.getInstance().getDiagramNodes(this.diagramHandle, ((GmCompositeNode)this.gmNode).getVisibleChildren());
    }

    @objid ("3b3b5423-f6ee-4dfa-9b92-b3b62ce2b3a5")
    @Override
    public Collection<IDiagramNode> getNodes(Role role) {
        if (role == Role.INNER) {
            return getNodes();
        } else {
            return Collections.emptyList();
        }
    }

}
