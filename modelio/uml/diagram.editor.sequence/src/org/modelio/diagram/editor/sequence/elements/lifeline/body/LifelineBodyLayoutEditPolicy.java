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

package org.modelio.diagram.editor.sequence.elements.lifeline.body;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.editor.sequence.elements.executionoccurencespecification.GmExecutionOccurenceSpecification;
import org.modelio.diagram.editor.sequence.elements.executionoccurencespecification.MoveExecutionOccurenceSpecificationCommand;
import org.modelio.diagram.editor.sequence.elements.executionoccurencespecification.MoveExecutionOccurrenceSpecificationEditPolicy;
import org.modelio.diagram.editor.sequence.elements.executionspecification.CreateExecutionSpecificationCommand;
import org.modelio.diagram.editor.sequence.elements.executionspecification.ExecutionSpecificationEditPart;
import org.modelio.diagram.editor.sequence.elements.executionspecification.GmExecutionSpecification;
import org.modelio.diagram.editor.sequence.elements.executionspecification.MoveResizeExecutionSpecificationCommand;
import org.modelio.diagram.editor.sequence.elements.executionspecification.MoveResizeExecutionSpecificationEditPolicy;
import org.modelio.diagram.editor.sequence.elements.executionspecification.ReparentExecutionSpecificationCommand;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.IPlacementConstraintProvider;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.PlacementConstraint;
import org.modelio.diagram.editor.sequence.elements.stateinvariant.CreateStateInvariantCommand;
import org.modelio.diagram.editor.sequence.elements.stateinvariant.GmStateInvariant;
import org.modelio.diagram.editor.sequence.elements.stateinvariant.MoveResizeStateInvariantCommand;
import org.modelio.diagram.editor.sequence.elements.stateinvariant.ReparentStateInvariantCommand;
import org.modelio.diagram.editor.sequence.elements.stateinvariant.StateInvariantEditPart;
import org.modelio.diagram.elements.common.freezone.DefaultFreeZoneLayoutEditPolicy;
import org.modelio.diagram.elements.common.freezone.ILayoutAssistant;
import org.modelio.diagram.elements.common.freezone.NoopLayoutAssistant;
import org.modelio.diagram.elements.core.commands.DefaultCreateElementCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.policies.DefaultNodeNonResizableEditPolicy;
import org.modelio.diagram.elements.core.policies.DefaultNodeResizableEditPolicy;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.StateInvariant;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specialization of the {@link DefaultFreeZoneLayoutEditPolicy} to handle the specific constraints of execution specifications on a lifeline (centered, etc).
 * 
 * @author fpoyer
 */
@objid ("d9345d06-55b6-11e2-877f-002564c97630")
public class LifelineBodyLayoutEditPolicy extends DefaultFreeZoneLayoutEditPolicy {
    @objid ("d9345d0a-55b6-11e2-877f-002564c97630")
    @Override
    protected Command createAddCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
        MObject relatedElement = ((GmModel) child.getModel()).getRelatedElement();
        
        if (relatedElement instanceof ExecutionSpecification) {
            Rectangle requestConstraint = (Rectangle) constraint;
            Rectangle tmp = requestConstraint.getCopy();
            tmp.translate(getLayoutOrigin());
            int newTime = tmp.y;
        
            ExecutionSpecification spec = (ExecutionSpecification) relatedElement;
            if (spec.getLineNumber() == -1 || newTime == spec.getLineNumber()) {
                return createAddCommandForExecution(child, (Rectangle) constraint);
            } else {
                return null;
            }
        } else if (relatedElement instanceof StateInvariant) {
            return createAddCommandForStateInvariant(child, (Rectangle) constraint);
        } else if (relatedElement instanceof ExecutionOccurenceSpecification) {
            return createAddCommandForBlueSquare(child, (Rectangle) constraint);
        } else {
            return super.createAddCommand(request, child, constraint);
        }
    }

    @objid ("d9345d12-55b6-11e2-877f-002564c97630")
    @Override
    protected Command createChangeConstraintCommand(final ChangeBoundsRequest request, final EditPart child, final Object constraint) {
        MObject relatedElement = ((GmModel) child.getModel()).getRelatedElement();
        
        if (relatedElement instanceof ExecutionSpecification) {
            return createChangeConstraintCommandForExecutionSpecification(child, (Rectangle) constraint);
        } else if (relatedElement instanceof ExecutionOccurenceSpecification) {
            return createChangeConstraintCommandForExecutionOccurenceSpecification(child, (Rectangle) constraint);
        } else if (relatedElement instanceof StateInvariant) {
            return createChangeConstraintCommandForStateInvariant(child, (Rectangle) constraint);
        } else {
            return super.createChangeConstraintCommand(request, child, constraint);
        }
    }

    @objid ("d9345d1c-55b6-11e2-877f-002564c97630")
    @Override
    protected EditPolicy createChildEditPolicy(final EditPart child) {
        if (child.getModel() instanceof GmExecutionSpecification) {
            DefaultNodeResizableEditPolicy policy = new MoveResizeExecutionSpecificationEditPolicy();
            policy.setResizeDirections(PositionConstants.NORTH_SOUTH);
            return policy;
        } else if (child.getModel() instanceof GmStateInvariant) {
            DefaultNodeResizableEditPolicy policy = new DefaultNodeResizableEditPolicy();
            return policy;
        } else if (child.getModel() instanceof GmExecutionOccurenceSpecification) {
            DefaultNodeNonResizableEditPolicy policy = new MoveExecutionOccurrenceSpecificationEditPolicy();
            return policy;
        } else {
            return super.createChildEditPolicy(child);
        }
    }

    @objid ("d9345d22-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getCreateCommand(CreateRequest req) {
        ModelioCreationContext ctx = ModelioCreationContext.lookRequest(req);
        if (ctx != null) {
            MObject hostElement = getHostElement();
            MObject elementToUnmask = ctx.getElementToUnmask();
            if (elementToUnmask != null) {
                return getUnmaskCommand(req, hostElement, ctx, elementToUnmask);
            }
        
            MMetamodel mm = hostElement.getMClass().getMetamodel();
        
            MClass metaclassToCreate = ctx.getMetaclass();
            if (mm.getMExpert().canCompose(hostElement.getMClass(), metaclassToCreate, ctx.getDependencyName())) {
                Rectangle requestConstraint = (Rectangle) getConstraintFor(req);
        
                if (ExecutionSpecification.class.isAssignableFrom(metaclassToCreate.getJavaInterface())) {
                    int newHeight = requestConstraint.height == -1 ? ExecutionSpecificationEditPart.DEFAULT_EXECUTION_HEIGHT
                            : requestConstraint.height;
                    requestConstraint.setSize(ExecutionSpecificationEditPart.EXECUTION_WIDTH, newHeight);
                    return new CreateExecutionSpecificationCommand(getHostCompositeNode(), requestConstraint);
        
                } else if (StateInvariant.class.isAssignableFrom(metaclassToCreate.getJavaInterface())) {
                    int newHeight = requestConstraint.height == -1 ? StateInvariantEditPart.DEFAULT_STATEINVARIANT_HEIGHT
                            : requestConstraint.height;
                    int newWidth = requestConstraint.width == -1 ? StateInvariantEditPart.DEFAULT_STATEINVARIANT_WIDTH
                            : requestConstraint.width;
                    requestConstraint.setSize(newWidth, newHeight);
                    return new CreateStateInvariantCommand(getHostCompositeNode(), requestConstraint);
                } else {
                    return new DefaultCreateElementCommand(hostElement, getHostCompositeNode(), ctx, requestConstraint);
                }
            }
        }
        return null;
    }

    @objid ("d9345d28-55b6-11e2-877f-002564c97630")
    @Override
    protected Rectangle getCurrentConstraintFor(final GraphicalEditPart child) {
        IFigure childFigure = child.getFigure();
        return ((PlacementConstraint) childFigure.getParent().getLayoutManager().getConstraint(childFigure))
                        .getUpdatedBounds(childFigure);
    }

    /**
     * Disable layout assistant on sequence diagrams.
     */
    @objid ("64f07bf1-e482-40a4-ba94-ba6eb3f392d0")
    @Override
    protected ILayoutAssistant getLayoutAssistant(ChangeBoundsRequest request) {
        return NoopLayoutAssistant.instance;
    }

    @objid ("d9345d2f-55b6-11e2-877f-002564c97630")
    @Override
    protected Point getLayoutOrigin() {
        return getHostFigure().getClientArea().getLocation();
    }

    @objid ("d935e3cd-55b6-11e2-877f-002564c97630")
    private Command createAddCommandForBlueSquare(final EditPart child, final Rectangle constraint) {
        Rectangle tmp = constraint.getCopy();
        tmp.translate(getLayoutOrigin());
        int startTime = tmp.getCenter().y();
        return new ReparentBlueSquareCommand(getHostCompositeNode(), (GmExecutionOccurenceSpecification) child.getModel(), startTime);
    }

    @objid ("d9345d34-55b6-11e2-877f-002564c97630")
    private Command createAddCommandForExecution(final EditPart child, final Rectangle constraint) {
        Rectangle tmp = constraint.getCopy();
        tmp.translate(getLayoutOrigin());
        int startTime = tmp.y;
        int finishTime = tmp.bottom();
        return new ReparentExecutionSpecificationCommand(getHostCompositeNode(), (GmExecutionSpecification) child.getModel(), startTime, finishTime);
    }

    @objid ("d935e3bd-55b6-11e2-877f-002564c97630")
    private Command createAddCommandForStateInvariant(final EditPart child, final Rectangle constraint) {
        Rectangle tmp = constraint.getCopy();
        tmp.translate(getLayoutOrigin());
        int startTime = tmp.y;
        int finishTime = tmp.bottom();
        return new ReparentStateInvariantCommand(getHostCompositeNode(), (GmStateInvariant) child.getModel(), startTime, finishTime);
    }

    @objid ("d935e3b5-55b6-11e2-877f-002564c97630")
    private Command createChangeConstraintCommandForExecutionOccurenceSpecification(final EditPart child, final Rectangle constraint) {
        Rectangle tmp = constraint.getCopy();
        tmp.translate(getLayoutOrigin());
        int newTime = tmp.getCenter().y;
        
        MoveExecutionOccurenceSpecificationCommand cmd = new MoveExecutionOccurenceSpecificationCommand();
        cmd.setGmExecutionOccurenceSpecification((GmExecutionOccurenceSpecification) child.getModel());
        cmd.setNewTime(newTime);
        return cmd;
    }

    @objid ("d935e39c-55b6-11e2-877f-002564c97630")
    private Command createChangeConstraintCommandForExecutionSpecification(final EditPart child, final Rectangle constraint) {
        Rectangle tmp = constraint.getCopy();
        tmp.translate(getLayoutOrigin());
        int startTime = tmp.y;
        int finishtime = tmp.bottom();
        
        MoveResizeExecutionSpecificationCommand cmd = new MoveResizeExecutionSpecificationCommand();
        cmd.setGmExecution((GmExecutionSpecification) child.getModel());
        cmd.setStartTime(startTime);
        cmd.setFinishTime(finishtime);
        return cmd;
    }

    @objid ("d935e3c5-55b6-11e2-877f-002564c97630")
    private Command createChangeConstraintCommandForStateInvariant(final EditPart child, final Rectangle constraint) {
        Rectangle tmp = constraint.getCopy();
        tmp.translate(getLayoutOrigin());
        int startTime = tmp.y;
        int finishtime = tmp.bottom();
        
        MoveResizeStateInvariantCommand cmd = new MoveResizeStateInvariantCommand();
        cmd.setGmStateInvariant((GmStateInvariant) child.getModel());
        cmd.setStartTime(startTime);
        cmd.setFinishTime(finishtime);
        
        PlacementConstraint placementConstraint = ((IPlacementConstraintProvider) child).createPlacementConstraint(
                (GmModel) child.getModel(), tmp.x, tmp.y, tmp.width, tmp.height);
        cmd.setNewLayoutData(placementConstraint);
        return cmd;
    }

    @objid ("d935e3a4-55b6-11e2-877f-002564c97630")
    private Command getUnmaskCommand(final CreateRequest req, final MObject hostElement, final ModelioCreationContext ctx, final MObject elementToUnmask) {
        if (getHostCompositeNode().canUnmask(elementToUnmask)) {
            return new DefaultCreateElementCommand(hostElement, getHostCompositeNode(), ctx, getConstraintFor(req));
        } else {
            return null;
        }
    }

}
