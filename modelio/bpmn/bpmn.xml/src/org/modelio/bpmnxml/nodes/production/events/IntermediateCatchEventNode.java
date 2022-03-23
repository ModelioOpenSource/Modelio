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
import org.modelio.bpmnxml.model.TIntermediateCatchEvent;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("d1249c36-110d-4517-aeac-e36c5ac92a21")
public class IntermediateCatchEventNode implements IProductionNode<BpmnIntermediateCatchEvent, TIntermediateCatchEvent> {
    @objid ("71b6f760-57d2-48ef-bd60-495862b98c10")
    private Map<String, Object> elementsMap;

    @objid ("7e99a8c7-c9f7-4448-bbff-0fb038e57f70")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("7476dcf3-6bf4-4cd4-b9c7-06a0ded4a5f8")
    @Override
    public BpmnIntermediateCatchEvent findUMLElement(MObject context, TIntermediateCatchEvent jaxbElement) {
        if (context instanceof BpmnProcess) {
            for (BpmnFlowElement flowElement : ((BpmnProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnIntermediateCatchEvent && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnIntermediateCatchEvent) flowElement;
                }
            }
        } else if (context instanceof BpmnSubProcess) {
            for (BpmnFlowElement flowElement : ((BpmnSubProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnIntermediateCatchEvent && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnIntermediateCatchEvent) flowElement;
                }
            }
        }
        return null;
    }

    @objid ("5713ea09-3cc2-4a1b-8a29-4e933ba161a7")
    @Override
    public BpmnIntermediateCatchEvent createUMLElement(MObject context, TIntermediateCatchEvent jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnIntermediateCatchEvent.class, context,jaxbElement.getId());
        } else {
            return factory.create(BpmnIntermediateCatchEvent.class, context);
        }
        
    }

    @objid ("05900787-9767-4f26-a192-d02e1f691480")
    @Override
    public BpmnIntermediateCatchEvent updateUMLElement(MObject context, BpmnIntermediateCatchEvent modelioElement, TIntermediateCatchEvent jaxbElement) {
        // Set owner
        if (context instanceof BpmnProcess) {
            ((BpmnProcess) context).getFlowElement().add(modelioElement);
        } else if (context instanceof BpmnSubProcess) {
            ((BpmnSubProcess) context).getFlowElement().add(modelioElement);
        }
        
        if (jaxbElement.getName() != null)
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        
        modelioElement.setParallelMultiple(jaxbElement.isParallelMultiple());
        return modelioElement;
    }

    @objid ("fe9507a5-5dd2-4ea5-82b0-0bc1bf6a2bec")
    @Override
    public TIntermediateCatchEvent createJaxbElement(Object context, BpmnIntermediateCatchEvent modelioElement) {
        // Create JaxbElement
        TIntermediateCatchEvent jaxEvent = new TIntermediateCatchEvent();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if (context instanceof TProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess) context).getFlowElement();
            jaxContent.add(factory.createIntermediateCatchEvent(jaxEvent));
        } else if (context instanceof TSubProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess) context).getFlowElement();
            jaxContent.add(factory.createIntermediateCatchEvent(jaxEvent));
        }
        // Edit Properties
        jaxEvent.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxEvent;
    }

    @objid ("126c56ac-5c21-4ce9-a1f1-d7008cd3eab5")
    @Override
    public TIntermediateCatchEvent updateJaxbElement(Object context, TIntermediateCatchEvent jaxEvent, BpmnIntermediateCatchEvent modelioElement) {
        jaxEvent.setParallelMultiple(modelioElement.isParallelMultiple());
        jaxEvent.setName(modelioElement.getName());
        return jaxEvent;
    }

    @objid ("432a9a6e-fd27-4928-86cf-d7fe261588c6")
    @Override
    public BpmnIntermediateCatchEvent findUMLElementById(TIntermediateCatchEvent jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnIntermediateCatchEvent.class, jaxbElement.getId());
    }

}
