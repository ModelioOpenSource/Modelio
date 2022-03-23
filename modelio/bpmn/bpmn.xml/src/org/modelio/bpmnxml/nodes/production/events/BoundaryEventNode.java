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
import javax.xml.namespace.QName;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TActivity;
import org.modelio.bpmnxml.model.TBoundaryEvent;
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("6dffdb67-3c14-434d-b528-8ddbc85d376c")
public class BoundaryEventNode implements IProductionNode<BpmnBoundaryEvent, TBoundaryEvent> {
    @objid ("7bf91a28-29eb-4536-8103-992029c992be")
    private Map<String, Object> elementsMap;

    @objid ("1d89b21e-beaf-40d4-98c7-b44ab95bdf5a")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("6e2ac994-0ded-4b38-89c4-bc33c2317cd6")
    @Override
    public BpmnBoundaryEvent findUMLElement(MObject context, TBoundaryEvent jaxbElement) {
        if(context instanceof BpmnProcess){
            for(BpmnFlowElement flowElement :((BpmnProcess) context).getFlowElement()){
                if(flowElement instanceof BpmnBoundaryEvent && flowElement.getName().equals(jaxbElement.getName())){
                    return (BpmnBoundaryEvent)flowElement;
                }
            }
        }else if(context instanceof BpmnSubProcess){
            for(BpmnFlowElement flowElement :((BpmnSubProcess) context).getFlowElement()){
                if(flowElement instanceof BpmnBoundaryEvent && flowElement.getName().equals(jaxbElement.getName())){
                    return (BpmnBoundaryEvent)flowElement;
                }
            }
        }
        return null;
    }

    @objid ("9494ee49-5665-4bbd-98c2-479f5e4d3395")
    @Override
    public BpmnBoundaryEvent createUMLElement(MObject context, TBoundaryEvent jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnBoundaryEvent.class, context,jaxbElement.getId());
        } else {
            return factory.create(BpmnBoundaryEvent.class, context);
        }
        
    }

    @objid ("90b29606-1a1f-4935-bf3f-e913587eb8c2")
    @Override
    public BpmnBoundaryEvent updateUMLElement(MObject context, BpmnBoundaryEvent modelioElement, TBoundaryEvent jaxbElement) {
        // Set owner
        if(context instanceof BpmnProcess){
            ((BpmnProcess) context).getFlowElement().add(modelioElement);
        }else if(context instanceof BpmnSubProcess){
            ((BpmnSubProcess) context).getFlowElement().add(modelioElement);
        }
        
        if (jaxbElement.getName() != null)
                            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        
        modelioElement.setParallelMultiple(jaxbElement.isParallelMultiple());
        
        if(jaxbElement.getAttachedToRef() != null){
            BpmnActivity activity = (BpmnActivity)this.elementsMap.get(jaxbElement.getAttachedToRef().getLocalPart());
            if(activity != null){
                modelioElement.setAttachedToRef(activity);
            }
        }
        
        modelioElement.setCancelActivity(jaxbElement.isCancelActivity());
        return modelioElement;
    }

    @objid ("6ef20da8-bcb3-4628-b9f1-03ef6f47fcc3")
    @Override
    public TBoundaryEvent createJaxbElement(Object context, BpmnBoundaryEvent modelioElement) {
        // Create JaxbElement
        TBoundaryEvent jaxEvent = new TBoundaryEvent();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if(context instanceof TProcess){
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess)context).getFlowElement();
            jaxContent.add(factory.createBoundaryEvent(jaxEvent));
        }else if(context instanceof TSubProcess){
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess)context).getFlowElement();
            jaxContent.add(factory.createBoundaryEvent(jaxEvent));
        }
        // Edit Properties
        jaxEvent.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxEvent;
    }

    @objid ("6250d28f-0267-46b1-a762-6b29647bae50")
    @Override
    public TBoundaryEvent updateJaxbElement(Object context, TBoundaryEvent jaxEvent, BpmnBoundaryEvent modelioElement) {
        jaxEvent.setParallelMultiple(modelioElement.isParallelMultiple());
        jaxEvent.setCancelActivity(modelioElement.isCancelActivity());
        jaxEvent.setName(modelioElement.getName());
        
        if(modelioElement.getAttachedToRef() != null){
            TActivity activity = (TActivity) this.elementsMap.get(modelioElement.getAttachedToRef().getUuid());
            if(activity != null){
                jaxEvent.setAttachedToRef(new QName(activity.getId()));
            }
        }
        return jaxEvent;
    }

    @objid ("56f411b1-b18b-46cf-ba5a-e7cbcb1eb85c")
    @Override
    public BpmnBoundaryEvent findUMLElementById(TBoundaryEvent jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnBoundaryEvent.class, jaxbElement.getId());
    }

}
