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

package org.modelio.gproject.module.rtcache;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.model.spi.IGMetamodelExtension;

/**
 * Cache for a file module catalog.
 * <p>
 * Works by copying in a cache directory the module files.
 */
@objid ("a731a18e-dfa0-4d58-a433-71ab0367c395")
public class ModuleRTCache implements IModuleRTCache {
    @objid ("3b707c01-d763-4a72-ab8a-16161c063e52")
    private IModuleStore catalog;

    @objid ("1a6c18bf-f509-4220-a9c9-9d7033e54abd")
    private FileModuleAdditionStore cache;

    /**
     * C'tor the module catalog cache.
     * @param catalog the cached catalog
     * @param metamodelFragments the metamodel fragments to use
     * @param cachePath the cache path
     */
    @objid ("08d572ed-641d-4243-81b5-6ea4bd7fc40a")
    public ModuleRTCache(IModuleStore catalog, Collection<IGMetamodelExtension> metamodelFragments, Path cachePath) {
        this.catalog = catalog;
        this.cache = new FileModuleAdditionStore(metamodelFragments, cachePath);
    }

    @objid ("5f6ac1b3-e763-4e70-81a4-e06123f2e5a3")
    @Override
    public IModuleHandle installModuleArchive(Path archivePath, IModelioProgress monitor) throws IOException {
        return this.cache.installModuleArchive(archivePath, monitor);
    }

    @objid ("ecf4f942-0ad6-4471-964f-bfa9d3131db6")
    @Override
    public IModuleHandle findModule(String name, String version, IModelioProgress monitor) throws IOException {
        IModuleHandle moduleHandle = this.cache.findModule(name, version, monitor);
        if (moduleHandle == null) {
            IModuleHandle catalogModuleHandle = this.catalog.findModule(name, version, monitor);
            if (catalogModuleHandle != null) {
                moduleHandle = this.cache.installModuleHandle(catalogModuleHandle, monitor);
            }
        }
        return moduleHandle;
    }

    @objid ("4786bf8a-ddd3-430a-bd7e-c699278d26b1")
    public IModuleStore getCatalog() {
        return this.catalog;
    }

}
