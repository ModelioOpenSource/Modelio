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

package org.modelio.script.options;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Simple class used to store the current state of the LinkEditor options.
 * <p>Relies on {@link IPreferenceStore} for persistence.</p>
 */
@objid ("ed5b9bdc-3126-41b6-8f3e-4a7751ee79c5")
public class ScriptOptions {
    @objid ("c80f76da-5f65-476a-8e62-d2707b8b0c7f")
    private static final String DEBUG_MODE = "org.modelio.script.property.DebugMode";

    @objid ("58c3d561-186b-44fc-bec7-032a471ba085")
    private static final String DEBUG_MODE_DEFAULT = "false";

    @objid ("9630fcd6-84f7-4e71-93d6-b981d4451b4d")
    private IPreferenceStore preferences;

    @objid ("bacb1d17-9556-4e4a-93cd-6ce10b6132c6")
    public Boolean getDebugMode() {
        if(this.preferences != null){
            return this.preferences.getString(DEBUG_MODE).equals("true");
        }
        return false;
    }

    @objid ("3de96171-93cc-4be8-876c-39d78ff54d8b")
    public void setDebugMode(Boolean value) {
        if(this.preferences != null){
            this.preferences.setValue(DEBUG_MODE, value ? "true" : "false");
        }
    }

    @objid ("6d0d5a01-6665-43cb-8ffe-1cc7ff84d042")
    public ScriptOptions(IPreferenceStore preferences) {
        this.preferences = preferences;
               
        if (preferences != null) {
            preferences.setDefault(DEBUG_MODE, DEBUG_MODE_DEFAULT);
        }
    }

}
