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

package org.modelio.diagram.symbol.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Singleton that represents the diagram symbol plugin.
 */
@objid ("ac4988ae-55b7-11e2-877f-002564c97630")
public class DiagramSymbol extends Plugin {
    /**
     * The plug-in ID
     */
    @objid ("ac4988b0-55b7-11e2-877f-002564c97630")
    public static final String PLUGIN_ID = "org.modelio.app.diagram.symbol";

    @objid ("ac4988b3-55b7-11e2-877f-002564c97630")
    private static DiagramSymbol _instance = new DiagramSymbol();

    /**
     * diagram.symbol plugin logger.
     */
    @objid ("9be3306f-58d7-11e2-8bfd-001ec947ccaf")
    public static PluginLogger LOG;

    /**
     * diagram.symbol plugin logger.
     */
    @objid ("bdce717f-58d7-11e2-8bfd-001ec947ccaf")
    public static BundledMessages I18N;

    @objid ("1c699882-58d7-11e2-8bfd-001ec947ccaf")
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        ServiceReference<ExtendedLogService> ref = context.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = context.getService(ref);
        LOG = new PluginLogger(service.getLogger(context.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("diagram_symbol"));
    }

    @objid ("1c699886-58d7-11e2-8bfd-001ec947ccaf")
    @Override
    public void stop(BundleContext context) throws Exception {
        super.stop(context);
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path
     * 
     * @param path the path
     * @return the image descriptor
     */
    @objid ("27abfcfb-22fa-4943-9baa-7859e3d6ba45")
    public static ImageDescriptor getImageDescriptor(final String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

}
