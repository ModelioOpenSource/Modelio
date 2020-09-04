/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.modelio.diagram.elements.common.ghostlink.GhostLinkEditPart;
import org.modelio.diagram.elements.common.ghostnode.GhostNodeEditPart;
import org.modelio.diagram.elements.core.commands.DeleteInDiagramCommand;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.drawings.core.IGmDrawing;

/**
 * Default edit policy for the {@link EditPolicy#COMPONENT_ROLE} role that can delete a graphic element from the
 * diagram.
 * <p>
 * A model-based EditPolicy for <i>components within a </i>container</i>. A model-based EditPolicy only knows about the
 * host's model and the basic operations it supports. A <i>component</i> is anything that is inside a container. By
 * default, DefaultDeleteEditPolicy understands being DELETEd from its container. Subclasses can add support to handle
 * additional behavior specific to the model.
 * <P>
 * DELETE is forwarded to the <i>parent</i> EditPart, but subclasses may also contribute to the delete by overriding
 * {@link #getDeleteCommand(GroupRequest)}.
 * <P>
 * This EditPolicy is not a {@link org.eclipse.gef.editpolicies.GraphicalEditPolicy}, and should not be used to show
 * feedback or interact with the host's visuals in any way.
 * <P>
 * This EditPolicy should not be used with {@link org.eclipse.gef.ConnectionEditPart}. Connections do not really have a
 * parent; use {@link ConnectionEditPolicy}.
 * 
 * @author cmarin
 * @see org.eclipse.gef.editpolicies.ComponentEditPolicy
 */
@objid ("80bbb745-1dec-11e2-8cad-001ec947c8cc")
public class DefaultDeleteNodeEditPolicy extends AbstractEditPolicy {
    /**
     * Factors the incoming Request into ORPHANs and DELETEs.
     * @see org.eclipse.gef.EditPolicy#getCommand(Request)
     */
    @objid ("80bbb749-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Command getCommand(Request request) {
        if (RequestConstants.REQ_DELETE.equals(request.getType())) {
            return getDeleteCommand((GroupRequest) request);
        }
        return null;
    }

    /**
     * Create a DeleteInDiagramCommand if the edit part is selectable.
     * <p>
     * Forwards the request to its parent if the edit part is not selectable.
     * @param request the DeleteRequest
     * @return a delete command
     */
    @objid ("80bbb754-1dec-11e2-8cad-001ec947c8cc")
    protected Command getDeleteCommand(GroupRequest request) {
        final EditPart host = getHost();
        if (host.isSelectable()) {
            final Object model = host.getModel();
            if (model instanceof GmModel) {
                final GmModel gmModel = (GmModel) model;
        
                // Allow deletion only if the graphic is a main node/link
                if (((gmModel.getRepresentedElement() != null)
                        || host instanceof GhostNodeEditPart
                        || host instanceof GhostLinkEditPart)) {
                    DeleteInDiagramCommand ret = new DeleteInDiagramCommand();
                    ret.setNodetoDelete((IGmObject) model);
                    // Since 3.7 : "notify" the parent about removal so that it can
                    // auto resize itself.
                    final Command removeFromParentCommand = getRemoveFromParentCommand(request);
                    return ret.chain(removeFromParentCommand);
        
                }
            } else if (model instanceof IGmDrawing) {
                DeleteInDiagramCommand ret = new DeleteInDiagramCommand();
                ret.setNodetoDelete((IGmObject) model);
                return ret;
            }
        }
        return host.getParent().getCommand(request);
    }

    @objid ("1edefe50-a5b4-4352-8b2d-0f883f4a0e09")
    private Command getRemoveFromParentCommand(GroupRequest request) {
        // Hack: REQ_ORPHAN_CHILDREN is not meant to be used for this, we should create
        // another request type in a place accessible from everywhere.
        GroupRequest req2 = new GroupRequest(RequestConstants.REQ_ORPHAN_CHILDREN);
        req2.setEditParts(request.getEditParts());
        EditPart parent = getHost().getParent();
        return parent != null ? parent.getCommand(req2) : null;
    }

}
