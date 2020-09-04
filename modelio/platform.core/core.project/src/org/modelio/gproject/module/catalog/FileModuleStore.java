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

package org.modelio.gproject.module.catalog;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.files.Unzipper;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.model.spi.IGMetamodelExtension;

/**
 * File and directory based implementation of IModuleStore
 * 
 * This module store structure is based on directories and files. The store maintains one directory by module (name+version).</br>
 * Both the original module archive and an unzipped file structure are kept for each stored module.<br/>
 * This structure helps saving the cost of unzipping/parsing the module archive each time a module is needed. Furthermore, finding all the modules remains a costly opreation as it consists in looping in all the store module directories. This is why an
 * internal cache is managed as a Map of FileModuleStoreEntry.
 * <p>
 * Needs a writable directory to store unzipped .jmdacs into.
 * </p>
 * <p>
 * For now, modules are stored in the cache as a directory $(jmdac name)_$(jmdac version).
 * </p>
 * <p>
 * When installing a module into the store, a new directory is ALWAYS created.
 * </p>
 */
@objid ("8e51c484-8f66-4139-91f4-0f1b7a3b1ccf")
public class FileModuleStore implements IModuleStore {
    /**
     * Cache structure version: changing this version will clean the cache.
     */
    @objid ("677c29aa-29ac-4343-9596-4c5fd20400ed")
    private static final int VERSION = 4;

/*
     * The cache state
     */
    @objid ("f88e2410-3d7c-4b86-962a-a1e799926a0a")
    protected CacheState state = CacheState.INITIAL;

    @objid ("441532fc-54db-452d-8953-c9b42cfd4b6c")
    protected Path cachePath;

    @objid ("31d6da48-2da5-4263-8813-f8f7a2457973")
    protected final Map<String, FileModuleStoreEntry> entries;

    @objid ("27eb6c91-a6bb-4de5-b04f-f145c197ed9a")
    protected final Collection<IGMetamodelExtension> metamodelExtensions;

    /**
     * Instantiate a new FileModuleCatalog.
     * 
     * @param metamodelFragments the metamodel fragments to use
     * @param cachePath the path to store unzipped .jmdacs into. Needs to be a writable directory.
     */
    @objid ("8e17727a-2852-4bc3-a5d6-5e3d6522c7e5")
    public FileModuleStore(Collection<IGMetamodelExtension> metamodelFragments, Path cachePath) {
        this.metamodelExtensions = metamodelFragments;
        this.cachePath = cachePath;
        
        if (!Files.isDirectory(cachePath)) {
            try {
                Files.createDirectories(cachePath);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        this.entries = new HashMap<>();
        this.state = CacheState.INITIAL;
        migration();
    }

    @objid ("b8063de3-7e52-4cc0-93ba-26fa46227682")
    @Override
    public List<IModuleHandle> findAllModules(IModelioProgress monitor) throws IOException {
        if (this.state == CacheState.INITIAL) {
            load(monitor);
        }
        
        List<IModuleHandle> ret = new ArrayList<>();
        for (FileModuleStoreEntry entry : this.entries.values()) {
            ret.add(entry.getModuleHandle(monitor));
        }
        return ret;
    }

    @objid ("9db9b274-6f44-40f1-b4d5-bfed06617ffb")
    @Override
    public IModuleHandle findModule(String moduleName, String moduleVersion, IModelioProgress monitor) throws IOException {
        // When moduleVersion is null, look up for the latest (higher) version
        if (moduleVersion == null) {
            IModuleHandle guessed = findLatest(moduleName);
            if (guessed != null) {
                // Recursive call with the latest version
                return findModule(guessed.getName(), guessed.getVersion().toString(), monitor);
            } else {
                // Module is not available at all in the store
                return null;
            }
        }
        
        // First lookup in the entries cache
        if (this.state == CacheState.INITIAL) {
            load(monitor);
        }
        
        String key = getEntryKey(moduleName, moduleVersion);
        return (this.entries.get(key) != null) ? this.entries.get(key).getModuleHandle(monitor) : null;
    }

    @objid ("8937f15c-a430-4def-8df5-ac38e827e7db")
    @Override
    public List<IModuleHandle> findModule(String moduleName, IModelioProgress monitor) throws FileSystemException, IOException {
        List<IModuleHandle> results = new ArrayList<>();
        
        for (IModuleHandle mh : findAllModules(monitor)) {
            if (Objects.equals(mh.getName(), moduleName)) {
                results.add(mh);
            }
        }
        return results;
    }

    @objid ("ed5cd02e-00a6-44b5-a554-61bd3106c20a")
    @Override
    public IModuleHandle findModule(Path archive, IModelioProgress monitor) throws FileSystemException, IOException {
        // poorly inefficient temporary implementation
        
        IModuleHandle moduleHandle = null;
        
        // Check if the jmdac is valid
        if (Files.notExists(archive)) {
            return null;
        }
        SubProgress m = SubProgress.convert(monitor, 100);
        
        // Unzip module
        Path extractionDir = Files.createTempDirectory("temp");
        new Unzipper()
                .setProgressLabelPrefix(CoreProject.I18N.getMessage("FileModuleStore.unzippingJMdac", archive.getFileName()))
                .unzip(archive, extractionDir, m.newChild(60));
        
        // Create a temporary ModuleHandle
        IModuleHandle tempModuleHandle = new FileModuleStoreEntry(extractionDir, this.metamodelExtensions).getModuleHandle(m);
        
        // return new module handle if exists in cache
        moduleHandle = findModule(tempModuleHandle.getName(), tempModuleHandle.getVersion().toString(), monitor);
        
        // Clean up extraction dir
        FileUtils.delete(extractionDir);
        
        m.setWorkRemaining(40);
        return moduleHandle;
    }

    /**
     * Find all modules with the given name.
     * 
     * @return the module cache path
     */
    @objid ("e9f4408c-6779-4901-ba99-2d54c726e6b4")
    protected final Path getCachePath() {
        return this.cachePath;
    }

    /**
     * @return the configured metamodel fragments.
     */
    @objid ("fd35e3b1-4ce3-4fb0-8875-9935c48b755d")
    protected final Collection<IGMetamodelExtension> getMetamodelExtensions() {
        return this.metamodelExtensions;
    }

    /**
     * Install a physical module archive file into the store.
     * <p>
     * Replaces the existing entry if necessary.
     * </p>
     */
    @objid ("52c6fcf8-b6f5-450f-9054-ef5b7d9b9bda")
    @Override
    public IModuleHandle installModuleArchive(Path archive, IModelioProgress monitor) throws IOException {
        // Check if the jmdac archive is valid otherwise abort
        if (Files.notExists(archive)) {
            return null;
        }
        SubProgress m = SubProgress.convert(monitor, 100);
        
        // Unzip module archive in a temporary directory
        Path extractionDir = Files.createTempDirectory("temp");
        new Unzipper()
                .setProgressLabelPrefix(CoreProject.I18N.getMessage("FileModuleStore.unzippingJMdac", archive.getFileName()))
                .unzip(archive, extractionDir, m.newChild(60));
        
        // Get the temporary store entry and module handle
        FileModuleStoreEntry tempEntry = new FileModuleStoreEntry(extractionDir, this.metamodelExtensions);
        IModuleHandle tempHandle = tempEntry.getModuleHandle(m.newChild(40));
        
        // Move extracted contents in the proper directory in the store
        Path moduleStorePath = computeNewModuleStorePath(tempHandle);
        
        // Create parent directory in the store if required
        if (!Files.isDirectory(moduleStorePath.getParent())) {
            Files.createDirectories(moduleStorePath.getParent());
        }
        
        // Clean existing contents if exist
        if (Files.exists(moduleStorePath)) {
            FileUtils.delete(moduleStorePath);
        }
        
        // Move extracted files
        FileUtils.move(extractionDir, moduleStorePath);
        
        // Copy the archive itself
        String moduleArchiveName = computeModuleArchiveName(tempHandle);
        Files.copy(archive, moduleStorePath.resolve(moduleArchiveName));
        
        // Update the cache
        FileModuleStoreEntry entry = new FileModuleStoreEntry(moduleStorePath, this.metamodelExtensions);
        String key = getEntryKey(tempHandle.getName(), tempHandle.getVersion().toString());
        
        if (this.state == CacheState.VALID) {
            this.entries.put(key, entry);
        }
        return entry.getModuleHandle(m.newChild(20));
    }

    /**
     * Remove a module from the store given its current module handle
     */
    @objid ("23c99ccb-b34f-4e11-8f2f-22b991aee931")
    @Override
    public void removeModule(IModuleHandle mh) throws FileSystemException, IOException {
        // Update the cache
        if (this.state == CacheState.VALID) {
            String key = getEntryKey(mh.getName(), mh.getVersion().toString());
            this.entries.remove(key);
        }
        
        // Delete store directory entry
        Path path = ((FileModuleStoreHandle) mh).getModuleCachePath();
        Path path1 = path.getParent();
        FileUtils.delete(path1);
    }

    /**
     * @param cachePath the module cache path.
     */
    @objid ("cf13c850-e8c1-4008-a23b-7619bbffbfe7")
    public final void setCachePath(Path cachePath) {
        this.cachePath = cachePath;
    }

    @objid ("eaa4ee81-08b0-4314-9d0e-233cb4733285")
    protected String computeModuleArchiveName(IModuleHandle moduleHandle) {
        return moduleHandle.getName() + "_" + moduleHandle.getVersion().toString("V.R.C") + ".jmdac";
    }

    @objid ("a46d052f-71e2-425c-95e9-6075d77af3bd")
    protected Path computeModuleCachePath(String moduleName, String moduleVersion) {
        return this.cachePath.resolve(moduleName + "_" + moduleVersion);
    }

    @objid ("a325556e-04c2-4e92-bea2-4d02bab7964d")
    protected Path computeNewModuleStorePath(IModuleHandle moduleHandle) {
        return computeModuleCachePath(moduleHandle.getName(), moduleHandle.getVersion().toString("V.R.C"));
    }

    /**
     * Instantiate a new ModuleHandle from a .jmdac archive file. Returns a zip file system
     * 
     * @param zipPath to construct the file system from
     * @param create true if the zip file should be created
     * @return a zip file system
     * @throws java.io.IOException in case of failure
     */
    @objid ("47ce0cea-c8ad-4559-8969-95fa8ce3eb38")
    protected FileSystem createZipFileSystem(Path zipPath, boolean create) throws IOException {
        // convert the filename to a URI
        final URI uri = URI.create("jar:file:" + zipPath.toUri().getPath());
        
        final Map<String, String> env = new HashMap<>();
        if (create) {
            env.put("create", "true");
        }
        return FileSystems.newFileSystem(uri, env);
    }

    @objid ("efb22b75-c8f4-4e58-a398-ec6907edcbf4")
    protected final String getEntryKey(String moduleName, String moduleVersion) {
        return moduleName + moduleVersion;
    }

    @objid ("987aa411-f242-4885-af11-aae462474c79")
    protected boolean isFileModuleStoreEntry(Path moduleCachePath) {
        if (!Files.exists(moduleCachePath)) {
            return false;
        }
        
        if (!Files.isDirectory(moduleCachePath)) {
            return false;
        }
        return true;
    }

    @objid ("08a103e0-3118-4a19-90f5-e52a38f3a021")
    protected void migration() {
        if (!Files.isDirectory(this.cachePath)) {
            return;
        }
        
        Path confFile = getConfigFile();
        Properties props = new Properties();
        
        int version;
        // Read catalog version
        try (InputStream is = Files.newInputStream(confFile)) {
            props.load(is);
            version = Integer.valueOf(props.getProperty("version", "0"));
        } catch (@SuppressWarnings ("unused") NoSuchFileException | NumberFormatException e) {
            // ignore, use 0 as default value
            version = 0;
        } catch (IOException e) {
            // unable to read configuration file, log and abort
            Log.warning(e);
            version = 0;
        }
        
        boolean isMigrationNeeded = version < getRequiredConfVersion();
        if (isMigrationNeeded) {
            try {
                Log.trace("'" + this.cachePath + "' module catalog version is " + version + " instead of " + getRequiredConfVersion() + ", cleaning the catalog...");
        
                // Workaround for windows: we have to move the old cache to a
                // temporary directory before deleting it, in order
                // to avoid an AccessDeniedException when creating the new
                // directory...
                Path tmpDir = Files.createTempDirectory("ModuleCatalog");
                FileUtils.move(this.cachePath, tmpDir);
                FileUtils.delete(tmpDir);
        
                // Create the catalog directory again
                Files.createDirectories(this.cachePath);
        
                // Write the version
                props.setProperty("version", String.valueOf(getRequiredConfVersion()));
        
                try (OutputStream out = Files.newOutputStream(confFile)) {
                    props.store(out, "Module catalog");
                }
                Log.trace("'" + this.cachePath + "' module catalog cleaning done");
            } catch (FileSystemException e) {
                Log.warning("Cannot migrate '" + this.cachePath + "' module catalog from " + version + " to " + getRequiredConfVersion());
                Log.warning(FileUtils.getLocalizedMessage(e));
                Log.warning(e);
            } catch (IOException e) {
                Log.warning("Cannot migrate '" + this.cachePath + "' module catalog from " + version + " to " + getRequiredConfVersion());
                Log.warning(e);
            }
        }
    }

    @objid ("09943088-a2e9-4bb6-b6a5-9bac14e48262")
    protected IModuleHandle findLatest(String moduleName) throws FileSystemException, IOException {
        IModuleHandle candidate = null;
        for (IModuleHandle mh : findModule(moduleName, null)) {
            if (candidate == null || candidate.getVersion().isOlderThan(mh.getVersion())) {
                candidate = mh;
            }
        }
        return candidate;
    }

    /**
     * @return the version of the configuration file handled by the current store implementation.
     */
    @objid ("5869fa53-689c-4990-84a6-402db0b63b47")
    protected int getRequiredConfVersion() {
        return FileModuleStore.VERSION;
    }

    /**
     * @return the configuration file to read for the current store implementation.
     */
    @objid ("9cbad662-ee51-4e45-b58e-aef99a7a9399")
    protected Path getConfigFile() {
        return this.cachePath.resolve("version.dat");
    }

    /**
     * Load the store into the 'entries' cache
     * @param monitor
     */
    @objid ("523345c8-d7c4-4ced-9935-d9bb1f62608c")
    protected void load(IModelioProgress monitor) {
        this.entries.clear();
        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path file) throws IOException {
                return Files.isDirectory(file);
            }
        };
        
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(this.cachePath, filter)) {
            for (Path p : stream) {
                try {
                    FileModuleStoreEntry entry = new FileModuleStoreEntry(p.toAbsolutePath(), this.metamodelExtensions);
                    IModuleHandle mh = entry.getModuleHandle(monitor);
                    String key = getEntryKey(mh.getName(), mh.getVersion().toString());
                    this.entries.put(key, entry);
                } catch (IOException e) {
                    Log.warning(e);
                }
            }
            this.state = CacheState.VALID;
        } catch (IOException e) {
            Log.warning(e);
            this.state = CacheState.INITIAL;
        }
    }

    /**
     * FileModuleStore entry: this class manages an extracted module directory.
     */
    @objid ("de703be5-0b74-4f31-bc34-8f9069119d08")
    protected static final class FileModuleStoreEntry {
        @objid ("166332f9-7e4e-469c-9e07-47c170d525c1")
        private final Collection<IGMetamodelExtension> metamodelExtensions;

        /**
         * The path of the represented entry
         */
        @objid ("4ef8c8c7-fd50-4f8e-b219-31ef9d25025a")
        private final Path entryPath;

        /**
         * The path of the module expanded data file (a directory)
         */
        @objid ("1416fd43-535d-489b-b569-7a6c781a53c6")
        private final Path datapath;

        /**
         * A lazily computed IModuleHandle
         */
        @objid ("54311aa2-353d-4bc2-b943-7e8ea768c1e3")
        private IModuleHandle moduleHandle;

        /**
         * @param entryPath the extracted module path
         * @param metamodelFragments the metamodel fragments
         * @throws java.io.IOException on failure finding a directory in the extracted module path.
         */
        @objid ("faf4dc9d-a5af-4ddf-898d-52fa95a022fd")
        public FileModuleStoreEntry(Path entryPath, Collection<IGMetamodelExtension> metamodelFragments) throws IOException {
            this.entryPath = entryPath;
            this.datapath = getModuleContentsPath(entryPath);
            this.metamodelExtensions = metamodelFragments;
        }

        /**
         * Get the module handle for this entry
         * 
         * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported and that
         * the
         * operation cannot be cancelled.
         * @return the module handle
         * @throws java.io.IOException in case of failure.
         */
        @objid ("b7eb1bfc-94ff-4bbe-9d9d-71174d7800de")
        public IModuleHandle getModuleHandle(IModelioProgress monitor) throws IOException {
            if (this.moduleHandle == null) {
            
                this.moduleHandle = new ModuleXmlExtractor(
                        this.datapath.resolve("module.xml"), 
                        this.datapath, 
                        this.metamodelExtensions)
                        .getModuleHandle(monitor);
            
            }
            return this.moduleHandle;
        }

        @objid ("94ac5d96-5db8-4433-807e-726daaf2544f")
        private Path getModuleContentsPath(Path aPath) throws IOException {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(aPath)) {
                for (Path p : stream) {
                    if (Files.isDirectory(p)) {
                        return p;
                    }
                }
            }
            
            throw new NoSuchFileException(aPath.toString(), null,
                    CoreProject.I18N.getMessage("FileModuleStoreEntry.doesNotContainDirectory", aPath));
        }

        /**
         * This method ensures that the computed files (ramc, xml ) are properly installed in the entry. The <code>normalizeEntryContents</code> method is designed to parse the module.xml file and to create the module RAMC file only when required because
         * these operations are expensive.
         * @param monitor
         * @throws IOException
         */
        @objid ("f5af2f30-5833-4a24-8c5e-b95bcd9a6951")
        private void normalizeEntryContents(IModelioProgress monitor) throws IOException {
            new ModuleXmlExtractor(this.datapath.resolve("module.xml"), this.datapath, this.metamodelExtensions)
            .extractModuleXmlContent(monitor);
        }

    }

    /**
     * Cache states:
     * - INITIAL the cache is empty and has not been loaded yet.
     * - VALID the cache has been loaded and further operations (adding/removing) could update it in order to keep its contents valid.
     */
    @objid ("d5396a79-db7d-4e82-80f4-245c1314007f")
    protected enum CacheState {
        INITIAL,
        VALID;
    }

}
