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

package org.modelio.diagram.elements.common.label.name;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.elements.common.label.modelelement.ModelElementLabelEditPart;

/**
 * Specialisation of the {@link ModelElementLabelEditPart} to remove the DefaultDeleteNodeEditPolicy so that NameLabel cannot be masked.
 * 
 * @author fpoyer
 */
@objid ("7eaa6d24-1dec-11e2-8cad-001ec947c8cc")
public class NameLabelEditPart extends ModelElementLabelEditPart {
    @objid ("7eaa6d26-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        // Remove the DefaultDeleteNodeEditPolicy installed by default since NameLabel is a floating label, selectable but NOT maskable.
        removeEditPolicy(EditPolicy.COMPONENT_ROLE);
    }

}
