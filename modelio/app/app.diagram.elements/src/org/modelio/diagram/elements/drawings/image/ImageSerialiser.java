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
package org.modelio.diagram.elements.drawings.image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

@objid ("060f7697-5b70-4eef-a548-a6ccc12a97d1")
public class ImageSerialiser {
    @objid ("d2b0c907-9011-463d-8ea9-9848c38138e4")
    public static String serialise(ImageData data) {
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.data = new ImageData[] { data };
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        imageLoader.save(out, SWT.IMAGE_PNG);
        return Base64.getEncoder().encodeToString(out.toByteArray());
    }

    @objid ("cf1f85b2-30b3-4f2f-867c-eddb716f7311")
    public static ImageData deserialise(String imageString) {
        byte[] decode = Base64.getDecoder().decode(imageString.getBytes());
        try(InputStream is = new ByteArrayInputStream(decode)) {
            return new ImageData(is);
        } catch (IOException e) {
          return null;
        }
        
    }

}
