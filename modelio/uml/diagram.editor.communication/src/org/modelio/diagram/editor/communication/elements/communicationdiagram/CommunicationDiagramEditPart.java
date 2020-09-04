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

package org.modelio.diagram.editor.communication.elements.communicationdiagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramEditLayoutPolicy;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramElementDropEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeFinishCreationEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.core.policies.CreateLinkIntermediateEditPolicy;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;

/**
 * EditPart (== controller in the GEF model) for Communication diagram background.
 */
@objid ("7a2816aa-55b6-11e2-877f-002564c97630")
public class CommunicationDiagramEditPart extends AbstractDiagramEditPart {
    /**
     * @see AbstractDiagramEditPart#createEditPolicies()
     */
    @objid ("7a2816b4-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        // Policy to add nodes on the diagram
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new DiagramEditLayoutPolicy());
        
        // Policy to create notes
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_END,
                new LinkedNodeFinishCreationEditPolicy());
        
        // Policy to add bend points to connections being created
        installEditPolicy(CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT,
                new CreateLinkIntermediateEditPolicy());
        
        // Policy to create Lost, Found and Creation messages.
        installEditPolicy(ModelElementDropRequest.TYPE, new DiagramElementDropEditPolicy());
    }

}
