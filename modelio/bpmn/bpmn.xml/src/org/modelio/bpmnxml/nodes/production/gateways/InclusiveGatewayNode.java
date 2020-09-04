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

package org.modelio.bpmnxml.nodes.production.gateways;

import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TGatewayDirection;
import org.modelio.bpmnxml.model.TInclusiveGateway;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TSequenceFlow;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.gateways.BpmnGatewayDirection;
import org.modelio.metamodel.bpmn.gateways.BpmnInclusiveGateway;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("e5791879-5763-41eb-b53c-3a39efc95299")
public class InclusiveGatewayNode implements IProductionNode<BpmnInclusiveGateway,TInclusiveGateway> {
    @objid ("cc934d19-d94b-4046-9aef-cfa31ed43c37")
    private Map<String, Object> elementsMap;

    @objid ("49800105-4de3-4589-a503-a3c10c4a601c")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("c9b33cc6-ec4a-42ff-8003-7ca91a0bdab7")
    @Override
    public BpmnInclusiveGateway findUMLElement(MObject context, TInclusiveGateway jaxbElement) {
        if (context instanceof BpmnProcess) {
            for (BpmnFlowElement flowElement : ((BpmnProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnInclusiveGateway && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnInclusiveGateway) flowElement;
                }
            }
        } else if (context instanceof BpmnSubProcess) {
            for (BpmnFlowElement flowElement : ((BpmnSubProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnInclusiveGateway && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnInclusiveGateway) flowElement;
                }
            }
        }
        return null;
    }

    @objid ("484b1662-af7a-4778-be10-c8a0214a3820")
    @Override
    public BpmnInclusiveGateway createUMLElement(MObject context, TInclusiveGateway jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId && jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {
            return factory.createWithId(BpmnInclusiveGateway.class, context, jaxbElement.getId());
        } else {
            return factory.create(BpmnInclusiveGateway.class, context);
        }
    }

    @objid ("e137625f-c8c0-46f2-b1e1-b9db3152b6e6")
    @Override
    public BpmnInclusiveGateway updateUMLElement(MObject context, BpmnInclusiveGateway modelioElement, TInclusiveGateway jaxbElement) {
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
        
        // Set properties
        if (jaxbElement.getName() != null) {
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
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

    @objid ("3d2cbbec-4a71-40de-94d3-0c2e1a37a879")
    @Override
    public TInclusiveGateway createJaxbElement(Object context, BpmnInclusiveGateway modelioElement) {
        // Create JaxbElement
        TInclusiveGateway jaxTask = new TInclusiveGateway();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if (context instanceof TProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess) context).getFlowElement();
            jaxContent.add(factory.createInclusiveGateway(jaxTask));
        } else if (context instanceof TSubProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess) context).getFlowElement();
            jaxContent.add(factory.createInclusiveGateway(jaxTask));
        }
        
        jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxTask;
    }

    @objid ("d297d450-6093-494a-9d0e-b0555b1c4f0b")
    @Override
    public TInclusiveGateway updateJaxbElement(Object context, TInclusiveGateway jaxbElement, BpmnInclusiveGateway modelioElement) {
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
        
        // Default Flow
        if (modelioElement.getDefaultFlow() != null) {
            Object target = this.elementsMap.get(modelioElement.getDefaultFlow().getUuid());
            if (target != null) {
                jaxbElement.setDefault(target);
            }
        }
        return jaxbElement;
    }

    @objid ("81026a86-6626-4d67-8a6b-3aa68ddf918e")
    @Override
    public BpmnInclusiveGateway findUMLElementById(TInclusiveGateway jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnInclusiveGateway.class, jaxbElement.getId());
    }

}
