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

package org.modelio.diagram.editor.communication.plugin;

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
 * Singleton that represents the diagram plugin.
 */
@objid ("7a682cdd-55b6-11e2-877f-002564c97630")
public final class DiagramEditorCommunication implements BundleActivator {
    /**
     * The plug-in ID
     */
    @objid ("7a69b35a-55b6-11e2-877f-002564c97630")
    public static final String PLUGIN_ID = "org.modelio.diagram.editor.communication";

    @objid ("059657d8-599a-11e2-ae45-002564c97630")
    public static PluginLogger LOG = null;

    @objid ("059657d9-599a-11e2-ae45-002564c97630")
    public static BundledMessages I18N;

    @objid ("059657da-599a-11e2-ae45-002564c97630")
    private static BundleContext context;

    @objid ("059657db-599a-11e2-ae45-002564c97630")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("diagrameditorcommunication"));
    }

    @objid ("0598b91f-599a-11e2-ae45-002564c97630")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("0598b923-599a-11e2-ae45-002564c97630")
    public static BundleContext getContext() {
        return context;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path
     * 
     * @param path the path
     * @return the image descriptor
     */
    @objid ("0598b927-599a-11e2-ae45-002564c97630")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

}
