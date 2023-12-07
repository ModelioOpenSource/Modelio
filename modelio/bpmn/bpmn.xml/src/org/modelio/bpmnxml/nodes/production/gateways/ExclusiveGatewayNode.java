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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jakarta.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TExclusiveGateway;
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
import org.modelio.metamodel.bpmn.gateways.BpmnExclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnGatewayDirection;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("3f945518-635f-4988-b760-648276805592")
public class ExclusiveGatewayNode implements IProductionNode<BpmnExclusiveGateway, TExclusiveGateway> {
    @objid ("461ebaa5-dfe5-4c8d-8932-a03f7188dd14")
    private Map<String, Object> elementsMap;

    @objid ("e4584a04-19eb-4203-8220-7158430fc337")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("587d7215-550b-431d-822b-07b45560a967")
    @Override
    public BpmnExclusiveGateway findUMLElement(MObject context, TExclusiveGateway jaxbElement) {
        if (context instanceof BpmnProcess) {
            for (BpmnFlowElement flowElement : ((BpmnProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnExclusiveGateway && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnExclusiveGateway) flowElement;
                }
            }
        } else if (context instanceof BpmnSubProcess) {
            for (BpmnFlowElement flowElement : ((BpmnSubProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnExclusiveGateway && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnExclusiveGateway) flowElement;
                }
            }
        }
        return null;
    }

    @objid ("fc9e799d-a466-41c6-964f-9d2d9c000f2c")
    @Override
    public BpmnExclusiveGateway createUMLElement(MObject context, TExclusiveGateway jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId && jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {
            return factory.createWithId(BpmnExclusiveGateway.class, context, jaxbElement.getId());
        } else {
            return factory.create(BpmnExclusiveGateway.class, context);
        }
        
    }

    @objid ("9d12c057-b715-4454-b37f-5505a51ce4d0")
    @Override
    public BpmnExclusiveGateway updateUMLElement(MObject context, BpmnExclusiveGateway modelioElement, TExclusiveGateway jaxbElement) {
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

    @objid ("8c97a7f3-7395-44fc-9478-bab70362b6dd")
    @Override
    public TExclusiveGateway createJaxbElement(Object context, BpmnExclusiveGateway modelioElement) {
        // Create JaxbElement
        TExclusiveGateway jaxTask = new TExclusiveGateway();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if (context instanceof TProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess) context).getFlowElement();
            jaxContent.add(factory.createExclusiveGateway(jaxTask));
        } else if (context instanceof TSubProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess) context).getFlowElement();
            jaxContent.add(factory.createExclusiveGateway(jaxTask));
        }
        
        jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxTask;
    }

    @objid ("8613ebf8-5158-422e-b849-c02cfbe8769c")
    @Override
    public TExclusiveGateway updateJaxbElement(Object context, TExclusiveGateway jaxbElement, BpmnExclusiveGateway modelioElement) {
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

    @objid ("b8b0b5b9-9481-4965-82d1-9554d8adaf27")
    @Override
    public BpmnExclusiveGateway findUMLElementById(TExclusiveGateway jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnExclusiveGateway.class, jaxbElement.getId());
    }

}
