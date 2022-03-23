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
@objid ("cbac4862-0453-4e0f-933a-7b2123b1df9a")
public class LayoutConnectionsCompositeNodeLayoutEditPolicyDecorator extends LayoutConnectionsAbstractLayoutEditPolicyDecorator {
    /**
     * @param decorated the initial layout edit policy.
     */
    @objid ("29c9a069-62f3-4b6c-bdf0-7d4b874e2442")
    public  LayoutConnectionsCompositeNodeLayoutEditPolicyDecorator(LayoutEditPolicy decorated) {
        super(decorated);
    }

    @objid ("72581b7e-dd86-47c7-9289-554330afb5b4")
    @Override
    protected void addAddCommand(CompoundCommand command, Request request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditPart(getHost())
        .createCommands(command);
        
    }

    @objid ("30b93711-0921-4733-ab75-ae7098ca03f6")
    @Override
    protected void addCloneCommand(CompoundCommand command, ChangeBoundsRequest request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditPart(getHost())
        .createCommands(command);
        
    }

    /**
     * Add <code>Commands</code> to perform a create.
     * @param request the CreateRequest
     */
    @objid ("9a4bf6d4-6bc0-43a2-abb6-5b50e8898c35")
    @Override
    protected void addCreateCommand(CompoundCommand command, CreateRequest request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditPart(getHost())
        .createCommands(command);
        
    }

    /**
     * Add <code>Commands</code> to delete a child.
     * <p>
     * This method does not get called unless the child forwards an additional request to the
     * container editpart.
     * @param request the Request
     */
    @objid ("715b6d59-0bab-4f71-bae4-34f991e0e280")
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
    @objid ("72026c8d-1823-4aa7-b4c1-ac6c840a37cc")
    @Override
    protected void addMoveChildrenCommand(CompoundCommand command, ChangeBoundsRequest request) {
        // do nothing
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
    @objid ("037c1141-c3a0-481d-b448-b2480f4cc835")
    @Override
    protected void addOrphanChildrenCommand(CompoundCommand command, GroupRequest request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditParts(getHost().getChildren())
        .removeEditParts(request)
        .createCommands(command);
        
    }

}
