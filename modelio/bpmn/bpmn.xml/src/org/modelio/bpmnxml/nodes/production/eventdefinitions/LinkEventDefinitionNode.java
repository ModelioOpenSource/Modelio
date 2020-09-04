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
import org.modelio.bpmnxml.model.TEvent;
import org.modelio.bpmnxml.model.TEventDefinition;
import org.modelio.bpmnxml.model.TLinkEventDefinition;
import org.modelio.bpmnxml.model.TThrowEvent;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnLinkEventDefinition;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("051b38fa-8196-4b0b-a159-8d1160369bf3")
public class LinkEventDefinitionNode implements IProductionNode<BpmnLinkEventDefinition,TLinkEventDefinition> {
    @objid ("b8f79c71-f25d-4fea-897d-6461d025cd3c")
    private Map<String, Object> elementsMap;

    @objid ("a5829321-e3cd-4803-a90e-2c34846de656")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("1f92b02f-d708-465a-bc66-91c8131b2453")
    @Override
    public BpmnLinkEventDefinition findUMLElement(MObject context, TLinkEventDefinition jaxbElement) {
        if (context instanceof BpmnEvent) {
            for (BpmnEventDefinition eventDefinition : ((BpmnEvent) context).getEventDefinitions()) {
                if (eventDefinition instanceof BpmnLinkEventDefinition) {
                    return (BpmnLinkEventDefinition) eventDefinition;
                }
            }
        }
        return null;
    }

    @objid ("de0a6a59-66b1-491e-837b-4044a66054e2")
    @Override
    public BpmnLinkEventDefinition createUMLElement(MObject context, TLinkEventDefinition jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId && jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {
            return factory.createWithId(BpmnLinkEventDefinition.class, context, "EventDefinitions", jaxbElement.getId());
        } else {
            return factory.create(BpmnLinkEventDefinition.class, context, "EventDefinitions");
        }
    }

    @objid ("4c0ac2b5-ad80-424e-9edc-6273d599b525")
    @Override
    public BpmnLinkEventDefinition updateUMLElement(MObject context, BpmnLinkEventDefinition modelioElement, TLinkEventDefinition jaxbElement) {
        if (jaxbElement.getSource() != null) {
            for (QName source : jaxbElement.getSource()) {
                BpmnLinkEventDefinition modelioSource = (BpmnLinkEventDefinition) this.elementsMap.get(source.getLocalPart());
                if (modelioSource != null) {
                    modelioElement.getSource().add(modelioSource);
                }
            }
        }
        
        if (jaxbElement.getTarget() != null) {
            BpmnLinkEventDefinition modeliTargete = (BpmnLinkEventDefinition) this.elementsMap.get(jaxbElement.getTarget().getLocalPart());
            if (modeliTargete != null) {
                modelioElement.setTarget(modeliTargete);
            }
        }
        return modelioElement;
    }

    @objid ("963d7619-cd9e-4a14-be8c-37921f935dcd")
    @Override
    public TLinkEventDefinition createJaxbElement(Object context, BpmnLinkEventDefinition modelioElement) {
        TEvent jaxEvent = (TEvent) context;
        
        // Create JaxbElement
        TLinkEventDefinition jaxEventDefinition = new TLinkEventDefinition();
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
        jaxContext.add(factory.createLinkEventDefinition(jaxEventDefinition));
        jaxEventDefinition.setId(IDUtils.getJaxbId(context, modelioElement));
        return jaxEventDefinition;
    }

    @objid ("2f20852f-23c2-4f03-8e8e-f7bc49bc9aa8")
    @Override
    public TLinkEventDefinition updateJaxbElement(Object context, TLinkEventDefinition jaxEventDefinition, BpmnLinkEventDefinition modelioElement) {
        for (BpmnLinkEventDefinition modelioSource : modelioElement.getSource()) {
            TLinkEventDefinition jaxSource = (TLinkEventDefinition) this.elementsMap.get(modelioSource.getUuid());
            if (jaxSource != null && jaxSource.getId() != null) {
                jaxEventDefinition.getSource().add(new QName(jaxSource.getId()));
            }
        }
        
        BpmnLinkEventDefinition modelioTarget = modelioElement.getTarget();
        if (modelioTarget != null) {
            TLinkEventDefinition jaxTarget = (TLinkEventDefinition) this.elementsMap.get(modelioTarget.getUuid());
            if (jaxTarget != null && jaxTarget.getId() != null) {
                jaxEventDefinition.setTarget(new QName(jaxTarget.getId()));
            }
        }
        return jaxEventDefinition;
    }

    @objid ("6d981245-6a82-42a0-9069-936df311a86b")
    @Override
    public BpmnLinkEventDefinition findUMLElementById(TLinkEventDefinition jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnLinkEventDefinition.class, jaxbElement.getId());
    }

}
