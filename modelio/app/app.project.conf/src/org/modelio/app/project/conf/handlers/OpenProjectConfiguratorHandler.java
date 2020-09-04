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

package org.modelio.app.project.conf.handlers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystemException;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.statusreporter.StatusReporter;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.project.conf.dialog.ProjectConfigurationDialog;
import org.modelio.app.project.conf.plugin.AppProjectConf;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.gproject.gproject.GProject;
import org.modelio.ui.progress.IModelioProgressService;
import org.modelio.vbasic.files.FileUtils;

/**
 * Handler that opens the project configuration dialog.
 * <p>
 * This handler has a "folder" parameter to specify the {@link ProjectConfigurationDialog dialog tab} to show.
 * </ul>
 */
@objid ("008a0b06-5a8d-10a6-888d-001ec947cd2a")
public class OpenProjectConfiguratorHandler {
    @objid ("0023c4cc-5a8e-10a6-888d-001ec947cd2a")
    @Execute
    void execute(final MApplication application, final IProjectService projectService, @Named (IServiceConstants.ACTIVE_SHELL) final Shell shell, @Optional @Named ("folder") final String folder, IModelioProgressService progressService, StatusReporter statusReporter) {
        AppProjectConf.LOG.info("Opening project configurator");
        
        GProject openedProject = projectService.getOpenedProject();
        ProjectConfigurationDialog dialog = new ProjectConfigurationDialog(application, openedProject, shell);
        if (folder != null) {
            try {
                dialog.setSelectedPage(folder);
            } catch (Exception e) {
                // Invalid folder name, open the dialog anyway
                AppProjectConf.LOG.warning("Invalid folder to select: " + folder);
            }
        }
        dialog.open();
        
        try {
            saveProject(progressService, projectService);
            projectService.refreshWorkspace(openedProject.getName());
        } catch (InvocationTargetException e) {
            // Error during save...
            AppProjectConf.LOG.error("Unable to save project");
            AppProjectConf.LOG.error(e);
            statusReporter.show(StatusReporter.ERROR, e.getLocalizedMessage(), e);
        } catch (InterruptedException e) {
            AppProjectConf.LOG.error(e);
        }
    }

    @objid ("0024384e-5a8e-10a6-888d-001ec947cd2a")
    @CanExecute
    boolean canExecute(final IProjectService projectService) {
        return projectService.getOpenedProject() != null;
    }

    @objid ("be8412e8-51a9-4f98-b6ee-6ce3df733435")
    private void saveProject(IModelioProgressService svc, final IProjectService projectService) throws InterruptedException, InvocationTargetException {
        IRunnableWithProgress runnable = new IRunnableWithProgress() {
        
            @Override
            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                try {
                    projectService.saveProject(monitor);
                } catch (FileSystemException e) {
                    throw new InvocationTargetException(e, FileUtils.getLocalizedMessage(e));
                } catch (IOException e) {
                    throw new InvocationTargetException(e, e.getLocalizedMessage());
                }
            }
        };
        
        svc.run(true, false, runnable);
    }

}
