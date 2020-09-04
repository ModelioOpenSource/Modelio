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

package org.modelio.diagram.editor.state.elements.statediagram;

import java.util.Deque;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramElementDropEditPolicyExtension;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramElementDropEditPolicy;
import org.modelio.diagram.elements.common.abstractdiagram.UnmaskConstraintCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.metamodel.StandardMetamodel;
import org.modelio.metamodel.diagrams.StateMachineDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Drop extension for {@link StateMachineDiagram}.
 * <p>
 * Smart interactions are:
 * <ul>
 * <li>dropping a state machine creates a submachine State referencing the drag and dropped State Machine.</li>
 * </ul>
 * </p>
 */
@objid ("ff266bbe-c270-46e7-b0f2-83ee4e9e5793")
public class StateDiagramDropEditPolicyExtension extends AbstractDiagramElementDropEditPolicyExtension {
    @objid ("2bb00199-c0a5-4c44-845e-6436b25e3ffd")
    @Override
    public Command getUnmaskCommandFor(DiagramElementDropEditPolicy dropPolicy, MObject droppedElement, Point dropLocation) {
        if (droppedElement instanceof StateMachine) {
            return getBehaviorUnmaskCommand(dropPolicy, (StateMachine) droppedElement, dropLocation);
        } else if (droppedElement != null) {
            // TODO improve check for a "state diagram" element
            if (droppedElement.getMClass().getOrigin().getName().equals(StandardMetamodel.NAME)) {
                Command cmd = (Command) droppedElement.accept(new StandardVisitorImpl(dropPolicy, dropLocation));
                return cmd != null ? cmd : super.getUnmaskCommandFor(dropPolicy, droppedElement, dropLocation);
                //return super.getUnmaskCommandFor(dropPolicy, droppedElement, dropLocation);
            }
        }
        return null;
    }

    /**
     * Overridden to allow independent unmasking of the content of a Composite State (i.e. a State with at least 1 region).
     */
    @objid ("89cc0276-54ae-48ee-ad9f-4ad01cdccb0f")
    @Override
    public boolean isToBeAddedToHierarchy(IGmDiagram context, Deque<MObject> hierarchy, MObject candidate) {
        if (candidate == null || candidate instanceof StateMachine || (candidate instanceof Region && ((Region) candidate).getParent() == null)) {
            return false;
        }
        
        // Make sure the element is part of the current state machine
        ModelElement stateMachine = context.getRelatedElement().getOrigin();
        MObject parent = candidate.getCompositionOwner();
        while (parent != null) {
            if (parent.equals(stateMachine)) {
                return true;
            } else {
                parent = parent.getCompositionOwner();
            }
        }
        return false;
    }

    /**
     * Smart unmask: StateMachine are unmasked as CallBehavior (see {@link CreateStateMachineStateCommand}).
     */
    @objid ("bf5f1a6f-56c5-4c03-a44d-dbddb23efafa")
    private Command getBehaviorUnmaskCommand(DiagramElementDropEditPolicy dropPolicy, final StateMachine toUnmask, final Point dropLocation) {
        final GmModel gmModel = (GmModel) dropPolicy.getHost().getModel();
        final IGmDiagram gmDiagram = gmModel.getDiagram();
        final StateMachineDiagram diag = (StateMachineDiagram) gmDiagram.getRelatedElement();
        final StateMachine owner = (StateMachine) diag.getOrigin();
        return new CreateStateMachineStateCommand(dropLocation, toUnmask, dropPolicy.getHost(), owner);
    }

    /**
     * Create a {@link State} linked to the given {@link StateMachine} and unmask it in the diagram.
     */
    @objid ("f58b9cba-55b6-11e2-877f-002564c97630")
    private static class CreateStateMachineStateCommand extends Command {
        @objid ("f58b9cbd-55b6-11e2-877f-002564c97630")
        private StateMachine stateMachine;

        @objid ("f58b9cc1-55b6-11e2-877f-002564c97630")
        private StateMachine parentElement;

        @objid ("fe4d2f74-5a5b-11e2-9e33-00137282c51b")
        private EditPart editPart;

        @objid ("8dcc2736-3b3c-428e-8fcb-4dc36126c9b4")
        private Point dropLocation;

        /**
         * Initialize the command.
         * 
         * @param dropLocation The location of the element in the diagram
         * @param toUnmask The operation to unmask
         * @param editPart The destination edit part that will own the call operation.
         * @param parentElement The element that will own the call operation action.
         */
        @objid ("f58b9cc4-55b6-11e2-877f-002564c97630")
        public CreateStateMachineStateCommand(final Point dropLocation, final StateMachine toUnmask, final EditPart editPart, final StateMachine parentElement) {
            this.stateMachine = toUnmask;
            this.dropLocation = dropLocation;
            this.editPart = editPart;
            this.parentElement = parentElement;
        }

        @objid ("f58d235f-55b6-11e2-877f-002564c97630")
        @Override
        public void execute() {
            final GmModel gmModel = (GmModel) this.editPart.getModel();
            final IGmDiagram gmDiagram = gmModel.getDiagram();
            final IStandardModelFactory modelFactory = gmDiagram.getModelManager().getModelFactory()
                    .getFactory(IStandardModelFactory.class);
            
            // Create the node
            final State el = modelFactory.createState();
            
            // Attach to its parent
            el.setParent(this.parentElement.getTop());
            
            // Attach to the dropped element
            el.setName(this.stateMachine.getName());
            el.setSubMachine(this.stateMachine);
            
            // Also create ConnectionPointReference for each EntryPoint and
            // ExitPoint on the StateMachine.
            for (EntryPointPseudoState entry : this.stateMachine.getEntryPoint()) {
                ConnectionPointReference reference = modelFactory.createConnectionPointReference();
                reference.setName(entry.getName());
                reference.setEntry(entry);
                reference.setOwnerState(el);
            }
            for (ExitPointPseudoState exit : this.stateMachine.getExitPoint()) {
                ConnectionPointReference reference = modelFactory.createConnectionPointReference();
                reference.setName(exit.getName());
                reference.setExit(exit);
                reference.setOwnerState(el);
            }
            
            // Unmask the created node
            unmaskElement(el);
        }

        /**
         * Unmask the given element in the destination edit part.
         * 
         * @param el The element to unmask
         */
        @objid ("f58d2362-55b6-11e2-877f-002564c97630")
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

        @objid ("f58d2369-55b6-11e2-877f-002564c97630")
        @Override
        public boolean canExecute() {
            return this.parentElement != null && this.parentElement.isValid() && this.parentElement.isModifiable();
        }

    }

    @objid ("415b24cd-7c7c-4009-a831-8e66225984bd")
    private static class StandardVisitorImpl extends DefaultModelVisitor {
        @objid ("11d7e68a-6f90-4f15-9a57-948dfa0684a6")
        private Point dropLocation;

        @objid ("373d9b03-61e6-4568-9dfa-a5de0c175900")
        private DiagramElementDropEditPolicy dropPolicy;

        @objid ("290576bd-624f-4179-b503-f2ae9c37381f")
        public StandardVisitorImpl(DiagramElementDropEditPolicy dropPolicy, Point dropLocation) {
            this.dropPolicy = dropPolicy;
            this.dropLocation = dropLocation;
        }

        @objid ("902b8fb6-a3ed-4e1b-9320-69c52d16b2ac")
        @Override
        public Object visitConstraint(final Constraint theConstraint) {
            return new UnmaskConstraintCommand(theConstraint, (AbstractDiagramEditPart) this.dropPolicy.getHost(), new Rectangle(this.dropLocation, new Dimension(-1, -1)));
        }

    }

}
