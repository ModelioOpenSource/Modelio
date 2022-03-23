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
package org.modelio.gproject.module.catalog;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.vbasic.progress.IModelioProgress;

/**
 * Default module catalog that caches no module and access directly .jmdac archives.
 */
@objid ("38b38903-06f0-11e2-9469-001ec947ccaf")
public class EmptyModuleCatalog implements IModuleStore {
    @objid ("57cd5c28-06f3-11e2-9469-001ec947ccaf")
    private static final EmptyModuleCatalog INSTANCE = new EmptyModuleCatalog();

    /**
     * @return the catalog.
     */
    @objid ("57cd5c29-06f3-11e2-9469-001ec947ccaf")
    public static IModuleStore getInstance() {
        return EmptyModuleCatalog.INSTANCE;
    }

    @objid ("57cd5c34-06f3-11e2-9469-001ec947ccaf")
    private  EmptyModuleCatalog() {
        // Nothing
    }

    @objid ("d9518293-37da-11e2-8ba4-002564c97630")
    @Override
    public IModuleHandle findModule(String moduleName, String moduleVersion, IModelioProgress monitor) {
        // No modules are available...
        return null;
    }

    @objid ("b59334f8-452a-11e2-aeb7-002564c97630")
    @Override
    public List<IModuleHandle> findAllModules(IModelioProgress monitor) throws IOException {
        // No modules are available...
        return Collections.emptyList();
    }

    @objid ("4a2883ae-5a9e-4b53-9038-100a441971f6")
    @Override
    public void removeModule(IModuleHandle mh) throws IOException {
        // nothing to do
    }

    @objid ("dc8cce9f-203f-44d0-896d-75a194184a27")
    @Override
    public IModuleHandle installModuleArchive(Path archive, IModelioProgress monitor) throws IOException {
        return null;
    }

    @objid ("dc47d954-d9f8-4f40-a185-b10193b971f1")
    @Override
    public IModuleHandle findModule(Path archivePath, IModelioProgress monitor) throws FileSystemException, IOException {
        return null;
    }

    @objid ("487f7fea-64f8-45df-ad1a-8db4e051a8cf")
    @Override
    public List<IModuleHandle> findModule(String moduleName, IModelioProgress monitor) throws FileSystemException, IOException {
        return Collections.emptyList();
    }

}
