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
package org.modelio.editors.richnote.microsoft.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Singleton that represents the LibreOffice plugin.
 * <p>
 * Used to retrieve the current modeling session.<br/>
 * Clears the named style repository when the session is closed.
 */
@objid ("43a55a6c-1fa0-43b0-a55c-dcb952dc4b05")
public class MicrosoftEditors implements BundleActivator {
    /**
     * ID of this plugin.
     */
    @objid ("61c90c5c-b312-4248-aa78-c9a67aa970a7")
    public static final String PLUGIN_ID = "org.modelio.edition.extern.microsoft";

    @objid ("e02de8b9-1538-4c40-991e-2b7bf48f386d")
    private static BundleContext context;

    /**
     * Logger
     */
    @objid ("44d60432-2bcf-42e0-a3c3-2fc1b4cbe9ad")
    public static PluginLogger LOG;

    /**
     * Messages for the plugin.
     */
    @objid ("7aadb0c5-8883-42c5-b33c-15912722de56")
    public static BundledMessages I18N;

    @objid ("387c54b2-2dde-40cc-b6d3-3fa9ab65b8ac")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("edition_extern_microsoft"));
        
    }

    @objid ("5da942cf-0ca1-422b-9abb-302882e0d6b5")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    /**
     * @return the bundle's execution context
     */
    @objid ("2031a600-f463-4c28-a450-e0b182f10504")
    public static BundleContext getContext() {
        return context;
    }

}
