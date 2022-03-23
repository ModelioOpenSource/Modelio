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
package org.modelio.bpmnxml.nodes.finaliser;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.bpmnxml.model.TCollaboration;
import org.modelio.bpmnxml.nodes.IFinaliseNode;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnCollaborationDiagram;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called;
import org.modelio.vcore.session.api.ICoreSession;

@objid ("3b51042f-09fc-4cf6-9646-38f58ae0f59e")
public class CollaborationFinaliser implements IFinaliseNode<BpmnCollaboration, TCollaboration> {
    @objid ("b15904fa-208f-4eb1-92f0-fdeb0bdd41da")
    private Map<String, Object> elementsMap;

    @objid ("f30915bc-375c-43a2-8ca4-2acc9b776115")
    private Map<String, Object> configuration;

    @objid ("6eb7bbe2-277f-4a5a-bbeb-46d90f2c716c")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("36a84fc5-c9e3-4f64-961f-bdf4cdc46a2e")
    @Override
    public BpmnCollaboration finalizeElement(BpmnCollaboration modelioElement, TCollaboration jaxbElement, ICoreSession session, IDiagramService diagramService) {
        createDiagrams(modelioElement, session);
        crateParticipant(modelioElement, session);
        return modelioElement;
    }

    @objid ("07983e30-b619-42a8-98c2-c0ad926aa7b9")
    private void crateParticipant(BpmnCollaboration modelioElement, ICoreSession session) {
        for (Object obj : this.elementsMap.values()) {
            if (obj instanceof BpmnProcess) {
                BpmnProcess process = (BpmnProcess) obj;
                if (getFirstBpmnCaller(process) == null && process.getParticipant().isEmpty()) {
                    BpmnParticipant participant = session.getModel().getGenericFactory().create(BpmnParticipant.class, modelioElement);
                    participant.setName(process.getName());
                    participant.setProcess(process);
                    participant.setContainer(modelioElement);
                }
            }
        }
        
    }

    @objid ("16db0132-2d80-46a9-9307-3817b1f1d019")
    @Override
    public TCollaboration finalizeJaxbElement(BpmnCollaboration modelioElement, TCollaboration jaxbElement, IDiagramService diagramService) {
        return null;
    }

    @objid ("bc904ada-dfbf-4a2f-8332-6bb2e5e51fca")
    private void createDiagrams(BpmnCollaboration modelioElement, ICoreSession session) {
        if (modelioElement.getProduct().isEmpty()) {
            session.getModel().getGenericFactory().create(BpmnCollaborationDiagram.class, modelioElement, "Product");
        }
        
    }

    @objid ("ce641aed-9354-4c69-8d5a-c9bcab3205b3")
    @Override
    public void setConfiguration(Map<String, Object> configuration) {
        this.configuration = configuration;
    }

    @objid ("150eefb8-62a0-400d-b809-34249c2492b5")
    private BpmnCallActivity getFirstBpmnCaller(BpmnProcess modelioElement) {
        for (MethodologicalLink dep : modelioElement.getImpactedDependency(MethodologicalLink.class)) {
            if (dep.isStereotyped(Called.MdaTypes.STEREOTYPE_ELT)) {
                ModelElement source = dep.getImpacted();
                if (source instanceof BpmnCallActivity) {
                    return (BpmnCallActivity) source;
                }
            }
        }
        return null;
    }

}
