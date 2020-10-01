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
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.commands.ICreationCommand;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * Command that splits a connection in two to insert a node created by another command.
 * @author cma from phv
 * @since 3.7
 */
@objid ("247f3495-896d-400d-ac03-1ca71c48d90e")
class SplitConnectionCommand extends Command {
    @objid ("1276ef20-ac7d-4bbd-b590-50e5e929c7e6")
    private final ICreationCommand<GmNodeModel> createNodeCmd;

    @objid ("777ac442-158a-413b-96a7-fcfb8cbf54e2")
    private final ConnectionEditPart connectionEditPart;

    /**
     * Creates the that splits a connection in two to insert a node created by another command.
     * 
     * @param createNodeCmd the node creation command.
     * @param connectionEditPart the connection to split.
     */
    @objid ("cdca42d8-c1f2-4b86-8b69-537d821f2f85")
    public SplitConnectionCommand(ICreationCommand<GmNodeModel> createNodeCmd, ConnectionEditPart connectionEditPart) {
        this.connectionEditPart = connectionEditPart;
        this.createNodeCmd = createNodeCmd;
    }

    @objid ("6886e5a6-7d38-49dd-aad4-76c82240d229")
    @Override
    public void execute() {
        GmNodeModel newGmNode = this.createNodeCmd.getCreatedGraphicModel();
        GmLink gmLink = (GmLink) this.connectionEditPart.getModel();
        
        // NodeEditPart srcNode = (NodeEditPart) this.connectionEditPart.getSource();
        NodeEditPart targetNode = (NodeEditPart) this.connectionEditPart.getTarget();
        NodeEditPart newNode = findNode(newGmNode);
        
        // Create flow from newNode to targetNode
        createSecondConnection(gmLink, newNode, targetNode);
        
        // Reconnect connectionEditPart from srcNode to newNode
        ReconnectRequest recoReq = new ReconnectRequest(RequestConstants.REQ_RECONNECT_TARGET);
        recoReq.setConnectionEditPart(this.connectionEditPart);
        recoReq.setLocation(newNode.getFigure().getBounds().getCenter());
        newNode.getFigure().translateToAbsolute(recoReq.getLocation());
        
        EditPart newMainNode = findChildEditPartFor(newNode, recoReq);
        recoReq.setTargetEditPart(newMainNode);
        
        Command recoCommand = newMainNode.getCommand(recoReq);
        checkCommand(recoReq, recoCommand);
        recoCommand.execute();
        
        // Remove bendpoints
        GmPath newPath = (GmPath) gmLink.getLayoutData();
        ((List<?>) newPath.getPathData()).clear();
        gmLink.setLayoutData(new GmPath(newPath));
    }

    @objid ("96238a77-d73d-4cf2-b9d2-0a2e6dbc0c73")
    private void createSecondConnection(GmLink gmLink, NodeEditPart newNode, NodeEditPart targetNode) {
        MClass mclass = gmLink.getRelatedMClass();
        
        CreateBendedConnectionRequest creq = new CreateBendedConnectionRequest();
        ModelioLinkCreationContext ctx = new ModelioLinkCreationContext(mclass, null);
        creq.setFactory(ctx);
        
        creq.setType(RequestConstants.REQ_CONNECTION_START);
        
        creq.setSourceEditPart(newNode);
        creq.setTargetEditPart(null);
        creq.setLocation(newNode.getFigure().getBounds().getCenter());
        newNode.getFigure().translateToAbsolute(creq.getLocation());
        creq.getData().setRoutingMode(gmLink.getPath().getRouterKind());
        creq.getData().setSrcPoint(new Point(creq.getLocation()));
        
        EditPart newMainNode = findChildEditPartFor(newNode, creq);
        creq.setStartCommand(newMainNode.getCommand(creq));
        creq.setSourceEditPart(newMainNode);
        
        checkCommand(creq, creq.getStartCommand());
        
        creq.setType(RequestConstants.REQ_CONNECTION_END);
        creq.setTargetEditPart(targetNode);
        creq.setLocation(targetNode.getTargetConnectionAnchor(this.connectionEditPart).getReferencePoint());
        creq.getData().setLastPoint(new Point(creq.getLocation()));
        
        Command finishCmd = targetNode.getCommand(creq);
        
        checkCommand(creq, finishCmd);
        
        finishCmd.execute();
        
        @SuppressWarnings ("unchecked")
        IGmLink newGmLink = ((ICreationCommand<IGmLink>) finishCmd).getCreatedGraphicModel();
        
        copyStyleKeys(gmLink, newGmLink);
        
        // copy stereotypes
        ((ModelElement) newGmLink.getRelatedElement()).getExtension().addAll(((ModelElement) gmLink.getRelatedElement()).getExtension());
    }

    @objid ("bc4987a2-f2f3-48a1-a9e7-da67dc3c115f")
    private void checkCommand(Request recoReq, Command command) {
        if (command == null || !command.canExecute()) {
            String msg = String.format("%s is not excutable for %s", command, RequestHelper.toString(recoReq));
            throw new IllegalStateException(msg);
        }
    }

    @objid ("d80beaaf-5d0c-497f-90af-890ef506ed2e")
    private void copyStyleKeys(IGmObject from, IGmObject to) {
        // Keep local style changes, converting style keys
        IStyle newStyle = to.getPersistedStyle();
        IStyle fromStyle = from.getPersistedStyle();
        
        for (StyleKey oldKey : fromStyle.getLocalKeys()) {
            Object oldValue = fromStyle.getProperty(oldKey);
            newStyle.setProperty(oldKey, oldValue);
        }
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
