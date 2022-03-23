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
package org.modelio.diagram.elements.core.policies;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.ToolUtilities;
import org.modelio.diagram.elements.core.commands.DefaultAddBendPointCommand;
import org.modelio.diagram.elements.core.commands.DefaultDeleteBendPointCommand;
import org.modelio.diagram.elements.core.commands.DefaultMoveBendPointCommand;
import org.modelio.diagram.elements.core.figures.routers.ConnectionState;
import org.modelio.diagram.elements.core.link.ortho.TranslateBendpointsCommand;
import org.modelio.diagram.elements.core.model.IGmLinkObject;

/**
 * Default bendpoint edit policy.
 * <p>
 * Used to add, move and delete bendpoints on a connection with the bendpoint router.
 * 
 * @author cmarin
 */
@objid ("80b4905e-1dec-11e2-8cad-001ec947c8cc")
public class DefaultBendpointEditPolicy extends BendpointEditPolicy {
    @objid ("67f57219-1e83-11e2-8cad-001ec947c8cc")
    private ConnectionState changeBoundsOrigState;

    @objid ("80b6f27d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getCreateBendpointCommand(BendpointRequest request) {
        int index = request.getIndex();
        final Point point = request.getLocation().getCopy();
        getConnection().translateToRelative(point);
        AbsoluteBendpoint newpoint = new AbsoluteBendpoint(point);
        return new DefaultAddBendPointCommand((IGmLinkObject) request.getSource().getModel(), index, newpoint);
    }

    @objid ("80b6f287-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getDeleteBendpointCommand(BendpointRequest request) {
        int index = request.getIndex();
        // AbsoluteBendpoint newpoint = new AbsoluteBendpoint(request.getLocation());
        return new DefaultDeleteBendPointCommand((IGmLinkObject) request.getSource().getModel(), index);
    }

    @objid ("80b6f291-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getMoveBendpointCommand(BendpointRequest request) {
        int index = request.getIndex();
        final Point point = request.getLocation().getCopy();
        getConnection().translateToRelative(point);
        AbsoluteBendpoint newpoint = new AbsoluteBendpoint(point);
        return new DefaultMoveBendPointCommand((IGmLinkObject) request.getSource().getModel(), index, newpoint);
    }

    @objid ("80b6f29b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Command getCommand(final Request request) {
        if (RequestConstants.REQ_MOVE.equals(request.getType()) || RequestConstants.REQ_ADD.equals(request.getType())) {
            return getMoveCommand((ChangeBoundsRequest) request);
        } else {
            return super.getCommand(request);
        }
        
    }

    @objid ("80b6f2a5-1dec-11e2-8cad-001ec947c8cc")
    protected Command getMoveCommand(final ChangeBoundsRequest request) {
        Connection connection = getConnection();
        
        // Temporarly restore original anchors to compute the command
        // TODO : seems this stuff is not needed by the command
        ConnectionAnchor currentSourceAnchor = connection.getSourceAnchor();
        ConnectionAnchor currentTargetAnchor = connection.getTargetAnchor();
        if (this.changeBoundsOrigState != null) {
            connection.setSourceAnchor(this.changeBoundsOrigState.getSourceAnchor());
            connection.setTargetAnchor(this.changeBoundsOrigState.getTargetAnchor());
        }
        
        ConnectionEditPart hostEP = getHost();
        Command command = new TranslateBendpointsCommand(hostEP);
        
        // Restore connection to feedback state
        if (this.changeBoundsOrigState != null) {
            connection.setSourceAnchor(currentSourceAnchor);
            connection.setTargetAnchor(currentTargetAnchor);
        }
        return command;
    }

    @objid ("80b6f2af-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean understandsRequest(final Request req) {
        if (RequestConstants.REQ_MOVE.equals(req.getType())) {
            return true;
        } else {
            return super.understandsRequest(req);
        }
        
    }

    @objid ("80b6f2b7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void eraseConnectionFeedback(final BendpointRequest request) {
        super.eraseConnectionFeedback(request);
        this.changeBoundsOrigState = null;
        
    }

    @objid ("80b6f2be-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void eraseSourceFeedback(final Request request) {
        if (RequestConstants.REQ_MOVE.equals(request.getType()) || RequestConstants.REQ_ADD.equals(request.getType())) {
            eraseChangeBoundsFeedback((ChangeBoundsRequest) request);
        } else {
            super.eraseSourceFeedback(request);
        }
        
    }

    @objid ("80b6f2c5-1dec-11e2-8cad-001ec947c8cc")
    protected void restoreMoveOriginalConstraint() {
        if (this.changeBoundsOrigState != null) {
            this.changeBoundsOrigState.applyTo(getConnection());
        }
        
    }

    @objid ("80b6f2c8-1dec-11e2-8cad-001ec947c8cc")
    @SuppressWarnings ("unchecked")
    protected void saveMoveOriginalConstraint() {
        Connection connection = getConnection();
        
        this.changeBoundsOrigState = new ConnectionState().init(connection);
        
        if (this.changeBoundsOrigState.getConstraint() == null) {
            connection.setRoutingConstraint( new ArrayList<>(1));
        } else {
            connection.setRoutingConstraint(new ArrayList<>((List<Bendpoint>)this.changeBoundsOrigState.getConstraint()));
        }
        
    }

    @objid ("80b6f2cb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void showSourceFeedback(final Request request) {
        if (RequestConstants.REQ_MOVE.equals(request.getType()) || RequestConstants.REQ_ADD.equals(request.getType())) {
            showChangeBoundsFeedback((ChangeBoundsRequest) request);
        } else {
            super.showSourceFeedback(request);
        }
        
    }

    @objid ("11f5c08f-e5b7-44b7-863f-b8fb074a85ee")
    @Override
    public ConnectionEditPart getHost() {
        return (ConnectionEditPart) super.getHost();
    }

    @objid ("80b6f2d2-1dec-11e2-8cad-001ec947c8cc")
    protected void showChangeBoundsFeedback(final ChangeBoundsRequest request) {
        // Before modifying the connection, save its original constraint and anchors so as to be able to cancel if needed.
        if (this.changeBoundsOrigState == null) {
            saveMoveOriginalConstraint();
        }
        
        Connection connection = getConnection();
        ConnectionEditPart linkEditPart = getHost();
        Point absMoveDelta = request.getMoveDelta();
        
        // Deal with the source
        Point sourceAnchor = this.changeBoundsOrigState.getSourceAnchor().getReferencePoint();
        GraphicalEditPart linkSource = (GraphicalEditPart) linkEditPart.getSource();
        boolean sourceInSet = linkSource == null || ToolUtilities.isAncestorContainedIn(request.getEditParts(), linkSource);
        if (sourceInSet) {
            sourceAnchor = sourceAnchor.getTranslated(absMoveDelta);
        }
        connection.setSourceAnchor(new XYAnchor(sourceAnchor));
        
        // Deal with the target
        Point targetAnchor = this.changeBoundsOrigState.getTargetAnchor().getReferencePoint();
        GraphicalEditPart linkTarget = (GraphicalEditPart) linkEditPart.getTarget();
        boolean targetInSet = linkTarget == null || ToolUtilities.isAncestorContainedIn(request.getEditParts(), linkTarget);
        if (targetInSet) {
            targetAnchor = targetAnchor.getTranslated(absMoveDelta);
        }
        connection.setTargetAnchor(new XYAnchor(targetAnchor));
        
        if (sourceInSet && targetInSet) {
            // Both source and target are being moved, move the link's points too
            List<Bendpoint> newConstraint = new ArrayList<>();
            for (Bendpoint bendpoint : (List<Bendpoint>) this.changeBoundsOrigState.getConstraint()) {
                AbsoluteBendpoint location = new AbsoluteBendpoint(bendpoint.getLocation());
                connection.translateToAbsolute(location);
                location.translate(absMoveDelta);
                connection.translateToRelative(location);
                newConstraint.add(location);
            }
            connection.setRoutingConstraint(newConstraint);
        }
        
    }

    @objid ("80b954d4-1dec-11e2-8cad-001ec947c8cc")
    protected void eraseChangeBoundsFeedback(final ChangeBoundsRequest request) {
        restoreMoveOriginalConstraint();
        this.changeBoundsOrigState = null;
        
    }

    @objid ("72de9a17-6672-4d84-bf53-4637558588fd")
    @Override
    protected List<?> createSelectionHandles() {
        List<Object> newHandles = super.createSelectionHandles();
        return SelectionHandlesBuilder.disableHandlesIfReadOnly(getHost(), newHandles);
    }

}
