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
package org.modelio.uml.activitydiagram.editor.elements.commands;

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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Create a {@link ICallBehaviorAction} linked to the given {@link IBehavior} and unmask it in the diagram.
 * 
 * @author cmarin
 */
@objid ("2a0a71c5-55b6-11e2-877f-002564c97630")
public class CreateActivityParameterNodeCommand extends Command {
    @objid ("2a0bf859-55b6-11e2-877f-002564c97630")
    private BehaviorParameter toUnmask;

    @objid ("2a0bf85d-55b6-11e2-877f-002564c97630")
    private MObject parentElement;

    @objid ("2a0a71c7-55b6-11e2-877f-002564c97630")
    private EditPart parentEditPart;

    @objid ("2a0bf85c-55b6-11e2-877f-002564c97630")
    private Point dropLocation;

    /**
     * Initialize the command.
     * @param dropLocation The location of the element in the diagram
     * @param toUnmask The parameter to unmask
     * @param editPart The destination edit part that will own the call operation.
     * @param parentElement The element that will own the call operation action.
     */
    @objid ("2a0bf860-55b6-11e2-877f-002564c97630")
    public  CreateActivityParameterNodeCommand(Point dropLocation, BehaviorParameter toUnmask, EditPart editPart, MObject parentElement) {
        this.toUnmask = toUnmask;
        this.dropLocation = dropLocation;
        this.parentEditPart = editPart;
        this.parentElement = parentElement;
        
    }

    @objid ("2a0bf86b-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        final GmModel gmModel = (GmModel) this.parentEditPart.getModel();
        final IGmDiagram gmDiagram = gmModel.getDiagram();
        IStandardModelFactory modelFactory = gmDiagram.getModelManager().getModelFactory().getFactory(IStandardModelFactory.class);
        
        // Create the node
        final ActivityParameterNode el = modelFactory.createActivityParameterNode();
        
        MExpert mExpert = this.parentElement.getMClass().getMetamodel().getMExpert();
        // Attach to its parent
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
        el.setRepresentedRealParameter(this.toUnmask);
        el.setName(this.toUnmask.getName());
        
        // Unmask the created element
        unmaskElement(el);
        
    }

    /**
     * Unmask the given element in the destination edit part.
     * @param el The element to unmask
     */
    @objid ("2a0bf86e-55b6-11e2-877f-002564c97630")
    private void unmaskElement(MObject el) {
        final ModelioCreationContext gmCreationContext = new ModelioCreationContext(el);
        
        final CreateRequest creationRequest = new CreateRequest();
        creationRequest.setLocation(this.dropLocation);
        creationRequest.setSize(new Dimension(-1, -1));
        creationRequest.setFactory(gmCreationContext);
        
        final Command cmd = this.parentEditPart.getTargetEditPart(creationRequest).getCommand(creationRequest);
        if (cmd != null && cmd.canExecute()) {
            cmd.execute();
        }
        
    }

    @objid ("2a0bf874-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        final GmModel gmModel = (GmModel) this.parentEditPart.getModel();
        if (!MTools.getAuthTool().canModify(gmModel.getDiagram().getRelatedElement())) {
            return false;
        }
        return this.parentElement != null && this.parentElement.isValid() && this.parentElement.getStatus().isModifiable();
    }

}
