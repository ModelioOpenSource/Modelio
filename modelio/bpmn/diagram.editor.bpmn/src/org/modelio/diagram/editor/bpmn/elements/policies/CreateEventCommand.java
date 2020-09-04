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

package org.modelio.diagram.editor.bpmn.elements.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Event;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specific command that will create and unmask a {@link BpmnEvent} and use the unmasked model element as referenced event.
 */
@objid ("26ccd8a6-23eb-4070-b8aa-3d8407ad722d")
public class CreateEventCommand extends Command {
    @objid ("84f29ba8-3686-44fb-ae2b-4ca9afc2f961")
    private MObject parentElement;

    @objid ("470ca99e-7550-4946-8ef5-1adb2df13098")
    private EditPart editPart;

    @objid ("bbca3851-882a-4e48-9b40-854b9feb08db")
    private ModelElement referencedEvent;

    @objid ("6ee93609-ae7d-4ade-9162-d6af10e9cd30")
    private Point dropLocation;

    /**
     * Initialize the command.
     * 
     * @param dropLocation The location of the element in the diagram
     * @param referencedEvent The element to be used as referencedEvent.
     * @param editPart The destination edit part that will own the data object.
     * @param parentElement The element that will own the data object.
     */
    @objid ("140b4810-2d3f-4f1b-8097-c30e1377a024")
    public CreateEventCommand(final Point dropLocation, final ModelElement referencedEvent, final EditPart editPart, final MObject parentElement) {
        this.referencedEvent = referencedEvent;
        this.dropLocation = dropLocation;
        this.editPart = editPart;
        this.parentElement = parentElement;
    }

    @objid ("cecc3a98-d8a5-4e9e-87db-ca0c1d00918f")
    @Override
    public void execute() {
        final GmModel gmModel = (GmModel) this.editPart.getModel();
        final IGmDiagram gmDiagram = gmModel.getDiagram();
        final IModelManager modelManager = gmDiagram.getModelManager();
        final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
        
        // Create the smart node
        final BpmnIntermediateCatchEvent newElement = modelFactory.createBpmnIntermediateCatchEvent();
        
        if (this.parentElement instanceof BpmnLane) {
            BpmnLane lane = (BpmnLane) this.parentElement;
            newElement.getLane().add((BpmnLane) this.parentElement);
            if (lane.getLaneSet().getProcess() != null) {
                this.parentElement = lane.getLaneSet().getProcess();
            } else {
                this.parentElement = lane.getLaneSet().getSubProcess();
            }
        }
        
        if (this.parentElement instanceof BpmnProcess) {
            newElement.setContainer((BpmnProcess) this.parentElement);
        } else if (this.parentElement instanceof BpmnSubProcess) {
            newElement.setSubProcess((BpmnSubProcess) this.parentElement);
        }
        
        if (newElement.getCompositionOwner() == null) {
            // The new element must be attached to its parent using the composition dependency
            // provided by the context.
            // If the context provides a null dependency, use the default dependency recommended by the metamodel
            MDependency effectiveDependency = modelManager.getMetamodel().getMExpert()
                    .getDefaultCompositionDep(this.parentElement, newElement);
        
            // Attach to parent
            if (effectiveDependency != null) {
                // ... and attach it to its parent.
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
        
        if (this.referencedEvent != null) {
            // Set default name
            newElement.setName(this.referencedEvent.getName());
        
            MClass linkMetaclass = modelManager.getMetamodel().getMClass(MethodologicalLink.MQNAME);
            IMdaExpert mdaExpert = modelManager.getMdaExpert();
            if (mdaExpert.canLink(Event.MdaTypes.STEREOTYPE_ELT, linkMetaclass, newElement.getMClass(), this.referencedEvent.getMClass())) {
                Event.setTarget(newElement, this.referencedEvent);
        
                BpmnEventDefinition eventDefinition;
                if (this.referencedEvent instanceof Signal) {
                    eventDefinition = modelFactory.createBpmnSignalEventDefinition();
                } else {
                    eventDefinition = modelFactory.createBpmnMessageEventDefinition();
                }
                eventDefinition.setName(eventDefinition.getMClass().getName());
                eventDefinition.setDefined(newElement);
            }
        
        }
        
        unmaskElement(newElement);
    }

    /**
     * Unmask the given element in the destination edit part.
     * 
     * @param el The element to unmask
     */
    @objid ("9167c9af-756c-4d92-887f-ec13d96b49e7")
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

    @objid ("817ee95c-47bb-429c-947f-4f5dec696dcf")
    @Override
    public boolean canExecute() {
        return this.parentElement != null &&
                this.parentElement.isValid() &&
                this.parentElement.getStatus().isModifiable();
    }

}
