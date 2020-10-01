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

package org.modelio.linkeditor.panel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * Builds an image from a GEF root edit part. The returned image must be disposed by the caller when no longer in use.
 */
@objid ("1b4776f2-5e33-11e2-b81d-002564c97630")
class ImageBuilder {
    @objid ("1b4776f4-5e33-11e2-b81d-002564c97630")
    private static final int MARGIN = 10;

    @objid ("1b4776f6-5e33-11e2-b81d-002564c97630")
    public Image makeImage(RootEditPart rootEditPart) {
        final LayerManager lm = (LayerManager) rootEditPart;
        final Layer printableLayers = (Layer) lm.getLayer(LayerConstants.PRINTABLE_LAYERS);
        final ConnectionLayer connectionLayer = (ConnectionLayer) lm.getLayer(LayerConstants.CONNECTION_LAYER);
        
        // image size is the diagram contents size expanded for the margin
        final Rectangle contentsBounds = computeContentsBounds(((GraphicalEditPart) rootEditPart.getContents()).getFigure(),
                                                               connectionLayer);
        final Image img = new Image(Display.getDefault(),
                                    contentsBounds.width + 2 * MARGIN,
                                    contentsBounds.height + 2 * MARGIN);
        final GC imageGC = new GC(img);
        
        // prepare a translation of the drawing
        // the role of the  translation is to: 
        // - compensate for the margin 
        // - deal with x and y of the contents bounds
        final int deltaX = MARGIN + (-contentsBounds.x);
        final int deltaY = MARGIN + (-contentsBounds.y);
        
        final Graphics graphics = new SWTGraphics(imageGC);
        graphics.translate(deltaX, deltaY);
        
        graphics.setClip(contentsBounds);
        
        // draw
        printableLayers.paint(graphics);
        graphics.dispose();
        imageGC.dispose();
        return img; // the caller is responsible for disposing the returned image
    }

    /**
     * Compute the minimum contents size of the diagram. This size is defined as the union of the smallest bounding
     * rectangle that encloses both all the nodes and all the links
     */
    @objid ("1b4776fd-5e33-11e2-b81d-002564c97630")
    private Rectangle computeContentsBounds(final IFigure backgroundFigure, ConnectionLayer connectionLayer) {
        Rectangle results = null;
        
        // Compute for the nodes
        results = this.computeMinimumBounds(backgroundFigure);
        
        // Compute for links
        results.union(this.computeMinimumBounds(connectionLayer));
        return results;
    }

    /**
     * Computes the minimum bounds of a connection layer. The returned rectangle is the smallest rectangle enclosing all
     * the links.
     */
    @objid ("1b47770b-5e33-11e2-b81d-002564c97630")
    private Rectangle computeMinimumBounds(ConnectionLayer connectionLayer) {
        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;
        
        for (final Object o : connectionLayer.getChildren()) {
            final Rectangle b = ((Figure) o).getBounds();
        
            if (b.x < xMin) {
                xMin = b.x;
            }
            if (b.x + b.width > xMax) {
                xMax = b.x + b.width;
            }
        
            if (b.y < yMin) {
                yMin = b.y;
            }
            if (b.y + b.height > yMax) {
                yMax = b.y + b.height;
            }
        
        }
        return new Rectangle(xMin, yMin, xMax - xMin, yMax - yMin);
    }

    /**
     * Computes the minimum bounds of a background figure. The returned rectangle is the smallest rectangle enclosing
     * all the nodes (note: the computation does not take links into account which are laid in the Connection layer)
     */
    @objid ("1b477715-5e33-11e2-b81d-002564c97630")
    private Rectangle computeMinimumBounds(IFigure figure) {
        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;
        
        for (final Object fig : figure.getChildren()) {
            final Rectangle b = ((Figure) fig).getBounds();
        
            if (b.x < xMin) {
                xMin = b.x;
            }
            if (b.x + b.width > xMax) {
                xMax = b.x + b.width;
            }
        
            if (b.y < yMin) {
                yMin = b.y;
            }
            if (b.y + b.height > yMax) {
                yMax = b.y + b.height;
            }
        
        }
        return new Rectangle(xMin, yMin, xMax - xMin, yMax - yMin);
    }

}
