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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.core.figures.freeform.FreeformLayer2;

/**
 * Additionnal layer for Modelio diagram providing a background image
 */
@objid ("657b423c-33f7-11e2-95fe-001ec947c8cc")
public class BackgroundLayer extends FreeformLayer2 {
    @objid ("657b423e-33f7-11e2-95fe-001ec947c8cc")
    private int alpha = 255;

    @objid ("6e1e4a9b-a8dc-4d6c-a2f3-58cf59d49af8")
    private Image bgImage = null;

    @objid ("2c885649-8e70-4c38-adec-19afd9dce815")
    private ImageDescriptor bgDescriptor = null;

    @objid ("65826917-33f7-11e2-95fe-001ec947c8cc")
    private Dimension tileSize = null;

    @objid ("65826918-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected void paintFigure(Graphics graphics) {
        graphics.setAlpha(this.alpha);
        super.paintFigure(graphics);
        
        if (this.bgImage != null) {
            if (this.tileSize == null || ((this.tileSize != null) && (this.tileSize.isEmpty())))
                // no significative tile size => use the full size of the layer (no tiling)
                drawBackgroundTiles(graphics, this.bgImage, this.getBounds().getSize());
            else
                //  a significative tile size is defined => use tiling for the image
                drawBackgroundTiles(graphics, this.bgImage, this.tileSize);
        }
        
    }

    @objid ("6582691c-33f7-11e2-95fe-001ec947c8cc")
    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    /**
     * Set the background image. Use 'null' to set no image. Allocation/disposal of the image is controlled by the
     * BackgroundLayer class. If an invalid descriptor or null is passed, or if the image cannot be created, the
     * background is set to no image.
     * @param id
     */
    @objid ("6582691f-33f7-11e2-95fe-001ec947c8cc")
    public void setImage(ImageDescriptor id) {
        // Null descriptor => clear background image
        if (id == null) {
            removeBgImage();
            return;
        }
        
        // Attempt to set the same image => return, do nothing
        if (id.equals(this.bgDescriptor)) {
            return;
        }
        
        // Setting a new image
        removeBgImage();
        Image newImage = id.createImage();
        if (newImage != null) {
            setImage(id, newImage);
        }
        
    }

    @objid ("65826923-33f7-11e2-95fe-001ec947c8cc")
    private void setImage(ImageDescriptor id, Image newImage) {
        this.bgDescriptor = id;
        this.bgImage = newImage;
        
    }

    @objid ("65826927-33f7-11e2-95fe-001ec947c8cc")
    private void removeBgImage() {
        if (this.bgImage != null) {
            this.bgImage.dispose();
            this.bgImage = null;
            this.bgDescriptor = null;
        }
        
    }

    @objid ("65826929-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected void finalize() throws Throwable {
        removeBgImage();
        super.finalize();
        
    }

    @objid ("6582692c-33f7-11e2-95fe-001ec947c8cc")
    private void drawBackgroundTiles(Graphics graphics, Image img, Dimension size) {
        Rectangle r = getBounds();
        int x = r.x;
        int y = r.y;
        int xmax = r.x + r.width;
        int ymax = r.y + r.height;
        
        y = r.y;
        while (y < ymax) {
            x = r.x;
            while (x < xmax) {
                graphics.drawImage(img,
                                   0,
                                   0,
                                   img.getBounds().width,
                                   img.getBounds().height,
                                   x,
                                   y,
                                   size.width,
                                   size.height);
                x += size.width;
            }
            y += size.height;
        }
        
    }

    /**
     * Set the page size (width, height) in pixels. The caller is responsible for converting physical dimensions (mm or
     * ") into pixels taking care of the Display getDPI() conversion factor The Page will be used to tile the background
     * figure when there is one
     * @param size
     */
    @objid ("65826931-33f7-11e2-95fe-001ec947c8cc")
    public void setTileSize(Dimension size) {
        this.tileSize = size;
    }

    @objid ("0d22f73f-ea50-4582-8c09-36efdfd440ef")
    public Image getImage() {
        return this.bgImage;
    }

    @objid ("f6e80215-d246-4272-b22e-9dd23bb7d84f")
    public Dimension getTileSize() {
        return this.tileSize;
    }

    @objid ("9a11d7cf-4a8a-4f1d-acdc-024335badaf8")
    public int getAlpha() {
        return this.alpha;
    }

}
