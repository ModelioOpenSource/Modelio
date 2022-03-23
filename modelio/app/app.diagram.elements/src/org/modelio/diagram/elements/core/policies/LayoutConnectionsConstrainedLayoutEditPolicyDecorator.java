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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;

/**
 * Decorator for your {@link ConstrainedLayoutEditPolicy} that asks connections to update their layout
 * when editing the container members.
 * <p>
 * <h2>Usage</h2>
 * This policy must be installed with {@link EditPolicy#LAYOUT_ROLE}.
 * Pass to the constructor the real {@link ConstrainedLayoutEditPolicy layout policy}
 * <p>
 * With this policy there is no need to use {@link LayoutNodeConnectionsEditPolicy} on the child edit parts.
 * @author cma
 * @see ConstrainedLayoutEditPolicy
 * @see LayoutConnectionsOrderedLayoutEditPolicyDecorator to decorate ordered layout policies
 * @since 5.1.0
 */
@objid ("cc7cc0fe-e62a-4de0-a946-c085952c84f8")
public class LayoutConnectionsConstrainedLayoutEditPolicyDecorator extends LayoutConnectionsAbstractLayoutEditPolicyDecorator {
    /**
     * @param decorated the initial layout edit policy.
     */
    @objid ("0f75fd2c-407d-4520-9ecd-5d67d0922c31")
    public  LayoutConnectionsConstrainedLayoutEditPolicyDecorator(ConstrainedLayoutEditPolicy decorated) {
        super(decorated);
    }

    @objid ("dd965647-06dd-4eea-8c58-18df0b7a5c20")
    @Override
    protected void addAddCommand(CompoundCommand command, Request request) {
        //LayoutChildrenNodeConnectionsHelper.addForAddRequest(getHost(), command, request);
    }

    @objid ("7a20da76-48b7-423a-84be-06e53d15ed06")
    @Override
    protected void addCloneCommand(CompoundCommand command, ChangeBoundsRequest request) {
        //LayoutChildrenNodeConnectionsHelper.addForAddRequest(getHost(), command, request);
    }

    @objid ("bf317d44-1760-44cd-8e1c-159109374a7d")
    @Override
    protected void addCreateCommand(CompoundCommand command, CreateRequest request) {
        //LayoutChildrenNodeConnectionsHelper.addForAddRequest(getHost(), command, request);
    }

    @objid ("c4612a91-e873-49e3-ae9c-0804584587d5")
    @Override
    protected void addDeleteDependantCommand(CompoundCommand command, GroupRequest request) {
        //LayoutChildrenNodeConnectionsHelper.addForDeleteChildren(getHost(), request.getEditParts(), command, request);
    }

    @objid ("a0b21fa2-e606-4385-a672-b8603024cbc7")
    @Override
    protected void addMoveChildrenCommand(CompoundCommand command, ChangeBoundsRequest request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditParts(request)
        .createCommands(command);
        
    }

    @objid ("af33724b-4c14-4ed2-ba4a-5596b3d37ec8")
    @Override
    protected void addOrphanChildrenCommand(CompoundCommand command, GroupRequest request) {
        
    }

}
