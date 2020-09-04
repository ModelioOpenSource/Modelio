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

package org.modelio.gproject.module.rtcache;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.catalog.FileModuleStore;
import org.modelio.gproject.module.catalog.FileModuleStoreHandle;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.model.spi.IGMetamodelExtension;

/**
 * Extension of {@link FileModuleStore} systematically creating new directories when a module is installed in the cache, to avoid problems with locked jar files...
 * <p>
 * They are stored in the cache as a directory $(jmdac name)_$(jmdac version)/X, where X is the number of modules having the same version already installed in the cache.
 * </p>
 */
@objid ("2c95c248-f37d-11e1-a3c7-002564c97630")
class FileModuleAdditionStore extends FileModuleStore {
    /**
     * Cache structure version: changing this version will clean the cache.
     */
    @objid ("43401e6b-f9b3-4f86-82ad-6cbd93bcc0b7")
    private static final int CACHE_VERSION = 1;

    /**
     * Instantiate a new FileModuleCatalog.
     * 
     * @param metamodelFragments the metamodel fragments to use
     * @param cachePath the path to store unzipped .jmdacs into. Needs to be a writable directory.
     */
    @objid ("2c95c24a-f37d-11e1-a3c7-002564c97630")
    public FileModuleAdditionStore(Collection<IGMetamodelExtension> metamodelFragments, Path cachePath) {
        super(metamodelFragments, cachePath);
    }

    /**
     * Redefine the computeModuleCachePath() method in order to take the additional index of the module store path into account.
     */
    @objid ("e05cb9ac-7ab0-4153-96dc-14e0dd6d1c5f")
    @Override
    protected Path computeModuleCachePath(String moduleName, String moduleVersion) {
        Path baseModuleCachePath = this.cachePath.resolve(moduleName + "_" + moduleVersion);
        long index = computeModuleCachePathIndex(baseModuleCachePath);
        return baseModuleCachePath.resolve(Long.toString(index));
    }

    /**
     * Redefine the computeNewModuleStorePath() method in order to take the additional index of the module store path into account.
     */
    @objid ("08a37fb7-6e18-4d6d-866a-28b56ef9c730")
    @Override
    protected Path computeNewModuleStorePath(IModuleHandle moduleHandle) {
        Path baseModuleCachePath = this.cachePath.resolve(moduleHandle.getName() + "_" + moduleHandle.getVersion().toString("V.R.C"));
        long index = computeModuleCachePathIndex(baseModuleCachePath) + 1;
        return baseModuleCachePath.resolve(Long.toString(index));
    }

    /**
     * Get the next available index in the store directory for a module.
     * 
     * @param baseModuleCachePath @return
     */
    @objid ("e99e8d20-018f-4f81-8af6-1f1cb48ab164")
    protected long computeModuleCachePathIndex(Path baseModuleCachePath) {
        try {
            long count = Files.list(baseModuleCachePath).filter(p -> Files.isDirectory(p)).count();
            return count > 0 ? count - 1 : 0;
        } catch (@SuppressWarnings ("unused") IOException e) {
            // Assume it's -1
            return -1;
        }
    }

    @objid ("69f1aa55-26b6-40c4-84e9-83dd1e8e3a0b")
    @Override
    protected int getRequiredConfVersion() {
        return FileModuleAdditionStore.CACHE_VERSION;
    }

    @objid ("0a84ef54-9cd1-41b3-b8d9-be7ae473144b")
    @Override
    protected Path getConfigFile() {
        return this.cachePath.resolve("cacheversion.dat");
    }

    @objid ("aab52010-def8-4791-bc9e-6fe3b313f7e2")
    public IModuleHandle installModuleHandle(IModuleHandle handle, IModelioProgress monitor) throws FileSystemException, IOException {
        FileModuleStoreHandle h = (FileModuleStoreHandle) handle;
        Path dir = h.getModuleCachePath();
        Path dirParent = dir.getParent();
        
        // Move extracted contents in the proper directory in the catalog
        Path moduleCatalogPath = computeNewModuleStorePath(handle);
        
        // Create parent directory
        if (!Files.isDirectory(moduleCatalogPath.getParent())) {
            Files.createDirectories(moduleCatalogPath.getParent());
        }
        
        // Clean existing contents if exist
        if (Files.exists(moduleCatalogPath)) {
            FileUtils.delete(moduleCatalogPath);
        }
        
        // Move extracted files
        FileUtils.copyDirectoryTo(dirParent, moduleCatalogPath);
        
        // Update the cache
        FileModuleStoreEntry entry = new FileModuleStoreEntry(moduleCatalogPath, this.metamodelExtensions);
        String key = getEntryKey(handle.getName(), handle.getVersion().toString());
        
        if (this.state == CacheState.VALID) {
            this.entries.put(key, entry);
        }
        return entry.getModuleHandle(monitor);
    }

    /**
     * Redefine the load() method in order to take the additional index of the module store path into account.
     */
    @objid ("03680a68-9c99-45f8-ae4a-fba5f5d5fdc4")
    @Override
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
                long pathIndex = computeModuleCachePathIndex(p);
                Path entryPath = p.resolve(Long.toString(pathIndex)).toAbsolutePath();
        
                FileModuleStoreEntry entry = new FileModuleStoreEntry(entryPath, this.metamodelExtensions);
                IModuleHandle mh = entry.getModuleHandle(monitor);
                String key = getEntryKey(mh.getName(), mh.getVersion().toString());
                this.entries.put(key, entry);
        
            }
            this.state = CacheState.VALID;
        } catch (IOException e) {
            Log.warning(e);
            this.state = CacheState.INITIAL;
        }
    }

}
