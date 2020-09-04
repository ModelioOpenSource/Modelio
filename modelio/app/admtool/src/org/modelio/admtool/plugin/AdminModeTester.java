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

package org.modelio.admtool.plugin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.app.preferences.AppSharedPreferencesKeys;
import org.modelio.app.preferences.plugin.AppPreferences;

/**
 * Property tester implementation, called by the platform to set visibility of the 'admin' menu.
 */
@objid ("d8ad7d7a-df88-4b35-bd84-28230e49866e")
public class AdminModeTester extends PropertyTester {
    @objid ("85455b1f-127a-4b07-9f66-19df40a17881")
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (!(receiver instanceof ISelection)) {
            return false;
        }
        
        switch (property) {
        case "adminmode":
            final IPreferenceStore preferenceStore = AppPreferences.getPreferences();
            final boolean value = preferenceStore.getBoolean(AppSharedPreferencesKeys.SHOWADMTOOLS_PREFKEY);
            return value;
        default:
            throw new IllegalArgumentException(property + " property not supported by " + getClass().getSimpleName());
        }
    }

}
