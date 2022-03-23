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

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.model.TDataStore;
import org.modelio.bpmnxml.model.TDataStoreReference;
import org.modelio.bpmnxml.model.TDefinitions;
import org.modelio.bpmnxml.model.TFlowElement;
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.model.TRootElement;
import org.modelio.bpmnxml.model.TSubProcess;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.objects.BpmnDataStore;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("f46cb9b0-fb4f-4e36-a314-d47ea0d014c7")
public class DataStoreNode implements IProductionNode<BpmnDataStore, TDataStoreReference> {
    @objid ("4965c2d9-1433-4aff-9bc5-de552bae7a8f")
    private Map<String, Object> elementsMap;

    @objid ("91cf6312-f633-4d61-97ce-e2d99a08b5e5")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("22ae09ca-b9e7-4750-820d-fb7b626d6f88")
    @Override
    public BpmnDataStore findUMLElement(MObject context, TDataStoreReference jaxbElement) {
        if (context instanceof BpmnProcess) {
            for (BpmnFlowElement flowElement : ((BpmnProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnDataStore && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnDataStore) flowElement;
                }
            }
        } else if (context instanceof BpmnSubProcess) {
            for (BpmnFlowElement flowElement : ((BpmnSubProcess) context).getFlowElement()) {
                if (flowElement instanceof BpmnDataStore && flowElement.getName().equals(jaxbElement.getName())) {
                    return (BpmnDataStore) flowElement;
                }
            }
        }
        return null;
    }

    @objid ("f66eeaa2-f84f-49db-8014-82b53d4b59db")
    @Override
    public BpmnDataStore createUMLElement(MObject context, TDataStoreReference jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId && jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {
            return factory.createWithId(BpmnDataStore.class, context, jaxbElement.getId());
        } else {
            return factory.create(BpmnDataStore.class, context);
        }
        
    }

    @objid ("3cbefddc-3ea2-4839-a820-233a5379a878")
    @Override
    public BpmnDataStore updateUMLElement(MObject context, BpmnDataStore modelioElement, TDataStoreReference jaxbElement) {
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
        if (jaxbElement.getName() != null) {
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        }
        return modelioElement;
    }

    @objid ("7d7cdba9-b035-401f-88f8-6734be6bb52e")
    @Override
    public TDataStoreReference createJaxbElement(Object context, BpmnDataStore modelioElement) {
        // Create DataStoreReference
        TDataStoreReference jaxTask = new TDataStoreReference();
        
        // Add to context
        ObjectFactory factory = new ObjectFactory();
        if (context instanceof TProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TProcess) context).getFlowElement();
            jaxContent.add(factory.createDataStoreReference(jaxTask));
        } else if (context instanceof TSubProcess) {
            List<JAXBElement<? extends TFlowElement>> jaxContent = ((TSubProcess) context).getFlowElement();
            jaxContent.add(factory.createDataStoreReference(jaxTask));
        }
        
        jaxTask.setId(IDUtils.formatJaxbID(modelioElement));
        return jaxTask;
    }

    @objid ("8b2bc188-4bf3-4ad0-b13d-532678bfafbd")
    @Override
    public TDataStoreReference updateJaxbElement(Object context, TDataStoreReference jaxTask, BpmnDataStore modelioElement) {
        jaxTask.setName(modelioElement.getName());
        
        // Create Data Store
        
        BpmnProcess process = getBpmnProcess(modelioElement);
        Object jaxRoot = this.elementsMap.get("TDefinitions");
        if (jaxRoot instanceof TDefinitions) {
            TDataStore jaxDataObject = new TDataStore();
            jaxDataObject.setIsUnlimited(modelioElement.isIsUnlimited());
            jaxDataObject.setName(modelioElement.getName());
            jaxDataObject.setId("REF-" + IDUtils.formatJaxbID(modelioElement));
            jaxDataObject.setCapacity(BigInteger.valueOf(modelioElement.getCapacity()));
        
            // Add to context
            ObjectFactory factory = new ObjectFactory();
            List<JAXBElement<? extends TRootElement>> jaxContent = ((TDefinitions) jaxRoot).getRootElement();
            jaxContent.add(factory.createDataStore(jaxDataObject));
            jaxTask.setDataStoreRef(new QName(jaxDataObject.getId()));
        }
        return jaxTask;
    }

    @objid ("d7bc67c8-3aee-4c5c-8046-60369a638181")
    private BpmnProcess getBpmnProcess(MObject modelioElement) {
        if (modelioElement instanceof BpmnProcess || modelioElement == null) {
            return (BpmnProcess) modelioElement;
        } else {
            return getBpmnProcess(modelioElement.getCompositionOwner());
        }
        
    }

    @objid ("15f35cf6-93da-467e-87be-d2cf0bb832a1")
    @Override
    public BpmnDataStore findUMLElementById(TDataStoreReference jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnDataStore.class, jaxbElement.getId());
    }

}
