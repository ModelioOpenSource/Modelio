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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.bind.JAXBElement;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TCatchEvent;
import org.modelio.bpmnxml.model.TConditionalEventDefinition;
import org.modelio.bpmnxml.model.TEvent;
import org.modelio.bpmnxml.model.TEventDefinition;
import org.modelio.bpmnxml.model.TExpression;
import org.modelio.bpmnxml.model.TThrowEvent;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.metamodel.bpmn.events.BpmnConditionalEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("208bca14-04de-472d-9960-08658d727228")
public class ConditionalEventDefinitionNode implements IProductionNode<BpmnConditionalEventDefinition, TConditionalEventDefinition> {
    @objid ("8723d226-2e9d-4ba6-b8c9-933ede7f4892")
    private Map<String, Object> elementsMap;

    @objid ("9a3af8f1-73b5-4cfa-b595-3ef7ecc63778")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("eef95eec-8de1-4af1-8f71-e2cd8fb0e775")
    @Override
    public BpmnConditionalEventDefinition findUMLElement(MObject context, TConditionalEventDefinition jaxbElement) {
        if (context instanceof BpmnEvent) {
            for (BpmnEventDefinition eventDefinition : ((BpmnEvent) context).getEventDefinitions()) {
                if (eventDefinition instanceof BpmnConditionalEventDefinition) {
                    return (BpmnConditionalEventDefinition) eventDefinition;
                }
            }
        }
        return null;
    }

    @objid ("062eb33e-cc38-47c0-9dd3-1766e6063d3b")
    @Override
    public BpmnConditionalEventDefinition createUMLElement(MObject context, TConditionalEventDefinition jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnConditionalEventDefinition.class, context, "EventDefinitions", jaxbElement.getId());
        } else {
            return factory.create(BpmnConditionalEventDefinition.class, context, "EventDefinitions");
        }
        
    }

    @objid ("c14559e5-0fa5-4435-835a-91fda56b8b71")
    @Override
    public BpmnConditionalEventDefinition updateUMLElement(MObject context, BpmnConditionalEventDefinition modelioElement, TConditionalEventDefinition jaxbElement) {
        if (jaxbElement.getCondition() != null) {
            String condition = "";
            for (Serializable val : jaxbElement.getCondition().getContent()) {
                condition = condition + val.toString() + "";
            }
            modelioElement.setCondition(condition);
        }
        return modelioElement;
    }

    @objid ("084ddf17-57f3-43e0-9914-77323da8f9c4")
    @Override
    public TConditionalEventDefinition createJaxbElement(Object context, BpmnConditionalEventDefinition modelioElement) {
        TEvent jaxEvent = (TEvent) context;
        
        // Create JaxbElement
        TConditionalEventDefinition jaxEventDefinition = new TConditionalEventDefinition();
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
        jaxContext.add(factory.createConditionalEventDefinition(jaxEventDefinition));
        
        jaxEventDefinition.setId(IDUtils.getJaxbId(context, modelioElement));
        return jaxEventDefinition;
    }

    @objid ("c9167993-7268-4fa3-aafb-a3531e78da66")
    @Override
    public TConditionalEventDefinition updateJaxbElement(Object context, TConditionalEventDefinition jaxEventDefinition, BpmnConditionalEventDefinition modelioElement) {
        if (!"".equals(modelioElement.getCondition())) {
            TExpression exp = new TExpression();
            exp.getContent().add(modelioElement.getCondition());
            jaxEventDefinition.setCondition(exp);
        }
        return jaxEventDefinition;
    }

    @objid ("d249b19d-3fe3-4fce-b04c-f8b8a457a836")
    @Override
    public BpmnConditionalEventDefinition findUMLElementById(TConditionalEventDefinition jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnConditionalEventDefinition.class, jaxbElement.getId());
    }

}
