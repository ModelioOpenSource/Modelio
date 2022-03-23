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
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.DefaultNodeResizableEditPolicy;
import org.modelio.diagram.elements.core.requests.ChangeBoundsFeedbackMap;

/**
 * {@link PortContainerEditPart} preferred drag policy.
 * <p>
 * Enhanced version of {@link AutoSizeEditPolicy}.
 * 
 * @author cmarin
 * @since 3.4
 */
@objid ("53550f63-8d68-4c04-b24a-d4acdcd2e064")
public class AutoSizeEditPolicy2 extends DefaultNodeResizableEditPolicy {
    @objid ("de384d28-6f4c-4fe7-8679-7f74af876a2f")
    private SelectionEditPolicy mainNodeMovePolicy;

    @objid ("1cd4f0a1-a660-4127-9d86-0983c29ced04")
    private SelectionEditPolicy mainNodeResizePolicy;

    @objid ("dce6626f-f4f4-4211-a6f8-ea1a2d202402")
    private SelectionEditPolicy getMainNodeMovePolicy(AbstractNodeEditPart mainNodeEditPart) {
        if (this.mainNodeMovePolicy == null || !this.mainNodeMovePolicy.getHost().isActive()) {
            this.mainNodeMovePolicy = mainNodeEditPart.getPreferredDragRolePolicy(RequestConstants.REQ_MOVE);
            if (this.mainNodeMovePolicy != null) {
                this.mainNodeMovePolicy.setHost(mainNodeEditPart);
                this.mainNodeMovePolicy.activate();
            }
        }
        return this.mainNodeMovePolicy;
    }

    @objid ("7b7ae00c-573f-43db-9fea-131048ca6a8d")
    private SelectionEditPolicy getMainNodeResizePolicy(AbstractNodeEditPart mainNodeEditPart) {
        if (this.mainNodeResizePolicy == null || !this.mainNodeResizePolicy.getHost().isActive()) {
            this.mainNodeResizePolicy = mainNodeEditPart.getPreferredDragRolePolicy(RequestConstants.REQ_RESIZE);
            if (this.mainNodeResizePolicy != null) {
                this.mainNodeResizePolicy.setHost(mainNodeEditPart);
                this.mainNodeResizePolicy.activate();
            }
        }
        return this.mainNodeResizePolicy;
    }

    @objid ("3faa45cd-0055-4245-9d04-53699f48464e")
    @Override
    protected Command getMoveCommand(ChangeBoundsRequest request) {
        // First look whether the main node has a special drag policy wish
        AbstractNodeEditPart mainNodeEditPart = getMainNodeEditPart();
        if (mainNodeEditPart != null) {
            SelectionEditPolicy dragPolicy = getMainNodeMovePolicy(mainNodeEditPart);
            if (dragPolicy != null) {
                // Instead of delegating the request "as is" to the parent,
                // apply the delta to the main node, then compute the container's new
                // bounds and delegate THAT request to the parent.
                // Apply the bounds change to the main node. The container should
                // adapt itself if needed.
                ChangeBoundsRequest changeMainNodeBoundsRequest = RequestHelper.shallowCopy(request);
                changeMainNodeBoundsRequest.setType(REQ_MOVE);
                changeMainNodeBoundsRequest.setEditParts(mainNodeEditPart);
                RequestHelper.addSharedEditParts(changeMainNodeBoundsRequest, request);
        
                // shortcut : ask directly to the policy
                dragPolicy.setHost(mainNodeEditPart);
                Command resizeMainNodeCommand = dragPolicy.getCommand(changeMainNodeBoundsRequest);
                return resizeMainNodeCommand;
            }
        }
        
        // No special main node drag policy.
        // Begin same as ResizableEditPolicy
        ChangeBoundsRequest req = new ChangeBoundsRequest(REQ_MOVE_CHILDREN);
        req.setEditParts(getHost());
        req.setExtendedData(request.getExtendedData());
        req.setMoveDelta(request.getMoveDelta());
        req.setSizeDelta(request.getSizeDelta());
        req.setLocation(request.getLocation());
        RequestHelper.addSharedEditParts(req, request);
        
        // Adding modified handle bounds to extended data.
        PortContainerFigure containerFigure = (PortContainerFigure) getHostFigure();
        Rectangle newMainNodeBounds = PortResizeHelper.computeRequestedMainNodeBounds(containerFigure, request);
        req.getExtendedData().put(PortResizeHelper.REQPROP_MAIN_NODE_BOUNDS, newMainNodeBounds);
        
        // Same for trimmed bounds
        Rectangle tb = getHost().getTrimmedBounds().getCopy();
        containerFigure.translateToAbsolute(tb);
        tb = req.getTransformedRectangle(tb);
        req.getExtendedData().put(AbstractNodeEditPart.REQPROP_TRIMMED_BOUNDS, tb);
        
        // Ask to parent edit part
        return getHost().getParent().getCommand(req);
    }

    @objid ("7e31d20c-33fe-49b0-8e0b-3c4262e934ff")
    @Override
    public PortContainerEditPart getHost() {
        return (PortContainerEditPart) super.getHost();
    }

    @objid ("359a7534-7710-4420-a2f1-d1450473c2d1")
    @Override
    protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
        AbstractNodeEditPart mainNodeEditPart = getMainNodeEditPart();
        SelectionEditPolicy dragPolicy = getMainNodeDragPolicy(request, mainNodeEditPart);
        if (dragPolicy != null) {
            dragPolicy.showSourceFeedback(request);
        
            ChangeBoundsFeedbackMap feedbackMap = ChangeBoundsFeedbackMap.getOrCreate(request);
            feedbackMap.put(getHost(), feedbackMap.get(mainNodeEditPart));
        } else {
            super.showChangeBoundsFeedback(request);
        
            ChangeBoundsFeedbackMap feedbackMap = ChangeBoundsFeedbackMap.getOrCreate(request);
            feedbackMap.put(mainNodeEditPart, feedbackMap.get(getHost()));
        
            // DiagramElements.LOG.debug("%s: No drag policy for show feedback '%s'\n", getClass().getSimpleName(), request.getType());
        }
        
    }

    @objid ("1c21067c-7add-45d4-b10a-4577fdaaeae4")
    @Override
    public void eraseSourceFeedback(Request request) {
        GraphicalEditPart mainNodeEditPart = getMainNodeEditPart();
        SelectionEditPolicy dragPolicy = getMainNodeDragPolicy(request, mainNodeEditPart);
        
        if (dragPolicy != null) {
            dragPolicy.eraseSourceFeedback(request);
        } else {
            // DiagramElements.LOG.debug("%s: No drag policy for erase feedback '%s'\n", getClass().getSimpleName(), request.getType());
        }
        
        super.eraseSourceFeedback(request);
        
    }

    @objid ("694ef610-18ca-49aa-b48c-51506c642cd2")
    @Override
    public void deactivate() {
        if (this.mainNodeMovePolicy != null) {
            this.mainNodeMovePolicy.deactivate();
        }
        
        if (this.mainNodeResizePolicy != null) {
            this.mainNodeResizePolicy.deactivate();
        }
        
        super.deactivate();
        
    }

    @objid ("2ce7d296-493a-4a83-94d9-71b85f3b7fa3")
    protected SelectionEditPolicy getMainNodeDragPolicy(Request request, GraphicalEditPart mainNodeEditPart) {
        Object requestType = request.getType();
        
        if (REQ_MOVE.equals(requestType) || REQ_ADD.equals(requestType)
                || REQ_CLONE.equals(requestType) || REQ_ALIGN.equals(requestType)) {
            return getMainNodeMovePolicy((AbstractNodeEditPart) mainNodeEditPart);
        } else if (REQ_RESIZE.equals(requestType)) {
            return getMainNodeResizePolicy((AbstractNodeEditPart) mainNodeEditPart);
        }
        return null;
    }

    @objid ("f06496ea-1f37-48f9-96e3-39edbb347aca")
    @Override
    protected Command getOrphanCommand(final Request req) {
        ChangeBoundsRequest request = (ChangeBoundsRequest) req;
        
        // Adding modified handle bounds to extended data of request, so that it might be used by the layout policy of the "would be" parent.
        PortContainerFigure containerFigure = (PortContainerFigure) getHostFigure();
        Rectangle newMainNodeBounds = PortResizeHelper.computeRequestedMainNodeBounds(containerFigure, request);
        request.getExtendedData().put(PortResizeHelper.REQPROP_MAIN_NODE_BOUNDS, newMainNodeBounds);
        return super.getOrphanCommand(request);
    }

    @objid ("e3166c20-1042-44b7-ae54-24b537559f4c")
    @Override
    protected Command getResizeCommand(ChangeBoundsRequest request) {
        // Instead of delegating the request "as is" to the parent,
        // apply the delta to the main node, then compute the container's new
        // bounds and delegate THAT request to the parent.
        AbstractNodeEditPart mainNodeEditPart = getMainNodeEditPart();
        if (mainNodeEditPart != null) {
            // If the main node can provide a resize policy, use it, otherwise use default.
            SelectionEditPolicy resizePolicy = mainNodeEditPart.getPreferredDragRolePolicy(REQ_RESIZE);
            if (resizePolicy == null) {
                resizePolicy = new DefaultNodeResizableEditPolicy();
            }
        
            EditPolicy oldPol = mainNodeEditPart.getEditPolicy(PRIMARY_DRAG_ROLE);
            mainNodeEditPart.installEditPolicy(PRIMARY_DRAG_ROLE, resizePolicy);
        
            // Apply the bounds change to the main node. The container should
            // adapt itself if needed.
            ChangeBoundsRequest changeMainNodeBoundsRequest = RequestHelper.shallowCopy(request);
            changeMainNodeBoundsRequest.setType(REQ_RESIZE);
            changeMainNodeBoundsRequest.setEditParts(mainNodeEditPart);
            RequestHelper.addSharedEditParts(changeMainNodeBoundsRequest, request);
        
            Command resizeMainNodeCommand = mainNodeEditPart.getCommand(changeMainNodeBoundsRequest);
        
            // Remove the installed resize policy: remember that main node is NOT
            // directly selectable nor moveable nor resizeable.
            mainNodeEditPart.installEditPolicy(PRIMARY_DRAG_ROLE, oldPol);
        
            return resizeMainNodeCommand;
        } else {
            return super.getResizeCommand(request);
        }
        
    }

    @objid ("a4401948-50b0-4f89-94e3-db9a3db414b7")
    @Override
    protected AbstractNodeEditPart getMainNodeEditPart() {
        return getHost().getMainNodeEditPart();
    }

}
