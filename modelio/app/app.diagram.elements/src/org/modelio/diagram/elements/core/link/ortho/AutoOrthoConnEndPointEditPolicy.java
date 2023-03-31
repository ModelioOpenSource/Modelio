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
    @objid ("2583bfa7-7602-4a4b-b492-a50806ba0110")
    private OperationState operationState;

    @objid ("9f177739-a096-4fac-ac15-530201a7412b")
    @Override
    protected FeedbackHelper getFeedbackHelper(ReconnectRequest request) {
        return getOperationStateLong().getFeedbackHelper(request);
    }

    @objid ("001b8942-9e66-451c-9e9e-22211dadf078")
    protected final OperationState getOperationStateLong() {
        if (this.operationState == null) {
            ConnectionEditPart connHost = (ConnectionEditPart) getHost();
            this.operationState = new OperationState(connHost).reference();
        }
        return this.operationState;
    }

    @objid ("e72cfaac-2c2f-439b-b52b-1cc24e627d1b")
    protected final OperationState getOperationStateShort() {
        if (this.operationState == null) {
            this.operationState = createOperationState();
        }
        return this.operationState.reference();
    }

    @objid ("3e534760-90a4-4e52-aac5-90e391b3e9bf")
    protected final void derefOperationState() {
        if (this.operationState != null && ! this.operationState.dereference()) {
            this.operationState.dispose();
            this.operationState = null;
        }
        
    }

    @objid ("33e92f7f-9d48-4835-a672-8ad3424e4701")
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
        @objid ("9231e93b-f972-4025-993b-1696aff9f2ad")
        private final ILinkPathEditorFactory linkPathEditor;

        @objid ("d2df7550-2b47-477a-b37d-7cdd1dc1edbd")
        private final ConnectionEditPart editPart;

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

    @objid ("383dbbfb-ffd6-4eec-8382-e39b4e0e4877")
    protected static class OperationState {
        @objid ("d5f3d476-7100-47af-825b-2a9405bca348")
        private int refCount;

        @objid ("247307bd-8fbe-4394-a43c-bbc79704ef06")
        private OrthoFeedbackHelper feedbackHelper;

        @objid ("2a76183f-96b0-46dd-8d29-12695f80a228")
        private final ConnectionEditPart host;

        @objid ("2cf89dd4-51f4-4ad3-a6b2-56279cbdde99")
        private final ILinkPathEditorFactory linkPathEditor;

        @objid ("887b02d9-456f-45b2-8fc7-a57f608f0025")
        private final ConnectionState origPath;

        @objid ("3f687fa5-7f1b-479b-94fe-b59220669a58")
        public  OperationState(ConnectionEditPart host) {
            this.host = host;
            this.linkPathEditor = ConnectionPolicyUtils.getRoutingServices(host).getLinkPathEditor(ConnectionRouterId.ORTHOGONAL);
            this.origPath = this.linkPathEditor.from(host).backupConnection();
            
        }

        @objid ("21e78e62-ba86-4564-b007-a25c1e802f2a")
        public final OperationState reference() {
            this.refCount++;
            return this;
        }

        @objid ("8d99a955-88be-4d66-9c2b-f399e458ffde")
        public final boolean dereference() {
            this.refCount--;
            return this.refCount > 0;
        }

        @objid ("b65700cb-7b67-4df4-819d-81efda8ab24a")
        public void dispose() {
            if (this.feedbackHelper != null)
                this.feedbackHelper.dispose();
            
        }

        @objid ("4ab07f5c-9605-4c0d-8ad7-d2f124d41875")
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

        @objid ("b43dc7b0-73ea-40a7-ad56-042c3aeea8c1")
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
