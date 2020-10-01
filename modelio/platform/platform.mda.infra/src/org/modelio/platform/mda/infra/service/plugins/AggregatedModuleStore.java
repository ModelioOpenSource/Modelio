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

package org.modelio.platform.mda.infra.service.plugins;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;

/**
 * Aggregate the plugins modules store and the modules store.
 * 
 * @author cma
 * @since 3.8
 */
@objid ("07bd7c42-91e3-4685-9d9c-19f576b2e7b8")
public class AggregatedModuleStore implements IModuleStore {
    @objid ("28b21162-dec2-4c71-8fef-cdb10e216865")
    private final IModuleStore pluginStore;

    @objid ("5fb1f6ef-739e-4523-b6d4-a9023b7c5878")
    private final IModuleStore modulesStore;

    @objid ("034574ae-283a-47f6-95a6-19f13aae9668")
    public AggregatedModuleStore(IModuleStore pluginStore, IModuleStore modulesStore) {
        this.pluginStore = Objects.requireNonNull(pluginStore);
        this.modulesStore = Objects.requireNonNull(modulesStore);
    }

    @objid ("a456d840-8d00-438e-8f62-3468e99a519e")
    @Override
    public IModuleHandle installModuleArchive(Path archive, IModelioProgress monitor) throws IOException {
        return this.modulesStore.installModuleArchive(archive, monitor);
    }

    @objid ("15283f48-9d54-4eac-bca7-37c2084583f7")
    @Override
    public void removeModule(IModuleHandle mh) throws FileSystemException, IOException {
        this.modulesStore.removeModule(mh);
    }

    @objid ("88289ab6-2936-4a52-8911-ca3da8a86966")
    @Override
    public List<IModuleHandle> findAllModules(IModelioProgress monitor) throws FileSystemException, IOException {
        SubProgress mon = SubProgress.convert(monitor, 5);
        List<IModuleHandle> ret = new ArrayList<>();
        
        ret.addAll(this.modulesStore.findAllModules(mon.newChild(4)));
        ret.addAll(this.pluginStore.findAllModules(mon.newChild(1)));
        return ret;
    }

    @objid ("16031d77-64f6-4a16-8b56-46efb7fda0d2")
    @Override
    public IModuleHandle findModule(Path archivePath, IModelioProgress monitor) throws FileSystemException, IOException {
        SubProgress mon = SubProgress.convert(monitor, 5);
        IModuleHandle ret = this.pluginStore.findModule(archivePath, mon.newChild(1));
        if (ret != null) {
            return ret;
        }
        
        ret = this.modulesStore.findModule(archivePath, monitor);
        return ret;
    }

    @objid ("32c7c8f2-4216-437a-9c67-942288092cd2")
    @Override
    public IModuleHandle findModule(String moduleName, String moduleVersion, IModelioProgress monitor) throws FileSystemException, IOException {
        SubProgress mon = SubProgress.convert(monitor, 5);
        IModuleHandle ret = this.pluginStore.findModule(moduleName, moduleVersion, mon.newChild(1));
        if (ret != null) {
            return ret;
        }
        
        ret = this.modulesStore.findModule(moduleName, moduleVersion, monitor);
        return ret;
    }

    @objid ("05006c0e-5405-4e4b-9cda-46cb9cbba2f6")
    @Override
    public List<IModuleHandle> findModule(String moduleName, IModelioProgress monitor) throws FileSystemException, IOException {
        SubProgress mon = SubProgress.convert(monitor, 5);
        List<IModuleHandle> ret = this.pluginStore.findModule(moduleName, mon.newChild(1));
        if (!ret.isEmpty()) {
            return ret;
        }
        
        ret = this.modulesStore.findModule(moduleName, monitor);
        return ret;
    }

}
