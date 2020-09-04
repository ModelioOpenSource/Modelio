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

package org.modelio.diagram.editor.activity.elements.commands;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Create a {@link ICallBehaviorAction} linked to the given {@link IBehavior} and unmask it in the diagram.
 * 
 * @author cmarin
 */
@objid ("2a0bf886-55b6-11e2-877f-002564c97630")
public class CreateCallBehaviorCommand extends Command {
    @objid ("2a0bf889-55b6-11e2-877f-002564c97630")
    private Behavior operation;

    @objid ("2a0bf88d-55b6-11e2-877f-002564c97630")
    private MObject parentElement;

    @objid ("2a0bf888-55b6-11e2-877f-002564c97630")
    private EditPart editPart;

    @objid ("2a0bf88c-55b6-11e2-877f-002564c97630")
    private Point dropLocation;

    /**
     * Initialize the command.
     * 
     * @param dropLocation The location of the element in the diagram
     * @param toUnmask The operation to unmask
     * @param editPart The destination edit part that will own the call operation.
     * @param parentElement The element that will own the call operation action.
     */
    @objid ("2a0bf890-55b6-11e2-877f-002564c97630")
    public CreateCallBehaviorCommand(Point dropLocation, Behavior toUnmask, EditPart editPart, MObject parentElement) {
        this.operation = toUnmask;
        this.dropLocation = dropLocation;
        this.editPart = editPart;
        this.parentElement = parentElement;
    }

    @objid ("2a0bf89b-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        final GmModel gmModel = (GmModel) this.editPart.getModel();
        final IGmDiagram gmDiagram = gmModel.getDiagram();
        IStandardModelFactory modelFactory = gmDiagram.getModelManager().getModelFactory().getFactory(IStandardModelFactory.class);
        
        // Create the node
        final CallBehaviorAction el = modelFactory.createCallBehaviorAction();
        
        // Attach to its parent
        MExpert mExpert = this.parentElement.getMClass().getMetamodel().getMExpert();
        final MDependency effectiveDependency = mExpert.getDefaultCompositionDep(this.parentElement, el);
        
        if (effectiveDependency == null) {
            StringBuilder msg = new StringBuilder();
            msg.append("Cannot find a composition dependency to attach ");
            msg.append(el.toString());
            msg.append(" to ");
            msg.append(this.parentElement.toString());
            throw new IllegalStateException(msg.toString());
        }
        
        this.parentElement.mGet(effectiveDependency).add(el);
        
        // Attach to the dropped element
        el.setName(this.operation.getName());
        el.setCalled(this.operation);
        
        // Unmask the created node
        unmaskElement(el);
    }

    /**
     * Unmask the given element in the destination edit part.
     * 
     * @param el The element to unmask
     */
    @objid ("2a0bf89e-55b6-11e2-877f-002564c97630")
    private void unmaskElement(MObject el) {
        final ModelioCreationContext gmCreationContext = new ModelioCreationContext(el);
        
        final CreateRequest creationRequest = new CreateRequest();
        creationRequest.setLocation(this.dropLocation);
        creationRequest.setSize(new Dimension(-1, -1));
        creationRequest.setFactory(gmCreationContext);
        
        final Command cmd = this.editPart.getTargetEditPart(creationRequest).getCommand(creationRequest);
        if (cmd != null && cmd.canExecute()) {
            cmd.execute();
        }
    }

    @objid ("2a0d7efc-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        return this.parentElement != null && this.parentElement.isValid() && this.parentElement.getStatus().isModifiable();
    }

}
