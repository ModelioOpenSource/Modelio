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

package org.modelio.bpmnxml.nodes.production.gateways;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TComplexGateway;
import org.modelio.bpmnxml.model.TExpression;
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TGatewayDirection;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TSequenceFlow;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.gateways.BpmnComplexGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnGatewayDirection;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("b0728695-d82a-4af6-9cb3-60cd1d9b9924")
public class ComplexGatewayNode implements IProductionNode<BpmnComplexGateway,TComplexGateway> {
    @objid ("dcfbf182-753e-43d4-81b2-6fcd8e992a75")
    private Map<String, Object> elementsMap;

    @objid ("79c7d385-f6a1-4b13-b01e-0f191cac1cb8")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("c32d52ea-5604-4993-9435-dbc5df24192f")
    @Override
    public BpmnComplexGateway findUMLElement(MObject context, TComplexGateway jaxbElement) {
        if (context instanceof BpmnProcess) {
            for (BpmnFlowElement flowElement : ((BpmnProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnComplexGateway && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnComplexGateway) flowElement;
                }
            }
        } else if (context instanceof BpmnSubProcess) {
            for (BpmnFlowElement flowElement : ((BpmnSubProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnComplexGateway && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnComplexGateway) flowElement;
                }
            }
        }
        return null;
    }

    @objid ("ae7826b0-41f9-4720-9ef9-16e5c742c1d1")
    @Override
    public BpmnComplexGateway createUMLElement(MObject context, TComplexGateway jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId && jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {
            return factory.createWithId(BpmnComplexGateway.class, context, jaxbElement.getId());
        } else {
            return factory.create(BpmnComplexGateway.class, context);
        }
    }

    @objid ("118d3f2f-723e-41f1-bf19-1ef0c7f332af")
    @Override
    public BpmnComplexGateway updateUMLElement(MObject context, BpmnComplexGateway modelioElement, TComplexGateway jaxbElement) {
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
        
        TGatewayDirection direction = jaxbElement.getGatewayDirection();
        if (direction != null) {
            if (direction == TGatewayDirection.CONVERGING) {
                modelioElement.setGatewayDirection(BpmnGatewayDirection.CONVERGINGDIRECTION);
            } else if (direction == TGatewayDirection.DIVERGING) {
                modelioElement.setGatewayDirection(BpmnGatewayDirection.DIVERGINGDIRECTION);
            } else if (direction == TGatewayDirection.MIXED) {
                modelioElement.setGatewayDirection(BpmnGatewayDirection.MIXEDDIRECTION);
            } else if (direction == TGatewayDirection.UNSPECIFIED) {
                modelioElement.setGatewayDirection(BpmnGatewayDirection.UNSPECIFIEDDIRECTION);
            }
        }
        
        if (jaxbElement.getActivationCondition() != null) {
            String condition = "";
            for (Serializable val : jaxbElement.getActivationCondition().getContent()) {
                condition = condition + val.toString() + "";
            }
            modelioElement.setActivationExpression(condition);
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

    @objid ("ff4fa2b8-37f1-46d4-8800-92942962cc2f")
    @Override
    public TComplexGateway createJaxbElement(Object context, BpmnComplexGateway modelioElement) {
        // Create JaxbElement
        TComplexGateway jaxTask = new TComplexGateway();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if (context instanceof TProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess) context).getFlowElement();
            jaxContent.add(factory.createComplexGateway(jaxTask));
        } else if (context instanceof TSubProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess) context).getFlowElement();
            jaxContent.add(factory.createComplexGateway(jaxTask));
        }
        
        jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxTask;
    }

    @objid ("c7013d9c-145f-4731-a5e7-c2994d4ae8d9")
    @Override
    public TComplexGateway updateJaxbElement(Object context, TComplexGateway jaxbElement, BpmnComplexGateway modelioElement) {
        jaxbElement.setName(modelioElement.getName());
        
        BpmnGatewayDirection direction = modelioElement.getGatewayDirection();
        if (direction != null) {
            if (direction == BpmnGatewayDirection.CONVERGINGDIRECTION) {
                jaxbElement.setGatewayDirection(TGatewayDirection.CONVERGING);
            } else if (direction == BpmnGatewayDirection.DIVERGINGDIRECTION) {
                jaxbElement.setGatewayDirection(TGatewayDirection.DIVERGING);
            } else if (direction == BpmnGatewayDirection.MIXEDDIRECTION) {
                jaxbElement.setGatewayDirection(TGatewayDirection.MIXED);
            } else if (direction == BpmnGatewayDirection.UNSPECIFIEDDIRECTION) {
                jaxbElement.setGatewayDirection(TGatewayDirection.UNSPECIFIED);
            }
        }
        
        if (!"".equals(modelioElement.getActivationExpression())) {
            TExpression expression = new TExpression();
            expression.getContent().add(modelioElement.getActivationExpression());
            jaxbElement.setActivationCondition(expression);
        }
        
        // Default Flow
        if (modelioElement.getDefaultFlow() != null) {
            Object target = this.elementsMap.get(modelioElement.getDefaultFlow().getUuid());
            if (target != null) {
                jaxbElement.setDefault(target);
            }
        }
        return jaxbElement;
    }

    @objid ("ecb1f3a0-9b8f-41d3-869a-224369b17d8e")
    @Override
    public BpmnComplexGateway findUMLElementById(TComplexGateway jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnComplexGateway.class, jaxbElement.getId());
    }

}
