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

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.DropRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Allow creation of nodes on the link into zones that are not already displayed.
 * <p>
 * Defers the creation to the right {@link GmCompositeNode}.
 * 
 * @author cmarin
 */
@objid ("80c540c0-1dec-11e2-8cad-001ec947c8cc")
public class DeferringCreateNodeOnLinkEditPolicy extends LayoutEditPolicy {
    @objid ("80c540c4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final Command getAddCommand(final Request request) {
        if (getTargetEditPart(request) == null) {
            return null;
        }
        return new DeferredChangeBoundsCommand((ChangeBoundsRequest) request, getHost());
    }

    @objid ("80c540cf-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final Command getCloneCommand(final ChangeBoundsRequest request) {
        if (getTargetEditPart(request) == null) {
            return null;
        }
        return new DeferredChangeBoundsCommand(request, getHost());
    }

    @objid ("80c540da-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final Command getCreateCommand(final CreateRequest createReq) {
        if (getTargetEditPart(createReq) == null) {
            return null;
        }
        return new DeferredCreateCommand(createReq, getHost());
    }

    @objid ("80c7a2f6-1dec-11e2-8cad-001ec947c8cc")
    protected EditPart getEditPartFor(final Class<? extends MObject> metaclass, final Point location) {
        final GmLink gmLink = (GmLink) getHost().getModel();
        final GmCompositeNode gmTargetChild = getExtensionFor(gmLink, metaclass, location);
        
        if (gmTargetChild == null) {
            return null;
        }
        
        if (!gmTargetChild.isVisible()) {
            return getHost();
        }
        
        final EditPart p = (EditPart) getHost().getViewer().getEditPartRegistry().get(gmTargetChild);
        return p;
    }

    /**
     * Get the extension on which the information flow label must be added.
     * <p>
     * Looks for and return the first {@link GmCompositeNode} extension on the link.
     * <p>
     * Subclasses may override this method.
     * 
     * @param gmLink The model link
     * @param location The mouse location
     * @return The composite node where the label must be added.
     */
    @objid ("80c7a304-1dec-11e2-8cad-001ec947c8cc")
    protected GmCompositeNode getExtensionFor(final GmLink gmLink, final Class<? extends MObject> metaclass, final Point location) {
        GmCompositeNode gmTargetChild = null;
        for (GmNodeModel n : gmLink.getExtensions()) {
            if (n instanceof GmCompositeNode && ((GmCompositeNode) n).canCreate(metaclass)) {
                gmTargetChild = (GmCompositeNode) n;
                break;
            }
        }
        return gmTargetChild;
    }

    @objid ("80c7a313-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final EditPart getTargetEditPart(final Request request) {
        // System.out.println("getTarget("+request+") on "+getHost().getModel());
        if (REQ_CREATE.equals(request.getType())) {
            CreateRequest createRequest = (CreateRequest) request;
            return getCreateTargetEditPart(createRequest);
        }
        if (REQ_ADD.equals(request.getType()) || REQ_CLONE.equals(request.getType()) || REQ_MOVE.equals(request.getType())) {
            ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) request;
            return getChangeBoundsTargetEditPart(changeBoundsRequest);
        }
        if (REQ_RESIZE.equals(request.getType()) || REQ_RESIZE_CHILDREN.equals(request.getType())) {
            return null;
        }
        return null;
    }

    @objid ("80c7a31e-1dec-11e2-8cad-001ec947c8cc")
    private EditPart getCreateTargetEditPart(final CreateRequest createRequest) {
        // Creation request, only one element is involved
        final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(createRequest);
        if (ctx != null) {
            return getEditPartFor(ctx.getMetaclass().getJavaInterface(), createRequest.getLocation());
        }
        return null;
    }

    @objid ("80c7a328-1dec-11e2-8cad-001ec947c8cc")
    private EditPart getChangeBoundsTargetEditPart(final ChangeBoundsRequest req) {
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
                final EditPart target = getEditPartFor(gm.getRelatedMClass().getJavaInterface(),
                        req.getLocation());
                if (ret == null) {
                    ret = target;
                } else if (ret != target) {
                    return null;
                }
            }
        }
        return ret;
    }

    @objid ("80c7a332-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getMoveChildrenCommand(final Request request) {
        if (getTargetEditPart(request) == null) {
            return null;
        }
        return new DeferredChangeBoundsCommand((ChangeBoundsRequest) request, getHost());
    }

    @objid ("80c7a33d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void decorateChild(final EditPart child) {
        EditPolicy policy = createChildEditPolicy(child);
        if (policy != null) {
            child.installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, policy);
        }
    }

    /**
     * Returns the "satellite" EditPolicy used to decorate the child.
     * <p>
     * If <code>null</code>, no policy will be added and the existing drag policy will be preserved. By default no policy is created
     * and the existing one is kept.
     */
    @objid ("80c7a344-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPolicy createChildEditPolicy(final EditPart child) {
        return null;
    }

    @objid ("80ca0548-1dec-11e2-8cad-001ec947c8cc")
    private class DeferredCreateCommand extends Command {
        @objid ("80ca054f-1dec-11e2-8cad-001ec947c8cc")
        private Map<?,?> editPartRegistry;

        @objid ("80ca054e-1dec-11e2-8cad-001ec947c8cc")
        private GmLink gmLink;

        @objid ("8cf977ca-7356-40c7-be83-17cca24899e3")
        private Request req;

        /**
         * Create a deferred command.
         * 
         * @param req The creation request.
         * @param sender The edit part sending the request
         */
        @objid ("80ca0553-1dec-11e2-8cad-001ec947c8cc")
        public DeferredCreateCommand(final Request req, final EditPart sender) {
            this.req = req;
            this.gmLink = (GmLink) sender.getModel();
            this.editPartRegistry = sender.getViewer().getEditPartRegistry();
        }

        @objid ("80ca055e-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public boolean canExecute() {
            if (!MTools.getAuthTool().canModify(this.gmLink.getDiagram().getRelatedElement())) {
                return false;
            }
            
            if (!MTools.getAuthTool().canModify(this.gmLink.getRelatedElement())) {
                return false;
            }
            return true;
        }

        @objid ("80ca0563-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public void execute() {
            Command cmd = createCommand();
            if (cmd != null && cmd.canExecute()) {
                cmd.execute();
            }
        }

        @objid ("80ca0566-1dec-11e2-8cad-001ec947c8cc")
        private Command createCommand() {
            final GmCompositeNode gmTarget = getExtensionFor(this.gmLink, getMetaclass(), ((DropRequest) this.req).getLocation());
            
            if (gmTarget == null) {
                return null;
            }
            
            if (!gmTarget.isVisible()) {
                gmTarget.setVisible(true);
            }
            
            final EditPart p = (EditPart) this.editPartRegistry.get(gmTarget);
            if (p == null) {
                return null;
            }
            
            final EditPart targetPart = p.getTargetEditPart(this.req);
            if (targetPart != null) {
                return targetPart.getCommand(this.req);
            } else {
                return null;
            }
        }

        /**
         * @return the metaclass to create
         */
        @objid ("80ca056b-1dec-11e2-8cad-001ec947c8cc")
        private Class<? extends MObject> getMetaclass() {
            ModelioCreationContext ctx = ModelioCreationContext.fromRequest((CreateRequest) this.req);
            return ctx.getMetaclass().getJavaInterface();
        }

    }

    /**
     * Command that defers a {@link DeferredChangeBoundsCommand} to an extension of the link.
     * <p>
     * The actual edit part is found by calling {@link GmCompositeNode#getCompositeFor(Class)} for all involved GmModel, then
     * looking for their edit part.
     * 
     * @author cmarin
     */
    @objid ("80ca0572-1dec-11e2-8cad-001ec947c8cc")
    private class DeferredChangeBoundsCommand extends Command {
        @objid ("80ca057a-1dec-11e2-8cad-001ec947c8cc")
        private Map<?,?> editPartRegistry;

        @objid ("80ca0579-1dec-11e2-8cad-001ec947c8cc")
        private GmLink gmLink;

        @objid ("22093461-e056-4214-86c2-cdaaafd69115")
        private ChangeBoundsRequest req;

        /**
         * Create a deferred command.
         * 
         * @param req The creation request.
         * @param sender The edit part sending the request
         */
        @objid ("80cc67a4-1dec-11e2-8cad-001ec947c8cc")
        public DeferredChangeBoundsCommand(final ChangeBoundsRequest req, final EditPart sender) {
            this.req = req;
            this.gmLink = (GmLink) sender.getModel();
            this.editPartRegistry = sender.getViewer().getEditPartRegistry();
        }

        @objid ("80cc67af-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public boolean canExecute() {
            return getGmTarget() != null;
        }

        @objid ("80cc67b4-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public void execute() {
            final GmCompositeNode gmTarget = getGmTarget();
            
            if (!gmTarget.isVisible()) {
                gmTarget.setVisible(true);
            }
            
            final EditPart p = (EditPart) this.editPartRegistry.get(gmTarget);
            if (p != null) {
                p.getTargetEditPart(this.req).getCommand(this.req).execute();
            }
        }

        /**
         * Get the node model where all the request must be handled or <tt>null</tt> if the request cannot be executed in a single
         * node (the selection is not homogeneous).
         * 
         * @return the node model where the request must be handled.
         */
        @objid ("80cc67b7-1dec-11e2-8cad-001ec947c8cc")
        private GmCompositeNode getGmTarget() {
            GmCompositeNode gmTarget = null;
            
            for (Object o : this.req.getEditParts()) {
                final EditPart part = (EditPart) o;
                final GmModel model = (GmModel) part.getModel();
                final Class<? extends MObject> metaclass = model.getRelatedMClass().getJavaInterface();
            
                final GmCompositeNode cont = getExtensionFor(this.gmLink, metaclass, this.req.getLocation());
            
                if (cont == null) {
                    return null;
                }
            
                if (gmTarget == null) {
                    gmTarget = cont;
                } else if (gmTarget != cont) {
                    return null;
                }
            }
            return gmTarget;
        }

    }

}
