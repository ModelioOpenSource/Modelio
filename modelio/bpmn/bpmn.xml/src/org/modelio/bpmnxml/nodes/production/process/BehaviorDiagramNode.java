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
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnCollaborationDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.BehaviorDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("46a6d758-ce29-4153-b5e1-cb0dfda5c946")
public class BehaviorDiagramNode implements IProductionNode<BehaviorDiagram,BPMNDiagram> {
    @objid ("52a8d0b7-912a-43e0-8bb3-27084115a781")
    private Map<String, Object> elementsMap;

    @objid ("59fe2737-baf7-41bf-9a17-030897012889")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("d2e143c8-df67-49fe-a6e9-0bc1d104bc28")
    @Override
    public BehaviorDiagram findUMLElement(MObject context, BPMNDiagram jaxbElement) {
        ModelElement parent = findContext(jaxbElement);
        if (parent != null) {
            for (AbstractDiagram diagram : parent.getProduct()) {
                if (diagram instanceof BehaviorDiagram && diagram.getName().equals(jaxbElement.getName())) {
                    return (BehaviorDiagram) diagram;
                }
            }
        }
        return null;
    }

    @objid ("fceea3ef-a5bb-4d05-b08c-2766240a8eb6")
    @Override
    public BehaviorDiagram createUMLElement(MObject context, BPMNDiagram jaxbElement, BpmnImportFactory factory, boolean keepId) {
        if (keepId) {
            if (context instanceof BpmnCollaboration) {
                return factory.createWithId(BpmnCollaborationDiagram.class, context, jaxbElement.getId());
            } else if (context instanceof BpmnProcess){
                return factory.createWithId(BpmnProcessDesignDiagram.class, context, jaxbElement.getId());
            }else{
                return factory.create(BpmnSubProcessDiagram.class, context);
            }
        } else {
            if (context instanceof BpmnCollaboration) {
                return factory.create(BpmnCollaborationDiagram.class, context);
            }else if (context instanceof BpmnProcess){
                return factory.create(BpmnProcessDesignDiagram.class, context);
            }else{
                return factory.create(BpmnSubProcessDiagram.class, context);
            }
        }
    }

    @objid ("10275ab1-82a8-4c2d-9d16-9820b2699989")
    @Override
    public BehaviorDiagram updateUMLElement(MObject context, BehaviorDiagram modelioElement, BPMNDiagram jaxbElement) {
        ModelElement parent = findContext(jaxbElement);
        modelioElement.setOrigin(parent);
        
        if (jaxbElement.getName() != null)
            modelioElement.setName(StringConvertor.imports(jaxbElement.getName()));
        return modelioElement;
    }

    @objid ("5ed8903b-b084-4343-b85d-9fe7ac095d50")
    @Override
    public BPMNDiagram createJaxbElement(Object context, BehaviorDiagram modelioElement) {
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

    @objid ("7cccbfd5-7351-4786-ac46-93bbd20b335e")
    @Override
    public BPMNDiagram updateJaxbElement(Object context, BPMNDiagram jaxDiagram, BehaviorDiagram modelioElement) {
        jaxDiagram.setName(modelioElement.getName());
        return jaxDiagram;
    }

    @objid ("05948cee-c6b2-4b91-993f-2ed3fe994f38")
    private ModelElement findContext(BPMNDiagram jaxbElement) {
        BPMNPlane plan = jaxbElement.getBPMNPlane();
        if (plan != null) {
            return (ModelElement) this.elementsMap.get(plan.getBpmnElement().getLocalPart());
        }
        return null;
    }

    @objid ("09ecf4cc-26f8-4e41-8e43-f587959f6533")
    @Override
    public BehaviorDiagram findUMLElementById(BPMNDiagram jaxbElement, ICoreSession session) {
        return session.getModel().findById(BehaviorDiagram.class, jaxbElement.getId());
    }

}
