/* 
 * Copyright 2013-2019 Modeliosoft
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

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.project.core.plugin.AppProjectCore;
import org.modelio.core.rcp.extensionpoint.ExtensionPointContributionManager;
import org.modelio.core.ui.dialogs.auth.AuthDataDialog;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.fragment.FragmentAuthenticationException;
import org.modelio.gproject.fragment.FragmentMigrationNeededException;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.fragment.VersionHelper;
import org.modelio.gproject.fragment.migration.BasicMigrationReporter;
import org.modelio.gproject.fragment.migration.IFragmentMigrator.IMigrationProcess;
import org.modelio.gproject.fragment.migration.IFragmentMigrator;
import org.modelio.gproject.fragment.migration.MigrationFailedException;
import org.modelio.gproject.gproject.GProject;
import org.modelio.ui.progress.ModelioProgressAdapter;
import org.modelio.ui.swt.DefaultShellProvider;
import org.modelio.ui.swt.SwtThreadHelper;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.auth.UserPasswordAuthData;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;

/**
 * Service to propose the user to migrate one or more fragments.
 * 
 * @author cmarin
 */
@objid ("16f8c376-8e39-4ad2-b7b6-e08070c1af49")
public class FragmentsMigrator {
    @objid ("4198d829-4760-4d94-8ab8-7c3a56d85f8c")
    protected IStatus lastConfirmMigrationResult = null;

    @objid ("b5d708e3-6cb5-47cf-945e-b362b89c0993")
    private static final String EXTENSIONPOINT_ID = "org.modelio.app.project.core.services.IFragmentMigrationContributor";

    @objid ("33af27b4-62a7-47dd-8637-70cff7bf8f52")
    protected final MetamodelVersionDescriptor mmDesc;

    @objid ("6f0a469f-6b49-491d-bd4d-6032ade960d4")
    protected final GProject project;

    @objid ("178633dc-89f5-4149-bacd-2a3e8317020f")
    protected final Display display = Display.getDefault();

    @objid ("a52cf5db-6e06-4ede-8791-a68c1327062a")
    protected final IEclipseContext eclipseContext;

    /**
     * C'tor
     * 
     * @param project the project to work on.
     * @param withConfirmation whether to ask user for confirmation.
     */
    @objid ("042f317a-bf12-42ab-bbf5-467301437964")
    public FragmentsMigrator(final IEclipseContext eclipseContext, final GProject project, final boolean withConfirmation) {
        this.eclipseContext = Objects.requireNonNull(eclipseContext);
        this.project = Objects.requireNonNull(project);
        this.lastConfirmMigrationResult = toStatus(withConfirmation ? IDialogConstants.YES_ID : IDialogConstants.YES_TO_ALL_ID, null);
        this.mmDesc = VersionHelper.getDescriptors(this.project.getSession().getMetamodel());
    }

    /**
     * Propose migration of a fragment.
     * 
     * @param f the fragment to migrate
     * @param parentShell a parent SWT shell
     * @param parentMon the progress monitor to use
     * @param allowedMonWork the maximum progress ticks this method may use
     * @return the migration report or null if nothing was done
     * @throws java.lang.InterruptedException on user cancellation
     */
    @objid ("50464582-98ef-465e-8abc-f9d4443e5562")
    public IStatus migrateFragment(final IProjectFragment f, final Shell parentShell, final SubMonitor parentMon, final int allowedMonWork) throws InterruptedException {
        if (f.getDownError() instanceof FragmentMigrationNeededException) {
        
            final IStatus acceptMigration = getMigrationPermission(f, parentShell);
        
            if (!acceptMigration.isOK()) {
                if (acceptMigration.getSeverity() == IStatus.CANCEL) {
                    throw new InterruptedException();
                }
        
                return acceptMigration; // new Status(IStatus.WARNING, AppProjectCore.PLUGIN_ID, msg, null);
            }
        
            boolean tryAgain = false;
            do {
                final Path logFile = this.project.getProjectFileStructure().getProjectRuntimePath().resolve("fragments").resolve(f.getId()).resolve("migration.log");
        
                try (BasicMigrationReporter reporter = new BasicMigrationReporter(logFile);) {
                    try {
                        final SubProgress mon = ModelioProgressAdapter.convert(parentMon.newChild(allowedMonWork), 2);
        
                        final IFragmentMigrator m = f.getMigrator(this.mmDesc);
        
                        if (m != null) {
                            runFragmentMigration(mon, reporter, f, m);
                        }
        
                        f.mount(mon.newChild(1));
        
                        final Status reportStatus = new Status(IStatus.INFO, AppProjectCore.PLUGIN_ID, reporter.getResult(), null);
                        final String msg = AppProjectCore.I18N.getMessage("FragmentsMigrator.migrateFragment.success", f.getId());
                        return new MultiStatus(AppProjectCore.PLUGIN_ID, IStatus.INFO, new Status[] { reportStatus }, msg, null);
        
                    } catch (final MigrationFailedException migrationFailExc) {
                        reporter.getResultReporter().println(migrationFailExc.getLocalizedMessage());
                        reporter.getLogger().println(migrationFailExc.getLocalizedMessage());
                        migrationFailExc.printStackTrace(reporter.getLogger());
                    } catch (final RuntimeException migrationFailExc) {
                        migrationFailExc.printStackTrace(reporter.getLogger());
                        reporter.getResultReporter().println(migrationFailExc.toString());
                    } catch (final FragmentAuthenticationException authExc) {
                        // open the auth data dialog then try again
                        tryAgain = handleFragmentAuthenticationException(f, authExc, parentShell);
                        if (!tryAgain) {
                            final Throwable oldError = f.getDownError();
                            authExc.addSuppressed(oldError);
                            f.setDown(authExc);
        
                            reporter.getLogger().println(authExc.getLocalizedMessage());
                            reporter.getResultReporter().println(authExc.getLocalizedMessage());
                        }
                    }
        
                    final Status reportStatus = new Status(
                            IStatus.ERROR,
                            AppProjectCore.PLUGIN_ID,
                            reporter.getResult(),
                            null);
        
                    if (parentMon.isCanceled()) {
                        final String msg = AppProjectCore.I18N.getMessage("FragmentsMigrator.migrateFragment.cancel", f.getId());
                        return new Status(IStatus.WARNING, AppProjectCore.PLUGIN_ID, msg, null);
                    } else if (!tryAgain) {
                        final String msg = AppProjectCore.I18N.getMessage("FragmentsMigrator.migrateFragment.failed", f.getId());
                        return new MultiStatus(AppProjectCore.PLUGIN_ID, IStatus.ERROR, new Status[] { reportStatus }, msg, null);
                    }
        
                } catch (final IOException e) {
                    // "reporter" instantiation failed
                    return new Status(IStatus.ERROR, AppProjectCore.PLUGIN_ID, FileUtils.getLocalizedMessage(e), e);
                }
            } while (tryAgain);
        
        }
        return null;
    }

    /**
     * Propose migration of all model fragments that may be migrated.
     * 
     * @param parentMon the progress monitor to use
     * @param allowedMonWork the maximum progress ticks this method may use
     * @throws java.lang.InterruptedException on cancel
     */
    @objid ("2afc1535-052c-439e-84e7-d17d8de792eb")
    public void migrateFragments(final SubMonitor parentMon, final int allowedMonWork) throws InterruptedException {
        final List<IProjectFragment> fragsToMigrate = this.project.getOwnFragments()
                .stream()
                .filter(f -> (f.getDownError() instanceof FragmentMigrationNeededException))
                .collect(Collectors.toList());
        
        final Shell parentShell = SwtThreadHelper.syncSupply(this.display, DefaultShellProvider::getBestParentShell);
        
        if (!fragsToMigrate.isEmpty()) {
            final String taskName = AppProjectCore.I18N.getMessage("FragmentsMigrator.monitor.begin", fragsToMigrate.size());
            parentMon.setTaskName(taskName);
            parentMon.subTask(taskName);
            int nbFails = 0;
        
            final ArrayList<IStatus> allresult = new ArrayList<>();
        
            for (final IProjectFragment f : fragsToMigrate) {
                final IStatus result = migrateFragment(f, parentShell, parentMon, 1);
        
                if (result != null) {
                    allresult.add(result);
                    if (result.getSeverity() > IStatus.INFO) {
                        nbFails++;
                    }
                }
        
                if (parentMon.isCanceled()) {
                    // Abort remaining
                    throw new InterruptedException();
                }
            }
        
            if (allresult.isEmpty()) {
                // nothing to display
                return;
            } else if (allresult.size() > 1) {
                // many fragments
                final String resMsg = nbFails == 0 ? AppProjectCore.I18N.getMessage("FragmentsMigrator.displayMigrationResult.success.message", fragsToMigrate.size())
                        : AppProjectCore.I18N.getMessage("FragmentsMigrator.displayMigrationResult.failed.message", fragsToMigrate.size(), nbFails);
                final MultiStatus globresult = new MultiStatus(AppProjectCore.PLUGIN_ID, 0, allresult.toArray(new Status[allresult.size()]), resMsg, null);
                displayMigrationResult(globresult, parentShell);
            } else {
                // Only one fragment
                displayMigrationResult(allresult.get(0), parentShell);
            }
        }
    }

    @objid ("82717dbf-6005-4fe9-a9bc-be5a19af828f")
    protected IStatus askForMigration(final IProjectFragment f, final Shell parentShell) throws InterruptedException {
        // First test for global answer
        switch (this.lastConfirmMigrationResult.getCode()) {
        case IDialogConstants.YES_TO_ALL_ID:
        case IDialogConstants.NO_TO_ALL_ID:
            return this.lastConfirmMigrationResult;
        default:
        
        }
        
        // Display the dialog in SWT thread
        final IStatus status = displayConfirmMigrationDialog(f, parentShell).join();
        
        switch (status.getCode()) {
        case IDialogConstants.YES_TO_ALL_ID:
        case IDialogConstants.NO_TO_ALL_ID:
            // Remember result
            this.lastConfirmMigrationResult = status;
            break;
        case IDialogConstants.CANCEL_ID:
        case IDialogConstants.ABORT_ID:
            // Abort project opening
            throw new InterruptedException(status.getMessage());
        default:
        
        }
        return status;
    }

    @objid ("e05e191a-88a0-4ed1-b48d-bcecf0ad7ea7")
    private void displayMigrationResult(final IStatus report, final Shell parentShell) {
        this.display.syncExec(() -> {
            final String title = AppProjectCore.I18N.getMessage("FragmentsMigrator.displayMigrationResult.title");
        
            ErrorDialog.openError(parentShell, title, null, report);
        });
    }

    @objid ("a8f9ad05-873e-478a-bc3f-4f411bf4ed9e")
    private boolean handleFragmentAuthenticationException(final IProjectFragment f, final FragmentAuthenticationException e, final Shell parentShell) {
        final IAuthData ret[] = new IAuthData[] { null };
        
        this.display.syncExec(() -> {
            final IAuthData badData = f.getAuthConfiguration().getAuthData();
            final IAuthData initialData = badData != null ? badData : new UserPasswordAuthData();
            final AuthDataDialog dlg = new AuthDataDialog(
                    parentShell,
                    initialData,
                    f.getId() + " @ " + f.getUri().getHost());
            dlg.setWarningMessage(e.getLocalizedMessage());
            if (dlg.open() == 0) {
                ret[0] = dlg.getAuthData();
            }
        });
        
        if (ret[0] != null) {
            f.getAuthConfiguration().setAuthData(ret[0]);
            return true;
        } else {
            return false;
        }
    }

    @objid ("0d8b727d-61f6-4cde-9951-e639bb776ee3")
    protected void runFragmentMigration(final SubProgress monitor, final BasicMigrationReporter reporter, final IProjectFragment f, final IFragmentMigrator migrator) throws FragmentAuthenticationException, IOException, MigrationFailedException {
        final SubProgress mon = SubProgress.convert(monitor, 6);
        final MetamodelVersionDescriptor fromVersion = f.getRequiredMetamodelDescriptor();
        
        try (IMigrationProcess mp = migrator.start(mon.newChild(1), reporter);) {
        
            mp.migrateModel(mon.newChild(2));
            f.mount(mon.newChild(1));
            runFragmentMigrationContributors(mon.newChild(1), reporter, f, fromVersion);
            f.unmount();
            mp.finish(mon.newChild(1));
        }
    }

    @objid ("dbefcd5d-94f9-4be9-8f1c-932d1428331a")
    private void runFragmentMigrationContributors(final SubProgress monitor, final BasicMigrationReporter reporter, final IProjectFragment f, final MetamodelVersionDescriptor fromVersion) throws MigrationFailedException {
        final Collection<IConfigurationElement> configurationElements = new ExtensionPointContributionManager(EXTENSIONPOINT_ID).getExtensions("contributor");
        monitor.setWorkRemaining(configurationElements.size() * 3);
        for (final IConfigurationElement ce : configurationElements) {
            try {
                final IFragmentMigrationContributor svc = (IFragmentMigrationContributor) ce.createExecutableExtension("class");
                reporter.getLogger().format("Running %s migration contributor ...%n", svc.getClass());
        
                svc.contributeMigration(monitor.newChild(2), reporter, this.project, f, fromVersion, this.eclipseContext);
        
                f.getRepository().save(monitor.newChild(1));
            } catch (final CoreException e) {
                throw new MigrationFailedException(e.getLocalizedMessage(), e);
            } catch (final IOException e) {
                throw new MigrationFailedException(FileUtils.getLocalizedMessage(e), e);
            }
        }
    }

    /**
     * Ask the user for migration confirmation.
     * 
     * @param f the fragment to migrate
     * @param parentShell a parent SWT shell
     * @return a future that will be completed when the user has made a choice.
     */
    @objid ("56454e44-e3bf-4a77-8622-f08411b39acb")
    protected CompletableFuture<IStatus> displayConfirmMigrationDialog(final IProjectFragment f, final Shell parentShell) {
        final CompletableFuture<IStatus> ret = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        final ConfirmMigrationDialog dlg = new ConfirmMigrationDialog(parentShell, f, this.mmDesc);
                        // Display the dialog
                        final int dlgResult = dlg.open();
                        return toStatus(dlgResult, f);
                    } catch (final IOException e) {
                        return new Status(IStatus.ERROR, AppProjectCore.PLUGIN_ID, IDialogConstants.NO_ID, FileUtils.getLocalizedMessage(e), e);
                    }
                }, this.display::syncExec)
                .exceptionally(t -> new Status(
                        IStatus.ERROR,
                        AppProjectCore.PLUGIN_ID,
                        IDialogConstants.NO_ID,
                        AppProjectCore.I18N.getMessage("FragmentsMigrator.ask.error", t.getClass().getSimpleName(), f.getId()),
                        t));
        return ret;
    }

    @objid ("fad998d8-4a07-4290-98ad-433248667485")
    protected IStatus getMigrationPermission(final IProjectFragment f, final Shell parentShell) throws InterruptedException {
        final FragmentMigrationNeededException ex = (FragmentMigrationNeededException) f.getDownError();
        if (!ex.isRemoteMigrationNeeded()) {
            // Local migration only, always accept
            return toStatus(IDialogConstants.YES_ID, f);
        }
        
        if (f.getScope() == DefinitionScope.SHARED) {
            return new Status(
                    IStatus.ERROR,
                    AppProjectCore.PLUGIN_ID,
                    IDialogConstants.NO_ID,
                    AppProjectCore.I18N.getMessage("FragmentsMigrator.ask.denied", f.getId(), f.getType()), null);
        
        }
        return askForMigration(f, parentShell);
    }

    /**
     * Convert {@link IDialogConstants} to a {@link IStatus} with a user friendly message.
     * 
     * @param dlgConstant one of {@link IDialogConstants}
     * @param f a project fragment
     * @return the built {@link IStatus}, never <i>null</i>.
     */
    @objid ("71e67bb3-30af-410c-86aa-6920c4e7ed87")
    protected static IStatus toStatus(final int dlgConstant, final IProjectFragment f) {
        String msg;
        switch (dlgConstant) {
        case IDialogConstants.YES_ID:
        case IDialogConstants.YES_TO_ALL_ID:
            return new Status(IStatus.OK, AppProjectCore.PLUGIN_ID, dlgConstant, "", null);
        case IDialogConstants.NO_ID:
        case IDialogConstants.NO_TO_ALL_ID:
            msg = AppProjectCore.I18N.getMessage("FragmentsMigrator.migrateFragment.skip", f.getId());
            return new Status(IStatus.INFO, AppProjectCore.PLUGIN_ID, dlgConstant, msg, null);
        case IDialogConstants.CANCEL_ID:
        case IDialogConstants.ABORT_ID:
            msg = AppProjectCore.I18N.getMessage("FragmentsMigrator.migrateFragment.abort", f.getId());
            return new Status(IStatus.CANCEL, AppProjectCore.PLUGIN_ID, dlgConstant, msg, null);
        default:
            // should not happen
            final String strVal = String.valueOf(dlgConstant);
            return new Status(IStatus.ERROR, AppProjectCore.PLUGIN_ID, dlgConstant, "unknown " + strVal, new IllegalArgumentException(strVal));
        }
    }

}
