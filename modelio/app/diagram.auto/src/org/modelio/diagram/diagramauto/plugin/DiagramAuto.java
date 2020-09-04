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

package org.modelio.diagram.diagramauto.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * core.utils plugin class.
 */
@objid ("4eaed3af-8ee0-4924-81da-334aa3ed2742")
public class DiagramAuto implements BundleActivator {
    /**
     * Plugin ID.
     */
    @objid ("abac7a44-1d85-41aa-9ed0-73b298f62fde")
    public static final String PLUGIN_ID = "org.modelio.diagram.diagramauto";

    @objid ("19319afb-99d2-4cf1-8a6f-f74d011a3a70")
    public static BundleContext context;

    /**
     * Translated messages bundle.
     */
    @objid ("62e0bc12-d52a-4a53-91c5-04d6c7a68cf5")
    public static final ResourceBundle I18N = ResourceBundle.getBundle("diagramauto");

    @objid ("1d2558dc-b189-4c80-9f9e-caf570eeea3d")
    public static PluginLogger LOG = null;

    @objid ("2abf16bd-f0de-4bab-b483-39e0209e3d61")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        DiagramAuto.context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
    }

    @objid ("9d4cfffb-8da2-4feb-b385-f4320b3c6da4")
    @Override
    public void stop(BundleContext ctx) throws Exception {
        DiagramAuto.context = null;
    }

}
