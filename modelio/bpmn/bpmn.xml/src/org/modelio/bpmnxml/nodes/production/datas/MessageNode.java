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
package org.modelio.bpmnxml.nodes.production.datas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.bind.JAXBElement;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TDefinitions;
import org.modelio.bpmnxml.model.TMessage;
import org.modelio.bpmnxml.model.TRootElement;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("007457d6-b568-4bb3-802e-4e8e401175b3")
public class MessageNode implements IProductionNode<BpmnMessage, TMessage> {
    @objid ("340f1e8c-ee41-4801-a715-38d737910d6f")
    private Map<String, Object> elementsMap;

    @objid ("49b6e925-cc87-4df3-b0c4-1ac4b3248082")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("e58b131f-8a4d-4007-81fd-3f55b7d96860")
    @Override
    public BpmnMessage findUMLElement(MObject context, TMessage jaxbElement) {
        if (context instanceof BpmnCollaboration) {
            for (BpmnMessage message : ((BpmnCollaboration) context).getMessages()) {
                if (message.getName().equals(jaxbElement.getName())) {
                    return message;
                }
            }
        }
        return null;
    }

    @objid ("f2959ce1-372b-4ed1-911c-719af320ec35")
    @Override
    public BpmnMessage createUMLElement(MObject context, TMessage jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnMessage.class,context,jaxbElement.getId());
        }else{
            return factory.create(BpmnMessage.class,context);
        }
        
    }

    @objid ("4971d085-657f-48ed-a060-912e88ef1cfa")
    @Override
    public BpmnMessage updateUMLElement(MObject context, BpmnMessage modelioElement, TMessage jaxbElement) {
        if (context instanceof BpmnCollaboration) {
            ((BpmnCollaboration) context).getMessages().add(modelioElement);
        }
        
        if (jaxbElement.getName() != null) {
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        }
        return modelioElement;
    }

    @objid ("08ef8d6d-2ddf-4ffc-ac43-bead51fefe7c")
    @Override
    public TMessage createJaxbElement(Object context, BpmnMessage modelioElement) {
        // Create JaxbElement
        TMessage message = new TMessage();
        
        // Add to context
        TDefinitions jaxDefinition = (TDefinitions) context;
        List<JAXBElement<? extends TRootElement>> jaxContent = jaxDefinition.getRootElement();
        if (jaxContent == null) {
            jaxContent = new ArrayList<>();
        }
        ObjectFactory factory = new ObjectFactory();
        jaxContent.add(factory.createMessage(message));
        
        message.setId(IDUtils.formatJaxbID(modelioElement));
        return message;
    }

    @objid ("157a1609-a04f-4e6c-86ed-7016abf89f7e")
    @Override
    public TMessage updateJaxbElement(Object context, TMessage jaxFlow, BpmnMessage modelioElement) {
        if (!"".equals(modelioElement.getName())) {
            jaxFlow.setName(modelioElement.getName());
        }
        return jaxFlow;
    }

    @objid ("5d129347-83f9-43d5-ab34-b722222385c1")
    @Override
    public BpmnMessage findUMLElementById(TMessage jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnMessage.class, jaxbElement.getId());
    }

}
