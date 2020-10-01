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

package org.modelio.bpmn.diagram.editor.elements.bpmnmessage;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.bpmn.diagram.editor.elements.diagrams.processcollaboration.GmBpmnProcessCollaborationDiagram;
import org.modelio.diagram.elements.common.linkednode.CreateLinkedNodeCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * {@link GmNodeModel} creation command that:
 * <ul>
 * <li>creates and initialize the MObject if asked.
 * <li>creates the {@link GmNodeModel} and unmask it.
 * </ul>
 * according to the provided {@link ModelioCreationContext}.
 */
@objid ("615a99f9-55b6-11e2-877f-002564c97630")
public class CreateLinkedBpmnMessageCommand extends CreateLinkedNodeCommand {
    /**
     * Creates a node creation command.
     * 
     * @param context Details on the MObject and/or the node to create
     */
    @objid ("615a99fd-55b6-11e2-877f-002564c97630")
    public CreateLinkedBpmnMessageCommand(final ModelioCreationContext context) {
        super(context);
    }

    @objid ("615c205c-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        final GmBpmnProcessCollaborationDiagram diagram = (GmBpmnProcessCollaborationDiagram) this.sourceNode.getDiagram();
        
        BpmnMessage newElement = (BpmnMessage) this.context.getElementToUnmask();
        
        if (newElement == null) {
            IModelManager modelManager = diagram.getModelManager();
            final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
            newElement = modelFactory.createBpmnMessage();
            BpmnCollaboration collaboration = (BpmnCollaboration) diagram.getRelatedElement().getOrigin();
            newElement.setCollaboration(collaboration);
            newElement.getMessageFlow().add((BpmnMessageFlow) this.parentElement);
        
            String uniqueName = modelManager.getModelServices().getElementNamer().getUniqueName(newElement);
            newElement.setName(uniqueName);
        
            // Attach the stereotype if needed.
            if (this.context.getStereotype() != null) {
                ((ModelElement) newElement).getExtension().add(this.context.getStereotype());
            }
        
            // Set default name
            newElement.setName(modelManager.getModelServices().getElementNamer().getUniqueName(newElement));
        }
        
        // Get the new element bounds
        final Rectangle rect;
        if (this.size != null) {
            rect = new Rectangle(this.location, this.size);
        } else {
            rect = new Rectangle(this.location, new Dimension(-1, -1));
        }
        
        // Show the new element in the diagram (ie create its Gm )
        diagram.unmask(this.destNode, newElement, rect);
        
        // Show the link between the source node and the unmasked node
        // if (this.sourceNode != this.destNode) {
        // CreateConnectionRequest connCreateRequest = getConnectionCreationRequest(createdNode);
        // IGmLinkable linkTarget = (IGmLinkable) connCreateRequest.getTargetEditPart().getModel();
        //
        // GmLink gmlink = diagram.unmaskLink(newElement);
        // linkTarget.addEndingLink(gmlink); // Add the target first because the link depends on the target
        // this.sourceNode.addStartingLink(gmlink);
        //
        // IGmPath path = new GmPath();
        // path.setSourceAnchor(this.sourceAnchor);
        // path.setTargetAnchor(AnchorModelHelper.getTargetAnchorModel(connCreateRequest));
        // path.setPathData(new ArrayList<Bendpoint>());
        // gmlink.setLayoutData(path);
        // }
    }

}
