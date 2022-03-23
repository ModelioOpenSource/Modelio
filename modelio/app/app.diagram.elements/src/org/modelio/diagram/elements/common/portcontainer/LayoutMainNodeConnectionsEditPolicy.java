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
package org.modelio.diagram.elements.common.portcontainer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.LayoutNodeConnectionsEditPolicy;

/**
 * Additional policy to put on movable port containers that request links to update their feedback then layout.
 * <p>
 * Port container policy that delegates requests to instantiated a
 * {@link LayoutNodeConnectionsEditPolicy} plugged on the main node.
 * 
 * @author cma
 * @since 5.0.2
 * @see LayoutNodeConnectionsEditPolicy
 */
@objid ("7e95bd2f-f96f-4d88-8d42-6539207c8b2b")
public class LayoutMainNodeConnectionsEditPolicy extends GraphicalEditPolicy {
    /**
     * The role to use to register this policy.
     * <p>
     * It is NOT the main role as {@link LayoutNodeConnectionsEditPolicy} so that this policy is not overriden the other.
     */
    @objid ("8c4aad79-7d16-4b39-b512-559a4204c547")
    public static final Object ROLE = LayoutMainNodeConnectionsEditPolicy.class.getSimpleName();

    /**
     * The main node edit policy
     */
    @objid ("b2b3ef36-73bf-4d3a-98e7-d861361740ab")
    private GraphicalEditPolicy delegate;

    @objid ("fc65225a-ccc9-4abb-b069-0fd515ad3f7f")
    private GraphicalEditPolicy getDelegate() {
        if (this.delegate== null || ! isValid(this.delegate)) {
            if (this.delegate != null) {
                this.delegate.deactivate();
            }
        
            AbstractNodeEditPart mainNodeEditPart = getMainNodeEditPart();
            if (mainNodeEditPart == null)
                return null;
        
            this.delegate = new LayoutNodeConnectionsEditPolicy(mainNodeEditPart);
            this.delegate.setHost(mainNodeEditPart);
            this.delegate.activate();
        }
        return this.delegate;
    }

    @objid ("8bbd39a6-94bb-4fd4-90b6-536d9db2c488")
    private boolean isValid(GraphicalEditPolicy pol) {
        EditPart polHost = pol.getHost();
        if (polHost==null)
            return false;
        if (!polHost.isActive())
            return false;
        if (polHost.getParent() != getHost())
            return false;
        return true;
    }

    @objid ("65618da2-89be-4cef-9d90-8949c97fe20c")
    @Override
    public void deactivate() {
        if (this.delegate != null) {
            this.delegate.deactivate();
            this.delegate = null;
        }
        
        super.deactivate();
        
    }

    @objid ("c0d94849-cae6-4ed4-a509-034779e3b648")
    @Override
    public void showSourceFeedback(Request request) {
        if (! isHandled(request))
            return;
        
        GraphicalEditPolicy d = getDelegate();
        if (d != null)
            d.showSourceFeedback(request);
        
    }

    @objid ("57b4b773-405c-4d58-9a0a-4c9142739ee0")
    @Override
    public void eraseSourceFeedback(Request request) {
        if (! isHandled(request))
            return;
        
        GraphicalEditPolicy d = getDelegate();
        if (d != null)
            d.eraseSourceFeedback(request);
        
    }

    @objid ("aa381ffe-8c75-4e86-8d6c-29382097880a")
    @Override
    public Command getCommand(Request request) {
        if (! isHandled(request))
            return null;
        
        GraphicalEditPolicy d = getDelegate();
        if (d == null)
            return null;
        return d.getCommand(request);
    }

    @objid ("10619f1b-036e-4892-9289-af58a676d286")
    private boolean isHandled(Request req) {
        Object type = req.getType();
        return REQ_ADD.equals(type) || REQ_MOVE.equals(type) || REQ_RESIZE.equals(type);
    }

    @objid ("817ea012-4814-4bcf-a875-8349511e2303")
    protected AbstractNodeEditPart getMainNodeEditPart() {
        GraphicalEditPart mainNodeEditPart = null;
        GmPortContainer gmPortContainer = (GmPortContainer) getHost().getModel();
        for (Object childEditPartObj : getHost().getChildren()) {
            GraphicalEditPart childEditPart = (GraphicalEditPart) childEditPartObj;
            GmNodeModel childModel = (GmNodeModel) childEditPart.getModel();
            if (gmPortContainer.equals(childModel.getParent())) {
                // There is a possibility (when the port container is deleting
                // a child) that the edit part has a child whose model is not a
                // child of the GmPortContainer.
                if (childModel == gmPortContainer.getMainNode()) {
                    mainNodeEditPart = childEditPart;
                    break;
                }
            }
        }
        
        if (mainNodeEditPart instanceof AbstractNodeEditPart) {
            return (AbstractNodeEditPart) mainNodeEditPart;
        } else {
            return null;
        }
        
    }

}
