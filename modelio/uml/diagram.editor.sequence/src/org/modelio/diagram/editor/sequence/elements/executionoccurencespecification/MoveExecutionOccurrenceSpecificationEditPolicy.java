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

package org.modelio.diagram.editor.sequence.elements.executionoccurencespecification;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.handles.AbstractHandle;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.editor.sequence.elements.modelmanipulation.ManipulationHelper;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.policies.DefaultNodeNonResizableEditPolicy;
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
@objid ("d8e070bb-55b6-11e2-877f-002564c97630")
public class MoveExecutionOccurrenceSpecificationEditPolicy extends DefaultNodeNonResizableEditPolicy {
    @objid ("d8e070bf-55b6-11e2-877f-002564c97630")
    private ManipulationHelper manipHelper;

    @objid ("d8e070c6-55b6-11e2-877f-002564c97630")
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

    @objid ("d8e070cc-55b6-11e2-877f-002564c97630")
    private void computePredicatesForHost() {
        ExecutionOccurenceSpecification executionOccurrenceSpecification = (ExecutionOccurenceSpecification) ((GmExecutionOccurenceSpecification) getHost().getModel()).getRelatedElement();
        this.manipHelper.computePredicatesForHost(executionOccurrenceSpecification);
    }

    @objid ("d8e070ce-55b6-11e2-877f-002564c97630")
    private void updateVariablesFromRequest(final ChangeBoundsRequest request) {
        if (request.getEditParts() == null) {
            return;
        }
        for (Object obj : request.getEditParts()) {
            GraphicalEditPart editPart = (GraphicalEditPart) obj;
            if (editPart != null) {
                GmModel model = (GmModel) editPart.getModel();
                MObject el = model.getRelatedElement();
        
                Dimension moveDelta = new Dimension(request.getMoveDelta().x, request.getMoveDelta().y);
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
                    Dimension sizeDelta = request.getSizeDelta().getCopy();
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
                    if (message.getSendEvent() instanceof ExecutionOccurenceSpecification &&
                            ((ExecutionOccurenceSpecification) message.getSendEvent()).getStarted() != null) {
                        MessageEnd otherEnd = ((ExecutionOccurenceSpecification) message.getSendEvent()).getStarted()
                                .getFinish();
                        newLineNumber = otherEnd.getLineNumber();
                        newLineNumber += moveDelta.height;
                        this.manipHelper.updateVariable(otherEnd, newLineNumber);
                    }
                    if (message.getReceiveEvent() instanceof ExecutionOccurenceSpecification &&
                            ((ExecutionOccurenceSpecification) message.getReceiveEvent()).getStarted() != null) {
                        MessageEnd otherEnd = ((ExecutionOccurenceSpecification) message.getReceiveEvent()).getStarted()
                                .getFinish();
                        newLineNumber = otherEnd.getLineNumber();
                        newLineNumber += moveDelta.height;
                        this.manipHelper.updateVariable(otherEnd, newLineNumber);
                    }
                }
        
            }
        }
    }

    @objid ("237cd2d6-9fc0-4fba-95e4-28ec89bc4ce9")
    @Override
    public void activate() {
        super.activate();
        
        this.manipHelper = new ManipulationHelper((GraphicalEditPart) getHost());
    }

    @objid ("6aee83a4-6d27-4422-94fe-1df86fa7d8d8")
    @Override
    public void showSourceFeedback(Request request) {
        Object type = request.getType();
        if (type.equals(REQ_MOVE) || type.equals(REQ_RESIZE)) {
            computePredicatesForHost();
            updateVariablesFromRequest((ChangeBoundsRequest) request);
            this.manipHelper.showFeedBack(getFeedbackLayer());
        } else {
            super.showSourceFeedback(request);
            this.manipHelper.eraseFeedback(getFeedbackLayer());
        }
    }

    @objid ("febe3e69-f8b0-481e-857f-989b5fb90db6")
    @Override
    public void eraseSourceFeedback(Request request) {
        super.eraseSourceFeedback(request);
        
        Object type = request.getType();
        if (type.equals(REQ_MOVE) || type.equals(REQ_RESIZE)) {
            this.manipHelper.eraseFeedback(getFeedbackLayer());
        }
    }

    /**
     * Create a single rectangular handle that fits the entire figure.
     */
    @objid ("be72b7f8-4a93-4787-a259-5115d1ea820a")
    @Override
    protected List<AbstractHandle> createSelectionHandles() {
        final List<AbstractHandle> ret = new ArrayList<>(1);
        ret.add(new MoveHandle((GraphicalEditPart) getHost()));
        
        SelectionHandlesBuilder.disableHandlesIfReadOnly(getHost(), ret);
        return ret;
    }

}
