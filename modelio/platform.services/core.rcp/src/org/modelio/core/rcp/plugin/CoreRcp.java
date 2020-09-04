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

package org.modelio.core.rcp.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@objid ("420aef92-de8e-4875-8aca-b6c3b1af2b16")
public class CoreRcp implements BundleActivator {
    @objid ("1dfb16de-c5cb-469b-9a51-f57200a1d755")
    public static final String PLUGIN_ID = "org.modelio.core.rcp";

    @objid ("dc83f8df-52e7-44d6-a1bd-3b54385b557b")
    private static BundleContext context;

    @objid ("49339434-c06e-42fb-828a-a802dfb82bac")
    public static BundledMessages I18N;

    @objid ("e7d74d15-84aa-4f73-a834-79fa9da695ef")
    public static PluginLogger LOG;

    @objid ("7518e62c-8301-407b-b1c1-3c8ce8642213")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger((String)null));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("corehelp"));
    }

    @objid ("65ddda0d-ad99-4b0d-86bf-81cba31841b4")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("8a3b46e8-0739-40f6-9601-ad2785772867")
    public static BundleContext getContext() {
        return context;
    }

}
