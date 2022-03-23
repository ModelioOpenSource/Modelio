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
import org.modelio.bpmnxml.model.TProcess;
import org.modelio.bpmnxml.nodes.IFinaliseNode;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.vcore.session.api.ICoreSession;

@objid ("0897348d-f86b-4f1a-bcbd-76857839dc40")
public class ProcessFinaliser implements IFinaliseNode<BpmnProcess, TProcess> {
    @objid ("3138326d-cb21-4067-9c15-32c3e0459e55")
    private Map<String, Object> elementsMap;

    @objid ("2c263313-4d83-4c33-984b-80eb30a46fb5")
    private Map<String, Object> configuration;

    @objid ("2a9d7b43-a333-458a-87ad-cbd8237ea4df")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("c51c4a5a-ae1c-4024-b223-6f61085ec134")
    @Override
    public BpmnProcess finalizeElement(BpmnProcess modelioElement, TProcess jaxbElement, ICoreSession session, IDiagramService diagramService) {
        createDiagrams(modelioElement, session);
        return modelioElement;
    }

    @objid ("cf456403-598f-4b03-825a-5a703836dbdf")
    @Override
    public TProcess finalizeJaxbElement(BpmnProcess modelioElement, TProcess jaxbElement, IDiagramService diagramService) {
        return null;
    }

    @objid ("8ff49e9c-646f-450c-894a-85f7090c7459")
    private void createDiagrams(BpmnProcess modelioElement, ICoreSession session) {
        if (modelioElement.getProduct().isEmpty()) {
            session.getModel().getGenericFactory().create(BpmnProcessDesignDiagram.class, modelioElement, "Product");
        }
        createSubProcessDiagram(session, modelioElement);
        
    }

    @objid ("b893603f-b985-4521-abc6-1488114ca6ce")
    private void createSubProcessDiagram(ICoreSession session, Object process) {
        if (process instanceof BpmnProcess) {
            for (BpmnSubProcess sub : ((BpmnProcess) process).getFlowElement(BpmnSubProcess.class)) {
                if (sub.getProduct().isEmpty()) {
                    session.getModel().getGenericFactory().create(BpmnSubProcessDiagram.class, sub, "Product");
                    createSubProcessDiagram(session, process);
                }
            }
        } else if (process instanceof BpmnSubProcess) {
            for (BpmnSubProcess sub : ((BpmnSubProcess) process).getFlowElement(BpmnSubProcess.class)) {
                if (sub.getProduct().isEmpty()) {
                    session.getModel().getGenericFactory().create(BpmnSubProcessDiagram.class, sub, "Product");
                    createSubProcessDiagram(session, process);
                }
            }
        }
        
    }

    @objid ("778b1faa-e853-496f-a887-86033c1523a6")
    @Override
    public void setConfiguration(Map<String, Object> configuration) {
        this.configuration = configuration;
    }

}
