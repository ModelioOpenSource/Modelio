/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.admtool.plugin;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.equinox.log.ExtendedLogService;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.app.preferences.AppSharedPreferencesKeys;
import org.modelio.app.preferences.LogLevelUpdater;
import org.modelio.app.preferences.plugin.AppPreferences;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.utils.log.writers.PluginLogger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * {@value #PLUGIN_ID} plugin.
 */
@objid ("33beb656-1c91-4a87-ae51-11ffff75a345")
public class AdmTool extends AbstractUIPlugin {
    /**
     * The plugin identifier.
     */
    @objid ("4a890383-81b5-4491-8712-d5579777f905")
    public static final String PLUGIN_ID = "org.modelio.admtool"; // $NON-NLS-1$

    /**
     * {@value #PLUGIN_ID} plugin logger.
     */
    @objid ("ea10a913-5824-4387-a532-51f6aa56a300")
    public static PluginLogger LOG;

    /**
     * {@value #PLUGIN_ID} translated messages.
     */
    @objid ("2b5f7786-6f98-41c3-b16c-7e566b8d414a")
    public static BundledMessages I18N;

    @objid ("418ffdaa-7867-4312-8ca2-c66096e94634")
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        ServiceReference<ExtendedLogService> ref = bundleContext.getServiceReference(ExtendedLogService.class);
        ExtendedLogService service = bundleContext.getService(ref);
        AdmTool.LOG = new PluginLogger(service.getLogger((String) null));
        AdmTool.I18N = new BundledMessages(AdmTool.LOG, ResourceBundle.getBundle("admtool"));
        
        AppPreferences.getPreferences().addPropertyChangeListener(new LogLevelPreferenceListener());
    }

    @objid ("a5fef189-2227-40b0-8d45-c67df88b2ee1")
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        // Nothing to do
    }

    /**
     * Updates the log level when log level preference changes.
     */
    @objid ("a6b15699-4f7a-4421-b141-41dc0b2e03c9")
    private static final class LogLevelPreferenceListener implements IPropertyChangeListener {
        @objid ("70b89178-1219-44b7-91fa-3aef99a25a46")
        public LogLevelPreferenceListener() {
            super();
        }

        @objid ("4c7e43a5-403a-411f-b10c-8979e9d2a31c")
        @Override
        public void propertyChange(PropertyChangeEvent event) {
            if (AppSharedPreferencesKeys.LOGLEVEL_PREFKEY.equals(event.getProperty())) {
                /*
                 * Updates the log level from the preferences.
                 */
                LogLevelUpdater.setLogLevelFromPreferences();
            }
        }

    }

}
