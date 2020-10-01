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

package org.modelio.platform.search.engine.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@objid ("67b6228a-8b33-4b20-8b29-90d6cebd123a")
public class SearchEngine implements BundleActivator {
    @objid ("9d550847-c6b7-4025-a79c-25b9660ab669")
    public static final String PLUGIN_ID = "org.modelio.platform.search.engine";

    @objid ("fa875103-3f39-4451-96e9-24e3dc5949ea")
    private static BundleContext context;

    @objid ("dcf470a7-93fb-4cc2-af2a-eade4448c4e7")
    public static BundledMessages I18N;

    @objid ("15991694-78aa-4e07-877e-46494bd26113")
    public static PluginLogger LOG;

    @objid ("2a5338e6-1733-49b1-8779-0647ea868fb0")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger((String)null));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("modelsearch"));
    }

    @objid ("8f1b14b3-ea91-4edf-9995-f1ef94a48012")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("72eb7b06-5643-4a77-83e1-f6bf51d2b29c")
    public static BundleContext getContext() {
        return context;
    }

}
