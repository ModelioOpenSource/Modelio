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
package org.modelio.bpmnxml.importer.service.walker;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.bind.JAXBElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.modelio.bpmnxml.importer.service.processor.IBPMNImportProcessor;
import org.modelio.bpmnxml.model.BPMNDiagram;
import org.modelio.bpmnxml.model.BPMNPlane;
import org.modelio.bpmnxml.model.TActivity;
import org.modelio.bpmnxml.model.TArtifact;
import org.modelio.bpmnxml.model.TAssociation;
import org.modelio.bpmnxml.model.TCatchEvent;
import org.modelio.bpmnxml.model.TCollaboration;
import org.modelio.bpmnxml.model.TDataAssociation;
import org.modelio.bpmnxml.model.TDataInput;
import org.modelio.bpmnxml.model.TDataObject;
import org.modelio.bpmnxml.model.TDataOutput;
import org.modelio.bpmnxml.model.TDefinitions;
import org.modelio.bpmnxml.model.TEvent;
import org.modelio.bpmnxml.model.TEventDefinition;
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TInputOutputSpecification;
import org.modelio.bpmnxml.model.TLane;
import org.modelio.bpmnxml.model.TLaneSet;
import org.modelio.bpmnxml.model.TMessage;
import org.modelio.bpmnxml.model.TMessageFlow;
import org.modelio.bpmnxml.model.TParticipant;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TRootElement;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.model.TTextAnnotation;
import org.modelio.bpmnxml.model.TThrowEvent;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("7b1ef8d3-c416-4d7f-b131-d5cfca594003")
public class JaxbWalker {
    @objid ("c7f0fd88-fe63-47f3-839e-5f8b15ab1cee")
    private IBPMNImportProcessor processor;

    @objid ("a7cf0c56-eabe-487e-9bac-f98be0bd7ebd")
    private TDefinitions jaxbRoot;

    @objid ("0184886f-7269-4727-a7d7-8823b37cea4d")
    public  JaxbWalker(TDefinitions jaxbRoot, IBPMNImportProcessor processor) {
        this.processor = processor;
        this.jaxbRoot = jaxbRoot;
        
    }

    @objid ("bfbbc56b-1216-4c27-a50a-66f6876b41a2")
    public MObject walk(MObject context, IProgressMonitor progress) {
        MObject collaboration = null;
        TCollaboration jaxCollaboration = null;
        
        // Create Collaboration model
        for (JAXBElement<? extends TRootElement> jaxElement : this.jaxbRoot.getRootElement()) {
            TRootElement rootElement = jaxElement.getValue();
            if (rootElement instanceof TCollaboration) {
                jaxCollaboration = (TCollaboration) rootElement;
                if (jaxCollaboration.getName() == null || "".equals(jaxCollaboration.getName())) {
                    jaxCollaboration.setName(this.jaxbRoot.getName());
                }
                collaboration = walkCollaboration(context, jaxCollaboration);
                break;
            }
        }
        
        // Crate Processes models
        for (JAXBElement<? extends TRootElement> jaxElement : this.jaxbRoot.getRootElement()) {
            TRootElement rootElement = jaxElement.getValue();
            if (rootElement instanceof TProcess) {
           
                BpmnProcess process = (BpmnProcess) walkProcess(context, (TProcess) rootElement);
                
                if(process.getName().equals("Process") && this.jaxbRoot.getName() != null){
                    process.setName(this.jaxbRoot.getName());
                }
        
                // Create Process diagram
                for (BPMNDiagram jaxDiagram : this.jaxbRoot.getBPMNDiagram()) {
                    BPMNPlane plane = jaxDiagram.getBPMNPlane();
                    if (plane.getBpmnElement().getLocalPart().equals(rootElement.getId())) {
                        walkDiagram(process, jaxDiagram);
                    }
                }
            }
        }
        
        // Create Massages
        if (jaxCollaboration != null) {
            for (JAXBElement<? extends TRootElement> jaxElement : this.jaxbRoot.getRootElement()) {
                TRootElement rootElement = jaxElement.getValue();
                if (rootElement instanceof TMessage) {
                    walkMessage(collaboration, (TMessage) rootElement);
                }
            }
        }
        
        // Create Collaboration diagrams
        if (jaxCollaboration != null) {
            for (BPMNDiagram jaxDiagram : this.jaxbRoot.getBPMNDiagram()) {
                BPMNPlane plane = jaxDiagram.getBPMNPlane();
                if (plane.getBpmnElement().getLocalPart().equals(jaxCollaboration.getId())) {
                    walkDiagram(collaboration, jaxDiagram);
                }
            }
        }
        return null;
    }

    @objid ("8d244aff-d260-44fd-8c07-c5240b1fd514")
    private MObject walkCollaboration(MObject context, TCollaboration jaxElement) {
        MObject result = this.processor.process(context, jaxElement);
        
        for (TParticipant jaxParticipant : jaxElement.getParticipant()) {
            this.processor.process(result, jaxParticipant);
        }
        
        for (TMessageFlow jaxFlow : jaxElement.getMessageFlow()) {
            this.processor.process(result, jaxFlow);
        }
        return result;
    }

    @objid ("f21622c1-45ec-44c1-aeb7-1c445ec92c4f")
    private MObject walkProcess(MObject context, TProcess jaxProcess) {
        MObject result = this.processor.process(context, jaxProcess);
        
        for (TLaneSet jaxElement : jaxProcess.getLaneSet()) {
            walkLaneSet(result, jaxElement);
        }
        
        // Walk Elements
        for (JAXBElement<? extends TFlowElement> jaxElement : jaxProcess.getFlowElement()) {
            walkFlowElement(result, jaxElement.getValue());
        }
        
        for (JAXBElement<? extends TArtifact> jaxArt : jaxProcess.getArtifact()) {
            if (jaxArt.getValue() instanceof TAssociation || jaxArt.getValue() instanceof TTextAnnotation) {
                this.processor.process(result, jaxArt.getValue());
            }
        }
        return result;
    }

    @objid ("f2ba8687-4425-431a-a227-1f5e651ffe97")
    private void walkLaneSet(MObject context, TLaneSet jaxElement) {
        MObject result = this.processor.process(context, jaxElement);
        for (TLane jaxLane : jaxElement.getLane()) {
            walkLane(result, jaxLane);
        }
        
    }

    @objid ("5ddb4bed-eb73-4f32-812a-cb06eb6a4735")
    private void walkLane(MObject context, TLane jaxElement) {
        MObject result = this.processor.process(context, jaxElement);
        TLaneSet lanset = jaxElement.getChildLaneSet();
        if (lanset != null) {
            if(lanset.getLane() != null &&  !lanset.getLane().isEmpty()){
                walkLaneSet(result, lanset);
            }
        }
        
    }

    @objid ("1c910d85-bd8d-4426-be4b-6885f675b50a")
    private void walkFlowElement(MObject context, TFlowElement jaxElement) {
        if (!(jaxElement instanceof TDataObject)) {
            MObject result = this.processor.process(context, jaxElement);
        
            if (jaxElement instanceof TActivity) {
                walkActivity(result, (TActivity) jaxElement);
            }
        
            if (jaxElement instanceof TEvent) {
                walkEvent(result, (TEvent) jaxElement);
            }
        }
        
    }

    @objid ("7bd01583-e18d-444d-9d95-e75dfa8b205c")
    private void walkActivity(MObject context, TActivity jaxElement) {
        if (jaxElement.getLoopCharacteristics() != null) {
            this.processor.process(context, jaxElement.getLoopCharacteristics().getValue());
        }
        
        if (jaxElement instanceof TSubProcess) {
            walkSubProcess(context, (TSubProcess) jaxElement);
            
            for (BPMNDiagram jaxDiagram : this.jaxbRoot.getBPMNDiagram()) {
                BPMNPlane plane = jaxDiagram.getBPMNPlane();
                if (plane.getBpmnElement().getLocalPart().equals(jaxElement.getId())) {
                    walkDiagram(context, jaxDiagram);
                }
            }
        }
        
        TInputOutputSpecification iospecification = jaxElement.getIoSpecification();
        if (iospecification != null) {
            walkIoSpecification(context, iospecification);
        }
        
        for (TDataAssociation jaxDataAsso : jaxElement.getDataInputAssociation()) {
            walkDataAssociation(context, jaxDataAsso);
        }
        
        for (TDataAssociation jaxDataAsso : jaxElement.getDataOutputAssociation()) {
            walkDataAssociation(context, jaxDataAsso);
        }
        
    }

    @objid ("0c0338a2-944d-448b-9eb2-45f8b1892b0f")
    private void walkIoSpecification(MObject context, TInputOutputSpecification iospecification) {
        for (TDataInput jaxinput : iospecification.getDataInput()) {
            this.processor.process(context, jaxinput);
        }
        
        for (TDataOutput jaxoutput : iospecification.getDataOutput()) {
            this.processor.process(context, jaxoutput);
        }
        
    }

    @objid ("0199fe7f-5e8a-48fd-a9ac-841f8cb58c5d")
    private void walkDataAssociation(MObject context, TDataAssociation jaxDataAsso) {
        this.processor.process(context, jaxDataAsso);
    }

    @objid ("80c7f28c-b511-4dc1-874d-dca9f324ac59")
    private void walkSubProcess(MObject context, TSubProcess jaxElement) {
        for (JAXBElement<? extends TFlowElement> jaxFlow : jaxElement.getFlowElement()) {
            walkFlowElement(context, jaxFlow.getValue());  
        }
               
        
        for (JAXBElement<? extends TArtifact> jaxArt : jaxElement.getArtifact()) {
            if (jaxArt.getValue() instanceof TAssociation || jaxArt.getValue() instanceof TTextAnnotation) {
                this.processor.process(context, jaxArt.getValue());
            }
        }
        
    }

    @objid ("93989f63-bbea-497f-b363-b437645a56c1")
    private MObject walkDiagram(MObject context, BPMNDiagram jaxDiagram) {
        MObject result = this.processor.process(context, jaxDiagram);
        return result;
    }

    @objid ("e55bdabf-56e3-4a3b-a079-5b6462bb1f88")
    private void walkEvent(MObject context, TEvent jaxEvent) {
        if (jaxEvent instanceof TCatchEvent) {
            walkCatchEvent(context, (TCatchEvent) jaxEvent);
        } else if (jaxEvent instanceof TThrowEvent) {
            walkThrowEvent(context, (TThrowEvent) jaxEvent);
        }
        
    }

    @objid ("54602768-1a0d-4a67-af84-a05893c7b120")
    private void walkThrowEvent(MObject context, TThrowEvent jaxThrowEvent) {
        if (jaxThrowEvent.getEventDefinition() != null) {
            for (JAXBElement<? extends TEventDefinition> definition : jaxThrowEvent.getEventDefinition()) {
                this.processor.process(context, definition.getValue());
            }
        }
        
        for (TDataInput datainput : jaxThrowEvent.getDataInput()) {
            this.processor.process(context, datainput);
        }
        
        for (TDataAssociation jaxDataAsso : jaxThrowEvent.getDataInputAssociation()) {
            walkDataAssociation(context, jaxDataAsso);
        }
        
    }

    @objid ("07fa8798-2b93-4ae7-b618-6f596b57cc53")
    private void walkCatchEvent(MObject context, TCatchEvent jaxCatchEvent) {
        if (jaxCatchEvent.getEventDefinition() != null) {
            for (JAXBElement<? extends TEventDefinition> definition : jaxCatchEvent.getEventDefinition()) {
                this.processor.process(context, definition.getValue());
            }
        }
        
        for (TDataOutput dataoutput : jaxCatchEvent.getDataOutput()) {
            this.processor.process(context, dataoutput);
        }
        
        for (TDataAssociation jaxDataAsso : jaxCatchEvent.getDataOutputAssociation()) {
            walkDataAssociation(context, jaxDataAsso);
        }
        
    }

    @objid ("4610687d-fd1b-4d09-aaed-98c8cbe24431")
    private void walkMessage(MObject context, TMessage jaxElement) {
        this.processor.process(context, jaxElement);
    }

}
