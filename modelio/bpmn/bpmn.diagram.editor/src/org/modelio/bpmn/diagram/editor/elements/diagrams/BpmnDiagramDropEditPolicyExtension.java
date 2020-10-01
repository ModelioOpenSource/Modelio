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

import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramElementDropEditPolicyExtension;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramElementDropEditPolicy;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.StandardMetamodel;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnCollaborationDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Process;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Drop extension for Bpmn diagrams.
 * <p>
 * Smart interactions are:
 * <ul>
 * <li>dropping a {@link BpmnProcess} or a {@link Represents}-compatible element creates a {@link BpmnParticipant}</li>
 * <li>dropping a {@link Process}-compatible element creates a {@link BpmnProcess} and a {@link BpmnParticipant}</li>
 * <li>dropping a {@link BpmnMessage} unmasks the message in a specific way</li>
 * </ul>
 * 
 * Warning: available smart interactions depends on which BPMN diagram type is opened.
 * </p>
 */
@objid ("d25e3f4d-4e55-46b4-8c11-1940e066c12e")
public class BpmnDiagramDropEditPolicyExtension extends AbstractDiagramElementDropEditPolicyExtension {
    @objid ("734395d7-9476-4360-b01e-bad8a9883d6d")
    @Override
    public Command getUnmaskCommandFor(DiagramElementDropEditPolicy dropPolicy, MObject droppedElement, Point dropLocation) {
        IGmDiagram gmDiagram = getHostDiagram(dropPolicy);
        final AbstractDiagram diag = gmDiagram.getRelatedElement();
        
        // FIXME d & d in an Embedded Diagram should swap diag to the embedded diagram itself
        
        if (diag instanceof BpmnCollaborationDiagram) {
            if (isSmartProcess(droppedElement, gmDiagram)) {
                return getCreateParticipantCommand(dropPolicy, droppedElement, dropLocation);
            } else if (droppedElement instanceof BpmnMessage) {
                return getUnmaskMessageCommand(dropPolicy, droppedElement, dropLocation);
            } else if (droppedElement instanceof BpmnParticipant) {
                // Never unmask participants from another collaboration
                if (Objects.equals(findParentCollaboration(droppedElement), findParentCollaboration(diag))) {
                    return super.getUnmaskCommandFor(dropPolicy, droppedElement, dropLocation);
                }
            } else if (droppedElement != null) {
                // TODO improve check for a "bpmn diagram" element
                if (droppedElement.getMClass().getOrigin().getName().equals(StandardMetamodel.NAME)) {
                    return super.getUnmaskCommandFor(dropPolicy, droppedElement, dropLocation);
                }
            }
        } else {
            // TODO improve check for a "bpmn diagram" element
            if (droppedElement.getMClass().getOrigin().getName().equals(StandardMetamodel.NAME)) {
                return super.getUnmaskCommandFor(dropPolicy, droppedElement, dropLocation);
            }
        }
        return null;
    }

    @objid ("447ddc53-9afd-4b99-98a6-3429fbce9a7a")
    @Override
    public boolean canUnmask(DiagramElementDropEditPolicy dropPolicy, MObject candidate) {
        IGmDiagram gmDiagram = getHostDiagram(dropPolicy);
        final AbstractDiagram diag = gmDiagram.getRelatedElement();
        
        if (candidate instanceof BpmnGroup) {
            return false;
        } else if (candidate instanceof BpmnMessage
                || candidate instanceof BpmnMessageFlow
                || candidate instanceof BpmnParticipant
                || isSmartProcess(candidate, gmDiagram)) {
            return diag instanceof BpmnCollaborationDiagram;
        } else if (candidate instanceof BpmnLane) {
            return diag instanceof BpmnProcessDesignDiagram;
        } else if (candidate instanceof AbstractDiagram
                || candidate instanceof Behavior
                || candidate instanceof BpmnDataAssociation
                || candidate instanceof BpmnFlowElement
                || candidate instanceof Dependency
                || candidate instanceof Document
                || candidate instanceof Note) {
            return true;
        }
        return false;
    }

    /**
     * Hierarchy is complete for BpmnCollaboration and BpmnProcess.
     */
    @objid ("0afeeac8-4c97-48e9-ac59-619d8017508c")
    @Override
    public boolean isToBeAddedToHierarchy(IGmDiagram context, Deque<MObject> hierarchy, MObject candidate) {
        boolean isBpmnElement = candidate != null && candidate.getMClass().getOrigin().getName().equals(StandardMetamodel.NAME) && candidate.getClass().getName().startsWith("org.modelio.metamodel.bpmn");
        return isBpmnElement && !(candidate instanceof BpmnCollaboration) && (hierarchy.isEmpty() || !(hierarchy.getFirst() instanceof BpmnProcess));
    }

    @objid ("b00823fe-23da-4ded-9660-a133b279e209")
    @Override
    public MObject getParentInGraphicalHierarchy(IGmDiagram context, MObject element) {
        MObject ret = element.getCompositionOwner();
        if (element instanceof BpmnBoundaryEvent) {
            BpmnBoundaryEvent event = (BpmnBoundaryEvent) element;
            ret = event.getAttachedToRef();
        } else if (element instanceof BpmnFlowElement) {
            BpmnFlowElement flowelement = (BpmnFlowElement) element;
            if (!flowelement.getLane().isEmpty()) {
                ret = flowelement.getLane().get(0);
            }
        } else if (element instanceof BpmnLane) {
            BpmnLane lane = (BpmnLane) element;
            ret = lane.getLaneSet();
        } else if (element instanceof BpmnLaneSet) {
            BpmnLaneSet laneSet = (BpmnLaneSet) element;
            ret = laneSet.getCompositionOwner();
        }
        
        if (ret instanceof BpmnProcess) {
            AbstractDiagram diagram = context.getRelatedElement();
            if (diagram instanceof BpmnCollaborationDiagram) {
                BpmnCollaboration collaboration = findParentCollaboration(diagram);
                if (collaboration != null) {
                    BpmnParticipant existingParticipant = BpmnDiagramDropEditPolicyExtension.getExistingParticipant(collaboration, ret);
                    if (existingParticipant != null) {
                        ret = existingParticipant;
                    }
                }
            } else if (context instanceof BpmnProcessDesignDiagram) {
                return ret.getCompositionOwner();
            } else if (diagram instanceof BpmnSubProcessDiagram) {
                return null;
            }
        }
        return ret;
    }

    @objid ("61ee9c69-55b6-11e2-877f-002564c97630")
    private Command getUnmaskMessageCommand(DiagramElementDropEditPolicy dropPolicy, MObject toUnmask, Point dropLocation) {
        return new UnmaskBpmnMessageCommand((BpmnMessage) toUnmask, (AbstractDiagramEditPart) dropPolicy.getHost(), new Rectangle(dropLocation, new Dimension(-1, -1)), dropLocation);
    }

    @objid ("17e14755-bbe9-46f5-bd9f-2eb211012dfa")
    private Command getCreateParticipantCommand(DiagramElementDropEditPolicy dropPolicy, MObject toUnmask, Point dropLocation) {
        IGmDiagram gmDiagram = getHostDiagram(dropPolicy);
        final AbstractDiagram diag = gmDiagram.getRelatedElement();
        return new CreateParticipantCommand(dropLocation, (ModelElement) toUnmask, dropPolicy.getHost(), findParentCollaboration(diag));
    }

    @objid ("be6ac11b-c720-4b23-9e0d-5bcef5f2743e")
    private BpmnCollaboration findParentCollaboration(MObject elt) {
        MObject owner = elt.getCompositionOwner();
        if (owner == null) {
            return null;
        } else if (owner instanceof BpmnCollaboration) {
            return (BpmnCollaboration) owner;
        } else {
            return findParentCollaboration(owner);
        }
    }

    /**
     * @return a participant already referencing the type being unmasked.
     */
    @objid ("913e2a6c-8009-4ac6-b05f-93bb6873f446")
    private static BpmnParticipant getExistingParticipant(BpmnCollaboration collaboration, MObject type) {
        for (BpmnParticipant p : collaboration.getParticipants()) {
            if (type instanceof BpmnProcess) {
                BpmnProcess process = p.getProcess();
                if (process != null) {
                    if (type.equals(process)) {
                        return p;
                    } else if (Objects.equals(type, Process.getTarget(process))) {
                        return p;
                    }
                }
            } else {
                if (Objects.equals(type, Represents.getTarget(p))) {
                    return p;
                }
            }
        }
        return null;
    }

    @objid ("5b9b5d5b-c619-4272-91e0-eba08a963e02")
    private IGmDiagram getHostDiagram(DiagramElementDropEditPolicy dropPolicy) {
        final GmModel gmModel = (GmModel) dropPolicy.getHost().getModel();
        return gmModel.getDiagram();
    }

    @objid ("bc015949-cd31-460f-b20d-0a0341fb6f7f")
    protected boolean isSmartProcess(MObject droppedElement, IGmDiagram gmDiagram) {
        if (droppedElement instanceof BpmnProcess) {
            return true;
        }
        
        IModelManager modelManager = gmDiagram.getModelManager();
        MMetamodel metamodel = modelManager.getMetamodel();
        MClass linkMetaclass = metamodel.getMClass(MethodologicalLink.MQNAME);
        IMdaExpert mdaExpert = modelManager.getMdaExpert();
        return mdaExpert.canLink(Represents.MdaTypes.STEREOTYPE_ELT, linkMetaclass, metamodel.getMClass(BpmnParticipant.MQNAME), droppedElement.getMClass())
                || mdaExpert.canLink(Process.MdaTypes.STEREOTYPE_ELT, linkMetaclass, metamodel.getMClass(BpmnProcess.MQNAME), droppedElement.getMClass());
    }

    /**
     * Specific command that will unmask a message and the link between it and the annoted model element.
     */
    @objid ("6169dc00-55b6-11e2-877f-002564c97630")
    private static class UnmaskBpmnMessageCommand extends Command {
        @objid ("6169dc02-55b6-11e2-877f-002564c97630")
        private final BpmnMessage theBpmnMessage;

        @objid ("6169dc06-55b6-11e2-877f-002564c97630")
        private final Object constraint;

        @objid ("71f22f9b-55c1-11e2-9337-002564c97630")
        private final IGmDiagram diagram;

        @objid ("71f22f9d-55c1-11e2-9337-002564c97630")
        private final AbstractDiagramEditPart host;

        @objid ("471f1a2b-db38-4a0c-a60c-4b599487ac1f")
        private final Point dropLocation;

        /**
         * C'tor.
         * 
         * @param theBpmnMessage the message to unmask.
         * @param host the edit part of the diagram in which to unmask it.
         * @param initialLayoutData the initial layout data for the node part
         * @param dropLocation the drop location retrieved from the request.
         */
        @objid ("6169dc12-55b6-11e2-877f-002564c97630")
        public UnmaskBpmnMessageCommand(final BpmnMessage theBpmnMessage, final AbstractDiagramEditPart host, final Object initialLayoutData, final Point dropLocation) {
            this.theBpmnMessage = theBpmnMessage;
            this.host = host;
            this.diagram = (IGmDiagram) host.getModel();
            this.constraint = initialLayoutData;
            this.dropLocation = dropLocation;
        }

        @objid ("6169dc21-55b6-11e2-877f-002564c97630")
        @Override
        public void execute() {
            // unmask the node part
            GmNodeModel targetModel = this.diagram.unmaskAsChild(this.theBpmnMessage, this.constraint);
            
            // Simulate creation of the link:
            // 1 - start creation of a connection
            final CreateConnectionRequest req = new CreateConnectionRequest();
            req.setLocation(this.dropLocation);
            req.setSize(new Dimension(-1, -1));
            req.setFactory(new ModelioLinkCreationContext(this.theBpmnMessage));
            req.setType(RequestConstants.REQ_CONNECTION_START);
            
            // Look for edit part of subject element... If none found, unmask it.
            List<BpmnMessageFlow> subjects = this.theBpmnMessage.getMessageFlow();
            for (BpmnMessageFlow subject : subjects) {
                EditPart sourceEditPart = getEditPartFor(subject, req);
                if (sourceEditPart == null) {
                    unmaskElement(subject, this.dropLocation.getTranslated(-50, 0));
                    sourceEditPart = getEditPartFor(subject, req);
                    if (sourceEditPart == null) {
                        // Failed to get subject element, revert what was done until now and abort.
                        targetModel.delete();
                        return;
                    }
                }
            }
        }

        @objid ("6169dc24-55b6-11e2-877f-002564c97630")
        private EditPart getEditPartFor(final MObject element, final CreateConnectionRequest req) {
            // Search all gm related the element
            Collection<GmModel> models = this.diagram.getAllGMRelatedTo(new MRef(element));
            // This boolean will be used to note that the searched End was found
            // unmasked at least once.
            for (GmModel model : models) {
                // For each gm, search the corresponding edit part
                EditPart editPart = (EditPart) this.host.getViewer().getEditPartRegistry().get(model);
                if (editPart != null) {
                    // See if this edit part accepts the reconnection request
                    EditPart targetEditPart = editPart.getTargetEditPart(req);
                    if (targetEditPart != null) {
                        return targetEditPart;
                    }
                }
            }
            return null;
        }

        @objid ("6169dc2e-55b6-11e2-877f-002564c97630")
        private void unmaskElement(final MObject element, final Point location) {
            ModelElementDropRequest dropRequest = new ModelElementDropRequest();
            dropRequest.setDroppedElements(new MObject[] { element });
            dropRequest.setLocation(location);
            EditPart targetEditPart = this.host.getTargetEditPart(dropRequest);
            Command command = targetEditPart.getCommand(dropRequest);
            if (command != null && command.canExecute()) {
                command.execute();
            }
        }

    }

    /**
     * Specific command that unmask a {@link BpmnProcess}, a {@link Represents}-compatible element or a {@link Process}-compatible element as a {@link BpmnParticipant}.
     * <p>
     * When no participant is found for the type, a new one is created along with a {@link BpmnCollaboration}.
     * </p>
     */
    @objid ("e4971dc7-eff6-4dd1-94d9-baec6b174322")
    private static class CreateParticipantCommand extends Command {
        @objid ("ea82bf1c-af75-43f2-90dc-d59affb1b052")
        private ModelElement type;

        @objid ("37bc51a7-d72d-4341-8743-d032d2bd10d0")
        private BpmnCollaboration parentElement;

        @objid ("53f85a11-f2a7-4971-b6c0-0d59b78b0ef1")
        private EditPart editPart;

        @objid ("29096f1e-2b81-41aa-9a8f-130950ddbd9a")
        private Point dropLocation;

        /**
         * Initialize the command.
         * 
         * @param dropLocation The location of the element in the diagram
         * @param type The type to unmask
         * @param editPart The destination edit part that will own the participant.
         * @param parentElement The element that will own the participant.
         */
        @objid ("922313b9-5316-41b0-8cb2-d8b8e2d110fe")
        public CreateParticipantCommand(final Point dropLocation, final ModelElement type, final EditPart editPart, final BpmnCollaboration parentElement) {
            this.type = type;
            this.dropLocation = dropLocation;
            this.editPart = editPart;
            this.parentElement = parentElement;
        }

        @objid ("33eae2eb-7c51-458b-a417-5a16ff81883b")
        @Override
        public void execute() {
            final GmModel gmModel = (GmModel) this.editPart.getModel();
            final IGmDiagram gmDiagram = gmModel.getDiagram();
            final IModelManager modelManager = gmDiagram.getModelManager();
            final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
            
            MMetamodel metamodel = modelManager.getMetamodel();
            MClass linkMetaclass = metamodel.getMClass(MethodologicalLink.MQNAME);
            IMdaExpert mdaExpert = modelManager.getMdaExpert();
            
            BpmnParticipant newParticipant = BpmnDiagramDropEditPolicyExtension.getExistingParticipant(this.parentElement, this.type);
            if (newParticipant == null) {
                // Create the Participant
                newParticipant = modelFactory.createBpmnParticipant();
                newParticipant.setContainer(this.parentElement);
                if (this.type instanceof BpmnProcess) {
                    newParticipant.setProcess((BpmnProcess) this.type);
                } else if (mdaExpert.canLink(Represents.MdaTypes.STEREOTYPE_ELT, linkMetaclass, metamodel.getMClass(BpmnParticipant.MQNAME), this.type.getMClass())) {
                    Represents.setTarget(newParticipant, this.type);
                } else {
                    newParticipant.delete();
                    unmaskElement(this.type);
                    return;
                }
                newParticipant.setName(this.type.getName());
            }
            
            unmaskElement(newParticipant);
        }

        @objid ("89d2061a-06db-41b4-9f77-f12d4bbe73d5")
        private void unmaskElement(final MObject el) {
            final ModelioCreationContext gmCreationContext = new ModelioCreationContext(el);
            
            final CreateRequest creationRequest = new CreateRequest();
            creationRequest.setLocation(this.dropLocation);
            creationRequest.setSize(new Dimension(-1, -1));
            creationRequest.setFactory(gmCreationContext);
            
            final Command cmd = this.editPart.getTargetEditPart(creationRequest).getCommand(creationRequest);
            if (cmd != null && cmd.canExecute()) {
                cmd.execute();
            }
        }

        @objid ("73b68c2c-bcdb-4b9a-9995-9bff0dd7cb78")
        @Override
        public boolean canExecute() {
            return this.type != null &&
                    this.parentElement != null &&
                    this.parentElement.isValid() &&
                    this.parentElement.getStatus().isModifiable();
        }

    }

}
