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

package org.modelio.diagram.editor.activity.elements.interruptible;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.figures.RoundedBoxFigure;

/**
 * "Hollow" figure for Interruptible Activity Region.
 * 
 * @author fpoyer
 */
@objid ("2ab6de6e-55b6-11e2-877f-002564c97630")
public class InterruptibleFigure extends RoundedBoxFigure {
    @objid ("2ab6de72-55b6-11e2-877f-002564c97630")
    @Override
    public boolean containsPoint(final int x, final int y) {
        return getBounds().contains(x, y) && !getBounds().getExpanded(-15, -15).contains(x, y);
    }

}
