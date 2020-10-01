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

package org.modelio.diagram.elements.common.root;

import java.beans.PropertyChangeListener;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FreeformViewport;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.ScalableFreeformLayeredPane;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gef.editparts.FreeformGraphicalRootEditPart;
import org.eclipse.gef.editparts.GridLayer;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.core.figures.freeform.ConnectionLayer2;
import org.modelio.diagram.elements.core.figures.freeform.FreeformLayer2;
import org.modelio.diagram.elements.core.figures.freeform.FreeformLayeredPane2;
import org.modelio.diagram.elements.core.figures.freeform.ScalableFreeformLayeredPane2;

/**
 * Extended {@link ScalableFreeformRootEditPart} that: <ul>
 * <li> adds an additional background layer used to either display a color or an image.
 * <li> deals with {@link GridLayer2} additional configuration properties.
 * <li> subclass {@link ScalableFreeformLayeredPane} to better handle zommed text.
 * </ul>
 */
@objid ("668fd320-33f7-11e2-95fe-001ec947c8cc")
public class ScalableFreeformRootEditPart2 extends ScalableFreeformRootEditPart {
    /**
     * Identifier of the diagram background layer.
     */
    @objid ("2d194393-ba62-48f2-8821-063c3ad773a7")
    public static final String BACKGROUND_LAYER = "BACKGROUND_LAYER";

    @objid ("668fd323-33f7-11e2-95fe-001ec947c8cc")
    private static final Point ORIGIN = new Point(0, 0);

    @objid ("668fd325-33f7-11e2-95fe-001ec947c8cc")
    private Point creationLocationTip = ORIGIN;

    @objid ("668fd322-33f7-11e2-95fe-001ec947c8cc")
    private final PropertyChangeListener gridAndBackgroundListener;

    @objid ("6692352c-33f7-11e2-95fe-001ec947c8cc")
    public ScalableFreeformRootEditPart2() {
        this.gridAndBackgroundListener = evt -> {
              
            String property = evt.getPropertyName();
            if (property.equals(AbstractDiagramEditPart.PROPERTY_GRID_ALPHA) ||
                property.equals(AbstractDiagramEditPart.PROPERTY_GRID_COLOR)) {
                refreshGridLayer();
            }
            if (property.equals(AbstractDiagramEditPart.PROPERTY_FILL_ALPHA) ||
                property.equals(AbstractDiagramEditPart.PROPERTY_FILL_COLOR) ||
                property.equals(AbstractDiagramEditPart.PROPERTY_FILL_TILE_SIZE) ||
                property.equals(AbstractDiagramEditPart.PROPERTY_FILL_IMAGE)) {
                refreshBackground();
            }
        };
    }

    @objid ("66923548-33f7-11e2-95fe-001ec947c8cc")
    public Point getCreationLocationTip() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.creationLocationTip;
    }

    @objid ("66923545-33f7-11e2-95fe-001ec947c8cc")
    public void setCreationLocationTip(Point location) {
        if (getFigure().containsPoint(location)) {
            this.creationLocationTip = location;
        } else {
            this.creationLocationTip = ORIGIN;
        }
    }

    /**
     * Creates the background layer.
     * 
     * @return the background layer
     */
    @objid ("66923541-33f7-11e2-95fe-001ec947c8cc")
    protected Layer createBackgroundLayer() {
        Layer l = new BackgroundLayer();
        l.setOpaque(true);
        l.setBackgroundColor(ColorConstants.black);
        return l;
    }

    @objid ("668fd326-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected GridLayer createGridLayer() {
        return new GridLayer2();
    }

    /**
     * Creates a layered pane and the layers that should be scaled.
     * 
     * @return a new freeform layered pane containing the scalable layers
     */
    @objid ("6692353b-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected ScalableFreeformLayeredPane createScaledLayers() {
        // instantiate our better layer pane
        ScalableFreeformLayeredPane2 layers = new ScalableFreeformLayeredPane2();
        
        // create children the same way as super() 
        layers.add(createGridLayer(), GRID_LAYER);
        layers.add(getPrintableLayers(), PRINTABLE_LAYERS);
        layers.add(new FeedbackLayer(), SCALED_FEEDBACK_LAYER);
        
        // Add our background layer at bottom
        Layer l = createBackgroundLayer();
        if (l != null) {
            layers.add(l, BACKGROUND_LAYER, 0);
        }
        return layers;
    }

    @objid ("66923531-33f7-11e2-95fe-001ec947c8cc")
    protected void refreshBackground() {
        IFigure bgLayer = getLayer(BACKGROUND_LAYER);
        if (bgLayer != null) {
            BackgroundLayer backgroundLayer = (BackgroundLayer) bgLayer;
            final EditPartViewer v = getViewer();
        
            // fill color
            Object fillColor = v.getProperty(AbstractDiagramEditPart.PROPERTY_FILL_COLOR);
            if (fillColor != null) {
                backgroundLayer.setBackgroundColor((Color) fillColor);
            }
        
            // fill alpha
            Integer fillAlpha = (Integer) v.getProperty(AbstractDiagramEditPart.PROPERTY_FILL_ALPHA);
            if (fillAlpha != null) {
                backgroundLayer.setAlpha(fillAlpha);
            }
        
            // fill image
            String fillImage = (String) v.getProperty(AbstractDiagramEditPart.PROPERTY_FILL_IMAGE);
            if (fillImage != null) {
                try {
                    // Try to handle as URL
                    ImageDescriptor id = ImageDescriptor.createFromURL(new URL(fillImage));
                    backgroundLayer.setImage(id);
                } catch (MalformedURLException e) {
                    // Handle as file path
                    
                    ImageDescriptor id = ImageDescriptor.createFromFile(null, fillImage);
                    backgroundLayer.setImage(id);
                }
            } else {
                backgroundLayer.setImage(null);    
            }
        
            // page boundaries size (in pixels)
            Dimension tileSize = (Dimension) v.getProperty(AbstractDiagramEditPart.PROPERTY_FILL_TILE_SIZE);
            backgroundLayer.setTileSize(tileSize);
        
        }
    }

    @objid ("6692352e-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected void refreshGridLayer() {
        // super method processes: grid visible, grid spacing, grid origin
        super.refreshGridLayer();
        
        // process additionnal properties
        GridLayer2 grid = (GridLayer2) getLayer(GRID_LAYER);
        
        // grid color
        Object gridColor = getViewer().getProperty(AbstractDiagramEditPart.PROPERTY_GRID_COLOR);
        if (gridColor != null) {
            grid.setGridColor((Color) gridColor);
        }
        
        // grid alpha
        Integer gridAlpha = (Integer) getViewer()
                                          .getProperty(AbstractDiagramEditPart.PROPERTY_GRID_ALPHA);
        if (gridAlpha != null) {
            grid.setGridAlpha(gridAlpha);
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#register()
     */
    @objid ("66923533-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected void register() {
        super.register();
        if (getLayer(GRID_LAYER) != null) {
            getViewer().addPropertyChangeListener(this.gridAndBackgroundListener);
            refreshBackground();
            refreshGridLayer();
        }
    }

    /**
     * @see AbstractEditPart#unregister()
     */
    @objid ("66923537-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected void unregister() {
        getViewer().removePropertyChangeListener(this.gridAndBackgroundListener);
        super.unregister();
    }

    /**
     * redefined to create a FreeformLayeredPane2
     */
    @objid ("cfbbc1fa-0f8c-4959-a902-97e4456cc219")
    @Override
    protected IFigure createFigure() {
        // redefined to create a FreeformLayeredPane2
        FreeformViewport viewport = createViewport();
        FreeformLayeredPane2 innerLayers = new FreeformLayeredPane2();
        
        // Fill inherited private 'innerLayers' field
        try {
            Field f = FreeformGraphicalRootEditPart.class.getDeclaredField("innerLayers");
            f.setAccessible(true);
            f.set(this, innerLayers);
            f.setAccessible(false);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
        
        // same as super.createFigure() 
        createLayers(innerLayers);
        viewport.setContents(innerLayers);
        return viewport;
    }

    @objid ("682ceab7-3e6e-4b42-80f4-620273514b6e")
    protected FreeformViewport createViewport() {
        return new FreeformViewport();
    }

    @objid ("cad38ffe-f99d-41a1-8f57-ef2e9ba4a08f")
    @Override
    protected LayeredPane createPrintableLayers() {
        FreeformLayeredPane2 layeredPane = new FreeformLayeredPane2();
        layeredPane.add(new FreeformLayer2(), PRIMARY_LAYER);
        layeredPane.add(new ConnectionLayer2(), CONNECTION_LAYER);
        return layeredPane;
    }

    @objid ("630d79cb-659e-445f-91d4-0b080a08acc2")
    public static class FeedbackLayer extends FreeformLayer2 {
        @objid ("c944089e-ca8a-4eec-bc14-d158801741ca")
        FeedbackLayer() {
            setEnabled(false);
        }

    }

}
