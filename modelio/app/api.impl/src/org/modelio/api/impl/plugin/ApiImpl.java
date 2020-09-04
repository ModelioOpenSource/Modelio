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

package org.modelio.api.impl.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@objid ("73c04858-8ae9-4b59-8aa2-35d49277759a")
public class ApiImpl implements BundleActivator {
    @objid ("4f183a85-0f86-465c-8f5f-6d08d475999a")
    public static final String PLUGIN_ID = "org.modelio.api.impl";

    @objid ("5c6f46be-29dd-4684-bdcc-c539daec684e")
    private static BundleContext context;

    @objid ("c76ac482-f9df-4566-951f-1bf09ed4cac2")
    public static PluginLogger LOG;

    @objid ("06ceaf3d-f4f1-4710-aa79-e3b838d1cd28")
    public static BundledMessages I18N;

    @objid ("ebed2990-81af-4cb5-92c5-a7db9967b679")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger((String)null));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("apiimpl"));
    }

    @objid ("f0bc1f3a-1cf8-47f0-850e-c5f8d8eda754")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("6ab7bb5c-58df-450e-baac-95158b1aa32c")
    public static BundleContext getContext() {
        return context;
    }

}
