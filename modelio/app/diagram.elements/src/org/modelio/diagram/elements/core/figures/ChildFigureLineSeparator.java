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

package org.modelio.diagram.elements.core.figures;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;

/**
 * Composite figure whose children are separated by a line border.
 * <p>
 * Figures added to this figure should have no border because it will be overriden by this composite figure.
 */
@objid ("7f6b8b9d-1dec-11e2-8cad-001ec947c8cc")
public class ChildFigureLineSeparator {
    /**
     * Update the separation line between visible children of the given figure.
     * @param layoutedFigure The parent figure.
     * @param separationBorder The border to set
     */
    @objid ("7f6b8b9f-1dec-11e2-8cad-001ec947c8cc")
    public static void updateSeparation(IFigure layoutedFigure, Border separationBorder) {
        updateSeparation(layoutedFigure.getChildren(), separationBorder);
    }

    @objid ("7f6b8ba9-1dec-11e2-8cad-001ec947c8cc")
    private static void updateSeparation(List<IFigure> children, Border separationBorder) {
        // Sort out visible and non visible children
        final ArrayList<IFigure> withBorder = new ArrayList<>(children.size());
        final ArrayList<IFigure> withNoBorder = new ArrayList<>(children.size());
        for (Object o : children) {
            IFigure child = (IFigure) o;
            if (child.isVisible())
                withBorder.add(child);
            else
                withNoBorder.add(child);
        }
        
        // The last visible child doesn't need a border
        if (!withBorder.isEmpty())
            withNoBorder.add(withBorder.remove(withBorder.size() - 1));
        
        // Add border to visible children
        for (IFigure f : withBorder) {
            f.setBorder(separationBorder);
        }
        
        // Remove unneeded borders
        for (IFigure f : withNoBorder)
            f.setBorder(null);
    }

    /**
     * Update the separation line between visible children figures of the given edit parts.
     * @param layoutedEditPart The parent edit part.
     * @param separationBorder The border to set
     */
    @objid ("7f6b8bb3-1dec-11e2-8cad-001ec947c8cc")
    public static void updateSeparation(EditPart layoutedEditPart, Border separationBorder) {
        final ArrayList<IFigure> children = new ArrayList<>(layoutedEditPart.getChildren().size());
        for (Object e : layoutedEditPart.getChildren()) {
            children.add(((GraphicalEditPart) e).getFigure());
        }
        
        updateSeparation(children, separationBorder);
    }

}
