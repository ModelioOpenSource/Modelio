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
package org.modelio.platform.project.services;

import java.io.IOException;
import java.net.URI;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.modelio.gproject.auth.GProjectAuthenticationException;
import org.modelio.gproject.data.project.GProjectDescriptor;
import org.modelio.platform.core.project.ICurrentProjectService;
import org.modelio.platform.preferences.IGProjectPreferenceStore;
import org.modelio.platform.project.services.closeproject.IProjectCloser;
import org.modelio.platform.project.services.createproject.IProjectCreator;
import org.modelio.platform.project.services.workspace.IWorkspaceService;
import org.modelio.vbasic.auth.IAuthData;

/**
 * IProjectService: modelio application scoped services dealing with
 * <ul>
 * <li>workspace management</li>
 * <li>project management</li>
 * 
 * IProjectService has a singleton instance available for injection.
 */
@objid ("0056d718-9dc5-103b-a520-001ec947cd2a")
public interface IProjectService extends ICurrentProjectService, IWorkspaceService, IProjectCreator, IProjectCloser, IFragmentMigratorFactory {
    /**
     * @param nodeId a preference node identifier.
     * @return the project preference store for the node.
     */
    @objid ("afae4f86-0efd-4d61-b575-75ec081826ae")
    @Override
    IGProjectPreferenceStore getProjectPreferences(String nodeId);

    /**
     * Opens a project in the application.
     * <p>
     * On successful return the given project receives an active CoreSession instance.
     * @param project The project to open. The project must not be already opened.
     * @param authData project authentication data.
     * @param monitor a progress monitor.
     * @throws IOException If the project opening failed at the IO level.
     * @throws IllegalArgumentException If <code>project</code> is null.
     * @throws IllegalStateException If a project is currently opened.
     * @throws GProjectAuthenticationException if the authentication fails
     * @throws InterruptedException on user cancel
     */
    @objid ("0082f550-acc2-103b-a520-001ec947cd2a")
    void openProject(final GProjectDescriptor project, IAuthData authData, IProgressMonitor monitor) throws IOException, IllegalArgumentException, IllegalStateException, GProjectAuthenticationException, InterruptedException;

    /**
     * Opens the project designated by 'projectURI' in the current workspace.
     * @param projectURI the project URI. eg for a local project: <i>file://myworkspace/myprojectspace/project.conf</i>
     * @param authData authentication data, may be <code>null</code> if not needed.
     * @param monitor a progress monitor.
     * @throws GProjectAuthenticationException in case of authentication failure
     * @throws IOException If the project opening failed at the IO level.
     * @throws InterruptedException on user cancel
     */
    @objid ("004e41e8-8d1e-10b4-9941-001ec947cd2a")
    void openProject(URI projectURI, IAuthData authData, IProgressMonitor monitor) throws GProjectAuthenticationException, IOException, InterruptedException;

    /**
     * Opens the project designated by 'projectName' in the current workspace. This is a convenience method that build a "file://workspace/projectName/project.conf" URI to open the project.
     * @param projectName the project name of the project to open.
     * @param authData authentication data, may be <code>null</code> if not needed.
     * @param monitor a progress monitor.
     * @throws GProjectAuthenticationException in case of authentication failure
     * @throws IOException If the project opening failed at the IO level.
     * @throws InterruptedException on user cancel
     */
    @objid ("8c36a49b-e1ab-4fba-9ecd-8229ed5655cc")
    void openProject(String projectName, IAuthData authData, IProgressMonitor monitor) throws GProjectAuthenticationException, IOException, InterruptedException;

    /**
     * Saves the contents of the project currently opened in the application.
     * @param monitor a progress monitor. If <code>null</code>, no progress will be reported.
     * @throws IOException If the project saving failed at the IO level.
     * @throws IllegalStateException If no project is currently opened.
     */
    @objid ("00831bd4-acc2-103b-a520-001ec947cd2a")
    void saveProject(IProgressMonitor monitor) throws IOException, IllegalStateException;

    /**
     * @return <code>true</code> if Modelio runs in batch mode.
     * @since 5.2
     */
    @objid ("c852e319-b81f-423f-a084-147aeca3e74d")
    boolean isBatchMode();
}

