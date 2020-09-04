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

package org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflow.insert;

import java.util.Collections;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.ICreationCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.GmLinkLayoutEditPolicy;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.gateways.BpmnGateway;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This policy creates a workflow node and insert it in a sequence flow.
 */
@objid ("3a935e2d-33ae-4da2-85b1-d2e0073b42af")
public class InsertInFlowEditPolicy extends GmLinkLayoutEditPolicy {
    @objid ("8241e11f-916d-41cc-a2ae-2d095044997e")
    private InsertInFlowFeedback feedback;

    @objid ("d58ab386-1fea-49f2-bd6b-41037158fe45")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (RequestConstants.REQ_CREATE.equals(request.getType())) {
            CreateRequest createRequest = (CreateRequest) request;
            final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(createRequest);
            if (ctx != null) {
                if (ctx.getElementToUnmask() instanceof BpmnMessage) {
                    return getHost();
                }
            }
        
        }
        return super.getTargetEditPart(request);
    }

    @objid ("4bff5022-7c8f-4fdc-af40-8b98d34475d0")
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        ModelioCreationContext ctx = ModelioCreationContext.lookRequest(request);
        if (ctx == null) {
            return null;
        }
        
        // Node insertion, allowed cases:
        // - Any BpmnActivity
        // - Any BpmnGateway
        // - Any throw event excepted 'throw link'
        // - Any catch event excepted 'catch link'
        
        if (canInsertInFlow(ctx.getJavaClass(), ctx.getProperties())) {
            LinkEditPart flowEp = getHost();
            EditPart backEp = flowEp.getViewer().findObjectAtExcluding(
                    request.getLocation(),
                    Collections.singleton(getHostFigure()),
                    ep -> {
                        EditPart targetEp = ep.getTargetEditPart(request);
                        return targetEp != null && targetEp != flowEp;
                    });
        
            if (!isSameContext(backEp, getHost())) {
                return null;
            }
        
            Command nodeCmd = backEp.getTargetEditPart(request).getCommand(request);
            if (nodeCmd instanceof ICreationCommand) {
                return nodeCmd.chain(new SplitConnectionCommand((ICreationCommand<GmNodeModel>) nodeCmd, flowEp));
            }
        
        }
        return super.getCreateCommand(request);
    }

    @objid ("d261f9eb-48e4-41d8-9d9b-487c6fa0c940")
    @Override
    protected void showLayoutTargetFeedback(Request request) {
        // Show nothing if we cannot issue an executable command.
        Command command = getCommand(request);
        if (command == null || !command.canExecute()) {
            return;
        }
        
        // If command is create show 'InsertInFlow' feedback
        if (RequestConstants.REQ_CREATE.equals(request.getType())) {
        
            CreateRequest createRequest = (CreateRequest) request;
            GmLink gm = getHost().getModel();
            String metaclass = (String) createRequest.getNewObjectType();
            MClass mClass = gm.getRelatedElement().getMClass().getMetamodel().getMClass(metaclass);
        
            int type = -1;
            if (BpmnActivity.class.isAssignableFrom(mClass.getJavaInterface())) {
                type = 0;
            } else if (BpmnGateway.class.isAssignableFrom(mClass.getJavaInterface())) {
                type = 1;
            } else if (BpmnEvent.class.isAssignableFrom(mClass.getJavaInterface())) {
                type = 2;
            }
        
            if (this.feedback == null) {
                this.feedback = new InsertInFlowFeedback(getFeedbackLayer(), (ZoomManager) getHost().getViewer().getProperty(ZoomManager.class.toString()));
            }
            this.feedback.show(getHostFigure(), createRequest.getLocation(), type);
        }
    }

    @objid ("706e675d-c598-4790-8b4e-993e336d04e1")
    @Override
    protected void eraseLayoutTargetFeedback(Request request) {
        if (this.feedback != null) {
            this.feedback.hide();
            this.feedback = null;
        }
    }

    /**
     * Determine whether or not an insertion of the kind defined by (metaclass, properties) is allowed.
     * <ul/>
     * <li>Any BpmnActivity</li>
     * <li>Any BpmnGateway</li>
     * <li>Any throw event excepted 'throw link'</li>
     * <li>Any catch event excepted 'catch link'</li>
     * </ul>
     * @param metaclass
     * 
     * @param properties @return
     */
    @objid ("e0b0345b-b7fe-43ef-8361-e98252371940")
    private boolean canInsertInFlow(Class<? extends MObject> metaclass, Map<String, Object> properties) {
        return BpmnActivity.class.isAssignableFrom(metaclass)
                || BpmnGateway.class.isAssignableFrom(metaclass)
                || (BpmnIntermediateThrowEvent.class.isAssignableFrom(metaclass) && !properties.getOrDefault("type", "").equals("LINK"))
                || (BpmnIntermediateCatchEvent.class.isAssignableFrom(metaclass) && !properties.getOrDefault("type", "").equals("LINK"));
    }

    @objid ("135ab48b-e430-4360-859a-9d6199995a18")
    private boolean isSameContext(EditPart backEp, EditPart host) {
        Object process1 = getDiagram(backEp);
        Object process2 = getDiagram(host);
        return process1 != null && process2 != null && process1.equals(process2);
    }

    @objid ("f848edc8-fe25-46a1-9e03-62caff147cc6")
    private IGmDiagram getDiagram(EditPart host) {
        GmModel gmModel = (GmModel) host.getModel();
        return gmModel.getDiagram();
    }

    @objid ("c976a9d1-f819-429c-8b90-802f689195d0")
    @Override
    public LinkEditPart getHost() {
        return (LinkEditPart) super.getHost();
    }

}
