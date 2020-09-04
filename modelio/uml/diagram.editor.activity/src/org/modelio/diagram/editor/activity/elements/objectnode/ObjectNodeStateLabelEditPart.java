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

package org.modelio.diagram.editor.activity.elements.objectnode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.modelio.diagram.elements.common.label.base.ElementLabelEditPart;

/**
 * Specialization of the ElementLabelEditPart to remove the edition functionalities. Displays the text in italic.
 */
@objid ("2ade8aae-55b6-11e2-877f-002564c97630")
public class ObjectNodeStateLabelEditPart extends ElementLabelEditPart {
    /**
     * Default constructor.
     */
    @objid ("2ade8ab2-55b6-11e2-877f-002564c97630")
    public ObjectNodeStateLabelEditPart() {
    }

    @objid ("2ade8ab5-55b6-11e2-877f-002564c97630")
    @Override
    public void performRequest(final Request req) {
        // Overload performRequest to avoid being edited through the delayed click.
        return;
    }

    @objid ("2ade8abb-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        // operation label isn't meant to be editable
        removeEditPolicy(EditPolicy.DIRECT_EDIT_ROLE);
    }

    @objid ("2ade8abe-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

}
