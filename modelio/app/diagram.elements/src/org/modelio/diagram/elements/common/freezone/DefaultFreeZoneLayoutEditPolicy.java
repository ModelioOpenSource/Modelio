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

package org.modelio.diagram.elements.common.freezone;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.AutoExpandHelper;
import org.modelio.diagram.elements.plugin.DiagramElements;

/**
 * Specialisation for most body zone: do not allow resized child to either become smaller than its minimum size nor bigger than the available space.
 * 
 * @author fpoyer
 */
@objid ("7e37fc6c-1dec-11e2-8cad-001ec947c8cc")
public class DefaultFreeZoneLayoutEditPolicy extends BaseFreeZoneLayoutEditPolicy {
    @objid ("d7702c9a-238e-4345-b590-dac789246281")
    private FeedbackHelper feedbackHelper;

    @objid ("2ec5ffa7-6b73-4e8c-b9af-3c35bdff5a92")
    @Override
    public void showLayoutTargetFeedback(Request request) {
        super.showLayoutTargetFeedback(request);
        
        if (request instanceof ChangeBoundsRequest) {
            ChangeBoundsRequest xpr = getAutoExpandRequest(Collections.singletonList((ChangeBoundsRequest) request), Collections.emptyList());
            if (xpr != null) {
                getFeedbackHelper().show(xpr, getHost());
            } else if (this.feedbackHelper != null) {
                this.feedbackHelper.erase();
                this.feedbackHelper = null;
            }
        }
    }

    /**
     * Creates the EditPartListener for observing when children are added to the host.
     * <p>
     * Redefined to auto expand on child edit part addition.
     * @return EditPartListener
     */
    @objid ("cc404576-6efc-401b-a682-39c42add3cbc")
    @Override
    protected EditPartListener createListener() {
        EditPartListener.Stub listener = new EditPartListener.Stub() {
            @Override
            public void childAdded(EditPart child, int index) {
                onChildAdded(child);
            }
        };
        return listener;
    }

    @objid ("b5baedbc-2d0f-4a0d-a89e-de44d8685a65")
    @Override
    protected void eraseLayoutTargetFeedback(Request request) {
        super.eraseLayoutTargetFeedback(request);
        
        // if (REQ_RESIZE.equals(request.getType()) ||
        // REQ_MOVE.equals(request.getType())) {
        if (request instanceof ChangeBoundsRequest) {
            if (this.feedbackHelper != null) {
                this.feedbackHelper.erase();
                this.feedbackHelper = null;
            }
        }
    }

    @objid ("fa2cb4de-cbe8-41d7-aef8-7a191499c024")
    @Override
    protected final Command getAddCommand(Request request) {
        if (!request.getExtendedData().containsKey("noautoexpand")) {
            // Expand the container if needed
            return getAutoResizeAddChildrenCommand((ChangeBoundsRequest) request);
        
        } else {
            return super.getAddCommand(request);
        }
    }

    /**
     * Get a auto expand container command for a collection of move/resize requests relating container child edit parts.
     * @param request children edit parts move/resize requests
     * @param bpRequests connection bend points to move
     * @return the auto expand command
     */
    @objid ("28991aa2-1b7f-451b-95fe-5a0343cfce35")
    protected Command getAutoExpandCommand(Collection<ChangeBoundsRequest> requests, Collection<BendpointRequest> bpRequests) {
        ChangeBoundsRequest autoExpandRequest = getAutoExpandRequest(requests, bpRequests);
        
        if (autoExpandRequest != null) {
            Command resizeContainerCommand = getHost().getCommand(autoExpandRequest);
            if (resizeContainerCommand == null || !resizeContainerCommand.canExecute()) {
                logAutoExpandNotAvailable(autoExpandRequest, getHost(), resizeContainerCommand);
            }
        
            return resizeContainerCommand;
        } else {
            return null;
        }
    }

    /**
     * Get a auto expand container request for a collection of move/resize requests relating container child edit parts.
     * <p>
     * The container will take into account connection bend points that were initially in the container.
     * @param request children edit parts move/resize requests
     * @param bpRequests connection bend points to move
     * @return the auto expand command
     */
    @objid ("7375ea4f-19d9-4b3d-ac14-2021923dba13")
    protected ChangeBoundsRequest getAutoExpandRequest(Collection<ChangeBoundsRequest> requests, Collection<BendpointRequest> bpRequests) {
        IFigure layoutContainer = getLayoutContainer();
        Point layoutOrigin = getLayoutOrigin();
        
        Rectangle clientArea = layoutContainer.getClientArea().getCopy();
        layoutContainer.translateToAbsolute(clientArea);
        
        Rectangle requiredRect = clientArea.getCopy();
        
        for (ChangeBoundsRequest request : requests) {
            List<?> children = request.getEditParts();
            for (int i = 0; i < children.size(); i++) {
                Object o = children.get(i);
                if (o instanceof AbstractNodeEditPart) {
                    AbstractNodeEditPart child = (AbstractNodeEditPart) o;
                    Rectangle constraint = getNewAbsBounds(request, layoutOrigin, child);
                    requiredRect.union(constraint);
                }
            }
        }
        
        for (BendpointRequest req : bpRequests) {
            Connection c = (Connection) req.getSource().getFigure();
            Point pt = c.getPoints().getPoint(req.getIndex() + 1);
            c.translateToAbsolute(pt);
        
            if (clientArea.contains(pt)) {
                requiredRect.union(req.getLocation());
            }
        }
        
        if (!clientArea.contains(requiredRect)) {
            // Expand needed, ask for resize
            Dimension currentSize = clientArea.getSize();
            Dimension sizeDelta = requiredRect.getSize().union(currentSize).shrink(currentSize);
        
            ChangeBoundsRequest r2 = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
            r2.setEditParts(getHost());
            r2.setSizeDelta(sizeDelta);
            r2.getMoveDelta().translate(requiredRect.x() - clientArea.x(), requiredRect.y() - clientArea.y());
            return r2;
        }
        
        // No expand needed
        return null;
    }

    /**
     * Overridden to prevent size from becoming smaller than min size of the resized child and from becoming larger than the available area.
     */
    @objid ("7e37fc6e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Object getConstraintFor(ChangeBoundsRequest request, GraphicalEditPart child) {
        IFigure childFig = child.getFigure();
        Rectangle currentConstraint = getCurrentConstraintFor(child);
        
        // With this new children are pushed inside container bounds by FreezoneLayout if they don't fit the container.
        Rectangle origBounds = new PrecisionRectangle(childFig.getBounds());
        
        Rectangle newConstraint = origBounds.getCopy();
        childFig.translateToParent(newConstraint);
        childFig.translateToAbsolute(newConstraint);
        newConstraint = request.getTransformedRectangle(newConstraint);
        translateFromAbsoluteToLayoutRelative(newConstraint);
        
        if (request.getSizeDelta().equals(0, 0)) {
            if (currentConstraint != null) {
                // Bug 86473 allows for unintended use of this method
                newConstraint.setSize(currentConstraint.width, currentConstraint.height);
            }
        } else {
            // resize
        
            // TODO: override getMinimumSizeFor(child) so that it uses the
            // actual minimum size of the child's figure.
            // Not done yet, because labels returns as minimum size the full
            // label length.
            @SuppressWarnings ("deprecation")
            Dimension minSize = getMinimumSizeFor(child);
        
            if (newConstraint.width < minSize.width) {
                newConstraint.width = minSize.width;
                if (newConstraint.x > (origBounds.right() - minSize.width)) {
                    newConstraint.x = origBounds.right() - minSize.width;
                }
            }
            if (newConstraint.height < minSize.height) {
                newConstraint.height = minSize.height;
                if (newConstraint.y > (origBounds.bottom() - minSize.height)) {
                    newConstraint.y = origBounds.bottom() - minSize.height;
                }
            }
        }
        return getConstraintFor(newConstraint);
    }

    /**
     * Calls {@link BaseFreeZoneLayoutEditPolicy#getAddCommand(Request)}.
     * @param generic a REQ_ADD request.
     * @return the command
     */
    @objid ("c2d7f43c-80ac-4e36-8281-74f37b108ee0")
    protected Command getOriginalAddCommand(Request generic) {
        return super.getAddCommand(generic);
    }

    /**
     * Redefined to resize itself first if needed.
     */
    @objid ("7ce29760-bcb0-4600-91e0-33234f1a8a59")
    @Override
    protected final Command getResizeChildrenCommand(ChangeBoundsRequest request) {
        if (!request.getExtendedData().containsKey("noautoexpand")) {
            // Expand the container if needed
            return getAutoResizeChangeChildrenCommand(request);
        
        } else {
            return super.getResizeChildrenCommand(request);
        }
    }

    @objid ("99daa132-a7cc-4b8a-a1aa-683234c06047")
    protected void logAutoExpandNotAvailable(ChangeBoundsRequest autoExpandRequest, EditPart targetEp, Command cmd) {
        if (DiagramElements.LOG.isDebugEnabled()) {
            DiagramElements.LOG.warning("<%s> is not resizeable for request: move= %s , size+= %s", getHost().getClass().getSimpleName(), autoExpandRequest.getMoveDelta(), autoExpandRequest.getSizeDelta());
            DiagramElements.LOG.warning("  parent edit part= <%s>;\n\t this policy = <%s>\n\t target edit part=%s\n\t command=%s", getHost().getParent(), this, targetEp, cmd);
            DiagramElements.LOG.warning(new Throwable("stack trace"));
        }
    }

    /**
     * Called by the edit part listener created by {@link #createListener()} when a child edit part is added.
     * <p>
     * Try to expand the container to fit all children.
     * @param child the added edit part
     */
    @objid ("56630146-8851-4e0c-932d-92ddd099784a")
    protected void onChildAdded(EditPart child) {
        // Standard behavior inherited from LayoutEditPolicy#createListener()
        decorateChild(child);
        
        // The child figure has just been added but not yet layouted, force layout now to avoid strange effects.
        getHostFigure().getUpdateManager().performValidation();
        
        // Auto expand to fit the new child
        GraphicalEditPart graphicChild = (GraphicalEditPart) child;
        ChangeBoundsRequest req = AutoExpandHelper.getNewChildAutoExpandRequest(getHost(), graphicChild, getLayoutContainer());
        
        if (req != null) {
            // resize needed
            AutoExpandHelper.executeExpandRequest(req, getHost());
        }
    }

    /**
     * Returns the Command to move/resize a group of children after having resized the container to fit the children requested bounds.
     * <p>
     * Defers the other commands creation until the container is expanded and new container bounds are computed by layout. This is needed because when the container expands toward top/left the constraint coordinates origin changes after the new constraints
     * would be computed. They would then become invalid regarding the wanted result.
     * @param request the initial move/resize children request
     * @return the final command.
     */
    @objid ("498e9063-adf4-41d5-a734-e2d3cbb8aeac")
    private Command getAutoResizeAddChildrenCommand(ChangeBoundsRequest request) {
        CompoundCommand finalCmd = new CompoundCommand();
        
        // Compute request to avoid new intersections
        ILayoutAssistant helper = getLayoutAssistant(request);
        Collection<ChangeBoundsRequest> pushNodeRequests = new ArrayList<>(helper.getNodeRequests());
        Collection<BendpointRequest> pushBendPointRequests = helper.getBendPointRequests();
        pushNodeRequests.add(request); // add the initial request to auto expand computation
        
        // Compute auto expand command
        Command autoExpandCommand = getAutoExpandCommand(pushNodeRequests, pushBendPointRequests);
        finalCmd.add(autoExpandCommand);
        
        // Defer the other commands creation until the container is expanded and new container bounds are computed by layout.
        // This is needed because when the container expands toward top/left the constraint coordinates origin
        // changes after the new constraints would be computed. They would then become invalid regarding the wanted result.
        class DeferredAddCommand extends Command {
            @SuppressWarnings ("synthetic-access")
            @Override
            public void execute() {
                // Run figure validations to have new figure bounds.
                getHostFigure().getUpdateManager().performValidation();
        
                CompoundCommand postCommands = new CompoundCommand();
        
                // Get XYLayoutEditPolicy child move/resize command
                Command childChangeCommand = getOriginalAddCommand(request);
                postCommands.add(childChangeCommand);
        
                // build commands for initial and added requests
                // build commands for moved bend points
                createLayoutAssistantCommands(helper, postCommands);
        
                postCommands.execute();
            }
        
            @Override
            public boolean canExecute() {
                // Get XYLayoutEditPolicy child move/resize command
                Command childChangeCommand = getOriginalAddCommand(request);
        
                return childChangeCommand != null && childChangeCommand.canExecute();
            }
        
            @Override
            public String toString() {
                return getClass().getName() + "[ req=" + RequestHelper.toString(request) + "]";
            }
        }
        
        Command cmd = new DeferredAddCommand();
        finalCmd.add(cmd);
        return finalCmd;
    }

    /**
     * Returns the Command to move/resize a group of children after having resized the container to fit the children requested bounds.
     * <p>
     * Defers the other commands creation until the container is expanded and new container bounds are computed by layout. This is needed because when the container expands toward top/left the constraint coordinates origin changes after the new constraints
     * would be computed. They would then become invalid regarding the wanted result.
     * @param request the initial move/resize children request
     * @return the final command.
     */
    @objid ("5384c66d-abb6-4d11-9162-4f334ca7b176")
    private Command getAutoResizeChangeChildrenCommand(ChangeBoundsRequest request) {
        CompoundCommand finalCmd = new CompoundCommand();
        
        // Compute request to avoid new intersections
        ILayoutAssistant helper = getLayoutAssistant(request);
        Collection<ChangeBoundsRequest> pushNodeRequests = new ArrayList<>(helper.getNodeRequests());
        Collection<BendpointRequest> pushBendPointRequests = helper.getBendPointRequests();
        pushNodeRequests.add(request); // add the initial request to auto expand computation
        
        // Compute auto expand command
        Command autoExpandCommand = getAutoExpandCommand(pushNodeRequests, pushBendPointRequests);
        finalCmd.add(autoExpandCommand);
        
        // Defer the other commands creation until the container is expanded and new container bounds are computed by layout.
        // This is needed because when the container expands toward top/left the constraint coordinates origin
        // changes after the new constraints would be computed. They would then become invalid regarding the wanted result.
        class DeferredChangeCommand extends Command {
            @SuppressWarnings ("synthetic-access")
            @Override
            public void execute() {
                // Run figure validations to have new figure bounds.
                getHostFigure().getUpdateManager().performValidation();
        
                CompoundCommand postCommands = new CompoundCommand();
        
                // Get "initial" XYLayoutEditPolicy child move/resize command
                Command childChangeCommand = getChangeConstraintCommand(request);
                postCommands.add(childChangeCommand);
        
                // build commands for initial and added requests
                // build commands for moved bend points
                createLayoutAssistantCommands(helper, postCommands);
        
                postCommands.execute();
            }
        
            @Override
            public String toString() {
                return getClass().getName() + "[ req=" + RequestHelper.toString(request) + "]";
            }
        }
        
        Command cmd = new DeferredChangeCommand();
        finalCmd.add(cmd);
        return finalCmd;
    }

    @objid ("c2752151-9fb2-43e0-b445-9525fb709b4b")
    private FeedbackHelper getFeedbackHelper() {
        if (this.feedbackHelper == null) {
            this.feedbackHelper = new FeedbackHelper();
        }
        return this.feedbackHelper;
    }

    /**
     * Expand feedback helper.
     * <p>
     * Asks the host target edit part of a request to show source feedback.
     */
    @objid ("c5229c74-bef6-468c-9af8-2251f3880662")
    private static class FeedbackHelper {
        @objid ("cfdb6c60-1174-4fec-ba52-d8e535968421")
        private EditPart targetEditPart;

        @objid ("a61ca9a4-7cf1-409b-9afe-636e503b8cbf")
        private Request request;

        @objid ("b78783e7-1aac-433a-b14b-6b7a45438ec7")
        public FeedbackHelper() {
            super();
        }

        @objid ("378de2a1-d714-4497-ab67-8069851413f6")
        public void show(Request aRequest, EditPart host) {
            EditPart t = host.getTargetEditPart(aRequest);
            if (t != this.targetEditPart) {
                if (this.targetEditPart != null) {
                    this.targetEditPart.eraseSourceFeedback(this.request);
                }
                this.targetEditPart = t;
            }
            if (t != null) {
                t.showSourceFeedback(aRequest);
            }
            this.request = aRequest;
        }

        @objid ("9158bca5-8619-44ad-bee0-05d3a51eb778")
        public void erase() {
            if (this.targetEditPart != null) {
                this.targetEditPart.eraseSourceFeedback(this.request);
                this.targetEditPart = null;
                this.request = null;
            }
        }

    }

}
