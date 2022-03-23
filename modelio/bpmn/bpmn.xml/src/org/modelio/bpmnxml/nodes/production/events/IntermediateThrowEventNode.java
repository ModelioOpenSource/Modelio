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
import org.modelio.bpmnxml.model.TIntermediateThrowEvent;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("ae55edc3-4876-49c8-ada7-175907896c55")
public class IntermediateThrowEventNode implements IProductionNode<BpmnIntermediateThrowEvent, TIntermediateThrowEvent> {
    @objid ("a3f29d22-5f88-40a4-98b0-34aea78360ae")
    private Map<String, Object> elementsMap;

    @objid ("fdd0ffcc-7030-4e4a-bfab-1f5838975744")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("fd7e71d0-bd5a-45f5-9570-ab82d54f4f6d")
    @Override
    public BpmnIntermediateThrowEvent findUMLElement(MObject context, TIntermediateThrowEvent jaxbElement) {
        if (context instanceof BpmnProcess) {
            for (BpmnFlowElement flowElement : ((BpmnProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnIntermediateThrowEvent && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnIntermediateThrowEvent) flowElement;
                }
            }
        } else if (context instanceof BpmnSubProcess) {
            for (BpmnFlowElement flowElement : ((BpmnSubProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnIntermediateThrowEvent && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnIntermediateThrowEvent) flowElement;
                }
            }
        }
        return null;
    }

    @objid ("51a54499-8270-4362-93ca-ffa765b33ad4")
    @Override
    public BpmnIntermediateThrowEvent createUMLElement(MObject context, TIntermediateThrowEvent jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnIntermediateThrowEvent.class, context,jaxbElement.getId());
        } else {
            return factory.create(BpmnIntermediateThrowEvent.class, context);
        }
        
    }

    @objid ("e659c823-5546-40c1-9bc4-3b04e05159ea")
    @Override
    public BpmnIntermediateThrowEvent updateUMLElement(MObject context, BpmnIntermediateThrowEvent modelioElement, TIntermediateThrowEvent jaxbElement) {
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

    @objid ("a881346f-ef52-436c-a306-1b591adbd855")
    @Override
    public TIntermediateThrowEvent createJaxbElement(Object context, BpmnIntermediateThrowEvent modelioElement) {
        // Create JaxbElement
        TIntermediateThrowEvent jaxEvent = new TIntermediateThrowEvent();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if (context instanceof TProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess) context).getFlowElement();
            jaxContent.add(factory.createIntermediateThrowEvent(jaxEvent));
        } else if (context instanceof TSubProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess) context).getFlowElement();
            jaxContent.add(factory.createIntermediateThrowEvent(jaxEvent));
        }
        // Edit Properties
        jaxEvent.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxEvent;
    }

    @objid ("cf658aa9-2e1a-4683-bdf0-8fd153cfdc07")
    @Override
    public TIntermediateThrowEvent updateJaxbElement(Object context, TIntermediateThrowEvent jaxEvent, BpmnIntermediateThrowEvent modelioElement) {
        jaxEvent.setName(modelioElement.getName());
        return jaxEvent;
    }

    @objid ("ec0bbf7b-6e5b-456b-93f1-d8da03a2c95d")
    @Override
    public BpmnIntermediateThrowEvent findUMLElementById(TIntermediateThrowEvent jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnIntermediateThrowEvent.class, jaxbElement.getId());
    }

}
