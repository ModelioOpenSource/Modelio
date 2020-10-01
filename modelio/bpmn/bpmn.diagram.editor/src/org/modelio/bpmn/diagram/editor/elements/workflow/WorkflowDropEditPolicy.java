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

package org.modelio.bpmn.diagram.editor.elements.workflow;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.bpmn.diagram.editor.elements.bpmnlane.GmBpmnLane;
import org.modelio.bpmn.diagram.editor.elements.bpmnlanesetcontainer.GmBpmnLaneSetContainer;
import org.modelio.bpmn.diagram.editor.elements.diagrams.GmBpmnDiagramStyleKeys;
import org.modelio.bpmn.diagram.editor.elements.policies.CreateCallActivityCommand;
import org.modelio.bpmn.diagram.editor.elements.policies.CreateDataObjectCommand;
import org.modelio.bpmn.diagram.editor.elements.policies.CreateEventCommand;
import org.modelio.bpmn.diagram.editor.elements.policies.CreateServiceTaskCommand;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.activities.BpmnServiceTask;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataObject;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.default_.infrastructure.dependency.RelatedDiagram;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Event;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.PartitionElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.State;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Drop extension for Bpmn workflow.
 * <p>
 * Smart interactions are:
 * <ul>
 * <li>dropping a {@link Called}-compatible element creates a {@link BpmnCallActivity} or {@link BpmnServiceTask}</li>
 * <li>dropping a {@link Represents} or {State}-compatible element creates a {@link BpmnDataObject}</li>
 * <li>dropping an {@link Event}-compatible element creates a {@link BpmnIntermediateCatchEvent}</li>
 * <li>dropping a {@link PartitionElement}-compatible element creates a {@link BpmnLane}</li>
 * </ul>
 * 
 * Warning: available smart interactions depends on which BPMN diagram type is opened.
 * </p>
 */
@objid ("066f9299-4796-47c4-8214-3c5b38d15d20")
public class WorkflowDropEditPolicy extends DefaultElementDropEditPolicy {
    @objid ("001e3ff2-4837-4b31-85b2-17fbfb80f4a9")
    @Override
    protected EditPart getDropTargetEditPart(ModelElementDropRequest request) {
        final Object model = getHost().getModel();
        if (!(model instanceof GmModel)) {
            return null;
        }
        
        final GmModel gmModel = (GmModel) model;
        
        // If either of the dropped elements cannot be unmasked, return null.
        for (final MObject droppedElement : request.getDroppedElements()) {
            if (!gmModel.canUnmask(droppedElement) && (!request.isSmart() || !isSmartDropTarget(droppedElement))) {
                return null;
            }
        }
        
        // All dropped elements understood: return host!
        return getHost();
    }

    @objid ("6ee03319-4c7d-486b-baec-477897bc357f")
    @Override
    protected Command getSmartDropCommand(final ModelElementDropRequest request) {
        if (!request.isSmart()) {
            return null;
        }
        
        Point dropLocation = request.getDropLocation();
        CompoundCommand command = new CompoundCommand();
        for (MObject droppedElement : request.getDroppedElements()) {
            Command subCmd;
            if (isSmartDropTarget(droppedElement)) {
                // Smart drop
                subCmd = createSmartCommandForElement(dropLocation, droppedElement);
            } else if (droppedElement.getMClass().isLinkMetaclass() && !(droppedElement instanceof Dependency && (((Dependency) droppedElement).isStereotyped(RelatedDiagram.MdaTypes.STEREOTYPE_ELT)))) {
                subCmd = createDropCommandForLinkElement(dropLocation, droppedElement);
            } else if (BpmnDataAssociation.class.isAssignableFrom(droppedElement.getMClass().getJavaInterface())) {
                // BpmnDataAssociations are not flagged as link in the metamodel, but are displayed as such
                subCmd = createDropCommandForLinkElement(dropLocation, droppedElement);
            } else {
                subCmd = createDropCommandForElement(dropLocation, droppedElement);
            }
        
            if (subCmd != null) {
                command.add(subCmd);
        
                // Introduce some offset, so that all elements are not totally
                // on top of each other.
                dropLocation = dropLocation.getTranslated(20, 20);
            }
        
        }
        return command.unwrap();
    }

    @objid ("48836220-8771-461d-b974-363de113acc8")
    private boolean isSmartDropTarget(MObject droppedElement) {
        final GmModel gmModel = (GmModel) getHost().getModel();
        IGmDiagram gmDiagram = gmModel.getDiagram();
        
        if (isSmartCallActivity(droppedElement, gmDiagram)) {
            return gmModel.canCreate(BpmnCallActivity.class);
        } else if (isSmartServiceTask(droppedElement, gmDiagram)) {
            return gmModel.canCreate(BpmnServiceTask.class);
        } else if (isSmartLane(droppedElement, gmDiagram)) {
            return true;
        } else if (isSmartDataObject(droppedElement, gmDiagram)) {
            return gmModel.canCreate(BpmnDataObject.class);
        } else if (isSmartEvent(droppedElement, gmDiagram)) {
            return gmModel.canCreate(BpmnIntermediateCatchEvent.class);
        }
        return false;
    }

    @objid ("aaebe9d1-f361-445e-ab54-d9ed649f381d")
    protected Command createSmartCommandForElement(Point dropLocation, MObject droppedElement) {
        final GmModel gmModel = (GmModel) getHost().getModel();
        IGmDiagram gmDiagram = gmModel.getDiagram();
        final AbstractDiagram diagram = gmDiagram.getRelatedElement();
        
        if (isSmartCallActivity(droppedElement, gmDiagram)) {
            return getCreateCallActivityCommand(diagram.getOrigin(), droppedElement, dropLocation);
        } else if (isSmartServiceTask(droppedElement, gmDiagram)) {
            return getCreateServiceTaskCommand(diagram.getOrigin(), droppedElement, dropLocation);
        } else if (isSmartLane(droppedElement, gmDiagram)) {
            return getCreateLaneCommand((BpmnProcess) diagram.getOrigin(), droppedElement, dropLocation);
        } else if (isSmartEvent(droppedElement, gmDiagram)) {
            return getCreateEventCommand(diagram.getOrigin(), droppedElement, dropLocation);
        } else if (isSmartDataObject(droppedElement, gmDiagram)) {
            return getCreateDataObjectCommand(diagram.getOrigin(), droppedElement, dropLocation);
        }
        return null;
    }

    @objid ("61ee9c5f-55b6-11e2-877f-002564c97630")
    private Command getCreateCallActivityCommand(MObject owner, MObject toUnmask, Point dropLocation) {
        return new CreateCallActivityCommand(dropLocation, toUnmask, getHost(), owner);
    }

    @objid ("61ee9c73-55b6-11e2-877f-002564c97630")
    private Command getCreateDataObjectCommand(MObject owner, MObject toUnmask, Point dropLocation) {
        return new CreateDataObjectCommand(dropLocation, (ModelElement) toUnmask, getHost(), owner);
    }

    @objid ("61ee9c7d-55b6-11e2-877f-002564c97630")
    private Command getCreateLaneCommand(BpmnProcess owner, MObject toUnmask, Point dropLocation) {
        return new CreateLaneCommand(dropLocation, toUnmask, getHost(), owner);
    }

    @objid ("c163cace-9e92-4994-a48b-242249f273fa")
    private boolean isSmartCallActivity(MObject droppedElement, IGmDiagram gmDiagram) {
        IModelManager modelManager = gmDiagram.getModelManager();
        MMetamodel metamodel = modelManager.getMetamodel();
        MClass sourceMetaclass = metamodel.getMClass(BpmnCallActivity.MQNAME);
        MClass linkMetaclass = metamodel.getMClass(MethodologicalLink.MQNAME);
        IMdaExpert mdaExpert = modelManager.getMdaExpert();
        return mdaExpert.canLink(Called.MdaTypes.STEREOTYPE_ELT, linkMetaclass, sourceMetaclass, droppedElement.getMClass());
    }

    @objid ("80b6fa34-f3f5-43e0-8c86-c5a1241ddae8")
    private boolean isSmartDataObject(MObject droppedElement, IGmDiagram gmDiagram) {
        IModelManager modelManager = gmDiagram.getModelManager();
        MMetamodel metamodel = modelManager.getMetamodel();
        MClass sourceMetaclass = metamodel.getMClass(BpmnDataObject.MQNAME);
        MClass linkMetaclass = metamodel.getMClass(MethodologicalLink.MQNAME);
        IMdaExpert mdaExpert = modelManager.getMdaExpert();
        return mdaExpert.canLink(Represents.MdaTypes.STEREOTYPE_ELT, linkMetaclass, sourceMetaclass, droppedElement.getMClass()) ||
                mdaExpert.canLink(State.MdaTypes.STEREOTYPE_ELT, linkMetaclass, sourceMetaclass, droppedElement.getMClass());
    }

    @objid ("b6ff151b-d34f-4609-93ff-ab800f3df27c")
    private boolean isSmartLane(MObject droppedElement, IGmDiagram gmDiagram) {
        if (!(gmDiagram.getRelatedElement() instanceof BpmnProcessDesignDiagram)) {
            return false;
        }
        
        IModelManager modelManager = gmDiagram.getModelManager();
        MMetamodel metamodel = modelManager.getMetamodel();
        MClass sourceMetaclass = metamodel.getMClass(BpmnLane.MQNAME);
        MClass linkMetaclass = metamodel.getMClass(MethodologicalLink.MQNAME);
        IMdaExpert mdaExpert = modelManager.getMdaExpert();
        return mdaExpert.canLink(PartitionElement.MdaTypes.STEREOTYPE_ELT, linkMetaclass, sourceMetaclass, droppedElement.getMClass());
    }

    @objid ("977a42e9-a6f3-4ff5-a9d3-c2bf87e3a5d2")
    private Command getCreateEventCommand(MObject owner, MObject toUnmask, Point dropLocation) {
        return new CreateEventCommand(dropLocation, (ModelElement) toUnmask, getHost(), owner);
    }

    @objid ("92d739cc-a45c-469e-8827-44e35baad7c6")
    private boolean isSmartEvent(MObject droppedElement, IGmDiagram gmDiagram) {
        IModelManager modelManager = gmDiagram.getModelManager();
        MMetamodel metamodel = modelManager.getMetamodel();
        MClass sourceMetaclass = metamodel.getMClass(BpmnIntermediateCatchEvent.MQNAME);
        MClass linkMetaclass = metamodel.getMClass(MethodologicalLink.MQNAME);
        IMdaExpert mdaExpert = modelManager.getMdaExpert();
        return mdaExpert.canLink(Event.MdaTypes.STEREOTYPE_ELT, linkMetaclass, sourceMetaclass, droppedElement.getMClass());
    }

    @objid ("e5dbabfa-c70a-4244-a3f4-dd2d21807b33")
    private boolean isSmartServiceTask(MObject droppedElement, IGmDiagram gmDiagram) {
        IModelManager modelManager = gmDiagram.getModelManager();
        MMetamodel metamodel = modelManager.getMetamodel();
        MClass sourceMetaclass = metamodel.getMClass(BpmnServiceTask.MQNAME);
        MClass linkMetaclass = metamodel.getMClass(MethodologicalLink.MQNAME);
        IMdaExpert mdaExpert = modelManager.getMdaExpert();
        return mdaExpert.canLink(Called.MdaTypes.STEREOTYPE_ELT, linkMetaclass, sourceMetaclass, droppedElement.getMClass());
    }

    @objid ("2d7a6a72-4730-4145-ba75-c960ae001c88")
    private Command getCreateServiceTaskCommand(MObject owner, MObject toUnmask, Point dropLocation) {
        return new CreateServiceTaskCommand(dropLocation, toUnmask, getHost(), owner);
    }

    /**
     * Specific command that will create and unmask a {@link BpmnLane} and use the unmasked model element as a 'partitionElement'.
     * <p>
     * Also unmask the {@link BpmnLaneSet}, which might need the instantiation of a new {@link BpmnLaneSet} to own the lane when it doesn't already exists.
     * </p>
     */
    @objid ("61f94ae1-55b6-11e2-877f-002564c97630")
    private static class CreateLaneCommand extends Command {
        @objid ("61f94ae3-55b6-11e2-877f-002564c97630")
        private MObject partitionElement;

        @objid ("61f94ae7-55b6-11e2-877f-002564c97630")
        private BpmnProcess parentElement;

        @objid ("f78836fa-5a3f-11e2-9e33-00137282c51b")
        private EditPart editPart;

        @objid ("0cb33bce-d270-4df5-ad0d-1bc99eb2236b")
        private Point dropLocation;

        /**
         * Initialize the command.
         * 
         * @param dropLocation The location of the element in the diagram
         * @param partitionElement element to be referenced by the {@link BpmnLane} as a 'partitionElement'.
         * @param editPart The destination edit part that will own the lane.
         * @param parentElement The process owing the lane.
         */
        @objid ("61f94aea-55b6-11e2-877f-002564c97630")
        public CreateLaneCommand(final Point dropLocation, final MObject partitionElement, final EditPart editPart, final BpmnProcess parentElement) {
            this.partitionElement = partitionElement;
            this.dropLocation = dropLocation;
            this.editPart = editPart;
            this.parentElement = parentElement;
        }

        @objid ("61f94af9-55b6-11e2-877f-002564c97630")
        @Override
        public void execute() {
            final GmModel gmModel = (GmModel) this.editPart.getModel();
            final IGmDiagram gmDiagram = gmModel.getDiagram();
            final IModelManager modelManager = gmDiagram.getModelManager();
            final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
            
            BpmnLane newElement = modelFactory.createBpmnLane();
            BpmnLaneSet laneSet = this.parentElement.getLaneSet();
            if (laneSet == null) {
                laneSet = modelFactory.createBpmnLaneSet();
                laneSet.setProcess(this.parentElement);
            }
            newElement.setLaneSet(laneSet);
            newElement.setName(modelManager.getModelServices().getElementNamer().getUniqueName(newElement));
            PartitionElement.setTarget(newElement, (ModelElement) this.partitionElement);
            
            // Show the new elements in the diagram (ie create their Gm )
            unmaskLane(gmDiagram, newElement, laneSet);
        }

        @objid ("61f94b02-55b6-11e2-877f-002564c97630")
        @Override
        public boolean canExecute() {
            return this.parentElement != null &&
                    this.parentElement.isValid() &&
                    this.parentElement.getStatus().isModifiable() &&
                    (this.partitionElement == null || this.partitionElement instanceof ModelElement);
        }

        @objid ("6a11eb25-885e-4331-8ff6-4e31529cdbcf")
        private void unmaskLane(final IGmDiagram diagram, final BpmnLane lane, final BpmnLaneSet laneSet) {
            Rectangle laneConstraint = null;
            final List<GmNodeModel> nodesToRelocateInTheLane;
            
            // OwnedNode to relocate in the new Lane ?
            final GmCompositeNode parentNode = (GmCompositeNode) this.editPart.getModel();
            nodesToRelocateInTheLane = parentNode.getChildren(GmWorkflow.OWNED_NODE);
            if (!nodesToRelocateInTheLane.isEmpty()) {
                laneConstraint = new Rectangle((Rectangle) nodesToRelocateInTheLane.get(0).getLayoutData());
                for (GmNodeModel n : nodesToRelocateInTheLane) {
                    Rectangle r = (Rectangle) n.getLayoutData();
                    laneConstraint.union(r);
                }
            }
            
            boolean isHorizontalLaneOrientation = diagram.getDisplayedStyle().getProperty(GmBpmnDiagramStyleKeys.HORIZONTAL_LANES);
            int laneLayoutConstraint = -1;
            if (laneConstraint == null) {
                laneConstraint = new Rectangle(0, 0, 100, 100);
            }
            
            if (isHorizontalLaneOrientation) {
                laneLayoutConstraint = laneConstraint.height + 25;
            } else {
                laneLayoutConstraint = laneConstraint.width + 25;
            }
            
            GmBpmnLaneSetContainer gmLaneSet;
            if (!diagram.getAllGMRepresenting(new MRef(laneSet)).isEmpty()) {
                gmLaneSet = (GmBpmnLaneSetContainer) diagram.getAllGMRepresenting(new MRef(laneSet)).get(0);
            } else {
                if (isHorizontalLaneOrientation) {
                    gmLaneSet = (GmBpmnLaneSetContainer) diagram.unmask(parentNode, laneSet, new Rectangle(this.dropLocation.x, this.dropLocation.y, laneConstraint.width + 50, laneConstraint.height + 25));
                } else {
                    gmLaneSet = (GmBpmnLaneSetContainer) diagram.unmask(parentNode, laneSet, new Rectangle(this.dropLocation.x, this.dropLocation.y, laneConstraint.width + 25, laneConstraint.height + 50));
                }
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
        }

    }

}
