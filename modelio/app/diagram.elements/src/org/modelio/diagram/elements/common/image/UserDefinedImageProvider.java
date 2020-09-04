/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.elements.common.image;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Image provider that reads the {userDiagramImage} tagged value on the model element.
 * 
 * @author cmarin
 * @since 3.4
 */
@objid ("24f960de-f036-40d1-9b16-e729c2a92f9d")
public class UserDefinedImageProvider implements IImageableNode {
    @objid ("8f9a7b58-3128-4608-97e3-c32e8f2fab93")
    private GmModel model;

    @objid ("7e7a4449-0ae1-4680-b7a3-e5a0ccec7a43")
    private ImageRegistry imageRegistry;

    /**
     * @param model the graphic model
     * @param imageRegistry the image registry.
     */
    @objid ("9f65a2f6-943a-402f-9792-e1bf78f06b39")
    public UserDefinedImageProvider(GmModel model, ImageRegistry imageRegistry) {
        this.model = model;
        this.imageRegistry = imageRegistry;
    }

    @objid ("cfcdfdb9-c256-4fb8-b7a2-77aa6c51eb9d")
    @Override
    public Image getImage() {
        MObject mobj = this.model.getRelatedElement();
        if (mobj instanceof ModelElement) {
            ModelElement el = (ModelElement) mobj;
            String imPath = el.getTagValue("ModelerModule", "userDiagramImage");
        
            Image image = this.imageRegistry.get(imPath);
            if (image == null) {
                ImageDescriptor descriptor = getImageDescriptor(imPath);
        
                if (descriptor != null) {
                    this.imageRegistry.put(imPath, descriptor);
                    image = this.imageRegistry.get(imPath);
                }
            }
        
            return image;
        }
        return null;
    }

    /**
     * Compute an image descriptor from an image location specification.
     * <p>
     * Are supported: <ul>
     * <li> an absolute file path
     * <li> a file path relative to the project
     * <li> an absolute URL
     * </ul>
     * Returns <i>null</i> if the image is not found.
     * 
     * @param imageSpec an image location specification.
     * @return an image descriptor or <i>null</i>.
     */
    @objid ("ca20d98f-ce16-4b50-a041-e2ad9390165f")
    protected ImageDescriptor getImageDescriptor(String imageSpec) {
        if (imageSpec==null || imageSpec.isEmpty()) {
            return null;
        }
        
        if (new File(imageSpec).isFile()) {
            // The path is absolute
            return ImageDescriptor.createFromFile(null, imageSpec);
        }
        
        // Try relative path
        try {
            Path projPath = this.model.getDiagram().getModelManager().getProjectPath();
            Path apath = projPath.resolve(imageSpec);
            if (Files.isRegularFile(apath)) {
                // The path is relative
                return ImageDescriptor.createFromFile(null, apath.toString());
            }
        } catch (InvalidPathException e) {
            // ignore
        }
        
        // try URL
        try {
            URL url = new URL(imageSpec);
            // no exception, it is an URL
            return ImageDescriptor.createFromURL(url);
        } catch (MalformedURLException e) {
            // Not an URL either
            return null;
        }
    }

}
