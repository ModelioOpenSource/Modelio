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
package org.modelio.bpmn.diagram.editor.elements.bpmnlinkedobject;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.common.linkednode.CreateLinkedNodeCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.anchors.GmLinkAnchor;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * {@link GmNodeModel} creation command that:
 * <ul>
 * <li>creates and initialize the MObject if asked.
 * <li>creates the {@link GmNodeModel} and unmask it.
 * </ul>
 * according to the provided {@link ModelioCreationContext}.
 */
@objid ("614cde1e-55b6-11e2-877f-002564c97630")
public class BpmnLinkedObjectCommand extends CreateLinkedNodeCommand {
    @objid ("3ffd2089-bece-47ec-beb7-40dfe61f1f41")
    private Dimension sourceAnchorLocation;

    /**
     * Creates a node creation command.
     * @param context Details on the MObject and/or the node to create
     */
    @objid ("614e64c6-55b6-11e2-877f-002564c97630")
    public  BpmnLinkedObjectCommand(ModelioCreationContext context) {
        super(context);
    }

    @objid ("614e64cc-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        final IGmDiagram diagram = this.sourceNode.getDiagram();
        
        BpmnItemAwareElement newElement = (BpmnItemAwareElement) this.context.getElementToUnmask();
        List<BpmnDataAssociation> dataAssociation = new ArrayList<>();
        // GmNodeModel source = null;
        // GmNodeModel target = null;
        
        if (newElement == null) {
            final IModelManager modelManager = diagram.getModelManager();
            final MExpert expert = this.context.getMetaclass().getMetamodel().getMExpert();
        
            // Create the Element...
            final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
            newElement = (BpmnItemAwareElement) modelFactory.createElement(this.context.getMetaclass());
        
            // The new element must be attached to its parent using the composition dependency
            // provided by the context.
            // If the context provides a null dependency, use the default dependency recommended by the metamodel
            MDependency effectiveDependency = this.context.getDependency();
            if (effectiveDependency == null) {
                effectiveDependency = expert
                        .getDefaultCompositionDep(this.parentElement, newElement);
            }
        
            // ... and attach it to its parent.
            try {
                this.parentElement.mGet(effectiveDependency).add(newElement);
            } catch (@SuppressWarnings ("unused") Exception e) {
                // The dependency indicated in the context cannot be used: try
                // to find a valid one!
                MDependency compositionDep = expert.getDefaultCompositionDep(this.parentElement, newElement);
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
        
            // Configure element from properties
            final IElementConfigurator elementConfigurer = modelManager.getModelServices().getElementConfigurer();
            elementConfigurer.configure(newElement, this.context.getProperties());
        
            BpmnDataAssociation newDataAssociation = modelFactory.createBpmnDataAssociation();
        
            if (this.parentElement instanceof BpmnActivity) {
                BpmnActivity owner = (BpmnActivity) this.parentElement;
                if (newElement instanceof BpmnDataOutput) {
                    owner.getDataOutputAssociation().add(newDataAssociation);
                    newDataAssociation.setTargetRef(newElement);
                } else {
                    owner.getDataInputAssociation().add(newDataAssociation);
                    newDataAssociation.getSourceRef().add(newElement);
                }
            } else if (this.parentElement instanceof BpmnThrowEvent) {
                BpmnThrowEvent owner = (BpmnThrowEvent) this.parentElement;
                newDataAssociation.setStartingEvent(owner);
                newDataAssociation.getSourceRef().add(newElement);
                newDataAssociation.getSourceRef().add(newElement);
            } else if (this.parentElement instanceof BpmnCatchEvent) {
                BpmnCatchEvent owner = (BpmnCatchEvent) this.parentElement;
                newDataAssociation.setEndingEvent(owner);
                newDataAssociation.setTargetRef(newElement);
            }
        
            dataAssociation.add(newDataAssociation);
        } else {
            dataAssociation.addAll(newElement.getTargetOfDataAssociation());
            dataAssociation.addAll(newElement.getSourceOfDataAssociation());
        }
        
        // Show the new element in the diagram (ie create its Gm )
        final Rectangle rect = this.size != null ? new Rectangle(this.location, this.size)
                : new Rectangle(this.location, new Dimension(-1, -1));
        
        final GmNodeModel createdNode = diagram.unmask(this.destNode, newElement, rect);
        
        // Show the link between the source node and the unmasked node
        if (this.sourceNode != this.destNode) {
            for (BpmnDataAssociation link : dataAssociation) {
                if (newElement instanceof BpmnDataOutput) {
                    GmPath gmpath = new GmPath();
                    gmpath.setSourceAnchor(new GmLinkAnchor(this.sourceAnchorLocation));
                    gmpath.setTargetAnchor(new GmLinkAnchor(new Dimension(0, 10)));
        
                    @SuppressWarnings ("unused")
                    IGmLink gmlink = diagram.unmaskLink(link, this.sourceNode, createdNode, gmpath);
                } else {
                    GmPath gmpath = new GmPath();
                    gmpath.setSourceAnchor(new GmLinkAnchor(this.sourceAnchorLocation));
                    gmpath.setTargetAnchor(new GmLinkAnchor(new Dimension(0, 10)));
        
                    @SuppressWarnings ("unused")
                    IGmLink gmlink = diagram.unmaskLink(link, createdNode, this.sourceNode, gmpath);
                }
        
            }
        }
        
    }

    /**
     * Set the node inside which the node will be created.
     * @param destNode The node in which the node will be created.
     */
    @objid ("614e64cf-55b6-11e2-877f-002564c97630")
    @Override
    public void setDestinationNode(GmCompositeNode destNode) {
        this.destNode = destNode;
    }

    /**
     * Set the node location.
     * @param location The location in absolute coordinates.
     */
    @objid ("614e64d6-55b6-11e2-877f-002564c97630")
    @Override
    public void setNodeLocation(Point location) {
        this.location = location;
    }

    /**
     * set the node size.
     * @param size The size of the node to create
     */
    @objid ("614e64db-55b6-11e2-877f-002564c97630")
    @Override
    public void setNodeSize(Dimension size) {
        this.size = size;
    }

    /**
     * Set the parent element independently from the parent node.
     * @param parentElement the parent element.
     */
    @objid ("614e64e0-55b6-11e2-877f-002564c97630")
    @Override
    public void setParentElement(MObject parentElement) {
        this.parentElement = parentElement;
    }

    /**
     * Set the exact source point of the link relative to the target node location.
     * @param location the exact source point of the link.
     */
    @objid ("614e64e7-55b6-11e2-877f-002564c97630")
    public void setSourceAnchorLocation(Dimension location) {
        this.sourceAnchorLocation = location;
    }

    /**
     * Set the node on which the created node will be linked.
     * <p>
     * Set the parent element to be the represented element of the source node.
     * @param sourceNode the source node.
     */
    @objid ("614e64eb-55b6-11e2-877f-002564c97630")
    public void setSourceNode(GmNodeModel sourceNode) {
        this.sourceNode = sourceNode;
        this.parentElement = sourceNode.getRelatedElement();
        
    }

}
