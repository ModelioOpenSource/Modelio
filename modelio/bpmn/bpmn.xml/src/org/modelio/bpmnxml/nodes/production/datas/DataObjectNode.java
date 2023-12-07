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

import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jakarta.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TDataObject;
import org.modelio.bpmnxml.model.TDataObjectReference;
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.objects.BpmnDataObject;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("d927d820-6c35-4844-a87a-6e4a6a7aa4b9")
public class DataObjectNode implements IProductionNode<BpmnItemAwareElement, TDataObjectReference> {
    @objid ("8a3e587f-7eb3-438c-bae1-64d7193619b9")
    private Map<String, Object> elementsMap;

    @objid ("6fe89a5a-a2d2-4b2f-aed0-13549e438a5b")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("87ad7eb7-694c-4372-a359-5c6a06a49c3a")
    @Override
    public BpmnItemAwareElement findUMLElement(MObject context, TDataObjectReference jaxbElement) {
        if (context instanceof BpmnProcess) {
            for (BpmnFlowElement flowElement : ((BpmnProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnItemAwareElement
                        && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnItemAwareElement) flowElement;
                }
            }
        } else if (context instanceof BpmnSubProcess) {
            for (BpmnFlowElement flowElement : ((BpmnSubProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnItemAwareElement
                        && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnItemAwareElement) flowElement;
                }
            }
        }
        return null;
    }

    @objid ("c33dfe96-e98b-4b2a-ba0f-a114afe0fd62")
    @Override
    public BpmnItemAwareElement createUMLElement(MObject context, TDataObjectReference jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnDataObject.class,context,jaxbElement.getId());
        }else{
            return factory.create(BpmnDataObject.class,context);
        }
        
    }

    @objid ("ce5d11f9-53aa-49b0-a94a-58d6bc9ce4a5")
    @Override
    public BpmnItemAwareElement updateUMLElement(MObject context, BpmnItemAwareElement modelioElement, TDataObjectReference jaxbElement) {
        // Set owner
        if (context instanceof BpmnProcess) {
            ((BpmnProcess) context).getFlowElement().add(modelioElement);
        } else if (context instanceof BpmnSubProcess) {
            ((BpmnSubProcess) context).getFlowElement().add(modelioElement);
        }
        
        // Group
        if (jaxbElement.getCategoryValueRef() != null) {
            for (QName jaxGroupRef : jaxbElement.getCategoryValueRef()) {
                BpmnGroup modelioGroup = (BpmnGroup) this.elementsMap.get(jaxGroupRef.getLocalPart());
                if (modelioGroup != null) {
                    modelioElement.getGroups().add(modelioGroup);
                }
            }
        }
        
        // Set properties
        if (jaxbElement.getName() != null)
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        return modelioElement;
    }

    @objid ("466ec812-ce1c-4ea8-b9b5-48f814915193")
    @Override
    public TDataObjectReference createJaxbElement(Object context, BpmnItemAwareElement modelioElement) {
        // Create JaxbElement
        TDataObjectReference jaxTask = new TDataObjectReference();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if (context instanceof TProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess) context).getFlowElement();
            jaxContent.add(factory.createDataObjectReference(jaxTask));
        } else if (context instanceof TSubProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess) context).getFlowElement();
            jaxContent.add(factory.createDataObjectReference(jaxTask));
        }
        
        jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxTask;
    }

    @objid ("dc33695a-406a-42b4-9602-22a5f3b7f9e0")
    @Override
    public TDataObjectReference updateJaxbElement(Object context, TDataObjectReference jaxTask, BpmnItemAwareElement modelioElement) {
        jaxTask.setName(modelioElement.getName());
        
        TDataObject jaxDataObject = new TDataObject();
        
        jaxDataObject.setName(modelioElement.getName());
        jaxDataObject.setId("REF-" + IDUtils.formatJaxbID(modelioElement));
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if (context instanceof TProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess) context).getFlowElement();
            jaxContent.add(0, factory.createDataObject(jaxDataObject));
        } else if (context instanceof TSubProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess) context).getFlowElement();
            jaxContent.add(0, factory.createDataObject(jaxDataObject));
        }
        
        jaxTask.setDataObjectRef(jaxDataObject);
        return jaxTask;
    }

    @objid ("a7f66275-3efd-496b-bc82-e0ee3927c87e")
    @Override
    public BpmnItemAwareElement findUMLElementById(TDataObjectReference jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnItemAwareElement.class, jaxbElement.getId());
    }

}
