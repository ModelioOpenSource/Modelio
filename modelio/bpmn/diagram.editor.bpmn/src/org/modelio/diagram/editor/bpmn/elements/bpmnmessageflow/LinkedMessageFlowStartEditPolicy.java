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

package org.modelio.diagram.editor.bpmn.elements.bpmnmessageflow;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessage.CreateLinkedBpmnMessageCommand;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.helpers.AnchorModelHelper;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;

/**
 * Edit policy that allow to create a node linked to this node.
 */
@objid ("616ff6a7-55b6-11e2-877f-002564c97630")
public class LinkedMessageFlowStartEditPolicy extends LinkedNodeStartCreationEditPolicy {
    @objid ("616ff6ab-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getConnectionCreateCommand(final CreateConnectionRequest request) {
        final ModelioCreationContext context = ModelioCreationContext.fromRequest(request);
        if (context.getJavaClass() == BpmnMessage.class) {
            final EditPart nodeEditPart = getHost();
            final CreateLinkedBpmnMessageCommand cmd = new CreateLinkedBpmnMessageCommand(context);
            cmd.setSource(nodeEditPart);
            cmd.setSourceAnchor(AnchorModelHelper.getSourceAnchorModel(nodeEditPart, request));
            request.setStartCommand(cmd);
            return cmd;
        } else {
            return super.getConnectionCreateCommand(request);
        }
    }

    @objid ("616ff6b1-55b6-11e2-877f-002564c97630")
    @Override
    public EditPart getTargetEditPart(final Request request) {
        if (getHost().getModel() instanceof GmBpmnMessageFlow) {
            BpmnMessageFlow element = ((GmBpmnMessageFlow) getHost().getModel()).getRepresentedElement();
            if (element.getMessageRef() != null) {
                return null;
            }
        
            if (REQ_LINKEDNODE_START.equals(request.getType()) ||
                    REQ_RECONNECT_SOURCE.equals(request.getType())) {
                return getHost();
            }
        }
        return null;
    }

}
