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

package org.modelio.app.project.core.services.openproject;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.concurrent.CompletableFuture;
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
import org.modelio.app.core.IModelioEventService;
import org.modelio.app.core.ModelioEnv;
import org.modelio.app.core.events.ModelioEvent;
import org.modelio.app.preferences.AppStatePreferenceStore;
import org.modelio.app.preferences.GProjectPreferenceStore;
import org.modelio.app.project.core.modelshield.ModelShieldController;
import org.modelio.app.project.core.plugin.AppProjectCore;
import org.modelio.app.project.core.prefs.ProjectPreferencesHelper;
import org.modelio.app.project.core.prefs.ProjectPreferencesKeys;
import org.modelio.app.project.core.services.FragmentsMigrator;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.app.project.core.services.ModulesUpdater;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.fragment.VersionHelper;
import org.modelio.gproject.fragment.migration.MigrationFailedException;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.gproject.GProjectAuthenticationException;
import org.modelio.gproject.gproject.GProjectConfigurer.Failure;
import org.modelio.gproject.gproject.GProjectConfigurer;
import org.modelio.gproject.gproject.GProjectEnvironment;
import org.modelio.gproject.gproject.GProjectFactory;
import org.modelio.gproject.gproject.IGProjectEnv;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.mda.infra.service.IModuleManagementService;
import org.modelio.metamodel.PredefinedTypes;
import org.modelio.metamodel.mmextensions.infrastructure.IInfrastructureModelFactory;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.mmextensions.standard.services.MModelServices;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.ui.progress.ModelioProgressAdapter;
import org.modelio.ui.swt.DefaultShellProvider;
import org.modelio.ui.swt.SwtThreadHelper;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.version.ModelioVersion;

/**
 * Implementation of {@link IProjectService#openProject(ProjectDescriptor, IAuthData, IProgressMonitor)}
 * 
 * @author cmarin
 * @since 3.5
 */
@objid ("3637c01a-8731-452e-88da-1336b1672c4b")
public class OpenProjectService implements IProjectOpener {
    @objid ("8a7d7512-6c36-41d9-8c71-dd8aa0f47a03")
    private static final boolean askFragmentMigrationConfirmation = true;

    @objid ("0f90b23f-459e-447a-be94-58d4dbb8839c")
    private final IEclipseContext context;

    @objid ("dda5b170-192e-4d0c-93c9-afbc6725c9dc")
    private boolean batchMode;

    /**
     * Project being opened.
     */
    @objid ("1df929df-fa25-4e61-8752-97d704e9803e")
    private GProject project;

    @objid ("02eb511b-174a-41a1-a516-98a3f4a23e5a")
    private final GProjectConfigurer projectSynchronizer;

    @objid ("52b1fb1f-9314-4ed4-8db6-476d58746f3b")
    public OpenProjectService(IEclipseContext context, GProjectConfigurer synchronizer) {
        this.context = context;
        this.projectSynchronizer = synchronizer;
    }

    @objid ("3c935a75-c2af-4fc2-ab7f-3ad8e30e3c76")
    @Override
    public void openProject(ProjectDescriptor projectToOpen, IAuthData authData, IProjectServiceAccess svcAccess, boolean runInBatchMode, IProgressMonitor monitor) throws GProjectAuthenticationException, IOException, InterruptedException {
        // Reconfigure this instance
        this.project = null;
        this.batchMode = runInBatchMode;
        IGProjectEnv gprojectEnv = getProjectFactoryConfiguration();
        
        if (projectToOpen == null) {
            throw new IllegalArgumentException("Cannot open 'null' project.");
        }
        
        final String taskName = AppProjectCore.I18N.getMessage("ProjectService.open.task", projectToOpen.getName());
        final SubMonitor mon = SubMonitor.convert(monitor, taskName, 250);
        mon.subTask(taskName);
        
        StatusReporter statusReporter = this.context.get(StatusReporter.class);
        final ProjectMonitor projectMonitor = new ProjectMonitor(
                svcAccess.getProjectService(),
                statusReporter,
                this.context.get(IModelioEventService.class));
        
        final ProjectOpeningMonitor projectOpeningMonitor = new ProjectOpeningMonitor(
                svcAccess.getProjectService(), projectMonitor);
        
        this.project = GProjectFactory
                .from(projectToOpen)
                .withEnvironment(gprojectEnv)
                .withAuth(authData)
                .withEventListener(projectOpeningMonitor)
                .load(new ModelioProgressAdapter(mon.newChild(50)));
        
        svcAccess.setOpenedProject(this.project);
        
        try (ProjectCloser closer = new ProjectCloser(svcAccess)) {
            svcAccess.setOpeningEventSent(false);
        
            // Synchronize the project against server
            synchronizeProjectAgainstServer(mon.newChild(50));
        
            mon.setWorkRemaining(150);
        
            // Check Modelio version
            boolean migrateModules = checkModelioVersion(this.project, projectToOpen, getASwtShell());
        
            // Project preferences
            GProjectPreferenceStore prefsStore = new GProjectPreferenceStore(this.project);
            svcAccess.setProjectPreferenceStore(prefsStore);
        
            // State preferences
            svcAccess.setStatePreferenceStore(new AppStatePreferenceStore(this.project));
        
            // open the project
            this.project.open(new ModelioProgressAdapter(mon.newChild(50)));
            this.project.getSession().getModelChangeSupport().addModelChangeListener(event -> {
                // Force refresh of e4 elements...
                IEventBroker eventBroker = getContext().get(IEventBroker.class);
                eventBroker.post(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC, UIEvents.ALL_ELEMENT_ID);
            });
        
            // Add the services that depends on project to the context
            // model service
            final MModelServices modelServices = new MModelServices(this.project.getSession());
            this.context.set(IMModelServices.class, modelServices);
        
            IModuleManagementService moduleService = this.context.get(IModuleManagementService.class);
        
            // Migrate model if needed and allowed
            migrateFragments(mon, 50, this.project);
            mon.setWorkRemaining(152);
        
            // Fire PROJECT_OPENING
            // note: most of ModelioEvent.PROJECT_OPENING listeners need a
            // CoreSession
            this.context.get(IModelioEventService.class).postSyncEvent(svcAccess.getProjectService(), ModelioEvent.PROJECT_OPENING, this.project);
            svcAccess.setOpeningEventSent(true);
        
            // Update modules
            updateModules(mon, statusReporter, moduleService, migrateModules);
            mon.setWorkRemaining(102);
        
            // Configure project preferences
            installProjectPreferencesDefaults(svcAccess);
            installProjectPreferences(svcAccess.getProjectService(), prefsStore);
        
            // Configure ModelShield for the project
            ModelShieldController.onProjectOpening(this.project);
        
            // Start all modules
            moduleService.startAllModules(this.project, mon.newChild(50));
        
            // Post process migrations
            TodoRunner todoRunner = new TodoRunner(this.project, moduleService);
            todoRunner.execute(new ModelioProgressAdapter(mon.newChild(50)));
            if (!todoRunner.getFailures().isEmpty()) {
                AppProjectCore.LOG.warning(todoRunner.getFailuresReport().toString());
                if (!this.batchMode) {
                    todoRunner.reportFailures(getASwtShell());
                }
            }
        
            // Save local project descriptor with done to-do removed
            this.project.save(new ModelioProgressAdapter(mon.newChild(2)));
        
            // Fire PROJECT_OPENED
            this.context.get(IModelioEventService.class).postAsyncEvent(svcAccess.getProjectService(), ModelioEvent.PROJECT_OPENED, this.project);
        
            // Validate project opening
            closer.abort();
        
            // Change the project event monitor
            this.project.getMonitorSupport().removeMonitor(projectOpeningMonitor);
            this.project.getMonitorSupport().addMonitor(projectMonitor);
            projectOpeningMonitor.processDefered();
        }
    }

    @objid ("5fee25be-557f-44f1-afc8-fca04ccdbf93")
    protected final IEclipseContext getContext() {
        return this.context;
    }

    /**
     * Check Modelio version and ask user for confirmation if Modelio versions don't match.
     * @param gProject the project
     * @param projectDescriptor the project descriptor
     * @param parent a SWT shell
     * @return true if modelio versions were different, else false
     * @throws java.io.IOException to abort project opening
     */
    @objid ("2d55ddaa-dcb3-4928-ac40-c3f6f89c208d")
    private boolean checkModelioVersion(GProject gProject, ProjectDescriptor projectDescriptor, Shell parent) throws IOException {
        Version neededVersion = gProject.getExpectedModelioVersion();
        String pbMsg = null;
        boolean canOpen = true;
        
        if (neededVersion == null) {
            neededVersion = VersionHelper.guessModelioVersion(projectDescriptor, gProject);
        }
        
        if (neededVersion == null) {
            pbMsg = AppProjectCore.I18N.getMessage("OpenProjectService.version.none",
                    projectDescriptor.getName(),
                    null,
                    ModelioVersion.VERSION);
        } else if (neededVersion.isNewerThan(ModelioVersion.VERSION)) {
            pbMsg = AppProjectCore.I18N.getMessage("OpenProjectService.version.future",
                    projectDescriptor.getName(),
                    neededVersion,
                    ModelioVersion.VERSION);
        } else if (neededVersion.isOlderThan(ModelioVersion.VERSION)) {
            if (neededVersion.getMajorVersion() < 2) {
                // need to be migrated from Modelio 1
                pbMsg = AppProjectCore.I18N.getMessage("OpenProjectService.version.v1", projectDescriptor.getName(), neededVersion, ModelioVersion.VERSION);
                canOpen = false;
            } else if (neededVersion.getMajorVersion() < 3) {
                // need to be migrated from Modelio 2
                pbMsg = AppProjectCore.I18N.getMessage("OpenProjectService.version.v2", projectDescriptor.getName(), neededVersion, ModelioVersion.VERSION);
                canOpen = false;
            } else if (neededVersion.getMinorVersion() < ModelioVersion.VERSION.getMinorVersion()) {
                // migration needed from 3.x
                pbMsg = AppProjectCore.I18N.getMessage("OpenProjectService.version.MigrationNeeded", projectDescriptor.getName(), neededVersion, ModelioVersion.VERSION);
            } else {
                // only build changes
                pbMsg = checkNewerModelioVersionBuild(projectDescriptor, parent, neededVersion);
            }
        }
        
        if (pbMsg != null) {
            if (canOpen) {
                onModelioVersionMismatch(projectDescriptor, parent, neededVersion, pbMsg);
                return true;
            } else {
                throw new IOException(pbMsg);
            }
        }
        return false;
    }

    @objid ("cb61fb6e-713f-480a-8fe4-42c7e06a13ec")
    private void installProjectPreferences(IProjectService svc, IPreferenceStore prefsStore) {
        final MTools mTools = MTools.get(this.project.getSession());
        final IStandardModelFactory factory = mTools.getModelFactory(IStandardModelFactory.class);
        final IInfrastructureModelFactory imfactory = mTools.getModelFactory(IInfrastructureModelFactory.class);
        
        // Setup the model factories
        factory.setDefaultValue("DEFAULT_ATTRIBUTE_TYPE", ProjectPreferencesHelper.getAttributeDefaultType(svc));
        factory.setDefaultValue("DEFAULT_ATTRIBUTE_VISIBILITY", ProjectPreferencesHelper.getAttributeDefaultVisibility(svc));
        factory.setDefaultValue("DEFAULT_PARAMETER_TYPE", ProjectPreferencesHelper.getParameterDefaultType(svc));
        factory.setDefaultValue("DEFAULT_RETURN_TYPE", ProjectPreferencesHelper.getReturnDefaultType(svc));
        imfactory.setDefaultValue("DEFAULT_NOTE_MIMETYPE", ProjectPreferencesHelper.getNoteDefaultMimeType(svc));
        
        // Setup a listener that reconfigures the model factories
        // when project preferences change
        prefsStore.addPropertyChangeListener(event -> {
            String name = event.getProperty();
        
            if (name.endsWith(ProjectPreferencesKeys.ATT_DEFAULT_TYPE_PREFKEY)) {
                factory.setDefaultValue("DEFAULT_ATTRIBUTE_TYPE", ProjectPreferencesHelper.getAttributeDefaultType(svc));
            } else if (name.endsWith(ProjectPreferencesKeys.ATT_DEFAULT_VIS_PREFKEY)) {
                factory.setDefaultValue("DEFAULT_ATTRIBUTE_VISIBILITY", ProjectPreferencesHelper.getAttributeDefaultVisibility(svc));
            } else if (name.endsWith(ProjectPreferencesKeys.PARAM_DEFAULT_TYPE_PREFKEY)) {
                factory.setDefaultValue("DEFAULT_PARAMETER_TYPE", ProjectPreferencesHelper.getParameterDefaultType(svc));
            } else if (name.endsWith(ProjectPreferencesKeys.RETURN_DEFAULT_TYPE_PREFKEY)) {
                factory.setDefaultValue("DEFAULT_RETURN_TYPE", ProjectPreferencesHelper.getReturnDefaultType(svc));
            } else if (name.endsWith(ProjectPreferencesKeys.NOTE_DEFAULT_MIMETYPE_PREFKEY)) {
                imfactory.setDefaultValue("DEFAULT_NOTE_MIMETYPE", ProjectPreferencesHelper.getNoteDefaultMimeType(svc));
            }
        });
    }

    @objid ("7b203cde-7d61-4515-88a8-cb2cc182b426")
    private void installProjectPreferencesDefaults(IProjectServiceAccess svcAccess) {
        CoreSession coreSession = (CoreSession) this.project.getSession();
        try (ITransaction t = coreSession.getTransactionSupport().createTransaction("Create default preference DataTypes")) {
            // PredefinedTypes might not be deployed yet, use a shell object if
            // they are missing
            SmClass datatypeMClass = coreSession.getMetamodel().getMClass(DataType.class);
            DataType stringDataType = (DataType) coreSession.getSmFactory().getObjectReference(datatypeMClass, PredefinedTypes.STRING_UID, PredefinedTypes.STRING_NAME);
            DataType integerDataType = (DataType) coreSession.getSmFactory().getObjectReference(datatypeMClass, PredefinedTypes.INTEGER_UID, PredefinedTypes.INTEGER_NAME);
            t.commit();
        
            // Define default values if not already defined
            IPreferenceStore store = svcAccess.getProjectService().getProjectPreferences(ProjectPreferencesKeys.NODE_ID);
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

    @objid ("bc6e9b7c-48b4-4f36-ab76-51e1b856a5f2")
    protected void migrateFragments(SubMonitor mon, int allowedMonWork, GProject gproject) throws IOException, InterruptedException {
        if (this.batchMode) {
            // Don't migrate in batch mode, abort if any fragment need migration
            String pb = gproject
                    .getFragments()
                    .stream()
                    .filter(f -> f.getDownError() instanceof MigrationFailedException)
                    .map(f -> f.getId() + ": " + f.getDownError().getLocalizedMessage())
                    .collect(Collectors.joining("\n"));
        
            if (!pb.isEmpty()) {
                throw new IOException(pb);
            }
        } else {
            new FragmentsMigrator(this.context, gproject, OpenProjectService.askFragmentMigrationConfirmation).migrateFragments(mon, allowedMonWork);
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
                String title = AppProjectCore.I18N.getMessage("ProjectService.ProjectSynchroFailed.title", failedProj.getName());
                MessageDialog.openWarning(new DefaultShellProvider().getShell(), title, message);
            });
        }
    }

    @objid ("41d09262-e599-4095-b93d-6e639bd3bc9a")
    private void reportSynchronizationFailures() {
        final StringBuilder sb = new StringBuilder();
        
        sb.append(AppProjectCore.I18N.getMessage("ProjectService.ProjectSynchroProblems.message", this.project.getName()));
        
        for (Failure f : this.projectSynchronizer.getFailures()) {
            sb.append(" - ").append(f.getSourceIdentifier()).append(": ");
        
            Throwable cause = f.getCause();
            if (cause instanceof IOException) {
                sb.append(FileUtils.getLocalizedMessage((IOException) cause));
            } else if (cause instanceof AccessDeniedException) {
                sb.append(cause.getLocalizedMessage());
            } else if (cause instanceof RuntimeException) {
                sb.append(cause.toString());
            } else {
                sb.append(cause.getLocalizedMessage());
            }
        }
        
        final String title = AppProjectCore.I18N.getMessage("ProjectService.ProjectSynchroProblems.title", this.project.getName());
        if (this.batchMode) {
            AppProjectCore.LOG.warning(title);
            AppProjectCore.LOG.warning(sb.toString());
            System.err.println(title);
            System.err.println(sb.toString());
        } else {
            // Get a shell
            Shell shell = getASwtShell();
            Display d = shell != null ? shell.getDisplay() : Display.getDefault();
            d.syncExec(() -> MessageDialog.openWarning(shell, title, sb.toString()));
        }
    }

    @objid ("6b778bcd-111d-4cf9-a603-ff8dc45cff97")
    protected final Shell getASwtShell() {
        Shell shell = (Shell) this.context.getActive(IServiceConstants.ACTIVE_SHELL);
        
        if (shell == null) {
            IShellProvider sp = this.context.getActive(IShellProvider.class);
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

    @objid ("729e1eec-bca8-4c15-bd60-64c8adaa1efa")
    private IGProjectEnv getProjectFactoryConfiguration() {
        ModelioEnv env = this.context.get(ModelioEnv.class);
        return new GProjectEnvironment()
                .addMetamodelExtensions(env.getMetamodelExtensions())
                .setModulesCache(getModuleCache())
                .setRamcCache(env.getRamcCachePath());
    }

    /**
     * @return the module catalog
     */
    @objid ("8c17a887-1f2b-4550-afa9-4477e0014389")
    private IModuleRTCache getModuleCache() {
        // look for the module catalog
        return this.context.get(IModuleRTCache.class);
    }

    /**
     * Called by {@link #checkModelioVersion(GProject, ProjectDescriptor, Shell)} when Modelio is newer by build number.
     * <p>
     * @see ModelioVersion#VERSION
     * @param projectDescriptor the project descriptor
     * @param parent a SWT shell to use for GUI
     * @param neededVersion the project Modelio version
     * @return a open confirmation message or null
     * @throws java.io.IOException to refuse project opening
     */
    @objid ("7cc75f37-19a3-43e6-8094-1345e1e3e9f0")
    protected String checkNewerModelioVersionBuild(ProjectDescriptor projectDescriptor, Shell parent, Version neededVersion) throws IOException {
        // accept by default
        return null;
    }

    @objid ("9fe036ba-05ed-40de-8a20-73ec90f4a76c")
    protected final boolean isBatchMode() {
        return this.batchMode;
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
     * @param canOpen whether Modelio is able to open the project
     * @param projectDescriptor the project descriptor
     * @param parent a SWT shell to use for GUI
     * @param neededVersion the project Modelio version
     * @param pbMsg a computed message
     * @throws java.io.IOException to refuse opening the project. The caller will display a message dialog from the exception content.
     */
    @objid ("cd898073-3ce3-4e62-87a5-336f9bdb5896")
    protected void onModelioVersionMismatch(ProjectDescriptor projectDescriptor, Shell parent, Version neededVersion, String pbMsg) throws IOException {
        final String message = pbMsg;
        final Version fProjectVersion = neededVersion;
        
        boolean acceptMigration = SwtThreadHelper.syncSupply(
                parent.getDisplay(),
                () -> MessageDialog.openQuestion(
                        parent,
                        AppProjectCore.I18N.getMessage("OpenProjectService.version.dialog.possible.title",
                                projectDescriptor.getName(), fProjectVersion, ModelioVersion.VERSION),
                        AppProjectCore.I18N.getMessage("OpenProjectService.version.dialog.possible.message",
                                projectDescriptor.getName(), fProjectVersion, ModelioVersion.VERSION, message)));
        
        if (!acceptMigration) {
            throw new IOException(pbMsg);
        }
    }

    @objid ("8685002f-396a-473f-b0c0-faf944473433")
    private void updateModules(final SubMonitor mon, StatusReporter statusReporter, IModuleManagementService moduleService, boolean migrateModules) {
        moduleService.initRTModules(this.project);
        IModuleStore modulesCatalog = this.context.get(IModuleStore.class);
        ModulesUpdater modulesUpdater = new ModulesUpdater(moduleService, modulesCatalog, this.project, false);
        
        if (migrateModules) {
            modulesUpdater.run(mon.newChild(50));
        } else {
            // Ensure mandatory modules are installed
            modulesUpdater.installMandatoryModules(mon.newChild(50));
        }
        
        // Report update errors
        MultiStatus ms = new MultiStatus(AppProjectCore.PLUGIN_ID, 0, "Modules updating results:", null);
        modulesUpdater.getResults()
                .stream()
                .filter(s -> !s.isOK())
                .forEach(s -> ms.add(s));
        if (!ms.isOK()) {
            Display.getDefault().asyncExec(() -> statusReporter.report(ms, StatusReporter.SHOW));
        }
    }

    @objid ("da992962-eac5-4a2d-965f-b6b235a68760")
    private void synchronizeProjectAgainstServer(final SubMonitor mon) throws GProjectAuthenticationException {
        try {
            ProjectPreOpenSynchronizer authCB = new ProjectPreOpenSynchronizer();
        
            this.projectSynchronizer.setAccessDeniedHandler(authCB);
            this.projectSynchronizer.synchronize(
                    this.project,
                    this.context.get(IModuleStore.class),
                    new ModelioProgressAdapter(mon));
            if (!this.projectSynchronizer.getFailures().isEmpty()) {
                reportSynchronizationFailures();
            }
        } catch (IOException e) {
            reportSynchronizationFail(e, this.project);
        }
    }

    /**
     * To be used in try-with-resource to automatically close the project unless abort is called.
     * 
     * @author cmarin
     */
    @objid ("db5f4a1c-12ac-4142-a3c3-ea0d159d9669")
    private class ProjectCloser implements AutoCloseable {
        @objid ("8f4b6e4e-040b-41c6-bc9c-03e32fa18ad9")
        private boolean abort = false;

        @objid ("b3235dae-085c-4655-86f5-dd6e46bcf721")
        private IProjectServiceAccess svcAccess;

        @objid ("0d7b407c-27ba-46bf-9bf2-06fff2bc4286")
        public ProjectCloser(IProjectServiceAccess svcAccess) {
            this.svcAccess = svcAccess;
        }

        @objid ("be54458f-6225-4ced-aba2-963ce44de6f0")
        @Override
        public void close() {
            if (!this.abort) {
                this.svcAccess.getProjectService().closeProject(OpenProjectService.this.project);
            }
        }

        @objid ("d8c4e9a6-9abb-44b7-a308-7c0d155518b6")
        public void abort() {
            this.abort = true;
        }

    }

}
