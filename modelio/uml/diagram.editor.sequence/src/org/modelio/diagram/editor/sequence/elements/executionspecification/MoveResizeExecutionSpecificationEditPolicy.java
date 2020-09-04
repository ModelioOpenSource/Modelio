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

package org.modelio.diagram.editor.sequence.elements.executionspecification;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionDimension;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.editor.sequence.elements.modelmanipulation.ManipulationHelper;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.policies.DefaultNodeResizableEditPolicy;
import org.modelio.diagram.elements.core.policies.SelectionHandlesBuilder;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specialisation of the default resize edit policy to add some model checks before returning a command.
 * 
 * @author fpoyer
 */
@objid ("d8efb2f2-55b6-11e2-877f-002564c97630")
public class MoveResizeExecutionSpecificationEditPolicy extends DefaultNodeResizableEditPolicy {
    @objid ("d8efb2f6-55b6-11e2-877f-002564c97630")
    private ManipulationHelper manipHelper;

    @objid ("d8efb2fd-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getResizeCommand(final ChangeBoundsRequest request) {
        // Compute predicates, update variables and check predicates
        computePredicatesForHost();
        updateVariablesFromRequest(request);
        if (this.manipHelper.checkAllPredicates()) {
            return super.getResizeCommand(request);
        } else {
            return UnexecutableCommand.INSTANCE;
        }
    }

    @objid ("d8efb303-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getMoveCommand(final ChangeBoundsRequest request) {
        // Compute predicates, update variables and check predicates
        computePredicatesForHost();
        updateVariablesFromRequest(request);
        if (this.manipHelper.checkAllPredicates()) {
            return super.getMoveCommand(request);
        } else {
            return UnexecutableCommand.INSTANCE;
        }
    }

    @objid ("d8efb309-55b6-11e2-877f-002564c97630")
    private void computePredicatesForHost() {
        // Move and/or resizing an ExecutionSpecification, is really like move the ExecutionOccurrenceSpecification at each end.
        ExecutionSpecification executionSpecification = ((GmExecutionSpecification) getHost().getModel()).getRelatedElement();
        this.manipHelper.computePredicatesForHost(
                executionSpecification.getStart(),
                executionSpecification.getFinish());
    }

    @objid ("d8efb30b-55b6-11e2-877f-002564c97630")
    private void updateVariablesFromRequest(final ChangeBoundsRequest request) {
        if (request.getEditParts() == null) {
            return;
        }
        
        for (Object obj : request.getEditParts()) {
            GraphicalEditPart editPart = (GraphicalEditPart) obj;
            if (editPart != null) {
                GmModel model = (GmModel) editPart.getModel();
                final MObject el = model.getRelatedElement();
        
                Dimension moveDelta = new PrecisionDimension(request.getMoveDelta().x, request.getMoveDelta().y);
                editPart.getFigure().translateToRelative(moveDelta);
        
                if (el instanceof MessageEnd) {
                    int newLineNumber = ((MessageEnd) el).getLineNumber();
                    newLineNumber += moveDelta.height;
                    this.manipHelper.updateVariable(el, newLineNumber);
                } else if (el instanceof ExecutionSpecification) {
                    // Start with the Execution itself.
                    ExecutionSpecification executionSpecification = (ExecutionSpecification) el;
                    int newLineNumber = executionSpecification.getLineNumber();
                    newLineNumber += moveDelta.height;
                    this.manipHelper.updateVariable(el, newLineNumber);
        
                    // Now the Execution start.
                    newLineNumber = executionSpecification.getStart().getLineNumber();
                    newLineNumber += moveDelta.height;
                    this.manipHelper.updateVariable(executionSpecification.getStart(), newLineNumber);
        
                    // And finally the Execution end.
                    Dimension sizeDelta = new PrecisionDimension(request.getSizeDelta());
                    editPart.getFigure().translateToRelative(sizeDelta);
        
                    newLineNumber = executionSpecification.getFinish().getLineNumber();
                    newLineNumber += moveDelta.height + sizeDelta.height;
                    this.manipHelper.updateVariable(executionSpecification.getFinish(), newLineNumber);
        
                } else if (el instanceof Message) {
                    Message message = (Message) el;
                    int newLineNumber = message.getSendEvent().getLineNumber();
                    newLineNumber += moveDelta.height;
                    this.manipHelper.updateVariable(message.getSendEvent(), newLineNumber);
        
                    newLineNumber = message.getReceiveEvent().getLineNumber();
                    newLineNumber += moveDelta.height;
                    this.manipHelper.updateVariable(message.getReceiveEvent(), newLineNumber);
        
                    // If the moved message starts some execution specification, they will be moved too.
                    if (message.getSendEvent() instanceof ExecutionOccurenceSpecification) {
                        ExecutionOccurenceSpecification event = (ExecutionOccurenceSpecification) message.getSendEvent();
                        if (event.getStarted() != null) {
                            MessageEnd otherEnd = event.getStarted().getFinish();
                            newLineNumber = otherEnd.getLineNumber();
                            newLineNumber += moveDelta.height;
                            this.manipHelper.updateVariable(otherEnd, newLineNumber);
                        }
                    }
                    if (message.getReceiveEvent() instanceof ExecutionOccurenceSpecification) {
                        ExecutionOccurenceSpecification event = (ExecutionOccurenceSpecification) message.getReceiveEvent();
                        if (event.getStarted() != null) {
                            MessageEnd otherEnd = event.getStarted().getFinish();
                            newLineNumber = otherEnd.getLineNumber();
                            newLineNumber += moveDelta.height;
                            this.manipHelper.updateVariable(otherEnd, newLineNumber);
                        }
                    }
                }
        
            }
        }
    }

    @objid ("b3b5c7cc-4dd5-47d7-82ca-ea39f0eeac37")
    @Override
    public void activate() {
        super.activate();
        
        this.manipHelper = new ManipulationHelper((GraphicalEditPart) getHost());
    }

    @objid ("949c963a-5756-44ad-af9d-14c7064dcd45")
    @Override
    public void showSourceFeedback(Request request) {
        super.showSourceFeedback(request);
        
        Object type = request.getType();
        if (type.equals(REQ_MOVE) || type.equals(REQ_RESIZE)) {
            computePredicatesForHost();
            updateVariablesFromRequest((ChangeBoundsRequest) request);
            this.manipHelper.showFeedBack(getFeedbackLayer());
        } else {
            this.manipHelper.eraseFeedback(getFeedbackLayer());
        }
    }

    @objid ("42ff2e5f-94b2-4424-8ea8-1c775b50f2f8")
    @Override
    public void eraseSourceFeedback(Request request) {
        super.eraseSourceFeedback(request);
        
        Object type = request.getType();
        if (type.equals(REQ_MOVE) || type.equals(REQ_RESIZE)) {
            this.manipHelper.eraseFeedback(getFeedbackLayer());
        }
    }

    /**
     * Execution rectangles are resizeable only vertically
     */
    @objid ("fad888bc-0284-44af-b556-2871e665d90f")
    @Override
    public int getResizeDirections() {
        return PositionConstants.NORTH | PositionConstants.SOUTH;
    }

    /**
     * Create only north and south resize handles
     */
    @objid ("2a506fab-055c-4eec-b047-de051bfd4736")
    @Override
    protected List<?> createSelectionHandles() {
        return new SelectionHandlesBuilder((GraphicalEditPart) getHost())
                        .withResizeDirections(getResizeDirections())
                        .withDragAllowed(isDragAllowed())
                        .addResizeHandle(PositionConstants.NORTH)
                        .addResizeHandle(PositionConstants.SOUTH)
                        .getHandles();
    }

}
