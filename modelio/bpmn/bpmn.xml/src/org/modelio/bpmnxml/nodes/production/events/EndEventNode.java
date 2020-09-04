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
import javax.xml.bind.JAXBElement;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TEndEvent;
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("9bdadb6d-a1f2-4dbb-a4f1-5dcc8ad1a1bb")
public class EndEventNode implements IProductionNode<BpmnEndEvent,TEndEvent> {
    @objid ("71e45297-ecb9-48e7-8b15-1e76c8525bbd")
    private Map<String, Object> elementsMap;

    @objid ("e1815894-b2b3-4ed7-82d9-84e4a086b7bb")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("23544db1-b985-49b8-b7fe-792a3b1f1a90")
    @Override
    public BpmnEndEvent findUMLElement(MObject context, TEndEvent jaxbElement) {
        if (context instanceof BpmnProcess) {
            for (BpmnFlowElement flowElement : ((BpmnProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnEndEvent && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnEndEvent) flowElement;
                }
            }
        } else if (context instanceof BpmnSubProcess) {
            for (BpmnFlowElement flowElement : ((BpmnSubProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnEndEvent && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnEndEvent) flowElement;
                }
            }
        }
        return null;
    }

    @objid ("cb7295ea-afc7-4ac8-b211-09d867e73237")
    @Override
    public BpmnEndEvent createUMLElement(MObject context, TEndEvent jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnEndEvent.class, context,jaxbElement.getId());
        } else {
            return factory.create(BpmnEndEvent.class, context);
        }
    }

    @objid ("87a232de-f554-4330-8f4f-e999c8d8dd8a")
    @Override
    public BpmnEndEvent updateUMLElement(MObject context, BpmnEndEvent modelioElement, TEndEvent jaxbElement) {
        // Set owner
        if (context instanceof BpmnProcess) {
            ((BpmnProcess) context).getFlowElement().add(modelioElement);
        } else if (context instanceof BpmnSubProcess) {
            ((BpmnSubProcess) context).getFlowElement().add(modelioElement);
        }
        
        if (jaxbElement.getName() != null)
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        return modelioElement;
    }

    @objid ("3829d7ed-c1dc-4021-8fe4-2a73f9bbba4e")
    @Override
    public TEndEvent createJaxbElement(Object context, BpmnEndEvent modelioElement) {
        // Create JaxbElement
        TEndEvent jaxEndEvent = new TEndEvent();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if (context instanceof TProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess) context).getFlowElement();
            jaxContent.add(factory.createEndEvent(jaxEndEvent));
        } else if (context instanceof TSubProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess) context).getFlowElement();
            jaxContent.add(factory.createEndEvent(jaxEndEvent));
        }
        
        jaxEndEvent.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxEndEvent;
    }

    @objid ("5f4a4f49-8570-4ecb-a623-3098e165ea38")
    @Override
    public TEndEvent updateJaxbElement(Object context, TEndEvent jaxEndEvent, BpmnEndEvent modelioElement) {
        jaxEndEvent.setName(modelioElement.getName());
        return jaxEndEvent;
    }

    @objid ("0207809e-7160-4296-a8d5-d93984cf35bf")
    @Override
    public BpmnEndEvent findUMLElementById(TEndEvent jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnEndEvent.class, jaxbElement.getId());
    }

}
