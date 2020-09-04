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

package org.modelio.app.project.conf.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * app.project.core plugin class.
 */
@objid ("000a6716-2e60-10a6-96e4-001ec947cd2a")
public class AppProjectConf implements BundleActivator {
    @objid ("002cc7fc-5a8e-10a6-888d-001ec947cd2a")
    public static final String PLUGIN_ID = "org.modelio.app.project.conf";

    @objid ("000aefba-2e60-10a6-96e4-001ec947cd2a")
    public static BundledMessages I18N;

    @objid ("000af0f0-2e60-10a6-96e4-001ec947cd2a")
    public static PluginLogger LOG;

    @objid ("0080f958-5a8d-10a6-888d-001ec947cd2a")
    private static BundleContext context;

    @objid ("000aebfa-2e60-10a6-96e4-001ec947cd2a")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger((String)null));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("appprojectconf"));
    }

    @objid ("000aec90-2e60-10a6-96e4-001ec947cd2a")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("000aed30-2e60-10a6-96e4-001ec947cd2a")
    public static BundleContext getContext() {
        return context;
    }

}
