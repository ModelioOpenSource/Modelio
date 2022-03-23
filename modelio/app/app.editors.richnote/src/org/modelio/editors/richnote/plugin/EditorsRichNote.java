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
package org.modelio.editors.richnote.plugin;

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
@objid ("ccc2fec1-5102-41ad-81de-7e3f09c77b50")
public class EditorsRichNote implements BundleActivator {
    /**
     * Plugin ID
     */
    @objid ("59666718-4fe6-41db-a16c-87201f25df5d")
    public static final String PLUGIN_ID = "org.modelio.app.editors.richnote";

    @objid ("6f2d592d-24f2-4a1e-90d2-805ba671d6a9")
    private static BundleContext context;

    /**
     * Logger
     */
    @objid ("38ebbc23-9a96-47cd-bcbb-63b23fba0422")
    public static PluginLogger LOG;

    /**
     * Messages
     */
    @objid ("ef812117-3098-42e3-bca0-70d76eb3f84d")
    public static BundledMessages I18N;

    @objid ("c34be2e7-a06a-44d3-a4b4-8ed19fc433e7")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("editors_richnote"));
        
    }

    @objid ("0456aa9f-7c86-4609-a599-82e9df6e7ed3")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    /**
     * @return the Eclipse bundle context.
     */
    @objid ("67dda278-5c8b-43b8-87e8-ac64af9afbbc")
    public static BundleContext getContext() {
        return context;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path
     * @param path the path
     * @return the image descriptor
     */
    @objid ("aa847c6c-b8ef-47e7-8815-3b3619d139a5")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

}
