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
package org.modelio.platform.preferences;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.preferences.plugin.Preferences;

/**
 * Keys for Modelio application scoped preferences.
 * 
 * Note that only keys that are used by several plugins should be defined here.
 * 
 * A plugin that defines global preferences in a private way (ie no other plugin
 * uses them) is not supposed to lay its eggs here!
 * 
 * <p>Usage example:
 * <code>
 * AppPreferences.getPreferences().getBoolean(AppSharedPreferencesKeys.SHOWADMTOOLS_PREFKEY);
 * </code>
 * </p>
 */
@objid ("788294a9-d735-4241-8477-4c94dd48c664")
public interface AppSharedPreferencesKeys {
    /**
     * Preferences key for LogLevel.
     * 
     * Value type is integer.
     */
    @objid ("36e87876-783c-4121-9767-f2dcf842e957")
    public static final String LOGLEVEL_PREFKEY = "Log.LogLevel";

    /**
     * Preference key for the Modelio update site.
     * 
     * Value type is String (an URL)
     */
    @objid ("700d9f15-6173-4ad2-8e77-4d98edfa6ba5")
    public static final String UPDATESITE_PREFKEY = "Update.Site";

    /**
     * Preference key for enabling/disabling the administration commands.
     * 
     * Value type is boolean.
     */
    @objid ("da6894f8-4ba5-46a5-ab2c-b18fd69ca3f0")
    public static final String SHOWADMTOOLS_PREFKEY = "AdmTools.Show";

}
