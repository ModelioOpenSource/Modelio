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
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.editor.sequence.elements.lifeline.body.GmLifelineBody;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'Gate' element.
 */
@objid ("2d29921e-6259-43a7-9acd-cb1e23a87961")
public class LifelineDG extends DiagramNode {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("a12281df-cab9-4bb5-918b-8ef9f87e2e20")
    public LifelineDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("bb6c1d92-f8b9-43ec-952c-85defc4b2b7c")
    @Override
    public List<IDiagramNode> getNodes() {
        // Inner nodes
        GmLifelineBody bodyNode = (GmLifelineBody) ((GmCompositeNode) this.gmNode).getFirstChild("body");
        if (bodyNode != null) {
            return DGFactory.getInstance().getDiagramNodes(this.diagramHandle, bodyNode.getVisibleChildren());
        } else {
            return Collections.emptyList();
        }
    }

    @objid ("95fadb4c-1cdc-45dc-9b55-62d5487a7821")
    @Override
    public Collection<IDiagramNode> getNodes(Role role) {
        if (role == Role.INNER) {
            return getNodes();
        } else {
            return Collections.emptyList();
        }
    }

}
