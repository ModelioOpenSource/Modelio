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
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DefaultNodeResizableEditPolicy;

/**
 * {@link PortContainerEditPart} preferred drag policy.
 * <p>
 * Specialisation that will actually resize the main node while making sure that the container bounds still contains all
 * children (and no more).
 * 
 * @author fpoyer
 */
@objid ("7ee607ea-1dec-11e2-8cad-001ec947c8cc")
public class AutoSizeEditPolicy extends DefaultNodeResizableEditPolicy {
    @objid ("7ee607ec-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getMoveCommand(ChangeBoundsRequest request) {
        ChangeBoundsRequest req = new ChangeBoundsRequest(REQ_MOVE_CHILDREN);
        req.setEditParts(getHost());
        
        req.setMoveDelta(request.getMoveDelta());
        req.setSizeDelta(request.getSizeDelta());
        req.setLocation(request.getLocation());
        req.setExtendedData(request.getExtendedData());
        // Adding modified handle bounds to extended data.
        PortContainerFigure containerFigure = (PortContainerFigure) getHostFigure();
        PortContainerLayout portContainerLayout = containerFigure.getPortContainerLayout();
        Rectangle newHandleBounds = portContainerLayout.getMainNodeConstraint();
        if (newHandleBounds != null) {
            newHandleBounds = newHandleBounds.getTranslated(portContainerLayout.getOrigin(containerFigure));
        } else {
            newHandleBounds = containerFigure.getHandleBounds().getCopy();
        }
        //Rectangle newHandleBounds = ((HandleBounds)containerFigure).getHandleBounds().getCopy();
        containerFigure.translateToAbsolute(newHandleBounds);
        newHandleBounds = request.getTransformedRectangle(newHandleBounds);
        //containerFigure.translateToRelative(newHandleBounds);
        
        req.getExtendedData().put(PortResizeHelper.REQPROP_MAIN_NODE_BOUNDS, newHandleBounds);
        return getHost().getParent().getCommand(req);
    }

    @objid ("7ee607f6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getOrphanCommand(final Request req) {
        ChangeBoundsRequest request = (ChangeBoundsRequest) req;
        // Adding modified handle bounds to extended data of request, so that it might be used by the layout policy of the "would be" parent.
        PortContainerFigure containerFigure = (PortContainerFigure) getHostFigure();
        PortContainerLayout portContainerLayout = containerFigure.getPortContainerLayout();
        Rectangle newHandleBounds = portContainerLayout.getMainNodeConstraint();
        if (newHandleBounds != null) {
            newHandleBounds = newHandleBounds.getTranslated(portContainerLayout.getOrigin(containerFigure));
        } else {
            newHandleBounds = containerFigure.getHandleBounds().getCopy();
        }
        containerFigure.translateToAbsolute(newHandleBounds);
        newHandleBounds = request.getTransformedRectangle(newHandleBounds);
        //containerFigure.translateToRelative(newHandleBounds);
        
        request.getExtendedData().put(PortResizeHelper.REQPROP_MAIN_NODE_BOUNDS, newHandleBounds);
        return super.getOrphanCommand(request);
    }

    @objid ("7ee60801-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getResizeCommand(ChangeBoundsRequest request) {
        // Instead of delegating the request "as is" to the parent,
        // apply the delta to the main node, then compute the container's new
        // bounds and delegate THAT request to the parent.
        GraphicalEditPart mainNodeEditPart = null;
        GmPortContainer pc = (GmPortContainer) getHost().getModel();
        for (Object childEditPartObj : getHost().getChildren()) {
            GraphicalEditPart childEditPart = (GraphicalEditPart) childEditPartObj;
            GmNodeModel childModel = (GmNodeModel) childEditPart.getModel();
            if (pc.equals(childModel.getParent())) {
                // There is a possibility (when the port container is deleting
                // a child) that the edit part has a child whose model is not a
                // child of the GmPortContainer.
                if (childModel == pc.getMainNode()) {
                    mainNodeEditPart = childEditPart;
                    break;
                }
            }
        }
        if (mainNodeEditPart != null) {
            // If the main node can provide a resize policy, use it, otherwise
            // use default.
            if (mainNodeEditPart instanceof AbstractNodeEditPart &&
                ((AbstractNodeEditPart) mainNodeEditPart).getPreferredDragRolePolicy(REQ_RESIZE) != null) {
                mainNodeEditPart.installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE,
                                                   ((AbstractNodeEditPart) mainNodeEditPart).getPreferredDragRolePolicy(REQ_RESIZE));
            } else {
                mainNodeEditPart.installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE,
                                                   new DefaultNodeResizableEditPolicy());
            }
        
            // Apply the bounds change to the main node. The container should
            // adapt itself if needed.
            ChangeBoundsRequest changeMainNodeBoundsRequest = new ChangeBoundsRequest(REQ_RESIZE);
            changeMainNodeBoundsRequest.setEditParts(mainNodeEditPart);
            changeMainNodeBoundsRequest.setCenteredResize(request.isCenteredResize());
            changeMainNodeBoundsRequest.setConstrainedMove(request.isConstrainedMove());
            changeMainNodeBoundsRequest.setConstrainedResize(request.isConstrainedResize());
            changeMainNodeBoundsRequest.setExtendedData(request.getExtendedData());
            changeMainNodeBoundsRequest.setLocation(request.getLocation());
            changeMainNodeBoundsRequest.setMoveDelta(request.getMoveDelta());
            changeMainNodeBoundsRequest.setResizeDirection(request.getResizeDirection());
            changeMainNodeBoundsRequest.setSizeDelta(request.getSizeDelta());
            Command resizeMainNodeCommand = mainNodeEditPart.getCommand(changeMainNodeBoundsRequest);
        
            // Remove the install resize policy: remember that main node is NOT
            // directly selectable nor moveable nor resizeable.
            mainNodeEditPart.installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, null);
        
            return resizeMainNodeCommand;
        }
        // else
        return super.getResizeCommand(request);
    }

}
