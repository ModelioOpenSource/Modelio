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
package org.modelio.bpmn.diagram.editor.elements.diagrams.processcollaboration;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.modelio.bpmn.diagram.editor.elements.workflow.WorkflowEditPart;
import org.modelio.diagram.elements.common.linkednode.CreateLinkedNodeCommand;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeFinishCreationEditPolicy;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnCollaborationDiagram;

/**
 * This policy extends the standard linked node creation policy in order to create these linked nodes in workflows rather than in the policy's host (usually, a {@link BpmnCollaborationDiagram}.
 */
@objid ("0811933f-01da-41f1-b5bc-c7664ae7dade")
class BpmnLinkedNodeFinishCreationEditPolicy extends LinkedNodeFinishCreationEditPolicy {
    /**
     * @return the first workflow upwards the source edit part composition tree, or the host.
     */
    @objid ("6d588a82-f722-4eac-b82e-9f30a0255882")
    private EditPart getDestinationEditPart(CreateConnectionRequest connectionRequest) {
        EditPart sourceEditPart = connectionRequest.getSourceEditPart();
        while (sourceEditPart != null) {
            if (sourceEditPart instanceof WorkflowEditPart) {
                return sourceEditPart;
            }
            sourceEditPart = sourceEditPart.getParent();
        }
        return getHost();
    }

    @objid ("3beb70fc-ef3d-4a8b-a919-d1be08b50693")
    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        EditPart nodeEditPart = getDestinationEditPart(request);
        CreateLinkedNodeCommand startCommand = (CreateLinkedNodeCommand) request.getStartCommand();
        
        startCommand.setDestinationNode((GmCompositeNode) nodeEditPart.getModel());
        Point p = new Point(request.getLocation());
        getHostFigure().translateToRelative(p);
        startCommand.setNodeLocation(p);
        if (request.getSize() != null) {
            Dimension d = new Dimension(request.getSize());
            getHostFigure().translateToRelative(d);
            startCommand.setNodeSize(d);
        } else {
            startCommand.setNodeSize(null);
        }
        return startCommand;
    }

}
