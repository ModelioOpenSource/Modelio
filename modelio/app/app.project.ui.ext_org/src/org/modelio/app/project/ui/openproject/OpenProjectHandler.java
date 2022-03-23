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
package org.modelio.app.project.ui.openproject;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.app.project.ui.plugin.AppProjectUiExt;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.InheritedAuthData;
import org.modelio.gproject.data.project.ModuleDescriptor;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.fragment.FragmentAuthenticationException;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.gproject.GProjectAuthenticationException;
import org.modelio.gproject.gproject.GProjectFactory;
import org.modelio.gproject.module.GModule;
import org.modelio.gproject.module.ModuleSorter;
import org.modelio.platform.mda.infra.service.IModuleManagementService;
import org.modelio.platform.model.ui.dialogs.auth.AuthDataDialog;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.platform.ui.progress.IModelioProgressService;
import org.modelio.platform.ui.progress.ModelioProgressAdapter;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.auth.NoneAuthData;
import org.modelio.vbasic.auth.UserPasswordAuthData;
import org.modelio.vbasic.collections.TopologicalSorter.CyclicDependencyException;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.progress.SubProgress;

/**
 * Handler for "Open project" command.
 */
@objid ("bccc4591-a245-46dc-babf-d7866aadeb46")
public class OpenProjectHandler {
    @objid ("d967aed1-0beb-4ce8-925c-e9617e82dfb3")
    @Inject
    @Optional
    IModuleManagementService moduleService;

    @objid ("5b2674b8-5be0-4710-9c1c-485178781df4")
    @CanExecute
    boolean canExecute(final IProjectService projectService, @Optional
    @Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        if (selection == null || projectService.getOpenedProject() != null) {
            return false;
        }
        
        List<ProjectDescriptor> projects = SelectionHelper.toList(selection, ProjectDescriptor.class);
        if (projects.size() != 1) {
            // refuse multiple and empty selection
            return false;
        }
        
        // Do not authorize opening of constellation project
        if (projects.get(0).getRemoteLocation() != null && projects.get(0).getRemoteLocation().startsWith("constellation")) {
            return false;
        }
        
        if (projects.get(0).getLockInfo() != null) {
            // refuse locked projects
            return false;
        }
        return true;
    }

    @objid ("bd021792-6326-4e6b-8049-772ab0100a0f")
    @Execute
    void execute(IProjectService projectService, @Named (IServiceConstants.ACTIVE_SHELL) final Shell shell, @Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, final IModelioProgressService progressService) {
        final ProjectDescriptor projectToOpen = SelectionHelper.getFirst(selection, ProjectDescriptor.class);
        if (projectToOpen == null) {
            return;
        }
        
        // Check that authentication data is complete, if not => prompt user
        IAuthData authData = checkProjectAuth(shell, projectToOpen);
        
        if (authData == null) {
            // User cancelled => abort
            return;
        }
        
        assert (projectService.getOpenedProject() == null);
        AppProjectUiExt.LOG.info("Opening project '%s' ", projectToOpen.getName());
        
        boolean more = true;
        while (more) {
            final IAuthData effectiveAuthData = authData;
            final String progressTitle = AppProjectUiExt.I18N.getMessage("OpenProjectHandler.mon.title", projectToOpen.getName());
            final String progressTaskName = AppProjectUiExt.I18N.getMessage("OpenProjectHandler.mon.task", projectToOpen.getName());
            try {
                progressService.run(progressTitle, true, false, monitor -> {
                    SubMonitor mon = SubMonitor.convert(monitor, progressTaskName, 3);
        
                    // Open the project
                    try {
                        projectService.openProject(projectToOpen, effectiveAuthData, mon.newChild(2));
                    } catch (IOException | GProjectAuthenticationException e1) {
                        throw new InvocationTargetException(e1, e1.getLocalizedMessage());
                    }
        
                    // Check again that authentication data is complete, if not => prompt user
                    shell.getDisplay().asyncExec(
                            () -> doCheckOpenProjectAuth(mon.newChild(1), shell, projectService.getOpenedProject()));
        
                    // Update the descriptor lock infos
                    try {
                        projectToOpen.setLockInfo(GProjectFactory.getLockInformations(projectToOpen));
                    } catch (IOException e2) {
                        AppProjectUiExt.LOG.warning(e2);
                    }
                });
                more = false;
            } catch (InvocationTargetException e) {
                try {
                    throw e.getCause();
                } catch (GProjectAuthenticationException cause) {
                    AppProjectUiExt.LOG.info(cause.getLocalizedMessage());
                    String label = AppProjectUiExt.I18N.getMessage("OpenProjectHandler.Auth.ProjectLabel", projectToOpen.getName());
                    authData = promptAuthentication(shell, effectiveAuthData, label, cause.getLocalizedMessage());
                    more = (authData != null);
                } catch (IOException cause) {
                    AppProjectUiExt.LOG.error(FileUtils.getLocalizedMessage(cause));
                    AppProjectUiExt.LOG.debug(e);
                    MessageDialog.openError(shell, AppProjectUiExt.I18N.getString("Error"), FileUtils.getLocalizedMessage(cause));
                    more = false;
                } catch (SecurityException cause) {
                    AppProjectUiExt.LOG.error(cause.getLocalizedMessage());
                    AppProjectUiExt.LOG.debug(e);
                    MessageDialog.openError(shell, AppProjectUiExt.I18N.getString("Error"), cause.getLocalizedMessage());
                    more = false;
                } catch (Throwable cause) {
                    AppProjectUiExt.LOG.error(e);
                    MessageDialog.openError(shell, AppProjectUiExt.I18N.getString("Error"), cause.toString());
                    more = false;
                }
            } catch (@SuppressWarnings ("unused") InterruptedException e) {
                // nothing
                more = false;
            }
        }
        
    }

    @objid ("a66fd3f1-603a-4541-9cdd-25cb6ad29b10")
    private IAuthData checkPartAuth(final Shell shell, IAuthData authToCheck, String name) {
        IAuthData authData = authToCheck;
        
        if (authData == null || !authData.isComplete()) {
            if (authData == null) {
                authData = new UserPasswordAuthData();
            }
        
            do {
                authData = promptAuthentication(shell, authData, name, null);
            } while (authData != null && !authData.isComplete());
        }
        return authData;
    }

    /**
     * Check authentication data on the project and all fragments before the project is opened.
     * @param shell a SWT shell
     * @param projectToOpen a project descriptor
     * @return the project authentication data on success, <i>null</i> if the user aborts open.
     */
    @objid ("fb82d6ea-2d28-45f3-95c9-cae6ddf18c8c")
    private IAuthData checkProjectAuth(final Shell shell, final ProjectDescriptor projectToOpen) {
        String label = AppProjectUiExt.I18N.getMessage("OpenProjectHandler.Auth.ProjectLabel", projectToOpen.getName());
        IAuthData projAuthData = checkPartAuth(shell, projectToOpen.getAuthDescriptor().getData(), projectToOpen.getName()
                + " project");
        
        for (FragmentDescriptor f : projectToOpen.getFragments()) {
            IAuthData authData = f.getAuthDescriptor().getData();
            if (needsAuthPrompt(authData, f.getUri())) {
                label = AppProjectUiExt.I18N.getMessage("OpenProjectHandler.Auth.FragmentLabel", f.getId());
                IAuthData newAuthData = checkPartAuth(shell, authData, label);
                if (newAuthData == null) {
                    return null;
                }
                if (authData != newAuthData) {
                    f.getAuthDescriptor().setData(newAuthData);
                }
            }
        }
        
        for (ModuleDescriptor f : projectToOpen.getModules()) {
            IAuthData authData = f.getAuthDescriptor().getData();
            if (needsAuthPrompt(authData, f.getArchiveLocation())) {
                label = AppProjectUiExt.I18N.getMessage("OpenProjectHandler.Auth.ModuleLabel", f.getName(), f.getVersion().toString());
                IAuthData newAuthData = checkPartAuth(shell, authData, label);
                if (newAuthData == null) {
                    return null;
                }
                if (authData != newAuthData) {
                    f.getAuthDescriptor().setData(newAuthData);
                }
            }
        }
        return projAuthData;
    }

    /**
     * Check authentication status on the project and all its fragments.
     * <p>
     * Ask the user for authentication data if needed and possible. Try to mount again fragments after the user changed authentication data.
     * @param monitor a progress monitor, may be null.
     * @param shell a SWT shell
     * @param openedProject the opened project
     */
    @objid ("2b2847d9-ee11-42f1-a65a-822795504f59")
    private void doCheckOpenProjectAuth(IProgressMonitor monitor, Shell shell, GProject openedProject) {
        int monCount = openedProject.getFragments().size() + openedProject.getModules().size();
        SubProgress mon = ModelioProgressAdapter.convert(monitor, monCount);
        
        for (IProjectFragment f : openedProject.getFragments()) {
            while (needsAuth(f)) {
                mon.setWorkRemaining(monCount);
        
                IAuthData authData = f.getAuthConfiguration().getAuthData();
                if (authData == null) {
                    authData = new NoneAuthData();
                }
        
                String label = AppProjectUiExt.I18N.getMessage("OpenProjectHandler.Auth.FragmentLabel", f.getId());
        
                IAuthData newAuthData = promptAuthentication(shell, authData, label, OpenProjectHandler.getError(f));
        
                // Check for user abort
                if (newAuthData == null) {
                    break;
                }
        
                if (authData != newAuthData) {
                    f.getAuthConfiguration().setAuthData(newAuthData);
                }
        
                SubProgress m2 = mon.newChild(1);
                f.mount(m2);
            }
        
            mon.worked(1);
            monCount--;
        }
        
        List<GModule> sortedModules = openedProject.getModules();
        try {
            sortedModules = ModuleSorter.sortModules(sortedModules);
        } catch (CyclicDependencyException e) {
            AppProjectUiExt.LOG.warning(e.toString());
            AppProjectUiExt.LOG.debug(e);
        }
        
        for (GModule f : sortedModules) {
            IAuthData authData = f.getAuthData().getAuthData();
            while (needsAuthPrompt(f)) {
                String label = AppProjectUiExt.I18N.getMessage("OpenProjectHandler.Auth.ModuleLabel", f.getName(), f.getVersion().toString());
                IAuthData newAuthData = promptAuthentication(shell, authData, label, OpenProjectHandler.getError(f));
        
                // Check for user abort
                if (newAuthData == null) {
                    break;
                }
        
                if (authData != newAuthData) {
                    f.getAuthData().setAuthData(newAuthData);
                }
        
                try {
                    this.moduleService.activateModule(f);
                } catch (@SuppressWarnings ("unused") ModuleException e) {
                    // ignore
                }
            }
        
            mon.worked(1);
            monCount--;
        }
        
    }

    @objid ("0ff863fc-c3c0-4b45-b430-2859ebd9a8fd")
    private static String getError(GModule f) {
        IProjectFragment moduleFrag = f.getModelFragment();
        Throwable downError = moduleFrag != null ? moduleFrag.getDownError() : null;
        return OpenProjectHandler.getErrorMessage(downError);
    }

    @objid ("05f530d9-b652-4ba7-b44f-59351240b45d")
    private static String getError(IProjectFragment f) {
        return OpenProjectHandler.getErrorMessage(f.getDownError());
    }

    @objid ("99e2145f-d0ab-4ebc-af42-6edf617acdec")
    private static String getErrorMessage(final Throwable e) {
        if (e == null) {
            return null;
        } else if (e instanceof IOException) {
            return FileUtils.getLocalizedMessage((IOException) e);
        } else {
            return e.getLocalizedMessage();
        }
        
    }

    @objid ("31847f92-bd76-48c6-b983-806e1c372dea")
    private List<ProjectDescriptor> getSelectedElements(final IStructuredSelection selection) {
        List<ProjectDescriptor> selectedElements = new ArrayList<>();
        if (selection.size() > 0) {
            Object[] elements = selection.toArray();
            for (Object element : elements) {
                if (element instanceof ProjectDescriptor) {
                    selectedElements.add((ProjectDescriptor) element);
                }
            }
        }
        return selectedElements;
    }

    /**
     * A fragment needs authentication prompting if it is down with a {@link FragmentAuthenticationException} or a {@link AccessDeniedException}.
     * @param f the module to check
     * @return true if authentication needs to be prompted
     */
    @objid ("52260f54-c79f-4cab-b7b0-99ed39edf29a")
    private boolean needsAuth(IProjectFragment f) {
        Throwable downError = f.getDownError();
        if (downError instanceof FragmentAuthenticationException || downError instanceof AccessDeniedException) {
            return (f.getAuthConfiguration().getScope() != DefinitionScope.SHARED &&
                    !InheritedAuthData.SCHEME_ID.equals(f.getAuthConfiguration().getSchemeId()));
        } else {
            return false;
        }
        
    }

    /**
     * A module needs authentication prompting if its model component fragment is down with a {@link FragmentAuthenticationException} or a {@link AccessDeniedException}.
     * @param f the module to check
     * @return true if authentication needs to be prompted
     */
    @objid ("a4254d04-58e2-4b48-8a98-46888135c3fe")
    private boolean needsAuthPrompt(GModule f) {
        if (f.getAuthData().getScope() == DefinitionScope.SHARED
                || InheritedAuthData.SCHEME_ID.equals(f.getAuthData().getSchemeId())) {
            return false;
        }
        
        IProjectFragment moduleFrag = f.getModelFragment();
        Throwable downError = moduleFrag != null ? moduleFrag.getDownError() : null;
        return downError instanceof FragmentAuthenticationException || downError instanceof AccessDeniedException;
    }

    @objid ("142732d3-04cf-4855-aca7-4c33220795c3")
    private boolean needsAuthPrompt(IAuthData authData, URI uri) {
        if (authData != null || uri == null) {
            return false;
        }
        
        final String scheme = uri.getScheme();
        
        if (scheme == null || scheme.isEmpty()) {
            // relative path : no auth
            return false;
        } else if (scheme.startsWith("svn")) {
            // svn, svn+http, svn+*** : auth needed
            return true;
        } else if (scheme.equals("file")) {
            // file : no auth
            return false;
        }
        
        // all other cases : auth needed
        return true;
    }

    /**
     * @param parent a SWT shell
     * @param authData the authentication to complete
     * @param name the project or fragment name to authenticate.
     * @return the authentication data or null to abort.
     */
    @objid ("419c122d-1a96-44ce-a956-13f63a821f2f")
    private IAuthData promptAuthentication(Shell parent, IAuthData authData, String name, String errorMessage) {
        AuthDataDialog dlg = new AuthDataDialog(parent, authData, name);
        dlg.setBlockOnOpen(true);
        dlg.setErrorMessage(errorMessage);
        int ret = dlg.open();
        switch (ret) {
        case 0:
            return dlg.getAuthData();
        default:
            return null;
        }
        
    }

}
