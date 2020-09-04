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

package org.modelio.diagram.editor.usecase.plugin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.osgi.framework.Bundle;

@objid ("5e91e78a-55b7-11e2-877f-002564c97630")
public class UseCaseSharedImages implements ISharedImages {
    @objid ("3e01b62e-13a5-491c-ad55-00df78edc773")
    private ImageRegistry imageRegistry;

    @objid ("5e91e78d-55b7-11e2-877f-002564c97630")
    UseCaseSharedImages() {
        initializeImageRegistry();
    }

    @objid ("5e91e790-55b7-11e2-877f-002564c97630")
    @Override
    public final Image getImage(String key) {
        return this.imageRegistry.get(key);
    }

    @objid ("5e91e796-55b7-11e2-877f-002564c97630")
    @Override
    public final ImageDescriptor getImageDescriptor(String key) {
        return this.imageRegistry.getDescriptor(key);
    }

    @objid ("5e91e79c-55b7-11e2-877f-002564c97630")
    private void declareImage(String key, ImageDescriptor descriptor) {
        this.imageRegistry.put(key, descriptor);
    }

    @objid ("5e91e7a0-55b7-11e2-877f-002564c97630")
    private void initializeImageRegistry() {
        this.imageRegistry = new ImageRegistry(Display.getDefault());
        this.imageRegistry.put("MissingImage", ImageDescriptor.getMissingImageDescriptor());
        
        Bundle bundle = Platform.getBundle(DiagramEditorUseCase.PLUGIN_ID);
        
        ImageDescriptor image = ImageDescriptor.createFromURL(FileLocator.find(bundle,
                                                                               new Path("images/actor64x64.png"),
                                                                               null));
        declareImage(Actor.class.getName(), image);
    }

}
