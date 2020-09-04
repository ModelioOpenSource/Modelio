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
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.GmBpmnLane;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'BpmnLane' element.
 */
@objid ("f184c793-eb79-483c-89e2-748224adeee0")
public class BpmnLaneDG extends DiagramNode {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("7b43bc9f-61eb-451e-939e-c7e6a34b49fd")
    public BpmnLaneDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("4cf92da8-6820-4a0c-b550-816f9e34e772")
    @Override
    public List<IDiagramNode> getNodes() {
        GmCompositeNode contentsArea = ((GmBpmnLane) this.gmNode).getContentsArea();
        if (contentsArea != null) {
            return DGFactory.getInstance().getDiagramNodes(this.diagramHandle,contentsArea.getVisibleChildren());
        } else {
            return Collections.emptyList();
        }
    }

    @objid ("d5e68a6d-ff11-49b8-8493-64f4504e9b52")
    @Override
    public Rectangle getBounds() {
        // Do NOT return handle bounds, as those are the bounds of the header!!
        final GraphicalEditPart p = this.diagramHandle.getEditPart(this.gmNode);
        return p.getFigure().getBounds().getCopy();
    }

    @objid ("85ed41cc-cb32-43a1-9a71-06a888dfd88c")
    @Override
    public Collection<IDiagramNode> getNodes(Role role) {
        if (role == Role.INNER) {
            return getNodes();
        } else {
            return Collections.emptyList();
        }
    }

}
