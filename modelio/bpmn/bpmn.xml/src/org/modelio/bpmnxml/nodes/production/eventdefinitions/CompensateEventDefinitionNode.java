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
import javax.xml.namespace.QName;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TCatchEvent;
import org.modelio.bpmnxml.model.TCompensateEventDefinition;
import org.modelio.bpmnxml.model.TEvent;
import org.modelio.bpmnxml.model.TEventDefinition;
import org.modelio.bpmnxml.model.TThrowEvent;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnCompensateEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("f1df3bd5-590f-459c-8f92-a3e58bac7edf")
public class CompensateEventDefinitionNode implements IProductionNode<BpmnCompensateEventDefinition,TCompensateEventDefinition> {
    @objid ("54de20c5-3427-4689-a691-f9c2d56e8d2e")
    private Map<String, Object> elementsMap;

    @objid ("d70a5f3b-4f47-4ab7-8ba7-25cad6b88bf4")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("b6a30a72-a17c-4773-b4f2-569ed5e30283")
    @Override
    public BpmnCompensateEventDefinition findUMLElement(MObject context, TCompensateEventDefinition jaxbElement) {
        if (context instanceof BpmnEvent) {
            for (BpmnEventDefinition eventDefinition : ((BpmnEvent) context).getEventDefinitions()) {
                if (eventDefinition instanceof BpmnCompensateEventDefinition) {
                    return (BpmnCompensateEventDefinition) eventDefinition;
                }
            }
        }
        return null;
    }

    @objid ("52f5d2c6-fc1c-4c93-bc45-e3846870b8c4")
    @Override
    public BpmnCompensateEventDefinition createUMLElement(MObject context, TCompensateEventDefinition jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnCompensateEventDefinition.class, context, "EventDefinitions", jaxbElement.getId());
        } else {
            return factory.create(BpmnCompensateEventDefinition.class, context, "EventDefinitions");
        }
    }

    @objid ("c2149777-faf5-413f-90b6-ee637e7269bd")
    @Override
    public BpmnCompensateEventDefinition updateUMLElement(MObject context, BpmnCompensateEventDefinition modelioElement, TCompensateEventDefinition jaxbElement) {
        if (jaxbElement.getActivityRef() != null) {
            BpmnActivity ref = (BpmnActivity) this.elementsMap.get(jaxbElement.getActivityRef().getLocalPart());
            if (ref != null) {
                modelioElement.setActivityRef(ref);
            }
        }
        
        if (jaxbElement.isWaitForCompletion() != null) {
            modelioElement.setWaitForCompletion(jaxbElement.isWaitForCompletion().toString());
        }
        return modelioElement;
    }

    @objid ("1c78762c-c073-4615-a6eb-85ee9940f1b0")
    @Override
    public TCompensateEventDefinition createJaxbElement(Object context, BpmnCompensateEventDefinition modelioElement) {
        TEvent jaxEvent = (TEvent) context;
        
        // Create JaxbElement
        TCompensateEventDefinition jaxEventDefinition = new TCompensateEventDefinition();
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
        jaxContext.add(factory.createCompensateEventDefinition(jaxEventDefinition));
        
        jaxEventDefinition.setId(IDUtils.getJaxbId(context, modelioElement));
        
        if (!modelioElement.getWaitForCompletion().equals("")) {
            jaxEventDefinition.setWaitForCompletion(true);
        } else {
            jaxEventDefinition.setWaitForCompletion(false);
        }
        return jaxEventDefinition;
    }

    @objid ("faaf1beb-f2cd-4d04-b931-4237b863b324")
    @Override
    public TCompensateEventDefinition updateJaxbElement(Object context, TCompensateEventDefinition jaxEventDefinition, BpmnCompensateEventDefinition modelioElement) {
        // Edit Properties
        if (modelioElement.getActivityRef() != null) {
            jaxEventDefinition.setActivityRef(new QName(IDUtils.getJaxbId(context, modelioElement.getActivityRef())));
        }
        return jaxEventDefinition;
    }

    @objid ("f4c36613-ea9d-4f95-ab42-0eae248c21f5")
    @Override
    public BpmnCompensateEventDefinition findUMLElementById(TCompensateEventDefinition jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnCompensateEventDefinition.class, jaxbElement.getId());
    }

}
