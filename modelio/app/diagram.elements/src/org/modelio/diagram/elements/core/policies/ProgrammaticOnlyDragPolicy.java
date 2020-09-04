/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.helpers.RequestHelper;

/**
 * Same as {@link org.eclipse.gef.editpolicies.ResizableEditPolicy} without
 * feedback nor selection handles.
 * <p>
 * This policy is meant to be put on edit part the user shouldn't select
 * but that should be able to respond to move and resize requests created internally.
 * 
 * @author cmarin
 * @since 3.4
 */
@objid ("9624b358-e041-432c-beac-15fef8719d76")
public class ProgrammaticOnlyDragPolicy extends GraphicalEditPolicy {
    /**
     * Returns <code>true</code> for move, align, add, and orphan request types.
     * This method is never called for some of these types, but they are
     * included for possible future use.
     * @see org.eclipse.gef.EditPolicy#understandsRequest(org.eclipse.gef.Request)
     */
    @objid ("a6b7d19d-9160-4d12-8c15-427935d92b67")
    @Override
    public boolean understandsRequest(Request request) {
        if (REQ_MOVE.equals(request.getType())) {
            return isDragAllowed();
        } else if (REQ_CLONE.equals(request.getType())
                || REQ_ADD.equals(request.getType())
                || REQ_ORPHAN.equals(request.getType())
                || REQ_ALIGN.equals(request.getType())) {
            return true;
        }
        return false;
    }

    @objid ("b0dd10c6-cf47-4124-87e8-a822f4e41f38")
    @Override
    public Command getCommand(Request request) {
        Object type = request.getType();
        
        if (REQ_MOVE.equals(type)) {
            if (isDragAllowed()) {
                return getMoveCommand((ChangeBoundsRequest) request);
            }
        } else if (REQ_ORPHAN.equals(type)) {
            return getOrphanCommand(request);
        } else if (REQ_ALIGN.equals(type)) {
            return getAlignCommand((AlignmentRequest) request);
        } else if (REQ_RESIZE.equals(request.getType())) {
            return getResizeCommand((ChangeBoundsRequest) request);
        }
        return null;
    }

    /**
     * Drag is allowed programmatically by default.
     * @return
     */
    @objid ("efda106f-74d6-41db-8d10-868b550eb62d")
    protected boolean isDragAllowed() {
        return true;
    }

    /**
     * Returns the command contribution to an alignment request
     * 
     * @param request the alignment request
     * @return the contribution to the alignment
     */
    @objid ("89e05382-075a-4164-b365-457f7f8ca5ba")
    protected Command getAlignCommand(AlignmentRequest request) {
        AlignmentRequest req = new AlignmentRequest(REQ_ALIGN_CHILDREN);
        req.setEditParts(getHost());
        req.setAlignment(request.getAlignment());
        req.setAlignmentRectangle(request.getAlignmentRectangle());
        return getHost().getParent().getCommand(req);
    }

    /**
     * Returns the command contribution to a change bounds request. The
     * implementation actually redispatches the request to the host's parent
     * editpart as a {@link RequestConstants#REQ_MOVE_CHILDREN} request. The
     * parent's contribution is returned.
     * 
     * @param request the change bounds request
     * @return the command contribution to the request
     */
    @objid ("449bb9b7-de18-4d29-9cb5-39cff8fea551")
    protected Command getMoveCommand(ChangeBoundsRequest request) {
        ChangeBoundsRequest req = new ChangeBoundsRequest(REQ_MOVE_CHILDREN);
        req.setEditParts(getHost());
        
        req.setMoveDelta(request.getMoveDelta());
        req.setSizeDelta(request.getSizeDelta());
        req.setLocation(request.getLocation());
        req.setExtendedData(request.getExtendedData());
        return getHost().getParent().getCommand(req);
    }

    /**
     * Subclasses may override to contribute to the orphan request. By default,
     * <code>null</code> is returned to indicate no participation. Orphan
     * requests are not forwarded to the host's parent here. That is done in
     * {@link ComponentEditPolicy}. So, if the host has a component editpolicy,
     * then the parent will already have a chance to contribute.
     * 
     * @param req the orphan request
     * @return <code>null</code> by default
     */
    @objid ("0134ee4b-c451-4606-aa61-7566b39284c8")
    protected Command getOrphanCommand(Request req) {
        return null;
    }

    /**
     * Returns the command contribution for the given resize request. By
     * default, the request is re-dispatched to the host's parent as a
     * {@link org.eclipse.gef.RequestConstants#REQ_RESIZE_CHILDREN}. The
     * parent's edit policies determine how to perform the resize based on the
     * layout manager in use.
     * 
     * @param request the resize request
     * @return the command contribution obtained from the parent
     */
    @objid ("e3d9b83b-b752-4338-8862-9685cf501bb7")
    protected Command getResizeCommand(ChangeBoundsRequest request) {
        ChangeBoundsRequest req = RequestHelper.shallowCopy(request);
        req.setType(REQ_RESIZE_CHILDREN);
        req.setEditParts(getHost());
        return getHost().getParent().getCommand(req);
    }

}
