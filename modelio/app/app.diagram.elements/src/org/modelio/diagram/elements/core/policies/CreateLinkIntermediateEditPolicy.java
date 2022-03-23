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
package org.modelio.diagram.elements.core.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;

/**
 * Edit policy that allow to add bend points to connections being created.
 * <p>
 * This policy is to be put on edit part where bend points should be displayed. eg: this policy should not be put on
 * element headers but can be put on free zone edit parts.
 * 
 * @author cmarin
 */
@objid ("80b49040-1dec-11e2-8cad-001ec947c8cc")
public class CreateLinkIntermediateEditPolicy extends GraphicalEditPolicy {
    /**
     * Updates the CreateBendedConnectionRequest to add a point to its path.
     */
    @objid ("80b49044-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Command getCommand(Request request) {
        if (request.getType() == CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT) {
            final CreateBendedConnectionRequest r = (CreateBendedConnectionRequest) request;
            return r.getStartCommand();
        }
        return super.getCommand(request);
    }

    @objid ("80b4904f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (request.getType() == CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT)
            return getHost();
        return super.getTargetEditPart(request);
    }

}
