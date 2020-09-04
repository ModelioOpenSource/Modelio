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

package org.modelio.app.update.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.app.preferences.AppSharedPreferencesKeys;
import org.modelio.app.preferences.plugin.AppPreferences;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@objid ("798daa57-9f4b-40e0-a1fb-4344fb5c82a1")
public class AppUpdateExt implements BundleActivator {
    @objid ("2d099b0c-0914-4007-b7bf-a2be6aa50d46")
    public static final String PLUGIN_ID = "org.modelio.app.update.ext";

    @objid ("3600088f-a7d8-4911-881e-806e37bb371d")
    private static BundleContext context;

    @objid ("d8bc81b7-f921-40ad-a50a-223b48cc266f")
    public static BundledMessages I18N;

    @objid ("405a4679-378d-40c0-b710-3a5d9d28aaf5")
    public static PluginLogger LOG;

    @objid ("0440c9ab-35f7-4e13-a4cd-ea25d6143af8")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        AppUpdateExt.context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        AppUpdateExt.LOG = new PluginLogger(service.getLogger((String)null));
        AppUpdateExt.I18N = new BundledMessages(AppUpdateExt.LOG, ResourceBundle.getBundle("appupdate"));
    }

    @objid ("de96e952-b3f8-40f7-93f9-d5035774d424")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        AppUpdateExt.context = null;
    }

    @objid ("0c83e932-e0cb-455b-844d-5c99aea27214")
    public static BundleContext getContext() {
        return AppUpdateExt.context;
    }

    /**
     * Get the image descriptor for an image stored in this plugin.
     * 
     * @param path a path relative to the plugin
     * @return the image descriptor.
     */
    @objid ("ddd7eaf9-4bd6-47f7-b4a4-af519a782e65")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(AppUpdateExt.PLUGIN_ID, path);
    }

    @objid ("4cb9644d-8e5d-4659-a5b0-2fb8ba9ab2dc")
    public static String getUpdateSite() {
        IPersistentPreferenceStore updatePrefs = AppPreferences.getPreferences();
        String updateSiteUrl = updatePrefs.getString(AppSharedPreferencesKeys.UPDATESITE_PREFKEY);
        return updateSiteUrl;
    }

}
