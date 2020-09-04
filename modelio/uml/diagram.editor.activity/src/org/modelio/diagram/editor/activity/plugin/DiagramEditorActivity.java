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

package org.modelio.diagram.editor.activity.plugin;

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
 * Class that represents the activity diagram plugin.
 * 
 * Contains static services for log and i18n, as well as the plugin ID.
 */
@objid ("2b746222-55b6-11e2-877f-002564c97630")
public class DiagramEditorActivity implements BundleActivator {
    /**
     * The plug-in ID
     */
    @objid ("2b746227-55b6-11e2-877f-002564c97630")
    public static final String PLUGIN_ID = "org.modelio.diagram.editor.activity";

    @objid ("2dc6d034-58a2-11e2-9574-002564c97630")
    private static BundleContext context;

    @objid ("38ebf5e2-81cb-400e-828e-9519c0bcda7e")
    public static PluginLogger LOG = null;

    @objid ("513f79fa-02db-4b9f-a974-584d4fdfa66f")
    public static BundledMessages I18N;

    @objid ("2dc6d035-58a2-11e2-9574-002564c97630")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("diagrameditoractivity"));
    }

    @objid ("2dc6d039-58a2-11e2-9574-002564c97630")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("2dc6d03d-58a2-11e2-9574-002564c97630")
    public static BundleContext getContext() {
        return context;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path
     * 
     * @param path the path
     * @return the image descriptor
     */
    @objid ("2dc6d041-58a2-11e2-9574-002564c97630")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

}
