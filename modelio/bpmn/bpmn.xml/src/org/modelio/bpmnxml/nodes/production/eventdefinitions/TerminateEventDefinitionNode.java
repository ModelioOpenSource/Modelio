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
package org.modelio.bpmnxml.nodes.production.eventdefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.bind.JAXBElement;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TCatchEvent;
import org.modelio.bpmnxml.model.TEvent;
import org.modelio.bpmnxml.model.TEventDefinition;
import org.modelio.bpmnxml.model.TTerminateEventDefinition;
import org.modelio.bpmnxml.model.TThrowEvent;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnTerminateEventDefinition;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("5cb0f7ac-118e-469d-b852-ea3e3afb3253")
public class TerminateEventDefinitionNode implements IProductionNode<BpmnTerminateEventDefinition, TTerminateEventDefinition> {
    @objid ("797c1d5d-cc45-4622-bb54-68a0e1c7d204")
    private Map<String, Object> elementsMap;

    @objid ("d740c450-7bab-48e2-a487-39368cd4e4c9")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("474e6156-4713-42f9-801f-a14eb25718b2")
    @Override
    public BpmnTerminateEventDefinition findUMLElement(MObject context, TTerminateEventDefinition jaxbElement) {
        if (context instanceof BpmnEvent) {
            for (BpmnEventDefinition eventDefinition : ((BpmnEvent) context).getEventDefinitions()) {
                if (eventDefinition instanceof BpmnTerminateEventDefinition) {
                    return (BpmnTerminateEventDefinition) eventDefinition;
                }
            }
        }
        return null;
    }

    @objid ("d4350227-c8a4-4c80-8259-6a33e22a973f")
    @Override
    public BpmnTerminateEventDefinition createUMLElement(MObject context, TTerminateEventDefinition jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnTerminateEventDefinition.class, context, "EventDefinitions", jaxbElement.getId());
        } else {
            return factory.create(BpmnTerminateEventDefinition.class, context, "EventDefinitions");
        }
        
    }

    @objid ("b229e514-0b36-4fdc-bcd8-a8b561259c2f")
    @Override
    public BpmnTerminateEventDefinition updateUMLElement(MObject context, BpmnTerminateEventDefinition modelioElement, TTerminateEventDefinition jaxbElement) {
        // NA
        return modelioElement;
    }

    @objid ("570c22c1-3da2-4263-940a-e7e0a35c8536")
    @Override
    public TTerminateEventDefinition createJaxbElement(Object context, BpmnTerminateEventDefinition modelioElement) {
        TEvent jaxEvent = (TEvent) context;
        
        // Create JaxbElement
        TTerminateEventDefinition jaxEventDefinition = new TTerminateEventDefinition();
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
        jaxContext.add(factory.createTerminateEventDefinition(jaxEventDefinition));
        
        jaxEventDefinition.setId(IDUtils.getJaxbId(context, modelioElement));
        return jaxEventDefinition;
    }

    @objid ("cdf57e45-2955-432e-8ce8-b1b869f67c27")
    @Override
    public TTerminateEventDefinition updateJaxbElement(Object context, TTerminateEventDefinition jaxbElement, BpmnTerminateEventDefinition modelioElement) {
        // NA
        return jaxbElement;
    }

    @objid ("e881c2bd-9e74-435a-8483-6c6f4bb80a17")
    @Override
    public BpmnTerminateEventDefinition findUMLElementById(TTerminateEventDefinition jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnTerminateEventDefinition.class, jaxbElement.getId());
    }

}
