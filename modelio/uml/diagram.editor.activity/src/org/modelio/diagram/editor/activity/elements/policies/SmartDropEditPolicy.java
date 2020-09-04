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

package org.modelio.diagram.editor.activity.elements.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.modelio.diagram.editor.activity.elements.commands.CreateActivityParameterNodeCommand;
import org.modelio.diagram.editor.activity.elements.commands.CreateCallBehaviorCommand;
import org.modelio.diagram.editor.activity.elements.commands.CreateCallOperationCommand;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Drag & drop edit policy for smart interactions.
 * <p>
 * Allow drag and drop of Operation, Behaviors, BehaviorParameter and more...
 * 
 * @author cmarin
 */
@objid ("2b3efa2d-55b6-11e2-877f-002564c97630")
public class SmartDropEditPolicy extends DefaultElementDropEditPolicy {
    @objid ("2b3efa31-55b6-11e2-877f-002564c97630")
    @Override
    protected EditPart getDropTargetEditPart(ModelElementDropRequest request) {
        if (!request.isSmart()) {
            return super.getDropTargetEditPart(request);
        }
        
        for (MObject droppedElement : request.getDroppedElements()) {
            if (!(droppedElement instanceof Operation) &&
                    !(droppedElement instanceof Behavior) &&
                    !(droppedElement instanceof BehaviorParameter)) {
                // Smart interaction not handled, default behavior.
                return super.getDropTargetEditPart(request);
            }
        }
        
        // All dropped elements have a smart interaction
        return this.getHost();
    }

    @objid ("2b3efa39-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getSmartDropCommand(ModelElementDropRequest request) {
        CompoundCommand command = new CompoundCommand();
        
        Point dropLocation = request.getDropLocation();
        
        if (request.isSmart()) {
            for (MObject toUnmask : request.getDroppedElements()) {
                if (toUnmask instanceof Operation) {
                    command.add(getOperationDropCommand(dropLocation, (Operation) toUnmask));
                } else if (toUnmask instanceof Behavior) {
                    command.add(getBehaviorDropCommand(dropLocation, (Behavior) toUnmask));
                } else if (toUnmask instanceof BehaviorParameter) {
                    command.add(getBehaviorParameterDropCommand(dropLocation, (BehaviorParameter) toUnmask));
                }
            }
        }
        
        if (command.isEmpty())
            return null;
        return command;
    }

    @objid ("2b3efa41-55b6-11e2-877f-002564c97630")
    private Command getBehaviorParameterDropCommand(Point dropLocation, BehaviorParameter toUnmask) {
        final GmModel gmModel = (GmModel) getHost().getModel();
        final MObject owner = gmModel.getRelatedElement();
        return new CreateActivityParameterNodeCommand(dropLocation, toUnmask, getHost(), owner);
    }

    @objid ("2b3efa49-55b6-11e2-877f-002564c97630")
    private Command getOperationDropCommand(Point dropLocation, Operation toUnmask) {
        final GmModel gmModel = (GmModel) getHost().getModel();
        final MObject owner = gmModel.getRelatedElement();
        return new CreateCallOperationCommand(dropLocation, toUnmask, getHost(), owner);
    }

    @objid ("2b4080b9-55b6-11e2-877f-002564c97630")
    private Command getBehaviorDropCommand(Point dropLocation, Behavior toUnmask) {
        final GmModel gmModel = (GmModel) getHost().getModel();
        final MObject owner = gmModel.getRelatedElement();
        return new CreateCallBehaviorCommand(dropLocation, toUnmask, getHost(), owner);
    }

}
