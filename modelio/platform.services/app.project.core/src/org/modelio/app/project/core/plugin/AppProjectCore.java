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

package org.modelio.app.project.core.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * {@value #PLUGIN_ID} plugin class.
 */
@objid ("003fe7d8-fa18-1ff2-9ac8-001ec947cd2a")
public class AppProjectCore implements BundleActivator {
    /**
     * app.project.core plugin ID : {@value #PLUGIN_ID} .
     */
    @objid ("003ff052-fa18-1ff2-9ac8-001ec947cd2a")
    public static final String PLUGIN_ID = "org.modelio.app.project.core";

    @objid ("003ff17e-fa18-1ff2-9ac8-001ec947cd2a")
    private static BundleContext context;

    /**
     * {@value #PLUGIN_ID} plugin messages.
     */
    @objid ("003ffee4-fa18-1ff2-9ac8-001ec947cd2a")
    public static BundledMessages I18N;

    /**
     * {@value #PLUGIN_ID} plugin logger.
     */
    @objid ("003ff278-fa18-1ff2-9ac8-001ec947cd2a")
    public static PluginLogger LOG;

    @objid ("003fee2c-fa18-1ff2-9ac8-001ec947cd2a")
    @Override
    public void start(BundleContext bundleContext) {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger((String)null));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("appprojectcore"));
    }

    @objid ("003feef4-fa18-1ff2-9ac8-001ec947cd2a")
    @Override
    public void stop(BundleContext bundleContext) {
        context = null;
    }

    /**
     * @return the {@value #PLUGIN_ID} plugin execution context.
     */
    @objid ("003fef94-fa18-1ff2-9ac8-001ec947cd2a")
    public static BundleContext getContext() {
        return context;
    }

}
