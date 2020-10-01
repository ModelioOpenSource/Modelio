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

package org.modelio.diagram.elements.core.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

/**
 * A colorizable figure based on the alpha mask of a png file. The drawn colorized image is build by applying the alpha mask of the
 * original image on a filled colored rectangle of the same size as the image.<br>
 * This class does not manage the lifecycle of the image, this is left to the caller.
 */
@objid ("7f6b8bc1-1dec-11e2-8cad-001ec947c8cc")
public class ColorizableImageFigure extends Figure {
    @objid ("f0549d29-c6e7-4c1d-987b-946e9055e907")
    private int pixelColor;

    @objid ("daad8ff2-e127-44bc-bf94-1eb8bcfe9e0e")
    private Image originalImage;

    @objid ("e32b0981-1ff7-47da-9950-a685c371da77")
    private Color color;

    /**
     * Creates an image colored in black.
     * 
     * @param image the image
     */
    @objid ("7f6b8bc7-1dec-11e2-8cad-001ec947c8cc")
    public ColorizableImageFigure(Image image) {
        this(image, Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
    }

    /**
     * Constructor.
     * 
     * @param image the image
     * @param color the color to apply
     */
    @objid ("7f6b8bca-1dec-11e2-8cad-001ec947c8cc")
    public ColorizableImageFigure(Image image, Color color) {
        assert (image != null);
        assert (color != null);
        this.setColor(color);
        this.originalImage = image;
    }

    @objid ("7f6b8bce-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void paintFigure(Graphics gc) {
        super.paintFigure(gc);
        Image colorizedImage = colorize(this.originalImage, this.pixelColor);
        org.eclipse.swt.graphics.Rectangle rect = colorizedImage.getBounds();
        Rectangle area = this.getClientArea();
        gc.drawImage(colorizedImage, rect.x, rect.y, rect.width, rect.height, area.x, area.y, area.width, area.height);
        colorizedImage.dispose();
    }

    @objid ("7f6b8bd4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void finalize() throws Throwable {
        this.originalImage = null;
        this.color = null;
        
        super.finalize();
    }

    @objid ("7f6b8bd7-1dec-11e2-8cad-001ec947c8cc")
    private Image colorize(Image original, int apixelColor) {
        if (System.getProperty("os.name").equals("Linux")) {
            // dumpImage("original image", original);
            ImageData data = original.getImageData();
            ImageData imageData = new ImageData(data.width, data.height, data.depth, data.palette);
            for (int y = 0; y < imageData.height; y++) {
                for (int x = 0; x < imageData.width; x++) {
                    imageData.setPixel(x, y, apixelColor);
                    imageData.setAlpha(x, y, data.getAlpha(x, y));
                }
            }
            Image image = new Image(original.getDevice(), imageData);
            // dumpImage("new image", image);
            return image;
        } else { // Assume it's windows
            Image image = new Image(this.originalImage.getDevice(), this.originalImage.getImageData());
            GC gc = new GC(image);
            gc.setBackground(this.color);
            // gc.setXORMode(true);
            gc.fillRectangle(image.getBounds());
            gc.dispose();
            return image;
        }
    }

    /**
     * @return the color applied on the image.
     */
    @objid ("7f6b8bdb-1dec-11e2-8cad-001ec947c8cc")
    public Color getColor() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.color;
    }

    /**
     * @param color the color to appply.
     */
    @objid ("7f6b8bdf-1dec-11e2-8cad-001ec947c8cc")
    public void setColor(final Color color) {
        this.color = color;
        this.pixelColor = (color.getRed() << 16) + (color.getGreen() << 8) + color.getBlue();
        // System.out.println("color = " + color);
        // System.out.printf("pixelcolor = %x \n", pixelColor);
    }

    @objid ("d4eee5b8-51f3-400e-93d5-03194782aff5")
    private static void dumpImage(String title, Image img) {
        System.out.println(title);
        
        ImageData data = img.getImageData();
        System.out.printf(" h=%d, w=%d, d=%d bits per pixel\n", data.height, data.width, data.depth);
        
        System.out.printf(" alpha: %d\n", data.alpha);
        System.out.printf(" bytesPerLine: %d\n", data.bytesPerLine);
        System.out.printf(" delayTime: %d\n", data.delayTime);
        
        System.out.printf(" maskPad: %d\n", data.maskPad);
        System.out.printf(" scanLinePad: %d\n", data.scanlinePad);
        System.out.printf(" transparentPixel: %d\n", data.transparentPixel);
        System.out.printf(" type: %d\n", data.type);
        System.out.printf(" data: %s %d bytes\n", data.data, (data.data != null) ? data.data.length : -1);
        System.out.printf(" alphaData: %s %d bytes\n", data.alphaData, (data.alphaData != null) ? data.alphaData.length : -1);
        System.out.printf(" maskData: %s %d bytes \n", data.maskData, (data.maskData != null) ? data.maskData.length : -1);
        System.out.printf(" palette: %s\n", data.palette);
        
        System.out.println();
    }

    @objid ("0979be81-578d-496b-9b3e-39b6b32ec394")
    public Image getOriginalImage() {
        return this.originalImage;
    }

}
