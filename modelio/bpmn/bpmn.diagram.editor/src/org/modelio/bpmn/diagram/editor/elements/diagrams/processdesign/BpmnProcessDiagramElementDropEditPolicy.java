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

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.modelio.bpmn.diagram.editor.elements.diagrams.processcollaboration.GmBpmnProcessCollaborationDiagram;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramElementDropEditPolicy;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("1f91ec7e-4b82-4d6f-93be-6e56aa308a3e")
class BpmnProcessDiagramElementDropEditPolicy extends DiagramElementDropEditPolicy {
    @objid ("1e10db7c-43ef-4dc4-b3cf-dfecf5aa088d")
    @Override
    protected Command createDropCommandForElement(final Point p, MObject toUnmask) {
        if (toUnmask instanceof BpmnMessageFlow || !isInWorkflow((IGmDiagram) getHost().getModel(), toUnmask)) {
            return super.createDropCommandForElement(p, toUnmask);
        } else {
            return null;
        }
    }

    /**
     * @return <code>true</code> if the element belongs to the current workflow.
     */
    @objid ("f0712f44-05f3-4324-82fe-8b99d5cbc28f")
    private boolean isInWorkflow(IGmDiagram diagram, MObject elt) {
        if (elt == null) {
            return false;
        } else if (Objects.equals(diagram.getRelatedElement().getOrigin(), elt)) {
            return true;
        } else {
            return isInWorkflow(diagram, elt.getCompositionOwner());
        }
    }

    @objid ("f48894d7-3b6a-4dcc-bfd1-d024cce12361")
    @Override
    protected EditPart getDropTargetEditPart(ModelElementDropRequest request) {
        EditPart ret = super.getDropTargetEditPart(request);
        IGmDiagram diagram = (IGmDiagram) getHost().getModel();
        if (ret != null) {
            for (final MObject toUnmask : request.getDroppedElements()) {
                if (isInWorkflow(diagram, toUnmask)) {
                    return null;
                } else if (diagram.getDiagramOwner() instanceof GmBpmnProcessCollaborationDiagram) {
                    // No generic unmask when the process is displayed in a collaboration diagram
                    return null;
                }
            }
        }
        return ret;
    }

}
