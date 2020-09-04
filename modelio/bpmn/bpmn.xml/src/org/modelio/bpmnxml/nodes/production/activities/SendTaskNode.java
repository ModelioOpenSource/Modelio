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
import org.modelio.bpmnxml.model.TMessage;
import org.modelio.bpmnxml.model.TOperation;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TSendTask;
import org.modelio.bpmnxml.model.TSequenceFlow;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnSendTask;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("505c264f-d369-4465-bc70-aa7f678f1547")
public class SendTaskNode implements IProductionNode<BpmnSendTask,TSendTask> {
    @objid ("23e51b43-d008-4a6c-959e-1036feff1986")
    private Map<String, Object> elementsMap;

    @objid ("8102104a-3945-4ff6-b5af-f3f2ec5719b0")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("a4b5c282-5d52-4dd1-a566-4ffa2113d6c5")
    @Override
    public BpmnSendTask findUMLElement(MObject context, TSendTask jaxbElement) {
        if(context instanceof BpmnProcess){
            for(BpmnFlowElement flowElement :((BpmnProcess) context).getFlowElement()){
                if(flowElement instanceof BpmnSendTask && flowElement.getName().equals(jaxbElement.getName())){
                    return (BpmnSendTask)flowElement;
                }
            }
        }else if(context instanceof BpmnSubProcess){
            for(BpmnFlowElement flowElement :((BpmnSubProcess) context).getFlowElement()){
                if(flowElement instanceof BpmnSendTask && flowElement.getName().equals(jaxbElement.getName())){
                    return (BpmnSendTask)flowElement;
                }
            }
        }
        return null;
    }

    @objid ("21e2ae80-cac2-489a-8b13-989338e3d6cc")
    @Override
    public BpmnSendTask createUMLElement(MObject context, TSendTask jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnSendTask.class,context,jaxbElement.getId());
        }else{
            return factory.create(BpmnSendTask.class,context);
        }
    }

    @objid ("09ddf7fb-1bf0-4907-8ec5-76896e7f16d0")
    @Override
    public BpmnSendTask updateUMLElement(MObject context, BpmnSendTask modelioElement, TSendTask jaxbElement) {
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
        
        // Message
        if (jaxbElement.getMessageRef() != null) {
            BpmnMessage modelioMessage = (BpmnMessage) this.elementsMap.get(jaxbElement.getMessageRef().getLocalPart());
            if (modelioMessage != null) {
                modelioElement.setMessageRef(modelioMessage);
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
        
        // Default Flow
        if(jaxbElement.getDefault() != null && jaxbElement.getDefault() instanceof TSequenceFlow){
            BpmnSequenceFlow  flow = (BpmnSequenceFlow) this.elementsMap.get( ((TSequenceFlow)jaxbElement.getDefault()).getId());
            if(flow != null){
                modelioElement.setDefaultFlow(flow);
            }
        }
        return modelioElement;
    }

    @objid ("765c32dd-3b3a-4fd0-955a-157c10fe2501")
    @Override
    public TSendTask createJaxbElement(Object context, BpmnSendTask modelioElement) {
        // Create JaxbElement
        TSendTask jaxTask = new TSendTask();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if(context instanceof TProcess){
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess)context).getFlowElement();
            jaxContent.add(factory.createSendTask(jaxTask));
        }else if(context instanceof TSubProcess){
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess)context).getFlowElement();
            jaxContent.add(factory.createSendTask(jaxTask));
        }
        
        jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxTask;
    }

    @objid ("4326306a-9548-433f-a6d8-cfc8d355868b")
    @Override
    public TSendTask updateJaxbElement(Object context, TSendTask jaxTask, BpmnSendTask modelioElement) {
        jaxTask.setName(modelioElement.getName());
        
        if(modelioElement.getCompletionQuantity() != 0){
            jaxTask.setCompletionQuantity(BigInteger.valueOf(modelioElement.getCompletionQuantity()));
        }
        
        if(modelioElement.getStartQuantity() != 0){
            jaxTask.setStartQuantity(BigInteger.valueOf(modelioElement.getStartQuantity()));
        }
        
        if(modelioElement.getMessageRef() != null){
            TMessage jaxMessage = (TMessage)this.elementsMap.get(modelioElement.getMessageRef().getUuid());
            if(jaxMessage != null){
                jaxTask.setMessageRef(new QName(IDUtils.formatJaxbID(modelioElement.getMessageRef())));
            }
        }
        
        if(modelioElement.getOperationRef() != null){
            TOperation jaxOper = (TOperation)this.elementsMap.get(modelioElement.getOperationRef().getUuid());
            if(jaxOper != null){
                jaxTask.setOperationRef(new QName(IDUtils.formatJaxbID(modelioElement.getOperationRef())));
            }
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

    @objid ("f6325a77-7801-47f0-8d51-f6a69077c103")
    @Override
    public BpmnSendTask findUMLElementById(TSendTask jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnSendTask.class, jaxbElement.getId());
    }

}
