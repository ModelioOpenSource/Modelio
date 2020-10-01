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

package org.modelio.model.browser.view.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * The activator class controls the plug-in life cycle
 */
@objid ("001bf4ae-dd16-1fab-b27f-001ec947cd2a")
public class ModelBrowserExtOrg extends AbstractUIPlugin {
    /**
     * The plug-in ID
     */
    @objid ("001c1240-dd16-1fab-b27f-001ec947cd2a")
    public static final String PLUGIN_ID = "org.modelio.app.model.browser.view.ext_org"; // $NON-NLS-1$

    @objid ("f0357e0e-bedd-11e1-b430-001ec947c8cc")
    public static BundledMessages I18N;

    @objid ("3455ec4a-bede-11e1-b430-001ec947c8cc")
    private static BundleContext context;

    @objid ("e10f3898-c02f-11e1-b430-001ec947c8cc")
    public static PluginLogger LOG;

    @objid ("001c60ba-dd16-1fab-b27f-001ec947cd2a")
    @Override
    public void start(final BundleContext bundleContext) throws Exception {
        ModelBrowserExtOrg.context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext
                .getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        ModelBrowserExtOrg.LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), ModelBrowserExtOrg.PLUGIN_ID));
        ModelBrowserExtOrg.I18N = new BundledMessages(ModelBrowserExtOrg.LOG, ResourceBundle.getBundle("modelbrowser"));
    }

    @objid ("001c89b4-dd16-1fab-b27f-001ec947cd2a")
    @Override
    public void stop(final BundleContext bundleContext) throws Exception {
        ModelBrowserExtOrg.context = null;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path
     * 
     * @param path the path
     * @return the image descriptor
     */
    @objid ("001cd874-dd16-1fab-b27f-001ec947cd2a")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(ModelBrowserExtOrg.PLUGIN_ID, path);
    }

    @objid ("6ef57313-6122-44c5-8eb4-1d2487ed7dae")
    public static BundleContext getContext() {
        return ModelBrowserExtOrg.context;
    }

}
