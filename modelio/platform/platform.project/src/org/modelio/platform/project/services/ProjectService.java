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

package org.modelio.platform.project.services;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.data.project.ProjectFileStructure;
import org.modelio.gproject.fragment.Fragments;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.FragmentConflictException;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.gproject.GProjectAuthenticationException;
import org.modelio.gproject.gproject.GProjectConfigurer;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.platform.core.IModelioEventService;
import org.modelio.platform.core.events.ModelioEvent;
import org.modelio.platform.preferences.AppStatePreferenceStore;
import org.modelio.platform.preferences.GProjectPreferenceNode;
import org.modelio.platform.preferences.GProjectPreferenceStore;
import org.modelio.platform.preferences.IGProjectPreferenceStore;
import org.modelio.platform.project.creation.IProjectCreationData;
import org.modelio.platform.project.creation.IProjectCreator;
import org.modelio.platform.project.plugin.AppProjectCore;
import org.modelio.platform.project.services.closeproject.IProjectCloser;
import org.modelio.platform.project.services.createproject.IProjectCreator2;
import org.modelio.platform.project.services.openproject.IProjectOpener;
import org.modelio.platform.project.services.openproject.IProjectServiceAccess;
import org.modelio.platform.project.services.workspace.IWorkspaceService;
import org.modelio.platform.ui.progress.IModelioProgressService;
import org.modelio.platform.ui.progress.ModelioProgressAdapter;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.session.api.ICoreSession;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

/**
 * {@link IProjectService} implementation.
 */
@objid ("00964fc4-9ea6-103b-a520-001ec947cd2a")
public class ProjectService implements IProjectService, EventHandler {
    /**
     * Whether Modelio is in batch mode.
     * 
     * FIXME since creation (3.6), another implementation of {@link IProjectService} should be instantiated.
     */
    @objid ("3b141013-8dc9-42dc-9de3-c78f097775c0")
    private boolean batchMode;

    /**
     * Plenty event listeners are not prepared to receive a PROJECT_CLOSING event if PROJECT_OPENING has never been sent.
     */
    @objid ("33e06f51-2ce4-45e3-b1b4-05764f3927d1")
    private boolean openingEventSent;

    @objid ("d77b76fe-232e-4e7a-9232-de403377c1f1")
    private final IProjectCreator2 projectCreator2;

    @objid ("fc48faca-a678-48a5-a2f8-cb40a8dc9fc8")
    private final IProjectOpener projectOpener;

    @objid ("89878141-4556-4adf-9212-ee39a4c474dc")
    private final GProjectConfigurer projectSynchronizer;

    @objid ("739a6657-2bf8-4419-8800-3f37f9310cc4")
    private final IWorkspaceService worskpaceService;

    @objid ("f0d75e23-6c72-4ccb-b883-8377642d25d6")
    private IPersistentPreferenceStore appStateStore;

    @objid ("f354133c-7447-4867-895c-f63bf790b2f0")
    private GProjectPreferenceStore prefsStore;

    @objid ("008017fe-acc2-103b-a520-001ec947cd2a")
    private GProject project;

    @objid ("2ddd01d5-0476-4acd-aac8-b9e98c131935")
    private final IEclipseContext context;

    @objid ("2879dc6a-867c-440a-8b48-9ddcf61495fb")
    private final IProjectCloser projectCloser;

    @objid ("bbaa47ab-d4dc-4487-a44c-5dc91bbdf1d3")
    private final IFragmentMigratorFactory fragmentMigratorFactory;

    /**
     * Mandatory constructor.
     * 
     * @param context the Eclipse context.
     * @param projectCreator2 the project creation service
     * @param projectOpener the project openeing service
     * @param projectSynchronizer the project synchronization service
     * @param projectCloser the project closing service
     * @param worskpaceService the workspace service
     * @param fragmentMigratorFactory the fragment migration service factory
     */
    @objid ("00802442-acc2-103b-a520-001ec947cd2a")
    public ProjectService(final IEclipseContext context, final IProjectCreator2 projectCreator2, final IProjectOpener projectOpener, final GProjectConfigurer projectSynchronizer, final IProjectCloser projectCloser, final IWorkspaceService worskpaceService, IFragmentMigratorFactory fragmentMigratorFactory) {
        this.context = Objects.requireNonNull(context);
        this.projectCreator2 = Objects.requireNonNull(projectCreator2);
        this.projectOpener = Objects.requireNonNull(projectOpener);
        this.projectSynchronizer = Objects.requireNonNull(projectSynchronizer);
        this.projectCloser = Objects.requireNonNull(projectCloser);
        this.worskpaceService = Objects.requireNonNull(worskpaceService);
        this.fragmentMigratorFactory = Objects.requireNonNull(fragmentMigratorFactory);
        
        // Create a write access to ProjectService members.
        final IProjectServiceAccess svcAccess = new ProjectServiceAccess(this);
        configure(svcAccess);
        
        final IEventBroker eventBroker = context.get(IEventBroker.class);
        eventBroker.subscribe("BATCH", this);
    }

    @objid ("005385e0-bb2f-103c-a520-001ec947cd2a")
    @Override
    public void addFragment(final GProject openedProject, final FragmentDescriptor fragmentDescriptor, final IProgressMonitor monitor) throws FragmentConflictException {
        final IProjectFragment newFragment = Fragments.getFactory(fragmentDescriptor).instantiate(fragmentDescriptor);
        openedProject.registerFragment(newFragment, new ModelioProgressAdapter(monitor));
        
        this.context.get(IModelioEventService.class).postAsyncEvent(this, ModelioEvent.FRAGMENT_ADDED, newFragment);
    }

    @objid ("4bf5aa25-e4cd-4639-850f-47816c59766d")
    @Override
    public void changeWorkspace(Path path) throws IllegalArgumentException, IllegalStateException {
        if (this.project != null) {
            throw new IllegalStateException("A project is already opened.");
        }
        this.worskpaceService.changeWorkspace(path);
    }

    @objid ("177f74d3-e774-49fd-85ed-cdc3e12f08e1")
    @Override
    public void deleteProject(ProjectDescriptor projectToDelete) throws FileSystemException, IOException {
        this.worskpaceService.deleteProject(projectToDelete);
    }

    @objid ("707e1558-0776-4550-be66-21a514e0b74f")
    @Override
    public void exportProject(ProjectDescriptor project, Path archivePath, IModelioProgress monitor) throws IOException {
        this.worskpaceService.exportProject(project, archivePath, monitor);
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
    public IGProjectPreferenceStore getProjectPreferences(final String nodeId) {
        return new GProjectPreferenceNode(this.prefsStore, nodeId);
    }

    @objid ("0053aa84-bb2f-103c-a520-001ec947cd2a")
    @Override
    public ICoreSession getSession() {
        return this.project != null ? this.project.getSession() : null;
    }

    /**
     * Note that the accessor returns a IPreferenceStore instead of the effective IPersistentPreferenceStore handled by the class.
     * 
     * The difference is that IPreferenceStore does not provide a save() method and we do not want callers to save the state preferences themselves.
     * 
     * The state preference save policy is strictly under project service control.
     */
    @objid ("f6d902e7-d4bb-4bbb-a5ef-f98f4d4527d6")
    @Override
    public IPreferenceStore getStatePreferences() {
        return this.appStateStore;
    }

    @objid ("b9dd1c28-cc2d-4143-8b49-57b1a2cc5c35")
    @Override
    public Path getWorkspace() {
        return this.worskpaceService.getWorkspace();
    }

    @objid ("004d9dc4-8d1e-10b4-9941-001ec947cd2a")
    @Override
    public void handleEvent(final Event event) {
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
        final boolean isSessionDirty = this.project != null && this.project.isOpen() && this.project.getSession().isDirty();
        final boolean isPrefStoreDirty = this.prefsStore != null && this.prefsStore.needsSaving();
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
    public void openProject(final ProjectDescriptor projectToOpen, final IAuthData authData, final IProgressMonitor monitor) throws IOException, GProjectAuthenticationException, InterruptedException {
        if (this.project != null) {
            throw new IllegalStateException(String.format(
                    "A '%s' project in '%s' is already opened.",
                    this.project.getName(),
                    this.project.getProjectFileStructure().getProjectPath()));
        }
        
        Objects.requireNonNull(projectToOpen, "Cannot open 'null' project descriptor.");
        
        this.projectOpener.openProject(projectToOpen, authData, this.batchMode, monitor);
    }

    @objid ("004dc0f6-8d1e-10b4-9941-001ec947cd2a")
    @Override
    public void openProject(final URI projectURI, final IAuthData authData, final IProgressMonitor monitor) throws GProjectAuthenticationException, IOException, InterruptedException {
        if (this.project != null) {
            throw new IllegalStateException(String.format(
                    "A '%s' project in '%s' is already opened.",
                    this.project.getName(),
                    this.project.getProjectFileStructure().getProjectPath()));
        }
        
        Objects.requireNonNull(projectURI, "Cannot open 'null' project URI.");
        
        this.projectOpener.openProject(projectURI, authData, this.batchMode, monitor);
    }

    @objid ("29e438c4-9d31-43c5-a5b7-d48c966d1f20")
    @Override
    public void openProject(final String projectName, final IAuthData authData, final IProgressMonitor newChild) throws GProjectAuthenticationException, IOException, InterruptedException {
        if (this.project != null) {
            throw new IllegalStateException(String.format(
                    "A '%s' project in '%s' is already opened.",
                    this.project.getName(),
                    this.project.getProjectFileStructure().getProjectPath()));
        }
        
        Objects.requireNonNull(projectName, "Cannot open 'null' project.");
        
        final Path projectPath = getWorkspace().resolve(projectName);
        final Path confFile = new ProjectFileStructure(projectPath).getProjectConfFile();
        if (Files.isRegularFile(confFile)) {
            openProject(confFile.toUri(), authData, newChild);
        }
    }

    @objid ("06b1cb72-cbd4-4798-9761-554f3afd144a")
    @Override
    public void openProject(ProjectDescriptor projectToOpen, IAuthData authData, boolean batchMode, IProgressMonitor monitor) throws GProjectAuthenticationException, IOException, InterruptedException {
        this.projectOpener.openProject(projectToOpen, authData, batchMode, monitor);
    }

    @objid ("12ae51fc-b8d6-46c1-8ff1-49a538e9f4e1")
    @Override
    public void openProject(URI projectURI, IAuthData authData, boolean batchMode, IProgressMonitor monitor) throws GProjectAuthenticationException, IOException, InterruptedException {
        this.projectOpener.openProject(projectURI, authData, batchMode, monitor);
    }

    @objid ("8e938902-eeec-4181-8f9a-ea5d5d8326f0")
    @Override
    public void refreshWorkspace(String projectToSelect) {
        this.worskpaceService.refreshWorkspace(projectToSelect);
    }

    @objid ("002e01f8-a4c3-1044-a30e-001ec947cd2a")
    @Override
    public void removeFragment(final GProject openedProject, final IProjectFragment fragmentToRemove) {
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
    public void renameFragment(final GProject openedProject, final IProjectFragment fragment, final String name) throws FileSystemException, IOException, FragmentConflictException {
        if (fragment == null) {
            throw new IllegalArgumentException("Fragment must not be null.");
        }
        
        // Rename the fragment files.
        fragment.rename(name, null);
        
        this.context.get(IModelioEventService.class).postAsyncEvent(this, ModelioEvent.FRAGMENT_ADDED, fragment);
    }

    @objid ("17b2b85f-9ab0-429f-8a8f-6e0d5dc5b91e")
    @Override
    public void renameProject(ProjectDescriptor projectDescriptor, String name) throws FileSystemException, IOException {
        this.worskpaceService.renameProject(projectDescriptor, name);
    }

    @objid ("0080b83a-acc2-103b-a520-001ec947cd2a")
    @Override
    public void saveProject(final IProgressMonitor monitor) throws IOException {
        checkProjectOpened();
        
        final String taskName = AppProjectCore.I18N.getMessage("ProjectService.save.task", this.project.getName());
        final SubMonitor m = SubMonitor.convert(monitor, taskName, 1000);
        
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
     * 
     * @return the module catalog cache.
     */
    @objid ("e84ef926-a1af-4f78-9342-507d1c7efcb4")
    private IModuleRTCache getModuleCache() {
        // look for the module catalog
        return this.context.get(IModuleRTCache.class);
    }

    @objid ("e92ae6b5-2c62-44c7-8665-3a6bd7f76ee9")
    @Override
    public void createProject(final IProjectCreator projectCreator, final IProjectCreationData data, final IProgressMonitor monitor) throws IOException {
        Objects.requireNonNull(data);
        this.projectCreator2.createProject(projectCreator, data, monitor);
    }

    @objid ("fd9e23c8-9e64-4728-8928-26116f210a04")
    @Override
    public void createProject(final IProjectCreationData data, final IProgressMonitor monitor) throws IOException {
        Objects.requireNonNull(data);
        this.projectCreator2.createProject(data, monitor);
    }

    @objid ("7d1533ec-7800-448b-8b2c-a27212baf61d")
    @Override
    public void configure(IProjectServiceAccess svcAccess) {
        this.projectCreator2.configure(svcAccess);
        this.projectOpener.configure(svcAccess);
        this.worskpaceService.configure(svcAccess);
        this.projectCloser.configure(svcAccess);
    }

    @objid ("00809bf2-acc2-103b-a520-001ec947cd2a")
    @Override
    public void closeProject(final GProject projectToClose, final boolean sendSyncEvents) throws IllegalStateException {
        checkProjectOpened();
        
        if (projectToClose == null || projectToClose != this.project) {
            throw new IllegalArgumentException("Closing invalid " + projectToClose + " project.");
        }
        
        this.projectCloser.closeProject(projectToClose, sendSyncEvents);
    }

    @objid ("4d01f465-9cf4-4e11-9a47-4e061e01c47e")
    @Override
    public void closeProject(final GProject projectToClose) throws IllegalStateException {
        closeProject(projectToClose, false);
    }

    @objid ("0ee23269-9ebc-4fbb-8a11-b04329eeacc3")
    @Override
    public FragmentsMigrator getFragmentMigrator(IEclipseContext eclipseContext, GProject project, boolean withConfirmation) {
        return this.fragmentMigratorFactory.getFragmentMigrator(eclipseContext, project, withConfirmation);
    }

    /**
     * Provides write accessors on a {@link ProjectService}
     */
    @objid ("53bcd332-f7e4-4565-9a7d-acbc13d2539b")
    protected static class ProjectServiceAccess implements IProjectServiceAccess {
        @objid ("646a73fb-4964-4cdb-86b0-55f96959e041")
        private final ProjectService ps;

        @objid ("3881c5fc-d4b4-47c9-b78c-4cd920d2cebc")
        @Override
        public boolean isOpeningEventSent() {
            return this.ps.openingEventSent;
        }

        /**
         * @param ps the {@link ProjectService} to setup.
         */
        @objid ("934935a3-a06d-4d50-9b89-c5e9e37be9e2")
        public ProjectServiceAccess(ProjectService ps) {
            this.ps = ps;
        }

        @objid ("45634d3c-6cfc-47e9-842a-83f3a2001443")
        @Override
        public void setOpeningEventSent(final boolean b) {
            this.ps.openingEventSent = b;
        }

        @objid ("41bee32e-5b60-472c-9dff-c3f0ed1ad49d")
        @Override
        public void setProjectPreferenceStore(final GProjectPreferenceStore prefsStore) {
            this.ps.prefsStore = prefsStore;
        }

        @objid ("544e4833-c7bc-4c78-866f-2e29e6f1721b")
        @Override
        public IProjectService getProjectService() {
            return this.ps;
        }

        @objid ("9aba6f60-b734-4739-a3c1-22cd674fb8ce")
        @Override
        public void setOpenedProject(final GProject project) {
            this.ps.project = project;
        }

        @objid ("eb1e227c-f9e8-4278-9dcb-c40066188f39")
        @Override
        public void postSyncEvent(ModelioEvent topic, Object data) {
            this.ps.context.get(IModelioEventService.class).postSyncEvent(this.ps, topic, data);
        }

        @objid ("be2df205-1ec9-4cb2-8154-7cbba2cad358")
        @Override
        public void postAsyncEvent(ModelioEvent topic, Object data) {
            this.ps.context.get(IModelioEventService.class).postAsyncEvent(this.ps, topic, data);
        }

        @objid ("99cb1ce3-22ba-4c4b-a0a5-b1bad64f3505")
        @Override
        public IEclipseContext getEclipseContext() {
            return this.ps.context;
        }

        @objid ("1c5993d0-dfaa-4fb4-a1cf-e9b01967624b")
        @Override
        public void openAppStatePreferenceStore(GProject project) {
            this.ps.appStateStore = new AppStatePreferenceStore(project);
        }

        @objid ("3ed82ffc-d55e-4ff7-b71f-ea27d0935119")
        @Override
        public void closeAppStatePreferenceStore() {
            if (this.ps.appStateStore != null) {
                try {
                    this.ps.appStateStore.save();
                } catch (IOException e) {
                    AppProjectCore.LOG.debug(e);
                } finally {
                    this.ps.appStateStore = null;
                }
            }
        }

        @objid ("0a9e400f-6f6d-48ac-bc24-5acbe9f4f7b1")
        @Override
        public Path getWorkspace() {
            return this.ps.getWorkspace();
        }

    }

}
