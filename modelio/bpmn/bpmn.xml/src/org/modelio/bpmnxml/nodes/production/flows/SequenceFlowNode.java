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

package org.modelio.bpmnxml.nodes.production.flows;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TBaseElement;
import org.modelio.bpmnxml.model.TExpression;
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TFlowNode;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TSequenceFlow;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("e670c6bd-cddb-4879-b2af-172437fa69e0")
public class SequenceFlowNode implements IProductionNode<BpmnSequenceFlow,TSequenceFlow> {
    @objid ("92bccb3c-77a3-4754-ad05-b38158567d47")
    private Map<String, Object> elementsMap;

    @objid ("7070b2d5-00b3-4500-bd82-ed3ca677eb7e")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("0426ee08-2197-4e91-889c-f2e990a38bfa")
    @Override
    public BpmnSequenceFlow findUMLElement(MObject context, TSequenceFlow jaxbElement) {
        Object from = this.elementsMap.get(((TBaseElement) jaxbElement.getSourceRef()).getId());
        Object to = this.elementsMap.get(((TBaseElement) jaxbElement.getTargetRef()).getId());
        
        if (from != null && to != null) {
            BpmnFlowNode fromM = (BpmnFlowNode) from;
            for (BpmnSequenceFlow out : fromM.getOutgoing()) {
                if (out.getTargetRef().equals(to)) {
                    return out;
                }
            }
        
        }
        return null;
    }

    @objid ("ef46102a-341c-47cc-8931-16a90c18dee8")
    @Override
    public BpmnSequenceFlow createUMLElement(MObject context, TSequenceFlow jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId) {
            return factory.createWithId(BpmnSequenceFlow.class, context, "FlowElement", jaxbElement.getId());
        } else {
            return factory.create(BpmnSequenceFlow.class, context, "FlowElement");
        }
    }

    @objid ("e3c7371c-8da1-4bf7-a0bf-c24f61af389d")
    @Override
    public BpmnSequenceFlow updateUMLElement(MObject context, BpmnSequenceFlow modelioElement, TSequenceFlow jaxbElement) {
        if(jaxbElement.getSourceRef() != null && jaxbElement.getTargetRef() != null ){
            Object from = this.elementsMap.get(((TBaseElement) jaxbElement.getSourceRef()).getId());
            Object to = this.elementsMap.get(((TBaseElement) jaxbElement.getTargetRef()).getId());
            if (from != null && to != null) {
                modelioElement.setSourceRef((BpmnFlowNode) from);
                modelioElement.setTargetRef((BpmnFlowNode) to);
            }
        }
        
        if (jaxbElement.getName() != null) {
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        
        }
        
        // Update Properties
        if (jaxbElement.getConditionExpression() != null) {
            String condition = "";
            for (Serializable val : jaxbElement.getConditionExpression().getContent()) {
                condition = condition + val.toString() + "";
            }
            modelioElement.setConditionExpression(condition);
        }
        
        if (jaxbElement.isIsImmediate() != null)
            modelioElement.setIsImmediate(jaxbElement.isIsImmediate());
        return modelioElement;
    }

    @objid ("12ec59d1-87c7-434c-a780-98cccc4de179")
    @Override
    public TSequenceFlow createJaxbElement(Object context, BpmnSequenceFlow modelioElement) {
        if (modelioElement.getSourceRef() != null && modelioElement.getTargetRef() != null) {
        
            // Create JaxbElement
            TSequenceFlow jaxSequenceFlow = new TSequenceFlow();
        
            // Add to context
            ObjectFactory factory = new ObjectFactory();
            List<JAXBElement<? extends TFlowElement>> jaxContent = null;
            if (context instanceof TProcess) {
                TProcess jaxProcess = (TProcess) context;
                jaxContent = jaxProcess.getFlowElement();
                if (jaxContent == null) {
                    jaxContent = new ArrayList<>();
                }
            } else if (context instanceof TSubProcess) {
                TSubProcess jaxProcess = (TSubProcess) context;
                jaxContent = jaxProcess.getFlowElement();
                if (jaxContent == null) {
                    jaxContent = new ArrayList<>();
                }
            }
        
            if (jaxContent != null) {
                jaxContent.add(factory.createSequenceFlow(jaxSequenceFlow));
            }
        
            // Edit Properties
            jaxSequenceFlow.setId(IDUtils.formatJaxbID(modelioElement));
        
            return jaxSequenceFlow;
        }
        return null;
    }

    @objid ("d219b66c-c1cc-4734-88f5-acea9b840d41")
    @Override
    public TSequenceFlow updateJaxbElement(Object context, TSequenceFlow jaxSequenceFlow, BpmnSequenceFlow modelioElement) {
        if (modelioElement.getSourceRef() != null && modelioElement.getTargetRef() != null) {
            Object jaxInput = this.elementsMap.get(modelioElement.getSourceRef().getUuid());
            Object jaxOutput = this.elementsMap.get(modelioElement.getTargetRef().getUuid());
        
            if (jaxInput != null && jaxOutput != null) {
                jaxSequenceFlow.setName(modelioElement.getName());
                jaxSequenceFlow.setSourceRef(jaxInput);
                jaxSequenceFlow.setTargetRef(jaxOutput);
        
                if (!"".equals(modelioElement.getConditionExpression()) /*&& !modelioElement.getConditionExpression().equals(modelioElement.getName())*/) {
                    TExpression expr = new TExpression();
                    expr.getContent().add(modelioElement.getConditionExpression());
                    jaxSequenceFlow.setConditionExpression(expr);
                }
        
                if (jaxInput instanceof TFlowNode) {
                    ((TFlowNode) jaxInput).getOutgoing().add(new QName(IDUtils.getJaxbId(null, jaxSequenceFlow)));
                }
        
                if (jaxOutput instanceof TFlowNode) {
                    ((TFlowNode) jaxOutput).getIncoming().add(new QName(IDUtils.getJaxbId(null, jaxSequenceFlow)));
                }
            }
        }
        return jaxSequenceFlow;
    }

    @objid ("0c9d7340-4a76-442f-ab4e-f67799283406")
    @Override
    public BpmnSequenceFlow findUMLElementById(TSequenceFlow jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnSequenceFlow.class, jaxbElement.getId());
    }

}
