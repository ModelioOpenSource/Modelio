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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
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
import org.modelio.diagram.elements.core.link.ortho.AutoOrthogonalRouterSynchronizeConstraintCommand;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * Command that splits a connection in two to insert a node created by another command.
 * 
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
     * @param createNodeCmd the node creation command.
     * @param connectionEditPart the connection to split.
     */
    @objid ("cdca42d8-c1f2-4b86-8b69-537d821f2f85")
    public  SplitConnectionCommand(ICreationCommand<GmNodeModel> createNodeCmd, ConnectionEditPart connectionEditPart) {
        this.connectionEditPart = connectionEditPart;
        this.createNodeCmd = createNodeCmd;
        
    }

    @objid ("6886e5a6-7d38-49dd-aad4-76c82240d229")
    @Override
    public void execute() {
        GmNodeModel newGmNode = this.createNodeCmd.getCreatedGraphicModel();
        IGmLink gmLink = (GmLink) this.connectionEditPart.getModel();
        
        Connection connection = (Connection) this.connectionEditPart.getFigure();
        PointList connPoints = connection.getPoints();
        Point origSource = connPoints.getFirstPoint();
        Point origTarget = connPoints.getLastPoint();
        connection.translateToAbsolute(origSource);
        connection.translateToAbsolute(origTarget);
        
        NodeEditPart srcNode = (NodeEditPart) this.connectionEditPart.getSource();
        NodeEditPart targetNode = (NodeEditPart) this.connectionEditPart.getTarget();
        NodeEditPart newNode = findNode(newGmNode);
        
        // Create flow from newNode to targetNode
        createSecondConnection(gmLink, newNode, targetNode, origTarget);
        
        // Reconnect connectionEditPart from srcNode to newNode
        ReconnectRequest recoReq = new ReconnectRequest(RequestConstants.REQ_RECONNECT_TARGET);
        recoReq.setConnectionEditPart(this.connectionEditPart);
        recoReq.setLocation(origSource);
        
        // Remove bendpoints
        GmPath newPath = new GmPath((GmPath) gmLink.getLayoutData());
        if (newPath.getPathData() instanceof List) {
            newPath.setPathData(new ArrayList<>(0));
        } else if (newPath.getTargetRake() != null) {
            // Quick fix split connection badly works on rake toward target:
            // detach the connection form the rake.
            newPath.setPathData(new ArrayList<>(0));
            newPath.setTargetRake(null);
            newPath.setRouterKind(ConnectionRouterId.ORTHOGONAL);
        }
        gmLink.setLayoutData(newPath);
        connection.getUpdateManager().performValidation();
        
        EditPart newMainNode = findChildEditPartFor(newNode, recoReq);
        recoReq.setTargetEditPart(newMainNode);
        
        Command recoCommand = newMainNode.getCommand(recoReq);
        if (false && newPath.getRouterKind() == ConnectionRouterId.ORTHOGONAL &&
                newPath.getSourceRake() == null && newPath.getTargetRake() == null) {
            // CMA 29/06/2021 seems to be useless now
            recoCommand = recoCommand.chain(new AutoOrthogonalRouterSynchronizeConstraintCommand(this.connectionEditPart));
        }
        
        checkCommand(recoReq, recoCommand);
        recoCommand.execute();
        
    }

    /**
     * Create a connection from newNode to targetNode.
     * @param gmLink the initial connection model
     * @param newNode the new intermediate node
     * @param targetNode the target node
     */
    @objid ("96238a77-d73d-4cf2-b9d2-0a2e6dbc0c73")
    private void createSecondConnection(IGmLink gmLink, NodeEditPart newNode, NodeEditPart targetNode, Point targetLocation) {
        MClass mclass = gmLink.getRelatedMClass();
        
        CreateBendedConnectionRequest creq = new CreateBendedConnectionRequest();
        ModelioLinkCreationContext ctx = new ModelioLinkCreationContext(mclass, null);
        creq.setFactory(ctx);
        
        creq.setType(RequestConstants.REQ_CONNECTION_START);
        
        creq.setSourceEditPart(newNode);
        creq.setTargetEditPart(null);
        creq.setLocation(targetLocation.getCopy());
        creq.getData().setRoutingMode(gmLink.getPath().getRouterKind());
        
        NodeEditPart newMainNode = (NodeEditPart) findChildEditPartFor(newNode, creq);
        creq.setStartCommand(newMainNode.getCommand(creq));
        creq.setSourceEditPart(newMainNode);
        
        checkCommand(creq, creq.getStartCommand());
        
        creq.setType(RequestConstants.REQ_CONNECTION_END);
        creq.setTargetEditPart(targetNode);
        creq.getData().setSrcPoint(targetLocation.getCopy());
        creq.getData().setSrcPoint(newMainNode.getSourceConnectionAnchor(creq).getLocation(targetLocation));
        creq.getData().setLastPoint(targetLocation.getCopy());
        
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

    @objid ("fd6348bb-85e5-4b67-adf5-185302547f6f")
    private NodeEditPart findNode(GmNodeModel mainLinkable) {
        return (NodeEditPart) this.connectionEditPart.getViewer().getEditPartRegistry().get(mainLinkable);
    }

    @objid ("0a81fe3a-a7ad-43ec-a4aa-1fae481d93d8")
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
