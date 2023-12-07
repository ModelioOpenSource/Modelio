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

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jakarta.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TAdHocOrdering;
import org.modelio.bpmnxml.model.TAdHocSubProcess;
import org.modelio.bpmnxml.model.TExpression;
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TSequenceFlow;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.AdHocOrdering;
import org.modelio.metamodel.bpmn.activities.BpmnAdHocSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("ec5a3194-5413-45bb-819c-11d1b83e8f0c")
public class AdHocSubProcessNode implements IProductionNode<BpmnAdHocSubProcess, TAdHocSubProcess> {
    @objid ("a8a98c51-aff6-43f4-a739-1773bffd0e84")
    private Map<String, Object> elementsMap;

    @objid ("843b8884-e451-4d44-8760-d385c9affefd")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("152705fd-4d72-4e6f-b8b8-3f3379c4e3f5")
    @Override
    public BpmnAdHocSubProcess findUMLElement(MObject context, TAdHocSubProcess jaxbElement) {
        if (context instanceof BpmnProcess) {
            for (BpmnFlowElement flowElement : ((BpmnProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnAdHocSubProcess && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnAdHocSubProcess) flowElement;
                }
            }
        } else if (context instanceof BpmnSubProcess) {
            for (BpmnFlowElement flowElement : ((BpmnSubProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnAdHocSubProcess && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnAdHocSubProcess) flowElement;
                }
            }
        }
        return null;
    }

    @objid ("81418929-a765-4ba5-a633-13d504093f03")
    @Override
    public BpmnAdHocSubProcess createUMLElement(MObject context, TAdHocSubProcess jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId && jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {
            return factory.createWithId(BpmnAdHocSubProcess.class, context, jaxbElement.getId());
        } else {
            return factory.create(BpmnAdHocSubProcess.class, context);
        }
        
    }

    @objid ("8a82ee6e-fbc7-4f7f-bae2-50a79b56896c")
    @Override
    public BpmnAdHocSubProcess updateUMLElement(MObject context, BpmnAdHocSubProcess modelioElement, TAdHocSubProcess jaxbElement) {
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
        
        if (jaxbElement.getCompletionQuantity() != null) {
            modelioElement.setCompletionQuantity(jaxbElement.getCompletionQuantity().intValue());
        }
        
        if (jaxbElement.getStartQuantity() != null) {
            modelioElement.setStartQuantity(jaxbElement.getStartQuantity().intValue());
        }
        
        modelioElement.setIsForCompensation(jaxbElement.isIsForCompensation());
        
        modelioElement.setTriggeredByEvent(jaxbElement.isTriggeredByEvent());
        
        modelioElement.setIsForCompensation(jaxbElement.isIsForCompensation());
        
        if (jaxbElement.getOrdering() != null) {
            if (jaxbElement.getOrdering() == TAdHocOrdering.PARALLEL) {
                modelioElement.setOrdering(AdHocOrdering.PARALLELORDERING);
            } else if (jaxbElement.getOrdering() == TAdHocOrdering.SEQUENTIAL) {
                modelioElement.setOrdering(AdHocOrdering.SEQUENTIALORDERING);
            }
        }
        
        modelioElement.setCancelRemainingInstances(jaxbElement.isCancelRemainingInstances());
        
        if (jaxbElement.getCompletionCondition() != null) {
            String condition = "";
            for (Serializable val : jaxbElement.getCompletionCondition().getContent()) {
                condition = condition + val.toString() + "";
            }
            modelioElement.setCompletionCondition(condition);
        }
        
        // Default Flow
        if (jaxbElement.getDefault() != null && jaxbElement.getDefault() instanceof TSequenceFlow) {
            BpmnSequenceFlow flow = (BpmnSequenceFlow) this.elementsMap.get(((TSequenceFlow) jaxbElement.getDefault()).getId());
            if (flow != null) {
                modelioElement.setDefaultFlow(flow);
            }
        }
        return modelioElement;
    }

    @objid ("9628b863-94e0-4523-b6b9-dfa8cd8c1710")
    @Override
    public TAdHocSubProcess createJaxbElement(Object context, BpmnAdHocSubProcess modelioElement) {
        // Create JaxbElement
        TAdHocSubProcess jaxTask = new TAdHocSubProcess();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if (context instanceof TProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess) context).getFlowElement();
            jaxContent.add(factory.createAdHocSubProcess(jaxTask));
        } else if (context instanceof TSubProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess) context).getFlowElement();
            jaxContent.add(factory.createAdHocSubProcess(jaxTask));
        }
        
        jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxTask;
    }

    @objid ("f0bc8998-0f94-4bf5-86c5-f7d6103d1708")
    @Override
    public TAdHocSubProcess updateJaxbElement(Object context, TAdHocSubProcess jaxTask, BpmnAdHocSubProcess modelioElement) {
        jaxTask.setName(modelioElement.getName());
        
        if (modelioElement.getCompletionQuantity() != 0) {
            jaxTask.setCompletionQuantity(BigInteger.valueOf(modelioElement.getCompletionQuantity()));
        }
        
        if (modelioElement.getStartQuantity() != 0) {
            jaxTask.setStartQuantity(BigInteger.valueOf(modelioElement.getStartQuantity()));
        }
        
        jaxTask.setIsForCompensation(modelioElement.isIsForCompensation());
        
        jaxTask.setTriggeredByEvent(modelioElement.isTriggeredByEvent());
        
        if (modelioElement.getOrdering() != null) {
            if (modelioElement.getOrdering() == AdHocOrdering.PARALLELORDERING) {
                jaxTask.setOrdering(TAdHocOrdering.PARALLEL);
            } else if (modelioElement.getOrdering() == AdHocOrdering.SEQUENTIALORDERING) {
                jaxTask.setOrdering(TAdHocOrdering.SEQUENTIAL);
            }
        }
        
        jaxTask.setCancelRemainingInstances(modelioElement.isCancelRemainingInstances());
        
        if (!"".equals(modelioElement.getCompletionCondition())) {
            TExpression expression = new TExpression();
            expression.getContent().add(modelioElement.getCompletionCondition());
            jaxTask.setCompletionCondition(expression);
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

    @objid ("0bcd2566-b87d-4e4a-9fe3-d5c042f33882")
    @Override
    public BpmnAdHocSubProcess findUMLElementById(TAdHocSubProcess jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnAdHocSubProcess.class, jaxbElement.getId());
    }

}
