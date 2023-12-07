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
package org.modelio.bpmn.diagram.editor.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.diagram.ILinkPoint;
import org.modelio.api.modelio.diagram.ILinkRoute;
import org.modelio.api.modelio.diagram.InvalidLinkPathException;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnAssociation;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This model transformer gathers every selected {@link BpmnFlowNode} into a {@link BpmnSubProcess}, keeping the link's layout between them.
 * <p>
 * Flows which source or target is not moved into the sub process are considered outside the scope and are rerouted to link the {@link BpmnSubProcess} itself instead.
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

    @objid ("0221badf-1558-497d-bd87-2d59697a257c")
    private Set<BpmnLane> lanes = new HashSet<>();

    @objid ("9e1ddf0f-30bd-4248-a1fe-f782a6d1d3af")
    private List<BpmnAssociation> inputAssociation;

    @objid ("c7da74fa-77ec-4a65-b0ba-bd9a3ecafc86")
    private List<BpmnAssociation> outputAssociation;

    @objid ("5cb0cd7a-6dc8-4571-aa29-921660f705f6")
    private List<BpmnAssociation> assocLinks;

    @objid ("8cf6e259-004f-4483-86f4-16637b8431e1")
    private List<BpmnFlowNode> nodes = new ArrayList<>();

    @objid ("0474bdfd-23ff-4833-9059-07f65718e0e4")
    @Inject
    private IModuleContext moduleContext;

    /**
     * Criteria:
     * <ul>
     * <li>process design diagram or subprocess diagram
     * <li>selection contains only flow nodes
     * </ul>
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
     * Criteria:
     * <ul>
     * <li>process design diagram or subprocess diagram
     * <li>selection conforms to the processable selection scheme: only flow nodes, homogeneous incoming and outgoing links.
     * </ul>
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
        return !this.nodes.isEmpty();
    }

    @objid ("0c9d58a2-8734-4d5a-8c5b-c109fd7a9e8c")
    private boolean parse(List<MObject> elements) {
        this.inLinks.clear();
        this.outLinks.clear();
        this.links.clear();
        this.nodes.clear();
        
        for (MObject elt : elements) {
            if (elt instanceof BpmnFlowNode) {
                BpmnFlowNode node = (BpmnFlowNode) elt;
                if (this.nodes.contains(node)) {
                    continue;
                }
        
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
        
                for (BpmnAssociation flow : node.getOutgoingAssoc()) {
                    if (elements.contains(flow.getTargetRef())) {
                        // subprocess internal link
                        this.assocLinks.add(flow);
                    } else {
                        // subprocess outgoing link
                        this.outputAssociation.add(flow);
                    }
                }
        
                for (BpmnAssociation flow : node.getIncomingAssoc()) {
                    if (elements.contains(flow.getTargetRef())) {
                        // subprocess internal link
                        this.assocLinks.add(flow);
                    } else {
                        // subprocess outgoing link
                        this.inputAssociation.add(flow);
                    }
                }
                this.nodes.add(node);
                this.lanes.addAll(node.getLane());
            }
        
        }
        return true;
    }

    @objid ("ab8b7bac-9dea-4f99-958d-7f9091389d3e")
    @Override
    public List<MObject> transform(AbstractDiagram diagram, ISelection selection) {
        List<MObject> elementsToMove = SelectionHelper.toList(selection, MObject.class);
        
        parse(elementsToMove);
        
        MObject owner = this.nodes.get(0).getCompositionOwner();
        
        Map<Object, Rectangle> nodeGeometry = getNodeGeometry(diagram);
        Map<MObject, ILinkRoute> linkGeometry = getLinkGeometry(diagram);
        BpmnProcess temporaryProcess = storeTemporaryElement(owner);
        BpmnSubProcess subprocess = createSubprocess(owner, nodeGeometry, linkGeometry, diagram);
        
        cleanTemporaryElements(temporaryProcess);
        
        // Point spTranslation = new Point(nodeGeometry.get("global").getTopLeft().negate());
        // layoutSubprocessDiagram(ds, nodeGeometry, linkGeometry, spdiagram, spTranslation);
        
        // IDiagramService ds = this.moduleContext.getModelioServices().getDiagramService();
        // Map<Object, Rectangle> nodeGeometry;
        // Map<MObject, ILinkRoute> linkGeometry;
        //
        // try (ITransaction tr = this.moduleContext.getModelingSession().createTransaction("sub1")) {
        // try (IDiagramHandle dh = ds.getDiagramHandle(diagram)) {
        // nodeGeometry = getNodeGeometry(dh, flowNodes);
        // linkGeometry = getLinkGeometry(dh, this.links);
        //
        // // Mask links to the new SubProcess, the diagram will unmask them automatically with a proper layout
        // for (BpmnSequenceFlow incoming : this.outLinks) {
        // for (IDiagramGraphic incomingDg : dh.getDiagramGraphics(incoming)) {
        // incomingDg.mask();
        // }
        // }
        //
        // // Mask links from the new SubProcess, the diagram will unmask them automatically with a proper layout
        // for (BpmnSequenceFlow outgoing : this.inLinks) {
        // for (IDiagramGraphic outgoingDg : dh.getDiagramGraphics(outgoing)) {
        // outgoingDg.mask();
        // }
        // }
        //
        // dh.save();
        // }
        //
        // tr.commit();
        // }
        
        // BpmnSubProcess sp;
        // BpmnSubProcessDiagram spdiagram;
        // try (ITransaction tr = this.moduleContext.getModelingSession().createTransaction("sub1")) {
        //
        // Object[] results = createSubprocess(flowNodes.get(0).getCompositionOwner());
        //
        // sp = (BpmnSubProcess) results[0];
        // spdiagram = (BpmnSubProcessDiagram) results[1];
        //
        // // Atach Links
        // for (BpmnSequenceFlow incoming : this.inLinks) {
        // incoming.setTargetRef(sp);
        // }
        //
        // for (BpmnSequenceFlow outgoing : this.outLinks) {
        // outgoing.setSourceRef(sp);
        // }
        // tr.commit();
        // }
        // // Mask Moved Elements+
        //
        // try (IDiagramHandle dh = ds.getDiagramHandle(diagram)) {
        // for (BpmnSequenceFlow e : this.links) {
        // IDiagramGraphic dg = getDiagramGraphic(dh, e);
        // if (dg != null) {
        // dg.mask();
        // }
        // }
        //
        // for (BpmnFlowNode e : flowNodes) {
        // IDiagramGraphic dg = getDiagramGraphic(dh, e);
        // if (dg != null) {
        // dg.mask();
        // }
        // }
        //
        // IDiagramNode dg = (IDiagramNode) getDiagramGraphic(dh, sp);
        // if (dg != null) {
        //
        // dg.setBounds(nodeGeometry.get("global").getCopy());
        // dg.setProperty("SUBPROCESS_SHOWCONTENT", "false");
        // } else {
        // dh.unmask(sp, 0, 0);
        // }
        //
        // // Mask links to the new SubProcess, the diagram will unmask them automatically with a proper layout
        // for (BpmnSequenceFlow incoming : sp.getIncoming()) {
        // for (IDiagramGraphic incomingDg : dh.getDiagramGraphics(incoming)) {
        // incomingDg.mask();
        // }
        // }
        //
        // // Mask links from the new SubProcess, the diagram will unmask them automatically with a proper layout
        // for (BpmnSequenceFlow outgoing : sp.getOutgoing()) {
        // for (IDiagramGraphic outgoingDg : dh.getDiagramGraphics(outgoing)) {
        // outgoingDg.mask();
        // }
        // }
        // dh.save();
        // }
        
        // Create the sub process and adapt the model
        
        // Layout the subprocess diagram
        // Point spTranslation = new Point(nodeGeometry.get("global").getTopLeft().negate());
        // layoutSubprocessDiagram(ds, nodeGeometry, linkGeometry, spdiagram, spTranslation);
        return Arrays.asList(subprocess);
    }

    @objid ("74a94d4f-78d3-4630-a1ff-f749b0f6d689")
    private void layoutSubProcess(AbstractDiagram diagram, BpmnSubProcess subprocess, Map<Object, Rectangle> nodeGeometry, Map<MObject, ILinkRoute> linkGeometry) {
        try (ITransaction tr = this.moduleContext.getModelingSession().createTransaction("SubProcessLayout")) {
            IDiagramService ds = this.moduleContext.getModelioServices().getDiagramService();
            try (IDiagramHandle dh = ds.getDiagramHandle(diagram)) {
                IDiagramNode dg = (IDiagramNode) getDiagramGraphic(dh, subprocess);
                if (dg != null) {
                    dg.setBounds(nodeGeometry.get("global").getCopy());
                    dg.setProperty("SUBPROCESS_SHOWCONTENT", "false");
                } else {
                    dg = (IDiagramNode) dh.unmask(subprocess, nodeGeometry.get("global").x, nodeGeometry.get("global").y).get(0);
                    if (dg != null) {
                        dg.setBounds(nodeGeometry.get("global").getCopy());
                        dg.setProperty("SUBPROCESS_SHOWCONTENT", "false");
                    }
                }
        
                for (BpmnSequenceFlow f : this.inLinks) {
                    IDiagramLink dg1 = (IDiagramLink) getDiagramGraphic(dh, f);
                    if (dg1 != null) {
                        dh.mask(dg1);
                        dh.unmask(f, 0, 0);
                    }
        
                }
        
                for (BpmnSequenceFlow f : this.outLinks) {
                    IDiagramLink dg1 = (IDiagramLink) getDiagramGraphic(dh, f);
                    if (dg1 != null) {
                        dh.mask(dg1);
                        dh.unmask(f, 0, 0);
                    }
        
                }
        
                for (BpmnSequenceFlow e : this.links) {
                    IDiagramGraphic ldg = getDiagramGraphic(dh, e);
                    if (ldg != null) {
                        ldg.mask();
                    }
                }
                dh.save();
            }
            tr.commit();
        }
        
    }

    @objid ("c41016f3-1a81-4a84-bbe8-5e668d1d0300")
    private void cleanTemporaryElements(BpmnProcess temporaryProcess) {
        try (ITransaction tr = this.moduleContext.getModelingSession().createTransaction("SubProcessLayout")) {
            temporaryProcess.delete();
            tr.commit();
        }
        
    }

    @objid ("a7a4e31c-d730-48cb-8074-508d4378e669")
    private BpmnProcess storeTemporaryElement(MObject owner) {
        IModelingSession session = this.moduleContext.getModelingSession();
        
        while (!(owner instanceof NameSpace)) {
            owner = owner.getCompositionOwner();
        }
        
        BpmnProcess temporaryProcess = null;
        try (ITransaction tr = this.moduleContext.getModelingSession().createTransaction("TemporarySubProcessCreation")) {
            temporaryProcess = session.getModel().createBpmnProcess();
            temporaryProcess.setOwner((NameSpace) owner);
            for (BpmnFlowNode move : this.nodes) {
                move.setContainer(temporaryProcess);
                move.getLane().clear();
            }
        
            for (BpmnSequenceFlow move : this.links) {
                move.setContainer(temporaryProcess);
                move.getLane().clear();
            }
            tr.commit();
        }
        return temporaryProcess;
    }

    @objid ("70a9b126-3561-470d-a8e5-749015668e07")
    private void layoutSubprocessDiagram(IDiagramService ds, Map<Object, Rectangle> nodeGeometry, Map<MObject, ILinkRoute> linkGeometry, BpmnSubProcessDiagram spdiagram, Point translation) {
        try (IDiagramHandle dhsp = ds.getDiagramHandle(spdiagram)) {
            final Point offset = new Point(translation);
            layoutNodes(dhsp, nodeGeometry, offset);
        
            layoutLinks(dhsp, linkGeometry, offset);
        
            dhsp.save();
        }
        
    }

    @objid ("443b730e-3b21-4af1-8329-29fc2b51a14f")
    private void layoutNodes(IDiagramHandle dhsp, Map<Object, Rectangle> nodeGeometry, final Point offset) {
        // Get nodes back in place
        for (BpmnFlowNode e : this.nodes) {
            Rectangle bounds = nodeGeometry.get(e).translate(offset);
        
            IDiagramNode dg1 = (IDiagramNode) getDiagramGraphic(dhsp, e);
            if (dg1 == null) {
                List<IDiagramGraphic> dgs = dhsp.unmask(e, bounds.x, bounds.y);
                dg1 = dgs.isEmpty() ? null : (IDiagramNode) dgs.get(0);
            }
            if (dg1 != null) {
                dg1.setBounds(bounds);
            }
        }
        
    }

    @objid ("5ad27bb7-a6d0-4968-b4b6-9096a3bdbf40")
    private void layoutLinks(IDiagramHandle dhsp, Map<MObject, ILinkRoute> linkGeometry, final Point offset) {
        // Now that nodes are placed, fix the link layout
        final Point tmp = new Point();
        for (BpmnSequenceFlow f : this.links) {
            ILinkRoute linkPath = linkGeometry.get(f);
            if (linkPath != null) {
                IDiagramLink dg1 = (IDiagramLink) getDiagramGraphic(dhsp, f);
                if (dg1 == null) {
                    List<IDiagramGraphic> dgs = dhsp.unmask(f, 0, 0);
                    dg1 = dgs.isEmpty() ? null : (IDiagramLink) dgs.get(0);
                }
        
                if (dg1 != null) {
                    try {
                        List<ILinkPoint> points = linkPath.getAllPoints();
                        for (ILinkPoint p : points) {
                            p.setLocation(p.getLocation(tmp).translate(offset));
                        }
                        dg1.setRoute(linkPath);
                    } catch (@SuppressWarnings ("unused") InvalidLinkPathException e1) {
                        // Invalid path, let the auto-unmask do its job
                        dg1.mask();
                    }
                }
            }
        }
        
    }

    @objid ("ecc2cf3e-d303-40fa-828e-a991baa6e777")
    private IDiagramGraphic getDiagramGraphic(IDiagramHandle dh, MObject o) {
        List<IDiagramGraphic> dgs = dh.getDiagramGraphics(o);
        return dgs.isEmpty() ? null : dgs.get(0);
    }

    @objid ("ecec32bd-383b-4bb9-bca0-695de902ab88")
    private BpmnSubProcess createSubprocess(MObject owner, Map<Object, Rectangle> nodeGeometry, Map<MObject, ILinkRoute> linkGeometry, AbstractDiagram ownerDiagram) {
        BpmnSubProcess newSubProcess = null;
        IModelingSession session = this.moduleContext.getModelingSession();
        try (ITransaction tr = this.moduleContext.getModelingSession().createTransaction("CrateSubProcess")) {
        
            // Create a sub process
            newSubProcess = session.getModel().createBpmnSubProcess();
        
            setFlowElementParent(owner, newSubProcess);
        
            newSubProcess.setName("subprocess");
        
            for (BpmnLane lane : this.lanes) {
                lane.getFlowElementRef().add(newSubProcess);
            }
        
            // Create a subprocess diagram
            BpmnSubProcessDiagram subProcessDiagram = session.getModel().createBpmnSubProcessDiagram();
            subProcessDiagram.setOrigin(newSubProcess);
            subProcessDiagram.setName("sub process diagram");
        
            for (BpmnFlowNode e : this.nodes) {
                setFlowElementParent(newSubProcess, e);
                e.getDiagramElement().add(subProcessDiagram);
            }
        
            for (BpmnSequenceFlow f : this.links) {
                setFlowElementParent(newSubProcess, f);
                f.getDiagramElement().add(subProcessDiagram);
            }
        
            // Attach Links
            for (BpmnSequenceFlow incoming : this.inLinks) {
                incoming.setTargetRef(newSubProcess);
            }
        
            for (BpmnSequenceFlow outgoing : this.outLinks) {
                outgoing.setSourceRef(newSubProcess);
            }
        
            layoutSubProcess(ownerDiagram, newSubProcess, nodeGeometry, linkGeometry);
        
            IDiagramService ds = this.moduleContext.getModelioServices().getDiagramService();
            Point spTranslation = new Point(nodeGeometry.get("global").getTopLeft().negate());
            layoutSubprocessDiagram(ds, nodeGeometry, linkGeometry, subProcessDiagram, spTranslation);
        
            tr.commit();
        }
        return newSubProcess;
    }

    @objid ("ce404017-6624-4e51-b0ab-f1bfb5efc8d4")
    private void setFlowElementParent(MObject owner, BpmnFlowElement element) {
        if (owner instanceof BpmnSubProcess) {
            element.setContainer(null);
            element.setSubProcess((BpmnSubProcess) owner);
        } else if (owner instanceof BpmnProcess) {
            element.setSubProcess(null);
            element.setContainer((BpmnProcess) owner);
        }
        
    }

    @objid ("f0b3d746-0b6b-4b9c-98b1-410c39f125bd")
    private Map<Object, Rectangle> getNodeGeometry(AbstractDiagram diagram) {
        Map<Object, Rectangle> results = new HashMap<>();
        IDiagramService ds = this.moduleContext.getModelioServices().getDiagramService();
        try (IDiagramHandle dh = ds.getDiagramHandle(diagram)) {
            int minX = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE;
            int minY = Integer.MAX_VALUE;
            int maxY = Integer.MIN_VALUE;
        
            for (MObject e : this.nodes) {
                IDiagramGraphic dg = getDiagramGraphic(dh, e);
                if (dg instanceof IDiagramNode) {
                    Rectangle bounds = ((IDiagramNode) dg).getBounds().getCopy();
                    results.put(e, bounds);
                    minX = Math.min(bounds.x(), minX);
                    maxX = Math.max(bounds.x() + bounds.width(), maxX);
                    minY = Math.min(bounds.y(), minY);
                    maxY = Math.max(bounds.y() + bounds.height(), maxY);
                }
            }
            results.put("global", new Rectangle(minX, minY, maxX - minX, maxY - minY));
            dh.save();
        }
        return results;
    }

    @objid ("e9c7673d-9e72-49c1-a43b-de84f7d4a8d2")
    private Map<MObject, ILinkRoute> getLinkGeometry(AbstractDiagram diagram) {
        Map<MObject, ILinkRoute> results = new HashMap<>();
        IDiagramService ds = this.moduleContext.getModelioServices().getDiagramService();
        try (IDiagramHandle dh = ds.getDiagramHandle(diagram)) {
            for (MObject e : this.links) {
                IDiagramGraphic dg = getDiagramGraphic(dh, e);
                if (dg instanceof IDiagramLink) {
                    ILinkRoute path = ((IDiagramLink) dg).getRoute();
                    results.put(e, path);
                }
            }
        
            for (MObject e : this.inLinks) {
                IDiagramGraphic dg = getDiagramGraphic(dh, e);
                if (dg instanceof IDiagramLink) {
                    ILinkRoute path = ((IDiagramLink) dg).getRoute();
                    results.put(e, path);
                }
            }
        
            for (MObject e : this.outLinks) {
                IDiagramGraphic dg = getDiagramGraphic(dh, e);
                if (dg instanceof IDiagramLink) {
                    ILinkRoute path = ((IDiagramLink) dg).getRoute();
                    results.put(e, path);
                }
            }
        
            dh.save();
        }
        return results;
    }

}
