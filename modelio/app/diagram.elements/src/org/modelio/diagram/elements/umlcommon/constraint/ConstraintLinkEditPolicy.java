/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.elements.umlcommon.constraint;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.tools.multipoint.AcceptModifiableNodeCommand;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.core.tools.multipoint.MultiPointCreationEditPolicy;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * Default edit policy that should be installed on a node to make it accept constraint creation.
 * 
 * @author fpoyer
 */
@objid ("811650af-1dec-11e2-8cad-001ec947c8cc")
public class ConstraintLinkEditPolicy extends MultiPointCreationEditPolicy {
    /**
     * C'tor.
     * @see MultiPointCreationEditPolicy#MultiPointCreationEditPolicy(boolean) for details on the effect of isOpaque
     * parameter.
     * 
     * @param isOpaque whether this policy is opaque or not.
     */
    @objid ("811650b1-1dec-11e2-8cad-001ec947c8cc")
    public ConstraintLinkEditPolicy(final boolean isOpaque) {
        super(isOpaque);
    }

    @objid ("811650b6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getMultiPointFirstCommand(final CreateMultiPointRequest request) {
        // Return a command that does nothing (nothing specific to do for this node to accept the constraint, but do not return null).
        if (isCreationOf(request, Constraint.class) && isValidConstraintEnd()) {
            return new AcceptModifiableNodeCommand(getHost());
        } else {
            return null;
        }
    }

    @objid ("811650be-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getMultiPointFinalCommand(final CreateMultiPointRequest request) {
        // Do not accept to hold the "body" of the constraint,
        // only containers like diagram/package/partitions/etc should accept that.
        return null;
    }

    @objid ("8118b2e9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPart getTargetEditPartLast(final CreateMultiPointRequest request) {
        // Do not accept to hold the "body" of the constraint,
        // only containers like diagram/package/partitions/etc should accept that.
        return null;
    }

    @objid ("8118b2f2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getMultiPointAdditionalCommand(final CreateMultiPointRequest request) {
        // Return a command that does nothing (nothing specific to do for this node to accept the constraint, but do not return null).
        if (isCreationOf(request, Constraint.class) && isValidConstraintEnd()) {
            return new AcceptModifiableNodeCommand(getHost());
        } else {
            return null;
        }
    }

    @objid ("2385bd26-76db-4555-b484-51329b54398d")
    private boolean isValidConstraintEnd() {
        return ((GmModel) getHost().getModel()).getRelatedElement() instanceof UmlModelElement;
    }

}
