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

package org.modelio.module.propertytab.plugin;

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

@objid ("c8800fb1-1eba-11e2-9382-bc305ba4815c")
public class ModulePropertyTab implements BundleActivator {
    /**
     * ID of the ModuleBrowserCommands plugin.
     */
    @objid ("04528b80-1ebb-11e2-9382-bc305ba4815c")
    public static final String PLUGIN_ID = "org.modelio.app.module.propertytab";

    @objid ("c88036c3-1eba-11e2-9382-bc305ba4815c")
    private static BundleContext context;

    @objid ("c8805dd0-1eba-11e2-9382-bc305ba4815c")
    public static BundledMessages I18N;

    @objid ("c8805dd2-1eba-11e2-9382-bc305ba4815c")
    public static PluginLogger LOG;

    @objid ("c8805dd3-1eba-11e2-9382-bc305ba4815c")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("modulepropertytab"));
    }

    @objid ("c88084e1-1eba-11e2-9382-bc305ba4815c")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("c88084e5-1eba-11e2-9382-bc305ba4815c")
    public static BundleContext getContext() {
        return context;
    }

    @objid ("c880abf3-1eba-11e2-9382-bc305ba4815c")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

}
