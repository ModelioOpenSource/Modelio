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
package org.modelio.app.project.ui.newproject;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.statusreporter.StatusReporter;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.project.ui.application.saveproject.SaveProjectHandler;
import org.modelio.app.project.ui.newproject.gui.ProjectCreationDialog;
import org.modelio.app.project.ui.plugin.AppProjectUiExt;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.gproject.GProjectAuthenticationException;
import org.modelio.gproject.gproject.GProjectFactory;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.platform.core.activation.IActivationService;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.platform.project.creation.ProjectCreationDataModel;
import org.modelio.platform.project.creation.ProjectCreator;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.platform.ui.progress.IModelioProgressService;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Handler that creates a new project, open it, and opens diagrams.
 */
@objid ("004485f4-cc35-1ff2-a7f4-001ec947cd2a")
@SuppressWarnings("restriction")
public class NewProjectHandler {
    @objid ("847c8c7a-fdf9-45b0-9b6e-7934a09535ce")
    private boolean openconfigurator = false;

    @objid ("0046f83e-cc35-1ff2-a7f4-001ec947cd2a")
    @Execute
    void execute(final IEclipseContext context, IModuleStore moduleCatalog, final IProjectService projectService, @Named(IServiceConstants.ACTIVE_SHELL) final Shell shell, IModelioProgressService progressSvc, StatusReporter statusReporter) {
        AppProjectUiExt.LOG.info("New project..");
        
        // Check if the directory is writable
        if (!Files.isWritable(projectService.getWorkspace())) {
            MessageDialog.openError(new Shell(), 
                    AppProjectUiExt.I18N.getString("AccessWorkspaceWrite.failed.title"), 
                    AppProjectUiExt.I18N.getMessage("AccessWorkspaceWrite.failed.message", projectService.getWorkspace().toString()));
            return;
        }      
        
        // Check for an already opened project prompt the user for closing
        if (!promptSaveBeforeClose(projectService, shell, progressSvc, statusReporter))
            return;
        
        try {
            // Prompt the user for the new project data
            ProjectCreationDataModel dataModel = promptUser(shell, projectService, moduleCatalog);
        
            if (dataModel != null) {
                // Open the new project
                final ProjectCreator projectCreator = createOpenProject(projectService, dataModel, progressSvc);
                if (projectService.getOpenedProject() == null) {
                    // If project creation failed, do not try to open
                    return;
                }
        
                final ICoreSession session = projectService.getOpenedProject().getSession();
                // The project creator should have created an initial model in
                // the new project made of model element(s) and diagram(s)
                // Here we try to open those diagrams...
                final IActivationService evs = context.get(IActivationService.class);
                final IModelioNavigationService navs = context.get(IModelioNavigationService.class);
        
                shell.getDisplay().asyncExec(new Runnable() {
                    @Override
                    public void run() {
                        for (MRef ref : projectCreator.getDiagramsToOpen()) {
                            AbstractDiagram diagram = (AbstractDiagram) session.getModel().findByRef(ref);
                            if (evs != null) {
                                evs.activateMObject(diagram);
                            }
                            if (navs != null) {
                                navs.fireNavigate(diagram.getCompositionOwner());
                                navs.fireNavigate(diagram.getCompositionOwner().getCompositionChildren().get(0));
                            }
                        }
                    }
                });
        
            }
            // Open the project configurator if asked
            // FIXME: today 23/1/2013 there is no known and satisfying means of
            // opening the project configurator
            // if (dataModel.openConfigurator) open the project configurator
        } catch (InvocationTargetException e) {
            AppProjectUiExt.LOG.error(e);
            statusReporter.show(StatusReporter.ERROR, e.getLocalizedMessage(), e.getCause());
        } catch (FileSystemException e) {
            AppProjectUiExt.LOG.error(e);
            statusReporter.show(StatusReporter.ERROR, FileUtils.getLocalizedMessage(e), e);
        } catch (IOException e) {
            AppProjectUiExt.LOG.error(e);
            statusReporter.show(StatusReporter.ERROR, e.getLocalizedMessage(), e);
        } catch (InterruptedException e) {
            AppProjectUiExt.LOG.error(e);
        }
        
    }

    @objid ("0046f8d4-cc35-1ff2-a7f4-001ec947cd2a")
    @CanExecute
    boolean canExecute(final IProjectService projectService) {
        return (projectService != null); // &&
        // (projectService.getOpenedProject()
        // == null);
    }

    @objid ("0046f96a-cc35-1ff2-a7f4-001ec947cd2a")
    private ProjectCreationDataModel promptUser(final Shell parentShell, final IProjectService projectService, IModuleStore moduleCatalog) throws IOException {
        ProjectCreationDataModel dataModel = new ProjectCreationDataModel(projectService.getWorkspace());
        ProjectCreationDialog dialog = new ProjectCreationDialog(parentShell, dataModel, moduleCatalog);
        
        // Compute a default name for the project
        String defaultName = computeDefaultName(projectService.getWorkspace());
        dataModel.setProjectName(defaultName);
        
        // Open the main window
        // Don't return from open() until dialog window closes
        dialog.setBlockOnOpen(true);
        int code = dialog.open();
        
        if (code == IDialogConstants.OK_ID) {
        
            // Checks that the project does not exist yet with the same name.
            final Path projectDir = projectService.getWorkspace().resolve(dataModel.getProjectName());
            if (Files.exists(projectDir)) {
        
                boolean answer = MessageDialog.openQuestion(parentShell,
                        AppProjectUiExt.I18N.getMessage("ProjectAlreadyExistsTitle"),
                        AppProjectUiExt.I18N.getMessage("ProjectAlreadyExistsDesc"));
        
                if (answer) {
                    // The user ask for deletion of the existing project
                    ProjectDescriptor projectToDelete = GProjectFactory.readProjectDirectory(projectDir);
                    projectService.deleteProject(projectToDelete);
                    return dataModel;
                } else {
                    // Prompt the user again for another name
                    return promptUser(parentShell, projectService, moduleCatalog);
                }
        
            }
            // The dialog has matched the entered project name agains't a set of
            // legal characters.
            // However, a post normalization is necessary even on a legal user
            // entry.
            // This is because the name has to be used for naming a directory
            // It consists currently in removing trailing blanks or dot chars.
            String normalizedName = dataModel.getProjectName();
        
            while (normalizedName.endsWith(" ") || normalizedName.endsWith(".")) {
                normalizedName = normalizedName.substring(0, normalizedName.length() - 1);
            }
        
            dataModel.setProjectName(normalizedName);
            return dataModel;
        }
        return null;
    }

    @objid ("d16399ee-4dc0-4b9a-813c-c977ca23ea8d")
    private String computeDefaultName(Path workspace) {
        String baseName = AppProjectUiExt.I18N.getString("ProjectDefaultName");
        int index = 1;
        while (Files.exists(workspace.resolve(baseName + index))) {
            index++;
        }
        return baseName + index;
    }

    @objid ("d29775ec-93f1-44e6-813d-bb30f1ae737f")
    private boolean promptSaveBeforeClose(final IProjectService projectService, final Shell shell, IModelioProgressService progressSvc, StatusReporter statusReporter) throws IllegalArgumentException, IllegalStateException {
        GProject openedProject = projectService.getOpenedProject();
        if (openedProject == null) 
            return true;
        
        String[] buttonLabels = { AppProjectUiExt.I18N.getString("SaveAndClose"),
                AppProjectUiExt.I18N.getString("CloseNoSave"), AppProjectUiExt.I18N.getString("Cancel") };
              
        final String title = AppProjectUiExt.I18N.getString("NewProjectCloseConfirmTitle");
        MessageDialog dlg = new MessageDialog(shell, title, null,
                AppProjectUiExt.I18N.getString("NewProjectCloseConfirmMsg"), MessageDialog.QUESTION, buttonLabels, 0);
              
        dlg.setBlockOnOpen(true);
        int answer = dlg.open();
              
        // Deal with saving and closing before continuing
        switch (answer) {
        case 0: 
            // Save and close
            if (!SaveProjectHandler.saveProject(title, projectService, progressSvc, statusReporter))
                return false;
            
            projectService.closeProject(openedProject);
            return true;
        case 1:
            // Close no save
            projectService.closeProject(openedProject);
            return true;
        case 2: 
        default:
            // Cancel
            return false;
              
        }
        
    }

    @objid ("8aa5dc99-8e60-40ab-baa0-e4563e1d1abc")
    private ProjectCreator createOpenProject(final IProjectService projectService, final ProjectCreationDataModel dataModel, IModelioProgressService progressSvc) throws InvocationTargetException, InterruptedException {
        final ProjectCreator projectCreator = new ProjectCreator();
        IRunnableWithProgress runnable = new IRunnableWithProgress() {
            
            @Override
            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                SubMonitor mon = SubMonitor.convert(monitor, 10);
                
                try {
                    projectService.createProject(projectCreator, dataModel, mon.newChild(4));
                    projectService.openProject(dataModel.getProjectName(), null, mon.newChild(6));
                    
                } catch (GProjectAuthenticationException e) {
                    throw new InvocationTargetException(e, e.getLocalizedMessage());
                } catch (FileSystemException e) {
                    throw new InvocationTargetException(e, FileUtils.getLocalizedMessage(e));
                } catch (IOException e) {
                    throw new InvocationTargetException(e, e.getLocalizedMessage());
                }
            }
        };
        
        progressSvc.run(true, true, runnable);
        return projectCreator;
    }

}
