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

package org.modelio.diagram.editor.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * This class represents the diagram.editor plugin.
 */
@objid ("0a20d54f-21a7-11e2-95fe-001ec947c8cc")
public class DiagramEditor implements BundleActivator {
    /**
     * ID of the DiagramEditor plugin.
     */
    @objid ("150b14c6-21a7-11e2-95fe-001ec947c8cc")
    public static final String PLUGIN_ID = "org.modelio.diagram.editor";

    @objid ("150b14c4-21a7-11e2-95fe-001ec947c8cc")
    private static BundleContext context;

    @objid ("150b14ca-21a7-11e2-95fe-001ec947c8cc")
    public static BundledMessages I18N = null;

    @objid ("150b14cb-21a7-11e2-95fe-001ec947c8cc")
    public static PluginLogger LOG = null;

    @objid ("150b14c5-21a7-11e2-95fe-001ec947c8cc")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("diagrameditor"));
    }

    @objid ("150b14cc-21a7-11e2-95fe-001ec947c8cc")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("150b14cd-21a7-11e2-95fe-001ec947c8cc")
    public static BundleContext getContext() {
        return context;
    }

}
