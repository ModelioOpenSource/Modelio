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
package org.modelio.bpmnxml.nodes.production.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.xml.type.internal.QName;
import org.modelio.bpmnxml.model.BPMNDiagram;
import org.modelio.bpmnxml.model.BPMNPlane;
import org.modelio.bpmnxml.model.TDefinitions;
import org.modelio.bpmnxml.nodes.IProductionNode;
import org.modelio.bpmnxml.utils.BpmnImportFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.bpmnxml.utils.StringConvertor;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("fdfda389-6b18-40e1-973a-42ddd2d6dc95")
public class ProcessDiagramNode implements IProductionNode<BpmnProcessDesignDiagram, BPMNDiagram> {
    @objid ("89280623-017a-4ffb-8525-3d94a8bf9f70")
    private Map<String, Object> elementsMap;

    @objid ("7da89d28-cd0d-4496-84ba-69ed1371c3d1")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("f54b1546-3f66-4cf1-8600-1bcb99ad7415")
    @Override
    public BpmnProcessDesignDiagram findUMLElement(MObject context, BPMNDiagram jaxbElement) {
        ModelElement parent = findContext(jaxbElement);
        if (parent != null) {
            for (AbstractDiagram diagram : parent.getProduct()) {
                if (diagram instanceof BpmnProcessDesignDiagram && diagram.getName().equals(jaxbElement.getName())) {
                    return (BpmnProcessDesignDiagram) diagram;
                }
            }
        }
        return null;
    }

    @objid ("d19657c3-4389-4d3d-a046-725afe9e7f83")
    @Override
    public BpmnProcessDesignDiagram createUMLElement(MObject context, BPMNDiagram jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId &&  jaxbElement.getId() != null && !"".equals(jaxbElement.getId())) {  
            return factory.createWithId(BpmnProcessDesignDiagram.class, context,jaxbElement.getId());
        } else {
            return factory.create(BpmnProcessDesignDiagram.class, context);
        }
        
    }

    @objid ("2b6c816f-04fb-418a-9594-fa4970e4694b")
    @Override
    public BpmnProcessDesignDiagram updateUMLElement(MObject context, BpmnProcessDesignDiagram modelioElement, BPMNDiagram jaxbElement) {
        ModelElement parent = findContext(jaxbElement);
        modelioElement.setOrigin(parent);
        
        if (jaxbElement.getName() != null)
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        return modelioElement;
    }

    @objid ("488feb50-190c-4f72-8e6a-6b2eb9e57a85")
    @Override
    public BPMNDiagram createJaxbElement(Object context, BpmnProcessDesignDiagram modelioElement) {
        TDefinitions jaxDefinition = (TDefinitions) context;
        jaxDefinition.setTargetNamespace("http://www.omg.org/bpmn20");
        
        // Create JaxbElement
        BPMNDiagram jaxDiagram = new BPMNDiagram();
        
        // Add to context
        List<BPMNDiagram> jaxContent = jaxDefinition.getBPMNDiagram();
        if (jaxContent == null) {
            jaxContent = new ArrayList<>();
        }
        
        jaxContent.add(jaxDiagram);
        jaxDiagram.setId(IDUtils.formatJaxbID(modelioElement));
        
        // Create BPMNPlane
        BPMNPlane jaxPlan = new BPMNPlane();
        MObject owner = modelioElement.getCompositionOwner();
          
        jaxPlan.setBpmnElement(new QName(IDUtils.formatJaxbID(owner)));
        jaxDiagram.setBPMNPlane(jaxPlan);
        return jaxDiagram;
    }

    @objid ("39c67ade-2ac3-4ebc-aa3e-13bded184af9")
    @Override
    public BPMNDiagram updateJaxbElement(Object context, BPMNDiagram jaxDiagram, BpmnProcessDesignDiagram modelioElement) {
        jaxDiagram.setName(modelioElement.getName());
        return jaxDiagram;
    }

    @objid ("b0e96c0e-8aa2-4fc6-93b3-792aa7eaaa25")
    private ModelElement findContext(BPMNDiagram jaxbElement) {
        BPMNPlane plan = jaxbElement.getBPMNPlane();
        if (plan != null) {
            ModelElement result = (ModelElement) this.elementsMap.get(plan.getBpmnElement().getLocalPart());
            if (result instanceof BpmnCollaboration) {
                return (ModelElement) result.getCompositionOwner();
            }
            return result;
        }
        return null;
    }

    @objid ("4e8646f7-14bc-4383-969a-292fdbc49836")
    @Override
    public BpmnProcessDesignDiagram findUMLElementById(BPMNDiagram jaxbElement, ICoreSession session) {
        return session.getModel().findById(BpmnProcessDesignDiagram.class, jaxbElement.getId());
    }

}
