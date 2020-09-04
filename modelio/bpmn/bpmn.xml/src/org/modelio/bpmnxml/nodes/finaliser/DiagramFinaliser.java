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

package org.modelio.bpmnxml.nodes.finaliser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.UUID;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink.ExtensionRole;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode.Role;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.bpmnxml.model.BPMNDiagram;
import org.modelio.bpmnxml.model.BPMNEdge;
import org.modelio.bpmnxml.model.BPMNLabel;
import org.modelio.bpmnxml.model.BPMNLabelStyle;
import org.modelio.bpmnxml.model.BPMNPlane;
import org.modelio.bpmnxml.model.BPMNShape;
import org.modelio.bpmnxml.model.Bounds;
import org.modelio.bpmnxml.model.DiagramElement;
import org.modelio.bpmnxml.model.Font;
import org.modelio.bpmnxml.model.ObjectFactory;
import org.modelio.bpmnxml.nodes.IFinaliseNode;
import org.modelio.bpmnxml.nodes.finaliser.subplan.ParticipantPlan;
import org.modelio.bpmnxml.nodes.finaliser.subplan.ParticipantPlanFinder;
import org.modelio.bpmnxml.nodes.finaliser.subplan.SubProcessActivityPlan;
import org.modelio.bpmnxml.nodes.finaliser.subplan.SubProcessPlanFinder;
import org.modelio.bpmnxml.utils.IDUtils;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnCollaborationDiagram;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.diagrams.BehaviorDiagram;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("602f166a-54bc-4c2a-876a-26ec5e7ac504")
public class DiagramFinaliser implements IFinaliseNode<BehaviorDiagram,BPMNDiagram> {
    @objid ("adc88ba1-10a1-42db-86fc-02ce476ddd7c")
    private Map<String, Object> elementsMap;

    @objid ("edab6537-20ba-4565-af72-2bef8565f9d7")
    private Map<String, Object> configuration;

    @objid ("238e7c40-9312-419c-85df-ff47ce8db637")
    @Override
    public void setElements(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("dcd09fc6-9089-4b1e-88b6-7c676ee4a47b")
    @Override
    public BehaviorDiagram finalizeElement(BehaviorDiagram modelioDiagram, BPMNDiagram jaxbElement, ICoreSession session, IDiagramService diagramService) {
        BPMNPlane plane = jaxbElement.getBPMNPlane();
        if (plane != null) {
        
            // Collect Label Style
            Map<String, String> labelStyle = collectLabelStyle(jaxbElement);
        
            // Manage the case of non collapsed call activity.
            // Extraction of layout data of model elements contained in this call activity and layouting of this element in her own diagrams
            SubProcessPlanFinder callPlanFinder = new SubProcessPlanFinder(this.elementsMap);
            for (SubProcessActivityPlan content : callPlanFinder.extractCallActivityFromPlan(plane)) {
                finalizeElement(content.getModelioDiagram(), content.getJaxbDiagram(), session, diagramService);
            }
        
            if (modelioDiagram instanceof BpmnCollaborationDiagram) {
                // Extract elements in participant from main plan
                ParticipantPlanFinder planFinder = new ParticipantPlanFinder(this.elementsMap);
                List<ParticipantPlan> plans = planFinder.getParticipantPlan(plane, (BpmnCollaboration) modelioDiagram.getOrigin());
        
                // Populate process diagram
                for (ParticipantPlan subplan : plans) {
                    importDiagram(subplan.getDiagram(), diagramService, subplan.getSubPlan(), labelStyle);
                }
        
                // Populate Collaboration Diagram with Participant, MeassageFlow and Message
                importDiagram(modelioDiagram, diagramService, plane, labelStyle);
            } else {
                importDiagram(modelioDiagram, diagramService, plane, labelStyle);
            }
        }
        return modelioDiagram;
    }

    @objid ("4918dad9-a0c1-4641-be51-cfc5edf51886")
    private void importDiagram(BehaviorDiagram modelioDiagram, IDiagramService diagramService, BPMNPlane plane, Map<String, String> labelStyle) {
        // First, trigger the auto-unmask and save the diagram to avoid refresh issues
        try (IDiagramHandle handle = diagramService.getDiagramHandle(modelioDiagram)) {
            handle.save();
            handle.close();
        }
        
        // Then, properly layout the diagram itself
        try (IDiagramHandle handle = diagramService.getDiagramHandle(modelioDiagram)) {
        
            defineLineOrientation(plane, handle);
        
            unmaskLanes(plane, handle);
        
            unmaskNodes(plane, handle, labelStyle);
        
            unmaskLinks(plane, handle);
        
            handle.save();
            handle.close();
        }
    }

    @objid ("3060f92a-4bd9-4618-af8d-d2cb5906a3f7")
    private String isHorizontal(BPMNShape jaxShape) {
        if (jaxShape.isIsHorizontal() != null && jaxShape.isIsHorizontal() == false) {
            return "false";
        }
        return "true";
    }

    @objid ("a9d2298d-bc1d-4a8d-83f8-19d920608f6e")
    private void unmaskLanes(BPMNPlane plane, IDiagramHandle handle) {
        BpmnLaneSet rootLanSet = null;
        
        Point topLeft = new Point();
        Point bottomRight = new Point();
        
        for (JAXBElement<? extends DiagramElement> jaxDiag : plane.getDiagramElement()) {
            if (jaxDiag.getValue() instanceof BPMNShape) {
                BPMNShape jaxShape = (BPMNShape) jaxDiag.getValue();
                MObject modelioNode = (MObject) this.elementsMap.get(jaxShape.getBpmnElement().getLocalPart());
        
                if (modelioNode instanceof BpmnLane) {
                    Bounds bounds = jaxShape.getBounds();
                    Rectangle rect = new Rectangle();
                    rect.width = new Double(bounds.getWidth()).intValue();
                    rect.height = new Double(bounds.getHeight()).intValue();
                    rect.x = new Double(bounds.getX()).intValue();
                    rect.y = new Double(bounds.getY()).intValue();
                    unmaskNode(modelioNode, rect, handle);
                    BpmnLaneSet lanset = ((BpmnLane) modelioNode).getLaneSet();
                    if (lanset.getProcess() != null && rootLanSet == null) {
                        rootLanSet = lanset;
                        topLeft = new Point(rect.x, rect.y);
                        bottomRight = new Point(rect.x + rect.width, rect.y + rect.height);
                    } else {
                        if (rect.x < topLeft.x) {
                            topLeft.x = rect.x;
                        }
        
                        if (rect.y < topLeft.y) {
                            topLeft.y = rect.y;
                        }
        
                        if (rect.x + rect.width > bottomRight.x) {
                            bottomRight.x = rect.x + rect.width;
                        }
        
                        if (rect.y + rect.height > bottomRight.y) {
                            bottomRight.y = rect.y + rect.height;
                        }
        
                    }
                }
            }
        }
        if (rootLanSet != null) {
            List<IDiagramGraphic> lanSetGr = handle.getDiagramGraphics(rootLanSet);
            if (lanSetGr != null && !lanSetGr.isEmpty() && lanSetGr.get(0) instanceof IDiagramNode) {
                Rectangle rect = new Rectangle(topLeft.x, topLeft.y, bottomRight.x - topLeft.x, bottomRight.y - topLeft.y);
                ((IDiagramNode) lanSetGr.get(0)).setBounds(rect);
            }
        }
    }

    @objid ("84d22696-36ca-47cb-88fe-7d6d1ba2da04")
    private void unmaskLane(MObject modelioNode, Bounds bounds, IDiagramHandle handle) {
        Rectangle rect = new Rectangle();
        rect.width = new Double(bounds.getWidth()).intValue();
        rect.height = new Double(bounds.getHeight()).intValue();
        rect.x = new Double(bounds.getX()).intValue();
        rect.y = new Double(bounds.getY()).intValue();
        unmaskNode(modelioNode, rect, handle);
    }

    @objid ("82cc6c9d-7b8f-4062-88cd-ad09a2a90a57")
    private void unmaskLinks(BPMNPlane plane, IDiagramHandle handle) {
        for (JAXBElement<? extends DiagramElement> jaxDiag : plane.getDiagramElement()) {
            if (jaxDiag.getValue() instanceof BPMNEdge) {
                BPMNEdge jaxEdge = (BPMNEdge) jaxDiag.getValue();
        
                MObject modelioLink = (MObject) this.elementsMap.get(jaxEdge.getBpmnElement().getLocalPart());
                if (modelioLink != null) {
        
                    List<Point> points = new ArrayList<>();
                    for (org.modelio.bpmnxml.model.Point p : jaxEdge.getWaypoint()) {
                        points.add(new Point(new Double(p.getX()).intValue(), new Double(p.getY()).intValue()));
                    }
                    IDiagramLink dgLink = unmaskLink(modelioLink, points, handle);
                    BPMNLabel jaxLabel = jaxEdge.getBPMNLabel();
                    if (dgLink != null && jaxLabel != null) {
                        List<IDiagramNode> labels = new ArrayList<>(dgLink.getExtensions(ExtensionRole.EDGE_GUARD));
                        labels.addAll(new ArrayList<>(dgLink.getExtensions(ExtensionRole.MAIN)));
                        if (labels.size() > 0) {
                            unmaskLabel(labels.get(0), jaxLabel);
                        }
                    }
        
                    if (modelioLink instanceof BpmnMessageFlow) {
                        unmaskMessage(handle, (BpmnMessageFlow) modelioLink, points);
                    }
                }
            }
        }
    }

    @objid ("bf2d3a32-6a78-40cd-ac43-a4b850b9ef7e")
    private void unmaskNodes(BPMNPlane plane, IDiagramHandle handle, Map<String, String> labelStyle) {
        for (JAXBElement<? extends DiagramElement> jaxDiag : plane.getDiagramElement()) {
            if (jaxDiag.getValue() instanceof BPMNShape) {
                BPMNShape jaxShape = (BPMNShape) jaxDiag.getValue();
        
                MObject modelioNode = (MObject) this.elementsMap.get(jaxShape.getBpmnElement().getLocalPart());
        
                if (modelioNode != null && !(modelioNode instanceof BpmnLaneSet) && !(modelioNode instanceof BpmnLane)) {
        
                    Rectangle rect = new Rectangle();
                    Bounds bounds = jaxShape.getBounds();
        
                    rect.width = new Double(bounds.getWidth()).intValue();
                    rect.height = new Double(bounds.getHeight()).intValue();
                    rect.x = new Double(bounds.getX()).intValue();
                    rect.y = new Double(bounds.getY()).intValue();
                    IDiagramNode dgNode = unmaskNode(modelioNode, rect, handle);
        
                    if (dgNode == null) {
                        dgNode = fixBounds(handle, modelioNode, rect);
                    } else if (dgNode.getElement() instanceof BpmnSubProcess) {
                        if (jaxShape.isIsExpanded() != null && jaxShape.isIsExpanded()) {
                            dgNode.setProperty("SUBPROCESS_SHOWCONTENT", "true");
                        } else {
                            dgNode.setProperty("SUBPROCESS_SHOWCONTENT", "false");
                        }
                    }
        
                    unmaskLabel(jaxShape, rect, dgNode, labelStyle);
                }
            }
        }
    }

    @objid ("f634b3d4-7bf5-40d2-b130-1763002c1422")
    private IDiagramNode unmaskNode(MObject modelioElement, Rectangle rect, IDiagramHandle handle) {
        try {
            if (rect.width == 0 && rect.height == 0) {
                return null;
            }
            List<IDiagramGraphic> graphics = handle.getDiagramGraphics(modelioElement);
            if (graphics.isEmpty()) {
                graphics = handle.unmask(modelioElement, rect.x, rect.y);
            }
        
            try {
                if (graphics.size() > 0 && graphics.get(0) instanceof IDiagramNode) {
                    IDiagramNode dgNode = (IDiagramNode) graphics.get(0);
                    dgNode.setBounds(rect);
        
                    return dgNode;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @objid ("d6b51faa-be19-4b82-bf0a-4d1613e180f6")
    private IDiagramLink unmaskLink(MObject modelioElement, List<Point> points, IDiagramHandle handle) {
        try {
            List<IDiagramGraphic> graphics = handle.unmask(modelioElement, 0, 0);
        
            if (graphics.isEmpty()) {
                graphics = handle.getDiagramGraphics(modelioElement);
            }
        
            if (!graphics.isEmpty()) {
                IDiagramGraphic linkDG = graphics.get(0);
                if (linkDG instanceof IDiagramLink) {
                    ((IDiagramLink) linkDG).setPath(points);
                    return (IDiagramLink) linkDG;
                }
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @objid ("00cefd5b-8c45-42aa-b7ba-5ec037dfe0bc")
    @Override
    public BPMNDiagram finalizeJaxbElement(BehaviorDiagram modelioElement, BPMNDiagram jaxbElement, IDiagramService diagramService) {
        Map<String, String> labelStyle = new HashMap<>();
        try (IDiagramHandle handle = diagramService.getDiagramHandle(modelioElement)) {
            BPMNPlane plane = jaxbElement.getBPMNPlane();
        
            for (BpmnParticipant participant : getParticipant(modelioElement)) {
                exportParticipant(participant, handle, plane);
            }
        
            for (MObject graphElement : getGraphicElements(modelioElement)) {
                List<IDiagramGraphic> grs = handle.getDiagramGraphics(graphElement);
                if (grs.size() > 0) {
                    if (grs.get(0) instanceof IDiagramNode) {
                        exportNode(handle.getDiagramNode(), (IDiagramNode) grs.get(0), plane, labelStyle);
                    } else if (grs.get(0) instanceof IDiagramLink) {
                        exportLink((IDiagramLink) grs.get(0), plane);
                    }
                }
            }
        
            exportStyles(jaxbElement, labelStyle);
        
            handle.close();
        }
        return jaxbElement;
    }

    @objid ("45912053-4131-4677-b44c-1b66693a833e")
    private void exportLink(IDiagramLink dgLink, BPMNPlane plane) {
        BPMNEdge jaxbEdge = new BPMNEdge();
        
        for (Point p : dgLink.getPath().getPoints()) {
            org.modelio.bpmnxml.model.Point jaxPoint = new org.modelio.bpmnxml.model.Point();
            jaxPoint.setX(p.x);
            jaxPoint.setY(p.y);
            jaxbEdge.getWaypoint().add(jaxPoint);
        }
        
        jaxbEdge.setBpmnElement(new QName(IDUtils.formatJaxbID(dgLink.getElement())));
        jaxbEdge.setId(IDUtils.formatJaxbID(dgLink.getElement()) + "-gr");
        
        if (dgLink.getElement() instanceof BpmnDataAssociation) {
            BpmnDataAssociation assoc = (BpmnDataAssociation) dgLink.getElement();
        
            if (assoc.getEndingActivity() != null && assoc.getTargetRef() != null) {
                jaxbEdge.setTargetElement(new QName(IDUtils.formatJaxbID(assoc.getEndingActivity()) + "-gr"));
            } else if (assoc.getStartingActivity() != null && assoc.getSourceRef().size() > 0) {
                jaxbEdge.setSourceElement(new QName(IDUtils.formatJaxbID(assoc.getStartingActivity()) + "-gr"));
            } else if (assoc.getEndingEvent() != null && assoc.getTargetRef() != null) {
                jaxbEdge.setTargetElement(new QName(IDUtils.formatJaxbID(assoc.getEndingEvent()) + "-gr"));
            } else if (assoc.getStartingEvent() != null && assoc.getSourceRef().size() > 0) {
                jaxbEdge.setSourceElement(new QName(IDUtils.formatJaxbID(assoc.getStartingEvent()) + "-gr"));
            }
        }
        
        List<JAXBElement<? extends DiagramElement>> jaxContent = plane.getDiagramElement();
        if (jaxContent == null)
        
        {
            jaxContent = new ArrayList<>();
        }
        
        ObjectFactory factory = new ObjectFactory();
        jaxContent.add(factory.createBPMNEdge(jaxbEdge));
    }

    @objid ("ba9ccf2a-43fa-4148-8d10-021a2a7f4a07")
    private void exportNode(IDiagramNode diagramDG, IDiagramNode dgNode, BPMNPlane plane, Map<String, String> labelStyle) {
        BPMNShape jaxbShape = new BPMNShape();
        Bounds bonds = new Bounds();
        bonds.setX(dgNode.getBounds().x);
        bonds.setY(dgNode.getBounds().y);
        bonds.setWidth(dgNode.getBounds().width);
        bonds.setHeight(dgNode.getBounds().height);
        jaxbShape.setBounds(bonds);
        
        jaxbShape.setBpmnElement(new QName(IDUtils.formatJaxbID(dgNode.getElement())));
        jaxbShape.setId(IDUtils.formatJaxbID(dgNode.getElement()) + "-gr");
        
        List<JAXBElement<? extends DiagramElement>> jaxContent = plane.getDiagramElement();
        if (jaxContent == null) {
            jaxContent = new ArrayList<>();
        }
        ObjectFactory factory = new ObjectFactory();
        
        jaxContent.add(factory.createBPMNShape(jaxbShape));
        
        List<IDiagramNode> nodes = new ArrayList<>(dgNode.getNodes(Role.LABEL));
        if (nodes.size() > 0) {
            BPMNLabel jaxLabel = new BPMNLabel();
        
            Bounds bondLabel = new Bounds();
            bondLabel.setX(nodes.get(0).getBounds().x);
            bondLabel.setY(nodes.get(0).getBounds().y);
            bondLabel.setWidth(nodes.get(0).getBounds().width);
            bondLabel.setHeight(nodes.get(0).getBounds().height);
            jaxLabel.setBounds(bondLabel);
        
            jaxbShape.setBPMNLabel(jaxLabel);
        
            String id = null;
            for (Entry<String, String> val : labelStyle.entrySet()) {
                if (val.getValue().equals(dgNode.getFont())) {
                    id = val.getKey();
                }
            }
        
            if (id == null) {
                id = UUID.randomUUID().toString();
                labelStyle.put(id, dgNode.getFont());
            }
        
            jaxLabel.setLabelStyle(new QName(id));
        }
        
        if (dgNode.getElement() instanceof BpmnLane) {
            jaxbShape.setIsExpanded("true".equals(dgNode.getProperty("SUBPROCESS_SHOWCONTENT")));
        }
        
        if (dgNode.getElement() instanceof BpmnLane) {
            Boolean isHorizontal = true;
            if ("false".equals(diagramDG.getProperty("DIAGRAM_HORIZONTAL_LANES"))) {
                isHorizontal = false;
            }
            jaxbShape.setIsHorizontal(isHorizontal);
        }
        
        if (dgNode.getElement() instanceof BpmnSubProcess) {
            jaxbShape.setIsExpanded("true".equals(dgNode.getProperty("SUBPROCESS_SHOWCONTENT")));
        }
    }

    @objid ("8467d508-bf1f-4e3c-a446-5d0ff08d0ac2")
    private List<MObject> getGraphicElements(BehaviorDiagram modelioDiagram) {
        List<MObject> displayObject = new ArrayList<>();
        
        MObject context = modelioDiagram.getCompositionOwner();
        if (context instanceof BpmnCollaboration) {
            displayObject.addAll(getGraphicElements((BpmnCollaboration) context));
        } else if (context instanceof BpmnProcess) {
            BpmnProcess process = (BpmnProcess) context;
            // if(process.getParticipant().isEmpty()){
            displayObject.addAll(getGraphicElements(process));
            // }
        } else if (context instanceof BpmnSubProcess) {
            displayObject.addAll(getGraphicElements((BpmnSubProcess) context));
        }
        return displayObject;
    }

    @objid ("e680f1e7-5233-4525-8462-12202627eaa8")
    private List<MObject> getGraphicElements(BpmnCollaboration modelioElement) {
        List<MObject> displayObject = new ArrayList<>();
        for (BpmnMessageFlow flow : modelioElement.getMessageFlow()) {
            displayObject.add(flow);
        }
        
        for (BpmnMessage messages : modelioElement.getMessages()) {
            displayObject.add(messages);
        }
        
        // for (BpmnParticipant participant : modelioElement.getParticipants()) {
        // BpmnProcess process = participant.getProcess();
        // if (process != null) {
        // displayObject.addAll(getGraphicElements(process));
        // }
        // }
        return displayObject;
    }

    @objid ("956f95ac-9f0c-49c8-b6b0-c1cf955f9701")
    private List<MObject> getGraphicElements(BpmnProcess modelioElement) {
        List<MObject> displayObject = new ArrayList<>();
        
        BpmnLaneSet laneSet = modelioElement.getLaneSet();
        if (laneSet != null) {
            displayObject.addAll(getGraphicElements(laneSet));
        }
        
        for (BpmnFlowElement root : modelioElement.getFlowElement()) {
            displayObject.add(root);
            if (root instanceof BpmnSubProcess) {
                // displayObject.addAll(getGraphicElements((BpmnSubProcess) root));
                // for (BpmnDataAssociation sub : ((BpmnSubProcess) root).getDataInputAssociation()) {
                // displayObject.add(sub);
                // }
                //
                // for (BpmnDataAssociation sub : ((BpmnSubProcess) root).getDataOutputAssociation()) {
                // displayObject.add(sub);
                // }
            } else if (root instanceof BpmnActivity) {
                for (BpmnDataAssociation sub : ((BpmnActivity) root).getDataInputAssociation()) {
                    displayObject.add(sub);
                }
        
                for (BpmnDataAssociation sub : ((BpmnActivity) root).getDataOutputAssociation()) {
                    displayObject.add(sub);
                }
            } else if (root instanceof BpmnThrowEvent) {
                for (BpmnDataAssociation sub : ((BpmnThrowEvent) root).getDataInputAssociation()) {
                    displayObject.add(sub);
                }
            } else if (root instanceof BpmnCatchEvent) {
                for (BpmnDataAssociation sub : ((BpmnCatchEvent) root).getDataOutputAssociation()) {
                    displayObject.add(sub);
                }
            }
        
        }
        return displayObject;
    }

    @objid ("4947fb36-993f-4f9c-b1a9-3e3828c5ee2b")
    private List<MObject> getGraphicElements(BpmnLaneSet modelioElement) {
        List<MObject> displayObject = new ArrayList<>();
        for (BpmnLane lane : modelioElement.getLane()) {
            displayObject.add(lane);
            BpmnLaneSet subLanSet = lane.getChildLaneSet();
            if (subLanSet != null) {
                displayObject.addAll(getGraphicElements(subLanSet));
            }
        }
        return displayObject;
    }

    @objid ("c1d76425-3dfd-4ddd-8a0b-c4a467c68844")
    private List<MObject> getGraphicElements(BpmnSubProcess modelioElement) {
        List<MObject> displayObject = new ArrayList<>();
        for (BpmnFlowElement root : modelioElement.getFlowElement()) {
            displayObject.add(root);
        
            if (root instanceof BpmnSubProcess) {
                displayObject.addAll(getGraphicElements((BpmnSubProcess) root));
                for (BpmnDataAssociation sub : ((BpmnSubProcess) root).getDataInputAssociation()) {
                    displayObject.add(sub);
                }
        
                for (BpmnDataAssociation sub : ((BpmnSubProcess) root).getDataOutputAssociation()) {
                    displayObject.add(sub);
                }
            } else if (root instanceof BpmnActivity) {
                for (BpmnDataAssociation sub : ((BpmnActivity) root).getDataInputAssociation()) {
                    displayObject.add(sub);
                }
        
                for (BpmnDataAssociation sub : ((BpmnActivity) root).getDataOutputAssociation()) {
                    displayObject.add(sub);
                }
            } else if (root instanceof BpmnThrowEvent) {
                for (BpmnDataAssociation sub : ((BpmnThrowEvent) root).getDataInputAssociation()) {
                    displayObject.add(sub);
                }
            } else if (root instanceof BpmnCatchEvent) {
                for (BpmnDataAssociation sub : ((BpmnCatchEvent) root).getDataOutputAssociation()) {
                    displayObject.add(sub);
                }
            }
        
        }
        return displayObject;
    }

    @objid ("cac80848-2cdf-41d1-8740-68d9387823c2")
    private void unmaskMessage(IDiagramHandle handle, BpmnMessageFlow modelioLink, List<Point> points) {
        BpmnMessage message = modelioLink.getMessageRef();
        if (message != null) {
            int x = 0;
            int y = 0;
        
            if (points.size() > 1) {
                x = (points.get(0).x + points.get(points.size() - 1).x) / 2;
                y = (points.get(0).y + points.get(points.size() - 1).y) / 2;
            }
        
            unmaskNode(message, new Rectangle(x, y, 0, 0), handle);
        }
    }

    @objid ("d79e2210-e752-4d41-ae67-131a3bd8b817")
    private List<BpmnParticipant> getParticipant(BehaviorDiagram modelioElement) {
        if (modelioElement.getOrigin() instanceof BpmnCollaboration) {
            BpmnCollaboration collaboration = (BpmnCollaboration) modelioElement.getOrigin();
            return collaboration.getParticipants();
        }
        return new ArrayList<>();
    }

    @objid ("b4a97cd9-8d8a-4ed8-9874-9cf7a9a163a8")
    private void exportParticipant(BpmnParticipant participant, IDiagramHandle handle, BPMNPlane plane) {
        List<IDiagramGraphic> grs = handle.getDiagramGraphics(participant);
        if (grs.size() > 0 && grs.get(0) instanceof IDiagramNode) {
            IDiagramNode dgNode = (IDiagramNode) grs.get(0);
            BPMNShape jaxbShape = new BPMNShape();
            Bounds bonds = new Bounds();
            bonds.setX(dgNode.getBounds().x);
            bonds.setY(dgNode.getBounds().y);
            bonds.setWidth(dgNode.getBounds().width);
            bonds.setHeight(dgNode.getBounds().height);
            jaxbShape.setBounds(bonds);
        
            jaxbShape.setBpmnElement(new QName(IDUtils.formatJaxbID(participant)));
            jaxbShape.setId(IDUtils.formatJaxbID(participant) + "-gr");
        
            List<JAXBElement<? extends DiagramElement>> jaxContent = plane.getDiagramElement();
            if (jaxContent == null) {
                jaxContent = new ArrayList<>();
            }
            ObjectFactory factory = new ObjectFactory();
            jaxContent.add(factory.createBPMNShape(jaxbShape));
        }
    }

    @objid ("90f76bd9-071e-49ab-a542-1cdfe869af29")
    private void unmaskLabel(IDiagramNode label, BPMNLabel jaxLabel) {
        Bounds bounds;
        if (label != null) {
            bounds = jaxLabel.getBounds();
            if (bounds != null) {
                Rectangle rect = new Rectangle();
                rect.width = new Double(bounds.getWidth()).intValue();
                rect.height = new Double(bounds.getHeight()).intValue();
                rect.x = new Double(bounds.getX()).intValue();
                rect.y = new Double(bounds.getY()).intValue();
        
                label.setBounds(rect);
            }
        }
    }

    @objid ("246a37d0-25b4-44ce-b34b-f50cc6a533e4")
    private void defineLineOrientation(BPMNPlane plane, IDiagramHandle handle) {
        for (JAXBElement<? extends DiagramElement> jaxDiag : plane.getDiagramElement()) {
            if (jaxDiag.getValue() instanceof BPMNShape) {
                BPMNShape jaxShape = (BPMNShape) jaxDiag.getValue();
                MObject modelioNode = (MObject) this.elementsMap.get(jaxShape.getBpmnElement().getLocalPart());
                if (modelioNode instanceof BpmnLane) {
                    handle.getDiagramNode().setProperty("DIAGRAM_HORIZONTAL_LANES", isHorizontal(jaxShape));
                    break;
                }
            }
        }
    }

    @objid ("dd8bc0b4-cf66-4262-975e-2e012362c08f")
    private IDiagramNode fixBounds(IDiagramHandle handle, MObject modelioNode, Rectangle rect) {
        if (modelioNode instanceof BpmnBoundaryEvent) {
            BpmnActivity activity = ((BpmnBoundaryEvent) modelioNode).getAttachedToRef();
            List<IDiagramGraphic> ownergr = handle.getDiagramGraphics(activity);
        
            if (ownergr != null && !ownergr.isEmpty() && ownergr.get(0) instanceof IDiagramNode) {
                IDiagramNode ownerNode = (IDiagramNode) ownergr.get(0);
                if (rect.x < ownerNode.getBounds().x || rect.x > ownerNode.getBounds().x + ownerNode.getBounds().width) {
                    rect.x = ownerNode.getBounds().x;
                }
                if (rect.y < ownerNode.getBounds().y || rect.y > ownerNode.getBounds().y + ownerNode.getBounds().height) {
                    rect.y = ownerNode.getBounds().y;
                }
                return unmaskNode(modelioNode, rect, handle);
            }
        }
        return null;
    }

    @objid ("3c8a71dd-37dd-4a69-bf77-c326e7f91c0c")
    private void unmaskLabel(BPMNShape jaxShape, Rectangle rect, IDiagramNode dgNode, Map<String, String> labelStyle) {
        BPMNLabel jaxLabel = jaxShape.getBPMNLabel();
        if (dgNode != null) {
        
            List<IDiagramNode> labels = new ArrayList<>(dgNode.getNodes(Role.LABEL));
            if (jaxLabel != null) {
                if (labels.size() > 0) {
                    unmaskLabel(labels.get(0), jaxLabel);
                    if (jaxLabel.getLabelStyle() != null && labelStyle.get(jaxLabel.getLabelStyle().getLocalPart()) != null) {
                        dgNode.setFont(labelStyle.get(jaxLabel.getLabelStyle().getLocalPart()));
                    }
                }
            } else {
                if (labels.size() > 0) {
                    Rectangle rectLabel = new Rectangle(rect);
                    rectLabel.x = rectLabel.x - 10;
                    rectLabel.y = rectLabel.y + rectLabel.height;
                    rectLabel.width = rectLabel.width * 2;
                    labels.get(0).setBounds(rectLabel);
                }
            }
        }
    }

    @objid ("b2ac3b3a-e466-4a38-a288-87c776848ef1")
    @Override
    public void setConfiguration(Map<String, Object> configuration) {
        this.configuration = configuration;
    }

    @objid ("85ee3be6-b8f0-4533-b64a-19be044fd439")
    private Map<String, String> collectLabelStyle(BPMNDiagram jaxbElement) {
        Map<String, String> styles = new HashMap<>();
        for (BPMNLabelStyle style : jaxbElement.getBPMNLabelStyle()) {
            if (style.getFont() != null) {
                String modifier = "-regular-";
        
                if (Boolean.TRUE.equals(style.getFont().isIsBold())) {
                    modifier = "-bold-";
                } else if (Boolean.TRUE.equals(style.getFont().isIsItalic())) {
                    modifier = "-italic-";
                }
                styles.put(style.getId(), style.getFont().getName() + modifier + style.getFont().getSize().intValue());
            }
        }
        return styles;
    }

    @objid ("0819cf87-0f0f-4a9c-b67d-348006de9058")
    private void exportStyles(BPMNDiagram jaxbElement, Map<String, String> labelStyle) {
        for (Entry<String, String> style : labelStyle.entrySet()) {
            if (style.getValue().contains("-")) {
                String[] stylespart = style.getValue().split("-");
                if (stylespart.length == 3) {
                    Font jaxFont = new Font();
                    jaxFont.setName(stylespart[0]);
                    jaxFont.setSize(Double.valueOf(stylespart[2]));
        
                    if ("bold".equals(stylespart[1])) {
                        jaxFont.setIsBold(true);
                    } else {
                        jaxFont.setIsBold(false);
                    }
        
                    if ("italic".equals(stylespart[1])) {
                        jaxFont.setIsItalic(true);
                    } else {
                        jaxFont.setIsItalic(false);
                    }
        
                    BPMNLabelStyle jaxStyle = new BPMNLabelStyle();
                    jaxStyle.setId(style.getKey());
                    jaxStyle.setFont(jaxFont);
        
                    jaxbElement.getBPMNLabelStyle().add(jaxStyle);
                }
            }
        }
    }

}
