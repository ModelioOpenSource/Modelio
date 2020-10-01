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

package org.modelio.gproject.fragment;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.GAuthConf;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.fragment.migration.IFragmentMigrator;
import org.modelio.gproject.gproject.AuthResolver;
import org.modelio.gproject.gproject.FragmentConflictException;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.gproject.GProjectEvent;
import org.modelio.gproject.gproject.GProjectEventType;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.net.UriAuthenticationException;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.model.spi.mm.MmVersionComparator;
import org.modelio.vcore.session.api.IAccessManager;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.api.repository.IRepositoryErrorListener;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor.Difference;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Abstract implementation of a project fragment.
 */
@objid ("00dad9ac-abbf-11e1-8392-001ec947ccaf")
public abstract class AbstractFragment implements IProjectFragment {
    @objid ("06425676-3019-11e2-8f81-001ec947ccaf")
    private DefinitionScope definitionScope;

    @objid ("e0fc65f3-d66d-11e1-9f03-001ec947ccaf")
    private FragmentState state = FragmentState.INITIAL;

    @objid ("a32081fc-abf1-11e1-8392-001ec947ccaf")
    private String id;

    /**
     * Project data sub directory where fragment data are stored. Contains one directory for each fragment.
     */
    @objid ("7c90d1fb-a9e7-4cd0-9056-9155358cf9d3")
    private static final String FRAGMENTS_SUBDIR = "fragments";

    @objid ("4417921b-39f5-4b0a-9f7f-346475d15790")
    private String encodedDirName;

    /**
     * File name of the file containing the metamodel versions.
     * <p>
     * To be used by {@link #getRequiredMetamodelDescriptor()} in most cases.
     */
    @objid ("56ffb25b-35a1-40b0-aead-be40f853cf28")
    protected static final String MMVERSION_FILE_NAME = "mmversion.dat";

    @objid ("a303e5f8-abf1-11e1-8392-001ec947ccaf")
    private GProperties properties;

    @objid ("6a70dfce-d66d-11e1-9f03-001ec947ccaf")
    private Throwable downError;

    @objid ("6a70dfcf-d66d-11e1-9f03-001ec947ccaf")
    private RepositoryListener errSupport;

    @objid ("8ed3c8d4-07f4-11e2-b193-001ec947ccaf")
    private GProject gproject;

    @objid ("682da9e4-b4df-4297-856f-5e80b9ad608b")
    private final GAuthConf authConf;

    @objid ("2ad5e7b6-57b5-483b-81c7-79faa6bafd27")
    private final Object stateLock;

    /**
     * Initialize the fragment
     * 
     * @param id the fragment id.
     * @param definitionScope definition scope
     * @param properties the fragment properties.
     * @param authConf authentication configuration
     */
    @objid ("c1778cce-95da-11e1-ac83-001ec947ccaf")
    public AbstractFragment(final String id, DefinitionScope definitionScope, final GProperties properties, GAuthConf authConf) {
        Objects.requireNonNull(id, "id is null.");
        Objects.requireNonNull(definitionScope, "definition scope is null");
        Objects.requireNonNull(properties, "properties is null");
        
        this.stateLock = new Object();
        this.id = id;
        this.encodedDirName = FileUtils.encodeFileName(getId(), new StringBuilder()).toString();
        this.properties = properties;
        this.definitionScope = definitionScope;
        this.errSupport = new RepositoryListener();
        this.authConf = authConf;
    }

    @objid ("c1778cd1-95da-11e1-ac83-001ec947ccaf")
    @Override
    public final String getId() {
        return this.id;
    }

    @objid ("49bc2ad3-ab3f-11e1-8392-001ec947ccaf")
    @Override
    public final GProperties getProperties() {
        return this.properties;
    }

    /**
     * Changes the fragment state and fires a project change event.
     * 
     * @param newState the new fragment state.
     */
    @objid ("6a73421e-d66d-11e1-9f03-001ec947ccaf")
    private final void setState(FragmentState newState) {
        final boolean changed;
        
        synchronized (this.stateLock) {
            changed = newState != this.state;
        
            if (newState != FragmentState.DOWN) {
                this.downError = null;
            }
            this.state = newState;
        }
        
        if (changed && getProject() != null) {
            getProject().getMonitorSupport().fireMonitors(GProjectEvent.fragmentStateChanged(this));
        }
    }

    @objid ("9df6c318-ceb8-4cc7-9b93-886f0de15a85")
    @Override
    public GAuthConf getAuthConfiguration() {
        return this.authConf;
    }

    @objid ("6a734225-d66d-11e1-9f03-001ec947ccaf")
    @Override
    public final Throwable getDownError() {
        synchronized (this.stateLock) {
            return this.downError;
        }
    }

    /**
     * @return the fragment data directory.
     */
    @objid ("b422a1e8-0baa-11e2-bed6-001ec947ccaf")
    public final Path getDataDirectory() {
        return this.gproject.getProjectFileStructure().getProjectDataPath().resolve(AbstractFragment.FRAGMENTS_SUBDIR)
                .resolve(this.encodedDirName);
    }

    /**
     * Get the fragment runtime directory.
     * <p>
     * The runtime directory contains files that can be deleted at any time without breaking the fragment.
     * 
     * @return the fragment runtime directory.
     */
    @objid ("b422a1ed-0baa-11e2-bed6-001ec947ccaf")
    public final Path getRuntimeDirectory() {
        return this.gproject.getProjectFileStructure().getProjectRuntimePath().resolve(AbstractFragment.FRAGMENTS_SUBDIR)
                .resolve(this.encodedDirName);
    }

    /**
     * Get the root elements of the fragment.
     * <p>
     * Returns an empty list as long as there is no open repository. In the other case delegates to {@link #doGetRoots()}.
     * <p>
     * If the doGetRoots() fails to get the roots calls {@link #setDown(Throwable)}.
     */
    @objid ("b4276693-0baa-11e2-bed6-001ec947ccaf")
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

    @objid ("6327ff06-3004-11e2-8f81-001ec947ccaf")
    @Override
    public DefinitionScope getScope() {
        return this.definitionScope;
    }

    @objid ("6a73422a-d66d-11e1-9f03-001ec947ccaf")
    @Override
    public final FragmentState getState() {
        synchronized (this.stateLock) {
            return this.state;
        }
    }

    /**
     * Get the project to which the fragment is attached
     * 
     * @return the project.
     */
    @objid ("8ed62b33-07f4-11e2-b193-001ec947ccaf")
    public final GProject getProject() {
        return this.gproject;
    }

    @objid ("dd899fe8-54f3-11e2-b5ff-001ec947ccaf")
    @Override
    public final void delete() throws IOException {
        // Call sub classes
        // TODO : add a progress monitor to delete()
        doDelete(null);
        
        // Do standard cleaning
        unmount();
        FileUtils.delete(getRuntimeDirectory());
        FileUtils.delete(getDataDirectory());
    }

    /**
     * Mount a fragment.
     * <p>
     * The process is:
     * <ul>
     * <li>set the state as MOUNTING
     * <li>set the current project
     * <li>call {@link #doMountInitRepository(IModelioProgress)}
     * <li>connect the repository to the session
     * <li>call {@link #doMountPostConnect(IModelioProgress)}
     * <li>set the state as UP
     * </ul>
     * Subclasses must implement {@link #doMountInitRepository(IModelioProgress)} and may implement {@link #doMountPostConnect(IModelioProgress)}.
     */
    @objid ("8ed62b2b-07f4-11e2-b193-001ec947ccaf")
    @Override
    public final void mount(IModelioProgress aMonitor) {
        synchronized (this.stateLock) {
            if (this.state != FragmentState.INITIAL && this.state != FragmentState.DOWN) {
                // Log for debug and return immediately
                Log.trace(new IllegalStateException(MessageFormat.format("The ''{0}'' fragment is already mount: {1}", getId(), this.state)));
                return;
            }
        
            setState(FragmentState.MOUNTING);
        }
        
        try {
            SubProgress mon = SubProgress.convert(aMonitor, 100);
            IRepository repository = doMountInitRepository(mon);
        
            checkVersions();
        
            mon.setWorkRemaining(100);
            repository.getErrorSupport().addErrorListener(getRepositoryErrorSupport());
            IAccessManager accessManager = doInitAccessManager();
            this.gproject.getSession().getRepositorySupport().connectRepository(repository, getId(), accessManager, mon);
        
            mon.setWorkRemaining(100);
            doMountPostConnect(mon);
        
            setState(FragmentState.UP_FULL);
        } catch (RuntimeException e) {
            setDown(e);
        } catch (UriAuthenticationException e) {
            setDown(new FragmentAuthenticationException(FileUtils.getLocalizedMessage(e), e));
        } catch (IOException | FragmentMigrationNeededException | FragmentAuthenticationException e) {
            setDown(e);
        } finally {
            synchronized (this.stateLock) {
                if (this.state == FragmentState.MOUNTING) {
                    setState(FragmentState.INITIAL);
                }
            }
        }
    }

    @objid ("8ed62b30-07f4-11e2-b193-001ec947ccaf")
    @Override
    public final void unmount() {
        IRepository repository = getRepository();
        if (repository != null) {
            if (repository.isOpen()) {
                this.gproject.getSession().getRepositorySupport().disconnectRepository(repository);
            }
        
            repository.getErrorSupport().removeErrorListener(getRepositoryErrorSupport());
        }
        
        try {
            doUnmountPostProcess();
        } catch (IOException e) {
            this.gproject.getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(this, e));
        }
        
        setState(FragmentState.INITIAL);
    }

    /**
     * Default implementation.
     * <p>
     * Unregister itself and install a new fragment if the URI is different. Replaces its properties by the given ones.
     * 
     * @param aMonitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that
     * the operation cannot be cancelled.
     */
    @objid ("4f36bb41-af48-4929-bdde-6d867d92e941")
    @Override
    public void reconfigure(FragmentDescriptor fd, IModelioProgress aMonitor) {
        if (Objects.equals(getUri(), fd.getUri())) {
            // Same URI : unmount, update properties and remount
        
            boolean isMount = getState() != FragmentState.INITIAL;
        
            if (isMount) {
                unmount();
            }
        
            this.definitionScope = fd.getScope();
            this.properties = new GProperties(fd.getProperties());
            this.authConf.reconfigure(fd.getAuthDescriptor());
        
            if (isMount) {
                mount(aMonitor);
            }
        } else {
            // URI changed : forget this fragment and register a new one
            getProject().unregisterFragment(this);
        
            IProjectFragment fragment = Fragments.getFactory(fd).instantiate(fd);
        
            try (UndoableDeletion back = new UndoableDeletion()) {
                // move directories to backup place
                back.sendToTrash(getRuntimeDirectory(), getDataDirectory());
        
                // Delete from disk
                delete();
        
                getProject().registerFragment(fragment, aMonitor);
        
                back.allowDelete();
            } catch (RuntimeException | FragmentConflictException | IOException e) {
                // Report error
                getProject().getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(fragment, e));
        
                // try to rollback
                try {
                    getProject().registerFragment(this, aMonitor);
                } catch (FragmentConflictException e1) {
                    e1.addSuppressed(e);
                    setDown(e1);
                }
            }
        }
    }

    /**
     * Check the fragment is mount and throws an exception if not.
     * 
     * @throws java.lang.IllegalStateException if the fragment is not mount.
     */
    @objid ("242ea4bc-d0da-11e1-b069-001ec947ccaf")
    protected final void assertMount() throws IllegalStateException {
        synchronized (this.stateLock) {
            if (this.state == FragmentState.INITIAL || this.state == FragmentState.MOUNTING) {
                throw new IllegalStateException(MessageFormat.format("The ''{0}'' fragment is not mount.", getId()));
            }
        }
    }

    /**
     * Hook for sub classes called by {@link #delete()} in first place.
     * <p>
     * Does nothing by default. The fragment is still mounted and all files still exist but will be deleted on return. Sub classes may do more cleaning.
     * 
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     */
    @objid ("f573d3f7-6457-4167-bfea-27f196854503")
    protected void doDelete(IModelioProgress monitor) {
        // nothing by default
    }

    /**
     * Hook called by {@link #unmount()} after having disconnected the repository from the modeling session.
     * <p>
     * May be redefined by subclasses to clean resources.
     * 
     * @throws java.io.IOException if some errors should be reported. The unmount will not be cancelled.
     */
    @objid ("8ed62b38-07f4-11e2-b193-001ec947ccaf")
    protected void doUnmountPostProcess() throws IOException {
        // Does nothing by default
    }

    /**
     * Hook called by {@link #mount(GProject, IModelioProgress)} after having connected the repository to the session.
     * <p>
     * Does nothing by default. Subclasses may redefine it to populate the repository.
     * 
     * @param mon the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     * @throws java.io.IOException in case of failure.
     */
    @objid ("8ed62b3b-07f4-11e2-b193-001ec947ccaf")
    protected void doMountPostConnect(IModelioProgress mon) throws IOException {
        // Nothing by default
    }

    /**
     * Get the root elements of the fragment.
     * <p>
     * This method is called by {@link AbstractFragment#getRoots()} that ensures there is an open repository.
     * 
     * @return the root elements of the fragment.
     * @throws java.io.IOException in case of failure.
     */
    @objid ("b427669a-0baa-11e2-bed6-001ec947ccaf")
    protected abstract Collection<MObject> doGetRoots() throws IOException;

    /**
     * Initialize the access manager.
     * 
     * @return the access manager.
     */
    @objid ("a15a3399-38aa-11e2-a6db-001ec947ccaf")
    protected abstract IAccessManager doInitAccessManager();

    /**
     * Instantiate the {@link #getRepository() repository}.
     * <p>
     * This is a hook called by {@link #mount(GProject, IModelioProgress)}. The implementation must just instantiate the {@link IRepository} without opening it.
     * <p>
     * The implementation must <b>not</b> call <code>done()</code> on the given monitor and must leave a non negligible part of the progress unconsumed.
     * 
     * @param aMonitor the progress monitor to use for reporting progress to the user.
     * <p>
     * The implementation must <b>not</b> call <code>done()</code> the given monitor and must leave a non negligible part of the progress unconsumed.
     * <p>
     * Accepts <i>null</i>, indicating that no progress should be reported and that the operation cannot be cancelled.
     * @return the instantiated repository.
     * @throws java.io.IOException in case of failure.
     * @throws org.modelio.gproject.fragment.FragmentAuthenticationException in case of authentication failure
     */
    @objid ("8ed62b3e-07f4-11e2-b193-001ec947ccaf")
    protected abstract IRepository doMountInitRepository(IModelioProgress aMonitor) throws IOException, FragmentAuthenticationException;

    /**
     * Get the real authentication data to use for this fragment.
     * <p>
     * If the fragment uses inherited authentication data, returns the project authentication data.
     * 
     * @return the fragment authentication data.
     */
    @objid ("577094a7-243a-48fb-90d6-5dbb08813676")
    protected final IAuthData getAuthData() {
        return new AuthResolver(getProject()).resolve(this);
    }

    /**
     * Get the error handler that should be attached as listener to the repository when mount.
     * 
     * @return the error handler
     */
    @objid ("6a70dfdd-d66d-11e1-9f03-001ec947ccaf")
    protected final RepositoryListener getRepositoryErrorSupport() {
        return this.errSupport;
    }

    /**
     * Set the fragment in "down" state, with the cause.
     * <p>
     * Fires a {@link GProjectEventType#FRAGMENT_DOWN FRAGMENT_DOWN} {@link GProjectEvent event}.
     * 
     * @param error the cause of down state
     */
    @objid ("6a734221-d66d-11e1-9f03-001ec947ccaf")
    @Override
    public final void setDown(Throwable error) {
        assert error != null;
        
        final boolean wasDown;
        synchronized (this.stateLock) {
            wasDown = getDownError() != null;
            if (!wasDown) {
                this.downError = error;
                this.state = FragmentState.DOWN;
            }
        }
        
        if (!wasDown) {
            // Disconnect the repository
            // and set all loaded objects as shell.
            IRepository repository = getRepository();
            try {
                if (repository != null && repository.isOpen()) {
                    this.gproject.getSession().getRepositorySupport().disconnectRepository(repository);
                }
        
                doUnmountPostProcess();
            } catch (IOException | RuntimeException e) {
                error.addSuppressed(e);
            }
        
            // Change the states and fires a GProjectEvent
            getProject().getMonitorSupport().fireMonitors(GProjectEvent.FragmentDownEvent(this));
        }
    }

    /**
     * Check the required metamodels versions against current Modelio metamodel versions.
     * <p>
     * May also check the repository format version against the last implementation.
     * <p>
     * Returns normally if the fragment may be directly open else throws an exception. Implementations may use {@link #getRepository()} that returns the not yet open repository to check .
     * @param repository the repository to check
     * 
     * @throws java.io.IOException if the metamodel version does not allow the fragment to be open.
     * @throws org.modelio.gproject.fragment.FragmentMigrationNeededException if the fragment needs to be migrated before opening.
     */
    @objid ("86471d8d-0b51-4f7c-b692-423a07a2286f")
    protected void checkVersions() throws IOException, FragmentMigrationNeededException {
        MetamodelVersionDescriptor neededMm = getRequiredMetamodelDescriptor();
        MetamodelVersionDescriptor currentMm = getCurrentMmDescriptor();
        MmVersionComparator comparator = MmVersionComparator.withSource(neededMm).withTarget(currentMm);
        
        if (!comparator.isTargetCompatible(false)) {
            IFragmentMigrator migrator = getMigrator(currentMm);
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

    /**
     * Get the current Modelio metamodel version descriptor Get the last available metamodel version descriptor.
     * 
     * @return the last metamodel version.
     */
    @objid ("420b51fd-a248-4d22-8d40-f342f7a3d024")
    protected final MetamodelVersionDescriptor getCurrentMmDescriptor() {
        return VersionHelper.getDescriptors(getProjectMetamodel());
    }

    /**
     * Returns the name, type and state of the fragment.
     */
    @objid ("8a5d8144-811a-44f4-9a96-6903e6f96c22")
    @Override
    public String toString() {
        final String stateStr = getState() == FragmentState.DOWN ? getState() + ": " + getDownError()
                : getState().toString();
        return "'" + getId() + "' " + getType().toString() + " fragment (" + stateStr + ")";
    }

    /**
     * Convenience method to get the project metamodel.
     * 
     * @return the project metamodel.
     */
    @objid ("881fb52d-a77f-4193-b7d4-42ac2ff91099")
    protected final SmMetamodel getProjectMetamodel() {
        return getProject().getSession().getMetamodel();
    }

    @objid ("c6a3ba78-c756-4ef8-b425-3a6be6375c4a")
    @Override
    public void setProject(GProject project) {
        this.gproject = project;
    }

    /**
     * The default implementation returns null.
     */
    @objid ("afda8e70-6541-408a-ac66-c2bbd43a4105")
    @Override
    public IFragmentMigrator getMigrator(MetamodelVersionDescriptor targetMetamodel) throws IOException {
        return null;
    }

    @objid ("9afdc343-a9ce-472d-a618-7ee7338cfed7")
    @Override
    public void rename(String name, IModelioProgress aMonitor) throws IOException {
        if (!Objects.equals(getId(), name)) {
            // URI changed : forget this fragment
            getProject().unregisterFragment(this);
        
            Path oldDataDirectory = getDataDirectory();
            Path oldRuntimePath = getRuntimeDirectory();
        
            this.id = name;
            this.encodedDirName = FileUtils.encodeFileName(getId(), new StringBuilder()).toString();
        
            Files.move(oldDataDirectory, getDataDirectory());
            Files.move(oldRuntimePath, getRuntimeDirectory());
        
            try {
                // Register fragment again
                getProject().registerFragment(this, aMonitor);
            } catch (FragmentConflictException e) {
                // Report error
                getProject().getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(this, e));
        
                // try to rollback
                try {
                    getProject().registerFragment(this, aMonitor);
                } catch (FragmentConflictException e1) {
                    e1.addSuppressed(e);
                    setDown(e1);
                }
            }
        }
    }

    @objid ("4877c4b1-b98f-4cb4-9b20-bb5dcf2a1354")
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
    @objid ("6a70dfd0-d66d-11e1-9f03-001ec947ccaf")
    private class RepositoryListener implements IRepositoryErrorListener {
        @objid ("6a70dfd1-d66d-11e1-9f03-001ec947ccaf")
        public RepositoryListener() {
            // nothing
        }

        @objid ("6a70dfd3-d66d-11e1-9f03-001ec947ccaf")
        @Override
        public void onWarning(IRepository repository, Throwable e) {
            getProject().getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(AbstractFragment.this, e));
        }

        @objid ("6a70dfd8-d66d-11e1-9f03-001ec947ccaf")
        @Override
        public void onError(IRepository repository, Throwable e) {
            setDown(e);
        }

    }

    /**
     * Directories delete operation that will be undone on {@link #close()} unless {@link #allowDelete()} has been called before.
     * 
     * @author cma
     * @since Valkyrie
     */
    @objid ("8fea5f58-92fa-4893-b5cf-0855af3fe298")
    protected static class UndoableDeletion implements Closeable {
        @objid ("30dcbd01-119a-4f4d-b4dd-88c4280417f7")
        private boolean doDelete;

        @objid ("329d27b3-9460-468d-9fc2-f18115243a71")
        private List<Path> toDelete;

        @objid ("0a3be069-696c-407c-97ff-8d4b63cfbe77")
        private List<Path> backup;

        @objid ("d97534b4-0587-4fbf-a8f5-f40464af92b9")
        public UndoableDeletion() {
        }

        @objid ("9e03d6fb-edfe-4eea-b454-afb070ce2891")
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
        @objid ("a399ef1a-a324-40b1-94cd-3102eea819ac")
        public void allowDelete() {
            this.doDelete = true;
        }

        @objid ("d0676307-8143-4f25-af85-b3d1e4c070a3")
        @Override
        public void close() throws IOException {
            if (this.doDelete) {
                for (Path element : this.backup) {
                    try {
                        FileUtils.delete(element);
                    } catch (IOException e) {
                        // Log and continue
                        Log.warning("Cannot delete '%s': %s",element, FileUtils.getLocalizedMessage(e));
                        Log.trace(e);
                    }
                }
            } else {
                restore();
            }
        }

        @objid ("32bbb4c7-ad20-4c42-be85-9cfce6a35f74")
        private void restore() throws IOException {
            for (int i = 0; i < this.backup.size(); i++) {
                Path toRestore = this.backup.get(i);
                FileUtils.move(toRestore, this.toDelete.get(i));
            }
        }

    }

}
