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
package org.modelio.uml.sequencediagram.editor.elements.combinedfragment.primarynode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.modelio.diagram.elements.core.figures.GradientFigure;

/**
 * Hollow figure
 * 
 * @author fpoyer
 */
@objid ("d8d12e59-55b6-11e2-877f-002564c97630")
public class InteractionOperandContainerFigure extends GradientFigure {
    @objid ("d8d12e5d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean containsPoint(final int x, final int y) {
        for (Object childObj : getChildren()) {
            if (((IFigure) childObj).containsPoint(x, y)) {
                return true;
            }
        }
        return false;
    }

}
