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

package org.modelio.diagram.elements.common.resizablegroup;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.model.api.IModelFactory;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specific command to handle the creation of one (or more) child(ren) in a resizable group container.
 * 
 * @author fpoyer
 */
@objid ("7f0768d6-1dec-11e2-8cad-001ec947c8cc")
public class AddChildToGroupCommand extends Command {
    @objid ("7f0768da-1dec-11e2-8cad-001ec947c8cc")
    private int newConstraint;

    @objid ("7f0768db-1dec-11e2-8cad-001ec947c8cc")
    private ModelioCreationContext context;

    @objid ("7f0768dc-1dec-11e2-8cad-001ec947c8cc")
    private GmNodeModel insertAfter;

    @objid ("7f0768dd-1dec-11e2-8cad-001ec947c8cc")
    private MObject parentElement;

    @objid ("7f0768de-1dec-11e2-8cad-001ec947c8cc")
    private GmCompositeNode parentNode;

    /**
     * Creates a node creation command.
     * 
     * @param parentEditPart The parent editPart
     * @param context Details on the MObject and/or the node to create
     * @param insertAfter The editPart used as reference for insertion at the correct place.
     * @param newConstraint the constraint with which to create the child.
     */
    @objid ("7f0768df-1dec-11e2-8cad-001ec947c8cc")
    public AddChildToGroupCommand(EditPart parentEditPart, ModelioCreationContext context, GmNodeModel insertAfter, int newConstraint) {
        this.parentNode = (GmCompositeNode) parentEditPart.getModel();
        this.parentElement = this.parentNode.getRelatedElement();
        if (this.parentElement instanceof AbstractDiagram) {
            this.parentElement = ((AbstractDiagram) this.parentElement).getOrigin();
        }
        this.context = context;
        this.insertAfter = insertAfter;
        this.newConstraint = newConstraint;
    }

    @objid ("7f09cb2f-1dec-11e2-8cad-001ec947c8cc")
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

    /**
     * @param diagram
     */
    @objid ("7f09cb32-1dec-11e2-8cad-001ec947c8cc")
    private void executeCreation(final IGmDiagram diagram) {
        MObject newElement;
        IModelManager modelManager = diagram.getModelManager();
        MExpert mExpert = modelManager.getMetamodel().getMExpert();
        
        // Create the Element...
        final IModelFactory modelFactory = modelManager.getModelFactory();
        newElement = modelFactory.createElement(this.context.getMetaclass());
        
        // ... and attach it to its parent.
        try {
            MDependency dependency = this.context.getDependency();
        
            if (dependency == null) {
                // No dependency provided by context, fetch the default one.
                dependency = mExpert.getDefaultCompositionDep(this.parentElement, newElement);
            }
            this.parentElement.mGet(dependency).add(newElement);
        } catch (@SuppressWarnings("unused") Exception e) {
            // The dependency indicated in the context cannot be used: try
            // to find a valid one!
            MDependency compositionDep = mExpert.getDefaultCompositionDep(this.parentElement, newElement);
            if (compositionDep != null) {
                this.parentElement.mGet(compositionDep).add(newElement);
            } else {
                newElement.delete();
                return;
            }
        }
        
        // Attach the stereotype if needed.
        if (this.context.getStereotype() != null) {
            ((ModelElement) newElement).getExtension().add(this.context.getStereotype());
        }
        
        // Set default name
        newElement.setName(modelManager.getModelServices().getElementNamer().getUniqueName(newElement));
        
        unmaskAdditionalChild(diagram, newElement);
    }

    @objid ("7f09cb37-1dec-11e2-8cad-001ec947c8cc")
    private GmCompositeNode unmaskAdditionalChild(final IGmDiagram diagram, MObject newElement) {
        // Update the constraint of all already existing children to make
        // space for the new child, and compute its own constraint.
        int nbOfChildren = this.parentNode.getChildren().size();
        for (GmNodeModel child : this.parentNode.getChildren()) {
            Integer childConstraint = (Integer) child.getLayoutData();
            if (childConstraint.intValue() != -1) {
                childConstraint = Integer.valueOf(childConstraint.intValue() * nbOfChildren / (nbOfChildren + 1));
                child.setLayoutData(childConstraint);
            }
        }
        // Show the new element in the diagram (ie create its Gm )
        GmCompositeNode newChild = (GmCompositeNode) diagram.unmask(this.parentNode, newElement,
                Integer.valueOf(this.newConstraint));
        
        // Put it at the correct place
        this.parentNode.moveChild(newChild, this.parentNode.getChildren().indexOf(this.insertAfter));
        return newChild;
    }

    @objid ("7f09cb3e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        final IGmDiagram diagram = this.parentNode.getDiagram();
        if (!MTools.getAuthTool().canModify(diagram.getRelatedElement())) {
            return false;
        }
        
        if (this.context.getElementToUnmask() == null) {
            return MTools.getAuthTool().canAdd(this.parentElement, this.context.getMetaclass());
        } else {
            return true;
        }
    }

}
