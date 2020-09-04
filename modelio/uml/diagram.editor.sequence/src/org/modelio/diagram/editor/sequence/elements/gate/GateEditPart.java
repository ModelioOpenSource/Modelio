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

package org.modelio.diagram.editor.sequence.elements.gate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.GmSequenceDiagram;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.IPlacementConstraintProvider;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.PlacementConstraint;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPart;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;

/**
 * EditPart for Gate on an InteractionUse. Specialisation of a PortContainerEditPart.
 * 
 * @author fpoyer
 */
@objid ("d8f446fe-55b6-11e2-877f-002564c97630")
public class GateEditPart extends PortContainerEditPart implements IPlacementConstraintProvider {
    /**
     * Creates and returns a PlacementConstraint for the given model.
     * 
     * @param model the graphic model for which a constraint is to be created.
     * @param x the desired X coordinate in coordinates relative to the parent figure.
     * @param y the desired Y in coordinates relative to the parent figure.
     * @param width the desired width of the figure.
     * @param height the desired height of the figure.
     * @return a new PlacementConstraint.
     */
    @objid ("d8f44702-55b6-11e2-877f-002564c97630")
    @Override
    public PlacementConstraint createPlacementConstraint(final GmModel model, final int x, final int y, final int width, final int height) {
        return new GatePlacementConstraint(x, y, width, height, (GmSequenceDiagram) model.getDiagram());
    }

    @objid ("d8f5cd67-55b6-11e2-877f-002564c97630")
    @Override
    public Command getCommand(final Request request) {
        Command command = super.getCommand(request);
        if (RequestConstants.REQ_MOVE.equals(request.getType()) &&
                (((ChangeBoundsRequest) request).getMoveDelta().y != 0)) {
            Command updateModelCommand = new Command() {
                @Override
                public void execute() {
                    Gate gate = ((GmGate) GateEditPart.this.getModel()).getRelatedElement();
                    int newTime = ((IFigure) GateEditPart.this.getFigure().getChildren().get(0)).getBounds()
                            .getCenter().y +
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
