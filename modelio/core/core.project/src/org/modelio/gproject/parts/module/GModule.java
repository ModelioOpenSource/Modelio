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
package org.modelio.gproject.parts.module;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGPart.GPartException;
import org.modelio.gproject.core.IGPartState.GPartStateEnum;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.GProjectPartDescriptor;
import org.modelio.gproject.data.project.GProjectPartDescriptor.GProjectPartType;
import org.modelio.gproject.data.project.ProjectFileStructure;
import org.modelio.gproject.data.project.auth.AuthDescriptor;
import org.modelio.gproject.module.EmptyModuleHandle;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.gproject.monitor.GProjectEvent;
import org.modelio.gproject.monitor.GProjectEventType;
import org.modelio.gproject.parts.AbstractGPart;
import org.modelio.gproject.parts.GPartFactory;
import org.modelio.gproject.parts.GPartState;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.net.UriConnections;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Represent a Module in a project, managing a {@link IModuleHandle}.
 */
@objid ("30163fea-1701-4252-9e61-2b197cdc2e7b")
public class GModule extends AbstractGPart {
    @objid ("436ab43f-6aae-4752-9575-f20fc4660e7f")
    private IModuleHandle moduleHandle = null;

    @objid ("0e14b159-d15a-4979-bdfc-170cec1f9799")
    private IGModelFragment mdaFragment = null;

    @objid ("3b7ae0eb-3150-4df8-b0c2-5983dcb52160")
    private ModuleComponent moduleComponent;

    /**
     * Initialize the module.
     * @param desc the part descriptor
     */
    @objid ("01f6a492-b41d-498d-bdb0-7fb730ddcb34")
    public  GModule(GProjectPartDescriptor desc) {
        super(desc);
    }

    /**
     * Installs the module in the project.
     * <p>
     * Initializes the {@link IModuleHandle} and copies the backup archive into the project's data directory.
     * </p>
     */
    @objid ("3fc41424-35d9-4976-bb7e-9989ab2ab4b2")
    @Override
    public void install(IGProject aProject, IModelioProgress monitor) throws GPartException {
        try {
            super.install(aProject, monitor);
            this.moduleHandle = createModuleHandle(aProject, this.getId(), this.getVersion(), monitor);
        
            Path archive = this.moduleHandle.getArchive(); // The archive of the module to install
            Path backupPath = aProject.getPfs().getModuleBackupDir(this.getId()); // Where installed module archives are backed up
        
            // Install archive into backup directory
            if (archive != null) {
                boolean hasChanged = installArchiveInBackup(aProject, archive, backupPath);
            }
        } catch (IOException e) {
            setDown(e);
        }
        
    }

    /**
     * Unmount the part in the project making it 'down' in the project.<br/>
     * The umount applies immediately for the current project session.<br/>
     * The 'down' state of the part is <b>not</b> persisted in the project.conf file and the part will be mounted again at next project opening.
     * Fires a {@link GProjectEventType#FRAGMENT_DOWN FRAGMENT_DOWN} {@link GProjectEvent event}.
     * 
     * Implementors have to:
     * <ul>
     * <li>update the part state (see {@link GPartState#sendDown(Throwable)}).</li>
     * <li>report the down error in the state in case of failure</li>
     * <li>post GFailure to the project to log the umount failure cause</li>
     * </ul>
     * 
     * Please note that the {@link GPartState} will automatically fire {@link GProjectEvent} to the project event support when state changes.
     * @throws GPartException if the unmount fails.
     * @param error the cause of down state
     */
    @objid ("68ffb43f-bcf5-4445-b629-a5b11e96761a")
    public final void setDown(Throwable error) {
        assert error != null;
        
        if (this.state.getValue()==GPartStateEnum.DOWN) {
            // Ignore new error
            return;
        }
        
        if (this.mdaFragment != null) {
            try {
                this.mdaFragment.unmount(null);
            } catch (GPartException | RuntimeException e) {
                error.addSuppressed(e);
            }
        }
        
        this.state.sendDown(error);
        
    }

    /**
     * Si l'archive est deja copiee dans le backup
     * - on verifie sa date
     * - si la nouvelle archive est plus recente
     * - on remplace le backup
     * - on clean le data dir du module deja installe
     * Si l'archive n'est pas encore copiee dans le backup
     * - on rince le backup et on la copie
     * - on rince le data dir du fragment MDA du module deja installe si il y en a un
     */
    @objid ("e3636389-a148-4fe0-bb4f-561b63bcaaad")
    private boolean installArchiveInBackup(IGProject aProject, Path archive, Path backupPath) throws GPartException {
        Path existingArchive = backupPath.resolve(archive.getFileName());
        boolean hasToCopy = false;
        
        if (Files.isRegularFile(existingArchive)) {
            // Existing archive : check last modification date
            try {
                FileTime d1 = Files.getLastModifiedTime(archive);
                FileTime d2 = Files.getLastModifiedTime(existingArchive);
                if (d1.compareTo(d2) > 0) {
                    hasToCopy = true;
                }
            } catch (IOException e) {
                hasToCopy = true;
            }
        } else {
            // No existing archive
            hasToCopy = true;
        }
        
        if (hasToCopy) {
            // Clean the module backup directory for module an copy new archive
            try {
                FileUtils.delete(backupPath);
                Files.createDirectories(backupPath);
                Path localArchivePath = aProject.getPfs().getModuleBackupArchivePath(this.moduleHandle.getName(), this.moduleHandle.getVersion());
                Files.copy(this.moduleHandle.getArchive(), localArchivePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (SecurityException e) {
                throw new GPartException(e);
            } catch (IOException e) {
                throw new GPartException(FileUtils.getLocalizedMessage(e), e);
            }
        }
        return hasToCopy;
    }

    /**
     * Mount a GModule in project.
     * <p>
     * Mounting a module consists in mounting a model fragment containing the module MDA contents (profiles)
     * </p>
     */
    @objid ("0a57bf68-daac-4244-87dc-7a14ff1fc12d")
    @Override
    public void mount(IModelioProgress monitor) throws GPartException {
        this.state.sendStartMount();
        
        IGProject project = getProject();
        if (this.moduleHandle != null) {
            this.mdaFragment = createMdaFragment();
            if (this.mdaFragment != null) {
                project.addGPart(monitor, this.mdaFragment, false);
            } else {
                String msg = this.getId() + " " + getVersion() + " has no model fragment.";
                project.getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(project, msg));
            }
        
            this.state.sendEndMount(null);
        } else {
            this.state.sendEndMount(new Exception(String.format("GModule#mount(): Could not get module handle for %s %s", this.getId(), this.getVersion())));
        }
        
    }

    @objid ("ee8847c7-c2fe-4173-a962-e9afd911458f")
    public IGModelFragment getMdaFragment() {
        return this.mdaFragment;
    }

    /**
     * Unmount a GModule in project.
     * <p>
     * Unmounting a module consists in unmounting a model fragment containing the module MDA contents (profiles)
     * </p>
     */
    @objid ("a1d63a90-652e-489b-b106-80725bfcbbd2")
    @Override
    public void unmount(IModelioProgress monitor) throws GPartException {
        IGProject project = getProject();
        if (this.mdaFragment != null) {
            this.mdaFragment.unmount(monitor);
        } else {
            String msg = this.getId() + " " + getVersion() + " has no model fragment.";
            project.getMonitorSupport().fireMonitors(GProjectEvent.buildWarning(project, msg));
        }
        
        this.state.sendUnmount();
        
    }

    @objid ("f2d2de22-a126-4002-9b7a-c330c6a527ce")
    @Override
    public void uninstall(IGProject project, IModelioProgress monitor) throws GPartException {
        super.uninstall(project, monitor);
        this.moduleHandle = null;
        this.moduleComponent = null;
        if (this.mdaFragment != null) {
            project.removeGPart(monitor, this.mdaFragment);
            this.mdaFragment = null;
        } else {
            // MDA fragment is missing, make sure there are no remaining files in the project
            delete(project);
        }
        
    }

    @objid ("de7b8d78-b6f6-4f4a-9d41-195c896a9d80")
    private void delete(IGProject project) {
        String encodedDirectoryName = FileUtils.encodeFileName(getId(), new StringBuilder()).toString();
        String FRAGMENTS_SUBDIR = "fragments";
        
        ProjectFileStructure pfs = project.getPfs();
        Path runtimeDirectory = pfs.getProjectRuntimePath().resolve(FRAGMENTS_SUBDIR).resolve(encodedDirectoryName);
        try {
            FileUtils.delete(runtimeDirectory);
        } catch (@SuppressWarnings ("unused") SecurityException | IOException e) {
            // Just skip the error
        }
        
        Path dataDirectory = pfs.getProjectDataPath().resolve(FRAGMENTS_SUBDIR).resolve(encodedDirectoryName);
        try {
            FileUtils.delete(dataDirectory);
        } catch (@SuppressWarnings ("unused") SecurityException | IOException e) {
            // Just skip the error
        }
        
    }

    /**
     * Make the model part of the module, aka the MDA model RAMC fragment.
     */
    @objid ("67208125-90f1-4fed-ae16-4d6aee54de5a")
    private IGModelFragment createMdaFragment() {
        Path ramcPath = this.moduleHandle.getModelComponentPath();
        if (ramcPath != null) {
            GProjectPartDescriptor d = new GProjectPartDescriptor(
                    GProjectPartType.RAMC,
                    this.moduleHandle.getName(),
                    this.moduleHandle.getVersion(),
                    getDefinitionScope());
            d.setLocation(ramcPath.toUri());
            d.setAuth(new AuthDescriptor(getAuth()));
            return (IGModelFragment) GPartFactory.getInstance().instantiate(d);
        } else {
            return null;
        }
        
    }

    /**
     * Get a module handle from a module descriptor in the context of 'project'.
     * @param project the project context
     * @param name the name of the module
     * @param version the version of the module
     * @param progress the monitor monitor to use for reporting monitor to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that the
     * operation cannot be cancelled.
     * @return the module handle
     * @throws FileSystemException in case of file system error getting the module archive
     * @throws IOException in case of failure getting the module archive
     */
    @objid ("6ad99ec7-35a2-41db-b416-889c2a924c7f")
    IModuleHandle createModuleHandle(IGProject project, String name, Version version, IModelioProgress progress) throws FileSystemException, IOException {
        // First look in module cache
        IModuleRTCache moduleCache = project.getProjectEnvironment().getModulesCache();
        IModuleHandle mh = moduleCache.findModule(name, version.toString("V.R.C"), progress);
        
        // If the RT cache was not able to provide a handle try to install it in the cache from the local backup
        if (mh == null) {
            Path backupPath = project.getPfs().getModuleBackupArchivePath(name, version);
            mh = moduleCache.installModuleArchive(backupPath, progress);
        }
        
        // Last resort: restore from initial configuration
        if (mh == null) {
            URI archiveURI = this.getDescriptor().getLocation();
            if (archiveURI != null) {
                // try to get the original archive and copy it to local
                IAuthData authData = resolveAuthData();
                try {
                    Path backupPath = project.getPfs().getModuleBackupArchivePath(name, version);
                    copyJmdacToModuleDir(project.getPfs().getProjectDataPath(), archiveURI, authData, backupPath);
                    if (Files.isRegularFile(backupPath)) {
                        mh = moduleCache.installModuleArchive(backupPath, progress);
                    }
                } catch (FileNotFoundException | NoSuchFileException e) {
                    // log and ignore
                    Log.warning("'%s' module '%s' URI is invalid: %s", name, archiveURI, FileUtils.getLocalizedMessage(e));
                }
            }
        }
        
        // Get the handle from the cache (last attempt)
        if (mh == null) {
            mh = moduleCache.findModule(name, version.toString("V.R.C"), progress);
        }
        return mh == null ? new EmptyModuleHandle(name, version) : mh;
    }

    /**
     * Copy the URI content to a given Path .
     * <p>
     * <li>If the URI represents a file directory, uses the represented Path.
     * <li>If the URI represents a relative path, resolve it against the project path.
     * <li>In the other case try to open an URL connection to copy the file to the given file path.
     * @param anUri the URI to copy.
     * @param copyTo the path to copy the URI content to.
     * @throws IOException if the URI couldn't be resolved.
     */
    @objid ("0bdedc8f-1f63-4046-992a-cab3d87f21b2")
    protected boolean copyJmdacToModuleDir(Path projectDataPath, URI anUri, IAuthData authData, Path copyTo) throws IOException {
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
                fsPath = projectDataPath.resolve(anUri.getPath());
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
     * @return the name of the module in its {@link IModuleHandle}
     * @deprecated use {@link #getId()} instead.
     */
    @objid ("4e91e7c3-345a-4f2c-934c-3f346f80d3c7")
    @Deprecated
    public String getName() {
        return this.moduleHandle != null ? this.moduleHandle.getName() : "";
    }

    /**
     * Get the base structure defining the module contents.
     * @return the {@link IModuleHandle} for this {@link GModule}. Might be <code>null</code> if the module has been uninstalled.
     */
    @objid ("2b760f1e-4e91-41f9-bcbd-71151151f3bb")
    public IModuleHandle getModuleHandle() {
        return this.moduleHandle;
    }

    /**
     * Get the Module model element.
     * @return the module model element.
     */
    @objid ("e0b54506-e0ab-4916-9c2e-82f93a8d81f7")
    public ModuleComponent getModuleElement() {
        if (this.moduleComponent == null) {
            try {
                String uid = getModuleHandle().getUid();
                IRepository repository = this.mdaFragment != null ? this.mdaFragment.getRepository() : null;
                if (repository != null) {
                    SmMetamodel mm = getProject().getSession().getMetamodel();
                    this.moduleComponent = (ModuleComponent) repository.findById(mm.getMClass(ModuleComponent.class), uid);
                }
            } catch (IllegalArgumentException e) {
                // Invalid uuid, no module component to return
            }
        }
        return this.moduleComponent;
    }

}
