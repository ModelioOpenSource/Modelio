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

package org.modelio.diagram.editor.bpmn.wizard;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Transmuter implementation for {@link BpmnEvent} elements.
 */
@objid ("d14a6efa-6b4a-4fa6-836c-ca117099647f")
public class BpmnEventTransmuter extends AbstractElementTransmuter {
    /**
     * Public constructor required by the {@link TransformerRegistry}.
     * 
     * @param targetMetaclass target metaclass of the transmutation
     */
    @objid ("e578b6ac-0406-40c1-a82f-e9f6133e29b9")
    public BpmnEventTransmuter(final String targetMetaclass) {
        super(targetMetaclass);
    }

    @objid ("7e52afed-9641-447d-84b9-eeae7a7687cd")
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
        
        if (elementToBeTransmuted instanceof BpmnEvent && newElement instanceof BpmnEvent) {
            BpmnEvent newEvent = (BpmnEvent) newElement;
            BpmnEvent oldEvent = (BpmnEvent) elementToBeTransmuted;
            newEvent.setName(oldEvent.getName());
        
            for (BpmnLane lane : oldEvent.getLane()) {
                newEvent.getLane().add(lane);
            }
        
            if (!(newEvent instanceof BpmnEndEvent)) {
                for (BpmnSequenceFlow subelement : new ArrayList<>(oldEvent.getOutgoing())) {
                    newEvent.getOutgoing().add(subelement);
                }
            }
        
            if (!(newEvent instanceof BpmnStartEvent)) {
                for (BpmnSequenceFlow subelement : new ArrayList<>(oldEvent.getIncoming())) {
                    newEvent.getIncoming().add(subelement);
                }
            }
        
            for (BpmnLane subelement : new ArrayList<>(oldEvent.getLane())) {
                newEvent.getLane().add(subelement);
            }
        
            for (Dependency subelement : new ArrayList<>(oldEvent.getImpactedDependency())) {
                newEvent.getImpactedDependency().add(subelement);
            }
        
            for (Dependency subelement : new ArrayList<>(oldEvent.getDependsOnDependency())) {
                newEvent.getDependsOnDependency().add(subelement);
            }
        
            for (BpmnEventDefinition definition : new ArrayList<>(oldEvent.getEventDefinitions())) {
                newEvent.getEventDefinitions().add(definition);
            }
        
            if (oldEvent.getOutgoingFlow().size() > 0) {
                for (BpmnMessageFlow flow : new ArrayList<>(oldEvent.getOutgoingFlow())) {
                    newEvent.getOutgoingFlow().add(flow);
                }
            }
        
            if (oldEvent.getIncomingFlow().size() > 0) {
                for (BpmnMessageFlow flow : new ArrayList<>(oldEvent.getIncomingFlow())) {
                    newEvent.getIncomingFlow().add(flow);
                }
            }
        }
        return newElement;
    }

    @objid ("8266a91a-bd35-42ac-90a4-ab6164c6b33b")
    @Override
    public boolean isAvailable(AbstractDiagram diagram, ISelection selection) {
        return super.isAvailable(diagram, selection) && SelectionHelper.containsOnly(selection, BpmnEvent.class);
    }

}
