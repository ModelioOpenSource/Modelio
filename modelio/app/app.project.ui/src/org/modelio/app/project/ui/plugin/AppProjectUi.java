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

@objid ("004a0e3e-de3d-1ff2-a7f4-001ec947cd2a")
public class AppProjectUi implements BundleActivator {
    @objid ("0052e3ce-80ac-10af-9941-001ec947cd2a")
    public static final String PLUGIN_ID = "org.modelio.app.project.ui";

    @objid ("533519bd-a1f8-4da6-8930-bd87140fb3ad")
    private static BundleContext context;

    @objid ("004a0ef2-de3d-1ff2-a7f4-001ec947cd2a")
    public static BundledMessages I18N;

    @objid ("0049ff66-de3d-1ff2-a7f4-001ec947cd2a")
    public static PluginLogger LOG;

    @objid ("004a0cfe-de3d-1ff2-a7f4-001ec947cd2a")
    @Override
    public void start(BundleContext bundleContext) {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(null));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("appprojectui"));
    }

    @objid ("004a05f6-de3d-1ff2-a7f4-001ec947cd2a")
    @Override
    public void stop(BundleContext bundleContext) {
        context = null;
    }

    @objid ("004a0344-de3d-1ff2-a7f4-001ec947cd2a")
    public static BundleContext getContext() {
        return context;
    }

}
