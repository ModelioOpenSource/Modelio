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
package org.modelio.diagram.elements.core.link.anchors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Get the default anchor reference point for a link creation request.
 * 
 * @since 5.1
 */
@objid ("4670a5ab-2d59-4550-9534-e38838e6955f")
@FunctionalInterface
public interface IAnchorRefResolver {
    /**
     * Get the default anchor reference point for a link unmask request.
     * @param sourceEditPart edit part of the link's source.
     * @param targetEditPart edit part of the link's target.
     * @param linkElement the link being unmasked.
     * @return an absolute point.
     */
    @objid ("b8321ca6-d031-40c5-8695-321c036654d8")
    Point resolveAnchorRef(AbstractGraphicalEditPart sourceEditPart, AbstractGraphicalEditPart targetEditPart, MObject linkElement);
}

