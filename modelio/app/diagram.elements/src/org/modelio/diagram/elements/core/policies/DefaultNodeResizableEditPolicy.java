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
import org.modelio.diagram.elements.core.model.IGmObject;

/**
 * A local specialization of {@link ResizableEditPolicy} is used by default for children. <br>
 * <br>
 * This specialization adds a behaviour on ORPHAN requests similar to what is done in {@link NonResizableEditPolicy#getMoveCommand},
 * meaning that it actually dispatches the request to the host's parent edit part as a {@link RequestConstants#REQ_ORPHAN_CHILDREN}
 * request. The parent's contribution is returned.<br>
 * <br>
 * Subclasses may override this method to supply a different EditPolicy.
 * 
 * @see org.eclipse.gef.editpolicies.NonResizableEditPolicy#getMoveCommand
 */
@objid ("80c07c0b-1dec-11e2-8cad-001ec947c8cc")
public class DefaultNodeResizableEditPolicy extends ResizableEditPolicy {
    @objid ("80c07c0f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected List<?> createSelectionHandles() {
        return new SelectionHandlesBuilder((GraphicalEditPart) getHost())
                        .withResizeDirections(getResizeDirections())
                        .withDragAllowed(isDragAllowed())
                        .addResizeableHandles()
                        .getHandles();
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

    @objid ("80c07c1d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getOrphanCommand(Request request) {
        ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_ORPHAN_CHILDREN);
        req.setEditParts(getHost());
        
        req.setMoveDelta(((ChangeBoundsRequest) request).getMoveDelta());
        req.setSizeDelta(((ChangeBoundsRequest) request).getSizeDelta());
        req.setLocation(((ChangeBoundsRequest) request).getLocation());
        req.setExtendedData(request.getExtendedData());
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

}
