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

package org.modelio.app.project.core.services.openproject;

import java.io.IOException;
import java.net.URI;
import java.nio.file.AccessDeniedException;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.app.project.core.plugin.AppProjectCore;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.InheritedAuthData;
import org.modelio.gproject.data.project.ModuleDescriptor;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.fragment.FragmentAuthenticationException;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.module.GModule;
import org.modelio.gproject.module.ModuleSorter;
import org.modelio.mda.infra.service.IModuleManagementService;
import org.modelio.ui.progress.ModelioProgressAdapter;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.auth.NoneAuthData;
import org.modelio.vbasic.auth.UserPasswordAuthData;
import org.modelio.vbasic.collections.TopologicalSorter.CyclicDependencyException;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.progress.SubProgress;

/**
 * Helper class that prompt user for missing or denied authentication for
 * a project and all its fragments and modules.
 * <p>
 * It contains 2 main methods:<ul>
 * <li>{@link #checkMissingAuths(ProjectDescriptor)} : to be called before opening the project
 * <li>{@link #checkAuthErrors(IProgressMonitor, GProject)} : to be called after project
 * opening to request auth on fragments that failed authentication.
 * </ul>
 * @author cma
 * @since 4.0 : was before in one of the OpenProjectHandler implementations.
 */
@objid ("cda9c70b-20e2-4784-a07a-98cf6681ad46")
class ProjectAuthsChecker {
    @objid ("b968215b-588c-4190-b80f-db840a6d7973")
    private final IModuleManagementService moduleService;

    @objid ("f615350e-309b-4317-969a-1e2a592c0fc4")
    private final IAuthenticationPrompter authPrompter;

    @objid ("a34c1b87-76c6-4f62-9a9b-807903b68884")
    private final IAuthData projectAuth;

    @objid ("bdd08bcf-d7f8-4bb2-9bba-c67bc6adafd9")
    public ProjectAuthsChecker(IAuthenticationPrompter authPrompter, IModuleManagementService moduleService, IAuthData projectAuth) {
        super();
        this.authPrompter = authPrompter;
        this.moduleService = moduleService;
        this.projectAuth = projectAuth;
    }

    /**
     * Check authentication status on the project and all its fragments.
     * <p>
     * Ask the user for authentication data if needed and possible.
     * Try to mount again fragments after the user changed authentication data.
     * <p>
     * TODO : this method might be useless now {@link OpenProjectService} and {@link TodoRunner}
     * nearly catches everywhere authentication problems and uses {@link IAuthenticationPrompter}
     * to resolve them real time.
     * 
     * @param monitor a progress monitor, may be null.
     * @param openedProject the opened project
     */
    @objid ("5ef276f9-7d08-4baf-9c93-89a096fac7b3")
    public void checkAuthErrors(IProgressMonitor monitor, GProject openedProject) {
        if (this.authPrompter == null)
            return;
        
        int monCount = openedProject.getFragments().size() + openedProject.getModules().size();
        SubProgress mon = ModelioProgressAdapter.convert(monitor, monCount);
        
        for (IProjectFragment f : openedProject.getFragments()) {
            while (needsAuthPrompt(f)) {
                mon.setWorkRemaining(monCount+1);
        
                IAuthData confAuthData = f.getAuthConfiguration().getAuthData();
                IAuthData oldAuthData = confAuthData != null ? confAuthData : new NoneAuthData();
                String label = AppProjectCore.I18N.getMessage("OpenProjectHandler.Auth.FragmentLabel", f.getId());
        
                // Prompt for auth
                IAuthData newAuthData = promptAuthentication( oldAuthData, label, uriToString(f.getUri()), getError(f));
        
                // Check for user abort
                if (newAuthData == null) {
                    break;
                }
        
                if (oldAuthData != newAuthData) {
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
            AppProjectCore.LOG.warning(e.toString());
            AppProjectCore.LOG.debug(e);
        }
        
        for (GModule f : sortedModules) {
            IAuthData authData = f.getAuthData().getAuthData();
            while (needsAuthPrompt(f)) {
                String label = AppProjectCore.I18N.getMessage("OpenProjectHandler.Auth.ModuleLabel", f.getName(), f.getVersion().toString());
                IAuthData newAuthData = promptAuthentication( authData, label, f.getOriginalArchiveUri().toString(), getError(f));
        
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
                    // ignore here, error is checked in while condition
                }
            }
        
            mon.worked(1);
            monCount--;
        }
    }

    /**
     * Check authentication data on the project and all fragments before the project is opened.
     * 
     * @param projectToOpen a project descriptor
     * @return the project authentication data on success, <i>null</i> if the user aborts open.
     */
    @objid ("ad0a56c0-7178-456b-8463-3f8f3e50a6ab")
    public IAuthData checkMissingAuths(final ProjectDescriptor projectToOpen) {
        String label = AppProjectCore.I18N.getMessage("OpenProjectHandler.Auth.ProjectLabel", projectToOpen.getName());
        IAuthData firstProjAuth = this.projectAuth != null ? this.projectAuth : projectToOpen.getAuthDescriptor().getData();
        
        IAuthData projAuthData = checkMissingPartAuth(
                firstProjAuth ,
                label,
                projectToOpen.getRemoteLocation());
        
        for (FragmentDescriptor f : projectToOpen.getFragments()) {
            IAuthData authData = f.getAuthDescriptor().getData();
            if (needsAuthPrompt(authData, f.getUri())) {
                label = AppProjectCore.I18N.getMessage("OpenProjectHandler.Auth.FragmentLabel", f.getId());
                IAuthData newAuthData = checkMissingPartAuth( authData, label, uriToString(f.getUri()));
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
                label = AppProjectCore.I18N.getMessage("OpenProjectHandler.Auth.ModuleLabel", f.getName(), f.getVersion().toString());
                IAuthData newAuthData = checkMissingPartAuth( authData, label, uriToString(f.getArchiveLocation()));
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
     * Display a SWT dialog that prompts authentication .
     * 
     * @param authData the authentication to complete
     * @param name the project or fragment name to authenticate.
     * @param location the location of the element to authenticate , usually an URL.
     * @param errorMessage the previous authentication error if any.
     * @return the authentication data or null to abort.
     */
    @objid ("7161b2f6-c45d-4826-9441-df4c7fc50d80")
    public IAuthData promptAuthentication(IAuthData authData, String name, String location, String errorMessage) {
        return this.authPrompter.promptAuthentication(authData, name, location, errorMessage);
    }

    @objid ("384b0ca0-9855-4902-bc4a-44b058677c75")
    private IAuthData checkMissingPartAuth(IAuthData authToCheck, String name, String location) {
        IAuthData authData = authToCheck;
        
        if (authData == null || !authData.isComplete()) {
            if (authData == null) {
                authData = new UserPasswordAuthData();
            }
        
            do {
                authData = promptAuthentication( authData, name, location, null);
            } while (authData != null && !authData.isComplete());
        }
        return authData;
    }

    @objid ("8a5d51f9-24e8-4959-a478-d895a849e593")
    private static String getError(GModule f) {
        IProjectFragment moduleFrag = f.getModelFragment();
        Throwable downError = moduleFrag != null ? moduleFrag.getDownError() : null;
        return getErrorMessage(downError);
    }

    @objid ("5cbc64de-2cfa-458d-8c6d-164f6b0cbf9a")
    private static String getError(IProjectFragment f) {
        return getErrorMessage(f.getDownError());
    }

    @objid ("82fd3377-f3b9-450e-a7c9-d8aac938521d")
    private static String getErrorMessage(final Throwable e) {
        if (e == null) {
            return null;
        } else if (e instanceof IOException) {
            return FileUtils.getLocalizedMessage((IOException) e);
        } else {
            return e.getLocalizedMessage();
        }
    }

    /**
     * A fragment needs authentication prompting if it is down with
     * a {@link FragmentAuthenticationException} or a {@link AccessDeniedException}.
     * 
     * @param f the module to check
     * @return true if authentication needs to be prompted
     */
    @objid ("4588b5fc-47d4-41d9-b185-892929816229")
    private boolean needsAuthPrompt(IProjectFragment f) {
        Throwable downError = f.getDownError();
        if (downError instanceof FragmentAuthenticationException || downError instanceof AccessDeniedException) {
            return (f.getAuthConfiguration().getScope() != DefinitionScope.SHARED &&
                    !InheritedAuthData.SCHEME_ID.equals(f.getAuthConfiguration().getSchemeId()));
        } else {
            return false;
        }
    }

    /**
     * A module needs authentication prompting if its model component fragment is down
     * with a {@link FragmentAuthenticationException} or a {@link AccessDeniedException}.
     * 
     * @param f the module to check
     * @return true if authentication needs to be prompted
     */
    @objid ("41451476-d25e-478b-abff-d6c9699fe875")
    private boolean needsAuthPrompt(GModule f) {
        if (f.getAuthData().getScope() == DefinitionScope.SHARED
                || InheritedAuthData.SCHEME_ID.equals(f.getAuthData().getSchemeId())) {
            return false;
        }
        
        IProjectFragment moduleFrag = f.getModelFragment();
        Throwable downError = moduleFrag != null ? moduleFrag.getDownError() : null;
        return downError instanceof FragmentAuthenticationException || downError instanceof AccessDeniedException;
    }

    @objid ("469a1cea-08a6-4fe6-9abe-4a3f5f3691cc")
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

    @objid ("db249017-f9d3-4836-b7aa-92f2f7f4c42f")
    private static String uriToString(URI uri) {
        if (uri==null)
            return null;
        return uri.toString();
    }

}
