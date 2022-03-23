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
package org.modelio.uml.sequencediagram.editor.elements.combinedfragment.primarynode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.common.resizablegroup.GmResizableGroup;
import org.modelio.diagram.elements.common.resizablegroup.ResizableGroupLayoutEditPolicy;
import org.modelio.diagram.elements.common.resizablegroup.ResizeChildrenCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.uml.sequencediagram.editor.elements.interactionoperand.CreateInteractionOperandCommand;

/**
 * Specialisation of the {@link ResizableGroupLayoutEditPolicy} policy to handle the ObModel additionally.
 * 
 * @author fpoyer
 */
@objid ("d8d12e68-55b6-11e2-877f-002564c97630")
public class InteractionOperandContainerLayoutEditPolicy extends ResizableGroupLayoutEditPolicy {
    @objid ("d8d12e6c-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getCreateCommand(final CreateRequest request) {
        ModelioCreationContext ctx = ModelioCreationContext.lookRequest(request);
        if (ctx != null) {
            EditPart insertAfter = getInsertionReference(request);
            GmNodeModel insertAfterModel = insertAfter == null ? null : (GmNodeModel) insertAfter.getModel();
            getHostFigure().revalidate();
            int constraint = 50;
            return new CreateInteractionOperandCommand(getHost(), ctx, insertAfterModel, constraint);
        } else {
            return null;
        }
        
    }

    @objid ("d8d12e73-55b6-11e2-877f-002564c97630")
    @Override
    protected Command createMoveChildCommand(final EditPart child, final EditPart after) {
        GmNodeModel reference = null;
        if (after != null) {
            reference = (GmNodeModel) after.getModel();
        }
        ReorderOperandsCommand command = new ReorderOperandsCommand(getHostCompositeNode(),
                (GmNodeModel) child.getModel(),
                reference);
        return command;
    }

    @objid ("d8d12e7c-55b6-11e2-877f-002564c97630")
    private GmResizableGroup getHostCompositeNode() {
        return (GmResizableGroup) this.getHost().getModel();
    }

    @objid ("d8d12e82-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getResizeChildrenCommand(final ChangeBoundsRequest request) {
        // Resizing children: resize children as they asked (as much as
        // possible) and also resize their "neighbour" to keep the whole
        // container the same size. If there is no neighbour (resized child is
        // either the first or the last), in that particular case then
        // try to resize the container.
        CompoundCommand compound = new CompoundCommand();
        ResizeChildrenCommand command = new ResizeChildrenCommand(getHostCompositeNode());
        List<?> resizedEditParts = request.getEditParts();
        Map<GmNodeModel, Integer> newConstraints = new HashMap<>();
        // int newHeight = -1;
        for (int i = 0; i < resizedEditParts.size(); i++) {
            GraphicalEditPart resizedChild = (GraphicalEditPart) resizedEditParts.get(i);
            Dimension constraint = getConstraintFor(request, resizedChild).getSize();
            newConstraints.put((GmNodeModel) resizedChild.getModel(),
                    isHorizontal() ? Integer.valueOf(constraint.width)
                            : Integer.valueOf(constraint.height));
            // Get the impacted neighbour:
            GraphicalEditPart impactedNeighbour = getImpactedNeighbour(resizedChild, request);
            if (impactedNeighbour != null) {
                // Resize neighbour to compensate for size change of
                // resizedChild.
                ChangeBoundsRequest inverseRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
                inverseRequest.setEditParts(impactedNeighbour);
                inverseRequest.setLocation(request.getLocation());
                inverseRequest.setSizeDelta(request.getSizeDelta().getNegated());
                inverseRequest.setResizeDirection(request.getResizeDirection());
                RequestHelper.addSharedEditParts(inverseRequest, request);
                Dimension neighbourConstraint = getConstraintFor(inverseRequest, impactedNeighbour).getSize();
                newConstraints.put((GmNodeModel) impactedNeighbour.getModel(),
                        isHorizontal() ? Integer.valueOf(neighbourConstraint.width)
                                : Integer.valueOf(neighbourConstraint.height));
            } else if (getHost().getChildren().indexOf(resizedChild) == 0) {
                // First child: request a resize of the the container and append the
                // resulting command to the returned command.
                // Ask that container parent is resized (not container itself,
                // as it is only meant to be a child)
                ChangeBoundsRequest resizeContainerRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
                resizeContainerRequest.setEditParts(getHost().getParent());
                resizeContainerRequest.setLocation(request.getLocation());
                resizeContainerRequest.setResizeDirection(request.getResizeDirection());
                RequestHelper.addSharedEditParts(resizeContainerRequest, request);
                Dimension sizeDelta = request.getSizeDelta().getCopy();
                // Only ask to be resized in the "major" axis.
                if (isHorizontal()) {
                    sizeDelta.height = 0;
                } else {
                    sizeDelta.width = 0;
                }
                resizeContainerRequest.setSizeDelta(sizeDelta);
        
                Command parentCommand = getHost().getParent().getCommand(resizeContainerRequest);
                compound.add(parentCommand);
            }
        }
        command.setNewConstraints(newConstraints);
        compound.add(command);
        return compound.unwrap();
    }

    @objid ("d8d12e89-55b6-11e2-877f-002564c97630")
    private GraphicalEditPart getImpactedNeighbour(final GraphicalEditPart resizedChild, final ChangeBoundsRequest request) {
        // Previous child is initially "null", indicating there is no neighbour
        // on the left of first child.
        GraphicalEditPart previousChild = null;
        List<GraphicalEditPart> nextChildren = new ArrayList<>(getHost().getChildren()
                .size());
        for (Object childObj : getHost().getChildren()) {
            nextChildren.add((GraphicalEditPart) childObj);
        }
        nextChildren.removeAll(request.getEditParts());
        // Add "null" at the end, indicating there is no neighbour on the right
        // of last child.
        nextChildren.add(null);
        
        for (Object childObj : getHost().getChildren()) {
            GraphicalEditPart child = (GraphicalEditPart) childObj;
            if (child.equals(resizedChild)) {
                // Depending on the resize direction, return either previous or
                // next child, or null.
                // If movement to the south, return next child
                if (request.getSizeDelta().height - request.getMoveDelta().y != 0) {
                    // Watch out: first element if the nextChildren list might
                    // be the current child, in which case just skip over it.
                    if (child.equals(nextChildren.get(0))) {
                        return nextChildren.get(1);
                    }
                    // else
                    return nextChildren.get(0);
                }
                // If movement to the north, return previous child
                if (request.getMoveDelta().y != 0) {
                    return previousChild;
                }
                // else
                return null;
        
            }
            // Update the nextChildren list by removing current child (note that
            // first element of nextChildren may NOT be current child, since we
            // already removed all resized children from it) and add it as the
            // previous child for next loop.
            if (child.equals(nextChildren.get(0))) {
                nextChildren.remove(0);
                previousChild = child;
            }
        
        }
        // Not found, something is wrong here
        throw new IllegalArgumentException(resizedChild + " edit part is not a child of current container " + getHost());
        
    }

}
