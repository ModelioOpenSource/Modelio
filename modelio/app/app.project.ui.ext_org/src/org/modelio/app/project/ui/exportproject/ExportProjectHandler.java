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

package org.modelio.app.project.ui.exportproject;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.project.ui.plugin.AppProjectUi;
import org.modelio.app.project.ui.plugin.AppProjectUiExt;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.gproject.GProject;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.platform.ui.progress.IModelioProgressService;
import org.modelio.platform.ui.progress.ModelioProgressAdapter;
import org.modelio.vbasic.files.FileUtils;

@objid ("3ad6a6cb-d1d5-4b8c-8c63-185b44edddb8")
public class ExportProjectHandler {
    @objid ("1cbf7c45-b351-4b81-97ab-812e65e8be14")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_SHELL) final Shell shell, final IProjectService projectService, @Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, IModelioProgressService progressService) {
        final ProjectDescriptor projectToExport = SelectionHelper.getFirst(selection, ProjectDescriptor.class);
        if (projectToExport == null) {
            return;
        }
        
        AppProjectUi.LOG.info("Exporting project " + projectToExport.getName());
        
        final GProject openedProject = projectService.getOpenedProject();
        
        // check that the project to export is not the currently opened one.
        // Opened project cannot be exported as the exported project file would
        // be locked
        if (openedProject != null && openedProject.getName().equals(projectToExport.getName())) {
            MessageDialog.openError(shell,
                    AppProjectUiExt.I18N.getMessage("ExportProjectHandler.CannotExportOpenedProjectTitle", projectToExport.getName()),
                    AppProjectUiExt.I18N.getMessage("ExportProjectHandler.CannotExportOpenedProjectMsg", projectToExport.getName()));
            return;
        }
        
        // Exporting a project consists in zipping its directory contents into a
        // zip archive
        // Prompt the user for the archive file path and name
        final Path archiveFile = promptUserForFile(shell, projectToExport.getProjectFileStructure().getProjectPath());
        if (archiveFile == null) {
            AppProjectUi.LOG.info("Export aborted by user.");
            return;
        }
        
        try {
            progressService.busyCursorWhile(monitor -> {
                try {
                    projectService.exportProject(projectToExport, archiveFile, new ModelioProgressAdapter(monitor));
                } catch (IOException e) {
                    throw new InvocationTargetException(e);
                }
            });
        } catch (@SuppressWarnings ("unused") InterruptedException e) {
            AppProjectUi.LOG.info("Export aborted by user.");
        } catch (InvocationTargetException ex) {
            AppProjectUi.LOG.error(ex.getCause());
            try {
                throw ex.getCause();
            } catch (IOException e) {
                MessageDialog.openError(shell, AppProjectUiExt.I18N.getString("ExportProjectHandler.ExportError"), FileUtils.getLocalizedMessage(e));
            } catch (Error e) {
                throw e;
            } catch (Throwable e) {
                MessageDialog.openError(shell, AppProjectUiExt.I18N.getString("ExportProjectHandler.ExportError"), e.getCause().toString());
            }
        }
    }

    @objid ("51338a00-c621-499a-ac12-f7a9e1bdaada")
    @CanExecute
    public boolean canExecute(final IProjectService projectService, @Optional @Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        List<ProjectDescriptor> projectDescriptors = SelectionHelper.toList(selection, ProjectDescriptor.class);
        if (projectDescriptors.size() != 1) {
            return false;
        }
        
        final ProjectDescriptor desc = projectDescriptors.get(0);
        if (desc.getLockInfo() != null) {
            return false;
        }
        
        GProject openedProject = projectService.getOpenedProject();
        if (openedProject != null) {
            // cannot export currently opened project
            if (openedProject.getName().equals(desc.getName())) {
                return false;
            }
        }
        return true;
    }

    @objid ("1f0e3998-bcdb-49df-8bda-056552d51fe3")
    protected Path promptUserForFile(Shell parentShell, Path projectSpace) throws java.nio.file.InvalidPathException {
        FileDialog dialog = new FileDialog(parentShell, SWT.SAVE);
        
        dialog.setFilterNames(new String[] { AppProjectUiExt.I18N.getString("ExportProjectHandler.ProjectArchive") });
        dialog.setFilterExtensions(new String[] { "*.zip;" });
        dialog.setFileName(projectSpace.getFileName() + ".zip");
        dialog.setFilterPath(projectSpace.getParent().toString());
        
        String sfilePath = dialog.open();
        if (sfilePath != null) {
        
            if (!sfilePath.endsWith(".zip")) {
                sfilePath += ".zip";
            }
        
            Path path = Paths.get(sfilePath);
        
            if (Files.exists(path)) {
                boolean override = MessageDialog.openConfirm(
                        parentShell,
                        AppProjectUiExt.I18N.getString("ExportProjectHandler.override.title"),
                        AppProjectUiExt.I18N.getString("ExportProjectHandler.override.message"));
        
                if (override == false) {
                    return promptUserForFile(parentShell, projectSpace);
                }
            }
        
            return path;
        }
        return null;
    }

}
