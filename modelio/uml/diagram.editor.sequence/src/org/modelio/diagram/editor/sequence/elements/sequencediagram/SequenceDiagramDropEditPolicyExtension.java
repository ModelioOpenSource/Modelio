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

package org.modelio.diagram.editor.sequence.elements.sequencediagram;

import java.util.Deque;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramElementDropEditPolicyExtension;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramElementDropEditPolicy;
import org.modelio.diagram.elements.common.abstractdiagram.UnmaskConstraintCommand;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.metamodel.StandardMetamodel;
import org.modelio.metamodel.diagrams.SequenceDiagram;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Drop extension for {@link SequenceDiagram}.
 * <p>
 * Smart interactions are:
 * <ul>
 * <li>dropping an {@link Instance}, {@link Attribute}, {@link AssociationEnd}, {@link GeneralClass} creates an {@link Instance} and unmasks a {@link Lifeline}</li>
 * <li>dropping an {@link Interaction} creates an {@link InteractionUse} and unmasks it</li>
 * </ul>
 * </p>
 */
@objid ("848db8f0-41af-4e9f-aa6e-622bf63e66a6")
public class SequenceDiagramDropEditPolicyExtension extends AbstractDiagramElementDropEditPolicyExtension {
    @objid ("5d7602d6-46b4-469f-8114-18c1c5b5fa3f")
    @Override
    public Command getUnmaskCommandFor(DiagramElementDropEditPolicy dropPolicy, MObject droppedElement, Point dropLocation) {
        if ((droppedElement instanceof AssociationEnd)
                || (droppedElement instanceof Attribute)
                || (droppedElement instanceof Instance && !(droppedElement instanceof Port))
                || (droppedElement instanceof GeneralClass)) {
            return getSmartCreateLifelineCommand(dropPolicy, droppedElement, dropLocation);
        } else if (droppedElement instanceof Interaction) {
            return getSmartCreateInteractionUseCommand(dropPolicy, droppedElement, dropLocation);
        } else if (droppedElement != null) {
            // TODO improve check for a "sequence diagram" element
            if (droppedElement.getMClass().getOrigin().getName().equals(StandardMetamodel.NAME)) {
                Command cmd = (Command) droppedElement.accept(new StandardVisitorImpl(dropPolicy, dropLocation));
                return cmd != null ? cmd : super.getUnmaskCommandFor(dropPolicy, droppedElement, dropLocation);
            }
        }
        return null;
    }

    @objid ("334eb72c-21c4-457f-9e20-4d6849513568")
    @Override
    public boolean canUnmask(DiagramElementDropEditPolicy dropPolicy, MObject candidate) {
        // Gm doesn't know how to handle this element directly, look if
        // it is an toUnmask for which we can do something "smarter".
        if (!(candidate instanceof AssociationEnd)
                && !(candidate instanceof Attribute)
                && !(candidate instanceof Instance || candidate instanceof Port)
                && !(candidate instanceof GeneralClass)
                && !(candidate instanceof Interaction)) {
            // It is not a smart interaction
            //
            // The Gm cannot unmask this element directly, and we don't know
            // what to do with it... return null
            return false;
        }
        
        // All dropped elements understood: return host!
        return true;
    }

    @objid ("b204d9b5-bfaa-423d-922f-66d11258c3bc")
    @Override
    public boolean isToBeAddedToHierarchy(IGmDiagram context, Deque<MObject> hierarchy, MObject candidate) {
        if (candidate == null || candidate instanceof Interaction) {
            return false;
        }
        
        // Make sure the element is part of the current interaction
        ModelElement interaction = context.getRelatedElement().getOrigin();
        MObject parent = candidate.getCompositionOwner();
        while (parent != null) {
            if (parent.equals(interaction)) {
                return true;
            } else {
                parent = parent.getCompositionOwner();
            }
        }
        return false;
    }

    @objid ("d980a86a-55b6-11e2-877f-002564c97630")
    private Command getSmartCreateLifelineCommand(DiagramElementDropEditPolicy dropPolicy, MObject toUnmask, Point dropLocation) {
        final GmModel gmModel = (GmModel) dropPolicy.getHost().getModel();
        final SequenceDiagram owner = (SequenceDiagram) gmModel.getRelatedElement();
        ModelElement origin = owner.getOrigin();
        if (origin instanceof Interaction) {
            return new SmartCreateLifelineCommand(dropLocation, toUnmask, dropPolicy.getHost(), origin);
        } else {
            // No interaction, return null
            return null;
        }
    }

    @objid ("d980a87f-55b6-11e2-877f-002564c97630")
    private Command getSmartCreateInteractionUseCommand(DiagramElementDropEditPolicy dropPolicy, MObject toUnmask, Point dropLocation) {
        final GmSequenceDiagram diagramModel = (GmSequenceDiagram) dropPolicy.getHost().getModel();
        final SequenceDiagram owner = diagramModel.getRelatedElement();
        Interaction origin = (Interaction) owner.getOrigin();
        return new SmartCreateInteractionUseCommand(dropLocation, (Interaction) toUnmask, dropPolicy.getHost(), origin);
    }

    @objid ("50a8ac2c-c8e5-4b13-aa6d-07fefde77edb")
    private static class StandardVisitorImpl extends DefaultModelVisitor {
        @objid ("052cda81-7477-476a-9b0e-b2779f4e0136")
        private Point dropLocation;

        @objid ("84f36b49-a999-45fc-9503-5b7d168039b0")
        private DiagramElementDropEditPolicy dropPolicy;

        @objid ("5edcc9b7-c8ce-46fb-9994-08743c2667b9")
        public StandardVisitorImpl(DiagramElementDropEditPolicy dropPolicy, Point dropLocation) {
            this.dropPolicy = dropPolicy;
            this.dropLocation = dropLocation;
        }

        @objid ("6f1ea738-9f0a-45d6-9939-9ce564db1494")
        @Override
        public Object visitConstraint(final Constraint theConstraint) {
            return new UnmaskConstraintCommand(theConstraint, (AbstractDiagramEditPart) this.dropPolicy.getHost(), new Rectangle(this.dropLocation, new Dimension(-1, -1)));
        }

    }

}
