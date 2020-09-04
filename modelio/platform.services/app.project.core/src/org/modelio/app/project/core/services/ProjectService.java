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

package org.modelio.app.project.core.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.core.IModelioEventService;
import org.modelio.app.core.ModelioEnv;
import org.modelio.app.core.events.ModelioEvent;
import org.modelio.app.preferences.GProjectPreferenceNode;
import org.modelio.app.preferences.GProjectPreferenceStore;
import org.modelio.app.preferences.IGProjectPreferenceStore;
import org.modelio.app.project.core.creation.IProjectCreationData;
import org.modelio.app.project.core.creation.IProjectCreator;
import org.modelio.app.project.core.modelshield.ModelShieldController;
import org.modelio.app.project.core.plugin.AppProjectCore;
import org.modelio.app.project.core.services.openproject.IProjectOpener;
import org.modelio.app.project.core.services.openproject.IProjectServiceAccess;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.data.project.ProjectDescriptorWriter;
import org.modelio.gproject.fragment.Fragments;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.FragmentConflictException;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.gproject.GProjectAuthenticationException;
import org.modelio.gproject.gproject.GProjectConfigurer;
import org.modelio.gproject.gproject.GProjectEnvironment;
import org.modelio.gproject.gproject.GProjectFactory;
import org.modelio.gproject.gproject.IGProjectEnv;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.mda.infra.service.IModuleManagementService;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.mmextensions.standard.services.MModelServices;
import org.modelio.ui.progress.IModelioProgressService;
import org.modelio.ui.progress.ModelioProgressAdapter;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.files.Zipper;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.session.api.ICoreSession;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.osgi.service.prefs.BackingStoreException;

/**
 * {@link IProjectService} implementation.
 */
@objid ("00964fc4-9ea6-103b-a520-001ec947cd2a")
public class ProjectService implements IProjectService, EventHandler {
    @objid ("003dc516-7baa-10b3-9941-001ec947cd2a")
    private static final String LAST_USED_WORKSPACE_PREFERENCE_KEY = "workspace.last";

    /**
     * Plenty event listeners are not prepared to receive a PROJECT_CLOSING event if PROJECT_OPENING has never been sent.
     */
    @objid ("33e06f51-2ce4-45e3-b1b4-05764f3927d1")
    private boolean openingEventSent;

    @objid ("8f3c4e54-c92e-4116-b433-e37e7319520b")
    private final IEclipseContext context;

    /**
     * Whether Modelio is in batch mode.
     * 
     * @deprecated FIXME since creation (3.6), another implementation of {@link IProjectService} should be instantiated.
     */
    @objid ("3b141013-8dc9-42dc-9de3-c78f097775c0")
    @Deprecated
    private boolean batchMode;

    @objid ("f354133c-7447-4867-895c-f63bf790b2f0")
    private GProjectPreferenceStore prefsStore;

    @objid ("008017fe-acc2-103b-a520-001ec947cd2a")
    private GProject project;

    @objid ("00801182-acc2-103b-a520-001ec947cd2a")
    private Path workspace;

    @objid ("89878141-4556-4adf-9212-ee39a4c474dc")
    private final GProjectConfigurer projectSynchronizer;

    @objid ("f0d75e23-6c72-4ccb-b883-8377642d25d6")
    private IPersistentPreferenceStore appStateStore;

    @objid ("c4227390-308b-42a2-a7d9-cff8276c54b3")
    private IProjectCreatorFactory projectCreatorFactory;

    @objid ("706d04fa-0399-47bb-85b0-489af72d6b27")
    private IProjectOpener projectOpener;

    /**
     * C'tor
     * @param context the Eclipse context.
     */
    @objid ("00802442-acc2-103b-a520-001ec947cd2a")
    public ProjectService(final IEclipseContext context, IProjectCreatorFactory projectCreatorFactory, IProjectOpener projectOpener, GProjectConfigurer projectSynchronizer) {
        this.context = context;
        this.projectCreatorFactory = projectCreatorFactory;
        this.projectOpener = projectOpener;
        this.projectSynchronizer = projectSynchronizer;
        
        final IEventBroker eventBroker = context.get(IEventBroker.class);
        eventBroker.subscribe("BATCH", this);
    }

    @objid ("005385e0-bb2f-103c-a520-001ec947cd2a")
    @Override
    public void addFragment(GProject openedProject, FragmentDescriptor fragmentDescriptor, IProgressMonitor monitor) throws FragmentConflictException {
        final IProjectFragment newFragment = Fragments.getFactory(fragmentDescriptor).instantiate(fragmentDescriptor);
        openedProject.registerFragment(newFragment, new ModelioProgressAdapter(monitor));
        
        this.context.get(IModelioEventService.class).postAsyncEvent(this, ModelioEvent.FRAGMENT_ADDED, newFragment);
    }

    /**
     * @Inheritdoc
     */
    @objid ("008063d0-acc2-103b-a520-001ec947cd2a")
    @Override
    public void changeWorkspace(Path workspacePath) {
        if (this.project != null) {
            throw new IllegalStateException("A project is already opened.");
        }
        
        if (Files.exists(workspacePath) && Files.isDirectory(workspacePath)) {
            if (Files.isWritable(workspacePath)) {
                this.workspace = workspacePath;
                ProjectService.writePreferedWorkspace(workspacePath);
                this.context.get(IModelioEventService.class).postAsyncEvent(this, ModelioEvent.WORKSPACE_SWITCH, workspacePath);
            } else {
                MessageDialog.openError(Display.getCurrent().getActiveShell(),
                        AppProjectCore.I18N.getString("AccessWorkspaceWrite.failed.title"),
                        AppProjectCore.I18N.getMessage("AccessWorkspaceWrite.failed.message", workspacePath.toString()));
            }
        } else {
            if (workspacePath.toFile().exists()) {
                MessageDialog.openError(Display.getCurrent().getActiveShell(),
                        AppProjectCore.I18N.getString("AccessWorkspaceAccessDenied.failed.title"),
                        AppProjectCore.I18N.getMessage("AccessWorkspaceAccessDenied.failed.message", workspacePath.toString()));
            } else {
                throw new IllegalArgumentException("Invalid workspace path: " + workspacePath);
            }
        }
    }

    @objid ("00809bf2-acc2-103b-a520-001ec947cd2a")
    @Override
    public void closeProject(GProject projectToClose, boolean sendSyncEvents) throws IllegalStateException {
        checkProjectOpened();
        
        if ((projectToClose == null) || (projectToClose != this.project)) {
            throw new IllegalArgumentException("Closing invalid " + projectToClose + " project.");
        }
        
        if (this.openingEventSent) {
            // Plenty event listeners are not prepared to receive a
            // PROJECT_CLOSING if PROJECT_OPENING
            // has never been sent.
            this.context.get(IModelioEventService.class).postSyncEvent(this, ModelioEvent.PROJECT_CLOSING, projectToClose);
        }
        
        // Save the state preferences once the CLOSING events have been fired and processed by listeners
        if (this.appStateStore != null) {
            try {
                this.appStateStore.save();
            } catch (IOException e) {
                AppProjectCore.LOG.debug(e);
            }
        }
        this.appStateStore = null;
        
        // FIXME use the current monitor...
        IModuleManagementService moduleService = this.context.get(IModuleManagementService.class);
        if (moduleService != null) {
            moduleService.stopAllModules(this.project);
        }
        
        // End ModelShield
        try {
            ModelShieldController.onProjectClosing(this.project);
        } catch (RuntimeException e) {
            AppProjectCore.LOG.debug(e);
        }
        
        this.project.close();
        this.project = null;
        
        // preferences store
        this.prefsStore = null;
        
        // Invalidate the current model services instance before removing it
        // so that if some reference have been kept on it by @&#!%*!&
        // programmers, the error will be detected.
        final IMModelServices s = this.context.get(IMModelServices.class);
        if (s != null) {
            ((MModelServices) s).invalidateProject(null);
            this.context.remove(IMModelServices.class);
        }
        
        if (this.openingEventSent) {
            if (sendSyncEvents) {
                this.context.get(IModelioEventService.class).postSyncEvent(this, ModelioEvent.PROJECT_CLOSED, null);
            } else {
                this.context.get(IModelioEventService.class).postAsyncEvent(this, ModelioEvent.PROJECT_CLOSED, null);
            }
        }
        
        this.openingEventSent = false;
    }

    @objid ("4d01f465-9cf4-4e11-9a47-4e061e01c47e")
    @Override
    public void closeProject(GProject projectToClose) throws IllegalStateException {
        closeProject(projectToClose, false);
    }

    @objid ("e92ae6b5-2c62-44c7-8665-3a6bd7f76ee9")
    @Override
    public void createProject(final IProjectCreator projectCreator, final IProjectCreationData data, IProgressMonitor monitor) throws IOException {
        Objects.requireNonNull(data);
        if (projectCreator == null) {
            createProject(data, monitor);
        } else {
            doCreateProject(projectCreator, data, monitor);
        }
    }

    @objid ("0089dd7a-8c65-103c-a520-001ec947cd2a")
    @Override
    public void deleteProject(ProjectDescriptor projectToDelete) throws FileSystemException, IOException {
        // TODO this is a quite naive implementation
        // should deal with project path for delegating project
        
        FileUtils.delete(projectToDelete.getPath());
        refreshWorkspace(null);
    }

    /**
     * @throws java.io.IOException in case of I/O failure.
     */
    @objid ("0089fb8e-8c65-103c-a520-001ec947cd2a")
    @Override
    public void exportProject(ProjectDescriptor projectToExport, Path archivePath, IModelioProgress monitor) throws IOException {
        Zipper zipper = new Zipper(archivePath);
        List<PathMatcher> skipDirectoryMatchers = new ArrayList<>();
        
        // do not export .runtime/modules directory
        skipDirectoryMatchers.add(FileSystems.getDefault().getPathMatcher(("glob:**" + projectToExport.getPath().resolve(".runtime").resolve("modules")).replace("\\", "\\\\")));
        
        // do not export .DS_Store directory (MacOs)
        skipDirectoryMatchers.add(FileSystems.getDefault().getPathMatcher("glob:**.DS_Store"));
        
        zipper.compress(projectToExport.getPath(), skipDirectoryMatchers, null, monitor, null);
        
        AppProjectCore.LOG.info("Exported archive '%s' %,d bytes.", archivePath, Files.size(archivePath));
    }

    @objid ("00804076-acc2-103b-a520-001ec947cd2a")
    @Override
    public String getName() {
        return "ProjectService";
    }

    @objid ("0080cffa-acc2-103b-a520-001ec947cd2a")
    @Override
    public GProject getOpenedProject() {
        return this.project;
    }

    @objid ("14aa57e7-a7cb-4ee1-82ed-973bb22d6870")
    @Override
    public IGProjectPreferenceStore getProjectPreferences(String nodeId) {
        return new GProjectPreferenceNode(this.prefsStore, nodeId);
    }

    @objid ("0053aa84-bb2f-103c-a520-001ec947cd2a")
    @Override
    public ICoreSession getSession() {
        return (this.project != null) ? this.project.getSession() : null;
    }

    @objid ("0080f426-acc2-103b-a520-001ec947cd2a")
    @Override
    public Path getWorkspace() {
        if (this.workspace == null) {
            this.workspace = ProjectService.readPreferedWorkspace();
        }
        return this.workspace;
    }

    @objid ("004d9dc4-8d1e-10b4-9941-001ec947cd2a")
    @Override
    public void handleEvent(Event event) {
        switch (event.getTopic()) {
        case "BATCH":
            onBATCH((CommandLineData) event.getProperty("org.eclipse.e4.data"));
            return;
        default:
            return;
        }
    }

    /**
     * Dirty if either the model or the project preferences is modified. Note that the state preferences do not set the project dirty as they are systematically saved on project closing.
     */
    @objid ("ca2f60e4-1f24-4956-965e-92f53059258c")
    @Override
    public boolean isDirty() {
        boolean isSessionDirty = (this.project != null) && this.project.isOpen() && this.project.getSession().isDirty();
        boolean isPrefStoreDirty = (this.prefsStore != null) && this.prefsStore.needsSaving();
        return isSessionDirty || isPrefStoreDirty;
    }

    @objid ("004d1728-8d1e-10b4-9941-001ec947cd2a")
    @Inject
    @Optional
    public void onBATCH(@EventTopic ("BATCH") final CommandLineData data) {
        final IEventBroker eventBroker = this.context.get(IEventBroker.class);
        eventBroker.unsubscribe(this);
        
        AppProjectCore.LOG.info("Running batch: %s", data.toString());
        if (!data.isEmpty()) {
            this.batchMode = data.isBatch();
            Display.getCurrent().asyncExec(new BatchRunner(this.context.get(IModelioProgressService.class), this, data));
        }
    }

    @objid ("971ad6d9-026d-11e2-8189-001ec947ccaf")
    @Override
    public void openProject(ProjectDescriptor projectToOpen, IAuthData authData, IProgressMonitor monitor) throws GProjectAuthenticationException, IOException, InterruptedException {
        if (this.project != null) {
            throw new IllegalStateException(String.format(
                    "A '%s' project in '%s' is already opened.",
                    this.project.getName(),
                    this.project.getProjectPath()));
        }
        
        Objects.requireNonNull(projectToOpen, "Cannot open 'null' project descriptor.");
        
        // Create a write access to ProjectService members.
        IProjectServiceAccess svcAccess = new IProjectServiceAccess() {
            @Override
            public void setOpeningEventSent(boolean b) {
                ProjectService.this.openingEventSent = b;
            }
        
            @Override
            public void setProjectPreferenceStore(GProjectPreferenceStore prefsStore) {
                ProjectService.this.prefsStore = prefsStore;
            }
        
            @Override
            public void setStatePreferenceStore(IPersistentPreferenceStore store) {
                ProjectService.this.appStateStore = store;
            }
        
            @Override
            public IProjectService getProjectService() {
                return ProjectService.this;
            }
        
            @Override
            public void setOpenedProject(GProject project) {
                ProjectService.this.project = project;
            }
        };
        
        this.projectOpener.openProject(projectToOpen, authData, svcAccess, this.batchMode, monitor);
        
        // new OpenProjectService(this.context, getProjectFactoryConfiguration(), svcAccess, this.projectSynchronizer, this.batchMode)
        // .openProject(projectToOpen, authData, monitor);
    }

    @objid ("004dc0f6-8d1e-10b4-9941-001ec947cd2a")
    @Override
    public void openProject(String projectName, IAuthData authData, IProgressMonitor monitor) throws GProjectAuthenticationException, IOException, InterruptedException {
        final Path projectPath = getWorkspace().resolve(projectName);
        final Path confFile = projectPath.resolve("project.conf");
        
        if (Files.isRegularFile(confFile)) {
            ProjectDescriptor pDesc = GProjectFactory.readProjectDirectory(projectPath);
            openProject(pDesc, authData, monitor);
        }
    }

    /**
     * @param projectToSelect the project to select after refresh, can be null
     */
    @objid ("008118fc-acc2-103b-a520-001ec947cd2a")
    @Override
    public void refreshWorkspace(String projectToSelect) {
        this.context.get(IModelioEventService.class).postAsyncEvent(this, ModelioEvent.WORKSPACE_CONTENTS, this.workspace);
        
        if (projectToSelect != null) {
            this.context.get(IModelioEventService.class).postAsyncEvent(this, ModelioEvent.WORKSPACE_NAVIGATE, projectToSelect);
        }
    }

    @objid ("002e01f8-a4c3-1044-a30e-001ec947cd2a")
    @Override
    public void removeFragment(GProject openedProject, IProjectFragment fragmentToRemove) {
        if (fragmentToRemove == null) {
            throw new IllegalArgumentException("Fragment must not be null.");
        }
        
        openedProject.unregisterFragment(fragmentToRemove);
        
        // Delete the fragment files.
        try {
            fragmentToRemove.delete();
        } catch (final IOException e) {
            AppProjectCore.LOG.warning(e);
        }
        
        this.context.get(IModelioEventService.class).postAsyncEvent(this, ModelioEvent.FRAGMENT_REMOVED, fragmentToRemove);
    }

    @objid ("b336181e-5102-4ca9-a4c5-2408111aca57")
    @Override
    public void renameFragment(GProject openedProject, IProjectFragment fragment, String name) throws FileSystemException, FragmentConflictException, IOException {
        if (fragment == null) {
            throw new IllegalArgumentException("Fragment must not be null.");
        }
        
        // Rename the fragment files.
        fragment.rename(name, null);
        
        this.context.get(IModelioEventService.class).postAsyncEvent(this, ModelioEvent.FRAGMENT_ADDED, fragment);
    }

    @objid ("ddfed9a9-bcb8-4683-9326-13ed63566396")
    @Override
    public void renameProject(ProjectDescriptor projectDescriptor, String name) throws IOException {
        final Path oldPath = projectDescriptor.getPath();
        
        // Set the project name
        projectDescriptor.setName(name);
        
        // Save the new project conf
        Path confFilePath = oldPath.resolve("project.conf");
        new ProjectDescriptorWriter().write(projectDescriptor, confFilePath);
        
        // Move the project directory itself
        final Path targetPath = oldPath.resolveSibling(name);
        
        Files.move(oldPath, targetPath, StandardCopyOption.ATOMIC_MOVE);
        
        refreshWorkspace(name);
    }

    @objid ("0080b83a-acc2-103b-a520-001ec947cd2a")
    @Override
    public void saveProject(IProgressMonitor monitor) throws IOException {
        checkProjectOpened();
        
        final String taskName = AppProjectCore.I18N.getMessage("ProjectService.save.task", this.project.getName());
        SubMonitor m = SubMonitor.convert(monitor, taskName, 1000);
        
        this.context.set(IProgressMonitor.class, m.newChild(50));
        this.context.get(IModelioEventService.class).postSyncEvent(this, ModelioEvent.PROJECT_SAVING, this.project);
        this.context.remove(IProgressMonitor.class);
        
        this.project.save(new ModelioProgressAdapter(m.newChild(900)));
        this.prefsStore.resetDirty();
        
        this.context.get(IModelioEventService.class).postAsyncEvent(this, ModelioEvent.PROJECT_SAVED, this.project);
    }

    @objid ("9de1f14d-8669-48e5-b0c0-11c43702c837")
    private void checkProjectOpened() throws IllegalStateException {
        if (this.project == null) {
            throw new IllegalStateException("No current project.");
        }
    }

    /**
     * Get a module catalog.
     * <p>
     * Returns the module catalog cache. Instantiate the module catalog and the cache on first call.
     * @return the module catalog cache.
     */
    @objid ("e84ef926-a1af-4f78-9342-507d1c7efcb4")
    private IModuleRTCache getModuleCache() {
        // look for the module catalog
        return this.context.get(IModuleRTCache.class);
    }

    /**
     * Get the workspace to use:
     * <ol>
     * <li>use the last used workspace as saved in the preferences</li>
     * <li>default to user's home directory otherwise</li>
     * </ol>
     * @return the workspace path
     */
    @objid ("0035c4b0-7baa-10b3-9941-001ec947cd2a")
    private static Path readPreferedWorkspace() {
        final IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode(AppProjectCore.PLUGIN_ID);
        
        final String lastUsed = prefs.get(ProjectService.LAST_USED_WORKSPACE_PREFERENCE_KEY, null);
        if (lastUsed != null) {
            final Path lastPath = Paths.get(lastUsed);
            if ((lastPath != null) && Files.isDirectory(lastPath)) {
                return lastPath; // we are done
            }
        }
        // Preferences could not provide a valid workspace, default to user's
        // home
        Path defaultPath = Paths.get(System.getProperty("user.home"), "modelio", "workspace");
        if (!Files.exists(defaultPath, LinkOption.NOFOLLOW_LINKS)) {
            (new File(defaultPath.toString())).mkdirs(); // create if the
            // default workspace
            // doesn't exist.
        }
        return defaultPath;
    }

    /**
     * Write the workspace preferences
     * @param workspace the workspace path
     */
    @objid ("0035eddc-7baa-10b3-9941-001ec947cd2a")
    private static void writePreferedWorkspace(Path workspace) {
        if (workspace != null) {
            final IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode(AppProjectCore.PLUGIN_ID);
        
            prefs.put(ProjectService.LAST_USED_WORKSPACE_PREFERENCE_KEY, workspace.toString());
            try {
                prefs.flush();
            } catch (final BackingStoreException e) {
                AppProjectCore.LOG.error(e);
            }
        }
    }

    @objid ("806d8c19-00fa-4de0-8d44-08c18843bbc9")
    private IGProjectEnv getProjectFactoryConfiguration() {
        ModelioEnv env = this.context.get(ModelioEnv.class);
        return new GProjectEnvironment().addMetamodelExtensions(env.getMetamodelExtensions())
                                .setModulesCache(getModuleCache())
                                .setRamcCache(env.getRamcCachePath());
    }

    /**
     * Note that the accessor returns a IPreferenceStore instead of the effective IPersistentPreferenceStore handled by the class. The difference is that IPreferenceStore does not provide a save() method and we do not want callers to save the state
     * preferences themselves. The state preference save policy is strictly under project service control.
     */
    @objid ("f6d902e7-d4bb-4bbb-a5ef-f98f4d4527d6")
    @Override
    public IPreferenceStore getStatePreferences() {
        return this.appStateStore;
    }

    @objid ("fd9e23c8-9e64-4728-8928-26116f210a04")
    @Override
    public void createProject(final IProjectCreationData data, IProgressMonitor monitor) throws IOException {
        Objects.requireNonNull(data);
        doCreateProject(this.projectCreatorFactory.getProjectCreator(data), data, monitor);
    }

    @objid ("bd27b341-5e92-478e-a577-0384f6da0050")
    private void doCreateProject(final IProjectCreator projectCreator, final IProjectCreationData data, IProgressMonitor monitor) throws IOException {
        Objects.requireNonNull(projectCreator);
        
        IModelioProgress progress = monitor == null ? null : new ModelioProgressAdapter(monitor);
        projectCreator.createProject(data, getProjectFactoryConfiguration(), progress);
        this.context.get(IModelioEventService.class).postAsyncEvent(this, ModelioEvent.WORKSPACE_CONTENTS, getWorkspace());
    }

}
