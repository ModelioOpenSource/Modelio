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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TSequenceFlow;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.model.TUserTask;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnBusinessRuleTask;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnUserTask;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("0703a0b7-1058-45a0-b1bf-f774bcf08fcf")
public class UserTaskNode implements IProductionNode<BpmnUserTask, TUserTask> {
    @objid ("0a527be4-e063-46e6-aceb-ae96ea3cc62f")
    private Map<String, Object> elementsMap;

    @objid ("d7c289e9-b4e5-447e-9f8a-8216bf76d21c")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("d4080f1f-1c93-495f-8dd3-42bc8a9caab9")
    @Override
    public BpmnUserTask findUMLElement(MObject context, TUserTask jaxbElement) {
        if(context instanceof BpmnProcess){
            for(BpmnFlowElement flowElement :((BpmnProcess) context).getFlowElement()){
                if(flowElement instanceof BpmnUserTask && flowElement.getName().equals(jaxbElement.getName())){
                    return (BpmnUserTask)flowElement;
                }
            }
        }else if(context instanceof BpmnSubProcess){
            for(BpmnFlowElement flowElement :((BpmnSubProcess) context).getFlowElement()){
                if(flowElement instanceof BpmnBusinessRuleTask && flowElement.getName().equals(jaxbElement.getName())){
                    return (BpmnUserTask)flowElement;
                }
            }
        }
        return null;
    }

    @objid ("5dbd6857-267f-4004-826e-172e33a5c93f")
    @Override
    public BpmnUserTask createUMLElement(MObject context, TUserTask jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnUserTask.class,context,jaxbElement.getId());
        }else{
            return factory.create(BpmnUserTask.class,context);
        }
        
    }

    @objid ("d3d6089c-482c-412b-bc52-ef6080d621d2")
    @Override
    public BpmnUserTask updateUMLElement(MObject context, BpmnUserTask modelioElement, TUserTask jaxbElement) {
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
        
        
        // Set properties
        if(jaxbElement.getName()!=null)
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        
        if(jaxbElement.getCompletionQuantity() != null)
            modelioElement.setCompletionQuantity(jaxbElement.getCompletionQuantity().intValue());
        
        if(jaxbElement.getStartQuantity() != null)
            modelioElement.setStartQuantity(jaxbElement.getStartQuantity().intValue());
        
        modelioElement.setIsForCompensation(jaxbElement.isIsForCompensation());
        
        if(jaxbElement.getImplementation() != null){
            modelioElement.setImplementation(jaxbElement.getImplementation() );
        }
        
        // Default Flow
        if(jaxbElement.getDefault() != null && jaxbElement.getDefault() instanceof TSequenceFlow){
            BpmnSequenceFlow  flow = (BpmnSequenceFlow) this.elementsMap.get( ((TSequenceFlow)jaxbElement.getDefault()).getId());
            if(flow != null){
                modelioElement.setDefaultFlow(flow);
            }
        }
        return modelioElement;
    }

    @objid ("01149de0-bf92-4150-9d44-367807050fb8")
    @Override
    public TUserTask createJaxbElement(Object context, BpmnUserTask modelioElement) {
        // Create JaxbElement
        TUserTask jaxTask = new TUserTask();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if(context instanceof TProcess){
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess)context).getFlowElement();
            jaxContent.add(factory.createUserTask(jaxTask));
        }else if(context instanceof TSubProcess){
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess)context).getFlowElement();
            jaxContent.add(factory.createUserTask(jaxTask));
        }
        
        jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxTask;
    }

    @objid ("16e64cc9-7611-4cf6-956d-ece77ac51a7b")
    @Override
    public TUserTask updateJaxbElement(Object context, TUserTask jaxTask, BpmnUserTask modelioElement) {
        jaxTask.setName(modelioElement.getName());
        
        if(modelioElement.getCompletionQuantity() != 0){
            jaxTask.setCompletionQuantity(BigInteger.valueOf(modelioElement.getCompletionQuantity()));
        }
        
        if(modelioElement.getStartQuantity() != 0){
            jaxTask.setStartQuantity(BigInteger.valueOf(modelioElement.getStartQuantity()));
        }
        
        jaxTask.setIsForCompensation(modelioElement.isIsForCompensation());
        
        if(! "".equals(modelioElement.getImplementation())){
            jaxTask.setImplementation(modelioElement.getImplementation());
        }
        
        // Default Flow
        if(modelioElement.getDefaultFlow() != null){
            Object target = this.elementsMap.get(modelioElement.getDefaultFlow().getUuid());
            if(target != null)
                jaxTask.setDefault(target);
        }
        return jaxTask;
    }

    @objid ("33811216-22ab-4ffd-9a4e-0c36eab076b1")
    @Override
    public BpmnUserTask findUMLElementById(TUserTask jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnUserTask.class, jaxbElement.getId());
    }

}
