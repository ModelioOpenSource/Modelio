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

package org.modelio.propertyview.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * PropertyView plugin main class.
 * 
 * Contains static services for log and i18n, as well as the plugin ID.
 */
@objid ("dfda3753-6a14-4698-9d5d-3c9827571331")
public class PropertyViewPlugin implements BundleActivator {
    @objid ("44bf4bfe-238d-4933-b6fb-84fadd34ab51")
    public static final String PLUGIN_ID = "org.modelio.propertyview";

    @objid ("2b8a71f6-1cb7-456b-870c-e1de74e957c7")
    private static BundleContext context;

    @objid ("bfcd5ebc-30b0-4b2d-8fe3-a03723f43868")
    public static BundledMessages I18N;

    @objid ("fd710a4e-4c3b-4dde-8c20-4ed43fdd7347")
    public static PluginLogger LOG = null;

    @objid ("7a4785bd-53aa-43d9-905e-ababe0a4d7b4")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("propertyview"));
    }

    @objid ("87a79497-ce12-41b0-9d93-610a2f6584c8")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("4ea5e626-28c1-4749-a274-d1b2e767fda5")
    public static BundleContext getContext() {
        return context;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path
     * @param path the path
     * @return the image descriptor
     */
    @objid ("595f5a76-57fe-402f-887f-5f8a84142e8a")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

}
