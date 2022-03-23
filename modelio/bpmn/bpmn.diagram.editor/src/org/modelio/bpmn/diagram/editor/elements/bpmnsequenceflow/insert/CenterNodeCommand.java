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
package org.modelio.bpmn.diagram.editor.elements.bpmnsequenceflow.insert;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.commands.ICreationCommand;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * Command that center a node on its top left corner.
 */
@objid ("e4d687b0-b1d0-47f7-9b2e-841e47a88ddb")
class CenterNodeCommand extends Command {
    @objid ("231d9e33-11a5-4864-802c-eca0553804a5")
    private final ICreationCommand<GmNodeModel> createNodeCmd;

    @objid ("e45a5a37-8071-4205-ab27-cfba08c58004")
    private final ConnectionEditPart connectionEditPart;

    @objid ("97bf1b1f-049f-4452-a52f-ef4927cb11d5")
    public  CenterNodeCommand(ICreationCommand<GmNodeModel> createNodeCmd, ConnectionEditPart connectionEditPart) {
        this.connectionEditPart = connectionEditPart;
        this.createNodeCmd = createNodeCmd;
        
    }

    @objid ("6327dfd5-ff99-4904-acc7-39d21cfb69e5")
    @Override
    public void execute() {
        GmNodeModel newGmNode = this.createNodeCmd.getCreatedGraphicModel();
        NodeEditPart newNodeEditPart = findNode(newGmNode);
        
        IFigure primaryFigure = newNodeEditPart.getFigure();
        Rectangle primaryBounds = getEffectiveBounds(primaryFigure);
        primaryFigure.translateToAbsolute(primaryBounds);
        
        Point moveDelta = new Point(primaryBounds.width / 2, primaryBounds.height / 2).getNegated();
        ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        req.setEditParts(newNodeEditPart);
        req.setMoveDelta(moveDelta);
        req.setSizeDelta(new Dimension(0, 0));
        
        Command command = newNodeEditPart.getCommand(req);
        
        if (command.canExecute()) {
            newNodeEditPart.getViewer().getEditDomain().getCommandStack().execute(command);
            primaryFigure.getUpdateManager().performValidation();
        }
        
    }

    /**
     * This method returns the effective bounds (those seen by the end user) of a figure
     * @param figure the figure which bounds are to be returned.
     * @return a copy of the effective bounds of the figure
     */
    @objid ("46e72eb9-5a04-4227-8adb-a1f31bac44d7")
    protected Rectangle getEffectiveBounds(IFigure figure) {
        return figure instanceof HandleBounds ? ((HandleBounds) figure).getHandleBounds().getCopy() : figure.getBounds().getCopy();
    }

    @objid ("8a788296-f160-4126-a00b-5fefcf9646f4")
    private NodeEditPart findNode(GmNodeModel mainLinkable) {
        return (NodeEditPart) this.connectionEditPart.getViewer().getEditPartRegistry().get(mainLinkable);
    }

    @objid ("4c55cb52-679c-49d4-bcf1-8cfa09f47ac0")
    private static EditPart findChildEditPartFor(EditPart from, Request req) {
        for (EditPart e : (List<EditPart>) from.getChildren()) {
            EditPart targetEditPart = e.getTargetEditPart(req);
            if (targetEditPart != null) {
                return targetEditPart;
            }
        }
        
        throw new IllegalArgumentException(String.format("No child edit part in '%s' that supports %s", from, RequestHelper.toString(req)));
        
    }

}
