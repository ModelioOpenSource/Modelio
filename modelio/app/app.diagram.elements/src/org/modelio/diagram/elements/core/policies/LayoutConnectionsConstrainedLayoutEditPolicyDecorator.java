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
@objid ("60b9135d-aeec-4787-a6c0-b520a9d1928f")
public class LayoutConnectionsConstrainedLayoutEditPolicyDecorator extends LayoutConnectionsAbstractLayoutEditPolicyDecorator {
    /**
     * @param decorated the initial layout edit policy.
     */
    @objid ("24e76c53-b04f-4da4-994d-9e3baf442048")
    public  LayoutConnectionsConstrainedLayoutEditPolicyDecorator(ConstrainedLayoutEditPolicy decorated) {
        super(decorated);
    }

    @objid ("f119a1ab-0b20-44b4-856f-89a22fc1e8df")
    @Override
    protected void addAddCommand(CompoundCommand command, Request request) {
        //LayoutChildrenNodeConnectionsHelper.addForAddRequest(getHost(), command, request);
    }

    @objid ("0e011931-b3f0-4674-b91c-10e4b7302930")
    @Override
    protected void addCloneCommand(CompoundCommand command, ChangeBoundsRequest request) {
        //LayoutChildrenNodeConnectionsHelper.addForAddRequest(getHost(), command, request);
    }

    @objid ("fae9d32b-b8f7-4c9b-99a9-363464fea201")
    @Override
    protected void addCreateCommand(CompoundCommand command, CreateRequest request) {
        //LayoutChildrenNodeConnectionsHelper.addForAddRequest(getHost(), command, request);
    }

    @objid ("9e65d0f7-75dc-4ede-b903-b3d86023d70b")
    @Override
    protected void addDeleteDependantCommand(CompoundCommand command, GroupRequest request) {
        //LayoutChildrenNodeConnectionsHelper.addForDeleteChildren(getHost(), request.getEditParts(), command, request);
    }

    @objid ("2fd36255-4149-4c69-9771-db2164ed7de2")
    @Override
    protected void addMoveChildrenCommand(CompoundCommand command, ChangeBoundsRequest request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditParts(request)
        .createCommands(command);
        
    }

    @objid ("2975e948-4378-4129-8870-bde40db0cab0")
    @Override
    protected void addOrphanChildrenCommand(CompoundCommand command, GroupRequest request) {
        
    }

}
