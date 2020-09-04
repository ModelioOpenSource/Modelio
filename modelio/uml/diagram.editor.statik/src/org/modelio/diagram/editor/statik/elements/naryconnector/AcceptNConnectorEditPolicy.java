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

package org.modelio.diagram.editor.statik.elements.naryconnector;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.tools.multipoint.AcceptModifiableNodeCommand;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.core.tools.multipoint.MultiPointCreationEditPolicy;
import org.modelio.metamodel.uml.statik.NaryConnector;

/**
 * Edit policy that accept creation of n-ary NaryConnector from the host.
 * <p>
 * To be put on all BindableInstance edit parts.
 * 
 * @author cmarin
 */
@objid ("35d43759-55b7-11e2-877f-002564c97630")
public class AcceptNConnectorEditPolicy extends MultiPointCreationEditPolicy {
    /**
     * C'tor.
     * @see MultiPointCreationEditPolicy#MultiPointCreationEditPolicy(boolean) for details on the effect of isOpaque
     * parameter.
     * @param isOpaque whether this policy is opaque or not.
     */
    @objid ("35d4375d-55b7-11e2-877f-002564c97630")
    public AcceptNConnectorEditPolicy(final boolean isOpaque) {
        super(isOpaque);
    }

    @objid ("35d43762-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getMultiPointFirstCommand(final CreateMultiPointRequest request) {
        // Return a command that does nothing (nothing specific to do for this node to accept the constraint, but do not return null).
        if (isCreationOf(request, NaryConnector.class))
            return new AcceptModifiableNodeCommand(getHost());
        else
            return null;
    }

    @objid ("35d4376a-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getMultiPointFinalCommand(final CreateMultiPointRequest request) {
        return null;
    }

    @objid ("35d43773-55b7-11e2-877f-002564c97630")
    @Override
    protected EditPart getTargetEditPartLast(final CreateMultiPointRequest request) {
        return null;
    }

    @objid ("35d4377c-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getMultiPointAdditionalCommand(final CreateMultiPointRequest request) {
        // Return a command that does nothing (nothing specific to do for this node to accept the constraint, but do not return null).
        if (isCreationOf(request, NaryConnector.class)) {
            //TODO Test all selected elements are compatible for a connector
            return new AcceptModifiableNodeCommand(getHost());
        } else
            return null;
    }

}
