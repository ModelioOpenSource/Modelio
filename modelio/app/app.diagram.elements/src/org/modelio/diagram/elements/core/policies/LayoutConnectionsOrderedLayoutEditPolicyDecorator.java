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
@objid ("b686f483-d6d1-46e5-a276-5656f7019c10")
public class LayoutConnectionsOrderedLayoutEditPolicyDecorator extends LayoutConnectionsAbstractLayoutEditPolicyDecorator {
    /**
     * @param decorated the initial layout edit policy.
     */
    @objid ("4a47d43f-8079-459f-b005-353880399bdb")
    public  LayoutConnectionsOrderedLayoutEditPolicyDecorator(OrderedLayoutEditPolicy decorated) {
        super( decorated);
    }

    @objid ("58007ad5-488c-4acd-a32b-656814e2716f")
    @Override
    protected void addAddCommand(CompoundCommand command, Request request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditParts(getHost().getChildren())
        .createCommands(command);
        
    }

    @objid ("92a8999a-f8a1-430a-87ea-16ade66d161e")
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
    @objid ("ff30f0ff-0bc6-46ac-8b04-ddc2131e5741")
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
    @objid ("2e895f92-dfe9-4e9c-8cda-37a840180d88")
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
    @objid ("48143f6a-e0ea-4809-8e55-de282e8aa3de")
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
    @objid ("894efe5a-c692-42da-9153-a22888ea1461")
    @Override
    protected void addOrphanChildrenCommand(CompoundCommand command, GroupRequest request) {
        LayoutChildrenNodeConnectionsHelper.forRequest(request)
        .addEditParts(getHost().getChildren())
        .removeEditParts(request)
        .createCommands(command);
        
    }

}
