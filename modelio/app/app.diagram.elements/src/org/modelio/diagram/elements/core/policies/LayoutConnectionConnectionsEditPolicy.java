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

import java.util.function.Consumer;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;

/**
 * Additional policy to put on editable {@link ConnectionEditPart} that request links connected to
 * the host connection to update their feedback then layout.
 * <p>
 * This policy must be registered <b>after</b> {@link EditPolicy#CONNECTION_BENDPOINTS_ROLE}
 * and {@link EditPolicy#CONNECTION_ENDPOINTS_ROLE}, or a slot for them must have been registered before.
 * @author cma
 * @since 5.1.0
 */
@objid ("0742c6c6-db3b-469d-80de-62311c5d868f")
public class LayoutConnectionConnectionsEditPolicy extends GraphicalEditPolicy {
    /**
     * The role to use for this edit policy
     */
    @objid ("a520c6c5-8846-44c8-8dcb-2f461af8dba3")
    public static final Object ROLE = LayoutConnectionConnectionsEditPolicy.class.getSimpleName();

    @objid ("2f033ba4-d6ce-49c7-9720-a788ef2ea31d")
    private static final Object[] DEPENDENT_POLICIES = new Object[] {EditPolicy.PRIMARY_DRAG_ROLE, EditPolicy.CONNECTION_BENDPOINTS_ROLE, EditPolicy.CONNECTION_ENDPOINTS_ROLE};

    /**
     * Public constructor.
     * <p>
     * Reserve slots for drag policies that must be installed before this one.
     * @param editpart the edit part destined to have this policy.
     */
    @objid ("97dd5cd8-4345-47a4-8986-982f02a428b1")
    public  LayoutConnectionConnectionsEditPolicy(EditPart editpart) {
        super();
        
        if (editpart != null)
            reserveNeededSlots(editpart);
        
    }

    @objid ("36d47020-8cbd-43e2-ad4e-9210d52ca6c0")
    @Override
    public void eraseSourceFeedback(Request request) {
        if (! isHandled(request))
            return;
        
        Request childReq = getAdaptedRequest(request);
        
        forEachConnection(c -> c.eraseSourceFeedback(childReq));
        
    }

    @objid ("1e3f337a-a7f1-42d3-b7d6-4d8a88fcc449")
    @Override
    public Command getCommand(Request request) {
        if (! isHandled(request))
            return null;
        
        CompoundCommand command = new CompoundCommand();
        Request childReq = getAdaptedRequest(request);
        
        
        forEachConnection(c -> {
            Command connCommand = c.getCommand(childReq);
            if (connCommand != null)
                command.add(connCommand);
        });
        return command.isEmpty() ? null : command.unwrap();
    }

    /**
     * Show the source drag feedback for the drag occurring within the viewer.
     */
    @objid ("cd934492-746c-4f62-8291-6b5836077003")
    @Override
    public void showSourceFeedback(Request request) {
        if (! isHandled(request))
            return;
        
        Request childReq = getAdaptedRequest(request);
        
        forEachConnection(c -> c.showSourceFeedback(childReq));
        
    }

    @objid ("57ae6448-f630-456d-9ee3-39fad423265c")
    protected final void forEachConnection(Consumer<EditPart> action) {
        GraphicalEditPart node = (GraphicalEditPart) getHost();
        
        Consumer<EditPart> a = c -> {if (c.isActive()) action.accept(c);};
        
        node.getSourceConnections().forEach(a);
        node.getTargetConnections().forEach(a);
        
    }

    @objid ("27ba7e17-0374-4f10-87fb-7da3761ac929")
    protected Request getAdaptedRequest(Request parentRequest) {
        ChangeBoundsRequest req = new ChangeBoundsRequest(REQ_RESIZE);
        req.setEditParts(getHost());
        req.setExtendedData(parentRequest.getExtendedData());
        return req;
    }

    @objid ("641c7a19-3be1-41de-a58c-db33a7ff6d43")
    private boolean isHandled(Request req) {
        Object type = req.getType();
        return REQ_CREATE_BENDPOINT.equals(type)
                || REQ_MOVE_BENDPOINT.equals(type)
                || CreateLinkConstants.REQ_CONNECTION_MOVE_SEGMENT.equals(type)
                || CreateLinkConstants.REQ_CONNECTION_UPDATE_ROUTING_CONSTRAINT.equals(type)
                || isMoveEndPointRequest(req);
        
    }

    /**
     * Tells whether the request is to move the host source or target end point.
     * <p>
     * Returns false if the request is to moven another connection (connected to the host?)
     * @param request a request
     * @return true if the request is to move host end point.
     */
    @objid ("4576594f-653a-4545-a48e-1c4c92166daa")
    private boolean isMoveEndPointRequest(Request request) {
        Object type = request.getType();
        
        if (!REQ_RECONNECT_SOURCE.equals(type)
                && !REQ_RECONNECT_TARGET.equals(type)) {
            return false;
        }
        
        ReconnectRequest req = (ReconnectRequest) request;
        return req.getConnectionEditPart() == getHost();
    }

    /**
     * Reserve slots for drag policies that must be installed before this one, needed by LayoutConnectionConnectionsEditPolicy.
     * @param editpart the edit part destined to have this policy.
     */
    @objid ("14bea299-1390-4add-8a24-d59609b72cfe")
    private void reserveNeededSlots(EditPart editpart) {
        for (Object role : DEPENDENT_POLICIES) {
            if (editpart.getEditPolicy(role) == null) {
                editpart.installEditPolicy(role, null);
            }
        }
        
    }

}
