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
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;

/**
 * <p>
 * Generic policy that delegates the {@link #getTargetEditPart(Request) getTargetEditPart},
 * {@link #understandsRequest(Request) understandsRequest} and {@link #getCommand(Request) getCommand} methods call when
 * the request is of type {@link RequestConstants#REQ_DIRECT_EDIT DIRECT_EDIT} to the first {@link EditPart} amongst the
 * children of the Host that does understand/returns a command for this request.
 * </p>
 * <p>
 * All other methods will do nothing and/or return <code>null</code>.
 * </p>
 * 
 * @author fpoyer
 */
@objid ("80ceca14-1dec-11e2-8cad-001ec947c8cc")
public class DelegatingDirectEditionEditPolicy implements EditPolicy {
    /**
     * The EditPart this policy is installed on.
     */
    @objid ("680ae741-1e83-11e2-8cad-001ec947c8cc")
    private EditPart host;

    @objid ("80ceca1c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void activate() {
        // Nothing specific to do.
    }

    @objid ("80ceca1f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void deactivate() {
        // Nothing specific to do.
    }

    @objid ("80ceca22-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void eraseSourceFeedback(Request request) {
        // Nothing specific to do.
    }

    @objid ("80ceca28-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void eraseTargetFeedback(Request request) {
        // Nothing specific to do.
    }

    @objid ("80ceca2e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Command getCommand(Request request) {
        return null;
    }

    @objid ("80ceca38-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public EditPart getHost() {
        return this.host;
    }

    @objid ("80ceca3f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (RequestConstants.REQ_DIRECT_EDIT.equals(request.getType())) {
            for (Object childEditPartObj : getHost().getChildren()) {
                EditPart childEditPart = (EditPart) childEditPartObj;
                EditPart target = childEditPart.getTargetEditPart(request);
                if (target != null) {
                    return target;
                }
            }
        }
        // No satisfying edit part found...
        return null;
    }

    @objid ("80ceca49-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setHost(EditPart editpart) {
        this.host = editpart;
    }

    @objid ("80ceca4f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void showSourceFeedback(Request request) {
        // Nothing specific to do.
    }

    @objid ("80ceca55-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void showTargetFeedback(Request request) {
        // Nothing specific to do.
    }

    @objid ("80d12c59-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean understandsRequest(Request request) {
        if (RequestConstants.REQ_DIRECT_EDIT.equals(request.getType())) {
            for (Object childEditPartObj : getHost().getChildren()) {
                EditPart childEditPart = (EditPart) childEditPartObj;
                if (childEditPart.understandsRequest(request))
                    return true;
            }
        }
        // No child edit part found that understands the request...
        return false;
    }

}
