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

package org.modelio.editors.richnote.libreoffice.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Singleton that represents the LibreOffice plugin.
 * <p>
 * Used to retrieve the current modeling session.<br/>
 * Clears the named style repository when the session is closed.
 */
@objid ("4c676e25-9a29-4247-8af6-5d2b2e6e62ce")
public class LibreOfficeEditors implements BundleActivator {
    /**
     * ID of this plugin.
     */
    @objid ("7269940c-9801-4fd2-a088-7b9e3b6090a8")
    public static final String PLUGIN_ID = "org.modelio.editors.richnote.libreoffice";

    @objid ("abb38e0c-4b96-4db0-9b6c-60ba06a5cb55")
    private static BundleContext context;

    /**
     * Logger for the plugin.
     */
    @objid ("1ab5d884-0fb4-42b1-ac97-23328fe42ff3")
    public static PluginLogger LOG;

    /**
     * Messages for the plugin.
     */
    @objid ("ae2338f3-806e-491c-8531-7480e4f4ff45")
    public static BundledMessages I18N;

    /**
     * The LibreOffices preferences.
     */
    @objid ("448a5bd9-6081-4da5-9cf7-e5d5a7a7ef17")
    public static IPersistentPreferenceStore PREFERENCES;

    @objid ("a01358a3-0a07-4575-b082-e3ef5287612e")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        LOG = new PluginLogger(service.getLogger(bundleContext.getBundle(), PLUGIN_ID));
        I18N = new BundledMessages(LOG, ResourceBundle.getBundle("richnotes_libreoffice"));
        
        // Since 4.0 use our own preference node instead of 'app.preferences' one.
        PREFERENCES = new ScopedPreferenceStore(InstanceScope.INSTANCE, PLUGIN_ID);
    }

    @objid ("f38e7cd8-6672-4f28-bb06-b412857c3dc4")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        context = null;
    }

    /**
     * @return the bundle context
     */
    @objid ("384df6dd-6fa9-4a96-a813-85243b85113b")
    public static BundleContext getContext() {
        return context;
    }

}
