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
package org.modelio.diagram.elements.core.link.rake;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.figures.routers.RakeConstraint;
import org.modelio.diagram.elements.core.figures.routers.RakeRouter;
import org.modelio.diagram.elements.core.link.GmLinkRake;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.IAnchorModelProvider;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Edit policy to put on links to allow making rake links.
 * <p>
 * 
 * @author cmarin
 */
@objid ("8059f6dc-1dec-11e2-8cad-001ec947c8cc")
public class CreateRakeLinkEditPolicy extends GraphicalNodeEditPolicy {
    /**
     * Flag to disable temporarily rake policies.
     */
    @objid ("8059f6e0-1dec-11e2-8cad-001ec947c8cc")
    private static boolean activated = true;

    @objid ("805c5977-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (RequestConstants.REQ_CONNECTION_START.equals(request.getType()) || RequestConstants.REQ_CONNECTION_END.equals(request.getType())) {
            return getHost();
        }
        if (RequestConstants.REQ_RECONNECT_SOURCE.equals(request.getType()) || RequestConstants.REQ_RECONNECT_TARGET.equals(request.getType())) {
            ConnectionEditPart reconnectedConnectionEP = ((ReconnectRequest) request).getConnectionEditPart();
            if (isLinkCycle(getHost(), reconnectedConnectionEP) || !isHandled(request)) {
                return null;
            } else {
                return getHost();
            }
        }
        return null;
    }

    @objid ("58335438-98d2-45c9-89fe-c8e280994188")
    @Override
    public void showSourceFeedback(Request request) {
        // do nothing
        // note : please test "if (isHandled(request))" if you need to do something
        
    }

    @objid ("805c593f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void showTargetFeedback(final Request request) {
        // do nothing
        // note : please test "if (isHandled(request))" if you need to do something
        
    }

    /**
     * Create a serializable path model from the given connection creation request.
     * @param req a connection creation request.
     * @param gmTargetLink the link to rake to.
     * @return A serializable path model.
     */
    @objid ("805c5957-1dec-11e2-8cad-001ec947c8cc")
    protected IGmPath createPathModel(final CreateConnectionRequest req, final IGmLink gmTargetLink) {
        GmPath path = new GmPath();
        
        path.setRouterKind(ConnectionRouterId.ORTHOGONAL);
        
        // Hack for RectangleNodeAnchorProvider.getSourceConnectionAnchor(CreateConnectionRequest)
        // that need to know the rake mode
        req.getExtendedData().put("rake", Boolean.TRUE);
        
        // Getting a hold on the model of source anchors
        
        NodeEditPart sourceEditPart = (NodeEditPart) req.getSourceEditPart();
        ConnectionAnchor srcAnchor = sourceEditPart.getSourceConnectionAnchor(req);
        path.setSourceAnchor(getAnchorModel(sourceEditPart, srcAnchor));
        
        IGmPath targetLinkPath = gmTargetLink.getPath();
        if (targetLinkPath.getTargetRake() == null) {
            // Target is not in rake mode, build a rake here
        
            // Target anchor
            NodeEditPart targetPart = (NodeEditPart) ((ConnectionEditPart) getHost()).getTarget();
            ConnectionAnchor targetAnchor = targetPart.getTargetConnectionAnchor(req);
            path.setTargetAnchor(getAnchorModel(targetPart, targetAnchor));
        
            // Build rake
            GmLinkRake rake = new GmLinkRake();
            rake.setSharedAnchor(path.getTargetAnchor());
            path.setTargetRake(rake);
        
            // Compute rake anchor
            NodeEditPart thisLinkPart = (NodeEditPart) getHost();
            Point p = thisLinkPart.getTargetConnectionAnchor(req).getReferencePoint();
            thisLinkPart.getFigure().translateToRelative(p);
        
            // Build rake constraint with computed rake anchor
            RakeConstraint pathData = new RakeConstraint();
            pathData.setTargetRakeAnchor(new XYAnchor(p));
            path.setPathData(pathData);
        } else {
            // Target link is in rake mode, connect the new link to the existing rake
            path.setTargetAnchor(targetLinkPath.getTargetAnchor());
            path.setTargetRake(targetLinkPath.getTargetRake());
            path.setPathData(targetLinkPath.getPathData());
        
        }
        return path;
    }

    @objid ("8059f6e2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        if (allowRakeCreation(request)) {
            IGmLink gm = (IGmLink) getHost().getModel();
            CreateRakedLinkCommand cmd = new CreateRakedLinkCommand((ModelioLinkCreationContext) request.getNewObject(), gm);
        
            cmd.setSource((IGmLinkable) request.getSourceEditPart().getModel());
            cmd.setTarget(gm.getTo());
            cmd.setPath(createPathModel(request, gm));
        
            return cmd;
        }
        return null;
    }

    @objid ("8059f6ec-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        return null;
    }

    @objid ("8059f6f6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getReconnectSourceCommand(ReconnectRequest request) {
        if (!isHandled(request)) {
            return null;
        }
        
        ConnectionEditPart toReconnect = request.getConnectionEditPart();
        Point loc = request.getLocation().getCopy();
        toReconnect.getFigure().translateToRelative(loc);
        
        NodeEditPart newSourceEditPart = (NodeEditPart) ((ConnectionEditPart) getHost()).getSource();
        ConnectionAnchor anchor = getFinalSourceAnchor(newSourceEditPart, request);
        Object gmAnchor = ((IAnchorModelProvider) newSourceEditPart).createAnchorModel(anchor);
        return new RakeLinkOnSourceCommand((IGmLink) toReconnect.getModel(), (IGmLink) getHost().getModel(), loc, gmAnchor);
    }

    @objid ("8059f700-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getReconnectTargetCommand(ReconnectRequest request) {
        if (!isHandled(request)) {
            return null;
        }
        
        final ConnectionEditPart toReconnect = request.getConnectionEditPart();
        Point loc = request.getLocation().getCopy();
        toReconnect.getFigure().translateToRelative(loc);
        
        NodeEditPart newTargetEditPart = (NodeEditPart) ((ConnectionEditPart) getHost()).getTarget();
        ConnectionAnchor anchor = getFinalTargetAnchor(newTargetEditPart, request);
        Object gmAnchor = ((IAnchorModelProvider) newTargetEditPart).createAnchorModel(anchor);
        return new RakeLinkOnTargetCommand((IGmLink) toReconnect.getModel(), (IGmLink) getHost().getModel(), loc, gmAnchor);
    }

    @objid ("805c5946-1dec-11e2-8cad-001ec947c8cc")
    protected boolean isHandled(final Request request) {
        final Object model = getHost().getModel();
        if (request instanceof ReconnectRequest) {
            final ReconnectRequest reconnectRequest = (ReconnectRequest) request;
            if (reconnectRequest.getConnectionEditPart() instanceof LinkEditPart) {
                LinkEditPart toReconnect = (LinkEditPart) reconnectRequest.getConnectionEditPart();
                if (toReconnect.getModel().getClass() == model.getClass()) {
                    return true;
                }
            }
        
        } else if (request instanceof CreateConnectionRequest && model instanceof IGmLink) {
            // Test the metaclass
            MClass toCreate = getMetaclass((CreateConnectionRequest) request);
            MObject repEl = ((IGmLink) model).getRelatedElement();
        
            return (toCreate != null
                    && repEl != null
                    && toCreate == repEl.getMClass()) ;
        }
        return false;
    }

    @objid ("805c596f-1dec-11e2-8cad-001ec947c8cc")
    private boolean allowRakeCreation(final CreateConnectionRequest request) {
        // Deny if the policy is disabled
        if (!CreateRakeLinkEditPolicy.activated) {
            return false;
        }
        
        if (!isHandled(request)) {
            return false;
        }
        
        // Disable the policy and see if another policy allows something.
        // If another policy handles the request answer false.
        CreateRakeLinkEditPolicy.activated = false;
        try {
            Command cmd = getHost().getCommand(request);
            if (cmd != null && cmd.canExecute()) {
                return false;
            }
        } finally {
            CreateRakeLinkEditPolicy.activated = true;
        }
        
        // Allow the rake if the target of the target link allow the
        // connection creation request.
        EditPart ep = request.getTargetEditPart();
        request.setTargetEditPart(((ConnectionEditPart) getHost()).getTarget());
        EditPart t = request.getTargetEditPart().getTargetEditPart(request);
        boolean ret = (t != null) && (t.getCommand(request) != null);
        request.setTargetEditPart(ep);
        return ret;
    }

    /**
     * Get the anchor model for the given anchor.
     * @param editpart a node edit part.
     * @param anchor a draw2d anchor
     * @return the anchor model, may be <code>null</code>
     */
    @objid ("805c5963-1dec-11e2-8cad-001ec947c8cc")
    private Object getAnchorModel(final NodeEditPart editpart, final ConnectionAnchor anchor) {
        if (editpart instanceof IAnchorModelProvider) {
            return (((IAnchorModelProvider) editpart).createAnchorModel(anchor));
        } else {
            return (null); // TODO handle non IAnchorModelProvider
        }
        
    }

    @objid ("805c5923-1dec-11e2-8cad-001ec947c8cc")
    private ConnectionAnchor getFinalSourceAnchor(final NodeEditPart newSourceEditPart, final ReconnectRequest request) {
        final ConnectionEditPart connectionEditPart = request.getConnectionEditPart();
        // Save old router & constraint
        Connection fig = (Connection) connectionEditPart.getFigure();
        Object oldConstraint = fig.getRoutingConstraint();
        ConnectionRouter oldRouter = fig.getConnectionRouter();
        
        // Change the router
        fig.setConnectionRouter(new RakeRouter());
        fig.setRoutingConstraint(new RakeConstraint());
        
        // Ask for an anchor in rake mode
        ReconnectRequest r = new ReconnectRequest(RequestConstants.REQ_RECONNECT_SOURCE);
        r.setConnectionEditPart(request.getConnectionEditPart());
        r.setLocation(request.getLocation());
        r.setTargetEditPart(newSourceEditPart);
        
        ConnectionAnchor ret = newSourceEditPart.getSourceConnectionAnchor(r);
        
        // Restore the connection
        fig.setConnectionRouter(oldRouter);
        fig.setRoutingConstraint(oldConstraint);
        return ret;
    }

    @objid ("805c5931-1dec-11e2-8cad-001ec947c8cc")
    private ConnectionAnchor getFinalTargetAnchor(final NodeEditPart newTargetEditPart, final ReconnectRequest request) {
        final ConnectionEditPart connectionEditPart = request.getConnectionEditPart();
        // Save old router & constraint
        Connection fig = (Connection) connectionEditPart.getFigure();
        Object oldConstraint = fig.getRoutingConstraint();
        ConnectionRouter oldRouter = fig.getConnectionRouter();
        
        // Change the router
        fig.setConnectionRouter(new RakeRouter());
        fig.setRoutingConstraint(new RakeConstraint());
        
        // Ask for an anchor in rake mode
        Point p = ((Connection) getHostFigure()).getPoints().getLastPoint();
        fig.translateToAbsolute(p);
        
        ReconnectRequest r = new ReconnectRequest(RequestConstants.REQ_RECONNECT_TARGET);
        r.setConnectionEditPart(connectionEditPart);
        r.setLocation(p);
        r.setTargetEditPart(newTargetEditPart);
        
        ConnectionAnchor ret = newTargetEditPart.getTargetConnectionAnchor(r);
        
        // Restore the connection
        fig.setConnectionRouter(oldRouter);
        fig.setRoutingConstraint(oldConstraint);
        return ret;
    }

    @objid ("805c594e-1dec-11e2-8cad-001ec947c8cc")
    private MClass getMetaclass(final CreateConnectionRequest request) {
        Object factory = request.getNewObject();
        if (factory instanceof ModelioLinkCreationContext) {
            ModelioLinkCreationContext ctx = (ModelioLinkCreationContext) factory;
            return ctx.getMetaclass();
        } else if (factory instanceof ModelioCreationContext) {
            ModelioCreationContext ctx = (ModelioCreationContext) factory;
            return ctx.getMetaclass();
        } else {
            return null;
        }
        
    }

    @objid ("805ebb7a-1dec-11e2-8cad-001ec947c8cc")
    private boolean isLinkCycle(EditPart testedEditPart, ConnectionEditPart reconnectedConnectionEP) {
        if (testedEditPart.equals(reconnectedConnectionEP)) {
            return true;
        } else if (testedEditPart instanceof ConnectionEditPart) {
            ConnectionEditPart connectionEP = (ConnectionEditPart) testedEditPart;
            return isLinkCycle(connectionEP.getSource(), reconnectedConnectionEP)
                    || isLinkCycle(connectionEP.getTarget(), reconnectedConnectionEP);
        } else {
            return false;
        }
        
    }

}
