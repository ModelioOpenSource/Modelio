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
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.commands.DefaultAddBendPointCommand;
import org.modelio.diagram.elements.core.commands.DefaultDeleteBendPointCommand;
import org.modelio.diagram.elements.core.commands.DefaultMoveBendPointCommand;
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
    @objid ("67f57217-1e83-11e2-8cad-001ec947c8cc")
    private ConnectionAnchor originalSourceAnchor;

    @objid ("67f57218-1e83-11e2-8cad-001ec947c8cc")
    private ConnectionAnchor originalTargetAnchor;

    @objid ("67f57219-1e83-11e2-8cad-001ec947c8cc")
    private List<Bendpoint> originalConstraint;

    @objid ("67f5721c-1e83-11e2-8cad-001ec947c8cc")
    private static final List<Bendpoint> NULL_CONSTRAINT = new ArrayList<>();

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
        ConnectionAnchor currentSourceAnchor = getConnection().getSourceAnchor();
        ConnectionAnchor currentTargetAnchor = getConnection().getTargetAnchor();
        getConnection().setSourceAnchor(this.originalSourceAnchor);
        getConnection().setTargetAnchor(this.originalTargetAnchor);
        
        ConnectionEditPart hostEP = (ConnectionEditPart) getHost();
        Command command = new TranslateBendpointsCommand( hostEP);
        
        getConnection().setSourceAnchor(currentSourceAnchor);
        getConnection().setTargetAnchor(currentTargetAnchor);
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
        this.originalConstraint = null;
        this.originalSourceAnchor = null;
        this.originalTargetAnchor = null;
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
    @Override
    protected void restoreOriginalConstraint() {
        if (this.originalConstraint != null) {
            if (this.originalConstraint == DefaultBendpointEditPolicy.NULL_CONSTRAINT) {
                getConnection().setRoutingConstraint(null);
            } else {
                getConnection().setRoutingConstraint(this.originalConstraint);
            }
        }
        getConnection().setSourceAnchor(this.originalSourceAnchor);
        getConnection().setTargetAnchor(this.originalTargetAnchor);
        super.restoreOriginalConstraint();
    }

    @objid ("80b6f2c8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    @SuppressWarnings ("unchecked")
    protected void saveOriginalConstraint() {
        super.saveOriginalConstraint();
        this.originalConstraint = (List<Bendpoint>) getConnection().getRoutingConstraint();
        if (this.originalConstraint == null) {
            this.originalConstraint = DefaultBendpointEditPolicy.NULL_CONSTRAINT;
        }
        getConnection().setRoutingConstraint(new ArrayList<>(this.originalConstraint));
        this.originalSourceAnchor = getConnection().getSourceAnchor();
        this.originalTargetAnchor = getConnection().getTargetAnchor();
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

    @objid ("80b6f2d2-1dec-11e2-8cad-001ec947c8cc")
    protected void showChangeBoundsFeedback(final ChangeBoundsRequest request) {
        // Before modifying the connection, save its original constraint and anchors so as to be able to cancel if needed.
        if (this.originalConstraint == null) {
            saveOriginalConstraint();
        }
        Connection connection = getConnection();
        List<Bendpoint> newConstraint = new ArrayList<>();
        for (Bendpoint bendpoint : this.originalConstraint) {
            Point location = Point.SINGLETON;
            location.setLocation(bendpoint.getLocation());
            connection.translateToAbsolute(location);
            location.translate(request.getMoveDelta());
            connection.translateToRelative(location);
            newConstraint.add(new AbsoluteBendpoint(location));
        }
        getConnection().setSourceAnchor(new XYAnchor(this.originalSourceAnchor.getReferencePoint()
                .getTranslated(request.getMoveDelta())));
        getConnection().setTargetAnchor(new XYAnchor(this.originalTargetAnchor.getReferencePoint()
                .getTranslated(request.getMoveDelta())));
        
        getConnection().setRoutingConstraint(newConstraint);
    }

    @objid ("80b954d4-1dec-11e2-8cad-001ec947c8cc")
    protected void eraseChangeBoundsFeedback(final ChangeBoundsRequest request) {
        restoreOriginalConstraint();
        this.originalConstraint = null;
        this.originalSourceAnchor = null;
        this.originalTargetAnchor = null;
    }

    @objid ("72de9a17-6672-4d84-bf53-4637558588fd")
    @SuppressWarnings ("unchecked")
    @Override
    protected List<?> createSelectionHandles() {
        return SelectionHandlesBuilder.disableHandlesIfReadOnly(getHost(), super.createSelectionHandles());
    }

}
