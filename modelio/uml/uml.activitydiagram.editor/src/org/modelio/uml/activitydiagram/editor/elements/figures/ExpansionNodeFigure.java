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

package org.modelio.uml.activitydiagram.editor.elements.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.common.portcontainer.PortConstraint.Border;
import org.modelio.diagram.elements.core.figures.IOrientableShaper.Orientation;
import org.modelio.diagram.elements.core.figures.ShapedFigure;
import org.modelio.diagram.elements.core.figures.borders.ShapedBorder;

/**
 * Specialised figure for ExpansionNode. It is based on a Shaper, and it is orientable.
 * 
 * @author fpoyer
 */
@objid ("2a6c19ba-55b6-11e2-877f-002564c97630")
public class ExpansionNodeFigure extends ShapedFigure {
    @objid ("d1f7e6ab-55c0-11e2-9337-002564c97630")
    private ShapedBorder shapedBorder;

    /**
     * C'tor.
     */
    @objid ("2a6c19c1-55b6-11e2-877f-002564c97630")
    public ExpansionNodeFigure() {
        super();
        setSize(100, 100);
        setShaper(new ExpansionNodeShaper());
        setOpaque(true);
        this.shapedBorder = new ShapedBorder(this.penOptions.lineColor,
                this.penOptions.lineWidth,
                this.shaper);
        
        setBorder(new CompoundBorder(this.shapedBorder, new MarginBorder(2)));
    }

    @objid ("2a6c19c4-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineColor(Color lineColor) {
        if (lineColor != this.penOptions.lineColor) {
            super.setLineColor(lineColor);
            this.shapedBorder.setColor(lineColor);
        }
    }

    @objid ("2a6c19c8-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineWidth(int lineWidth) {
        if (lineWidth != this.penOptions.lineWidth) {
            super.setLineWidth(lineWidth);
            this.shapedBorder.setWidth(lineWidth);
        }
    }

    /**
     * Sets the reference border for this figure and returns the new orientation of the figure.
     * 
     * @param b the border on which this figure will be "attached".
     * @return the new {@link Orientation} of the figure.
     */
    @objid ("2a6c19cc-55b6-11e2-877f-002564c97630")
    public Orientation setReferenceBorder(Border b) {
        ExpansionNodeShaper expansionNodeShaper = (ExpansionNodeShaper) this.shaper;
        Orientation newOrientation = getOrientation(b);
        expansionNodeShaper.setOrientation(newOrientation);
        return newOrientation;
    }

    /**
     * @param b a border
     * @return the orientation for the given border.
     */
    @objid ("9b4361ff-4f92-4506-8116-ae389ce37834")
    public static Orientation getOrientation(Border b) {
        switch (b) {
        case North:
        case South:
            // Attached to top or bottom border: draw figure horizontally
            return Orientation.WestEast;
        case East:
        case West:
            // Attached to left or right border: draw figure vertically
            return Orientation.NorthSouth;
        default:
            // Should not happen, but better be safe
            return Orientation.Undefined;
        }
    }

}
