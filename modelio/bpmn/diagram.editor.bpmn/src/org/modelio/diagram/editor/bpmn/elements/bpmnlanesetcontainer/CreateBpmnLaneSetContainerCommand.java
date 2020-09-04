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

package org.modelio.diagram.editor.bpmn.elements.bpmnlanesetcontainer;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.GmBpmnLane;
import org.modelio.diagram.editor.bpmn.elements.diagrams.GmBpmnDiagramStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.workflow.GmWorkflow;
import org.modelio.diagram.elements.common.resizablegroup.ReorderChildrenCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specific command to handle the creation of one (or more) lane in a lane set container.
 */
@objid ("ef9ac42b-077f-4365-bee5-b7234e642693")
public class CreateBpmnLaneSetContainerCommand extends Command {
    @objid ("40becbac-f2f8-4273-937d-7b46b31b8d15")
    private Object newConstraint;

    @objid ("1a33fad0-b8c4-4ee8-9f7d-65fe73def7ec")
    private ModelioCreationContext context;

    @objid ("2834d595-640b-4bac-b2bd-effc5318d839")
    private GmCompositeNode parentNode;

    @objid ("cc2adca3-f5b6-46d3-a72e-ceff1e530487")
    private MObject parentElement;

    @objid ("258a82e5-46c2-4e5d-bca1-0475edc0338d")
    private GmNodeModel insertionReference;

    /**
     * Creates a node creation command.
     * 
     * @param parentElement the element that lead to this command.
     * @param parentNode The parent editPart
     * @param context Details on the MObject and/or the node to create
     * @param requestConstraint Request Constraint
     * @param afterEditPart <code>null</code> or a reference EditPart
     */
    @objid ("589d59ad-83b5-403a-8348-0941be0688f3")
    public CreateBpmnLaneSetContainerCommand(MObject parentElement, GmCompositeNode parentNode, ModelioCreationContext context, Object requestConstraint, EditPart afterEditPart) {
        this.parentNode = parentNode;
        this.parentElement = parentElement;
        this.context = context;
        this.newConstraint = requestConstraint;
        Object model = afterEditPart != null ? afterEditPart.getModel() : null;
        this.insertionReference = model instanceof GmNodeModel ? (GmNodeModel) model : null;
    }

    @objid ("528c6f52-41eb-456f-a7bc-907f86bbbeef")
    @Override
    public void execute() {
        final IGmDiagram diagram = this.parentNode.getDiagram();
        
        BpmnLaneSet laneSet;
        
        if (this.context.getElementToUnmask() != null) {
            BpmnLaneSet elementToUnmask;
            if (this.context.getElementToUnmask() instanceof BpmnLane) {
                elementToUnmask = ((BpmnLane) this.context.getElementToUnmask()).getLaneSet();
            } else {
                elementToUnmask = (BpmnLaneSet) this.context.getElementToUnmask();
            }
        
            for (BpmnLane lane : elementToUnmask.getLane()) {
                unmaskLane(diagram, lane, elementToUnmask);
            }
        
        } else {
            IModelManager modelManager = diagram.getModelManager();
        
            // Create the MObject...
            final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
        
            if (this.parentElement instanceof BpmnProcess) {
                BpmnProcess process = (BpmnProcess) this.parentElement;
                laneSet = process.getLaneSet();
                if (laneSet == null) {
                    laneSet = modelFactory.createBpmnLaneSet();
                    laneSet.setProcess(process);
                }
            } else if (this.parentElement instanceof BpmnLane) {
                BpmnLane lane = (BpmnLane) this.parentElement;
                laneSet = lane.getChildLaneSet();
                if (laneSet == null) {
                    laneSet = modelFactory.createBpmnLaneSet();
                    laneSet.setParentLane(lane);
                }
            } else if (this.parentElement instanceof BpmnLaneSet) {
                laneSet = (BpmnLaneSet) this.parentElement;
            } else {
                return;
            }
        
            // Attach the stereotype if needed.
            if (this.context.getStereotype() != null) {
                ((ModelElement) laneSet).getExtension().add(this.context.getStereotype());
            }
        
            // Configure element from properties
            final IElementConfigurator elementConfigurer = modelManager.getModelServices().getElementConfigurer();
            elementConfigurer.configure(laneSet, this.context.getProperties());
        
            BpmnLane lane = createLane(diagram, laneSet);
            unmaskLane(diagram, lane, laneSet);
            if (laneSet.getLane().size() == 1) {
                lane = createLane(diagram, laneSet);
                unmaskLane(diagram, lane, laneSet);
            }
        }
    }

    @objid ("d1171505-6431-4008-88c5-a3727b28fe01")
    private BpmnLane createLane(final IGmDiagram diagram, final BpmnLaneSet laneset) {
        IModelManager modelManager = diagram.getModelManager();
        final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
        BpmnLane newElement = modelFactory.createBpmnLane();
        
        newElement.setLaneSet(laneset);
        
        // Attach the stereotype if needed.
        if (this.context.getStereotype() != null) {
            ((ModelElement) newElement).getExtension().add(this.context.getStereotype());
        }
        
        // Configure element from properties
        final IElementConfigurator elementConfigurer = modelManager.getModelServices().getElementConfigurer();
        elementConfigurer.configure(newElement, this.context.getProperties());
        
        // Set default name
        newElement.setName(modelManager.getModelServices().getElementNamer().getUniqueName(newElement));
        return newElement;
    }

    @objid ("fa263601-eeca-42c6-afff-8be01fa945cc")
    @Override
    public boolean canExecute() {
        // The diagram must be valid and modifiable.
        final IGmDiagram gmDiagram = this.parentNode.getDiagram();
        if (gmDiagram == null || !MTools.getAuthTool().canModify(gmDiagram.getRelatedElement())) {
            return false;
        }
        
        // If it is an actual creation (and not a simple unmasking).
        if (this.context.getElementToUnmask() == null) {
            // The parent element must be modifiable or both must be CMS nodes.
            if (!MTools.getAuthTool().canAdd(this.parentElement, this.context.getMetaclass())) {
                return false;
            }
        }
        return true;
    }

    @objid ("7bafcfc7-2aaa-47ab-8cd6-237eb15564d6")
    private void unmaskLane(final IGmDiagram diagram, final BpmnLane lane, final BpmnLaneSet laneSet) {
        boolean isHorizontalLaneOrientation = diagram.getDisplayedStyle().getProperty(GmBpmnDiagramStyleKeys.HORIZONTAL_LANES);
        
        // OwnedNode to relocate in the new Lane ?
        List<GmNodeModel> nodesToRelocateInTheLane = this.parentNode.getChildren(GmWorkflow.OWNED_NODE);
        Rectangle laneConstraint = null;
        if (!nodesToRelocateInTheLane.isEmpty()) {
            laneConstraint = new Rectangle((Rectangle) nodesToRelocateInTheLane.get(0).getLayoutData());
            for (GmNodeModel n : nodesToRelocateInTheLane) {
                Rectangle r = (Rectangle) n.getLayoutData();
                laneConstraint.union(r);
            }
        }
        
        int laneLayoutConstraint = -1;
        if (laneConstraint != null) {
            laneConstraint.expand(10, 10);
            if (this.newConstraint instanceof Rectangle) {
                Rectangle newRectangleConstraint = (Rectangle) this.newConstraint;
                newRectangleConstraint.setWidth(Math.max(newRectangleConstraint.width, laneConstraint.width));
                newRectangleConstraint.setHeight(Math.max(newRectangleConstraint.height, laneConstraint.height));
                if (isHorizontalLaneOrientation) {
                    laneLayoutConstraint = newRectangleConstraint.height;
                } else {
                    laneLayoutConstraint = newRectangleConstraint.width;
                }
            }
        } else {
            if (this.newConstraint instanceof Rectangle) {
                Rectangle newRectangleConstraint = (Rectangle) this.newConstraint;
                if (isHorizontalLaneOrientation) {
                    laneLayoutConstraint = newRectangleConstraint.height == -1 ? 100 : newRectangleConstraint.height;
                } else {
                    laneLayoutConstraint = newRectangleConstraint.width == -1 ? 100 : newRectangleConstraint.width;
                }
            }
            laneConstraint = new Rectangle();
        }
        
        GmBpmnLaneSetContainer gmLaneSet;
        if (!diagram.getAllGMRepresenting(new MRef(laneSet)).isEmpty()) {
            gmLaneSet = (GmBpmnLaneSetContainer) diagram.getAllGMRepresenting(new MRef(laneSet)).get(0);
        } else {
            gmLaneSet = (GmBpmnLaneSetContainer) diagram.unmask(this.parentNode, laneSet, this.newConstraint);
        }
        
        // Show the new elements in the diagram (ie create their Gm )
        
        GmBpmnLane newLaneGm = (GmBpmnLane) diagram.unmask(gmLaneSet, lane, laneLayoutConstraint);
        
        for (GmNodeModel n : nodesToRelocateInTheLane) {
            // Update the OB model
            if (n.getRelatedElement() instanceof BpmnFlowElement) {
                BpmnFlowElement flowElement = (BpmnFlowElement) n.getRelatedElement();
                for (BpmnLane elane : new ArrayList<>(flowElement.getLane())) {
                    flowElement.getLane().remove(elane);
                }
                flowElement.getLane().add(lane);
            }
        
            Rectangle r = (Rectangle) n.getLayoutData();
        
            Rectangle r2 = new Rectangle(r);
            r2.translate(laneConstraint.getTopLeft().getNegated());
            n.setLayoutData(r2);
        
            n.getParentNode().removeChild(n);
            newLaneGm.addChild(n);
        }
        
        if (this.insertionReference != null) {
            new ReorderChildrenCommand(gmLaneSet, newLaneGm, this.insertionReference).execute();
        }
    }

}
