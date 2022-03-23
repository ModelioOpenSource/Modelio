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
import org.modelio.bpmnxml.model.TScript;
import org.modelio.bpmnxml.model.TScriptTask;
import org.modelio.bpmnxml.model.TSequenceFlow;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnScriptTask;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("e960be15-296f-4fbc-904a-e79f8e8127c8")
public class ScriptTaskNode implements IProductionNode<BpmnScriptTask, TScriptTask> {
    @objid ("0f35a789-ea71-41c0-b148-c566302d5536")
    private Map<String, Object> elementsMap;

    @objid ("072ccd6b-ea71-4438-8263-df552821f5be")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("dc28d0f2-ca9f-419f-a7b5-812f6266c88c")
    @Override
    public BpmnScriptTask findUMLElement(MObject context, TScriptTask jaxbElement) {
        if(context instanceof BpmnProcess){
            for(BpmnFlowElement flowElement :((BpmnProcess) context).getFlowElement()){
                if(flowElement instanceof BpmnScriptTask && flowElement.getName().equals(jaxbElement.getName())){
                    return (BpmnScriptTask)flowElement;
                }
            }
        }else if(context instanceof BpmnSubProcess){
            for(BpmnFlowElement flowElement :((BpmnSubProcess) context).getFlowElement()){
                if(flowElement instanceof BpmnScriptTask && flowElement.getName().equals(jaxbElement.getName())){
                    return (BpmnScriptTask)flowElement;
                }
            }
        }
        return null;
    }

    @objid ("76d93666-381b-4cb3-ae52-2227396a8067")
    @Override
    public BpmnScriptTask createUMLElement(MObject context, TScriptTask jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnScriptTask.class,context,jaxbElement.getId());
        }else{
            return factory.create(BpmnScriptTask.class,context);
        }
        
    }

    @objid ("eda6bace-29a5-4d55-b413-02232d6801b8")
    @Override
    public BpmnScriptTask updateUMLElement(MObject context, BpmnScriptTask modelioElement, TScriptTask jaxbElement) {
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
        
        if(jaxbElement.getScriptFormat() != null){
            modelioElement.setScriptLanguage(jaxbElement.getScriptFormat());
        }
        
        if(jaxbElement.getScript() != null){
            String content = "";
        
            for(Object ct :jaxbElement.getScript().getContent()){
                content = content + ct.toString() + " ";
            }
            modelioElement.setScript(content);
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

    @objid ("5777ae40-4359-4f9f-a2c0-732fd4f9f0bd")
    @Override
    public TScriptTask createJaxbElement(Object context, BpmnScriptTask modelioElement) {
        // Create JaxbElement
        TScriptTask jaxTask = new TScriptTask();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if(context instanceof TProcess){
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess)context).getFlowElement();
            jaxContent.add(factory.createScriptTask(jaxTask));
        }else if(context instanceof TSubProcess){
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess)context).getFlowElement();
            jaxContent.add(factory.createScriptTask(jaxTask));
        }
        
        jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxTask;
    }

    @objid ("0170bb64-a78b-491c-a8e9-bbc62331cd38")
    @Override
    public TScriptTask updateJaxbElement(Object context, TScriptTask jaxTask, BpmnScriptTask modelioElement) {
        jaxTask.setName(modelioElement.getName());
        
        if(modelioElement.getCompletionQuantity() != 0){
            jaxTask.setCompletionQuantity(BigInteger.valueOf(modelioElement.getCompletionQuantity()));
        }
        
        if(modelioElement.getStartQuantity() != 0){
            jaxTask.setStartQuantity(BigInteger.valueOf(modelioElement.getStartQuantity()));
        }
        
        jaxTask.setIsForCompensation(modelioElement.isIsForCompensation());
        
        if(!"".equals(modelioElement.getScriptLanguage())){
            jaxTask.setScriptFormat(modelioElement.getScriptLanguage());
        }
        
        if(!"".equals(modelioElement.getScript())){
            TScript jaxScript = new TScript();
            jaxScript.getContent().add(modelioElement.getScript());
            jaxTask.setScript(jaxScript);
        }
        
        // Default Flow
        if(modelioElement.getDefaultFlow() != null){
            Object target = this.elementsMap.get(modelioElement.getDefaultFlow().getUuid());
            if(target != null)
                jaxTask.setDefault(target);
        }
        return jaxTask;
    }

    @objid ("b295e844-fafb-4c40-86a7-eebd50bd4d24")
    @Override
    public BpmnScriptTask findUMLElementById(TScriptTask jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnScriptTask.class, jaxbElement.getId());
    }

}
