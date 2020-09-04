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

package org.modelio.diagram.elements.common.groupitem;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.elements.common.label.modelelement.ModelElementLabelEditPart;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.policies.HoverFeedbackEditPolicy;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;

/**
 * EditPart for model element labels in groups.<br>
 * The create link, note and constraint policies are installed by default.
 * <p>
 * A group item is selectable if the group is in a box or the group is already selected.
 */
@objid ("7e5e21e0-1dec-11e2-8cad-001ec947c8cc")
public class GroupItemEditPart extends ModelElementLabelEditPart {
    /**
     * Default constructor.
     */
    @objid ("7e5e21e2-1dec-11e2-8cad-001ec947c8cc")
    public GroupItemEditPart() {
    }

    @objid ("7e5e21e5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                          new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        installEditPolicy("hover", new HoverFeedbackEditPolicy());
    }

    /**
     * A group item is selectable if:<ul>
     * <li> the group is in a box
     * <li> or the group (owned by a link) is already selected.
     * </ul>
     */
    @objid ("f2e2d058-fddc-4787-8a79-ef86e5275b5c")
    @Override
    public boolean isSelectable() {
        if (isOwnedByConnection()) {
            return selectableOnlyWithParent();
        } else {
            return super.isSelectable();
        }
    }

}
