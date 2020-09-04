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

package org.modelio.diagram.editor.activity.elements.partitioncontainer;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.IModelFactory;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specific command to handle the creation of one (or more) partitions in a partition container.
 */
@objid ("2b1e2ba9-55b6-11e2-877f-002564c97630")
public class CreatePartitionInPartitionContainerCommand extends Command {
    @objid ("2b1e52be-55b6-11e2-877f-002564c97630")
    private MObject parentElement;

    @objid ("2b1e79cd-55b6-11e2-877f-002564c97630")
    private int newConstraint;

    @objid ("2b1e79cf-55b6-11e2-877f-002564c97630")
    private Map<?,?> editPartRegistry;

    @objid ("2b1e79ce-55b6-11e2-877f-002564c97630")
    private CreateRequest originalRequest;

    @objid ("d270720a-55c0-11e2-9337-002564c97630")
    private ModelioCreationContext context;

    @objid ("d270720b-55c0-11e2-9337-002564c97630")
    private GmNodeModel insertAfter;

    @objid ("d270720c-55c0-11e2-9337-002564c97630")
    private GmCompositeNode parentNode;

    /**
     * Creates a node creation command.
     * @param originalRequest the request that lead to this command.
     * @param parentEditPart The parent editPart
     * @param context Details on the MObject and/or the node to create
     * @param insertAfter The editPart used as reference for insertion at the correct place.
     * @param newConstraint the constraint with which to create the child.
     */
    @objid ("2b1ea0dc-55b6-11e2-877f-002564c97630")
    public CreatePartitionInPartitionContainerCommand(CreateRequest originalRequest, EditPart parentEditPart, ModelioCreationContext context, GmNodeModel insertAfter, int newConstraint) {
        this.originalRequest = originalRequest;
        this.editPartRegistry = parentEditPart.getViewer().getEditPartRegistry();
        this.parentNode = (GmCompositeNode) parentEditPart.getModel();
        this.parentElement = this.parentNode.getRelatedElement();
        if (this.parentElement instanceof AbstractDiagram) {
            this.parentElement = ((AbstractDiagram) this.parentElement).getOrigin();
        }
        this.context = context;
        this.insertAfter = insertAfter;
        this.newConstraint = newConstraint;
    }

    @objid ("2b1ec7f0-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        final IGmDiagram diagram = this.parentNode.getDiagram();
        
        final ActivityPartition newElement = (ActivityPartition) this.context.getElementToUnmask();
        
        if (newElement != null) {
            executeUnmask(diagram, newElement);
        } else {
            executeCreation(diagram);
        }
    }

    @objid ("2b1eeefb-55b6-11e2-877f-002564c97630")
    private void executeUnmask(final IGmDiagram diagram, ActivityPartition requestedPartition) {
        ActivityPartition partitionToUnmask = requestedPartition;
        // The request to unmask a partition might concern a sub partition
        // that is not directly under the host: determine the direct
        // subpartition of parentElement.
        while (partitionToUnmask.getSuperPartition() != null && !partitionToUnmask.getSuperPartition().equals(this.parentElement)) {
            partitionToUnmask = partitionToUnmask.getSuperPartition();
        }
        GmCompositeNode newChild = unmaskAdditionalChild(diagram, partitionToUnmask);
        // If the partition we have just unmasked is not the requested one (but
        // one of its "not yet unmasked" super partition) then delegate further
        // work to the unmasked partition body.
        if (!partitionToUnmask.equals(requestedPartition)) {
            // Now we should have a GmPartitionContainer, find the corresponding
            // edit part, and delegate the unmasking of subsequent children to
            // it.
            assert (newChild != null) : "CreatePartitionInPartitionContainerCommand#executeUnmask: could not find a valid Gm for partition: "
                    + partitionToUnmask.getName();
            GmCompositeNode newChildBody = newChild.getCompositeFor(ActivityPartition.class);
            EditPart newChildBodyEditPart = (EditPart) this.editPartRegistry.get(newChildBody);
            Command command = newChildBodyEditPart.getCommand(this.originalRequest);
            if (command != null && command.canExecute()) {
                command.execute();
            }
        }
    }

    /**
     * @param diagram
     */
    @objid ("2b1f160e-55b6-11e2-877f-002564c97630")
    private void executeCreation(final IGmDiagram diagram) {
        ActivityPartition newElement;
        // Create the Element...
        final IModelFactory modelFactory = diagram.getModelManager().getModelFactory();
        newElement = (ActivityPartition) modelFactory.createElement(this.context.getMetaclass());
        
        MExpert mExpert = newElement.getMClass().getMetamodel().getMExpert();
        
        // The new element must be attached to its parent using the composition dependency
        // provided by the context.
        // If the context provides a null dependency, use the default dependency recommended by the metamodel
        MDependency effectiveDependency = this.context.getDependency();
        if (effectiveDependency == null) {
            effectiveDependency = mExpert.getDefaultCompositionDep(this.parentElement, newElement);
        }
        
        // ... and attach it to its parent.
        try {
            this.parentElement.mGet(effectiveDependency).add(newElement);
        } catch (@SuppressWarnings ("unused") Exception e) {
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
        
        // Configure element from properties
        IModelManager modelManager = diagram.getModelManager();
        final IElementConfigurator elementConfigurer = modelManager.getModelServices().getElementConfigurer();
        elementConfigurer.configure(newElement, this.context.getProperties());
        
        // Set default name
        IElementNamer elementNamer = modelManager.getModelServices().getElementNamer();
        newElement.setName(elementNamer.getUniqueName(newElement));
        
        int nbOfChildren = this.parentNode.getChildren(GmPartitionContainer.SUB_PARTITION).size();
        if (nbOfChildren != 0) {
            unmaskAdditionalChild(diagram, newElement);
        } else {
            // No child yet! The request was to actually create a child(and
            // not just unmask an existing one), then create a second child
            // of the same type, attach it, decorate it, and split the
            // available space between the two.
            createAndUnmaskFirstPairOfSubPartitions(diagram, newElement);
            // No subsequent actions expected here, just return.
            return;
        }
    }

    /**
     * @param diagram
     * @param newElement
     */
    @objid ("2b1f3d1d-55b6-11e2-877f-002564c97630")
    private void createAndUnmaskFirstPairOfSubPartitions(final IGmDiagram diagram, ActivityPartition newElement) {
        // Create the Element...
        final IModelFactory modelFactory = diagram.getModelManager().getModelFactory();
        ActivityPartition newElement2 = (ActivityPartition) modelFactory.createElement(this.context.getMetaclass());
        
        MExpert mExpert = newElement.getMClass().getMetamodel().getMExpert();
        
        // The new element must be attached to its parent using the composition dependency
        // provided by the context.
        // If the context provides a null dependency, use the default dependency recommended by the metamodel
        MDependency effectiveDependency = this.context.getDependency();
        if (effectiveDependency == null) {
            effectiveDependency = mExpert.getDefaultCompositionDep(this.parentElement, newElement);
        }
        
        // ... and attach it to its parent.
        try {
            this.parentElement.mGet(effectiveDependency).add(newElement2);
        } catch (@SuppressWarnings ("unused") Exception e) {
            // The dependency indicated in the context cannot be used: try
            // to find a valid one!
            MDependency compositionDep = mExpert.getDefaultCompositionDep(this.parentElement, newElement);
            if (compositionDep != null) {
                this.parentElement.mGet(compositionDep).add(newElement2);
            } else {
                newElement.delete();
                newElement2.delete();
                return;
            }
        }
        
        // Attach the stereotype if needed.
        if (this.context.getStereotype() != null) {
            ((ModelElement) newElement2).getExtension().add(this.context.getStereotype());
        }
        
        // Configure element from properties
        IModelManager modelManager = diagram.getModelManager();
        final IElementConfigurator elementConfigurer = modelManager.getModelServices().getElementConfigurer();
        elementConfigurer.configure(newElement2, this.context.getProperties());
        
        // Set default name
        IElementNamer elementNamer = modelManager.getModelServices().getElementNamer();
        newElement2.setName(elementNamer.getUniqueName(newElement2));
        
        // Show the new elements in the diagram (ie create their Gm )
        diagram.unmask(this.parentNode, newElement, Integer.valueOf(this.newConstraint));
        diagram.unmask(this.parentNode, newElement2, Integer.valueOf(this.newConstraint));
    }

    @objid ("2b1f8b3b-55b6-11e2-877f-002564c97630")
    private GmCompositeNode unmaskAdditionalChild(final IGmDiagram diagram, ActivityPartition newElement) {
        // Update the constraint of all already existing children to make
        // space for the new child, and compute its own constraint.
        int nbOfChildren = this.parentNode.getChildren(GmPartitionContainer.SUB_PARTITION).size();
        for (GmNodeModel child : this.parentNode.getChildren(GmPartitionContainer.SUB_PARTITION)) {
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

    @objid ("2b1fd959-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        if (!MTools.getAuthTool().canModify(this.parentNode.getDiagram().getRelatedElement())) {
            return false;
        }
        
        final ActivityPartition newElement = (ActivityPartition) this.context.getElementToUnmask();
        
        if (newElement != null) {
            return true;
        } else {
            return MTools.getAuthTool().canAdd(this.parentElement, ActivityPartition.MQNAME);
        }
    }

}
