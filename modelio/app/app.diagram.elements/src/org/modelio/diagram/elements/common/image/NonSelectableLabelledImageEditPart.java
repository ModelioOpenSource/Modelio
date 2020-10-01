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

package org.modelio.diagram.elements.common.image;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Specialisation of the LabelledImageEditPart that is not selectable (used when embedded in a PortContainerEditPart for
 * example).
 * 
 * @author fpoyer
 */
@objid ("7e890c61-1dec-11e2-8cad-001ec947c8cc")
public class NonSelectableLabelledImageEditPart extends LabelledImageEditPart {
    @objid ("7e890c63-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isSelectable() {
        return false;
    }

}
