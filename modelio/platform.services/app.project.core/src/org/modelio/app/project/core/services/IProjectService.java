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

package org.modelio.app.project.core.services;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.modelio.app.core.project.ICurrentProjectService;
import org.modelio.app.preferences.IGProjectPreferenceStore;
import org.modelio.app.project.core.creation.IProjectCreationData;
import org.modelio.app.project.core.creation.IProjectCreator;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.FragmentConflictException;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.gproject.GProjectAuthenticationException;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.progress.IModelioProgress;

/**
 * IProjectService: modelio application scoped services dealing with
 * <ul>
 * <li>workspace management</li>
 * <li>project management</li>
 * 
 * IProjectService has a singleton instance available for injection.
 */
@objid ("0056d718-9dc5-103b-a520-001ec947cd2a")
public interface IProjectService extends ICurrentProjectService {
    /**
     * Adds a model fragment to the currently opened project.
     * @param project the project to modify
     * @param fragmentDescriptor the descriptor of the fragment to add.
     * @param monitor a progress monitor.
     * @throws org.modelio.gproject.gproject.FragmentConflictException if a fragment with same name or URI is already deployed.
     */
    @objid ("00545222-bb2f-103c-a520-001ec947cd2a")
    void addFragment(GProject project, FragmentDescriptor fragmentDescriptor, IProgressMonitor monitor) throws FragmentConflictException;

    /**
     * Changes the current workspace to the given path. Changing the current workspace is not possible when a project is currently opened.
     * @param path the path of the new workspace to use. Must be a valid directory path.
     * @throws java.lang.IllegalArgumentException If the given path is cannot be used as a workspace path.
     * @throws java.lang.IllegalStateException If a project is currently opened.
     */
    @objid ("0082e218-acc2-103b-a520-001ec947cd2a")
    void changeWorkspace(final Path path) throws IllegalArgumentException, IllegalStateException;

    /**
     * Closes the project currently opened in the application.
     * @param sentSyncEvents
     * @param project The project to close. Must be equal to the currently opened project.
     * @throws java.lang.IllegalArgumentException If <code>project</code> is null or different from the currently opened project.
     * @throws java.lang.IllegalStateException If no project is currently opened.
     */
    @objid ("0083087e-acc2-103b-a520-001ec947cd2a")
    void closeProject(final GProject project, boolean sentSyncEvents) throws IllegalArgumentException, IllegalStateException;

    /**
     * Calls {@link #closeProject(GProject, false)}
     * @param project
     * @throws IllegalArgumentException
     * @throws IllegalStateException
     */
    @objid ("7159706d-b1e2-4a5b-ab06-9bad395e0fbc")
    void closeProject(final GProject project) throws IllegalArgumentException, IllegalStateException;

    /**
     * Creates a new project in the current workspace. The nature and the properties of the project to create are passed in the <code>dataModel</code> argument.
     * @param projectCreator the delegated project creator, typically null. If null a project creator will be guessed from 'data' and used to create the project.
     * @param data the nature, characteristics and properties of the project to create.
     * @param monitor a progress monitor, null accepted.
     * @throws java.io.IOException in case of failure
     */
    @objid ("8e2f8b3f-a57d-4898-b25d-1ad77925152d")
    void createProject(IProjectCreator projectCreator, IProjectCreationData data, IProgressMonitor monitor) throws IOException;

    /**
     * Deletes a project from the workspace. This operation is definitive. The project must not be currently opened.
     * @param projectToDelete the project to delete.
     * @throws java.io.IOException in case of I/O failure.
     * @throws java.nio.file.FileSystemException in case of file system error.
     * @Throws IllegalStateException If <code>project</code> is currently opened.
     */
    @objid ("008838ee-8c65-103c-a520-001ec947cd2a")
    void deleteProject(ProjectDescriptor projectToDelete) throws FileSystemException, IOException;

    /**
     * Exports the whole contents of a project into a single archive file.
     * @param project the project to export. It must not be opened.
     * @param archivePath the path of the file archive to produce.
     * @param monitor a progress monitor.
     * @throws java.io.IOException in case of I/O failure.
     */
    @objid ("0088453c-8c65-103c-a520-001ec947cd2a")
    void exportProject(ProjectDescriptor project, Path archivePath, IModelioProgress monitor) throws IOException;

    /**
     * Opens a project in the application.
     * <p>
     * On successful return the given project receives an active CoreSession instance.
     * @param project The project to open. The project must not be already opened.
     * @param authData project authentication data.
     * @param monitor a progress monitor.
     * @throws java.io.IOException If the project opening failed at the IO level.
     * @throws java.lang.IllegalArgumentException If <code>project</code> is null.
     * @throws java.lang.IllegalStateException If a project is currently opened.
     * @throws org.modelio.gproject.gproject.GProjectAuthenticationException if the authentication fails
     * @throws java.lang.InterruptedException on user cancel
     */
    @objid ("0082f550-acc2-103b-a520-001ec947cd2a")
    void openProject(final ProjectDescriptor project, IAuthData authData, IProgressMonitor monitor) throws GProjectAuthenticationException, IOException, IllegalArgumentException, IllegalStateException, InterruptedException;

    /**
     * Opens the project name 'projectName' in the current workspace.
     * @param projectName the project name.
     * @param authData authentication data, may be <code>null</code> if not needed.
     * @param monitor a progress monitor.
     * @throws org.modelio.gproject.gproject.GProjectAuthenticationException in case of authentication failure
     * @throws java.io.IOException If the project opening failed at the IO level.
     * @throws java.lang.InterruptedException on user cancel
     */
    @objid ("004e41e8-8d1e-10b4-9941-001ec947cd2a")
    void openProject(String projectName, IAuthData authData, IProgressMonitor monitor) throws GProjectAuthenticationException, IOException, InterruptedException;

    /**
     * Fires a refresh of the workspace contents. The workspace directory will be re-scanned for new/removed projects and the workspace internal data structures updated accordingly. The project named 'projectToSelect' (if not null) is selected in workspace
     */
    @objid ("0083325e-acc2-103b-a520-001ec947cd2a")
    void refreshWorkspace(String projectToSelect);

    /**
     * Remove a model fragment from the currently opened project.
     * <p>
     * All fragment datas will be deleted from disk.
     * @param project the project to modify
     * @param fragment the fragment to remove
     */
    @objid ("002f56d4-a4c3-1044-a30e-001ec947cd2a")
    void removeFragment(GProject project, IProjectFragment fragment);

    /**
     * Rename a fragment and adapt its directory to match the new name.
     * @param project the project that owns the fragment
     * @param fragment the fragment to edit.
     * @param name the new name.
     * @throws java.nio.file.FileSystemException in case of file system error.
     * @throws java.io.IOException in case of I/O failure.
     * @throws org.modelio.gproject.gproject.FragmentConflictException if a fragment with same name or URI is already deployed.
     */
    @objid ("616c200b-ef1a-44b2-9bbb-a43efbd8f4cc")
    void renameFragment(GProject project, IProjectFragment fragment, String name) throws FileSystemException, FragmentConflictException, IOException;

    /**
     * Rename a project and adapt its directory to match the new name.
     * @param projectDescriptor the project to edit.
     * @param name the new name.
     * @throws java.io.IOException in case of I/O failure.
     * @throws java.nio.file.FileSystemException in case of file system error.
     */
    @objid ("0c049288-0008-4133-a8ae-527b5c3a01de")
    void renameProject(ProjectDescriptor projectDescriptor, String name) throws FileSystemException, IOException;

    /**
     * Saves the contents of the project currently opened in the application.
     * @param monitor a progress monitor. If <code>null</code>, no progress will be reported.
     * @throws java.io.IOException If the project saving failed at the IO level.
     * @throws java.lang.IllegalStateException If no project is currently opened.
     */
    @objid ("00831bd4-acc2-103b-a520-001ec947cd2a")
    void saveProject(IProgressMonitor monitor) throws IOException, IllegalStateException;

    /**
     * Equivalent to calling
     * @link {@link IProjectService#createProject(IProjectCreator, IProjectCreationData, IProgressMonitor)} with projectCreator being null.
     * @throws IOException
     * @param data the nature, characteristics and properties of the project to create. This will determine wich project creator to use.
     * @param monitor a progress monitor, null accepted.
     */
    @objid ("f2253cd7-31ec-421e-bbcc-2b51d1d5e5c1")
    void createProject(IProjectCreationData data, IProgressMonitor monitor) throws IOException;

    /**
     * @param nodeId a preference node identifier.
     * @return the project preference store for the node.
     */
    @objid ("afae4f86-0efd-4d61-b575-75ec081826ae")
    @Override
    IGProjectPreferenceStore getProjectPreferences(String nodeId);

}
