/* 
 * Copyright 2013-2018 Modeliosoft
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

import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TActivity;
import org.modelio.bpmnxml.model.TDataInput;
import org.modelio.bpmnxml.model.TDataObjectReference;
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TInputOutputSpecification;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("0b737586-cc95-42e1-adc9-7829d306ed46")
public class DataInputNode implements IProductionNode<BpmnDataInput,TDataInput> {
    @objid ("b376fd23-62a6-4a51-a4dd-35d7a84d2817")
    private Map<String, Object> elementsMap;

    @objid ("c1007cd2-3a18-4d57-9a2f-91c350bb6bc3")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("757fc988-5d82-42ef-b81d-b4e842c3ff0c")
    @Override
    public BpmnDataInput findUMLElement(MObject context, TDataInput jaxbElement) {
        if (context instanceof BpmnActivity) {
            for (BpmnDataInput dataInput : ((BpmnActivity) context).getInputSpecification()) {
                if (dataInput.getName().equals(jaxbElement.getName())) {
                    return dataInput;
                }
            }
        }
        return null;
    }

    @objid ("00842838-ea4b-47f9-84ac-06918676ca2f")
    @Override
    public BpmnDataInput createUMLElement(MObject context, TDataInput jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnDataInput.class,context,jaxbElement.getId());
        }else{
            return factory.create(BpmnDataInput.class,context);
        }
    }

    @objid ("b1fc6a6f-aa0e-4db3-b3a8-c4dfbe284782")
    @Override
    public BpmnDataInput updateUMLElement(MObject context, BpmnDataInput modelioElement, TDataInput jaxbElement) {
        // Set owner
        if (context instanceof BpmnActivity) {
            ((BpmnActivity) context).getInputSpecification().add(modelioElement);
        }else if(context instanceof BpmnThrowEvent){
            ((BpmnThrowEvent) context).setDataInput(modelioElement);
        }
        // Set properties
        if (jaxbElement.getName() != null)
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        
        modelioElement.setIsCollection(jaxbElement.isIsCollection());
        return modelioElement;
    }

    @objid ("921d3efa-7b7c-411d-9073-6d349c06ab40")
    @Override
    public TDataInput createJaxbElement(Object context, BpmnDataInput modelioElement) {
        // Create JaxbElement
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if (context instanceof TActivity) {
            TDataInput jaxTask = new TDataInput();
            TInputOutputSpecification jaxContent = ((TActivity) context).getIoSpecification();
            jaxContent.getDataInput().add(jaxTask);
            jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
            return jaxTask;
        } else if (context instanceof TProcess) {
            TDataObjectReference jaxTask = new TDataObjectReference();
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess) context).getFlowElement();
            jaxContent.add(factory.createDataObjectReference(jaxTask));
            jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
            return null;
        } else if (context instanceof TSubProcess) {
            TDataObjectReference jaxTask = new TDataObjectReference();
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess) context).getFlowElement();
            jaxContent.add(factory.createDataObjectReference(jaxTask));
            jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
            return null;
        }
        return null;
    }

    @objid ("947120d1-2984-4f3e-99a5-c3c6c9af63fc")
    @Override
    public TDataInput updateJaxbElement(Object context, TDataInput jaxTask, BpmnDataInput modelioElement) {
        jaxTask.setName(modelioElement.getName());
        jaxTask.setIsCollection(modelioElement.isIsCollection());
        return jaxTask;
    }

    @objid ("d522c87a-1ece-497e-b28f-5fe1a3608464")
    @Override
    public BpmnDataInput findUMLElementById(TDataInput jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnDataInput.class, jaxbElement.getId());
    }

}
