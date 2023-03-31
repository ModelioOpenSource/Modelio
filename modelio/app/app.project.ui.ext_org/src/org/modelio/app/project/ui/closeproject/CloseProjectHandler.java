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
package org.modelio.app.project.ui.closeproject;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.statusreporter.StatusReporter;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.IWindowCloseHandler;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.project.ui.application.saveproject.SaveProjectHandler;
import org.modelio.app.project.ui.plugin.AppProjectUi;
import org.modelio.app.project.ui.plugin.AppProjectUiExt;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.GProjectDescriptor;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.platform.ui.progress.IModelioProgressService;

/**
 * Handler for the "Close project" command. This handler simply closes the currently opened project in Modelio.
 * <p>
 * If the project has not been saved, a dialog box asks the user what to do.
 * </p>
 */
@objid ("f1dc6714-17d1-486d-b799-7f098cb48066")
public class CloseProjectHandler {
    @objid ("f6604157-43ba-4d9c-9741-1ad68d99a377")
    @Inject
    @Optional
    private IProjectService projectService;

    @objid ("b77a8328-2572-4ee1-8cb3-001171a0b1c3")
    @Inject
    @Optional
    private MApplication window;

    @objid ("15cd2624-2a48-429c-9eb3-cd05e8647468")
    @Inject
    @Optional
    private IModelioProgressService progressService;

    @objid ("a1daaf5f-0fc8-41d7-b0f1-1785cfbfb358")
    @Inject
    @Optional
    private StatusReporter statusReporter;

    @objid ("1f60e06e-f6eb-4536-8a65-a1d5ecac269c")
    @Execute
    void execute(final IProjectService projectService, @Named (IServiceConstants.ACTIVE_SHELL) final Shell shell, IModelioProgressService progressService, StatusReporter statusReporter) {
        IGProject openedProject = projectService.getOpenedProject();
        
        if (saveBeforeClose(shell, projectService, openedProject, progressService, statusReporter)) {
            if (openedProject != null) {
                projectService.closeProject(openedProject);
            }
        }
        
    }

    @objid ("f91de263-a0ce-4305-ace0-f6b418bec739")
    @CanExecute
    boolean canExecute(final IProjectService projectService, @Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        IGProject openedProject = projectService.getOpenedProject();
        if (openedProject == null) {
            return false;
        }
        if (selection == null) {
            return true;
        }
        List<GProjectDescriptor> projectDescriptors = getSelectedElements(selection);
        if (projectDescriptors.size() == 0) {
            return true;
        }
        if (projectDescriptors.size() == 1) {
            if (openedProject.getName().equals(projectDescriptors.get(0).getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Asks the user whether he wants to save the project if it is dirty.
     * <p>
     * If the project is not dirty, returns <code>true</code>. In the other case, if the user answers :
     * <ul>
     * <li>yes: the project is saved and returns <code>true</code> unless the save fails.
     * <li>no : returns yes without saving the project
     * <li>cancel : returns <code>false</code>.
     */
    @objid ("539c746b-408d-43ce-9633-adc0e176428d")
    public static boolean saveBeforeClose(Shell shell, IProjectService projectService, IGProject openedProject, IModelioProgressService progressService, StatusReporter statusReporter) {
        if (openedProject != null) {
            AppProjectUi.LOG.info("Closing project '%s'", openedProject.getName());
        
            if (projectService.isDirty()) {
                // Ask the user to save before quitting
                final String[] tab = { IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL };
                final MessageDialog dialog = new MessageDialog(shell,
                        AppProjectUiExt.I18N.getMessage("SaveBeforeCloseTitle"), // title
                        null, // title image
                        AppProjectUiExt.I18N.getMessage("SaveBeforeCloseQuestion", openedProject.getName()), // message
                        MessageDialog.QUESTION, // image type
                        tab, // labels
                        IDialogConstants.YES_ID); // default answer
        
                dialog.setBlockOnOpen(true);
                final int res = dialog.open();
        
                if (res == -1 || tab[res] == IDialogConstants.CANCEL_LABEL) {
                    return false;
                } else if (tab[res] == IDialogConstants.YES_LABEL) {
                    String title = AppProjectUiExt.I18N.getMessage("SaveBeforeCloseTitle");
                    return SaveProjectHandler.saveProject(title, projectService, progressService, statusReporter);
                }
            }
        }
        return true;
    }

    @objid ("671440ed-dbcd-4cff-9430-dc69fa9f74d3")
    private List<GProjectDescriptor> getSelectedElements(final IStructuredSelection selection) {
        List<GProjectDescriptor> selectedElements = new ArrayList<>();
        if (selection.size() > 0) {
            Object[] elements = selection.toArray();
            for (Object element : elements) {
                if (element instanceof GProjectDescriptor) {
                    selectedElements.add((GProjectDescriptor) element);
                }
            }
        }
        return selectedElements;
    }

    @objid ("34e0e519-02b8-444b-bfd5-4a0145c3589c")
    @Inject
    @Optional
    void onProjectOpened(@SuppressWarnings ("unused")
    @UIEventTopic (ModelioEventTopics.PROJECT_OPENED) final IGProject project, @Named (IServiceConstants.ACTIVE_SHELL) final Shell shell) {
        IWindowCloseHandler handler = new IWindowCloseHandler() {
            @Override
            public boolean close(MWindow windoww) {
                IGProject openedProject = CloseProjectHandler.this.projectService.getOpenedProject();
                if (CloseProjectHandler.saveBeforeClose(shell, CloseProjectHandler.this.projectService, openedProject, CloseProjectHandler.this.progressService, CloseProjectHandler.this.statusReporter)) {
                    if (openedProject != null) {
                        CloseProjectHandler.this.projectService.closeProject(openedProject, true);
                    }
                    return true;
                } else {
                    return false;
                }
            }
        };
        
        MWindow applicationWindow = getApplicationShellWindow(this.window);
        if (applicationWindow != null) {
            applicationWindow.getContext().set(IWindowCloseHandler.class, handler);
        }
        
    }

    @objid ("9120cd18-402f-4913-b2ad-6cf53d9fbbb9")
    private MWindow getApplicationShellWindow(final MApplication application) {
        final EModelService modelService = application.getContext().get(EModelService.class);
        final List<MWindow> windows = modelService.findElements(application, null, MWindow.class, null);
        for (final MWindow w : windows) {
            if (w.getElementId().equals("org.modelio.app.ui.trimmed")) {
                return w;
            }
        }
        return null;
    }

}
