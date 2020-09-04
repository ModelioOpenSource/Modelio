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

package org.modelio.diagram.elements.common.group;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.OrderedLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.DefaultCloneElementCommand;
import org.modelio.diagram.elements.core.commands.DefaultCreateElementCommand;
import org.modelio.diagram.elements.core.commands.DefaultReparentElementCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.figures.FigureUtilities2;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DefaultNodeNonResizableEditPolicy;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MExpert;

/**
 * Edit policy that allows to create and remove elements from a {@link GroupFigure}.
 * <p>
 * Also handles the drop of a model element to correctly setup the coordinates.
 */
@objid ("7e4fd3c4-1dec-11e2-8cad-001ec947c8cc")
public class DefaultGroupLayoutEditPolicy extends OrderedLayoutEditPolicy {
    @objid ("b5d03dda-84a8-47ae-bb4c-acdaeca47e0a")
    private IFigure highlight = null;

    @objid ("7e523641-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void eraseTargetFeedback(Request request) {
        if (REQ_ADD.equals(request.getType()) || REQ_CREATE.equals(request.getType())) {
        
            if (this.highlight != null) {
                removeFeedback(this.highlight);
                this.highlight = null;
            }
        }
        
        super.eraseTargetFeedback(request);
    }

    @objid ("7e4fd3f4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (REQ_CREATE.equals(request.getType())) {
            final CreateRequest createRequest = (CreateRequest) request;
            return getTargetEditPart(createRequest);
        }
        if (REQ_ADD.equals(request.getType()) || REQ_CLONE.equals(request.getType()) || REQ_MOVE.equals(request.getType())) {
            final ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) request;
            return getTargetEditPart(changeBoundsRequest);
        }
        return null;
    }

    @objid ("7e52363b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void showTargetFeedback(Request request) {
        if (REQ_ADD.equals(request.getType()) || REQ_CREATE.equals(request.getType())) {
        
            // compute highlight type
            final Command c = getCommand(request);
            FigureUtilities2.HighlightType hightlightType = FigureUtilities2.HighlightType.INFO;
            if (c == null) {
                hightlightType = FigureUtilities2.HighlightType.ERROR;
            } else if (c.canExecute()) {
                hightlightType = FigureUtilities2.HighlightType.SUCCESS;
            } else {
                hightlightType = FigureUtilities2.HighlightType.WARNING;
            }
        
            // create a highlight figure if it does not exist
            if (this.highlight == null) {
                this.highlight = FigureUtilities2.createHighlightFigure(getFeedbackLayer(), getHostFigure(), hightlightType);
        
                // add the highlight figure to the feedback layer
                getFeedbackLayer().add(this.highlight);
            }
            // configure the highlight figure
            FigureUtilities2.updateHighlightType(this.highlight, hightlightType);
        }
        super.showTargetFeedback(request);
    }

    /**
     * Returns whether this edit policy can handle this metaclass (either through simple or smart behavior). Default behavior is to
     * accept any metaclass that can be child (in the CreationExpert's understanding) of the host's metaclass This method should be
     * overridden by subclasses to add specific the behavior.
     * 
     * @param metaclass the metaclass to handle.
     * @return true if this policy can handle the metaclass.
     */
    @objid ("7e4fd3ec-1dec-11e2-8cad-001ec947c8cc")
    protected boolean canHandle(MClass metaclass) {
        final GmCompositeNode node = (GmCompositeNode) getHost().getModel();
        if (!node.canCreate(metaclass.getJavaInterface())) {
            return false;
        }
        return metaclass.getMetamodel().getMExpert().canCompose(node.getRelatedElement(), metaclass, null);
    }

    @objid ("7e4fd3d5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command createAddCommand(EditPart child, EditPart after) {
        final GmCompositeNode gmNode = (GmCompositeNode) getHost().getModel();
        return new DefaultReparentElementCommand(gmNode.getRelatedElement(), gmNode, (GmNodeModel) child.getModel(), null);
    }

    @objid ("7e523647-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPolicy createChildEditPolicy(EditPart child) {
        return new DefaultNodeNonResizableEditPolicy();
    }

    @objid ("7e523624-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command createMoveChildCommand(EditPart child, EditPart after) {
        if (child instanceof AbstractNodeEditPart) {
            final GmGroupMoveChildCommand command = new GmGroupMoveChildCommand();
            command.setChild(child.getModel());
            command.setAfter(after.getModel());
            return command;
        }
        return null;
    }

    @objid ("7e523651-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getAddCommand(final Request req) {
        ChangeBoundsRequest request = (ChangeBoundsRequest) req;
        List<?> editParts = request.getEditParts();
        CompoundCommand command = new CompoundCommand();
        for (int i = 0; i < editParts.size(); i++) {
            EditPart child = (EditPart) editParts.get(i);
            if (child instanceof ConnectionEditPart) {
                command.add(child.getCommand(req));
            } else {
                command.add(createAddCommand(child, getInsertionReference(request)));
            }
        }
        return command.unwrap();
    }

    @objid ("7e4fd3e2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getCloneCommand(ChangeBoundsRequest request) {
        if (getHost().getModel() instanceof GmCompositeNode) {
            final GmCompositeNode hostModel = (GmCompositeNode) getHost().getModel();
        
            final CompoundCommand command = new CompoundCommand();
            for (final Object editPartObj : request.getEditParts()) {
                final EditPart editPart = (EditPart) editPartObj;
                if (editPart.getModel() instanceof GmModel) {
                    final GmModel gmModel = (GmModel) editPart.getModel();
                    final MClass metaclassToCreate = gmModel.getRelatedMClass();
                    final boolean returnCommand = metaclassToCreate.getMetamodel().getMExpert().canCompose(hostModel.getRelatedElement(), metaclassToCreate, null);
                    if (returnCommand) {
                        command.add(new DefaultCloneElementCommand(hostModel, hostModel.getRelatedElement(), gmModel
                                .getRelatedElement(), Integer.valueOf(-1)));
                    }
                }
                // TODO: handle drawings?
            }
            return command.unwrap();
        }
        return null;
    }

    @objid ("7e4fd3cb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(request);
        if (ctx != null) {
            final MClass mc = ctx.getMetaclass();
            final MExpert expert = mc.getMetamodel().getMExpert();
        
            final GmCompositeNode gmGroup = (GmCompositeNode) getHost().getModel();
            final boolean returnCommand = expert.canCompose(gmGroup.getRelatedElement(), mc, null);
            if (returnCommand) {
        
                return new DefaultCreateElementCommand(gmGroup, ctx, Integer.valueOf(-1));
            }
        }
        return null;
    }

    @objid ("7e523631-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPart getInsertionReference(Request request) {
        // TODO Always return the last child for the moment
        final List<?> children = getHost().getChildren();
        if (children.isEmpty()) {
            return null;
        }
        return (EditPart) children.get(children.size() - 1);
    }

    /**
     * Return the host edit part if this policy can handle the metaclass involved in the request.
     * 
     * @param createRequest the request.
     * @return the host editpart if the metaclass involved in the request can be handled by this policy, <code>null</code>
     * otherwise.
     */
    @objid ("7e4fd3fe-1dec-11e2-8cad-001ec947c8cc")
    private EditPart getTargetEditPart(CreateRequest createRequest) {
        final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(createRequest);
        if (ctx != null) {
            if (canHandle(ctx.getMetaclass())) {
                return getHost();
            }
            return null;
        }
        return null;
    }

    /**
     * Return the host edit part if this policy can handle all edit parts involved in the request.
     * 
     * @param changeBoundsRequest the request, can be CLONE or ADD.
     * @return the host editpart if all editparts involved in the request can be handled by this policy, <code>null</code>
     * otherwise.
     */
    @objid ("7e4fd408-1dec-11e2-8cad-001ec947c8cc")
    private EditPart getTargetEditPart(ChangeBoundsRequest changeBoundsRequest) {
        for (final Object editPartObj : changeBoundsRequest.getEditParts()) {
            final EditPart editPart = (EditPart) editPartObj;
            if (editPart.getModel() instanceof GmModel) {
                final GmModel gmModel = (GmModel) editPart.getModel();
                // If there is at least 1 element that this policy cannot
                // handle, do not handle the request at all!
                if (!canHandle(gmModel.getRelatedMClass())
                        && !(editPart instanceof ConnectionEditPart)) {
                    return null;
                }
            } else {
                // Not a GmModel, probably a drawing : not handled
                return null;
            }
        }
        // This policy can handle all elements of this request: handle it!
        return getHost();
    }

}
