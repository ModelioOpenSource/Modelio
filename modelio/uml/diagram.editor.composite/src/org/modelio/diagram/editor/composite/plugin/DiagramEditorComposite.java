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

package org.modelio.diagram.editor.composite.plugin;

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
@objid ("808b383a-55b6-11e2-877f-002564c97630")
public final class DiagramEditorComposite implements BundleActivator {
    /**
     * The plug-in ID
     */
    @objid ("808e457b-55b6-11e2-877f-002564c97630")
    public static final String PLUGIN_ID = "org.modelio.diagram.editor.composite";

    @objid ("151d87aa-55e8-4d60-a41d-649dcb8ab5cc")
    private static BundleContext context;

    @objid ("a573722a-5bd8-11e2-9e33-00137282c51b")
    public static PluginLogger LOG = null;

    @objid ("a573722b-5bd8-11e2-9e33-00137282c51b")
    public static BundledMessages I18N;

    @objid ("a573722d-5bd8-11e2-9e33-00137282c51b")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("diagrameditorcomposite"));
    }

    @objid ("a5737231-5bd8-11e2-9e33-00137282c51b")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("a5737235-5bd8-11e2-9e33-00137282c51b")
    public static BundleContext getContext() {
        return context;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path
     * @param path the path
     * @return the image descriptor
     */
    @objid ("fe94b60d-5a5b-11e2-9e33-00137282c51b")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

}
