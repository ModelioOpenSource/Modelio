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

package org.modelio.bpmn.ui.plugin;

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
@objid ("f7cd43de-6e54-482f-8f52-55b6f8a7e345")
public class BpmnUi extends AbstractUIPlugin {
    /**
     * The plug-in ID
     */
    @objid ("cf123d74-b9cb-4ec2-ac06-a7ba13e935e4")
    public static final String PLUGIN_ID = "org.modelio.bpmn.ui"; // $NON-NLS-1$

    @objid ("df4e627b-15bc-480c-82ad-befef69feb18")
    private static BundleContext context;

    /**
     * The plug-in's i18n bundle.
     */
    @objid ("3dac4d78-e5ec-4585-a6d2-1ce0d0fe192f")
    public static BundledMessages I18N;

    /**
     * The plug-in's logger.
     */
    @objid ("68d7abad-6e86-4260-8219-b03b054ec653")
    public static PluginLogger LOG;

    @objid ("d4750ebc-42c4-4247-b027-5e53f85fe127")
    @Override
    public void start(final BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext
                .getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("bpmnui"));
    }

    @objid ("e56195ad-d9ca-4530-8473-e7d11b3c6f41")
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
    @objid ("e9a942cb-a3e7-4dbd-8001-ee8141d23cd6")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

    /**
     * @return the bundle's execution context within the Framework.
     */
    @objid ("35470b45-4a73-4bc0-a174-9dd3cb8f3b1b")
    public static BundleContext getContext() {
        return context;
    }

}
