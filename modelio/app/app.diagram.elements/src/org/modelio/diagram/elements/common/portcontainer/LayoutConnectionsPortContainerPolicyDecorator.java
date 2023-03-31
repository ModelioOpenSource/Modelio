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
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.modelio.diagram.elements.core.policies.LayoutChildrenNodeConnectionsHelper;
import org.modelio.diagram.elements.core.policies.LayoutConnectionsConstrainedLayoutEditPolicyDecorator;
import org.modelio.diagram.elements.core.policies.LayoutNodeConnectionsEditPolicy;

/**
 * Decorator for {@link PortContainerEditPolicy} that asks connections to update their layout
 * when editing the container members.
 * <p>
 * <h2>Usage</h2>
 * This policy must be installed with {@link EditPolicy#LAYOUT_ROLE}.
 * Pass to the constructor the real {@link ConstrainedLayoutEditPolicy layout policy}
 * <p>
 * With this policy there is no need to use {@link LayoutNodeConnectionsEditPolicy} on the child edit parts.
 * @author cma
 * @since 5.1.0
 */
@objid ("cadda286-da78-435b-a147-4301fad524a0")
public class LayoutConnectionsPortContainerPolicyDecorator extends LayoutConnectionsConstrainedLayoutEditPolicyDecorator {
    /**
     * @param decorated the initial layout edit policy.
     */
    @objid ("7df3017c-801b-48e2-9e52-ddddd3dbff5a")
    public  LayoutConnectionsPortContainerPolicyDecorator(PortContainerEditPolicy decorated) {
        super(decorated);
    }

    @objid ("8bc93024-609d-45e7-a2f8-b9208da16b64")
    protected void addLayoutAllCommands(CompoundCommand command, Request request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditPart(getHost())
        .createCommands(command);
        
    }

    @objid ("fbbe2181-9386-4dfe-8933-716c236c2182")
    @Override
    protected void addAddCommand(CompoundCommand command, Request request) {
        addLayoutAllCommands(command, request);
    }

    @objid ("2d6a9d0b-13c9-4799-991e-0fa35f818eb7")
    @Override
    protected void addCloneCommand(CompoundCommand command, ChangeBoundsRequest request) {
        addLayoutAllCommands(command, request);
    }

    @objid ("3beaeb66-07fd-497f-b498-5ab37a5a686f")
    @Override
    protected void addCreateCommand(CompoundCommand command, CreateRequest request) {
        addLayoutAllCommands(command, request);
    }

    @objid ("a41ecd53-4f6b-49ca-8231-b0672eb81a90")
    @Override
    protected void addDeleteDependantCommand(CompoundCommand command, GroupRequest request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditPart(getHost())
        .removeEditParts(request)
        .createCommands(command);
        
    }

    @objid ("a7d44e2d-9b2a-4344-883e-4970e6a3eddf")
    @Override
    protected void addMoveChildrenCommand(CompoundCommand command, ChangeBoundsRequest request) {
        addLayoutAllCommands(command, request);
    }

    @objid ("7fbb657b-e056-43e6-8ce2-29864bad4189")
    @Override
    protected void addOrphanChildrenCommand(CompoundCommand command, GroupRequest request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditPart(getHost())
        .removeEditParts(request)
        .createCommands(command);
        
    }

}
