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

import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnlanesetcontainer.GmBpmnLaneSetContainer;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'BpmnLaneSetContainer' element.
 */
@objid ("d25c3953-0b1e-4c28-aee7-1fd543317583")
public class BpmnLaneSetContainerDG extends DiagramNode {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("50e1b068-c136-4b9b-9a20-5ec9d725a643")
    public BpmnLaneSetContainerDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("a1eef93c-cceb-4c2b-a9f8-9561f257130d")
    @Override
    public List<IDiagramNode> getNodes() {
        return DGFactory.getInstance().getDiagramNodes(this.diagramHandle, ((GmBpmnLaneSetContainer) this.gmNode).getVisibleChildren());
    }

    @objid ("3144f144-033b-4de3-b690-ca2d906f3bf8")
    @Override
    public Collection<IDiagramNode> getNodes(Role role) {
        if (role == Role.INNER) {
            return getNodes();
        } else {
            return null;
        }
    }

}
