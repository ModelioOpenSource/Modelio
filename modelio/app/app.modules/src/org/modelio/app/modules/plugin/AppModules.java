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

package org.modelio.app.modules.plugin;

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

@objid ("c1a950fb-43d5-4e04-a4a6-d9faad2527c3")
public class AppModules implements BundleActivator {
    @objid ("5abdcf38-8835-4ecc-824e-0778f44cf316")
    public static final String PLUGIN_ID = "org.modelio.app.modules";

    @objid ("dc044937-6fc1-4de9-b39d-c3a305eb079c")
    private static BundleContext context;

    @objid ("f98b1783-7bb3-46ac-9ad3-ccf75f6722cd")
    public static BundledMessages I18N;

    @objid ("93f7853a-f781-4a96-afce-292d5019f903")
    public static PluginLogger LOG;

    @objid ("eeff41ca-f719-47e2-b4cb-ef3aa2775042")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        final ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        final ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger((String)null));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("appmodules"));
    }

    @objid ("99476565-7bfe-4d4f-8642-641e9660255f")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("743c2962-4e03-40ac-929b-2adcb2179502")
    public static BundleContext getContext() {
        return context;
    }

    /**
     * Get the image descriptor for an image stored in this plugin.
     * 
     * @param path a path relative to the plugin
     * @return the image descriptor.
     */
    @objid ("2fc45d30-bc5f-4180-b82c-94c2e6bc3b41")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

}
