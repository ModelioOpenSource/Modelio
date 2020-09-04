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

package org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflow;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.CreateBpmnDataObjectCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.link.GmLinkLayoutEditPolicy;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;

@objid ("61a25153-55b6-11e2-877f-002564c97630")
public class SequenceFlowLinkLayoutEditPolicy extends GmLinkLayoutEditPolicy {
    @objid ("1a7a101f-053e-4fd2-9853-8d89f7d20ca0")
    private DataObjectCreationFeedback feedback;

    @objid ("61a3d7b9-55b6-11e2-877f-002564c97630")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (RequestConstants.REQ_CREATE.equals(request.getType())) {
            CreateRequest createRequest = (CreateRequest) request;
            if (createRequest.getNewObject() instanceof ModelioCreationContext) {
                final ModelioCreationContext ctx = (ModelioCreationContext) createRequest.getNewObject();
                if (ctx.getElementToUnmask() instanceof BpmnMessage) {
                    return getHost();
                }
            }
        
        }
        return super.getTargetEditPart(request);
    }

    @objid ("61a3d7bf-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        if (request.getNewObjectType().equals("BpmnDataObject")) {
            GmBpmnSequenceFlow gm = (GmBpmnSequenceFlow) getHost().getModel();
            ModelioCreationContext ctx = (ModelioCreationContext) request.getNewObject();
            return new CreateBpmnDataObjectCommand((BpmnSequenceFlow) gm.getRelatedElement(), gm, ctx);
        }
        return super.getCreateCommand(request);
    }

    @objid ("81705516-687e-49ae-bb5e-339082023823")
    @Override
    protected void showLayoutTargetFeedback(Request request) {
        // Show nothing if we cannot issue an executable command.
        Command command = getCommand(request);
        if (command == null || !command.canExecute()) {
            return;
        }
        
        // If command is create show 'InsertInFlow' feedback
        if (RequestConstants.REQ_CREATE.equals(request.getType()) && command instanceof CreateBpmnDataObjectCommand) {
            CreateRequest createRequest = (CreateRequest) request;
        
            if (this.feedback == null) {
                this.feedback = new DataObjectCreationFeedback(getFeedbackLayer(), (ZoomManager) getHost().getViewer().getProperty(ZoomManager.class.toString()));
            }
            this.feedback.show(getHostFigure(), createRequest.getLocation());
        }
    }

    @objid ("19e05681-f361-4b18-8e13-1840abeedc68")
    @Override
    protected void eraseLayoutTargetFeedback(Request request) {
        if (this.feedback != null) {
            this.feedback.hide();
            this.feedback = null;
        }
    }

}
