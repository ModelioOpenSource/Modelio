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
package org.modelio.model.search.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@objid ("00190168-9e11-10ab-8258-001ec947cd2a")
public class ModelSearch implements BundleActivator {
    @objid ("00190956-9e11-10ab-8258-001ec947cd2a")
    public static final String PLUGIN_ID = "org.modelio.app.model.search.ui";

    @objid ("00190aa0-9e11-10ab-8258-001ec947cd2a")
    private static BundleContext context;

    @objid ("00190db6-9e11-10ab-8258-001ec947cd2a")
    public static BundledMessages I18N;

    @objid ("00190ee2-9e11-10ab-8258-001ec947cd2a")
    public static PluginLogger LOG;

    @objid ("00190bae-9e11-10ab-8258-001ec947cd2a")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger((String)null));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("modelsearch"));
        
    }

    @objid ("00190c6c-9e11-10ab-8258-001ec947cd2a")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("00190d0c-9e11-10ab-8258-001ec947cd2a")
    public static BundleContext getContext() {
        return context;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path
     * @param path the path
     * @return the image descriptor
     */
    @objid ("1109abaa-697b-402b-8309-c1060d854855")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(ModelSearch.PLUGIN_ID, path);
    }

}
