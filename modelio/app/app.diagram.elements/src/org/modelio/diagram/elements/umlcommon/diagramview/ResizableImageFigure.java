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
package org.modelio.diagram.elements.umlcommon.diagramview;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

/**
 * Draw an SWT {@link Image} at the center of the figure, shrinking it if necessary to match the client area.
 */
@objid ("e8b518fa-6288-40f3-9ebb-e5c9a3a592b8")
class ResizableImageFigure extends Figure {
    @objid ("c9f630d5-96d5-492a-9c33-5c900444ca89")
    private Image image;

    @objid ("7aa09281-a239-42f6-a54f-959a46b3903f")
    @Override
    protected void paintFigure(Graphics gc) {
        super.paintFigure(gc);
        
        if (this.image != null) {
            org.eclipse.swt.graphics.Rectangle imageBounds = this.image.getBounds();
            Rectangle areaBounds = this.getClientArea();
        
            Rectangle drawBounds = computeDrawBounds(imageBounds, areaBounds);
        
            gc.drawImage(this.image, imageBounds.x, imageBounds.y, imageBounds.width, imageBounds.height, drawBounds.x, drawBounds.y, drawBounds.width, drawBounds.height);
        }
        
    }

    /**
     * Compute the appropriate draw size to make the image fit the area and center it.
     */
    @objid ("0cb40ef6-130e-4d01-8dca-2b9fa18445d7")
    private Rectangle computeDrawBounds(org.eclipse.swt.graphics.Rectangle imageBounds, Rectangle areaBounds) {
        double ratio;
        if (imageBounds.width > areaBounds.width || imageBounds.height > areaBounds.height) {
            ratio = Math.max((double) imageBounds.width / (double) areaBounds.width, (double) imageBounds.height / (double) areaBounds.height);
        } else {
            ratio = 1.0;
        }
        
        Rectangle drawBounds = Rectangle.SINGLETON;
        // Resize the image to fit the draw area
        drawBounds.width = (int) (imageBounds.width / ratio);
        drawBounds.height = (int) (imageBounds.height / ratio);
        // Center the draw
        drawBounds.x = drawBounds.width < areaBounds.width ? areaBounds.x + (areaBounds.width - drawBounds.width) / 2 : areaBounds.x;
        drawBounds.y = drawBounds.height < areaBounds.height ? areaBounds.y + (areaBounds.height - drawBounds.height) / 2 : areaBounds.y;
        return drawBounds;
    }

    /**
     * @return the image's full size.
     */
    @objid ("a2aacf45-30be-4422-9428-8377b0c0d3fe")
    @Override
    public Dimension getPreferredSize(int wHint, int hHint) {
        if (this.image != null) {
            org.eclipse.swt.graphics.Rectangle imageBounds = this.image.getBounds();
            return new Dimension(imageBounds.width, imageBounds.height);
        } else {
            return super.getPreferredSize(wHint, hHint);
        }
        
    }

    @objid ("c9f53b37-69e2-405a-ae39-cc966193fbcd")
    @Override
    protected void finalize() throws Throwable {
        this.image = null;
        
        super.finalize();
        
    }

    /**
     * @return the displayed image.
     */
    @objid ("ed829450-8637-4e2e-94af-d1ec3e2db519")
    public Image getImage() {
        return this.image;
    }

    /**
     * @param image the image to display.
     */
    @objid ("e0df31b8-22b7-4eb5-8f95-9adf884e4b6d")
    public void setImage(Image image) {
        this.image = image;
    }

}
