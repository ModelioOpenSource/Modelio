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
package org.modelio.uml.sequencediagram.editor.elements.interactionuse;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.common.portcontainer.AutoSizeEditPolicy2;
import org.modelio.diagram.elements.common.portcontainer.LayoutMainNodeConnectionsEditPolicy;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPart;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPolicy;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.policies.LayoutConnectionsConstrainedLayoutEditPolicyDecorator;
import org.modelio.diagram.elements.core.policies.LayoutNodeConnectionsEditPolicy;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.GmSequenceDiagram;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.IPlacementConstraintProvider;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.PlacementConstraint;

/**
 * EditPart for the InteractionUse. Specialisation of the PortContainerEditPart to add the IPlacementConstraintProvider role.
 * 
 * @author fpoyer
 */
@objid ("d92086fc-55b6-11e2-877f-002564c97630")
public class InteractionUseEditPart extends PortContainerEditPart implements IPlacementConstraintProvider {
    /**
     * Creates and returns a PlacementConstraint for the given model.
     * @param model the graphic model for which a constraint is to be created.
     * @param x the desired X coordinate in coordinates relative to the parent figure.
     * @param y the desired Y coordinate in coordinates relative to the parent figure.
     * @param width the desired width of the figure.
     * @param height the desired height of the figure.
     * @return a new PlacementConstraint.
     */
    @objid ("d9208700-55b6-11e2-877f-002564c97630")
    @Override
    public PlacementConstraint createPlacementConstraint(final GmModel model, final int x, final int y, final int width, final int height) {
        return new InteractionUsePlacementConstraint(x,
                        y,
                        width,
                        height,
                        (GmSequenceDiagram) model.getDiagram());
        
    }

    @objid ("d9208712-55b6-11e2-877f-002564c97630")
    @Override
    public Command getCommand(final Request request) {
        Command command = super.getCommand(request);
        if (RequestConstants.REQ_MOVE.equals(request.getType()) &&
                (((ChangeBoundsRequest) request).getMoveDelta().y != 0)) {
            Command updateModelCommand = new Command() {
                @Override
                public void execute() {
                    GmInteractionUse model = (GmInteractionUse) InteractionUseEditPart.this.getModel();
                    InteractionUse interactionUse = model.getRelatedElement();
                    int startTime = interactionUse.getLineNumber() +
                            ((ChangeBoundsRequest) request).getMoveDelta().y;
                    int finishTime = interactionUse.getEndLineNumber() +
                            ((ChangeBoundsRequest) request).getMoveDelta().y;
        
                    interactionUse.setLineNumber(startTime);
                    interactionUse.setEndLineNumber(finishTime);
                    // FIXME: also move the gates.
        
                }
            };
            command = updateModelCommand.chain(command);
        }
        return command;
    }

    @objid ("d9220d7a-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        // Specifying the layout policy to handle creation of Gates.
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new InteractionUseEditPolicy());
        
        removeEditPolicy(LayoutMainNodeConnectionsEditPolicy.ROLE);
        
    }

    @objid ("448b3ccd-a010-4508-ad10-c3801e30099e")
    @Override
    public SelectionEditPolicy getPreferredDragRolePolicy(String requestType) {
        return new AutoSizeEditPolicy2() {
            @Override
            public void activate() {
                super.activate();
        
                EditPart host = getHost();
                host.removeEditPolicy(LayoutNodeConnectionsEditPolicy.ROLE);
            }
        };
        
    }

    @objid ("1cd3ebf2-3c49-43de-a6fd-2b3cece9df29")
    @Override
    protected EditPolicy createLayoutPolicyDecorator(EditPolicy layoutPolicy) {
        return new LayoutConnectionsConstrainedLayoutEditPolicyDecorator((PortContainerEditPolicy) layoutPolicy);
    }

}
