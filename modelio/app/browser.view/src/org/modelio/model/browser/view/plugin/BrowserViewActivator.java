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

package org.modelio.model.browser.view.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@objid ("2b6dd658-7585-49a4-bc64-634dde33a8a2")
public class BrowserViewActivator extends AbstractUIPlugin {
    /**
     * The plug-in ID
     */
    @objid ("a1490720-2c5b-4ab0-8728-4022b8c81dd8")
    public static final String PLUGIN_ID = "org.modelio.model.browser.view"; // $NON-NLS-1$

    @objid ("976b51e6-b6ef-4d2c-8754-7d1a43a0cc94")
    public static BundledMessages I18N;

    @objid ("5b1c623d-f4e6-496c-9ee3-8c083634c822")
    private static BundleContext context;

    @objid ("05651608-6147-4865-bdcd-519020a83eca")
    public static PluginLogger LOG;

    @objid ("f1c25450-bc17-47a2-8594-a57247608e83")
    @Override
    public void start(final BundleContext bundleContext) throws Exception {
        BrowserViewActivator.context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        BrowserViewActivator.LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), BrowserViewActivator.PLUGIN_ID));
        BrowserViewActivator.I18N = new BundledMessages(BrowserViewActivator.LOG, ResourceBundle.getBundle("browserview"));
    }

    @objid ("d808f0e7-f68e-46a0-9c38-1163c8f6a8b0")
    @Override
    public void stop(final BundleContext bundleContext) throws Exception {
        BrowserViewActivator.context = null;
    }

    @objid ("c2fffcc8-9ec1-4ca0-817f-b9df55284183")
    public static BundleContext getContext() {
        return BrowserViewActivator.context;
    }

}
