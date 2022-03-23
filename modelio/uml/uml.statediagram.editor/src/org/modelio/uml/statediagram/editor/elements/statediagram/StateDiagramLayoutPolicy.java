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
package org.modelio.uml.statediagram.editor.elements.statediagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramEditLayoutPolicy;
import org.modelio.diagram.elements.core.commands.DefaultCreateElementCommand;
import org.modelio.diagram.elements.core.commands.DefaultReparentElementCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * LayoutPolicy that is specific to State Diagram background. Handles EntryPointPseudoState and ExitPointPseudoState
 * creation, since they have to be created in the StateMachine, while the element represented by the diagram is actually
 * the Top Region of said StateMachine.
 */
@objid ("f5933df0-55b6-11e2-877f-002564c97630")
class StateDiagramLayoutPolicy extends DiagramEditLayoutPolicy {
    @objid ("f5933df4-55b6-11e2-877f-002564c97630")
    @Override
    protected MObject getHostElement() {
        final StateMachine stateMachine = (StateMachine) super.getHostElement();
        return stateMachine.getTop();
    }

    @objid ("f5933dfb-55b6-11e2-877f-002564c97630")
    @Override
    protected boolean canHandle(MClass metaclass, String dep) {
        // Accept Entry and Exit point (they will be attached directly to the state machine, instead of its top region
        if (EntryPointPseudoState.class.isAssignableFrom(metaclass.getJavaInterface()) ||
                ExitPointPseudoState.class.isAssignableFrom(metaclass.getJavaInterface()))
            return true;
        return super.canHandle(metaclass, dep);
    }

    @objid ("f5933e04-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getCreateCommand(final CreateRequest request) {
        MObject hostElement = getHostElement();
        if (hostElement != null) {
            ModelioCreationContext ctx = (ModelioCreationContext) request.getNewObject();
            if (ctx.getElementToUnmask() == null && EntryPointPseudoState.class == ctx.getJavaClass() || ExitPointPseudoState.class == ctx.getJavaClass()) {
                Object requestConstraint = getConstraintFor(request);
                return new DefaultCreateElementCommand(((Region) hostElement).getRepresented(),
                        getHostCompositeNode(),
                        ctx,
                        requestConstraint);
            }
        }
        return super.getCreateCommand(request);
    }

    @objid ("f5933e0b-55b6-11e2-877f-002564c97630")
    @Override
    protected Command createAddCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
        // Entry and Exit point must be attached directly to the state machine, instead of its top region
        if ((GmNodeModel) child.getModel() != null && (((GmNodeModel) child.getModel()).getRepresentedElement() instanceof EntryPointPseudoState) || (((GmNodeModel) child.getModel()).getRepresentedElement() instanceof ExitPointPseudoState)) {
            final StateMachine stateMachine = (StateMachine) super.getHostElement();
            return new DefaultReparentElementCommand(stateMachine,
                    getHostCompositeNode(),
                    (GmNodeModel) child.getModel(),
                    constraint);
        }
        return super.createAddCommand(request, child, constraint);
    }

}
