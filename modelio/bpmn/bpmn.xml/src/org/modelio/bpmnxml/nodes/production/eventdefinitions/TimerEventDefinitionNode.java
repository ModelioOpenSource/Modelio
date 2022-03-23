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
import org.modelio.bpmnxml.model.TEvent;
import org.modelio.bpmnxml.model.TEventDefinition;
import org.modelio.bpmnxml.model.TExpression;
import org.modelio.bpmnxml.model.TThrowEvent;
import org.modelio.bpmnxml.model.TTimerEventDefinition;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnTimerEventDefinition;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("b5e85c07-49c8-4d60-893a-2b67acd66f1d")
public class TimerEventDefinitionNode implements IProductionNode<BpmnTimerEventDefinition, TTimerEventDefinition> {
    @objid ("5d1b95bc-bfa7-4c15-8a09-ba27dbd3063b")
    private Map<String, Object> elementsMap;

    @objid ("5bf6c1af-3868-4804-9039-ceb830882daa")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("c2109866-4f97-4a5d-9bec-88a5a88be313")
    @Override
    public BpmnTimerEventDefinition findUMLElement(MObject context, TTimerEventDefinition jaxbElement) {
        if (context instanceof BpmnEvent) {
            for (BpmnEventDefinition eventDefinition : ((BpmnEvent) context).getEventDefinitions()) {
                if (eventDefinition instanceof BpmnTimerEventDefinition) {
                    return (BpmnTimerEventDefinition) eventDefinition;
                }
            }
        }
        return null;
    }

    @objid ("dd1534c9-7a65-4d78-ad08-25ee92dc7e1c")
    @Override
    public BpmnTimerEventDefinition createUMLElement(MObject context, TTimerEventDefinition jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnTimerEventDefinition.class, context, "EventDefinitions", jaxbElement.getId());
        } else {
            return factory.create(BpmnTimerEventDefinition.class, context, "EventDefinitions");
        }
        
    }

    @objid ("75ed98cf-df7e-4e1f-9ed2-a4ecef67bab0")
    @Override
    public BpmnTimerEventDefinition updateUMLElement(MObject context, BpmnTimerEventDefinition modelioElement, TTimerEventDefinition jaxbElement) {
        if (jaxbElement.getTimeCycle() != null) {
            String condition = "";
            for (Serializable val : jaxbElement.getTimeCycle().getContent()) {
                condition = condition + val.toString() + "";
            }
            modelioElement.setTimeCycle(condition);
        }
        
        if (jaxbElement.getTimeDate() != null) {
            String condition = "";
            for (Serializable val : jaxbElement.getTimeDate().getContent()) {
                condition = condition + val.toString() + "";
            }
            modelioElement.setTimeDate(condition);
        }
        
        if (jaxbElement.getTimeDuration() != null) {
            String condition = "";
            for (Serializable val : jaxbElement.getTimeDuration().getContent()) {
                condition = condition + val.toString() + "";
            }
            modelioElement.setTimeDuration(condition);
        }
        return modelioElement;
    }

    @objid ("489325b4-0a1e-4580-8c94-b25d3452ce60")
    @Override
    public TTimerEventDefinition createJaxbElement(Object context, BpmnTimerEventDefinition modelioElement) {
        TEvent jaxEvent = (TEvent) context;
        
        // Create JaxbElement
        TTimerEventDefinition jaxEventDefinition = new TTimerEventDefinition();
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
        jaxContext.add(factory.createTimerEventDefinition(jaxEventDefinition));
        
        // Edit Properties
        return jaxEventDefinition;
    }

    @objid ("abf1f2c0-f61c-4326-a84c-d670688ac574")
    @Override
    public TTimerEventDefinition updateJaxbElement(Object context, TTimerEventDefinition jaxEventDefinition, BpmnTimerEventDefinition modelioElement) {
        if (!"".equals(modelioElement.getTimeCycle())) {
            TExpression exp = new TExpression();
            exp.getContent().add(modelioElement.getTimeCycle());
            jaxEventDefinition.setTimeCycle(exp);
        }
        
        if (!"".equals(modelioElement.getTimeDate())) {
            TExpression exp = new TExpression();
            exp.getContent().add(modelioElement.getTimeDate());
            jaxEventDefinition.setTimeDate(exp);
        }
        
        if (!"".equals(modelioElement.getTimeDuration())) {
            TExpression exp = new TExpression();
            exp.getContent().add(modelioElement.getTimeDuration());
            jaxEventDefinition.setTimeDate(exp);
        }
        return jaxEventDefinition;
    }

    @objid ("dcabcd56-146f-409a-9ce6-305fb812885c")
    @Override
    public BpmnTimerEventDefinition findUMLElementById(TTimerEventDefinition jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnTimerEventDefinition.class, jaxbElement.getId());
    }

}
