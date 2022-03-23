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
package org.modelio.uml.sequencediagram.editor.elements.modelmanipulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Helper to be used by edit policies that moves interaction elements vertically.
 * <p>
 * Computes whether the move is valid, gives feedback to the user and could also compute the command.
 * <p>
 * Usage:
 * <ul>
 * <li>to be instantiated in {@link org.eclipse.gef.EditPolicy#activate()} or lazilly.
 * <li>from {@link org.eclipse.gef.EditPolicy#showSourceFeedback(org.eclipse.gef.Request)} and {@link org.eclipse.gef.EditPolicy#getCommand(org.eclipse.gef.Request)} (directly or indirectly) :
 * <ul>
 * <li>call {@link #computePredicatesForHost(MObject...)} for all involved model elements
 * <li>call {@link #updateVariable(MObject, int)}/{@link #updateVariable(TimeReference, int)} on each model element to be moved.
 * <li>then call {@link #showFeedBack(IFigure)} or {@link #checkAllPredicates()} to know whether the move is valid.
 * </ul>
 * <li>from {@link org.eclipse.gef.EditPolicy#eraseSourceFeedback(org.eclipse.gef.Request)} , call {@link #eraseFeedback(IFigure)}.
 * <li>in some cases {@link #eraseFeedback(IFigure)} must be called from {@link org.eclipse.gef.EditPolicy#showSourceFeedback(org.eclipse.gef.Request)} when the request type changes from REQ_MOVE to REQ_ADD.
 * </ul>
 */
@objid ("9c507aaa-8711-4355-9562-5ecd1a42cf83")
public class ManipulationHelper {
    @objid ("d9653132-55b6-11e2-877f-002564c97630")
    private final Map<TimeReference, Variable> variables = new HashMap<>();

    @objid ("d966b79a-55b6-11e2-877f-002564c97630")
    private final List<Predicate> predicates = new ArrayList<>();

    @objid ("f929b32a-2dbd-4153-84d5-146f1b77dd30")
    private final List<IFigure> fbRectangles = new ArrayList<>();

    @objid ("87da3209-2745-46c9-9726-0f8da8c9272f")
    private final GraphicalEditPart host;

    /**
     * Initialize the helper.
     * @param host the host edit part.
     */
    @objid ("25cf255d-1267-4a12-a8f2-a57dd6ba3f0d")
    public  ManipulationHelper(GraphicalEditPart host) {
        this.host = host;
    }

    /**
     * Reset the helper and computes predicates that must be valid.
     * @param objs the interaction element(s) handled by the host edit part.
     */
    @objid ("ddb5ecb0-a980-4f34-88f5-c8c3b839890f")
    public void computePredicatesForHost(MObject... objs) {
        this.variables.clear();
        this.predicates.clear();
        
        for (MObject obj : objs) {
            if (obj instanceof Message) {
                computePredicatesForMessage((Message) obj);
            } else if (obj instanceof MessageEnd) {
                computePredicatesForMessageEnd((MessageEnd) obj);
            } else {
                throw new UnsupportedOperationException(obj.toString());
            }
        }
        
    }

    /**
     * Show source feed back on the given feedback layer.
     * <p>
     * Display rectangles in red where predicates fail.
     * @param fbLayer the feedback layer.
     */
    @objid ("c86dd748-832c-4c1f-b3cf-feab04b57f05")
    public void showFeedBack(IFigure fbLayer) {
        // remove existing feedback rectangles
        for (IFigure fig : this.fbRectangles) {
            fbLayer.remove(fig);
        }
        this.fbRectangles.clear();
        
        final Color redColor = ColorConstants.red;
        final Color greenColor = ColorConstants.green;
        
        for (Predicate pr : this.predicates) {
            for (Variable v : pr.getVariables()) {
                MObject el = v.getRef().getElement();
                final GraphicalEditPart elEditPart = getEditPart(el);
                if (elEditPart == null) {
                    continue;
                }
                final IFigure elFig = elEditPart.getFigure();
                Rectangle r = elFig.getBounds().getCopy();
                r.y = v.getValue() - r.height / 2;
                elFig.translateToAbsolute(r);
                fbLayer.translateToRelative(r);
                RectangleFigure rf = new RectangleFigure();
                if (!pr.evaluate()) {
                    rf.setForegroundColor(redColor);
                } else {
                    rf.setForegroundColor(greenColor);
                }
                rf.setOpaque(false);
                rf.setFill(false);
                rf.setLineStyle(Graphics.LINE_DASH);
                // rf.setAlpha(50);
                rf.setBounds(r);
                rf.validate();
                fbLayer.add(rf, r);
                this.fbRectangles.add(rf);
            }
        }
        
    }

    @objid ("0f5e6d35-8ef6-44ca-aa4b-947c0eff6cca")
    private GraphicalEditPart getEditPart(MObject obj) {
        GmModel messageModel = (GmModel) this.host.getModel();
        Collection<GmModel> sourceEndModels = messageModel.getDiagram().getAllGMRelatedTo(new MRef(obj));
        for (GmModel sourceEndModel : sourceEndModels) {
            if (((GmNodeModel) sourceEndModel).isVisible()) {
                return getEditPart(sourceEndModel);
            }
        }
        return null;
    }

    @objid ("11a11236-0781-4437-a5c1-0e4fbfed3bef")
    private GraphicalEditPart getEditPart(GmModel model) {
        return (GraphicalEditPart) this.host.getViewer().getEditPartRegistry().get(model);
    }

    /**
     * Update the {@link Variable} for a {@link MObject}.
     * <p>
     * In case of initialization the variable is created if non existent.
     * @param ref the time reference
     * @param value the new line value
     * @return the found or created variable.
     */
    @objid ("b470caed-393e-47e5-8a9c-f9ecf753312a")
    public Variable updateVariable(MObject ref, int value) {
        return updateVariable(new TimeReference(ref), value);
    }

    /**
     * Update the {@link Variable} for a {@link TimeReference}.
     * <p>
     * In case of initialization the variable is created if non existent.
     * @param ref the time reference
     * @param value the new line value
     * @return the found or created variable.
     */
    @objid ("e49f3b33-e937-4d3b-98cc-50b28fdcb860")
    public Variable updateVariable(TimeReference ref, int value) {
        Variable variable2 = this.variables.get(ref);
        if (variable2 == null) {
            // assert (false);
        } else {
            if (!variable2.isUpdated()) {
                variable2.setValue(value);
                variable2.setUpdated(true);
            }
        }
        return variable2;
    }

    /**
     * Tests whether the move is valid.
     * @return <i>true</i> if the move is valid else <i>false</i>.
     */
    @objid ("d9683e49-55b6-11e2-877f-002564c97630")
    public boolean checkAllPredicates() {
        for (Predicate predicate : this.predicates) {
            if (!predicate.evaluate()) {
                return false;
            }
        }
        
        // All predicate evaluated to true
        return true;
    }

    /**
     * @param message a Message
     */
    @objid ("d9683e53-55b6-11e2-877f-002564c97630")
    private void computePredicatesForMessage(final Message message) {
        computePredicatesForMessageEnd(message.getSendEvent());
        
        if (message.getSendEvent() instanceof ExecutionOccurenceSpecification) {
            ExecutionOccurenceSpecification startEos = (ExecutionOccurenceSpecification) message.getSendEvent();
            if (startEos.getStarted() != null) {
                ExecutionOccurenceSpecification finishEos = startEos.getStarted().getFinish();
                computePredicatesForMessageEnd(finishEos);
            }
        }
        
        computePredicatesForMessageEnd(message.getReceiveEvent());
        
        if (message.getReceiveEvent() instanceof ExecutionOccurenceSpecification) {
            ExecutionOccurenceSpecification recvEvent = (ExecutionOccurenceSpecification) message.getReceiveEvent();
            if (recvEvent.getStarted() != null) {
                ExecutionOccurenceSpecification finishEos = recvEvent.getStarted().getFinish();
                
                computePredicatesForMessageEnd(finishEos);
        
                Message finishSentMessage = finishEos.getSentMessage();
                if (finishSentMessage != null &&
                        finishSentMessage.getSortOfMessage() == MessageSort.RETURNMESSAGE) {
                    computePredicatesForMessage(finishSentMessage);
                }
            }
        }
        
    }

    @objid ("d9683e4d-55b6-11e2-877f-002564c97630")
    private void computePredicatesForMessageEnd(final MessageEnd messageEnd) {
        TimeReference timeReference = new TimeReference(messageEnd);
        Variable variable = initVariable(timeReference, messageEnd.getLineNumber());
        
        if (messageEnd instanceof ExecutionOccurenceSpecification) {
            final ExecutionOccurenceSpecification eosDot = (ExecutionOccurenceSpecification) messageEnd;
            if (eosDot.getStarted() != null) {
                ExecutionOccurenceSpecification msgFinish = eosDot.getStarted()
                        .getFinish();
                TimeReference msgFinishTimeRef = new TimeReference(msgFinish);
                Variable msgfinishVar = initVariable(msgFinishTimeRef, msgFinish.getLineNumber());
        
                Predicate predicate = new IsBeforePredicate(variable,
                        msgfinishVar,
                        0/* execution specification min size */,
                        true);
                this.predicates.add(predicate);
            }
            if (eosDot.getFinished() != null) {
                ExecutionOccurenceSpecification msgStart = eosDot.getFinished()
                        .getStart();
                TimeReference msgStartTimeRef = new TimeReference(msgStart);
                Variable msgStartVar = initVariable(msgStartTimeRef, msgStart.getLineNumber());
        
                Predicate predicate = new IsBeforePredicate(msgStartVar,
                        variable,
                        0/* execution specification min size */,
                        true);
                this.predicates.add(predicate);
            }
        }
        if (messageEnd.getSentMessage() != null) {
            // the message target must be after this end.
            MessageEnd otherEnd = messageEnd.getSentMessage().getReceiveEvent();
            TimeReference otherEndTimeReference = new TimeReference(otherEnd);
            Variable variable2 = initVariable(otherEndTimeReference, otherEnd.getLineNumber());
        
            Predicate predicate = new IsBeforePredicate(variable, variable2, 0, false);
            this.predicates.add(predicate);
        }
        if (messageEnd.getReceivedMessage() != null) {
            // the message start must be before this end
            MessageEnd otherEnd = messageEnd.getReceivedMessage().getSendEvent();
            TimeReference otherEndTimeReference = new TimeReference(otherEnd);
            Variable variable2 = initVariable(otherEndTimeReference, otherEnd.getLineNumber());
            Predicate predicate = new IsBeforePredicate(variable2, variable, 0, false);
            this.predicates.add(predicate);
        }
        
        computeCreateDestroyPredicates(messageEnd, variable);
        
    }

    /**
     * Initialize a {@link Variable} for a {@link TimeReference}.
     * <p>
     * The variable is created if non existent. If the variable already exists it is directly returned.
     * @param ref the time reference
     * @param value the new line value
     * @return the found or created variable.
     */
    @objid ("50732a66-849a-425f-9f08-002c2c621b91")
    private Variable initVariable(TimeReference ref, int value) {
        Variable variable2 = this.variables.get(ref);
        if (variable2 == null) {
            variable2 = new Variable(ref);
            variable2.setValue(value);
            this.variables.put(ref, variable2);
        } else {
            // already done
            // variable2.setValue(value);
        }
        return variable2;
    }

    /**
     * Erase source feedback.
     * @param fbLayer The feedback layer.
     */
    @objid ("88f80ce7-aac5-445f-91d5-a149ad2d2e13")
    public void eraseFeedback(IFigure fbLayer) {
        for (IFigure fig : this.fbRectangles) {
            fbLayer.remove(fig);
        }
        this.fbRectangles.clear();
        
    }

    /**
     * Ensure forMessageEnd is not moved before a creation message or after a destroy message received on its lifeline.
     * @param forMessageEnd a message end
     * @param forVariable the message end variable
     * @since 3.7.1
     */
    @objid ("fa2f65f0-00c4-4e42-a081-3a1b299f8432")
    private void computeCreateDestroyPredicates(MessageEnd forMessageEnd, Variable forVariable) {
        for (Lifeline lifeline : forMessageEnd.getCovered()) {
            for (InteractionFragment ifr : lifeline.getCoveredBy()) {
                if (ifr instanceof MessageEnd && !ifr.equals(forMessageEnd)) {
                    MessageEnd messageEnd = (MessageEnd) ifr;
                    Message recMessage = messageEnd.getReceivedMessage();
                    if (recMessage == null) {
                        // continue
                    } else if (recMessage.getSortOfMessage() == MessageSort.CREATEMESSAGE) {
                        // no message before recMessage
                        TimeReference creationTimeRef = new TimeReference(messageEnd);
                        Variable creationVar = initVariable(creationTimeRef, messageEnd.getLineNumber());
                        Predicate predicate = new IsBeforePredicate(creationVar, forVariable, 0, true);
                        this.predicates.add(predicate);
                    } else if (recMessage.getSortOfMessage() == MessageSort.DESTROYMESSAGE) {
                        // no message after recMessage
                        TimeReference destroyTimeRef = new TimeReference(messageEnd);
                        Variable destroyVar = initVariable(destroyTimeRef, messageEnd.getLineNumber());
                        Predicate predicate = new IsBeforePredicate(forVariable, destroyVar, 0, true);
                        this.predicates.add(predicate);
                    } 
                }
            }
        }
        
        Message recMessage = forMessageEnd.getReceivedMessage();
        if (recMessage == null) {
            // continue
        } else if (recMessage.getSortOfMessage() == MessageSort.CREATEMESSAGE) {
            // no message before forMessageEnd
            for (Lifeline lifeline : forMessageEnd.getCovered()) {
                for (InteractionFragment ifr : lifeline.getCoveredBy()) {
                    if ( !ifr.equals(forMessageEnd)) {
                        TimeReference fTimeRef = new TimeReference(ifr);
                        Variable fVar = initVariable(fTimeRef, ifr.getLineNumber());
                        Predicate predicate = new IsBeforePredicate(forVariable, fVar, 0, true);
                        this.predicates.add(predicate);
                    }
                }
            }
        } else if (recMessage.getSortOfMessage() == MessageSort.DESTROYMESSAGE) {
            // no message after forMessageEnd
            for (Lifeline lifeline : forMessageEnd.getCovered()) {
                for (InteractionFragment ifr : lifeline.getCoveredBy()) {
                    if ( !ifr.equals(forMessageEnd)) {
                        TimeReference fTimeRef = new TimeReference(ifr);
                        Variable fVar = initVariable(fTimeRef, ifr.getLineNumber());
                        Predicate predicate = new IsBeforePredicate(fVar, forVariable, 0, true);
                        this.predicates.add(predicate);
                    }
                }
            }
        }
        
    }

}
