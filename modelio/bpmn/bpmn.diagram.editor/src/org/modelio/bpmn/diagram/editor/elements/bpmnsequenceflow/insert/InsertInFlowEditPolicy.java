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
package org.modelio.bpmn.diagram.editor.elements.bpmnsequenceflow.insert;

import java.util.Collections;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractPointListShape;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.ICreationCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.link.GmLink;
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
public class InsertInFlowEditPolicy extends GraphicalEditPolicy {
    @objid ("8241e11f-916d-41cc-a2ae-2d095044997e")
    private InsertInFlowFeedback feedback;

    @objid ("a0ee82ff-4771-405a-b1b1-2c435eb0e6e2")
    private static final Object WATCHDOG = new Object();

    @objid ("d58ab386-1fea-49f2-bd6b-41037158fe45")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (RequestConstants.REQ_CREATE.equals(request.getType())) {
            CreateRequest createRequest = (CreateRequest) request;
            final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(createRequest);
            if (ctx == null)
                return null;
        
            if (canInsertInFlow(ctx.getJavaClass(), ctx.getProperties()) || ctx.getElementToUnmask() instanceof BpmnMessage) {
                return getHost();
            }
        }
        return null;
    }

    @objid ("f0abf208-10db-423c-b4d5-22dcd01c3e53")
    @Override
    public Command getCommand(Request request) {
        if (getTargetEditPart(request) == null)
            return null;
        return getCreateCommand((CreateRequest) request);
    }

    @objid ("4bff5022-7c8f-4fdc-af40-8b98d34475d0")
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
        
        if (! canInsertInFlow(ctx.getJavaClass(), ctx.getProperties())) {
            return null;
        }
        
        LinkEditPart flowEp = getHostLinkEditPart();
        Point location = snapInsertLocation(request);
        EditPart backEp = flowEp.getViewer().findObjectAtExcluding(
                location,
                Collections.singleton(getHostFigure()),
                ep -> {
                    EditPart targetEp = ep.getTargetEditPart(request);
                    return targetEp != null && targetEp != flowEp;
                });
        
        if (!isSameContext(backEp, getHost())) {
            return null;
        }
        
        EditPart targetEditPart = backEp.getTargetEditPart(request);
        if (targetEditPart == flowEp || targetEditPart == null) {
            return null;
        }
        
        int watchdog = (int) request.getExtendedData().merge(WATCHDOG, 1, (a,b) -> (int)a+1);
        
        try {
            if( watchdog > 5) {
                //DiagramEditorBpmn.LOG.warning(new IllegalStateException("infinite recursion"));
                return null;
            }
        
            Command nodeCmd = targetEditPart.getCommand(request);
            if (! (nodeCmd instanceof ICreationCommand))
                return null;
        
            @SuppressWarnings ("unchecked")
            ICreationCommand<GmNodeModel> gmNodeCreateCmd = (ICreationCommand<GmNodeModel>) nodeCmd;
            return nodeCmd
                    .chain(new CenterNodeCommand(gmNodeCreateCmd, flowEp))
                    .chain(new SplitConnectionCommand(gmNodeCreateCmd, flowEp));
        } finally {
            request.getExtendedData().remove(WATCHDOG);
        }
        
    }

    /**
     * Update the request's location to snap with the link's source or target if possible and avoid unwanted bendpoints.
     * @param request the link creation request.
     * @return the actual insert location to use.
     */
    @objid ("73731c39-eec9-47d2-a724-a15a93ec1931")
    private Point snapInsertLocation(CreateRequest request) {
        Point location = request.getLocation();
        
        AbstractPointListShape flowFigure = (AbstractPointListShape) getHostFigure();
        
        Point flowInStartPoint = flowFigure.getStart().getCopy();
        flowFigure.translateToAbsolute(flowInStartPoint);
        
        Point flowOutEndPoint = flowFigure.getEnd().getCopy();
        flowFigure.translateToAbsolute(flowOutEndPoint);
        
        if (Math.abs(location.x - flowInStartPoint.x) < 5) {
            // Align location with link's source
            location.x = flowInStartPoint.x;
        } else if (Math.abs(location.y - flowInStartPoint.y) < 5) {
            // Align location with link's source
            location.y = flowInStartPoint.y;
        } else if (Math.abs(location.x - flowOutEndPoint.x) < 5) {
            // Align location with link's target
            location.x = flowOutEndPoint.x;
        } else if (Math.abs(location.y - flowOutEndPoint.y) < 5) {
            // Align location with link's target
            location.y = flowOutEndPoint.y;
        }
        return location;
    }

    @objid ("d261f9eb-48e4-41d8-9d9b-487c6fa0c940")
    @Override
    public void showTargetFeedback(Request request) {
        // Show nothing if we cannot issue an executable command.
        Command command = getCommand(request);
        if (command == null || !command.canExecute()) {
            return;
        }
        
        // If command is create show 'InsertInFlow' feedback
        if (RequestConstants.REQ_CREATE.equals(request.getType())) {
        
            CreateRequest createRequest = (CreateRequest) request;
        
            GmLink gm = getHostLinkEditPart().getModel();
            String metaclass = (String) createRequest.getNewObjectType();
            MClass mClass = gm.getRelatedElement().getMClass().getMetamodel().getMClass(metaclass);
        
            if (this.feedback == null) {
                this.feedback = new InsertInFlowFeedback(getFeedbackLayer(), (ZoomManager) getHost().getViewer().getProperty(ZoomManager.class.toString()));
            }
            this.feedback.show(getHostFigure(), snapInsertLocation(createRequest), BpmnNodeType.fromMClass(mClass));
        }
        
    }

    @objid ("706e675d-c598-4790-8b4e-993e336d04e1")
    @Override
    public void eraseTargetFeedback(Request request) {
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
     * @return true if the insertion is allowed
     */
    @objid ("e0b0345b-b7fe-43ef-8361-e98252371940")
    private boolean canInsertInFlow(Class<? extends MObject> metaclass, Map<String, Object> properties) {
        return BpmnActivity.class.isAssignableFrom(metaclass)
                || BpmnGateway.class.isAssignableFrom(metaclass)
                || BpmnIntermediateThrowEvent.class.isAssignableFrom(metaclass) && !"LINK".equals(properties.get("type"))
                || BpmnIntermediateCatchEvent.class.isAssignableFrom(metaclass) && !"LINK".equals(properties.get("type"));
        
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
    public LinkEditPart getHostLinkEditPart() {
        return (LinkEditPart) super.getHost();
    }

    /**
     * This enum represents BPMN node types with their default size.
     */
    @objid ("6596393b-d78e-4b61-bd72-d3695e4ddc1a")
    enum BpmnNodeType {
        @objid ("e73007a0-07c2-48ff-bd7d-f69b7bf1a7e4")
        ACTIVITY(90, 46),
        @objid ("430daf5c-88b9-4350-9152-3985161b7b26")
        GATEWAY(40, 40),
        @objid ("601a0a69-4b4d-49e5-88f2-161b8fd90999")
        EVENT(33, 33),
        @objid ("fc468e19-bfa1-4792-a6ea-1633006dc29b")
        OTHER(90, 46);

        @objid ("68ca405c-974a-4b2c-8679-5855f770be83")
        final int width;

        @objid ("c01d9c68-523b-4b3d-80fb-d5a4c94d4d12")
        final int height;

        @objid ("64645d12-a9e1-4f1d-b46e-9f9dc902a337")
        private  BpmnNodeType(int width, int height) {
            this.width = width;
            this.height = height;
            
        }

        @objid ("e0e17ba3-4a35-40bb-93cc-71109e0e8ffd")
        public static BpmnNodeType fromMClass(MClass mClass) {
            if (BpmnActivity.class.isAssignableFrom(mClass.getJavaInterface())) {
                return ACTIVITY;
            } else if (BpmnGateway.class.isAssignableFrom(mClass.getJavaInterface())) {
                return GATEWAY;
            } else if (BpmnEvent.class.isAssignableFrom(mClass.getJavaInterface())) {
                return EVENT;
            } else {
                return OTHER;
            }
            
        }

    }

}
