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

package org.modelio.diagram.editor.sequence.elements.interactionuse.gate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPart;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;

/**
 * EditPart for Gate on an InteractionUse. Specialisation of a PortContainerEditPart.
 * 
 * @author fpoyer
 */
@objid ("d912cb6d-55b6-11e2-877f-002564c97630")
public class GateOnInteractionUseEditPart extends PortContainerEditPart {
    @objid ("d912cb71-55b6-11e2-877f-002564c97630")
    @Override
    public Command getCommand(final Request request) {
        Command command = super.getCommand(request);
        if (RequestConstants.REQ_MOVE.equals(request.getType()) &&
                (((ChangeBoundsRequest) request).getMoveDelta().y != 0)) {
            Command updateModelCommand = new Command() {
                @Override
                public void execute() {
                    Gate gate = ((GmGateOnInteractionUse) GateOnInteractionUseEditPart.this.getModel()).getRelatedElement();
                    int newTime = ((IFigure) GateOnInteractionUseEditPart.this.getFigure()
                            .getChildren()
                            .get(0)).getBounds()
                                    .getCenter().y
                            +
                            ((ChangeBoundsRequest) request).getMoveDelta().y;
                    if (newTime != gate.getLineNumber()) {
                        gate.setLineNumber(newTime);
                    }
                }
            };
            command = updateModelCommand.chain(command);
        }
        return command;
    }

}
