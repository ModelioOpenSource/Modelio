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
package org.modelio.app.model.merge.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * 'org.modelio.app.model.merge' plugin singleton.
 * 
 * @since 5.3.1
 */
@objid ("83a6edd4-d700-4af2-9cab-e9bcecd9f853")
public class AppModelMerge implements BundleActivator {
    @objid ("e8f4d659-ff16-4245-b3be-4a712201e3de")
    public static final String PLUGIN_ID = "org.modelio.app.model.merge";

    @objid ("55e87c00-b8e8-47cc-b49f-47f07447816a")
    public static BundledMessages I18N;

    @objid ("9d2bbeb9-6c02-4646-97ea-78c33ae3213d")
    public static PluginLogger LOG;

    @objid ("3d1d9ae1-b3e2-4951-9488-67468013d03d")
    private static BundleContext context;

    @objid ("ac41ea54-4534-477a-be3f-ce6fd3e1437d")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        AppModelMerge.context = bundleContext;
        AppModelMerge.LOG = new PluginLogger(AppModelMerge.PLUGIN_ID);
        AppModelMerge.I18N = new BundledMessages(AppModelMerge.LOG, ResourceBundle.getBundle("res.app-model-merge"));
        
    }

    @objid ("ac735018-2052-495d-a827-3b4b8f761dde")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        AppModelMerge.context = null;
    }

    @objid ("c21afb7e-f969-498d-9660-9fe3474afb77")
    public static BundleContext getContext() {
        return AppModelMerge.context;
    }

    /**
     * Returns an image descriptor for the image file in the plug-in relative path.
     * @param path a path relative to this plugin.
     * @return the image descriptor.
     */
    @objid ("cf19dc84-7889-4803-858d-70444a0d1405")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return ImageDescriptor.createFromURL(context.getBundle().getEntry(path));
    }

}
