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
import org.modelio.bpmn.diagram.editor.elements.common.policies.BpmnDiagramElementDropEditPolicy;
import org.modelio.bpmn.diagram.editor.elements.diagrams.processcollaboration.GmBpmnProcessCollaborationDiagram;
import org.modelio.bpmn.diagram.editor.elements.workflow.WorkflowEditPart;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnSequenceFlowDataAssociation;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("1f91ec7e-4b82-4d6f-93be-6e56aa308a3e")
class BpmnProcessDiagramElementDropEditPolicy extends BpmnDiagramElementDropEditPolicy {
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
        if (elt == null || elt instanceof BpmnSequenceFlow) {
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
            MExpert mExpert = diagram.getModelManager().getMetamodel().getMExpert();
            for (final MObject toUnmask : request.getDroppedElements()) {
                if (toUnmask.getMClass().isLinkMetaclass() || toUnmask instanceof BpmnDataAssociation || toUnmask instanceof BpmnSequenceFlowDataAssociation) {
                    // Links between workflow elements must be managed by the diagram itself
                    if (isInWorkflow(diagram, mExpert.getSource(toUnmask)) && isInWorkflow(diagram, mExpert.getTarget(toUnmask))) {
                        continue;
                    }
                }
        
                if (isInWorkflow(diagram, toUnmask)) {
                    // The element must be handled by the WorkflowEditPart or nobody.
                    // Fixes 0013736: [BPMN] Context menu - unmask notes and constraint doesnt work
                    // UnmaskManager creates drop requests outside WorkflowEditPart bounds.
                    return getDropInWorkflowTargetEditPart(request);
                } else if (diagram.getDiagramOwner() instanceof GmBpmnProcessCollaborationDiagram) {
                    // No generic unmask when the process is displayed in a collaboration diagram
                    return null;
                }
            }
        }
        return ret;
    }

    /**
     * Asks the owned WorkflowEditPart whether it accepts the drop request.
     * @param request a drop request
     * @return whatever {@link WorkflowEditPart#getTargetEditPart(org.eclipse.gef.Request)} answered or null.
     */
    @objid ("9f300823-8936-4939-bedc-c2ab233e0a4b")
    private EditPart getDropInWorkflowTargetEditPart(ModelElementDropRequest request) {
        IGmDiagram diagram = (IGmDiagram) getHost().getModel();
        
        // Look for our WorkflowEditPart
        WorkflowEditPart wep = null;
        for (Object childEp : getHost().getChildren()) {
            if (childEp instanceof WorkflowEditPart) {
                wep = (WorkflowEditPart) childEp;
            }
        }
        
        // Abort if no WorkflowEditPart
        if (wep == null)
            return null;
        
        // Abort if any element is not in the workflow
        for (final MObject toUnmask : request.getDroppedElements()) {
            if (! isInWorkflow(diagram, toUnmask))
                return null;
        }
        
        // Asks the WorkflowEditPart
        return wep.getTargetEditPart(request);
    }

}
