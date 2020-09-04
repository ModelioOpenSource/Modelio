/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.admtool.preferences;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.modelio.app.preferences.AppSharedPreferencesKeys;
import org.modelio.app.preferences.plugin.AppPreferences;
import org.osgi.service.log.LogService;

/**
 * Preferences initializer.
 */
@objid ("1a3f4331-8cf3-4d60-bece-a3ec16972e0b")
public class AdminPreferencesInitializer extends AbstractPreferenceInitializer {
    @objid ("fc890909-15f8-4ed4-af4e-255aa22d1a28")
    @Override
    public void initializeDefaultPreferences() {
        AppPreferences.getPreferences().setDefault(AppSharedPreferencesKeys.LOGLEVEL_PREFKEY, LogService.LOG_INFO);
        AppPreferences.getPreferences().setDefault(AppSharedPreferencesKeys.SHOWADMTOOLS_PREFKEY, false);
    }

}
