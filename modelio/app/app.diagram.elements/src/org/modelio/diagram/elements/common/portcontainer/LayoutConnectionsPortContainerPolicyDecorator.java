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
@objid ("b7327f68-8cc6-41ae-9ede-92533494802f")
public class LayoutConnectionsPortContainerPolicyDecorator extends LayoutConnectionsConstrainedLayoutEditPolicyDecorator {
    /**
     * @param decorated the initial layout edit policy.
     */
    @objid ("fd638bb6-812b-4b09-b776-f960b1720445")
    public  LayoutConnectionsPortContainerPolicyDecorator(PortContainerEditPolicy decorated) {
        super(decorated);
    }

    @objid ("56037c56-a751-40e1-901c-5f70ec265e23")
    protected void addLayoutAllCommands(CompoundCommand command, Request request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditPart(getHost())
        .createCommands(command);
        
    }

    @objid ("b66009b3-66d2-438b-9eb0-acb337e18ccb")
    @Override
    protected void addAddCommand(CompoundCommand command, Request request) {
        addLayoutAllCommands(command, request);
    }

    @objid ("e4fc142e-b98d-43fc-a251-6d0c2b2b0d6d")
    @Override
    protected void addCloneCommand(CompoundCommand command, ChangeBoundsRequest request) {
        addLayoutAllCommands(command, request);
    }

    @objid ("16b7eb97-e8fc-446a-a54e-20b6996e657f")
    @Override
    protected void addCreateCommand(CompoundCommand command, CreateRequest request) {
        addLayoutAllCommands(command, request);
    }

    @objid ("7224da56-fa87-45e6-80bf-17cf189f3d04")
    @Override
    protected void addDeleteDependantCommand(CompoundCommand command, GroupRequest request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditPart(getHost())
        .removeEditParts(request)
        .createCommands(command);
        
    }

    @objid ("3f4d7eb6-d561-4c0b-a353-10bcfefbd6d5")
    @Override
    protected void addMoveChildrenCommand(CompoundCommand command, ChangeBoundsRequest request) {
        addLayoutAllCommands(command, request);
    }

    @objid ("b9fb38be-094a-4b0e-9936-e57893107a93")
    @Override
    protected void addOrphanChildrenCommand(CompoundCommand command, GroupRequest request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditPart(getHost())
        .removeEditParts(request)
        .createCommands(command);
        
    }

}
