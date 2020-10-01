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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.DefaultCreateElementCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This policy is responsible for moving, resizing, reparenting, and creating the children of the group. It should provide proper
 * Commands for all of these operations.
 */
@objid ("80b954e7-1dec-11e2-8cad-001ec947c8cc")
public class DefaultCreateNodeEditPolicy extends AutoExpandLayoutEditPolicy {
    @objid ("80b954eb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (REQ_CREATE.equals(request.getType())) {
            CreateRequest createRequest = (CreateRequest) request;
            return getCreateTargetEditPart(createRequest);
        }
        if (REQ_ADD.equals(request.getType())
                || REQ_CLONE.equals(request.getType())
                || REQ_MOVE.equals(request.getType())) {
            ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) request;
            return getChangeBoundsTargetEditPart(changeBoundsRequest);
        }
        return null;
    }

    /**
     * Returns whether this edit policy can handle this metaclass (either through simple or smart behavior). Default behavior is to
     * accept any metaclass that can be child (in the CreationExpert's understanding) of the host's metaclass This method should be
     * overridden by subclasses to add specific the behavior.
     * 
     * @param metaclass the metaclass to handle.
     * @return true if this policy can handle the metaclass.
     */
    @objid ("80b954f5-1dec-11e2-8cad-001ec947c8cc")
    protected boolean canHandle(MClass metaclass) {
        MClass hostMetaclass = getHostElement().getMClass();
        MExpert expert = hostMetaclass.getMetamodel().getMExpert();
        return expert.canCompose(hostMetaclass, metaclass, null)
                                                                                && ((GmCompositeNode) getHost().getModel()).canCreate(metaclass.getJavaInterface());
    }

    /**
     * Returns <code>null</code> or the appropriate <code>EditPart</code> for the specified CLONE, MOVE or ADD
     * <code>ChangeBoundsRequest</code>.
     * <p>
     * Return the host edit part if this policy can handle all edit parts involved in the request.
     * 
     * @param changeBoundsRequest the request, can be CLONE, MOVE or ADD.
     * @return the host edit part if all edit parts involved in the request can be handled by this policy, <code>null</code>
     * otherwise.
     */
    @objid ("80b95507-1dec-11e2-8cad-001ec947c8cc")
    protected EditPart getChangeBoundsTargetEditPart(ChangeBoundsRequest changeBoundsRequest) {
        for (Object editPartObj : changeBoundsRequest.getEditParts()) {
            // If there is at least 1 element that this policy cannot
            // handle, do not handle the request at all!
            final EditPart editPart = (EditPart) editPartObj;
            if (editPart.getModel() instanceof GmModel) {
                final GmModel gmModel = (GmModel) editPart.getModel();
        
                if (!canHandle(gmModel.getRelatedMClass())) {
                    return null;
                }
            }
        }
        // This policy can handle all elements of this request: handle it!
        return getHost();
    }

    @objid ("80b95511-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getCreateCommand(CreateRequest req) {
        ModelioCreationContext ctx = ModelioCreationContext.fromRequest(req);
        
        MClass metaclassToCreate = ctx.getMetaclass();
        String depName = ctx.getDependencyName();
        MObject hostElement = getHostElement();
        MExpert expert = metaclassToCreate.getMetamodel().getMExpert();
        
        boolean returnCommand = expert.canCompose(hostElement.getMClass(), metaclassToCreate, depName);
        if (returnCommand) {
            Rectangle requestRect = new Rectangle(req.getLocation(), new Dimension(-1, -1));
            return new DefaultCreateElementCommand(hostElement, (GmCompositeNode) getHost().getModel(), ctx, requestRect);
        }
        return null;
    }

    /**
     * Returns <code>null</code> or the appropriate <code>EditPart</code> for the specified <code>CreateRequest</code>.
     * <p>
     * Return the host edit part if this policy can handle the metaclass involved in the request.
     * 
     * @param createRequest the request.
     * @return the host edit part if the metaclass involved in the request can be handled by this policy, <code>null</code>
     * otherwise.
     */
    @objid ("80b9551b-1dec-11e2-8cad-001ec947c8cc")
    protected EditPart getCreateTargetEditPart(CreateRequest createRequest) {
        final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(createRequest);
        if (ctx != null) {
            if (!canHandle(ctx.getMetaclass())) {
                return null;
            }
        }
        return getHost();
    }

    /**
     * Returns the MObject represented by the host's model. Might be <code>null</code>!
     * 
     * @return the MObject represented by the host's model.
     */
    @objid ("80b95525-1dec-11e2-8cad-001ec947c8cc")
    protected MObject getHostElement() {
        if (getHost().getModel() instanceof GmModel) {
            return ((GmModel) getHost().getModel()).getRelatedElement();
        }
        return null;
    }

    @objid ("80b9552a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getMoveChildrenCommand(Request request) {
        return null;
    }

}
