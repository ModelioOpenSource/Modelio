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
package org.modelio.gproject.parts.fragment;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.FragmentAuthenticationException;
import org.modelio.gproject.FragmentConflictException;
import org.modelio.gproject.FragmentMigrationNeededException;
import org.modelio.gproject.auth.AuthReconfigurer;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGPart;
import org.modelio.gproject.core.IGPart.GPartException;
import org.modelio.gproject.core.IGPartState.GPartStateEnum;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.monitor.GProjectEvent;
import org.modelio.gproject.monitor.GProjectEventType;
import org.modelio.gproject.parts.AbstractGPart;
import org.modelio.gproject.parts.GPartFactory;
import org.modelio.gproject.parts.GPartState;
import org.modelio.gproject.parts.IGModelFragmentMigrator;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.gproject.project.GProject;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.model.spi.mm.MmVersionComparator;
import org.modelio.vcore.session.api.IAccessManager;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.api.repository.IRepositoryErrorListener;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor.Difference;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Specific abstract implementation of {@link IGModelFragment} used for project model fragment implementation.
 * 
 * <p>
 * For most of the public contract operations, the class is based on delegation for specific implementations while carrying out the common processing itself.
 * 
 * Implementers of additional model fragment parts are strongly advised to derive from this class instead of directly implementing {@link IGModelFragment}.
 * </p>
 */
@objid ("7de9c170-7198-4657-b07e-e17041c607bf")
public abstract class AbstractGModelFragment extends AbstractGPart implements IGModelFragment {
    /**
     * Project data sub directory where fragments data are stored. Contains one directory for each fragment.
     */
    @objid ("4575c9fb-1969-4705-87e7-a2ce072512ce")
    protected static final String FRAGMENTS_SUBDIR = "fragments";

    /**
     * File name of the file containing the metamodel versions.
     * <p>
     * To be used by {@link #getRequiredMetamodelDescriptor()} in most cases.
     */
    @objid ("da8eafaa-9de0-45b1-8ae5-76b5db44400c")
    protected static final String MMVERSION_FILE_NAME = "mmversion.dat";

    @objid ("8176615b-15b1-41f7-a683-b9384877db9e")
    protected String encodedDirectoryName;

    @objid ("a301acc7-213c-45f5-958e-ea1dbc7e7b25")
    private final Object stateLock;

    @objid ("12cc7d95-fdfe-45eb-b06f-28c39ea0a0bc")
    private IRepositoryErrorListener errSupport;

    @objid ("a3cf47a4-c792-4365-a9ab-8e9c7d8ac552")
    protected  AbstractGModelFragment(GProjectPartDescriptor desc) {
        super(desc);
        this.stateLock = new Object();
        this.encodedDirectoryName = FileUtils.encodeFileName(getId(), new StringBuilder()).toString();
        
    }

    @objid ("7537dfb6-7c6f-41bf-a9ca-229cb0853fc8")
    @Override
    public final void install(IGProject aProject, IModelioProgress monitor) throws GPartException {
        // Call inherited behavior that changes the state
        super.install(aProject, monitor);
        
        // Sub classes hook
        doInstall(aProject, monitor);
        
    }

    @objid ("0c506f3d-9cb1-49f9-ae1b-34fa49a59fe3")
    protected abstract void doInstall(IGProject aProject, IModelioProgress monitor) throws GPartException;

    @objid ("3feea1c5-57d4-4e2d-8e51-58b061aabe76")
    @Override
    public final void uninstall(IGProject project, IModelioProgress monitor) throws GPartException {
        // Sub classes hook
        doUninstall(project, monitor);
        
        // Call inherited behavior that changes state to UNINSTALLED
        super.uninstall(project, monitor);
        
        // Delete from disk
        try {
            delete(project, monitor);
        } catch (IOException e) {
            throw new GPartException(FileUtils.getLocalizedMessage(e), e);
        }
        
    }

    /**
     * Sub classes hook for {@link #uninstall(IGProject, IModelioProgress)}
     * @param project the project
     * @param monitor a progress monitor
     * @throws GPartException on failure
     */
    @objid ("63037aa2-c29e-4600-890c-62625c1112e0")
    protected abstract void doUninstall(IGProject project, IModelioProgress monitor) throws GPartException;

    @objid ("505c4b17-ef67-4d15-841a-f79944178b56")
    @Override
    public void rename(String name, IModelioProgress aMonitor) throws IOException, GPartException {
        if (Objects.equals(getId(), name)) {
            return;
        }
        
        IGProject project = getProject();
        
        String newEncodedDirectoryName = FileUtils.encodeFileName(name, new StringBuilder()).toString();
        Path newDataDirectory = project.getPfs().getProjectDataPath().resolve(FRAGMENTS_SUBDIR).resolve(newEncodedDirectoryName);
        
        GProjectPartDescriptor descriptor = getDescriptor();
        boolean isPermanent = project.getDescriptor().getPartDescriptors().contains(descriptor);
        Path oldDataDirectory = getDataDirectory();
        
        Files.move(oldDataDirectory, newDataDirectory);
        //Files.move(oldRuntimePath, newRuntimeDirectory);
        
        SubProgress mon = SubProgress.convert(aMonitor, 2);
        
        // URI changed : forget this fragment
        project.removeGPart(mon.newChild(1), this);
        
        try {
            descriptor.setId(name);
            this.encodedDirectoryName = newEncodedDirectoryName;
        
            // Register fragment again
            project.addGPart(mon.newChild(1), this, isPermanent);
        } catch (FragmentConflictException e) {
            // Report error
            project.getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(this, e));
        
            // try to rollback
            try {
                project.addGPart(mon.newChild(1), this, isPermanent);
            } catch (FragmentConflictException e1) {
                e1.addSuppressed(e);
                setDown(e1);
            }
        }
        
    }

    @objid ("8a335cef-a166-44b8-a1bb-e565ed296ae8")
    @Override
    public final void mount(IModelioProgress aMonitor) throws GPartException {
        GPartStateEnum stateValue = this.state.getValue();
        if (stateValue != GPartStateEnum.INSTALLED && stateValue != GPartStateEnum.DOWN) {
            throw new GPartException("Mount failed, invalid part state " + stateValue);
        }
        
        try {
            this.state.sendStartMount();
        
            SubProgress mon = SubProgress.convert(aMonitor, 90);
        
            IGProject project = getProject();
            IRepository repository = doMountInitRepository(project, mon.newChild(30));
            checkVersions();
        
            this.errSupport = new RepositoryErrorListener(this);
            repository.getErrorSupport().addErrorListener(this.errSupport);
        
            IAccessManager accessManager = doInitAccessManager();
            project.getSession().getRepositorySupport().connectRepository(repository, getId(), accessManager, mon.newChild(30));
            doMountPostConnect(mon.newChild(30));
        
            this.state.sendEndMount(null);
        } catch (IllegalStateException | IOException | FragmentAuthenticationException | FragmentMigrationNeededException e) {
            // State transition error, mark the part as down
            this.state.sendEndMount(e);
            throw new GPartException(e);
        }
        
    }

    @objid ("75a40b7a-19d2-4310-b14e-690300a478bc")
    @Override
    public final void unmount(IModelioProgress monitor) throws GPartException {
        IGProject project = getProject();
        
        IRepository repository = getRepository();
        if (repository != null) {
            if (repository.isOpen()) {
                project.getSession().getRepositorySupport().disconnectRepository(repository);
            }
            repository.getErrorSupport().removeErrorListener(this.errSupport);
        }
        
        // Call the umount post process hook
        try {
            doUmountPostProcess(project, monitor);
        } catch (IOException e) {
            project.getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(this, e));
        }
        
        this.state.sendUnmount();
        
    }

    /**
     * Unmount the part in the project making it 'down' in the project.<br/>
     * The umount applies immediately for the current project session.<br/>
     * The 'down' state of the part is <b>not</b> persisted in the project.conf file and the part will be mounted again at next project opening.
     * Fires a {@link GProjectEventType#PART_DOWN PART_DOWN} {@link GProjectEvent event}.
     * 
     * Implementors have to:
     * <ul>
     * <li>update the part state (see {@link GPartState#sendDown(Throwable)}).</li>
     * <li>report the down error in the state in case of failure</li>
     * <li>post GFailure to the project to log the umount failure cause</li>
     * </ul>
     * 
     * Please note that the {@link GPartState} will automatically fire {@link GProjectEvent} to the project event support when state changes.
     * @param error the cause of down state
     */
    @objid ("d4d902c2-8738-4c2a-89fb-5cab7e270f77")
    public final void setDown(Throwable error) {
        assert error != null;
        
        if (this.state.getValue()==GPartStateEnum.DOWN) {
            // Ignore new error
            return;
        }
        
        // Disconnect the repository
        // and set all loaded objects as shell.
        
        IGProject project = getProject();
        IRepository repository = getRepository();
        if (repository != null) {
            if (repository.isOpen()) {
                project.getSession().getRepositorySupport().disconnectRepository(repository);
            }
            repository.getErrorSupport().removeErrorListener(this.errSupport);
        }
        
        // Call the umount post process hook
        try {
            doUmountPostProcess(project, null);
        } catch (IOException | RuntimeException e) {
            project.getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(this, e));
        }
        
        this.state.sendDown(error);
        
    }

    /**
     * Convenience method to get the project metamodel.
     * @return the project metamodel.
     */
    @objid ("90dce4cc-c67f-4944-97e0-400cf9973de5")
    protected final SmMetamodel getProjectMetamodel() {
        return getProject().getSession().getMetamodel();
    }

    /**
     * Hook called by {@link #unmount(IModelioProgress)} after having disconnected the repository from the modeling session.
     * <p>
     * May be redefined by subclasses to clean resources.
     * @param project the current project.
     * @param monitor the progress monitor to use for reporting progress to the user.
     * @throws IOException if some errors should be reported. The unmount will not be cancelled.
     */
    @objid ("a5d431b8-09b8-4d4b-9240-edc24e0ab96d")
    protected void doUmountPostProcess(IGProject project, IModelioProgress monitor) throws IOException {
        // Does nothing by default
    }

    /**
     * Instantiate the {@link #getRepository() repository}.
     * <p>
     * This is a hook called by {@link #mount(IModelioProgress)}. The implementation must just instantiate the {@link IRepository} without opening it.
     * <p>
     * The implementation must <b>not</b> call <code>done()</code> on the given monitor and must leave a non negligible part of the progress unconsumed.
     * @param project the current project.
     * @param aMonitor the progress monitor to use for reporting progress to the user.
     * <p>
     * The implementation must <b>not</b> call <code>done()</code> the given monitor and must leave a non negligible part of the progress unconsumed.
     * <p>
     * Accepts <i>null</i>, indicating that no progress should be reported and that the operation cannot be cancelled.
     * @return the instantiated repository.
     * @throws IOException in case of failure.
     * @throws FragmentAuthenticationException in case of authentication failure
     */
    @objid ("6ac2a7b9-5637-4e9c-b620-8906e26aea13")
    protected abstract IRepository doMountInitRepository(IGProject project, IModelioProgress aMonitor) throws IOException, FragmentAuthenticationException;

    /**
     * Initialize the access manager.
     * @return the access manager.
     */
    @objid ("83d0c7ba-03d5-4319-91f2-c1e0b9a7c67f")
    protected abstract IAccessManager doInitAccessManager();

    /**
     * Hook called by {@link #mount(IModelioProgress)} after having connected the repository to the session.
     * <p>
     * Does nothing by default. Subclasses may redefine it to populate the repository.
     * @param mon the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     * @throws IOException in case of failure.
     */
    @objid ("78af850a-f769-4a79-a3e9-4d80db38b637")
    protected void doMountPostConnect(IModelioProgress mon) throws IOException {
        // Nothing by default
    }

    @objid ("0ba59a89-9fd8-4b90-96f2-740b359a5a00")
    @Override
    public final Path getDataDirectory() {
        IGProject project = getProject();
        if (project == null) {
            throw new IllegalArgumentException("This fragment is not in a project");
        }
        return getDataDirectory(project);
    }

    @objid ("cd59e1fc-e40c-4186-9ae8-10a0fe07a80d")
    protected final Path getDataDirectory(IGProject project) {
        return project.getPfs().getProjectDataPath().resolve(FRAGMENTS_SUBDIR)
                .resolve(this.encodedDirectoryName);
        
    }

    /**
     * Delete the fragment contents from the disk when applicable.
     * <p>
     * To be called from {@link #uninstall(IGProject, IModelioProgress)}
     * <h2>History</h2>
     * From 5.3.1 this method is protected , final and not published in IGModelFragment.
     * @param project context
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     * @throws IOException if the fragment could not completely be removed from disk.
     */
    @objid ("689d617e-16d0-4dee-acb2-44587fe860b5")
    protected final void delete(IGProject project, IModelioProgress monitor) throws IOException, GPartException {
        if (this.state.getValue() != GPartStateEnum.UNINSTALLED) {
            throw new GPartException("Delete failed");
        }
        
        // Call sub classes
        doDelete(project, monitor);
        
        // Do standard cleaning
        FileUtils.delete(getRuntimeDirectory(project));
        FileUtils.delete(getDataDirectory(project));
        
    }

    /**
     * Redefined this method if your fragment implementation deals with files that are not in the <i>data</i> nor <i>runtime</i> directories.
     * @param project the current project.
     * @param monitor a progress monitor.
     */
    @objid ("2c1a4d59-b3c8-4e3e-adc8-8ce0336462ad")
    protected void doDelete(IGProject project, IModelioProgress monitor) {
        // nothing by default
    }

    @objid ("9278e1ea-2c20-41e7-84f0-4207eccda760")
    @Override
    public final Path getRuntimeDirectory() {
        IGProject project = getProject();
        if (project == null) {
            throw new IllegalArgumentException("This fragment is not in a project");
        }
        return getRuntimeDirectory(project);
    }

    @objid ("44002958-cf73-4bc7-8276-95bedfb22c1e")
    protected final Path getRuntimeDirectory(IGProject project) {
        return project.getPfs().getProjectRuntimePath().resolve(FRAGMENTS_SUBDIR)
                .resolve(this.encodedDirectoryName);
        
    }

    @objid ("232a92a9-8792-49d1-a4ed-7f23f44b4213")
    protected final MetamodelVersionDescriptor getCurrentMmDescriptor() {
        return VersionHelper.getDescriptors(getProjectMetamodel());
    }

    /**
     * Get the root elements of the fragment.
     * <p>
     * Returns an empty list as long as there is no open repository. In the other case delegates to {@link #doGetRoots()}.
     * <p>
     * If the doGetRoots() fails to get the roots calls {@link #setDown(Throwable)}.
     */
    @objid ("f5d398b8-84bc-4af4-ba9d-092671be6ed1")
    @Override
    public final Collection<MObject> getRoots() {
        IRepository repository = getRepository();
        if (repository == null || !repository.isOpen()) {
            return Collections.emptyList();
        } else {
            try {
                return doGetRoots().stream().filter(o -> o.isValid()).collect(Collectors.toList());
            } catch (IOException e) {
                setDown(e);
                return Collections.emptyList();
            }
        }
        
    }

    /**
     * Delegate get the root elements of the fragment.
     * <p>
     * This method is called by {@link AbstractGModelFragment#getRoots()} that ensures there is an open repository.
     * @return the root elements of the fragment.
     * @throws IOException in case of failure.
     */
    @objid ("ac589d56-7273-4081-834d-3a99ceeb1d6a")
    protected abstract Collection<MObject> doGetRoots() throws IOException;

    /**
     * The default implementation returns null.
     */
    @objid ("fac98c6a-8de3-4d75-89b2-9cf465cbf454")
    @Override
    public IGModelFragmentMigrator getMigrator(MetamodelVersionDescriptor targetMetamodel) throws IOException {
        return null;
    }

    /**
     * Default implementation.
     * <p>
     * Unregister itself and install a new fragment if the URI is different. Replaces its properties by the given ones.
     * @param aMonitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that
     * the operation cannot be cancelled.
     */
    @objid ("343672e8-c111-49ac-a935-1e841bcc0d4f")
    @Override
    public void reconfigure(GProjectPartDescriptor desc, IModelioProgress aMonitor) throws GPartException {
        GProjectPartDescriptor currentDescriptor = getDescriptor();
        if (Objects.equals(currentDescriptor.getLocation(), desc.getLocation())) {
            // Same URI : unmount, update properties and remount
            boolean isMount = getState().getValue() == GPartStateEnum.MOUNTED;
            SubProgress mon = SubProgress.convert(aMonitor, 2);
        
            if (isMount) {
                unmount(mon.newChild(1));
            }
        
            currentDescriptor.setDefinitionScope(desc.getDefinitionScope());
            setProperties(new GProperties(desc.getProperties()));
            AuthReconfigurer.reconfigure(getAuth(), desc.getAuth());
        
            if (isMount) {
                mon.setWorkRemaining(1);
                mount(mon);
            }
        } else {
            // URI changed : forget this fragment and register a new one
            removeAndRecreateFragment(desc, aMonitor);
        }
        
    }

    /**
     * Removes this fragment from the project and recreate another from the given descriptor, atomically.
     * <p>
     * If the other fragment creation fails, best effort are made to restore the previous one.
     * @param desc the new fragment descriptor
     * @param aMonitor a progress monitor
     * @throws GPartException on failure
     */
    @objid ("3ef62df4-b46b-4524-a690-e18742285427")
    protected void removeAndRecreateFragment(GProjectPartDescriptor desc, IModelioProgress aMonitor) throws GPartException {
        SubProgress mon = SubProgress.convert(aMonitor, 4);
        IGProject currentProject = getProject();
        currentProject.removeGPart(mon.newChild(1),  this);
        
        IGPart fragment = GPartFactory.getInstance().instantiate(desc);
        
        try (UndoableDeletion back = new UndoableDeletion()) {
            // move directories to backup place
            back.sendToTrash(getRuntimeDirectory(currentProject), getDataDirectory(currentProject));
        
            // Delete from disk
            delete(currentProject, mon.newChild(1));
        
            currentProject.addGPart(mon.newChild(1), fragment, true);
        
            back.allowDelete();
        } catch (RuntimeException | FragmentConflictException | IOException e) {
            // Report error
            currentProject.getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(fragment, e));
        
            // try to rollback
            try {
                currentProject.addGPart(mon.newChild(1), this, true);
            } catch (FragmentConflictException e1) {
                e1.addSuppressed(e);
                setDown(e);
            }
        }
        
    }

    /**
     * Check the required metamodels versions against current Modelio metamodel versions.
     * <p>
     * May also check the repository format version against the last implementation.
     * <p>
     * Returns normally if the fragment may be directly open else throws an exception. Implementations may use {@link #getRepository()} that returns the not yet open repository to check .
     * @throws IOException if the metamodel version does not allow the fragment to be open.
     * @throws FragmentMigrationNeededException if the fragment needs to be migrated before opening.
     */
    @objid ("0b2fac9e-98a0-49de-a033-90ed722e05d7")
    protected void checkVersions() throws IOException, FragmentMigrationNeededException {
        MetamodelVersionDescriptor neededMm = getRequiredMetamodelDescriptor();
        MetamodelVersionDescriptor currentMm = getCurrentMmDescriptor();
        MmVersionComparator comparator = MmVersionComparator.withSource(neededMm).withTarget(currentMm);
        
        if (!comparator.isTargetCompatible(false)) {
            IGModelFragmentMigrator migrator = getMigrator(currentMm);
            if (migrator != null) {
                // A migrator is available, tell migration needed
                throw new FragmentMigrationNeededException(this, currentMm);
            } else {
                // No migrator available, fragment not usable unless :
                // - Modelio misses some metamodel fragments (we will have shell metaclasses)
                // - Modelio metamodel is build compatible.
                if (!comparator
                        .withMissingSourcesRemoved()
                        .isTargetCompatible(true)) {
                    // Fragment not usable
                    Collection<Difference> checkRes = currentMm.getIncompatibilities(neededMm, false);
                    throw new IOException(compileErrors(checkRes, currentMm));
                }
            }
        }
        
    }

    @objid ("d16dbc70-ddf9-48f7-b389-4e4d735ea4c6")
    private String compileErrors(Collection<Difference> checkRes, MetamodelVersionDescriptor currentMm) {
        String msg = checkRes.stream()
                .map(entry -> {
                    String fragName = entry.neededMmFragment.getName();
                    Version fragVersion = entry.neededMmFragment.getVersion();
                    switch (entry.type) {
                    case older:
                        return CoreProject.I18N.getMessage("AbstractFragment.MmVersionNotSupported", getId(),
                                fragName, fragVersion, currentMm.getVersion(fragName));
                    case missing:
                        return CoreProject.I18N.getMessage("AbstractFragment.MissingMetamodelFragment", getId(),
                                fragName, fragVersion);
                    case newer:
                        return CoreProject.I18N.getMessage("AbstractFragment.FutureMmVersion", getId(),
                                fragName, fragVersion, currentMm.getVersion(fragName));
                    case olderCompatibleBuild:
                        return CoreProject.I18N.getMessage("AbstractFragment.CompatibleMmVersion", getId(),
                                fragName, fragVersion, currentMm.getVersion(fragName));
        
                    case same:
                    default:
                        return null;
                    }
        
                })
                .filter(s -> s != null)
                .collect(Collectors.joining(", \n"));
        return msg;
    }

    /**
     * Repository error listener.
     * <p>
     * Catches repository errors and transmit them to the {@link GProject} event monitor.
     */
    @objid ("b55df463-5723-4e1b-aede-52d356d99d65")
    private static class RepositoryErrorListener implements IRepositoryErrorListener {
        @objid ("02accf90-387f-448f-a6e2-8e6a4a09f70a")
        private AbstractGModelFragment modelFragment;

        @objid ("1e9be065-2cc5-4f6f-98d8-c221eee88868")
        public  RepositoryErrorListener(AbstractGModelFragment fragment) {
            this.modelFragment = fragment;
        }

        @objid ("6c6f417a-4262-4462-b322-1ebd14e77fba")
        @Override
        public void onWarning(IRepository repository, Throwable e) {
            this.modelFragment.getProject().getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(this.modelFragment, e));
        }

        @objid ("286215af-0811-4ac3-a42f-cd3e41e101b1")
        @Override
        public void onError(IRepository repository, Throwable e) {
            this.modelFragment.setDown(e);
        }

    }

    /**
     * Directories delete operation that will be undone on {@link #close()} unless {@link #allowDelete()} has been called before.
     * 
     * @author cma
     * @since Valkyrie
     */
    @objid ("5c34e5d5-c184-4a46-a8b8-3bc05de1dff8")
    protected static class UndoableDeletion implements Closeable {
        @objid ("4eca9d23-b2d5-4ada-811a-9219610c3386")
        private boolean doDelete;

        @objid ("e0fc36b1-4d22-4e46-803c-775e25d6ec46")
        private List<Path> toDelete;

        @objid ("751cd2c1-b3f5-4294-8ba0-ad94aa3935c2")
        private List<Path> backup;

        @objid ("9fbc4008-75d8-46ae-818f-d74e2406e877")
        public  UndoableDeletion() {
            
        }

        @objid ("047d8426-b581-4a5e-b3b6-b00704f43235")
        public void sendToTrash(Path... pathsToDelete) throws IOException {
            this.toDelete = new ArrayList<>(pathsToDelete.length);
            this.backup = new ArrayList<>(pathsToDelete.length);
            for (Path path : pathsToDelete) {
                if (path != null && Files.exists(path)) {
                    Path backupPath = path.resolveSibling("_" + path.getFileName() + ".bak");
                    // clean previous backup
                    FileUtils.delete(backupPath);
                    // do backup
                    FileUtils.move(path, backupPath);
                    // record successful backup
                    this.backup.add(backupPath);
                    this.toDelete.add(path);
                }
            }
            
        }

        /**
         * {@link #close()} will delete the paths.
         */
        @objid ("85449068-5094-4629-814b-8ea570f1aebe")
        public void allowDelete() {
            this.doDelete = true;
        }

        @objid ("336b542d-72cc-4c76-9135-421842c5ab81")
        @Override
        public void close() throws IOException {
            if (this.doDelete) {
                for (Path element : this.backup) {
                    try {
                        FileUtils.delete(element);
                    } catch (IOException e) {
                        // Log and continue
                        GProjectEvent.buildWarning(element, e);
                    }
                }
            } else {
                restore();
            }
            
        }

        @objid ("a669781a-e177-4bb6-88f0-65cd6a3ba04e")
        private void restore() throws IOException {
            for (int i = 0; i < this.backup.size(); i++) {
                Path toRestore = this.backup.get(i);
                FileUtils.move(toRestore, this.toDelete.get(i));
            }
            
        }

    }

}
