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

package org.modelio.app.preferences;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.modelio.gproject.gproject.GProject;

/**
 * An IGProjectPreferenceStore is a preference store that is backed to the project.conf file of a GProject.
 * 
 * The redefinition of IPersistentPreferenceStore simply adds a getProject() accessor for convenience.
 */
@objid ("0cd5fed3-f0ed-4d45-ad3c-f753efb3bba3")
public interface IGProjectPreferenceStore extends IPersistentPreferenceStore {
    @objid ("b7e0a35d-e455-44c6-b70c-d68196a41b18")
    GProject getProject();

}
