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
package org.modelio.app.update.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@objid ("b9d1adc2-52eb-4b97-89ee-60a31386268c")
public class AppUpdate implements BundleActivator {
    @objid ("5b209984-fcc3-4536-b221-c16b47a4281b")
    public static final String PLUGIN_ID = "org.modelio.app.update";

    @objid ("865b05d2-f0ea-4b50-a591-e749967caaf2")
    private static BundleContext context;

    @objid ("8840e43f-2398-4390-8bf6-a1ce7d3fb4b2")
    public static BundledMessages I18N;

    @objid ("7b5f2a42-563f-45e9-b36d-d2fc4c55bbef")
    public static PluginLogger LOG;

    @objid ("22ffda4c-75cd-4d68-99c3-836e15493cc3")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        AppUpdate.context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        AppUpdate.LOG = new PluginLogger(service.getLogger((String)null));
        AppUpdate.I18N = new BundledMessages(AppUpdate.LOG, ResourceBundle.getBundle("appupdate"));
        
    }

    @objid ("419e086f-27f9-4d27-882b-4d2f6fbde4f0")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        AppUpdate.context = null;
    }

    @objid ("be9e4f98-0364-4c94-aadc-faff59c71f79")
    public static BundleContext getContext() {
        return AppUpdate.context;
    }

    /**
     * Get the image descriptor for an image stored in this plugin.
     * @param path a path relative to the plugin
     * @return the image descriptor.
     */
    @objid ("5fd736da-2ee2-4038-9c13-24821bb9ae7d")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(AppUpdate.PLUGIN_ID, path);
    }

}
