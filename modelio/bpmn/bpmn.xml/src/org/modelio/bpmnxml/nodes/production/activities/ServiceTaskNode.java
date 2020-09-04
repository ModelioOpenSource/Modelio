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

package org.modelio.bpmnxml.nodes.production.activities;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TOperation;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TSequenceFlow;
import org.modelio.bpmnxml.model.TServiceTask;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnServiceTask;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("689fa1c7-690f-44c4-a58e-ef882fc9da0c")
public class ServiceTaskNode implements IProductionNode<BpmnServiceTask,TServiceTask> {
    @objid ("fb973941-4cac-4714-8e63-368e200b4812")
    private Map<String, Object> elementsMap;

    @objid ("073513ef-9f98-4652-b5ef-889598797663")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("b161db3f-35c0-40ad-8afe-8e9a97942e07")
    @Override
    public BpmnServiceTask findUMLElement(MObject context, TServiceTask jaxbElement) {
        if(context instanceof BpmnProcess){
            for(BpmnFlowElement flowElement :((BpmnProcess) context).getFlowElement()){
                if(flowElement instanceof BpmnServiceTask && flowElement.getName().equals(jaxbElement.getName())){
                    return (BpmnServiceTask)flowElement;
                }
            }
        }else if(context instanceof BpmnSubProcess){
            for(BpmnFlowElement flowElement :((BpmnSubProcess) context).getFlowElement()){
                if(flowElement instanceof BpmnServiceTask && flowElement.getName().equals(jaxbElement.getName())){
                    return (BpmnServiceTask)flowElement;
                }
            }
        }
        return null;
    }

    @objid ("fb62bc03-4b7f-4f0b-9723-3675a4cf5032")
    @Override
    public BpmnServiceTask createUMLElement(MObject context, TServiceTask jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnServiceTask.class,context,jaxbElement.getId());
        }else{
            return factory.create(BpmnServiceTask.class,context);
        }
    }

    @objid ("b86c8ff5-e550-4301-b553-ccec3977b6c9")
    @Override
    public BpmnServiceTask updateUMLElement(MObject context, BpmnServiceTask modelioElement, TServiceTask jaxbElement) {
        // Set owner
        if(context instanceof BpmnProcess){
            ((BpmnProcess) context).getFlowElement().add(modelioElement);
        }else if(context instanceof BpmnSubProcess){
            ((BpmnSubProcess) context).getFlowElement().add(modelioElement);
        }
        
        // Group
        if(jaxbElement.getCategoryValueRef() != null){
            for(QName jaxGroupRef : jaxbElement.getCategoryValueRef()){
                BpmnGroup modelioGroup = (BpmnGroup)this.elementsMap.get(jaxGroupRef.getLocalPart());
                if(modelioGroup != null){
                    modelioElement.getGroups().add(modelioGroup);
                }
            }
        }
        
        // Operations
        if(jaxbElement.getOperationRef() != null){
            BpmnOperation modelioOper = (BpmnOperation) this.elementsMap.get(jaxbElement.getOperationRef().getLocalPart());
            if (modelioOper != null) {
                modelioElement.setOperationRef(modelioOper);
            }
        }
        
        // Set properties
        if(jaxbElement.getName()!=null)
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        
        if(jaxbElement.getCompletionQuantity() != null)
            modelioElement.setCompletionQuantity(jaxbElement.getCompletionQuantity().intValue());
        
        if(jaxbElement.getStartQuantity() != null)
            modelioElement.setStartQuantity(jaxbElement.getStartQuantity().intValue());
        
        
        modelioElement.setIsForCompensation(jaxbElement.isIsForCompensation());
        
        modelioElement.setImplementation(jaxbElement.getImplementation());
        // Default Flow
        if(jaxbElement.getDefault() != null && jaxbElement.getDefault() instanceof TSequenceFlow){
            BpmnSequenceFlow  flow = (BpmnSequenceFlow) this.elementsMap.get( ((TSequenceFlow)jaxbElement.getDefault()).getId());
            if(flow != null){
                modelioElement.setDefaultFlow(flow);
            }
        }
        return modelioElement;
    }

    @objid ("7248aca3-c924-4d9c-b75b-077086f3c4e2")
    @Override
    public TServiceTask createJaxbElement(Object context, BpmnServiceTask modelioElement) {
        // Create JaxbElement
        TServiceTask jaxTask = new TServiceTask();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if(context instanceof TProcess){
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess)context).getFlowElement();
            jaxContent.add(factory.createServiceTask(jaxTask));
        }else if(context instanceof TSubProcess){
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess)context).getFlowElement();
            jaxContent.add(factory.createServiceTask(jaxTask));
        }
        
        jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxTask;
    }

    @objid ("39bdcb43-1910-4f1a-a360-570da8171b2b")
    @Override
    public TServiceTask updateJaxbElement(Object context, TServiceTask jaxTask, BpmnServiceTask modelioElement) {
        jaxTask.setName(modelioElement.getName());
        
        if(modelioElement.getCompletionQuantity() != 0){
            jaxTask.setCompletionQuantity(BigInteger.valueOf(modelioElement.getCompletionQuantity()));
        }
        
        if(modelioElement.getStartQuantity() != 0){
            jaxTask.setStartQuantity(BigInteger.valueOf(modelioElement.getStartQuantity()));
        }
        
        if(modelioElement.getOperationRef() != null){
            TOperation jaxOper = (TOperation)this.elementsMap.get(modelioElement.getOperationRef().getUuid());
            if(jaxOper != null){
                jaxTask.setOperationRef(new QName(IDUtils.formatJaxbID(modelioElement.getOperationRef())));
            }
        }
        
        jaxTask.setImplementation(modelioElement.getImplementation());
        jaxTask.setIsForCompensation(modelioElement.isIsForCompensation());
        
        // Default Flow
        if(modelioElement.getDefaultFlow() != null){
            Object target = this.elementsMap.get(modelioElement.getDefaultFlow().getUuid());
            if(target != null)
                jaxTask.setDefault(target);
        }
        return jaxTask;
    }

    @objid ("1d07f9f2-7e49-4049-b78e-e3d83ed1800e")
    @Override
    public BpmnServiceTask findUMLElementById(TServiceTask jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnServiceTask.class, jaxbElement.getId());
    }

}
