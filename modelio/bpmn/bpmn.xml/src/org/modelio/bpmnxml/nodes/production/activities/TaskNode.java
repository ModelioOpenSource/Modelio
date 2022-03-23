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
import org.modelio.bpmnxml.model.TTask;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("8ce7ae50-297b-4269-975b-8ce35602d1fb")
public class TaskNode implements IProductionNode<BpmnTask, TTask> {
    @objid ("3ed09e3f-c8b0-4377-9fd8-310ec4e5accd")
    private Map<String, Object> elementsMap;

    @objid ("841079a8-adde-48fc-8625-f889efa1781e")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("6f8a4aae-f43a-4401-af97-30fdef279615")
    @Override
    public BpmnTask findUMLElement(MObject context, TTask jaxbElement) {
        if(context instanceof BpmnProcess){
            for(BpmnFlowElement flowElement :((BpmnProcess) context).getFlowElement()){
                if(flowElement.getClass().getSimpleName().equals("BpmnTaskImpl") && flowElement.getName().equals(jaxbElement.getName())){
                    return (BpmnTask)flowElement;
                }
            }
        }else if(context instanceof BpmnSubProcess){
            for(BpmnFlowElement flowElement :((BpmnSubProcess) context).getFlowElement()){
                if(flowElement.getClass().getSimpleName().equals("BpmnTaskImpl") && flowElement.getName().equals(jaxbElement.getName())){
                    return (BpmnTask)flowElement;
                }
            }
        }
        return null;
    }

    @objid ("53efdebc-80bd-4bc6-ae2b-b0b210140e4a")
    @Override
    public BpmnTask createUMLElement(MObject context, TTask jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnTask.class,context,jaxbElement.getId());
        }else{
            return factory.create(BpmnTask.class,context);
        }
        
    }

    @objid ("70cd3eb9-15ac-448f-8816-ee23fdb201d4")
    @Override
    public BpmnTask updateUMLElement(MObject context, BpmnTask modelioElement, TTask jaxbElement) {
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

    @objid ("d946aa5f-30ae-4e8b-badf-765e0440a96e")
    @Override
    public TTask createJaxbElement(Object context, BpmnTask modelioElement) {
        // Create JaxbElement
        TTask jaxTask = new TTask();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if(context instanceof TProcess){
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess)context).getFlowElement();
            jaxContent.add(factory.createTask(jaxTask));
        }else if(context instanceof TSubProcess){
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess)context).getFlowElement();
            jaxContent.add(factory.createTask(jaxTask));
        }
        
        jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxTask;
    }

    @objid ("66be84a7-54de-4592-aaed-b384faa29edf")
    @Override
    public TTask updateJaxbElement(Object context, TTask jaxTask, BpmnTask modelioElement) {
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

    @objid ("98b380ee-1e22-47b4-a606-42794de73c87")
    @Override
    public BpmnTask findUMLElementById(TTask jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnTask.class, jaxbElement.getId());
    }

}
