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
package org.modelio.diagram.outline.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@objid ("082dfece-8e8c-4e80-a38e-a587e5df4611")
public class DiagramOutline implements BundleActivator {
    /**
     * ID of the DiagramOutline plugin.
     */
    @objid ("53de227e-b521-4130-9a09-e5f6079749bb")
    public static final String PLUGIN_ID = "org.modelio.app.diagram.outline";

    @objid ("9cd42768-c991-48a7-af04-c3209fb0d818")
    private static BundleContext context;

    @objid ("6193cee4-7543-4732-aba5-a14e1d79c022")
    public static BundledMessages I18N = null;

    @objid ("c533e300-6666-4f56-a088-435aaa3fe112")
    public static PluginLogger LOG = null;

    @objid ("03ea42a2-9ae4-4787-8f45-bd326d948e4d")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("diagramoutline"));
        
    }

    @objid ("b485ccac-8c60-4a00-b9fa-2427c220b94c")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    @objid ("c26716ef-c0a5-4bec-b2a3-652d2ef8ea37")
    public static BundleContext getContext() {
        return context;
    }

}
