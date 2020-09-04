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

package org.modelio.app.project.core.ext.plugin;

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
@objid ("8350da4c-7dd6-4039-96ab-7538074a3a09")
public class AppProjectCoreOrg implements BundleActivator {
    /**
     * app.project.core plugin ID : {@value #PLUGIN_ID} .
     */
    @objid ("c3cae1e0-b846-4d6c-b078-b7fd6b3da86b")
    public static final String PLUGIN_ID = "org.modelio.app.project.core.ext";

    @objid ("14eed896-d01d-4ef6-8f49-2e1d2f0f38e7")
    private static BundleContext context;

    /**
     * {@value #PLUGIN_ID} plugin messages.
     */
    @objid ("00ecd17e-7ad1-4053-a854-ced52de5999d")
    public static BundledMessages I18N;

    /**
     * {@value #PLUGIN_ID} plugin logger.
     */
    @objid ("6bd43899-3461-475a-a43e-69af2882c22f")
    public static PluginLogger LOG;

    @objid ("c57cbcec-2e82-4335-81a2-9034729a6957")
    @Override
    public void start(BundleContext bundleContext) {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger((String)null));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("appprojectcore"));
    }

    @objid ("580c6fbf-64d0-4385-bcfc-9be26a80baf9")
    @Override
    public void stop(BundleContext bundleContext) {
        context = null;
    }

    /**
     * @return the {@value #PLUGIN_ID} plugin execution context.
     */
    @objid ("a09b240e-91d9-464f-aae0-ed4d2f7b41dc")
    public static BundleContext getContext() {
        return context;
    }

}
