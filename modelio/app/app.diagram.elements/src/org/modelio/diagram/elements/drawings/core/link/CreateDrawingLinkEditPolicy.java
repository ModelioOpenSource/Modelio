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

package org.modelio.diagram.elements.drawings.core.link;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.FreeformFigure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
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
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.figures.FigureUtilities2;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.path.ConnectionHelperFactory;
import org.modelio.diagram.elements.core.link.path.ConnectionPolicyUtils;
import org.modelio.diagram.elements.core.link.path.IConnectionHelper;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLink;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLinkable;

/**
 * Edit policy that allow creating drawing links from/to the edit part.
 */
@objid ("1c1c8012-9766-4962-809d-c17561f69415")
public class CreateDrawingLinkEditPolicy extends GraphicalNodeEditPolicy {
    @objid ("cd93b724-d4fb-44a1-b257-ecc1562b4a5e")
    private static final int ARROW_DEPTH = 9;

    @objid ("2e5dd8e7-009f-4a06-88fa-4ea9123efd24")
    private static final int ARROW_WIDTH = 4;

    /**
     * <p>
     * This boolean is used to determine the behavior to adopt when receiving a request about a link metaclass that is not handled
     * because the CreationExpert does not allow it.
     * </p>
     * <ul>
     * <li>When <code>true</code>, the policy will still return the host in the "getTargetEditPart" method and it will return a non
     * executable command in the getCommand method. This will in effect prevent the tool from proposing the request to the host's
     * parent edit part, meaning the host is "opaque".</li>
     * <li>When <code>false</code> on the other hand, the getTargetEditPart method will return <code>null</code>, giving a chance to
     * the tool to propose the request to the host's parent edit part, meaning the host is "transparent".</li>
     * </ul>
     */
    @objid ("b1319d6c-bfc3-4fc5-a5f3-3bb364bcbfce")
    private boolean isOpaque = true;

    @objid ("3b869150-7e2c-46e6-82c9-d930c0d71385")
    private XYAnchor dummyAnchor = new XYAnchor(new Point(10, 10));

    @objid ("4c9f9264-54e3-483f-b41f-4bffd565c6ac")
    private IFigure highlight;

    /**
     * No Parameter c'tor: creates an opaque instance of this policy.
     * @see #CreateDrawingLinkEditPolicy(boolean)
     */
    @objid ("f8035320-96b3-46c9-97d4-a5b58018dc7a")
    public CreateDrawingLinkEditPolicy() {
        this(true);
    }

    /**
     * <p>
     * C'tor.
     * </p>
     * <p>
     * <string><em>Note about isOpaque effect:</em></strong>
     * <p>
     * This boolean is used to determine the behavior to adopt when receiving a request about a link metaclass that is not handled
     * because the CreationExpert does not allow it.
     * </p>
     * <ul>
     * <li>When <code>true</code>, the policy will still return the host in the "getTargetEditPart" method and it will return a non
     * executable command in the getCommand method. This will in effect prevent the tool from proposing the request to the host's
     * parent edit part, meaning the host is "opaque".</li>
     * <li>When <code>false</code> on the other hand, the getTargetEditPart method will return <code>null</code>, giving a chance to
     * the tool to propose the request to the host's parent edit part, meaning the host is "transparent".</li>
     * </ul>
     * </p>
     * 
     * @param isOpaque determines the behavior of this policy on request where the creation expert doesn't allow. See Note.
     */
    @objid ("07670c82-1127-4184-b55f-baf4a5e8667d")
    public CreateDrawingLinkEditPolicy(final boolean isOpaque) {
        super();
        this.isOpaque = isOpaque;
    }

    @objid ("a7c83a97-fdd7-4aff-abae-f93cc6775b68")
    @Override
    public void eraseSourceFeedback(Request request) {
        if (CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT.equals(request.getType())) {
            eraseCreationFeedback((CreateConnectionRequest) request);
        } else {
            super.eraseSourceFeedback(request);
        }
    }

    @objid ("6a45c223-ac00-446c-a25e-1ce88ba0c77f")
    @Override
    public void eraseTargetFeedback(Request request) {
        if (CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT.equals(request.getType())) {
            eraseTargetConnectionFeedback((DropRequest) request);
        } else {
            super.eraseTargetFeedback(request);
        }
    }

    @objid ("5034db51-7e92-43af-bb04-8421ea9941a0")
    @Override
    public EditPart getTargetEditPart(Request request) {
        // Based on the exact type of request, delegate to a specialized private method.
        if (RequestConstants.REQ_CONNECTION_START.equals(request.getType())) {
            return getTargetEditPartConnectionStart((CreateConnectionRequest) request);
        } else if (RequestConstants.REQ_CONNECTION_END.equals(request.getType())) {
            return getTargetEditPartConnectionEnd((CreateConnectionRequest) request);
        } else if (RequestConstants.REQ_RECONNECT_SOURCE.equals(request.getType())) {
            return getReconnectSourceTargetEditPart((ReconnectRequest) request);
        } else if (RequestConstants.REQ_RECONNECT_TARGET.equals(request.getType())) {
            return getReconnectTargetTargetEditPart((ReconnectRequest) request);
        }
        return super.getTargetEditPart(request);
    }

    @objid ("231475aa-1be1-4a2e-bedb-b64c8346b377")
    @Override
    public void showSourceFeedback(Request request) {
        if (CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT.equals(request.getType())) {
            showCreationFeedback((CreateConnectionRequest) request);
        } else {
            super.showSourceFeedback(request);
        }
    }

    @objid ("e2980085-0a68-494c-ad10-ace32edf1a1b")
    @Override
    public void showTargetFeedback(final Request request) {
        if (CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT.equals(request.getType())) {
            showTargetConnectionFeedback((DropRequest) request);
        } else {
            super.showTargetFeedback(request);
        }
    }

    @objid ("64b18ff3-83ff-4a8a-b0c2-609c40e61252")
    @Override
    protected Connection createDummyConnection(Request req) {
        final PolylineConnection ret = new PolylineConnection();
        
        // Add an arrow
        final PolylineDecoration arrow = new PolylineDecoration();
        arrow.setTemplate(PolylineDecoration.TRIANGLE_TIP);
        arrow.setScale(CreateDrawingLinkEditPolicy.ARROW_DEPTH, CreateDrawingLinkEditPolicy.ARROW_WIDTH);
        arrow.setOpaque(false);
        arrow.setBackgroundColor(null);
        arrow.setFill(false);
        
        ret.setTargetDecoration(arrow);
        return ret;
    }

    /**
     * Create a serializable path model from the given connection creation request.
     * 
     * @param req a connection creation request.
     * @return A serializable path model.
     */
    @objid ("99841a88-04e4-44ae-b490-c6e9f2efff7d")
    protected final IGmPath createPathModel(final CreateConnectionRequest req) {
        // Create a temporary connection to be able to compute the path data
        final Connection tmpConnection = createDummyConnection(req);
        IGmPath ret = ConnectionPolicyUtils.createPathModel(req, tmpConnection);
        if (tmpConnection.getParent() != null) {
            tmpConnection.getParent().remove(tmpConnection);
        }
        return ret;
    }

    @objid ("11fabfe7-142b-4078-89b6-89b575c8abbe")
    @Override
    protected void eraseTargetConnectionFeedback(DropRequest request) {
        super.eraseTargetConnectionFeedback(request);
        
        // Additional feedback: outline the Node.
        if (this.highlight != null) {
            final IFigure feedbackLayer = getFeedbackLayer();
            feedbackLayer.remove(this.highlight);
            this.highlight = null;
        }
    }

    @objid ("5e1a1492-d03b-447f-aa97-1e04650a40b2")
    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest req) {
        // Only handle model element creation requests.
        if (!isLineCreationRequest(req)) {
            return null;
        }
        
        // Extract start command from request (see getConnectionCreateCommand).
        final CreateDrawingLinkCommand startCommand = (CreateDrawingLinkCommand) req.getStartCommand();
        
        startCommand.setTarget((IGmDrawingLinkable) getHost().getModel());
        // Additional step: add the optional bend points.
        startCommand.setPath(createPathModel(req));
        return startCommand;
    }

    @objid ("b95961ed-e797-40d8-8084-751046393cd8")
    @SuppressWarnings ("unchecked")
    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest req) {
        // Only handle model element creation requests.
        if (!isLineCreationRequest(req)) {
            return null;
        }
        
        // Getting a hold on the source anchor
        NodeEditPart sourceEditPart = (NodeEditPart) getHost();
        req.getExtendedData().remove("srcAnchor");
        ConnectionAnchor srcAnchor = sourceEditPart.getSourceConnectionAnchor(req);
        req.getExtendedData().put("srcAnchor", srcAnchor);
        
        // Create the create connection command
        final Class<? extends IGmDrawingLink> context = (Class<? extends IGmDrawingLink>) req.getNewObjectType();
        final String drawingIdent = (String) req.getNewObject();
        
        final CreateDrawingLinkCommand command = new CreateDrawingLinkCommand(context, drawingIdent);
        
        command.setSource((IGmDrawingLinkable) getHost().getModel());
        
        // Store command in the request so that it can be used (and in most cases completed) later by the target node.
        req.setStartCommand(command);
        return command;
    }

    @objid ("e3ec47ef-6b0b-4763-bb74-7944510f0dff")
    @Override
    protected ConnectionRouter getDummyConnectionRouter(final CreateConnectionRequest request) {
        return new BendpointConnectionRouter();
    }

    @objid ("621be315-417f-4161-befd-8ce1bc8c6e61")
    @Override
    protected Command getReconnectSourceCommand(ReconnectRequest req) {
        final NodeEditPart newSourceNodeEditPart = (NodeEditPart) req.getTarget();
        final ConnectionEditPart connectionEditPart = req.getConnectionEditPart();
        
        if (!(connectionEditPart.getModel() instanceof IGmDrawingLink)) {
            return null;
        }
        
        final IGmDrawingLink linkModel = (IGmDrawingLink) connectionEditPart.getModel();
        final IGmDrawingLinkable newSourceNode = (IGmDrawingLinkable) newSourceNodeEditPart.getModel();
        
        if (linkModel.getDiagram() != newSourceNode.getDiagram()) {
            return null;
        }
        
        final ReconnectDrawingSourceCommand cmd = new ReconnectDrawingSourceCommand(linkModel, newSourceNode);
        
        final ConnectionAnchor srcAnchor = newSourceNodeEditPart.getSourceConnectionAnchor(req);
        cmd.setAnchorModel(ConnectionPolicyUtils.getAnchorModel(newSourceNodeEditPart, srcAnchor));
        return cmd;
    }

    @objid ("65bf5f45-bcd6-48b2-bed7-173994af54ff")
    @Override
    protected Command getReconnectTargetCommand(ReconnectRequest req) {
        final ConnectionEditPart connectionEditPart = req.getConnectionEditPart();
        if (!(connectionEditPart.getModel() instanceof IGmDrawingLink)) {
            return null;
        }
        
        final NodeEditPart destEditPart = (NodeEditPart) req.getTarget();
        final IGmDrawingLinkable newTargetNode = (IGmDrawingLinkable) destEditPart.getModel();
        final IGmDrawingLink gmLink = (IGmDrawingLink) connectionEditPart.getModel();
        
        if (gmLink.getDiagram() != newTargetNode.getDiagram()) {
            return null;
        }
        
        // build the command
        ReconnectDrawingTargetCommand cmd = new ReconnectDrawingTargetCommand(gmLink, newTargetNode);
        ConnectionAnchor targetAnchor = destEditPart.getTargetConnectionAnchor(req);
        cmd.setAnchorModel(ConnectionPolicyUtils.getAnchorModel(destEditPart, targetAnchor));
        return cmd;
    }

    @objid ("06c99a4f-6cfc-4ef6-8e93-3a0cedd5414b")
    @Override
    protected void showCreationFeedback(CreateConnectionRequest request) {
        // Only handle model element creation requests.
        if (!isLineCreationRequest(request)) {
            return;
        }
        if (request instanceof CreateBendedConnectionRequest) {
            final CreateBendedConnectionRequest req = (CreateBendedConnectionRequest) request;
        
            // Call the method to force creation of the connection feedback
            getFeedbackHelper(request);
        
            // Set/update the router
            final ConnectionRouter router = ConnectionPolicyUtils.getRouterRegistry(getHost()).get(req.getData().getRoutingMode());
            this.connectionFeedback.setConnectionRouter(router);
        
            // Set/update the source anchor
            final ConnectionAnchor srcAnchor = getSourceConnectionAnchor(req);
            this.connectionFeedback.setSourceAnchor(srcAnchor);
        
            // Set/update the target anchor
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

    @objid ("f7bb2384-3588-41b9-b2d2-d1efd3dc3528")
    @Override
    protected void showTargetConnectionFeedback(DropRequest request) {
        final IFigure hostFigure = getHostFigure();
        
        // Additional feedback: highlight the node.
        // compute highlight type
        final Command c = getHost().getCommand((Request) request);
        FigureUtilities2.HighlightType hightlightType = FigureUtilities2.HighlightType.INFO;
        if (c == null) {
            hightlightType = FigureUtilities2.HighlightType.ERROR;
        } else if (c.canExecute()) {
            hightlightType = FigureUtilities2.HighlightType.SUCCESS;
        
            // No feedback if the node is a layer or the background
            if (hostFigure instanceof FreeformFigure) {
                return;
            }
        } else {
            hightlightType = FigureUtilities2.HighlightType.WARNING;
        }
        
        // create a highlight figure if it does not exist
        if (this.highlight == null) {
            // create a highlight figure
            this.highlight = FigureUtilities2.createHighlightFigure(getFeedbackLayer(), hostFigure, hightlightType);
            // add the highlight figure to the feedback layer
            getFeedbackLayer().add(this.highlight);
        } else {
            // configure the highlight figure
            FigureUtilities2.updateHighlightType(this.highlight, hightlightType);
        }
    }

    /**
     * Returns the host if the given request can be handled, <code>null</code> otherwise.
     * 
     * @param request a Source Reconnect request.
     * @return the host edit part or <code>null</code>.
     */
    @objid ("df046104-4b28-411e-976d-436a50e7d1fe")
    private EditPart getReconnectSourceTargetEditPart(final ReconnectRequest request) {
        ConnectionEditPart reconnectedConnectionEP = request.getConnectionEditPart();
        
        if (!(reconnectedConnectionEP.getModel() instanceof IGmDrawingLink)) {
            return null;
        }
        
        final IGmDrawingLink gmLink = (IGmDrawingLink) reconnectedConnectionEP.getModel();
        final IGmDrawingLinkable newSrcNode = (IGmDrawingLinkable) getHost().getModel();
        final IGmDrawingLinkable oldSrcNode = gmLink.getFrom();
        
        // No check needed if the source node is unchanged
        if (oldSrcNode == newSrcNode) {
            return getHost();
        }
        
        // Avoid creating links cycles
        if (isLinkCycle(getHost(), reconnectedConnectionEP)) {
            return null;
        }
        
        if (this.isOpaque) {
            return getHost();
        } else {
            return null;
        }
    }

    /**
     * Returns the host if the given request can be handled, <code>null</code> otherwise.
     * 
     * @param request a Target Reconnect request.
     * @return the host edit part or <code>null</code>.
     */
    @objid ("3a9942f1-4585-48a8-ba68-5453a313c527")
    private EditPart getReconnectTargetTargetEditPart(final ReconnectRequest request) {
        ConnectionEditPart reconnectedConnectionEP = request.getConnectionEditPart();
        if (!(reconnectedConnectionEP.getModel() instanceof IGmDrawingLink)) {
            return null;
        }
        
        final IGmDrawingLink gmLink = (IGmDrawingLink) reconnectedConnectionEP.getModel();
        final IGmDrawingLinkable newTargetNode = (IGmDrawingLinkable) getHost().getModel();
        final IGmDrawingLinkable oldTargetNode = gmLink.getTo();
        
        // No check needed if the source node is unchanged
        if (oldTargetNode == newTargetNode) {
            return getHost();
        }
        
        // Avoid creating links cycles
        if (isLinkCycle(getHost(), reconnectedConnectionEP)) {
            return null;
        }
        
        // If creation experts allows OR this instance is "opaque" (see javadoc on private attribute isOpaque for details), return
        // host.
        if (this.isOpaque) {
            return getHost();
        } else {
            return null;
        }
    }

    /**
     * Returns the host if the given request can be handled, <code>null</code> otherwise.
     * 
     * @param request a complete Connection creation request.
     * @return the host edit part or <code>null</code>.
     */
    @objid ("cb86814a-bf27-4cb6-a422-55d217670249")
    protected EditPart getTargetEditPartConnectionEnd(final CreateConnectionRequest request) {
        // Only handle model element creation requests.
        
        if (!isLineCreationRequest(request)) {
            return null;
        }
        if (!this.isOpaque) {
            return null;
        }
        return getHost();
    }

    /**
     * Returns the host if the given request can be handled, <code>null</code> otherwise.
     * 
     * @param request a starting Connection creation request.
     * @return the host edit part or <code>null</code>.
     */
    @objid ("b2fbbeba-4fc7-4850-8c83-9391db685d31")
    protected EditPart getTargetEditPartConnectionStart(final CreateConnectionRequest request) {
        // Only handle model element creation requests.
        if (!isLineCreationRequest(request)) {
            return null;
        }
        
        if (!this.isOpaque) {
            return null;
        }
        return getHost();
    }

    @objid ("3169f02c-ed05-46a9-a5e5-d535b0ec6391")
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

    @objid ("cc7ebfac-35ba-48f3-87b4-19a82e031294")
    private boolean isLineCreationRequest(CreateConnectionRequest request) {
        Object type = request.getNewObjectType();
        if (type instanceof Class) {
            return IGmDrawingLink.class.isAssignableFrom((Class<?>) type);
        }
        return false;
    }

}
