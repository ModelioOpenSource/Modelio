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

package org.modelio.bpmn.diagram.editor.elements.bpmnlane.hibridcontainer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.bpmn.diagram.editor.elements.bpmnlane.hibridcontainer.BodyHybridContainerEditPart.Behaviour;
import org.modelio.bpmn.diagram.editor.elements.policies.BpmnLaneSetDropEditPolicy;
import org.modelio.bpmn.diagram.editor.elements.policies.CreateCallActivityCommand;
import org.modelio.bpmn.diagram.editor.elements.policies.CreateDataObjectCommand;
import org.modelio.bpmn.diagram.editor.elements.policies.CreateEventCommand;
import org.modelio.bpmn.diagram.editor.elements.policies.CreateServiceTaskCommand;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.activities.BpmnServiceTask;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.objects.BpmnDataObject;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Event;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.State;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Very specific hybrid edit policy that behave either like a default free zone layout edit policy OR like a lane container layout edit policy, depending on the nature of the current children of the Gm.
 * <p>
 * Smart interactions are:
 * <ul>
 * <li>dropping a {@link Called}-compatible element creates a {@link BpmnCallActivity} or a {@link BpmnServiceTask}</li>
 * <li>dropping a {@link Represents} or {State}-compatible element creates a {@link BpmnDataObject}</li>
 * <li>dropping an {@link Event}-compatible element creates a {@link BpmnIntermediateCatchEvent}</li>
 * </ul>
 * </p>
 */
@objid ("612e599a-55b6-11e2-877f-002564c97630")
class BodyHybridContainerDropEditPolicy extends AbstractEditPolicy {
    /**
     * Current behavioural state. Defaults to {@link Behaviour#HYBRID}
     */
    @objid ("68f7c21a-55b6-11e2-877f-002564c97630")
    private Behaviour behaviour = Behaviour.HYBRID;

    /**
     * This will be used to "get" the behaviour of a free zone policy.
     */
    @objid ("c486b38f-59a6-11e2-ae45-002564c97630")
    private SmartDropEditPolicy freeZonePolicy;

    /**
     * This will be used to "get" the behaviour of a lane container policy.
     */
    @objid ("c486b391-59a6-11e2-ae45-002564c97630")
    private BpmnLaneSetDropEditPolicy laneSetPolicy;

    /**
     * C'tor.
     */
    @objid ("612e59a2-55b6-11e2-877f-002564c97630")
    public BodyHybridContainerDropEditPolicy() {
        super();
        // Create an instance of both free zone and lane container policies.
        this.freeZonePolicy = new SmartDropEditPolicy();
        this.laneSetPolicy = new BpmnLaneSetDropEditPolicy();
    }

    @objid ("612e59a5-55b6-11e2-877f-002564c97630")
    @Override
    public void activate() {
        super.activate();
        switch (this.behaviour) {
        case HYBRID: {
            this.freeZonePolicy.activate();
            this.laneSetPolicy.activate();
            break;
        }
        case FREE_ZONE: {
            this.freeZonePolicy.activate();
            this.laneSetPolicy.deactivate();
            break;
        }
        case LANE_CONTAINER: {
            this.freeZonePolicy.deactivate();
            this.laneSetPolicy.activate();
            break;
        }
        default: {
            assert false : "Invalid behaviour : " + this.behaviour;
        }
        }
    }

    @objid ("612e59a8-55b6-11e2-877f-002564c97630")
    @Override
    public void deactivate() {
        switch (this.behaviour) {
        case HYBRID: {
            this.freeZonePolicy.deactivate();
            this.laneSetPolicy.deactivate();
            break;
        }
        case FREE_ZONE: {
            this.freeZonePolicy.deactivate();
            break;
        }
        case LANE_CONTAINER: {
            this.laneSetPolicy.deactivate();
            break;
        }
        default: {
            assert false : "Invalid behaviour : " + this.behaviour;
        }
        }
        super.deactivate();
    }

    @objid ("612e59ab-55b6-11e2-877f-002564c97630")
    @Override
    public void eraseSourceFeedback(final Request request) {
        super.eraseSourceFeedback(request);
        switch (this.behaviour) {
        case HYBRID: {
            GraphicalEditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                editPolicy.eraseSourceFeedback(request);
            }
            break;
        }
        case FREE_ZONE: {
            this.freeZonePolicy.eraseSourceFeedback(request);
            break;
        }
        case LANE_CONTAINER: {
            this.laneSetPolicy.eraseSourceFeedback(request);
            break;
        }
        default: {
            assert false : "Invalid behaviour : " + this.behaviour;
        }
        }
    }

    @objid ("612e59b0-55b6-11e2-877f-002564c97630")
    @Override
    public void eraseTargetFeedback(final Request request) {
        super.eraseTargetFeedback(request);
        switch (this.behaviour) {
        case HYBRID: {
            GraphicalEditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                editPolicy.eraseTargetFeedback(request);
            }
            break;
        }
        case FREE_ZONE: {
            this.freeZonePolicy.eraseTargetFeedback(request);
            break;
        }
        case LANE_CONTAINER: {
            this.laneSetPolicy.eraseTargetFeedback(request);
            break;
        }
        default: {
            assert false : "Invalid behaviour : " + this.behaviour;
        }
        }
    }

    @objid ("612e59b5-55b6-11e2-877f-002564c97630")
    @Override
    public Command getCommand(final Request request) {
        Command command = null;
        switch (this.behaviour) {
        case HYBRID: {
            GraphicalEditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                command = editPolicy.getCommand(request);
            }
            break;
        }
        case FREE_ZONE: {
            command = this.freeZonePolicy.getCommand(request);
            break;
        }
        case LANE_CONTAINER: {
            command = this.laneSetPolicy.getCommand(request);
            break;
        }
        default: {
            assert false : "Invalid behaviour : " + this.behaviour;
        }
        }
        return command;
    }

    @objid ("612e59bc-55b6-11e2-877f-002564c97630")
    @Override
    public EditPart getTargetEditPart(final Request request) {
        EditPart targetEditPart = null;
        switch (this.behaviour) {
        case HYBRID: {
            GraphicalEditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                targetEditPart = editPolicy.getTargetEditPart(request);
            }
            break;
        }
        case FREE_ZONE: {
            targetEditPart = this.freeZonePolicy.getTargetEditPart(request);
            break;
        }
        case LANE_CONTAINER: {
            targetEditPart = this.laneSetPolicy.getTargetEditPart(request);
            break;
        }
        default: {
            assert false : "Invalid behaviour : " + this.behaviour;
        }
        }
        return targetEditPart;
    }

    @objid ("612e59c3-55b6-11e2-877f-002564c97630")
    @Override
    public void setHost(final EditPart editpart) {
        super.setHost(editpart);
        this.freeZonePolicy.setHost(editpart);
        this.laneSetPolicy.setHost(editpart);
    }

    @objid ("612e59c8-55b6-11e2-877f-002564c97630")
    @Override
    public void showSourceFeedback(final Request request) {
        switch (this.behaviour) {
        case HYBRID: {
            GraphicalEditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                editPolicy.showSourceFeedback(request);
            }
            break;
        }
        case FREE_ZONE: {
            this.freeZonePolicy.showSourceFeedback(request);
            break;
        }
        case LANE_CONTAINER: {
            this.laneSetPolicy.showSourceFeedback(request);
            break;
        }
        default: {
            assert false : "Invalid behaviour : " + this.behaviour;
        }
        }
    }

    @objid ("612e59cc-55b6-11e2-877f-002564c97630")
    @Override
    public void showTargetFeedback(final Request request) {
        switch (this.behaviour) {
        case HYBRID: {
            GraphicalEditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                editPolicy.showTargetFeedback(request);
            }
            break;
        }
        case FREE_ZONE: {
            this.freeZonePolicy.showTargetFeedback(request);
            break;
        }
        case LANE_CONTAINER: {
            this.laneSetPolicy.showTargetFeedback(request);
            break;
        }
        default: {
            assert false : "Invalid behaviour : " + this.behaviour;
        }
        }
    }

    @objid ("612fe03b-55b6-11e2-877f-002564c97630")
    @Override
    public boolean understandsRequest(final Request request) {
        switch (this.behaviour) {
        case HYBRID: {
            GraphicalEditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                return editPolicy.understandsRequest(request);
            }
            // else
            return false;
        }
        case FREE_ZONE: {
            return this.freeZonePolicy.understandsRequest(request);
        }
        case LANE_CONTAINER: {
            return this.laneSetPolicy.understandsRequest(request);
        }
        default: {
            assert false : "Invalid behaviour : " + this.behaviour;
        }
        }
        return false;
    }

    /**
     * Sets the behaviour to adopt.
     * 
     * @param value the new behaviour.
     */
    @objid ("612fe042-55b6-11e2-877f-002564c97630")
    public void setBehaviour(final Behaviour value) {
        // If state actually changes, update he behaviour.
        if (this.behaviour != value) {
            Behaviour previousBehaviour = this.behaviour;
            this.behaviour = value;
            switch (this.behaviour) {
            case HYBRID: {
                if (previousBehaviour == Behaviour.LANE_CONTAINER) {
                    this.freeZonePolicy.activate();
                }
                if (previousBehaviour == Behaviour.FREE_ZONE) {
                    this.laneSetPolicy.activate();
                }
                break;
            }
            case FREE_ZONE: {
                if (previousBehaviour == Behaviour.LANE_CONTAINER) {
                    this.laneSetPolicy.deactivate();
                    this.freeZonePolicy.activate();
                }
                if (previousBehaviour == Behaviour.HYBRID) {
                    this.laneSetPolicy.deactivate();
                }
                break;
            }
            case LANE_CONTAINER: {
                if (previousBehaviour == Behaviour.FREE_ZONE) {
                    this.freeZonePolicy.deactivate();
                    this.laneSetPolicy.activate();
                }
                if (previousBehaviour == Behaviour.HYBRID) {
                    this.freeZonePolicy.deactivate();
                }
                break;
            }
            default: {
                assert false : "Invalid behaviour : " + this.behaviour;
            }
            }
        }
    }

    @objid ("612fe047-55b6-11e2-877f-002564c97630")
    private GraphicalEditPolicy getPolicyInvolvedWith(final Request request) {
        if (ModelElementDropRequest.TYPE.equals(request.getType())) {
            return getPolicyInvolvedWith((ModelElementDropRequest) request);
        }
        return null;
    }

    /**
     * <p>
     * This Request analysis method is used when in hybrid mode, to determine which policy should handle a specific request.
     * </p>
     * <p>
     * Basically, if the request concerns the creation of sub lanes, then the lane container policy should handle it. Otherwise, the free zone policy is concerned.
     * </p>
     * @return
     */
    @objid ("612fe04d-55b6-11e2-877f-002564c97630")
    private GraphicalEditPolicy getPolicyInvolvedWith(final ModelElementDropRequest request) {
        if (this.laneSetPolicy.getTargetEditPart(request) != null) {
            return this.laneSetPolicy;
        }
        if (this.freeZonePolicy.getTargetEditPart(request) != null) {
            return this.freeZonePolicy;
        }
        return null;
    }

    /**
     * Returns the current behaviour.
     * 
     * @return the current behaviour.
     */
    @objid ("612fe056-55b6-11e2-877f-002564c97630")
    public Behaviour getBehaviour() {
        return this.behaviour;
    }

    /**
     * * Specialization of the default drop request handling policy to add some smart interactions for {@link BpmnLane}.
     * <p>
     * Smart interactions are:
     * <ul>
     * <li>dropping a {@link Called}-compatible element creates a {@link BpmnCallActivity}</li>
     * <li>dropping a {@link Represents}-compatible element creates a {@link BpmnDataObject}</li>
     * <li>dropping an {@link Event}-compatible element creates a {@link BpmnIntermediateCatchEvent}</li>
     * <li>dropping a {@link BpmnFlowElement} element assigns it to the {@link BpmnLane}.
     * </ul>
     * </p>
     */
    @objid ("614b57a8-55b6-11e2-877f-002564c97630")
    private static class SmartDropEditPolicy extends DefaultElementDropEditPolicy {
        @objid ("614b57ac-55b6-11e2-877f-002564c97630")
        @Override
        protected EditPart getDropTargetEditPart(final ModelElementDropRequest request) {
            final Object model = getHost().getModel();
            if (!(model instanceof GmModel)) {
                return null;
            }
            
            final GmModel gmModel = (GmModel) model;
            IGmDiagram gmDiagram = gmModel.getDiagram();
            
            // If either of the dropped elements cannot be unmasked, return null.
            for (final MObject droppedElement : request.getDroppedElements()) {
                if (!gmModel.canUnmask(droppedElement) && (!request.isSmart() || !isSmartDropTarget(droppedElement, gmDiagram))) {
                    return null;
                }
            }
            
            // All dropped elements understood: return host!
            return getHost();
        }

        @objid ("614b57b5-55b6-11e2-877f-002564c97630")
        @Override
        protected Command getSmartDropCommand(final ModelElementDropRequest request) {
            if (!request.isSmart()) {
                return null;
            }
            
            final GmModel gmModel = (GmModel) getHost().getModel();
            IGmDiagram gmDiagram = gmModel.getDiagram();
            
            final CompoundCommand command = new CompoundCommand();
            
            BpmnLane lane = (BpmnLane) ((GmNodeModel) getHost().getModel()).getRelatedElement();
            for (MObject droppedElement : request.getDroppedElements()) {
                if (droppedElement instanceof BpmnFlowElement) {
                    command.add(getFlowElementSetLaneCommand((BpmnFlowElement) droppedElement, lane));
                } else if (isSmartCallActivity(droppedElement, gmDiagram)) {
                    return getCreateCallActivityCommand(lane, droppedElement, request.getDropLocation());
                } else if (isSmartServiceTask(droppedElement, gmDiagram)) {
                    return getCreateServiceTaskCommand(lane, droppedElement, request.getDropLocation());
                } else if (isSmartEvent(droppedElement, gmDiagram)) {
                    return getCreateEventCommand(lane, droppedElement, request.getDropLocation());
                } else if (isSmartDataObject(droppedElement, gmDiagram)) {
                    return getCreateDataObjectCommand(lane, droppedElement, request.getDropLocation());
                }
            }
            
            command.add(super.getDropCommand(request));
            return command.unwrap();
        }

        /**
         * This command assigns a {@link BpmnFlowElement} to a {@link BpmnLane}.
         */
        @objid ("5bf40956-a3e8-4a06-aae9-393541dffe11")
        private Command getFlowElementSetLaneCommand(BpmnFlowElement droppedElement, BpmnLane lane) {
            if (droppedElement.getLane().isEmpty()) {
                return new Command() {
                    @Override
                    public void execute() {
                        droppedElement.getLane().add(lane);
                    }
            
                    @Override
                    public boolean canExecute() {
                        return droppedElement.isModifiable();
                    }
                };
            } else {
                return null;
            }
        }

        @objid ("14dc2c99-6d6e-4996-8758-305636222af0")
        private boolean isSmartDropTarget(MObject droppedElement, IGmDiagram gmDiagram) {
            return isSmartCallActivity(droppedElement, gmDiagram) || isSmartServiceTask(droppedElement, gmDiagram) || isSmartDataObject(droppedElement, gmDiagram) || isSmartEvent(droppedElement, gmDiagram);
        }

        @objid ("fd240ef7-f16a-4f0a-b108-0fe434749870")
        private Command getCreateCallActivityCommand(MObject owner, MObject toUnmask, Point dropLocation) {
            return new CreateCallActivityCommand(dropLocation, toUnmask, getHost(), owner);
        }

        @objid ("d919d23e-0933-4085-ad86-effdc3cbc631")
        private Command getCreateDataObjectCommand(MObject owner, MObject toUnmask, Point dropLocation) {
            return new CreateDataObjectCommand(dropLocation, (ModelElement) toUnmask, getHost(), owner);
        }

        @objid ("bf3a7f91-913b-4f0b-9928-86632ae93906")
        private boolean isSmartCallActivity(MObject droppedElement, IGmDiagram gmDiagram) {
            IModelManager modelManager = gmDiagram.getModelManager();
            MMetamodel metamodel = modelManager.getMetamodel();
            MClass sourceMetaclass = metamodel.getMClass(BpmnCallActivity.MQNAME);
            MClass linkMetaclass = metamodel.getMClass(MethodologicalLink.MQNAME);
            IMdaExpert mdaExpert = modelManager.getMdaExpert();
            return mdaExpert.canLink(Called.MdaTypes.STEREOTYPE_ELT, linkMetaclass, sourceMetaclass, droppedElement.getMClass());
        }

        @objid ("fdabf320-d496-4d7b-8826-56e96ac022db")
        private boolean isSmartDataObject(MObject droppedElement, IGmDiagram gmDiagram) {
            IModelManager modelManager = gmDiagram.getModelManager();
            MMetamodel metamodel = modelManager.getMetamodel();
            MClass sourceMetaclass = metamodel.getMClass(BpmnDataObject.MQNAME);
            MClass linkMetaclass = metamodel.getMClass(MethodologicalLink.MQNAME);
            IMdaExpert mdaExpert = modelManager.getMdaExpert();
            return mdaExpert.canLink(Represents.MdaTypes.STEREOTYPE_ELT, linkMetaclass, sourceMetaclass, droppedElement.getMClass()) ||
                    mdaExpert.canLink(State.MdaTypes.STEREOTYPE_ELT, linkMetaclass, sourceMetaclass, droppedElement.getMClass());
        }

        @objid ("22b53325-bd37-4785-b1c8-1730f2d803bb")
        private Command getCreateEventCommand(MObject owner, MObject toUnmask, Point dropLocation) {
            return new CreateEventCommand(dropLocation, (ModelElement) toUnmask, getHost(), owner);
        }

        @objid ("1d69790d-e832-44e3-9d24-534090d76770")
        private boolean isSmartEvent(MObject droppedElement, IGmDiagram gmDiagram) {
            IModelManager modelManager = gmDiagram.getModelManager();
            MMetamodel metamodel = modelManager.getMetamodel();
            MClass sourceMetaclass = metamodel.getMClass(BpmnIntermediateCatchEvent.MQNAME);
            MClass linkMetaclass = metamodel.getMClass(MethodologicalLink.MQNAME);
            IMdaExpert mdaExpert = modelManager.getMdaExpert();
            return mdaExpert.canLink(Event.MdaTypes.STEREOTYPE_ELT, linkMetaclass, sourceMetaclass, droppedElement.getMClass());
        }

        @objid ("e8668000-100c-405a-8d6f-6859a46086f0")
        private boolean isSmartServiceTask(MObject droppedElement, IGmDiagram gmDiagram) {
            IModelManager modelManager = gmDiagram.getModelManager();
            MMetamodel metamodel = modelManager.getMetamodel();
            MClass sourceMetaclass = metamodel.getMClass(BpmnServiceTask.MQNAME);
            MClass linkMetaclass = metamodel.getMClass(MethodologicalLink.MQNAME);
            IMdaExpert mdaExpert = modelManager.getMdaExpert();
            return mdaExpert.canLink(Called.MdaTypes.STEREOTYPE_ELT, linkMetaclass, sourceMetaclass, droppedElement.getMClass());
        }

        @objid ("7a04f55e-f97b-4300-92a2-0370078d1ba7")
        private Command getCreateServiceTaskCommand(MObject owner, MObject toUnmask, Point dropLocation) {
            return new CreateServiceTaskCommand(dropLocation, toUnmask, getHost(), owner);
        }

    }

}
