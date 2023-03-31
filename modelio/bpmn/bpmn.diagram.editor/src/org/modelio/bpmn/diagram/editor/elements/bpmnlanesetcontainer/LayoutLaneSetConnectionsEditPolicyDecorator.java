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
@objid ("7cb7c73e-4c7b-4a2c-a491-54769579a639")
public class LayoutLaneSetConnectionsEditPolicyDecorator extends LayoutConnectionsOrderedLayoutEditPolicyDecorator {
    @objid ("4a6b8bf7-0b3f-44f1-9dff-0c997ddaf011")
    public  LayoutLaneSetConnectionsEditPolicyDecorator(OrderedLayoutEditPolicy decorated) {
        super(decorated);
    }

    @objid ("7190c40c-3a73-400e-980c-ec48d30a428c")
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

    @objid ("ec5fe42a-1825-4b54-9903-a98671ca7ea8")
    protected static boolean isLaneRelatedEditPart(Object parent) {
        return parent instanceof BpmnLaneSetContainerEditPart ||
                parent instanceof BpmnLaneEditPart ||
                parent instanceof BodyHybridContainerEditPart;
        
    }

    @objid ("bcdf413f-efcd-4ccb-9cb0-7a495800a348")
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

    @objid ("cc4d1fe5-d2d7-4edb-9989-6c7bc9456372")
    @Override
    protected void addAddCommand(CompoundCommand command, Request request) {
        if (isLaneRelatedRequest(request)) {
            addLayoutRootLaneSetConnectionsCommands(command, request);
        } else {
            super.addAddCommand(command, request);
        }
        
    }

    @objid ("07ebadda-47f3-420b-909c-30711d9a0d1f")
    @Override
    protected void addDeleteDependantCommand(CompoundCommand command, GroupRequest request) {
        if (request.getEditParts().stream().anyMatch(e -> isLaneRelatedEditPart(e) )) {
            addLayoutRootLaneSetConnectionsCommands(command, request);
        } else {
            super.addDeleteDependantCommand(command, request);
        }
        
    }

    @objid ("314dbc69-2211-4a38-b96f-85b1a398b6b8")
    @Override
    protected void addCloneCommand(CompoundCommand command, ChangeBoundsRequest request) {
        if (isLaneRelatedRequest(request)) {
            addLayoutRootLaneSetConnectionsCommands(command, request);
        } else {
            super.addCloneCommand(command, request);
        }
        
    }

    @objid ("161939b9-35c9-4860-bbe5-e5f3a75679aa")
    @Override
    protected void addOrphanChildrenCommand(CompoundCommand command, GroupRequest request) {
        if (isLaneRelatedRequest(request)) {
            addLayoutRootLaneSetConnectionsCommands(command, request);
        } else {
            super.addOrphanChildrenCommand(command, request);
        }
        
    }

    @objid ("c28bb1a2-5c61-43df-abf4-a4f769aca6be")
    @Override
    protected void addMoveChildrenCommand(CompoundCommand command, ChangeBoundsRequest request) {
        if (isLaneRelatedRequest(request)) {
            addLayoutRootLaneSetConnectionsCommands(command, request);
        } else {
            super.addMoveChildrenCommand(command, request);
        }
        
    }

    @objid ("bf0400e8-6dda-46ff-83ed-96637f5af69f")
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
