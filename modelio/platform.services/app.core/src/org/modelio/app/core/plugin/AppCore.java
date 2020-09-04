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

package org.modelio.app.core.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@objid ("0044aba6-cc35-1ff2-a7f4-001ec947cd2a")
public class AppCore implements BundleActivator {
    @objid ("00497fbe-cc35-1ff2-a7f4-001ec947cd2a")
    public static final String PLUGIN_ID = "org.modelio.app.core";

    @objid ("004980c2-cc35-1ff2-a7f4-001ec947cd2a")
    private static BundleContext context;

    @objid ("0049922e-cc35-1ff2-a7f4-001ec947cd2a")
    public static PluginLogger LOG;

    @objid ("0049910c-cc35-1ff2-a7f4-001ec947cd2a")
    public static BundledMessages I18N;

    @objid ("00499350-cc35-1ff2-a7f4-001ec947cd2a")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger((String)null));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("appcore"));
    }

    @objid ("004993e6-cc35-1ff2-a7f4-001ec947cd2a")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("0049947c-cc35-1ff2-a7f4-001ec947cd2a")
    public static BundleContext getContext() {
        return context;
    }

}
