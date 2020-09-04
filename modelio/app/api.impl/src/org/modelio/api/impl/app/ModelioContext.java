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

package org.modelio.api.impl.app;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.Platform;
import org.modelio.api.modelio.IModelioContext;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.gproject.gproject.GProject;
import org.modelio.vbasic.version.Version;
import org.modelio.version.ModelioVersion;

/**
 * Implementation of {@link IModelioContext}.
 */
@objid ("0c8c9168-dc59-4af9-97d4-72d44b05742f")
public class ModelioContext implements IModelioContext {
    @objid ("d1720ac9-e9c4-4820-8fea-464db62453ea")
    private IProjectService projectService;

    @objid ("043b6707-6d21-4449-9b07-032f9f3cc2e3")
    public ModelioContext(final IProjectService projectService) {
        this.projectService = projectService;
    }

    @objid ("941c8207-80c9-4515-bce9-ca32981d4a18")
    @Override
    public File getInstallPath() {
        return new File(Platform.getInstallLocation().getURL().getPath());
    }

    /**
     * Get the language defined for resources.
     * <p>
     * The returned value is the value that Locale.getDefault().getLanguage()
     * returns if this value is supported by Modelio. Otherwise 'us' is returned.
     * 
     * @return a String containing the language used for Modelio resources.
     */
    @objid ("8b3ba8b8-4d09-4d8e-9064-983191cc6b26")
    @Override
    public String getLanguage() {
        String language = Platform.getNL();
        if (language.contains("_")) {
            return Platform.getNL().substring(0, language.lastIndexOf("_"));
        }
        return Platform.getNL();
    }

    @objid ("c15f7253-d64d-4950-a048-85b14d9708ef")
    @Deprecated
    @Override
    public File getProjectSpacePath() {
        GProject openedProject = this.projectService.getOpenedProject();
        if (openedProject != null) {
            return openedProject.getProjectFileStructure().getProjectPath().toFile();
        }
        return null;
    }

    /**
     * Get the version of the current Modelio
     * 
     * @return an object of the Version class that represents the version of the current Modelio.
     */
    @objid ("1fac425c-de46-4e1e-b4d3-45a97af65df0")
    @Override
    public Version getVersion() {
        return ModelioVersion.VERSION;
    }

    @objid ("9675b57d-2e1b-41a6-9a42-abaca7ded8ed")
    @Override
    public File getWorkspacePath() {
        return this.projectService.getWorkspace().toFile();
    }

}
