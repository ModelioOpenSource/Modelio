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

package org.modelio.bpmnxml.nodes.production.eventdefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TBaseElement;
import org.modelio.bpmnxml.model.TCatchEvent;
import org.modelio.bpmnxml.model.TEvent;
import org.modelio.bpmnxml.model.TEventDefinition;
import org.modelio.bpmnxml.model.TMessage;
import org.modelio.bpmnxml.model.TMessageEventDefinition;
import org.modelio.bpmnxml.model.TOperation;
import org.modelio.bpmnxml.model.TThrowEvent;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnMessageEventDefinition;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("deae660c-7b89-410a-b0f2-a914bf300021")
public class MessageEventDefinitionNode implements IProductionNode<BpmnMessageEventDefinition,TMessageEventDefinition> {
    @objid ("d845294a-7e5b-4c75-8383-870cd28dea8f")
    private Map<String, Object> elementsMap;

    @objid ("dfa59501-87e0-497d-a105-9ee11214c1a2")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("ee9a85df-d094-4b06-a6be-55b137d77230")
    @Override
    public BpmnMessageEventDefinition findUMLElement(MObject context, TMessageEventDefinition jaxbElement) {
        if (context instanceof BpmnEvent) {
            for (BpmnEventDefinition eventDefinition : ((BpmnEvent) context).getEventDefinitions()) {
                if (eventDefinition instanceof BpmnMessageEventDefinition) {
                    return (BpmnMessageEventDefinition) eventDefinition;
                }
            }
        }
        return null;
    }

    @objid ("feb49180-f524-48fc-8de5-6514265e8c5d")
    @Override
    public BpmnMessageEventDefinition createUMLElement(MObject context, TMessageEventDefinition jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnMessageEventDefinition.class, context, "EventDefinitions", jaxbElement.getId());
        } else {
            return factory.create(BpmnMessageEventDefinition.class, context, "EventDefinitions");
        }
    }

    @objid ("df9b0930-c640-4f3c-85ff-f7863f4e63d6")
    @Override
    public BpmnMessageEventDefinition updateUMLElement(MObject context, BpmnMessageEventDefinition modelioElement, TMessageEventDefinition jaxbElement) {
        if (jaxbElement.getMessageRef() != null) {
            BpmnMessage modelioMessage = (BpmnMessage) this.elementsMap.get(jaxbElement.getMessageRef().getLocalPart());
            if (modelioMessage != null) {
                modelioElement.setMessageRef(modelioMessage);
            }
        }
        
        if (jaxbElement.getOperationRef() != null) {
            BpmnOperation modelioOperation = (BpmnOperation) this.elementsMap.get(jaxbElement.getOperationRef().getLocalPart());
            if (modelioOperation != null) {
                modelioElement.getOperationRef().add(modelioOperation);
            }
        }
        return modelioElement;
    }

    @objid ("59ecaa61-7d81-4ce9-96b6-58b1dbf4fda6")
    @Override
    public TMessageEventDefinition createJaxbElement(Object context, BpmnMessageEventDefinition modelioElement) {
        TEvent jaxEvent = (TEvent) context;
        
        // Create JaxbElement
        TMessageEventDefinition jaxEventDefinition = new TMessageEventDefinition();
        this.elementsMap.put(modelioElement.getUuid(), jaxEventDefinition);
        
        // Add to context
        List<JAXBElement<? extends TEventDefinition>> jaxContext = null;
        if (jaxEvent instanceof TThrowEvent) {
            jaxContext = ((TThrowEvent) jaxEvent).getEventDefinition();
        } else if (jaxEvent instanceof TCatchEvent) {
            jaxContext = ((TCatchEvent) jaxEvent).getEventDefinition();
        }
        if (jaxContext == null) {
            jaxContext = new ArrayList<>();
        }
        ObjectFactory factory = new ObjectFactory();
        jaxContext.add(factory.createMessageEventDefinition(jaxEventDefinition));
        
        jaxEventDefinition.setId(IDUtils.getJaxbId(context, modelioElement));
        return jaxEventDefinition;
    }

    @objid ("42b0d0bc-6336-490b-a395-e70c3b2725b1")
    @Override
    public TMessageEventDefinition updateJaxbElement(Object context, TMessageEventDefinition jaxEventDefinition, BpmnMessageEventDefinition modelioElement) {
        // Edit Properties
        
        if (modelioElement.getMessageRef() != null) {
            TMessage jaxMessage = (TMessage) this.elementsMap.get(modelioElement.getMessageRef().getUuid());
            if (jaxMessage != null) {
                jaxEventDefinition.setMessageRef(new QName(jaxMessage.getId()));
            }
        }
        
        if (modelioElement.getOperationRef() != null) {
            TBaseElement jaxOperation = (TBaseElement) this.elementsMap.get(modelioElement.getUuid());
            if (jaxOperation instanceof TOperation) {
                jaxEventDefinition.setOperationRef(new QName(jaxOperation.getId()));
            }
        }
        return jaxEventDefinition;
    }

    @objid ("79aac5ce-c1f6-442c-81fc-9013383e90f5")
    @Override
    public BpmnMessageEventDefinition findUMLElementById(TMessageEventDefinition jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnMessageEventDefinition.class, jaxbElement.getId());
    }

}
