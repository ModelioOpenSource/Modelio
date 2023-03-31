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
package org.modelio.bpmn.diagram.editor.elements.bpmnsequenceflow.throwcatch;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.bpmn.diagram.editor.elements.bpmnsequenceflow.BpmnSequenceFlowEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnsequenceflow.GmBpmnSequenceFlow;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;

/**
 * This policy creates a workflow node and insert it in a sequence flow.
 */
@objid ("cf49b75f-87b0-45b1-a6e8-bdc6c869f6ee")
public class InsertThrowCatchEditPolicy extends GraphicalEditPolicy {
    @objid ("3f2fb89c-b27b-4f4b-8868-eb9bf858c952")
    private InsertThrowCatchFeedback feedback;

    @objid ("a38cc5ae-83e2-4049-a9fc-42725097498f")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (RequestConstants.REQ_CREATE.equals(request.getType())) {
            CreateRequest createRequest = (CreateRequest) request;
            ModelioCreationContext ctx = ModelioCreationContext.lookRequest(createRequest);
        
            if (ctx != null && isHandled(ctx)) {
                return getHost();
            }
        }
        return null;
    }

    @objid ("ffebd843-acec-488b-917c-67158b4938b4")
    @Override
    public Command getCommand(Request request) {
        if (getTargetEditPart(request) == null)
            return null;
        return getCreateCommand((CreateRequest) request);
    }

    @objid ("55515f02-67f0-4f75-806d-114ed636f634")
    protected Command getCreateCommand(CreateRequest createRequest) {
        ModelioCreationContext ctx = ModelioCreationContext.lookRequest(createRequest);
        if (ctx == null) {
            return null;
        }
        
        // Throw link - Catch Link insertion, allowed cases:
        // - BpmnIntermediateThrowEvent in 'throw link' flavor
        // - BpmnIntermediateCatchEvent in 'catch link' flavor
        //
        if (isHandled(ctx)) {
        
            // Create task command
            GmBpmnSequenceFlow gm = getSequenceFlowHost().getModel();
            Rectangle requestRect = new Rectangle(new Point(0,0), new Dimension(-1, -1));
        
            InsertThrowCatchCommand createTaskCommand = new InsertThrowCatchCommand(gm.getDiagram(), gm, ctx, requestRect);
            return createTaskCommand;
        }
        return null;
    }

    @objid ("6d5d3733-dc4c-4313-8a0e-99cfd7e44ca4")
    private boolean isHandled(ModelioCreationContext ctx) {
        return (BpmnIntermediateThrowEvent.class.isAssignableFrom(ctx.getJavaClass())
                || BpmnIntermediateCatchEvent.class.isAssignableFrom(ctx.getJavaClass()))
                && "LINK".equals(ctx.getProperties().get("type"));
        
    }

    @objid ("3a9b9e73-f38f-4ae1-b06f-95fccabc61a0")
    @Override
    public void showTargetFeedback(Request request) {
        // Show nothing if we cannot issue an executable command.
        Command command = getCommand(request);
        if (command == null || !command.canExecute()) {
            return;
        }
        
        // If command is create show 'InsertThrowCatch' feedback
        if (RequestConstants.REQ_CREATE.equals(request.getType())) {
            CreateRequest createRequest = (CreateRequest) request;
            if (this.feedback == null) {
                this.feedback = new InsertThrowCatchFeedback(getFeedbackLayer(), (ZoomManager) getHost().getViewer().getProperty(ZoomManager.class.toString()));
            }
            this.feedback.show(getHostFigure(), createRequest.getLocation());
        }
        
    }

    @objid ("497c5802-e384-413c-80e4-a72f6e13194c")
    @Override
    public void eraseTargetFeedback(Request request) {
        if (this.feedback != null) {
            this.feedback.hide();
            this.feedback = null;
        }
        
    }

    /**
     * @see #getHost()
     * @return the <i>host</i> BpmnSequenceFlowEditPart on which this policy is installed.
     */
    @objid ("24f4deae-4a6b-4eec-a559-cf9e4832a68b")
    public BpmnSequenceFlowEditPart getSequenceFlowHost() {
        return (BpmnSequenceFlowEditPart) super.getHost();
    }

}
