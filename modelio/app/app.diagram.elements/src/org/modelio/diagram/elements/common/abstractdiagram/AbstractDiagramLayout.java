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
package org.modelio.diagram.elements.common.abstractdiagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Layout for diagrams.
 * <p>
 * Extends {@link FreeformLayout} to modify incomplete constraints (have -1 in size).
 * <p>
 * The constraint must be modified so that the layout is reproducible with auto resize edit policies when the preferred size changes.
 * 
 * 
 * @author cmarin
 * @since 3.4.1
 */
@objid ("56c174cd-d098-4460-9ffb-1b0452c9061e")
public class AbstractDiagramLayout extends FreeformLayout {
    @objid ("bb369c46-2cdf-435b-a1d5-3cc23822fc4a")
    @Override
    public void layout(IFigure parent) {
        for (Object o : parent.getChildren()) {
            IFigure f = (IFigure) o;
            Rectangle bounds = getConstraint(f);
            if (bounds != null) {
                // Modifies the constraint if not complete.
                // The constraint must be modified so that the layout is reproducible
                // with auto resize edit policies when the preferred size changes.
                if (bounds.width == -1 || bounds.height == -1) {
                    Dimension childPreferredSize = f.getPreferredSize(bounds.width, bounds.height);
                    if (bounds.width == -1) {
                        bounds.width = childPreferredSize.width;
                    }
                    if (bounds.height == -1) {
                        bounds.height = childPreferredSize.height;
                    }
                }
            }
        }
        
        // Call original behavior
        super.layout(parent);
        
    }

    @objid ("7973b886-7964-4fc8-bbde-39e754caaa0e")
    @Override
    public Rectangle getConstraint(IFigure figure) {
        Object constraint = super.getConstraint(figure);
        if (constraint instanceof Rectangle) {
            return (Rectangle) constraint;
        } else {
            // In an abstract diagram layout, constraints should always be Rectangle, ignoring invalid constraint
            return null;
        }
        
    }

}
