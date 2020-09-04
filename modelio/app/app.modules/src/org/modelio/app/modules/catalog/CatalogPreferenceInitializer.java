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

package org.modelio.app.modules.catalog;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.modelio.app.modules.catalog.update.CatalogUpdatePreferencesPage;
import org.modelio.app.preferences.plugin.AppPreferences;

/**
 * 'mda.infra' preferences default values initializer.
 */
@objid ("07e9fa3d-7719-4782-9d03-5402f912a4af")
public class CatalogPreferenceInitializer extends AbstractPreferenceInitializer {
    @objid ("a03ebbe4-b098-4658-aa2c-17280eb91cfd")
    @Override
    public void initializeDefaultPreferences() {
        final IPreferenceStore prefs = AppPreferences.getPreferences();
        
        prefs.setDefault(CatalogUpdatePreferencesPage.CATALOG_SHOW_COMPATIBLE, true);
        prefs.setDefault(CatalogUpdatePreferencesPage.CATALOG_SHOW_LATEST, true);
    }

}
