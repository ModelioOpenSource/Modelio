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

package org.modelio.diagram.editor.bpmn.wizard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Objects;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.api.modelio.IModelioServices;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessCollaborationDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("da21f72e-ae8a-4fa6-ab75-fdaf5a91557e")
public class UndoSubProcessTransformer implements IModelTransformer {
    @objid ("d09b557c-cfe3-44d4-9fdf-0167cb66281a")
    @Inject
    private IModuleContext moduleContext;

    /**
     * Change the Bpmn owner of 'fe' to 'parent' dealing with the 'Container/SubProcess' duality
     */
    @objid ("e4ca9c18-0603-4b3c-92af-9652129745ad")
    private void setFlowElementParent(BpmnFlowElement fe, MObject parent) {
        if (parent instanceof BpmnSubProcess) {
            fe.setContainer(null);
            fe.setSubProcess((BpmnSubProcess) parent);
        } else if (parent instanceof BpmnProcess) {
            fe.setSubProcess(null);
            fe.setContainer((BpmnProcess) parent);
        }
    }

    @objid ("a78e24a5-7e1b-42bb-a872-40ef6c802937")
    private IDiagramNode getDiagramGraphic(IDiagramHandle dh, MObject e) {
        List<IDiagramGraphic> dgs = dh.getDiagramGraphics(e);
        if (!dgs.isEmpty()) {
            return (IDiagramNode) dgs.get(0);
        } else {
            return null;
        }
    }

    @objid ("ebf690fc-cbb0-4ea2-8c44-11a14755fe2e")
    @Override
    public List<MObject> transform(AbstractDiagram diagram, ISelection selection) {
        BpmnSubProcess subProcess = SelectionHelper.getFirst(selection, BpmnSubProcess.class);
        
        Map<MObject, Rectangle> unmaskCoordinates = new HashMap<>();
        
        // open the process diagram to collect the geometry of the selection
        IModelioServices modelioServices = this.moduleContext.getModelioServices();
        try (IDiagramHandle dh = modelioServices.getDiagramService().getDiagramHandle(diagram)) {
            dh.setBatchMode(true);
        
            // open the subprocess diagram
            Rectangle spBounds = getDiagramGraphic(dh, subProcess).getBounds().getCopy();
            MObject parent = subProcess.getCompositionOwner();
        
            // get subprocess nodes
            BpmnStartEvent startEvent = null;
            BpmnEndEvent endEvent = null;
            List<BpmnFlowElement> nodes = new ArrayList<>(); // all sp internal nodes excepted start and end events
            List<BpmnSequenceFlow> links = new ArrayList<>();
        
            for (BpmnFlowElement n : new ArrayList<>(subProcess.getFlowElement())) {
                if (n instanceof BpmnStartEvent) {
                    if (!((BpmnStartEvent) n).getOutgoing().isEmpty()) {
                        startEvent = (BpmnStartEvent) n;
                    } else {
                        // Delete start events not having outgoing links
                        n.delete();
                    }
                } else if (n instanceof BpmnEndEvent) {
                    if (!((BpmnEndEvent) n).getIncoming().isEmpty()) {
                        endEvent = (BpmnEndEvent) n;
                    } else {
                        // Delete end events not having incoming links
                        n.delete();
                    }
                } else if (n instanceof BpmnSequenceFlow) {
                    links.add((BpmnSequenceFlow) n);
                } else {
                    nodes.add(n);
                }
            }
        
            // transform the model
            BpmnFlowNode inputNode = null;
            BpmnFlowNode outputNode = null;
        
            // 1 - processing 'inputNode'
            // - if the subprocess startEvent has several outgoing flows we have several 'beginning tasks' in the subprocess.
            // In this case we create a BpmnComplexGateway in the process diagram to replace the sp startEvent. The created gateway becomes the 'inputNode' of the transformation.
            // - if the subprocess startEvent has a unique outgoing flow, the target of this flow will directly be the 'inputNode' node for the transformation
        
            // Once the 'inputNode' node has been determined, the subprocess incoming flows shall be rerouted towards the 'inputNode'
            boolean createStartGateway = startEvent == null || startEvent.getOutgoing().size() > 1;
            if (startEvent == null || createStartGateway) {
                inputNode = this.moduleContext.getModelingSession().getModel().createBpmnComplexGateway();
                inputNode.setName("input node");
            } else {
                inputNode = startEvent.getOutgoing().get(0).getTargetRef();
            }
        
            IDiagramNode inputDg = getDiagramGraphic(dh, inputNode);
            setFlowElementParent(inputNode, parent);
        
            if (inputDg != null) {
                unmaskCoordinates.put(inputNode, inputDg.getBounds().getCopy());
                inputDg.mask();
                inputDg = null;
            } else {
                unmaskCoordinates.put(inputNode, new Rectangle(spBounds.x, spBounds.y + spBounds.height / 2, -1, -1));
            }
        
            // 2 - processing 'outputNode'
            // - if the subprocess endEvent has several incomings flows we have several 'ending tasks' in the subprocess.
            // In this case we create a BpmnComplexGateway in the process diagram to replace the sp endEvent. The created gateway becomes the 'outputNode' of the transformation
            // - if the subprocess endEvent has a unique outgoing flow, the source of this flow will be the 'outputNode' of the transformation
            //
            // Once the 'outputNode' node has been determined, the subprocess outgoing flows shall be rerouted from the 'outputNode'
            boolean createEndGateway = endEvent == null || endEvent.getIncoming().size() > 1;
            if (endEvent == null || createEndGateway) {
                outputNode = this.moduleContext.getModelingSession().getModel().createBpmnComplexGateway();
                outputNode.setName("output node");
            } else {
                outputNode = endEvent.getIncoming().get(0).getSourceRef();
            }
        
            IDiagramNode outputDg = getDiagramGraphic(dh, outputNode);
            setFlowElementParent(outputNode, parent);
        
            if (outputDg != null) {
                unmaskCoordinates.put(outputNode, outputDg.getBounds().getCopy());
                outputDg.mask();
                outputDg = null;
            } else if (outputNode != inputNode) {
                unmaskCoordinates.put(outputNode, new Rectangle(spBounds.x + spBounds.width, spBounds.y + spBounds.height / 2, -1, -1));
            }
        
            // 3 - once the 'inputNode' and 'outputNode' nodes of the transformation have been properly set up and connected to 'incoming and outgoing links'
            // Just move the sp internal nodes and flows in the Process
            for (BpmnFlowElement n : nodes) {
                if (!Objects.equals(n, inputNode) && !Objects.equals(n, outputNode)) {
                    setFlowElementParent(n, parent);
                    IDiagramNode nodeDg = getDiagramGraphic(dh, n);
                    if (nodeDg != null) {
                        unmaskCoordinates.put(n, nodeDg.getBounds().getCopy());
                        nodeDg.mask();
                        nodeDg = null;
                    } else {
                        unmaskCoordinates.put(n, new Rectangle(0, 0, -1, -1));
                    }
                }
            }
        
            for (BpmnSequenceFlow l : links) {
                if (Objects.equals(l.getSourceRef(), startEvent)) {
                    if (startEvent == null || createStartGateway) {
                        l.setSourceRef(inputNode);
                        setFlowElementParent(l, parent);
                    } else {
                        l.delete();
                    }
                } else if (Objects.equals(l.getTargetRef(), endEvent)) {
                    if (endEvent == null || createEndGateway) {
                        setFlowElementParent(l, parent);
                        l.setTargetRef(outputNode);
                    } else {
                        l.delete();
                    }
                } else {
                    setFlowElementParent(l, parent);
                }
            }
        
            if (createStartGateway && subProcess.getIncoming().size() == 1) {
                BpmnSequenceFlow flow = subProcess.getIncoming().get(0);
        
                List<IDiagramGraphic> dgs = dh.getDiagramGraphics(flow);
                if (!dgs.isEmpty()) {
                    IDiagramLink flowDG = (IDiagramLink) dgs.get(0);
                    Point lastPoint = flowDG.getPath().getPoints().get(flowDG.getPath().getPoints().size() - 1);
        
                    Rectangle bounds = unmaskCoordinates.get(inputNode);
                    switch (GeomUtils.getDirection(lastPoint, spBounds)) {
                    case NORTH:
                        bounds.setLocation(lastPoint.x() - 25, lastPoint.y());
                        break;
                    case SOUTH:
                        bounds.setLocation(lastPoint.x() - 25, lastPoint.y() - 50);
                        break;
                    case EAST:
                        bounds.setLocation(lastPoint.x() - 50, lastPoint.y() - 25);
                        break;
                    case WEST:
                        bounds.setLocation(lastPoint.x(), lastPoint.y() - 25);
                        break;
                    default:
                        break;
                    }
                }
            }
        
            // Reroute the former subprocess incoming links: targeted to the 'inputNode'
            for (BpmnSequenceFlow flow : new ArrayList<>(subProcess.getIncoming())) {
                flow.setTargetRef(inputNode);
            }
        
            if (createEndGateway && subProcess.getOutgoing().size() == 1) {
                BpmnSequenceFlow flow = subProcess.getOutgoing().get(0);
        
                List<IDiagramGraphic> dgs = dh.getDiagramGraphics(flow);
                if (!dgs.isEmpty()) {
                    IDiagramLink flowDG = (IDiagramLink) dgs.get(0);
                    Point firstPoint = flowDG.getPath().getPoints().get(0);
        
                    Rectangle bounds = unmaskCoordinates.get(outputNode);
                    switch (GeomUtils.getDirection(firstPoint, spBounds)) {
                    case NORTH:
                        bounds.setLocation(firstPoint.x() - 25, firstPoint.y());
                        break;
                    case SOUTH:
                        bounds.setLocation(firstPoint.x() - 25, firstPoint.y() - 50);
                        break;
                    case EAST:
                        bounds.setLocation(firstPoint.x() - 50, firstPoint.y() - 25);
                        break;
                    case WEST:
                        bounds.setLocation(firstPoint.x(), firstPoint.y() - 25);
                        break;
                    default:
                        break;
                    }
                }
            }
        
            // Reroute the former subprocess outgoing links: sourced to the 'outputNode'
            for (BpmnSequenceFlow flow : new ArrayList<>(subProcess.getOutgoing())) {
                flow.setSourceRef(outputNode);
            }
        
            // 4 - clean up
            if (startEvent != null) {
                startEvent.delete();
            }
            if (endEvent != null) {
                endEvent.delete();
            }
            subProcess.delete();
        
            dh.setBatchMode(false);
            dh.save();
        
            for (Entry<MObject, Rectangle> e : unmaskCoordinates.entrySet()) {
                MObject elt = e.getKey();
                Rectangle coordinates = e.getValue();
        
                dh.unmask(elt, coordinates.x, coordinates.y);
                IDiagramNode dg = getDiagramGraphic(dh, elt);
                dg.setLocation(coordinates.x, coordinates.y);
                if (!coordinates.getSize().isEmpty()) {
                    dg.setSize(coordinates.width(), coordinates.height());
                }
            }
        
            dh.save();
        }
        return Collections.emptyList();
    }

    @objid ("77c82c62-f7f8-486e-8fc8-a7e5e75a41e0")
    @Override
    public boolean isAvailable(AbstractDiagram diagram, ISelection selection) {
        // One selected element only
        if (SelectionHelper.size(selection) != 1) {
            return false;
        }
        
        // In a BPMN diagram
        if (!(diagram instanceof BpmnSubProcessDiagram) && !(diagram instanceof BpmnProcessCollaborationDiagram)) {
            return false;
        }
        
        // Must be a BpmnSubProcess
        return SelectionHelper.contains(selection, BpmnSubProcess.class);
    }

    @objid ("c1b62e8b-3a69-410e-b7ae-6972d44e77c8")
    @Override
    public boolean canExecute(AbstractDiagram diagram, ISelection selection) {
        for (BpmnSubProcess subProcess : SelectionHelper.toList(selection, BpmnSubProcess.class)) {
            if (subProcess.getFlowElement(BpmnStartEvent.class).size() != 1 || subProcess.getFlowElement(BpmnEndEvent.class).size() != 1 || subProcess.getProduct(BpmnSubProcessDiagram.class).isEmpty()) {
                return false;
            }
        }
        return true;
    }

}
