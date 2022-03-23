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
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Generic policy that delegates the {@link #getTargetEditPart(Request)} method call to another edit part (determined by a call to the host's getCompositeFor method).
 * <p>
 * All other methods will do nothing and/or return <code>null</code>.
 * 
 * @author fpoyer
 */
@objid ("80d12c67-1dec-11e2-8cad-001ec947c8cc")
public class DelegatingEditPolicy implements EditPolicy {
    /**
     * The EditPart this policy is installed on.
     */
    @objid ("680fabf5-1e83-11e2-8cad-001ec947c8cc")
    private EditPart host;

    /**
     * Activates this EditPolicy. The EditPolicy might need to hook listeners. These listeners should be unhooked in <code>deactivate()</code>. The EditPolicy might also contribute feedback/visuals immediately, such as <i>selection handles</i> if the
     * EditPart was selected at the time of activation.
     * <P>
     * Activate is called after the <i>host</i> has been set, and that host has been activated.
     * @see EditPart#activate()
     * @see #deactivate()
     * @see EditPart#installEditPolicy(Object, EditPolicy)
     */
    @objid ("80d12c6f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void activate() {
        // Nothing specific to do.
    }

    /**
     * Deactivates the EditPolicy, the inverse of {@link #activate()}. Deactivate is called when the <i>host</i> is deactivated, or when the EditPolicy is uninstalled from an active host. Deactivate unhooks any listeners, and removes all feedback.
     * @see EditPart#deactivate()
     * @see #activate()
     * @see EditPart#removeEditPolicy(Object)
     */
    @objid ("80d12c73-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void deactivate() {
        // Nothing specific to do.
    }

    /**
     * Erases source feedback based on the given <code>Request</code>. Does nothing if the EditPolicy does not apply to the given Request.
     * <P>
     * This method is declared on {@link EditPart#eraseSourceFeedback(Request) EditPart}, and is redeclared here so that EditPart can delegate its implementation to each of its EditPolicies.
     * @param request the Request
     */
    @objid ("80d12c77-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void eraseSourceFeedback(Request request) {
        // Nothing specific to do.
    }

    /**
     * Erases target feedback based on the given <code>Request</code>. Does nothing if the EditPolicy does not apply to the given Request.
     * <P>
     * This method is declared on {@link EditPart#eraseTargetFeedback(Request) EditPart}, and is redeclared here so that EditPart can delegate its implementation to each of its EditPolicies.
     * @param request the Request
     */
    @objid ("80d12c7e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void eraseTargetFeedback(Request request) {
        // Nothing specific to do.
    }

    /**
     * Returns the <code>Command</code> contribution for the given <code>Request</code>, or <code>null</code>. <code>null</code> is treated as a no-op by the caller, or an empty contribution. The EditPolicy must return an
     * {@link org.eclipse.gef.commands.UnexecutableCommand} if it wishes to disallow the Request.
     * <P>
     * This method is declared on {@link EditPart#getCommand(Request) EditPart}, and is redeclared here so that EditPart can delegate its implementation to each of its EditPolicies. The EditPart will combine each EditPolicy's contribution into a
     * {@link org.eclipse.gef.commands.CompoundCommand}.
     * @param request the Request
     * @return <code>null</code> or a Command contribution
     */
    @objid ("80d12c85-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Command getCommand(Request request) {
        return null;
    }

    @objid ("80d12c90-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public EditPart getHost() {
        return this.host;
    }

    @objid ("80d12c97-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (RequestConstants.REQ_CREATE == request.getType()) {
            return getTargetEditPartForCreateRequest((CreateRequest) request);
        } else if (RequestConstants.REQ_ADD.equals(request.getType()) || RequestConstants.REQ_MOVE.equals(request.getType()) || RequestConstants.REQ_CLONE.equals(request.getType())) {
            return getTargetEditPartForGroupRequest((GroupRequest) request);
        }
        
        // No satisfying edit part found...
        return null;
    }

    @objid ("80d12ca1-1dec-11e2-8cad-001ec947c8cc")
    private EditPart getTargetEditPartForGroupRequest(GroupRequest gr) {
        // Add/move/clone request : many elements are involved.
        // Look for an edit part that can handle the whole request with
        // all its involved elements, or null if none found.
        EditPart ret = null;
        for (Object f : gr.getEditParts()) {
            final EditPart part = (EditPart) f;
            final Object model = part.getModel();
            if (model instanceof GmModel) {
                final GmModel gm = (GmModel) model;
                final EditPart targetEditPart = getEditPartFor(gm.getRelatedMClass().getJavaInterface());
        
                // No known edit part can handle that metaclass, consider we cannot the request cannot handled.
                if (targetEditPart == null) {
                    return null;
                }
        
                if (ret == null) {
                    // First time, initialize...
                    ret = targetEditPart;
                } else if (ret != targetEditPart) {
                    // ... then compare: if change, consider the request cannot handled
                    return null;
                }
            }
        }
        if (ret == null) {
            return null;
        }
        // else
        if (ret.equals(getHost())) {
            return ret;
        }
        // else
        return ret.getTargetEditPart(gr);
    }

    @objid ("80d38eb7-1dec-11e2-8cad-001ec947c8cc")
    private EditPart getTargetEditPartForCreateRequest(CreateRequest createReq) {
        // Creation request, only one element is involved
        ModelioCreationContext ctx = ModelioCreationContext.lookRequest(createReq);
        if (ctx != null) {
            EditPart targetEditPart = getEditPartFor(ctx.getMetaclass().getJavaInterface());
            if (targetEditPart == null) {
                return null;
            } else if (targetEditPart.equals(getHost())) {
                return targetEditPart;
            } else {
                return targetEditPart.getTargetEditPart(createReq);
            }
        } else {
            return null;
        }
        
    }

    @objid ("80d38ec0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setHost(EditPart editpart) {
        this.host = editpart;
    }

    /**
     * Shows or updates <i>source feedback</i> for the specified <code>Request</code>. This method may be called repeatedly for the purpose of updating feedback based on changes to the Request.
     * <P>
     * Does nothing if the EditPolicy does not recognize the given Request.
     * <P>
     * This method is declared on {@link EditPart#showSourceFeedback(Request) EditPart}, and is redeclared here so that EditPart can delegate its implementation to each of its EditPolicies.
     * @param request the Request
     */
    @objid ("80d38ec6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void showSourceFeedback(Request request) {
        // Nothing specific to do.
    }

    /**
     * Shows or updates <i>target feedback</i> for the specified <code>Request</code>. This method may be called repeatedly for the purpose of updating feedback based on changes to the Request.
     * <P>
     * Does nothing if the EditPolicy does not recognize the given request.
     * <P>
     * This method is declared on {@link EditPart#showTargetFeedback(Request) EditPart}, and is redeclared here so that EditPart can delegate its implementation to each of its EditPolicies.
     * @param request the Request
     */
    @objid ("80d38ecd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void showTargetFeedback(Request request) {
        // Nothing specific to do.
    }

    /**
     * Returns <code>true</code> if this EditPolicy understand the specified request.
     * <P>
     * This method is declared on {@link EditPart#understandsRequest(Request) EditPart}, and is redeclared here so that EditPart can delegate its implementation to each of its EditPolicies. <code>EditPart</code> returns <code>true</code> if any of its
     * EditPolicies returns <code>true</code>. In other words, it performs a logical OR.
     * @see EditPart#understandsRequest(Request)
     * @param request the Request
     * @return boolean <code>true</code> if the EditPolicy understands the specified request
     */
    @objid ("80d38ed4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean understandsRequest(Request request) {
        // Nothing specific to do.
        return false;
    }

    /**
     * Get the child EditPart where elements of the given metaclass are displayed.
     * <p>
     * {@link GmCompositeNode#getCompositeFor(Class)} is used on the model in order to find the right edit part.
     * <p>
     * If no child model is found, return <tt>null</tt>.<br>
     * If the found model is not visible, return the host edit part.
     * <p>
     * @param metaclass The metaclass to create or drop.
     * @return
     * <ul>
     * <li><tt>null</tt> if no suitable child model could be found
     * <li>{@link #getHost()} if the child model is not visible
     * <li>The found child model edit part if one suitable is found.
     * </ul>
     */
    @objid ("80d38edd-1dec-11e2-8cad-001ec947c8cc")
    private EditPart getEditPartFor(Class<? extends MObject> metaclass) {
        final GmCompositeNode gmNode = (GmCompositeNode) getHost().getModel();
        
        final GmCompositeNode gmTargetChild = gmNode.getCompositeFor(metaclass);
        
        if (gmTargetChild == null || !gmTargetChild.isVisible()) {
            return null;
        }
        
        final EditPart p = (EditPart) getHost().getRoot().getViewer().getEditPartRegistry().get(gmTargetChild);
        return p;
    }

}
