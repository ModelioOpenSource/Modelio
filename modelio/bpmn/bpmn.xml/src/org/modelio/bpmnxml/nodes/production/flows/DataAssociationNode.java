/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.bpmnxml.nodes.production.flows;

import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TActivity;
import org.modelio.bpmnxml.model.TBaseElement;
import org.modelio.bpmnxml.model.TCatchEvent;
import org.modelio.bpmnxml.model.TDataAssociation;
import org.modelio.bpmnxml.model.TDataInput;
import org.modelio.bpmnxml.model.TDataInputAssociation;
import org.modelio.bpmnxml.model.TDataOutput;
import org.modelio.bpmnxml.model.TDataOutputAssociation;
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TInputOutputSpecification;
import org.modelio.bpmnxml.model.TInputSet;
import org.modelio.bpmnxml.model.TOutputSet;
import org.modelio.bpmnxml.model.TThrowEvent;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("a2043dcb-8436-48f3-895c-a3eeb0ed4f55")
public class DataAssociationNode implements IProductionNode<BpmnDataAssociation,TDataAssociation> {
    @objid ("4c9876f6-72ac-4a3d-9a36-e8619c8fac1b")
    private Map<String, Object> elementsMap;

    @objid ("5c93c667-b078-4c2d-a1cc-da8990587d29")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("f21145c1-0dec-453c-bbc6-8a19c3259ca2")
    @Override
    public BpmnDataAssociation findUMLElement(MObject context, TDataAssociation jaxbElement) {
        return null;
    }

    @objid ("bd10a47f-0096-4285-825b-4f4503ef8e40")
    @Override
    public BpmnDataAssociation createUMLElement(MObject context, TDataAssociation jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId) {
            return factory.createWithId(BpmnDataAssociation.class, context, jaxbElement.getId());
        } else {
            return factory.create(BpmnDataAssociation.class, context);
        }
    }

    @objid ("9bb674c5-3021-4227-8ee3-e42980ccf29b")
    @Override
    public BpmnDataAssociation updateUMLElement(MObject context, BpmnDataAssociation modelioElement, TDataAssociation jaxbElement) {
        Object from = null;
        
        if (jaxbElement.getSourceRef() != null && jaxbElement.getTargetRef() != null) {
            for (JAXBElement<Object> jaxInputs : jaxbElement.getSourceRef()) {
                from = this.elementsMap.get(((TBaseElement) jaxInputs.getValue()).getId());
                break;
            }
        
            Object to = this.elementsMap.get(((TBaseElement) jaxbElement.getTargetRef()).getId());
        
            if (from != null && to != null) {
                if (jaxbElement instanceof TDataInputAssociation) {
                    if (((BpmnDataInput) to).getOwnerActivity() != null) {
                        BpmnActivity toActivity = ((BpmnDataInput) to).getOwnerActivity();
                        BpmnItemAwareElement fromIt = (BpmnItemAwareElement) from;
                        modelioElement.setEndingActivity(toActivity);
                        modelioElement.getSourceRef().clear();
                        modelioElement.getSourceRef().add(fromIt);
        
                        fromIt.getLane().clear();
                        for (BpmnLane lane : toActivity.getLane()) {
                            fromIt.getLane().add(lane);
                        }
                    } else if (((BpmnDataInput) to).getOwnerThrowEvent() != null) {
                        BpmnThrowEvent fromEvent = ((BpmnDataInput) to).getOwnerThrowEvent();
                        BpmnItemAwareElement toIt = (BpmnItemAwareElement) from;
                        modelioElement.setStartingEvent(fromEvent);
                        modelioElement.getSourceRef().clear();
                        modelioElement.getSourceRef().add(toIt);
        
                        toIt.getLane().clear();
                        for (BpmnLane lane : fromEvent.getLane()) {
                            toIt.getLane().add(lane);
                        }
                    }
        
                } else if (jaxbElement instanceof TDataOutputAssociation) {
                    if (((BpmnDataOutput) from).getOwnerActivity() != null) {
                        BpmnActivity fromActivity = ((BpmnDataOutput) from).getOwnerActivity();
                        BpmnItemAwareElement toIt = (BpmnItemAwareElement) to;
                        modelioElement.setStartingActivity(fromActivity);
                        modelioElement.setTargetRef(toIt);
        
                        toIt.getLane().clear();
                        for (BpmnLane lane : fromActivity.getLane()) {
                            toIt.getLane().add(lane);
                        }
                    } else if (((BpmnDataOutput) from).getCatched() != null) {
                        BpmnCatchEvent toEvent = ((BpmnDataOutput) from).getCatched();
                        BpmnItemAwareElement fromIt = (BpmnItemAwareElement) to;
                        modelioElement.setEndingEvent(toEvent);
                        modelioElement.setTargetRef(fromIt);
        
                        fromIt.getLane().clear();
                        for (BpmnLane lane : toEvent.getLane()) {
                            fromIt.getLane().add(lane);
                        }
                    }
                } else {
                    modelioElement.delete();
                }
            } else {
                modelioElement.delete();
            }
        } else {
            modelioElement.delete();
        }
        return modelioElement;
    }

    @objid ("c48596ad-35b1-4093-9220-096186e0a5b4")
    @Override
    public TDataAssociation createJaxbElement(Object context, BpmnDataAssociation modelioElement) {
        if (modelioElement.getStartingActivity() != null && modelioElement.getTargetRef() != null) {
            // Create DataInputAssociation
            TDataOutputAssociation jaxAssoc = new TDataOutputAssociation();
            jaxAssoc.setId(IDUtils.formatJaxbID(modelioElement));
            return jaxAssoc;
        } else if (modelioElement.getEndingActivity() != null && modelioElement.getSourceRef().size() > 0) {
            TDataInputAssociation jaxAssoc = new TDataInputAssociation();
            jaxAssoc.setId(IDUtils.formatJaxbID(modelioElement));
            return jaxAssoc;
        } else if (modelioElement.getEndingEvent() != null && modelioElement.getTargetRef() != null) {
            // Create DataInputAssociation
            TDataOutputAssociation jaxAssoc = new TDataOutputAssociation();
            jaxAssoc.setId(IDUtils.formatJaxbID(modelioElement));
        
            return jaxAssoc;
        } else if (modelioElement.getStartingEvent() != null && modelioElement.getSourceRef().size() > 0) {
            TDataInputAssociation jaxAssoc = new TDataInputAssociation();
            jaxAssoc.setId(IDUtils.formatJaxbID(modelioElement));
            return jaxAssoc;
        }
        return null;
    }

    @objid ("2abee639-a465-4b70-9d18-f679c7c79936")
    @Override
    public TDataAssociation updateJaxbElement(Object context, TDataAssociation jaxAssociation, BpmnDataAssociation modelioElement) {
        ObjectFactory factory = new ObjectFactory();
        
        if (jaxAssociation instanceof TDataOutputAssociation) {
            // Data Input Association to Activity
            if (modelioElement.getStartingActivity() != null) {
                TActivity activity = (TActivity) this.elementsMap.get(modelioElement.getStartingActivity().getUuid());
                TFlowElement source = (TFlowElement) this.elementsMap.get(modelioElement.getTargetRef().getUuid());
        
                // Create Io Specification
                TInputOutputSpecification jaxIoSpec = activity.getIoSpecification();
        
                if (jaxIoSpec == null) {
                    jaxIoSpec = new TInputOutputSpecification();
                    activity.setIoSpecification(jaxIoSpec);
                }
        
                List<TInputSet> inputSets = jaxIoSpec.getInputSet();
                if (inputSets.isEmpty()) {
                    TInputSet inputSet = new TInputSet();
                    inputSet.setId("InputSet_" + activity.getId());
                    jaxIoSpec.getInputSet().add(inputSet);
                }
        
                List<TOutputSet> outputSets = jaxIoSpec.getOutputSet();
                if (outputSets.isEmpty()) {
                    TOutputSet outputSet = new TOutputSet();
                    outputSet.setId("OutputSet_" + activity.getId());
                    jaxIoSpec.getOutputSet().add(outputSet);
                }
        // Si Milieu ??
        //                if (inputSets.get(0).getOutputSetRefs() == null || inputSets.get(0).getOutputSetRefs().isEmpty()) {
        //                    inputSets.get(0).getOutputSetRefs().add(factory.createTInputSetOutputSetRefs(outputSets.get(0)));
        //                }
        //
        //                if (outputSets.get(0).getInputSetRefs() == null || outputSets.get(0).getInputSetRefs().isEmpty()) {
        //                    outputSets.get(0).getInputSetRefs().add(factory.createTOutputSetInputSetRefs(inputSets.get(0)));
        //                }
        
                // Create Data Input
                TDataOutput jaxDataOutput = new TDataOutput();
                jaxIoSpec.getDataOutput().add(jaxDataOutput);
                jaxDataOutput.setId(IDUtils.formatJaxbID(modelioElement.getStartingActivity()) + "-" + IDUtils.formatJaxbID(modelioElement.getTargetRef()));
                outputSets.get(0).getDataOutputRefs().add(factory.createTOutputSetDataOutputRefs(jaxDataOutput));
        
                // Create DataInputAssociation
                jaxAssociation.getSourceRef().add(factory.createTDataAssociationSourceRef(jaxDataOutput));
                jaxAssociation.setTargetRef(source);
                activity.getDataOutputAssociation().add((TDataOutputAssociation) jaxAssociation);
        
                // if(inputSets.get(0).getOutputSetRefs() == null || inputSets.get(0).getOutputSetRefs().isEmpty()){
                // inputSets.get(0).getOutputSetRefs().add(factory.createTInputSetOutputSetRefs(outputSets.get(0)));
                // }
                //
                // if(outputSets.get(0).getInputSetRefs() == null || outputSets.get(0).getInputSetRefs().isEmpty()){
                // outputSets.get(0).getInputSetRefs().add(factory.createTOutputSetInputSetRefs(inputSets.get(0)));
                // }
                //
                //
                // // Create Data Input
                // TDataInput jaxDataInput = new TDataInput();
                // jaxIoSpec.getDataInput().add(jaxDataInput);
                // jaxDataInput.setId(IDUtils.formatJaxbID(modelioElement.getEndingActivity()) + "-" + IDUtils.formatJaxbID(modelioElement.getTargetRef()));
                // inputSets.get(0).getDataInputRefs().add(factory.createTInputSetDataInputRefs(jaxDataInput));
                //
                // // Create DataInputAssociation
                // jaxAssociation.getSourceRef().add(factory.createTDataAssociationSourceRef(source));
                // jaxAssociation.setTargetRef(jaxDataInput);
                // activity.getDataInputAssociation().add((TDataInputAssociation) jaxAssociation);
            }else if (modelioElement.getEndingEvent() != null) {
                TCatchEvent event = (TCatchEvent) this.elementsMap.get(modelioElement.getEndingEvent().getUuid());
        
                TFlowElement source = (TFlowElement) this.elementsMap.get(modelioElement.getTargetRef().getUuid());
        
                // Create Data Input
                TDataOutput jaxDataOutput = new TDataOutput();
                event.getDataOutput().add(jaxDataOutput);
                jaxDataOutput.setId(IDUtils.formatJaxbID(modelioElement.getEndingEvent()) + "-" + IDUtils.formatJaxbID(modelioElement.getTargetRef()));
        
                // Create InputSet and DataInputRef
                TOutputSet outputSet = new TOutputSet();
                event.setOutputSet(outputSet);
                outputSet.getDataOutputRefs().add(factory.createTOutputSetDataOutputRefs(jaxDataOutput));
        
                // Create DataInputAssociation
                jaxAssociation.getSourceRef().add(factory.createTDataAssociationSourceRef(jaxDataOutput));
                jaxAssociation.setTargetRef(source);
                event.getDataOutputAssociation().add((TDataOutputAssociation) jaxAssociation);
            }
        
        } else if (jaxAssociation instanceof TDataInputAssociation) {
            //
            if (modelioElement.getEndingActivity() != null) {
                // Data Input Association to Activity
                TActivity activity = (TActivity) this.elementsMap.get(modelioElement.getEndingActivity().getUuid());
                TFlowElement source = (TFlowElement) this.elementsMap.get(modelioElement.getSourceRef().get(0).getUuid());
        
                // Create Io Specification
                TInputOutputSpecification jaxIoSpec = activity.getIoSpecification();
        
                if (jaxIoSpec == null) {
                    jaxIoSpec = new TInputOutputSpecification();
                    activity.setIoSpecification(jaxIoSpec);
                }
        
                List<TInputSet> inputSets = jaxIoSpec.getInputSet();
                if (inputSets.isEmpty()) {
                    TInputSet inputSet = new TInputSet();
                    inputSet.setId("InputSet_" + activity.getId());
                    jaxIoSpec.getInputSet().add(inputSet);
                }
        
                List<TOutputSet> outputSets = jaxIoSpec.getOutputSet();
                if (outputSets.isEmpty()) {
                    TOutputSet outputSet = new TOutputSet();
                    outputSet.setId("OutputSet" + activity.getId());
                    jaxIoSpec.getOutputSet().add(outputSet);
                }
        
        //                if (inputSets.get(0).getOutputSetRefs() == null || inputSets.get(0).getOutputSetRefs().isEmpty()) {
        //                    inputSets.get(0).getOutputSetRefs().add(factory.createTInputSetOutputSetRefs(outputSets.get(0)));
        //                }
        //
        //                if (outputSets.get(0).getInputSetRefs() == null || outputSets.get(0).getInputSetRefs().isEmpty()) {
        //                    outputSets.get(0).getInputSetRefs().add(factory.createTOutputSetInputSetRefs(inputSets.get(0)));
        //                }
        
                // Create Data Input
                TDataInput jaxDataInput = new TDataInput();
                jaxIoSpec.getDataInput().add(jaxDataInput);
                jaxDataInput.setId(IDUtils.formatJaxbID(modelioElement.getEndingActivity()) + "-" + IDUtils.formatJaxbID(modelioElement.getSourceRef().get(0)));
                inputSets.get(0).getDataInputRefs().add(factory.createTInputSetDataInputRefs(jaxDataInput));
        
                // Create DataInputAssociation
                jaxAssociation.getSourceRef().add(factory.createTDataAssociationSourceRef(source));
                jaxAssociation.setTargetRef(jaxDataInput);
                activity.getDataInputAssociation().add((TDataInputAssociation) jaxAssociation);
        
                // if(inputSets.get(0).getOutputSetRefs() == null || inputSets.get(0).getOutputSetRefs().isEmpty()){
                // inputSets.get(0).getOutputSetRefs().add(factory.createTInputSetOutputSetRefs(outputSets.get(0)));
                // }
                //
                // if(outputSets.get(0).getInputSetRefs() == null || outputSets.get(0).getInputSetRefs().isEmpty()){
                // outputSets.get(0).getInputSetRefs().add(factory.createTOutputSetInputSetRefs(inputSets.get(0)));
                // }
                //
                // // Create Data Input
                // TDataOutput jaxDataOutput = new TDataOutput();
                // jaxIoSpec.getDataOutput().add(jaxDataOutput);
                // jaxDataOutput.setId(IDUtils.formatJaxbID(modelioElement.getStartingActivity())+ "-" + IDUtils.formatJaxbID(modelioElement.getSourceRef().get(0)));
                // outputSets.get(0).getDataOutputRefs().add(factory.createTOutputSetDataOutputRefs(jaxDataOutput));
                //
                // // Create DataInputAssociation
                // jaxAssociation.getSourceRef().add(factory.createTDataAssociationSourceRef(jaxDataOutput));
                // jaxAssociation.setTargetRef(source);
                // activity.getDataOutputAssociation().add((TDataOutputAssociation) jaxAssociation);
            } else if (modelioElement.getStartingEvent() != null) {
                TThrowEvent event = (TThrowEvent) this.elementsMap.get(modelioElement.getStartingEvent().getUuid());
                TFlowElement source = (TFlowElement) this.elementsMap.get(modelioElement.getSourceRef().get(0).getUuid());
        
                // Create Data Input
                TDataInput jaxDataInput = new TDataInput();
                event.getDataInput().add(jaxDataInput);
                jaxDataInput.setId(IDUtils.formatJaxbID(modelioElement.getStartingEvent()) + "-" + IDUtils.formatJaxbID(modelioElement.getSourceRef().get(0)));
        
                // Create InputSet and DataInputRef
                TInputSet inputSet = new TInputSet();
                event.setInputSet(inputSet);
                inputSet.getDataInputRefs().add(factory.createTInputSetDataInputRefs(jaxDataInput));
        
                // Create DataInputAssociation
                jaxAssociation.getSourceRef().add(factory.createTDataAssociationSourceRef(source));
                jaxAssociation.setTargetRef(jaxDataInput);
                event.getDataInputAssociation().add((TDataInputAssociation) jaxAssociation);
            }      
        
              
        
        }
        return jaxAssociation;
    }

    @objid ("522f0380-e125-4d5e-b3f4-2110d38f65af")
    @Override
    public BpmnDataAssociation findUMLElementById(TDataAssociation jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnDataAssociation.class, jaxbElement.getId());
    }

}
