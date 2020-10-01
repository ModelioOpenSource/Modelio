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

package org.modelio.bpmn.diagram.editor.wizard;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Transmuter implementation for {@link BpmnActivity} elements.
 */
@objid ("f8757964-3927-40f3-81db-c6065c57da43")
public class BpmnActivityTransmuter extends AbstractElementTransmuter {
    /**
     * Public constructor required by the {@link TransformerRegistry}.
     * 
     * @param targetMetaclass target metaclass of the transmutation
     */
    @objid ("8f730ce3-950f-4c09-aaff-7850d8cc03e7")
    public BpmnActivityTransmuter(final String targetMetaclass) {
        super(targetMetaclass);
    }

    @objid ("e4afd90b-994f-4b7d-8c14-2bc3cf3a01c8")
    @Override
    protected MObject transmuteElement(MObject elementToBeTransmuted, IMModelServices modelServices) {
        MObject owner = elementToBeTransmuted.getCompositionOwner();
        MExpert mExpert = owner.getMClass().getMetamodel().getMExpert();
        
        // Create the Element...
        final IStandardModelFactory modelFactory = modelServices.getModelFactory().getFactory(IStandardModelFactory.class);
        MObject newElement = modelFactory.createElement(this.targetMetaclass);
        
        // The new element must be attached to its parent using the composition dependency
        // provided by the context.
        // If the context provides a null dependency, use the default dependency recommended by the metamodel
        MDependency effectiveDependency = mExpert.getDefaultCompositionDep(owner, newElement);
        // ... and attach it to its parent.
        
        try {
            owner.mGet(effectiveDependency).add(newElement);
        } catch (@SuppressWarnings ("unused") Exception e) {
            // The dependency indicated in the context cannot be used: try
            // to find a valid one!
            MDependency compositionDep = mExpert.getDefaultCompositionDep(owner, newElement);
            if (compositionDep != null) {
                owner.mGet(compositionDep).add(newElement);
            } else {
                newElement.delete();
                return null;
            }
        }
        
        if (newElement instanceof BpmnFlowElement && owner instanceof BpmnLane) {
            BpmnLane lane = (BpmnLane) owner;
            BpmnFlowElement flowElement = (BpmnFlowElement) newElement;
            flowElement.getLane().add(lane);
            lane.getFlowElementRef().add(flowElement);
        }
        
        // Set default name
        newElement.setName(modelServices.getElementNamer().getUniqueName(newElement));
        
        if (elementToBeTransmuted instanceof BpmnActivity && newElement instanceof BpmnActivity) {
            BpmnActivity newActivity = (BpmnActivity) newElement;
            BpmnActivity oldActivity = (BpmnActivity) elementToBeTransmuted;
            newActivity.setName(oldActivity.getName());
        
            for (BpmnLane lane : new ArrayList<>(oldActivity.getLane())) {
                newActivity.getLane().add(lane);
            }
        
            for (BpmnBoundaryEvent event : new ArrayList<>(oldActivity.getBoundaryEventRef())) {
                newActivity.getBoundaryEventRef().add(event);
                oldActivity.getBoundaryEventRef().remove(event);
            }
        
            for (BpmnDataInput subelement : new ArrayList<>(oldActivity.getInputSpecification())) {
                newActivity.getInputSpecification().add(subelement);
            }
        
            for (BpmnDataOutput subelement : new ArrayList<>(oldActivity.getOutputSpecification())) {
                newActivity.getOutputSpecification().add(subelement);
            }
        
            for (BpmnDataAssociation subelement : new ArrayList<>(oldActivity.getDataInputAssociation())) {
                newActivity.getDataInputAssociation().add(subelement);
            }
        
            for (BpmnDataAssociation subelement : new ArrayList<>(oldActivity.getDataOutputAssociation())) {
                newActivity.getDataOutputAssociation().add(subelement);
            }
        
            for (BpmnSequenceFlow subelement : new ArrayList<>(oldActivity.getOutgoing())) {
                newActivity.getOutgoing().add(subelement);
            }
        
            for (BpmnSequenceFlow subelement : new ArrayList<>(oldActivity.getIncoming())) {
                newActivity.getIncoming().add(subelement);
            }
        
            for (BpmnLane subelement : new ArrayList<>(oldActivity.getLane())) {
                newActivity.getLane().add(subelement);
            }
        
            for (Dependency subelement : new ArrayList<>(oldActivity.getImpactedDependency())) {
                newActivity.getImpactedDependency().add(subelement);
            }
        
            for (Dependency subelement : new ArrayList<>(oldActivity.getDependsOnDependency())) {
                newActivity.getDependsOnDependency().add(subelement);
            }
        
            if (oldActivity.getDefaultFlow() != null) {
                newActivity.setDefaultFlow(oldActivity.getDefaultFlow());
            }
        
            if (oldActivity.getLoopCharacteristics() != null) {
                newActivity.setLoopCharacteristics(oldActivity.getLoopCharacteristics());
            }
        
            if (oldActivity.getLoopCharacteristics() != null) {
                newActivity.setLoopCharacteristics(oldActivity.getLoopCharacteristics());
            }
        
            if (oldActivity.getOutgoingFlow().size() > 0) {
                for (BpmnMessageFlow flow : new ArrayList<>(oldActivity.getOutgoingFlow())) {
                    newActivity.getOutgoingFlow().add(flow);
                }
            }
        
            if (oldActivity.getIncomingFlow().size() > 0) {
                for (BpmnMessageFlow flow : new ArrayList<>(oldActivity.getIncomingFlow())) {
                    newActivity.getIncomingFlow().add(flow);
                }
            }
        }
        return newElement;
    }

    @objid ("42b68ae9-ac6a-40dc-947c-430754ee7051")
    @Override
    public boolean isAvailable(AbstractDiagram diagram, ISelection selection) {
        return super.isAvailable(diagram, selection) && SelectionHelper.containsOnly(selection, BpmnActivity.class);
    }

}
