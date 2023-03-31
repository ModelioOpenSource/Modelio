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
package org.modelio.xmi.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * The activator class controls the plug-in life cycle
 */
@objid ("66331fcd-41ae-4dcb-94a9-e95ee23e8ec0")
public class Xmi extends AbstractUIPlugin {
     // // The plug-in ID
    @objid ("394f68e1-7139-4387-85b6-f41108391653")
    public static final String PLUGIN_ID = "org.modelio.app.xmi";

    @objid ("7e554bfd-da4b-4297-9245-b2ae9ff549f4")
    private static BundleContext context;

    @objid ("d9f4dc21-d417-40a4-aa4f-b2c9d2074048")
    public static BundledMessages I18N;

    @objid ("128885b6-d172-475e-9a88-31a73ba634c4")
    public static PluginLogger LOG = null;

    /**
     * The constructor
     */
    @objid ("d7274482-54e0-47d0-b97f-85c852f93c7a")
    public  Xmi() {
        
    }

    /*
         * (non-Javadoc)
         * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
         */
    @objid ("8d0df960-03bd-460f-8bd7-fc86b1059793")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("xmi"));
        
    }

    /*
         * (non-Javadoc)
         * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
         */
    @objid ("e5320a2c-24b4-4926-9e48-dc0208225efc")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    /**
     * Returns the shared instance
     * @return the shared instance
     */
    @objid ("addbe9a5-6b4c-459b-9204-55778cf90708")
    public static BundleContext getContext() {
        return context;
    }

}
