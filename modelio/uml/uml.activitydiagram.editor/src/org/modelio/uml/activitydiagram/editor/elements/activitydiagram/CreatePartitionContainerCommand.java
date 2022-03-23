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
package org.modelio.uml.activitydiagram.editor.elements.activitydiagram;

import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.uml.activitydiagram.editor.elements.partition.PartitionToolKind;
import org.modelio.uml.activitydiagram.editor.elements.partitioncontainer.GmDiagramPartitionContainer;
import org.modelio.uml.activitydiagram.editor.elements.partitioncontainer.GmPartitionContainer;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specific command that creates a PartitionContainer (embedded in an ActivityParameterNodeContainter) on the diagram background.
 * 
 * @author fpoyer
 */
@objid ("2991e65f-55b6-11e2-877f-002564c97630")
public class CreatePartitionContainerCommand extends Command {
    @objid ("2991e661-55b6-11e2-877f-002564c97630")
    private boolean vertical;

    @objid ("2991e668-55b6-11e2-877f-002564c97630")
    private Map<?, ?> editPartRegistry;

    @objid ("2991e665-55b6-11e2-877f-002564c97630")
    private Rectangle constraint;

    @objid ("2991e666-55b6-11e2-877f-002564c97630")
    private GmActivityDiagram parentDiagram;

    @objid ("2991e667-55b6-11e2-877f-002564c97630")
    private CreateRequest originalRequest;

    @objid ("d0a1cc8a-55c0-11e2-9337-002564c97630")
    private ModelioCreationContext context;

    /**
     * C'tor.
     * @param originalRequest the request that lead to this command.
     * @param parentDiagramEditPart the edit part of the diagram in which the partition container is to be created.
     * @param context the creation context.
     * @param constraint the initial constraint of the created partition container.
     * @param vertical whether this is a vertical container.
     */
    @objid ("2991e66c-55b6-11e2-877f-002564c97630")
    public  CreatePartitionContainerCommand(CreateRequest originalRequest, ActivityDiagramEditPart parentDiagramEditPart, ModelioCreationContext context, Rectangle constraint, boolean vertical) {
        this.originalRequest = originalRequest;
        this.editPartRegistry = parentDiagramEditPart.getViewer().getEditPartRegistry();
        this.parentDiagram = (GmActivityDiagram) parentDiagramEditPart.getModel();
        this.context = context;
        this.constraint = constraint;
        this.vertical = vertical;
        
    }

    /**
     * Execution follow the following steps:
     * <ol>
     * <li>Creation of 2 activity partition in the UML model</li>
     * <li>Attachment of these partition under the context of the diagram</li>
     * <li></li>
     * </ol>
     */
    @objid ("2991e676-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        MObject parentElement = this.parentDiagram.getRelatedElement().getOrigin();
        // Determine wether it is actually the unmasking of an existing
        // partition, or the creation of a new one.
        ActivityPartition partition = (ActivityPartition) this.context.getElementToUnmask();
        // If partition is not null, that means we are actually trying to
        // unmask an existing partition that didn't have any unmasked
        // super partition in this diagram at the time this command was
        // issued (this may have changed by the time we get in this
        // execution).
        if (partition != null) {
            executeUnmasking(parentElement, partition);
        } else {
            executeActualCreation(parentElement, this.parentDiagram.getModelManager());
        }
        
    }

    @objid ("2991e67a-55b6-11e2-877f-002564c97630")
    private void executeUnmasking(MObject parentElement, ActivityPartition partition) {
        // Determine the top level super partition: if not yet unmasked, create
        // a partition container and unmask the top level partition in it.
        // Once the top level is unmasked, delegate the unmasking of rest of the
        // hierarchy to it.
        ActivityPartition topLevelPartition = partition;
        while (topLevelPartition.getSuperPartition() != null) {
            topLevelPartition = topLevelPartition.getSuperPartition();
        }
        // Now unmask top level if needed! It might have been unmasked between
        // the moment this command was issued and the moment it is actually
        // executed. For example by another command in the same compound...
        // Once we have an edit part for the body of the top level partition,
        // delegate.
        GmCompositeNode topLevelBodyGm = null;
        // Make sure it is not already unmasked
        List<GmModel> models = this.parentDiagram.getAllGMRepresenting(new MRef(topLevelPartition));
        if (!models.isEmpty()) {
            // Already unmasked: just look for the edit part of its "body".
            topLevelBodyGm = ((GmCompositeNode) models.get(0)).getCompositeFor(ActivityPartition.class);
        
        } else {
            // Not yet unmasked: unmask it and get the edit part of its "body".
            GmNodeModel createdModel = this.parentDiagram.unmask(this.parentDiagram, topLevelPartition, this.constraint);
            if (createdModel instanceof GmDiagramPartitionContainer) {
                // Set the orientation of the container (if the request is to create
                // a "vertical container", what the user wants is actually to have
                // vertical partitions, so the container must be horizontal!)
                ((GmPartitionContainer) createdModel).setVertical(!this.vertical);
            }
        
            // If the requested partition is the top level one, job is done.
            if (topLevelPartition == partition) {
                return;
            }
        
            // Watch out: returned node may not represent current candidate
            // (think: partition container on diagram background), in this case,
            // find a Gm that actually represents the topLevelPartition (there
            // should be at least one now).
            models = this.parentDiagram.getAllGMRepresenting(new MRef(topLevelPartition));
            if (!models.isEmpty()) {
                // Look for its "body" as parent node of next candidate
                // and continue.
                topLevelBodyGm = ((GmCompositeNode) models.get(0)).getCompositeFor(ActivityPartition.class);
            }
        
        }
        // Now we should have a GmPartitionContainer, find the corresponding
        // edit part, and delegate the unmasking of subsequent children to it.
        assert (topLevelBodyGm != null) : "CreatePartitionContainerCommand#executeUnmasking: could not find a valid Gm for body of top level super partition of : "
                + partition.getName();
        EditPart topLevelPartitionBodyEditPart = (EditPart) this.editPartRegistry.get(topLevelBodyGm);
        // From now on, the request must be considered to create an inner
        // partition, not a container anymore.
        this.context.setProperty("kind", PartitionToolKind.INNER.toString());
        Command command = topLevelPartitionBodyEditPart.getCommand(this.originalRequest);
        if (command != null && command.canExecute()) {
            command.execute();
        }
        
    }

    @objid ("2991e683-55b6-11e2-877f-002564c97630")
    private void executeActualCreation(MObject parentElement, IModelManager modelManager) {
        // No existing partition:create 2...
        final IStandardModelFactory modelFactory = this.parentDiagram.getModelManager().getModelFactory().getFactory(IStandardModelFactory.class);
        ActivityPartition partition1 = modelFactory.createActivityPartition();
        ActivityPartition partition2 = modelFactory.createActivityPartition();
        
        // ... and attach them to the parent.
        try {
            final MDependency dependency = this.context.getDependency();
            final List<MObject> elements = parentElement.mGet(dependency);
            elements.add(partition1);
            elements.add(partition2);
        } catch (@SuppressWarnings ("unused") Exception e) {
            // The dependency indicated in the context cannot be used: try
            // to find a valid one!
            MExpert mExpert = parentElement.getMClass().getMetamodel().getMExpert();
            MDependency compositionDep = mExpert.getDefaultCompositionDep(parentElement, partition1);
            if (compositionDep != null) {
                final List<MObject> elements = parentElement.mGet(compositionDep);
                elements.add(partition1);
                elements.add(partition2);
            } else {
                partition1.delete();
                partition2.delete();
                return;
            }
        }
        
        // Attach the stereotype if needed.
        if (this.context.getStereotype() != null) {
            ((ModelElement) partition1).getExtension().add(this.context.getStereotype());
            ((ModelElement) partition2).getExtension().add(this.context.getStereotype());
        }
        
        IElementNamer elementNamer = modelManager.getModelServices().getElementNamer();
        partition1.setName(elementNamer.getUniqueName(partition1));
        partition2.setName(elementNamer.getUniqueName(partition2));
        
        // Unmask the first partition
        GmPartitionContainer partitionContainer = (GmPartitionContainer) this.parentDiagram.unmask(this.parentDiagram, partition1,
                this.constraint);
        // Unmask the second partition in the same partition container.
        this.parentDiagram.unmask(partitionContainer, partition2, this.constraint);
        // Set the orientation of the container (if the request is to create
        // a "vertical container", what the user wants is actually to have
        // vertical partitions, so the container must be horizontal!)
        partitionContainer.setVertical(!this.vertical);
        // Set the constraints of both partition
        // TODO find a better way than forcing the constraint to be a
        // Rectangle...
        Integer partitionConstraint = Integer.valueOf(this.constraint.width / partitionContainer.getChildren().size());
        for (GmNodeModel gmPartition : partitionContainer.getChildren()) {
            gmPartition.setLayoutData(partitionConstraint);
        }
        
    }

    @objid ("2991e689-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        if (!MTools.getAuthTool().canModify(this.parentDiagram.getRelatedElement())) {
            return false;
        }
        
        MObject parentElement = this.parentDiagram.getRelatedElement().getOrigin();
        return (parentElement != null && parentElement.isValid() && parentElement.getStatus().isModifiable());
    }

}
