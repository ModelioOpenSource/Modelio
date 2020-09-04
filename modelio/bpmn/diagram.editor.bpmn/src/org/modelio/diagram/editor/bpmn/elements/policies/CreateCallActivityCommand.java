/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.bpmn.elements.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.editor.bpmn.plugin.DiagramEditorBpmn;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.default_.infrastructure.dependency.RelatedDiagram;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specific command that will create and unmask a {@link BpmnCallActivity} and call the unmasked model element.
 */
@objid ("4acea4d7-9e5a-4187-8ca2-5a473ce449c7")
public class CreateCallActivityCommand extends Command {
    @objid ("e9cd8aa8-97db-49ae-b23d-d487aec7acaa")
    private EditPart editPart;

    @objid ("0a27dc4d-f749-4f16-8f1c-b67ed497bcbd")
    private MObject elementToBeCalled;

    @objid ("d43539f9-193e-450b-a6c6-0ca3c857ab91")
    private MObject parentElement;

    @objid ("5b5090c6-fda9-4226-97b8-7157e86aef22")
    private Point dropLocation;

    /**
     * Initialize the command.
     * 
     * @param dropLocation The location of the element in the diagram
     * @param elementToBeCalled The element to be 'called'.
     * @param editPart The destination edit part that will own the call activity.
     * @param parentElement The element that will own the call activity.
     */
    @objid ("2307b808-2f7a-4926-9602-3397d30b46c5")
    public CreateCallActivityCommand(final Point dropLocation, final MObject elementToBeCalled, final EditPart editPart, final MObject parentElement) {
        this.elementToBeCalled = elementToBeCalled;
        this.dropLocation = dropLocation;
        this.editPart = editPart;
        this.parentElement = parentElement;
    }

    @objid ("388dc21a-bdfb-4fe0-96fd-a914519c999c")
    @Override
    public void execute() {
        final GmModel gmModel = (GmModel) this.editPart.getModel();
        final IGmDiagram gmDiagram = gmModel.getDiagram();
        final IModelManager modelManager = gmDiagram.getModelManager();
        final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
        
        // Create the smart node
        final BpmnCallActivity newElement = modelFactory.createBpmnCallActivity();
        
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
        
        // If called element is a non-process behavior, relate its diagrams
        if (this.elementToBeCalled instanceof Behavior) {
            Behavior behavior = (Behavior) this.elementToBeCalled;
            if (!(this.elementToBeCalled instanceof BpmnProcess)) {
                try {
                    for (AbstractDiagram diagram : behavior.getProduct()) {
                        modelFactory.createDependency(newElement, diagram, RelatedDiagram.MdaTypes.STEREOTYPE_ELT);
                    }
                } catch (ExtensionNotFoundException e) {
                    DiagramEditorBpmn.LOG.error(e);
                }
            }
        }
        
        unmaskElement(newElement);
    }

    /**
     * Unmask the given element in the destination edit part.
     * 
     * @param el The element to unmask
     */
    @objid ("4751a712-2962-4026-8129-a5e4438537ab")
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

    @objid ("5562e248-4deb-479c-8618-b6ea2f55049c")
    @Override
    public boolean canExecute() {
        return this.parentElement != null &&
                this.parentElement.isValid() &&
                this.parentElement.getStatus().isModifiable() &&
                (this.elementToBeCalled == null || this.elementToBeCalled instanceof ModelElement);
    }

}
