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

package org.modelio.creation.wizard.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * This class represents the diagram.creation.wizard plugin.
 */
@objid ("c8c21a32-6c67-495c-9033-0bf30ed1c22c")
public class CreationWizard implements BundleActivator {
    /**
     * ID of the plugin.
     */
    @objid ("046daf88-2fd5-41db-ad46-9816e51cc82f")
    public static final String PLUGIN_ID = "org.modelio.app.creation.wizard";

    @objid ("ea439f44-88ca-4f71-a3e2-0d9879dc279a")
    private static BundleContext context;

    /**
     * The plug-in's i18n bundle.
     */
    @objid ("432a78fb-8c1e-4517-ac85-88488c1482f3")
    public static BundledMessages I18N = null;

    /**
     * The plug-in's logger.
     */
    @objid ("f8ed9070-a899-4dfe-8d88-097cf6fe3212")
    public static PluginLogger LOG = null;

    @objid ("9139bde3-23a7-44af-9765-32a29fa99a05")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("diagramcreationwizard"));
    }

    @objid ("c3b630e3-5351-4a40-a81a-b56499e1e63e")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("aaba125a-e826-4147-a53a-2c246148c1a0")
    public static BundleContext getContext() {
        return context;
    }

}
