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
package org.modelio.platform.project.services.openproject;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.core.services.statusreporter.StatusReporter;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.gproject.GProblem;
import org.modelio.gproject.MigrationFailedException;
import org.modelio.gproject.auth.GProjectAuthenticationException;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.GProjectDescriptor;
import org.modelio.gproject.data.project.GProjectDescriptorReader;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.env.GProjectEnvironment;
import org.modelio.gproject.env.IGProjectEnv;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.gproject.parts.fragment.VersionHelper;
import org.modelio.gproject.parts.module.GModule;
import org.modelio.gproject.project.GProject;
import org.modelio.metamodel.PredefinedTypes;
import org.modelio.metamodel.mmextensions.infrastructure.IInfrastructureModelFactory;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.mmextensions.standard.services.MModelServices;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.platform.core.ModelioEnv;
import org.modelio.platform.core.events.ModelioEvent;
import org.modelio.platform.mda.infra.service.IModuleManagementService;
import org.modelio.platform.preferences.GProjectPreferenceStore;
import org.modelio.platform.project.modelshield.ModelShieldController;
import org.modelio.platform.project.plugin.AppProjectCore;
import org.modelio.platform.project.prefs.ProjectPreferencesHelper;
import org.modelio.platform.project.prefs.ProjectPreferencesKeys;
import org.modelio.platform.project.services.FragmentsMigrator;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.platform.project.services.ModulesUpdater;
import org.modelio.platform.project.services.syncproject.GProjectConfPlan;
import org.modelio.platform.project.services.syncproject.IGProjectConfUpdater;
import org.modelio.platform.project.services.syncproject.IGProjectConfUpdater.IAccessDeniedHandler;
import org.modelio.platform.project.services.syncproject.IGProjectConfUpdater.IGModuleUpdatePolicy;
import org.modelio.platform.ui.progress.ModelioProgressAdapter;
import org.modelio.platform.ui.swt.DefaultShellProvider;
import org.modelio.platform.utils.log.writers.PluginLogger;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.files.CloseOnFail;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.version.ModelioVersion;

/**
 * Implementation of {@link IProjectService#openProject(ProjectDescriptor, IAuthData, IProgressMonitor)}
 * 
 * @since 5.?
 */
@objid ("f2af0b85-65e8-4769-896d-c93e6b357b85")
public class OpenProjectService implements IProjectOpener {
    @objid ("cc38b856-aa3b-4bb3-a455-43fdb56c79f6")
    private static final boolean askFragmentMigrationConfirmation = true;

    /**
     * Project being opened.
     */
    @objid ("c4066d91-0bbd-4e3d-a2ef-8f58365244ff")
    private IGProject project;

    @objid ("d9979cf2-5ae8-4a16-8585-8842a67b702f")
    protected IProjectServiceAccess projectServiceAccess;

    @objid ("0bef8f84-c3b2-4ad1-99ae-a3a826417050")
    private IGProjectConfUpdater synchronizer;

    /**
     * @param synchronizer the service to synchronize the project from remote project configuration
     */
    @objid ("8efd9838-4109-4254-8aa6-c70dc012d966")
    public  OpenProjectService(IGProjectConfUpdater synchronizer) {
        this.synchronizer = synchronizer;
    }

    @objid ("86cd6b98-2366-46a4-92ac-d7c2e6950f74")
    @Override
    public void configure(IProjectServiceAccess projectService) {
        this.projectServiceAccess = projectService;
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * This implementation carries out the following tasks:
     * <ol>
     * <li>deal with batch mode, because batch mode somewhat changes the open execution sequence</li>
     * <li>build a IGProject instance</li>
     * <li>synchronize the project agains't server if applicable. This produces a list a {@link GProjectConfPlan} .</li>
     * <li>apply the project configuration plan</li>
     * <li>diagnose migration for Modelio version</li>
     * <li>load project preferences and state preferences</li>
     * <li>open the project</li>
     * <li>update the modules</li>
     * <li>Configure project preferences</li>
     * <li>Migrate model if needed and allowed</li>
     * <li>Configure ModelShield for the project</li>
     * <li>Start all modules</li>
     * <li>Save local project descriptor</li>
     * <li>The project is officially opened in Modelio</li>
     * </ol>
     * </p>
     * <p>
     * Progress management is based on six phases for a total of 500 units:
     * <ol>
     * <li>Preparing project, 50 units => covers steps 2,3</li>
     * <li>Synchronizing with server, 50 units => covers steps 4,5,6, 7</li>
     * <li>Loading models 250 units => covers steps 8, 9, 10</li>
     * <li>Migration, 50 units => coves steps 11, 12</li>
     * <li>Starting modules, 50 units => 13</li>
     * <li>Opening, some housekeeping including save, 50 units, covers 14, 15</li>
     * </ol>
     * </p>
     */
    @objid ("daf99e40-a31d-4cba-bc5e-6cc51e699f37")
    @Override
    public void openProject(final GProjectDescriptor projectToOpen, final IAuthData authData, final IProgressMonitor aMonitor) throws GProjectAuthenticationException, IOException, InterruptedException {
        // Reconfigure this instance
        this.project = null;
        final IGProjectEnv gprojectEnv = getProjectEnv();
        
        // Check illegal parameters
        if (projectToOpen == null) {
            throw new IllegalArgumentException("Cannot open 'null' project.");
        }
        final String taskName = AppProjectCore.I18N.getMessage("ProjectService.open.task", projectToOpen.getName());
        int remainingTicks = 500;
        final SubMonitor monitor = SubMonitor.convert(aMonitor, taskName, remainingTicks);
        
        // PROGRESS PHASE 1 - 50 units
        monitor.setTaskName(AppProjectCore.I18N.getMessage("ProjectService.open.phase1"));
        
        // step 1: Configure execution depending on batch mode - zero progress units
        final IModuleManagementService moduleService = getContext().get(IModuleManagementService.class);
        IAuthenticationPrompter authPrompter = null;
        ProjectAuthsChecker authsChecker = null;
        Shell parentShell = null;
        if (!isBatchMode()) {
            parentShell = getASwtShell();
            authPrompter = new AuthenticationPrompter(parentShell);
            authsChecker = new ProjectAuthsChecker(authPrompter, moduleService, authData);
            authsChecker.checkMissingAuths(projectToOpen);
        }
        
        // step 2 - instantiate the gproject instance - 50 progress units
        final StatusReporter statusReporter = getContext().get(StatusReporter.class);
        final ProjectMonitor projectMonitor = new ProjectMonitor(this.projectServiceAccess, statusReporter);
        this.project = GProject.newBuilder(projectToOpen)
                .withEnvironment(gprojectEnv)
                .withAuth(authData)
                .withEventMonitor(projectMonitor)
                .build(new ModelioProgressAdapter(monitor.newChild(50)));
        remainingTicks-=50;
        
        // PROGRESS PHASE 2 - 50 units
        monitor.setTaskName(AppProjectCore.I18N.getMessage("ProjectService.open.phase2"));
        
        GProjectConfPlan plan = null;
        OpenState openState = new OpenState(this.projectServiceAccess.getProjectService(), this.project);
        try (CloseOnFail shield = new CloseOnFail(openState);) {
            this.projectServiceAccess.setOpeningEventSent(false);
        
            // step 3 : Synchronize the project against server - 10 progress units
            plan = synchronizeProjectAgainstServer(authPrompter, monitor.newChild(10));
            remainingTicks-=10;
        
            // step 4 : apply the configuration plan - 40 progress units
            startSynchronizationPlan(monitor, remainingTicks, plan);
        
            // step 5 : Diagnose Modelio version and project version compatibility for migration possibilities - zero progress units
            MigrationDiagnostic migrationDiagnostic = checkVersionCompatibility(this.project);
            final boolean updateModules = migrationDiagnostic.getAction() == MigrationDiagnostic.MigrationAction.AUTO_MIGRATION_POSSIBLE;
        
            if (migrationDiagnostic.getAction() != MigrationDiagnostic.MigrationAction.NO_MIGRATION_NEEDED) {
                // DIRTY: the call to onModelioVersionMismatch() is expected to throw an exception if the user refuses migration
                onModelioVersionMismatch(this.project.getDescriptor(), migrationDiagnostic.getProjectRequiredVersion(), taskName, parentShell);
            }
        
            // step 6 : project and state preferences - zero progress units
            final GProjectPreferenceStore prefsStore = new GProjectPreferenceStore(this.project);
            this.projectServiceAccess.setProjectPreferenceStore(prefsStore);
            this.projectServiceAccess.openAppStatePreferenceStore(this.project);
            openState.add( () -> this.projectServiceAccess.closeAppStatePreferenceStore());
        
            // PROGRESS PHASE 3 - 250 units
            monitor.setTaskName(AppProjectCore.I18N.getMessage("ProjectService.open.phase3"));
            // step 7 : Open the project - 250 progress units
            this.project.open(new ModelioProgressAdapter(monitor.newChild(250)));
            openState.add( () -> this.project.close());
        
            // Register the services that depends on project to the context model service
            final MModelServices modelServices = new MModelServices(this.project.getSession());
            getContext().set(IMModelServices.class, modelServices);
        
            // Fire session up event to initialize MDA stuff : API impl and dynamic features
            this.projectServiceAccess.postSyncEvent(ModelioEvent.PROJECT_OPENING_MDA_SESSION_UP, this.project);
        
            this.project.getSession().getModelChangeSupport().addModelChangeListener(event -> {
                // Force refresh of e4 elements...
                final IEventBroker eventBroker = getContext().get(IEventBroker.class);
                eventBroker.post(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC, UIEvents.ALL_ELEMENT_ID);
            });
        
            // Fire PROJECT_OPENING event
            // note: most of ModelioEvent.PROJECT_OPENING listeners need a CoreSession
            // note: the Script engine expects IProjectService.getProject() return null until PROJECT_OPENING is sent.
            this.projectServiceAccess.postSyncEvent(ModelioEvent.PROJECT_OPENING, this.project);
            this.projectServiceAccess.setOpeningEventSent(true);
            openState.add(() -> this.projectServiceAccess.postSyncEvent(ModelioEvent.PROJECT_CLOSING, this.project));
        
            // step 8 : Update modules - zero progress units
            updateModules(statusReporter, moduleService, updateModules, monitor.newChild(50));
        
            // step 9 : Configure project preferences - zero progress units
            installProjectPreferencesDefaults(this.projectServiceAccess);
            installProjectPreferences(prefsStore, this.project.getSession());
        
            // Now that PROJECT_OPENING events have been sent and processed, we can "declare" the project is opened
            // and update ProjectService with the newly opened project.
            this.projectServiceAccess.setOpenedProject(this.project);
        
            // PROGRESS PHASE 4 - 50 units
            // step 11 : Migrate model if needed and allowed - 50 progress units
            // note : Diagram migration need projectServiceAccess.setOpenedProject() to be called before
            monitor.setTaskName(AppProjectCore.I18N.getMessage("ProjectService.open.phase4"));
            migrateFragments(this.project, monitor.newChild(50));
        
            // step 12 : Configure ModelShield for the project - zero progress units
            ModelShieldController.onProjectOpening(this.project);
        
            // PROGRESS PHASE 5 - 50 units
            monitor.setTaskName(AppProjectCore.I18N.getMessage("ProjectService.open.phase5"));
            // step 13 : Start all modules - 50 progress units
            moduleService.startAllModules(this.project, monitor.newChild(50));
        
            // Post processed configuration actions
            if (authsChecker != null)
                authsChecker.checkAuthErrors(monitor.newChild(1), this.project);
        
            // PROGRESS PHASE 6 - 50 units
            // step 15 - Save project and fire app events - 50 progress units
            monitor.setTaskName(AppProjectCore.I18N.getMessage("ProjectService.open.phase6"));
            this.project.save(new ModelioProgressAdapter(monitor.newChild(50)));
        
            // Fire PROJECT_OPENED
            if (isBatchMode()) {
                this.projectServiceAccess.postSyncEvent(ModelioEvent.PROJECT_OPENED, this.project);
            } else {
                this.projectServiceAccess.postAsyncEvent(ModelioEvent.PROJECT_OPENED, this.project);
            }
        
            // Validate project opening
            shield.success();
        
            monitor.done();
        } finally {
            // Sort out issues
            if (plan != null) {
                List<GProblem> problems = plan.getProblems();
                if (!problems.isEmpty()) {
                    reportSynchronizationProblems(parentShell, problems);
                }
                plan.stop();
            }
        
            reportProjectProblems();
        }
        
    }

    @objid ("a40f5f63-7577-45dc-ad54-e664950c192a")
    @Override
    public void openProject(URI projectURI, IAuthData authData, IProgressMonitor monitor) throws GProjectAuthenticationException, IOException, InterruptedException {
        IProjectService projectService = this.projectServiceAccess.getProjectService();
        
        final GProjectDescriptor projectToOpen = new GProjectDescriptorReader().read(Paths.get(projectURI), null);
        projectService.openProject(projectToOpen, authData, monitor);
        
    }

    @objid ("e55fa522-2d7d-45e7-ab0b-0d55310b427e")
    protected final Shell getASwtShell() {
        Shell shell = (Shell) getContext().getActive(IServiceConstants.ACTIVE_SHELL);
        
        if (shell == null) {
            final IShellProvider sp = getContext().getActive(IShellProvider.class);
            if (sp != null) {
                shell = sp.getShell();
            }
        }
        
        if (shell == null) {
            shell = CompletableFuture
                    .supplyAsync(
                            DefaultShellProvider::getBestParentShell,
                            Display.getDefault()::syncExec)
                    .join();
        
        }
        return shell;
    }

    @objid ("a6aa2299-2c2c-4e83-9f63-2025f692b152")
    protected final IEclipseContext getContext() {
        return this.projectServiceAccess.getEclipseContext();
    }

    @objid ("4e499adb-1b68-4509-bd37-fcdc5bec3118")
    protected final boolean isBatchMode() {
        return this.projectServiceAccess.getProjectService().isBatchMode();
    }

    @objid ("1b48e687-73cd-412b-8fe5-6a727daba4e5")
    protected void migrateFragments(final IGProject gproject, final SubMonitor mon) throws IOException, InterruptedException {
        if (isBatchMode()) {
            // Don't migrate in batch mode, abort if any fragment need migration
            final String pb = gproject.getParts(IGModelFragment.class)
                    .stream()
                    .filter(f -> f.getState().getDownError() instanceof MigrationFailedException)
                    .map(f -> f.getId() + ": " + f.getState().getDownError().getLocalizedMessage())
                    .collect(Collectors.joining("\n"));
        
            if (!pb.isEmpty()) {
                throw new IOException(pb);
            }
        } else {
            new FragmentsMigrator(getContext(), gproject, OpenProjectService.askFragmentMigrationConfirmation).migrateFragments(mon);
        }
        
    }

    /**
     * Called when the current Modelio version does not match the project Modelio version.
     * <p>
     * This method must either:
     * <ul>
     * <li>ignore and return,
     * <li>display a dialog and return,
     * <li>throw an exception. The caller will display a message dialog from the exception content.
     * </ul>
     * <p>
     * This method may be redefined by subclasses to add more constraints.
     * @param projectDescriptor the project descriptor
     * @param neededVersion the project Modelio version
     * @param message a computed message that indicates the migration conditions
     * @throws IOException to refuse opening the project. The caller will display a message dialog from the exception content.
     */
    @objid ("df849dae-fa52-4f19-9a95-1295ec36e7ff")
    protected void onModelioVersionMismatch(final GProjectDescriptor projectDescriptor, final Version neededVersion, final String message, final Shell parent) throws IOException {
        final String fProjectVersion = neededVersion.toString("V.R");
        final String strModelioVersion = ModelioVersion.VERSION.toString("V.R");
        
        boolean acceptMigration;
        try {
            acceptMigration = CompletableFuture.supplyAsync(
                    () -> MessageDialog.openQuestion(
                            parent,
                            AppProjectCore.I18N.getMessage("OpenProjectService.version.dialog.possible.title", projectDescriptor.getName(), fProjectVersion, strModelioVersion),
                            AppProjectCore.I18N.getMessage("OpenProjectService.version.dialog.possible.message", projectDescriptor.getName(), fProjectVersion, strModelioVersion, message)),
                    x -> parent.getDisplay().asyncExec(x))
                    .join();
        } catch (CompletionException | CancellationException e) {
            IOException ioe = new IOException(message);
            ioe.addSuppressed(e);
            throw ioe;
        }
        
        if (!acceptMigration) {
            throw new IOException(message);
        }
        
    }

    /**
     * Check 'project' compatibility with the the current Modelio running instance version.
     * @return A {@link MigrationDiagnostic} that indicates what kind of migration has to be applied.
     */
    @objid ("7178b2b5-3e8b-4e6b-8daa-fa0faa8a0b1b")
    private MigrationDiagnostic checkVersionCompatibility(IGProject gProject) {
        String modelioVersionStr = ModelioVersion.MAJOR_MINOR.toString("V.R");
        
        // Get the project version, use a fallback guess for older versions
        Version projectRequiredVersion = (gProject.getExpectedModelioVersion() != null) ? gProject.getExpectedModelioVersion() : VersionHelper.guessModelioVersion(gProject);
        
        String projectVersionStr = (projectRequiredVersion == null) ? "<none>" : projectRequiredVersion.toString("V.R");
        
        if (projectRequiredVersion == null) {
            String message = AppProjectCore.I18N.getMessage("OpenProjectService.version.none", gProject.getName(), projectVersionStr, modelioVersionStr);
            return new MigrationDiagnostic(MigrationDiagnostic.MigrationAction.NO_POSSIBLE_MIGRATION, projectRequiredVersion, message);
        }
        
        // For version comparisons, get rid of the build id which is not relevant
        projectRequiredVersion = projectRequiredVersion.withoutBuild();
        
        // Project version is more recent than Modelio, in other words Modelio is too old for the project
        if (projectRequiredVersion.isNewerThan(ModelioVersion.MAJOR_MINOR)) {
            String message = AppProjectCore.I18N.getMessage("OpenProjectService.version.future", gProject.getName(), projectVersionStr, modelioVersionStr);
            return new MigrationDiagnostic(MigrationDiagnostic.MigrationAction.BACKWARD_MIGRATION, projectRequiredVersion, message);
        }
        
        // If project is older than Modelio, need to differentiate 1.x, 2.x or 3.x cases
        if (projectRequiredVersion.isOlderThan(ModelioVersion.MAJOR_MINOR)) {
            if (projectRequiredVersion.getMajorVersion() < 2) {
                // Need to be migrated from Modelio 1
                String message = AppProjectCore.I18N.getMessage("OpenProjectService.version.v1", gProject.getName(), projectVersionStr, modelioVersionStr);
                return new MigrationDiagnostic(MigrationDiagnostic.MigrationAction.MIGRATION_FROMV1_REQUIRED, projectRequiredVersion, message);
            } else if (projectRequiredVersion.getMajorVersion() < 3) {
                // Need to be migrated from Modelio 2
                String message = AppProjectCore.I18N.getMessage("OpenProjectService.version.v2", gProject.getName(), projectVersionStr, modelioVersionStr);
                return new MigrationDiagnostic(MigrationDiagnostic.MigrationAction.MIGRATION_FROMV2_REQUIRED, projectRequiredVersion, message);
            } else {
                // Migration needed from 3.x
                // Automatic migration possible
                String message = AppProjectCore.I18N.getMessage("OpenProjectService.version.MigrationNeeded", gProject.getName(), projectVersionStr, modelioVersionStr);
                return new MigrationDiagnostic(MigrationDiagnostic.MigrationAction.AUTO_MIGRATION_POSSIBLE, projectRequiredVersion, message);
            }
        }
        return new MigrationDiagnostic(MigrationDiagnostic.MigrationAction.NO_MIGRATION_NEEDED, projectRequiredVersion, "");
    }

    @objid ("74f15440-503a-4bcb-8ec5-c4eb69a99812")
    private IGModuleUpdatePolicy getModuleUpdater() {
        IModuleManagementService svc = this.getContext().get(IModuleManagementService.class);
        return new IGModuleUpdatePolicy() {
            @Override
            public Collection<GProblem> updateModule(IModelioProgress progress, IGProject aproject, IModuleHandle handle, GProjectPartDescriptor md) {
                try {
                    svc.installModule(progress, aproject, handle, md.getLocation());
                } catch (ModuleException e) {
                    return Collections.singleton(new GProblem(md, e));
                }
                return Collections.emptyList();
            }
        
            @Override
            public Collection<GProblem> removeModule(IModelioProgress progress, IGProject aproject, GModule md) {
                try {
                    svc.removeModule(md, false);
                } catch (ModuleException e) {
                    return Collections.singleton(new GProblem(md, e));
                }
                return Collections.emptyList();
            }
        
            @Override
            public Collection<GProblem> installModule(IModelioProgress progress, IGProject aproject, IModuleHandle handle, GProjectPartDescriptor md) {
                try {
                    svc.installModule(progress, aproject, handle, md.getLocation());
                } catch (ModuleException e) {
                    return Collections.singleton(new GProblem(md, e));
                }
                return Collections.emptyList();
            }
        };
        
    }

    // /**
    // * @return the module catalog
    // */
    // @objid ("a14d7ad3-aec1-462a-9dc4-703fa99d6b59")
    // private IModuleRTCache getModuleCache() {
    // // look for the module catalog
    // return getContext().get(IModuleRTCache.class);
    // }
    @objid ("76f981c3-258b-4a63-ac0b-136f55dbf70a")
    private IGProjectEnv getProjectEnv() {
        final ModelioEnv env = getContext().get(ModelioEnv.class);
        IModuleRTCache moduleCache = getContext().get(IModuleRTCache.class);
        return new GProjectEnvironment()
                .addMetamodelExtensions(env.getActiveMetamodelExtensions())
                .setModulesCache(moduleCache)
                .setRamcCache(env.getRamcCachePath());
        
    }

    @objid ("99a1f132-00dd-43e0-9671-9617eecc334a")
    private void installProjectPreferences(GProjectPreferenceStore prefsStore, ICoreSession session) {
        final MTools mTools = MTools.get(this.project.getSession());
        final IStandardModelFactory factory = mTools.getModelFactory(IStandardModelFactory.class);
        final IInfrastructureModelFactory imfactory = mTools.getModelFactory(IInfrastructureModelFactory.class);
        
        ProjectPreferencesHelper prefsHelper = new ProjectPreferencesHelper(prefsStore, session);
        
        // Setup the model factories
        factory.setDefaultValue("DEFAULT_ATTRIBUTE_TYPE", prefsHelper.getAttributeDefaultType());
        factory.setDefaultValue("DEFAULT_ATTRIBUTE_VISIBILITY", prefsHelper.getAttributeDefaultVisibility());
        factory.setDefaultValue("DEFAULT_PARAMETER_TYPE", prefsHelper.getParameterDefaultType());
        factory.setDefaultValue("DEFAULT_RETURN_TYPE", prefsHelper.getReturnDefaultType());
        imfactory.setDefaultValue("DEFAULT_NOTE_MIMETYPE", prefsHelper.getNoteDefaultMimeType());
        
        // Setup a listener that reconfigures the model factories
        // when project preferences change
        prefsStore.addPropertyChangeListener(event -> {
            final String name = event.getProperty();
        
            if (name.endsWith(ProjectPreferencesKeys.ATT_DEFAULT_TYPE_PREFKEY)) {
                factory.setDefaultValue("DEFAULT_ATTRIBUTE_TYPE", prefsHelper.getAttributeDefaultType());
            } else if (name.endsWith(ProjectPreferencesKeys.ATT_DEFAULT_VIS_PREFKEY)) {
                factory.setDefaultValue("DEFAULT_ATTRIBUTE_VISIBILITY", prefsHelper.getAttributeDefaultVisibility());
            } else if (name.endsWith(ProjectPreferencesKeys.PARAM_DEFAULT_TYPE_PREFKEY)) {
                factory.setDefaultValue("DEFAULT_PARAMETER_TYPE", prefsHelper.getParameterDefaultType());
            } else if (name.endsWith(ProjectPreferencesKeys.RETURN_DEFAULT_TYPE_PREFKEY)) {
                factory.setDefaultValue("DEFAULT_RETURN_TYPE", prefsHelper.getReturnDefaultType());
            } else if (name.endsWith(ProjectPreferencesKeys.NOTE_DEFAULT_MIMETYPE_PREFKEY)) {
                imfactory.setDefaultValue("DEFAULT_NOTE_MIMETYPE", prefsHelper.getNoteDefaultMimeType());
            }
        });
        
    }

    @objid ("bd91bb36-5ba8-4fff-8d06-59abf5d6dd8b")
    private void installProjectPreferencesDefaults(final IProjectServiceAccess svcAccess) {
        final CoreSession coreSession = (CoreSession) this.project.getSession();
        try (ITransaction t = coreSession.getTransactionSupport().createTransaction("Create default preference DataTypes")) {
            // PredefinedTypes might not be deployed yet, use a shell object if
            // they are missing
            final SmClass datatypeMClass = coreSession.getMetamodel().getMClass(DataType.class);
            final DataType stringDataType = (DataType) coreSession.getSmFactory().getObjectReference(datatypeMClass, PredefinedTypes.STRING_UID, PredefinedTypes.STRING_NAME);
            final DataType integerDataType = (DataType) coreSession.getSmFactory().getObjectReference(datatypeMClass, PredefinedTypes.INTEGER_UID, PredefinedTypes.INTEGER_NAME);
            t.commit();
        
            // Define default values if not already defined
            final IPreferenceStore store = svcAccess.getProjectService().getProjectPreferences(ProjectPreferencesKeys.NODE_ID);
            if (store.getDefaultString(ProjectPreferencesKeys.ATT_DEFAULT_TYPE_PREFKEY).isEmpty()) {
                store.setDefault(ProjectPreferencesKeys.ATT_DEFAULT_TYPE_PREFKEY, new MRef(stringDataType).toString());
                store.setToDefault(ProjectPreferencesKeys.ATT_DEFAULT_TYPE_PREFKEY);
            }
            if (store.getDefaultString(ProjectPreferencesKeys.ATT_DEFAULT_VIS_PREFKEY).isEmpty()) {
                store.setDefault(ProjectPreferencesKeys.ATT_DEFAULT_VIS_PREFKEY, VisibilityMode.PUBLIC.getName());
                store.setToDefault(ProjectPreferencesKeys.ATT_DEFAULT_VIS_PREFKEY);
            }
            if (store.getDefaultString(ProjectPreferencesKeys.PARAM_DEFAULT_TYPE_PREFKEY).isEmpty()) {
                store.setDefault(ProjectPreferencesKeys.PARAM_DEFAULT_TYPE_PREFKEY, new MRef(stringDataType).toString());
                store.setToDefault(ProjectPreferencesKeys.PARAM_DEFAULT_TYPE_PREFKEY);
            }
            if (store.getDefaultString(ProjectPreferencesKeys.RETURN_DEFAULT_TYPE_PREFKEY).isEmpty()) {
                store.setDefault(ProjectPreferencesKeys.RETURN_DEFAULT_TYPE_PREFKEY, new MRef(integerDataType).toString());
                store.setToDefault(ProjectPreferencesKeys.RETURN_DEFAULT_TYPE_PREFKEY);
            }
            if (store.getDefaultString(ProjectPreferencesKeys.RICHNOTE_DEFAULT_TYPE_PREFKEY).isEmpty()) {
                // Use a default value
                String defaultValue;
                if (System.getProperty("os.name").equals("Linux")) {
                    defaultValue = "application/vnd.oasis.opendocument.text";
                } else {
                    defaultValue = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
                }
        
                store.setDefault(ProjectPreferencesKeys.RICHNOTE_DEFAULT_TYPE_PREFKEY, defaultValue);
                store.setToDefault(ProjectPreferencesKeys.RICHNOTE_DEFAULT_TYPE_PREFKEY);
            }
            if (store.getDefaultString(ProjectPreferencesKeys.NOTE_DEFAULT_MIMETYPE_PREFKEY).isEmpty()) {
                store.setDefault(ProjectPreferencesKeys.NOTE_DEFAULT_MIMETYPE_PREFKEY, "text/plain");
                store.setToDefault(ProjectPreferencesKeys.NOTE_DEFAULT_MIMETYPE_PREFKEY);
            }
        }
        
    }

    @objid ("bbd54128-da93-4cde-bb4b-db3154d4d3ae")
    private void logProblems(List<GProblem> problems) {
        boolean debugLog = AppProjectCore.LOG.isDebugEnabled();
        for (final GProblem p : problems) {
            if (p.getCause() instanceof RuntimeException) {
                AppProjectCore.LOG.warning(p.getCause());
            } else if (debugLog) {
                AppProjectCore.LOG.debug(p.getCause());
            }
        }
        
    }

    @objid ("33b858a4-2e24-4e70-9966-e868773e2767")
    private void reportProjectProblems() {
        if (this.project.getProblems().isEmpty())
            return;
        
        logProblems(this.project.getProblems());
        
        if (isBatchMode()) {
            for ( GProblem problem : this.project.getProblems() ) {
                System.out.printf(" - %s : %s", problem.getSubject(), problem.getProblem());
            }
        }
        
    }

    @objid ("352ea77b-16ed-4820-8342-e3387311e838")
    private void reportSynchronizationFail(final IOException e, final IGProject failedProj) {
        final String err = FileUtils.getLocalizedMessage(e);
        final String message = AppProjectCore.I18N.getMessage("ProjectService.ProjectSynchroFailed.message", this.project.getName(), err);
        
        AppProjectCore.LOG.warning(message);
        AppProjectCore.LOG.debug(e);
        
        if (isBatchMode()) {
            System.err.println(message);
        } else {
            Display.getDefault().asyncExec(() -> {
                final String title = AppProjectCore.I18N.getMessage("ProjectService.ProjectSynchroFailed.title", failedProj.getName());
                MessageDialog.openWarning(new DefaultShellProvider().getShell(), title, message);
            });
        }
        
    }

    /**
     * Log synchronization problems and report them to the user.
     * @param shell a parent shell.
     * @param problems the problems encountered during the synchronization process.
     */
    @objid ("2899bfb2-9e6e-4ed8-bfab-c6fdb5bf0cc7")
    private void reportSynchronizationProblems(Shell shell, List<GProblem> problems) {
        final String title = AppProjectCore.I18N.getMessage("ProjectService.ProjectSynchroProblems.title", this.project.getName());
        final StringBuilder sb = new StringBuilder();
        
        sb.append(AppProjectCore.I18N.getMessage("ProjectService.ProjectSynchroProblems.message", this.project.getName()));
        
        for (final GProblem p : problems) {
            sb.append(" - ").append(p.getSubject()).append(": ");
            sb.append(p.getProblem());
            sb.append("\n");
        }
        
        AppProjectCore.LOG.warning(title);
        AppProjectCore.LOG.warning(sb.toString());
        logProblems(problems);
        
        if (isBatchMode()) {
            System.err.println(title);
            System.err.println(sb.toString());
        } else {
            // Get a shell
            final Display d = shell != null ? shell.getDisplay() : Display.getDefault();
            d.syncExec(() -> GProblemReportDialog.open(shell, OpenProjectService.this.project.getName(), problems));
        }
        
    }

    @objid ("a7c98fe1-bad3-4621-ab9b-249fb334bcc3")
    private void startSynchronizationPlan(final SubMonitor monitor, int remainingTicks, GProjectConfPlan plan) {
        final int planTicks = 40;
        if (! plan.isEmpty()) {
            plan.dump(s -> AppProjectCore.LOG.debug(s));
            plan.start(ModelioProgressAdapter.convert(monitor.newChild(planTicks), 100));
        }
        
        remainingTicks-=planTicks;
        monitor.setWorkRemaining(remainingTicks);
        
    }

    @objid ("422bdc4c-6e06-4927-9ce7-573b3a3c9179")
    private GProjectConfPlan synchronizeProjectAgainstServer(IAuthenticationPrompter authPrompter, final SubMonitor aMonitor) {
        SubMonitor progress = SubMonitor.convert(aMonitor);
        try {
            if (this.synchronizer != null) {
                IAccessDeniedHandler authCB = (authPrompter == null) ? (name, uri, data, e) -> null : (name, uri, data, e) -> authPrompter.promptAuthentication(data, name, uri.toString(), FileUtils.getLocalizedMessage(e));
                GProjectConfPlan plan = this.synchronizer.computeReconfigurationPlan(
                        this.project,
                        authCB,
                        getModuleUpdater(),
                        new ModelioProgressAdapter(progress.newChild(50)));
                return plan;
            }
        } catch (final IOException e) {
            reportSynchronizationFail(e, this.project);
        }
        progress.done();
        return new GProjectConfPlan(this.project); // Simply return an empty plan
    }

    @objid ("46fcb043-0d46-4325-a4a6-835e8b72872e")
    private void updateModules(final StatusReporter statusReporter, final IModuleManagementService moduleService, final boolean migrateModules, final SubMonitor aMonitor) {
        final SubMonitor monitor = SubMonitor.convert(aMonitor, 1);
        
        moduleService.initRTModules(this.project);
        final IModuleStore modulesCatalog = getContext().get(IModuleStore.class);
        final ModulesUpdater modulesUpdater = new ModulesUpdater(moduleService, modulesCatalog, this.project, false);
        
        if (migrateModules) {
            modulesUpdater.run(monitor.newChild(1));
        } else {
            // Ensure mandatory modules are installed
            modulesUpdater.installMandatoryModules(monitor.newChild(1));
        }
        
        // Report update errors
        final MultiStatus ms = new MultiStatus(AppProjectCore.PLUGIN_ID, 0, "Modules updating results:", null);
        modulesUpdater.getResults().stream().filter(s -> !s.isOK()).forEach(s -> ms.add(s));
        if (!ms.isOK()) {
            Display.getDefault().asyncExec(() -> statusReporter.report(ms, StatusReporter.SHOW));
        }
        monitor.done();
        
    }

    /**
     * This diagnostic indicates if a project needs a migration or not, if a migration is possible and what kind of migration process is required.
     */
    @objid ("224cbe44-ee23-4697-8cc0-f3569d3eba28")
    private static class MigrationDiagnostic {
        /**
         * The required migration action
         */
        @objid ("2697e202-384e-4bd5-a39b-95930d33c56c")
        private final MigrationAction action;

        /**
         * A I18n message explaining the migration case
         */
        @objid ("f97f16a6-9f92-4f73-8a4b-b45b4995371b")
        private final String diagnostic;

        /**
         * The Modelio version required by the project
         */
        @objid ("9cbbb407-d23f-4d42-94e4-b367dc2f2a66")
        private final Version projectRequiredVersion;

        @objid ("c71f850b-de10-474f-8ffa-62eddb80b2ce")
        public  MigrationDiagnostic(MigrationAction action, Version projectRequiredVersion, String diagnostic) {
            this.action = action;
            this.projectRequiredVersion = projectRequiredVersion;
            this.diagnostic = diagnostic;
            
        }

        @objid ("7c684e73-fe8a-4cf4-977d-fae12b46d538")
        public Version getProjectRequiredVersion() {
            return this.projectRequiredVersion;
        }

        @objid ("6fc256bf-0f1e-446e-866e-125d15cefec3")
        public MigrationAction getAction() {
            return this.action;
        }

        @objid ("996e6832-1b32-4b7c-a26a-e795c3e3d360")
        public String getDiagnostic() {
            return this.diagnostic;
        }

        @objid ("50909c0d-655e-4899-ab75-791131d485ea")
        private enum MigrationAction {
            @objid ("a9c7de95-95a8-49c0-820c-f339e54491bd")
            NO_MIGRATION_NEEDED,
            @objid ("f4577387-9c2b-4511-a9e2-bd45bcebc592")
            NO_POSSIBLE_MIGRATION,
            @objid ("95b630bb-3abd-45bc-b1c1-b7dbe62035e4")
            BACKWARD_MIGRATION,
            @objid ("4be229a7-5113-47b1-9cba-5b7c92316477")
            MIGRATION_FROMV1_REQUIRED,
            @objid ("50f481f8-4b66-4af7-b855-c1568a75d17d")
            MIGRATION_FROMV2_REQUIRED,
            @objid ("8940d4ca-a93c-47ab-b645-16148df621fd")
            AUTO_MIGRATION_POSSIBLE;

        }

    }

    /**
     * Class that records and runs actions to be undone in case opening fails
     * @author cmarin
     * @since 5.3.1
     */
    @objid ("209272f1-eb79-4143-86e6-67254bb79f90")
    private static class OpenState implements Closeable {
        @objid ("04db946a-c8ee-4680-a125-611b85b79474")
        private final Deque<Runnable> actions = new ArrayDeque<>();

        @objid ("bfb3da8b-550b-4730-90fa-15629b1f2b56")
        private final IProjectService projectService;

        @objid ("c59ba91b-3251-445a-8f7e-6dbc8a96b6bb")
        private final IGProject project;

        @objid ("75d26acc-623d-40b7-b562-bf94389ab4bf")
        private static final PluginLogger LOG = AppProjectCore.LOG;

        @objid ("d6e401c4-9db5-4131-bc83-8d8285a65ff4")
        public  OpenState(IProjectService projectService, IGProject project) {
            this.projectService = projectService;
            this.project = project;
            
        }

        @objid ("93b2e1f4-f093-4ce5-8142-627267439601")
        @Override
        public void close() throws IOException {
            LOG.debug("OpenState: Aborting project open...");
            
            if (this.projectService.getOpenedProject() != null) {
                LOG.debug("OpenState: Project was fully open, use IProjectService ...");
                this.projectService.closeProject(this.project);
                return;
            }
            
            while (! this.actions.isEmpty()) {
                Runnable runnable = this.actions.pop();
                LOG.debug("OpenState: - Running %s ...", runnable);
                try {
                    runnable.run();
                } catch (RuntimeException e) {
                    LOG.warning(e);
                }
            }
            
            if (this.project.isOpen()) {
                LOG.debug("OpenState: Project still open, closing it ...");
                this.project.close();
            } else {
                LOG.debug("OpenState: Project already closed ...");
            }
            
            LOG.debug("OpenState: Opening completely aborted.");
            
        }

        @objid ("13e534d8-e048-4d33-972e-6d7664d2e933")
        public void add(Runnable closeAction) {
            this.actions.push(closeAction);
        }

    }

}
