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

package org.modelio.bpmnxml.nodes.production.activities;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TBusinessRuleTask;
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TSequenceFlow;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnBusinessRuleTask;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("b6097449-769b-453e-a114-b57e8a31e9e5")
public class BusinessTaskNode implements IProductionNode<BpmnBusinessRuleTask,TBusinessRuleTask> {
    @objid ("505635cf-3ca0-48ca-881e-dbb5b503643f")
    private Map<String, Object> elementsMap;

    @objid ("aaa1d693-333c-41d5-8fce-2453b5dfbf5e")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("54859f9b-c767-4e04-9024-9fa8c875404f")
    @Override
    public BpmnBusinessRuleTask findUMLElement(MObject context, TBusinessRuleTask jaxbElement) {
        if(context instanceof BpmnProcess){
            for(BpmnFlowElement flowElement :((BpmnProcess) context).getFlowElement()){
                if(flowElement instanceof BpmnBusinessRuleTask && flowElement.getName().equals(jaxbElement.getName())){
                    return (BpmnBusinessRuleTask)flowElement;
                }
            }
        }else if(context instanceof BpmnSubProcess){
            for(BpmnFlowElement flowElement :((BpmnSubProcess) context).getFlowElement()){
                if(flowElement instanceof BpmnBusinessRuleTask && flowElement.getName().equals(jaxbElement.getName())){
                    return (BpmnBusinessRuleTask)flowElement;
                }
            }
        }
        return null;
    }

    @objid ("4f2dfe4e-9303-4370-9157-65411ab48ff2")
    @Override
    public BpmnBusinessRuleTask createUMLElement(MObject context, TBusinessRuleTask jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnBusinessRuleTask.class,context,jaxbElement.getId());
        }else{
            return factory.create(BpmnBusinessRuleTask.class,context);
        }
    }

    @objid ("c48b1a38-19eb-4c7b-9e9e-e3e07787f798")
    @Override
    public BpmnBusinessRuleTask updateUMLElement(MObject context, BpmnBusinessRuleTask modelioElement, TBusinessRuleTask jaxbElement) {
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
        
        // Default Flow
        if(jaxbElement.getDefault() != null && jaxbElement.getDefault() instanceof TSequenceFlow){
            BpmnSequenceFlow  flow = (BpmnSequenceFlow) this.elementsMap.get( ((TSequenceFlow)jaxbElement.getDefault()).getId());
            if(flow != null){
                modelioElement.setDefaultFlow(flow);
            }
        }
        return modelioElement;
    }

    @objid ("a7b24164-c75a-445c-b1d2-34349967487e")
    @Override
    public TBusinessRuleTask createJaxbElement(Object context, BpmnBusinessRuleTask modelioElement) {
        // Create JaxbElement
        TBusinessRuleTask jaxTask = new TBusinessRuleTask();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if(context instanceof TProcess){
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess)context).getFlowElement();
            jaxContent.add(factory.createBusinessRuleTask(jaxTask));
        }else if(context instanceof TSubProcess){
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess)context).getFlowElement();
            jaxContent.add(factory.createBusinessRuleTask(jaxTask));
        }
        
        jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxTask;
    }

    @objid ("3158950e-70e4-4636-a0ea-12a232d01408")
    @Override
    public TBusinessRuleTask updateJaxbElement(Object context, TBusinessRuleTask jaxTask, BpmnBusinessRuleTask modelioElement) {
        jaxTask.setName(modelioElement.getName());
        
        if(modelioElement.getCompletionQuantity() != 0){
            jaxTask.setCompletionQuantity(BigInteger.valueOf(modelioElement.getCompletionQuantity()));
        }
        
        if(modelioElement.getStartQuantity() != 0){
            jaxTask.setStartQuantity(BigInteger.valueOf(modelioElement.getStartQuantity()));
        }
        
        jaxTask.setIsForCompensation(modelioElement.isIsForCompensation());
        
        // Default Flow
        if(modelioElement.getDefaultFlow() != null){
            Object target = this.elementsMap.get(modelioElement.getDefaultFlow().getUuid());
            if(target != null)
                jaxTask.setDefault(target);
        }
        return jaxTask;
    }

    @objid ("e4dac913-d7b2-46f6-bb2e-9d5f6444edf9")
    @Override
    public BpmnBusinessRuleTask findUMLElementById(TBusinessRuleTask jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnBusinessRuleTask.class, jaxbElement.getId());
    }

}
