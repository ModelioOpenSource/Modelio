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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.preference.PreferenceStore;
import org.modelio.gproject.gproject.GProject;
import org.modelio.platform.preferences.plugin.Preferences;
import org.modelio.vbasic.files.FileUtils;

/**
 * The AppStatePreferenceStore stores the state of the application in the context of a project.
 * Typical use: opened diagrams, shown views etc..
 */
@objid ("f698d9f8-7233-48f8-b4f5-22e9fd647d3f")
public class AppStatePreferenceStore extends PreferenceStore {
    @objid ("ab76bea7-2b14-4138-9e52-b373031c80c4")
    public AppStatePreferenceStore(GProject project) {
        super();
        
        Path p = project.getProjectFileStructure().getProjectRuntimePath().resolve(".save");
        setFilename(p.toString());
        try {
            // Create the file only if it does not exist
            Files.createDirectories(project.getProjectFileStructure().getProjectRuntimePath());
            p.toFile().createNewFile();
        
            load();
        } catch (IOException e) {
            Preferences.LOG.warning("Failed loading application state from '%s': %s", p, FileUtils.getLocalizedMessage(e));
            Preferences.LOG.debug(e);
        }
    }

}
