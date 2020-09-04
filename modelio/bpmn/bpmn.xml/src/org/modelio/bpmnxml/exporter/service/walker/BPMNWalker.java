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

package org.modelio.bpmnxml.exporter.service.walker;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.bpmnxml.exporter.service.processor.IBPMNProcessor;
import org.modelio.bpmnxml.model.TDefinitions;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

@objid ("1d60f34c-969b-4db5-9def-fac1f335b32e")
public class BPMNWalker {
    @objid ("52066428-40ac-41bb-8fd9-7080fa475e4f")
    private IBPMNProcessor processor;

    @objid ("232404e6-92c2-4e43-8b00-5fc882736eb1")
    private ModelElement modelioRoot;

    @objid ("17f83ca8-88ba-4ea1-b17d-76e62e238eed")
    private IDiagramService diagramService;

    @objid ("e18e1769-d56e-4297-b986-1ebdf989e586")
    private TDefinitions jaxRoot;

    @objid ("0d80f85f-ae2f-460a-ae3d-0d4d7e73d45b")
    public BPMNWalker(ModelElement modelioRoot, IBPMNProcessor processor, IDiagramService diagramService) {
        this.processor = processor;
        this.modelioRoot = modelioRoot;
        this.diagramService = diagramService;
    }

    @objid ("e8a10030-ea78-4e24-8715-9c8d2531e4b6")
    public void walk(TDefinitions context, IProgressMonitor progress) {
        this.jaxRoot = context;
        
        if (this.modelioRoot instanceof BpmnProcess) {
            walkBpmnProcess(context, (BpmnProcess) this.modelioRoot);
        } else if (this.modelioRoot instanceof BpmnCollaboration) {
            walkCollaboration(context, (BpmnCollaboration) this.modelioRoot);
        }
    }

    @objid ("7d2d9d5d-0d8a-419f-9c62-19d5f96d68bb")
    private void walkCollaboration(Object context, BpmnCollaboration modelioElement) {
        Object result = this.processor.process(context, modelioElement);
        
        for (BpmnParticipant modParticipant : modelioElement.getParticipants()) {
            this.processor.process(result, modParticipant);
            BpmnProcess process = modParticipant.getProcess();
            if (process != null) {
                walkBpmnProcess(context, process);
            }
        }
        
        for (BpmnMessageFlow modFlow : modelioElement.getMessageFlow()) {
            this.processor.process(result, modFlow);
        }
        
        for (BpmnMessage modMessage : modelioElement.getMessages()) {
            this.processor.process(context, modMessage);
        }
        
        for (AbstractDiagram diagram : modelioElement.getProduct()) {
            walkBpmnDiagram(result, diagram);
        }
    }

    @objid ("42b1e7c6-59d6-4350-ac54-e93275902e66")
    private void walkBpmnProcess(Object context, BpmnProcess modelioElement) {
        Object result = this.processor.process(context, modelioElement);
        
        BpmnLaneSet laneset = modelioElement.getLaneSet();
        if (laneset != null) {
            walkLaneSet(result, laneset);
        }
        
        for (BpmnFlowElement flowElement : modelioElement.getFlowElement()) {
            walkFlowElement(result, flowElement);
        }
        
        for (AbstractDiagram diagram : modelioElement.getProduct()) {
            walkBpmnDiagram(result, diagram);
        }
    }

    @objid ("1a769b97-081a-479d-b73c-da07d4b29c8c")
    private void walkLaneSet(Object context, BpmnLaneSet modelioElement) {
        Object rlanset = this.processor.process(context, modelioElement);
        
        for (BpmnLane lane : modelioElement.getLane()) {
            Object rlane = this.processor.process(rlanset, lane);
        
            BpmnLaneSet childLaneSet = lane.getChildLaneSet();
            if (childLaneSet != null) {
                walkLaneSet(rlane, childLaneSet);
            }
        }
    }

    @objid ("c0da4373-91a6-462a-a4b0-71315dfe2751")
    private void walkFlowElement(Object context, BpmnFlowElement flowElement) {
        if (flowElement instanceof BpmnFlowNode) {
            walkFlowNode(context, (BpmnFlowNode) flowElement);
        } else if (flowElement instanceof BpmnSequenceFlow) {
            walkSequenceFlow(context, (BpmnSequenceFlow) flowElement);
        } else if (flowElement instanceof BpmnItemAwareElement) {
            walkItem(context, (BpmnItemAwareElement) flowElement);
        }
    }

    @objid ("04e133c4-674a-4d3e-9fcc-2607ed30a547")
    private void walkItem(Object context, BpmnItemAwareElement flowElement) {
        this.processor.process(context, flowElement);
    }

    @objid ("f52fb87a-d3d5-4115-9b56-c40f6a06bbe2")
    private void walkBpmnDiagram(Object context, AbstractDiagram diagram) {
        this.processor.process(this.jaxRoot, diagram);
    }

    @objid ("e7e29bfb-2c90-47a5-8615-fee4482a4731")
    private void walkSequenceFlow(Object context, BpmnSequenceFlow modelioElement) {
        this.processor.process(context, modelioElement);
    }

    @objid ("f0e18201-4ee8-401a-9f91-5bc963f1f25e")
    private void walkFlowNode(Object context, BpmnFlowNode modelioElement) {
        Object result = this.processor.process(context, modelioElement);
        
        if (modelioElement instanceof BpmnActivity) {
            BpmnActivity activity = (BpmnActivity) modelioElement;
            if (activity.getLoopCharacteristics() != null) {
                this.processor.process(result, activity.getLoopCharacteristics());
            }
        
            if (modelioElement instanceof BpmnSubProcess) {
                walkSubProcess(result, (BpmnSubProcess) modelioElement);
            }
        
            for (BpmnDataAssociation assoc : activity.getDataOutputAssociation()) {
                this.processor.process(result, assoc);
            }
        
            for (BpmnDataAssociation assoc : activity.getDataInputAssociation()) {
                this.processor.process(result, assoc);
            }
        
        }
        
        if (modelioElement instanceof BpmnEvent) {
            BpmnEvent event = (BpmnEvent) modelioElement;
            for (BpmnEventDefinition definition : event.getEventDefinitions()) {
                this.processor.process(result, definition);
            }
        
            if (event instanceof BpmnThrowEvent) {
                for (BpmnDataAssociation assoc : ((BpmnThrowEvent) event).getDataInputAssociation()) {
                    this.processor.process(result, assoc);
                }
            }
        
            if (event instanceof BpmnCatchEvent) {
                for (BpmnDataAssociation assoc : ((BpmnCatchEvent) event).getDataOutputAssociation()) {
                    this.processor.process(result, assoc);
                }
            }
        
        }
    }

    @objid ("7a84d8dd-5156-451b-aa57-fe970f5cb42f")
    private void walkSubProcess(Object result, BpmnSubProcess modelioElement) {
        for (BpmnFlowElement flowElement : modelioElement.getFlowElement()) {
            walkFlowElement(result, flowElement);
        }
        
        for (AbstractDiagram diagram : modelioElement.getProduct()) {
            walkBpmnDiagram(result, diagram);
        }
    }

    @objid ("9dcef5c7-a70b-472e-b1ad-8a4b2d345785")
    private void walkMessage(Object context, BpmnMessage modelioElement) {
        this.processor.process(context, modelioElement);
    }

}
