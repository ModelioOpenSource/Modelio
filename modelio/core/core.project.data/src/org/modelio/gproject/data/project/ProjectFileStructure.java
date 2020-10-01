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

package org.modelio.gproject.data.project;

import java.io.IOException;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;

/**
 * This class manages the file structure of the project space of a GProject.
 * <p>
 * The project physical structure is documented on <a
 * href="https://forge-modelio.softeam.com/projects/modelio-phoenix/wiki/Project_space_structure"
 * >https://forge-modelio.softeam.com/projects/modelio-phoenix/wiki/Project_space_structure</a>.
 * <p>
 * 
 * @Since 3.8.1
 */
@objid ("b3350d05-7724-4234-baee-a81afb6e922f")
public class ProjectFileStructure {
    /**
     * Sub directory name relative to the 'data' directory where project configuration data are stored.
     * 
     * Files in this directory are good candidates for synchronization with a shared resources repository (Constellation).
     */
    @objid ("6549c8af-e752-420f-86f6-6123b78bc199")
    private static final String CONFIG_SUBDIR = ".config";

    /**
     * Project sub directory name where persistent data are stored.
     * TODO should be private
     */
    @objid ("8b9259ee-1fc0-48b3-a321-523f7f688fcd")
    public static final String DATA_SUBDIR = "data";

    /**
     * {@value #DATA_SUBDIR} sub directory where module data are stored.
     * TODO should be private
     */
    @objid ("853710c5-7ff9-458d-a93f-02b41196a0ec")
    public static final String MODULES_SUBDIR = "modules";

    /**
     * Runtime directory name.
     */
    @objid ("e03f536b-6621-4b5a-9a76-807028993ade")
    private static final String RUNTIME_SUBDIR = ".runtime";

    /**
     * Project directory.
     * <p>
     * It is the project.conf location and the directory of the project's ProjactSpace in the workspace.
     */
    @objid ("d8896ac5-45b8-4cd9-b29f-09d9325a3578")
    private final Path projectPath;

    @objid ("4010eea1-f4dc-446a-b349-774442a8817f")
    public ProjectFileStructure(Path path) {
        this.projectPath = path;
    }

    /**
     * Get the path to a .jmdac file stored locally in the project.
     * 
     * @param moduleName the module name.
     * @param version the module version.
     * @return the module .jmdac file path
     * @throws java.io.IOException the the file couldn't be retrieved.
     */
    @objid ("d9ff18e3-11d1-4ca9-811d-07f691ef3fd0")
    public Path getModuleBackupArchivePath(String moduleName, Version version) throws IOException {
        return getModuleBackupDir(moduleName).resolve(moduleName + "_" + version.toString() + ".jmdac");
    }

    /**
     * Get the path where a .jmdac file is stored locally in the project for a module.
     * 
     * @param moduleName the module name.
     * @return a directory containing .jmdac files
     * @throws java.io.IOException the the file couldn't be retrieved.
     */
    @objid ("c501fc39-9b97-458f-83ff-28837d210627")
    public Path getModuleBackupDir(String moduleName) throws IOException {
        return this.projectPath.resolve(ProjectFileStructure.DATA_SUBDIR).resolve("backups").resolve(ProjectFileStructure.MODULES_SUBDIR).resolve(moduleName);
    }

    @objid ("c453fffd-1f47-4964-b202-9e336b7ab5b3")
    public Path getNsUseRepositoryPath() {
        return getProjectDataPath().resolve("localmodel");
    }

    @objid ("3b7fcae9-7b30-4651-92b7-c5c1cd9cc00d")
    public Path getProjectConfFile() {
        return this.projectPath.resolve("project.conf");
    }

    @objid ("9e0daa75-5ebf-4f5b-ae76-d3d3f3abf842")
    public Path getProjectDataConfigPath() {
        return this.projectPath.resolve(ProjectFileStructure.DATA_SUBDIR).resolve(ProjectFileStructure.CONFIG_SUBDIR);
    }

    /**
     * Get the directory where the project data are managed by Modelio (model, fragments modules and so on). The internal structure
     * of this directory is left to its users (fragments, modules, CMS tools ...) User generated data like source code,
     * documentation are never stored there.
     * 
     * @return the path where the 'data' directory resides.
     */
    @objid ("fabd4f37-0593-4a7b-aa15-604c6417251e")
    public Path getProjectDataPath() {
        return this.projectPath.resolve(ProjectFileStructure.DATA_SUBDIR);
    }

    @objid ("9ee777d5-4122-465a-bfdc-c41f5e4c33c0")
    public Path getProjectPath() {
        return this.projectPath;
    }

    @objid ("3ec24b19-cb10-4fb8-924f-4edbeb7f1fc1")
    public Path getProjectRuntimePath() {
        return this.projectPath.resolve(ProjectFileStructure.RUNTIME_SUBDIR);
    }

}
