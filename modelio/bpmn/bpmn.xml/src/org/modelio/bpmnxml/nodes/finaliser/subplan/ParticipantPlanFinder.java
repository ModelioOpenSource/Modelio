/* 
 * Copyright 2013-2019 Modeliosoft
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
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.model.BPMNEdge;
import org.modelio.bpmnxml.model.BPMNPlane;
import org.modelio.bpmnxml.model.BPMNShape;
import org.modelio.bpmnxml.model.Bounds;
import org.modelio.bpmnxml.model.DiagramElement;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("1c1b3170-514d-477a-895f-ea85337a0b20")
public class ParticipantPlanFinder {
    @objid ("1c8125f6-b928-4089-9f8f-0ccd8f2150f2")
    private Map<String, Object> elementsMap;

    @objid ("aacc739b-2051-4799-8ef3-e6a82cffe621")
    public ParticipantPlanFinder(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("a88d4061-41d8-4ff3-b50f-7aebd0676e2e")
    public List<ParticipantPlan> getParticipantPlan(BPMNPlane plane, BpmnCollaboration modelioCollab) {
        // Find Participant referencing internal process
        List<ParticipantPlan> planeList = crateParticipantPlan(plane);
        
        // Create Plan for lane without Participant
        crateParticipantPlanForOrfanElements(planeList, modelioCollab, plane);
        
        // Transpose Elements into ParticipantPlan
        populateParticipantPlan(plane, planeList);
        return planeList;
    }

    @objid ("1cd1880c-0267-4950-b87a-1555b239c6aa")
    private void crateParticipantPlanForOrfanElements(List<ParticipantPlan> planeList, BpmnCollaboration modelioCollab, BPMNPlane plane) {
        for (BpmnParticipant participant : modelioCollab.getParticipants()) {
        
            // Find Plan associated to process
            ParticipantPlan partPlan = getPlanOfParticipant(planeList, participant);
            if (partPlan == null) {
                BpmnProcess process = participant.getProcess();
                if (process != null) {
        
                    Bounds partBounds = findBounds(process, plane);
                    // Create new Plane
                    if (partBounds != null && !process.getProduct().isEmpty() && process.getProduct().get(0) instanceof BpmnProcessDesignDiagram) {
                        BpmnProcessDesignDiagram diagram = (BpmnProcessDesignDiagram) process.getProduct().get(0);
                        planeList.add(new ParticipantPlan(diagram, partBounds));
        
                        // Create BPMNShape for the new participant
                        BPMNShape jaxbShape = new BPMNShape();
                        jaxbShape.setBounds(partBounds);
        
                        jaxbShape.setBpmnElement(new QName(IDUtils.formatJaxbID(participant)));
                        jaxbShape.setId(IDUtils.formatJaxbID(participant) + "-gr");
        
                        List<JAXBElement<? extends DiagramElement>> jaxContent = plane.getDiagramElement();
                        ObjectFactory factory = new ObjectFactory();
                        jaxContent.add(factory.createBPMNShape(jaxbShape));
        
                        this.elementsMap.put(jaxbShape.getBpmnElement().getLocalPart(), participant);
                    }
                }
            }
        }
    }

    @objid ("bc148a37-d889-4f33-b642-992ef390b748")
    private ParticipantPlan getPlanOfParticipant(List<ParticipantPlan> planeList, BpmnParticipant participant) {
        BpmnProcess process = participant.getProcess();
        if (process != null && !process.getProduct().isEmpty() && process.getProduct().get(0) instanceof BpmnProcessDesignDiagram) {
            for (ParticipantPlan plan : planeList) {
                if (plan.getDiagram().equals(process.getProduct().get(0))) {
                    return plan;
                }
            }
        }
        return null;
    }

    @objid ("31a071f0-27f1-45b9-a1b9-5a89437b8aae")
    private Bounds findBounds(BpmnProcess process, BPMNPlane plane) {
        Bounds bounds = null;
        
        List<MObject> processElement = new ArrayList<>();
        if (process.getLaneSet() != null) {
            processElement.addAll(process.getLaneSet().getLane());
        }
        
        for (JAXBElement<? extends DiagramElement> jaxDiag : plane.getDiagramElement()) {
            if (jaxDiag.getValue() instanceof BPMNShape) {
                BPMNShape jaxShape = (BPMNShape) jaxDiag.getValue();
                Object ref = this.elementsMap.get(jaxShape.getBpmnElement().getLocalPart());
                Bounds elemBounds = jaxShape.getBounds();
                if (processElement.contains(ref)) {
                    if (bounds == null) {
                        bounds = new Bounds();
                        bounds.setX(elemBounds.getX());
                        bounds.setY(elemBounds.getY());
                        bounds.setWidth(elemBounds.getWidth());
                        bounds.setHeight(elemBounds.getHeight());
                    } else {
                        if (elemBounds.getX() < bounds.getX()) {
                            bounds.setX(elemBounds.getX());
                        }
        
                        if (elemBounds.getX() + elemBounds.getWidth() > bounds.getX() + bounds.getWidth()) {
                            bounds.setWidth(elemBounds.getX() + elemBounds.getWidth() - bounds.getX());
                        }
        
                        if (elemBounds.getY() < bounds.getY()) {
                            bounds.setY(elemBounds.getY());
                        }
        
                        if (elemBounds.getY() + elemBounds.getHeight() > bounds.getY() + bounds.getHeight()) {
                            bounds.setHeight(elemBounds.getY() + elemBounds.getHeight() - bounds.getY());
                        }
                    }
                }
            }
        }
        return bounds;
    }

    @objid ("61f875f4-ab6f-4b7d-835a-4a3a5e9947f4")
    private List<ParticipantPlan> crateParticipantPlan(BPMNPlane plane) {
        List<ParticipantPlan> plans = new ArrayList<>();
        for (JAXBElement<? extends DiagramElement> jaxDiag : plane.getDiagramElement()) {
            if (jaxDiag.getValue() instanceof BPMNShape) {
                BPMNShape jaxShape = (BPMNShape) jaxDiag.getValue();
                Object ref = this.elementsMap.get(jaxShape.getBpmnElement().getLocalPart());
                if (ref instanceof BpmnParticipant) {
                    BpmnParticipant participant = (BpmnParticipant) ref;
                    BpmnProcess process = participant.getProcess();
                    if (process != null) {
                        for (AbstractDiagram diag : process.getProduct()) {
                            if (diag instanceof BpmnProcessDesignDiagram) {
                                plans.add(new ParticipantPlan((BpmnProcessDesignDiagram) diag, jaxShape.getBounds()));
                            }
                        }
                    }
                }
            }
        }
        return plans;
    }

    @objid ("6ab21685-e473-476a-875d-cbffe05462f9")
    private void populateParticipantPlan(BPMNPlane plane, List<ParticipantPlan> plans) {
        for (JAXBElement<? extends DiagramElement> jaxDiag : new ArrayList<>(plane.getDiagramElement())) {
            if (jaxDiag.getValue() instanceof BPMNShape) {
                BPMNShape jaxShape = (BPMNShape) jaxDiag.getValue();
                Object ref = this.elementsMap.get(jaxShape.getBpmnElement().getLocalPart());
                if (ref != null && !(ref instanceof BpmnParticipant) && !(ref instanceof BpmnMessage)) {
                    for (ParticipantPlan subplan : plans) {
                        if (jaxShape.getBounds() != null && subplan.isInPlan(jaxShape.getBounds().getX(), jaxShape.getBounds().getY())) {
                            subplan.addShape(jaxShape);
                            plane.getDiagramElement().remove(jaxDiag);
                            break;
                        }
                    }
                }
            }
        }
        
        for (JAXBElement<? extends DiagramElement> jaxDiag : new ArrayList<>(plane.getDiagramElement())) {
            if (jaxDiag.getValue() instanceof BPMNEdge) {
                BPMNEdge jaxEdge = (BPMNEdge) jaxDiag.getValue();
                Object ref = this.elementsMap.get(jaxEdge.getBpmnElement().getLocalPart());
                if (ref != null && !(ref instanceof BpmnMessageFlow)) {
                    for (ParticipantPlan subplan : plans) {
                        if (jaxEdge.getWaypoint().size() > 0 && subplan.isInPlan(jaxEdge.getWaypoint().get(0).getX(), jaxEdge.getWaypoint().get(0).getY())) {
                            subplan.addEdge(jaxEdge);
                            plane.getDiagramElement().remove(jaxDiag);
                            break;
                        }
                    }
                }
            }
        }
    }

}
