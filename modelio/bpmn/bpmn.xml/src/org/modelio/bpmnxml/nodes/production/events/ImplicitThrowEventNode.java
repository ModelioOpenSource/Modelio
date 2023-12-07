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
import jakarta.xml.bind.JAXBElement;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TComplexBehaviorDefinition;
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TImplicitThrowEvent;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnComplexBehaviorDefinition;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.events.BpmnImplicitThrowEvent;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("18331009-d430-44bf-bd6e-600551f57261")
public class ImplicitThrowEventNode implements IProductionNode<BpmnImplicitThrowEvent, TImplicitThrowEvent> {
    @objid ("5939448f-be72-4d2e-8f3e-c7a332fbdf1b")
    private Map<String, Object> elementsMap;

    @objid ("ba46947e-378c-4a3c-9fe2-7f7c36c47412")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("33734332-970a-4b51-874a-f5e37843e436")
    @Override
    public BpmnImplicitThrowEvent findUMLElement(MObject context, TImplicitThrowEvent jaxbElement) {
        if (context instanceof BpmnProcess) {
            for (BpmnFlowElement flowElement : ((BpmnProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnImplicitThrowEvent && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnImplicitThrowEvent) flowElement;
                }
            }
        } else if (context instanceof BpmnSubProcess) {
            for (BpmnFlowElement flowElement : ((BpmnSubProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnImplicitThrowEvent && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnImplicitThrowEvent) flowElement;
                }
            }
        } else if (context instanceof BpmnComplexBehaviorDefinition) {
            if (((BpmnComplexBehaviorDefinition) context).getEvent() != null && ((BpmnComplexBehaviorDefinition) context).getEvent().getName().equals(jaxbElement.getName())) {
                return ((BpmnComplexBehaviorDefinition) context).getEvent();
            }
        }
        return null;
    }

    @objid ("fa35deb9-ab02-47a8-a194-3601180afb86")
    @Override
    public BpmnImplicitThrowEvent createUMLElement(MObject context, TImplicitThrowEvent jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnImplicitThrowEvent.class, context,jaxbElement.getId());
        } else {
            return factory.create(BpmnImplicitThrowEvent.class, context);
        }
        
    }

    @objid ("915b6bc6-c619-4a62-a0a8-53343120e338")
    @Override
    public BpmnImplicitThrowEvent updateUMLElement(MObject context, BpmnImplicitThrowEvent modelioElement, TImplicitThrowEvent jaxbElement) {
        // Set owner
        if (context instanceof BpmnProcess) {
            ((BpmnProcess) context).getFlowElement().add(modelioElement);
        } else if (context instanceof BpmnSubProcess) {
            ((BpmnSubProcess) context).getFlowElement().add(modelioElement);
        } else if (context instanceof BpmnComplexBehaviorDefinition) {
            ((BpmnComplexBehaviorDefinition) context).setEvent(modelioElement);
        }
        
        if (jaxbElement.getName() != null)
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        return modelioElement;
    }

    @objid ("b4700f4f-3a21-455d-85b3-4856fe3ce427")
    @Override
    public TImplicitThrowEvent createJaxbElement(Object context, BpmnImplicitThrowEvent modelioElement) {
        // Create JaxbElement
        TImplicitThrowEvent jaxEndEvent = new TImplicitThrowEvent();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if (context instanceof TProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess) context).getFlowElement();
            jaxContent.add(factory.createImplicitThrowEvent(jaxEndEvent));
        } else if (context instanceof TSubProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess) context).getFlowElement();
            jaxContent.add(factory.createImplicitThrowEvent(jaxEndEvent));
        } else if (context instanceof TComplexBehaviorDefinition) {
            ((TComplexBehaviorDefinition) context).setEvent(jaxEndEvent);
        }
        
        jaxEndEvent.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxEndEvent;
    }

    @objid ("a5104b0b-7e9b-4ca9-9804-7ed4585e7968")
    @Override
    public TImplicitThrowEvent updateJaxbElement(Object context, TImplicitThrowEvent jaxEndEvent, BpmnImplicitThrowEvent modelioElement) {
        jaxEndEvent.setName(modelioElement.getName());
        return jaxEndEvent;
    }

    @objid ("a1c8db84-9fa8-42b2-a3c2-43f9005cd87b")
    @Override
    public BpmnImplicitThrowEvent findUMLElementById(TImplicitThrowEvent jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnImplicitThrowEvent.class, jaxbElement.getId());
    }

}
