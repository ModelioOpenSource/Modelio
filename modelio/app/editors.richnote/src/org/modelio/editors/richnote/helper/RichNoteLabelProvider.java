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

package org.modelio.editors.richnote.helper;

import java.net.URI;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.program.Program;
import org.modelio.editors.richnote.api.RichNoteFormat;
import org.modelio.editors.richnote.api.RichNoteFormatRegistry;
import org.modelio.mda.infra.ModuleI18NService;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ResourceType;

/**
 * Helper class to get the label and the icon of an {@link ExternDocument}.
 */
@objid ("a16dd783-ab5a-4516-918f-ed92ff83f273")
public class RichNoteLabelProvider {
    @objid ("56a5ec69-2a6f-40c9-8f18-99853cc63e3a")
    private static final ImageRegistry REGISTRY = new ImageRegistry();

    @objid ("d8aee85c-1451-4173-ba84-d22f908de772")
    private RichNoteLabelProvider() {
        // no instance
        throw new UnsupportedOperationException();
    }

    /**
     * Get the explorer icon of a resource.
     * <p>
     * The returned image is owned by a registry, may be used elsewhere and must <b>not</b> be disposed.
     * </p>
     * @param resource a resource.
     * @return an icon. Might be <code>null</code> if no icon is found for the resource's mime type.
     */
    @objid ("e5c8589a-eec4-4be3-8624-5a61c781331d")
    public static Image getIcon(final AbstractResource resource) {
        String mimeType = resource.getMimeType();
        RichNoteFormat format = RichNoteFormatRegistry.getInstance().getDocumentFormatForMime(mimeType);
        
        if (format != null) {
            return format.getIcon();
        } else {
            Image icon = RichNoteLabelProvider.REGISTRY.get(mimeType);
            if (icon == null) {
                try {
                    URI location = resource.getHandle().getLocation();
                    if (location != null) {
                        // External document, look at the location
                        RichNoteLabelProvider.REGISTRY.put(mimeType, RichNoteLabelProvider.getIcon(Paths.get(location)));
                        icon = RichNoteLabelProvider.REGISTRY.get(mimeType);
                    } else {
                        // Embedded document, try with the mime type
                        final int idx = mimeType.lastIndexOf('/');
                        if (idx != -1) {
                            RichNoteLabelProvider.REGISTRY.put(mimeType, RichNoteLabelProvider.getIcon("." + mimeType.substring(idx + 1)));
                        }
                    }
                } catch (IllegalArgumentException | FileSystemNotFoundException | SecurityException | NullPointerException e) {
                    icon = null;
                }
            }
            return icon;
        }
    }

    /**
     * Get the Windows explorer icon descriptor for a file.
     * <p>
     * Return <code>null</code> if the file has no registered extension or is a directory.
     * @param aFile a file.
     * @return the icon descriptor or <code>null</code>.
     */
    @objid ("3110b801-c154-4c5d-8634-9ed7682f89ca")
    private static ImageDescriptor getIcon(final Path aFile) {
        final String fname = aFile.getFileName().toString();
        final int idx = fname.lastIndexOf('.');
        
        if (idx != -1) {
            String extension = fname.substring(idx);
            return getIcon(extension);
        } else {
            return null;
        }
    }

    /**
     * Get the label to display for an extern document.
     * @param document a document.
     * @return the label.
     */
    @objid ("f6d6a31e-97aa-49bd-b7ac-1d9f5921c8c5")
    public static String getLabel(final Document document) {
        String name = document.getName();
        
        final ResourceType type = document.getType();
        if (type != null) {
            if (!name.isEmpty()) {
                name += " ";
            }
        
            final String label = ModuleI18NService.getLabel(type);
            if (!label.isEmpty()) {
                return name + "[" + label + "]";
            } else {
                return name + "[" + type.getName() + "]";
            }
        }
        return name;
    }

    @objid ("2507a3d6-38d1-457b-9e6b-3e5644bff4eb")
    protected static ImageDescriptor getIcon(String extension) {
        Program program = Program.findProgram(extension);
        if (program != null) {
            ImageData data = program.getImageData();
            if (data != null) {
                return ImageDescriptor.createFromImageData(data);
            }
        }
        return null;
    }

}
