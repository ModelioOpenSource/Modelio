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

/**
 * 
 */
package org.modelio.linkeditor.handlers.zoom;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.LayerManager;

/**
 * Handler that will set the zoom level to the value that will ensure that all the link editor elements will be visible.
 */
@objid ("1bc0dd01-5e33-11e2-b81d-002564c97630")
public class ZoomToFitHandler {
    @objid ("1bc0dd03-5e33-11e2-b81d-002564c97630")
    @Execute
    public Object execute(@Named(IServiceConstants.ACTIVE_PART) final MPart part) {
        System.out.println("ZoomToFitHandler.execute() FIXME ! ");
        
        //        if (! (part.getObject() instanceof ILinkEditorView)) {
        //            return null;
        //        }
        //        
        //        ILinkEditorView editor = (ILinkEditorView) part.getObject();
        //        
        //        ZoomManager zoomManager = editor.getAdapter(ZoomManager.class);
        //        
        //        
        //        final int MARGIN = 30;
        //              
        //        // Compute the whole diagram size
        //        final Rectangle overallRect = computeOverallSize(editor.getGraphicalViewer()
        //                                                                       .getRootEditPart());
        //        
        //        // Get the current view size
        //        final Dimension viewSize = ((FigureCanvas) editor.getGraphicalViewer()
        //                                                                 .getRootEditPart()
        //                                                                 .getViewer()
        //                                                                 .getControl()).getViewport()
        //                                                                               .getSize()
        //                                                                               .getCopy();
        //        viewSize.shrink(MARGIN, MARGIN);
        //        
        //        // Compute the zoom factor based on the ratio (view size / diagram size)
        //        double zoomFactor = 1.0;
        //        final double zoomFactorX = (double) overallRect.width / viewSize.width;
        //        final double zoomFactorY = (double) overallRect.height / viewSize.height;
        //        zoomFactor = 1 / Math.max(zoomFactorX, zoomFactorY);
        //        
        //        // Set the zoom factor
        //        if (zoomManager != null /*&&
        //                                zoomManager.getMinZoom() < zoomFactor &&
        //                                zoomFactor < zoomManager.getMaxZoom() */) {
        //            zoomManager.setZoom(zoomFactor);
        //        }
        //        
        //        final FigureCanvas canvas = (FigureCanvas) editor.getGraphicalViewer()
        //                                                                 .getRootEditPart()
        //                                                                 .getViewer()
        //                                                                 .getControl();
        //        final Rectangle scaledRect = overallRect.scale(zoomFactor);
        //        final int xPos = (scaledRect.x + (scaledRect.width / 2));
        //        final int yPos = (scaledRect.y + (scaledRect.height / 2));
        //        canvas.getViewport().setViewLocation(xPos, yPos);
        return null;
    }

    @objid ("1bc0dd0a-5e33-11e2-b81d-002564c97630")
    private Rectangle computeOverallSize(final RootEditPart rootEditPart) {
        Rectangle overallRect = null;
        final LayerManager lm = (LayerManager) rootEditPart;
        final ConnectionLayer connectionLayer = (ConnectionLayer) lm.getLayer(LayerConstants.CONNECTION_LAYER);
        
        // Compute for the nodes
        final Layer backgroundLayer = (Layer) ((GraphicalEditPart) rootEditPart.getContents()).getFigure();
        assert (backgroundLayer != null);
        final Rectangle nodesRect = computeMinimumBounds(backgroundLayer);
        overallRect = nodesRect;
        
        // Compute for links
        final Rectangle linksRect = computeMinimumBounds(connectionLayer);
        overallRect.union(linksRect);
        return overallRect;
    }

    @objid ("1bc0dd14-5e33-11e2-b81d-002564c97630")
    private Rectangle computeMinimumBounds(final Layer layer) {
        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;
        
        for (final Object o : layer.getChildren()) {
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

}
