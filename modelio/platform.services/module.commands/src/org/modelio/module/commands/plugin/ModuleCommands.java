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

package org.modelio.module.commands.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * ModuleBrowserCommands plugin singleton class.
 * @author fpoyer
 */
@objid ("6c968bf9-1442-11e2-a678-001ec947c8cc")
public class ModuleCommands implements BundleActivator {
    /**
     * ID of the ModuleBrowserCommands plugin.
     */
    @objid ("6c968c03-1442-11e2-a678-001ec947c8cc")
    public static final String PLUGIN_ID = "org.modelio.module.commands";

    @objid ("6c968bfb-1442-11e2-a678-001ec947c8cc")
    private static BundleContext context;

    @objid ("6c968bfa-1442-11e2-a678-001ec947c8cc")
    public static BundledMessages I18N;

    @objid ("6c968c04-1442-11e2-a678-001ec947c8cc")
    public static PluginLogger LOG;

    @objid ("6c968c05-1442-11e2-a678-001ec947c8cc")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("modulecommands"));
    }

    @objid ("6c968c06-1442-11e2-a678-001ec947c8cc")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("6c968c07-1442-11e2-a678-001ec947c8cc")
    public static BundleContext getContext() {
        return context;
    }

}
