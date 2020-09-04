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

package org.modelio.editors.richnote.libreoffice.preferences;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.lib.loader.InstallationFinder;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.modelio.editors.richnote.libreoffice.plugin.LibreOfficeEditors;

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
            LibreOfficeEditors.PREFERENCES.setDefault(PreferenceConstants.P_OOOPATH, path);
        }
    }

}
