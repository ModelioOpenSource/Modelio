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
package org.modelio.platform.preferences.plugin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.modelio.platform.rcp.extensionpoint.ExtensionPointContributionManager;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.osgi.service.log.LogLevel;

/**
 * {@link AbstractPreferenceInitializer Preference initializer} that scans 'org.modelio.platform.preferences' extension points for initializations.
 * <p>
 * The 'app.preference' preference node is used and initialized by many plugins.
 * This defeats 'org.eclipse.core.runtime.preferences' design that allows
 * each plugin to initialize its own preference node.
 * 
 * 
 * @author cma
 */
@objid ("6c08e380-1b36-4b7b-b595-22a5b95a31ab")
public class PreferencesInitializer extends AbstractPreferenceInitializer {
    @objid ("862187a1-323e-4ffa-be35-47f90d44fe5b")
    private static final String PREFS_XP = "org.modelio.platform.preferences";

    @objid ("fde999fe-55df-4e5b-ba56-b83528b6c5c5")
    private static final String ELMT_INIT = "initialization";

    @objid ("513eed00-b656-45ca-992d-ee5e0ec7e1e9")
    private static final String ELMT_PREF = "preference";

    @objid ("a1407a46-f3a6-4eb6-b300-0f7e8a0d5d97")
    private static final String ATT_KEY = "name";

    @objid ("806e28e6-a69c-4bb7-9db1-9627bb2f46d7")
    private static final String ATT_VAL = "value";

    @objid ("53dec8ff-23de-44d5-b836-d74a91b6db68")
    @Override
    public void initializeDefaultPreferences() {
        LogLevel oldLevel = PluginLogger.ensureLogLevel(LogLevel.DEBUG);
        try {
        
            IPersistentPreferenceStore preferences = Preferences.getPreferences();
        
            Preferences.LOG.debug("Initializing '%s' plugin preferences ...",Preferences.PLUGIN_ID);
        
            for (final IConfigurationElement elmt : new ExtensionPointContributionManager(PREFS_XP)
                    .getExtensions(ELMT_INIT)) {
                Preferences.LOG.debug(" - Initializing default values from '%s'...",elmt.getContributor().getName());
        
                for (IConfigurationElement prefEl : elmt.getChildren(ELMT_PREF)) {
                    final String key = prefEl.getAttribute(ATT_KEY);
                    final String val = prefEl.getAttribute(ATT_VAL);
        
                    Preferences.LOG.debug("   - default pref value '%s'='%s'", key, val);
        
                    preferences.setDefault(key, val);
                }
            }
        } finally {
            PluginLogger.setLogLevel(oldLevel);
        }
        
    }

}
