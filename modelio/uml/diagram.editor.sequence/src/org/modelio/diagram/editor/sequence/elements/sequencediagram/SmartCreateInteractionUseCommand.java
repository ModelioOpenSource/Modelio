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

package org.modelio.diagram.editor.sequence.elements.sequencediagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command used for smart unmask interactions: <br>
 * Creates a {@link BindableInstance} representing the dropped element.
 * 
 * @author cma
 */
@objid ("d9822efd-55b6-11e2-877f-002564c97630")
public class SmartCreateInteractionUseCommand extends Command {
    @objid ("d9822f07-55b6-11e2-877f-002564c97630")
    private static final int DEFAULT_SIZE = 50;

    @objid ("ff67e591-2101-45ad-99e4-252a92738a20")
    private Point location;

    @objid ("ecb78c4d-571a-4892-82af-7d2966092a78")
    private Interaction parentElement;

    @objid ("4364c762-6d5b-41f2-9947-449cf6712f45")
    private Interaction toUnmask;

    @objid ("ee0c9548-660d-4489-bb2e-d3f43e907ce3")
    private EditPart parentEditPart;

    /**
     * @param dropLocation the location where the ObjectNode is to be unmasked.
     * @param toUnmask the element that the ObjectNode will represent.
     * @param parentEditPart the edit part handling the unmasking
     * @param parentElement the element that will own the new ObjectNode
     */
    @objid ("d9822f09-55b6-11e2-877f-002564c97630")
    public SmartCreateInteractionUseCommand(final Point dropLocation, final Interaction toUnmask, final EditPart parentEditPart, final Interaction parentElement) {
        this.location = dropLocation;
        this.toUnmask = toUnmask;
        this.parentEditPart = parentEditPart;
        this.parentElement = parentElement;
    }

    @objid ("d9822f18-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        final GmModel gmModel = (GmModel) this.parentEditPart.getModel();
        final IGmDiagram gmDiagram = gmModel.getDiagram();
        
        if (!MTools.getAuthTool().canModify(gmDiagram.getRelatedElement())) {
            return false;
        }
        return (this.parentElement != null && this.parentElement.isValid() && this.parentElement.getStatus()
                        .isModifiable());
    }

    @objid ("d9822f1d-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        GmModel gmModel = (GmModel) this.parentEditPart.getModel();
        IGmDiagram gmDiagram = gmModel.getDiagram();
        IModelManager manager = gmDiagram.getModelManager();
        IStandardModelFactory factory = manager.getModelFactory().getFactory(IStandardModelFactory.class);
        
        // Create the InteractionUse
        InteractionUse interactionUse = factory.createInteractionUse(this.toUnmask);
        interactionUse.setLineNumber(this.location.y);
        interactionUse.setEndLineNumber(this.location.y + SmartCreateInteractionUseCommand.DEFAULT_SIZE);
        this.parentElement.getFragment().add(interactionUse);
        
        // Unmask it
        unmaskElement(interactionUse);
    }

    @objid ("d9822f20-55b6-11e2-877f-002564c97630")
    private void unmaskElement(final MObject el) {
        final ModelioCreationContext gmCreationContext = new ModelioCreationContext(el);
        
        final CreateRequest creationRequest = new CreateRequest();
        creationRequest.setLocation(this.location);
        creationRequest.setSize(new Dimension(-1, -1));
        creationRequest.setFactory(gmCreationContext);
        
        final Command cmd = this.parentEditPart.getTargetEditPart(creationRequest)
                .getCommand(creationRequest);
        if (cmd != null && cmd.canExecute()) {
            cmd.execute();
        }
    }

}
