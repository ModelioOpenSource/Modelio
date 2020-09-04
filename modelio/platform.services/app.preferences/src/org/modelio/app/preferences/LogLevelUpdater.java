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

package org.modelio.app.preferences;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.app.preferences.plugin.AppPreferences;
import org.modelio.utils.log.writers.PluginLogger;
import org.modelio.vbasic.log.IBasicLogger;
import org.osgi.service.log.LogLevel;

/**
 * Set the {@link PluginLogger} log level from the preferences.
 * @since 4.0
 */
@objid ("ce1e29eb-9946-4842-b0c0-63c5928f521c")
public class LogLevelUpdater {
    /**
     * Set the {@link PluginLogger} log level from the {@link AppPreferences#getPreferences()} preference store.
     */
    @objid ("f77725b3-9cc6-4f19-9567-cacc79050c46")
    public static void setLogLevelFromPreferences() {
        // Get log level from preferences
        int preferedLogLevel = AppPreferences.getPreferences().getInt(AppSharedPreferencesKeys.LOGLEVEL_PREFKEY);
        LogLevel logLevel = null;
        switch(preferedLogLevel) {
                case 1: logLevel = LogLevel.ERROR; break;
                case 2: logLevel = LogLevel.WARN; break;
                case 3: logLevel = LogLevel.INFO; break;
                case 4: logLevel = LogLevel.DEBUG; break;
                default:
                    // Keep existing value
                    logLevel = PluginLogger.getLogLevel();
        }
        
        // Temporarily force LogLevel.INFO to trace the log level changing message.
        //PluginLogger.setLogLevel(LogLevel.INFO);
        //AppPreferences.LOG.info("Log level: %s", logLevel.toString());
        PluginLogger.setLogLevel(logLevel);
        
        
        // Report the level on the vcore logger
        switch (logLevel) {
        case ERROR: // 1
            org.modelio.vbasic.log.Log.getLogger().setLevel(IBasicLogger.ERROR);
            break;
        case WARN: // 2
            org.modelio.vbasic.log.Log.getLogger().setLevel(IBasicLogger.WARNING);
            break;
        case INFO: // 3
            org.modelio.vbasic.log.Log.getLogger().setLevel(IBasicLogger.TRACE);
            break;
        case DEBUG: // 4
            org.modelio.vbasic.log.Log.getLogger().setLevel(IBasicLogger.TRACE);
            break;
        default:
            break;
        }
    }

}
