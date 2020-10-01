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

package org.modelio.platform.ui.swt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

/**
 * Custom clipboard transfer to work around SWT bug 283960 that make copy image to clipboard not working on Linux 64.
 * 
 * @see https://bugs.eclipse.org/bugs/show_bug.cgi?id=283960
 * @see https://mantis.softeam.com/view.php?id=13256
 * @author cma
 * @since 3.7
 */
@objid ("2e5069b1-c840-41f7-a0b5-1d463a31d313")
public class PngTransfer extends ByteArrayTransfer {
    @objid ("387b04a2-05b7-4616-9542-44b630f04d65")
    private static final String IMAGE_PNG = "image/png";

    @objid ("344dfff1-5565-47f4-9d9d-60f62e9704bf")
    private static final int ID = registerType(IMAGE_PNG);

    @objid ("e0c2e601-e60e-4f3f-8552-3ef589cc487b")
    private static PngTransfer _instance = new PngTransfer();

    @objid ("b9e99bd4-1ec2-4586-a0f4-b08c729e3327")
    private PngTransfer() {
    }

    @objid ("2ba408e6-9de7-490c-bc16-1a8ba4d3d5aa")
    public static PngTransfer getInstance() {
        return _instance;
    }

    @objid ("46f11465-d2ce-4446-8f35-e840f3bfd9ea")
    @Override
    protected String[] getTypeNames() {
        return new String[]{IMAGE_PNG};
    }

    @objid ("49c9ac71-3202-4388-a753-94f76f26288a")
    @Override
    protected int[] getTypeIds() {
        return new int[]{ID};
    }

    @objid ("5ccb63a4-af27-4c80-b4b9-2c8b6d2e9f13")
    @Override
    protected void javaToNative(Object object, TransferData transferData) {
        if (object == null || !(object instanceof ImageData)) {
            return;
        }
        
         if (isSupportedType(transferData)) {
             ImageData image = (ImageData) object;
             try (ByteArrayOutputStream out = new ByteArrayOutputStream();){
                 // write data to a byte array and then ask super to convert to pMedium
                 
                ImageLoader imgLoader = new ImageLoader();
                imgLoader.data = new ImageData[] { image };
                imgLoader.save(out, SWT.IMAGE_PNG);
        
                 byte[] buffer = out.toByteArray();
                 out.close();
        
                 super.javaToNative(buffer, transferData);
             } catch (IOException e) {
                 throw new UncheckedIOException(e);
             }
         }
    }

    @objid ("9be531ef-d7f6-440e-8d87-24ecbf287231")
    @Override
    protected Object nativeToJava(TransferData transferData) {
        if (isSupportedType(transferData)) {
        
             byte[] buffer = (byte[])super.nativeToJava(transferData);
             if (buffer == null) {
                return null;
            }
        
             try (ByteArrayInputStream in = new ByteArrayInputStream(buffer)){
                return new ImageData(in);
             } catch (IOException e) {
                 throw new UncheckedIOException(e);
            }
        }
        return null;
    }

}
