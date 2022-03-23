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
package org.modelio.bpmn.diagram.editor.elements.bpmnlanesetcontainer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.OrderedLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.modelio.bpmn.diagram.editor.elements.bpmnlane.BpmnLaneEditPart;
import org.modelio.bpmn.diagram.editor.elements.bpmnlane.hibridcontainer.BodyHybridContainerEditPart;
import org.modelio.bpmn.diagram.editor.plugin.DiagramEditorBpmn;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.policies.LayoutChildrenNodeConnectionsHelper;
import org.modelio.diagram.elements.core.policies.LayoutConnectionsOrderedLayoutEditPolicyDecorator;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;

/**
 * {@link LayoutConnectionsOrderedLayoutEditPolicyDecorator} redefined to handle autoresize mess
 * with laneset creations and destruction.
 * @author cma
 * @since 5.1.0
 */
@objid ("f4f1920a-80a6-4692-b133-b0eee1662f16")
public class LayoutLaneSetConnectionsEditPolicyDecorator extends LayoutConnectionsOrderedLayoutEditPolicyDecorator {
    @objid ("11630c53-7165-45b4-bcb9-f1c253f556ec")
    public  LayoutLaneSetConnectionsEditPolicyDecorator(OrderedLayoutEditPolicy decorated) {
        super(decorated);
    }

    @objid ("c3b5c206-9c6d-4109-ab30-5dba58c87128")
    protected void addLayoutRootLaneSetConnectionsCommands(CompoundCommand command, Request request) {
        // - host is BodyHybridContainerEditPart
        // - parent is BpmnLaneEditPart
        // - grand parent is BpmnLaneSetContainerEditPart
        // - ...
        EditPart root = getHost();
        EditPart parent = root.getParent();
        while (parent != null && isLaneRelatedEditPart(parent)) {
            root = parent;
            parent = root.getParent();
        }
        
        LayoutChildrenNodeConnectionsHelper
        .forRequest(request)
        .addEditPart((GraphicalEditPart) root)
        .createCommands(command);
        
    }

    @objid ("a7aa10bf-09e5-4420-9b3c-9833ee6e61c6")
    protected static boolean isLaneRelatedEditPart(Object parent) {
        return parent instanceof BpmnLaneSetContainerEditPart ||
                parent instanceof BpmnLaneEditPart ||
                parent instanceof BodyHybridContainerEditPart;
        
    }

    @objid ("e7548f5b-4a3c-4197-abbd-33e3e998573e")
    @Override
    protected void addCreateCommand(CompoundCommand command, CreateRequest request) {
        final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(request);
        
        if (ctx != null && ctx.getMetaclass().getJavaInterface() == BpmnLane.class) {
            // CreateBpmnLaneSetContainerCommand moves and resize
            // the host itself more or less directly, we need to
            // layout all links from the root laneset container: .
            addLayoutRootLaneSetConnectionsCommands(command, request);
        } else {
            super.addCreateCommand(command, request);
        }
        
    }

    @objid ("661cbe80-43e4-4c41-9bc9-8eb630e10f42")
    @Override
    protected void addAddCommand(CompoundCommand command, Request request) {
        if (isLaneRelatedRequest(request)) {
            addLayoutRootLaneSetConnectionsCommands(command, request);
        } else {
            super.addAddCommand(command, request);
        }
        
    }

    @objid ("6e697c8e-727b-4077-99f6-dfef9ecd43d7")
    @Override
    protected void addDeleteDependantCommand(CompoundCommand command, GroupRequest request) {
        if (request.getEditParts().stream().anyMatch(e -> isLaneRelatedEditPart(e) )) {
            addLayoutRootLaneSetConnectionsCommands(command, request);
        } else {
            super.addDeleteDependantCommand(command, request);
        }
        
    }

    @objid ("2f73668a-0ced-4c63-a098-1b56745acd28")
    @Override
    protected void addCloneCommand(CompoundCommand command, ChangeBoundsRequest request) {
        if (isLaneRelatedRequest(request)) {
            addLayoutRootLaneSetConnectionsCommands(command, request);
        } else {
            super.addCloneCommand(command, request);
        }
        
    }

    @objid ("5da75233-4c9d-4d45-9991-d05b6572cff5")
    @Override
    protected void addOrphanChildrenCommand(CompoundCommand command, GroupRequest request) {
        if (isLaneRelatedRequest(request)) {
            addLayoutRootLaneSetConnectionsCommands(command, request);
        } else {
            super.addOrphanChildrenCommand(command, request);
        }
        
    }

    @objid ("b5c0ff74-3081-49c2-9e41-fdbfbd7a15a2")
    @Override
    protected void addMoveChildrenCommand(CompoundCommand command, ChangeBoundsRequest request) {
        if (isLaneRelatedRequest(request)) {
            addLayoutRootLaneSetConnectionsCommands(command, request);
        } else {
            super.addMoveChildrenCommand(command, request);
        }
        
    }

    @objid ("77ef834b-207f-4170-825f-fc270894cbe3")
    protected static boolean isLaneRelatedRequest(Request request) {
        if (request instanceof GroupRequest) {
            GroupRequest groupRequest = (GroupRequest) request;
            for (Object o : groupRequest.getEditParts()) {
                if (isLaneRelatedEditPart(o))
                    return true;
            }
        } else if (DiagramEditorBpmn.LOG.isDebugEnabled()) {
            DiagramEditorBpmn.LOG.debug(new IllegalStateException(String.format("%s not a GroupRequest", RequestHelper.toString(request))));
        }
        return false;
    }

}
