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
package org.modelio.bpmnxml.nodes.production.flows;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.namespace.QName;
import org.modelio.bpmnxml.model.TCollaboration;
import org.modelio.bpmnxml.model.TMessage;
import org.modelio.bpmnxml.model.TMessageFlow;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("bf87ac5c-ac6c-4596-92e6-d47bca7cf482")
public class MessageFlowNode implements IProductionNode<BpmnMessageFlow, TMessageFlow> {
    @objid ("4311204d-db1d-45b2-afed-865d334db7cb")
    private Map<String, Object> elementsMap;

    @objid ("c9d18f09-d5c9-4509-bcf3-e091b631d5b5")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("0cc0f5ce-4346-4592-9e7a-135f8959eb25")
    @Override
    public BpmnMessageFlow findUMLElement(MObject context, TMessageFlow jaxbElement) {
        Object from = this.elementsMap.get(jaxbElement.getSourceRef().getLocalPart());
        Object to = this.elementsMap.get(jaxbElement.getTargetRef().getLocalPart());
        
        if (from != null && to != null) {
            BpmnBaseElement fromM = (BpmnBaseElement) from;
            for (BpmnMessageFlow out : fromM.getOutgoingFlow()) {
                if (out.getTargetRef().equals(to)) {
                    return out;
                }
            }
        
        }
        return null;
    }

    @objid ("fd0ee216-7f92-447d-a8ec-5efa144fbd13")
    @Override
    public BpmnMessageFlow createUMLElement(MObject context, TMessageFlow jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId) {
            return factory.createWithId(BpmnMessageFlow.class, context, "MessageFlow", jaxbElement.getId());
        } else {
            return factory.create(BpmnMessageFlow.class, context, "MessageFlow");
        }
        
    }

    @objid ("c916f0aa-0656-4fbd-9bf5-cddd6272665a")
    @Override
    public BpmnMessageFlow updateUMLElement(MObject context, BpmnMessageFlow modelioElement, TMessageFlow jaxbElement) {
        Object from = this.elementsMap.get(jaxbElement.getSourceRef().getLocalPart());
        Object to = this.elementsMap.get(jaxbElement.getTargetRef().getLocalPart());
        
        // Find element by id in the Modelio project
        if (from == null) {
            from = CoreSession.getSession(modelioElement).getModel().findById(BpmnFlowElement.class, IDUtils.formatModelioId(jaxbElement.getSourceRef().getLocalPart()));
        }
        
        if (to == null) {
            to = CoreSession.getSession(modelioElement).getModel().findById(BpmnFlowElement.class, IDUtils.formatModelioId(jaxbElement.getTargetRef().getLocalPart()));
        }
        
        if (from != null && to != null) {
            modelioElement.setSourceRef((BpmnBaseElement) from);
            modelioElement.setTargetRef((BpmnBaseElement) to);
        }
        
        if (jaxbElement.getName() != null) {
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        }
        
        if (jaxbElement.getMessageRef() != null) {
            Object message = this.elementsMap.get(jaxbElement.getMessageRef().getLocalPart());
            if (message instanceof BpmnMessage) {
                modelioElement.setMessageRef((BpmnMessage) message);
            }
        }
        return modelioElement;
    }

    @objid ("98a16cc3-b859-4d88-b259-522c9fb3b9e5")
    @Override
    public TMessageFlow createJaxbElement(Object context, BpmnMessageFlow modelioElement) {
        TCollaboration jaxCollaboration = (TCollaboration) context;
        
        if (modelioElement.getSourceRef() != null && modelioElement.getTargetRef() != null) {
            // Create JaxbElement
            TMessageFlow jaxFlow = new TMessageFlow();
        
            // Add to context
            jaxCollaboration.getMessageFlow().add(jaxFlow);
        
            // Edit Properties
            jaxFlow.setId(IDUtils.formatJaxbID(modelioElement));
            return jaxFlow;
        }
        return null;
    }

    @objid ("06fdd805-17ed-4354-b31b-35541aeb6719")
    @Override
    public TMessageFlow updateJaxbElement(Object context, TMessageFlow jaxFlow, BpmnMessageFlow modelioElement) {
        if (modelioElement.getSourceRef() != null && modelioElement.getTargetRef() != null) {
            jaxFlow.setName(modelioElement.getName());
            jaxFlow.setSourceRef(new QName(IDUtils.formatJaxbID(modelioElement.getSourceRef())));
            jaxFlow.setTargetRef(new QName(IDUtils.formatJaxbID(modelioElement.getTargetRef())));
        }
        
        if (modelioElement.getMessageRef() != null) {
            Object jaxMessage = this.elementsMap.get(modelioElement.getMessageRef().getUuid());
            if (jaxMessage instanceof TMessage) {
                jaxFlow.setMessageRef(new QName(((TMessage) jaxMessage).getId()));
            }
        }
        return jaxFlow;
    }

    @objid ("2f9dd607-9360-4b31-88f9-64f5d3026c2c")
    @Override
    public BpmnMessageFlow findUMLElementById(TMessageFlow jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnMessageFlow.class, jaxbElement.getId());
    }

}
