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
import org.eclipse.gef.handles.AbstractHandle;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.figures.FigureUtilities2;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.model.IGmObject;

/**
 * A local specialisation of {@link ResizableEditPolicy} is used by default for children. <br>
 * <br>
 * This specialisation adds a behaviour on ORPHAN requests similar to what is done in {@link NonResizableEditPolicy#getMoveCommand}, meaning that it actually dispatches the request to the host's parent edit part as a
 * {@link RequestConstants#REQ_ORPHAN_CHILDREN} request. The parent's contribution is returned.<br>
 * <br>
 * Subclasses may override this method to supply a different EditPolicy.
 * 
 * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChildEditPolicy(EditPart)
 * @see org.eclipse.gef.editpolicies.NonResizableEditPolicy#getMoveCommand
 */
@objid ("80be19de-1dec-11e2-8cad-001ec947c8cc")
public class DefaultNodeNonResizableEditPolicy extends NonResizableEditPolicy {
    @objid ("d0d7ca6f-a00d-454b-b306-179d968068db")
    @Override
    public void activate() {
        super.activate();
        
        // Sales!!! Two policies for the price of one !
        EditPart host = getHost();
        host.installEditPolicy(LayoutNodeConnectionsEditPolicy.ROLE, new LayoutNodeConnectionsEditPolicy(host));
        
    }

    @objid ("0a978f1f-1ec7-4a2d-8c94-25e3be174f2f")
    @Override
    public void deactivate() {
        getHost().removeEditPolicy(LayoutNodeConnectionsEditPolicy.ROLE);
        
        super.deactivate();
        
    }

    @objid ("80be19e2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected List<AbstractHandle> createSelectionHandles() {
        return new SelectionHandlesBuilder((GraphicalEditPart) getHost())
                .withDragAllowed(isDragAllowed())
                .addNonResizeableHandles()
                .getHandles();
        
    }

    @objid ("80c07be1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createDragSourceFeedbackFigure() {
        // Use a ghost rectangle for feedback
        RectangleFigure r = new RectangleFigure();
        FigureUtilities2.makeGhostShape(r, getHostFigure());
        addFeedback(r);
        return r;
    }

    @objid ("80c07be8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getOrphanCommand(Request request) {
        ChangeBoundsRequest req = new ChangeBoundsRequest(REQ_ORPHAN_CHILDREN);
        req.setEditParts(getHost());
        
        ChangeBoundsRequest cbRequest = (ChangeBoundsRequest) request;
        req.setMoveDelta(cbRequest.getMoveDelta());
        req.setSizeDelta(cbRequest.getSizeDelta());
        req.setLocation(cbRequest.getLocation());
        req.setExtendedData(request.getExtendedData());
        RequestHelper.addSharedEditParts(req, cbRequest);
        return getHost().getParent().getCommand(req);
    }

    @objid ("94116395-e119-4a2f-b20a-2392b5f0aa50")
    protected boolean isModelUserEditable() {
        IGmObject m = (IGmObject) getHost().getModel();
        return m.isUserEditable();
    }

}
