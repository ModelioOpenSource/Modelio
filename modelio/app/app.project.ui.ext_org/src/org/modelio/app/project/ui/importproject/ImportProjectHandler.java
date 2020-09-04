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

package org.modelio.app.project.ui.importproject;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.app.project.core.services.ProjectImporter;
import org.modelio.app.project.ui.plugin.AppProjectUi;
import org.modelio.app.project.ui.plugin.AppProjectUiExt;
import org.modelio.ui.progress.IModelioProgressService;

@objid ("b5bb1066-7951-44d5-a8c9-7b429f38c6cd")
public class ImportProjectHandler {
    @objid ("b130b17f-302f-4716-92ed-94fc37d32261")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_SHELL) final Shell shell, @Optional final IProjectService projectService, IModelioProgressService progressService) {
        AppProjectUi.LOG.info("Importing a project");
        // Check that the project is not opened.
        // Opened project cannot be exported as the exported project file would
        // be locked
        if (projectService.getOpenedProject() != null) {
            MessageDialog.openError(shell,
                    AppProjectUiExt.I18N.getMessage("CannotImportOpenedProjectTitle"),
                    AppProjectUiExt.I18N.getMessage("CannotImportOpenedProjectMsg"));
            return;
        }
        
        // Importing a project consists in un-zipping its archive contents into a directory.
        
        // Prompt the user for the archive file path and name.
        Path archiveFile = promptUserForFile(shell, projectService.getWorkspace());
        if (archiveFile != null) {
            AppProjectUi.LOG.info("Importing project '%s' in workspace '%s'", archiveFile.toString(), projectService.getWorkspace());
            try {
                progressService.run(true, false, monitor -> new ProjectImporter(projectService, shell).importProject(archiveFile, monitor));
            } catch (InvocationTargetException e) {
                AppProjectUi.LOG.error(e.getCause());
                MessageDialog.openError(shell, AppProjectUiExt.I18N.getString("ExportError"), e.getCause().toString());
            } catch (@SuppressWarnings ("unused") InterruptedException e) {
                AppProjectUi.LOG.info("Export aborted by user.");
            }
        
        } else {
            AppProjectUi.LOG.info("Import aborted by user.");
        }
        return;
    }

    @objid ("72721447-538f-40ef-a36d-66208f7c2120")
    @CanExecute
    public boolean canExecute(final IProjectService projectService) {
        return projectService.getOpenedProject() == null;
    }

    @objid ("fb852359-a966-41f9-ab9a-ef89bf80b95d")
    protected Path promptUserForFile(Shell parentShell, Path workspace) {
        FileDialog dialog = new FileDialog(parentShell, SWT.OPEN);
        
        dialog.setFilterNames(new String[] { AppProjectUiExt.I18N.getString("ProjectArchive") });
        dialog.setFilterExtensions(new String[] { "*.zip;" });
        
        dialog.setFileName("");
        
        dialog.setFilterPath(workspace.toString());
        
        String sfilePath = dialog.open();
        if (sfilePath != null) {
            return Paths.get(sfilePath);
        }
        return null;
    }

}
