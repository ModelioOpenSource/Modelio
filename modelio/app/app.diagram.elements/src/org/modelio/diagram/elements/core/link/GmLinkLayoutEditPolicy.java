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
package org.modelio.diagram.elements.core.link;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.link.extensions.ChangeExtensionSizeCommand;
import org.modelio.diagram.elements.core.link.extensions.ConnectionEndpoinLocatorMoveEditPolicy;
import org.modelio.diagram.elements.core.link.extensions.FractionalConnectionLocatorMoveEditPolicy;
import org.modelio.diagram.elements.core.link.extensions.GmConnectionEndpoinLocator;
import org.modelio.diagram.elements.core.link.extensions.GmFractionalConnectionLocator;
import org.modelio.diagram.elements.core.link.extensions.IGmLocator;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;

/**
 * Edit policy to move connection extension labels.
 */
@objid ("802320d3-1dec-11e2-8cad-001ec947c8cc")
public class GmLinkLayoutEditPolicy extends LayoutEditPolicy {
    @objid ("802320d7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPolicy createChildEditPolicy(EditPart child) {
        final IGmObject gmExtension = (IGmObject) child.getModel();
        final IGmLink gmlink = (IGmLink) getHost().getModel();
        
        final IGmLocator locator = gmlink.getLayoutContraint(gmExtension);
        
        if (locator instanceof GmConnectionEndpoinLocator) {
            return new ConnectionEndpoinLocatorMoveEditPolicy();
        } else if (locator instanceof GmFractionalConnectionLocator) {
            return new FractionalConnectionLocatorMoveEditPolicy();
        } else {
            return null;
        }
        
    }

    @objid ("802320e0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        return null;
    }

    @objid ("802320ea-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getMoveChildrenCommand(Request request) {
        // Move requests are directly handled by children
        throw new IllegalStateException();
        
    }

    /**
     * Returns the <code>Command</code> to resize a group of children.
     * @param request the ChangeBoundsRequest
     * @return the Command
     */
    @objid ("802320f4-1dec-11e2-8cad-001ec947c8cc")
    protected Command getResizeChildrenCommand(ChangeBoundsRequest request) {
        CompoundCommand resize = new CompoundCommand();
        Command c;
        GraphicalEditPart child;
        List<?> children = request.getEditParts();
        
        for (int i = 0; i < children.size(); i++) {
            child = (GraphicalEditPart) children.get(i);
            c = createResizeCommand(request, child);
            resize.add(c);
        }
        return resize.unwrap();
    }

    /**
     * @param request
     * @param child
     * @param moveDelta @return
     */
    @objid ("802320fe-1dec-11e2-8cad-001ec947c8cc")
    private Command createResizeCommand(ChangeBoundsRequest request, GraphicalEditPart child) {
        IFigure figure = child.getFigure();
        
        Rectangle newRect = figure.getBounds().getCopy();
        figure.translateToAbsolute(newRect);
        newRect.resize(request.getSizeDelta());
        newRect.translate(request.getMoveDelta());
        figure.translateToRelative(newRect);
        
        final IGmObject gmExtension = (IGmObject) child.getModel();
        final IGmLink gmlink = (IGmLink) getHost().getModel();
        return new ChangeExtensionSizeCommand(figure, gmlink, gmExtension, newRect.getSize());
    }

    /**
     * Decorates the child with a {@link EditPolicy#PRIMARY_DRAG_ROLE} such as {@link ResizableEditPolicy}.
     * <p>
     * Redefined to do nothing if {@link #createChildEditPolicy(EditPart)} returns <code>null</code>.
     * @param child the child EditPart being decorated
     */
    @objid ("8023210e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void decorateChild(final EditPart child) {
        EditPolicy policy = createChildEditPolicy(child);
        if (policy != null) {
            child.installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, policy);
        }
        
    }

    @objid ("8025830a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public EditPart getTargetEditPart(final Request request) {
        if (RequestConstants.REQ_ADD.equals(request.getType()) ||
                RequestConstants.REQ_MOVE.equals(request.getType()) ||
                RequestConstants.REQ_CLONE.equals(request.getType())) {
            // Special case: MOVE and ADD request can be tricky: depending on the "previous loop" of the tool sending
            // the request, an ADD request can be send for a simple graphic move, and a MOVE request can be sent for an
            // actual graphic re-parenting. Let's analyse the request a bit to determine how to handle it.
            final ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) request;
            if (isMove(changeBoundsRequest)) {
                // This is a simple graphic move of a child inside this container, it should be accepted.
                return getHost();
            } else {
                // This is a clone or a graphic re-parenting: do not accept it.
                return null;
            }
        } else if (!RequestConstants.REQ_CREATE.equals(request.getType()) || ModelioCreationContext.lookRequest((CreateRequest) request) != null) {
            return super.getTargetEditPart(request);
        } else {
            return null;
        }
        
    }

    @objid ("80258314-1dec-11e2-8cad-001ec947c8cc")
    private boolean isMove(final ChangeBoundsRequest changeBoundsRequest) {
        // Start by excluding CLONE: this is never a move:
        if (RequestConstants.REQ_CLONE.equals(changeBoundsRequest.getType())) {
            return false;
        }
        // The request is actually a move request (not taking its type into account) if the primary selection's
        // parent edit part is the host.
        for (final Object o : changeBoundsRequest.getEditParts()) {
            final EditPart editPart = (EditPart) o;
            if (editPart.getSelected() == EditPart.SELECTED_PRIMARY) {
                if (editPart.getParent() != null && editPart.getParent().equals(getHost())) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    @objid ("d68e44f9-1bee-4fbe-8a46-cd86729d3ad4")
    @Override
    public Command getCommand(Request request) {
        if (RequestConstants.REQ_RESIZE_CHILDREN.equals(request.getType())) {
            return getResizeChildrenCommand((ChangeBoundsRequest) request);
        }
        return super.getCommand(request);
    }

}
