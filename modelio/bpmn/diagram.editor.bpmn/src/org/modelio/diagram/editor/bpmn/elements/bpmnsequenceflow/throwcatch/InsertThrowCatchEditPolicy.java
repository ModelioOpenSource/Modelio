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

package org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflow.throwcatch;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflow.BpmnSequenceFlowEditPart;
import org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflow.GmBpmnSequenceFlow;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.link.GmLinkLayoutEditPolicy;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * This policy creates a workflow node and insert it in a sequence flow.
 */
@objid ("cf49b75f-87b0-45b1-a6e8-bdc6c869f6ee")
public class InsertThrowCatchEditPolicy extends GmLinkLayoutEditPolicy {
    @objid ("3f2fb89c-b27b-4f4b-8868-eb9bf858c952")
    private InsertThrowCatchFeedback feedback;

    @objid ("a38cc5ae-83e2-4049-a9fc-42725097498f")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (RequestConstants.REQ_CREATE.equals(request.getType())) {
            CreateRequest createRequest = (CreateRequest) request;
            ModelioCreationContext ctx = ModelioCreationContext.lookRequest(createRequest);
        
            if (ctx != null && ctx.getElementToUnmask() instanceof BpmnMessage) {
                return getHost();
            }
        }
        return super.getTargetEditPart(request);
    }

    @objid ("55515f02-67f0-4f75-806d-114ed636f634")
    @Override
    protected Command getCreateCommand(CreateRequest createRequest) {
        ModelioCreationContext ctx = ModelioCreationContext.lookRequest(createRequest);
        if (ctx == null) {
            return null;
        }
        
        GmBpmnSequenceFlow gm = getHost().getModel();
        String metaclass = (String) createRequest.getNewObjectType();
        MClass mClass = gm.getRelatedElement().getMClass().getMetamodel().getMClass(metaclass);
        
        // Throw link - Catch Link insertion, allowed cases:
        // - BpmnIntermediateThrowEvent in 'throw link' flavor
        // - BpmnIntermediateCatchEvent in 'catch link' flavor
        //
        if ((BpmnIntermediateThrowEvent.class.isAssignableFrom(mClass.getJavaInterface()) || BpmnIntermediateCatchEvent.class.isAssignableFrom(mClass.getJavaInterface()))
                && ctx.getProperties().getOrDefault("type", "").equals("LINK")) {
        
            // Create task command
            Point location = createRequest.getLocation().getCopy();
        
            int defaultWidth = 100;
            int defaultHeight = 50;
        
            Rectangle requestRect = new Rectangle(location, new Dimension(-1, -1));
            requestRect.translate(-defaultWidth / 2, -defaultHeight / 2);
            getHostFigure().translateToRelative(requestRect);
        
            InsertThrowCatchCommand createTaskCommand = new InsertThrowCatchCommand(gm.getDiagram(), gm, ctx, requestRect);
            return createTaskCommand;
        }
        return super.getCreateCommand(createRequest);
    }

    @objid ("3a9b9e73-f38f-4ae1-b06f-95fccabc61a0")
    @Override
    protected void showLayoutTargetFeedback(Request request) {
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
    protected void eraseLayoutTargetFeedback(Request request) {
        if (this.feedback != null) {
            this.feedback.hide();
            this.feedback = null;
        }
    }

    @objid ("24f4deae-4a6b-4eec-a559-cf9e4832a68b")
    @Override
    public BpmnSequenceFlowEditPart getHost() {
        return (BpmnSequenceFlowEditPart) super.getHost();
    }

}
