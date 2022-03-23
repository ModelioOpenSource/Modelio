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
package org.modelio.diagram.elements.core.link.ortho;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.FeedbackHelper;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.figures.routers.ConnectionState;
import org.modelio.diagram.elements.core.link.path.ConnectionPolicyUtils;
import org.modelio.diagram.elements.core.link.path.ILinkPathEditor;
import org.modelio.diagram.elements.core.link.path.ILinkPathEditorFactory;
import org.modelio.diagram.elements.core.policies.DefaultConnectionEndpointEditPolicy;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.diagram.elements.core.requests.WrappedRequest;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * {@link DefaultConnectionEndpointEditPolicy} redefinition to change the router to the automatic one during connection end dragging.
 * 
 * @since 5.0.2
 */
@objid ("0b3bd8ed-f3f9-46ed-b4e4-78945d3db436")
public class AutoOrthoConnEndPointEditPolicy extends DefaultConnectionEndpointEditPolicy {
    @objid ("17e6abb5-05b5-48d1-bf21-9a4062c74401")
    private OperationState operationState;

    @objid ("9f177739-a096-4fac-ac15-530201a7412b")
    @Override
    protected FeedbackHelper getFeedbackHelper(ReconnectRequest request) {
        return getOperationStateLong().getFeedbackHelper(request);
    }

    @objid ("9cdfeb9a-06b1-422e-8db2-abf4bef9a5df")
    protected final OperationState getOperationStateLong() {
        if (this.operationState == null) {
            ConnectionEditPart connHost = (ConnectionEditPart) getHost();
            this.operationState = new OperationState(connHost).reference();
        }
        return this.operationState;
    }

    @objid ("52bce869-9569-409f-932d-ff5e62790024")
    protected final OperationState getOperationStateShort() {
        if (this.operationState == null) {
            this.operationState = createOperationState();
        }
        return this.operationState.reference();
    }

    @objid ("d925620d-d446-4095-aee7-a2b23e74ae55")
    protected final void derefOperationState() {
        if (this.operationState != null && ! this.operationState.dereference()) {
            this.operationState.dispose();
            this.operationState = null;
        }
        
    }

    @objid ("b9ff65ef-e566-42f0-b8cc-06dc4afb7d8c")
    protected OperationState createOperationState() {
        ConnectionEditPart connHost = (ConnectionEditPart) getHost();
        return new OperationState(connHost);
    }

    @objid ("7386f9cc-80c4-4644-aea9-a10a6eedc41e")
    @Override
    public Command getCommand(Request request) {
        Object type = request.getType();
        
        if (type.equals(CreateLinkConstants.REQ_CONNECTION_UPDATE_ROUTING_CONSTRAINT)) {
            return getCommand (((WrappedRequest)request).getRequest());
        } else if (type.equals(REQ_RECONNECT_SOURCE) || type.equals(REQ_RECONNECT_TARGET)) {
            // Create command only for self source/target move.
            // The contrary happens when reconnecting connections on connections.
            if (((ReconnectRequest)request).getConnectionEditPart() != getHost())
                return null;
        
            ConnectionState newState = getOperationStateShort().apply((ReconnectRequest) request);
        
            ChangeLinkRoutingConstraintCommand command = new ChangeLinkRoutingConstraintCommand(
                    (ConnectionEditPart) getHost(),
                    newState,
                    true);
        
            derefOperationState();
            return command;
        } else {
            return null;
        }
        
    }

    @objid ("b2142844-21a2-4fbe-943d-2c1c7f80714f")
    @Override
    protected void eraseConnectionMoveFeedback(ReconnectRequest request) {
        derefOperationState();
        
        super.eraseConnectionMoveFeedback(request);
        
    }

    @objid ("e0c83301-1cbb-4405-8b61-d9f4d643ffb9")
    protected static class OrthoFeedbackHelper extends FeedbackHelper {
        @objid ("a1bd9ba3-d23a-457e-ada9-0a85b6f1d85e")
        private final ConnectionEditPart editPart;

        @objid ("9231e93b-f972-4025-993b-1696aff9f2ad")
        private final ILinkPathEditorFactory linkPathEditor;

        @objid ("85151bb4-d84a-4e30-a18d-1d495ef0efd6")
        private final ConnectionState origPath;

        @objid ("f72ebcb9-4363-4387-96c5-d87e8fbbb574")
        public  OrthoFeedbackHelper(ILinkPathEditorFactory linkPathEditor, ConnectionEditPart editPart, ConnectionState origPath) {
            this.linkPathEditor = linkPathEditor;
            this.editPart = editPart;
            this.origPath = origPath;
            
        }

        @objid ("5adfb45c-cd1a-4259-877d-a66ba568a5fc")
        public void dispose() {
            this.origPath.applyTo(getConnection());
        }

        @objid ("eb55cf94-9e7f-4817-8401-89c523d66965")
        @Override
        protected void setAnchor(ConnectionAnchor anchor) {
            // restore connection state before changing anchor
            ILinkPathEditor editor = this.linkPathEditor.from(this.editPart, this.origPath);
            if (isMovingStartAnchor()) {
                editor.setSourceAnchor(anchor).applyStateToConnection();
            } else {
                editor.setTargetAnchor(anchor).applyStateToConnection();
            }
            
        }

    }

    @objid ("fe6f78a9-289c-4f54-aaf3-c19b6523cb8d")
    protected static class OperationState {
        @objid ("cce5c6cc-e809-426d-9955-17e42b282462")
        private int refCount;

        @objid ("1f76dbc7-c49e-400c-9696-44c2effaab7e")
        private final ConnectionEditPart host;

        @objid ("247307bd-8fbe-4394-a43c-bbc79704ef06")
        private OrthoFeedbackHelper feedbackHelper;

        @objid ("36f98012-f7da-429a-a0ea-32969b6ee162")
        private final ILinkPathEditorFactory linkPathEditor;

        @objid ("160f1ad2-3ad2-4725-8313-69c03b77ca44")
        private final ConnectionState origPath;

        @objid ("f3c04076-1ba9-4f68-8ce3-15a8da5c23c0")
        public  OperationState(ConnectionEditPart host) {
            this.host = host;
            this.linkPathEditor = ConnectionPolicyUtils.getRoutingServices(host).getLinkPathEditor(ConnectionRouterId.ORTHOGONAL);
            this.origPath = this.linkPathEditor.from(host).backupConnection();
            
        }

        @objid ("e7242158-d63e-42fa-bf10-78024cbd5a15")
        public final OperationState reference() {
            this.refCount++;
            return this;
        }

        @objid ("ad618407-b83a-46bd-8e2d-e857abe1325f")
        public final boolean dereference() {
            this.refCount--;
            return this.refCount > 0;
        }

        @objid ("f6274f9a-4e9c-4cb2-9dd1-22934f2b175b")
        public void dispose() {
            if (this.feedbackHelper != null)
                this.feedbackHelper.dispose();
            
        }

        @objid ("6c24cf8c-6faa-4895-832b-7d520b566784")
        protected FeedbackHelper getFeedbackHelper(ReconnectRequest request) {
            if (this.feedbackHelper == null) {
                Connection connection = (Connection) this.host.getFigure();
                this.feedbackHelper = new OrthoFeedbackHelper(
                        this.linkPathEditor,
                        this.host, this.origPath);
                this.feedbackHelper.setConnection(connection);
                this.feedbackHelper.setMovingStartAnchor(request.isMovingStartAnchor());
            }
            return this.feedbackHelper;
        }

        @objid ("041cc9ed-40cc-4ff2-8b03-e8d8dea04039")
        public ConnectionState apply(ReconnectRequest request) {
            NodeEditPart node = null;
            if (request.getTarget() instanceof NodeEditPart)
                node  = (NodeEditPart) request.getTarget();
            
            if (node != null) {
                ILinkPathEditor editor = this.linkPathEditor.from(this.host, this.origPath);
                if (request.isMovingStartAnchor()) {
                    ConnectionAnchor anchor = node.getSourceConnectionAnchor(request);
                    editor.setSourceAnchor(anchor);
                    return editor.getState();
                } else {
                    ConnectionAnchor anchor = node.getTargetConnectionAnchor(request);
                    editor.setTargetAnchor(anchor);
                    return editor.getState();
                }
            }
            return null;
        }

    }

}
