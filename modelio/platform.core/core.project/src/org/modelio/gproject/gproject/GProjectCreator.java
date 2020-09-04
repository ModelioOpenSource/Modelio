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

package org.modelio.gproject.gproject;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.data.project.ProjectDescriptorWriter;
import org.modelio.vbasic.version.Version;
import org.modelio.version.ModelioVersion;

/**
 * Builds a new empty project in a directory.
 * 
 * @author cmarin
 */
@objid ("2d913552-ab32-11e1-8392-001ec947ccaf")
public class GProjectCreator {
    /**
     * Builds a new empty project space for the current version of Modelio.
     * <p>
     * The created project won't have any fragment.
     * <p>
     * The <i>projectPath</i> can then be given to
     * {@link GProjectFactory#openProject GProjectFactory.openProject()} to obtain a {@link GProject}.
     * @param projectName the project name.
     * @param projectPath the project path. Must be a directory that should be empty or
     * does not exist yet.
     * @return The created project descriptor
     * @throws java.io.IOException in case of I/O error.
     */
    @objid ("41d51dff-ab36-11e1-8392-001ec947ccaf")
    public static ProjectDescriptor buildEmptyProject(final String projectName, final Path projectPath) throws IOException {
        
        return buildEmptyProject(projectName, projectPath, ModelioVersion.VERSION);
    }

    /**
     * Builds a new empty project space with a given Modelio version.
     * <p>
     * The created project won't have any fragment.
     * <p>
     * The <i>projectPath</i> can then be given to
     * {@link GProjectFactory#openProject GProjectFactory.openProject()} to obtain a {@link GProject}.
     * @param projectName the project name.
     * @param projectPath the project path. Must be a directory that should be empty or
     * does not exist yet.
     * @param modelioVersion the Modelio version
     * @return The created project descriptor
     * @throws java.io.IOException in case of I/O error.
     * @since 3.4.1
     */
    @objid ("25c59074-2364-4ff7-8647-875067f01374")
    public static ProjectDescriptor buildEmptyProject(final String projectName, final Path projectPath, Version modelioVersion) throws IOException {
        // Create directory and the 'project.conf' file.
        Files.createDirectories(projectPath);
        Path confFilePath = projectPath.resolve("project.conf");
        
        if (Files.isRegularFile(confFilePath)) {
            throw new FileAlreadyExistsException(confFilePath.toString(), null, "A project already exists here.");
        }
        
        ProjectDescriptor desc = ProjectDescriptor.createEmpty(projectName, projectPath, modelioVersion);
        new ProjectDescriptorWriter().write(desc, confFilePath);
        return desc;
    }

}
