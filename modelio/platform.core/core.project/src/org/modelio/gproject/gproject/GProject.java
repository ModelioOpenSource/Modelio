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

package org.modelio.gproject.gproject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.DescriptorServices;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.GAuthConf;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.data.project.ModuleDescriptor;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.data.project.ProjectDescriptorWriter;
import org.modelio.gproject.data.project.ProjectFileStructure;
import org.modelio.gproject.data.project.ProjectType;
import org.modelio.gproject.data.project.ResourceDescriptor;
import org.modelio.gproject.data.project.todo.TodoDescriptor;
import org.modelio.gproject.fragment.Fragments;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.lock.ProjectLock;
import org.modelio.gproject.gproject.migration.GProjectSpaceFormatMigrator1;
import org.modelio.gproject.model.impl.mtools.AuthTool;
import org.modelio.gproject.model.impl.mtools.ModelTool;
import org.modelio.gproject.module.EmptyModuleHandle;
import org.modelio.gproject.module.GModule;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.gproject.module.rtcache.EmptyModuleCache;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.collections.TopologicalSorter.CyclicDependencyException;
import org.modelio.vbasic.collections.TopologicalSorter;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.net.UriConnections;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.model.spi.IGMetamodelExtension;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.api.repository.IRepositorySupport;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.session.impl.permission.BasicAccessManager;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.version.ModelioVersion;
import org.modelio.vstore.jdbm.JdbmRepository;

/**
 * Represents a Modelio project.
 * 
 * <h3>About Modelio version:</h3>
 * The Modelio version is checked in {@link #validateModelioVersion()}
 * called on {@link #open(IModelioProgress)} and on {@link #reconfigureExpectedVersion(Version)}.
 * A <code>GProject</code> has no "modelioVersion" property : it is always the current version,
 * and the current version is written in the project descriptor on save.
 */
@objid ("b29f8656-8ed4-11e1-be7e-001ec947ccaf")
public class GProject {
    @objid ("a327a8f5-abf1-11e1-8392-001ec947ccaf")
    private String name;

    @objid ("f846db24-583a-4a26-9221-44e02acf74ec")
    private static final boolean staticInitialized = GProject.staticInitialize();

    /**
     * Concatenation of {@link #ownFragments own fragments}and the fragments declared by the modules.
     */
    @objid ("6fe5077e-edc9-11e1-912e-001ec947ccaf")
    private final List<IProjectFragment> allFragments = new CopyOnWriteArrayList<>();

    /**
     * Unmodifiable view of {@link #allFragments}. Avoids instantiating a new view for each {@link #getFragments()} call.
     */
    @objid ("6fe50781-edc9-11e1-912e-001ec947ccaf")
    private final List<IProjectFragment> allFragmentsView = Collections.unmodifiableList(this.allFragments);

    /**
     * All GProject instances. Used to {@link #getProject(MObject) find the project} of a MObject.
     */
    @objid ("db776f80-1775-11e2-928d-001ec947ccaf")
    private static final List<GProject> allProjects = new CopyOnWriteArrayList<>();

    @objid ("f5328bd1-9045-4cb4-b97c-b65927a5a7a3")
    private GAuthConf auth;

    @objid ("76be9590-a794-40c6-aa0a-6bf9a0831903")
    private Version expectedModelioVersion;

    @objid ("73a7db10-abd0-4fdf-b91a-758e83bc15ab")
    private IModuleRTCache moduleCache;

    @objid ("3a8981a1-0d65-11e2-8e4b-001ec947ccaf")
    private final List<GModule> modules = new CopyOnWriteArrayList<>();

    @objid ("617f8be2-08b6-11e2-b193-001ec947ccaf")
    private final GProjectMonitorSupport monitorSupport = new GProjectMonitorSupport();

    /**
     * Fragments added by registerFragment(...) method.
     */
    @objid ("8af97b41-8ed5-11e1-be7e-001ec947ccaf")
    private final List<IProjectFragment> ownFragments = new CopyOnWriteArrayList<>();

    @objid ("2471de70-6187-40ea-b764-250bdf40f6f5")
    private IGProjectEnv projectEnvironment;

    @objid ("1a7541ce-b47f-440b-8839-f3e6aa30f2be")
    private ProjectLock projectLock;

    @objid ("a308aa99-abf1-11e1-8392-001ec947ccaf")
    private GProperties properties;

    @objid ("c1811639-95da-11e1-ac83-001ec947ccaf")
    private ICoreSession session;

    @objid ("f8321d71-4874-4e80-8f2f-b52081487a7e")
    private TodoDescriptor todo;

    @objid ("9b5febdd-2ca1-4366-acce-ba53634f68c1")
    private final List<ResourceDescriptor> sharedResources = new ArrayList<>();

    /**
     * The file structure of the project space of the project.
     */
    @objid ("2a522c87-8323-41e0-aa85-71f628fd5abe")
    private ProjectFileStructure pfs;

    /**
     * Close the project and release the resources.
     */
    @objid ("c1811645-95da-11e1-ac83-001ec947ccaf")
    public void close() {
        Log.trace("Closing the '%s' project ...", getName());
        
        for (IProjectFragment f : getFragments()) {
            try {
                // Directly close the repository before unmounting to avoid unloading each object.
                IRepository rep = f.getRepository();
                if (rep != null) {
                    rep.close();
                }
                f.unmount();
            } catch (Exception e) {
                getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(f, e));
            }
        }
        
        for (GModule m : this.modules) {
            try {
                m.unmount();
            } catch (Exception e) {
                getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(e));
            }
        }
        
        if (this.session != null) {
            this.session.close();
            this.session = null;
        }
        
        // Release the lock
        if (this.projectLock != null) {
            try {
                this.projectLock.close();
                this.projectLock = null;
            } catch (IOException e) {
                getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(e));
            }
        }
        
        GProject.allProjects.remove(this);
        
        Log.trace("Closed the '%s' project.", getName());
    }

    /**
     * @return the project authentication configuration or <code>null</code>.
     */
    @objid ("95bb733c-e0ff-4619-9c2e-30a6c40c4269")
    public GAuthConf getAuthConfiguration() {
        return this.auth;
    }

    /**
     * Get the environment with which this project was instantiated.
     * 
     * @return the project environment
     * @since 3.6
     */
    @objid ("16701722-623b-4e78-8cf2-26750fa18cc4")
    public IGProjectEnv getEnvironment() {
        return this.projectEnvironment;
    }

    /**
     * Get the fragment owning the given model object.
     * <p>
     * Returns <code>null</code> if the model object is not managed by a fragment.
     * 
     * @param obj a model object.
     * @return its fragment or <code>null</code>.
     */
    @objid ("db7c342f-1775-11e2-928d-001ec947ccaf")
    public IProjectFragment getFragment(MObject obj) {
        if (this.session != null) {
            IRepository repo = this.session.getRepositorySupport().getRepository(obj);
            for (IProjectFragment f : this.allFragments) {
                if (f.getRepository() == repo) {
                    return f;
                }
            }
        }
        return null;
    }

    /**
     * Get the list of all model fragments.
     * <p>
     * The returned list is:
     * <ul>
     * <li>not modifiable
     * <li>reflect changes when a fragment registered or removed.
     * <li>thread safe because based on {@link CopyOnWriteArrayList}.
     * </ul>
     * 
     * @return all model fragments.
     */
    @objid ("c1811647-95da-11e1-ac83-001ec947ccaf")
    public List<IProjectFragment> getFragments() {
        return this.allFragmentsView;
    }

    /**
     * Get the metamodel extensions loaded in the project.
     * 
     * @return the metamodel extensions.
     * @since 3.6
     */
    @objid ("f8c577de-7a8d-44e9-8665-6406427d6c0c")
    public Collection<IGMetamodelExtension> getMetamodelExtensions() {
        // note : extensions might be added later so may have to be stored elsewhere than in the environment.
        return this.projectEnvironment.getDefaultMetamodelExtensions();
    }

    /**
     * @return the modules cache
     */
    @objid ("f0342dfb-2708-4513-ba51-c784de7e7d8b")
    public IModuleRTCache getModuleCache() {
        return this.moduleCache;
    }

    /**
     * Get the list of all GModules installed in this project.
     * <p>
     * The returned list is:
     * <ul>
     * <li>not modifiable
     * <li>reflect changes when a fragment registered or removed.
     * <li>thread safe because based on {@link CopyOnWriteArrayList}.
     * </ul>
     * 
     * @return a GModule list.
     */
    @objid ("aa7d67e4-ec75-11e1-912e-001ec947ccaf")
    public List<GModule> getModules() {
        return Collections.unmodifiableList(this.modules);
    }

    /**
     * Get project monitoring support.
     * <p>
     * Allows to add, remove and fires GProject monitors.
     * 
     * @return the monitor support.
     */
    @objid ("617f8be3-08b6-11e2-b193-001ec947ccaf")
    public GProjectMonitorSupport getMonitorSupport() {
        return this.monitorSupport;
    }

    /**
     * Get the project's name.
     * 
     * @return the project's name.
     */
    @objid ("13aa21f2-9a85-11e1-ac83-001ec947ccaf")
    public String getName() {
        return this.name;
    }

    /**
     * Get the list of the project fragments registered with {@link #registerFragment(IProjectFragment, IModelioProgress)} .
     * <p>
     * Fragments provided by modules are excluded from the list.
     * <p>
     * The returned list is:
     * <ul>
     * <li>not modifiable
     * <li>reflect changes when a fragment registered or removed.
     * <li>thread safe because based on {@link CopyOnWriteArrayList}.
     * </ul>
     * 
     * @return registered project fragments.
     */
    @objid ("da6bd643-0e3b-11e2-8e4b-001ec947ccaf")
    public List<IProjectFragment> getOwnFragments() {
        return Collections.unmodifiableList(this.ownFragments);
    }

    /**
     * Get the GProject owning the given model object.
     * <p>
     * Returns <code>null</code> if the model object is not managed by a GProject.
     * 
     * @param obj a model object.
     * @return its GProject or <code>null</code>.
     */
    @objid ("db79d1d9-1775-11e2-928d-001ec947ccaf")
    public static GProject getProject(MObject obj) {
        CoreSession sess = CoreSession.getSession(obj);
        return GProject.getProject(sess);
    }

    /**
     * Get the GProject owning the given core session.
     * <p>
     * Returns <code>null</code> if the core session is not managed by a GProject.
     * 
     * @param session a core session.
     * @return its GProject or <code>null</code>.
     */
    @objid ("6769bb90-0774-4f1b-9f5e-b6f3658f451a")
    public static GProject getProject(ICoreSession session) {
        for (GProject p : GProject.allProjects) {
            if (p.session == session) {
                return p;
            }
        }
        return null;
    }

    /**
     * Get the properties stored in the project.
     * 
     * @return the current set of properties.
     */
    @objid ("f480100d-aa5a-11e1-8392-001ec947ccaf")
    public GProperties getProperties() {
        return this.properties;
    }

    /**
     * Get the project remote location.
     * <p>
     * Returns <code>null</code> for local projects.
     * 
     * @return the project remote location.
     */
    @objid ("3824d956-0c6e-11e2-bed6-001ec947ccaf")
    @SuppressWarnings ("static-method")
    public String getRemoteLocation() {
        return null;
    }

    /**
     * Get the core session corresponding to this project.
     * 
     * @return a CoreSession. Might be <code>null</code> if the project is closed.
     */
    @objid ("00377288-e2c5-1fd5-b969-001ec947cd2a")
    public ICoreSession getSession() {
        return this.session;
    }

    /**
     * @return the actions to make on project open.
     */
    @objid ("1f563352-67d9-4f15-b187-1dde9b064043")
    public TodoDescriptor getTodo() {
        return this.todo;
    }

    /**
     * Get the project type.
     * <p>
     * This method should be redefined by subclasses.
     * 
     * @return the project type.
     */
    @objid ("82724f7b-0be2-11e2-bed6-001ec947ccaf")
    @SuppressWarnings ("static-method")
    public ProjectType getType() {
        return ProjectType.LOCAL;
    }

    /**
     * Add a new module in the project.
     * <p>
     * If the project is currently opened, the MDA fragment of the module is mounted in the project.
     * 
     * @param moduleHandle the handle of the module to deploy
     * @param originalArchiveUri the original URI of .jmdac archive to deploy
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call
     * <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     * @return the instantiated module
     * @throws java.io.IOException in case of failure.
     */
    @objid ("ad60fb90-af3f-4ba1-aa7c-f33baf8fe2b0")
    public GModule installModule(IModuleHandle moduleHandle, URI originalArchiveUri, IModelioProgress monitor) throws IOException {
        Path localArchiveDir = this.pfs.getModuleBackupDir(moduleHandle.getName());
        Path localArchivePath = this.pfs.getModuleBackupArchivePath(moduleHandle.getName(), moduleHandle.getVersion());
        
        // Clean the module data directory
        FileUtils.delete(localArchiveDir);
        Files.createDirectories(localArchiveDir);
        
        // Copy archive in data directory
        if (moduleHandle.getArchive() != null) {
            Files.copy(moduleHandle.getArchive(), localArchivePath, StandardCopyOption.REPLACE_EXISTING);
        }
        
        // Instantiate module
        GModule module = new GModule(this, originalArchiveUri, moduleHandle, DefinitionScope.LOCAL, new GProperties(), true);
        
        if (isOpen()) {
            // Add the module only once it is successfully mount.
            this.modules.add(module);
            IProjectFragment modelFragment = module.getModelFragment();
            if (modelFragment != null) {
                this.allFragments.add(modelFragment);
                modelFragment.mount(monitor);
        
                getMonitorSupport().fireMonitors(GProjectEvent.fragmentAdded(modelFragment));
            } else {
                String msg = MessageFormat.format("{0} v{1} has no model fragment.", module.getName(), module.getVersion());
                getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(new Exception(msg)));
            }
        } else {
            this.modules.add(module);
        }
        return module;
    }

    /**
     * Indicates whether or not the project is opened.
     * 
     * @return <code>true</code> if there is a core session.
     */
    @objid ("6fe769dc-edc9-11e1-912e-001ec947ccaf")
    public boolean isOpen() {
        return this.session != null;
    }

    /**
     * Open the project.
     * <p>
     * {@link #load(ProjectDescriptor, IAuthData, IGProjectEnv, IModelioProgress)} must have been called before.
     * 
     * @param aProgress a progress monitor
     * @throws java.io.IOException in case of I/O error preventing the project from being open.
     * @throws java.nio.file.FileSystemException in case of file system I/O error preventing the project from being open.
     */
    @objid ("f7e310ea-0f09-11e2-bd8d-001ec947ccaf")
    public void open(IModelioProgress aProgress) throws FileSystemException, IOException {
        // check closed
        checkNotOpen();
        
        // check Modelio version validity
        validateModelioVersion();
        
        SubProgress mon = SubProgress.convert(aProgress, 372);
        
        boolean ok = false;
        try {
            this.session = new CoreSession();
            mountMetamodel(mon.newChild(2));
            mountDefaultRepositories(mon.newChild(20));
            mountModules(mon.newChild(200));
            mountFragments(mon.newChild(50));
            ok = true;
        } finally {
            if (!ok) {
                if (this.session != null) {
                    this.session.close();
                    this.session = null;
                }
            }
        }
    }

    /**
     * Reconfigure the project properties with the given new descriptor.
     * <p>
     * The default implementation replaces the project properties.<br/>
     * This method may be redefined to have another behavior.
     * 
     * @param newDesc the new project description
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call
     * <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     */
    @objid ("bf898e03-a499-4988-bd96-985caaaed03b")
    public void reconfigureProperties(GProperties newDesc, IModelioProgress monitor) {
        this.properties = new GProperties(newDesc);
    }

    /**
     * Register a fragment in the project.
     * <p>
     * The method mounts the fragment but it does not create any data for the fragment.
     * 
     * @param fragment the project fragment to register.
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call
     * <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     * @throws org.modelio.gproject.gproject.FragmentConflictException if a fragment with same identifier or URI is already registered
     */
    @objid ("d1110783-9849-11e1-ac83-001ec947ccaf")
    public void registerFragment(final IProjectFragment fragment, IModelioProgress monitor) throws FragmentConflictException {
        Objects.requireNonNull(fragment);
        
        checkFragmentUnique(this.allFragments, fragment);
        
        fragment.setProject(this);
        
        this.ownFragments.add(fragment);
        this.allFragments.add(fragment);
        
        if (isOpen()) {
            fragment.mount(monitor);
            this.monitorSupport.fireMonitors(GProjectEvent.fragmentAdded(fragment));
        }
    }

    /**
     * Remove module from project. If the project is currently opened, the MDA fragment of the module is dismounted from the
     * project.
     * 
     * @param module the module to remove from the project.
     */
    @objid ("003a5f66-60dd-1060-84ef-001ec947cd2a")
    public void removeModule(GModule module) {
        if (isOpen()) {
            // Remove the module and its fragment
            this.modules.remove(module);
            IProjectFragment modelFragment = module.getModelFragment();
            if (modelFragment != null) {
                modelFragment.unmount();
                try {
                    // Delete the fragment itself
                    modelFragment.delete();
                } catch (IOException e) {
                    Log.warning("Unable to delete '%s' module fragment files: %s ", modelFragment.getId(), FileUtils.getLocalizedMessage(e));
                }
                this.allFragments.remove(modelFragment);
                this.monitorSupport.fireMonitors(GProjectEvent.fragmentRemoved(modelFragment));
            }
        } else {
            // Remove the module only
            this.modules.remove(module);
        }
        
        // Remove local archive and module work path
        try {
            Path localArchiveDir = this.pfs.getModuleBackupDir(module.getName());
            if (localArchiveDir != null) {
                FileUtils.delete(localArchiveDir);
            }
        } catch (IOException e) {
            this.monitorSupport.fireMonitors(GProjectEvent.buildWarning(e));
        }
    }

    /**
     * Save the model and the project description.
     * 
     * @param progress a Modelio progress monitor
     * @throws java.io.IOException if a repository failed to save.
     */
    @objid ("c1811643-95da-11e1-ac83-001ec947ccaf")
    public void save(IModelioProgress progress) throws IOException {
        if (this.session != null) {
            this.session.save(progress);
        }
        
        saveProjectDescription();
    }

    /**
     * Unregisters a fragment in the project.
     * <p>
     * The method unmount the fragment but it does not <i>'delete'</i> the fragment physical data.
     * 
     * @param fragment the project fragment to register.
     */
    @objid ("00544b74-a58b-1044-a30e-001ec947cd2a")
    public void unregisterFragment(IProjectFragment fragment) {
        if (isOpen()) {
            fragment.unmount();
        }
        this.ownFragments.remove(fragment);
        this.allFragments.remove(fragment);
        
        this.monitorSupport.fireMonitors(GProjectEvent.fragmentRemoved(fragment));
    }

    /**
     * Get a module handle from a descriptor.
     * 
     * @param mon the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call
     * <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     * @param md the descriptor
     * @return the module handle
     * @throws java.nio.file.FileSystemException in case of file system error getting the module archive
     * @throws java.io.IOException in case of failure getting the module archive
     */
    @objid ("538790be-d9c8-4eeb-b2ba-c9c36f4adbaf")
    IModuleHandle getModuleHandle(IModelioProgress mon, final ModuleDescriptor md) throws FileSystemException, IOException {
        final Path backupPath = this.pfs.getModuleBackupArchivePath(md.getName(), md.getVersion());
        
        // First look in module cache
        IModuleHandle moduleHandle = this.moduleCache.findModule(md.getName(), md.getVersion().toString("V.R.C"), mon);
        
        if (moduleHandle == null) {
            // try to install from the local backup
            moduleHandle = this.moduleCache.installModuleArchive(backupPath, mon);
        }
        
        // Last resort: restore from initial configuration
        if (moduleHandle == null && md.getArchiveLocation() != null) {
            // try to get the original and copy it to local
            IAuthData authData = new AuthResolver(this).resolve(md);
            try {
                copyJmdacToModuleDir(md.getArchiveLocation(), authData, backupPath);
                if (Files.isRegularFile(backupPath)) {
                    moduleHandle = this.moduleCache.installModuleArchive(backupPath, mon);
                }
            } catch (FileNotFoundException | NoSuchFileException e) {
                // log and ignore
                Log.warning("'%s' module '%s' URI is invalid: %s", md.getName(), md.getArchiveLocation(), FileUtils.getLocalizedMessage(e));
            }
        }
        
        if (moduleHandle == null) {
            moduleHandle = this.moduleCache.findModule(md.getName(), md.getVersion().toString("V.R.C"), mon);
        }
        
        if (moduleHandle == null) {
            moduleHandle = new EmptyModuleHandle(md.getName(), md.getVersion());
        }
        return moduleHandle;
    }

    /**
     * Set the project authentication configuration.
     * <p>
     * Does nothing more. The project should be closed then open again after calling this.
     * 
     * @param newAuth the new project authentication configuration.
     */
    @objid ("e2de2d72-a859-4bcd-b899-126529a77684")
    void setAuthConfiguration(GAuthConf newAuth) {
        this.auth = newAuth;
    }

    @objid ("7eafc164-53cd-41dd-877e-b0eb71a4e6be")
    void setName(String name2) {
        this.name = name2;
    }

    /**
     * Instantiate a project.
     * <p>
     * To be called by the factory and sub classes only.
     */
    @objid ("c181163b-95da-11e1-ac83-001ec947ccaf")
    protected GProject() {
        GProject.allProjects.add(this);
    }

    /**
     * Check the project is not already opened.
     * 
     * @throws java.lang.IllegalStateException if the project is already opened.
     */
    @objid ("5cd9af91-86da-43a9-baab-b538979631dc")
    protected final void checkNotOpen() throws IllegalStateException {
        if (this.session != null) {
            throw new IllegalStateException("'" + this.name + "' project already open.");
        }
    }

    /**
     * Copy the URI content to a given Path .
     * <p>
     * <li>If the URI represents a file directory, uses the represented Path.
     * <li>If the URI represents a relative path, resolve it against the project path.
     * <li>In the other case try to open an URL connection to copy the file to the given file path.
     * 
     * @param anUri the URI to copy.
     * @param copyTo the path to copy the URI content to.
     * @throws java.io.IOException if the URI couldn't be resolved.
     */
    @objid ("684350a6-0d5c-11e2-9d8a-001ec947ccaf")
    protected boolean copyJmdacToModuleDir(URI anUri, IAuthData authData, Path copyTo) throws IOException {
        // First try to convert URI to Path
        Path fsPath = null;
        if (anUri.getScheme() != null) {
            try {
                fsPath = Paths.get(anUri);
            } catch (FileSystemNotFoundException | IllegalArgumentException e) {
                // continue
            }
        
            if (fsPath != null) {
                if (Files.isRegularFile(fsPath)) {
                    // Copy to module directory
                    Files.copy(fsPath, copyTo, StandardCopyOption.REPLACE_EXISTING);
                    return true;
                } else if (Files.isDirectory(fsPath)) {
                    throw new NoSuchFileException(fsPath.toString(), null, "It is a directory.");
                }
            }
        }
        
        // Maybe the URI is a relative path
        if (anUri.getScheme() == null || anUri.getScheme().equals("file")) {
            try {
                fsPath = this.pfs.getProjectDataPath().resolve(anUri.getPath());
            } catch (FileSystemNotFoundException | IllegalArgumentException e) {
                // continue
            }
        
            if (fsPath != null) {
                if (Files.isRegularFile(fsPath)) {
                    // Copy to module directory
                    Files.copy(fsPath, copyTo, StandardCopyOption.REPLACE_EXISTING);
                    return true;
                } else if (Files.isDirectory(fsPath)) {
                    throw new NoSuchFileException(fsPath.toString(), null, "It is a directory.");
                }
            }
        }
        
        // Try to open an URL connection & copy into a temp file.
        try {
            Files.createDirectories(copyTo.getParent());
            try (InputStream in = UriConnections.openInputStream(anUri, authData)) {
                Files.copy(in, copyTo, StandardCopyOption.REPLACE_EXISTING);
            }
            return true;
        } catch (MalformedURLException e) {
            // URI not supported
            throw new IOException(e);
        } catch (IndexOutOfBoundsException e) {
            throw new IOException(e);
        }
    }

    /**
     * Get the Modelio version required to use this project
     * 
     * @return the required Modelio version.
     */
    @objid ("dbb474f3-d531-49f6-be12-e1b62fbfa559")
    public final Version getExpectedModelioVersion() {
        return this.expectedModelioVersion;
    }

    /**
     * Load the project from a project descriptor.
     * <p>
     * To be called by the project factory only.
     * <p>
     * This method may be redefined in sub classes. In this case <i>super.load(...)</i> must be called.
     * 
     * @param projectDescriptor a project descriptor.
     * @param authData optional authentication data. If non <code>null</code> it overrides the authentication descriptor.
     * @param configuration the modules catalog.
     * @param aProgress a progress monitor
     * @throws java.io.IOException in case of I/O error preventing the project from being instantiated
     */
    @objid ("f7e310e4-0f09-11e2-bd8d-001ec947ccaf")
    protected void load(ProjectDescriptor projectDescriptor, IAuthData authData, IGProjectEnv configuration, IModelioProgress aProgress) throws IOException {
        SubProgress mon = SubProgress.convert(aProgress, 2);
        
        // Migrate if needed
        ProjectDescriptor descToLoad = migrateProjectSpace(projectDescriptor, configuration, mon.newChildSupplier(1));
        
        // Initialize fields
        this.name = descToLoad.getName();
        
        // this.projectPath = descToLoad.getPath();
        
        this.pfs = new ProjectFileStructure(descToLoad.getProjectFileStructure().getProjectPath());
        
        this.properties = descToLoad.getProperties();
        this.todo = descToLoad.getTodo();
        this.expectedModelioVersion = descToLoad.getModelioVersion();
        this.projectEnvironment = configuration;
        
        if (authData != null) {
            this.auth = new GAuthConf(authData, DefinitionScope.LOCAL);
        } else {
            this.auth = GAuthConf.from(descToLoad.getAuthDescriptor());
        }
        
        if (configuration.getModulesCache() == null) {
            this.moduleCache = EmptyModuleCache.getInstance();
        } else {
            this.moduleCache = configuration.getModulesCache();
        }
        
        // lock the project
        lockProject();
        
        // Resolve relative URIs against project path
        DescriptorServices.resolveUris(descToLoad, this.pfs.getProjectPath().toUri());
        
        // Instantiate fragments and GModules from the descriptor.
        loadDescriptor(descToLoad, mon);
    }

    /**
     * Tells whether the project configuration is different than the current configuration.
     * <p>
     * May be redefined if simply comparing descriptors is not enough or descriptors are not directly comparable.
     * 
     * @param serverDesc a server project configuration
     * @return <i>true</i> if the project configuration is different.
     */
    @objid ("9fe74f75-ee6a-4de6-b76b-625124f78bce")
    protected boolean needsReconfiguration(ProjectDescriptor serverDesc) {
        ProjectDescriptor currentDesc = new ProjectWriter(this).writeProject();
        DescriptorServices.removeLocalPart(currentDesc);
        return !serverDesc.equals(currentDesc);
    }

    /**
     * Reconfigure the expected Modelio version.
     * <p>
     * To be called by project configurer only.
     * 
     * @param expected the new expected version.
     * @throws java.io.IOException if the new version is not supported.
     */
    @objid ("06c780a0-15a7-43c7-8443-fc235b955a2f")
    protected final void reconfigureExpectedVersion(Version expected) throws IOException {
        if (!Objects.equals(this.expectedModelioVersion, expected)) {
            Version oldV = this.expectedModelioVersion;
            try {
                this.expectedModelioVersion = expected;
                validateModelioVersion();
            } catch (IOException e) {
                // Rollback
                this.expectedModelioVersion = oldV;
        
                throw e;
            }
        }
    }

    /**
     * Save the project description in its "project.conf" file.
     * 
     * @throws java.io.IOException in case of failure.
     */
    @objid ("cb0de6e2-4d1c-420f-a746-21b4cf2bc5a4")
    protected void saveProjectDescription() throws IOException {
        ProjectDescriptor desc = new ProjectWriter(this).writeProject();
        new ProjectDescriptorWriter().write(desc);
    }

    /**
     * Updates the project remote location.
     * <p>
     * <ul>
     * <li>To be called only by {@link GProjectConfigurer}.
     * <li>To be redefined by remote project implementations.
     * </ul>
     * 
     * @param remoteLocation the new project remote location.
     * @throws java.net.URISyntaxException if the given location is invalid
     */
    @objid ("4e103e2b-28fb-485e-acb7-66496103ff82")
    protected void setRemoteLocation(String remoteLocation) throws URISyntaxException {
        // nothing to do for a local project.
    }

    /**
     * Check the expected Modelio version with the current Modelio version.
     * <p>
     * On local project checks the Modelio version is same or newer than expected one.
     * If Modelio is newer its version will be written in the project descriptor at next save.
     * <p>
     * This method may be (and is) redefined to add other constraints on remote projects.
     * 
     * @throws java.io.IOException if the Modelio version does not match.
     */
    @objid ("94059fba-c002-4dd2-9df8-e5da3d13242f")
    protected void validateModelioVersion() throws IOException {
        if (this.expectedModelioVersion != null) {
            if (this.expectedModelioVersion.isNewerThan(ModelioVersion.VERSION)) {
                throw new IOException(CoreProject.I18N.getMessage("GProject.modelioTooOld",
                        getName(), this.expectedModelioVersion, ModelioVersion.VERSION));
            }
        }
        
        // If validated writes the new version
        this.expectedModelioVersion = ModelioVersion.VERSION;
    }

    @objid ("1de1a27e-07a4-46fd-ba78-39c6e7a36098")
    private void checkFragmentUnique(List<IProjectFragment> existing, final IProjectFragment fragment) throws FragmentConflictException {
        for (IProjectFragment f : existing) {
            if (f.getId().equals(fragment.getId())) {
                throw new FragmentConflictException(f, fragment, this);
            }
        }
    }

    /**
     * Setup the project from the descriptor.
     * <p>
     * Add the described fragments and the modules without mounting them.
     * 
     * @param projectDescriptor a project descriptor
     * @param aProgress a progress monitor.
     */
    @objid ("684350a0-0d5c-11e2-9d8a-001ec947ccaf")
    private void loadDescriptor(final ProjectDescriptor projectDescriptor, IModelioProgress aProgress) {
        SubProgress mon = SubProgress.convert(aProgress, projectDescriptor.getFragments().size()
                + projectDescriptor.getModules().size());
        
        // Read fragments
        for (FragmentDescriptor fd : projectDescriptor.getFragments()) {
            if (fd.getType() != null) {
                IProjectFragment fragment = Fragments.getFactory(fd).instantiate(fd);
                assert fragment != null;
        
                fragment.setProject(this);
        
                this.ownFragments.add(fragment);
                this.allFragments.add(fragment);
            }
            mon.worked(1);
        }
        
        // Read modules
        for (final ModuleDescriptor md : projectDescriptor.getModules()) {
            try {
                if (md.isValid()) {
                    loadModuleDescriptor(mon.newChild(1), md);
                }
            } catch (IOException e) {
                getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(e));
            }
        }
        
        // Read resources
        this.sharedResources.addAll(projectDescriptor.getSharedResources());
    }

    /**
     * Load the module defined by the given descriptor.
     * <p>
     * 
     * @param mon a progress monitor
     * @param md the module descriptor
     * @return the installed module.
     * @throws java.nio.file.FileSystemException on file system error.
     * @throws java.io.IOException on failure.
     */
    @objid ("72d39d62-cc13-465d-acbf-ed4aeb20fa6b")
    private final GModule loadModuleDescriptor(IModelioProgress mon, final ModuleDescriptor md) throws FileSystemException, IOException {
        final IModuleHandle moduleHandle = getModuleHandle(mon, md);
        final GModule module = new GModule(this, md.getArchiveLocation(), moduleHandle, md.getScope(), md.getParameters(),
                md.isActivated());
        module.setAuthData(GAuthConf.from(md.getAuthDescriptor()));
        this.modules.add(module);
        return module;
    }

    @objid ("f05cb080-d19b-4758-8039-82bebf73b885")
    private void lockProject() throws IOException {
        Files.createDirectories(this.pfs.getProjectRuntimePath());
        
        this.projectLock = ProjectLock.get(this.pfs.getProjectRuntimePath(), this.name);
        this.projectLock.lock();
    }

    /**
     * Open the default repository. Create it if missing or not readable.
     * 
     * @param monitor a progress monitor
     * @throws java.io.IOException in case of fatal I/O failure.
     */
    @objid ("707deddd-440c-4a01-92d1-1fbeb87a7dd2")
    private void mountDefaultRepositories(SubProgress monitor) throws IOException {
        monitor.subTask(CoreProject.I18N.getMessage("GProject.mountingDefaultRepositories"));
        
        Path nsUseRepoPath = this.pfs.getNsUseRepositoryPath();
        JdbmRepository nsUseRepo = null;
        
        IRepositorySupport repositorySupport = this.session.getRepositorySupport();
        try {
            monitor.setWorkRemaining(15);
            nsUseRepo = new JdbmRepository(nsUseRepoPath.toFile());
            repositorySupport.connectRepository(nsUseRepo, IRepositorySupport.REPOSITORY_KEY_LOCAL, new BasicAccessManager(),
                    monitor.newChild(10));
        } catch (final AccessDeniedException e) {
            // don't delete the model in case of locking problems
            throw e;
        } catch (final IOException e) {
            if (e.getCause() instanceof OverlappingFileLockException) {
                // don't delete the model in case of locking problems
                throw e;
            }
        
            // Try to recover by deleting the local repository and create a new empty one.
            try {
                // Close and delete the repository
                nsUseRepo.close();
                FileUtils.delete(nsUseRepoPath);
        
                // Create a new one
                nsUseRepo = new JdbmRepository(nsUseRepoPath.toFile());
                repositorySupport.connectRepository(nsUseRepo, IRepositorySupport.REPOSITORY_KEY_LOCAL, new BasicAccessManager(),
                        monitor.newChild(5));
        
                // Report the problem
                getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(new IOException(
                        CoreProject.I18N.getMessage("GProject.localRepositoryRecreated", FileUtils.getLocalizedMessage(e)), e)));
            } catch (IOException | RuntimeException e2) {
                // Recover failed: Add the new exception as suppressed and rethrow the initial one.
                e.addSuppressed(e2);
                throw e;
            }
        }
    }

    @objid ("c1811640-95da-11e1-ac83-001ec947ccaf")
    private void mountFragments(IModelioProgress aMonitor) {
        ArrayList<IProjectFragment> mounted = new ArrayList<>(this.allFragments.size());
        
        for (IProjectFragment fragment : this.allFragments) {
            try {
                checkFragmentUnique(mounted, fragment);
        
                fragment.mount(aMonitor);
                mounted.add(fragment);
            } catch (FragmentConflictException e) {
                fragment.setDown(e);
            }
        }
    }

    @objid ("eb3f9579-cf26-4307-a682-828aee8db4f6")
    private void mountMetamodel(SubProgress monitor) {
        ICoreSession coreSession = getSession();
        SmMetamodel mm = coreSession.getMetamodel();
        Collection<IGMetamodelExtension> metamodelExtensions = this.projectEnvironment.getDefaultMetamodelExtensions();
        
        monitor.subTask(CoreProject.I18N.getMessage("GProject.mountingDefaultMetamodel", metamodelExtensions.size()));
        monitor.setWorkRemaining(1 + metamodelExtensions.size() + 1);
        
        // Sort metamodel extensions according to their fragment's dependencies
        List<IGMetamodelExtension> sortedmm;
        try {
            GMetamodelExtensionTopologicalSorter<IGMetamodelExtension> sorter = new GMetamodelExtensionTopologicalSorter<>(metamodelExtensions);
            sortedmm = sorter.sort();
        } catch (CyclicDependencyException e) {
            throw new IllegalStateException(e.getLocalizedMessage(), e);
        }
        monitor.worked(1);
        
        // Mount each metamodel extension
        for (IGMetamodelExtension mmExt : sortedmm) {
            ISmMetamodelFragment mmF = mmExt.getMmFragment();
            monitor.subTask(CoreProject.I18N.getMessage("GProject.mountingMetamodelFragment", mmF.getName()));
        
            // Add metamodel fragments to the metamodel
            mm.addMetamodelFragment(mmF);
        
            // Register metamodel extensions
            mmExt.register(coreSession);
        
            monitor.worked(1);
        }
        monitor.done();
    }

    @objid ("3d98b924-f370-11e1-9173-001ec947ccaf")
    private void mountModules(IModelioProgress progress) {
        progress.subTask(CoreProject.I18N.getMessage("GProject.mountingModules"));
        SubProgress m = SubProgress.convert(progress, this.modules.size() * 10);
        for (GModule module : this.modules) {
            IProjectFragment modelFragment = module.getModelFragment();
            if (modelFragment != null) {
                modelFragment.setProject(this);
                this.allFragments.add(modelFragment);
            } else {
                String msg = module.getName() + " " + module.getVersion() + " has no model fragment.";
                getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(new Exception(msg)));
            }
            m.worked(10);
        }
    }

    /**
     * Static initializer to initialize {@link MTools}.
     * 
     * @return true
     */
    @objid ("8a0f8b12-aec6-4d95-8441-f9962c0aa0b7")
    private static boolean staticInitialize() {
        MTools.initializeMTools(new ModelTool(), new AuthTool());
        return true;
    }

    @objid ("52c5c666-11aa-4c14-9213-2622400bd447")
    public List<ResourceDescriptor> getSharedResources() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.sharedResources;
    }

    /**
     * Migrate the project space and the project descriptor if needed.
     * 
     * @param projectDescriptor the initial project descriptor.
     * @param configuration the project configuration
     * @param newChildSupplier a progress monitor supplier that will be used only if a migration is needed.
     * @return the migrated project descriptor, may be the initial one.
     * @throws java.io.IOException on failure
     */
    @objid ("10380823-a56f-40c1-a507-1a4ed104a976")
    private ProjectDescriptor migrateProjectSpace(ProjectDescriptor projectDescriptor, IGProjectEnv configuration, Supplier<SubProgress> newChildSupplier) throws IOException {
        if (projectDescriptor.getProjectSpaceVersion() < 1) {
            GProjectSpaceFormatMigrator1 mig = new GProjectSpaceFormatMigrator1(projectDescriptor);
            return mig.run(newChildSupplier.get());
        
        }
        return projectDescriptor;
    }

    @objid ("d3de3aad-ac0e-4c9e-b8da-caeb87da6855")
    public ProjectFileStructure getProjectFileStructure() {
        return this.pfs;
    }

    @objid ("3d6ebb94-a7a8-454a-ad1a-cf1f922e16c8")
    private final class GMetamodelExtensionTopologicalSorter<T extends IGMetamodelExtension> extends TopologicalSorter<T> {
        @objid ("218cff58-6ad8-4cc8-a3f8-058760e999a6")
        private Collection<T> extensions;

        @objid ("bae8f06b-e536-4a22-8e49-8dd971f2e102")
        public GMetamodelExtensionTopologicalSorter(Collection<T> extensions) {
            this.extensions = extensions;
        }

        @objid ("6771f085-d336-45eb-9cd8-834a16ace472")
        @Override
        public Collection<T> getNodes() {
            return this.extensions;
        }

        @objid ("2fcf57e1-ad3f-4dcf-90c3-b059b9eb67dd")
        @Override
        public Collection<T> getAdjacent(T node) {
            Collection<VersionedItem<MMetamodelFragment>> neededFragments = node.getMmFragment().getNeededFragments();
            if (neededFragments.isEmpty()) {
                return Collections.emptyList();
            } else {
                Collection<T> ret = new ArrayList<>(neededFragments.size());
                for (VersionedItem<MMetamodelFragment> needed : neededFragments) {
                    for (T f : this.extensions) {
                        if (f.getMmFragment().getName().equals(needed.getName())) {
                            // skip version checking
                            ret.add(f);
                        }
                    }
                }
            
                return ret;
            }
        }

    }

}
