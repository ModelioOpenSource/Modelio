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

package org.modelio.diagram.editor.statik.elements.internalstructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.elements.common.group.GroupEditPart;
import org.modelio.diagram.elements.core.model.IGmObject;

/**
 * {@link GmInternalStructureGroup} edit part.
 * <p>
 * Installs a custom layout edit policy.
 */
@objid ("3595a7cf-55b7-11e2-877f-002564c97630")
public class InternalStructureGroupEditPart extends GroupEditPart {
    @objid ("3595a7d3-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new InstanceGroupLayoutEditPolicy());
    }

    @objid ("3595a7db-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        IGmObject model = getModel();
        if (model.getLayoutData() != null) {
            getFigure().getParent().setConstraint(getFigure(), model.getLayoutData());
        }
    }

}
