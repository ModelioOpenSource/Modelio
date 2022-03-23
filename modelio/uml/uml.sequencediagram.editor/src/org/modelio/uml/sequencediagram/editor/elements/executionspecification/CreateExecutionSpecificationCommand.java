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
package org.modelio.uml.sequencediagram.editor.elements.executionspecification;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Creation Command for Execution. Creates (and Unmask) Execution on a Lifeline or on another Execution.
 */
@objid ("d8e37dda-55b6-11e2-877f-002564c97630")
public class CreateExecutionSpecificationCommand extends Command {
    @objid ("50086f09-55c2-11e2-9337-002564c97630")
    private GmCompositeNode parentNode;

    @objid ("085e9002-3d34-495e-bb81-45eec66266e9")
    private Rectangle initialLayoutData;

    /**
     * C'tor.
     * @param parentNode the node into which the created execution should be unmasked.
     * @param initialLayoutData the initial layout data to use. X coordinate will be ignored, since it will be updated in the container's layout.
     */
    @objid ("d8e37de0-55b6-11e2-877f-002564c97630")
    public  CreateExecutionSpecificationCommand(final GmCompositeNode parentNode, final Rectangle initialLayoutData) {
        this.initialLayoutData = initialLayoutData;
        this.parentNode = parentNode;
        
    }

    @objid ("d8e37de9-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        // Create the MObject
        MObject parentElement = this.parentNode.getRelatedElement();
        final IGmDiagram diagram = this.parentNode.getDiagram();
        IModelManager modelManager = diagram.getModelManager();
        final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
        
        Interaction interaction = null;
        Lifeline lifeline = null;
        
        if (parentElement instanceof Lifeline) {
            lifeline = ((Lifeline) parentElement);
            interaction = lifeline.getOwner();
        } else if (parentElement instanceof ExecutionSpecification) {
            lifeline = ((ExecutionSpecification) parentElement).getCovered().get(0);
            interaction = lifeline.getOwner();
        }
        
        if (lifeline != null) {
            // When a lifeline has a creation message, current layout data must be shifted to avoid triggering model shield
            for (InteractionFragment fragment : lifeline.getCoveredBy()) {
                if (fragment instanceof ExecutionOccurenceSpecification) {
                    ExecutionOccurenceSpecification eos = (ExecutionOccurenceSpecification) fragment;
                    if (eos.getReceivedMessage() != null) {
                        Message receivedMessage = eos.getReceivedMessage();
                        if (receivedMessage.getSortOfMessage() == MessageSort.CREATEMESSAGE) {
                            // we found a creation message end on the lifeline
                            this.initialLayoutData.y += eos.getLineNumber();
                            break;
                        }
                    }
                }
            }
        }
        
        // Use the collected elements to create and initialize the execution.
        ExecutionOccurenceSpecification startOccurence = modelFactory.createExecutionOccurenceSpecification();
        startOccurence.setEnclosingInteraction(interaction);
        startOccurence.getCovered().add(lifeline);
        startOccurence.setLineNumber(Math.max(1, this.initialLayoutData.y));
        
        ExecutionOccurenceSpecification finishOccurence = modelFactory.createExecutionOccurenceSpecification();
        finishOccurence.setEnclosingInteraction(interaction);
        finishOccurence.getCovered().add(lifeline);
        finishOccurence.setLineNumber(this.initialLayoutData.bottom());
        
        ExecutionSpecification newExecution = modelFactory.createExecutionSpecification();
        newExecution.setEnclosingInteraction(interaction);
        newExecution.getCovered().add(lifeline);
        newExecution.setStart(startOccurence);
        newExecution.setFinish(finishOccurence);
        
        // Show the new element in the diagram (ie create its Gm )
        diagram.unmask(this.parentNode, startOccurence, this.initialLayoutData);
        diagram.unmask(this.parentNode, finishOccurence, this.initialLayoutData);
        diagram.unmask(this.parentNode, newExecution, this.initialLayoutData);
        
    }

}
