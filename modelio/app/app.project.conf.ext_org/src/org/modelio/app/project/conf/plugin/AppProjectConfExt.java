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
 * Activator of the plugin fragment.
 */
@objid ("fba474c9-1dad-4af7-ace2-6d3b42686c8d")
public class AppProjectConfExt implements BundleActivator {
    /**
     * Plugin ID.
     */
    @objid ("dbd39727-45ad-42f2-857e-8b604528db1d")
    public static final String PLUGIN_ID = "org.modelio.app.project.conf.ext";

    @objid ("17cbed7f-7646-4455-a58b-bb676c599fad")
    private static BundleContext context;

    /**
     * Messages service.
     */
    @objid ("8679ac38-7cc3-49c4-bcb8-85a6d98e7886")
    public static BundledMessages I18N;

    /**
     * Logger
     */
    @objid ("aed4370c-d005-458e-9715-93868b87140f")
    public static PluginLogger LOG;

    @objid ("57e8fbe1-14f2-40cf-a8c0-69752c821f82")
    @Override
    public void start(BundleContext bundleContext) {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger((String)null));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("appprojectconfext"));
    }

    @objid ("c07e5e03-a768-440c-884b-861498499cf1")
    @Override
    public void stop(BundleContext bundleContext) {
        context = null;
    }

    /**
     * @return The execution context of the bundle.
     */
    @objid ("6db105a0-ad0f-4be7-a790-740c87475e86")
    public static BundleContext getContext() {
        return context;
    }

}
