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

package org.modelio.platform.model.ui.swt.images;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextLayout;
import org.modelio.platform.core.metamodel.MetamodelExtensionPoint;
import org.modelio.platform.model.ui.swt.images.spi.IMetamodelImageProvider;
import org.modelio.platform.ui.CoreFontRegistry;
import org.modelio.platform.ui.UIImages;
import org.modelio.platform.ui.swt.QualifiedImage;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * The MetamodelImageService provides icons and images for metaclasses. The returned icons (or images) only represents the metaclass (no additional decorations).
 * 
 * In some particular cases, a single metaclass can have different icons. In this case, use the 'flavor' parameter to distinguish between them (otherwise use null as 'flavor' value or call the methods without 'flavor' parameter).
 * 
 * @author phv
 */
@objid ("002642e2-5f24-100d-835d-001ec947cd2a")
public class MetamodelImageService {
    @objid ("0093f13e-a43d-100e-835d-001ec947cd2a")
    private static final String IMAGES_PATH = "icons/mmimages/";

    @objid ("00941ee8-a43d-100e-835d-001ec947cd2a")
    private static final String IMAGES_EXTENSION = ".png";

    /**
     * Identifier of the eclipse extension point.
     * <p>
     * See plugin.xml file
     * </p>
     */
    @objid ("42b9d6d8-08d8-427f-abbc-866fb1349320")
    private static final String EXTENSION_POINT_ID = "org.modelio.platform.model.ui.mclass.imageprovider";

    /**
     * Image registry for 'unknown' images
     */
    @objid ("003a53fe-6af9-100d-835d-001ec947cd2a")
    private static final ImageRegistry REGISTRY = new ImageRegistry();

    /**
     * Metamodel image providers contributed to the eclipse extension point.
     */
    @objid ("aad7659c-b043-4825-90fe-6ac0eb82128c")
    private static final MetamodelExtensionPoint<IMetamodelImageProvider> EXTENSIONPOINT = new MetamodelExtensionPoint<>(MetamodelImageService.EXTENSION_POINT_ID);

    /**
     * Get the icon for a metaclass and a flavor
     * 
     * @param metaclass a metaclass
     * @param flavor a flavor to concatenate to the lookup key.
     * @return the found icon or a default 'unknown' icon.
     */
    @objid ("003a5c1e-6af9-100d-835d-001ec947cd2a")
    public static Image getIcon(MClass metaclass, String flavor) {
        QualifiedImage qualifiedIcon = getQualifiedIcon(metaclass, flavor);
        return qualifiedIcon != null ? qualifiedIcon.getImage() : null;
    }

    /**
     * Get the diagram big image for a metaclass and a flavor.
     * 
     * @param metaclass a metaclass
     * @param flavor a flavor to concatenate to the lookup key.
     * @return the found image or a default 'unknown' image.
     */
    @objid ("003aa3f4-6af9-100d-835d-001ec947cd2a")
    public static Image getImage(MClass metaclass, String flavor) {
        QualifiedImage qualifiedImage = getQualifiedImage(metaclass, flavor);
        return qualifiedImage != null ? qualifiedImage.getImage() : null;
    }

    /**
     * Get the icon for a metaclass.
     * 
     * @param metaclassName a metaclass name.
     * @return the metaclass icon.
     * @deprecated this method is not compatible with metamodel extensions, use {@link #getIcon(MClass)} instead.
     */
    @objid ("00526976-030e-106c-bf4f-001ec947cd2a")
    @Deprecated
    public static Image getIcon(final String metaclassName) {
        // first, ask every extension
        for (IMetamodelImageProvider svc : MetamodelImageService.EXTENSIONPOINT.getAll()) {
            QualifiedImage icon = svc.getIcon(metaclassName);
            if (icon != null) {
                return icon.getImage();
            }
        }
        return null;
    }

    /**
     * Get the icon for a metaclass
     * 
     * @param metaclass a metaclass
     * @return the metaclass icon.
     */
    @objid ("42a5c0d5-2fce-4b43-b119-33a571e21d50")
    public static Image getIcon(MClass metaclass) {
        return getIcon(metaclass, null);
    }

    /**
     * Get the diagram big image for a metaclass
     * 
     * @param metaclass a metaclass
     * @return the diagram image
     */
    @objid ("1e2b1658-b4c5-488a-ab8c-008d3126d214")
    public static Image getImage(MClass metaclass) {
        return getImage(metaclass, null);
    }

    /**
     * Get the full physical path of a metaclass icon.
     * <p>
     * Usually needed when programmatically building e4 contributions.
     * </p>
     * 
     * @param metaclass a metaclass
     * @return path to the found icon or <code>null</code>.
     * @since 3.8
     */
    @objid ("98d64cb3-d296-442b-8c53-8fbabba53c50")
    public static String getIconCompletePath(MClass metaclass) {
        IMetamodelImageProvider svc = MetamodelImageService.EXTENSIONPOINT.get(metaclass.getOrigin());
        if (svc != null) {
            return svc.getIconCompletePath(metaclass);
        }
        return null;
    }

    /**
     * Get the full physical path of a metaclass icon.
     * <p>
     * Usually needed when programmatically building e4 contributions.
     * </p>
     * 
     * @param metaclassName name of a metaclass
     * @return path to the found icon or <code>null</code>.
     * @since 3.8
     */
    @objid ("abc9a946-ee94-4ae4-998f-c543caba01c5")
    public static String getIconCompletePath(String metaclassName) {
        // first, ask every extension
        for (IMetamodelImageProvider svc : MetamodelImageService.EXTENSIONPOINT.getAll()) {
            String path = svc.getIconCompletePath(metaclassName);
            if (path != null) {
                return path;
            }
        }
        return null;
    }

    /**
     * @return a unique key for the {@link ImageRegistry}.
     */
    @objid ("eb24065b-3a70-40fe-8ca2-05d54022fa6d")
    private static String getRegistryKey(MClass metaclass, String flavor, String suffix) {
        final StringBuilder keyBuffer = new StringBuilder();
        keyBuffer.append(metaclass.getQualifiedName());
        
        if (flavor != null && !flavor.isEmpty()) {
            keyBuffer.append(".");
            keyBuffer.append(flavor);
        }
        
        if (suffix != null && !suffix.isEmpty()) {
            keyBuffer.append(".");
            keyBuffer.append(suffix);
        }
        return keyBuffer.toString();
    }

    /**
     * Compute and record an image displaying the given label for the given unknown key.
     * <p>
     * This method will make best effort to draw the label into the image but it will probably not fit the image.
     * 
     * @param key a key.
     * @param label a string to draw in the image.
     * @param baseImage an existing image to define the size of the unknown image and initialize the {@link Device}.
     * @return the recorded unknown image to use
     */
    @objid ("28a1f130-2ba2-40e6-a19d-58da5cd2aa26")
    private static Image createUnknownImage(String key, String label, Image baseImage) {
        Device device = baseImage.getDevice();
        Image image = new Image(device, baseImage.getBounds());
        
        // Draw in small font the label
        TextLayout tl = new TextLayout(device);
        try {
            tl.setAlignment(SWT.CENTER);
            tl.setWidth(baseImage.getBounds().width);
            tl.setFont(CoreFontRegistry.getModifiedFont(device.getSystemFont(), 0, 0.5f));
            tl.setText(label);
        
            GC gc = new GC(image);
            try {
                tl.draw(gc, 0, 0);
            } finally {
                gc.dispose();
            }
        } finally {
            tl.dispose();
        }
        return image;
    }

    @objid ("8f898cdb-4885-4b3e-8324-17d4f8dfb9c5")
    public static QualifiedImage getQualifiedIcon(MClass metaclass, String flavor) {
        QualifiedImage qualifiedIcon = null;
        IMetamodelImageProvider svc = MetamodelImageService.EXTENSIONPOINT.get(metaclass.getOrigin());
        if (svc != null) {
            qualifiedIcon = svc.getIcon(metaclass, flavor);
        }
        
        if (qualifiedIcon != null && qualifiedIcon.getImage() != null) {
            return qualifiedIcon;
        }
        
        // No image defined by contributors, return an 'unknown' icon instead.
        String key = getRegistryKey(metaclass, flavor, "icon");
        Image icon = MetamodelImageService.REGISTRY.get(key);
        if (icon == null) {
            icon = createUnknownImage(key, metaclass.getName(), UIImages.PLACEHOLDER);
            if (icon != null) {
                MetamodelImageService.REGISTRY.put(key, icon);
            }
        }
        return new QualifiedImage(icon, key);
    }

    @objid ("bad72761-9a8e-4aca-bbdd-51fa8e854334")
    public static QualifiedImage getQualifiedImage(MClass metaclass, String flavor) {
        QualifiedImage qualifiedImage = null;
        IMetamodelImageProvider svc = MetamodelImageService.EXTENSIONPOINT.get(metaclass.getOrigin());
        if (svc != null) {
            qualifiedImage = svc.getImage(metaclass, flavor);
        }
        
        if (qualifiedImage != null && qualifiedImage.getImage() != null) {
            return qualifiedImage;
        }
        
        // No image defined by contributors, return an 'unknown' image instead.
        String key = getRegistryKey(metaclass, flavor, "image");
        Image image = MetamodelImageService.REGISTRY.get(key);
        if (image == null) {
            image = createUnknownImage(key, metaclass.getName(), UIImages.PLACEHOLDER_48);
            if (image != null) {
                MetamodelImageService.REGISTRY.put(key, image);
            }
        }
        return new QualifiedImage(image, key);
    }

}
