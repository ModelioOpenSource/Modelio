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

package org.modelio.uml.ui.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * The activator class controls the plug-in life cycle
 */
@objid ("25206690-4ad7-4d27-ab67-7ca9f2f08ba1")
public class UmlUi extends AbstractUIPlugin {
    /**
     * The plug-in ID
     */
    @objid ("54a27a5a-8456-4eaa-ac2f-75c61003327e")
    public static final String PLUGIN_ID = "org.modelio.uml.ui"; // $NON-NLS-1$

    @objid ("997fa8d0-69a3-4476-98f5-f7882298fbdd")
    private static BundleContext context;

    /**
     * The plug-in's i18n bundle.
     */
    @objid ("6b2d81ef-b0e1-46ab-a967-146cd46d1fdf")
    public static BundledMessages I18N;

    /**
     * The plug-in's logger.
     */
    @objid ("da072eb3-b397-4fcf-92a4-edb1c5febbd1")
    public static PluginLogger LOG;

    @objid ("4065f950-e2c9-40a7-bdba-a6fdc255fa05")
    @Override
    public void start(final BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext
                .getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("umlui"));
    }

    @objid ("55794b8c-61c8-4b28-b46e-7a16b8bfb66a")
    @Override
    public void stop(final BundleContext bundleContext) throws Exception {
        context = null;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path
     * 
     * @param path the path
     * @return the image descriptor
     */
    @objid ("95c6e724-b7bd-4b33-9a58-fd0b8deb4bb2")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

    /**
     * @return the bundle's execution context within the Framework.
     */
    @objid ("216e9ecf-eae7-4962-91ef-22cadbab9dd8")
    public static BundleContext getContext() {
        return context;
    }

}
