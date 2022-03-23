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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.bind.JAXBElement;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TCancelEventDefinition;
import org.modelio.bpmnxml.model.TCatchEvent;
import org.modelio.bpmnxml.model.TEvent;
import org.modelio.bpmnxml.model.TEventDefinition;
import org.modelio.bpmnxml.model.TThrowEvent;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.metamodel.bpmn.events.BpmnCancelEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("b73b6e84-4bd5-4d31-8c95-721535256e4f")
public class CancelEventDefinitionNode implements IProductionNode<BpmnCancelEventDefinition, TCancelEventDefinition> {
    @objid ("0bc5a28f-3999-475c-94f0-a931d100b9ec")
    private Map<String, Object> elementsMap;

    @objid ("85f96ba0-68fb-4711-9187-aaf8350d763b")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("fe5fafc6-4848-4463-af97-9914407cda51")
    @Override
    public BpmnCancelEventDefinition findUMLElement(MObject context, TCancelEventDefinition jaxbElement) {
        if(context instanceof BpmnEvent){
            for(BpmnEventDefinition eventDefinition :((BpmnEvent) context).getEventDefinitions()){
                if(eventDefinition instanceof BpmnCancelEventDefinition){
                    return (BpmnCancelEventDefinition)eventDefinition;
                }
            }
        }
        return null;
    }

    @objid ("c75c8e82-3be0-481e-bc63-ed6f2ad7b980")
    @Override
    public BpmnCancelEventDefinition createUMLElement(MObject context, TCancelEventDefinition jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnCancelEventDefinition.class,context,"EventDefinitions",jaxbElement.getId());
        }else{
            return factory.create(BpmnCancelEventDefinition.class,context,"EventDefinitions");
        }
        
    }

    @objid ("8c7dcd03-4c4e-4ebb-a6f2-3d9e88516d12")
    @Override
    public BpmnCancelEventDefinition updateUMLElement(MObject context, BpmnCancelEventDefinition modelioElement, TCancelEventDefinition jaxbElement) {
        // NA
        return modelioElement;
    }

    @objid ("99ccdd0b-3c9e-4bd2-bae8-0738df2eef73")
    @Override
    public TCancelEventDefinition createJaxbElement(Object context, BpmnCancelEventDefinition modelioElement) {
        TEvent jaxEvent = (TEvent)context;
        
        // Create JaxbElement
        TCancelEventDefinition jaxEventDefinition = new TCancelEventDefinition();
        this.elementsMap.put(modelioElement.getUuid(), jaxEventDefinition);
        
        // Add to context
        List<JAXBElement<? extends TEventDefinition>> jaxContext = null;
        if(jaxEvent instanceof TThrowEvent){
            jaxContext =  ((TThrowEvent) jaxEvent).getEventDefinition();
        }else if(jaxEvent instanceof TCatchEvent){
            jaxContext =  ((TCatchEvent) jaxEvent).getEventDefinition();
        }
        if(jaxContext == null){
            jaxContext = new ArrayList<>();
        }
        ObjectFactory factory = new ObjectFactory();
        jaxContext.add(factory.createCancelEventDefinition(jaxEventDefinition));
        
        jaxEventDefinition.setId(IDUtils.getJaxbId(context,modelioElement));
        return jaxEventDefinition;
    }

    @objid ("b3fd71f6-b7eb-4bf1-af33-de1321ea1ed0")
    @Override
    public TCancelEventDefinition updateJaxbElement(Object context, TCancelEventDefinition jaxbElement, BpmnCancelEventDefinition modelioElement) {
        // NA
        return jaxbElement;
    }

    @objid ("3595bcaf-4d8b-4ffe-850d-3509eeb9d00d")
    @Override
    public BpmnCancelEventDefinition findUMLElementById(TCancelEventDefinition jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnCancelEventDefinition.class, jaxbElement.getId());
    }

}
