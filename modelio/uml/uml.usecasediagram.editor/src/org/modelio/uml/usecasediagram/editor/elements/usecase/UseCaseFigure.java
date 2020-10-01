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

package org.modelio.uml.usecasediagram.editor.elements.usecase;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.core.figures.EllipseFigure;
import org.modelio.diagram.elements.core.figures.ToolbarLayoutWithGrab;
import org.modelio.diagram.elements.core.figures.borders.TLBRBorder;

@objid ("5e6bc1d8-55b7-11e2-877f-002564c97630")
public class UseCaseFigure extends EllipseFigure {
    @objid ("5e6bc1dc-55b7-11e2-877f-002564c97630")
    public UseCaseFigure() {
        super();
        
        final ToolbarLayoutWithGrab layout = new ToolbarLayoutWithGrab();
        layout.setHorizontal(false);
        layout.setStretchMinorAxis(true);
        layout.setLastChildGrab(false);
        
        this.setLayoutManager(layout);
    }

    @objid ("5e6bc1df-55b7-11e2-877f-002564c97630")
    @Override
    public void add(IFigure figure, Object constraint, int index) {
        super.add(figure, constraint, index);
        updateChildrenSeparationLine();
    }

    @objid ("5e6bc1e5-55b7-11e2-877f-002564c97630")
    @Override
    public void remove(IFigure figure) {
        super.remove(figure);
        updateChildrenSeparationLine();
    }

    @objid ("5e6bc1e9-55b7-11e2-877f-002564c97630")
    @Override
    public void setLineColor(Color lineColor) {
        super.setLineColor(lineColor);
        
        // Update children separation lines
        updateChildrenSeparationLine();
    }

    @objid ("5e6bc1ed-55b7-11e2-877f-002564c97630")
    @Override
    public void setLineWidth(int lineWidth) {
        super.setLineWidth(lineWidth);
    }

    @objid ("5e6bc1f1-55b7-11e2-877f-002564c97630")
    private void updateChildrenSeparationLine() {
        // Sort out visible and non visible children
        final List<IFigure> children = getChildren();
        final ArrayList<IFigure> withBorder = new ArrayList<>(children.size());
        final ArrayList<IFigure> withNoBorder = new ArrayList<>(children.size());
        for (IFigure child : children) {
            if (child.isVisible()) {
                withBorder.add(child);
            } else {
                withNoBorder.add(child);
            }
        }
        
        // The last visible child doesn't need a border
        if (!withBorder.isEmpty()) {
            withNoBorder.add(withBorder.remove(withBorder.size() - 1));
        }
        
        // Add border to visible children
        Border newBorder = new TLBRBorder(getLineColor(), getLineWidth(), false, false, true, false);
        for (IFigure f : withBorder) {
            f.setBorder(newBorder);
        }
        
        // Remove unneeded borders
        for (IFigure f : withNoBorder) {
            f.setBorder(null);
        }
    }

}
