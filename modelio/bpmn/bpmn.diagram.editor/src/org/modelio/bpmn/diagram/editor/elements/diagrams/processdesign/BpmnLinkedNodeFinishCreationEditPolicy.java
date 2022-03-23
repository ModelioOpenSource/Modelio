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
package org.modelio.bpmn.diagram.editor.elements.diagrams.processdesign;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeFinishCreationEditPolicy;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;

/**
 * This policy extends the standard linked node creation policy to forbid {@link BpmnMessage} creation.
 */
@objid ("d770264d-f034-48a7-93b9-f0bf638c52fe")
class BpmnLinkedNodeFinishCreationEditPolicy extends LinkedNodeFinishCreationEditPolicy {
    @objid ("a949adb8-fbd8-4377-9b12-031921a1339c")
    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        AbstractNodeEditPart nodeEditPart = (AbstractNodeEditPart) getHost();
        if (request.getNewObjectType().equals(BpmnMessage.MNAME) && nodeEditPart instanceof BpmnProcessDesignDiagramEditPart) {
            return null;
        }
        return super.getConnectionCompleteCommand(request);
    }

}
