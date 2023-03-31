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

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * A colorizable figure based on the alpha mask of a png file. The drawn colorized image is build by applying the alpha mask of the
 * original image on a filled colored rectangle of the same size as the image.<br>
 * This class does not manage the lifecycle of the image, this is left to the caller.
 */
@objid ("7f6b8bc1-1dec-11e2-8cad-001ec947c8cc")
public class ColorizableImageFigure extends Figure {
    @objid ("51434a59-e443-4639-a37d-31a6cb6bbc28")
    private Image originalImage;

    @objid ("fa5692a3-a015-4c75-a5a5-9ddb50148b9d")
    private RGB color;

    /**
     * Registry of all computed colorized images.
     * <p>
     * TODO : this registry should be owned by the diagram and passed to the figure constructor.
     * 
     * @author cmarin
     * @since Alouette 5.2.?
     */
    @objid ("1b201e63-7779-484b-895c-5a09c7360a18")
    protected static final LocalResourceManager colorizedImages = new LocalResourceManager(JFaceResources.getResources());

    /**
     * Creates an image colored in black.
     * @param image the image
     */
    @objid ("7f6b8bc7-1dec-11e2-8cad-001ec947c8cc")
    public  ColorizableImageFigure(Image image) {
        this(image, Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
    }

    /**
     * Constructor.
     * @param image the image
     * @param color the color to apply
     */
    @objid ("7f6b8bca-1dec-11e2-8cad-001ec947c8cc")
    public  ColorizableImageFigure(Image image, Color color) {
        assert (image != null);
        assert (color != null);
        this.setColor(color);
        this.originalImage = image;
        
    }

    @objid ("7f6b8bce-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void paintFigure(Graphics gc) {
        super.paintFigure(gc);
        
        if (false) {
            // This code computes a new Image resource each time
            ImageData imageData = createColorizedImageData(this.originalImage, this.color);
            Image colorizedImage = new Image(this.originalImage.getDevice(), imageData);
            try {
                org.eclipse.swt.graphics.Rectangle rect = colorizedImage.getBounds();
                Rectangle area = this.getClientArea();
                gc.drawImage(colorizedImage, rect.x, rect.y, rect.width, rect.height, area.x, area.y, area.width, area.height);
            } finally {
                colorizedImage.dispose();
            }
        } else {
            Image colorizedImage = (Image) colorizedImages.get(new ColorizedImageDescriptor(this.originalImage, this.color));
        
            org.eclipse.swt.graphics.Rectangle rect = colorizedImage.getBounds();
            Rectangle area = this.getClientArea();
            gc.drawImage(colorizedImage, rect.x, rect.y, rect.width, rect.height, area.x, area.y, area.width, area.height);
        
        }
        
    }

    @objid ("7f6b8bd4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void finalize() throws Throwable {
        this.originalImage = null;
        this.color = null;
        
        super.finalize();
        
    }

    /**
     * Produces a colorized version of an image.
     * <p>
     * The image must have an alpha layer in order to work.
     * @param original the original image
     * @param aColor the color to use
     * @return the colorized image data.
     */
    @objid ("49a842be-ad43-4f7b-97b5-8e0f1ca17ff4")
    public static ImageData createColorizedImageData(Image original, RGB aColor) {
        ImageData data = original.getImageData();
        ImageData imageData = new ImageData(data.width, data.height, data.depth, data.palette);
        int apixelColor = data.palette.getPixel(aColor);
        
        for (int y = 0; y < imageData.height; y++) {
            for (int x = 0; x < imageData.width; x++) {
                imageData.setPixel(x, y, apixelColor);
                imageData.setAlpha(x, y, data.getAlpha(x, y));
            }
        }
        return imageData;
    }

    /**
     * @return the color applied on the image.
     */
    @objid ("7f6b8bdb-1dec-11e2-8cad-001ec947c8cc")
    public RGB getColor() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.color;
    }

    /**
     * @param color the color to appply.
     */
    @objid ("7f6b8bdf-1dec-11e2-8cad-001ec947c8cc")
    public void setColor(final Color color) {
        this.color = color.getRGB();
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

    /**
     * ImageDescriptor that produces a colorized version of an original image.
     * @author cmarin
     * @since Alouette 5.2.?
     */
    @objid ("0237f583-dbaf-40d7-b5b9-671240dd086c")
    public static class ColorizedImageDescriptor extends ImageDescriptor {
        @objid ("10abab85-e0c5-4912-832d-45d11a721c6e")
        private final Image original;

        @objid ("a598d3b9-32ae-48a1-b1a9-2c1a1703c0a5")
        private final RGB color;

        /**
         * @param original the original image
         * @param color the wanted color
         */
        @objid ("f729ec64-31d8-49d7-807e-22edc9cc815a")
        public  ColorizedImageDescriptor(Image original, RGB color) {
            super();
            this.original = original;
            this.color = color;
            
        }

        @objid ("d6b70492-8d8d-4327-9ec8-dd455e8dee08")
        @Override
        public int hashCode() {
            return Objects.hash(this.color, this.original);
        }

        @objid ("5a1c9f42-b279-45d2-91ec-8b72f688289f")
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            ColorizedImageDescriptor other = (ColorizedImageDescriptor) obj;
            return Objects.equals(this.color, other.color) && Objects.equals(this.original, other.original);
        }

        @objid ("57ee0324-bb0c-4187-8887-f8c05618a67c")
        @Override
        public ImageData getImageData(int zoom) {
            if (zoom != 100 )
                return null;
            
            if (this.original==null || this.original.isDisposed())
                return ImageDescriptor.DEFAULT_IMAGE_DATA;
            return createColorizedImageData(this.original, this.color);
        }

    }

}
