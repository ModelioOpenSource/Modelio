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

package org.modelio.bpmnxml.nodes.production.activities;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TMessage;
import org.modelio.bpmnxml.model.TOperation;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TReceiveTask;
import org.modelio.bpmnxml.model.TSequenceFlow;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnReceiveTask;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("5bd7db9d-fa0f-4cdb-9200-cd2b1282f02a")
public class ReceiveTaskNode implements IProductionNode<BpmnReceiveTask,TReceiveTask> {
    @objid ("0377d040-a019-4ad1-9843-1bcce3fff508")
    private Map<String, Object> elementsMap;

    @objid ("6e66d6d9-a393-48b5-a796-85ad59eaf930")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("6c1a7e4c-1b3b-420d-b6c9-1ae28a819e92")
    @Override
    public BpmnReceiveTask findUMLElement(MObject context, TReceiveTask jaxbElement) {
        if (context instanceof BpmnProcess) {
            for (BpmnFlowElement flowElement : ((BpmnProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnReceiveTask && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnReceiveTask) flowElement;
                }
            }
        } else if (context instanceof BpmnSubProcess) {
            for (BpmnFlowElement flowElement : ((BpmnSubProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnReceiveTask && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnReceiveTask) flowElement;
                }
            }
        }
        return null;
    }

    @objid ("7c98fa97-33cd-451c-91da-6a1a4d74a1c8")
    @Override
    public BpmnReceiveTask createUMLElement(MObject context, TReceiveTask jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnReceiveTask.class, context,jaxbElement.getId());
        } else {
            return factory.create(BpmnReceiveTask.class, context);
        }
    }

    @objid ("7e8180eb-4765-4b2c-add3-25aead117147")
    @Override
    public BpmnReceiveTask updateUMLElement(MObject context, BpmnReceiveTask modelioElement, TReceiveTask jaxbElement) {
        // Set owner
        if (context instanceof BpmnProcess) {
            ((BpmnProcess) context).getFlowElement().add(modelioElement);
        } else if (context instanceof BpmnSubProcess) {
            ((BpmnSubProcess) context).getFlowElement().add(modelioElement);
        }
        
        // Group
        if (jaxbElement.getCategoryValueRef() != null) {
            for (QName jaxGroupRef : jaxbElement.getCategoryValueRef()) {
                BpmnGroup modelioGroup = (BpmnGroup) this.elementsMap.get(jaxGroupRef.getLocalPart());
                if (modelioGroup != null) {
                    modelioElement.getGroups().add(modelioGroup);
                }
            }
        }
        
        // Message
        if (jaxbElement.getMessageRef() != null) {
            BpmnMessage modelioMessage = (BpmnMessage) this.elementsMap.get(jaxbElement.getMessageRef().getLocalPart());
            if (modelioMessage != null) {
                modelioElement.setMessageRef(modelioMessage);
            }
        
        }
        
        // Operations
        if (jaxbElement.getOperationRef() != null) {
            BpmnOperation modelioOper = (BpmnOperation) this.elementsMap.get(jaxbElement.getOperationRef().getLocalPart());
            if (modelioOper != null) {
                modelioElement.setOperationRef(modelioOper);
            }
        }
        
        // Set properties
        if (jaxbElement.getName() != null)
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        
        if (jaxbElement.getCompletionQuantity() != null)
            modelioElement.setCompletionQuantity(jaxbElement.getCompletionQuantity().intValue());
        
        if (jaxbElement.getStartQuantity() != null)
            modelioElement.setStartQuantity(jaxbElement.getStartQuantity().intValue());
        
        modelioElement.setIsForCompensation(jaxbElement.isIsForCompensation());
        
        modelioElement.setImplementation(jaxbElement.getImplementation());
        
        modelioElement.setInstanciate(jaxbElement.isInstantiate());
        
        // Default Flow
        if (jaxbElement.getDefault() != null && jaxbElement.getDefault() instanceof TSequenceFlow) {
            BpmnSequenceFlow flow = (BpmnSequenceFlow) this.elementsMap.get(((TSequenceFlow) jaxbElement.getDefault()).getId());
            if (flow != null) {
                modelioElement.setDefaultFlow(flow);
            }
        }
        return modelioElement;
    }

    @objid ("d91b7da1-bb8b-4f08-88af-2702abb69c90")
    @Override
    public TReceiveTask createJaxbElement(Object context, BpmnReceiveTask modelioElement) {
        // Create JaxbElement
        TReceiveTask jaxTask = new TReceiveTask();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if (context instanceof TProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess) context).getFlowElement();
            jaxContent.add(factory.createReceiveTask(jaxTask));
        } else if (context instanceof TSubProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess) context).getFlowElement();
            jaxContent.add(factory.createReceiveTask(jaxTask));
        }
        
        jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxTask;
    }

    @objid ("c2b1bc0a-dfe0-4f67-8f16-95cb01581256")
    @Override
    public TReceiveTask updateJaxbElement(Object context, TReceiveTask jaxTask, BpmnReceiveTask modelioElement) {
        jaxTask.setName(modelioElement.getName());
        
        if (modelioElement.getMessageRef() != null) {
            TMessage jaxMessage = (TMessage) this.elementsMap.get(modelioElement.getMessageRef().getUuid());
            if (jaxMessage != null) {
                jaxTask.setMessageRef(new QName(IDUtils.formatJaxbID(modelioElement.getMessageRef())));
            }
        }
        
        if (modelioElement.getOperationRef() != null) {
            TOperation jaxOper = (TOperation) this.elementsMap.get(modelioElement.getOperationRef().getUuid());
            if (jaxOper != null) {
                jaxTask.setOperationRef(new QName(IDUtils.formatJaxbID(modelioElement.getOperationRef())));
            }
        }
        
        if (modelioElement.getCompletionQuantity() != 0) {
            jaxTask.setCompletionQuantity(BigInteger.valueOf(modelioElement.getCompletionQuantity()));
        }
        
        if (modelioElement.getStartQuantity() != 0) {
            jaxTask.setStartQuantity(BigInteger.valueOf(modelioElement.getStartQuantity()));
        }
        
        jaxTask.setImplementation(modelioElement.getImplementation());
        
        jaxTask.setInstantiate(modelioElement.isInstanciate());
        
        jaxTask.setIsForCompensation(modelioElement.isIsForCompensation());
        
        // Default Flow
        if (modelioElement.getDefaultFlow() != null) {
            Object target = this.elementsMap.get(modelioElement.getDefaultFlow().getUuid());
            if (target != null)
                jaxTask.setDefault(target);
        }
        return jaxTask;
    }

    @objid ("75b10506-4d74-4e50-a3ec-349d28093e21")
    @Override
    public BpmnReceiveTask findUMLElementById(TReceiveTask jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnReceiveTask.class, jaxbElement.getId());
    }

}
