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

package org.modelio.platform.script.engine.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@objid ("00842344-a068-10b7-9941-001ec947cd2a")
public class ScriptEnginePlugin implements BundleActivator {
    @objid ("0084257e-a068-10b7-9941-001ec947cd2a")
    public static final String PLUGIN_ID = "org.modelio.platform.script.engine";

    @objid ("008426e6-a068-10b7-9941-001ec947cd2a")
    private static BundleContext context;

    @objid ("00842a10-a068-10b7-9941-001ec947cd2a")
    public static BundledMessages I18N;

    @objid ("00842b8c-a068-10b7-9941-001ec947cd2a")
    public static PluginLogger LOG;

    @objid ("008427f4-a068-10b7-9941-001ec947cd2a")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger((String)null));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("scriptengine"));
    }

    @objid ("008428bc-a068-10b7-9941-001ec947cd2a")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("0084295c-a068-10b7-9941-001ec947cd2a")
    public static BundleContext getContext() {
        return context;
    }

}
