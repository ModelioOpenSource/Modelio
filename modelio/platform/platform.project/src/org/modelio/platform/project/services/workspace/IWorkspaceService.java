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
package org.modelio.platform.project.services.workspace;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.platform.project.services.openproject.IProjectServiceAccess;
import org.modelio.vbasic.progress.IModelioProgress;

@objid ("1a9a8135-191e-4441-b664-564136d9e00b")
public interface IWorkspaceService {
    /**
     * Changes the current workspace to the given path. Changing the current workspace is not possible when a project is currently opened.
     * @param path the path of the new workspace to use. Must be a valid directory path.
     * @throws IllegalArgumentException If the given path is cannot be used as a workspace path.
     * @throws IllegalStateException If a project is currently opened.
     */
    @objid ("0082e218-acc2-103b-a520-001ec947cd2a")
    void changeWorkspace(final Path path) throws IllegalArgumentException, IllegalStateException;

    /**
     * Deletes a project from the workspace. This operation is definitive. The project must not be currently opened.
     * @param projectToDelete the project to delete.
     * @throws IOException in case of I/O failure.
     * @throws FileSystemException in case of file system error.
     * @Throws IllegalStateException If <code>project</code> is currently opened.
     */
    @objid ("008838ee-8c65-103c-a520-001ec947cd2a")
    void deleteProject(ProjectDescriptor projectToDelete) throws IOException, FileSystemException;

    /**
     * Exports the whole contents of a project into a single archive file.
     * @param project the project to export. It must not be opened.
     * @param archivePath the path of the file archive to produce.
     * @param monitor a progress monitor.
     * @throws IOException in case of I/O failure.
     */
    @objid ("0088453c-8c65-103c-a520-001ec947cd2a")
    void exportProject(ProjectDescriptor project, Path archivePath, IModelioProgress monitor) throws IOException;

    /**
     * Fires a refresh of the workspace contents. The workspace directory will be re-scanned for new/removed projects and the workspace internal data structures updated accordingly. The project named 'projectToSelect' (if not null) is selected in workspace
     */
    @objid ("0083325e-acc2-103b-a520-001ec947cd2a")
    void refreshWorkspace(String projectToSelect);

    /**
     * Rename a project and adapt its directory to match the new name.
     * @param projectDescriptor the project to edit.
     * @param name the new name.
     * @throws IOException in case of I/O failure.
     * @throws FileSystemException in case of file system error.
     */
    @objid ("0c049288-0008-4133-a8ae-527b5c3a01de")
    void renameProject(ProjectDescriptor projectDescriptor, String name) throws IOException, FileSystemException;

    @objid ("06d76150-2225-468b-bbb2-38a8323cc1a6")
    Path getWorkspace();

    @objid ("cc81759e-92a1-44cc-9edd-17099ad3ca7e")
    void configure(IProjectServiceAccess service);

}
