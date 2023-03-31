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
@objid ("ba9573b6-fdf4-4913-af01-eff7ea0c428b")
public class LayoutNodeConnectionsEditPolicy extends GraphicalEditPolicy {
    /**
     * The role to use for this edit policy
     */
    @objid ("62c76347-8bc3-4755-b2a6-d71cec26d66a")
    public static final Object ROLE = LayoutNodeConnectionsEditPolicy.class.getSimpleName();

    /**
     * Public constructor.
     * <p>
     * Reserve slots for drag policies that must be installed before this one.
     * @param editpart the edit part destined to have this policy.
     */
    @objid ("1f073dac-c632-4fba-8365-cecb4bd063c1")
    public  LayoutNodeConnectionsEditPolicy(EditPart editpart) {
        super();
        
        if (editpart != null)
            reserveNeededSlots(editpart);
        
    }

    @objid ("57832663-7d87-433e-8129-ed275492937b")
    @Override
    public void eraseSourceFeedback(Request request) {
        if (! isHandled(request))
            return;
        
        forEachConnection(c -> c.eraseSourceFeedback(request));
        
    }

    @objid ("b210790f-634c-428d-8086-b690469f7b1c")
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
    @objid ("7a335e36-9cf3-4bf0-8810-84ba34dceee1")
    @Override
    public void showSourceFeedback(Request request) {
        if (! isHandled(request))
            return;
        
        forEachConnection(c -> c.showSourceFeedback(request));
        
    }

    @objid ("9ded6ed8-6750-4b95-84b2-f6998347208f")
    protected final void forEachConnection(Consumer<EditPart> action) {
        GraphicalEditPart node = (GraphicalEditPart) getHost();
        
        Consumer<EditPart> a = c -> {if (c.isActive()) action.accept(c);};
        
        node.getSourceConnections().forEach(a);
        node.getTargetConnections().forEach(a);
        
    }

    @objid ("639fce74-52d2-4a19-a660-36b8dec9b8e9")
    private boolean isHandled(Request req) {
        Object type = req.getType();
        return REQ_ADD.equals(type) || REQ_MOVE.equals(type) || REQ_RESIZE.equals(type);
    }

    /**
     * Reserve slots for drag policies that must be installed before this one.
     * @param editpart the edit part destined to have this policy.
     */
    @objid ("4b8a4e25-9406-412f-8318-d82ca9d7c4ac")
    private void reserveNeededSlots(EditPart editpart) {
        final EditPolicy pol = editpart.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
        if (pol == null)
            editpart.installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, null);
        
    }

}
