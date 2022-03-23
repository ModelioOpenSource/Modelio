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
package org.modelio.diagram.elements.core.figures.anchors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.elements.core.figures.IBrushOptionsSupport;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.link.anchors.fixed.IFixedConnectionAnchorFactory;

@objid ("1219600b-79b7-47dd-81ba-5833e340d223")
public class AnchorFigureFactory {
    /**
     * Create a connection end handle.
     * <p>
     * It is a circle figure using same colors as the node figure.
     * The figure has a defined size but has to be moved on the right location.
     * @param owner the source/target node figure
     * @return the created figure.
     */
    @objid ("aa8bd610-8f3d-4b97-8a4d-e138cc083c7a")
    public static Ellipse createHandleFigure(GraphicalEditPart owner) {
        final int diameter = IFixedConnectionAnchorFactory.ANCHOR_RADIUS * 2 + 1;
        
        Ellipse child = new Ellipse();
        child.setSize(diameter, diameter);
        child.setOpaque(true);
        child.setFill(true);
        child.setLineWidth(2);
        child.setAntialias(1);
        child.setBorder(new MarginBorder(3) );
        IFigure ownerFigure = owner.getFigure();
        
        // Use same colors as the node figure
        if (ownerFigure instanceof IPenOptionsSupport) {
            child.setForegroundColor(((IPenOptionsSupport)ownerFigure).getLineColor());
        } else {
            child.setForegroundColor(ownerFigure.getForegroundColor());
        }
        
        if (ownerFigure instanceof IBrushOptionsSupport) {
            child.setBackgroundColor(((IBrushOptionsSupport) ownerFigure).getFillColor());
        } else {
            child.setBackgroundColor(ownerFigure.getBackgroundColor());
        }
        return child;
    }

}
