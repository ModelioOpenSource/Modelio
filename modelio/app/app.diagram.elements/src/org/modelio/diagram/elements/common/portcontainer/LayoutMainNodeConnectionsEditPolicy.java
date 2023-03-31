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
@objid ("32cc4ddc-503e-4619-a8c5-297009203a40")
public class LayoutMainNodeConnectionsEditPolicy extends GraphicalEditPolicy {
    /**
     * The role to use to register this policy.
     * <p>
     * It is NOT the main role as {@link LayoutNodeConnectionsEditPolicy} so that this policy is not overriden the other.
     */
    @objid ("da508802-9811-4c4b-b456-9419c546ac19")
    public static final Object ROLE = LayoutMainNodeConnectionsEditPolicy.class.getSimpleName();

    /**
     * The main node edit policy
     */
    @objid ("6c5f48df-da4d-46ec-bde3-0d478e1bd7ec")
    private GraphicalEditPolicy delegate;

    @objid ("ba42c81f-ae47-44ce-9047-587bf9f5646a")
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

    @objid ("b7e0bf3f-1eb6-44f6-b2e1-e1ee3af2c282")
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

    @objid ("be5307ce-9dfb-4c5e-93eb-9a70d7ac36c0")
    @Override
    public void deactivate() {
        if (this.delegate != null) {
            this.delegate.deactivate();
            this.delegate = null;
        }
        
        super.deactivate();
        
    }

    @objid ("817ee49f-4af5-4d9f-a7ed-1de2c3f625ba")
    @Override
    public void showSourceFeedback(Request request) {
        if (! isHandled(request))
            return;
        
        GraphicalEditPolicy d = getDelegate();
        if (d != null)
            d.showSourceFeedback(request);
        
    }

    @objid ("3593be2d-e1a2-4b10-a2e6-a9363e880c11")
    @Override
    public void eraseSourceFeedback(Request request) {
        if (! isHandled(request))
            return;
        
        GraphicalEditPolicy d = getDelegate();
        if (d != null)
            d.eraseSourceFeedback(request);
        
    }

    @objid ("6489ca2a-bf87-49cf-bef4-c221b2d291a2")
    @Override
    public Command getCommand(Request request) {
        if (! isHandled(request))
            return null;
        
        GraphicalEditPolicy d = getDelegate();
        if (d == null)
            return null;
        return d.getCommand(request);
    }

    @objid ("e75c7641-ff1a-46bc-aace-53b536cf95b2")
    private boolean isHandled(Request req) {
        Object type = req.getType();
        return REQ_ADD.equals(type) || REQ_MOVE.equals(type) || REQ_RESIZE.equals(type);
    }

    @objid ("8a630cfa-8446-4e8b-b1ba-fefc9bf4d2cf")
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
