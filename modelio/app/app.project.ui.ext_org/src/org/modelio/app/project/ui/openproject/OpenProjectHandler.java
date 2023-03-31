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
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.project.ui.plugin.AppProjectUiExt;
import org.modelio.gproject.FragmentAuthenticationException;
import org.modelio.gproject.auth.GProjectAuthenticationException;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.GProjectDescriptor;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.project.GProjectPartDescriptor.GProjectPartType;
import org.modelio.gproject.data.project.auth.InheritedAuthData;
import org.modelio.gproject.project.GProjectDescriptorFactory;
import org.modelio.platform.mda.infra.service.IModuleManagementService;
import org.modelio.platform.model.ui.dialogs.auth.AuthDataDialog;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.platform.ui.progress.IModelioProgressService;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.auth.UserPasswordAuthData;
import org.modelio.vbasic.files.FileUtils;

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
        
        List<GProjectDescriptor> projects = SelectionHelper.toList(selection, GProjectDescriptor.class);
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
        final GProjectDescriptor projectToOpen = SelectionHelper.getFirst(selection, GProjectDescriptor.class);
        if (projectToOpen == null) {
            return;
        }
        
        // Check that authentication data is complete, if not => prompt user
        IAuthData authData = checkProjectAuth(shell, projectToOpen);
        
        if (authData == null) {
            // User cancelled => abort
            return;
        }
        
        assert projectService.getOpenedProject() == null;
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
        
                    // Update the descriptor lock infos
                    try {
                        projectToOpen.setLockInfo(GProjectDescriptorFactory.getLockInformations(projectToOpen));
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
                    more = authData != null;
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
    private IAuthData checkProjectAuth(final Shell shell, final GProjectDescriptor projectToOpen) {
        String label = AppProjectUiExt.I18N.getMessage("OpenProjectHandler.Auth.ProjectLabel", projectToOpen.getName());
        IAuthData projAuthData = checkPartAuth(shell, projectToOpen.getAuthDescriptor().getData(), projectToOpen.getName()
                + " project");
        
        for (GProjectPartDescriptor f : projectToOpen.getPartDescriptors()) {
            IAuthData authData = f.getAuth().getData();
            if (needsAuthPrompt(authData, f.getLocation())) {
                label = AppProjectUiExt.I18N.getMessage("OpenProjectHandler.Auth.FragmentLabel", f.getId());
                IAuthData newAuthData = checkPartAuth(shell, authData, label);
                if (newAuthData == null) {
                    return null;
                }
                if (authData != newAuthData) {
                    f.getAuth().setData(newAuthData);
                }
            }
        }
        
        for (GProjectPartDescriptor f : projectToOpen.getPartDescriptors(GProjectPartType.MODULE)) {
            IAuthData authData = f.getAuth().getData();
            if (needsAuthPrompt(authData, f.getLocation())) {
                label = AppProjectUiExt.I18N.getMessage("OpenProjectHandler.Auth.ModuleLabel", f.getId(), f.getVersion().toString());
                IAuthData newAuthData = checkPartAuth(shell, authData, label);
                if (newAuthData == null) {
                    return null;
                }
                if (authData != newAuthData) {
                    f.getAuth().setData(newAuthData);
                }
            }
        }
        return projAuthData;
    }

    @objid ("31847f92-bd76-48c6-b983-806e1c372dea")
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

    /**
     * A fragment needs authentication prompting if it is down with a {@link FragmentAuthenticationException} or a {@link AccessDeniedException}.
     * @param f the module to check
     * @return true if authentication needs to be prompted
     */
    @objid ("52260f54-c79f-4cab-b7b0-99ed39edf29a")
    private boolean needsAuth(IGModelFragment f) {
        Throwable downError = f.getState().getDownError();
        if (downError instanceof FragmentAuthenticationException || downError instanceof AccessDeniedException) {
            return f.getAuth().getScope() != DefinitionScope.SHARED &&
                    !InheritedAuthData.matches(f.getAuth().getData());
        } else {
            return false;
        }
        
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
