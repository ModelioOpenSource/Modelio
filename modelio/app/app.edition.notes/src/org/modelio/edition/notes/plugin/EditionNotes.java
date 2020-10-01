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

package org.modelio.edition.notes.plugin;

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

/**
 * edition.notes plugin main class.
 * 
 * Contains static services for log and i18n, as well as the plugin ID.
 */
@objid ("26ce1968-186f-11e2-bc4e-002564c97630")
public class EditionNotes implements BundleActivator {
    @objid ("34f4d0a6-186f-11e2-bc4e-002564c97630")
    public static final String PLUGIN_ID = "org.modelio.app.edition.notes";

    @objid ("26ce196c-186f-11e2-bc4e-002564c97630")
    private static BundleContext context;

    @objid ("26ce196f-186f-11e2-bc4e-002564c97630")
    public static PluginLogger LOG;

    @objid ("e13f92eb-18f8-11e2-bc4e-002564c97630")
    public static BundledMessages I18N;

    @objid ("26ce1970-186f-11e2-bc4e-002564c97630")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("editionnotes"));
    }

    @objid ("26ce1974-186f-11e2-bc4e-002564c97630")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("26ce1978-186f-11e2-bc4e-002564c97630")
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
    @objid ("26ce197c-186f-11e2-bc4e-002564c97630")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

}
