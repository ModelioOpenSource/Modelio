/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.bpmn.elements.bpmnlinkedobject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.gateways.BpmnComplexGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnExclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnInclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnParallelGateway;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Edit policy that allow to create a node linked to this node.
 */
@objid ("614e6507-55b6-11e2-877f-002564c97630")
public class BpmnLinkedObjectEditPolicy extends LinkedNodeStartCreationEditPolicy {
    @objid ("614feb5b-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        if (getHost().getModel() instanceof GmNodeModel) {
            if (request.getNewObjectType().equals("BpmnDataInput") ||
                    request.getNewObjectType().equals("BpmnDataOutput")) {
        
                final ModelioCreationContext context = (ModelioCreationContext) request.getNewObject();
                final AbstractGraphicalEditPart nodeEditPart = (AbstractGraphicalEditPart) getHost();
        
                GmNodeModel sourceGM = (GmNodeModel) getHost().getModel();
                MObject sourceElement = sourceGM.getRelatedElement();
        
                if (sourceElement instanceof BpmnThrowEvent &&
                        request.getNewObjectType().equals("BpmnDataOutput")) {
                    return null;
                } else if (sourceElement instanceof BpmnCatchEvent &&
                        request.getNewObjectType().equals("BpmnDataInput")) {
                    return null;
                } else if (sourceElement instanceof BpmnExclusiveGateway ||
                        sourceElement instanceof BpmnInclusiveGateway ||
                        sourceElement instanceof BpmnComplexGateway ||
                        sourceElement instanceof BpmnEventBasedGateway ||
                        sourceElement instanceof BpmnParallelGateway ||
                        sourceElement instanceof BpmnLane) {
                    return null;
                } else {
                    final BpmnLinkedObjectCommand cmd = new BpmnLinkedObjectCommand(context);
                    cmd.setSourceNode(sourceGM);
                    cmd.setSourceAnchorLocation(request.getLocation()
                            .getDifference(nodeEditPart.getFigure()
                                    .getBounds()
                                    .getLocation()));
                    request.setStartCommand(cmd);
                    return cmd;
                }
            }
        
            return super.getConnectionCreateCommand(request);
        }
        return null;
    }

    @objid ("614feb61-55b6-11e2-877f-002564c97630")
    @Override
    public EditPart getTargetEditPart(final Request request) {
        if (request instanceof CreateConnectionRequest) {
            final ModelioLinkCreationContext context = (ModelioLinkCreationContext) ((CreateConnectionRequest) request).getNewObject();
            if (context.getJavaClass() == BpmnMessage.class) {
                return null;
            }
        }
        return super.getTargetEditPart(request);
    }

}
