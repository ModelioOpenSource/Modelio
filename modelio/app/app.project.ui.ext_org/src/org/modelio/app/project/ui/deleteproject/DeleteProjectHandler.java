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

package org.modelio.app.project.ui.deleteproject;

import java.io.IOException;
import java.util.List;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.app.project.ui.plugin.AppProjectUi;
import org.modelio.app.project.ui.plugin.AppProjectUiExt;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.vbasic.files.FileUtils;

/**
 * Delete a project.
 */
@objid ("68006c5e-0e54-434e-9f2c-e9fb6106fc07")
public class DeleteProjectHandler {
    @objid ("c37c7e30-4f8c-40e8-9c5a-2e35d07a5539")
    @Execute
    public void execute(final IProjectService projectService, @Named(IServiceConstants.ACTIVE_SHELL) final Shell shell, @Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        List<ProjectDescriptor> projectDescriptors = SelectionHelper.toList(selection, ProjectDescriptor.class);
        
        StringBuilder nameList = new StringBuilder();
        for (ProjectDescriptor projectDescriptor : projectDescriptors) {
            nameList.append(" - ");
            nameList.append(projectDescriptor.getName());
            nameList.append('\n');
        }
        if (MessageDialog.openConfirm(shell, AppProjectUiExt.I18N.getString("ConfirmProjectDeletion"),
                                        AppProjectUiExt.I18N.getMessage("ConfirmProjectDeletionMessage", nameList.toString()))) {
            for (ProjectDescriptor projectDescriptor : projectDescriptors) {
                String projName = projectDescriptor.getName();
                AppProjectUi.LOG.info("Deleting project '%s' ", projName);
                
                try {
                    projectService.deleteProject(projectDescriptor);
                } catch (IOException e) {
                    AppProjectUi.LOG.error(FileUtils.getLocalizedMessage(e));
                    AppProjectUi.LOG.error(e);
                    
                    MessageDialog.openError(shell,
                            AppProjectUiExt.I18N.getMessage("DeleteProjectHandler.failed.title", projName) , 
                            AppProjectUiExt.I18N.getMessage("DeleteProjectHandler.failed.message", projName , FileUtils.getLocalizedMessage(e)));
                }     
            }
        }
    }

    @objid ("22628a89-8b02-48a7-a57c-8e615594b6e1")
    @CanExecute
    public boolean canExecute(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        if (selection == null || selection.isEmpty()) {
            return false;
        }
        
        for (ProjectDescriptor descriptor : SelectionHelper.toList(selection, ProjectDescriptor.class)) {
            // cannot delete currently opened project
            if (descriptor.getLockInfo() != null) {
                return false;
            }
        }
        return true;
    }

}
