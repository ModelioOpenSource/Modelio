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

package org.modelio.bpmn.diagram.editor.elements.diagrams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.objects.BpmnSequenceFlowDataAssociation;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Refresher for the BPMN diagrams, in charge of auto-unmasking workflow elements when the model changes.
 */
@objid ("7205b9ab-accd-4cda-a965-31d2594bfb68")
public class BpmnDiagramUnmaskHelper {
    @objid ("3329682f-4680-42b3-9b73-d82ba232da71")
    private final IGmDiagram gmDiagram;

    @objid ("400c2086-f984-4f06-82fa-ff010fdbbeaa")
    private final IFigure rootFigure;

    @objid ("1b87c9e7-4e0b-4093-a681-076e7eb0d103")
    private final EditPartViewer viewer;

    /**
     * C'tor.
     * 
     * @param gmDiagram the diagram to unmask contents into.
     * @param viewer the diagram viewer.
     */
    @objid ("27940cd1-e09c-4194-b7fb-ff7f2ecd829c")
    public BpmnDiagramUnmaskHelper(IGmDiagram gmDiagram, EditPartViewer viewer) {
        this.viewer = viewer;
        this.gmDiagram = gmDiagram;
        this.rootFigure = ((GraphicalEditPart) this.viewer.getRootEditPart()).getFigure();
    }

    /**
     * Run auto-unmask of workflow elements.
     */
    @objid ("8878da0f-0481-43f0-98bd-91688b955935")
    public void unmaskAllWorkflowElements() {
        AbstractDiagram diagram = this.gmDiagram.getRelatedElement();
        // Called by a deletion of the diagram => do nothing
        
        ModelElement origin = diagram.getOrigin();
        if (origin == null || !origin.isValid()) {
            return;
        }
        
        if (origin instanceof BpmnProcess) {
            doUnmaskWorkflowElements((BpmnProcess) origin);
        } else if (origin instanceof BpmnSubProcess) {
            doUnmaskWorkflowElements((BpmnSubProcess) origin);
        }
    }

    @objid ("2a569856-65d9-4e79-b784-b052166f0e1d")
    private void doUnmaskElement(MObject elementToUnmask, Point unmaskCoordinates, MObject graphicalOwner) {
        if (this.gmDiagram.getExistingModelFor(elementToUnmask) == null) {
            if (elementToUnmask == null) {
                return;
            }
            List<GmModel> existingGMs = getDiagramGraphicModels(elementToUnmask);
        
            Point dropLocation = unmaskCoordinates.getCopy();
        
            this.rootFigure.translateToParent(dropLocation);
        
            final ModelElementDropRequest unmaskReq = new ModelElementDropRequest();
            unmaskReq.setDroppedElements(new MObject[] { elementToUnmask });
            unmaskReq.setLocation(dropLocation);
            unmaskReq.setSmart(false);
        
            /*
             * Fix size of Root Figure ( diagram ) at least at the size of the unmasked element
             * in order to be able to find potential parent figure of unmasked figure.
             * Instead, if the unmasked element is out of the scope of Root Figure,
             * the algorithm which find the parent figure return always null
             */
        
            // Calcul min bounds
            Rectangle rootBounds = this.rootFigure.getBounds();
            if (rootBounds.x + rootBounds.width < unmaskCoordinates.x) {
                rootBounds.width = unmaskCoordinates.x - rootBounds.x + 10;
            }
        
            if (rootBounds.y + rootBounds.height < unmaskCoordinates.y) {
                rootBounds.height = unmaskCoordinates.y - rootBounds.y + 10;
            }
        
            // Set min bounds to diagram figure
            this.rootFigure.setBounds(rootBounds);
            this.rootFigure.getParent().setBounds(rootBounds);
            this.rootFigure.revalidate();
        
            Map<Object, EditPart> reg = this.viewer.getEditPartRegistry();
            Command unmaskCommand = reg.values()
                    .stream()
                    .filter(ep -> ep.getTargetEditPart(unmaskReq) != null)
                    .filter(ep -> graphicalOwner.equals(((GmModel) ep.getModel()).getRelatedElement()))
                    .findFirst()
                    .map(ep -> ep.getTargetEditPart(unmaskReq))
                    .map(ep -> ep.getCommand(unmaskReq))
                    .filter(Command::canExecute)
                    .orElse(null);
        
            if (unmaskCommand == null) {
                return;
            }
        
            unmaskCommand.execute();
        
            List<GmModel> allGms = getDiagramGraphicModels(elementToUnmask);
            for (GmModel gm : allGms) {
                if (!existingGMs.contains(gm)) {
                    // Force figure validation
                    GraphicalEditPart newEP = getEditPart(gm);
                    if (newEP != null) {
                        newEP.getFigure().getUpdateManager().performValidation();
                    }
                }
            }
        }
    }

    @objid ("1c5c1898-1df1-4e7e-8f30-40f334a7d939")
    private void doUnmaskLaneElements(BpmnLane lane, Point unmaskCoordinates) {
        BpmnLaneSet childLaneSet = lane.getChildLaneSet();
        if (childLaneSet != null) {
            doUnmaskElement(childLaneSet, unmaskCoordinates.translate(5, 5), lane);
        
            doUnmaskLaneSetElements(childLaneSet, unmaskCoordinates);
        }
        
        for (BpmnFlowElement node : lane.getFlowElementRef()) {
            doUnmaskElement(node, unmaskCoordinates.translate(5, 5), lane);
        }
    }

    @objid ("e5c395a7-586a-4c52-90cf-833dddc02878")
    private void doUnmaskLaneSetElements(BpmnLaneSet laneSet, Point unmaskCoordinates) {
        // Unmask lanes
        for (BpmnLane lane : laneSet.getLane()) {
            doUnmaskElement(lane, unmaskCoordinates.translate(5, 5), laneSet.getCompositionOwner());
        
            doUnmaskLaneElements(lane, unmaskCoordinates);
        }
    }

    @objid ("de99eed4-21e6-4290-a946-6960c949608c")
    private void doUnmaskWorkflowElements(BpmnProcess process) {
        BpmnLaneSet laneSet = process.getLaneSet();
        EList<BpmnFlowElement> flowElements = process.getFlowElement();
        
        Point unmaskCoordinates = new Point(0, 0);
        
        if (laneSet != null && !laneSet.getLane().isEmpty()) {
            // Unmask lane set
            doUnmaskElement(laneSet, unmaskCoordinates.translate(5, 5), this.gmDiagram.getRelatedElement());
            doUnmaskLaneSetElements(laneSet, unmaskCoordinates);
        }
        
        // Unmask nodes at root
        for (BpmnFlowElement elt : flowElements) {
            if (elt.getLane().isEmpty() && !elt.getMClass().isLinkMetaclass()) {
                doUnmaskElement(elt, unmaskCoordinates.translate(5, 5), this.gmDiagram.getRelatedElement());
            }
        }
        
        // Unmask links
        for (BpmnFlowElement elt : flowElements) {
            if (elt instanceof BpmnSequenceFlow) {
                doUnmaskElement(elt, unmaskCoordinates.translate(5, 5), this.gmDiagram.getRelatedElement());
            } else {
                unmaskDataAssociations(elt, unmaskCoordinates);
            }
        }
    }

    @objid ("5e7d746b-15d2-47d9-a79b-65a0314d86ae")
    private void doUnmaskWorkflowElements(BpmnSubProcess subProcess) {
        BpmnLaneSet laneSet = subProcess.getLaneSet();
        EList<BpmnFlowElement> flowElements = subProcess.getFlowElement();
        
        Point unmaskCoordinates = new Point(0, 0);
        
        if (laneSet != null && !laneSet.getLane().isEmpty()) {
            // Unmask lane set
            doUnmaskElement(laneSet, unmaskCoordinates.translate(5, 5), this.gmDiagram.getRelatedElement());
            doUnmaskLaneSetElements(laneSet, unmaskCoordinates);
        }
        
        // Unmask nodes at root
        for (BpmnFlowElement elt : flowElements) {
            if (elt.getLane().isEmpty() && !elt.getMClass().isLinkMetaclass()) {
                doUnmaskElement(elt, unmaskCoordinates.translate(5, 5), this.gmDiagram.getRelatedElement());
            }
        }
        
        // Unmask links
        for (BpmnFlowElement elt : flowElements) {
            if (elt instanceof BpmnSequenceFlow) {
                doUnmaskElement(elt, unmaskCoordinates.translate(5, 5), this.gmDiagram.getRelatedElement());
            }
        }
    }

    /**
     * Returns the diagram graphic models for the passed element.
     * @param gmObject a model element
     * 
     * @return a list of {@link GmModel}. Empty when the element is not unmasked in the diagram.
     */
    @objid ("84f64981-6ec8-4d5c-a19f-f02f8236ba7e")
    private List<GmModel> getDiagramGraphicModels(final MObject element) {
        List<GmModel> ret = new ArrayList<>();
        MRef ref = new MRef(element);
        for (GmModel gm : this.gmDiagram.getAllGMRepresenting(ref)) {
            if ((gm instanceof GmNodeModel) && !((GmNodeModel) gm).isVisible()) {
                continue;
            }
            ret.add(gm);
        }
        return ret;
    }

    /**
     * Returns the edit part for the passed object.
     * 
     * @param gmObject the graphic object model
     * @return the edit part
     */
    @objid ("8fc42458-94c6-40de-b9e3-ca0c36d7fa99")
    private GraphicalEditPart getEditPart(IGmObject gmObject) {
        return (GraphicalEditPart) this.viewer.getEditPartRegistry().get(gmObject);
    }

    /**
     * Unmask {@link BpmnDataAssociation} linked to this element.
     * 
     * @param elt the element to check for {@link BpmnDataAssociation} links.
     * @param unmaskCoordinates where to unmask the link.
     */
    @objid ("74b906dd-aea6-4412-8a56-2aca8a313ffa")
    private void unmaskDataAssociations(BpmnFlowElement elt, Point unmaskCoordinates) {
        // The metamodel being a complete mess, make sure every possible source and target MDependencies are scanned...
        if (elt instanceof BpmnActivity) {
            for (BpmnDataAssociation dataAssociation : ((BpmnActivity) elt).getDataInputAssociation()) {
                unmaskDataAssociations(unmaskCoordinates, dataAssociation);
            }
            for (BpmnDataAssociation dataAssociation : ((BpmnActivity) elt).getDataOutputAssociation()) {
                unmaskDataAssociations(unmaskCoordinates, dataAssociation);
            }
        } else if (elt instanceof BpmnThrowEvent) {
            for (BpmnDataAssociation dataAssociation : ((BpmnThrowEvent) elt).getDataInputAssociation()) {
                unmaskDataAssociations(unmaskCoordinates, dataAssociation);
            }
        } else if (elt instanceof BpmnCatchEvent) {
            for (BpmnDataAssociation dataAssociation : ((BpmnCatchEvent) elt).getDataOutputAssociation()) {
                unmaskDataAssociations(unmaskCoordinates, dataAssociation);
            }
        } else if (elt instanceof BpmnItemAwareElement) {
            for (BpmnDataAssociation dataAssociation : ((BpmnItemAwareElement) elt).getSourceOfDataAssociation()) {
                unmaskDataAssociations(unmaskCoordinates, dataAssociation);
            }
            for (BpmnDataAssociation dataAssociation : ((BpmnItemAwareElement) elt).getTargetOfDataAssociation()) {
                unmaskDataAssociations(unmaskCoordinates, dataAssociation);
            }
        }
    }

    @objid ("6cb17e72-1135-43c2-b9d7-3f7314838ceb")
    private void unmaskDataAssociations(Point unmaskCoordinates, BpmnDataAssociation dataAssociation) {
        if (dataAssociation.getVisualShortCut().isEmpty()) {
            doUnmaskElement(dataAssociation, unmaskCoordinates.translate(5, 5), this.gmDiagram.getRelatedElement());
        } else {
            for (BpmnSequenceFlowDataAssociation bpmnSequenceFlowDataAssociation : dataAssociation.getVisualShortCut()) {
                doUnmaskElement(bpmnSequenceFlowDataAssociation, unmaskCoordinates.translate(5, 5), this.gmDiagram.getRelatedElement());
            }
        }
    }

}
