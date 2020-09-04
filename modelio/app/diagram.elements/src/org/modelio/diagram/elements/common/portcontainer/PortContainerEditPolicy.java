/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.elements.common.portcontainer;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.common.freezone.BaseFreeZoneLayoutEditPolicy;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.commands.NodeChangeLayoutCommand;
import org.modelio.diagram.elements.core.commands.PostLayoutCommand;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DefaultNodeResizableEditPolicy;
import org.modelio.diagram.elements.core.policies.ProgrammaticOnlyDragPolicy;

/**
 * Try 2 of edit policy for port container.
 * 
 * @author fpoyer
 */
@objid ("7ef45653-1dec-11e2-8cad-001ec947c8cc")
public class PortContainerEditPolicy extends BaseFreeZoneLayoutEditPolicy {
    /**
     * Overridden instead of the usual {@link #createChangeConstraintCommand(EditPart, Object)} because the request
     * contains some useful informations like resize direction.
     */
    @objid ("7ef45655-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
        // If moving the main node move the port container instead.
        PortContainerEditPart pcHost = (PortContainerEditPart) getHost();
        if (REQ_MOVE_CHILDREN.equals(request.getType())) {
            GmNodeModel gmMainNode = ((GmPortContainer) pcHost.getModel()).getMainNode();
            if (child.getModel() == gmMainNode) {
                // Moving main node : move the port container itself instead.
                // This code is reached when the main node edit part has a preferred drag edit policy.
                ChangeBoundsRequest newReq = RequestHelper.deepCopy(request) ;
                newReq.setEditParts(pcHost);
        
                PortContainerFigure pcHostFigure = (PortContainerFigure) getHostFigure();
                Rectangle mainBounds = PortResizeHelper.computeRequestedMainNodeBounds(pcHostFigure, request);
                newReq.getExtendedData().put(PortResizeHelper.REQPROP_MAIN_NODE_BOUNDS, mainBounds);
        
                Rectangle trimmedBounds = pcHost.computeTrimmedBounds(child, constraint).getCopy();
                pcHostFigure.translateToAbsolute(trimmedBounds);
                newReq.getExtendedData().put(AbstractNodeEditPart.REQPROP_TRIMMED_BOUNDS, trimmedBounds);
        
                // Ask directly to parent to avoid infinite recursion
                return pcHost.getParent().getCommand(newReq);
            }
        }
        
        // if child is a 'node' it usually can be resized and/or moved
        if (child instanceof AbstractNodeEditPart) {
            // Create the command to resize/move child
            NodeChangeLayoutCommand resizeChildCommand = new NodeChangeLayoutCommand();
            resizeChildCommand.setModel(child.getModel());
            resizeChildCommand.setConstraint(constraint);
        
            // If needed, resize the container.
            Command resizeContainerCommand = new LastMinuteContainerAutoResizeCommand(pcHost);
        
            // combine both.
            CompoundCommand command = new CompoundCommand();
            command.add(resizeChildCommand);
            command.add(resizeContainerCommand);
        
            // Embed all in a  PostLayoutCommand to allow post layout operations.
            return new PostLayoutCommand(command.unwrap(), request);
        }
        return null;
    }

    /**
     * Returns an appropriate edit policy (either <code>null</code>, {@link NonResizableEditPolicy} or
     * {@link DefaultNodeResizableEditPolicy} at the moment).
     */
    @objid ("7ef45661-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPolicy createChildEditPolicy(EditPart child) {
        // Do not return a policy for main node: it is not supposed to
        // resize/move inside the container. Instead, every try to resize/move
        // the container will actually resize/move the main node, and the
        // container will adapt its bounds to continue to cover every children.
        // For other roles, ask if the child can provide its own policy,
        // otherwise use default.
        if (child instanceof AbstractNodeEditPart) {
            AbstractNodeEditPart childNodePart = (AbstractNodeEditPart) child;
            GmNodeModel childModel = childNodePart.getModel();
            GmPortContainer pc = (GmPortContainer) childModel.getParentNode();
            if (childModel == pc.getMainNode()) {
                // Resize of main node is actually handled by resize of
                // container, see AutoSizeEditPolicy.
                // Just allow programmatic move/resize requests
                return new ProgrammaticOnlyDragPolicy();
            } else if (pc.isPort(childModel)) {
                SelectionEditPolicy childPolicy = childNodePart.getPreferredDragRolePolicy(REQ_RESIZE);
                if (childPolicy != null) {
                    return childPolicy;
                } else {
                    return new DefaultNodeResizableEditPolicy();
                }
            } else if (pc.isSatellite(childModel)) {
                // Satellite: ask if the child can provide its own policy,
                // otherwise use default.
                SelectionEditPolicy childPolicy = childNodePart.getPreferredDragRolePolicy(REQ_RESIZE);
                if (childPolicy != null) {
                    return childPolicy;
                } else if (childModel instanceof GmElementLabel || childModel instanceof GmModelElementHeader) {
                    // Satellite label
                    SatelliteLabelMoveResizeEditPolicy policy = new SatelliteLabelMoveResizeEditPolicy();
                    //policy.setResizeDirections(PositionConstants.EAST_WEST);
                    return policy;
                } else {
                    return new DefaultNodeResizableEditPolicy();
                }
            }
        }
        return null;
    }

    @objid ("7ef6b872-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Object getConstraintFor(ChangeBoundsRequest request, GraphicalEditPart child) {
        GmNodeModel childModel = (GmNodeModel) child.getModel();
        GmCompositeNode parentNode = childModel.getParentNode();
        if (parentNode instanceof GmPortContainer) {
            GmPortContainer pc = (GmPortContainer) parentNode;
            // Main node and satellites use Rectangles.
            if ((pc.getMainNode() == childModel) || pc.isSatellite(childModel)) {
                return super.getConstraintFor(request, child);
            }
        }
        
        // else: Ports use PortConstraints.
        PortConstraint newConstraint = new PortConstraint();
        newConstraint.setRequestedBounds((Rectangle) super.getConstraintFor(request, child));
        
        Rectangle mainNodeBounds = (Rectangle) request.getExtendedData().get(PortResizeHelper.REQPROP_MAIN_NODE_BOUNDS);
        if (mainNodeBounds == null && child.getFigure() instanceof PortContainerFigure) {
            mainNodeBounds = PortResizeHelper.computeRequestedMainNodeBounds((PortContainerFigure) child.getFigure(), request);
        }
        
        if (mainNodeBounds != null) {
            // Port is itself a PortContainer and gave us the evolution of its
            // main node bounds: this main node bounds must be "fixed" to one border,
            // instead of using the bounds as usual.
            Point requestedCentre = mainNodeBounds.getCenter();
            getLayoutContainer().translateToRelative(requestedCentre);
            requestedCentre.translate(getLayoutOrigin().getNegated());
            newConstraint.setRequestedCenter(requestedCentre);
        }
        
        // let layout determine the new reference border.
        PortContainerLayout layout = getPortContainerLayout();
        layout.determineReferenceBorder(getHostFigure(), newConstraint);
        return newConstraint;
    }

    @objid ("7ef6b87d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Rectangle getCurrentConstraintFor(GraphicalEditPart child) {
        IFigure fig = child.getFigure();
        Object constraint = fig.getParent().getLayoutManager().getConstraint(fig);
        if (constraint == null) {
            return null;
        } else if (constraint instanceof Rectangle) {
            return (Rectangle) constraint;
        } else if (constraint instanceof PortConstraint) {
            return ((PortConstraint) constraint).getRequestedBounds();
        } else {
            return null;
        }
    }

    @objid ("7ef6b887-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Point getLayoutOrigin() {
        PortContainerLayout layout = getPortContainerLayout();
        return layout.getOrigin(getLayoutContainer());
    }

    @objid ("7ef6b88e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getAddCommand(final Request generic) {
        ChangeBoundsRequest request = (ChangeBoundsRequest) generic;
        List<?> editParts = request.getEditParts();
        CompoundCommand command = new CompoundCommand();
        command.setDebugLabel("Add in ConstrainedLayoutEditPolicy");//$NON-NLS-1$
        GraphicalEditPart childPart;
        Object constraint;
        Rectangle r;
        
        for (int i = 0; i < editParts.size(); i++) {
            childPart = (GraphicalEditPart) editParts.get(i);
            if (childPart instanceof ConnectionEditPart) {
                // The request also holds moved connections to move their bend points
                // Delegate to the connection bend point policy. 
                command.add(childPart.getCommand(request));
            } else {
                constraint = getConstraintFor(request, childPart);
                if (constraint instanceof Rectangle) {
                    r = (Rectangle) constraint;
                } else {
                    r = ((PortConstraint) constraint).getRequestedBounds();
                }
        
                r.translate(getLayoutOrigin());
                // convert r from relative to childPart figure to absolute
                childPart.getFigure().translateToAbsolute(r);
                // convert r from absolute to relative to this figure
                getLayoutContainer().translateToRelative(r);
                getLayoutContainer().translateFromParent(r);
                r.translate(getLayoutOrigin().getNegated());
                if (constraint instanceof Rectangle) {
                    constraint = r;
                } else {
                    ((PortConstraint) constraint).setRequestedBounds(r);
                }
                command.add(createAddCommand(request, childPart, translateToModelConstraint(constraint)));
            }
        }
        return command.unwrap();
    }

    @objid ("27efa8a0-cf23-4f4f-9525-e330fa589597")
    @Override
    public boolean understandsRequest(Request req) {
        if (PostLayoutCommand.REQ_TYPE == req.getType()) {
            return true;
        }
        return super.understandsRequest(req);
    }

    @objid ("9610fdc9-ab72-47ba-bbc8-27b02dc3d8cb")
    @Override
    protected IFigure getTargetFeedbackFigure(Request request) {
        IFigure hostFigure = getHostFigure();
        IFigure mainNodeFigure = ((PortContainerFigure) hostFigure).getMainNodeFigure();
        if (mainNodeFigure != null) {
            return mainNodeFigure;
        } else {
            return hostFigure;
        }
    }

    @objid ("702123c6-e56b-4990-a97e-1f0d6949e37a")
    protected PortContainerLayout getPortContainerLayout() {
        PortContainerLayout layout = ((PortContainerFigure) getHostFigure()).getPortContainerLayout();
        return layout;
    }

}
