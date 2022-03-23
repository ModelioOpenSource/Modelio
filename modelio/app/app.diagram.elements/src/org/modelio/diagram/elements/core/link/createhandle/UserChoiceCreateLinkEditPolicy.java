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
package org.modelio.diagram.elements.core.link.createhandle;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
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
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.figures.FigureUtilities2;
import org.modelio.diagram.elements.core.figures.RotatedPinFigureHelper;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.link.path.ConnectionPolicyUtils;
import org.modelio.diagram.elements.core.link.path.IConnectionHelper;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.diagram.elements.plugin.DiagramElements;

/**
 * Connection creation edit policy that scans the diagram palette for all connection tools and propose valid ones in a popup menu when the command is executed.
 * <p>
 * This edit policy may be installed on edit parts already allowing connections.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("69675c19-ecac-441a-90ad-c956ee3d3a1d")
public class UserChoiceCreateLinkEditPolicy extends GraphicalNodeEditPolicy {
    @objid ("208971a4-c817-47c5-872e-5cc3c70b5f2e")
    private boolean useSmartLinkHandle;

    @objid ("2760976d-52aa-4e35-862b-b116e79d5f9f")
    private static final int ARROW_SIZE = 9;

    @objid ("763ce685-38e5-42d5-8efd-679be6a413e9")
    private IFigure highlight;

    @objid ("0c1953b9-9154-4e5f-ba4b-2769078c648c")
    private XYAnchor dummyAnchor = new XYAnchor(new Point(10, 10));

    @objid ("318aafff-0a93-4b41-bd88-facde051c8d1")
    private ICreationActionProvider actionProvider;

    @objid ("76d355ac-db20-4709-8ab8-ab460b60f906")
    public  UserChoiceCreateLinkEditPolicy(ICreationActionProvider actionProvider, boolean useSmartLinkHandle) {
        if (actionProvider == null) {
            throw new IllegalArgumentException("Action provider cannot be null");
        }
        
        this.actionProvider = actionProvider;
        this.useSmartLinkHandle = useSmartLinkHandle;
        
    }

    @objid ("fccf568d-1f95-4fd4-8948-32526e26d555")
    @Override
    public void activate() {
        super.activate();
    }

    @objid ("bcc450b4-b6b6-4abe-a708-c68ed18bfbcd")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (isSupportedCreateLinkRequest(request)) {
            return getHost();
        }
        return null; // super.getTargetEditPart(request);
    }

    @objid ("703226b4-63fe-47c5-b4b8-22e2616edf2f")
    @Override
    public Command getCommand(Request request) {
        if (isSupportedCreateLinkRequest(request)) {
        
            CreateConnectionRequest createReq = (CreateConnectionRequest) request;
        
            if (RequestConstants.REQ_CONNECTION_START.equals(createReq.getType())) {
                UserChoiceCreationCommand cmd = new UserChoiceCreationCommand(getHost().getViewer(), getActionProvider());
                createReq.setStartCommand(cmd);
                return cmd;
        
            } else if (RequestConstants.REQ_CONNECTION_END.equals(createReq.getType())) {
                ICreationActionDescriptor a = getRequestAction(createReq);
        
                /*
                 * Optional<Action> anyAction = getPaletteActions(getHost(), createReq) .filter(action -> action.finishCommand != null && action.finishCommand.canExecute()) .findAny();
                 */
                if (a != null && a.getCommand() != null && a.getCommand().canExecute()) {
                    UserChoiceCreationCommand cmd = (UserChoiceCreationCommand) createReq.getStartCommand();
                    cmd.update(createReq);
                    // request.getExtendedData().put(Action.class, a);
                    return cmd;
                }
            }
        
            return null;
        } else {
            return super.getCommand(request);
        }
        
    }

    @objid ("192d1583-b95c-4506-8fad-199c41116587")
    @Override
    public void showSourceFeedback(Request request) {
        if (!isSupportedCreateLinkRequest(request)) {
            // do nothing, DefaultCreateLinkEditPolicy should handle it
            return;
        }
        
        if (CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT.equals(request.getType())) {
            showCreationFeedback((CreateConnectionRequest) request);
        
        } else {
            ICreationActionDescriptor a = getRequestAction(request);
            if (a != null) {
                showCreationFeedback((CreateConnectionRequest) request);
            }
        }
        
    }

    @objid ("080dfbfb-e944-46f5-a87b-7c08a587ec61")
    @Override
    public void eraseSourceFeedback(Request request) {
        if (!isSupportedCreateLinkRequest(request)) {
            // do nothing, DefaultCreateLinkEditPolicy should handle it
            return;
        }
        
        if (CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT.equals(request.getType())) {
            eraseCreationFeedback((CreateConnectionRequest) request);
        } else {
            ICreationActionDescriptor a = getRequestAction(request);
            if (a != null) {
                eraseCreationFeedback((CreateConnectionRequest) request);
            }
        }
        
    }

    @objid ("145a9243-5b33-432f-a856-10ad64c82b67")
    @Override
    public void showTargetFeedback(Request request) {
        if (!isSupportedCreateLinkRequest(request)) {
            // do nothing, DefaultCreateLinkEditPolicy should handle it
            return;
        }
        
        if (CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT.equals(request.getType())) {
            showTargetConnectionFeedback((DropRequest) request);
        } else {
            ICreationActionDescriptor a = getRequestAction(request);
            if (a != null) {
                super.showTargetFeedback(request);
            }
        }
        
    }

    @objid ("b450b4f5-eaa0-4b4d-9746-a4b7b72a96d1")
    @Override
    public void eraseTargetFeedback(Request request) {
        if (!isSupportedCreateLinkRequest(request)) {
            // do nothing, DefaultCreateLinkEditPolicy should handle it
            return;
        }
        
        if (CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT.equals(request.getType())) {
            eraseTargetConnectionFeedback((DropRequest) request);
        } else {
            ICreationActionDescriptor a = getRequestAction(request);
            if (a != null) {
                super.eraseTargetFeedback(request);
            }
        }
        
    }

    @objid ("ceae8a4b-396f-4f83-910e-4083c3bef5a0")
    public ICreationActionProvider getActionProvider() {
        return this.actionProvider;
    }

    @objid ("fea75c0f-f13b-4b7b-b136-72cec86c8c6e")
    @Override
    public boolean understandsRequest(Request req) {
        return isSupportedCreateLinkRequest(req) ? true : super.understandsRequest(req);
    }

    @objid ("b4d4e5e6-eb1d-4c90-831d-85f6655151fc")
    public boolean useSmartLinkHandle() {
        return this.useSmartLinkHandle;
    }

    @objid ("330fa3cd-295f-47b0-bdf3-0f58b495530b")
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

    @objid ("0389d3d7-d8ca-49e5-897d-ca0a761241cd")
    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        return null;
    }

    @objid ("01c87c01-8372-48a5-9ee6-b56b89abaeb1")
    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        return null;
    }

    @objid ("648da86e-b622-4e85-bafe-e001995aae18")
    @Override
    protected Command getReconnectSourceCommand(ReconnectRequest request) {
        return null;
    }

    @objid ("959f211e-9af3-488d-b442-9999ed655e42")
    @Override
    protected Command getReconnectTargetCommand(ReconnectRequest request) {
        return null;
    }

    @objid ("0aa4057d-f329-42bf-a0e8-402e596ec97d")
    protected ICreationActionDescriptor getRequestAction(Request request) {
        final CreateConnectionRequest req = (CreateConnectionRequest) request;
        
        ICreationActionDescriptor a = (ICreationActionDescriptor) request.getExtendedData().get(ICreationActionDescriptor.class);
        
        if (a == null) {
            // No action yet, put one
            a = getDefaultAction(req);
            request.getExtendedData().put(ICreationActionDescriptor.class, a);
        
            if (a != null && a.getRequest() instanceof CreateBendedConnectionRequest) {
                CreateBendedConnectionRequest createBendedConnectionRequest = (CreateBendedConnectionRequest) a.getRequest();
                createBendedConnectionRequest.setTargetEditPart(req.getTargetEditPart());
            }
        } else if (a.getRequest() instanceof CreateBendedConnectionRequest) {
            CreateBendedConnectionRequest createBendedConnectionRequest = (CreateBendedConnectionRequest) a.getRequest();
            if (createBendedConnectionRequest.getTargetEditPart() != req.getTargetEditPart() || createBendedConnectionRequest.getType() != request.getType()) {
                // Target edit part changed, replace existing action
                a = getDefaultAction(req);
                request.getExtendedData().put(ICreationActionDescriptor.class, a);
        
                createBendedConnectionRequest.setTargetEditPart(req.getTargetEditPart());
            } else {
                // Update cached request from last data
                a.getRequest().getLocation().setLocation(req.getLocation());
        
                createBendedConnectionRequest.getData().getLastPoint().setLocation(req.getLocation());
            }
        } else {
            DiagramElements.LOG.debug("Invalid UserChoice: node tool encountered in the link policy...");
            a = null;
        }
        return a;
    }

    @objid ("e30e67ea-6f04-4f2e-a53f-1b4bee9509a4")
    @Override
    protected void showCreationFeedback(CreateConnectionRequest request) {
        if (request instanceof CreateBendedConnectionRequest) {
            final CreateBendedConnectionRequest req = (CreateBendedConnectionRequest) request;
        
            // Call the method to force creation of the connection feedback
            getFeedbackHelper(request);
        
            // Set/update the router
            final ConnectionRouter router = ConnectionPolicyUtils.getRoutingServices(getHost()).getCreationRouter(req.getData().getRoutingMode());
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
        
            List<Point> constraint = (List<Point>) connHelper.getRoutingConstraint();
            this.connectionFeedback.setRoutingConstraint(constraint);
        
            // Debug code that display intermediate points
            showIntermediatePoints(constraint);
        
        } else {
            super.showCreationFeedback(request);
        }
        
    }

    /**
     * display intermediate points during link creation
     * @param constraint the current routing constraint
     */
    @objid ("d8f884ca-8e3b-4875-80e2-45116cbf2bda")
    protected void showIntermediatePoints(List<Point> constraint) {
        for (IFigure f : new ArrayList<IFigure>(this.connectionFeedback.getChildren())) {
            this.connectionFeedback.remove(f);
        }
        
        if (constraint == null) {
            return;
        }
        
        for (int i = 0; i < constraint.size(); i++) {
            Point cp = constraint.get(i);
            if (!(cp instanceof MPoint) || ((MPoint) cp).isFixed()) {
                RotatedPinFigureHelper.RotatedPinFigure pinFig = new RotatedPinFigureHelper.RotatedPinFigure(this.connectionFeedback, i+1);
                this.connectionFeedback.add(pinFig, pinFig.getLocator());
            }
        }
        
        if (false) {
            final int rect_size = 10;
            // Debug code that display intermediate points
            for (int i = 0; i < constraint.size(); i++) {
                Point cp = constraint.get(i);
                if (!(cp instanceof MPoint) || ((MPoint) cp).isFixed()) {
                        final Figure r = new RectangleFigure();
                        r.setBackgroundColor(org.eclipse.draw2d.ColorConstants.red);
                        r.setSize(2 * rect_size, 2 * rect_size);
                        Point p = new Point(cp).translate(-rect_size, -rect_size);
                        r.setLocation(p);
                        this.connectionFeedback.add(r);
                }
            }
        
        
            for (int i = 0; i < this.connectionFeedback.getPoints().size(); i++) {
                final Figure r = new RectangleFigure();
                r.setBackgroundColor(org.eclipse.draw2d.ColorConstants.blue);
                r.setSize(rect_size, rect_size);
                Point p = new Point(this.connectionFeedback.getPoints().getPoint(i).translate(-rect_size / 2, -rect_size / 2));
                r.setLocation(p);
                this.connectionFeedback.add(r);
            }
        }
        
    }

    @objid ("07b614bc-5e1d-46b6-88ab-c8c3c0fa9958")
    @Override
    protected void showTargetConnectionFeedback(DropRequest request) {
        // Additional feedback: highlight the node.
        // compute highlight type
        final Command c = getHost().getCommand((Request) request);
        FigureUtilities2.HighlightType hightlightType ;
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
     * Get the first executable action for the creation request.
     */
    @objid ("e293bf28-8ac6-41a2-a83f-7f0abc563e64")
    private ICreationActionDescriptor getDefaultAction(final CreateConnectionRequest req) {
        final UserChoiceCreationCommand startCommand = (UserChoiceCreationCommand) req.getStartCommand();
        if (startCommand != null) {
            ICreationActionDescriptor[] any = new ICreationActionDescriptor[] { null };
            return startCommand.getActionProvider().getPaletteActions(req)
                    .peek((o) -> any[0] = o)
                    .filter(action -> action.getCommand() != null && action.getCommand().canExecute())
                    .findAny()
                    .orElse(any[0]);
        } else {
            return null;
        }
        
    }

    /**
     * Get or create the updated connection helper for the given connection creation request.
     * @param req a bended connection creation request
     * @return the connection helper.
     */
    @objid ("3eb4763a-1a86-40fb-855e-18d3c4bcc3ab")
    private IConnectionHelper getUpdatedConnectionHelper(final CreateBendedConnectionRequest req, final Connection connection) {
        return ConnectionPolicyUtils.getRoutingServices(getHost()).getConnectionHelperFactory().getUpdatedConnectionHelper(req, connection);
    }

    @objid ("f9946cc3-8dd6-4230-82b7-b365b82bb1e4")
    private boolean isSupportedCreateLinkRequest(Request request) {
        return request instanceof CreateConnectionRequest
                && ((CreateConnectionRequest) request).getNewObjectType() == UserChoiceLinkCreationFactory.class;
        
    }

    @objid ("0a0e9c05-3b25-4e41-8b13-7f5b857ecad2")
    @Override
    protected Connection createDummyConnection(Request req) {
        final PolylineConnection ret = new PolylineConnection();
        ret.removeAllPoints();
        
        // Add an arrow
        final PolylineDecoration arrow = new PolylineDecoration();
        arrow.setTemplate(PolylineDecoration.TRIANGLE_TIP);
        arrow.setScale(ARROW_SIZE, ARROW_SIZE / 2.0 );
        arrow.setOpaque(false);
        arrow.setBackgroundColor(null);
        arrow.setFill(false);
        
        ret.setTargetDecoration(arrow);
        return ret;
    }

}
