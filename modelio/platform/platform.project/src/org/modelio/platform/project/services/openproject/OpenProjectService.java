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

import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
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
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.data.project.ProjectDescriptorReader;
import org.modelio.gproject.fragment.VersionHelper;
import org.modelio.gproject.fragment.migration.MigrationFailedException;
import org.modelio.gproject.gproject.GProblem;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.gproject.GProjectAuthenticationException;
import org.modelio.gproject.gproject.GProjectConfigurer;
import org.modelio.gproject.gproject.GProjectConfigurer.IAccessDeniedHandler;
import org.modelio.gproject.gproject.GProjectEnvironment;
import org.modelio.gproject.gproject.GProjectFactory;
import org.modelio.gproject.gproject.IGProjectEnv;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.gproject.module.IModuleStore;
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
import org.modelio.platform.ui.progress.ModelioProgressAdapter;
import org.modelio.platform.ui.swt.DefaultShellProvider;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.files.CloseOnFail;
import org.modelio.vbasic.files.FileUtils;
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
 * @since 3.5
 */
@objid ("3637c01a-8731-452e-88da-1336b1672c4b")
public class OpenProjectService implements IProjectOpener {
    @objid ("8a7d7512-6c36-41d9-8c71-dd8aa0f47a03")
    private static final boolean askFragmentMigrationConfirmation = true;

    @objid ("dda5b170-192e-4d0c-93c9-afbc6725c9dc")
    private boolean batchMode;

    /**
     * Project being opened.
     */
    @objid ("1df929df-fa25-4e61-8752-97d704e9803e")
    private GProject project;

    @objid ("508db407-6190-42b3-a8de-3789064914d3")
    protected IProjectServiceAccess projectServiceAccess;

    @objid ("02eb511b-174a-41a1-a516-98a3f4a23e5a")
    private final GProjectConfigurer projectSynchronizer;

    /**
     * @param synchronizer the service to synchronize the project from remote project configuration
     */
    @objid ("52b1fb1f-9314-4ed4-8db6-476d58746f3b")
    public  OpenProjectService(final GProjectConfigurer synchronizer) {
        this.projectSynchronizer = synchronizer;
    }

    @objid ("9759e357-12da-40e3-ba34-a70eff9c20e8")
    @Override
    public void configure(IProjectServiceAccess projectService) {
        this.projectServiceAccess = projectService;
    }

    @objid ("3c935a75-c2af-4fc2-ab7f-3ad8e30e3c76")
    @Override
    public void openProject(final ProjectDescriptor projectToOpen, final IAuthData authData, final boolean runInBatchMode, final IProgressMonitor monitor) throws IOException, GProjectAuthenticationException, InterruptedException {
        // Reconfigure this instance
        this.project = null;
        this.batchMode = runInBatchMode;
        final IGProjectEnv gprojectEnv = getProjectFactoryConfiguration();
        
        if (projectToOpen == null) {
            throw new IllegalArgumentException("Cannot open 'null' project.");
        }
        
        final IModuleManagementService moduleService = getContext().get(IModuleManagementService.class);
        
        AuthenticationPrompter authPrompter = null;
        ProjectAuthsChecker authsChecker = null;
        Shell parentShell = null;
        
        if (! this.batchMode) {
            parentShell = getASwtShell();
            authPrompter = new AuthenticationPrompter(parentShell);
            authsChecker = new ProjectAuthsChecker(authPrompter, moduleService, authData);
            authsChecker.checkMissingAuths(projectToOpen);
        }
        
        final String taskName = AppProjectCore.I18N.getMessage("ProjectService.open.task", projectToOpen.getName());
        final SubMonitor mon = SubMonitor.convert(monitor, taskName, 250);
        mon.subTask(taskName);
        
        final StatusReporter statusReporter = getContext().get(StatusReporter.class);
        final ProjectMonitor projectMonitor = new ProjectMonitor(this.projectServiceAccess, statusReporter);
        
        final ProjectOpeningMonitor projectOpeningMonitor = new ProjectOpeningMonitor(this.projectServiceAccess.getProjectService(), projectMonitor);
        
        this.project = GProjectFactory
                .from(projectToOpen)
                .withEnvironment(gprojectEnv)
                .withAuth(authData)
                .withEventListener(projectOpeningMonitor)
                .load(new ModelioProgressAdapter(mon.newChild(50)));
        
        
        try (CloseOnFail shield = new CloseOnFail(
                () -> this.projectServiceAccess.getProjectService().closeProject(this.project));) {
            this.projectServiceAccess.setOpeningEventSent(false);
        
            // Synchronize the project against server
            synchronizeProjectAgainstServer(mon.newChild(50), authPrompter);
        
            mon.setWorkRemaining(150);
        
            // Check Modelio version
            final boolean migrateModules = checkModelioVersion(this.project, projectToOpen, getASwtShell());
        
            // Project preferences
            final GProjectPreferenceStore prefsStore = new GProjectPreferenceStore(this.project);
            this.projectServiceAccess.setProjectPreferenceStore(prefsStore);
        
            // State preferences
            this.projectServiceAccess.openAppStatePreferenceStore(this.project);
        
            // open the project
            this.project.open(new ModelioProgressAdapter(mon.newChild(50)));
            this.project.getSession().getModelChangeSupport().addModelChangeListener(event -> {
                // Force refresh of e4 elements...
                final IEventBroker eventBroker = getContext().get(IEventBroker.class);
                eventBroker.post(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC, UIEvents.ALL_ELEMENT_ID);
            });
        
            // Add the services that depends on project to the context
            // model service
            final MModelServices modelServices = new MModelServices(this.project.getSession());
            getContext().set(IMModelServices.class, modelServices);
        
            // Fire PROJECT_OPENING
            //
            // note: most of ModelioEvent.PROJECT_OPENING listeners need a CoreSession
            // note: the Script engine expect IProjectService.getProject() return null until PROJECT_OPENING is sent.
            this.projectServiceAccess.postSyncEvent(ModelioEvent.PROJECT_OPENING, this.project);
            this.projectServiceAccess.setOpeningEventSent(true);
        
            // Update modules
            updateModules(mon, statusReporter, moduleService, migrateModules);
            mon.setWorkRemaining(153);
        
            // Configure project preferences
            installProjectPreferencesDefaults(this.projectServiceAccess);
            installProjectPreferences(prefsStore, this.project.getSession());
        
            // Now that PROJECT_OPENING events have been sent and processed, we can "declare" the project is opened
            // and update ProjectService with the newly opened project.
            this.projectServiceAccess.setOpenedProject(this.project);
        
            // Migrate model if needed and allowed
            // Note : Diagram migration need projectServiceAccess.setOpenedProject() to be called before
            migrateFragments(mon, 50, this.project);
            mon.setWorkRemaining(103);
        
            // Configure ModelShield for the project
            ModelShieldController.onProjectOpening(this.project);
        
            // Start all modules
            moduleService.startAllModules(this.project, mon.newChild(50));
        
            // Post process migrations
            final TodoRunner todoRunner = new TodoRunner(this.project, moduleService, authPrompter);
            todoRunner.execute(new ModelioProgressAdapter(mon.newChild(50)));
            if (!todoRunner.getFailures().isEmpty()) {
                AppProjectCore.LOG.warning(todoRunner.getFailuresReport());
                if (!this.batchMode) {
                    todoRunner.reportFailures(parentShell);
                }
            }
        
            if (authsChecker != null)
                authsChecker.checkAuthErrors(mon.newChild(1), this.project);
        
            // Save local project descriptor with done to-do removed
            this.project.save(new ModelioProgressAdapter(mon.newChild(2)));
        
        
            // Fire PROJECT_OPENED
            if (this.batchMode) {
                this.projectServiceAccess.postSyncEvent(ModelioEvent.PROJECT_OPENED, this.project);
            } else {
                this.projectServiceAccess.postAsyncEvent(ModelioEvent.PROJECT_OPENED, this.project);
            }
        
            // Validate project opening
            shield.success();
        
            // Change the project event monitor
            this.project.getMonitorSupport().removeMonitor(projectOpeningMonitor);
            this.project.getMonitorSupport().addMonitor(projectMonitor);
            projectOpeningMonitor.processDefered();
        }
        
    }

    @objid ("1c6b4f5b-40ef-4338-bbcf-c4df49d1476b")
    @Override
    public void openProject(final URI projectURI, final IAuthData authData, final boolean runInBatchMode, final IProgressMonitor monitor) throws GProjectAuthenticationException, IOException, InterruptedException {
        if (projectURI == null) {
            throw new IllegalArgumentException("Cannot open 'null' project.");
        }
        
        final ProjectDescriptor projectToOpen = new ProjectDescriptorReader().read(Paths.get(projectURI), null);
        
        openProject(projectToOpen, authData, runInBatchMode, monitor);
        
    }

    /**
     * Called by {@link #checkModelioVersion(GProject, ProjectDescriptor, Shell)} when Modelio is newer by build number.
     * <p>
     * @see ModelioVersion#VERSION
     * @param projectDescriptor the project descriptor
     * @param parent a SWT shell to use for GUI
     * @param neededVersion the project Modelio version
     * @return a open confirmation message or null
     * @throws IOException to refuse project opening
     * @deprecated not called since 5.0 anymore : build number difference are now always accepted.
     */
    @objid ("7cc75f37-19a3-43e6-8094-1345e1e3e9f0")
    @Deprecated
    protected String checkNewerModelioVersionBuild(final ProjectDescriptor projectDescriptor, final Shell parent, final Version neededVersion) throws IOException {
        // accept by default
        return null;
    }

    @objid ("6b778bcd-111d-4cf9-a603-ff8dc45cff97")
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

    @objid ("5fee25be-557f-44f1-afc8-fca04ccdbf93")
    protected final IEclipseContext getContext() {
        return this.projectServiceAccess.getEclipseContext();
    }

    @objid ("9fe036ba-05ed-40de-8a20-73ec90f4a76c")
    protected final boolean isBatchMode() {
        return this.batchMode;
    }

    @objid ("bc6e9b7c-48b4-4f36-ab76-51e1b856a5f2")
    protected void migrateFragments(final SubMonitor mon, final int allowedMonWork, final GProject gproject) throws IOException, InterruptedException {
        if (this.batchMode) {
            // Don't migrate in batch mode, abort if any fragment need migration
            final String pb = gproject
                    .getFragments()
                    .stream()
                    .filter(f -> f.getDownError() instanceof MigrationFailedException)
                    .map(f -> f.getId() + ": " + f.getDownError().getLocalizedMessage())
                    .collect(Collectors.joining("\n"));
        
            if (!pb.isEmpty()) {
                throw new IOException(pb);
            }
        } else {
            new FragmentsMigrator(getContext(), gproject, OpenProjectService.askFragmentMigrationConfirmation).migrateFragments(mon, allowedMonWork);
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
     * @param pbMsg a computed message
     * @param projectDescriptor the project descriptor
     * @param parent a SWT shell to use for GUI
     * @param neededVersion the project Modelio version
     * @throws IOException to refuse opening the project. The caller will display a message dialog from the exception content.
     */
    @objid ("cd898073-3ce3-4e62-87a5-336f9bdb5896")
    protected void onModelioVersionMismatch(final ProjectDescriptor projectDescriptor, final Shell parent, final Version neededVersion, final String message) throws IOException {
        final String fProjectVersion = neededVersion.toString("V.R");
        final String strModelioVersion = ModelioVersion.VERSION.toString("V.R");
        
        boolean acceptMigration;
        try {
            acceptMigration = CompletableFuture.supplyAsync(
                    () -> MessageDialog.openQuestion(
                            parent,
                            AppProjectCore.I18N.getMessage("OpenProjectService.version.dialog.possible.title",
                                    projectDescriptor.getName(), fProjectVersion, strModelioVersion ),
                            AppProjectCore.I18N.getMessage("OpenProjectService.version.dialog.possible.message",
                                    projectDescriptor.getName(), fProjectVersion, strModelioVersion, message)),
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
     * Check Modelio version and ask user for confirmation if Modelio versions don't match.
     * @param gProject the project
     * @param projectDescriptor the project descriptor
     * @param parent a SWT shell
     * @return true if modelio versions were different, else false
     * @throws IOException to abort project opening
     */
    @objid ("2d55ddaa-dcb3-4928-ac40-c3f6f89c208d")
    private boolean checkModelioVersion(final GProject gProject, final ProjectDescriptor projectDescriptor, final Shell parent) throws IOException {
        Version projectVersion = gProject.getExpectedModelioVersion().withoutBuild();
        String pbMsg = null;
        boolean canOpen = true;
        
        if (projectVersion == null) {
            projectVersion = VersionHelper.guessModelioVersion(projectDescriptor, gProject).withoutBuild();
        }
        
        String projectVersionStr = projectVersion==null ? "<none>" : projectVersion.toString("V.R");
        String modelioVersionStr = ModelioVersion.MAJOR_MINOR.toString("V.R");
        
        if (projectVersion == null) {
            pbMsg = AppProjectCore.I18N.getMessage("OpenProjectService.version.none",
                    projectDescriptor.getName(),
                    null,
                    modelioVersionStr);
        } else if (projectVersion.isNewerThan(ModelioVersion.MAJOR_MINOR)) {
            pbMsg = AppProjectCore.I18N.getMessage("OpenProjectService.version.future",
                    projectDescriptor.getName(),
                    projectVersionStr,
                    modelioVersionStr);
        } else if (projectVersion.isOlderThan(ModelioVersion.MAJOR_MINOR)) {
            if (projectVersion.getMajorVersion() < 2) {
                // need to be migrated from Modelio 1
                pbMsg = AppProjectCore.I18N.getMessage("OpenProjectService.version.v1", projectDescriptor.getName(), projectVersionStr, modelioVersionStr);
                canOpen = false;
            } else if (projectVersion.getMajorVersion() < 3) {
                // need to be migrated from Modelio 2
                pbMsg = AppProjectCore.I18N.getMessage("OpenProjectService.version.v2", projectDescriptor.getName(), projectVersionStr, modelioVersionStr);
                canOpen = false;
            } else {
                // Migration needed from 3.x
                // Automatic migration possible
                pbMsg = AppProjectCore.I18N.getMessage("OpenProjectService.version.MigrationNeeded", projectDescriptor.getName(), projectVersionStr, modelioVersionStr);
            }
        }
        
        if (pbMsg != null) {
            if (canOpen) {
                onModelioVersionMismatch(projectDescriptor, parent, projectVersion, pbMsg);
                return true;
            } else {
                throw new IOException(pbMsg);
            }
        }
        return false;
    }

    /**
     * @return the module catalog
     */
    @objid ("8c17a887-1f2b-4550-afa9-4477e0014389")
    private IModuleRTCache getModuleCache() {
        // look for the module catalog
        return getContext().get(IModuleRTCache.class);
    }

    @objid ("729e1eec-bca8-4c15-bd60-64c8adaa1efa")
    private IGProjectEnv getProjectFactoryConfiguration() {
        final ModelioEnv env = getContext().get(ModelioEnv.class);
        return new GProjectEnvironment()
                .addMetamodelExtensions(env.getActiveMetamodelExtensions())
                .setModulesCache(getModuleCache())
                .setRamcCache(env.getRamcCachePath());
        
    }

    @objid ("cb61fb6e-713f-480a-8fe4-42c7e06a13ec")
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

    @objid ("7b203cde-7d61-4515-88a8-cb2cc182b426")
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

    @objid ("9cb90edd-3cb3-4b86-a51b-f0a3992de641")
    private void reportSynchronizationFail(final IOException e, final GProject failedProj) {
        final String err = FileUtils.getLocalizedMessage(e);
        final String message = AppProjectCore.I18N.getMessage("ProjectService.ProjectSynchroFailed.message", this.project.getName(), err);
        
        AppProjectCore.LOG.warning(message);
        AppProjectCore.LOG.debug(e);
        
        if (this.batchMode) {
            System.err.println(message);
        } else {
            Display.getDefault().asyncExec(() -> {
                final String title = AppProjectCore.I18N.getMessage("ProjectService.ProjectSynchroFailed.title", failedProj.getName());
                MessageDialog.openWarning(new DefaultShellProvider().getShell(), title, message);
            });
        }
        
    }

    @objid ("41d09262-e599-4095-b93d-6e639bd3bc9a")
    private void reportSynchronizationFailures() {
        final String title = AppProjectCore.I18N.getMessage("ProjectService.ProjectSynchroProblems.title", this.project.getName());
        final StringBuilder sb = new StringBuilder();
        
        sb.append(AppProjectCore.I18N.getMessage("ProjectService.ProjectSynchroProblems.message", this.project.getName()));
        
        for (final GProblem f : this.projectSynchronizer.getFailures()) {
            sb.append(" - ").append(f.getSubject()).append(": ");
            sb.append(f.getProblem());
            sb.append("\n");
        }
        
        AppProjectCore.LOG.warning(title);
        AppProjectCore.LOG.warning(sb.toString());
        
        if (this.batchMode) {
            System.err.println(title);
            System.err.println(sb.toString());
        } else {
            // Get a shell
            final Shell shell = getASwtShell();
            final Display d = shell != null ? shell.getDisplay() : Display.getDefault();
            d.syncExec(() -> GProblemReportDialog.open(shell, OpenProjectService.this.project.getName(), OpenProjectService.this.projectSynchronizer.getFailures()));
        }
        
    }

    @objid ("da992962-eac5-4a2d-965f-b6b235a68760")
    private void synchronizeProjectAgainstServer(final SubMonitor mon, IAuthenticationPrompter authPrompter) throws GProjectAuthenticationException {
        try {
            IAccessDeniedHandler authCB = authPrompter==null ?
                    (name, uri, data, e) -> null :
                    (name, uri, data, e) -> authPrompter.promptAuthentication(data, name, uri.toString(), FileUtils.getLocalizedMessage(e));
        
            this.projectSynchronizer.setAccessDeniedHandler(authCB);
            this.projectSynchronizer.synchronize(
                    this.project,
                    getContext().get(IModuleStore.class),
                    new ModelioProgressAdapter(mon));
            if (!this.projectSynchronizer.getFailures().isEmpty()) {
                reportSynchronizationFailures();
            }
        } catch (GProjectAuthenticationException e) {
            if (authPrompter==null)
                throw e;
        
            IAuthData newAuth = authPrompter.promptAuthentication(
                    e.getAuthData(),
                    this.project.getName(),
                    this.project.getRemoteLocation(),
                    e.getLocalizedMessage());
            if (newAuth != null) {
                // try again by recursion
                this.project.getAuthConfiguration().setAuthData(newAuth);
                synchronizeProjectAgainstServer(mon, authPrompter);
            } else {
                throw e;
            }
        
        } catch (final IOException e) {
            reportSynchronizationFail(e, this.project);
        }
        
    }

    @objid ("8685002f-396a-473f-b0c0-faf944473433")
    private void updateModules(final SubMonitor mon, final StatusReporter statusReporter, final IModuleManagementService moduleService, final boolean migrateModules) {
        moduleService.initRTModules(this.project);
        final IModuleStore modulesCatalog = getContext().get(IModuleStore.class);
        final ModulesUpdater modulesUpdater = new ModulesUpdater(moduleService, modulesCatalog, this.project, false);
        
        if (migrateModules) {
            modulesUpdater.run(mon.newChild(50));
        } else {
            // Ensure mandatory modules are installed
            modulesUpdater.installMandatoryModules(mon.newChild(50));
        }
        
        // Report update errors
        final MultiStatus ms = new MultiStatus(AppProjectCore.PLUGIN_ID, 0, "Modules updating results:", null);
        modulesUpdater.getResults()
                .stream()
                .filter(s -> !s.isOK())
                .forEach(s -> ms.add(s));
        if (!ms.isOK()) {
            Display.getDefault().asyncExec(() -> statusReporter.report(ms, StatusReporter.SHOW));
        }
        
    }

}
