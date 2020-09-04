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
import org.modelio.bpmnxml.model.TCallActivity;
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TSequenceFlow;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.model.TTask;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("6252fb21-7d7b-4988-b1cb-ba8a56ac46e8")
public class CallActivityNode implements IProductionNode<BpmnCallActivity,TCallActivity> {
    @objid ("9c2a1b42-c955-4ad6-b870-0b96c00f01e1")
    private Map<String, Object> elementsMap;

    @objid ("bda8679a-1f3b-49ab-a9d1-ff7e2d643cb8")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("3819a358-56d9-4d5e-9529-419266b4b45b")
    @Override
    public BpmnCallActivity findUMLElement(MObject context, TCallActivity jaxbElement) {
        if (context instanceof BpmnProcess) {
            for (BpmnFlowElement flowElement : ((BpmnProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnCallActivity && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnCallActivity) flowElement;
                }
            }
        } else if (context instanceof BpmnSubProcess) {
            for (BpmnFlowElement flowElement : ((BpmnSubProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnCallActivity && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnCallActivity) flowElement;
                }
            }
        }
        return null;
    }

    @objid ("4d80f44a-c616-4df8-ab65-21b83f783ec1")
    @Override
    public BpmnCallActivity createUMLElement(MObject context, TCallActivity jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId && jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {
            return factory.createWithId(BpmnCallActivity.class, context, jaxbElement.getId());
        } else {
            return factory.create(BpmnCallActivity.class, context);
        }
    }

    @objid ("66148b5a-b6df-4592-a81c-467b2cb7ca9d")
    @Override
    public BpmnCallActivity updateUMLElement(MObject context, BpmnCallActivity modelioElement, TCallActivity jaxbElement) {
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
        
        // Set properties
        if (jaxbElement.getName() != null) {
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        }
        
        if (jaxbElement.getCalledElement() != null) {
            Object called = this.elementsMap.get(jaxbElement.getCalledElement().getLocalPart());
            if (called instanceof BpmnTask) {
                modelioElement.setCalledGlobalTask((BpmnTask) called);
            } else if (called instanceof BpmnProcess) {
                Called.setTarget(modelioElement, (BpmnProcess) called);
                if (((BpmnProcess) called).getName().equals("Process") || "".equals(((BpmnProcess) called).getName())) {
                    ((BpmnProcess) called).setName(modelioElement.getName());
                }
            } else if (called instanceof BpmnCollaboration) {
                Called.setTarget(modelioElement, (BpmnCollaboration) called);
            }
        }
        
        if (jaxbElement.getCompletionQuantity() != null) {
            modelioElement.setCompletionQuantity(jaxbElement.getCompletionQuantity().intValue());
        }
        
        if (jaxbElement.getStartQuantity() != null) {
            modelioElement.setStartQuantity(jaxbElement.getStartQuantity().intValue());
        }
        
        modelioElement.setIsForCompensation(jaxbElement.isIsForCompensation());
        
        // Default Flow
        if (jaxbElement.getDefault() != null && jaxbElement.getDefault() instanceof TSequenceFlow) {
            BpmnSequenceFlow flow = (BpmnSequenceFlow) this.elementsMap.get(((TSequenceFlow) jaxbElement.getDefault()).getId());
            if (flow != null) {
                modelioElement.setDefaultFlow(flow);
            }
        }
        return modelioElement;
    }

    @objid ("0d91535c-d6e9-4f42-b4ee-d7f1a8628a40")
    @Override
    public TCallActivity createJaxbElement(Object context, BpmnCallActivity modelioElement) {
        // Create JaxbElement
        TCallActivity jaxTask = new TCallActivity();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if (context instanceof TProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess) context).getFlowElement();
            jaxContent.add(factory.createCallActivity(jaxTask));
        } else if (context instanceof TSubProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess) context).getFlowElement();
            jaxContent.add(factory.createCallActivity(jaxTask));
        }
        
        jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxTask;
    }

    @objid ("f110937d-dfba-4fee-a839-65e488d5ab82")
    @Override
    public TCallActivity updateJaxbElement(Object context, TCallActivity jaxTask, BpmnCallActivity modelioElement) {
        jaxTask.setName(modelioElement.getName());
        
        if (modelioElement.getCompletionQuantity() != 0) {
            jaxTask.setCompletionQuantity(BigInteger.valueOf(modelioElement.getCompletionQuantity()));
        }
        
        if (modelioElement.getStartQuantity() != 0) {
            jaxTask.setStartQuantity(BigInteger.valueOf(modelioElement.getStartQuantity()));
        }
        
        jaxTask.setIsForCompensation(modelioElement.isIsForCompensation());
        
        if (modelioElement.getCalledGlobalTask() != null) {
            TTask called = (TTask) this.elementsMap.get(modelioElement.getCalledGlobalTask().getUuid());
            if (called != null) {
                jaxTask.setCalledElement(new QName(called.getId()));
            }
        }
        
        ModelElement calledElement = Called.getTarget(modelioElement);
        if (calledElement != null) {
            TProcess called = (TProcess) this.elementsMap.get(calledElement.getUuid());
            if (called != null) {
                jaxTask.setCalledElement(new QName(called.getId()));
            }
        }
        
        // Default Flow
        if (modelioElement.getDefaultFlow() != null) {
            Object target = this.elementsMap.get(modelioElement.getDefaultFlow().getUuid());
            if (target != null) {
                jaxTask.setDefault(target);
            }
        }
        return jaxTask;
    }

    @objid ("ef92d635-f096-4dab-8968-b784b7083e16")
    @Override
    public BpmnCallActivity findUMLElementById(TCallActivity jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnCallActivity.class, jaxbElement.getId());
    }

}
