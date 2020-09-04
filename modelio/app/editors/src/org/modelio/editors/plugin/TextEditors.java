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

package org.modelio.editors.plugin;

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

@objid ("7b457829-2a77-11e2-9fb9-bc305ba4815c")
public class TextEditors implements BundleActivator {
    /**
     * ID of the ModuleBrowserCommands plugin.
     */
    @objid ("ab507dcc-2a77-11e2-9fb9-bc305ba4815c")
    public static final String PLUGIN_ID = "org.modelio.editors.texteditors";

    @objid ("7b45782a-2a77-11e2-9fb9-bc305ba4815c")
    private static BundleContext context;

    @objid ("7b45782b-2a77-11e2-9fb9-bc305ba4815c")
    public static BundledMessages I18N;

    @objid ("7b45782c-2a77-11e2-9fb9-bc305ba4815c")
    public static PluginLogger LOG;

    @objid ("7b457830-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("editors"));
    }

    @objid ("7b457834-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("7b457838-2a77-11e2-9fb9-bc305ba4815c")
    public static BundleContext getContext() {
        return context;
    }

    @objid ("7b45783c-2a77-11e2-9fb9-bc305ba4815c")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

}
