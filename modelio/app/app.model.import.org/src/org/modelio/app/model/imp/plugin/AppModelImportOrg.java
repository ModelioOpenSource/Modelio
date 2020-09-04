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

package org.modelio.app.model.imp.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@objid ("eb6dd954-c0f0-4d03-9455-9e28c728ce25")
public class AppModelImportOrg implements BundleActivator {
    @objid ("5e3f9250-800e-47de-9e31-3a593f314653")
    public static final String PLUGIN_ID = "org.modelio.app.model.import";

    @objid ("dc60e487-66ee-435c-bbed-afdd5aef446a")
    private static BundleContext context;

    @objid ("51f25ce8-569c-47e0-9fc9-a9d098c01406")
    public static BundledMessages I18N;

    @objid ("3a4ee19b-6493-4434-a04b-cf88206b9528")
    public static PluginLogger LOG;

    @objid ("5e12462e-f0cd-4478-b9ba-703f6591059d")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        AppModelImportOrg.context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        AppModelImportOrg.LOG = new PluginLogger(service.getLogger(null));
        AppModelImportOrg.I18N = new BundledMessages(AppModelImportOrg.LOG, ResourceBundle.getBundle("appmodelimport"));
    }

    @objid ("b45a0502-c0d7-4c1d-9e46-db289742ea32")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        AppModelImportOrg.context = null;
    }

    @objid ("d1a26261-084b-49ce-bba6-dee25f396d67")
    public static BundleContext getContext() {
        return AppModelImportOrg.context;
    }

}
