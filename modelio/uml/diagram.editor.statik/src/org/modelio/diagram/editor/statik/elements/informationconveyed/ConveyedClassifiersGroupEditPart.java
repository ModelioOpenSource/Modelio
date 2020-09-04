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

package org.modelio.diagram.editor.statik.elements.informationconveyed;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.elements.common.group.DefaultGroupLayoutEditPolicy;
import org.modelio.diagram.elements.common.group.GroupEditPart;
import org.modelio.diagram.elements.core.policies.DefaultNodeNonResizableEditPolicy;
import org.modelio.diagram.elements.core.policies.HoverFeedbackEditPolicy;

/**
 * Edit part for {@link GmConveyedClassifiersGroup}.
 * <p>
 * It is a group whose elements are not movable. The group itself is selectable and movable.
 * 
 * @author cmarin
 */
@objid ("34fa03fa-55b7-11e2-877f-002564c97630")
public class ConveyedClassifiersGroupEditPart extends GroupEditPart {
    @objid ("34fa03fe-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return true;
    }

    @objid ("34fa0403-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new LayoutPolicy());
        installEditPolicy("hover", new HoverFeedbackEditPolicy());
    }

    /**
     * Layout policy that forbid move of the content.
     * 
     * @author cmarin
     */
    @objid ("34fa0406-55b7-11e2-877f-002564c97630")
    private static class LayoutPolicy extends DefaultGroupLayoutEditPolicy {
        @objid ("34fa040b-55b7-11e2-877f-002564c97630")
        public LayoutPolicy() {
        }

        @objid ("34fa040d-55b7-11e2-877f-002564c97630")
        @Override
        protected EditPolicy createChildEditPolicy(final EditPart child) {
            DefaultNodeNonResizableEditPolicy p = new DefaultNodeNonResizableEditPolicy();
            p.setDragAllowed(false);
            return p;
        }

    }

}
