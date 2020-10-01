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
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.commands.SimpleModeDeferredCreateCommand;
import org.modelio.diagram.elements.core.commands.SimpleModeDeferredGroupCommand;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Edit policy that delegates {@link RequestConstants#REQ_CREATE REQ_CREATE}, {@link RequestConstants#REQ_ADD REQ_ADD},
 * {@link RequestConstants#REQ_MOVE REQ_MOVE} and {@link RequestConstants#REQ_CLONE REQ_CLONE} requests to the child edit parts that
 * should contain the involved model element metaclass.
 * <p>
 * This policy is intended to be used on elements that represented in simple mode.
 * <p>
 * {@link GmCompositeNode#getCompositeFor(Class)} is called on the host model in order to find the right edit part.
 * <p>
 * If the child node model not displayed at the time the request is send, the corresponding edit part does not exist. So the policy
 * builds a command that when executed will first switch the representation mode to STRUCTURED, then make the node as visible and
 * then forward the request to the corresponding child edit part.
 * 
 * @see SimpleModeDeferredCreateCommand
 * @see SimpleModeDeferredGroupCommand
 * @author cmarin
 */
@objid ("80d5f137-1dec-11e2-8cad-001ec947c8cc")
public class SimpleModeDeferringCreateNodePolicy extends DefaultCreateNodeEditPolicy {
    @objid ("80d5f139-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getAddCommand(Request request) {
        return new SimpleModeDeferredGroupCommand((GroupRequest) request, getHost());
    }

    @objid ("80d5f143-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPart getChangeBoundsTargetEditPart(ChangeBoundsRequest req) {
        // Add/move/clone request : many elements are involved.
        // Look for an edit part that can handle the whole request with
        // all its involved elements, or null if none found.
        // We could later try to split the request by metaclass and let each inner
        // part handle what it can.
        EditPart ret = null;
        for (Object f : req.getEditParts()) {
            final EditPart part = (EditPart) f;
            final Object model = part.getModel();
            if (model instanceof GmModel) {
                final GmModel gm = (GmModel) model;
                final EditPart target = getEditPartFor(gm.getRelatedMClass().getJavaInterface());
                if (ret == null) {
                    ret = target;
                } else if (ret != target) {
                    return null;
                }
            }
        }
        return ret;
    }

    @objid ("80d5f14d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getCloneCommand(ChangeBoundsRequest request) {
        return new SimpleModeDeferredGroupCommand(request, getHost());
    }

    @objid ("80d5f157-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getCreateCommand(CreateRequest createReq) {
        return new SimpleModeDeferredCreateCommand(createReq, getHost());
    }

    @objid ("80d5f161-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPart getCreateTargetEditPart(CreateRequest createRequest) {
        ModelioCreationContext ctx = ModelioCreationContext.lookRequest(createRequest);
        
        if (ctx != null) {
            return getEditPartFor(ctx.getMetaclass().getJavaInterface());
        } else {
            return null;
        }
    }

    /**
     * Get the child EditPart where elements of the given metaclass are displayed.
     * <p>
     * {@link GmCompositeNode#getCompositeFor(Class)} is used on the model in order to find the right edit part.
     * <p>
     * If no child model is found, return <tt>null</tt>.<br>
     * If the found model has no edit part, return the host edit part.
     * <p>
     * 
     * @param metaclass The metaclass to create or drop.
     * @return <ul>
     * <li><tt>null</tt> if no suitable child model could be found <li>{@link #getHost()} if the child model is not
     * displayed <li>The found child model edit part if one suitable is found.
     * </ul>
     */
    @objid ("80d5f16b-1dec-11e2-8cad-001ec947c8cc")
    private EditPart getEditPartFor(Class<? extends MObject> metaclass) {
        Object model = getHost().getModel();
        if (!(model instanceof GmCompositeNode)) {
            return null;
        }
        final GmCompositeNode gmNode = (GmCompositeNode) model;
        
        final GmCompositeNode gmTargetChild = gmNode.getCompositeFor(metaclass);
        
        if (gmTargetChild == null || !gmTargetChild.canCreate(metaclass)) {
            return null;
        }
        
        final EditPart p = (EditPart) getHost().getRoot().getViewer().getEditPartRegistry().get(gmTargetChild);
        if (p != null) {
            return p;
        } else {
            return getHost();
        }
    }

}
