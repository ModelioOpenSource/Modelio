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
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Create a {@link ICallOperationAction} linked to the given {@link Operation} and unmask it in the diagram.
 * 
 * @author cmarin
 */
@objid ("2a0d7f0b-55b6-11e2-877f-002564c97630")
public class CreateCallOperationCommand extends Command {
    @objid ("2a0d7f0e-55b6-11e2-877f-002564c97630")
    private Operation operation;

    @objid ("2a0d7f12-55b6-11e2-877f-002564c97630")
    private MObject parentElement;

    @objid ("2a0d7f0d-55b6-11e2-877f-002564c97630")
    private EditPart editPart;

    @objid ("2a0d7f11-55b6-11e2-877f-002564c97630")
    private Point dropLocation;

    /**
     * Initialize the command.
     * @param dropLocation The location of the element in the diagram
     * @param toUnmask The operation to unmask
     * @param editPart The destination edit part that will own the call operation.
     * @param parentElement The element that will own the call operation action.
     */
    @objid ("2a0d7f15-55b6-11e2-877f-002564c97630")
    public CreateCallOperationCommand(Point dropLocation, Operation toUnmask, EditPart editPart, MObject parentElement) {
        this.operation = toUnmask;
        this.dropLocation = dropLocation;
        this.editPart = editPart;
        this.parentElement = parentElement;
    }

    @objid ("2a0d7f20-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        final GmModel gmModel = (GmModel) this.editPart.getModel();
        final IGmDiagram gmDiagram = gmModel.getDiagram();
        IStandardModelFactory modelFactory = gmDiagram.getModelManager().getModelFactory().getFactory(IStandardModelFactory.class);
        
        // Create the smart node
        final CallOperationAction el = modelFactory.createCallOperationAction();
        
        // Attach to parent
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
        
        // Attach to dropped element
        el.setName(this.operation.getName());
        el.setCalled(this.operation);
        
        for (Parameter p : this.operation.getIO()) {
            Pin pin;
            switch (p.getParameterPassing()) {
            case OUT:
                // Create an output pin
                pin = modelFactory.createOutputPin();
                initPin(p, pin);
                el.getOutput().add((OutputPin) pin);
                break;
            case INOUT:
                // Create an output pin AND an input pin
                pin = modelFactory.createOutputPin();
                initPin(p, pin);
                el.getOutput().add((OutputPin) pin);
        
                pin = modelFactory.createInputPin();
                initPin(p, pin);
                el.getInput().add((InputPin) pin);
                break;
            case IN:
                // Create an input pin
                pin = modelFactory.createInputPin();
                initPin(p, pin);
                el.getInput().add((InputPin) pin);
                break;
            default:
                break;
            }
        }
        
        final Parameter p = this.operation.getReturn();
        if (p != null) {
            // Create an output pin
            OutputPin pin = modelFactory.createOutputPin();
            initPin(p, pin);
            el.getOutput().add(pin);
        }
        
        unmaskElement(el);
    }

    /**
     * Unmask the given element in the destination edit part.
     * @param el The element to unmask
     */
    @objid ("2a0d7f23-55b6-11e2-877f-002564c97630")
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

    @objid ("2a0d7f29-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        return this.parentElement != null && this.parentElement.isValid() && this.parentElement.getStatus().isModifiable();
    }

    @objid ("2a0d7f2e-55b6-11e2-877f-002564c97630")
    protected void initPin(final Parameter p, final Pin pin) {
        pin.setMatched(p);
        pin.setName(p.getName());
        pin.setType(p.getType());
        pin.setUpperBound(p.getMultiplicityMax());
    }

}
