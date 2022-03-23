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
package org.modelio.bpmn.diagram.editor.elements.common.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.metamodel.bpmn.activities.BpmnServiceTask;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specific command that will create and unmask a {@link BpmnServiceTask} and call the unmasked model element.
 */
@objid ("bfc8546a-eed4-487a-b13d-0b37a46d8c1c")
public class CreateServiceTaskCommand extends Command {
    @objid ("fb66ebc5-f985-42fc-b367-a8f2a5fc0aa1")
    private EditPart editPart;

    @objid ("022c149c-4c62-4a14-82de-fcbe6b08c6de")
    private MObject elementToBeCalled;

    @objid ("cdd93e90-303b-47a3-9d86-02214e2c1b3b")
    private MObject parentElement;

    @objid ("7a026dab-8d79-46c1-8b69-1e397b5965b6")
    private Point dropLocation;

    /**
     * Initialize the command.
     * @param dropLocation The location of the element in the diagram
     * @param elementToBeCalled The element to be 'called'.
     * @param editPart The destination edit part that will own the call activity.
     * @param parentElement The element that will own the call activity.
     */
    @objid ("edf57152-ae92-4e8a-9216-bfa965a94746")
    public  CreateServiceTaskCommand(final Point dropLocation, final MObject elementToBeCalled, final EditPart editPart, final MObject parentElement) {
        this.elementToBeCalled = elementToBeCalled;
        this.dropLocation = dropLocation;
        this.editPart = editPart;
        this.parentElement = parentElement;
        
    }

    @objid ("e564b20b-062c-4dce-a4c0-a4a2d81f04e4")
    @Override
    public void execute() {
        final GmModel gmModel = (GmModel) this.editPart.getModel();
        final IGmDiagram gmDiagram = gmModel.getDiagram();
        final IModelManager modelManager = gmDiagram.getModelManager();
        final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
        
        // Create the smart node
        final BpmnServiceTask newElement = modelFactory.createBpmnServiceTask();
        
        // Set default name
        newElement.setName(modelManager.getModelServices().getElementNamer().getUniqueName(newElement));
        
        // In a BpmnLane, replace the parent with a BpmnProcess or BpmnSubProcess
        if (this.parentElement instanceof BpmnLane) {
            BpmnLane lane = (BpmnLane) this.parentElement;
            newElement.getLane().add((BpmnLane) this.parentElement);
            if (lane.getLaneSet().getProcess() != null) {
                this.parentElement = lane.getLaneSet().getProcess();
            } else {
                this.parentElement = lane.getLaneSet().getSubProcess();
            }
        }
        
        // Attach parent
        if (this.parentElement instanceof BpmnProcess) {
            newElement.setContainer((BpmnProcess) this.parentElement);
        } else if (this.parentElement instanceof BpmnSubProcess) {
            newElement.setSubProcess((BpmnSubProcess) this.parentElement);
        }
        
        if (newElement.getCompositionOwner() == null) {
            // The new element must be attached to its parent using the composition dependency
            // provided by the context.
            // If the context provides a null dependency, use the default dependency recommended by the metamodel
            MDependency effectiveDependency = modelManager.getMetamodel().getMExpert().getDefaultCompositionDep(this.parentElement, newElement);
            // Attach to parent
            if (effectiveDependency != null) {
                this.parentElement.mGet(effectiveDependency).add(newElement);
            } else {
                StringBuilder msg = new StringBuilder();
                msg.append("Cannot find a composition dependency to attach ");
                msg.append(newElement.toString());
                msg.append(" to ");
                msg.append(this.parentElement.toString());
                throw new IllegalStateException(msg.toString());
            }
        }
        
        // Set called
        Called.setTarget(newElement, (ModelElement) this.elementToBeCalled);
        
        unmaskElement(newElement);
        
    }

    /**
     * Unmask the given element in the destination edit part.
     * @param el The element to unmask
     */
    @objid ("e8cc09e2-e07a-4607-8545-e6783d469710")
    private void unmaskElement(final MObject el) {
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

    @objid ("21c7ee80-9400-4c47-97c2-e4418b33b852")
    @Override
    public boolean canExecute() {
        return this.parentElement != null &&
                this.parentElement.isValid() &&
                this.parentElement.getStatus().isModifiable() &&
                (this.elementToBeCalled == null || this.elementToBeCalled instanceof ModelElement);
        
    }

}
