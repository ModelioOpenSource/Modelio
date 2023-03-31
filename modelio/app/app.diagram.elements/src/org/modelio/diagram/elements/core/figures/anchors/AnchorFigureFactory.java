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
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.modelio.diagram.elements.core.figures.IBrushOptionsSupport;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;

/**
 * Factory that creates a figure for a {@link ConnectionAnchor}.
 * <p>
 * Used to display fixed anchor positions when displaying feedback or connection creation handles.
 */
@objid ("1219600b-79b7-47dd-81ba-5833e340d223")
public class AnchorFigureFactory {
    /**
     * Create a connection anchor handle figure.
     * <p>
     * If the anchor implements {@link IAnchorHandleProvider} its {@link IAnchorHandleProvider#createAnchorHandleFigure(ConnectionAnchor)}
     * is called with itself as parameter.
     * In the other case {@link #createDefaultHandleFigure(IFigure)} is used.
     * @param anchor the anchor to display
     * @return the created figure.
     */
    @objid ("cfec9c3d-f3c3-4662-a5d7-244dcd281968")
    public static IFigure createHandleFigure(ConnectionAnchor anchor) {
        if (anchor  instanceof IAnchorHandleProvider)
            return ((IAnchorHandleProvider) anchor).createAnchorHandleFigure(anchor);
        return createDefaultHandleFigure(anchor.getOwner());
    }

    /**
     * Create a default connection anchor handle figure.
     * <p>
     * It is a circle figure using same colors as the node figure.
     * The figure has a defined size but has to be moved on the right location.
     * @param ownerFigure the source/target node figure
     * @return the created figure.
     */
    @objid ("aa8bd610-8f3d-4b97-8a4d-e138cc083c7a")
    public static Ellipse createDefaultHandleFigure(IFigure ownerFigure) {
        final int diameter = FixedAnchor.ANCHOR_RADIUS * 2 + 1;
        
        Ellipse child = new Ellipse();
        child.setSize(diameter, diameter);
        child.setOpaque(true);
        child.setFill(true);
        child.setLineWidth(3);
        child.setAntialias(1);
        child.setBorder(new MarginBorder(3) );
        
        // Use same colors as the node figure
        copyForeground(ownerFigure, child);
        copyBackground(ownerFigure, child);
        return child;
    }

    @objid ("c299388d-4bee-4b10-b63e-d37b867573eb")
    public static void copyBackground(IFigure srcFigure, IFigure targetFigure) {
        if (srcFigure instanceof IBrushOptionsSupport) {
            targetFigure.setBackgroundColor(((IBrushOptionsSupport) srcFigure).getFillColor());
        } else {
            targetFigure.setBackgroundColor(srcFigure.getBackgroundColor());
        }
        
    }

    @objid ("2f8d874c-a07d-4536-9ef2-72637cdc94df")
    public static void copyForeground(IFigure srcFigure, IFigure targetFigure) {
        if (srcFigure instanceof IPenOptionsSupport) {
            targetFigure.setForegroundColor(((IPenOptionsSupport)srcFigure).getLineColor());
        } else {
            targetFigure.setForegroundColor(srcFigure.getForegroundColor());
        }
        
    }

}
