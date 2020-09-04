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

package org.modelio.core.ui.swt.images;

import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.plugin.CoreUi;
import org.modelio.core.ui.swt.images.spi.IElementImageProvider;
import org.modelio.core.ui.swt.images.spi.IMetamodelImageProvider;
import org.modelio.ui.swt.QualifiedImage;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default image provider with UML elements for the model browser.
 */
@objid ("54e1d10e-4528-4795-9b44-8939c30a6ab5")
public class InfrastructureElementImageProvider implements IElementImageProvider, IMetamodelImageProvider {
    @objid ("6245b849-cb64-4999-ac8d-560e04279076")
    private static final String IMAGES_PATH = "mmimages/";

    @objid ("673b016c-1b2d-4433-8fac-c2b9f161c203")
    private static final String IMAGES_EXTENSION = ".png";

    @objid ("ad7d7a8d-b18e-485d-9154-112708580fb4")
    private static final ImageRegistry REGISTRY = new ImageRegistry();

    @objid ("3f0ecbe8-647c-4067-b033-55834dadcb57")
    @Override
    public QualifiedImage getIcon(MObject element) {
        return getIcon(element.getMClass(), getFlavor(element));
    }

    /**
     * Get the icon for a metaclass and a flavor
     * 
     * @param metaclass a metaclass
     * @param flavor a flavor to concatenate to the lookup key.
     * @return the found icon or a default unknown icon.
     */
    @objid ("30cf3d70-5da3-4e2b-b464-ec0354cf28f6")
    @Override
    public QualifiedImage getIcon(MClass metaclass, String flavor) {
        // since 3.8.1 ALWAYS get metaclass icons by a qualified name
        return getImageFromKey(getIconKey(metaclass, flavor));
    }

    @objid ("b202e11e-8749-4463-9c5c-b16a599e2c41")
    @Override
    public QualifiedImage getImage(MObject element) {
        return getImage(element.getMClass(), getFlavor(element));
    }

    /**
     * Get the diagram big image for a metaclass and a flavor.
     * 
     * @param metaclass a metaclass
     * @param flavor a flavor to concatenate to the lookup key.
     * @return the found image or null.
     */
    @objid ("4488c7a2-61ba-43a2-86fc-70b9c50a568f")
    @Override
    public QualifiedImage getImage(MClass metaclass, String flavor) {
        final String key = getImageKey(metaclass, flavor);
        return getImageFromKey(key);
    }

    /**
     * @return a flavor to concatenate to the lookup key. Might be <code>null</code>.
     */
    @objid ("ab04ba2c-e9fa-478d-bce3-2da76a3b2873")
    private String getFlavor(@SuppressWarnings ("unused") MObject element) {
        return null;
    }

    @objid ("01c155e3-1d37-446b-8f64-645ba72317f1")
    private String getImageKey(MClass metaclass, String flavor) {
        return getIconKey(metaclass, flavor) + ".image";
    }

    @objid ("ad8224c8-b79e-4570-ace8-094607beb9c8")
    private String getIconKey(MClass metaclass, String flavor) {
        final String className = metaclass.getQualifiedName();
        if (flavor == null || flavor.isEmpty()) {
            return className;
        } else {
            final StringBuilder keyBuffer = new StringBuilder(className.length() + flavor.length() + 1);
            keyBuffer.append(className);
            keyBuffer.append(".");
            keyBuffer.append(flavor);
            return keyBuffer.toString();
        }
    }

    @objid ("5fd4ab28-abdb-403b-89e6-94aede9478d3")
    private Image loadImage(String key) {
        final IPath imagePath = new Path(getIconPath(key));
        URL url = FileLocator.find(CoreUi.getContext().getBundle(), imagePath, null);
        if (url != null) {
            ImageDescriptor desc = ImageDescriptor.createFromURL(url);
            return desc.createImage();
        }
        return null;
    }

    /**
     * Get the relative file name from the current plugin.
     */
    @objid ("05b6adec-f625-4ef7-9fba-2d39773aa920")
    private String getIconPath(String key) {
        final StringBuilder path = new StringBuilder(InfrastructureElementImageProvider.IMAGES_PATH);
        path.append(key.toLowerCase());
        path.append(InfrastructureElementImageProvider.IMAGES_EXTENSION);
        return path.toString();
    }

    /**
     * Get the icon for a metaclass.
     * 
     * @param metaclassName a metaclass name.
     * @return the metaclass icon.
     */
    @objid ("bb19d275-645c-4596-8501-08234dae6f21")
    @Override
    public QualifiedImage getIcon(final String metaclassName) {
        return getImageFromKey(metaclassName);
    }

    /**
     * Get the image for a given key.
     * <p>
     * The image is loaded into the registry if not yet done.
     * @return
     */
    @objid ("0ab937a1-a937-4b94-8603-595a64771251")
    private QualifiedImage getImageFromKey(final String key) {
        Image image = InfrastructureElementImageProvider.REGISTRY.get(key);
        if (image == null) {
            image = loadImage(key);
            if (image != null) {
                InfrastructureElementImageProvider.REGISTRY.put(key, image);
            } else {
                return null;
            }
        }
        // Image is not null
        return new QualifiedImage(image, key);
    }

    @objid ("5cf9493e-c699-4def-aa02-e4f560749633")
    @Override
    public String getIconCompletePath(String metaclassName) {
        String iconPath = getIconPath(metaclassName);
        final IPath imagePath = new Path(iconPath);
        URL url = FileLocator.find(CoreUi.getContext().getBundle(), imagePath, null);
        if (url != null) {
            StringBuilder ret = new StringBuilder();
            ret.append("platform:/plugin/");
            ret.append(CoreUi.PLUGIN_ID);
            ret.append("/");
            ret.append(iconPath);
            return ret.toString();
        } else {
            return null;
        }
    }

    @objid ("5d288c1c-899c-433b-aedf-086364532117")
    @Override
    public String getIconCompletePath(MClass metaclass) {
        final String key = getIconKey(metaclass, null);
        return getIconCompletePath(key);
    }

}
