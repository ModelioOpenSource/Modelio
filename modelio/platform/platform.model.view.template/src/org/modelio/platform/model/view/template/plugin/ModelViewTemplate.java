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
package org.modelio.platform.model.view.template.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * This class represents the diagram.creation plugin.
 */
@objid ("50f0d329-adb8-418d-b495-06fc129bfdc2")
public class ModelViewTemplate implements BundleActivator {
    /**
     * ID of the plugin.
     */
    @objid ("0136be14-cfeb-4fa5-893c-4dbb79eef40b")
    public static final String PLUGIN_ID = "org.modelio.platform.model.view.template";

    @objid ("4b70a7c8-07f1-4c4e-ad3c-0bce43db3602")
    private static BundleContext context;

    /**
     * The plug-in's i18n bundle.
     */
    @objid ("72322e0c-790a-42de-846b-db82db6c3ccb")
    public static BundledMessages I18N = null;

    /**
     * The plug-in's logger.
     */
    @objid ("a2281a3c-bebb-4884-b349-2dc932b60c8a")
    public static PluginLogger LOG = null;

    @objid ("a2ba8efd-f315-4fa1-ba50-c31a8d8b0a78")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("modelviewtemplate"));
        
    }

    @objid ("3f134164-21f5-48c2-9896-d2f418fe0712")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("dcafc1fb-fa88-4efe-9786-75fd9c9dca3b")
    public static BundleContext getContext() {
        return context;
    }

}
