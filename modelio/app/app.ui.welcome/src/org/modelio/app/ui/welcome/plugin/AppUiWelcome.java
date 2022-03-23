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
package org.modelio.app.ui.welcome.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Bundle activator.
 * <p>
 * Also initializes the log level from preferences.
 */
@objid ("82cfa496-2ed8-4257-95ae-7f2ecd34780c")
public class AppUiWelcome implements BundleActivator {
    /**
     * The 'app.ui' plugin identifier.
     */
    @objid ("76276681-71d5-4d50-a4b7-f406df06ae08")
    public static final String PLUGIN_ID = "org.modelio.app.ui.welcome";

    @objid ("9e555280-3305-406b-ae0e-45989fa6ef8a")
    private static BundleContext context;

    /**
     * Translated messages service.
     */
    @objid ("e7c2a592-89bb-4cf6-a9d2-7c76bfeb863c")
    public static BundledMessages I18N;

    /**
     * The plugin logger
     */
    @objid ("837c2ee5-454c-4ec0-a289-b0ee4b6a8aca")
    public static PluginLogger LOG;

    @objid ("71e60432-d4b4-473e-99ef-9dcad50c1962")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        AppUiWelcome.context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        AppUiWelcome.LOG = new PluginLogger(service.getLogger((String)null));
        AppUiWelcome.I18N = new BundledMessages(AppUiWelcome.LOG, ResourceBundle.getBundle("welcome"));
        
    }

    @objid ("1e5c4822-4b5c-4843-b5d9-635e90d02582")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        AppUiWelcome.context = null;
    }

    /**
     * @return the bundle context.
     */
    @objid ("38d17a72-37c4-47df-a3d0-69b94122a53f")
    public static BundleContext getContext() {
        return AppUiWelcome.context;
    }

}
