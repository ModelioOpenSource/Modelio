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
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;

/**
 * Additional policy to put on movable nodes that request links to update their feedback then layout.
 * @author cma
 * @since 5.0.2
 */
@objid ("40c1aabb-4c76-4d37-88a6-96632e049c55")
public class LayoutNodeConnectionsEditPolicy extends GraphicalEditPolicy {
    /**
     * The role to use for this edit policy
     */
    @objid ("02b0d80e-3a33-477c-a710-1e27b6e3805f")
    public static final Object ROLE = LayoutNodeConnectionsEditPolicy.class.getSimpleName();

    /**
     * Public constructor.
     * <p>
     * Reserve slots for drag policies that must be installed before this one.
     * @param editpart the edit part destined to have this policy.
     */
    @objid ("6f0b61ac-8483-411b-a2b6-0b243b67a6d9")
    public  LayoutNodeConnectionsEditPolicy(EditPart editpart) {
        super();
        
        if (editpart != null)
            reserveNeededSlots(editpart);
        
    }

    @objid ("2b1fa8bf-f6fc-43ac-92b5-2f7b26d72300")
    @Override
    public void eraseSourceFeedback(Request request) {
        if (! isHandled(request))
            return;
        
        forEachConnection(c -> c.eraseSourceFeedback(request));
        
    }

    @objid ("63f6e00d-8998-4d34-8942-70e18f71e03b")
    @Override
    public Command getCommand(Request request) {
        if (! isHandled(request))
            return null;
        
        CompoundCommand command = new CompoundCommand();
        
        
        forEachConnection(c -> {
            Command connCommand = c.getCommand(request);
            if (connCommand != null)
                command.add(connCommand);
        });
        return command.isEmpty() ? null : command.unwrap();
    }

    /**
     * Show the source drag feedback for the drag occurring within the viewer.
     */
    @objid ("09896390-74ed-4c91-9cc2-8873fcd970df")
    @Override
    public void showSourceFeedback(Request request) {
        if (! isHandled(request))
            return;
        
        forEachConnection(c -> c.showSourceFeedback(request));
        
    }

    @objid ("5d912a3f-796c-430a-925b-daf88f1af37d")
    protected final void forEachConnection(Consumer<EditPart> action) {
        GraphicalEditPart node = (GraphicalEditPart) getHost();
        
        Consumer<EditPart> a = c -> {if (c.isActive()) action.accept(c);};
        
        node.getSourceConnections().forEach(a);
        node.getTargetConnections().forEach(a);
        
    }

    @objid ("036e4fb8-6ed3-4cf7-8018-4151131be0f2")
    private boolean isHandled(Request req) {
        Object type = req.getType();
        return REQ_ADD.equals(type) || REQ_MOVE.equals(type) || REQ_RESIZE.equals(type);
    }

    /**
     * Reserve slots for drag policies that must be installed before this one.
     * @param editpart the edit part destined to have this policy.
     */
    @objid ("2efbf6c2-0f52-4a3a-8d9d-210f742a1816")
    private void reserveNeededSlots(EditPart editpart) {
        final EditPolicy pol = editpart.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
        if (pol == null)
            editpart.installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, null);
        
    }

}
