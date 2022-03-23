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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.ResizeTracker;
import org.modelio.diagram.elements.core.figures.FigureUtilities2;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.link.anchors.fixed.IFixedConnectionAnchorFactory;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.requests.ChangeBoundsFeedbackMap;

/**
 * A local specialization of {@link ResizableEditPolicy} is used by default for children. <br>
 * <p>
 * This specialization adds a behaviour on ORPHAN requests similar to what is done in {@link NonResizableEditPolicy#getMoveCommand}, meaning that it actually dispatches the request to the host's parent edit part as a
 * {@link RequestConstants#REQ_ORPHAN_CHILDREN} request. The parent's contribution is returned.<br>
 * <p>
 * It also store the drag feedback figure into {@link ChangeBoundsFeedbackMap} stored in the request.
 * <p>
 * Subclasses may override this class to supply a different EditPolicy.
 * 
 * @see org.eclipse.gef.editpolicies.NonResizableEditPolicy#getMoveCommand
 */
@objid ("80c07c0b-1dec-11e2-8cad-001ec947c8cc")
public class DefaultNodeResizableEditPolicy extends ResizableEditPolicy {
    @objid ("993cc54d-e1c4-42c7-9bfe-596396c2d165")
    private DisplayAnchorFeedbackHelper anchorFbHelper;

    @objid ("9e7733c9-12a4-443a-9a74-5946801ae9f8")
    @Override
    public void activate() {
        super.activate();
        
        // Sales!!! Two policies for the price of one !
        EditPart host = getHost();
        host.installEditPolicy(LayoutNodeConnectionsEditPolicy.ROLE, new LayoutNodeConnectionsEditPolicy(host));
        
    }

    @objid ("46f2298d-1aad-4f9d-95c8-d3f5ed8ec4a8")
    @Override
    public void deactivate() {
        if (this.anchorFbHelper != null) {
            this.anchorFbHelper.removeAllFeedbacks();
            this.anchorFbHelper = null;
        }
        
        getHost().removeEditPolicy(LayoutNodeConnectionsEditPolicy.ROLE);
        
        super.deactivate();
        
    }

    @objid ("80c07c16-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createDragSourceFeedbackFigure() {
        // Use a ghost rectangle for feedback
        RectangleFigure r = new RectangleFigure();
        FigureUtilities2.makeGhostShape(r, getHostFigure());
        addFeedback(r);
        return r;
    }

    @objid ("80c07c0f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected List<?> createSelectionHandles() {
        return new SelectionHandlesBuilder((GraphicalEditPart) getHost())
                .withResizeDirections(getResizeDirections())
                .withDragAllowed(isDragAllowed())
                .addResizeableHandles()
                .getHandles();
        
    }

    @objid ("5e6bf194-e382-43c5-baf1-efd2fd09f50f")
    @Override
    protected void eraseChangeBoundsFeedback(ChangeBoundsRequest request) {
        if (this.anchorFbHelper != null) {
            this.anchorFbHelper.removeAllFeedbacks();
            this.anchorFbHelper = null;
        }
        
        super.eraseChangeBoundsFeedback(request);
        
    }

    /**
     * Get the main node edit part.
     * <p>
     * The main node is the host by default. May be redefined on port container policies to return their main node edit part.
     * @return the main node host edit part.
     */
    @objid ("0e50b931-3a4e-49b5-928e-50be97e4fc48")
    protected EditPart getMainNodeEditPart() {
        return getHost();
    }

    @objid ("80c07c1d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getOrphanCommand(Request request) {
        ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_ORPHAN_CHILDREN);
        req.setEditParts(getHost());
        
        ChangeBoundsRequest cbRequest = (ChangeBoundsRequest) request;
        req.setMoveDelta(cbRequest.getMoveDelta());
        req.setSizeDelta(cbRequest.getSizeDelta());
        req.setLocation(cbRequest.getLocation());
        req.setExtendedData(request.getExtendedData());
        RequestHelper.addSharedEditParts(req, cbRequest);
        
        EditPart parent = getHost().getParent();
        return parent != null ? parent.getCommand(req) : null;
    }

    @objid ("4f06f345-3934-4372-8835-be9d03c4fcac")
    @Override
    protected ResizeTracker getResizeTracker(int direction) {
        return new DefaultResizeTracker((GraphicalEditPart) getHost(), direction);
    }

    @objid ("3d5f0f04-9b9a-46a9-a56a-3ddb6849d33f")
    protected boolean isModelUserEditable() {
        IGmObject m = (IGmObject) getHost().getModel();
        return m.isUserEditable();
    }

    @objid ("1998114c-8abc-4b81-8c99-d87e2ac2f29b")
    @Override
    protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
        super.showChangeBoundsFeedback(request);
        
        // record the feedback figure in the request
        IFigure feedbackFigure = getDragSourceFeedbackFigure();
        ChangeBoundsFeedbackMap.getOrCreate(request).put(getHost(), feedbackFigure);
        
        if (this.anchorFbHelper == null) {
            IFixedConnectionAnchorFactory anchorFactory = getMainNodeEditPart().getAdapter(IFixedConnectionAnchorFactory.class);
            if (anchorFactory != null) {
                this.anchorFbHelper = new DisplayAnchorFeedbackHelper(getHost(), feedbackFigure, anchorFactory, getFeedbackLayer());
            }
        }
        if (this.anchorFbHelper != null) {
            this.anchorFbHelper.showTargetFeedback(request);
        }
        
    }

}
