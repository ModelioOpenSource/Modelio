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
package org.modelio.bpmnxml.nodes.production.events;

import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.bind.JAXBElement;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TStartEvent;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("0e0c32c4-cd36-4d45-99ff-fc8b567376a6")
public class StartEventNode implements IProductionNode<BpmnStartEvent, TStartEvent> {
    @objid ("182dd5d0-2ca3-446a-9968-512a3bfc3054")
    private Map<String, Object> elementsMap;

    @objid ("082b2a64-8c95-4327-a3b3-8c79a0c14442")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("d27da0c1-2116-4324-83b3-f74e6e8fbdf7")
    @Override
    public BpmnStartEvent findUMLElement(MObject context, TStartEvent jaxbElement) {
        if (context instanceof BpmnProcess) {
            for (BpmnFlowElement flowElement : ((BpmnProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnStartEvent && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnStartEvent) flowElement;
                }
            }
        } else if (context instanceof BpmnSubProcess) {
            for (BpmnFlowElement flowElement : ((BpmnSubProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnStartEvent && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnStartEvent) flowElement;
                }
            }
        }
        return null;
    }

    @objid ("8c15c379-929b-4248-9db1-9bb1af7d923c")
    @Override
    public BpmnStartEvent createUMLElement(MObject context, TStartEvent jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnStartEvent.class, context,jaxbElement.getId());
        } else {
            return factory.create(BpmnStartEvent.class, context);
        }
        
    }

    @objid ("f499c053-5c3f-4aa7-8e63-7e4f0e2ddfe5")
    @Override
    public BpmnStartEvent updateUMLElement(MObject context, BpmnStartEvent modelioElement, TStartEvent jaxbElement) {
        // Set owner
        if (context instanceof BpmnProcess) {
            ((BpmnProcess) context).getFlowElement().add(modelioElement);
        } else if (context instanceof BpmnSubProcess) {
            ((BpmnSubProcess) context).getFlowElement().add(modelioElement);
        }
        
        if (jaxbElement.getName() != null)
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        
        modelioElement.setIsInterrupting(jaxbElement.isIsInterrupting());
        modelioElement.setParallelMultiple(jaxbElement.isParallelMultiple());
        return modelioElement;
    }

    @objid ("86e9220a-c5ae-41ed-86ed-bc0fe4d1a9d9")
    @Override
    public TStartEvent createJaxbElement(Object context, BpmnStartEvent modelioElement) {
        // Create JaxbElement
        TStartEvent jaxStartEvent = new TStartEvent();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if (context instanceof TProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess) context).getFlowElement();
            jaxContent.add(factory.createStartEvent(jaxStartEvent));
        } else if (context instanceof TSubProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess) context).getFlowElement();
            jaxContent.add(factory.createStartEvent(jaxStartEvent));
        }
        // Edit Properties
        jaxStartEvent.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxStartEvent;
    }

    @objid ("48e3b6d6-701d-4b00-8a40-2fef20356d1e")
    @Override
    public TStartEvent updateJaxbElement(Object context, TStartEvent jaxStartEvent, BpmnStartEvent modelioElement) {
        jaxStartEvent.setIsInterrupting(modelioElement.isIsInterrupting());
        jaxStartEvent.setParallelMultiple(modelioElement.isParallelMultiple());
        jaxStartEvent.setName(modelioElement.getName());
        return jaxStartEvent;
    }

    @objid ("974c31e2-e185-4340-b453-705c981074a3")
    @Override
    public BpmnStartEvent findUMLElementById(TStartEvent jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnStartEvent.class, jaxbElement.getId());
    }

}
