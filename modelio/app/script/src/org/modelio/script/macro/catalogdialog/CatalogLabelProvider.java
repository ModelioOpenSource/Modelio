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

package org.modelio.script.macro.catalogdialog;

import java.net.MalformedURLException;
import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.modelio.script.macro.catalog.Catalog;
import org.modelio.script.macro.catalog.Macro;
import org.modelio.script.plugin.Script;
import org.osgi.framework.Bundle;

@objid ("006960ea-c497-106a-bf4f-001ec947cd2a")
class CatalogLabelProvider extends LabelProvider {
    @objid ("006a8d9e-c497-106a-bf4f-001ec947cd2a")
    private static final String CATALOGID = "catalog";

    @objid ("00360f24-f1bd-106a-bf4f-001ec947cd2a")
    private final ImageRegistry images;

    @objid ("006a8fa6-c497-106a-bf4f-001ec947cd2a")
    @Override
    public void dispose() {
        this.images.dispose();
    }

    @objid ("006999fc-c497-106a-bf4f-001ec947cd2a")
    @Override
    public Image getImage(Object element) {
        if (element == null) {
            return null;
        } else if (element instanceof Catalog) {
            return this.images.get(CATALOGID);
        } else if (element instanceof Macro) {
            Macro s = (Macro) element;
            return getImageFromPath(s.getIconPath());
        } else {
            return null;
        }
    }

    @objid ("00699a92-c497-106a-bf4f-001ec947cd2a")
    @Override
    public String getText(Object element) {
        if (element == null) {
            return "<null>";
        } else if (element instanceof Catalog) {
            Catalog cat = (Catalog) element;
            if (cat.isModifiable()) {
                return cat.getName();
            } else {
                return cat.getName() + " " + Script.I18N.getString("CatalogDialog.CatalogReadOnly");
            }
        } else if (element instanceof Macro) {
            return ((Macro) element).getName();
        } else {
            return element.toString();
        }
    }

    @objid ("0069772e-c497-106a-bf4f-001ec947cd2a")
    CatalogLabelProvider() {
        this.images = new ImageRegistry(Display.getDefault());
        final Bundle bundle = Platform.getBundle(org.modelio.script.plugin.Script.PLUGIN_ID);
        final URL catalogImageUrl = FileLocator.find(bundle, new org.eclipse.core.runtime.Path("icons/scriptcatalog.png"), null);
        this.images.put(CATALOGID, ImageDescriptor.createFromURL(catalogImageUrl));
    }

    @objid ("00699dda-c497-106a-bf4f-001ec947cd2a")
    private Image getImageFromPath(java.nio.file.Path path) {
        if (path == null) {
            return null;
        }
        Image ret = this.images.get(path.toString());
        if (ret == null) {
            try {
                URL url = new URL("file", "", path.toString());
                ImageDescriptor desc = ImageDescriptor.createFromURL(url);
                this.images.put(path.toString(), desc);
                ret = this.images.get(path.toString());
            } catch (MalformedURLException e) {
                Script.LOG.error(e);
            }
        }
        return ret;
    }

}
