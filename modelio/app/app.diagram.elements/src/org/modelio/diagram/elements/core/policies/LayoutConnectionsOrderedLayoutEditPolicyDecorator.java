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
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.OrderedLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;

/**
 * Decorator for your {@link OrderedLayoutEditPolicy} that asks connections to update their layout
 * when editing the container members.
 * <p>
 * <h2>Usage</h2>
 * This policy must be installed with {@link EditPolicy#LAYOUT_ROLE}.
 * Pass to the constructor the real {@link OrderedLayoutEditPolicy layout policy}
 * <p>
 * With this policy there is no need to use {@link LayoutNodeConnectionsEditPolicy} on the child edit parts.
 * @author cma
 * @see LayoutEditPolicy
 * @since 5.1.0
 */
@objid ("464ee156-6568-4f35-bde4-f860573b72a8")
public class LayoutConnectionsOrderedLayoutEditPolicyDecorator extends LayoutConnectionsAbstractLayoutEditPolicyDecorator {
    /**
     * @param decorated the initial layout edit policy.
     */
    @objid ("bfcd8962-f4d4-481c-beab-0f2dd1a1405c")
    public  LayoutConnectionsOrderedLayoutEditPolicyDecorator(OrderedLayoutEditPolicy decorated) {
        super( decorated);
    }

    @objid ("7dd2cd43-5590-478d-9d04-f4b1e879310e")
    @Override
    protected void addAddCommand(CompoundCommand command, Request request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditParts(getHost().getChildren())
        .createCommands(command);
        
    }

    @objid ("fcb03359-e6b6-4dee-844b-6ab528be304d")
    @Override
    protected void addCloneCommand(CompoundCommand command, ChangeBoundsRequest request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditParts(getHost().getChildren())
        .createCommands(command);
        
    }

    /**
     * Add <code>Commands</code> to perform a create.
     * @param request the CreateRequest
     */
    @objid ("d2244079-e3b8-457e-9191-99bed336f612")
    @Override
    protected void addCreateCommand(CompoundCommand command, CreateRequest request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditParts(getHost().getChildren())
        .createCommands(command);
        
    }

    /**
     * Add <code>Commands</code> to delete a child.
     * <p>
     * This method does not get called unless the child forwards an additional request to the
     * container editpart.
     * @param request the Request
     */
    @objid ("b0bed91a-cdcb-4f75-b6c9-20a21629b694")
    @Override
    protected void addDeleteDependantCommand(CompoundCommand command, GroupRequest request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditParts(getHost().getChildren())
        .removeEditParts(request)
        .createCommands(command);
        
    }

    /**
     * Add <code>Commands</code> to move a group of children.
     * @param request the Request
     */
    @objid ("5d774154-d1a6-4ab3-a09c-f34ad6f95a0d")
    @Override
    protected void addMoveChildrenCommand(CompoundCommand command, ChangeBoundsRequest request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditParts(getHost().getChildren())
        .createCommands(command);
        
    }

    /**
     * Returns the <code>Command</code> to orphan a group of children.
     * <p>
     * The contribution to orphan might contain two parts, both of which are
     * optional.
     * <ul>
     * <li>The first part is to actually remove the children from their
     * existing parent. Some application models will perform an orphan
     * implicitly when the children are added to their new parent.
     * <li> The second part is to perform some adjustments on the remaining children. For
     * example, a Table layout might simplify itself by collapsing any unused
     * columns and rows.
     * </ul>
     * @param request the Request
     */
    @objid ("9956003d-0db2-437f-ab5b-b71df51a3a8c")
    @Override
    protected void addOrphanChildrenCommand(CompoundCommand command, GroupRequest request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditParts(getHost().getChildren())
        .removeEditParts(request)
        .createCommands(command);
        
    }

}
