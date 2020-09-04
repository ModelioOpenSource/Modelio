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

package org.modelio.app.project.core.services.workspace;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.core.events.ModelioEvent;
import org.modelio.app.project.core.plugin.AppProjectCore;
import org.modelio.app.project.core.services.openproject.IProjectServiceAccess;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.data.project.ProjectDescriptorWriter;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.files.Zipper;
import org.modelio.vbasic.progress.IModelioProgress;
import org.osgi.service.prefs.BackingStoreException;

@objid ("8434f57c-00e4-450d-add6-a975dba4101e")
public class WorkspaceService implements IWorkspaceService {
    @objid ("003dc516-7baa-10b3-9941-001ec947cd2a")
    private static final String LAST_USED_WORKSPACE_PREFERENCE_KEY = "workspace.last";

    @objid ("d7b2fd41-113a-4169-ad9e-c567da9eb3a5")
    private IProjectServiceAccess projectServiceAccess;

    @objid ("00801182-acc2-103b-a520-001ec947cd2a")
    private Path workspace;

    @objid ("28bbf1b8-8b77-4484-8291-3b1a14723032")
    public WorkspaceService(Path initialWorkspace) {
        this.workspace = initialWorkspace;
    }

    /**
     * @Inheritdoc
     */
    @objid ("008063d0-acc2-103b-a520-001ec947cd2a")
    @Override
    public void changeWorkspace(final Path workspacePath) {
        if (Files.exists(workspacePath) && Files.isDirectory(workspacePath)) {
            if (Files.isWritable(workspacePath)) {
                this.workspace = workspacePath;
                writePreferedWorkspace(workspacePath);
                this.projectServiceAccess.postAsyncEvent(ModelioEvent.WORKSPACE_SWITCH, workspacePath);
            } else {
                MessageDialog.openError(Display.getCurrent().getActiveShell(),
                        AppProjectCore.I18N.getString("AccessWorkspaceWrite.failed.title"),
                        AppProjectCore.I18N.getMessage("AccessWorkspaceWrite.failed.message", workspacePath.toString()));
            }
        } else {
            if (workspacePath.toFile().exists()) {
                MessageDialog.openError(Display.getCurrent().getActiveShell(),
                        AppProjectCore.I18N.getString("AccessWorkspaceAccessDenied.failed.title"),
                        AppProjectCore.I18N.getMessage("AccessWorkspaceAccessDenied.failed.message", workspacePath.toString()));
            } else {
                throw new IllegalArgumentException("Invalid workspace path: " + workspacePath);
            }
        }
    }

    @objid ("44289020-da16-41da-ba85-823c5f2e42c5")
    @Override
    public void configure(IProjectServiceAccess projectServiceAccess) {
        this.projectServiceAccess = projectServiceAccess;
    }

    @objid ("0089dd7a-8c65-103c-a520-001ec947cd2a")
    @Override
    public void deleteProject(final ProjectDescriptor projectToDelete) throws FileSystemException, IOException {
        // TODO this is a quite naive implementation
        // should deal with project path for delegating project
        
        FileUtils.delete(projectToDelete.getProjectFileStructure().getProjectPath());
        refreshWorkspace(null);
    }

    /**
     * @throws java.io.IOException in case of I/O failure.
     */
    @objid ("0089fb8e-8c65-103c-a520-001ec947cd2a")
    @Override
    public void exportProject(final ProjectDescriptor projectToExport, final Path archivePath, final IModelioProgress monitor) throws IOException {
        final Zipper zipper = new Zipper(archivePath);
        final List<PathMatcher> skipDirectoryMatchers = new ArrayList<>();
        
        // do not export .runtime/modules directory
        skipDirectoryMatchers.add(FileSystems.getDefault().getPathMatcher(("glob:**" + projectToExport.getProjectFileStructure().getProjectRuntimePath().resolve("modules")).replace("\\", "\\\\")));
        
        // do not export .DS_Store directory (MacOs)
        skipDirectoryMatchers.add(FileSystems.getDefault().getPathMatcher("glob:**.DS_Store"));
        
        zipper.compress(projectToExport.getProjectFileStructure().getProjectPath(), skipDirectoryMatchers, null, monitor, null);
        
        AppProjectCore.LOG.info("Exported archive '%s' %,d bytes.", archivePath, Files.size(archivePath));
    }

    @objid ("0080f426-acc2-103b-a520-001ec947cd2a")
    @Override
    public Path getWorkspace() {
        if (this.workspace == null) {
            this.workspace = readPreferedWorkspace();
        }
        return this.workspace;
    }

    /**
     * @param projectToSelect the project to select after refresh, can be null
     */
    @objid ("008118fc-acc2-103b-a520-001ec947cd2a")
    @Override
    public void refreshWorkspace(final String projectToSelect) {
        this.projectServiceAccess.postAsyncEvent(ModelioEvent.WORKSPACE_CONTENTS, this.workspace);
        if (projectToSelect != null) {
            this.projectServiceAccess.postAsyncEvent(ModelioEvent.WORKSPACE_NAVIGATE, projectToSelect);
        }
    }

    @objid ("ddfed9a9-bcb8-4683-9326-13ed63566396")
    @Override
    public void renameProject(final ProjectDescriptor projectDescriptor, final String name) throws IOException {
        final Path oldPath = projectDescriptor.getProjectFileStructure().getProjectPath();
        final Path newPath = oldPath.resolveSibling(name);
        
        // Move the project directory itself
        Files.move(oldPath, newPath, StandardCopyOption.ATOMIC_MOVE);
        
        // Change the project name and project space path
        projectDescriptor.setName(name);
        projectDescriptor.setPath(newPath);
        
        // Write the new project conf
        new ProjectDescriptorWriter().write(projectDescriptor);
        
        // Notify workspace for refresh
        refreshWorkspace(name);
    }

    /**
     * Get the workspace to use:
     * <ol>
     * <li>use the last used workspace as saved in the preferences</li>
     * <li>default to user's home directory otherwise</li>
     * </ol>
     * 
     * @return the workspace path
     */
    @objid ("0035c4b0-7baa-10b3-9941-001ec947cd2a")
    private Path readPreferedWorkspace() {
        final IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode(AppProjectCore.PLUGIN_ID);
        
        final String lastUsed = prefs.get(WorkspaceService.LAST_USED_WORKSPACE_PREFERENCE_KEY, null);
        if (lastUsed != null) {
            final Path lastPath = Paths.get(lastUsed);
            if (lastPath != null && Files.isDirectory(lastPath)) {
                return lastPath; // we are done
            }
        }
        // Preferences could not provide a valid workspace, default to user's
        // home
        final Path defaultPath = Paths.get(System.getProperty("user.home"), "modelio", "workspace");
        if (!Files.exists(defaultPath, LinkOption.NOFOLLOW_LINKS)) {
            new File(defaultPath.toString()).mkdirs(); // create if the
            // default workspace
            // doesn't exist.
        }
        return defaultPath;
    }

    /**
     * Write the workspace preferences
     * 
     * @param workspace the workspace path
     */
    @objid ("0035eddc-7baa-10b3-9941-001ec947cd2a")
    private void writePreferedWorkspace(final Path workspace) {
        if (workspace != null) {
            final IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode(AppProjectCore.PLUGIN_ID);
        
            prefs.put(WorkspaceService.LAST_USED_WORKSPACE_PREFERENCE_KEY, workspace.toString());
            try {
                prefs.flush();
            } catch (final BackingStoreException e) {
                AppProjectCore.LOG.error(e);
            }
        }
    }

    @objid ("849dd81c-478d-48a1-9588-6afd796b0995")
    public WorkspaceService() {
    }

}
