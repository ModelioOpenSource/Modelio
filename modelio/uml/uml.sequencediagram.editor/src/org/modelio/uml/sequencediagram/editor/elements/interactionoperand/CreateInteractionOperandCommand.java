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
package org.modelio.uml.sequencediagram.editor.elements.interactionoperand;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("d902025a-55b6-11e2-877f-002564c97630")
public class CreateInteractionOperandCommand extends Command {
    @objid ("d902025b-55b6-11e2-877f-002564c97630")
    private int newConstraint;

    @objid ("5020d918-55c2-11e2-9337-002564c97630")
    private ModelioCreationContext context;

    @objid ("5020d919-55c2-11e2-9337-002564c97630")
    private GmNodeModel insertAfter;

    @objid ("50225fa9-55c2-11e2-9337-002564c97630")
    private GmCompositeNode parentNode;

    @objid ("cf109e29-c07d-4880-9aa5-038b2a345f98")
    private CombinedFragment parentElement;

    /**
     * Creates a node creation command.
     * @param parentEditPart The parent editPart
     * @param context Details on the MObject and/or the node to create
     * @param insertAfter The editPart used as reference for insertion at the correct place.
     * @param newConstraint the constraint with which to create the child.
     */
    @objid ("d9020268-55b6-11e2-877f-002564c97630")
    public  CreateInteractionOperandCommand(final EditPart parentEditPart, final ModelioCreationContext context, final GmNodeModel insertAfter, final int newConstraint) {
        this.parentNode = (GmCompositeNode) parentEditPart.getModel();
        this.parentElement = (CombinedFragment) this.parentNode.getRelatedElement();
        this.context = context;
        this.insertAfter = insertAfter;
        this.newConstraint = newConstraint;
        
    }

    @objid ("d9020277-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        final IGmDiagram diagram = this.parentNode.getDiagram();
        
        MObject newElement = this.context.getElementToUnmask();
        
        if (newElement != null) {
            unmaskAdditionalChild(diagram, newElement);
        } else {
            executeCreation(diagram);
        }
        
    }

    @objid ("d902027a-55b6-11e2-877f-002564c97630")
    private void executeCreation(final IGmDiagram diagram) {
        final IModelManager modelManager = diagram.getModelManager();
        final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
        // Create the Element...
        InteractionOperand newElement = (InteractionOperand) modelFactory.createElement(this.context.getMetaclass());
        
        // Attach the stereotype if needed.
        if (this.context.getStereotype() != null) {
            ((ModelElement) newElement).getExtension().add(this.context.getStereotype());
        }
        
        // Configure element from properties
        final IElementConfigurator elementConfigurer = modelManager.getModelServices().getElementConfigurer();
        elementConfigurer.configure(newElement, this.context.getProperties());
        
        // Set default name
        newElement.setName(modelManager.getModelServices().getElementNamer().getUniqueName(newElement));
        
        newElement.setEnclosingInteraction(this.parentElement.getEnclosingInteraction());
        
        // line numbers handling
        EList<InteractionOperand> allOperand = this.parentElement.getOperand();
        if (this.insertAfter == null) {
            // insert at the end of the combined fragment, so endline is endline of last operand (or line of combined framgent if no operand at all) + constraint (if not == -1).
            int endLine = this.parentElement.getLineNumber();
            for (InteractionOperand operand : allOperand) {
                if (operand.getEndLineNumber() > endLine) {
                    endLine = operand.getEndLineNumber();
                }
            }
            this.parentElement.getOperand().add(newElement);
            newElement.setLineNumber(endLine + 1);
        
            newElement.setEndLineNumber(endLine + this.newConstraint);
        } else {
            // insert before the reference: get the previous (to use as line number) and move reference and all below down to make space.
            InteractionOperand referenceOperand = (InteractionOperand) this.insertAfter.getRelatedElement();
            int indexOfReference = allOperand.indexOf(referenceOperand);
            int lineNumber = referenceOperand.getLineNumber();
            for (InteractionOperand operand : allOperand) {
                if (operand.getLineNumber() >= lineNumber) {
                    operand.setLineNumber(operand.getLineNumber() + this.newConstraint + 1);
                    operand.setEndLineNumber(operand.getEndLineNumber() + this.newConstraint + 1);
                }
            }
            // reorder children so that new element is at the right place!
            List<InteractionOperand> reorderedOperands = new ArrayList<>(allOperand.size() + 1);
            for (int i = 0; i < indexOfReference; ++i) {
                reorderedOperands.add(allOperand.get(i));
            }
            reorderedOperands.add(newElement);
            for (int i = indexOfReference; i < allOperand.size(); ++i) {
                reorderedOperands.add(allOperand.get(i));
            }
            this.parentElement.getOperand().add(newElement);
            newElement.setLineNumber(lineNumber);
            newElement.setEndLineNumber(lineNumber + this.newConstraint);
            allOperand.clear();
            allOperand.addAll(reorderedOperands);
        }
        
        unmaskAdditionalChild(diagram, newElement);
        
    }

    @objid ("d9020280-55b6-11e2-877f-002564c97630")
    private GmCompositeNode unmaskAdditionalChild(final IGmDiagram diagram, final MObject newElement) {
        // Show the new element in the diagram (ie create its Gm )
        GmCompositeNode newChild = (GmCompositeNode) diagram.unmask(this.parentNode,
                newElement,
                Integer.valueOf(this.newConstraint));
        // Put it at the correct place
        this.parentNode.moveChild(newChild, this.parentNode.getChildren().indexOf(this.insertAfter));
        return newChild;
    }

}
