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
package org.modelio.diagram.persistence.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@objid ("efbf5733-1847-11e2-92d2-001ec947c8cc")
public class DiagramPersistence implements BundleActivator {
    /**
     * ID of the ModuleBrowserCommands plugin.
     */
    @objid ("cb9ff8b0-186f-11e2-92d2-001ec947c8cc")
    public static final String PLUGIN_ID = "org.modelio.app.diagram.persistence";

    @objid ("cb6b84f4-186f-11e2-92d2-001ec947c8cc")
    private static BundleContext context;

    @objid ("cb6b84f5-186f-11e2-92d2-001ec947c8cc")
    public static BundledMessages I18N = null;

    @objid ("cb6b84f7-186f-11e2-92d2-001ec947c8cc")
    public static PluginLogger LOG = null;

    @objid ("cb6b84f8-186f-11e2-92d2-001ec947c8cc")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("diagrampersistence"));
        
    }

    @objid ("cb6b84fc-186f-11e2-92d2-001ec947c8cc")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("cb6b8500-186f-11e2-92d2-001ec947c8cc")
    public static BundleContext getContext() {
        return context;
    }

}
