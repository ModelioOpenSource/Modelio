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
package org.modelio.platform.model.ui.swt.labelprovider;

import java.net.URL;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.modelio.platform.model.ui.plugin.CoreUi;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Model container that contain link elements.
 */
@objid ("6e3a67f9-dad6-43d9-8c7d-c66baaa6f8be")
public class LinkContainer extends AbstractContainer<MObject> {
    @objid ("8c7474f0-4122-4856-ac32-0e0c0cda18ef")
    private static final Image LINK_CONTAINER = LinkContainer.loadImage("icons/links.png");

    /**
     * C'tor.
     */
    @objid ("baaed270-4d47-4b76-9231-77b3d6bbc0b0")
    public  LinkContainer(MObject element, List<MObject> archives) {
        super(element, archives);
        
        setIcon(LinkContainer.LINK_CONTAINER);
        
    }

    @objid ("05374c68-5685-4254-abb4-aeef05127e96")
    protected static Image loadImage(String imageFilePath) {
        ImageDescriptor desc = null;
        Image image = null;
        
        // Get the relative file name
        final IPath imagePath = new Path(imageFilePath);
        final URL url = FileLocator.find(CoreUi.getContext().getBundle(), imagePath, null);
        assert url != null : imagePath + " not found in " + CoreUi.getContext().getBundle();
        
        if (url != null) {
            desc = ImageDescriptor.createFromURL(url);
            image = desc.createImage();
            assert image != null;
        }
        return image;
    }

    @objid ("c466a338-be28-4656-b898-4e61aca832b3")
    @Override
    public String getLabel() {
        final int nbLinks = getContents().size();
        return (nbLinks <= 1 ? CoreUi.I18N.getMessage("LinkContainer.Single", nbLinks) : CoreUi.I18N.getMessage("LinkContainer.Multi", nbLinks));
    }

}
