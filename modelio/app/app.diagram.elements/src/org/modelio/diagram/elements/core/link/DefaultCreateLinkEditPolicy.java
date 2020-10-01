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

package org.modelio.diagram.elements.core.link;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.diagram.elements.core.figures.FigureUtilities2;
import org.modelio.diagram.elements.core.helpers.palapi.PaletteActionProvider;
import org.modelio.diagram.elements.core.link.createhandle.CreateLinkHandleEditPolicy;
import org.modelio.diagram.elements.core.link.createhandle.UserChoiceCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.link.path.ConnectionHelperFactory;
import org.modelio.diagram.elements.core.link.path.ConnectionPolicyUtils;
import org.modelio.diagram.elements.core.link.path.IConnectionHelper;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.model.IGmModelRelated;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Edit policy that allow creating associations between 2 classes.
 */
@objid ("7fe78611-1dec-11e2-8cad-001ec947c8cc")
public class DefaultCreateLinkEditPolicy extends GraphicalNodeEditPolicy {
    @objid ("7fe78615-1dec-11e2-8cad-001ec947c8cc")
    private static final int ARROW_DEPTH = 9;

    @objid ("7fe78617-1dec-11e2-8cad-001ec947c8cc")
    private static final int ARROW_WIDTH = 4;

    /**
     * <p>
     * This boolean is used to determine the behavior to adopt when receiving a request about a link metaclass that is not handled because the CreationExpert does not allow it.
     * </p>
     * <ul>
     * <li>When <code>true</code>, the policy will still return the host in the "getTargetEditPart" method and it will return a non executable command in the getCommand method. This will in effect prevent the tool from proposing the request to the host's
     * parent edit part, meaning the host is "opaque".</li>
     * <li>When <code>false</code> on the other hand, the getTargetEditPart method will return <code>null</code>, giving a chance to the tool to propose the request to the host's parent edit part, meaning the host is "transparent".</li>
     * </ul>
     */
    @objid ("7fe78619-1dec-11e2-8cad-001ec947c8cc")
    private boolean isOpaque = true;

    @objid ("6723a327-1e83-11e2-8cad-001ec947c8cc")
    private XYAnchor dummyAnchor = new XYAnchor(new Point(10, 10));

    @objid ("6723a328-1e83-11e2-8cad-001ec947c8cc")
    private IFigure highlight;

    /**
     * No Parameter c'tor: creates an opaque instance of this policy.
     * @see #DefaultCreateLinkEditPolicy(boolean)
     */
    @objid ("7fe78621-1dec-11e2-8cad-001ec947c8cc")
    public DefaultCreateLinkEditPolicy() {
        this(true);
    }

    /**
     * <p>
     * C'tor.
     * </p>
     * <p>
     * <string><em>Note about isOpaque effect:</em></strong>
     * <p>
     * This boolean is used to determine the behavior to adopt when receiving a request about a link metaclass that is not handled because the CreationExpert does not allow it.
     * </p>
     * <ul>
     * <li>When <code>true</code>, the policy will still return the host in the "getTargetEditPart" method and it will return a non executable command in the getCommand method. This will in effect prevent the tool from proposing the request to the host's
     * parent edit part, meaning the host is "opaque".</li>
     * <li>When <code>false</code> on the other hand, the getTargetEditPart method will return <code>null</code>, giving a chance to the tool to propose the request to the host's parent edit part, meaning the host is "transparent".</li>
     * </ul>
     * </p>
     * 
     * @param isOpaque determines the behavior of this policy on request where the creation expert doesn't allow. See Note.
     */
    @objid ("7fe78624-1dec-11e2-8cad-001ec947c8cc")
    public DefaultCreateLinkEditPolicy(final boolean isOpaque) {
        super();
        this.isOpaque = isOpaque;
    }

    @objid ("7fe78629-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void eraseSourceFeedback(Request request) {
        if (CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT.equals(request.getType())) {
            eraseCreationFeedback((CreateConnectionRequest) request);
        } else {
            super.eraseSourceFeedback(request);
        }
    }

    @objid ("7fe7862f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void eraseTargetFeedback(Request request) {
        if (CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT.equals(request.getType())) {
            eraseTargetConnectionFeedback((DropRequest) request);
        } else {
            super.eraseTargetFeedback(request);
        }
    }

    @objid ("7fe78635-1dec-11e2-8cad-001ec947c8cc")
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

    @objid ("7fe9e843-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void showSourceFeedback(Request request) {
        if (CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT.equals(request.getType())) {
            showCreationFeedback((CreateConnectionRequest) request);
        } else {
            super.showSourceFeedback(request);
        }
    }

    @objid ("7fe9e849-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void showTargetFeedback(final Request request) {
        if (CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT.equals(request.getType())) {
            showTargetConnectionFeedback((DropRequest) request);
        } else {
            super.showTargetFeedback(request);
        }
    }

    @objid ("7fe9e850-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Connection createDummyConnection(Request req) {
        final PolylineConnection ret = new PolylineConnection();
        
        // Add an arrow
        final PolylineDecoration arrow = new PolylineDecoration();
        arrow.setTemplate(PolylineDecoration.TRIANGLE_TIP);
        arrow.setScale(DefaultCreateLinkEditPolicy.ARROW_DEPTH, DefaultCreateLinkEditPolicy.ARROW_WIDTH);
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
    @objid ("7fe9e85a-1dec-11e2-8cad-001ec947c8cc")
    protected final IGmPath createPathModel(final CreateConnectionRequest req) {
        // Create a temporary connection to be able to compute the path data
        final Connection tmpConnection = createDummyConnection(req);
        IGmPath ret = ConnectionPolicyUtils.createPathModel(req, tmpConnection);
        tmpConnection.getParent().remove(tmpConnection);
        return ret;
    }

    @objid ("7fe9e863-1dec-11e2-8cad-001ec947c8cc")
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

    @objid ("7fe9e869-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest req) {
        final ModelioLinkCreationContext context = ModelioLinkCreationContext.lookRequest(req);
        
        // Only handle link model element creation requests.
        if (context == null) {
            return null;
        }
        
        if (context.getElementToUnmask() == null) {
            final MObject sourceElement = ((IGmLinkable) req.getSourceEditPart().getModel()).getRelatedElement();
            final MObject targetElement = ((GmModel) getHost().getModel()).getRelatedElement();
        
            MExpert expert = context.getMetaclass().getMetamodel().getMExpert();
        
            // If creation expert does not allow and yet we ended here, this means this policy is opaque, so return non executable
            // command.
            if (!expert.canLink(context.getMetaclass(), sourceElement, targetElement)) {
                return null;
            }
        } else {
            // using an existing model element, make sure it does end on host's model element
            final MObject hostElement = ((GmModel) getHost().getModel()).getRelatedElement();
            if (hostElement == null || hostElement.isShell() || hostElement.isDeleted()) {
                return null;
            }
        
            final MExpert expert = hostElement.getMClass().getMetamodel().getMExpert();
            if (!hostElement.equals(expert.getTarget(context.getElementToUnmask()))) {
                return null;
            }
        }
        
        if (req.getExtendedData().containsKey(CreateLinkConstants.PROP_USER_INTERACTION)) {
            if (! isNewLinkDiagramEditable(req)) {
                return null;
            }
        }
        
        // Extract start command from request (see getConnectionCreateCommand).
        final DefaultCreateLinkCommand startCommand = (DefaultCreateLinkCommand) req.getStartCommand();
        
        startCommand.setTarget((IGmLinkable) getHost().getModel());
        // Additional step: add the optional bend points.
        startCommand.setPath(createPathModel(req));
        return startCommand;
    }

    @objid ("7fe9e873-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest req) {
        final ModelioLinkCreationContext context = ModelioLinkCreationContext.lookRequest(req);
        
        // Only handle link model element creation requests.
        if (context == null) {
            return null;
        }
        
        if (context.getElementToUnmask() == null) {
            final GmModel gmModel = (GmModel) getHost().getModel();
            final MObject sourceElement = gmModel.getRelatedElement();
            final IMdaExpert mdaExpert = gmModel.getDiagram().getModelManager().getMdaExpert();
        
            // If creation expert does not allow and yet we ended here, this means this policy is opaque, so return non executable
            // command.
            if (!mdaExpert.canSource(context.getStereotype(), context.getMetaclass(), sourceElement.getMClass())) {
                return null;
            }
        }
        final DefaultCreateLinkCommand command = new DefaultCreateLinkCommand(context);
        
        command.setSource((IGmLinkable) getHost().getModel());
        // Store command in the request so that it can be used (and in most cases completed) later by the target node.
        req.setStartCommand(command);
        return command;
    }

    @objid ("7fe9e87d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected ConnectionRouter getDummyConnectionRouter(final CreateConnectionRequest request) {
        return new BendpointConnectionRouter();
    }

    @objid ("7fe9e888-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getReconnectSourceCommand(ReconnectRequest req) {
        final NodeEditPart newSourceNodeEditPart = (NodeEditPart) req.getTarget();
        final GmModel newSourceNodeModel = (GmModel) newSourceNodeEditPart.getModel();
        final ConnectionEditPart connectionEditPart = req.getConnectionEditPart();
        
        if (!(connectionEditPart.getModel() instanceof GmLink)) {
            return null;
        }
        
        final GmLink linkModel = (GmLink) connectionEditPart.getModel();
        
        if (newSourceNodeModel != linkModel.getFrom()) {
            // The source node changes, check the new source is allowed.
            final MObject newSrcElement = newSourceNodeModel.getRelatedElement();
            final MObject targetElement = linkModel.getTo().getRelatedElement();
            final MObject linkElement = linkModel.getRelatedElement();
        
            // Ask the MM expert
            if (!canLink(newSrcElement, targetElement, linkElement)) {
                return null;
            }
        }
        
        final DefaultReconnectSourceCommand cmd = new DefaultReconnectSourceCommand(linkModel,
                (IGmLinkable) newSourceNodeEditPart.getModel());
        
        final ConnectionAnchor srcAnchor = newSourceNodeEditPart.getSourceConnectionAnchor(req);
        cmd.setAnchorModel(ConnectionPolicyUtils.getAnchorModel(newSourceNodeEditPart, srcAnchor));
        return cmd;
    }

    @objid ("7fe9e892-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getReconnectTargetCommand(ReconnectRequest req) {
        final NodeEditPart destEditPart = (NodeEditPart) req.getTarget();
        final IGmLinkable newTargetNode = (IGmLinkable) destEditPart.getModel();
        
        final ConnectionEditPart connectionEditPart = req.getConnectionEditPart();
        
        if (!(connectionEditPart.getModel() instanceof GmLink)) {
            return null;
        }
        
        final GmLink gmLink = (GmLink) connectionEditPart.getModel();
        
        if (newTargetNode != gmLink.getTo()) {
            if (!canLink(gmLink.getFrom().getRelatedElement(), newTargetNode.getRelatedElement(), gmLink.getRelatedElement())) {
                return null;
            }
        }
        
        // build the command
        DefaultReconnectTargetCommand cmd = new DefaultReconnectTargetCommand(gmLink, newTargetNode);
        ConnectionAnchor targetAnchor = destEditPart.getTargetConnectionAnchor(req);
        cmd.setAnchorModel(ConnectionPolicyUtils.getAnchorModel(destEditPart, targetAnchor));
        return cmd;
    }

    @objid ("7fec4a99-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void showCreationFeedback(CreateConnectionRequest request) {
        final ModelioLinkCreationContext context = ModelioLinkCreationContext.lookRequest(request);
        
        // Only handle link model element creation requests.
        if (context == null) {
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
            IConnectionHelper connHelper = getUpdatedConnectionHelper(req, this.connectionFeedback);
        
            @SuppressWarnings ("unchecked")
            List<Point> constraint = (List<Point>) connHelper.getRoutingConstraint();
            this.connectionFeedback.setRoutingConstraint(constraint);
        
            // Debug code that display intermediate points
            if (false) {
                int a = 10;
                for (IFigure f : new ArrayList<IFigure>(this.connectionFeedback.getChildren())) {
                    this.connectionFeedback.remove(f);
                }
        
                for (Point cp : constraint) {
                    final Figure r = new RectangleFigure();
                    r.setBackgroundColor(org.eclipse.draw2d.ColorConstants.red);
                    r.setSize(2 * a, 2 * a);
                    Point p = new Point(cp).translate(-a, -a);
                    r.setLocation(p);
                    this.connectionFeedback.add(r);
                }
        
                for (int i = 0; i < this.connectionFeedback.getPoints().size(); i++) {
                    final Figure r = new RectangleFigure();
                    r.setBackgroundColor(org.eclipse.draw2d.ColorConstants.blue);
                    r.setSize(a, a);
                    Point p = new Point(this.connectionFeedback.getPoints().getPoint(i).translate(-a / 2, -a / 2));
                    r.setLocation(p);
                    this.connectionFeedback.add(r);
                }
            }
        
        } else {
            super.showCreationFeedback(request);
        }
    }

    @objid ("7fec4a9f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void showTargetConnectionFeedback(DropRequest request) {
        // Additional feedback: highlight the node.
        // compute highlight type
        final Command c = getHost().getCommand((Request) request);
        FigureUtilities2.HighlightType hightlightType = FigureUtilities2.HighlightType.INFO;
        if (c == null) {
            hightlightType = FigureUtilities2.HighlightType.ERROR;
        } else if (c.canExecute()) {
            hightlightType = FigureUtilities2.HighlightType.SUCCESS;
        } else {
            hightlightType = FigureUtilities2.HighlightType.WARNING;
        }
        
        // create a highlight figure if it does not exist
        if (this.highlight == null) {
            // create a highlight figure
            this.highlight = FigureUtilities2.createHighlightFigure(getFeedbackLayer(), getHostFigure(), hightlightType);
            // add the highlight figure to the feedback layer
            getFeedbackLayer().add(this.highlight);
        } else {
            // configure the highlight figure
            FigureUtilities2.updateHighlightType(this.highlight, hightlightType);
        }
    }

    /**
     * Tells whether the metamodel experts allow to connect the given link to the 2 given nodes.
     * <p>
     * Asks the metamodel expert then the stereotypes experts.
     * 
     * @param newSrcElement the source element
     * @param targetElement the target element
     * @param linkElement the link
     */
    @objid ("7fec4aa5-1dec-11e2-8cad-001ec947c8cc")
    private static boolean canLink(final MObject newSrcElement, final MObject targetElement, final MObject linkElement) {
        MExpert expert = newSrcElement.getMClass().getMetamodel().getMExpert();
        return expert.canLink(linkElement.getMClass(), newSrcElement, targetElement);
    }

    /**
     * Returns the host if the given request can be handled, <code>null</code> otherwise.
     * 
     * @param request a Source Reconnect request.
     * @return the host edit part or <code>null</code>.
     */
    @objid ("7fec4abc-1dec-11e2-8cad-001ec947c8cc")
    private EditPart getReconnectSourceTargetEditPart(final ReconnectRequest request) {
        ConnectionEditPart reconnectedConnectionEP = request.getConnectionEditPart();
        
        if (!(reconnectedConnectionEP.getModel() instanceof GmLink)) {
            return null;
        }
        
        final GmLink gmLink = (GmLink) reconnectedConnectionEP.getModel();
        final IGmLinkable newSrcNode = (IGmLinkable) getHost().getModel();
        final IGmLinkable oldSrcNode = gmLink.getFrom();
        
        // No check needed if the source node is unchanged
        if (oldSrcNode == newSrcNode) {
            return getHost();
        }
        
        // Avoid creating links cycles
        if (isLinkCycle(getHost(), reconnectedConnectionEP)) {
            return null;
        }
        
        final MObject newSrcElement = newSrcNode.getRelatedElement();
        
        final MObject targetElement = gmLink.getTo().getRelatedElement();
        final MObject linkElement = gmLink.getRelatedElement();
        
        // The source and destination model element must exist
        if (newSrcElement == null || targetElement == null || linkElement == null) {
            return null;
        }
        
        // Ask the MM expert
        if (this.isOpaque || canLink(newSrcElement, targetElement, linkElement)) {
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
    @objid ("7fec4ac6-1dec-11e2-8cad-001ec947c8cc")
    private EditPart getReconnectTargetTargetEditPart(final ReconnectRequest request) {
        ConnectionEditPart reconnectedConnectionEP = request.getConnectionEditPart();
        
        if (!(reconnectedConnectionEP.getModel() instanceof GmLink)) {
            return null;
        }
        
        final GmLink gmLink = (GmLink) reconnectedConnectionEP.getModel();
        final IGmLinkable newTargetNode = (IGmLinkable) getHost().getModel();
        final IGmLinkable oldTargetNode = gmLink.getTo();
        
        // No check needed if the source node is unchanged
        if (oldTargetNode == newTargetNode) {
            return getHost();
        }
        
        // Avoid creating links cycles
        if (isLinkCycle(getHost(), reconnectedConnectionEP)) {
            return null;
        }
        
        final MObject newTargetElement = newTargetNode.getRelatedElement();
        
        final MObject srcElement = gmLink.getFrom().getRelatedElement();
        final MObject linkElement = gmLink.getRelatedElement();
        
        // The source and destination model element must exist
        if (newTargetElement == null || srcElement == null || linkElement == null) {
            return null;
        }
        
        // If creation experts allows OR this instance is "opaque" (see javadoc on private attribute isOpaque for details), return
        // host.
        if (this.isOpaque || canLink(srcElement, newTargetElement, linkElement)) {
            return getHost();
        } else {
            return null;
        }
    }

    /**
     * @return the connection routers registry.
     */
    @objid ("7fec4ad0-1dec-11e2-8cad-001ec947c8cc")
    private ConnectionRouterRegistry getRouterRegistry() {
        return (ConnectionRouterRegistry) getHost().getViewer().getProperty(ConnectionRouterRegistry.ID);
    }

    /**
     * Returns the host if the given request can be handled, <code>null</code> otherwise.
     * 
     * @param request a complete Connection creation request.
     * @return the host edit part or <code>null</code>.
     */
    @objid ("7fec4ad5-1dec-11e2-8cad-001ec947c8cc")
    protected EditPart getTargetEditPartConnectionEnd(final CreateConnectionRequest request) {
        final ModelioLinkCreationContext context = ModelioLinkCreationContext.lookRequest(request);
        
        // Only handle link model element creation requests.
        if (context == null) {
            return null;
        }
        
        // If it is an actual creation (and not an unmasking) then the source and target elements must exists and the creation
        // expert must allow (or this instance be opaque).
        if (context.getElementToUnmask() == null) {
            MObject sourceElement = null;
            final MObject targetElement = ((GmModel) getHost().getModel()).getRelatedElement();
            final IGmLinkable sourceNode = (IGmLinkable) request.getSourceEditPart().getModel();
            final MExpert expert = context.getMetaclass().getMetamodel().getMExpert();
            if (sourceNode == null || (sourceElement = sourceNode.getRelatedElement()) == null || (targetElement == null)
                    || (!expert.canLink(context.getMetaclass(), sourceElement, targetElement))
                            && !this.isOpaque) {
                return null;
            }
        }
        // Either this request is actually a request of the unmasking of a link,
        // or the source and target elements involved in the creation could be found
        // and this instance is opaque or the creation expert allowed.
        return getHost();
    }

    /**
     * Returns the host if the given request can be handled, <code>null</code> otherwise.
     * 
     * @param request a starting Connection creation request.
     * @return the host edit part or <code>null</code>.
     */
    @objid ("7fec4ae0-1dec-11e2-8cad-001ec947c8cc")
    protected EditPart getTargetEditPartConnectionStart(final CreateConnectionRequest request) {
        ModelioLinkCreationContext context = (ModelioLinkCreationContext.lookRequest(request));
        
        if (context == null) {
            // Only handle model element creation requests.
            return null;
        }
        
        // If it is an actual creation (and not an unmasking) then the source element must exists and the creation expert must allow
        // (or this instance be opaque).
        if (context.getElementToUnmask() == null) {
            GmModel gmModel = (GmModel) getHost().getModel();
            MObject sourceElement = gmModel.getRelatedElement();
            IMdaExpert mdaExpert = gmModel.getDiagram().getModelManager().getMdaExpert();
            // If source element cannot be found, or if creation expert doesn't allow AND this instance is not "opaque" (see javadoc
            // on private attribute isOpaque for details), return null.
            if (sourceElement == null) {
                return null;
            }
            if (this.isOpaque) {
                return getHost();
            }
        
            if (!mdaExpert.canSource(context.getStereotype(), context.getMetaclass(), sourceElement.getMClass())) {
                return null;
            }
        }
        
        // Either this request is actually a request of the unmasking of a link,
        // or the source element involved in the creation could be found
        // and this instance is opaque or the creation expert allowed.
        return getHost();
    }

    /**
     * Get or create the updated connection helper for the given connection creation request.
     * 
     * @param req a bended connection creation request
     * @return the connection helper.
     */
    @objid ("7feeacf0-1dec-11e2-8cad-001ec947c8cc")
    private static IConnectionHelper getUpdatedConnectionHelper(final CreateBendedConnectionRequest req, final Connection connection) {
        return ConnectionHelperFactory.getUpdatedConnectionHelper(req, connection);
    }

    @objid ("7feeacfc-1dec-11e2-8cad-001ec947c8cc")
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

    /**
     * Redefined to call {@link #installPopupMenuEditPolicies()}.
     * <p>
     * {@inheritDoc}
     */
    @objid ("079779f2-c264-473b-a220-c649b76459f1")
    @Override
    public void activate() {
        super.activate();
        installPopupMenuEditPolicies();
    }

    /**
     * Installs a {@link PopupMenuCreateLinkEditPolicy} and a {@link CreateLinkHandleEditPolicy} to allow creating connections by dragging a "create connection" handle from the source to another node.
     * <p>
     * To be called in {@link #activate()}.
     */
    @objid ("d4080196-289c-43f4-a936-f515eb430921")
    protected void installPopupMenuEditPolicies() {
        EditPart host = getHost();
        
        UserChoiceCreateLinkEditPolicy userChoiceEditPolicy = (UserChoiceCreateLinkEditPolicy) host.getEditPolicy(UserChoiceCreateLinkEditPolicy.class);
        
        if (userChoiceEditPolicy == null) {
            // Set up a default configuration based on a palette action provider for links
            userChoiceEditPolicy = new UserChoiceCreateLinkEditPolicy(new PaletteActionProvider(host, PaletteActionProvider.IS_LINK_TOOL), true);
            host.installEditPolicy(UserChoiceCreateLinkEditPolicy.class, userChoiceEditPolicy);
        }
        
        if (userChoiceEditPolicy.useSmartLinkHandle()) {
            // Look for a selectable parent representing the same element and install drag handle edit policy
            EditPart selectableParent = findSelectableParent(host);
            if (selectableParent != null) {
                selectableParent.installEditPolicy(CreateLinkHandleEditPolicy.ROLE, new CreateLinkHandleEditPolicy((GraphicalEditPart) getHost()));
            }
        }
    }

    /**
     * Test whether the diagram where the link must be created is editable by the user.
     * 
     * @param r the connection creation request
     * @return whether the diagram is editable by the user.
     */
    @objid ("9afae5f6-e691-4a5e-b880-719f510ea853")
    protected static boolean isNewLinkDiagramEditable(CreateConnectionRequest r) {
        IGmDiagram srcDiag = getGmDiagram(r.getSourceEditPart());
        IGmDiagram targetDiag = getGmDiagram(r.getTargetEditPart());
        IGmDiagram newLinkDiagram = IGmDiagram.getCommonDiagramOwner(srcDiag, targetDiag);
        return newLinkDiagram.isUserEditable();
    }

    @objid ("6a40a271-243d-45eb-b53c-a826530b1b39")
    private static final IGmDiagram getGmDiagram(EditPart ep) {
        return ((IGmObject) ep.getModel()).getDiagram();
    }

    @objid ("17fbb17f-b0d2-4cba-8830-3a3cb1a05ee5")
    @Override
    public void deactivate() {
        removeSmartLinkHandlePolicy();
        
        super.deactivate();
    }

    /**
     * Remove the smart link handle put on the selectable parent edit part.
     */
    @objid ("ea8fee6f-aec0-4338-80d3-1422d4916de1")
    protected void removeSmartLinkHandlePolicy() {
        EditPart host = getHost();
        
        UserChoiceCreateLinkEditPolicy userChoiceEditPolicy = (UserChoiceCreateLinkEditPolicy) host.getEditPolicy(UserChoiceCreateLinkEditPolicy.class);
        if (userChoiceEditPolicy != null && userChoiceEditPolicy.useSmartLinkHandle()) {
            // Look for a selectable parent and install drag handle edit policy
            EditPart selectableParent = findSelectableParent(host);
            if (selectableParent != null) {
                selectableParent.removeEditPolicy(CreateLinkHandleEditPolicy.ROLE);
            }
        }
    }

    @objid ("b3cc597f-217d-4cf2-a119-04afc9f0b040")
    protected EditPart findSelectableParent(EditPart host) {
        EditPart selectableParent = host;
        MRef ref = ((IGmModelRelated) host.getModel()).getRepresentedRef();
        
        while (selectableParent != null && !selectableParent.isSelectable()) {
            MRef parentRef = ((IGmModelRelated) selectableParent.getModel()).getRepresentedRef();
            if (parentRef.equals(ref)) {
                selectableParent = selectableParent.getParent();
            } else {
                selectableParent = null;
            } 
        }
        return selectableParent;
    }

}
