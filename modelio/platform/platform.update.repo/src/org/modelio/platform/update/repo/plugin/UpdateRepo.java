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

package org.modelio.platform.update.repo.plugin;

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

@objid ("cd9f6b0d-0e3d-431c-a253-82b0c82c6aee")
public class UpdateRepo implements BundleActivator {
    @objid ("726aa9e3-e71d-4399-94d6-128ebcb2c910")
    public static final String PLUGIN_ID = "org.modelio.platform.update.repo";

    @objid ("3b12c819-f4e7-459c-8956-0aa88cb0924c")
    private static BundleContext context;

    @objid ("d0829b69-d509-4bdb-a977-49fff6010da9")
    public static BundledMessages I18N;

    @objid ("b075398d-8ae7-4050-94e6-9488717e131e")
    public static PluginLogger LOG;

    @objid ("aa43e3c9-b31c-49e8-8cb3-b5e6c347288e")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        UpdateRepo.context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        UpdateRepo.LOG = new PluginLogger(service.getLogger((String) null));
        UpdateRepo.I18N = new BundledMessages(UpdateRepo.LOG, ResourceBundle.getBundle("appupdaterepo"));
    }

    @objid ("3d7cc374-f834-4e46-8f0f-ee96e55a4cc9")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        UpdateRepo.context = null;
    }

    @objid ("b7f1ff49-2314-45a1-895e-7fee7a5174ce")
    public static BundleContext getContext() {
        return UpdateRepo.context;
    }

    /**
     * Get the image descriptor for an image stored in this plugin.
     * 
     * @param path a path relative to the plugin
     * @return the image descriptor.
     */
    @objid ("74966d1d-8d1f-43b0-a056-2fc17a4bba5c")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(UpdateRepo.PLUGIN_ID, path);
    }

}
