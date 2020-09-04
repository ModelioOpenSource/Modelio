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

package org.modelio.app.project.ui.application.saveproject;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystemException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.statusreporter.StatusReporter;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.app.project.ui.plugin.AppProjectUi;
import org.modelio.ui.progress.IModelioProgressService;
import org.modelio.vbasic.files.FileUtils;

/**
 * Handler of the "Save project" command.
 */
@objid ("00448d88-cc35-1ff2-a7f4-001ec947cd2a")
public class SaveProjectHandler {
    @objid ("0046fabe-cc35-1ff2-a7f4-001ec947cd2a")
    @Execute
    void execute(final IProjectService projectService, IModelioProgressService progressSvc, StatusReporter statusReporter) {
        assert (projectService.getOpenedProject() != null);
        
        saveProject(AppProjectUi.I18N.getMessage("SaveProjectTitle"), projectService, progressSvc, statusReporter);
    }

    @objid ("0046fb54-cc35-1ff2-a7f4-001ec947cd2a")
    @CanExecute
    boolean canExecute(final IProjectService projectService) {
        return projectService.isDirty();
    }

    /**
     * Save the project opened by the project service with GUI feedback.
     * <p>
     * Displays the progress in a progress dialog.
     * Reports failure to the user using the given status reporter.
     * Returns <code>false</code> on failure.
     * 
     * @param title the progress monitor dialog title
     * @param projectService the project service. Get one on the Eclipse context.
     * @param progressSvc the service to use to report progress. Get one on the Eclipse context.
     * @param statusReporter the service to use to report errors. Get one on the Eclipse context.
     * @return <code>true</code> if the save is successful, <code>false</code> if the save failed.
     */
    @objid ("32c99624-ee43-4b53-8c8a-59fae2f2a0b2")
    public static boolean saveProject(String title, final IProjectService projectService, IModelioProgressService progressSvc, StatusReporter statusReporter) {
        AppProjectUi.LOG.info("Saving project '%s'", projectService.getOpenedProject().getName());
        try {
            saveProject(title, progressSvc, projectService);
            return true;
        } catch (InvocationTargetException e) {
            AppProjectUi.LOG.error(e);
            statusReporter.show(StatusReporter.ERROR, e.getLocalizedMessage(), e);
            return false;
        } catch (InterruptedException e) {
            AppProjectUi.LOG.error(e);
            return false;
        }
    }

    @objid ("03cea0c6-99b3-4b25-bbe1-e53c673a102a")
    private static void saveProject(String title, IModelioProgressService svc, final IProjectService projectService) throws InterruptedException, InvocationTargetException {
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
        
        svc.run(title, true, false, runnable);
    }

}
