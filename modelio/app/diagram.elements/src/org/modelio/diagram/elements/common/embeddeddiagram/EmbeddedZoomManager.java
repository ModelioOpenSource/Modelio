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

package org.modelio.diagram.elements.common.embeddeddiagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.FreeformFigure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.ZoomManager;

/**
 * Zoom manager for embedded diagrams.
 * @author cma
 * @since 3.7
 */
@objid ("034d4316-4c6e-47d6-b83c-c928f1bfadab")
class EmbeddedZoomManager extends ZoomManager {
    @objid ("024f908c-a971-42de-8441-d65bbe0e1d2d")
    private boolean centerView;

    @objid ("fc86529e-6f05-456d-ae7e-3e437f3e8f5f")
    public EmbeddedZoomManager(ScalableFigure pane, Viewport viewport) {
        super(pane, viewport);
    }

    @objid ("edc8b735-96f6-4f9d-b1fd-4f0fa9689854")
    public void fitToContent() {
        if (! getViewport().getClientArea().isEmpty()) {
            double fitZoomLevel = getFitPageZoomLevel();
            primSetZoom(fitZoomLevel);
        } else {
            // DiagramElements.LOG.debug(new Throwable("Skip fit to content on empty "+getViewport()+ " with area="+getViewport().getClientArea()));
        }
    }

    /**
     * Sets the zoom level to the given value. Min-max range check is not done.
     * @param zoom the new zoom level
     */
    @objid ("6bd9895a-342c-452f-b6a8-d586c5fb1f23")
    @Override
    protected void primSetZoom(double zoom) {
        double finalZoom = zoom;
        if (finalZoom == 0) {
            finalZoom = 1;
        }
        
        FreeformFigure scalableFigure = (FreeformFigure) getScalableFigure();
        
        double prevZoom = getZoom();
        if (Math.abs(prevZoom - finalZoom) >= 0.01) {
            super.primSetZoom(finalZoom);
        }
        
        Rectangle extent = scalableFigure.getFreeformExtent();
        Viewport vp = getViewport();
        Point topLeft =  extent.getTopLeft();
        
        if (this.centerView) {
            Rectangle avail = vp.getBounds();
        
            Dimension spare = avail.getSize().shrink(extent.width(), extent.height()).scale(0.5).negate();
        
            topLeft.translate(spare);
            vp.getHorizontalRangeModel().setMinimum(Math.min(topLeft.x, vp.getHorizontalRangeModel().getMinimum()));
            vp.getVerticalRangeModel().setMinimum(Math.min(topLeft.y, vp.getVerticalRangeModel().getMinimum()));
        }
        
        Point viewLocation = vp.getViewLocation();
        if (!viewLocation.equals(topLeft)) {
            setViewLocation(topLeft);
        }
    }

    /**
     * Redefined to exclude (0,0) point to 'desired' rectangle.
     * @see #getFitXZoomLevel(int)
     */
    @objid ("5b9af58d-7251-4444-869f-954a288855d3")
    @Override
    protected double getFitPageZoomLevel() {
        ScalableFigure scalableFigure = getScalableFigure();
        IFigure fig = scalableFigure;
        
        Dimension available = getViewport().getClientArea().getSize();
        Dimension desired;
        if (fig instanceof FreeformFigure) {
            desired = ((FreeformFigure) fig).getFreeformExtent().getSize();
        } else {
            desired = fig.getPreferredSize().getCopy();
        }
        
        Insets insets = fig.getInsets();
        desired.width -= insets.getWidth();
        desired.height -= insets.getHeight();
        
        while (fig != getViewport()) {
            insets = fig.getInsets();
            available.width -= insets.getWidth();
            available.height -= insets.getHeight();
            fig = fig.getParent();
        }
        
        double scalableFigureZoom = scalableFigure.getScale();
        double curZoom = getZoom();
        assert (Math.abs(curZoom - scalableFigureZoom) < 0.001) : String.format("curZoom:%f != fig zoom:%f", curZoom, scalableFigureZoom);
        double maxZoom = getMaxZoom();
        double scaleX = Math.min(available.width * curZoom / desired.width, maxZoom);
        double scaleY = Math.min(available.height * curZoom / desired.height, maxZoom);
        return Math.min(scaleX, scaleY);
    }

    @objid ("88da1bee-b9a2-4d1b-990b-2cf72b593ec4")
    public void setCenterView(boolean centerView) {
        this.centerView = centerView;
    }

}
