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
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.plugin.DiagramElements;

/**
 * The host will try to expands itself to fits its preferred size when a child node is added or changed.
 * <p>
 * To be used on edit parts that <b>already have</b> a layout policy but that should resize when one of their child
 * edit part wants to resize.
 * <p>
 * This policy should be installed with the {@link AutoExpandEditPolicy#ROLE} role.
 * <p>
 * <h2>Working:</h2>
 * This policy computes the new container size by asking its figure layout manager for the
 * new preferred size, after having forced the resized figure preferred size to conform
 * the resize request.
 * 
 * @author cmarin
 * @since 3.7
 */
@objid ("59a65266-dc4d-49f1-91c0-7448a1cd1f6c")
public class AutoExpandEditPolicy extends GraphicalEditPolicy {
    /**
     * Role to use when installing this edit policy.
     */
    @objid ("c4acda6a-523d-4162-81ac-6d92dfff500d")
    public static final String ROLE = "Auto expand to content";

    @objid ("0dbfe59c-7637-425b-8650-23410730db5d")
    private EditPartListener listener;

    @objid ("bc1fc8e6-0d32-444f-9766-0a8ce100385b")
    @Override
    public Command getCommand(final Request request) {
        final Object reqType = request.getType();
        
        if (REQ_RESIZE_CHILDREN.equals(reqType) ||
                REQ_MOVE_CHILDREN.equals(reqType)) {
            return getExpandContainerCommand((ChangeBoundsRequest) request);
        }
        return null;
    }

    /**
     * @param request a REQ_RESIZE_CHILDREN request
     * @return the container resize command.
     */
    @objid ("4cb29e32-0005-4043-9a5e-8e4ffa59f99f")
    protected Command getExpandContainerCommand(ChangeBoundsRequest request) {
        return AutoExpandHelper.getExpandContainerCommand(request, getHost(), ((GraphicalEditPart) getHost()).getContentPane());
    }

    /**
     * Called by an edit part listener when a child edit part is added.
     * <p>
     * Try to expand the container to fit all children.
     * 
     * @param child the added edit part
     */
    @objid ("ab42772c-e03e-4f28-866c-3789253bbaff")
    protected void onChildAdded(EditPart child) {
        ChangeBoundsRequest request = new ChangeBoundsRequest(REQ_RESIZE);
        request.setEditParts(child);
        
        // The child figure has just been added but not yet layouted, force layout now to avoid strange effects.
        getHostFigure().getUpdateManager().performValidation();
        
        Command cmd = getExpandContainerCommand(request);
        if (cmd != null && cmd.canExecute()) {
            cmd.execute();
        } else {
            DiagramElements.LOG.debug("%s.onChildAdded(%s) : unable to expand <%s>. Command = <%s>",
                    getClass().getSimpleName(), child, getHost(), cmd);
        }
    }

    /**
     * Creates the EditPartListener for observing when children are added to the host.
     */
    @objid ("f6c85cf4-62a6-4524-bded-23e7af3b6a8f")
    @Override
    public void activate() {
        super.activate();
        
        this.listener = new EditPartListener.Stub() {
            @Override
            public void childAdded(EditPart child, int index) {
                onChildAdded(child);
            }
        };
        
        getHost().addEditPartListener(this.listener );
    }

    @objid ("6c53c486-18aa-489d-a95f-9f98e9b3f44e")
    @Override
    public void deactivate() {
        getHost().removeEditPartListener(this.listener );
        this.listener = null;
        
        super.deactivate();
    }

}
