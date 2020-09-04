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

package org.modelio.uml.ui.dg.bpmn;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.editor.bpmn.elements.participant.GmBpmnParticipantPortContainer;
import org.modelio.diagram.editor.bpmn.elements.workflow.GmWorkflow;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'BpmnLane' element.
 */
@objid ("d9af88d1-c5d4-4a6f-aa91-e7e12a39f006")
public class BpmnParticipantDG extends DiagramNode {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("ebe57050-83cd-4e4c-bc0b-345eb118b421")
    public BpmnParticipantDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("cf7f7e26-b60b-40e7-ad9a-dcc6ae33df44")
    @Override
    public List<IDiagramNode> getNodes() {
        GmWorkflow contentsArea = ((GmBpmnParticipantPortContainer) this.gmNode).getWorkflow();
        if (contentsArea != null) {
            return DGFactory.getInstance().getDiagramNodes(this.diagramHandle,contentsArea.getVisibleChildren());
        } else {
            return Collections.emptyList();
        }
    }

    @objid ("3b89f941-5f63-4535-8608-0fb3a569474d")
    @Override
    public Rectangle getBounds() {
        // Do NOT return handle bounds, as those are the bounds of the header!!
        final GraphicalEditPart p = this.diagramHandle.getEditPart(this.gmNode);
        return p.getFigure().getBounds().getCopy();
    }

    @objid ("d4ab64a2-c142-42e5-ad23-4ec46dd8db2c")
    @Override
    public Collection<IDiagramNode> getNodes(Role role) {
        if (role == Role.INNER) {
            return getNodes();
        } else {
            return Collections.emptyList();
        }
    }

}
