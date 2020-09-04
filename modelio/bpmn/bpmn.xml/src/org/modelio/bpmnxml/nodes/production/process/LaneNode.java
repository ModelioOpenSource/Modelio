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

package org.modelio.bpmnxml.nodes.production.process;

import java.util.Map;
import javax.xml.bind.JAXBElement;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TBaseElement;
import org.modelio.bpmnxml.model.TLane;
import org.modelio.bpmnxml.model.TLaneSet;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.gateways.BpmnGateway;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("af0929f7-c344-45f8-9481-831740caa5ff")
public class LaneNode implements IProductionNode<BpmnLane,TLane> {
    @objid ("2723b0bb-ad04-4c06-811c-e4a522f7301c")
    private Map<String, Object> elementsMap;

    @objid ("5abc7cf9-6c5c-48d6-8b7b-5108e2f0e0fe")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("f02c12f4-ac7c-4fb6-b68a-58503ea1794b")
    @Override
    public BpmnLane findUMLElement(MObject context, TLane jaxbElement) {
        if (context instanceof BpmnLaneSet) {
            for (BpmnLane modelioLan : ((BpmnLaneSet) context).getLane()) {
                if (modelioLan.getName().equals(jaxbElement.getName())) {
                    return modelioLan;
                } else if (modelioLan.getChildLaneSet() != null) {
                    BpmnLane result = findUMLElement(modelioLan.getChildLaneSet(), jaxbElement);
        
                    if (result != null)
                        return result;
                }
            }
        
        }
        return null;
    }

    @objid ("83eea93c-a4a1-4fc3-8852-dfcfd31fa87f")
    @Override
    public BpmnLane createUMLElement(MObject context, TLane jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnLane.class, context,jaxbElement.getId());
        } else {
            return factory.create(BpmnLane.class, context);
        }
    }

    @objid ("6bf12e6a-dd80-4c0a-877a-f946d92829ce")
    @Override
    public BpmnLane updateUMLElement(MObject context, BpmnLane modelioElement, TLane jaxbElement) {
        // set owner
        if (modelioElement.getLaneSet() == null && context instanceof BpmnLaneSet) {
            modelioElement.setLaneSet((BpmnLaneSet) context);
        }
        
        // set properties
        for (JAXBElement<Object> jaxFlow : jaxbElement.getFlowNodeRef()) {
            if (jaxFlow.getValue() instanceof TBaseElement) {
                Object ref = this.elementsMap.get(((TBaseElement) jaxFlow.getValue()).getId());
                if (ref instanceof BpmnFlowElement) {
                    modelioElement.getFlowElementRef().add((BpmnFlowElement) ref);
                }
            }
        }
        
        if (jaxbElement.getName() != null) {
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        }else{
            modelioElement.setName("Role");
        }
        return modelioElement;
    }

    @objid ("0ef04b46-6db0-483c-acf9-70f1c93d2117")
    @Override
    public TLane createJaxbElement(Object context, BpmnLane modelioElement) {
        // Create JaxbElement
        TLane jaxElement = new TLane();
        
        // Add to context
        if (context instanceof TLaneSet) {
            TLaneSet jaxLanSet = (TLaneSet) context;
            jaxLanSet.getLane().add(jaxElement);
        }
        
        jaxElement.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxElement;
    }

    @objid ("04f7891e-7fb5-495f-85c9-9241cdc157ee")
    @Override
    public TLane updateJaxbElement(Object context, TLane jaxbElement, BpmnLane modelioElement) {
        if (!"".equals(modelioElement.getName())) {
            jaxbElement.setName(modelioElement.getName());
        }
        
        for (BpmnFlowElement modelioflow : modelioElement.getFlowElementRef()) {
            if(modelioflow instanceof BpmnActivity || modelioflow instanceof BpmnGateway || modelioflow instanceof BpmnEvent){
                   Object jaxRef = this.elementsMap.get(modelioflow.getUuid());
                   if (jaxRef != null) {
                       ObjectFactory factory = new ObjectFactory();
                       jaxbElement.getFlowNodeRef().add(factory.createTLaneFlowNodeRef(jaxRef));
                   }
            }     
        }
        return jaxbElement;
    }

    @objid ("afb4e782-9315-480f-8d39-a8defa963b61")
    @Override
    public BpmnLane findUMLElementById(TLane jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnLane.class, jaxbElement.getId());
    }

}
