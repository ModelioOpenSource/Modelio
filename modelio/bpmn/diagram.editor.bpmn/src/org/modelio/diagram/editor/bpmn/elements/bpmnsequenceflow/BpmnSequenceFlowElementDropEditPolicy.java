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

package org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflow;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataObject;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.objects.BpmnSequenceFlowDataAssociation;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.vcore.model.api.IModelFactoryService;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specialization of the default drop request handling policy to add some smart interactions for {@link BpmnSequenceFlow}.
 * <p>
 * Supports the drag & drop of a {@link BpmnDataObject} to attach it to the {@link BpmnSequenceFlow}.
 * </p>
 */
@objid ("26924583-1555-47c7-bcf1-5030ecd823c6")
public class BpmnSequenceFlowElementDropEditPolicy extends DefaultElementDropEditPolicy {
    /**
     * Unmasking of a {@link BpmnDataObject} on a {@link BpmnSequenceFlow} provokes a smart interaction attach it to the {@link BpmnSequenceFlow}.
     */
    @objid ("5531c6d1-418d-436f-9c1c-99c4e55c9abd")
    @Override
    protected EditPart getDropTargetEditPart(final ModelElementDropRequest request) {
        if (request.isSmart() && request.getDroppedElements().length > 0) {
            for (MObject droppedElement : request.getDroppedElements()) {
                if (!(droppedElement instanceof BpmnDataObject)) {
                    return super.getDropTargetEditPart(request);
                }
            }
            return getHost();
        }
        return super.getDropTargetEditPart(request);
    }

    @objid ("02051139-a9a8-4c55-acf8-acd8fa986cc8")
    @Override
    protected Command getSmartDropCommand(final ModelElementDropRequest request) {
        if (!request.isSmart()) {
            return null;
        }
        
        GmModel model = (GmModel) getHost().getModel();
        BpmnSequenceFlow element = (BpmnSequenceFlow) model.getRelatedElement();
        if (request.getDroppedElements().length > 0) {
            List<BpmnDataObject> dataObjects = new ArrayList<>();
            for (MObject droppedElement : request.getDroppedElements()) {
                if (droppedElement instanceof BpmnDataObject) {
                    dataObjects.add((BpmnDataObject) droppedElement);
                }
            }
            if (!dataObjects.isEmpty()) {
                return new BpmnDataObjectSmartAttachCommand(element, dataObjects, model.getDiagram().getModelManager().getModelFactory());
            }
        }
        return super.getSmartDropCommand(request);
    }

    /**
     * Command produced by the drop element edit policy that can attach a {@link BpmnDataObject} to a {@link BpmnSequenceFlow}.
     */
    @objid ("5881e78c-4706-456f-a917-de2f0c33126e")
    private static class BpmnDataObjectSmartAttachCommand extends Command {
        @objid ("8875b68a-7b6a-459f-a6f9-3b48420e1c10")
        private BpmnSequenceFlow elementToType;

        @objid ("65b3f3f1-cbc5-462a-8795-016d8b7aae8a")
        private List<BpmnDataObject> dataObjects;

        @objid ("f3c3c3da-a374-4173-a70f-677fd8edbf94")
        private IModelFactoryService modelFactory;

        /**
         * Constructor to attach a {@link BpmnDataObject} to a {@link BpmnSequenceFlow}.
         * 
         * @param elementToType the element to type.
         * @param dataObjects the objects to be attached.
         * @param modelFactory the model object factory registry.
         */
        @objid ("062610e1-6f62-4ddb-8fe4-673fc5f1206b")
        public BpmnDataObjectSmartAttachCommand(final BpmnSequenceFlow elementToType, List<BpmnDataObject> dataObjects, IModelFactoryService modelFactory) {
            this.elementToType = elementToType;
            this.modelFactory = modelFactory;
            this.dataObjects = new ArrayList<>();
            
            // Ignore already referenced data objects
            final List<BpmnDataObject> referencedDataObjects = new ArrayList<>();
            for (final BpmnSequenceFlowDataAssociation sfda : this.elementToType.getConnector()) {
                for (final BpmnDataAssociation dataAssocation : sfda.getDataAssociation()) {
                    final BpmnItemAwareElement ita = dataAssocation.getTargetRef();
                    if (ita instanceof BpmnDataObject) {
                        referencedDataObjects.add((BpmnDataObject) ita);
                    }
                }
            }
            for (final BpmnDataObject dataObject : dataObjects) {
                if (!referencedDataObjects.contains(dataObject)) {
                    this.dataObjects.add(dataObject);
                }
            }
        }

        @objid ("a996920e-dc1a-44f1-8757-569a4e772d79")
        @Override
        public boolean canExecute() {
            final BpmnFlowNode source = this.elementToType.getSourceRef();
            final BpmnFlowNode target = this.elementToType.getTargetRef();
            
            boolean isValidSource = source instanceof BpmnActivity || source instanceof BpmnCatchEvent;
            boolean isValidTarget = target instanceof BpmnActivity || target instanceof BpmnThrowEvent;
            boolean isEditable = MTools.getAuthTool().canModify(this.elementToType);
            boolean hasNewDataObjects = !this.dataObjects.isEmpty();
            return isEditable && hasNewDataObjects && isValidSource && isValidTarget;
        }

        @objid ("5299ee1f-7a56-4f3c-89e7-6617777ddf2e")
        @Override
        public void execute() {
            for (final BpmnDataObject dataObject : this.dataObjects) {
                addDataObject(dataObject);
            }
        }

        @objid ("11765974-8bbc-4793-9b80-c01b4f8d3021")
        @Override
        public void undo() {
            // Do nothing
        }

        @objid ("c034473c-e14e-422d-b859-af3684e979ef")
        @Override
        public void redo() {
            // Do nothing
        }

        /**
         * Attach a {@link BpmnDataObject} to a {@link BpmnSequenceFlow}.
         * 
         * @param dataObject a data object that is not already linked to the edited flow.
         */
        @objid ("1cd6747e-e3e5-49d6-8eb0-bb5bd3aeb4d3")
        private void addDataObject(final BpmnDataObject dataObject) {
            IStandardModelFactory bpmnModelFactory = this.modelFactory.getFactory(IStandardModelFactory.class);
            
            final BpmnFlowNode source = this.elementToType.getSourceRef();
            final BpmnFlowNode target = this.elementToType.getTargetRef();
            
            final BpmnDataAssociation sourceAssociation = bpmnModelFactory.createBpmnDataAssociation();
            if (source instanceof BpmnActivity) {
                sourceAssociation.setStartingActivity((BpmnActivity) source);
            } else if (source instanceof BpmnCatchEvent) {
                sourceAssociation.setEndingEvent((BpmnCatchEvent) source);
            } else if (source instanceof BpmnThrowEvent) {
                // this case shouldn't be possible here, but the metamodel has mixed-up role names...
                sourceAssociation.setStartingEvent((BpmnThrowEvent) source);
            }
            sourceAssociation.setTargetRef(dataObject);
            
            final BpmnDataAssociation targetAssociation = bpmnModelFactory.createBpmnDataAssociation();
            if (target instanceof BpmnActivity) {
                targetAssociation.setEndingActivity((BpmnActivity) target);
            } else if (target instanceof BpmnThrowEvent) {
                targetAssociation.setStartingEvent((BpmnThrowEvent) target);
            } else if (target instanceof BpmnCatchEvent) {
                // this case shouldn't be possible here, but the metamodel has mixed-up role names...
                targetAssociation.setEndingEvent((BpmnCatchEvent) target);
            }
            targetAssociation.getSourceRef().add(dataObject);
            
            final BpmnSequenceFlowDataAssociation sequenceFlowAssociation = bpmnModelFactory.createBpmnSequenceFlowDataAssociation();
            sequenceFlowAssociation.setConnected(this.elementToType);
            sequenceFlowAssociation.getDataAssociation().add(sourceAssociation);
            sequenceFlowAssociation.getDataAssociation().add(targetAssociation);
        }

    }

}
