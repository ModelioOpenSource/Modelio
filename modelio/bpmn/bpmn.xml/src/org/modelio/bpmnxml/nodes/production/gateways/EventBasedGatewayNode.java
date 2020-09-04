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

package org.modelio.bpmnxml.nodes.production.gateways;

import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TEventBasedGateway;
import org.modelio.bpmnxml.model.TEventBasedGatewayType;
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TGatewayDirection;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGatewayType;
import org.modelio.metamodel.bpmn.gateways.BpmnGatewayDirection;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("febd72b0-bbff-4552-ae6b-b3b84b335489")
public class EventBasedGatewayNode implements IProductionNode<BpmnEventBasedGateway,TEventBasedGateway> {
    @objid ("36090eee-5aa3-4cac-8ee0-2540858d4a6c")
    private Map<String, Object> elementsMap;

    @objid ("ffe6493e-2a17-417b-86d6-208298a01f50")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("438b01e4-9c0b-44ea-87a5-c1cb1a2de1a5")
    @Override
    public BpmnEventBasedGateway findUMLElement(MObject context, TEventBasedGateway jaxbElement) {
        if (context instanceof BpmnProcess) {
            for (BpmnFlowElement flowElement : ((BpmnProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnEventBasedGateway && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnEventBasedGateway) flowElement;
                }
            }
        } else if (context instanceof BpmnSubProcess) {
            for (BpmnFlowElement flowElement : ((BpmnSubProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnEventBasedGateway && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnEventBasedGateway) flowElement;
                }
            }
        }
        return null;
    }

    @objid ("535aba49-9d5c-4b8b-b800-9723dbbf1eb4")
    @Override
    public BpmnEventBasedGateway createUMLElement(MObject context, TEventBasedGateway jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId && jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {
            return factory.createWithId(BpmnEventBasedGateway.class, context, jaxbElement.getId());
        } else {
            return factory.create(BpmnEventBasedGateway.class, context);
        }
    }

    @objid ("abf4bacf-bb91-40f8-a587-c69c0cd73869")
    @Override
    public BpmnEventBasedGateway updateUMLElement(MObject context, BpmnEventBasedGateway modelioElement, TEventBasedGateway jaxbElement) {
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
        
        modelioElement.setInstanciate(jaxbElement.isInstantiate());
        
        if (jaxbElement.getEventGatewayType() != null) {
            TEventBasedGatewayType jaxtype = jaxbElement.getEventGatewayType();
            if (jaxtype == TEventBasedGatewayType.EXCLUSIVE) {
                modelioElement.setEventGatewayType(BpmnEventBasedGatewayType.EXCLUSIVEGATEWAY);
            } else if (jaxtype == TEventBasedGatewayType.PARALLEL) {
                modelioElement.setEventGatewayType(BpmnEventBasedGatewayType.PARALLELGATEWAY);
            }
        }
        return modelioElement;
    }

    @objid ("323a5664-fa5e-40b2-8fb2-84acb4bd3d2a")
    @Override
    public TEventBasedGateway createJaxbElement(Object context, BpmnEventBasedGateway modelioElement) {
        // Create JaxbElement
        TEventBasedGateway jaxTask = new TEventBasedGateway();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if (context instanceof TProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess) context).getFlowElement();
            jaxContent.add(factory.createEventBasedGateway(jaxTask));
        } else if (context instanceof TSubProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess) context).getFlowElement();
            jaxContent.add(factory.createEventBasedGateway(jaxTask));
        }
        
        jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxTask;
    }

    @objid ("ea1963df-cfe0-406e-a5fe-c7810638e215")
    @Override
    public TEventBasedGateway updateJaxbElement(Object context, TEventBasedGateway jaxbElement, BpmnEventBasedGateway modelioElement) {
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
        
        jaxbElement.setInstantiate(modelioElement.isInstanciate());
        
        if (modelioElement.getEventGatewayType() != null) {
            BpmnEventBasedGatewayType type = modelioElement.getEventGatewayType();
            if (type == BpmnEventBasedGatewayType.EXCLUSIVEGATEWAY) {
                jaxbElement.setEventGatewayType(TEventBasedGatewayType.EXCLUSIVE);
            } else if (type == BpmnEventBasedGatewayType.PARALLELGATEWAY) {
                jaxbElement.setEventGatewayType(TEventBasedGatewayType.PARALLEL);
            }
        }
        return jaxbElement;
    }

    @objid ("1423bdb6-8f13-4aa8-8e15-5db0b60785ae")
    @Override
    public BpmnEventBasedGateway findUMLElementById(TEventBasedGateway jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnEventBasedGateway.class, jaxbElement.getId());
    }

}
