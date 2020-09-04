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

package org.modelio.diagram.editor.bpmn.elements.bpmndataobject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflow.GmBpmnSequenceFlow;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataObject;
import org.modelio.metamodel.bpmn.objects.BpmnSequenceFlowDataAssociation;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specific command to handle the creation of {@link BpmnDataObject}.
 */
@objid ("60ae2d33-55b6-11e2-877f-002564c97630")
public class CreateBpmnDataObjectCommand extends Command {
    @objid ("60ae2d39-55b6-11e2-877f-002564c97630")
    private BpmnSequenceFlow parentElement;

    @objid ("7148d040-55c1-11e2-9337-002564c97630")
    private ModelioCreationContext context;

    @objid ("c5405ece-59a6-11e2-ae45-002564c97630")
    private GmBpmnSequenceFlow parentNode;

    /**
     * Creates a node creation command.
     * 
     * @param sequenceFlow the element that lead to this command.
     * @param gm The parent editPart
     * @param context Details on the MObject and/or the node to create
     */
    @objid ("60ae2d3c-55b6-11e2-877f-002564c97630")
    public CreateBpmnDataObjectCommand(BpmnSequenceFlow sequenceFlow, GmBpmnSequenceFlow gm, ModelioCreationContext context) {
        this.parentNode = gm;
        this.parentElement = sequenceFlow;
        this.context = context;
    }

    @objid ("60ae2d46-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        final IGmDiagram diagram = this.parentNode.getDiagram();
        
        // Create the Element...
        IModelManager modelManager = diagram.getModelManager();
        final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
        BpmnDataObject newElement = modelFactory.createBpmnDataObject();
        
        MObject owner = getOwnerProcess(this.parentElement.getCompositionOwner());
        if (owner instanceof BpmnProcess) {
            newElement.setContainer((BpmnProcess) owner);
        } else if (owner instanceof BpmnSubProcess) {
            newElement.setSubProcess((BpmnSubProcess) owner);
        }
        
        // Set default name
        newElement.setName(modelManager.getModelServices().getElementNamer().getUniqueName(newElement));
        
        BpmnFlowNode source = this.parentElement.getSourceRef();
        BpmnFlowNode target = this.parentElement.getTargetRef();
        
        // Set source data assoc
        final BpmnDataAssociation sourceAssociation = modelFactory.createBpmnDataAssociation();
        if (source instanceof BpmnActivity) {
            sourceAssociation.setStartingActivity((BpmnActivity) source);
        } else if (source instanceof BpmnCatchEvent) {
            sourceAssociation.setEndingEvent((BpmnCatchEvent) source);
        } else if (source instanceof BpmnThrowEvent) {
            // this case shouldn't be possible here, but the metamodel has mixed-up role names...
            sourceAssociation.setStartingEvent((BpmnThrowEvent) source);
        }
        sourceAssociation.setTargetRef(newElement);
        
        // Set default names
        sourceAssociation.setName(modelManager.getModelServices().getElementNamer().getUniqueName(sourceAssociation));
        
        // Set target
        final BpmnDataAssociation targetAssociation = modelFactory.createBpmnDataAssociation();
        if (target instanceof BpmnActivity) {
            targetAssociation.setEndingActivity((BpmnActivity) target);
        } else if (target instanceof BpmnThrowEvent) {
            targetAssociation.setStartingEvent((BpmnThrowEvent) target);
        } else if (target instanceof BpmnCatchEvent) {
            // this case shouldn't be possible here, but the metamodel has mixed-up role names...
            targetAssociation.setEndingEvent((BpmnCatchEvent) target);
        }
        targetAssociation.getSourceRef().add(newElement);
        
        // Set default names
        targetAssociation.setName(modelManager.getModelServices().getElementNamer().getUniqueName(targetAssociation));
        
        BpmnSequenceFlowDataAssociation sequenceFlowAssociation = modelFactory.createBpmnSequenceFlowDataAssociation();
        sequenceFlowAssociation.setConnected(this.parentElement);
        sequenceFlowAssociation.getDataAssociation().add(sourceAssociation);
        sequenceFlowAssociation.getDataAssociation().add(targetAssociation);
        
        // Set default name
        sequenceFlowAssociation.setName(modelManager.getModelServices().getElementNamer().getUniqueName(sequenceFlowAssociation));
        
        // Attach the stereotype if needed.
        if (this.context.getStereotype() != null) {
            ((ModelElement) newElement).getExtension().add(this.context.getStereotype());
        }
    }

    @objid ("60ae2d49-55b6-11e2-877f-002564c97630")
    private MObject getOwnerProcess(final MObject element) {
        if (element instanceof BpmnProcess || element instanceof BpmnSubProcess) {
            return element;
        }
        return getOwnerProcess(element.getCompositionOwner());
    }

    @objid ("690e0133-af28-4325-b4f7-39d1f888d453")
    @Override
    public boolean canExecute() {
        BpmnFlowNode source = this.parentElement.getSourceRef();
        BpmnFlowNode target = this.parentElement.getTargetRef();
        
        boolean isValidSource = source instanceof BpmnActivity || source instanceof BpmnCatchEvent;
        boolean isValidTarget = target instanceof BpmnActivity || target instanceof BpmnThrowEvent;
        boolean isEditable = MTools.getAuthTool().canModify(this.parentElement);
        return isEditable && isValidSource && isValidTarget;
    }

}
