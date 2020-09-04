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

package org.modelio.bpmnxml.nodes.production.eventdefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TCatchEvent;
import org.modelio.bpmnxml.model.TEvent;
import org.modelio.bpmnxml.model.TEventDefinition;
import org.modelio.bpmnxml.model.TSignalEventDefinition;
import org.modelio.bpmnxml.model.TThrowEvent;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnSignalEventDefinition;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("78874c40-8035-48e8-bedc-2117e460a0eb")
public class SignalEventDefinitionNode implements IProductionNode<BpmnSignalEventDefinition,TSignalEventDefinition> {
    @objid ("894e199d-d7cb-4e71-aa8d-2063a9a041ad")
    private Map<String, Object> elementsMap;

    @objid ("1fd25856-84a0-4294-bdc6-6d15049b41d4")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("59fcf685-7e3a-448f-8046-ef7c54248f20")
    @Override
    public BpmnSignalEventDefinition findUMLElement(MObject context, TSignalEventDefinition jaxbElement) {
        if (context instanceof BpmnEvent) {
            for (BpmnEventDefinition eventDefinition : ((BpmnEvent) context).getEventDefinitions()) {
                if (eventDefinition instanceof BpmnSignalEventDefinition) {
                    return (BpmnSignalEventDefinition) eventDefinition;
                }
            }
        }
        return null;
    }

    @objid ("8df4b505-2617-4e53-a277-62db9b480da8")
    @Override
    public BpmnSignalEventDefinition createUMLElement(MObject context, TSignalEventDefinition jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnSignalEventDefinition.class, context, "EventDefinitions", jaxbElement.getId());
        } else {
            return factory.create(BpmnSignalEventDefinition.class, context, "EventDefinitions");
        }
    }

    @objid ("6b076cab-8947-4a30-b9f6-020c8c105fad")
    @Override
    public BpmnSignalEventDefinition updateUMLElement(MObject context, BpmnSignalEventDefinition modelioElement, TSignalEventDefinition jaxbElement) {
        // NA
        return modelioElement;
    }

    @objid ("06410780-d4de-4a4d-b847-7fcbf74c32b4")
    @Override
    public TSignalEventDefinition createJaxbElement(Object context, BpmnSignalEventDefinition modelioElement) {
        TEvent jaxEvent = (TEvent) context;
        
        // Create JaxbElement
        TSignalEventDefinition jaxEventDefinition = new TSignalEventDefinition();
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
        jaxContext.add(factory.createSignalEventDefinition(jaxEventDefinition));
        jaxEventDefinition.setId(IDUtils.getJaxbId(context, modelioElement));
        return jaxEventDefinition;
    }

    @objid ("fe75817d-41ae-4675-8a4f-1167ce32870b")
    @Override
    public TSignalEventDefinition updateJaxbElement(Object context, TSignalEventDefinition jaxEventDefinition, BpmnSignalEventDefinition modelioElement) {
        // NA
        return jaxEventDefinition;
    }

    @objid ("504fb538-5ebb-41c8-b686-995047aa0bc5")
    @Override
    public BpmnSignalEventDefinition findUMLElementById(TSignalEventDefinition jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnSignalEventDefinition.class, jaxbElement.getId());
    }

}
