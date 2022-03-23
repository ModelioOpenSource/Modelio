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
package org.modelio.platform.module.browser.commands.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * ModuleBrowserCommands plugin singleton class.
 * @author fpoyer
 */
@objid ("f1a48d48-120c-11e2-8b3b-001ec947c8cc")
public class ModuleBrowserCommands implements BundleActivator {
    /**
     * ID of the ModuleBrowserCommands plugin.
     */
    @objid ("03e0e097-120d-11e2-8b3b-001ec947c8cc")
    public static final String PLUGIN_ID = "org.modelio.platform.module.browser.commands";

    @objid ("03e0e099-120d-11e2-8b3b-001ec947c8cc")
    private static BundleContext context;

    @objid ("03e0e09b-120d-11e2-8b3b-001ec947c8cc")
    public static BundledMessages I18N;

    @objid ("03e0e09c-120d-11e2-8b3b-001ec947c8cc")
    public static PluginLogger LOG;

    @objid ("03e0e096-120d-11e2-8b3b-001ec947c8cc")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("modulebrowsercommands"));
        
    }

    @objid ("03e0e09d-120d-11e2-8b3b-001ec947c8cc")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("03e0e09e-120d-11e2-8b3b-001ec947c8cc")
    public static BundleContext getContext() {
        return context;
    }

}
