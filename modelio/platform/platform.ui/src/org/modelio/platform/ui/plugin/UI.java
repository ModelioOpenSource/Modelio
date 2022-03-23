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
package org.modelio.platform.ui.plugin;

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

@objid ("b8727d03-4b5d-4024-a201-cd4a8d4f6b11")
public class UI implements BundleActivator {
    @objid ("f4257fe6-676e-470f-b5a4-7f8659b028b5")
    public static final String PLUGIN_ID = "org.modelio.platform.ui";

    @objid ("279b2cbc-84fc-41e4-a36e-bb92eab459ad")
    private static BundleContext context;

    @objid ("41e55bdb-0908-4a00-b9eb-4963c084057e")
    public static PluginLogger LOG;

    @objid ("9adefb82-bd01-4513-b2dd-ae72c579a287")
    public static BundledMessages I18N;

    @objid ("e1e415eb-36f1-4279-b0db-bfabb25aba57")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger((String)null));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("ui"));
        
    }

    @objid ("a393ff8f-271e-4975-96b6-da7cfc7055cd")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("f860a2d0-3133-4ba5-b58e-d2fa0634914b")
    public static BundleContext getContext() {
        return context;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative path
     * @param path the path
     * @return the image descriptor
     */
    @objid ("66ee1b5f-2c08-41c3-bf9e-5fb71effbd93")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(UI.PLUGIN_ID, path);
    }

}
