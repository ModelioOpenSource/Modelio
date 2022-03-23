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
/**
 * 
 */
package org.modelio.diagram.editor.handlers.zoom;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.editparts.ZoomManager;
import org.modelio.diagram.editor.AbstractDiagramEditor;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramFigure;

/**
 * Handler that will set the zoom level to the value that will ensure that all the diagram elements will be visible.
 * 
 * @author phv
 */
@objid ("66628623-33f7-11e2-95fe-001ec947c8cc")
public class ZoomFitHandler {
    /**
     * C'tor.
     */
    @objid ("66628625-33f7-11e2-95fe-001ec947c8cc")
    public  ZoomFitHandler() {
        super();
    }

    @objid ("66628628-33f7-11e2-95fe-001ec947c8cc")
    @Execute
    public Object execute(@Named (IServiceConstants.ACTIVE_PART) final MPart part) {
        if (!(part.getObject() instanceof AbstractDiagramEditor)) {
            return null;
        }
        
        AbstractDiagramEditor editor = (AbstractDiagramEditor) part.getObject();
        
        final int MARGIN = 30;
        final ZoomManager zoomManager = editor.getAdapter(ZoomManager.class);
        
        // Compute the whole diagram size
        final Rectangle overallRect = computeOverallSize((LayerManager) editor.getRootEditPart());
        
        // Get the current view size
        final Dimension viewSize = ((FigureCanvas) editor.getRootEditPart().getViewer().getControl()).getViewport()
                .getSize()
                .getCopy();
        viewSize.shrink(MARGIN, MARGIN);
        
        // Compute the zoom factor based on the ratio (view size / diagram size)
        double zoomFactor = 1.0;
        final double zoomFactorX = (double) overallRect.width / viewSize.width;
        final double zoomFactorY = (double) overallRect.height / viewSize.height;
        zoomFactor = 1 / Math.max(zoomFactorX, zoomFactorY);
        
        // Set the zoom factor
        if (zoomManager != null /*
                                 * && zoomManager.getMinZoom() < zoomFactor && zoomFactor < zoomManager.getMaxZoom()
                                 */) {
            zoomManager.setZoom(zoomFactor);
        }
        
        final FigureCanvas canvas = (FigureCanvas) editor.getRootEditPart().getViewer().getControl();
        final Rectangle scaledRect = overallRect.scale(zoomFactor);
        final int xPos = (scaledRect.x + (scaledRect.width / 2));
        final int yPos = (scaledRect.y + (scaledRect.height / 2));
        canvas.getViewport().setViewLocation(xPos, yPos);
        return null;
    }

    @objid ("6662862f-33f7-11e2-95fe-001ec947c8cc")
    private Rectangle computeOverallSize(final LayerManager lm) {
        Rectangle overallRect = null;
        final Layer printableLayers = (Layer) lm.getLayer(LayerConstants.PRINTABLE_LAYERS);
        final ConnectionLayer connectionLayer = (ConnectionLayer) lm.getLayer(LayerConstants.CONNECTION_LAYER);
        
        // Compute for the nodes
        final AbstractDiagramFigure diagramFigure = getDiagramFigure(printableLayers);
        assert (diagramFigure != null);
        final Rectangle nodesRect = computeMinimumBounds(diagramFigure);
        overallRect = nodesRect;
        
        // Compute for links
        final Rectangle linksRect = computeMinimumBounds(connectionLayer);
        overallRect.union(linksRect);
        return overallRect;
    }

    /**
     * Recurse through layers in order to find the DiagramFigure
     * @param layer @return
     */
    @objid ("66628635-33f7-11e2-95fe-001ec947c8cc")
    private AbstractDiagramFigure getDiagramFigure(final Layer layer) {
        AbstractDiagramFigure result = null;
        
        for (final Object o : layer.getChildren()) {
            if (o instanceof AbstractDiagramFigure) {
                return (AbstractDiagramFigure) o;
            } else if (o instanceof Layer) {
                final AbstractDiagramFigure fig = getDiagramFigure((Layer) o);
                if (fig != null) {
                    result = fig;
                }
            }
        }
        return result;
    }

    @objid ("6662863c-33f7-11e2-95fe-001ec947c8cc")
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
