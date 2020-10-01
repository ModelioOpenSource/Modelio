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

package org.modelio.semantic.browser.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * The activator class controls the plug-in life cycle
 */
@objid ("1a4bc87e-2fc2-4f30-89d4-982b6bafc757")
public class SemanticBrowser extends AbstractUIPlugin {
    /**
     * The plug-in ID
     */
    @objid ("b015db44-359c-4b38-a0ca-3653b7b7cb8c")
    public static final String PLUGIN_ID = "org.modelio.app.semantic.browser"; // $NON-NLS-1$

    @objid ("d3ff6416-148d-4347-9161-976344815356")
    public static BundledMessages I18N;

    @objid ("0a613a7f-eb58-44b7-8269-80a053c6a7d7")
    private static BundleContext context;

    @objid ("529b5729-e0fb-4503-be3e-e416f6297a94")
    public static PluginLogger LOG;

    @objid ("d7f5ea21-9b8b-4463-a312-fab18664664a")
    @Override
    public void start(final BundleContext bundleContext) throws Exception {
        SemanticBrowser.context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        SemanticBrowser.LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), SemanticBrowser.PLUGIN_ID));
        SemanticBrowser.I18N = new BundledMessages(SemanticBrowser.LOG, ResourceBundle.getBundle("semanticbrowser"));
    }

    @objid ("9d1d7481-8aba-4578-867b-e3a1f7da90f3")
    @Override
    public void stop(final BundleContext bundleContext) throws Exception {
        SemanticBrowser.context = null;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path
     * 
     * @param path the path
     * @return the image descriptor
     */
    @objid ("c18f3e9a-cb80-4931-a0e3-2cd9ff8b313a")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(SemanticBrowser.PLUGIN_ID, path);
    }

}
