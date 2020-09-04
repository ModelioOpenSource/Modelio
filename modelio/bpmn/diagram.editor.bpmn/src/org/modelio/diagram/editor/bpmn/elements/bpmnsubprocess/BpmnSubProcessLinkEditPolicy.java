/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.bpmn.elements.bpmnsubprocess;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.modelio.diagram.editor.bpmn.elements.policies.BpmnCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This policy forbids link creation between elements that are not part of the same SubProcess.
 */
@objid ("61c3e2f3-55b6-11e2-877f-002564c97630")
public class BpmnSubProcessLinkEditPolicy extends BpmnCreateLinkEditPolicy {
    @objid ("61c3e2f6-55b6-11e2-877f-002564c97630")
    @Override
    public EditPart getTargetEditPart(final Request request) {
        if (RequestConstants.REQ_CONNECTION_END.equals(request.getType())) {
            if (request instanceof CreateBendedConnectionRequest) {
                CreateBendedConnectionRequest bendedrequest = (CreateBendedConnectionRequest) request;
                GmModel sourceModel = (GmModel) bendedrequest.getSourceEditPart().getModel();
                GmModel targetModel = (GmModel) getHost().getModel();
        
                MObject source = sourceModel.getRelatedElement();
                MObject target = targetModel.getRelatedElement();
        
                if (source instanceof BpmnItemAwareElement) {
                    if (source.getCompositionOwner().equals(target)) {
                        return super.getTargetEditPart(request);
                    } else {
                        source = source.getCompositionOwner();
                    }
                } else if (target instanceof BpmnItemAwareElement) {
                    if (target.getCompositionOwner().equals(source)) {
                        return super.getTargetEditPart(request);
                    } else {
                        target = target.getCompositionOwner();
                    }
                }
        
                for (BpmnSubProcess owner : getOwnerSubProcesses(source)) {
                    if (target.equals(owner)) {
                        return null;
                    }
                }
            }
        }
        return super.getTargetEditPart(request);
    }

    /**
     * @return the {@link BpmnSubProcess} upwards composition hierarchy.
     */
    @objid ("61c3e2fd-55b6-11e2-877f-002564c97630")
    private List<BpmnSubProcess> getOwnerSubProcesses(final MObject element) {
        MObject owner = element.getCompositionOwner();
        if (owner instanceof BpmnSubProcess) {
            List<BpmnSubProcess> ret = getOwnerSubProcesses(owner);
            ret.add((BpmnSubProcess) owner);
            return ret;
        } else {
            return new ArrayList<>();
        }
    }

}
