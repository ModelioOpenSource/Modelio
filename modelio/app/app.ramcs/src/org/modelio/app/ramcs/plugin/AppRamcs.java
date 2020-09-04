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

package org.modelio.app.ramcs.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@objid ("5992b881-212f-46c1-b1e2-910a2feda30a")
public class AppRamcs implements BundleActivator {
    @objid ("58e44226-2378-4a45-82a8-6e0d9785f832")
    public static final String PLUGIN_ID = "org.modelio.app.ramcs";

    @objid ("79c9b934-793b-432d-b729-e72864615b70")
    private static BundleContext context;

    @objid ("96b021a8-7015-4685-83a3-562877046928")
    public static BundledMessages I18N;

    @objid ("4d730946-b0ea-4a22-860d-0aff09d59184")
    public static PluginLogger LOG;

    @objid ("fb701c16-80c8-487b-8b83-06d63def386f")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        final ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        final ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger((String)null));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("appramcs"));
    }

    @objid ("8d10286c-631c-44f0-a355-58275493f34a")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("bdf65f62-c36a-4bd3-82cb-0c24f8809c30")
    public static BundleContext getContext() {
        return context;
    }

}
