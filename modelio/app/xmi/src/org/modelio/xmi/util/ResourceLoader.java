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

package org.modelio.xmi.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.modelio.xmi.plugin.Xmi;
import org.osgi.framework.Bundle;

@objid ("512c4467-8e7e-4bb3-9cbc-4e72b8885201")
public class ResourceLoader {
    @objid ("73aa58a3-0a3b-4215-a922-89e033ddf9d7")
    private String imagesCommonPath = "images" +  java.io.File.separator;

    @objid ("c55d4ff3-c4dc-4df6-9bc9-96c81fc43ac7")
    private final String umlResourcesJarName = "org.eclipse.uml2.uml.resources_3.1.100.v201008191510.jar";

    @objid ("39bf1b8e-e9ac-4e5a-96ba-9e6d560b56d1")
    private String iconCommonPath = "icons" +  java.io.File.separator;

    @objid ("89e1019a-0500-49ca-9381-c8f9d6566dfe")
    private static ResourceLoader INSTANCE = null;

    @objid ("43ca3ec8-cd96-4f31-94ec-72ee3a1fcd1f")
    private ResourceLoader() {
    }

    @objid ("27a2b802-6499-4a7b-86ed-50c8099ee2ba")
    public static ResourceLoader getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ResourceLoader();
        return INSTANCE;
    }

    @objid ("95e8a094-f1c1-43f7-ab94-4fb0f7ef9813")
    public Image getImage(String resourceName) {
        final Bundle bundle = Platform.getBundle(Xmi.PLUGIN_ID);
        
        final URL url = FileLocator.find(bundle, new Path(this.imagesCommonPath + resourceName ), null);
        
        File file = null;
        try {
            file = new File(FileLocator.toFileURL(url).getPath());
            return new Image(Display.getDefault(), file.getAbsolutePath());
        } catch (IOException e) {
            Xmi.LOG.error(Xmi.PLUGIN_ID, e);       
        }
        return null;
    }

    @objid ("d483f4bb-b2bf-42b4-82cf-ae1bb1655cea")
    public File getResource(final String resourceName) {
        final Bundle bundle = Platform.getBundle(Xmi.PLUGIN_ID);
        
        final URL url = FileLocator.find(bundle, new Path("res"+ java.io.File.separator + resourceName ), null);
        
        File file = null;
        try {
            file = new File(FileLocator.toFileURL(url).getPath());
        } catch (IOException e) {
            Xmi.LOG.error(Xmi.PLUGIN_ID, e);       
        }
        return file;
    }

    @objid ("1ea8febf-fa54-491a-a78e-d15cfd8b71a4")
    public Image getIcon(String resourceName) {
        final Bundle bundle = Platform.getBundle(Xmi.PLUGIN_ID);
        
        final URL url = FileLocator.find(bundle, new Path(this.iconCommonPath + resourceName ), null);
        
        File file = null;
        try {
            file = new File(FileLocator.toFileURL(url).getPath());
            return new Image(Display.getDefault(), file.getAbsolutePath());
        } catch (IOException e) {
            Xmi.LOG.error(Xmi.PLUGIN_ID, e);       
        }
        return null;
    }

}
