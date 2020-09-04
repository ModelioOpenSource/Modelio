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

package org.modelio.model.property.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * ModelProperty plugin main class.
 * 
 * Contains static services for log and i18n, as well as the plugin ID.
 */
@objid ("8fb61066-c068-11e1-8c0a-002564c97630")
public class ModelProperty implements BundleActivator {
    @objid ("a8ac4327-c068-11e1-8c0a-002564c97630")
    public static final String PLUGIN_ID = "org.modelio.model.property";

    @objid ("0f5f7718-1d97-4b4f-bd52-49372e6f8963")
    private static BundleContext context;

    @objid ("8fb6106b-c068-11e1-8c0a-002564c97630")
    public static BundledMessages I18N;

    @objid ("8fb6106d-c068-11e1-8c0a-002564c97630")
    public static PluginLogger LOG = null;

    @objid ("8fb61083-c068-11e1-8c0a-002564c97630")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("modelproperty"));
    }

    @objid ("8fb61087-c068-11e1-8c0a-002564c97630")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("8fb6108b-c068-11e1-8c0a-002564c97630")
    public static BundleContext getContext() {
        return context;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path
     * 
     * @param path the path
     * @return the image descriptor
     */
    @objid ("3f8ed75f-1791-11e2-aa0d-002564c97630")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

}
