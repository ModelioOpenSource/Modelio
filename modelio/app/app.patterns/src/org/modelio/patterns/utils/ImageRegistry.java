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
package org.modelio.patterns.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.modelio.patterns.plugin.Patterns;

/**
 * Image registry
 */
@objid ("8f084e1e-4512-4b36-8d04-a7b1a8ac3a9e")
public class ImageRegistry {
    @objid ("32d9af4f-f086-4a3a-bab5-621cf651ba98")
    private static final int MISSING_IMAGE_SIZE = 10;

    /**
     * Internal value.
     */
    @objid ("9b17a198-b46e-4970-91a1-a0764377ffcb")
    protected static final int LAST_CORNER_KEY = 5;

    /**
     * Maps image paths to images.
     */
    @objid ("600c9b4f-7134-4429-9a2c-5aa5870dc568")
    private static Map<String, Image> mImageMap = new HashMap<>();

    /**
     * Returns an {@link Image} encoded by the specified {@link InputStream}.
     * @param stream the {@link InputStream} encoding the image data
     * @return the {@link Image} encoded by the specified input stream
     */
    @objid ("b4dc7238-0bd8-4b78-8be6-28db528a47f8")
    protected static Image getImage(InputStream stream) {
        Display display = Display.getDefault();
        ImageData data = new ImageData(stream);
        if (data.transparentPixel > 0) {
            return new Image(display, data, data.getTransparencyMask());
        }
        return new Image(display, data);
    }

    /**
     * Returns an {@link Image} stored in the file at the specified path.
     * @param path the path to the image file
     * @return the {@link Image} stored in the file at the specified path
     */
    @objid ("5f87e31c-0294-4040-b5a4-8cc6aa4a765d")
    public static Image getImage(Path path) {
        Image image = mImageMap.get(path.toString());
        if (image == null) {
            try {
                if (Files.exists(path)) {
                    Path tmpFile = Files.createTempFile("img", "");
                    Files.copy(path, tmpFile, StandardCopyOption.REPLACE_EXISTING);
                    try (FileInputStream is = new FileInputStream(tmpFile.toFile())) {
                        image = getImage(is);
                        is.close();
                    }
                } else {
                    final ImageDescriptor descriptor = Patterns.getImageDescriptor(path.toString());
                    image = descriptor.createImage();
                }
                mImageMap.put(path.toString(), image);
            } catch (IOException e) {
                image = getMissingImage();
                mImageMap.put(path.toString(), image);
            }
        }
        return image;
    }

    /**
     * @return the small {@link Image} that can be used as placeholder for
     * missing image.
     */
    @objid ("0ed7082f-e4dc-4348-a50b-b8c2b53d0868")
    private static Image getMissingImage() {
        Image image = new Image(Display.getDefault(), MISSING_IMAGE_SIZE, MISSING_IMAGE_SIZE);
        GC gc = new GC(image);
        gc.setBackground(ColorConstants.red);
        gc.fillRectangle(0, 0, MISSING_IMAGE_SIZE, MISSING_IMAGE_SIZE);
        gc.dispose();
        return image;
    }

    /**
     * Returns an {@link Image} stored in the file at the specified path.
     * @param path The file path as a string
     * @return the {@link Image} stored in the file at the specified path
     */
    @objid ("2d05a174-9ac1-4534-8e57-da730dd94bed")
    public static Image getImage(String path) {
        Image image = mImageMap.get(path);
        if (image == null) {
            try {
                if (new File(path).exists()) {
                    try (FileInputStream is = new FileInputStream(path)) {
                        image = getImage(is);
                        is.close();
                    }
                } else {
                    final ImageDescriptor descriptor = Patterns.getImageDescriptor(path);
                    image = descriptor.createImage();
                }
                mImageMap.put(path, image);
            } catch (IOException e) {
                image = getMissingImage();
                mImageMap.put(path, image);
            }
        }
        return image;
    }

}
