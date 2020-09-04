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

package org.modelio.diagram.editor.bpmn.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This model transformer gathers every selected {@link BpmnFlowNode} into a {@link BpmnSubProcess},
 * keeping the link's layout between them.
 * <p>
 * Flows which source or target is not moved into the sub process are considered outside the scope and
 * are rerouted to link the {@link BpmnSubProcess} itself instead.
 * </p>
 */
@objid ("8ca1f2b9-6dda-4366-958c-9247589724bf")
public class GroupAsSubProcessTransformer implements IModelTransformer {
    @objid ("cabe852a-3c47-40e7-b058-c86c0988d36c")
    private List<BpmnSequenceFlow> inLinks = new ArrayList<>();

    @objid ("2d1bd20a-f48c-446c-82ce-01cf8317df76")
    private List<BpmnSequenceFlow> outLinks = new ArrayList<>();

    @objid ("5789bdd6-de52-4159-b3c3-90504640f885")
    private Set<BpmnSequenceFlow> links = new HashSet<>();

    @objid ("8cf6e259-004f-4483-86f4-16637b8431e1")
    private List<BpmnFlowNode> nodes = new ArrayList<>();

    @objid ("0474bdfd-23ff-4833-9059-07f65718e0e4")
    @Inject
    private IModuleContext moduleContext;

    /**
     * Criteria: - process design diagram or subprocess diagram - selection contains only flow nodes (non-Javadoc)
     * @see IModelTransformer#isAvailable(AbstractDiagram, ISelection)
     */
    @objid ("1e2098b1-f3d8-4bf1-ade2-0519b17a40bb")
    @Override
    public boolean isAvailable(AbstractDiagram diagram, ISelection selection) {
        List<MObject> elements = SelectionHelper.toList(selection, MObject.class);
        if (!parse(elements)) {
            return false;
        }
        
        // In a BPMN diagram
        if (!(diagram instanceof BpmnSubProcessDiagram) && !(diagram instanceof BpmnProcessDesignDiagram)) {
            return false;
        }
        return true;
    }

    /**
     * Criteria: - process design diagram or subprocess diagram - selection conforms to the processable selection scheme: only flow nodes, homogeneous incoming and outgoing links. (non-Javadoc)
     * @see IModelTransformer#isAvailable(AbstractDiagram, ISelection)
     */
    @objid ("dbf0ed60-33df-4dd4-bdf9-fdaafc82d7e0")
    @Override
    public boolean canExecute(AbstractDiagram diagram, ISelection selection) {
        List<MObject> elements = SelectionHelper.toList(selection, MObject.class);
        if (!parse(elements)) {
            return false;
        }
        
        for (BpmnFlowNode fn : this.nodes) {
            if (fn instanceof BpmnStartEvent || fn instanceof BpmnEndEvent) {
                return false;
            }
        }
        
        if (this.inLinks.size() > 1) {
            BpmnFlowNode ref = this.inLinks.get(0).getTargetRef();
            for (BpmnSequenceFlow il : this.inLinks) {
                if (!Objects.equals(ref, il.getTargetRef())) {
                    // requires either exactly 0..1 input flow or several input flows to the same activity node
                    return false;
                }
            }
        }
        
        if (this.outLinks.size() > 1) {
            BpmnFlowNode ref = this.outLinks.get(0).getSourceRef();
            for (BpmnSequenceFlow ol : this.outLinks) {
                if (!Objects.equals(ref, ol.getSourceRef())) {
                    // requires either exactly 0..1 output flow or several out flows from the same activity node
                    return false;
                }
            }
        }
        return elements.size() > 0;
    }

    @objid ("0c9d58a2-8734-4d5a-8c5b-c109fd7a9e8c")
    private boolean parse(List<MObject> elements) {
        this.inLinks.clear();
        this.outLinks.clear();
        this.links.clear();
        this.nodes.clear();
        
        for (MObject elt : elements) {
            if (!(elt instanceof BpmnFlowNode)) {
                return false;
            }
            BpmnFlowNode node = (BpmnFlowNode) elt;
            for (BpmnSequenceFlow flow : node.getOutgoing()) {
                if (elements.contains(flow.getTargetRef())) {
                    // subprocess internal link
                    this.links.add(flow);
                } else {
                    // subprocess outgoing link
                    this.outLinks.add(flow);
                }
            }
            for (BpmnSequenceFlow flow : node.getIncoming()) {
                if (elements.contains(flow.getSourceRef())) {
                    // subprocess internal link
                    this.links.add(flow);
                } else {
                    // subprocess incoming link
                    this.inLinks.add(flow);
                }
            }
            this.nodes.add(node);
        }
        return true;
    }

    @objid ("ab8b7bac-9dea-4f99-958d-7f9091389d3e")
    @Override
    public List<MObject> transform(AbstractDiagram diagram, ISelection selection) {
        List<MObject> elements = SelectionHelper.toList(selection, MObject.class);
        parse(elements);
        
        List<BpmnFlowNode> flowNodes = elements.stream()
                .filter(e -> e instanceof BpmnFlowNode)
                .map(e -> (BpmnFlowNode) e)
                .collect(Collectors.toList());
        
        IDiagramService ds = this.moduleContext.getModelioServices().getDiagramService();
        
        try (IDiagramHandle dh = ds.getDiagramHandle(diagram)) {
            Map<Object, Rectangle> selectionGeometry = getSelectionGeometry(dh, flowNodes);
        
            // Create the sub process and adapt the model
            Object[] results = createSubprocess(flowNodes.get(0).getCompositionOwner());
        
            BpmnSubProcess sp = (BpmnSubProcess) results[0];
            BpmnSubProcessDiagram spdiagram = (BpmnSubProcessDiagram) results[1];
            BpmnStartEvent spStartEvent = (BpmnStartEvent) results[2];
            BpmnEndEvent spEndEvent = (BpmnEndEvent) results[3];
        
            // Layout the subprocess diagram
            Point spTranslation = new Point(selectionGeometry.get("global").getTopLeft().negate());
            layoutSubprocessDiagram(ds, selectionGeometry, spdiagram, spStartEvent, spEndEvent, spTranslation);
        
            for (BpmnFlowNode e : flowNodes) {
                IDiagramGraphic dg = getDiagramGraphic(dh, e);
                if (dg != null) {
                    dg.mask();
                }
            }
        
            // Update the layout of the process diagram
            // Adjust sub process size and location in the process diagram
            dh.save();
        
            IDiagramNode dg = (IDiagramNode) getDiagramGraphic(dh, sp);
            if (dg != null) {
                dg.setBounds(selectionGeometry.get("global").getCopy());
                dg.setProperty("SUBPROCESS_SHOWCONTENT", "true");
            }
            dh.save();
            dh.close();
            return Arrays.asList(sp);
        }
    }

    @objid ("70a9b126-3561-470d-a8e5-749015668e07")
    private void layoutSubprocessDiagram(IDiagramService ds, Map<Object, Rectangle> selectionGeometry, BpmnSubProcessDiagram spdiagram, BpmnStartEvent spStartEvent, BpmnEndEvent spEndEvent, Point translation) {
        try (IDiagramHandle dhsp = ds.getDiagramHandle(spdiagram)) {
        
            Point offset = new Point(translation);
            IDiagramNode dgStart = (IDiagramNode) getDiagramGraphic(dhsp, spStartEvent);
            if (dgStart != null) {
                offset = translation.getTranslated(dgStart.getBounds().width() + 20, 0);
            }
        
            for (BpmnFlowNode e : this.nodes) {
                Rectangle bounds = selectionGeometry.get(e).translate(offset);
        
                IDiagramNode dg1 = (IDiagramNode) getDiagramGraphic(dhsp, e);
                if (dg1 == null) {
                    List<IDiagramGraphic> dgs = dhsp.unmask(e, bounds.x, bounds.y);
                    dg1 = dgs.isEmpty() ? null : (IDiagramNode) dgs.get(0);
                }
                if (dg1 != null) {
                    dg1.setBounds(bounds);
                }
            }
        
            if (dgStart != null) {
                if (!spStartEvent.getOutgoing().isEmpty()) {
                    Rectangle linkedNodeBounds = selectionGeometry.get(spStartEvent.getOutgoing().get(0).getTargetRef());
                    int x = linkedNodeBounds.x() - dgStart.getBounds().width() - 20;
                    int y = linkedNodeBounds.y() + linkedNodeBounds.height() / 2 - dgStart.getBounds().height() / 2;
                    dgStart.setLocation(x, y);
                }
            }
        
            IDiagramNode dgEnd = (IDiagramNode) getDiagramGraphic(dhsp, spEndEvent);
            if (dgEnd != null) {
                if (!spEndEvent.getIncoming().isEmpty()) {
                    Rectangle linkedNodeBounds = selectionGeometry.get(spEndEvent.getIncoming().get(0).getSourceRef());
                    int x = linkedNodeBounds.x() + linkedNodeBounds.width() + 20;
                    int y = linkedNodeBounds.y() + linkedNodeBounds.height() / 2 - dgEnd.getBounds().height() / 2;
                    dgEnd.setLocation(x, y);
                } else {
                    Rectangle r = selectionGeometry.get("global");
                    int x = r.width() + 20;
                    int y = r.height() + 20;
                    dgEnd.setLocation(x, y);
                }
            }
        
            dhsp.save();
            dhsp.close();
        }
    }

    @objid ("ecc2cf3e-d303-40fa-828e-a991baa6e777")
    private IDiagramGraphic getDiagramGraphic(IDiagramHandle dh, BpmnBaseElement o) {
        List<IDiagramGraphic> dgs = dh.getDiagramGraphics(o);
        return dgs.isEmpty() ? null : dgs.get(0);
    }

    /**
     * Change the Bpmn owner of 'fe' to 'parent' dealing with the 'Container/SubProcess' duality
     */
    @objid ("ce404017-6624-4e51-b0ab-f1bfb5efc8d4")
    private static void setFlowElementParent(BpmnFlowElement fe, MObject parent) {
        if (parent instanceof BpmnSubProcess) {
            fe.setContainer(null);
            fe.setSubProcess((BpmnSubProcess) parent);
        } else if (parent instanceof BpmnProcess) {
            fe.setSubProcess(null);
            fe.setContainer((BpmnProcess) parent);
        }
    }

    @objid ("ecec32bd-383b-4bb9-bca0-695de902ab88")
    private Object[] createSubprocess(MObject owner) {
        List<BpmnLane> lanes = this.nodes.isEmpty() ? Collections.emptyList() : this.nodes.get(0).getLane();
        Object[] results = new Object[4];
        
        IModelingSession session = this.moduleContext.getModelingSession();
        
        // Create a sub process
        BpmnSubProcess sp = session.getModel().createBpmnSubProcess();
        
        setFlowElementParent(sp, owner);
        sp.setName("subprocess");
        
        for (BpmnLane lane : lanes) {
            lane.getFlowElementRef().add(sp);
        }
        
        // Create a subprocess diagram
        BpmnSubProcessDiagram diagram = session.getModel().createBpmnSubProcessDiagram();
        diagram.setOrigin(sp);
        diagram.setName("sub process diagram");
        
        // Create a 'start' event in the subprocess
        BpmnStartEvent startEvent = session.getModel().createBpmnStartEvent();
        setFlowElementParent(startEvent, sp);
        
        // Create an 'end' event in the subprocess
        BpmnEndEvent endEvent = session.getModel().createBpmnEndEvent();
        setFlowElementParent(endEvent, sp);
        
        // Create a flow from the subprocess start event to the common target of the 'inLinks' (if present)
        if (!this.inLinks.isEmpty()) {
            BpmnFlowNode spInTarget = this.inLinks.get(0).getTargetRef();
            BpmnSequenceFlow startFlow = session.getModel().createBpmnSequenceFlow();
            setFlowElementParent(startFlow, sp);
            startFlow.setName("begin");
            startFlow.setSourceRef(startEvent);
            startFlow.setTargetRef(spInTarget);
        
            // Change the 'inLinks' target to the subprocess
            for (BpmnSequenceFlow il : this.inLinks) {
                il.setTargetRef(sp);
                il.getLane().clear();
            }
        }
        
        // Create a flow from the common source of the 'outlinks' to the subprocess end event
        if (!this.outLinks.isEmpty()) {
            BpmnFlowNode spOutSource = this.outLinks.get(0).getSourceRef();
            BpmnSequenceFlow endFlow = session.getModel().createBpmnSequenceFlow();
            setFlowElementParent(endFlow, sp);
            endFlow.setName("end");
            endFlow.setSourceRef(spOutSource);
            endFlow.setTargetRef(endEvent);
        
            // Change the 'outLinks' source to the subprocess
            for (BpmnSequenceFlow ol : this.outLinks) {
                ol.setSourceRef(sp);
                ol.getLane().clear();
            }
        }
        
        for (BpmnFlowNode e : this.nodes) {
            setFlowElementParent(e, sp);
            e.getDiagramElement().add(diagram);
            e.getLane().clear();
        }
        
        for (BpmnSequenceFlow f : this.links) {
            setFlowElementParent(f, sp);
            f.getDiagramElement().add(diagram);
            f.getLane().clear();
        }
        
        results[0] = sp;
        results[1] = diagram;
        results[2] = startEvent;
        results[3] = endEvent;
        return results;
    }

    @objid ("f0b3d746-0b6b-4b9c-98b1-410c39f125bd")
    private Map<Object, Rectangle> getSelectionGeometry(IDiagramHandle dh, List<BpmnFlowNode> elements) {
        Map<Object, Rectangle> results = new HashMap<>();
        
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        
        for (BpmnFlowNode e : elements) {
            IDiagramGraphic dg = getDiagramGraphic(dh, e);
            if (dg != null) {
                Rectangle bounds = ((IDiagramNode) dg).getBounds().getCopy();
                results.put(e, bounds);
                minX = Math.min(bounds.x(), minX);
                maxX = Math.max(bounds.x() + bounds.width(), maxX);
                minY = Math.min(bounds.y(), minY);
                maxY = Math.max(bounds.y() + bounds.height(), maxY);
            }
        }
        
        results.put("global", new Rectangle(minX, minY, maxX - minX, maxY - minY));
        return results;
    }

}
