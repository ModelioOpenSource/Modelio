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
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TGatewayDirection;
import org.modelio.bpmnxml.model.TParallelGateway;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.gateways.BpmnGatewayDirection;
import org.modelio.metamodel.bpmn.gateways.BpmnParallelGateway;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("3c4d064d-e6ef-47cb-83c9-aa10a656289f")
public class ParallelGatewayNode implements IProductionNode<BpmnParallelGateway, TParallelGateway> {
    @objid ("48cded22-0781-41a7-97ac-7f7a38e47f2b")
    private Map<String, Object> elementsMap;

    @objid ("83f93e86-1768-46e3-8572-bfd64948a0f8")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("1292e89f-d1b0-426a-a12c-2da947b6d500")
    @Override
    public BpmnParallelGateway findUMLElement(MObject context, TParallelGateway jaxbElement) {
        if (context instanceof BpmnProcess) {
            for (BpmnFlowElement flowElement : ((BpmnProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnParallelGateway && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnParallelGateway) flowElement;
                }
            }
        } else if (context instanceof BpmnSubProcess) {
            for (BpmnFlowElement flowElement : ((BpmnSubProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnParallelGateway && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnParallelGateway) flowElement;
                }
            }
        }
        return null;
    }

    @objid ("a359d5c3-fbe8-4b9e-ac9e-57f0b62e3db9")
    @Override
    public BpmnParallelGateway createUMLElement(MObject context, TParallelGateway jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId && jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {
            return factory.createWithId(BpmnParallelGateway.class, context, jaxbElement.getId());
        } else {
            return factory.create(BpmnParallelGateway.class, context);
        }
        
    }

    @objid ("091db3b8-33fd-4372-bc14-c39082d0850d")
    @Override
    public BpmnParallelGateway updateUMLElement(MObject context, BpmnParallelGateway modelioElement, TParallelGateway jaxbElement) {
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
        return modelioElement;
    }

    @objid ("5873533c-c746-4ae9-a7cb-e38b059c232a")
    @Override
    public TParallelGateway createJaxbElement(Object context, BpmnParallelGateway modelioElement) {
        // Create JaxbElement
        TParallelGateway jaxTask = new TParallelGateway();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if (context instanceof TProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess) context).getFlowElement();
            jaxContent.add(factory.createParallelGateway(jaxTask));
        } else if (context instanceof TSubProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess) context).getFlowElement();
            jaxContent.add(factory.createParallelGateway(jaxTask));
        }
        
        jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxTask;
    }

    @objid ("4eef350b-d3b1-4ae8-a752-91c95f5c7449")
    @Override
    public TParallelGateway updateJaxbElement(Object context, TParallelGateway jaxbElement, BpmnParallelGateway modelioElement) {
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
        return jaxbElement;
    }

    @objid ("843e38bc-095a-4649-a2be-720c9c20cb98")
    @Override
    public BpmnParallelGateway findUMLElementById(TParallelGateway jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnParallelGateway.class, jaxbElement.getId());
    }

}
