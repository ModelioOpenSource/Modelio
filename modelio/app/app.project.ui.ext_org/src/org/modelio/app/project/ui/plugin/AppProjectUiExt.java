/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.app.project.ui.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@objid ("00958738-80f5-10af-9941-001ec947cd2a")
public class AppProjectUiExt implements BundleActivator {
    @objid ("00094fc0-80f6-10af-9941-001ec947cd2a")
    public static final String PLUGIN_ID = "org.modelio.app.project.ui.ext";

    @objid ("446c805b-df91-4359-9076-94d288dff7d2")
    private static BundleContext context;

    @objid ("0095ac68-80f5-10af-9941-001ec947cd2a")
    public static BundledMessages I18N;

    @objid ("0095b32a-80f5-10af-9941-001ec947cd2a")
    public static PluginLogger LOG;

    @objid ("0095b8fc-80f5-10af-9941-001ec947cd2a")
    @Override
    public void start(BundleContext bundleContext) {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(null));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("appprojectuiext"));
    }

    @objid ("0095da30-80f5-10af-9941-001ec947cd2a")
    @Override
    public void stop(BundleContext bundleContext) {
        context = null;
    }

    @objid ("0095f664-80f5-10af-9941-001ec947cd2a")
    public static BundleContext getContext() {
        return context;
    }

}
