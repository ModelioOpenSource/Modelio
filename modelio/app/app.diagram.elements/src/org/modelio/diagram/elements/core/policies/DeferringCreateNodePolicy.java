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
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.modelio.diagram.elements.core.commands.DeferredCreateCommand;
import org.modelio.diagram.elements.core.commands.DeferredGroupCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.figures.FigureUtilities2;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Edit policy that delegates {@link RequestConstants#REQ_CREATE REQ_CREATE}, {@link RequestConstants#REQ_ADD REQ_ADD},
 * {@link RequestConstants#REQ_MOVE REQ_MOVE} and {@link RequestConstants#REQ_CLONE REQ_CLONE} requests to the child edit parts that
 * should contain the involved model element metaclass.
 * <p>
 * This policy is intended to be used on elements that represents child elements in different zones depending on the metaclass. eg:
 * GmClass dispatch attributes in attribute zone, operations in the operation zone and parts in the internal structure.
 * <p>
 * {@link GmCompositeNode#getCompositeFor(Class)} is called on the host model in order to find the right edit part.
 * <p>
 * If the child node model is invisible at the time the request is send, the corresponding edit part does not exist. So the policy
 * builds a command that when executed will first make the node as visible and then forward the request to the corresponding child
 * edit part.
 * 
 * @see DeferredCreateCommand
 * @see DeferredGroupCommand
 * @author cmarin
 */
@objid ("80cc67c6-1dec-11e2-8cad-001ec947c8cc")
public class DeferringCreateNodePolicy extends DefaultCreateNodeEditPolicy {
    @objid ("6d09c2c9-782a-4711-83b3-f6fabf1ae1f6")
    private IFigure highlight;

    @objid ("80cc67cb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getAddCommand(Request request) {
        return new DeferredGroupCommand((GroupRequest) request, getHost());
    }

    @objid ("80cc67d5-1dec-11e2-8cad-001ec947c8cc")
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

    @objid ("80cc67df-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getCloneCommand(ChangeBoundsRequest request) {
        return new DeferredGroupCommand(request, getHost());
    }

    @objid ("80cc67e9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getCreateCommand(CreateRequest createReq) {
        return new DeferredCreateCommand(createReq, getHost());
    }

    @objid ("80cc67f3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPart getCreateTargetEditPart(CreateRequest createRequest) {
        // Creation request, only one element is involved
        final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(createRequest);
        if (ctx != null) {
            return getEditPartFor(ctx.getMetaclass().getJavaInterface(), createRequest.getLocation());
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
     * If the found model is not visible, return the host edit part.
     * <p>
     * 
     * @param metaclass The metaclass to create or drop.
     * @param location The mouse location.
     * @return <ul>
     * <li><tt>null</tt> if no suitable child model could be found <li>{@link #getHost()} if the child model is not visible
     * <li>The found child model edit part if one suitable is found.
     * </ul>
     */
    @objid ("80cc67fd-1dec-11e2-8cad-001ec947c8cc")
    protected EditPart getEditPartFor(Class<? extends MObject> metaclass, final Point location) {
        final GmCompositeNode gmNode = (GmCompositeNode) getHost().getModel();
        
        // Look for the child node accepting the given node type.
        final GmCompositeNode gmTargetChild = gmNode.getCompositeFor(metaclass);
        
        // If no one can contain the element, return null to forward to the parent.
        if (gmTargetChild == null) {
            return null;
        }
        
        // If the child is not visible, return the host so that we will make the child visible.
        if (!gmTargetChild.isVisible()) {
            return getHost();
        }
        
        // Return the edit part of the child node.
        final EditPart p = (EditPart) getHost().getRoot().getViewer().getEditPartRegistry().get(gmTargetChild);
        return p;
    }

    @objid ("80cec9fc-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void showTargetFeedback(final Request request) {
        if (REQ_ADD.equals(request.getType()) || REQ_CREATE.equals(request.getType())) {
        
            // Compute highlight type
            final Command c = getHost().getCommand(request);
            FigureUtilities2.HighlightType hightlightType = null;
            if (c == null) {
                hightlightType = FigureUtilities2.HighlightType.ERROR;
            } else if (c.canExecute()) {
                hightlightType = FigureUtilities2.HighlightType.SUCCESS;
            } else {
                hightlightType = FigureUtilities2.HighlightType.WARNING;
            }
        
            // create the highlight figure if it does not exists
            if (this.highlight == null) {
                // create a highlight figure
                this.highlight = FigureUtilities2.createHighlightFigure(getFeedbackLayer(), getHostFigure(), hightlightType);
                // add the highlight figure to the feedback layer
                getFeedbackLayer().add(this.highlight);
            }
            // configure the highlight figure
            FigureUtilities2.updateHighlightType(this.highlight, hightlightType);
        }
        super.showTargetFeedback(request);
    }

    @objid ("80ceca03-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void eraseTargetFeedback(final Request request) {
        if (REQ_ADD.equals(request.getType()) || REQ_CREATE.equals(request.getType())) {
            if (this.highlight != null) {
                removeFeedback(this.highlight);
                this.highlight = null;
            }
        }
        
        super.eraseTargetFeedback(request);
    }

}
