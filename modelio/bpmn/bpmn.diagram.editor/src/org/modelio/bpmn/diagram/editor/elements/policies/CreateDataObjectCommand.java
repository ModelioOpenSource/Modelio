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

package org.modelio.bpmn.diagram.editor.elements.policies;

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
import org.modelio.metamodel.bpmn.objects.BpmnDataObject;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.State;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specific command that will create and unmask a {@link BpmnDataObject} and use the unmasked model element as type.
 */
@objid ("38028854-a32e-46e1-a8a9-9412405936f4")
public class CreateDataObjectCommand extends Command {
    @objid ("4bfc2c83-ad04-4267-99a9-1f23542c1cc7")
    private MObject parentElement;

    @objid ("e051b224-30d2-44d8-a08e-8c6717da10a7")
    private EditPart editPart;

    @objid ("f8bdecf5-9a35-4f3c-8a19-2f72d20e6dc9")
    private ModelElement type;

    @objid ("6eed5ae4-48f6-4287-b5e0-41929d5e700d")
    private Point dropLocation;

    /**
     * Initialize the command.
     * 
     * @param dropLocation The location of the element in the diagram
     * @param type The element to be used as type.
     * @param editPart The destination edit part that will own the data object.
     * @param parentElement The element that will own the data object.
     */
    @objid ("a3d35a77-7c3a-4ae5-bc54-b11710738cac")
    public CreateDataObjectCommand(final Point dropLocation, final ModelElement type, final EditPart editPart, final MObject parentElement) {
        this.type = type;
        this.dropLocation = dropLocation;
        this.editPart = editPart;
        this.parentElement = parentElement;
    }

    @objid ("1e067f9a-cfd2-42bb-8244-5c88b80fe223")
    @Override
    public void execute() {
        final GmModel gmModel = (GmModel) this.editPart.getModel();
        final IGmDiagram gmDiagram = gmModel.getDiagram();
        final IModelManager modelManager = gmDiagram.getModelManager();
        final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
        
        // Create the smart node
        final BpmnDataObject newElement = modelFactory.createBpmnDataObject();
        
        // Set default name
        newElement.setName(modelManager.getModelServices().getElementNamer().getUniqueName(newElement));
        
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
        
        if (this.type != null) {
            MClass linkMetaclass = modelManager.getMetamodel().getMClass(MethodologicalLink.MQNAME);
            IMdaExpert mdaExpert = modelManager.getMdaExpert();
            if (mdaExpert.canLink(Represents.MdaTypes.STEREOTYPE_ELT, linkMetaclass, newElement.getMClass(), this.type.getMClass())) {
                Represents.setTarget(newElement, this.type);
            } else if (mdaExpert.canLink(State.MdaTypes.STEREOTYPE_ELT, linkMetaclass, newElement.getMClass(), this.type.getMClass())) {
                State.setTarget(newElement, this.type);
            }
        }
        
        unmaskElement(newElement);
    }

    /**
     * Unmask the given element in the destination edit part.
     * 
     * @param el The element to unmask
     */
    @objid ("b5307f74-e28b-4207-a709-59c94af7ea3c")
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

    @objid ("cbb9d825-1ecd-4b11-9fb0-af0acf5693d3")
    @Override
    public boolean canExecute() {
        return this.parentElement != null &&
                this.parentElement.isValid() &&
                this.parentElement.getStatus().isModifiable();
    }

}
