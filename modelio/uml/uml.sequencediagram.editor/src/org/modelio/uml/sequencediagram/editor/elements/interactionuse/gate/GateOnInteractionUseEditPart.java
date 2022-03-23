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
package org.modelio.uml.sequencediagram.editor.elements.interactionuse.gate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
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
import org.modelio.diagram.elements.core.policies.LayoutConnectionsConstrainedLayoutEditPolicyDecorator;
import org.modelio.diagram.elements.core.policies.LayoutNodeConnectionsEditPolicy;
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

    @objid ("705b9199-4325-49b4-a8d8-860695273555")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        removeEditPolicy(LayoutMainNodeConnectionsEditPolicy.ROLE);
        
    }

    @objid ("a29e29ce-177e-4d66-ba19-f6c93b5e0a10")
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

    @objid ("e802b362-026b-448e-b6f7-a010ac6b878b")
    @Override
    protected EditPolicy createLayoutPolicyDecorator(EditPolicy layoutPolicy) {
        return new LayoutConnectionsConstrainedLayoutEditPolicyDecorator((PortContainerEditPolicy) layoutPolicy);
    }

}
