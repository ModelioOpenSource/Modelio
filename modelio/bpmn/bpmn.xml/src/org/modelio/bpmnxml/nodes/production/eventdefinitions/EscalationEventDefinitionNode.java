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
import javax.xml.bind.JAXBElement;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TCatchEvent;
import org.modelio.bpmnxml.model.TEscalationEventDefinition;
import org.modelio.bpmnxml.model.TEvent;
import org.modelio.bpmnxml.model.TEventDefinition;
import org.modelio.bpmnxml.model.TThrowEvent;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.metamodel.bpmn.events.BpmnEscalationEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("1a15bd75-f2ed-4dc0-9fa8-005524847b32")
public class EscalationEventDefinitionNode implements IProductionNode<BpmnEscalationEventDefinition,TEscalationEventDefinition> {
    @objid ("5a62e71f-6ed4-41c9-a69b-6c2d55399085")
    private Map<String, Object> elementsMap;

    @objid ("c5437766-024f-40dd-97cc-d1b03144a696")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("1fe9a568-0db5-4cc2-bc4f-a34f5710eff9")
    @Override
    public BpmnEscalationEventDefinition findUMLElement(MObject context, TEscalationEventDefinition jaxbElement) {
        if (context instanceof BpmnEvent) {
            for (BpmnEventDefinition eventDefinition : ((BpmnEvent) context).getEventDefinitions()) {
                if (eventDefinition instanceof BpmnEscalationEventDefinition) {
                    return (BpmnEscalationEventDefinition) eventDefinition;
                }
            }
        }
        return null;
    }

    @objid ("c7f81812-6ff6-443b-82ce-e5114a7ba526")
    @Override
    public BpmnEscalationEventDefinition createUMLElement(MObject context, TEscalationEventDefinition jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnEscalationEventDefinition.class, context, "EventDefinitions", jaxbElement.getId());
        } else {
            return factory.create(BpmnEscalationEventDefinition.class, context, "EventDefinitions");
        }
    }

    @objid ("d1dafc45-3b10-4b74-9ca1-3c304d953ad3")
    @Override
    public BpmnEscalationEventDefinition updateUMLElement(MObject context, BpmnEscalationEventDefinition modelioElement, TEscalationEventDefinition jaxbElement) {
        if (jaxbElement.getEscalationRef() != null) {
            // TODO EscalationRef not supported in modelio
        }
        return modelioElement;
    }

    @objid ("52e5532d-7865-4a2f-8593-a0750d727863")
    @Override
    public TEscalationEventDefinition createJaxbElement(Object context, BpmnEscalationEventDefinition modelioElement) {
        TEvent jaxEvent = (TEvent) context;
        
        // Create JaxbElement
        TEscalationEventDefinition jaxEventDefinition = new TEscalationEventDefinition();
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
        jaxContext.add(factory.createEscalationEventDefinition(jaxEventDefinition));
        
        jaxEventDefinition.setId(IDUtils.getJaxbId(context, modelioElement));
        return jaxEventDefinition;
    }

    @objid ("c6527d6c-28a5-402c-b1c5-cbd89306146f")
    @Override
    public TEscalationEventDefinition updateJaxbElement(Object context, TEscalationEventDefinition jaxbElement, BpmnEscalationEventDefinition modelioElement) {
        // NA
        return jaxbElement;
    }

    @objid ("4aca687d-d016-4f38-ac46-88aa313d1295")
    @Override
    public BpmnEscalationEventDefinition findUMLElementById(TEscalationEventDefinition jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnEscalationEventDefinition.class, jaxbElement.getId());
    }

}
