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
import jakarta.xml.bind.JAXBElement;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TCatchEvent;
import org.modelio.bpmnxml.model.TErrorEventDefinition;
import org.modelio.bpmnxml.model.TEvent;
import org.modelio.bpmnxml.model.TEventDefinition;
import org.modelio.bpmnxml.model.TThrowEvent;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.metamodel.bpmn.events.BpmnErrorEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("d522d5bc-9e83-4e64-8cb5-8248a63c811e")
public class ErrorEventDefinitionNode implements IProductionNode<BpmnErrorEventDefinition, TErrorEventDefinition> {
    @objid ("f60d7dcf-ba2d-466a-9b6a-5370cea1caba")
    private Map<String, Object> elementsMap;

    @objid ("bbaa2bf2-dd61-4ae2-9f83-bc40c9f96251")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("fcafc327-2643-4832-a389-2d3b6cc2f6eb")
    @Override
    public BpmnErrorEventDefinition findUMLElement(MObject context, TErrorEventDefinition jaxbElement) {
        if (context instanceof BpmnEvent) {
            for (BpmnEventDefinition eventDefinition : ((BpmnEvent) context).getEventDefinitions()) {
                if (eventDefinition instanceof BpmnErrorEventDefinition) {
                    return (BpmnErrorEventDefinition) eventDefinition;
                }
            }
        }
        return null;
    }

    @objid ("05cdaf06-9969-4eea-a0a4-a9904c1b6178")
    @Override
    public BpmnErrorEventDefinition createUMLElement(MObject context, TErrorEventDefinition jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnErrorEventDefinition.class, context, "EventDefinitions", jaxbElement.getId());
        } else {
            return factory.create(BpmnErrorEventDefinition.class, context, "EventDefinitions");
        }
        
    }

    @objid ("1b6889ca-a49b-405c-9be3-5bb8ed38a5a9")
    @Override
    public BpmnErrorEventDefinition updateUMLElement(MObject context, BpmnErrorEventDefinition modelioElement, TErrorEventDefinition jaxbElement) {
        if (jaxbElement.getErrorRef() != null) {
            // TODO : Link with error ( not suported in MOdelio )
        }
        return modelioElement;
    }

    @objid ("e4df7b54-baf1-4beb-9b82-4a28253b859f")
    @Override
    public TErrorEventDefinition createJaxbElement(Object context, BpmnErrorEventDefinition modelioElement) {
        TEvent jaxEvent = (TEvent) context;
        
        // Create JaxbElement
        TErrorEventDefinition jaxEventDefinition = new TErrorEventDefinition();
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
        jaxContext.add(factory.createErrorEventDefinition(jaxEventDefinition));
        
        jaxEventDefinition.setId(IDUtils.getJaxbId(context, modelioElement));
        return jaxEventDefinition;
    }

    @objid ("4c9010a9-00ac-4701-b389-b1d3ca35ac48")
    @Override
    public TErrorEventDefinition updateJaxbElement(Object context, TErrorEventDefinition jaxbElement, BpmnErrorEventDefinition modelioElement) {
        // NA
        return jaxbElement;
    }

    @objid ("0c4755a6-b7fb-432f-966d-928f92521ceb")
    @Override
    public BpmnErrorEventDefinition findUMLElementById(TErrorEventDefinition jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnErrorEventDefinition.class, jaxbElement.getId());
    }

}
