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

package org.modelio.diagram.elements.drawings.core.policies;

import java.util.Arrays;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.NodeChangeLayoutCommand;
import org.modelio.diagram.elements.drawings.core.IGmDrawing;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLayer;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLink;
import org.modelio.diagram.elements.drawings.core.IGmNodeDrawing;
import org.modelio.diagram.elements.drawings.layer.GmDrawingLayer;

/**
 * Layout Edit policy for containers accepting drawings.
 */
@objid ("8bddee34-3f6c-4333-8218-846f593665d2")
public class DrawingContainerEditLayoutPolicy extends XYLayoutEditPolicy {
    @objid ("a55eb4fc-34c2-4478-9131-500994cf8507")
    private static final List<String> ACCEPTED_REQUESTS = Arrays.asList(REQ_MOVE, REQ_ADD, REQ_CLONE, REQ_ALIGN);

    @objid ("2d8c0c39-623b-4af9-8e1d-86b804436070")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (REQ_CREATE.equals(request.getType())) {
            final CreateRequest createRequest = (CreateRequest) request;
            final Object objType = createRequest.getNewObjectType();
            if (objType instanceof Class && IGmNodeDrawing.class.isAssignableFrom((Class<?>) objType)) {
                return getHost();
            }
            return null;
        }
        else if (ACCEPTED_REQUESTS.contains(request.getType())) {
            final ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) request;
            for (Object o : changeBoundsRequest.getEditParts()) {
                final Object model = ((EditPart)o).getModel();
                if (! (model instanceof IGmDrawing))
                    return null;
                
                // Process only moves/clones inside the same layer
                IGmDrawing drawing = (IGmDrawing) model;
                if (drawing.getLayer() != getHost().getModel())
                    return null;
            }
            
            return getHost();
        }
        return super.getTargetEditPart(request);
    }

    @objid ("7ca5462d-fc5d-48da-b235-4a6fc189544b")
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        final Object objType = request.getNewObjectType();
        if (objType instanceof Class && IGmNodeDrawing.class.isAssignableFrom((Class<?>) objType)) {
            @SuppressWarnings("unchecked")
            final Class<? extends IGmNodeDrawing> nodeClass = (Class<? extends IGmNodeDrawing>) objType;
            final Object requestConstraint = getConstraintFor(request);
            final GmDrawingLayer gm = (GmDrawingLayer) getHost().getModel();
            
            String drawingIdent = (String) request.getNewObject();
            
            return new DefaultCreateGmNodeDrawingCommand( gm, nodeClass, drawingIdent, requestConstraint);
        }
        return null;
    }

    @objid ("6627fa86-de50-4d36-b972-1571a9524ac3")
    @Override
    protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
        // if child is a 'node' it usually can be resized and/or moved
        final Object model = child.getModel();
        if (model instanceof IGmNodeDrawing) {
            IGmNodeDrawing drawing = (IGmNodeDrawing) model;
            if (drawing.getLayer() == getHost().getModel()) {
                final NodeChangeLayoutCommand command = new NodeChangeLayoutCommand();
                command.setModel(model);
                command.setConstraint(constraint);
                return command;
            }
        }
        return null;
    }

    /**
     * Overrides <code>getAddCommand()</code> to generate the proper constraint
     * for each child being added. Once the constraint is calculated,
     * {@link #createCloneCommand(ChangeBoundsRequest, GraphicalEditPart, Object)} is called. Subclasses must
     * implement this method.
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getAddCommand(Request)
     */
    @objid ("5f3b6579-42d0-4419-8bf6-9a535d3bec23")
    @Override
    protected Command getCloneCommand(ChangeBoundsRequest request) {
        List<?> editParts = request.getEditParts();
        CompoundCommand command = new CompoundCommand();
        command.setDebugLabel("Clone in "+DrawingContainerEditLayoutPolicy.class.getSimpleName());//$NON-NLS-1$
        GraphicalEditPart child;
        
        for (int i = 0; i < editParts.size(); i++) {
            child = (GraphicalEditPart) editParts.get(i);
            command.add(createCloneCommand(
                    request,
                    child,
                    translateToModelConstraint(getConstraintFor(request, child))));
        }
        return command.unwrap();
    }

    /**
     * Returns the <code>Command</code> to clone the specified edit part and
     * move it to the specified child's
     * constraint. The constraint has been converted from a draw2d constraint to
     * an object suitable for the model.
     * 
     * @param request the ChangeBoundsRequest
     * @param child the EditPart of the child being changed
     * @param modelConstraint the new constraint, after being
     * {@link #translateToModelConstraint(Object) translated}
     * @return A Command to change the constraints of the given child as
     * specified in the given request
     */
    @objid ("b1853dc2-adae-4de7-9e10-be580091f257")
    protected Command createCloneCommand(ChangeBoundsRequest request, GraphicalEditPart child, Object modelConstraint) {
        final Object model = child.getModel();
        if (model instanceof IGmNodeDrawing) {
            IGmDrawingLayer layer = (IGmDrawingLayer) getHost().getModel();
            IGmNodeDrawing nodeToCopy = (IGmNodeDrawing) model;
            
            // Copy only in the same layer
            if (nodeToCopy.getLayer() == layer) {
                return new CloneGmNodeDrawingCommand(layer, nodeToCopy, modelConstraint);
            }
        } else if (model instanceof IGmDrawingLink) {
            IGmDrawingLayer layer = (IGmDrawingLayer) getHost().getModel();
            
            if (((IGmDrawing) model).getLayer() == layer) {
                return new CloneGmLinkDrawingCommand((ConnectionEditPart) child, request.getMoveDelta());
            }
        }
        return null;
    }

}
