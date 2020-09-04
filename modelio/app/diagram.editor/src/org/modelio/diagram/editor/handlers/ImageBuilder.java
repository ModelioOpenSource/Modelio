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

package org.modelio.diagram.editor.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformFigure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.editor.plugin.DiagramEditor;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramFigure;

/**
 * Builds an image from a gef diagram. The returned image must be disposed by
 * the caller when no longer in use.
 */
@objid ("65b21824-33f7-11e2-95fe-001ec947c8cc")
public class ImageBuilder {
    @objid ("65b21826-33f7-11e2-95fe-001ec947c8cc")
    private static final int MARGIN = 10;

    @objid ("f4082e57-a12b-4409-a22a-462229d5ace0")
    private final int maxWidth;

    @objid ("c3c79d9f-3ab2-43fd-bc3e-477e0f7890dc")
    private final int maxHeight;

    @objid ("aec40530-c395-49f4-a742-10350ce976af")
    private final int margin;

    @objid ("1f5c39d7-c4b9-4dc8-af79-05e7525b13f4")
    private double scale = 1.0;

    @objid ("45955273-14d2-41d9-b518-3742e8ec513a")
    private int format;

    @objid ("c2af8b06-cfc7-4d8a-b4a8-3583ccb8f5b4")
    public ImageBuilder() {
        this(SWT.IMAGE_PNG);
    }

    @objid ("f00a3b84-d007-47e8-9c65-4d126d46ce41")
    public ImageBuilder(int format) {
        this(ImageBuilder.MARGIN, format);
    }

    @objid ("47b49445-29c8-49c8-b4b6-ba095a1bf39d")
    public ImageBuilder(int margin, int format) {
        switch (format) {
        case SWT.IMAGE_PNG:
        case SWT.IMAGE_JPEG:
        case SWT.IMAGE_GIF:
            this.maxWidth = 8192 * 3;
            this.maxHeight = 8192 * 3;
            break;
        case SWT.IMAGE_BMP:
        default:
            this.maxWidth = 8192;
            this.maxHeight = 8192;
            break;
        }
        this.margin = margin;
        this.format = format;
    }

    @objid ("d3118d79-4e32-4601-a1d4-7b54d98f1460")
    public double getLastBuildScale() {
        return this.scale;
    }

    @objid ("39cf4348-5bf2-4f54-83c7-379cb70d716d")
    public Image makeImage(RootEditPart rootEditPart) {
        // Temporarily add the background layer to the "printable layers" set so
        // that it is present in the saved image
        final LayerManager lm = (LayerManager) rootEditPart;
        final IFigure backgroundLayer = lm.getLayer("BACKGROUND_LAYER");
        final IFigure drawingLayers = lm.getLayer(AbstractDiagramEditPart.DRAWING_LAYER);
        final Layer printableLayers = (Layer) lm.getLayer(LayerConstants.PRINTABLE_LAYERS);
        final ConnectionLayer connectionLayer = (ConnectionLayer) lm.getLayer(LayerConstants.CONNECTION_LAYER);
        printableLayers.add(backgroundLayer, 0);
        
        // Compure the diagram figure
        final AbstractDiagramFigure diagramFigure = getDiagramFigure(printableLayers);
        
        // Make sure page boundaries are not displayed
        boolean oldShowBoundaries = diagramFigure.isShowPageBoundaries();
        diagramFigure.showPageBoundaries(false);
        
        // Scaling
        final Rectangle contentsBounds = computeContentsBounds(diagramFigure, connectionLayer, drawingLayers);
        int width = contentsBounds.width + 2 * this.margin;
        int height = contentsBounds.height + 2 * this.margin;
        
        double scaleX = (width > this.maxWidth) ? (double) width / (double) this.maxWidth : (double) 1.0;
        double scaleY = (height > this.maxHeight) ? (double) height / (double) this.maxHeight : (double) 1.0;
        this.scale = Math.min(scaleX, scaleY);
        int effectiveWidth = (int) (width * this.scale);
        int effectiveHeight = (int) (height * this.scale);
        
        if (this.scale < 1.0) {
            DiagramEditor.LOG.debug("makeImage: %dx%d ?> %dx%d =>using scale %f, %dx%d", width, height, this.maxWidth, this.maxHeight, this.scale,
                    effectiveWidth, effectiveHeight);
        }
        
        // Protection agains't BMP image size being greater than 64Mb
        long totalSize = effectiveWidth * effectiveHeight * 4;
        long max = 64 * 1024 * 1024;
        if (this.format == SWT.IMAGE_BMP && totalSize > max) {
            DiagramEditor.LOG.warning("Make image, size %d x %d would exced max size !", effectiveWidth, effectiveHeight);
            this.scale = Math.sqrt((double) max / (double) totalSize);
            effectiveWidth = (int) (effectiveWidth * this.scale);
            effectiveHeight = (int) (effectiveHeight * this.scale);
            DiagramEditor.LOG.warning("Make image, image resized to %d x %d  ", effectiveWidth, effectiveHeight);
        }
        
        final Image img = new Image(Display.getDefault(), effectiveWidth, effectiveHeight);
        final GC imageGC = new GC(img);
        
        // prepare a translation and a scaling of the drawing
        // the role of the translation is to:
        // - compensate for the margin
        // - deal with x and y of the contents bounds
        // the role of the scaling is ... obvious
        final int deltaX = this.margin + (-contentsBounds.x);
        final int deltaY = this.margin + (-contentsBounds.y);
        
        final Graphics graphics = new SWTGraphics(imageGC);
        graphics.scale(this.scale);
        graphics.translate(deltaX, deltaY);
        graphics.setClip(contentsBounds);
        
        // draw
        printableLayers.paint(graphics);
        graphics.dispose();
        imageGC.dispose();
        
        // Restore page boundaries
        diagramFigure.showPageBoundaries(oldShowBoundaries);
        
        // Restore the background layer placement
        printableLayers.remove(backgroundLayer);
        final Layer scalableLayers = (Layer) lm.getLayer(LayerConstants.SCALABLE_LAYERS);
        scalableLayers.add(backgroundLayer, "BACKGROUND_LAYER", 0);
        return img; // the caller is responsible for disposing the returned
        // image
    }

    /**
     * Recurse through layers in order to find the DiagramFigure
     * @param layer @return
     */
    @objid ("65b21831-33f7-11e2-95fe-001ec947c8cc")
    private AbstractDiagramFigure getDiagramFigure(Layer layer) {
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

    /**
     * Compute the minimum contents size of the diagram. This size is defined as
     * the union of the smallest bounding rectangle that encloses both all the
     * nodes and all the links
     * @param diagramFigure the diagram figure
     * @param connectionLayer the connection layer
     * @param drawingLayers the drawing layers pane
     * @return the bounds to export
     */
    @objid ("65b21837-33f7-11e2-95fe-001ec947c8cc")
    private Rectangle computeContentsBounds(AbstractDiagramFigure diagramFigure, ConnectionLayer connectionLayer, IFigure drawingLayers) {
        Rectangle results = null;
        
        // Compute for the nodes
        results = this.computeMinimumBounds(diagramFigure);
        
        // Compute for drawing layers
        final Rectangle drawingsBounds = ((FreeformFigure) drawingLayers).getFreeformExtent();
        results.union(drawingsBounds);
        
        // Compute for links
        
        results.union(this.computeMinimumBounds(connectionLayer));
        return results;
    }

    /**
     * Computes the minimum bounds of a connection layer. The returned rectangle
     * is the smallest rectangle enclosing all the links.
     * @param figure
     * @return
     */
    @objid ("65b2183e-33f7-11e2-95fe-001ec947c8cc")
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
     * Computes the minimum bounds of a diagram figure. The returned rectangle
     * is the smallest rectangle enclosing all the diagram nodes (note: the
     * computation does not take links into account which are laid in the
     * Connection layer)
     * @param figure @return
     */
    @objid ("65b21844-33f7-11e2-95fe-001ec947c8cc")
    private Rectangle computeMinimumBounds(AbstractDiagramFigure figure) {
        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;
        
        for (final Object fig : figure.getChildren()) {
            final Rectangle b;
            if (fig instanceof FreeformFigure) {
                b = ((FreeformFigure) fig).getFreeformExtent();
            } else if (fig instanceof ConnectionLayer) {
                b = computeMinimumBounds((ConnectionLayer) fig);
            } else {
                b = ((Figure) fig).getBounds();
            }
        
            if (b.isEmpty()) {
                continue;
            }
        
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
