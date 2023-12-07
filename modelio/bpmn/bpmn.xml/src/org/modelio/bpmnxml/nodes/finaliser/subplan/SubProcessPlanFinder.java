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
package org.modelio.bpmnxml.nodes.finaliser.subplan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jakarta.xml.bind.JAXBElement;
import org.modelio.bpmnxml.model.BPMNEdge;
import org.modelio.bpmnxml.model.BPMNPlane;
import org.modelio.bpmnxml.model.BPMNShape;
import org.modelio.bpmnxml.model.Bounds;
import org.modelio.bpmnxml.model.DiagramElement;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.BehaviorDiagram;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("5970a573-f625-4aa7-b1b9-dcc7f86f91c5")
public class SubProcessPlanFinder {
    @objid ("45c1b8bc-f948-40e6-9a8f-e9739768e048")
    private Map<String, Object> elementsMap;

    @objid ("3cf91bb9-2a53-490c-a4b0-b1aab8e83e28")
    public  SubProcessPlanFinder(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("85b89214-2cf1-4a81-a2e8-10176d7535b3")
    public List<SubProcessActivityPlan> extractCallActivityFromPlan(BPMNPlane plane) {
        // Find Call Activity in diagram
        List<SubProcessActivityPlan> callActivities = new ArrayList<>();
        for (JAXBElement<? extends DiagramElement> jaxDiag : new ArrayList<>(plane.getDiagramElement())) {
            if (jaxDiag.getValue() instanceof BPMNShape) {
                BPMNShape jaxShape = (BPMNShape) jaxDiag.getValue();
                if(jaxShape.getBpmnElement() != null) {
                    MObject modelioNode = (MObject) this.elementsMap.get(jaxShape.getBpmnElement().getLocalPart());
                    if (modelioNode instanceof BpmnCallActivity && (Called.getTarget((BpmnCallActivity) modelioNode) != null) || modelioNode instanceof BpmnSubProcess) {
        
                        // Find Diagram
                        SubProcessActivityPlan ct = new SubProcessActivityPlan(findDiagram((ModelElement) modelioNode), jaxShape.getBounds());
        
                        // Find Node contained in Call activity and add it into the CallActivity plan
                        extractNodesFromPlan(jaxShape.getBounds(), plane, ct);
                        callActivities.add(ct);
                    }
                }
            }
        }
        return callActivities;
    }

    @objid ("92262eb0-51fb-407c-adfb-102bac10f5b6")
    private BehaviorDiagram findDiagram(ModelElement modelioNode) {
        ModelElement node = modelioNode;
        if (node instanceof BpmnCallActivity) {
            ModelElement process = Called.getTarget(modelioNode);
            if (process instanceof BpmnProcess) {
                node = process;
            }
        }
        
        for (AbstractDiagram diag : node.getProduct()) {
            if (diag instanceof BpmnProcessDesignDiagram || diag instanceof BpmnSubProcessDiagram) {
                return (BehaviorDiagram) diag;
            }
        }
        return null;
    }

    @objid ("84dec156-6afb-49f5-973d-6f307aa6e448")
    private void extractNodesFromPlan(Bounds activityBounds, BPMNPlane plane, SubProcessActivityPlan ct) {
        for (JAXBElement<? extends DiagramElement> jaxDiag : new ArrayList<>(plane.getDiagramElement())) {
            if (jaxDiag.getValue() instanceof BPMNShape) {
                BPMNShape jaxShape = (BPMNShape) jaxDiag.getValue();
        
                if (isInBounds(activityBounds, jaxShape.getBounds().getX(), jaxShape.getBounds().getY()) &&
                        isInBounds(activityBounds, jaxShape.getBounds().getX() + jaxShape.getBounds().getWidth(), jaxShape.getBounds().getY() + jaxShape.getBounds().getHeight())) {
                    plane.getDiagramElement().remove(jaxDiag);
                    ct.addShape(jaxShape);
        
                }
            }
        }
        
        for (JAXBElement<? extends DiagramElement> jaxDiag : new ArrayList<>(plane.getDiagramElement())) {
            if (jaxDiag.getValue() instanceof BPMNEdge) {
                BPMNEdge jaxEdge = (BPMNEdge) jaxDiag.getValue();
                if (jaxEdge.getWaypoint().size() > 0 && isInBounds(activityBounds, jaxEdge.getWaypoint().get(0).getX(), jaxEdge.getWaypoint().get(0).getY())) {
                    ct.addEdge(jaxEdge);
                    plane.getDiagramElement().remove(jaxDiag);
                }
            }
        }
        
    }

    @objid ("4e7b6ece-5539-4851-9796-ba8ffb2b1799")
    private boolean isInBounds(Bounds activityBounds, double x, double y) {
        if (x <= activityBounds.getX()) {
            return false;
        }
        if (x >= activityBounds.getX() + activityBounds.getWidth()) {
            return false;
        }
        
        if (y <= activityBounds.getY()) {
            return false;
        }
        if (y >= activityBounds.getY() + activityBounds.getHeight()) {
            return false;
        }
        return true;
    }

    @objid ("0c28796d-ed23-4f73-adcd-16b1692773ba")
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
