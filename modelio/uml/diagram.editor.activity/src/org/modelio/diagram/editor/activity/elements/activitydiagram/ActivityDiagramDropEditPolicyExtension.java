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

package org.modelio.diagram.editor.activity.elements.activitydiagram;

import java.util.Deque;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.editor.activity.elements.commands.CreateActivityParameterNodeCommand;
import org.modelio.diagram.editor.activity.elements.commands.CreateCallBehaviorCommand;
import org.modelio.diagram.editor.activity.elements.commands.CreateCallOperationCommand;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramElementDropEditPolicyExtension;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramElementDropEditPolicy;
import org.modelio.diagram.elements.common.abstractdiagram.UnmaskConstraintCommand;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.metamodel.StandardMetamodel;
import org.modelio.metamodel.diagrams.ActivityDiagram;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Drop extension for {@link ActivityDiagram}.
 * <p>
 * Smart interactions are:
 * <ul>
 * <li>dropping an {@link Operation} creates a {@link CallOperationAction}</li>
 * <li>dropping a {@link Behavior} creates a {@link CallBehaviorAction}</li>
 * <li>dropping a {@link BehaviorParameter} creates an {@link ActivityParameterNode}</li>
 * <li>dropping an {@link AssociationEnd}, {@link Attribute}, {@link Instance}, {@link GeneralClass} or {@link Parameter} creates an {@link ObjectNode}</li>
 * </ul>
 * </p>
 */
@objid ("bbb14108-480b-491b-b268-af9e4fedf8df")
public class ActivityDiagramDropEditPolicyExtension extends AbstractDiagramElementDropEditPolicyExtension {
    /**
     * <p>
     * Overrides the default drop handling policy so that in some case it searches for the parent of the dropped element (eg: for pins).
     * </p>
     */
    @objid ("592d3814-c63a-41ff-8c6d-c8f3563f20fd")
    @Override
    public Command getUnmaskCommandFor(DiagramElementDropEditPolicy dropPolicy, MObject droppedElement, Point dropLocation) {
        if (droppedElement instanceof Operation) {
            return getOperationUnmaskCommand(dropPolicy, (Operation) droppedElement, dropLocation);
        } else if (droppedElement instanceof Behavior) {
            return getBehaviorUnmaskCommand(dropPolicy, (Behavior) droppedElement, dropLocation);
        } else if (droppedElement instanceof BehaviorParameter) {
            return getBehaviorParameterUnmaskCommand(dropPolicy, (BehaviorParameter) droppedElement, dropLocation);
        } else if (isSmartObjectNodeTarget(droppedElement)) {
            return getSmartObjectNodeUnmaskCommand(dropPolicy, droppedElement, dropLocation);
        } else if (droppedElement != null) {
            // TODO improve check for an "activity diagram" element
            if (droppedElement.getMClass().getOrigin().getName().equals(StandardMetamodel.NAME)) {
                Command cmd = (Command) droppedElement.accept(new StandardVisitorImpl(dropPolicy, dropLocation));
                return cmd != null ? cmd : super.getUnmaskCommandFor(dropPolicy, droppedElement, dropLocation);
            }
        }
        return null;
    }

    @objid ("298ed936-55b6-11e2-877f-002564c97630")
    private Command getBehaviorParameterUnmaskCommand(DiagramElementDropEditPolicy dropPolicy, BehaviorParameter droppedElement, Point dropLocation) {
        final GmModel gmModel = (GmModel) dropPolicy.getHost().getModel();
        final IGmDiagram gmDiagram = gmModel.getDiagram();
        final ActivityDiagram diag = (ActivityDiagram) gmDiagram.getRelatedElement();
        final Activity owner = (Activity) diag.getOrigin();
        return new CreateActivityParameterNodeCommand(dropLocation, droppedElement, dropPolicy.getHost(), owner);
    }

    @objid ("298ed93e-55b6-11e2-877f-002564c97630")
    private Command getOperationUnmaskCommand(DiagramElementDropEditPolicy dropPolicy, Operation droppedElement, Point dropLocation) {
        final GmModel gmModel = (GmModel) dropPolicy.getHost().getModel();
        final IGmDiagram gmDiagram = gmModel.getDiagram();
        final ActivityDiagram diag = (ActivityDiagram) gmDiagram.getRelatedElement();
        final Activity owner = (Activity) diag.getOrigin();
        return new CreateCallOperationCommand(dropLocation, droppedElement, dropPolicy.getHost(), owner);
    }

    @objid ("298ed946-55b6-11e2-877f-002564c97630")
    private Command getBehaviorUnmaskCommand(DiagramElementDropEditPolicy dropPolicy, Behavior droppedElement, Point dropLocation) {
        final GmModel gmModel = (GmModel) dropPolicy.getHost().getModel();
        final IGmDiagram gmDiagram = gmModel.getDiagram();
        final ActivityDiagram diag = (ActivityDiagram) gmDiagram.getRelatedElement();
        final Activity owner = (Activity) diag.getOrigin();
        return new CreateCallBehaviorCommand(dropLocation, droppedElement, dropPolicy.getHost(), owner);
    }

    @objid ("298ed94e-55b6-11e2-877f-002564c97630")
    private Command getSmartObjectNodeUnmaskCommand(DiagramElementDropEditPolicy dropPolicy, MObject droppedElement, Point dropLocation) {
        final GmModel gmModel = (GmModel) dropPolicy.getHost().getModel();
        final IGmDiagram gmDiagram = gmModel.getDiagram();
        final ActivityDiagram diag = (ActivityDiagram) gmDiagram.getRelatedElement();
        final Activity owner = (Activity) diag.getOrigin();
        return new SmartObjectNodeCommand(dropLocation, droppedElement, dropPolicy.getHost(), owner);
    }

    @objid ("298ed958-55b6-11e2-877f-002564c97630")
    private boolean isSmartObjectNodeTarget(final MObject element) {
        return (element instanceof AssociationEnd ||
                element instanceof Attribute ||
                element instanceof Instance ||
                element instanceof GeneralClass || element instanceof Parameter);
    }

    @objid ("ed85cd82-45bf-4899-8019-66139156994b")
    @Override
    public boolean canUnmask(DiagramElementDropEditPolicy dropPolicy, MObject candidate) {
        // Gm doesn't know how to handle this element directly, look if
        // it is an element for which we can do something "smarter".
        if (!(candidate instanceof Operation) &&
                !(candidate instanceof Behavior) &&
                !(candidate instanceof BehaviorParameter) &&
                !isSmartObjectNodeTarget(candidate)) {
            // It is not a smart interaction
            //
            // The Gm cannot unmask this element directly, and we don't know
            // what to do with it... return null
            return false;
        }
        
        // All dropped elements understood: return host!
        return true;
    }

    /**
     * Hierarchy is complete for Activity.
     */
    @objid ("228f20ce-8c9d-4a61-abac-f0c97bb7395a")
    @Override
    public boolean isToBeAddedToHierarchy(IGmDiagram context, Deque<MObject> hierarchy, MObject candidate) {
        if (candidate == null || candidate instanceof Activity) {
            return false;
        }
        
        // Make sure the element is part of the current activity
        ModelElement activity = context.getRelatedElement().getOrigin();
        MObject parent = candidate.getCompositionOwner();
        while (parent != null) {
            if (parent.equals(activity)) {
                return GmActivityDiagram.acceptUnmaskMetaclass(candidate.getMClass().getJavaInterface());
            } else {
                parent = parent.getCompositionOwner();
            }
        }
        return false;
    }

    @objid ("b1661927-3180-4f10-89cd-b68b7b41ba5c")
    private static class StandardVisitorImpl extends DefaultModelVisitor {
        @objid ("c8b03b88-258b-405b-b1fb-f6bc80e0f79d")
        private Point dropLocation;

        @objid ("e05695d0-f444-4ff7-b474-c025fc227260")
        private DiagramElementDropEditPolicy dropPolicy;

        @objid ("7ffce151-b0af-49c4-ba77-0a80d0c828fd")
        public StandardVisitorImpl(DiagramElementDropEditPolicy dropPolicy, Point dropLocation) {
            this.dropPolicy = dropPolicy;
            this.dropLocation = dropLocation;
        }

        @objid ("3ee100c4-6e5f-44c6-8638-691059e1a2ba")
        @Override
        public Object visitConstraint(final Constraint theConstraint) {
            return new UnmaskConstraintCommand(theConstraint, (AbstractDiagramEditPart) this.dropPolicy.getHost(), new Rectangle(this.dropLocation, new Dimension(-1, -1)));
        }

    }

}
