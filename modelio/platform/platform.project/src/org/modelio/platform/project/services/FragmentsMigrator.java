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
import org.modelio.gproject.FragmentAuthenticationException;
import org.modelio.gproject.FragmentMigrationNeededException;
import org.modelio.gproject.MigrationFailedException;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGPart.GPartException;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.migration.BasicMigrationReporter;
import org.modelio.gproject.parts.IGModelFragmentMigrator;
import org.modelio.gproject.parts.IGModelFragmentMigrator.IMigrationProcess;
import org.modelio.gproject.parts.fragment.VersionHelper;
import org.modelio.platform.model.ui.dialogs.auth.AuthDataDialog;
import org.modelio.platform.project.plugin.AppProjectCore;
import org.modelio.platform.rcp.extensionpoint.ExtensionPointContributionManager;
import org.modelio.platform.ui.progress.ModelioProgressAdapter;
import org.modelio.platform.ui.swt.DefaultShellProvider;
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
    @objid ("b5d708e3-6cb5-47cf-945e-b362b89c0993")
    private static final String EXTENSIONPOINT_ID = "org.modelio.platform.project.services.IFragmentMigrationContributor";

    @objid ("4198d829-4760-4d94-8ab8-7c3a56d85f8c")
    protected IStatus lastConfirmMigrationResult = null;

    @objid ("6fab28bb-dc1f-4dfe-8d44-e61dd31efeaa")
    protected final IEclipseContext eclipseContext;

    @objid ("76460d2e-70b8-4109-8668-c2d5b9c45ec5")
    protected final Display display = Display.getDefault();

    @objid ("33af27b4-62a7-47dd-8637-70cff7bf8f52")
    protected final MetamodelVersionDescriptor mmDesc;

    @objid ("6f0a469f-6b49-491d-bd4d-6032ade960d4")
    protected final IGProject project;

    /**
     * C'tor
     * @param project the project to work on.
     * @param withConfirmation whether to ask user for confirmation.
     */
    @objid ("042f317a-bf12-42ab-bbf5-467301437964")
    public  FragmentsMigrator(final IEclipseContext eclipseContext, final IGProject project, final boolean withConfirmation) {
        this.eclipseContext = Objects.requireNonNull(eclipseContext);
        this.project = Objects.requireNonNull(project);
        this.lastConfirmMigrationResult = toStatus(withConfirmation ? IDialogConstants.YES_ID : IDialogConstants.YES_TO_ALL_ID, null);
        this.mmDesc = VersionHelper.getDescriptors(this.project.getSession().getMetamodel());
        
    }

    /**
     * Propose migration of a fragment.
     * @param parentMon the progress monitor to use
     * @param allowedMonWork the maximum progress ticks this method may use
     * @param f the fragment to migrate
     * @param parentShell a parent SWT shell
     * @return the migration report or null if nothing was done
     * @throws InterruptedException on user cancellation
     */
    @objid ("50464582-98ef-465e-8abc-f9d4443e5562")
    public IStatus migrateFragment(final IGModelFragment f, final Shell parentShell, final SubMonitor aMonitor) throws InterruptedException {
        final SubMonitor progress = SubMonitor.convert(aMonitor);
        
        if (f.getState().getDownError() instanceof FragmentMigrationNeededException) {
            final IStatus acceptMigration = getMigrationPermission(f, parentShell);
            if (!acceptMigration.isOK()) {
                if (acceptMigration.getSeverity() == IStatus.CANCEL) {
                    throw new InterruptedException();
                }
                return acceptMigration; // new Status(IStatus.WARNING, AppProjectCore.PLUGIN_ID, msg, null);
            }
        
            boolean tryAgain = false;
            do {
                final Path logFile = this.project.getPfs().getProjectRuntimePath().resolve("fragments").resolve(f.getId()).resolve("migration.log");
        
                try (BasicMigrationReporter reporter = new BasicMigrationReporter(logFile);) {
                    try {
                        final SubProgress mon = ModelioProgressAdapter.convert(aMonitor.newChild(10), 10);
                        final IGModelFragmentMigrator m = f.getMigrator(this.mmDesc);
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
                    } catch (final RuntimeException | GPartException migrationFailExc) {
                        migrationFailExc.printStackTrace(reporter.getLogger());
                        reporter.getResultReporter().println(migrationFailExc.toString());
                    } catch (final FragmentAuthenticationException authExc) {
                        // open the auth data dialog then try again
                        tryAgain = handleFragmentAuthenticationException(f, authExc, parentShell);
                        if (!tryAgain) {
                            final Throwable oldError = f.getState().getDownError();
                            authExc.addSuppressed(oldError);
                            f.getState().setDownError(authExc);
        
                            reporter.getLogger().println(authExc.getLocalizedMessage());
                            reporter.getResultReporter().println(authExc.getLocalizedMessage());
                        }
                    }
        
                    final Status reportStatus = new Status(
                            IStatus.ERROR,
                            AppProjectCore.PLUGIN_ID,
                            reporter.getResult(),
                            null);
        
                    if (progress.isCanceled()) {
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
     * @param allowedMonWork the maximum progress ticks this method may use
     * @param aMonitor the progress monitor to use
     * @throws InterruptedException on cancel
     */
    @objid ("2afc1535-052c-439e-84e7-d17d8de792eb")
    public void migrateFragments(final SubMonitor aMonitor) throws InterruptedException {
        final SubMonitor progress = SubMonitor.convert(aMonitor);
        
        // Find fragments to migrate: those which are DOWN for migration needed reason.
        final List<IGModelFragment> fragsToMigrate = this.project.getParts(IGModelFragment.class)
                .stream()
                .filter(f -> (f.getState().getDownError() instanceof FragmentMigrationNeededException))
                .collect(Collectors.toList());
        
        
        final Shell parentShell = CompletableFuture.supplyAsync(DefaultShellProvider::getBestParentShell, this.display::syncExec).join();
        
        if (!fragsToMigrate.isEmpty()) {
            int nbFails = 0;
            final ArrayList<IStatus> allresult = new ArrayList<>();
        
            progress.setWorkRemaining(fragsToMigrate.size());
        
        
            for (final IGModelFragment f : fragsToMigrate) {
                progress.subTask(f.getId());
                progress.worked(1);
        
                final IStatus result = migrateFragment(f, parentShell, progress.newChild(1));
                if (result != null) {
                    allresult.add(result);
                    if (result.getSeverity() > IStatus.INFO) {
                        nbFails++;
                    }
                }
                if (progress.isCanceled()) {
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
        progress.done();
        
    }

    @objid ("82717dbf-6005-4fe9-a9bc-be5a19af828f")
    protected IStatus askForMigration(final IGModelFragment f, final Shell parentShell) throws InterruptedException {
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
    private boolean handleFragmentAuthenticationException(final IGModelFragment f, final FragmentAuthenticationException e, final Shell parentShell) {
        final IAuthData ret[] = new IAuthData[] { null };
        
        this.display.syncExec(() -> {
            final IAuthData badData = f.getAuth().getData();
            final IAuthData initialData = badData != null ? badData : new UserPasswordAuthData();
            final AuthDataDialog dlg = new AuthDataDialog(
                    parentShell,
                    initialData,
                    f.getId() + " @ " + f.getDescriptor().getLocation().getHost());
            dlg.setWarningMessage(e.getLocalizedMessage());
            if (dlg.open() == 0) {
                ret[0] = dlg.getAuthData();
            }
        });
        
        if (ret[0] != null) {
            f.getAuth().setData(ret[0]);
            return true;
        } else {
            return false;
        }
        
    }

    @objid ("0d8b727d-61f6-4cde-9951-e639bb776ee3")
    protected void runFragmentMigration(final SubProgress monitor, final BasicMigrationReporter reporter, final IGModelFragment f, final IGModelFragmentMigrator migrator) throws MigrationFailedException, FragmentAuthenticationException, IOException {
        final SubProgress mon = SubProgress.convert(monitor, 6);
        final MetamodelVersionDescriptor fromVersion = f.getRequiredMetamodelDescriptor();
        
        try (IMigrationProcess mp = migrator.start(mon.newChild(1), reporter);) {
            mp.migrateModel(mon.newChild(2));
            f.mount(mon.newChild(1));
            runFragmentMigrationContributors(mon.newChild(1), reporter, f, fromVersion);
            f.unmount(mon);
            mp.finish(mon.newChild(1));
        } catch (GPartException e) {
            throw new MigrationFailedException(e.getLocalizedMessage(), e);
        }
        
    }

    @objid ("dbefcd5d-94f9-4be9-8f1c-932d1428331a")
    private void runFragmentMigrationContributors(final SubProgress monitor, final BasicMigrationReporter reporter, final IGModelFragment f, final MetamodelVersionDescriptor fromVersion) throws MigrationFailedException {
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
     * @param f the fragment to migrate
     * @param parentShell a parent SWT shell
     * @return a future that will be completed when the user has made a choice.
     */
    @objid ("56454e44-e3bf-4a77-8622-f08411b39acb")
    protected CompletableFuture<IStatus> displayConfirmMigrationDialog(final IGModelFragment f, final Shell parentShell) {
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
    protected IStatus getMigrationPermission(final IGModelFragment f, final Shell parentShell) throws InterruptedException {
        final FragmentMigrationNeededException ex = (FragmentMigrationNeededException) f.getState().getDownError();
        if (!ex.isRemoteMigrationNeeded()) {
            // Local migration only, always accept
            return toStatus(IDialogConstants.YES_ID, f);
        }
        
        if (f.getDefinitionScope() == DefinitionScope.SHARED) {
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
     * @param dlgConstant one of {@link IDialogConstants}
     * @param f a project fragment
     * @return the built {@link IStatus}, never <i>null</i>.
     */
    @objid ("71e67bb3-30af-410c-86aa-6920c4e7ed87")
    protected static IStatus toStatus(final int dlgConstant, final IGModelFragment f) {
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
