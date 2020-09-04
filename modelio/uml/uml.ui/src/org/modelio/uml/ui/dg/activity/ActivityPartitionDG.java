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

package org.modelio.uml.ui.dg.activity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.editor.activity.elements.partition.GmPartition;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'ActivityPartition' element.
 */
@objid ("d7303177-51a9-44f3-bc47-28c5fdd5716b")
public class ActivityPartitionDG extends DiagramNode {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("891a6621-b9cd-4609-86c5-4b4e2a179fdd")
    public ActivityPartitionDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("380484dd-4a19-4a5d-b40a-760f3df6e75f")
    @Override
    public List<IDiagramNode> getNodes() {
        // Inner nodes
        GmCompositeNode contentsArea = ((GmPartition) this.gmNode).getContentsArea();
        if (contentsArea != null) {
            return DGFactory.getInstance().getDiagramNodes(this.diagramHandle, contentsArea.getVisibleChildren());
        } else {
            return Collections.emptyList();
        }
    }

    @objid ("55547a04-d932-4eda-8d14-3129c5f2ec47")
    @Override
    public Rectangle getBounds() {
        // Do NOT return handle bounds, as those are the bounds of the header!!
        final GraphicalEditPart p = this.diagramHandle.getEditPart(this.gmNode);
        return p.getFigure().getBounds().getCopy();
    }

    @objid ("a9fd4cd5-e0bc-47a3-aeed-06121dc7a0d3")
    @Override
    public void setBounds(final Rectangle newBounds) {
        if (newBounds.height == -1 || newBounds.width == -1) {
            return;
        }
        
        Rectangle currentBounds = getBounds();
        GraphicalEditPart p = this.diagramHandle.getEditPart(this.gmNode);
        
        ChangeBoundsRequest req = new ChangeBoundsRequest();
        req.setType(RequestConstants.REQ_RESIZE);
        req.setEditParts(p);
        req.setMoveDelta(newBounds.getLocation().getTranslated(currentBounds.getLocation().getNegated()));
        req.setSizeDelta(new Dimension(newBounds.width - currentBounds.width, newBounds.height -
                                                                              currentBounds.height));
        Command com = p.getCommand(req);
        if (com != null && com.canExecute()) {
            p.getViewer().getEditDomain().getCommandStack().execute(com);
        }
        
        req = new ChangeBoundsRequest();
        req.setType(RequestConstants.REQ_MOVE);
        req.setLocation(newBounds.getCenter());
        req.setEditParts(p);
        req.setMoveDelta(newBounds.getLocation().getTranslated(currentBounds.getLocation().getNegated()));
        com = p.getCommand(req);
        if (com != null && com.canExecute()) {
            p.getViewer().getEditDomain().getCommandStack().execute(com);
            p.getFigure().getUpdateManager().performValidation();
        }
    }

    @objid ("9ee32202-a74c-4c6a-93d6-2b247fa61dd3")
    @Override
    public Collection<IDiagramNode> getNodes(Role role) {
        if (role == Role.INNER) {
            return getNodes();
        } else {
            return Collections.emptyList();
        }
    }

}
