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
package org.modelio.diagram.elements.common.linkednode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.FeedbackHelper;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.model.IGmObject;

/**
 * Edit policy that allow creating notes on an element.
 * 
 * @author cmarin
 */
@objid ("7eaf31d4-1dec-11e2-8cad-001ec947c8cc")
public abstract class AbstractLinkedNodeCreationEditPolicy extends GraphicalEditPolicy implements LinkedNodeRequestConstants {
    /**
     * the current FeedbackHelper
     */
    @objid ("655ea460-1e83-11e2-8cad-001ec947c8cc")
    protected FeedbackHelper feedbackHelper;

    /**
     * The connection feedback displayed during creates
     */
    @objid ("655ea462-1e83-11e2-8cad-001ec947c8cc")
    protected Connection connectionFeedback;

    /**
     * @see org.eclipse.gef.EditPolicy#deactivate()
     */
    @objid ("7eaf31e0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void deactivate() {
        if (this.connectionFeedback != null) {
            removeFeedback(this.connectionFeedback);
            this.feedbackHelper = null;
            this.connectionFeedback = null;
        }
        super.deactivate();
        
    }

    /**
     * Calls {@link #eraseCreationFeedback(CreateConnectionRequest)} when appropriate.
     * @see org.eclipse.gef.EditPolicy#eraseSourceFeedback(Request)
     */
    @objid ("7eaf31e4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void eraseSourceFeedback(Request request) {
        if (REQ_LINKEDNODE_END.equals(request.getType())) {
            eraseCreationFeedback((CreateConnectionRequest) request);
        }
        
    }

    /**
     * Calls {@link #eraseTargetConnectionFeedback(DropRequest)} when appropriate.
     * @see org.eclipse.gef.EditPolicy#eraseTargetFeedback(Request)
     */
    @objid ("7eaf31eb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void eraseTargetFeedback(Request request) {
        if (isHandled(request)) {
            eraseTargetConnectionFeedback((DropRequest) request);
        }
        
    }

    /**
     * Factors the request into one of four abstract methods. Subclasses must implement these methods.
     * @see org.eclipse.gef.EditPolicy#getCommand(Request)
     */
    @objid ("7eb19433-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final Command getCommand(Request request) {
        if (!isUserEditable()) {
            return null;
        }
        
        if (REQ_LINKEDNODE_START.equals(request.getType())) {
            return getConnectionCreateCommand((CreateConnectionRequest) request);
        }
        if (REQ_LINKEDNODE_END.equals(request.getType())) {
            return getConnectionCompleteCommand((CreateConnectionRequest) request);
        }
        if (REQ_RECONNECT_TARGET.equals(request.getType())) {
            return getReconnectTargetCommand((ReconnectRequest) request);
        }
        if (REQ_RECONNECT_SOURCE.equals(request.getType())) {
            return getReconnectSourceCommand((ReconnectRequest) request);
        }
        return null;
    }

    /**
     * calls {@link #showCreationFeedback(CreateConnectionRequest)} when appropriate.
     * @see org.eclipse.gef.EditPolicy#showSourceFeedback(Request)
     */
    @objid ("7eb1943e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void showSourceFeedback(Request request) {
        if (REQ_LINKEDNODE_END.equals(request.getType())) {
            showCreationFeedback((CreateConnectionRequest) request);
        }
        
    }

    /**
     * Calls {@link #showTargetConnectionFeedback(DropRequest)} when appropriate.
     * @see org.eclipse.gef.EditPolicy#showTargetFeedback(Request)
     */
    @objid ("7eb19445-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void showTargetFeedback(Request request) {
        if (isHandled(request)) {
            showTargetConnectionFeedback((DropRequest) request);
        }
        
    }

    /**
     * Returns a connection to be used as feedback during creates.
     * @param req the operation being performed
     * @return a connection to use as feedback
     */
    @objid ("7eb1944c-1dec-11e2-8cad-001ec947c8cc")
    protected Connection createDummyConnection(Request req) {
        PolylineConnection dummy = new PolylineConnection();
        dummy.removeAllPoints();
        return dummy;
    }

    /**
     * Erases connection feedback if necessary. Frees unused fields.
     * @param request the CreateLinkedNodeRequest
     */
    @objid ("7eb19456-1dec-11e2-8cad-001ec947c8cc")
    protected void eraseCreationFeedback(CreateConnectionRequest request) {
        if (this.connectionFeedback != null) {
            removeFeedback(this.connectionFeedback);
            this.feedbackHelper = null;
            this.connectionFeedback = null;
        }
        
    }

    /**
     * Override to erase target feedback. Does nothing by default.
     * @param request the DropRequest
     */
    @objid ("7eb1945c-1dec-11e2-8cad-001ec947c8cc")
    protected void eraseTargetConnectionFeedback(DropRequest request) {
        // Nothing to do
    }

    /**
     * Returns the Command that will create the connection. This is second part of creation. {@link CreateConnectionRequest#getStartCommand()} is used here to obtain the contribution from the EditPart from which the User started the <i>creation</i>.
     * @param request the CreateLinkedNodeRequest
     * @return the complete command to create a connection
     */
    @objid ("7eb19462-1dec-11e2-8cad-001ec947c8cc")
    protected abstract Command getConnectionCompleteCommand(CreateConnectionRequest request);

    /**
     * Returns the Command that represents the first half of creating a connection. This Command will be passed to the target node EditPart. The target node may do anything necessary to create a Command that represents the entire creation.
     * @see #getConnectionCompleteCommand(CreateConnectionRequest)
     * @param request the CreateLinkedNodeRequest
     * @return a Command representing half of a connection creation
     */
    @objid ("7eb1946a-1dec-11e2-8cad-001ec947c8cc")
    protected abstract Command getConnectionCreateCommand(CreateConnectionRequest request);

    /**
     * Returns the ConnectionRouter for the creation feedback's connection.
     * @param request the create request
     * @return a connection router
     * @since 3.2
     */
    @objid ("7eb19472-1dec-11e2-8cad-001ec947c8cc")
    protected ConnectionRouter getDummyConnectionRouter(CreateConnectionRequest request) {
        return ((ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER)).getConnectionRouter();
    }

    /**
     * Returns the FeedbackHelper that is ready to use. The feedback helper must be configured with the connection that will be used to display feedback, and that connection must be added to the appropriate layer in the diagram.
     * @param request the CreateLinkedNodeRequest
     * @return a FeedbackHelper
     */
    @objid ("7eb3f68b-1dec-11e2-8cad-001ec947c8cc")
    protected FeedbackHelper getFeedbackHelper(CreateConnectionRequest request) {
        if (this.feedbackHelper == null) {
            this.feedbackHelper = new FeedbackHelper();
            Point p = request.getLocation();
            this.connectionFeedback = createDummyConnection(request);
            this.connectionFeedback.setConnectionRouter(getDummyConnectionRouter(request));
            this.connectionFeedback.setSourceAnchor(getSourceConnectionAnchor(request));
            this.feedbackHelper.setConnection(this.connectionFeedback);
            addFeedback(this.connectionFeedback);
            this.feedbackHelper.update(null, p);
        }
        return this.feedbackHelper;
    }

    /**
     * Called during the display of creation feedback to snap the feedback to the nearest source ConnectionAnchor.
     * @param request CreateLinkedNodeRequest
     * @return <code>null</code> or the nearest source ConnectionAnchor
     */
    @objid ("7eb3f695-1dec-11e2-8cad-001ec947c8cc")
    protected ConnectionAnchor getSourceConnectionAnchor(CreateConnectionRequest request) {
        EditPart source = request.getSourceEditPart();
        return source instanceof NodeEditPart ? ((NodeEditPart) source).getSourceConnectionAnchor(request)
                : null;
        
    }

    /**
     * Called during the display of creation feedback to snap the feedback to the nearest target ConnectionAnchor.
     * @param request CreateLinkedNodeRequest
     * @return <code>null</code> or the nearest target ConnectionAnchor
     */
    @objid ("7eb3f69f-1dec-11e2-8cad-001ec947c8cc")
    protected ConnectionAnchor getTargetConnectionAnchor(CreateConnectionRequest request) {
        EditPart target = request.getTargetEditPart();
        return target instanceof NodeEditPart ? ((NodeEditPart) target).getTargetConnectionAnchor(request)
                : null;
        
    }

    /**
     * Shows feedback during a creation.
     * @param request CreateLinkedNodeRequest
     */
    @objid ("7eb3f6a9-1dec-11e2-8cad-001ec947c8cc")
    protected void showCreationFeedback(CreateConnectionRequest request) {
        FeedbackHelper helper = getFeedbackHelper(request);
        Point p = new Point(request.getLocation());
        helper.update(getTargetConnectionAnchor(request), p);
        
    }

    /**
     * Override to show target connection feedback. Does nothing by default.
     * @param request the DropRequest
     */
    @objid ("7eb3f6af-1dec-11e2-8cad-001ec947c8cc")
    protected void showTargetConnectionFeedback(DropRequest request) {
        // Nothing to do
    }

    @objid ("7eb3f6b5-1dec-11e2-8cad-001ec947c8cc")
    protected Command getReconnectSourceCommand(@SuppressWarnings ("unused") final ReconnectRequest request) {
        return null;
    }

    @objid ("7eb3f6bf-1dec-11e2-8cad-001ec947c8cc")
    protected Command getReconnectTargetCommand(@SuppressWarnings ("unused") final ReconnectRequest request) {
        return null;
    }

    @objid ("7eb3f6c9-1dec-11e2-8cad-001ec947c8cc")
    protected abstract boolean isHandled(final Request req);

    @objid ("71cfcde2-9a79-4f08-875b-4ffb063f6e1a")
    protected boolean isUserEditable() {
        return ((IGmObject) getHost().getModel()).isUserEditable();
    }

}
