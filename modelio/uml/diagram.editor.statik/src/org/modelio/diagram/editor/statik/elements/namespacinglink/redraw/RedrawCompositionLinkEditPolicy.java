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

package org.modelio.diagram.editor.statik.elements.namespacinglink.redraw;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.editor.statik.elements.namespacinglink.CircleDeco;
import org.modelio.diagram.editor.statik.elements.namespacinglink.GmCompositionLink;
import org.modelio.diagram.elements.core.link.ConnectionRouterRegistry;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.path.ConnectionHelperFactory;
import org.modelio.diagram.elements.core.link.path.ConnectionPolicyUtils;
import org.modelio.diagram.elements.core.link.path.IConnectionHelper;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Edit policy that allow redrawing of Namespacing links.
 */
@objid ("35b2a5d0-55b7-11e2-877f-002564c97630")
public class RedrawCompositionLinkEditPolicy extends GraphicalNodeEditPolicy {
    @objid ("f3d5b96c-7cf8-4223-b789-a3a3a0cfe4a8")
    private XYAnchor dummyAnchor = new XYAnchor(new Point(10, 10));

    /**
     * C'tor.
     */
    @objid ("35b42c3b-55b7-11e2-877f-002564c97630")
    public RedrawCompositionLinkEditPolicy() {
        super();
    }

    @objid ("35b42c3e-55b7-11e2-877f-002564c97630")
    @Override
    public void eraseSourceFeedback(Request request) {
        if (CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT.equals(request.getType())) {
            eraseCreationFeedback((CreateConnectionRequest) request);
        } else {
            super.eraseSourceFeedback(request);
        }
    }

    @objid ("35b42c42-55b7-11e2-877f-002564c97630")
    @Override
    public void eraseTargetFeedback(Request request) {
        if (CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT.equals(request.getType())) {
            eraseTargetConnectionFeedback((DropRequest) request);
        } else {
            super.eraseTargetFeedback(request);
        }
    }

    @objid ("35b42c46-55b7-11e2-877f-002564c97630")
    @Override
    public EditPart getTargetEditPart(Request request) {
        // Based on the exact type of request, delegate to a specialized private method.
        if (REQ_CONNECTION_START.equals(request.getType())) {
            return getTargetEditPartConnectionStart((CreateConnectionRequest) request);
        } else if (REQ_CONNECTION_END.equals(request.getType())) {
            return getTargetEditPartConnectionEnd((CreateConnectionRequest) request);
        }
        
        if (REQ_RECONNECT_SOURCE.equals(request.getType())
                || REQ_RECONNECT_TARGET.equals(request.getType())) {
            ReconnectRequest req = (ReconnectRequest) request;
        
            // Handle reconnecting only for composition links
            if (req.getConnectionEditPart().getModel() instanceof GmCompositionLink) {
                return getHost();
            }
        }
        return null;
    }

    @objid ("35b42c4c-55b7-11e2-877f-002564c97630")
    @Override
    public void showSourceFeedback(Request request) {
        if (CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT.equals(request.getType())) {
            showCreationFeedback((CreateConnectionRequest) request);
        } else {
            super.showSourceFeedback(request);
        }
    }

    @objid ("35b42c50-55b7-11e2-877f-002564c97630")
    @Override
    public void showTargetFeedback(final Request request) {
        // Target feedback is already handled by DefaultCreateLinkEditPolicy.
        return;
    }

    @objid ("35b42c56-55b7-11e2-877f-002564c97630")
    @Override
    protected Connection createDummyConnection(Request req) {
        final PolylineConnection ret = new PolylineConnection();
        CircleDeco dec = new CircleDeco();
        dec.setOpaque(true);
        ret.setSourceDecoration(dec);
        return ret;
    }

    /**
     * Create a serializable path model from the given connection creation request.
     * @param req a connection creation request.
     * @return A serializable path model.
     */
    @objid ("35b42c5c-55b7-11e2-877f-002564c97630")
    protected final IGmPath createPathModel(final CreateConnectionRequest req) {
        // Create a temporary connection to be able to compute the path data
        final Connection tmpConnection = createDummyConnection(req);
        
        IGmPath ret = ConnectionPolicyUtils.createPathModel(req, tmpConnection);
        
        if (tmpConnection.getParent() != null) {
            tmpConnection.getParent().remove(tmpConnection);
        }
        return ret;
    }

    @objid ("35b42c65-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getConnectionCompleteCommand(final CreateConnectionRequest request) {
        // Only handle composition link redraw requests.
        if (!(request.getNewObject() instanceof RedrawCompositionLinkFactory)) {
            return null;
        }
        final RedrawCompositionLinkFactory context = (RedrawCompositionLinkFactory) request.getNewObject();
        
        // Check consistency of elements involved
        final MObject hostElement = ((GmModel) getHost().getModel()).getRelatedElement();
        if (hostElement == null ||
            !hostElement.isValid() ||
            !context.getSourceElement().equals(hostElement.getCompositionOwner()) ||
            !context.getTargetElement().equals(hostElement)) {
            return null;
        }
        
        // Extract start command from request (see getConnectionCreateCommand).
        final RedrawCompositionLinkCommand startCommand = (RedrawCompositionLinkCommand) request.getStartCommand();
        
        startCommand.setTarget((IGmLinkable) getHost().getModel());
        // Additional step: add the optional bend points.
        startCommand.setPath(createPathModel(request));
        return startCommand;
    }

    @objid ("35b42c6c-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest req) {
        // Only handle composition link redraw requests.
        if (!(req.getNewObject() instanceof RedrawCompositionLinkFactory)) {
            return null;
        }
        RedrawCompositionLinkFactory context = (RedrawCompositionLinkFactory) req.getNewObject();
        RedrawCompositionLinkCommand command = new RedrawCompositionLinkCommand(context);
        
        command.setSource((IGmLinkable) getHost().getModel());
        // Store command in the request so that it can be used (and in most cases completed) later by the target node.
        req.setStartCommand(command);
        return command;
    }

    @objid ("35b42c72-55b7-11e2-877f-002564c97630")
    @Override
    protected ConnectionRouter getDummyConnectionRouter(final CreateConnectionRequest request) {
        return new BendpointConnectionRouter();
    }

    @objid ("35b42c79-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getReconnectSourceCommand(ReconnectRequest req) {
        return null;
    }

    @objid ("35b5b2dd-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getReconnectTargetCommand(ReconnectRequest req) {
        return null;
    }

    @objid ("35b5b2e3-55b7-11e2-877f-002564c97630")
    @Override
    protected void showCreationFeedback(CreateConnectionRequest request) {
        // Only handle composition link redraw requests.
        if (!(request.getNewObject() instanceof RedrawCompositionLinkFactory)) {
            return;
        }
        if (request instanceof CreateBendedConnectionRequest) {
            final CreateBendedConnectionRequest req = (CreateBendedConnectionRequest) request;
        
            // Call the method to force creation of the connection feedback
            getFeedbackHelper(request);
        
            // Set/update the router
            final ConnectionRouter router = getRouterRegistry().get(req.getData().getRoutingMode());
            this.connectionFeedback.setConnectionRouter(router);
        
            // Set/update the anchors
            final ConnectionAnchor srcAnchor = getSourceConnectionAnchor(req);
            this.connectionFeedback.setSourceAnchor(srcAnchor);
            ConnectionAnchor targetAnchor = getTargetConnectionAnchor(req);
            if (targetAnchor == null) {
                // No target yet to provide an anchor, use a dummy positioned at the mouse tip.
                this.dummyAnchor.setLocation(request.getLocation());
                targetAnchor = this.dummyAnchor;
            }
            this.connectionFeedback.setTargetAnchor(targetAnchor);
        
            // Set/update the routing constraint.
            IConnectionHelper connHelper = ConnectionHelperFactory.getUpdatedConnectionHelper(req, this.connectionFeedback);
            this.connectionFeedback.setRoutingConstraint(connHelper.getRoutingConstraint());
        
        } else {
            super.showCreationFeedback(request);
        }
    }

    /**
     * @return the connection routers registry.
     */
    @objid ("35b5b2ef-55b7-11e2-877f-002564c97630")
    private ConnectionRouterRegistry getRouterRegistry() {
        return (ConnectionRouterRegistry) getHost().getViewer().getProperty(ConnectionRouterRegistry.ID);
    }

    /**
     * Returns the host if the given request can be handled, <code>null</code> otherwise.
     * @param request a complete Connection creation request.
     * @return the host edit part or <code>null</code>.
     */
    @objid ("35b5b2f6-55b7-11e2-877f-002564c97630")
    protected EditPart getTargetEditPartConnectionEnd(final CreateConnectionRequest request) {
        // Only handle composition link redraw requests.
        if (!(request.getNewObject() instanceof RedrawCompositionLinkFactory)) {
            return null;
        } else {
            return getHost();
        }
    }

    /**
     * Returns the host if the given request can be handled, <code>null</code> otherwise.
     * @param request a starting Connection creation request.
     * @return the host edit part or <code>null</code>.
     */
    @objid ("35b5b2fc-55b7-11e2-877f-002564c97630")
    protected EditPart getTargetEditPartConnectionStart(final CreateConnectionRequest request) {
        // Only handle composition link redraw requests.
        if (!(request.getNewObject() instanceof RedrawCompositionLinkFactory)) {
            return null;
        } else {
            return getHost();
        }
    }

}
