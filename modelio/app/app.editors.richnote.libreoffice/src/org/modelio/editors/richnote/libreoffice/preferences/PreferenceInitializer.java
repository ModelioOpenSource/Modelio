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

package org.modelio.editors.richnote.libreoffice.preferences;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.lib.loader.InstallationFinder;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.modelio.editors.richnote.libreoffice.plugin.LibreOfficeEditors;
import org.modelio.platform.preferences.plugin.Preferences;
import static org.modelio.editors.richnote.libreoffice.preferences.PreferenceConstants.P_OOOPATH;

/**
 * Class used to initialize default preference values.
 */
@objid ("c31d223e-a419-47a3-81f8-9ef3974a5d02")
public class PreferenceInitializer extends AbstractPreferenceInitializer {
    @objid ("d3c9f008-c5ac-47a6-95ad-69da0564c8f0")
    @Override
    public void initializeDefaultPreferences() {
        String path = InstallationFinder.getPath();
        if (path != null) {
            LibreOfficeEditors.PREFERENCES.setDefault(P_OOOPATH, path);
            LibreOfficeEditors.LOG.info("Found LibreOffice at '%s'", path);
        }
        
        // 3.8 -> 4.0 Migration :
        // LibreOffice plugin now uses its own preference node instead of 'app.preferences' one.
        // copy any existing value to our own preference node.
        migrateTo40(path);
    }

    /**
     * Apply 3.8 -> 4.0 Migration
     * <p>
     * LibreOffice plugin now uses its own preference node instead of 'app.preferences' one.
     * copy any existing value to our own preference node.
     * 
     * @param foundOooPath The found LibreOffice path
     */
    @objid ("c580d862-316c-4516-98dd-7be6fe852f81")
    private void migrateTo40(String foundOooPath) {
        IPersistentPreferenceStore appPrefPreferences = Preferences.getPreferences();
        
        if (appPrefPreferences.contains(P_OOOPATH)) {
            String appPrefValue = appPrefPreferences.getString(P_OOOPATH);
            LibreOfficeEditors.LOG.debug("Found 'app.pref' '%s' key = '%s'", P_OOOPATH, appPrefValue);
            if (! Objects.equals(appPrefValue, foundOooPath)) {
                LibreOfficeEditors.LOG.info("Migrating 'app.pref' '%s' key = '%s'", P_OOOPATH, appPrefValue);
        
                // copy to our node
                LibreOfficeEditors.PREFERENCES.setValue(P_OOOPATH, appPrefValue);
                // remove from previous
                appPrefPreferences.setDefault(P_OOOPATH, null);
                appPrefPreferences.setToDefault(P_OOOPATH);
            }
        }
    }

}
